/**
 */
package org.nasdanika.ncore;

import java.lang.String;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Composite</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A simple class for building hierarchies of documented entities. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Composite#getId <em>Id</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Composite#getChildren <em>Children</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getComposite()
 * @model
 * @generated
 */
public interface Composite extends NamedElement, Documented {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A unique identifier of this composite among its siblings.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.nasdanika.ncore.NcorePackage#getComposite_Id()
	 * @model annotation="urn:org.nasdanika default-feature='true'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Composite#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.Composite}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Composite's children.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getComposite_Children()
	 * @model containment="true" keys="id"
	 * @generated
	 */
	EList<Composite> getChildren();

} // Composite
