package org.nasdanika.cli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.Adaptable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import picocli.CommandLine;
import picocli.CommandLine.Model.CommandSpec;

/**
 * Base class for sub-command factories.
 * Creates {@link CommandLine} from the command object, adds sub-commands and mix-ins
 */
public abstract class SubCommandCapabilityFactory<T> extends ServiceCapabilityFactory<SubCommandRequirement, CommandLine> {
	
	private static Logger LOGGER = LoggerFactory.getLogger(SubCommandCapabilityFactory.class);

	@Override
	public boolean isFor(Class<?> type, Object requirement) {
		return CommandLine.class == type;
	}
	
	private record CommandLineAndPath(CommandLine commandLine, List<CommandLine> path) {};
	
	public static final int DEFAULT_MAX_COMMAND_PATH = 50;
	
	protected int getMaxPath() {
		return DEFAULT_MAX_COMMAND_PATH;
	}
	
	@Override
	protected CompletionStage<Iterable<CapabilityProvider<CommandLine>>> createService(
			Class<CommandLine> serviceType,
			SubCommandRequirement serviceRequirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		List<CommandLine> parentPath = serviceRequirement.parentPath();
		if (parentPath == null || parentPath.size() <= getMaxPath()) {
			CompletionStage<T> commandCS = createCommand(parentPath, loader, progressMonitor);
			if (commandCS != null) {
				CompletionStage<CommandLineAndPath> commandLineAndPathCS = commandCS.thenApply(command -> createCommandLine(command, serviceRequirement, progressMonitor));
				CompletionStage<Iterable<CapabilityProvider<CommandLine>>> subCommandsCS = commandLineAndPathCS.thenCompose(
						commandLineAndPath -> createSubCommands(
								commandLineAndPath == null ? null : commandLineAndPath.path(),
								loader,
								progressMonitor));
				
				CompletionStage<Iterable<CapabilityProvider<MixInRecord>>> mixInsCS = commandLineAndPathCS.thenCompose(
						commandLineAndPath -> createMixIns(
								commandLineAndPath == null ? null : commandLineAndPath.path(),
								loader,
								progressMonitor));
				
				CompletionStage<CommandLine> commandLineWithSubCommandsCS = commandLineAndPathCS.thenCombine(subCommandsCS, this::combineSubCommands);
				CompletionStage<CommandLine> commandLineWithSubCommandsAndMixInsCS = commandLineWithSubCommandsCS.thenCombine(mixInsCS, this::combineMixIns);
				CompletionStage<CommandLine> loggingCS = commandLineWithSubCommandsAndMixInsCS.thenApply(cl -> {
					if (parentPath.isEmpty()) {
						LOGGER.info("Created command line {}", cl.getCommandName());						
					} else {
						StringBuilder commandPath = new StringBuilder();
						for (CommandLine pathElement: parentPath) {
							if (commandPath.length() > 0) {
								commandPath.append(" ");
							}
							commandPath.append(pathElement.getCommandName());
						}
						LOGGER.info("Created command line {}, parent path {}", cl.getCommandName(), commandPath.toString());						
					}
					return cl;
				});
				return wrapCompletionStage(loggingCS);
			}
		}
		
		return CompletableFuture.completedStage(Collections.emptyList());
	}
			
	protected CommandLineAndPath createCommandLine(
			T command, 
			SubCommandRequirement serviceRequirement,
			ProgressMonitor progressMonitor) {
		if (command == null) {
			return null;
		}
		List<CommandLine> parentPath = serviceRequirement.parentPath();
		CommandLine commandLine = new CommandLine(command);
		List<CommandLine> path = new ArrayList<CommandLine>();
		if (serviceRequirement.parentPath() != null) {
			path.addAll(parentPath);
		}
		path.add(commandLine);		
				
		return new CommandLineAndPath(commandLine, path);
	}
	
	protected CompletionStage<Iterable<CapabilityProvider<CommandLine>>> createSubCommands(
				List<CommandLine> path,
				Loader loader,
				ProgressMonitor progressMonitor) {		

		if (path == null) {
			return CompletableFuture.completedStage(null);
		}
		
		Requirement<SubCommandRequirement, CommandLine> subCommandRequirement = ServiceCapabilityFactory.createRequirement(CommandLine.class, null, new SubCommandRequirement(path));
		@SuppressWarnings({ "rawtypes", "unchecked" })
		CompletionStage<Iterable<CapabilityProvider<CommandLine>>> subCommandsCS = (CompletionStage) loader.load(subCommandRequirement, progressMonitor);
		return subCommandsCS;
	}
	
	private CommandLine combineSubCommands(
			CommandLineAndPath commandLineAndPath,
			Iterable<CapabilityProvider<CommandLine>> subCommandsProviders) {
		
		if (commandLineAndPath == null) {
			return null;
		}
		
		CommandLine commandLine = commandLineAndPath.commandLine();
		List<CommandLine> subCommands = new ArrayList<>();
		subCommandsProviders.forEach(scp -> scp.getPublisher().filter(Objects::nonNull).collectList().block().forEach(subCommands::add));
		subCommands.sort((a,b) -> a.getCommandName().compareTo(b.getCommandName()));
		for (Entry<String, List<CommandLine>> commandGroup: Util.groupBy(subCommands, CommandLine::getCommandName).entrySet()) {
			if (commandGroup.getValue().size() == 1) {
				commandGroup.getValue().forEach(commandLine::addSubcommand);				
			} else {
				// Selecting one of several if possible
				CommandLine[] sca = commandGroup.getValue().toArray(size -> new CommandLine[size]);
				Z: for (int i = 0; i < sca.length; ++i) {
					if (sca[i] != null) {
						for (int j = i + 1; j < sca.length; ++j) {
							if (sca[j] != null) {
								if (overrides(sca[i].getCommandSpec().userObject(), sca[j].getCommandSpec().userObject())) {
									sca[j] = null;
								} else if (overrides(sca[j].getCommandSpec().userObject(), sca[i].getCommandSpec().userObject())) {
									sca[i] = null;
									continue Z;		
								}
							}
						}
					}
				}
				for (CommandLine sc: sca) {
					if (sc != null) {
						commandLine.addSubcommand(sc);
					}
				}				
			}
		}
		return commandLine;
	};
	
	/**
	 * @param a
	 * @param b
	 * @return true if a overrides b
	 */
	protected boolean overrides(Object a, Object b) {
		if (a instanceof Overrider && ((Overrider) a).overrides(b)) {
			return true;
		}
		Class<?> aClass = a.getClass();
		Overrides ova = aClass.getAnnotation(Overrides.class);
		if (ova != null) {
			for (Class<?> oc: ova.value()) {
				if (oc.isInstance(b)) {
					return true;
				}
			}
		}
		
		Class<?> bClass = b.getClass();
		return bClass != aClass && bClass.isAssignableFrom(aClass);
	}
	
	protected CompletionStage<Iterable<CapabilityProvider<MixInRecord>>> createMixIns(
			List<CommandLine> path,
			Loader loader,
			ProgressMonitor progressMonitor) {
	
		if (path == null) {
			return CompletableFuture.completedStage(null);
		}
		
		Requirement<MixInRequirement, MixInRecord> mixInRequirement = ServiceCapabilityFactory.createRequirement(MixInRecord.class, null,  new MixInRequirement(path));
		@SuppressWarnings({ "rawtypes", "unchecked" })
		CompletionStage<Iterable<CapabilityProvider<MixInRecord>>> mixInsCS = (CompletionStage) loader.load(mixInRequirement, progressMonitor);
		return mixInsCS;
	}
	
	private CommandLine combineMixIns(
			CommandLine commandLine,
			Iterable<CapabilityProvider<MixInRecord>> mixInProviders) {
		
		if (commandLine == null) {
			return null;
		}

		List<MixInRecord> mixIns = new ArrayList<>();
		mixInProviders.forEach(mcp -> mcp.getPublisher().filter(Objects::nonNull).collectList().block().forEach(mixIns::add));		
		
		for (Entry<String, List<MixInRecord>> mixInGroup: Util.groupBy(mixIns, MixInRecord::name).entrySet()) {
			if (mixInGroup.getValue().size() == 1) {
				mixInGroup.getValue().forEach(mr -> commandLine.addMixin(mr.name(), mr.mixIn()));				
			} else {
				// Selecting one of several if possible
				MixInRecord[] mra = mixInGroup.getValue().toArray(size -> new MixInRecord[size]);
				Z: for (int i = 0; i < mra.length; ++i) {
					if (mra[i] != null) {
						for (int j = i + 1; j < mra.length; ++j) {
							if (mra[j] != null) {
								if (overrides(mra[i].mixIn(), mra[j].mixIn())) {
									mra[j] = null;
								} else if (overrides(mra[j].mixIn(), mra[i].mixIn())) {
									mra[i] = null;
									continue Z;		
								}
							}
						}
					}
				}
				for (MixInRecord mr: mra) {
					if (mr != null) {
						commandLine.addMixin(mr.name(), mr.mixIn());
					}
				}				
			}
		}
				
		return commandLine;
	};
	
	protected abstract Class<T> getCommandType();
	
	/**
	 * Matches command to parent using annotations.
	 * @param parentPath
	 * @return
	 */
	protected boolean match(List<CommandLine> parentPath) {
		if (parentPath == null || parentPath.isEmpty()) {
			return false;
		}
		
		CommandLine parent = parentPath.get(parentPath.size() - 1);
		Object userObject = parent.getCommandSpec().userObject();
		if (userObject != null) {
			Class<T> commandType = getCommandType();
			if (commandType != null) {
				List<SubCommands> subCommandsAnnotations = Util.lineage(userObject.getClass())
						.stream()
						.map(c -> c.getAnnotation(SubCommands.class))
						.filter(Objects::nonNull)
						.toList();
				
				for (SubCommands subCommandsAnnotation: subCommandsAnnotations) {
					for (Class<?> at: subCommandsAnnotation.value()) {					
						if (at.isAssignableFrom(commandType)) {
							return true;
						}
					}
				}
			}
			
			if (commandType != null) {
				for (Class<?> le: Util.lineage(commandType)) {
					ParentCommands parentCommands = le.getAnnotation(ParentCommands.class);
					if (parentCommands != null) {
						for (Class<?> pt: parentCommands.value()) {
							if (Adaptable.adaptTo(userObject, pt)  != null) {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Calls doCreateCommand if match returns true
	 * @param progressMonitor
	 * @return
	 */
	protected CompletionStage<T> createCommand(
			List<CommandLine> parentPath, 
			Loader loader,
			ProgressMonitor progressMonitor) {
		return match(parentPath) ? doCreateCommand(parentPath, loader, progressMonitor) : null;
	};
	
	/**
	 * Creates a command instance (user object) to be wrapped into {@link CommandLine}
	 * @param progressMonitor
	 * @return
	 */
	protected CompletionStage<T> doCreateCommand(
			List<CommandLine> parentPath, 
			Loader loader,
			ProgressMonitor progressMonitor) {
		return null;
	}

}
