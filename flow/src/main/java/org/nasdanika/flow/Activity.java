/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.Activity#getServices <em>Services</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getActivity()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/flow/activity.md' load-doc-reference='doc/activity-load-doc.md'"
 * @generated
 */
public interface Activity<T extends Activity<T>> extends FlowElement<T> {
	/**
	 * Returns the value of the '<em><b>Services</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Service}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Service#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Derived opposite to Service target.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Services</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getActivity_Services()
	 * @see org.nasdanika.flow.Service#getTarget
	 * @model opposite="target" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<Service> getServices();

} // Activity
