/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.common.util.EMap;
import org.nasdanika.ncore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.Package#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.nasdanika.flow.Package#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.nasdanika.flow.Package#getSubPackages <em>Sub Packages</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getPackage()
 * @model annotation="urn:org.nasdanika reference-types='subPackages: zorro'"
 * @generated
 */
public interface Package extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Extends</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Package}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Package#getExtensions <em>Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Journey can  extend another journey and inherit its elements. Inherited elements can be overriden or suppressed.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extends</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getPackage_Extends()
	 * @see org.nasdanika.flow.Package#getExtensions
	 * @model opposite="extensions"
	 * @generated
	 */
	EList<Package> getExtends();

	/**
	 * Returns the value of the '<em><b>Extensions</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Package}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Package#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Journeys extending this journey.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extensions</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getPackage_Extensions()
	 * @see org.nasdanika.flow.Package#getExtends
	 * @model opposite="extends" changeable="false" derived="true"
	 * @generated
	 */
	EList<Package> getExtensions();

	/**
	 * Returns the value of the '<em><b>Sub Packages</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.nasdanika.flow.Package},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Packages</em>' map.
	 * @see org.nasdanika.flow.FlowPackage#getPackage_SubPackages()
	 * @model mapType="org.nasdanika.flow.PackageEntry&lt;org.eclipse.emf.ecore.EString, org.nasdanika.flow.Package&gt;"
	 * @generated
	 */
	EMap<String, Package> getSubPackages();

} // Package
