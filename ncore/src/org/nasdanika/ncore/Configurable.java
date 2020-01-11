/**
 */
package org.nasdanika.ncore;

import java.util.Map;
import java.lang.Object;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.SupplierFactory;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Configurable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Model element which contains configuration entries.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Configurable#getConfiguration <em>Configuration</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getConfigurable()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Configurable extends EObject {
	/**
	 * Returns the value of the '<em><b>Configuration</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.common.SupplierFactory}<code>&lt;java.lang.Object&gt;</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Configuration entries.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Configuration</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getConfigurable_Configuration()
	 * @model type="org.nasdanika.ncore.ISupplierFactory&lt;org.eclipse.emf.ecore.EJavaObject&gt;" containment="true"
	 * @generated
	 */
	EList<SupplierFactory<Object>> getConfiguration();
	
	/**
	 * Contextifies the supplier factory passed as an argument with configuration entries.
	 * Returns the factory AS-IS if the configuration is empty. 
	 * @param supplierFactory
	 * @return
	 */
	default <T> org.nasdanika.common.SupplierFactory<T> configure(SupplierFactory<T> supplierFactory) {
		if (getConfiguration().isEmpty()) {
			return supplierFactory;
		}
		MapCompoundSupplierFactory<String, Object> entriesFactory = new MapCompoundSupplierFactory<String, Object>("Entries");
		for (SupplierFactory<java.lang.Object> cef: getConfiguration()) {
			if (cef instanceof Entry) {
				entriesFactory.put(((Entry<?>) cef).getName(), cef);
			}
		}
		
		FunctionFactory<Map<String, java.lang.Object>, org.nasdanika.common.Context> contextFactory = new FunctionFactory<Map<String,Object>, org.nasdanika.common.Context>() {
			
			@Override
			public Function<Map<String, java.lang.Object>, org.nasdanika.common.Context> create(org.nasdanika.common.Context context) throws Exception {
				return Function.fromBiFunction((map, progressMonitor) -> {
					return org.nasdanika.common.Context.wrap(map::get).compose(context);
				}, "Contextifier", 1);
			}
			
		};
		
		return supplierFactory.contextify(entriesFactory.then(contextFactory));
	}

} // Configurable
