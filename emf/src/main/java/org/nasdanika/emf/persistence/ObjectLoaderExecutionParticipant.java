package org.nasdanika.emf.persistence;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.emf.DrawioResourceFactory;
import org.nasdanika.drawio.model.ModelFactory;
import org.nasdanika.persistence.Marker;
import org.nasdanika.persistence.ObjectLoader;
import org.nasdanika.persistence.ObjectLoaderResource;
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
		
		DrawioResourceFactory ncoreDrawioResourceFactory = new DrawioResourceFactory() {
			
			@Override
			protected Function<Marker, org.nasdanika.ncore.Marker> getMarkerFactory() {
				return m -> {
					org.nasdanika.ncore.Marker mm = markerFactory.createMarker(m.getLocation(), progressMonitor);
					mm.setPosition(m.getPosition());
					return mm;
				};
			}

			@Override
			protected ModelFactory getFactory() {
				return ObjectLoaderExecutionParticipant.this.getDrawioModelFactory();
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
	
	protected ModelFactory getDrawioModelFactory() {
		return org.nasdanika.drawio.model.ModelFactory.eINSTANCE;
	}

	protected ObjectLoaderResourceFactory createObjectLoaderResorceFactory(ResourceSet resourceSet, ProgressMonitor progressMonitor) {		
		return new ObjectLoaderResourceFactory() {
			
			@Override
			protected ObjectLoader getObjectLoader(Resource resource) {
				return ObjectLoaderExecutionParticipant.this.getObjectLLoader(resourceSet, resource, progressMonitor);
			}
			
			@Override
			protected Context getContext(Resource resource) {
				return context;
			}
			
			@Override
			protected ProgressMonitor getProgressMonitor(Resource resource) {
				return progressMonitor;
			}

			@Override
			protected BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> getResolver(Resource resource) {
				return ObjectLoaderExecutionParticipant.this.getResolver(resourceSet, resource, progressMonitor);
			}
			
		};
	}
	
	protected BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> getResolver(ResourceSet resourceSet, Resource resource, ProgressMonitor progressMonitor) {
		return null;
	}	

	/**
	 * Object loader used by {@link ObjectLoaderResourceFactory} and {@link ObjectLoaderResource}.
	 * @param resourceSet
	 * @param resource
	 * @param progressMonitor
	 * @return
	 */
	protected ObjectLoader getObjectLLoader(ResourceSet resourceSet, Resource resource, ProgressMonitor progressMonitor) {
		EObjectLoader eObjectLoader = new EObjectLoader(resourceSet);
		eObjectLoader.setMarkerFactory(new GitMarkerFactory());
		return eObjectLoader;
	}
	
}
