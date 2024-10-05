package org.nasdanika.groovy;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.cli.CommandBase;
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.cli.ProgressMonitorMixIn;
import org.nasdanika.cli.PropertiesMixIn;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParentCommand;


@Command(
		description = "Groovy Shell",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "gsh")
@ParentCommands(Invocable.Invoker.class)
public class GroovyShellCommand extends CommandBase {
	
	private CapabilityLoader capabilityLoader;

	public GroovyShellCommand(CapabilityLoader capablityLoader) {
		this.capabilityLoader = capablityLoader;
	}
	
	@Parameters(
		arity = "*",
		description = "Argument URIs")
	private String[] args;		
	
	@Mixin
	private PropertiesMixIn propertiesMixIn;
	
	@ParentCommand
	private Object parentCommand;
	
	@Mixin
	private ProgressMonitorMixIn progressMonitorMixIn;
	
	protected Invocable getInvocable() {
		try {
			int argsLength = args == null ? 0 : args.length;
			URI base = URI.createFileURI(new File(".").getAbsolutePath());		
			try (ProgressMonitor progressMonitor = progressMonitorMixIn.createProgressMonitor(2 + argsLength)) {
				Invocable invocable = new GroovyShellInvocable();
				try (ProgressMonitor propertiesMonitor = progressMonitor.split("Loading properties", 1)) {
					Function<Object, String> keyMapper = key -> {
						if (key instanceof String) {
							return (String) key;
						}
						return String.valueOf(key);
					};
					Function<Object, Object> valueMapper = value -> {
						if (value instanceof String) {
							URI valueURI = URI.createURI((String) value).resolve(base);
							Invocable valueInvocable = capabilityLoader.loadOne(
									ServiceCapabilityFactory.createRequirement(Invocable.class, null, valueURI),
									progressMonitor.split("Loading " + valueURI, 1));
							return valueInvocable.invoke();
						}
						
						return value;
					};
					Map<String, Object> properties = propertiesMixIn.getProperties(keyMapper, valueMapper);
					invocable = invocable.bindMap(properties);
				}
				
				if (args != null) {
					for (String arg: args) {
						URI argURI = URI.createURI(arg).resolve(base);
						Invocable argInvocable = capabilityLoader.loadOne(
								ServiceCapabilityFactory.createRequirement(Invocable.class, null, argURI),
								progressMonitor.split("Loading " + argURI, 1));
						invocable = invocable.bind(argInvocable.invoke());
					}
				}
				return invocable;
			}
		} catch (IOException e) {
			throw new NasdanikaException("Error starting Groovy shell: " + e, e);
		}
	}
	

	@Override
	public Integer call() throws Exception {
		if (parentCommand instanceof Invocable.Invoker) {
			((Invocable.Invoker) parentCommand).invoke(getInvocable());
			return 0;
		}
		getInvocable().invoke();
		return 0;
	}

}
