package org.nasdanika.ncore.gen;

import java.io.InputStream;

import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.ComposedAdapterFactory;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.ncore.NcorePackage;

/**
 * Provides adapters for the Engineering model elements.
 * @author Pavel
 *
 */
public class NcoreAdapterFactory extends ComposedAdapterFactory {
	
	public NcoreAdapterFactory() {
		
		// Content		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<InputStream>, org.nasdanika.ncore.String>(
					NcorePackage.Literals.STRING, 
					getInputStreamSupplierFactoryClass(), 
					this.getClass().getClassLoader(), 
					StringSupplierFactoryAdapter::new));
				
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Class<SupplierFactory<InputStream>> getInputStreamSupplierFactoryClass() {
		return (Class) SupplierFactory.class;
	}
	
	@Override
	public Object adapt(Object object, Object type) {
		// TODO Auto-generated method stub
		return super.adapt(object, type);
	}
	
}
