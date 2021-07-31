package org.nasdanika.exec.gen;

import java.io.InputStream;

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
	
	public ExecutionParticpantAdapterFactory() {
		
		// Block
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<InputStream>, Block>(
					ExecPackage.Literals.BLOCK, 
					getInputStreamSupplierFactoryClass(), 
					this.getClass().getClassLoader(), 
					BlockSupplierFactoryAdapter::new));		

		registerAdapterFactory(
				new FunctionAdapterFactory<ConsumerFactory<BinaryEntityContainer>, Block>(
					ExecPackage.Literals.BLOCK, 
					getBinaryEntityContainerConsumerFactoryClass(), 
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
					getInputStreamSupplierFactoryClass(), 
					this.getClass().getClassLoader(), 
					CallSupplierFactoryAdapter::new));		
		
		// Configurator
		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory<InputStream>, Configurator>(
					ExecPackage.Literals.CONFIGURATOR, 
					getInputStreamSupplierFactoryClass(), 
					this.getClass().getClassLoader(), 
					ConfiguratorSupplierFactoryAdapter::new));		

		registerAdapterFactory(
				new FunctionAdapterFactory<ConsumerFactory<BinaryEntityContainer>, Configurator>(
					ExecPackage.Literals.CONFIGURATOR, 
					getBinaryEntityContainerConsumerFactoryClass(), 
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
					getInputStreamSupplierFactoryClass(), 
					this.getClass().getClassLoader(), 
					FailSupplierFactoryAdapter::new));		

		registerAdapterFactory(
				new FunctionAdapterFactory<ConsumerFactory<BinaryEntityContainer>, Fail>(
					ExecPackage.Literals.FAIL, 
					getBinaryEntityContainerConsumerFactoryClass(), 
					this.getClass().getClassLoader(), 
					FailConsumerFactoryAdapter::new));		
		
		registerAdapterFactory(
				new FunctionAdapterFactory<CommandFactory, Fail>(
					ExecPackage.Literals.FAIL, 
					CommandFactory.class, 
					this.getClass().getClassLoader(), 
					FailCommandFactoryAdapter::new));				
		
		// Sub-packages
		registerAdapterFactory(new ContentAdapterFactory());
		registerAdapterFactory(new ResourcesAdapterFactory());
				
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Class<SupplierFactory<InputStream>> getInputStreamSupplierFactoryClass() {
		return (Class) SupplierFactory.class;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Class<ConsumerFactory<BinaryEntityContainer>> getBinaryEntityContainerConsumerFactoryClass() {
		return (Class) ConsumerFactory.class;
	}

	
}
