/**
 */
package org.nasdanika.rigel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * References a shared activity defined elsewhere.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.ActivityReference#getActivity <em>Activity</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.rigel.RigelPackage#getActivityReference()
 * @model annotation="urn:org.nasdanika label_ru='\u0421\u0441\u044b\u043b\u043a\u0430 \u043d\u0430 \u0440\u0430\u0431\u043e\u0442\u0443' documentation_ru='???\u0421\u0441\u044b\u043b\u043a\u0430 \u043d\u0430 \u043e\u0431\u0449\u0438\u0439 \u0432\u0438\u0434 \u0434\u0435\u044f\u0442\u0435\u043b\u044c\u043d\u043e\u0441\u0442\u0438, \u043e\u043f\u0440\u0435\u0434\u0435\u043b\u0435\u043d\u043d\u044b\u0439 \u0432 \u0434\u0440\u0443\u0433\u043e\u043c \u043c\u0435\u0441\u0442\u0435.'"
 * @generated
 */
public interface ActivityReference extends PackageElement, Source, Target {
	/**
	 * Returns the value of the '<em><b>Activity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * By performing an activity participants produce outputs from inputs using resources.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Activity</em>' reference.
	 * @see #setActivity(Activity)
	 * @see org.nasdanika.rigel.RigelPackage#getActivityReference_Activity()
	 * @model annotation="urn:org.nasdanika label_ru='\u0420\u0430\u0431\u043e\u0442\u0430' documentation_ru='\u0412\u044b\u043f\u043e\u043b\u043d\u044f\u044f \u0440\u0430\u0431\u043e\u0442\u0443 \u0438\u0441\u043f\u043e\u043b\u043d\u0438\u0442\u0435\u043b\u0438 \u043f\u0440\u043e\u0438\u0437\u0432\u043e\u0434\u044f\u0442 \u0438\u0441\u0445\u043e\u0434\u044f\u0449\u0438\u0435 (\u0446\u0435\u043b\u0435\u0432\u044b\u0435) \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b, \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u0443\u044f \u0432\u0445\u043e\u0434\u044f\u0449\u0438\u0435 \u0430\u0440\u0442\u0435\u0444\u0430\u043a\u0442\u044b \u0438 \u0440\u0435\u0441\u0443\u0440\u0441\u044b.'"
	 * @generated
	 */
	Activity getActivity();

	/**
	 * Sets the value of the '{@link org.nasdanika.rigel.ActivityReference#getActivity <em>Activity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Activity</em>' reference.
	 * @see #getActivity()
	 * @generated
	 */
	void setActivity(Activity value);

} // ActivityReference
