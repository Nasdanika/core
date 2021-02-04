package org.nasdanika.emf.ext;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.nasdanika.emf.ComposeableAdapterFactory;
import org.nasdanika.emf.ComposedAdapterFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	public static final String ADAPTER_FACTORIES_EXTENSION_POINT_ID = "org.nasdanika.emf.ext.adapterFactories";

	@Override
	public void start(BundleContext context) throws Exception {
		for (IConfigurationElement ce: Platform.getExtensionRegistry().getConfigurationElementsFor(ADAPTER_FACTORIES_EXTENSION_POINT_ID)) {
			if ("factory".equals(ce.getName())) {
				try {
					ComposedAdapterFactory.getGlobalFactory().registerAdapterFactory((ComposeableAdapterFactory) ce.createExecutableExtension("class"));
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
