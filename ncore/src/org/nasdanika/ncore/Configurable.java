/**
 */
package org.nasdanika.ncore;

import java.lang.Object;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

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
	 * The list contents are of type {@link org.nasdanika.ncore.Entry}<code>&lt;java.lang.Object&gt;</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Configuration entries.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Configuration</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getConfigurable_Configuration()
	 * @model type="org.nasdanika.ncore.Entry&lt;org.eclipse.emf.ecore.EJavaObject&gt;" containment="true"
	 * @generated
	 */
	EList<Entry<Object>> getConfiguration();
	
	/**
	 * Creates a supplier of context which is constructed from the configuration entries and is chained with the argument context.
	 * @param context
	 * @return
	 */
	default org.nasdanika.common.Supplier<Context> createContextSupplier(Context context) {
		throw new UnsupportedOperationException("TODO - implement");
	}

} // Configurable
