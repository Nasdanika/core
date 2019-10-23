package org.nasdanika.ncore.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.nasdanika.common.Context;
import org.nasdanika.common.Work;
import org.nasdanika.common.WorkFactory;
import org.nasdanika.ncore.NcorePackage;

/**
 * This class loads a model with the root element implementing {@link WorkFactory}, and delegates its methods to it. 
 * @author Pavel
 *
 */
public class ModelWorkFactory<T> implements WorkFactory<T> { 

	protected WorkFactory<T> workFactory;

	/**
	 * Creates a generator by loading a generator model specified by the platform URI, e.g. ``org.nasdanika.codegen.tests.models/models/static-text/basic.codegen``, into a new {@link ResourceSet}. 
	 * @param platformPluginUri
	 * @throws Exception 
	 */
	public ModelWorkFactory(String platformPluginUri) throws Exception {
		this(URI.createPlatformPluginURI(platformPluginUri, false));
	}
	
	/**
	 * Creates a generator by loading a generator model from the specified URI into a new {@link ResourceSet}. 
	 * @param modelUri
	 * @throws Exception 
	 */
	public ModelWorkFactory(URI modelUri) throws Exception {
		this(createResourceSet(), modelUri);
	}	
	
	/**
	 * Creates a generator by loading a generator model specified by the platform URI, e.g. ``org.nasdanika.codegen.tests.models/models/static-text/basic.codegen``, into 
	 * the specified {@link ResourceSet}.
	 * @param resourceSet Resource set to load the model to, see ``ModelGenerator(ResourceSet resourceSet, URI modelUri)`` for details. 
	 * @param platformPluginUri
	 * @throws Exception 
	 */
	public ModelWorkFactory(ResourceSet resourceSet, String platformPluginUri) throws Exception {
		this(resourceSet, URI.createPlatformPluginURI(platformPluginUri, false));
	}
	
	/**
	 * Creates a new {@link ResourceSet} if it was not passed to a constructor explicitly. This implementation creates a new resource set and configures it to load the model by registering an {@link XMIResourceFactoryImpl}
	 * and putting {@link NcorePackage} to the package registry. If the model uses model elements from other {@link EPackage}s pass  a resource set constructed by the client to one of the 
	 * constructors which accept resource set. The resource set can be constructed by this method and then additional packages may be registered by the client code before passing the resource
	 * set to a constructor.   
	 * @return
	 */
	public static ResourceSet createResourceSet() {
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		resourceSet.getPackageRegistry().put(NcorePackage.eNS_URI, NcorePackage.eINSTANCE);
		return resourceSet;
	}
	
	/**
	 * Creates a generator by loading a generator model from the specified URI into the specified {@link ResourceSet}.
	 * @param resourceSet Resource set to load the model to. 
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public ModelWorkFactory(ResourceSet resourceSet, URI modelUri) throws Exception {
		Resource modelResource = resourceSet.getResource(modelUri, true);
		workFactory = (WorkFactory<T>) modelResource.getContents().iterator().next();
	}
	
	@Override
	public Work<T> create(Context context) throws Exception {
		return workFactory.create(context);
	}

}
