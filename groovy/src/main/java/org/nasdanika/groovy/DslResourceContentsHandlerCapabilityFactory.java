package org.nasdanika.groovy;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import javax.script.CompiledScript;

import org.apache.commons.lang3.stream.Streams;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.emf.ResourceContentsHandler;
import org.nasdanika.common.ProgressMonitor;

/**
 * Transform handler factory for the {@code .pm} qualifier sitting to the left of a {@code .groovy}
 * source qualifier (e.g. {@code personas.pm.groovy}). It mirrors the Markdown
 * {@code MarkdownToProductManagementResourceContentsHandlerCapabilityFactory}, but requests a
 * {@link CompiledSource} from the downstream (source) handler instead of a Markdown {@code Document}.
 */
public class DslResourceContentsHandlerCapabilityFactory extends ServiceCapabilityFactory<org.nasdanika.capability.emf.ResourceContentsHandler.Requirement, ResourceContentsHandler<EObject[]>> {

	@Override
	public boolean isFor(Class<?> type, Object serviceRequirement) {
		return ResourceContentsHandler.class.equals(type) 
				&& serviceRequirement instanceof ResourceContentsHandler.Requirement handlerRequirement
				&& match(handlerRequirement);
	}

	private boolean match(org.nasdanika.capability.emf.ResourceContentsHandler.Requirement handlerRequirement) {
		return EObject[].class.equals(handlerRequirement.getContentsType())
				&& handlerRequirement.getQualifierIndex() == 0
				&& "groovy".equalsIgnoreCase(handlerRequirement.getQualifiers()[0]);
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<ResourceContentsHandler<EObject[]>>>> createService(
			Class<ResourceContentsHandler<EObject[]>> serviceType,
			org.nasdanika.capability.emf.ResourceContentsHandler.Requirement serviceRequirement,
			final Loader loader,
			ProgressMonitor progressMonitor) {

		ResourceContentsHandler.Requirement handlerRequirement = ResourceContentsHandler.createRequirement(
				serviceRequirement.getResource(),
				CompiledScript.class,
				serviceRequirement.getQualifiers(),
				serviceRequirement.getQualifierIndex());

		@SuppressWarnings("rawtypes")
		ServiceCapabilityFactory.Requirement<org.nasdanika.capability.emf.ResourceContentsHandler.Requirement, ResourceContentsHandler> sourceServiceRequirement = ServiceCapabilityFactory.createRequirement(ResourceContentsHandler.class, null, handlerRequirement);
		CompletionStage<Iterable<CapabilityProvider<ResourceContentsHandler<CompiledScript>>>> sourceHandlerCS = loader.load(sourceServiceRequirement, progressMonitor);

		ServiceCapabilityFactory.Requirement<Resource, DslResourceContentsHandler.Factory> factoryServiceRequirement = ServiceCapabilityFactory.createRequirement(DslResourceContentsHandler.Factory.class, null, serviceRequirement.getResource());
		CompletionStage<Iterable<CapabilityProvider<DslResourceContentsHandler.Factory>>> factoryCS = loader.load(factoryServiceRequirement, progressMonitor);

		ServiceCapabilityFactory.Requirement<Resource, DslResourceContentsHandler.Marker> markerServiceRequirement = ServiceCapabilityFactory.createRequirement(DslResourceContentsHandler.Marker.class, null, serviceRequirement.getResource());
		CompletionStage<Iterable<CapabilityProvider<DslResourceContentsHandler.Marker>>> markerCS = loader.load(markerServiceRequirement, progressMonitor);

		record FactoryAndMarkerProviders(
				Iterable<CapabilityProvider<DslResourceContentsHandler.Factory>> factories,
				Iterable<CapabilityProvider<DslResourceContentsHandler.Marker>> markers) {
		}	

		CompletionStage<FactoryAndMarkerProviders> providersCS = factoryCS.thenCombine(markerCS, FactoryAndMarkerProviders::new);

		return sourceHandlerCS.thenCombine(providersCS, (sourceHandlers, providers) -> createHandler(
				sourceHandlers,
				providers.factories(),
				providers.markers(),
				serviceRequirement.getResource()));
	}

	private Iterable<CapabilityProvider<ResourceContentsHandler<EObject[]>>> createHandler(
			Iterable<CapabilityProvider<ResourceContentsHandler<CompiledScript>>> providers,
			Iterable<CapabilityProvider<DslResourceContentsHandler.Factory>> factories,
			Iterable<CapabilityProvider<DslResourceContentsHandler.Marker>> markers,
			Resource resource) {

		Function<CapabilityProvider<ResourceContentsHandler<CompiledScript>>, CapabilityProvider<ResourceContentsHandler<EObject[]>>> mapper = provider -> provider.map(sourceHandler -> createHandler(resource, sourceHandler, factories, markers));
		return Streams.of(providers).map(mapper).toList();
	}

	protected DslResourceContentsHandler createHandler(
		Resource resource, 
		ResourceContentsHandler<CompiledScript> sh,
			Iterable<CapabilityProvider<DslResourceContentsHandler.Factory>> factoryProviders,
			Iterable<CapabilityProvider<DslResourceContentsHandler.Marker>> markerProviders) {

		List<DslResourceContentsHandler.Factory> factories = Streams.of(factoryProviders).flatMap(cf -> cf.getPublisher().collectList().block().stream()).sorted().toList();
		List<DslResourceContentsHandler.Marker> markers = Streams.of(markerProviders).flatMap(cf -> cf.getPublisher().collectList().block().stream()).toList();

		return new DslResourceContentsHandler(resource, sh) {

			@Override
			protected void mark(EObject eObject, EStructuralFeature feature, int line, int col) {
				markers.forEach(m -> m.mark(eObject, feature, line, col));
				super.mark(eObject, feature, line, col);
			}

			@Override
			protected EObject create(EReference eReference, Object value) {
				for (DslResourceContentsHandler.Factory factory: factories) {
					if (factory.canCreate(eReference, value)) {
						return factory.create(eReference, value);
					}
				}
				return super.create(eReference, value);
			}

		};
	}

}
