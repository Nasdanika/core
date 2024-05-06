package org.nasdanika.http;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.cli.RootCommand;
import org.nasdanika.cli.SubCommandCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class HttpServerCommandFactory extends SubCommandCapabilityFactory<HttpServerCommand> {

	@Override
	protected CompletionStage<HttpServerCommand> createCommand(
			List<CommandLine> parentPath, 
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		if (parentPath != null && parentPath.size() == 1 && parentPath.get(0).getCommandSpec().userObject() instanceof RootCommand) {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			CompletionStage<Iterable<CapabilityProvider<HttpServerRouteBuilder>>> routeBuildersCS = (CompletionStage) resolver.apply(ServiceCapabilityFactory.createRequirement(HttpServerRouteBuilder.class, null, null), progressMonitor);
			return routeBuildersCS.thenApply(routeBuilders -> createHttpServerCommand(routeBuilders, progressMonitor));
		}
		return null;
	}

	@Override
	protected Class<HttpServerCommand> getCommandType() {
		return HttpServerCommand.class;
	}
	
	protected HttpServerCommand createHttpServerCommand(
			Iterable<CapabilityProvider<HttpServerRouteBuilder>> routeBuilderProviders,
			ProgressMonitor progressMonitor) {
		Collection<HttpServerRouteBuilder> routeBuilders = Collections.synchronizedCollection(new ArrayList<>());
		for (CapabilityProvider<HttpServerRouteBuilder> rbp: routeBuilderProviders) {
			rbp.getPublisher().subscribe(routeBuilders::add);
		}
		
		return new HttpServerCommand(routeBuilders);
	}

}
