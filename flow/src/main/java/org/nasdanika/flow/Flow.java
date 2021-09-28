/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Flow</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.Flow#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getFlow()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/flow/journey.md'"
 * @generated
 */
public interface Flow extends Activity<Flow> {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.nasdanika.flow.FlowElement<?>},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Elements of this journey.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Elements</em>' map.
	 * @see org.nasdanika.flow.FlowPackage#getFlow_Elements()
	 * @model mapType="org.nasdanika.flow.FlowElementEntry&lt;org.eclipse.emf.ecore.EString, org.nasdanika.flow.FlowElement&lt;?&gt;&gt;"
	 * @generated
	 */
	EMap<String, FlowElement<?>> getElements();

} // Flow
