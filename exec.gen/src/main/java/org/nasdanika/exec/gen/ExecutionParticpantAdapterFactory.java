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
import org.nasdanika.exec.content.ContentPackage;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.exec.content.Text;
import org.nasdanika.exec.gen.content.ResourceSupplierFactoryAdapter;
import org.nasdanika.exec.gen.content.TextSupplierFactoryAdapter;

/**
 * Provides adapters for the Engineering model elements.
 * @author Pavel
 *
 */
public class ExecutionParticpantAdapterFactory extends ComposedAdapterFactory {
	
	public ExecutionParticpantAdapterFactory() {

		// Exec
		
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Class<ConsumerFactory<BinaryEntityContainer>> getBinaryEntityContainerConsumerFactoryClass() {
		return (Class) ConsumerFactory.class;
	}

	
}
