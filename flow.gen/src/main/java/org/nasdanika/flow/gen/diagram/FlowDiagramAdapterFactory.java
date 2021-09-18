package org.nasdanika.flow.gen.diagram;

import org.nasdanika.emf.ComposedAdapterFactory;

/**
 * Provides adapters for the Engineering model elements.
 * @author Pavel
 *
 */
public class FlowDiagramAdapterFactory extends ComposedAdapterFactory {
	
	public FlowDiagramAdapterFactory() {
		ClassLoader classLoader = getClassLoader();
		
//		registerAdapterFactory(
//				new FunctionAdapterFactory<SupplierFactory<InputStream>, Block>(
//					ExecPackage.Literals.BLOCK, 
//					INPUT_STREAM_SUPPLIER_FACTORY_CLASS, 
//					classLoader, 
//					BlockSupplierFactoryAdapter::new));		
				
	}

	protected ClassLoader getClassLoader() {
		return getClass().getClassLoader();
	}
	
}
