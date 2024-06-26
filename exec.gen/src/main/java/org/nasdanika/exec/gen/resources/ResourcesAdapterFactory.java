package org.nasdanika.exec.gen.resources;

import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.emf.ComposedAdapterFactory;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.exec.resources.Container;
import org.nasdanika.exec.resources.File;
import org.nasdanika.exec.resources.ResourcesPackage;
import org.nasdanika.resources.BinaryEntityContainer;

/**
 * Provides adapters for the Engineering model elements.
 * @author Pavel
 *
 */
public class ResourcesAdapterFactory extends ComposedAdapterFactory {
	
	public ResourcesAdapterFactory() {
		
		registerAdapterFactory(
			new FunctionAdapterFactory<ConsumerFactory<BinaryEntityContainer>, File>(
				ResourcesPackage.Literals.FILE, 
				getBinaryEntityContainerConsumerFactoryClass(), 
				Thread.currentThread().getContextClassLoader(), 
				FileConsumerFactoryAdapter::new));
		
		registerAdapterFactory(
			new FunctionAdapterFactory<ConsumerFactory<BinaryEntityContainer>, Container>(
				ResourcesPackage.Literals.CONTAINER, 
				getBinaryEntityContainerConsumerFactoryClass(), 
				Thread.currentThread().getContextClassLoader(), 
				ContainerConsumerFactoryAdapter::new));
				
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Class<ConsumerFactory<BinaryEntityContainer>> getBinaryEntityContainerConsumerFactoryClass() {
		return (Class) ConsumerFactory.class;
	}

	
}
