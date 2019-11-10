/**
 */
package org.nasdanika.ncore;

import java.lang.Object;
import org.nasdanika.common.SupplierFactory;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Typed Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Typed element is a _LegacyCommandToRemove factory with specified return type, e.g. ``java.lang.Integer``. Typed element gets its result from the context's service with the specified type. The primary pupose of of the typed element is to serve as a specification/description.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.TypedElement#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.ncore.TypedElement#isRequired <em>Required</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getTypedElement()
 * @model superTypes="org.nasdanika.ncore.ModelElement org.nasdanika.ncore.WorkFactory&lt;org.eclipse.emf.ecore.EJavaObject&gt;"
 * @generated
 */
public interface TypedElement extends ModelElement, SupplierFactory<Object> {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Fully qualified Java type name. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.nasdanika.ncore.NcorePackage#getTypedElement_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.TypedElement#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates that the element result can not be null.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Required</em>' attribute.
	 * @see #setRequired(boolean)
	 * @see org.nasdanika.ncore.NcorePackage#getTypedElement_Required()
	 * @model
	 * @generated
	 */
	boolean isRequired();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.TypedElement#isRequired <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Required</em>' attribute.
	 * @see #isRequired()
	 * @generated
	 */
	void setRequired(boolean value);

} // TypedElement
