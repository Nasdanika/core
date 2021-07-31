package org.nasdanika.exec.gen.content;

import java.io.InputStream;

import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.ComposedAdapterFactory;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.exec.content.ContentPackage;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.exec.content.Text;

/**
 * Provides adapters for the Engineering model elements.
 * @author Pavel
 *
 */
public class ContentAdapterFactory extends ComposedAdapterFactory {
	
	public ContentAdapterFactory() {
		
		// Content
		registerAdapterFactory(
			new FunctionAdapterFactory<SupplierFactory<InputStream>, Resource>(
				ContentPackage.Literals.RESOURCE, 
				getInputStreamSupplierFactoryClass(), 
				this.getClass().getClassLoader(), 
				ResourceSupplierFactoryAdapter::new));
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<InputStream>, Text>(
					ContentPackage.Literals.TEXT, 
					getInputStreamSupplierFactoryClass(), 
					this.getClass().getClassLoader(), 
					TextSupplierFactoryAdapter::new));
				
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Class<SupplierFactory<InputStream>> getInputStreamSupplierFactoryClass() {
		return (Class) SupplierFactory.class;
	}
	
}
