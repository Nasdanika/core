/**
 */
package org.nasdanika.ncore;

import java.lang.String;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Adaptable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getUri <em>Uri</em>}</li>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getUuid <em>Uuid</em>}</li>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getActionPrototype <em>Action Prototype</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getModelElement()
 * @model abstract="true" superTypes="org.nasdanika.ncore.Marked org.nasdanika.ncore.Adaptable"
 *        annotation="urn:org.nasdanika documentation-reference='doc/model-element.md'"
 * @generated
 */
public interface ModelElement extends Marked, Adaptable {
	/**
	 * Returns the value of the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If element's URI is not set then its default value is derived from the container URI and containment reference. 
	 * This is a logical URI and it can be used for cross-referencing of elements in a resource-independent fashion.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Uri</em>' attribute.
	 * @see #setUri(String)
	 * @see org.nasdanika.ncore.NcorePackage#getModelElement_Uri()
	 * @model
	 * @generated
	 */
	String getUri();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.ModelElement#getUri <em>Uri</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uri</em>' attribute.
	 * @see #getUri()
	 * @generated
	 */
	void setUri(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Description in HTML.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.nasdanika.ncore.NcorePackage#getModelElement_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.ModelElement#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uuid</em>' attribute.
	 * @see #setUuid(String)
	 * @see org.nasdanika.ncore.NcorePackage#getModelElement_Uuid()
	 * @model
	 * @generated
	 */
	String getUuid();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.ModelElement#getUuid <em>Uuid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uuid</em>' attribute.
	 * @see #getUuid()
	 * @generated
	 */
	void setUuid(String value);

	/**
	 * Returns the value of the '<em><b>Action Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If this reference is not set then EObjectActionProvider creates a new Action using AppFactory in newAction() method. 
	 * If this reference is set and is Action then a copy of the action is created and returned.
	 * Otherwise the reference value it is adapted to ActionProvider which is used to create an action. 
	 * This allows to merge actions and chain action generation. E.g. generate Ecore model documentation and merge it into the Engineering documentation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Action Prototype</em>' reference.
	 * @see #setActionPrototype(EObject)
	 * @see org.nasdanika.ncore.NcorePackage#getModelElement_ActionPrototype()
	 * @model
	 * @generated
	 */
	EObject getActionPrototype();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.ModelElement#getActionPrototype <em>Action Prototype</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Action Prototype</em>' reference.
	 * @see #getActionPrototype()
	 * @generated
	 */
	void setActionPrototype(EObject value);

} // ModelElement
