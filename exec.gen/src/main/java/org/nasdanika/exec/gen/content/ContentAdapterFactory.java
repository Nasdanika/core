package org.nasdanika.exec.gen.content;

import java.io.InputStream;

import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.ComposedAdapterFactory;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.exec.content.Base64;
import org.nasdanika.exec.content.ContentPackage;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
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
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<InputStream>, Markdown>(
					ContentPackage.Literals.MARKDOWN, 
					getInputStreamSupplierFactoryClass(), 
					this.getClass().getClassLoader(), 
					MarkdownSupplierFactoryAdapter::new));
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<InputStream>, Interpolator>(
					ContentPackage.Literals.INTERPOLATOR, 
					getInputStreamSupplierFactoryClass(), 
					this.getClass().getClassLoader(), 
					InterpolatorSupplierFactoryAdapter::new));
		
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<InputStream>, Base64>(
					ContentPackage.Literals.BASE64, 
					getInputStreamSupplierFactoryClass(), 
					this.getClass().getClassLoader(), 
					Base64SupplierFactoryAdapter::new));
				
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Class<SupplierFactory<InputStream>> getInputStreamSupplierFactoryClass() {
		return (Class) SupplierFactory.class;
	}
	
}
