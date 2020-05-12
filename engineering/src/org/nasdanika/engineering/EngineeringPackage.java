/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * A model of engineering - a process of building something composite by resolving issues associated with solution components by engineers in increments and making the new functionality available in releases.
 * <!-- end-model-doc -->
 * @see org.nasdanika.engineering.EngineeringFactory
 * @model kind="package"
 * @generated
 */
public interface EngineeringPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "engineering";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "urn:org.nasdanika.engineering";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.engineering";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EngineeringPackage eINSTANCE = org.nasdanika.engineering.impl.EngineeringPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.engineering.EngineeredElement <em>Engineered Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.engineering.EngineeredElement
	 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getEngineeredElement()
	 * @generated
	 */
	int ENGINEERED_ELEMENT = 0;

	/**
	 * The number of structural features of the '<em>Engineered Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERED_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Engineered Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENGINEERED_ELEMENT_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.nasdanika.engineering.EngineeredElement <em>Engineered Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Engineered Element</em>'.
	 * @see org.nasdanika.engineering.EngineeredElement
	 * @generated
	 */
	EClass getEngineeredElement();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EngineeringFactory getEngineeringFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.nasdanika.engineering.EngineeredElement <em>Engineered Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.engineering.EngineeredElement
		 * @see org.nasdanika.engineering.impl.EngineeringPackageImpl#getEngineeredElement()
		 * @generated
		 */
		EClass ENGINEERED_ELEMENT = eINSTANCE.getEngineeredElement();

	}

} //EngineeringPackage
