/**
 */
package org.nasdanika.drawio.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.nasdanika.ncore.Marked;
import org.nasdanika.ncore.StringIdentity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.ModelElement#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.ModelElement#getLabel <em>Label</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.ModelElement#getLink <em>Link</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.ModelElement#getLinkedPage <em>Linked Page</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.ModelElement#getStyle <em>Style</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.ModelElement#getTags <em>Tags</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.ModelElement#getTooltip <em>Tooltip</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.ModelElement#isVisible <em>Visible</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.drawio.model.ModelPackage#getModelElement()
 * @model abstract="true"
 * @generated
 */
public interface ModelElement extends Marked, StringIdentity {
	/**
	 * Returns the value of the '<em><b>Properties</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Properties</em>' map.
	 * @see org.nasdanika.drawio.model.ModelPackage#getModelElement_Properties()
	 * @model mapType="org.nasdanika.ncore.StringEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getProperties();

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see org.nasdanika.drawio.model.ModelPackage#getModelElement_Label()
	 * @model
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.ModelElement#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Link</em>' attribute.
	 * @see #setLink(String)
	 * @see org.nasdanika.drawio.model.ModelPackage#getModelElement_Link()
	 * @model
	 * @generated
	 */
	String getLink();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.ModelElement#getLink <em>Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link</em>' attribute.
	 * @see #getLink()
	 * @generated
	 */
	void setLink(String value);

	/**
	 * Returns the value of the '<em><b>Linked Page</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.drawio.model.Page#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Linked Page</em>' reference.
	 * @see #setLinkedPage(Page)
	 * @see org.nasdanika.drawio.model.ModelPackage#getModelElement_LinkedPage()
	 * @see org.nasdanika.drawio.model.Page#getLinks
	 * @model opposite="links"
	 * @generated
	 */
	Page getLinkedPage();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.ModelElement#getLinkedPage <em>Linked Page</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Linked Page</em>' reference.
	 * @see #getLinkedPage()
	 * @generated
	 */
	void setLinkedPage(Page value);

	/**
	 * Returns the value of the '<em><b>Style</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Style</em>' map.
	 * @see org.nasdanika.drawio.model.ModelPackage#getModelElement_Style()
	 * @model mapType="org.nasdanika.ncore.StringEntry&lt;org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString&gt;"
	 * @generated
	 */
	EMap<String, String> getStyle();

	/**
	 * Returns the value of the '<em><b>Tags</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' attribute list.
	 * @see org.nasdanika.drawio.model.ModelPackage#getModelElement_Tags()
	 * @model
	 * @generated
	 */
	EList<String> getTags();

	/**
	 * Returns the value of the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tooltip</em>' attribute.
	 * @see #setTooltip(String)
	 * @see org.nasdanika.drawio.model.ModelPackage#getModelElement_Tooltip()
	 * @model
	 * @generated
	 */
	String getTooltip();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.ModelElement#getTooltip <em>Tooltip</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Tooltip</em>' attribute.
	 * @see #getTooltip()
	 * @generated
	 */
	void setTooltip(String value);

	/**
	 * Returns the value of the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Visible</em>' attribute.
	 * @see #setVisible(boolean)
	 * @see org.nasdanika.drawio.model.ModelPackage#getModelElement_Visible()
	 * @model
	 * @generated
	 */
	boolean isVisible();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.ModelElement#isVisible <em>Visible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Visible</em>' attribute.
	 * @see #isVisible()
	 * @generated
	 */
	void setVisible(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	Document getDocument();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	Page getPage();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ModelElement getModelElementById(String modelElementId);

} // ModelElement
