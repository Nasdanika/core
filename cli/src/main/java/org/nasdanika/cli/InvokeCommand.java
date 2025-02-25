package org.nasdanika.cli;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.ParentCommand;

@Command(
		description = "Invokes URI",
		versionProvider = ModuleVersionProvider.class,		
		mixinStandardHelpOptions = true,
		name = "invoke")
@ParentCommands(Invocable.Invoker.class)
@Description(icon = "https://docs.nasdanika.org/images/automation.svg")
public class InvokeCommand extends CommandBase {
	
	public InvokeCommand(CapabilityLoader capablityLoader) {
		this.capabilityLoader = capablityLoader;
	}
	
	@Parameters(
		index =  "0",	
		arity = "1",
		description = "URI to invoke")
	private String uri;
	
	@Parameters(
		index =  "1",	
		arity = "*",
		description = "Bindings URIs")
	private String[] bindings;
		
	@Option(
		names = {"-f", "--file"},
		description = "URI parameter is a file path")
	private boolean isFile;
	
	@Mixin
	private PropertiesMixIn propertiesMixIn;
	
	@ParentCommand
	Invocable.Invoker parentCommand;
	
	@Mixin
	private ProgressMonitorMixIn progressMonitorMixIn;
	
	protected Invocable getInvocable() {
		try {
			int bindingsLength = bindings == null ? 0 : bindings.length;
			URI base = URI.createFileURI(new File(".").getAbsolutePath());		
			try (ProgressMonitor progressMonitor = progressMonitorMixIn.createProgressMonitor(2 + bindingsLength)) {
				URI targetURI = isFile ? URI.createFileURI(new File(uri).getAbsolutePath()) : URI.createURI(uri).resolve(base);
				Invocable invocable = capabilityLoader.loadOne(
						ServiceCapabilityFactory.createRequirement(Invocable.class, null, targetURI),
						progressMonitor.split("Loading " + targetURI, 1));
				
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
				
				if (bindings != null) {
					for (String binding: bindings) {
						URI bindingURI = URI.createURI(binding).resolve(base);
						Invocable bindingInvocable = capabilityLoader.loadOne(
								ServiceCapabilityFactory.createRequirement(Invocable.class, null, bindingURI),
								progressMonitor.split("Loading " + bindingURI, 1));
						invocable = invocable.bind(bindingInvocable.invoke());
					}
				}
				
				return invocable;
			}
		} catch (IOException e) {
			throw new NasdanikaException("Error invoking '" + uri + "': " + e, e);
		}
	}
	

	@Override
	public Integer call() throws Exception {
		if (parentCommand != null) {
			((Invocable.Invoker) parentCommand).invoke(getInvocable());
			return 0;
		}
		getInvocable().invoke();
		return 0;
	}

}
