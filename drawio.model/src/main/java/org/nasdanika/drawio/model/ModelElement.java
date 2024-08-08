/**
 */
package org.nasdanika.drawio.model;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

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
 *   <li>{@link org.nasdanika.drawio.model.ModelElement#getLinkTarget <em>Link Target</em>}</li>
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
public interface ModelElement extends LinkTarget {
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
	 * Returns the value of the '<em><b>Link Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.drawio.model.LinkTarget#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Link Target</em>' reference.
	 * @see #setLinkTarget(LinkTarget)
	 * @see org.nasdanika.drawio.model.ModelPackage#getModelElement_LinkTarget()
	 * @see org.nasdanika.drawio.model.LinkTarget#getLinks
	 * @model opposite="links"
	 * @generated
	 */
	LinkTarget getLinkTarget();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.ModelElement#getLinkTarget <em>Link Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Link Target</em>' reference.
	 * @see #getLinkTarget()
	 * @generated
	 */
	void setLinkTarget(LinkTarget value);

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
	 * Returns the value of the '<em><b>Tags</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.drawio.model.Tag}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.drawio.model.Tag#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Tags</em>' reference list.
	 * @see org.nasdanika.drawio.model.ModelPackage#getModelElement_Tags()
	 * @see org.nasdanika.drawio.model.Tag#getElements
	 * @model opposite="elements"
	 * @generated
	 */
	EList<Tag> getTags();

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

} // ModelElement
