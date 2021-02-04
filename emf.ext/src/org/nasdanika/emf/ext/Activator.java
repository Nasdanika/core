package org.nasdanika.emf.ext;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.emf.ComposeableAdapterFactory;
import org.nasdanika.emf.ComposedAdapterFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	public static final String ADAPTER_FACTORIES_EXTENSION_POINT_ID = "org.nasdanika.emf.ext.adapterFactories";
	
	private static final ComposedAdapterFactory GLOBAL_FACTORY = new ComposedAdapterFactory();
	
	/**
	 * Returns global instance of {@link ComposedAdapterFactory} with {@link ComposeableAdapterFactory} children loaded from extensions.
	 */
	public static ComposedAdapterFactory getGlobalFactory() {
		return GLOBAL_FACTORY;
	}
	
	/**
	 * Adds the global factory to the list or {@link ResourceSet} adapter factories if it is not yet there, so this method is idempotent.
	 * @param resourceSet
	 */
	public static void registerGlobalComposedFactory(ResourceSet resourceSet) {
		ComposedAdapterFactory gf = getGlobalFactory();
		if (!resourceSet.getAdapterFactories().contains(gf)) {
			resourceSet.getAdapterFactories().add(gf);
		}
	}
					
	/**
	 * Returns global instance of {@link ComposedAdapterFactory} with {@link ComposeableAdapterFactory} children loaded from extensions.
	 */
	public static ComposedAdapterFactory getGlobaComposedAdapterlFactory() {
		return GLOBAL_FACTORY;
	}

	@Override
	public void start(BundleContext context) throws Exception {
		for (IConfigurationElement ce: Platform.getExtensionRegistry().getConfigurationElementsFor(ADAPTER_FACTORIES_EXTENSION_POINT_ID)) {
			if ("factory".equals(ce.getName())) {
				try {
					GLOBAL_FACTORY.registerAdapterFactory((ComposeableAdapterFactory) ce.createExecutableExtension("class"));
				} catch (CoreException e) {
					EcorePlugin.INSTANCE.log(e);
				}
			}
		}
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// NOP
	}

}
