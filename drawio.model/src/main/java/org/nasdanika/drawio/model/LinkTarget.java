/**
 */
package org.nasdanika.drawio.model;

import org.eclipse.emf.common.util.EList;

import org.nasdanika.ncore.Marked;
import org.nasdanika.ncore.StringIdentity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.LinkTarget#getLinks <em>Links</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.drawio.model.ModelPackage#getLinkTarget()
 * @model
 * @generated
 */
public interface LinkTarget extends Marked, StringIdentity {
	/**
	 * Returns the value of the '<em><b>Links</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.drawio.model.ModelElement}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.drawio.model.ModelElement#getLinkTarget <em>Link Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Model elements linking to this page
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Links</em>' reference list.
	 * @see org.nasdanika.drawio.model.ModelPackage#getLinkTarget_Links()
	 * @see org.nasdanika.drawio.model.ModelElement#getLinkTarget
	 * @model opposite="linkTarget"
	 * @generated
	 */
	EList<ModelElement> getLinks();

} // LinkTarget
