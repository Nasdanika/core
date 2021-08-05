package org.nasdanika.exec.gen;

import java.io.InputStream;
import java.util.List;

import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.emf.ComposedAdapterFactory;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.exec.Block;
import org.nasdanika.exec.Call;
import org.nasdanika.exec.Configurator;
import org.nasdanika.exec.Eval;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.exec.Fail;
import org.nasdanika.exec.gen.content.ContentAdapterFactory;
import org.nasdanika.exec.gen.resources.ResourcesAdapterFactory;

/**
 * Provides adapters for the Engineering model elements.
 * @author Pavel
 *
 */
public class ExecutionParticpantAdapterFactory extends ComposedAdapterFactory {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Class<SupplierFactory<java.util.Map<String,Object>>> MAP_SUPPLIER_FACTORY_CLASS = (Class) SupplierFactory.class;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Class<SupplierFactory<List<Object>>> LIST_SUPPLIER_FACTORY_CLASS = (Class) SupplierFactory.class;

	// Generic class constants
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Class<SupplierFactory<Object>> OBJECT_SUPPLIER_FACTORY_CLASS = (Class) SupplierFactory.class;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Class<SupplierFactory<InputStream>> INPUT_STREAM_SUPPLIER_FACTORY_CLASS = (Class) SupplierFactory.class;
		
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Class<ConsumerFactory<BinaryEntityContainer>> BINARY_ENTITY_CONTAINER_CONSUMER_FACTORY_CLASS = (Class) ConsumerFactory.class;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final Class<ConsumerFactory<Object>> OBJECT_CONSUMER_FACTORY_CLASS = (Class) ConsumerFactory.class;
	
	public ExecutionParticpantAdapterFactory() {
		
		// Block
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<InputStream>, Block>(
					ExecPackage.Literals.BLOCK, 
					INPUT_STREAM_SUPPLIER_FACTORY_CLASS, 
					this.getClass().getClassLoader(), 
					BlockSupplierFactoryAdapter::new));		

		registerAdapterFactory(
				new FunctionAdapterFactory<ConsumerFactory<BinaryEntityContainer>, Block>(
					ExecPackage.Literals.BLOCK, 
					BINARY_ENTITY_CONTAINER_CONSUMER_FACTORY_CLASS, 
					this.getClass().getClassLoader(), 
					BlockConsumerFactoryAdapter::new));		
		
		registerAdapterFactory(
				new FunctionAdapterFactory<CommandFactory, Block>(
					ExecPackage.Literals.BLOCK, 
					CommandFactory.class, 
					this.getClass().getClassLoader(), 
					BlockCommandFactoryAdapter::new));
		
		// Call
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<InputStream>, Call>(
					ExecPackage.Literals.CALL, 
					INPUT_STREAM_SUPPLIER_FACTORY_CLASS, 
					this.getClass().getClassLoader(), 
					CallSupplierFactoryAdapter::new));		
		
		// Configurator
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<InputStream>, Configurator>(
					ExecPackage.Literals.CONFIGURATOR, 
					INPUT_STREAM_SUPPLIER_FACTORY_CLASS, 
					this.getClass().getClassLoader(), 
					ConfiguratorSupplierFactoryAdapter::new));		

		registerAdapterFactory(
				new FunctionAdapterFactory<ConsumerFactory<BinaryEntityContainer>, Configurator>(
					ExecPackage.Literals.CONFIGURATOR, 
					BINARY_ENTITY_CONTAINER_CONSUMER_FACTORY_CLASS, 
					this.getClass().getClassLoader(), 
					ConfiguratorConsumerFactoryAdapter::new));		
		
		registerAdapterFactory(
				new FunctionAdapterFactory<CommandFactory, Configurator>(
					ExecPackage.Literals.CONFIGURATOR, 
					CommandFactory.class, 
					this.getClass().getClassLoader(), 
					ConfiguratorCommandFactoryAdapter::new));
		
		// Eval
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory.Provider, Eval>(
					ExecPackage.Literals.EVAL, 
					SupplierFactory.Provider.class, 
					this.getClass().getClassLoader(), 
					EvalSupplierFactoryProviderAdapter::new));		

		// Fail
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<InputStream>, Fail>(
					ExecPackage.Literals.FAIL, 
					INPUT_STREAM_SUPPLIER_FACTORY_CLASS, 
					this.getClass().getClassLoader(), 
					FailSupplierFactoryAdapter::new));		

		registerAdapterFactory(
				new FunctionAdapterFactory<ConsumerFactory<BinaryEntityContainer>, Fail>(
					ExecPackage.Literals.FAIL, 
					BINARY_ENTITY_CONTAINER_CONSUMER_FACTORY_CLASS, 
					this.getClass().getClassLoader(), 
					FailConsumerFactoryAdapter::new));		
		
		registerAdapterFactory(
				new FunctionAdapterFactory<CommandFactory, Fail>(
					ExecPackage.Literals.FAIL, 
					CommandFactory.class, 
					this.getClass().getClassLoader(), 
					FailCommandFactoryAdapter::new));				
		
		// List
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<List<Object>>, org.nasdanika.exec.List>(
					ExecPackage.Literals.LIST, 
					LIST_SUPPLIER_FACTORY_CLASS, 
					this.getClass().getClassLoader(), 
					ListSupplierFactoryAdapter::new));		

//		registerAdapterFactory(
//				new FunctionAdapterFactory<ConsumerFactory<BinaryEntityContainer>, Fail>(
//					ExecPackage.Literals.FAIL, 
//					getBinaryEntityContainerConsumerFactoryClass(), 
//					this.getClass().getClassLoader(), 
//					FailConsumerFactoryAdapter::new));		
//		
//		registerAdapterFactory(
//				new FunctionAdapterFactory<CommandFactory, Fail>(
//					ExecPackage.Literals.FAIL, 
//					CommandFactory.class, 
//					this.getClass().getClassLoader(), 
//					FailCommandFactoryAdapter::new));				
		
		// Map
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<java.util.Map<String,Object>>, org.nasdanika.exec.Map>(
					ExecPackage.Literals.MAP, 
					MAP_SUPPLIER_FACTORY_CLASS, 
					this.getClass().getClassLoader(), 
					MapSupplierFactoryAdapter::new));		

//		registerAdapterFactory(
//				new FunctionAdapterFactory<ConsumerFactory<BinaryEntityContainer>, Fail>(
//					ExecPackage.Literals.FAIL, 
//					getBinaryEntityContainerConsumerFactoryClass(), 
//					this.getClass().getClassLoader(), 
//					FailConsumerFactoryAdapter::new));		
//		
//		registerAdapterFactory(
//				new FunctionAdapterFactory<CommandFactory, Fail>(
//					ExecPackage.Literals.FAIL, 
//					CommandFactory.class, 
//					this.getClass().getClassLoader(), 
//					FailCommandFactoryAdapter::new));				
		
		// Sub-packages
		registerAdapterFactory(new ContentAdapterFactory());
		registerAdapterFactory(new ResourcesAdapterFactory());
				
	}
	
}
