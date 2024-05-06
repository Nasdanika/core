package org.nasdanika.cli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

/**
 * Base class for sub-command factories.
 * Creates {@link CommandLine} from the command object, adds sub-commands and mix-ins
 */
public abstract class SubCommandCapabilityFactory<T> extends ServiceCapabilityFactory<SubCommandRequirement, CommandLine> {

	@Override
	public boolean isForServiceType(Class<?> type) {
		return CommandLine.class == type;
	}
	
	private record CommandLineAndPath(CommandLine commandLine, List<CommandLine> path) {};
	
	@Override
	protected CompletionStage<Iterable<CapabilityProvider<CommandLine>>> createService(
			Class<CommandLine> serviceType,
			SubCommandRequirement serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		
		List<CommandLine> parentPath = serviceRequirement.parentPath();
		CompletionStage<T> commandCS = createCommand(parentPath, resolver, progressMonitor);
		if (commandCS != null) {
			CompletionStage<CommandLineAndPath> commandLineAndPathCS = commandCS.thenApply(command -> createCommandLine(command, serviceRequirement, progressMonitor));
			CompletionStage<Iterable<CapabilityProvider<CommandLine>>> subCommandsCS = commandLineAndPathCS.thenCompose(
					commandLineAndPath -> createSubCommands(
							commandLineAndPath.path(),
							resolver,
							progressMonitor));
			
			CompletionStage<Iterable<CapabilityProvider<MixInRecord>>> mixInsCS = commandLineAndPathCS.thenCompose(
					commandLineAndPath -> createMixIns(
							commandLineAndPath.path(),
							resolver,
							progressMonitor));
			
			CompletionStage<CommandLine> commandLineWithSubCommandsCS = commandLineAndPathCS.thenCombine(subCommandsCS, this::combineSubCommands);
			CompletionStage<CommandLine> commandLineWithSubCommandsAndMixInsCS = commandLineWithSubCommandsCS.thenCombine(mixInsCS, this::combineMixIns);
			return wrapCompletionStage(commandLineWithSubCommandsAndMixInsCS);
		}
		
		return CompletableFuture.completedStage(Collections.emptyList());
	}
	
	protected CommandLineAndPath createCommandLine(
			T command, 
			SubCommandRequirement serviceRequirement,
			ProgressMonitor progressMonitor) {
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
				BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
				ProgressMonitor progressMonitor) {
		
		Requirement<SubCommandRequirement, CommandLine> subCommandRequirement = ServiceCapabilityFactory.createRequirement(CommandLine.class, null, new SubCommandRequirement(path));
		@SuppressWarnings({ "rawtypes", "unchecked" })
		CompletionStage<Iterable<CapabilityProvider<CommandLine>>> subCommandsCS = (CompletionStage) resolver.apply(subCommandRequirement, progressMonitor);
		return subCommandsCS;
	}
	
	private CommandLine combineSubCommands(
			CommandLineAndPath commandLineAndPath,
			Iterable<CapabilityProvider<CommandLine>> subCommandsProviders) {
		CommandLine commandLine = commandLineAndPath.commandLine();
		List<CommandLine> subCommands = new ArrayList<>();
		subCommandsProviders.forEach(scp -> scp.getPublisher().subscribe(subCommands::add));
		// TODO - group by name, selection
		subCommands.sort((a,b) -> a.getCommandName().compareTo(b.getCommandName()));
		subCommands.forEach(commandLine::addSubcommand);
		return commandLine;
	};
	
	protected CompletionStage<Iterable<CapabilityProvider<MixInRecord>>> createMixIns(
			List<CommandLine> path,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
	
		Requirement<MixInRequirement, MixInRecord> mixInRequirement = ServiceCapabilityFactory.createRequirement(MixInRecord.class, null,  new MixInRequirement(path));
		@SuppressWarnings({ "rawtypes", "unchecked" })
		CompletionStage<Iterable<CapabilityProvider<MixInRecord>>> mixInsCS = (CompletionStage) resolver.apply(mixInRequirement, progressMonitor);
		return mixInsCS;
	}
	
	private CommandLine combineMixIns(
			CommandLine commandLine,
			Iterable<CapabilityProvider<MixInRecord>> mixInProviders) {
		// TODO - group mix-ins by name, selection
		mixInProviders.forEach(mcp -> mcp.getPublisher().subscribe(mr -> commandLine.addMixin(mr.name(), mr.mixIn())));		
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
			SubCommands subCommandsAnnotation = userObject.getClass().getAnnotation(SubCommands.class);
			Class<T> commandType = getCommandType();
			if (subCommandsAnnotation != null && commandType != null) {
				for (Class<?> at: subCommandsAnnotation.value()) {
					if (at.isAssignableFrom(commandType)) {
						return true;
					}
				}
			}
			
			if (commandType != null) {
				ParentCommands parentCommands = commandType.getAnnotation(ParentCommands.class);
				if (parentCommands != null) {
					for (Class<?> pt: parentCommands.value()) {
						if (pt.isInstance(userObject)) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
//	/**
//	 * Creates a list of  command instances (user objects) to be wrapped into {@link CommandLine}
//	 * @param progressMonitor
//	 * @return
//	 */
//	protected List<CompletionStage<T>> createCommands(
//			List<CommandLine> parentPath, 
//			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
//			ProgressMonitor progressMonitor) {
//		CompletionStage<T> command = createCommand(parentPath, resolver, progressMonitor);
//		return command == null ? Collections.emptyList() : Collections.singletonList(command);
//	}
	
	/**
	 * Calls doCreateCommand if match returns true
	 * @param progressMonitor
	 * @return
	 */
	protected CompletionStage<T> createCommand(
			List<CommandLine> parentPath, 
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		return match(parentPath) ? doCreateCommand(parentPath, resolver, progressMonitor) : null;
	};
	
	/**
	 * Creates a command instance (user object) to be wrapped into {@link CommandLine}
	 * @param progressMonitor
	 * @return
	 */
	protected CompletionStage<T> doCreateCommand(
			List<CommandLine> parentPath, 
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		return null;
	}

}
