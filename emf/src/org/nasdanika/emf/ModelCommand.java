package org.nasdanika.emf;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.nasdanika.cli.DelegatingCommand;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;

import picocli.CommandLine.Parameters;

/**
 * Loads a model from URL, optionally validates it and then passes to consumer.
 * @author Pavel
 *
 */
public abstract class ModelCommand<T extends EObject> extends DelegatingCommand {
	
	@Parameters(
			paramLabel = "URI", 
			description = "Model (object) URI resolved relative to the current directory. May include fragment to address a non-root object")
	protected String uri;
	
	/**
	 * @return Consumer factory which create a consumer to pass the loaded model to.
	 */
	protected abstract ConsumerFactory<T> getConsumerFactory();
	
	/**
	 * @return {@link EPackage}'s to register with the resource set for loading the model.
	 * This implementation returns all registered packages.
	 */
	protected Collection<EPackage> getEPackages() {
		Registry registry = EPackage.Registry.INSTANCE;
		return new ArrayList<String>(registry.keySet()).stream().map(nsURI -> registry.getEPackage(nsURI)).collect(Collectors.toList());
	}
	
	protected Supplier<T> createSupplier(Context context) {
		return new Supplier<T>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Loading model";
			}

			@SuppressWarnings("unchecked")
			@Override
			public T execute(ProgressMonitor progressMonitor) throws Exception {
				
				// Creating a resource set
				ResourceSetImpl resourceSet = new ResourceSetImpl();
				resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
				for (EPackage ePackage: getEPackages()) {
					resourceSet.getPackageRegistry().put(ePackage.getNsURI(), ePackage);
				}

				URI modelUri = URI.createURI(new File(".").toURI().resolve(ModelCommand.this.uri).toString());
				Resource modelResource = resourceSet.getResource(modelUri, true);
				EObject target = modelUri.hasFragment() ? modelResource.getEObject(modelUri.fragment()) : modelResource.getContents().iterator().next();			
				
				Diagnostician diagnostician = new Diagnostician() {
					
					public Map<Object,Object> createDefaultContext() {
						Map<Object, Object> ctx = super.createDefaultContext();
						ctx.put(Context.class, context);
						return ctx;
					};
					
				};				
				Diagnostic validationResult = diagnostician.validate(target);
				if (validationResult.getSeverity() == Diagnostic.ERROR) {
					throw new DiagnosticException(validationResult);
				}
				return (T) target;
			}
		};
	}

	/**
	 * Supplier to Consumer. Supplier loads model from URL, consumer takes the model as an argument.
	 */
	@Override
	protected CommandFactory getCommandFactory() {
		return new CommandFactory() {
			
			@Override
			public org.nasdanika.common.Command create(Context context) throws Exception {
				return createSupplier(context).then(getConsumerFactory().create(context));
			}
		};
	}

}
