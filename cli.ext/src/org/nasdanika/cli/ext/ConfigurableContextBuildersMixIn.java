package org.nasdanika.cli.ext;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.nasdanika.cli.ContextBuilder;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.yaml.snakeyaml.Yaml;

import picocli.CommandLine.Option;

/**
 * Mixes-in configurable builders loaded from extensions.
 * @author Pavel
 *
 */
public abstract class ConfigurableContextBuildersMixIn implements ContextBuilder {
		
	@Option(			
			names = {"-B", "--context-builders"},
			paramLabel = "URL",
			description = {
					"Context builders configuration resource URL relative to the current directory.  "
					+ "See online documentation at https://www.nasdanika.org/builds/develop/doc/reference/cli/context-builders.html for details."
			})
	private List<String> contextBuilders = new ArrayList<>();
	
	@Override
	public Context build(Context context, ProgressMonitor progressMonitor) throws Exception {
		for (String cb: contextBuilders) {
			URL url = new File(".").toURI().resolve(cb).toURL();
			URLConnection connection = url.openConnection();
			try (InputStream in = connection.getInputStream()) {
				Yaml yaml = new Yaml();
				@SuppressWarnings("unchecked")
				Map<String, Object> config = (Map<String,Object>) yaml.load(in);
				context = buildContext(config, context, progressMonitor);
			}			
		}
		return context;
	}
		
	/**
	 * Builds context. Processes mounts first and then passes the result to the context builder if id is present.
	 * @param config
	 * @param ret
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Context buildContext(Map<String, Object> config, Context context, ProgressMonitor progressMonitor) throws Exception {
		Map<String, Map<String, Object>> cbMounts = (Map<String, Map<String, Object>>) config.get("mounts");
		double size = (config.get("id") == null ? 0 : 1) + (cbMounts == null ? 0 : cbMounts.size());
		progressMonitor.setWorkRemaining(size);
		if (cbMounts != null) {			
			for (Entry<String, Map<String, Object>> me: cbMounts.entrySet()) {
				context = context.mount(buildContext(me.getValue(), context, progressMonitor.split("Building context for mount "+me.getKey(), 1)), me.getKey());
			}
		}
		String id = (String) config.get("id");
		if (id != null) {
			ConfigurableContextBuilder contextBuilder = createConfigurableContextBuilder(id);
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
	private ConfigurableContextBuilder createConfigurableContextBuilder(String id) throws Exception {
		for (IConfigurationElement ce: Platform.getExtensionRegistry().getConfigurationElementsFor(Application.CLI_EXTENSION_POINT_ID)) {
			String ceId = ce.getAttribute("id");
			if ("context-builder".equals(ce.getName())
					&& !Util.isBlank(ceId)
					&& Application.equal(id, ce.getContributor().getName()+"/"+ceId)) {
				ConfigurableContextBuilder contextBuilder = (ConfigurableContextBuilder) ce.createExecutableExtension("class");
				for (IConfigurationElement parameter: ce.getChildren("parameter")) {
					((BiConsumer<String, String>) contextBuilder).accept(parameter.getAttribute("name"), parameter.getAttribute("value"));
				}
				return contextBuilder;
			}
		}
		throw new IllegalArgumentException("Context builder not found: " + id);
	}	
	
}
