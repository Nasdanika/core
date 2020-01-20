package org.nasdanika.emf.presentation;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.sirius.ui.business.api.dialect.DialectUI;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIServices;

/**
 * Dialect which uses {@link AdapterFactory} to represent the model as a tree in a similar way as 
 * the generated EMF editor does.
 * @author Pavel
 *
 */
public class AdapterFactoryTreeDialectUI implements DialectUI {

	private DialectUIServices services;

	@Override
	public String getName() {
		return "adapter-factory-tree";
	}

	@Override
	public DialectUIServices getServices() {
		if (services == null) {
			services = new AdapterFactoryTreeDialectUIServices();
		}
		return services;
	}

}
