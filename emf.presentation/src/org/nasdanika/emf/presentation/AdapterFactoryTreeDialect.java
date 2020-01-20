package org.nasdanika.emf.presentation;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.sirius.business.api.dialect.Dialect;
import org.eclipse.sirius.business.api.dialect.DialectServices;

/**
 * Dialect which uses {@link AdapterFactory} to represent the model as a tree in a similar way as 
 * the generated EMF editor does.
 * @author Pavel
 *
 */
public class AdapterFactoryTreeDialect implements Dialect {

	private DialectServices services;

	@Override
	public String getName() {
		return "adapter-factory-tree";
	}

	@Override
	public DialectServices getServices() {
		if (services == null) {
			services = new AdapterFactoryTreeDialectServices();
		}
		return services;
	}

}
