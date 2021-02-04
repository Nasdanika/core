package org.nasdanika.cli.ext;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.nasdanika.cli.ContextBuilder;
import org.nasdanika.cli.ContextCommand;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.yaml.snakeyaml.Yaml;

import picocli.CommandLine.Option;

/**
 * Base class for commands which build {@link Context} from command line options.
 * @author Pavel
 *
 */
public abstract class ContextCommandWithBuilders extends ContextCommand {
		
	@Option(			
			names = {"-B", "--context-builders"},
			paramLabel = "URL",
			description = {
					"Context builders configuration resource URL relative to the current directory.  "
					+ "See online documentation at https://www.nasdanika.org/builds/develop/doc/reference/cli/context-builders.html for details."
			})
	private List<String> contextBuilders = new ArrayList<>();
	
	/**
	 * Creates and configures context by adding mounts, contexts and calling context builders.
	 * @param progressMonitor
	 * @return
	 * @throws Exception
	 */
	@Override
	protected Context createContext(ProgressMonitor progressMonitor) throws Exception {
		Context ret = createContext(progressMonitor);		
		for (String cb: contextBuilders) {
			URL url = new File(".").toURI().resolve(cb).toURL();
			URLConnection connection = url.openConnection();
			try (InputStream in = connection.getInputStream()) {
				Yaml yaml = new Yaml();
				@SuppressWarnings("unchecked")
				Map<String, Object> config = (Map<String,Object>) yaml.load(in);
				ret = buildContext(config, ret, progressMonitor);
			}			
		}
		return ret;
	}
		
	/**
	 * Builds context. Processes mounts first and then passes the result to the context builder if id is present.
	 * @param config
	 * @param ret
	 * @return
	 */
	protected Context buildContext(Map<String, Object> config, Context context, ProgressMonitor progressMonitor) throws Exception {
		context = super.buildContext(config, context, progressMonitor);
		String id = (String) config.get("id");
		if (id != null) {
			ContextBuilder contextBuilder = createContextBuilder(id);
			context = contextBuilder.build(config.get("config"), context, progressMonitor.split("Building context for id: " +id, 1));
		}
		
		return context;
	}
	
	/**
	 * Collects subcommands from extensions 
	 * @param command
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	private ContextBuilder createContextBuilder(String id) throws Exception {
		for (IConfigurationElement ce: Platform.getExtensionRegistry().getConfigurationElementsFor(Application.CLI_EXTENSION_POINT_ID)) {
			String ceId = ce.getAttribute("id");
			if ("context-builder".equals(ce.getName())
					&& !Util.isBlank(ceId)
					&& Application.equal(id, ce.getContributor().getName()+"/"+ceId)) {
				ContextBuilder contextBuilder = (ContextBuilder) ce.createExecutableExtension("class");
				for (IConfigurationElement parameter: ce.getChildren("parameter")) {
					((BiConsumer<String, String>) contextBuilder).accept(parameter.getAttribute("name"), parameter.getAttribute("value"));
				}
				return contextBuilder;
			}
		}
		throw new IllegalArgumentException("Context builder not found: " + id);
	}	
	
}
