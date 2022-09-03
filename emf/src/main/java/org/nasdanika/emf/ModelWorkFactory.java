package org.nasdanika.emf;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.nasdanika.common.Context;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.ncore.NcorePackage;

/**
 * This class loads a model with the root element, adapts it to {@link SupplierFactory}, and delegates its methods to it. 
 * @author Pavel
 *
 */
public class ModelWorkFactory<T> implements SupplierFactory<T> { 

	protected EObject root;	
	protected Resource modelResource;

	/**
	 * Creates a generator by loading a generator model specified by the platform URI, e.g. ``org.nasdanika.codegen.tests.models/models/static-text/basic.codegen``, into a new {@link ResourceSet}. 
	 * @param platformPluginUri
	 */
	public ModelWorkFactory(String platformPluginUri, EPackage... ePackages) {
		this(URI.createPlatformPluginURI(platformPluginUri, false), ePackages);
	}
	
	/**
	 * Creates a generator by loading a generator model from the specified URI into a new {@link ResourceSet}. 
	 * @param modelUri
	 */
	public ModelWorkFactory(URI modelUri, EPackage... ePackages) {
		this(createResourceSet(ePackages), modelUri);
	}	
	
	/**
	 * Creates a generator by loading a generator model specified by the platform URI, e.g. ``org.nasdanika.codegen.tests.models/models/static-text/basic.codegen``, into 
	 * the specified {@link ResourceSet}.
	 * @param resourceSet Resource set to load the model to, see ``ModelGenerator(ResourceSet resourceSet, URI modelUri)`` for details. 
	 * @param platformPluginUri
	 */
	public ModelWorkFactory(ResourceSet resourceSet, String platformPluginUri) {
		this(resourceSet, URI.createPlatformPluginURI(platformPluginUri, false));
	}
	
	/**
	 * Creates a new {@link ResourceSet} if it was not passed to a constructor explicitly. This implementation creates a new resource set and configures it to load the model by registering an {@link XMIResourceFactoryImpl}
	 * and putting {@link NcorePackage} to the package registry. If the model uses model elements from other {@link EPackage}s pass  a resource set constructed by the client to one of the 
	 * constructors which accept resource set. The resource set can be constructed by this method and then additional packages may be registered by the client code before passing the resource
	 * set to a constructor.   
	 * @return
	 */
	public static ResourceSet createResourceSet(EPackage... ePackages) {
		ResourceSetImpl resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		for (EPackage ePackage: ePackages) {
			resourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
		}
		return resourceSet;
	}

	/**
	 * Override to specify result type of supplier.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getSupplierResultType() {
		return (Class<T>) Object.class;
	}
	
	/**
	 * Creates a generator by loading a generator model from the specified URI into the specified {@link ResourceSet}.
	 * @param resourceSet Resource set to load the model to. 
	 */
	public ModelWorkFactory(ResourceSet resourceSet, URI modelUri) {
		modelResource = resourceSet.getResource(modelUri, true);
		root = modelResource.getContents().iterator().next();
	}
	
	@Override
	public Supplier<T> create(Context context) {
		return EObjectAdaptable.adaptToSupplierFactoryNonNull(root, getSupplierResultType()).create(context);
	}
	
	public Resource getModelResource() {
		return modelResource;
	}

}
