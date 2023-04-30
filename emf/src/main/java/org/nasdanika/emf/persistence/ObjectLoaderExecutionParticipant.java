package org.nasdanika.emf.persistence;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.RegistryRecord;
import org.nasdanika.graph.processor.emf.SemanticProcessor;
import org.nasdanika.persistence.ObjectLoader;
import org.nasdanika.persistence.ObjectLoaderResourceFactory;

/**
 * Registers {@link YamlResourceFactory} with the resource set.
 * Also registers {@link EPackage}'s
 * @author Pavel
 *
 */
public abstract class ObjectLoaderExecutionParticipant extends LoadingExecutionParticipant {

	public ObjectLoaderExecutionParticipant(Context context) {
		super(context);
	}

	@Override
	protected ResourceSet createResourceSet(ProgressMonitor progressMonitor) {
		ResourceSet ret = super.createResourceSet(progressMonitor);
		
		ObjectLoaderResourceFactory objectLoaderResourceFactory = createObjectLoaderResorceFactory(ret, progressMonitor);
		
		Map<String, Object> extensionToFactoryMap = ret.getResourceFactoryRegistry().getExtensionToFactoryMap();
		extensionToFactoryMap.put("yml", objectLoaderResourceFactory);
		extensionToFactoryMap.put("json", objectLoaderResourceFactory);
		
		ret.getResourceFactoryRegistry().getProtocolToFactoryMap().put("data", objectLoaderResourceFactory);
		GitMarkerFactory markerFactory = new GitMarkerFactory();
		
		NcoreDrawioResourceFactory<EObject, RegistryRecord<SemanticProcessor<EObject>>> ncoreDrawioResourceFactory = new NcoreDrawioResourceFactory<EObject, RegistryRecord<SemanticProcessor<EObject>>>() {
			
			@Override
			protected ResourceSet getResourceSet() {
				return resourceSet;
			}
			
			@Override
			protected ProgressMonitor getProgressMonitor(URI uri) {
				return progressMonitor;
			}
			
			@Override
			protected MarkerFactory getMarkerFactory() {
				return markerFactory;
			}

			@Override
			protected ProcessorInfo<SemanticProcessor<EObject>, RegistryRecord<SemanticProcessor<EObject>>> getProcessorInfo(RegistryRecord<SemanticProcessor<EObject>> registry, org.nasdanika.graph.Element element) {
				return registry.processorInfoMap().get(element);
			}

			@Override
			protected Stream<EObject> getRegistrySemanticElements(RegistryRecord<SemanticProcessor<EObject>> registry) {
				return registryEntries(registry).stream().map(Map.Entry::getValue).map(ProcessorInfo::getProcessor).filter(Objects::nonNull).flatMap(sp -> sp.getSemanticElements().stream());
			}

			@Override
			protected RegistryRecord<SemanticProcessor<EObject>> createRegistry(Map<org.nasdanika.graph.Element, ProcessorInfo<SemanticProcessor<EObject>, RegistryRecord<SemanticProcessor<EObject>>>> registry) {
				return new RegistryRecord<>(registry);
			}

			@Override
			protected Collection<Entry<org.nasdanika.graph.Element, ProcessorInfo<SemanticProcessor<EObject>, RegistryRecord<SemanticProcessor<EObject>>>>> registryEntries(RegistryRecord<SemanticProcessor<EObject>> registry) {
				return registry.processorInfoMap().entrySet();
			}
		};
		
		extensionToFactoryMap.put("drawio", ncoreDrawioResourceFactory);		

		// For handling textual representations
		TextResourceFactory textResourceFactory = new TextResourceFactory();
		extensionToFactoryMap.put("txt", textResourceFactory);		
		extensionToFactoryMap.put("puml", textResourceFactory);								
		extensionToFactoryMap.put("mermaid", textResourceFactory);				
		
		return ret;
	}

	protected ObjectLoaderResourceFactory createObjectLoaderResorceFactory(ResourceSet resourceSet, ProgressMonitor progressMonitor) {		
		return new ObjectLoaderResourceFactory() {
			
			@Override
			protected ObjectLoader getObjectLoader(Resource resource) {
				return getObjectLLoader(resourceSet, resource, progressMonitor);
			}
			
			@Override
			protected Context getContext(Resource resource) {
				return context;
			}
			
			@Override
			protected ProgressMonitor getProgressMonitor(Resource resource) {
				return progressMonitor;
			}
			
		};
	}

	protected ObjectLoader getObjectLLoader(ResourceSet resourceSet, Resource resource, ProgressMonitor progressMonitor) {
		EObjectLoader eObjectLoader = new EObjectLoader(null, null, resourceSet);
		eObjectLoader.setMarkerFactory(new GitMarkerFactory());
		return eObjectLoader;
	}

}
