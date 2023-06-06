package org.nasdanika.emf.persistence;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;

/**
 * Supplies a {@link Consumer} of {@link EObject} which invokes the operation.
 * 
 * @author Pavel
 * @deprecated Experimental
 */
@Deprecated
public class EOperationSupplierFactory_ extends ETypedElementSupplierFactoryFeatureObject<Consumer<EObject>> {
	
	private EOperation eOperation;
	private EClass eClass;

	public EOperationSupplierFactory(			
			EObjectLoader loader,
			EClass eClass,
			EOperation eOperation,
			BiFunction<EClass,ENamedElement,String> keyProvider) {
		
		super(loader);		
		this.eClass = eClass;
		this.eOperation = eOperation;
		for (EParameter parameter: eOperation.getEParameters()) {
			String featureKey = keyProvider.apply(eClass, parameter);
			if (featureKey == null) {
				continue;
			}
			featureMap.put(featureKey, parameter);
// TODO			addFeature(wrapParameter(featureKey, parameter, loader, keyProvider));
		}
		
	}

	@Override
	protected Function<Map<Object, Object>, Consumer<EObject>> createResultFunction(Context context) {
		return new Function<Map<Object,Object>, Consumer<EObject>>() {
			
			@Override
			public double size() {
				return 1;
			}
			
			@Override
			public String name() {
				return "Creating consumer which invokes " + eClass.getName() + "." + eOperation.getName();
			}
			
			@Override
			public Consumer<EObject> execute(Map<Object, Object> data, ProgressMonitor progressMonitor) {
				return eObj -> {
					System.out.println("TODO - invoke " + eOperation + " on " + eObj);
				};				
			}
			
		};
	}
	
	
}
