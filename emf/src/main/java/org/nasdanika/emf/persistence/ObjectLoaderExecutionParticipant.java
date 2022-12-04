package org.nasdanika.emf.persistence;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
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
		
		resourceSet.getResourceFactoryRegistry().getProtocolToFactoryMap().put("data", objectLoaderResourceFactory);
		
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
