/**
 */
package org.nasdanika.ncore;

import java.lang.String;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Adaptable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for many Nasdanika model classes.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getUri <em>Uri</em>}</li>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getUuid <em>Uuid</em>}</li>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getActionPrototype <em>Action Prototype</em>}</li>
 *   <li>{@link org.nasdanika.ncore.ModelElement#getRepresentations <em>Representations</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getModelElement()
 * @model abstract="true" superTypes="org.nasdanika.ncore.Marked org.nasdanika.ncore.Adaptable"
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
	 * <!-- begin-model-doc -->
	 * Optional unique identifier for this model element. For root objects UUID is used to compute URI, if the URI is not set.
	 * <!-- end-model-doc -->
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

	/**
	 * Returns the value of the '<em><b>Representations</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Mapping of representation keys to URI's of representation resources. URI's are resolved relative to the model element resource URI.
	 * During object loading resources are loaded and linked to the object. Additional processing depends on the resource type.
	 * In case of Drawio diagrams, diagram elements are semantically mapped to model elements and representation resource root elements are added as children to the object.
	 * See [Nasdanika Core Drawio](../drawio/index.html) and [Nasdanika Core EMF](../emf/index.html) documentation for more information.
	 * 
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Representations</em>' map.
	 * @see org.nasdanika.ncore.NcorePackage#getModelElement_Representations()
	 * @model mapType="org.nasdanika.ncore.RepresentationEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 *        annotation="urn:org.nasdanika homogenous='true' load-doc-reference='doc/model-element--representations.md'"
	 * @generated
	 */
	EMap<String, String> getRepresentations();

} // ModelElement
