package org.nasdanika.emf;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.nasdanika.common.Context;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.common.Supplier;

/**
 * This class loads a model, validates it and then returns work for executing generation. 
 * @author Pavel
 *
 */
public class ValidatingModelWorkFactory<T> extends ModelWorkFactory<T> {

	public ValidatingModelWorkFactory(ResourceSet resourceSet, URI modelUri) throws Exception {
		super(resourceSet, modelUri);
	}

	public ValidatingModelWorkFactory(String platformPluginUri, EPackage... ePackages) throws Exception {
		super(platformPluginUri, ePackages);
	}

	public ValidatingModelWorkFactory(URI modelUri, EPackage... ePackages) throws Exception {
		super(modelUri, ePackages);
	}
	
	public ValidatingModelWorkFactory(ResourceSet resourceSet, String platformPluginUri) throws Exception {
		super(resourceSet, platformPluginUri);
	}
	
	@Override
	public Supplier<T> create(Context context) {
		Diagnostician diagnostician = new Diagnostician() {
			
			public Map<Object,Object> createDefaultContext() {
				Map<Object, Object> ctx = super.createDefaultContext();
				ctx.put(Context.class, context);
				return ctx;
			};
			
		};				
		Diagnostic validationResult = diagnostician.validate(root);
		if (validationResult.getSeverity() == Diagnostic.ERROR) {
			throw new ExecutionException(new DiagnosticException(validationResult));
		}
		return super.create(context);
	}

}
