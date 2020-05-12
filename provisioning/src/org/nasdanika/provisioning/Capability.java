/**
 */
package org.nasdanika.rigel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Capability</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Capability is something required by an issue.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.Capability#getRequiredBy <em>Required By</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.rigel.RigelPackage#getCapability()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Capability extends EObject {

	/**
	 * Returns the value of the '<em><b>Required By</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.rigel.Requirement}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.rigel.Requirement#getRequiredCapabilities <em>Required Capabilities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Activities using/leveraging this resource.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Required By</em>' reference list.
	 * @see org.nasdanika.rigel.RigelPackage#getCapability_RequiredBy()
	 * @see org.nasdanika.rigel.Requirement#getRequiredCapabilities
	 * @model opposite="requiredCapabilities"
	 *        annotation="urn:org.nasdanika label_ru='\u041f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u0435\u043b\u0438' documentation_ru='\u0420\u0430\u0431\u043e\u0442\u044b \u0438 \u043e\u043f\u0435\u0440\u0430\u0446\u0438\u0438 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u044e\u0449\u0438\u0435 \u0434\u0430\u043d\u043d\u044b\u0439 \u0440\u0435\u0441\u0443\u0440\u0441.'"
	 * @generated
	 */
	EList<Requirement> getRequiredBy();
} // Capability
