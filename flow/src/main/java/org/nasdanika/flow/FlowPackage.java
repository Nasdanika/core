/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.ncore.NcorePackage;

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
 * @see org.nasdanika.flow.FlowFactory
 * @model kind="package"
 *        annotation="urn:org.nasdanika documentation-reference='doc/package-summary.md'"
 * @generated
 */
public interface FlowPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "flow";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "urn:org.nasdanika.flow";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.flow";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	FlowPackage eINSTANCE = org.nasdanika.flow.impl.FlowPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.PackageElementImpl <em>Package Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.PackageElementImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getPackageElement()
	 * @generated
	 */
	int PACKAGE_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ELEMENT__MARKER = NcorePackage.NAMED_ELEMENT__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ELEMENT__URI = NcorePackage.NAMED_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ELEMENT__DESCRIPTION = NcorePackage.NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ELEMENT__UUID = NcorePackage.NAMED_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ELEMENT__NAME = NcorePackage.NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ELEMENT__PROTOTYPE = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ELEMENT__EXTENSIONS = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ELEMENT__EXTENDS = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ELEMENT__MODIFIERS = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ELEMENT__DOCUMENTATION = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ELEMENT__REPRESENTATIONS = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Package Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ELEMENT_FEATURE_COUNT = NcorePackage.NAMED_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ELEMENT___CREATE = NcorePackage.NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ELEMENT___APPLY__PACKAGEELEMENT = NcorePackage.NAMED_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ELEMENT___RESOLVE__PACKAGEELEMENT = NcorePackage.NAMED_ELEMENT_OPERATION_COUNT + 2;

	/**
	 * The number of operations of the '<em>Package Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ELEMENT_OPERATION_COUNT = NcorePackage.NAMED_ELEMENT_OPERATION_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.RepresentationEntryImpl <em>Representation Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.RepresentationEntryImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getRepresentationEntry()
	 * @generated
	 */
	int REPRESENTATION_ENTRY = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPRESENTATION_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPRESENTATION_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Representation Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPRESENTATION_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Representation Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPRESENTATION_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.PackageImpl <em>Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.PackageImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getPackage()
	 * @generated
	 */
	int PACKAGE = 2;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__MARKER = PACKAGE_ELEMENT__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__URI = PACKAGE_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__DESCRIPTION = PACKAGE_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__UUID = PACKAGE_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__NAME = PACKAGE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__PROTOTYPE = PACKAGE_ELEMENT__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__EXTENSIONS = PACKAGE_ELEMENT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__EXTENDS = PACKAGE_ELEMENT__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__MODIFIERS = PACKAGE_ELEMENT__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__DOCUMENTATION = PACKAGE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__REPRESENTATIONS = PACKAGE_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Super Packages</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__SUPER_PACKAGES = PACKAGE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Sub Packages</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__SUB_PACKAGES = PACKAGE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__PARTICIPANTS = PACKAGE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__RESOURCES = PACKAGE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Artifacts</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__ARTIFACTS = PACKAGE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Activities</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE__ACTIVITIES = PACKAGE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_FEATURE_COUNT = PACKAGE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE___CREATE = PACKAGE_ELEMENT___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE___APPLY__PACKAGEELEMENT = PACKAGE_ELEMENT___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE___RESOLVE__PACKAGEELEMENT = PACKAGE_ELEMENT___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_OPERATION_COUNT = PACKAGE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.PackageEntryImpl <em>Package Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.PackageEntryImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getPackageEntry()
	 * @generated
	 */
	int PACKAGE_ENTRY = 3;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Package Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Package Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ServiceProviderImpl <em>Service Provider</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ServiceProviderImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getServiceProvider()
	 * @generated
	 */
	int SERVICE_PROVIDER = 4;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROVIDER__MARKER = PACKAGE_ELEMENT__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROVIDER__URI = PACKAGE_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROVIDER__DESCRIPTION = PACKAGE_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROVIDER__UUID = PACKAGE_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROVIDER__NAME = PACKAGE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROVIDER__PROTOTYPE = PACKAGE_ELEMENT__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROVIDER__EXTENSIONS = PACKAGE_ELEMENT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROVIDER__EXTENDS = PACKAGE_ELEMENT__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROVIDER__MODIFIERS = PACKAGE_ELEMENT__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROVIDER__DOCUMENTATION = PACKAGE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROVIDER__REPRESENTATIONS = PACKAGE_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Services</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROVIDER__SERVICES = PACKAGE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Service Provider</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROVIDER_FEATURE_COUNT = PACKAGE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROVIDER___CREATE = PACKAGE_ELEMENT___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROVIDER___APPLY__PACKAGEELEMENT = PACKAGE_ELEMENT___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROVIDER___RESOLVE__PACKAGEELEMENT = PACKAGE_ELEMENT___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Service Provider</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_PROVIDER_OPERATION_COUNT = PACKAGE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ParticipantImpl <em>Participant</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ParticipantImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getParticipant()
	 * @generated
	 */
	int PARTICIPANT = 5;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__MARKER = SERVICE_PROVIDER__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__URI = SERVICE_PROVIDER__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__DESCRIPTION = SERVICE_PROVIDER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__UUID = SERVICE_PROVIDER__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__NAME = SERVICE_PROVIDER__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__PROTOTYPE = SERVICE_PROVIDER__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__EXTENSIONS = SERVICE_PROVIDER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__EXTENDS = SERVICE_PROVIDER__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__MODIFIERS = SERVICE_PROVIDER__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__DOCUMENTATION = SERVICE_PROVIDER__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__REPRESENTATIONS = SERVICE_PROVIDER__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Services</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__SERVICES = SERVICE_PROVIDER__SERVICES;

	/**
	 * The feature id for the '<em><b>Participates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__PARTICIPATES = SERVICE_PROVIDER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__RESOURCES = SERVICE_PROVIDER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__ARTIFACTS = SERVICE_PROVIDER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Specializations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__SPECIALIZATIONS = SERVICE_PROVIDER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Base Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__BASE_KEYS = SERVICE_PROVIDER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Bases</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__BASES = SERVICE_PROVIDER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__RESPONSIBLE = SERVICE_PROVIDER_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__ACCOUNTABLE = SERVICE_PROVIDER_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__CONSULTED = SERVICE_PROVIDER_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__INFORMED = SERVICE_PROVIDER_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Children</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT__CHILDREN = SERVICE_PROVIDER_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_FEATURE_COUNT = SERVICE_PROVIDER_FEATURE_COUNT + 11;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT___CREATE = SERVICE_PROVIDER___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT___APPLY__PACKAGEELEMENT = SERVICE_PROVIDER___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT___RESOLVE__PACKAGEELEMENT = SERVICE_PROVIDER___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Participant</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_OPERATION_COUNT = SERVICE_PROVIDER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ParticipantEntryImpl <em>Participant Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ParticipantEntryImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getParticipantEntry()
	 * @generated
	 */
	int PARTICIPANT_ENTRY = 6;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Participant Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Participant Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ResourceImpl <em>Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ResourceImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getResource()
	 * @generated
	 */
	int RESOURCE = 7;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__MARKER = SERVICE_PROVIDER__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__URI = SERVICE_PROVIDER__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__DESCRIPTION = SERVICE_PROVIDER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__UUID = SERVICE_PROVIDER__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__NAME = SERVICE_PROVIDER__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__PROTOTYPE = SERVICE_PROVIDER__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__EXTENSIONS = SERVICE_PROVIDER__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__EXTENDS = SERVICE_PROVIDER__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__MODIFIERS = SERVICE_PROVIDER__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__DOCUMENTATION = SERVICE_PROVIDER__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__REPRESENTATIONS = SERVICE_PROVIDER__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Services</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__SERVICES = SERVICE_PROVIDER__SERVICES;

	/**
	 * The feature id for the '<em><b>Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__ARTIFACTS = SERVICE_PROVIDER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Used In</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__USED_IN = SERVICE_PROVIDER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Used By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__USED_BY = SERVICE_PROVIDER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Children</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__CHILDREN = SERVICE_PROVIDER_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FEATURE_COUNT = SERVICE_PROVIDER_FEATURE_COUNT + 4;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE___CREATE = SERVICE_PROVIDER___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE___APPLY__PACKAGEELEMENT = SERVICE_PROVIDER___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE___RESOLVE__PACKAGEELEMENT = SERVICE_PROVIDER___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPERATION_COUNT = SERVICE_PROVIDER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ResourceEntryImpl <em>Resource Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ResourceEntryImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getResourceEntry()
	 * @generated
	 */
	int RESOURCE_ENTRY = 8;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Resource Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Resource Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ParticipantResponsibilityImpl <em>Participant Responsibility</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ParticipantResponsibilityImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getParticipantResponsibility()
	 * @generated
	 */
	int PARTICIPANT_RESPONSIBILITY = 9;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__MARKER = PACKAGE_ELEMENT__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__URI = PACKAGE_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__DESCRIPTION = PACKAGE_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__UUID = PACKAGE_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__NAME = PACKAGE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__PROTOTYPE = PACKAGE_ELEMENT__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__EXTENSIONS = PACKAGE_ELEMENT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__EXTENDS = PACKAGE_ELEMENT__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__MODIFIERS = PACKAGE_ELEMENT__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__DOCUMENTATION = PACKAGE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__REPRESENTATIONS = PACKAGE_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__RESPONSIBLE = PACKAGE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__RESPONSIBLE_KEYS = PACKAGE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE = PACKAGE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE_KEYS = PACKAGE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__CONSULTED = PACKAGE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__CONSULTED_KEYS = PACKAGE_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__INFORMED = PACKAGE_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY__INFORMED_KEYS = PACKAGE_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Participant Responsibility</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT = PACKAGE_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY___CREATE = PACKAGE_ELEMENT___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY___APPLY__PACKAGEELEMENT = PACKAGE_ELEMENT___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY___RESOLVE__PACKAGEELEMENT = PACKAGE_ELEMENT___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Participant Responsibility</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTICIPANT_RESPONSIBILITY_OPERATION_COUNT = PACKAGE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ArtifactImpl <em>Artifact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ArtifactImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getArtifact()
	 * @generated
	 */
	int ARTIFACT = 10;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__MARKER = PARTICIPANT_RESPONSIBILITY__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__URI = PARTICIPANT_RESPONSIBILITY__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__DESCRIPTION = PARTICIPANT_RESPONSIBILITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__UUID = PARTICIPANT_RESPONSIBILITY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__NAME = PARTICIPANT_RESPONSIBILITY__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PROTOTYPE = PARTICIPANT_RESPONSIBILITY__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__EXTENSIONS = PARTICIPANT_RESPONSIBILITY__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__EXTENDS = PARTICIPANT_RESPONSIBILITY__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__MODIFIERS = PARTICIPANT_RESPONSIBILITY__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__DOCUMENTATION = PARTICIPANT_RESPONSIBILITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__REPRESENTATIONS = PARTICIPANT_RESPONSIBILITY__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__RESPONSIBLE = PARTICIPANT_RESPONSIBILITY__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__RESPONSIBLE_KEYS = PARTICIPANT_RESPONSIBILITY__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__ACCOUNTABLE = PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__ACCOUNTABLE_KEYS = PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__CONSULTED = PARTICIPANT_RESPONSIBILITY__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__CONSULTED_KEYS = PARTICIPANT_RESPONSIBILITY__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__INFORMED = PARTICIPANT_RESPONSIBILITY__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__INFORMED_KEYS = PARTICIPANT_RESPONSIBILITY__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Services</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__SERVICES = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Repositories</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__REPOSITORIES = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Repository Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__REPOSITORY_KEYS = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Input For</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__INPUT_FOR = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Output For</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__OUTPUT_FOR = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Payload For</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PAYLOAD_FOR = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Response For</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__RESPONSE_FOR = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Used By</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__USED_BY = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Responsibilities</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__RESPONSIBILITIES = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Children</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__CHILDREN = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Outbound Relationships</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__OUTBOUND_RELATIONSHIPS = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Inbound Relationships</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__INBOUND_RELATIONSHIPS = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Partition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__PARTITION = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT__STYLE = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 13;

	/**
	 * The number of structural features of the '<em>Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_FEATURE_COUNT = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 14;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT___CREATE = PARTICIPANT_RESPONSIBILITY___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT___APPLY__PACKAGEELEMENT = PARTICIPANT_RESPONSIBILITY___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT___RESOLVE__PACKAGEELEMENT = PARTICIPANT_RESPONSIBILITY___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Artifact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_OPERATION_COUNT = PARTICIPANT_RESPONSIBILITY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ArtifactParticipantResponsibilityImpl <em>Artifact Participant Responsibility</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ArtifactParticipantResponsibilityImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getArtifactParticipantResponsibility()
	 * @generated
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY = 12;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.RelationshipImpl <em>Relationship</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.RelationshipImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getRelationship()
	 * @generated
	 */
	int RELATIONSHIP = 13;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.RelationshipEntryImpl <em>Relationship Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.RelationshipEntryImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getRelationshipEntry()
	 * @generated
	 */
	int RELATIONSHIP_ENTRY = 14;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ArtifactEntryImpl <em>Artifact Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ArtifactEntryImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getArtifactEntry()
	 * @generated
	 */
	int ARTIFACT_ENTRY = 11;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Artifact Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Artifact Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__MARKER = PARTICIPANT_RESPONSIBILITY__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__URI = PARTICIPANT_RESPONSIBILITY__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__DESCRIPTION = PARTICIPANT_RESPONSIBILITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__UUID = PARTICIPANT_RESPONSIBILITY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__NAME = PARTICIPANT_RESPONSIBILITY__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__PROTOTYPE = PARTICIPANT_RESPONSIBILITY__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__EXTENSIONS = PARTICIPANT_RESPONSIBILITY__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__EXTENDS = PARTICIPANT_RESPONSIBILITY__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__MODIFIERS = PARTICIPANT_RESPONSIBILITY__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__DOCUMENTATION = PARTICIPANT_RESPONSIBILITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__REPRESENTATIONS = PARTICIPANT_RESPONSIBILITY__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__RESPONSIBLE = PARTICIPANT_RESPONSIBILITY__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__RESPONSIBLE_KEYS = PARTICIPANT_RESPONSIBILITY__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE = PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE_KEYS = PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__CONSULTED = PARTICIPANT_RESPONSIBILITY__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__CONSULTED_KEYS = PARTICIPANT_RESPONSIBILITY__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__INFORMED = PARTICIPANT_RESPONSIBILITY__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__INFORMED_KEYS = PARTICIPANT_RESPONSIBILITY__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Artifact Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT_KEY = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Artifact</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Suppress</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY__SUPPRESS = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Artifact Participant Responsibility</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY___CREATE = PARTICIPANT_RESPONSIBILITY___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY___APPLY__PACKAGEELEMENT = PARTICIPANT_RESPONSIBILITY___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY___RESOLVE__PACKAGEELEMENT = PARTICIPANT_RESPONSIBILITY___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Artifact Participant Responsibility</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARTIFACT_PARTICIPANT_RESPONSIBILITY_OPERATION_COUNT = PARTICIPANT_RESPONSIBILITY_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__MARKER = PACKAGE_ELEMENT__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__URI = PACKAGE_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__DESCRIPTION = PACKAGE_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__UUID = PACKAGE_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__NAME = PACKAGE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__PROTOTYPE = PACKAGE_ELEMENT__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__EXTENSIONS = PACKAGE_ELEMENT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__EXTENDS = PACKAGE_ELEMENT__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__MODIFIERS = PACKAGE_ELEMENT__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__DOCUMENTATION = PACKAGE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__REPRESENTATIONS = PACKAGE_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Target Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__TARGET_KEY = PACKAGE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__TARGET = PACKAGE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP__STYLE = PACKAGE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Relationship</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_FEATURE_COUNT = PACKAGE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP___CREATE = PACKAGE_ELEMENT___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP___APPLY__PACKAGEELEMENT = PACKAGE_ELEMENT___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP___RESOLVE__PACKAGEELEMENT = PACKAGE_ELEMENT___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Relationship</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_OPERATION_COUNT = PACKAGE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Relationship Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Relationship Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATIONSHIP_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.FlowElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.FlowElementImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getFlowElement()
	 * @generated
	 */
	int FLOW_ELEMENT = 15;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__MARKER = PARTICIPANT_RESPONSIBILITY__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__URI = PARTICIPANT_RESPONSIBILITY__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__DESCRIPTION = PARTICIPANT_RESPONSIBILITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__UUID = PARTICIPANT_RESPONSIBILITY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__NAME = PARTICIPANT_RESPONSIBILITY__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__PROTOTYPE = PARTICIPANT_RESPONSIBILITY__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__EXTENSIONS = PARTICIPANT_RESPONSIBILITY__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__EXTENDS = PARTICIPANT_RESPONSIBILITY__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__MODIFIERS = PARTICIPANT_RESPONSIBILITY__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__DOCUMENTATION = PARTICIPANT_RESPONSIBILITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__REPRESENTATIONS = PARTICIPANT_RESPONSIBILITY__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__RESPONSIBLE = PARTICIPANT_RESPONSIBILITY__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__RESPONSIBLE_KEYS = PARTICIPANT_RESPONSIBILITY__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__ACCOUNTABLE = PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__ACCOUNTABLE_KEYS = PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__CONSULTED = PARTICIPANT_RESPONSIBILITY__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__CONSULTED_KEYS = PARTICIPANT_RESPONSIBILITY__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__INFORMED = PARTICIPANT_RESPONSIBILITY__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__INFORMED_KEYS = PARTICIPANT_RESPONSIBILITY__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__OUTPUTS = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__INPUTS = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__CALLS = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Invocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__INVOCATIONS = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__INPUT_ARTIFACTS = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Input Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__INPUT_ARTIFACT_KEYS = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Output Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__OUTPUT_ARTIFACTS = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Output Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__PARTICIPANTS = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Participant Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__PARTICIPANT_KEYS = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__RESOURCES = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Resource Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__RESOURCE_KEYS = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Artifact Responsibilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT__ARTIFACT_RESPONSIBILITIES = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 12;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT_FEATURE_COUNT = PARTICIPANT_RESPONSIBILITY_FEATURE_COUNT + 13;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT___CREATE = PARTICIPANT_RESPONSIBILITY___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT___APPLY__PACKAGEELEMENT = PARTICIPANT_RESPONSIBILITY___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT___RESOLVE__PACKAGEELEMENT = PARTICIPANT_RESPONSIBILITY___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT_OPERATION_COUNT = PARTICIPANT_RESPONSIBILITY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.FlowElementEntryImpl <em>Element Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.FlowElementEntryImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getFlowElementEntry()
	 * @generated
	 */
	int FLOW_ELEMENT_ENTRY = 16;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Element Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Element Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_ELEMENT_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ActivityImpl <em>Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ActivityImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getActivity()
	 * @generated
	 */
	int ACTIVITY = 21;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ActivityEntryImpl <em>Activity Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ActivityEntryImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getActivityEntry()
	 * @generated
	 */
	int ACTIVITY_ENTRY = 22;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ServiceImpl <em>Service</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ServiceImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getService()
	 * @generated
	 */
	int SERVICE = 23;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.TransitionImpl <em>Transition</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.TransitionImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getTransition()
	 * @generated
	 */
	int TRANSITION = 17;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__MARKER = PACKAGE_ELEMENT__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__URI = PACKAGE_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__DESCRIPTION = PACKAGE_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__UUID = PACKAGE_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__NAME = PACKAGE_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__PROTOTYPE = PACKAGE_ELEMENT__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__EXTENSIONS = PACKAGE_ELEMENT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__EXTENDS = PACKAGE_ELEMENT__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__MODIFIERS = PACKAGE_ELEMENT__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__DOCUMENTATION = PACKAGE_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__REPRESENTATIONS = PACKAGE_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Payload</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__PAYLOAD = PACKAGE_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Payload Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__PAYLOAD_KEYS = PACKAGE_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Target Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__TARGET_KEY = PACKAGE_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION__TARGET = PACKAGE_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_FEATURE_COUNT = PACKAGE_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION___CREATE = PACKAGE_ELEMENT___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION___APPLY__PACKAGEELEMENT = PACKAGE_ELEMENT___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION___RESOLVE__PACKAGEELEMENT = PACKAGE_ELEMENT___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Transition</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_OPERATION_COUNT = PACKAGE_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.TransitionEntryImpl <em>Transition Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.TransitionEntryImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getTransitionEntry()
	 * @generated
	 */
	int TRANSITION_ENTRY = 18;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Transition Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Transition Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSITION_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.CallImpl <em>Call</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.CallImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getCall()
	 * @generated
	 */
	int CALL = 19;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__MARKER = TRANSITION__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__URI = TRANSITION__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__DESCRIPTION = TRANSITION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__UUID = TRANSITION__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__NAME = TRANSITION__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__PROTOTYPE = TRANSITION__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__EXTENSIONS = TRANSITION__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__EXTENDS = TRANSITION__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__MODIFIERS = TRANSITION__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__DOCUMENTATION = TRANSITION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__REPRESENTATIONS = TRANSITION__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Payload</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__PAYLOAD = TRANSITION__PAYLOAD;

	/**
	 * The feature id for the '<em><b>Payload Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__PAYLOAD_KEYS = TRANSITION__PAYLOAD_KEYS;

	/**
	 * The feature id for the '<em><b>Target Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__TARGET_KEY = TRANSITION__TARGET_KEY;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__TARGET = TRANSITION__TARGET;

	/**
	 * The feature id for the '<em><b>Response</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__RESPONSE = TRANSITION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Response Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL__RESPONSE_KEYS = TRANSITION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_FEATURE_COUNT = TRANSITION_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL___CREATE = TRANSITION___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL___APPLY__PACKAGEELEMENT = TRANSITION___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL___RESOLVE__PACKAGEELEMENT = TRANSITION___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_OPERATION_COUNT = TRANSITION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.CallEntryImpl <em>Call Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.CallEntryImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getCallEntry()
	 * @generated
	 */
	int CALL_ENTRY = 20;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Call Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Call Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALL_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__MARKER = FLOW_ELEMENT__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__URI = FLOW_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__DESCRIPTION = FLOW_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__UUID = FLOW_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__NAME = FLOW_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PROTOTYPE = FLOW_ELEMENT__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__EXTENSIONS = FLOW_ELEMENT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__EXTENDS = FLOW_ELEMENT__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__MODIFIERS = FLOW_ELEMENT__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__DOCUMENTATION = FLOW_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__REPRESENTATIONS = FLOW_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__RESPONSIBLE = FLOW_ELEMENT__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__RESPONSIBLE_KEYS = FLOW_ELEMENT__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__ACCOUNTABLE = FLOW_ELEMENT__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__ACCOUNTABLE_KEYS = FLOW_ELEMENT__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__CONSULTED = FLOW_ELEMENT__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__CONSULTED_KEYS = FLOW_ELEMENT__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__INFORMED = FLOW_ELEMENT__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__INFORMED_KEYS = FLOW_ELEMENT__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__OUTPUTS = FLOW_ELEMENT__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__INPUTS = FLOW_ELEMENT__INPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__CALLS = FLOW_ELEMENT__CALLS;

	/**
	 * The feature id for the '<em><b>Invocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__INVOCATIONS = FLOW_ELEMENT__INVOCATIONS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__INPUT_ARTIFACTS = FLOW_ELEMENT__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Input Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__INPUT_ARTIFACT_KEYS = FLOW_ELEMENT__INPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Output Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__OUTPUT_ARTIFACTS = FLOW_ELEMENT__OUTPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Output Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__OUTPUT_ARTIFACT_KEYS = FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PARTICIPANTS = FLOW_ELEMENT__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Participant Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__PARTICIPANT_KEYS = FLOW_ELEMENT__PARTICIPANT_KEYS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__RESOURCES = FLOW_ELEMENT__RESOURCES;

	/**
	 * The feature id for the '<em><b>Resource Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__RESOURCE_KEYS = FLOW_ELEMENT__RESOURCE_KEYS;

	/**
	 * The feature id for the '<em><b>Artifact Responsibilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__ARTIFACT_RESPONSIBILITIES = FLOW_ELEMENT__ARTIFACT_RESPONSIBILITIES;

	/**
	 * The feature id for the '<em><b>Services</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY__SERVICES = FLOW_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_FEATURE_COUNT = FLOW_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY___CREATE = FLOW_ELEMENT___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY___APPLY__PACKAGEELEMENT = FLOW_ELEMENT___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY___RESOLVE__PACKAGEELEMENT = FLOW_ELEMENT___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_OPERATION_COUNT = FLOW_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Activity Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Activity Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVITY_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__MARKER = FLOW_ELEMENT__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__URI = FLOW_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__DESCRIPTION = FLOW_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__UUID = FLOW_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__NAME = FLOW_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__PROTOTYPE = FLOW_ELEMENT__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__EXTENSIONS = FLOW_ELEMENT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__EXTENDS = FLOW_ELEMENT__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__MODIFIERS = FLOW_ELEMENT__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__DOCUMENTATION = FLOW_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__REPRESENTATIONS = FLOW_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__RESPONSIBLE = FLOW_ELEMENT__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__RESPONSIBLE_KEYS = FLOW_ELEMENT__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__ACCOUNTABLE = FLOW_ELEMENT__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__ACCOUNTABLE_KEYS = FLOW_ELEMENT__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__CONSULTED = FLOW_ELEMENT__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__CONSULTED_KEYS = FLOW_ELEMENT__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__INFORMED = FLOW_ELEMENT__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__INFORMED_KEYS = FLOW_ELEMENT__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__OUTPUTS = FLOW_ELEMENT__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__INPUTS = FLOW_ELEMENT__INPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__CALLS = FLOW_ELEMENT__CALLS;

	/**
	 * The feature id for the '<em><b>Invocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__INVOCATIONS = FLOW_ELEMENT__INVOCATIONS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__INPUT_ARTIFACTS = FLOW_ELEMENT__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Input Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__INPUT_ARTIFACT_KEYS = FLOW_ELEMENT__INPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Output Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__OUTPUT_ARTIFACTS = FLOW_ELEMENT__OUTPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Output Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__OUTPUT_ARTIFACT_KEYS = FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__PARTICIPANTS = FLOW_ELEMENT__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Participant Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__PARTICIPANT_KEYS = FLOW_ELEMENT__PARTICIPANT_KEYS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__RESOURCES = FLOW_ELEMENT__RESOURCES;

	/**
	 * The feature id for the '<em><b>Resource Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__RESOURCE_KEYS = FLOW_ELEMENT__RESOURCE_KEYS;

	/**
	 * The feature id for the '<em><b>Artifact Responsibilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__ARTIFACT_RESPONSIBILITIES = FLOW_ELEMENT__ARTIFACT_RESPONSIBILITIES;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__TARGET = FLOW_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE__TARGET_KEY = FLOW_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_FEATURE_COUNT = FLOW_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE___CREATE = FLOW_ELEMENT___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE___APPLY__PACKAGEELEMENT = FLOW_ELEMENT___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE___RESOLVE__PACKAGEELEMENT = FLOW_ELEMENT___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Service</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SERVICE_OPERATION_COUNT = FLOW_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.FlowImpl <em>Flow</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.FlowImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getFlow()
	 * @generated
	 */
	int FLOW = 24;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__MARKER = ACTIVITY__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__URI = ACTIVITY__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__DESCRIPTION = ACTIVITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__UUID = ACTIVITY__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__NAME = ACTIVITY__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__PROTOTYPE = ACTIVITY__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__EXTENSIONS = ACTIVITY__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__EXTENDS = ACTIVITY__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__MODIFIERS = ACTIVITY__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__DOCUMENTATION = ACTIVITY__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__REPRESENTATIONS = ACTIVITY__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__RESPONSIBLE = ACTIVITY__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__RESPONSIBLE_KEYS = ACTIVITY__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__ACCOUNTABLE = ACTIVITY__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__ACCOUNTABLE_KEYS = ACTIVITY__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__CONSULTED = ACTIVITY__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__CONSULTED_KEYS = ACTIVITY__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__INFORMED = ACTIVITY__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__INFORMED_KEYS = ACTIVITY__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__OUTPUTS = ACTIVITY__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__INPUTS = ACTIVITY__INPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__CALLS = ACTIVITY__CALLS;

	/**
	 * The feature id for the '<em><b>Invocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__INVOCATIONS = ACTIVITY__INVOCATIONS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__INPUT_ARTIFACTS = ACTIVITY__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Input Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__INPUT_ARTIFACT_KEYS = ACTIVITY__INPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Output Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__OUTPUT_ARTIFACTS = ACTIVITY__OUTPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Output Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__OUTPUT_ARTIFACT_KEYS = ACTIVITY__OUTPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__PARTICIPANTS = ACTIVITY__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Participant Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__PARTICIPANT_KEYS = ACTIVITY__PARTICIPANT_KEYS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__RESOURCES = ACTIVITY__RESOURCES;

	/**
	 * The feature id for the '<em><b>Resource Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__RESOURCE_KEYS = ACTIVITY__RESOURCE_KEYS;

	/**
	 * The feature id for the '<em><b>Artifact Responsibilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__ARTIFACT_RESPONSIBILITIES = ACTIVITY__ARTIFACT_RESPONSIBILITIES;

	/**
	 * The feature id for the '<em><b>Services</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__SERVICES = ACTIVITY__SERVICES;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__ELEMENTS = ACTIVITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Partition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW__PARTITION = ACTIVITY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Flow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_FEATURE_COUNT = ACTIVITY_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW___CREATE = ACTIVITY___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW___APPLY__PACKAGEELEMENT = ACTIVITY___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW___RESOLVE__PACKAGEELEMENT = ACTIVITY___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Flow</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FLOW_OPERATION_COUNT = ACTIVITY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.PseudoStateImpl <em>Pseudo State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.PseudoStateImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getPseudoState()
	 * @generated
	 */
	int PSEUDO_STATE = 25;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__MARKER = FLOW_ELEMENT__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__URI = FLOW_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__DESCRIPTION = FLOW_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__UUID = FLOW_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__NAME = FLOW_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__PROTOTYPE = FLOW_ELEMENT__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__EXTENSIONS = FLOW_ELEMENT__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__EXTENDS = FLOW_ELEMENT__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__MODIFIERS = FLOW_ELEMENT__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__DOCUMENTATION = FLOW_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__REPRESENTATIONS = FLOW_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__RESPONSIBLE = FLOW_ELEMENT__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__RESPONSIBLE_KEYS = FLOW_ELEMENT__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__ACCOUNTABLE = FLOW_ELEMENT__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__ACCOUNTABLE_KEYS = FLOW_ELEMENT__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__CONSULTED = FLOW_ELEMENT__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__CONSULTED_KEYS = FLOW_ELEMENT__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__INFORMED = FLOW_ELEMENT__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__INFORMED_KEYS = FLOW_ELEMENT__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__OUTPUTS = FLOW_ELEMENT__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__INPUTS = FLOW_ELEMENT__INPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__CALLS = FLOW_ELEMENT__CALLS;

	/**
	 * The feature id for the '<em><b>Invocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__INVOCATIONS = FLOW_ELEMENT__INVOCATIONS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__INPUT_ARTIFACTS = FLOW_ELEMENT__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Input Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__INPUT_ARTIFACT_KEYS = FLOW_ELEMENT__INPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Output Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__OUTPUT_ARTIFACTS = FLOW_ELEMENT__OUTPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Output Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__OUTPUT_ARTIFACT_KEYS = FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__PARTICIPANTS = FLOW_ELEMENT__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Participant Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__PARTICIPANT_KEYS = FLOW_ELEMENT__PARTICIPANT_KEYS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__RESOURCES = FLOW_ELEMENT__RESOURCES;

	/**
	 * The feature id for the '<em><b>Resource Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__RESOURCE_KEYS = FLOW_ELEMENT__RESOURCE_KEYS;

	/**
	 * The feature id for the '<em><b>Artifact Responsibilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__ARTIFACT_RESPONSIBILITIES = FLOW_ELEMENT__ARTIFACT_RESPONSIBILITIES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE__TYPE = FLOW_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Pseudo State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE_FEATURE_COUNT = FLOW_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE___CREATE = FLOW_ELEMENT___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE___APPLY__PACKAGEELEMENT = FLOW_ELEMENT___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE___RESOLVE__PACKAGEELEMENT = FLOW_ELEMENT___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Pseudo State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PSEUDO_STATE_OPERATION_COUNT = FLOW_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ChoiceImpl <em>Choice</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ChoiceImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getChoice()
	 * @generated
	 */
	int CHOICE = 26;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__MARKER = PSEUDO_STATE__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__URI = PSEUDO_STATE__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__DESCRIPTION = PSEUDO_STATE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__UUID = PSEUDO_STATE__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__NAME = PSEUDO_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__PROTOTYPE = PSEUDO_STATE__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__EXTENSIONS = PSEUDO_STATE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__EXTENDS = PSEUDO_STATE__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__DOCUMENTATION = PSEUDO_STATE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__REPRESENTATIONS = PSEUDO_STATE__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__RESPONSIBLE = PSEUDO_STATE__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__RESPONSIBLE_KEYS = PSEUDO_STATE__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__ACCOUNTABLE = PSEUDO_STATE__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__ACCOUNTABLE_KEYS = PSEUDO_STATE__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__CONSULTED = PSEUDO_STATE__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__CONSULTED_KEYS = PSEUDO_STATE__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__INFORMED = PSEUDO_STATE__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__INFORMED_KEYS = PSEUDO_STATE__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__INPUTS = PSEUDO_STATE__INPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Invocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__INVOCATIONS = PSEUDO_STATE__INVOCATIONS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Input Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__INPUT_ARTIFACT_KEYS = PSEUDO_STATE__INPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Output Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__OUTPUT_ARTIFACTS = PSEUDO_STATE__OUTPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Output Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__OUTPUT_ARTIFACT_KEYS = PSEUDO_STATE__OUTPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Participant Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__PARTICIPANT_KEYS = PSEUDO_STATE__PARTICIPANT_KEYS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Resource Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__RESOURCE_KEYS = PSEUDO_STATE__RESOURCE_KEYS;

	/**
	 * The feature id for the '<em><b>Artifact Responsibilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__ARTIFACT_RESPONSIBILITIES = PSEUDO_STATE__ARTIFACT_RESPONSIBILITIES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Choice</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE___CREATE = PSEUDO_STATE___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE___APPLY__PACKAGEELEMENT = PSEUDO_STATE___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE___RESOLVE__PACKAGEELEMENT = PSEUDO_STATE___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Choice</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CHOICE_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.EndImpl <em>End</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.EndImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getEnd()
	 * @generated
	 */
	int END = 27;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__MARKER = PSEUDO_STATE__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__URI = PSEUDO_STATE__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__DESCRIPTION = PSEUDO_STATE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__UUID = PSEUDO_STATE__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__NAME = PSEUDO_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__PROTOTYPE = PSEUDO_STATE__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__EXTENSIONS = PSEUDO_STATE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__EXTENDS = PSEUDO_STATE__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__DOCUMENTATION = PSEUDO_STATE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__REPRESENTATIONS = PSEUDO_STATE__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__RESPONSIBLE = PSEUDO_STATE__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__RESPONSIBLE_KEYS = PSEUDO_STATE__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__ACCOUNTABLE = PSEUDO_STATE__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__ACCOUNTABLE_KEYS = PSEUDO_STATE__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__CONSULTED = PSEUDO_STATE__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__CONSULTED_KEYS = PSEUDO_STATE__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__INFORMED = PSEUDO_STATE__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__INFORMED_KEYS = PSEUDO_STATE__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__INPUTS = PSEUDO_STATE__INPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Invocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__INVOCATIONS = PSEUDO_STATE__INVOCATIONS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Input Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__INPUT_ARTIFACT_KEYS = PSEUDO_STATE__INPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Output Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__OUTPUT_ARTIFACTS = PSEUDO_STATE__OUTPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Output Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__OUTPUT_ARTIFACT_KEYS = PSEUDO_STATE__OUTPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Participant Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__PARTICIPANT_KEYS = PSEUDO_STATE__PARTICIPANT_KEYS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Resource Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__RESOURCE_KEYS = PSEUDO_STATE__RESOURCE_KEYS;

	/**
	 * The feature id for the '<em><b>Artifact Responsibilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__ARTIFACT_RESPONSIBILITIES = PSEUDO_STATE__ARTIFACT_RESPONSIBILITIES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>End</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END___CREATE = PSEUDO_STATE___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END___APPLY__PACKAGEELEMENT = PSEUDO_STATE___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END___RESOLVE__PACKAGEELEMENT = PSEUDO_STATE___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>End</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int END_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.EntryPointImpl <em>Entry Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.EntryPointImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getEntryPoint()
	 * @generated
	 */
	int ENTRY_POINT = 28;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__MARKER = PSEUDO_STATE__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__URI = PSEUDO_STATE__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__DESCRIPTION = PSEUDO_STATE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__UUID = PSEUDO_STATE__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__NAME = PSEUDO_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__PROTOTYPE = PSEUDO_STATE__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__EXTENSIONS = PSEUDO_STATE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__EXTENDS = PSEUDO_STATE__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__DOCUMENTATION = PSEUDO_STATE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__REPRESENTATIONS = PSEUDO_STATE__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__RESPONSIBLE = PSEUDO_STATE__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__RESPONSIBLE_KEYS = PSEUDO_STATE__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__ACCOUNTABLE = PSEUDO_STATE__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__ACCOUNTABLE_KEYS = PSEUDO_STATE__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__CONSULTED = PSEUDO_STATE__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__CONSULTED_KEYS = PSEUDO_STATE__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__INFORMED = PSEUDO_STATE__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__INFORMED_KEYS = PSEUDO_STATE__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__INPUTS = PSEUDO_STATE__INPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Invocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__INVOCATIONS = PSEUDO_STATE__INVOCATIONS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Input Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__INPUT_ARTIFACT_KEYS = PSEUDO_STATE__INPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Output Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__OUTPUT_ARTIFACTS = PSEUDO_STATE__OUTPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Output Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__OUTPUT_ARTIFACT_KEYS = PSEUDO_STATE__OUTPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Participant Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__PARTICIPANT_KEYS = PSEUDO_STATE__PARTICIPANT_KEYS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Resource Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__RESOURCE_KEYS = PSEUDO_STATE__RESOURCE_KEYS;

	/**
	 * The feature id for the '<em><b>Artifact Responsibilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__ARTIFACT_RESPONSIBILITIES = PSEUDO_STATE__ARTIFACT_RESPONSIBILITIES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Entry Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT___CREATE = PSEUDO_STATE___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT___APPLY__PACKAGEELEMENT = PSEUDO_STATE___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT___RESOLVE__PACKAGEELEMENT = PSEUDO_STATE___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Entry Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ExitPointImpl <em>Exit Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ExitPointImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getExitPoint()
	 * @generated
	 */
	int EXIT_POINT = 29;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__MARKER = PSEUDO_STATE__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__URI = PSEUDO_STATE__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__DESCRIPTION = PSEUDO_STATE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__UUID = PSEUDO_STATE__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__NAME = PSEUDO_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__PROTOTYPE = PSEUDO_STATE__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__EXTENSIONS = PSEUDO_STATE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__EXTENDS = PSEUDO_STATE__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__DOCUMENTATION = PSEUDO_STATE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__REPRESENTATIONS = PSEUDO_STATE__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__RESPONSIBLE = PSEUDO_STATE__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__RESPONSIBLE_KEYS = PSEUDO_STATE__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__ACCOUNTABLE = PSEUDO_STATE__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__ACCOUNTABLE_KEYS = PSEUDO_STATE__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__CONSULTED = PSEUDO_STATE__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__CONSULTED_KEYS = PSEUDO_STATE__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__INFORMED = PSEUDO_STATE__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__INFORMED_KEYS = PSEUDO_STATE__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__INPUTS = PSEUDO_STATE__INPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Invocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__INVOCATIONS = PSEUDO_STATE__INVOCATIONS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Input Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__INPUT_ARTIFACT_KEYS = PSEUDO_STATE__INPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Output Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__OUTPUT_ARTIFACTS = PSEUDO_STATE__OUTPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Output Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__OUTPUT_ARTIFACT_KEYS = PSEUDO_STATE__OUTPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Participant Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__PARTICIPANT_KEYS = PSEUDO_STATE__PARTICIPANT_KEYS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Resource Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__RESOURCE_KEYS = PSEUDO_STATE__RESOURCE_KEYS;

	/**
	 * The feature id for the '<em><b>Artifact Responsibilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__ARTIFACT_RESPONSIBILITIES = PSEUDO_STATE__ARTIFACT_RESPONSIBILITIES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Exit Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT___CREATE = PSEUDO_STATE___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT___APPLY__PACKAGEELEMENT = PSEUDO_STATE___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT___RESOLVE__PACKAGEELEMENT = PSEUDO_STATE___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Exit Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXIT_POINT_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ExpansionInputImpl <em>Expansion Input</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ExpansionInputImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getExpansionInput()
	 * @generated
	 */
	int EXPANSION_INPUT = 30;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__MARKER = PSEUDO_STATE__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__URI = PSEUDO_STATE__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__DESCRIPTION = PSEUDO_STATE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__UUID = PSEUDO_STATE__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__NAME = PSEUDO_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__PROTOTYPE = PSEUDO_STATE__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__EXTENSIONS = PSEUDO_STATE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__EXTENDS = PSEUDO_STATE__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__DOCUMENTATION = PSEUDO_STATE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__REPRESENTATIONS = PSEUDO_STATE__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__RESPONSIBLE = PSEUDO_STATE__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__RESPONSIBLE_KEYS = PSEUDO_STATE__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__ACCOUNTABLE = PSEUDO_STATE__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__ACCOUNTABLE_KEYS = PSEUDO_STATE__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__CONSULTED = PSEUDO_STATE__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__CONSULTED_KEYS = PSEUDO_STATE__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__INFORMED = PSEUDO_STATE__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__INFORMED_KEYS = PSEUDO_STATE__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__INPUTS = PSEUDO_STATE__INPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Invocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__INVOCATIONS = PSEUDO_STATE__INVOCATIONS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Input Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__INPUT_ARTIFACT_KEYS = PSEUDO_STATE__INPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Output Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__OUTPUT_ARTIFACTS = PSEUDO_STATE__OUTPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Output Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__OUTPUT_ARTIFACT_KEYS = PSEUDO_STATE__OUTPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Participant Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__PARTICIPANT_KEYS = PSEUDO_STATE__PARTICIPANT_KEYS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Resource Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__RESOURCE_KEYS = PSEUDO_STATE__RESOURCE_KEYS;

	/**
	 * The feature id for the '<em><b>Artifact Responsibilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__ARTIFACT_RESPONSIBILITIES = PSEUDO_STATE__ARTIFACT_RESPONSIBILITIES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Expansion Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT___CREATE = PSEUDO_STATE___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT___APPLY__PACKAGEELEMENT = PSEUDO_STATE___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT___RESOLVE__PACKAGEELEMENT = PSEUDO_STATE___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Expansion Input</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_INPUT_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ExpansionOutputImpl <em>Expansion Output</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ExpansionOutputImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getExpansionOutput()
	 * @generated
	 */
	int EXPANSION_OUTPUT = 31;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__MARKER = PSEUDO_STATE__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__URI = PSEUDO_STATE__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__DESCRIPTION = PSEUDO_STATE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__UUID = PSEUDO_STATE__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__NAME = PSEUDO_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__PROTOTYPE = PSEUDO_STATE__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__EXTENSIONS = PSEUDO_STATE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__EXTENDS = PSEUDO_STATE__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__DOCUMENTATION = PSEUDO_STATE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__REPRESENTATIONS = PSEUDO_STATE__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__RESPONSIBLE = PSEUDO_STATE__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__RESPONSIBLE_KEYS = PSEUDO_STATE__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__ACCOUNTABLE = PSEUDO_STATE__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__ACCOUNTABLE_KEYS = PSEUDO_STATE__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__CONSULTED = PSEUDO_STATE__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__CONSULTED_KEYS = PSEUDO_STATE__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__INFORMED = PSEUDO_STATE__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__INFORMED_KEYS = PSEUDO_STATE__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__INPUTS = PSEUDO_STATE__INPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Invocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__INVOCATIONS = PSEUDO_STATE__INVOCATIONS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Input Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__INPUT_ARTIFACT_KEYS = PSEUDO_STATE__INPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Output Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__OUTPUT_ARTIFACTS = PSEUDO_STATE__OUTPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Output Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__OUTPUT_ARTIFACT_KEYS = PSEUDO_STATE__OUTPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Participant Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__PARTICIPANT_KEYS = PSEUDO_STATE__PARTICIPANT_KEYS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Resource Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__RESOURCE_KEYS = PSEUDO_STATE__RESOURCE_KEYS;

	/**
	 * The feature id for the '<em><b>Artifact Responsibilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__ARTIFACT_RESPONSIBILITIES = PSEUDO_STATE__ARTIFACT_RESPONSIBILITIES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Expansion Output</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT___CREATE = PSEUDO_STATE___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT___APPLY__PACKAGEELEMENT = PSEUDO_STATE___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT___RESOLVE__PACKAGEELEMENT = PSEUDO_STATE___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Expansion Output</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPANSION_OUTPUT_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.ForkImpl <em>Fork</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.ForkImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getFork()
	 * @generated
	 */
	int FORK = 32;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__MARKER = PSEUDO_STATE__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__URI = PSEUDO_STATE__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__DESCRIPTION = PSEUDO_STATE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__UUID = PSEUDO_STATE__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__NAME = PSEUDO_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__PROTOTYPE = PSEUDO_STATE__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__EXTENSIONS = PSEUDO_STATE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__EXTENDS = PSEUDO_STATE__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__DOCUMENTATION = PSEUDO_STATE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__REPRESENTATIONS = PSEUDO_STATE__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__RESPONSIBLE = PSEUDO_STATE__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__RESPONSIBLE_KEYS = PSEUDO_STATE__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__ACCOUNTABLE = PSEUDO_STATE__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__ACCOUNTABLE_KEYS = PSEUDO_STATE__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__CONSULTED = PSEUDO_STATE__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__CONSULTED_KEYS = PSEUDO_STATE__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__INFORMED = PSEUDO_STATE__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__INFORMED_KEYS = PSEUDO_STATE__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__INPUTS = PSEUDO_STATE__INPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Invocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__INVOCATIONS = PSEUDO_STATE__INVOCATIONS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Input Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__INPUT_ARTIFACT_KEYS = PSEUDO_STATE__INPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Output Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__OUTPUT_ARTIFACTS = PSEUDO_STATE__OUTPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Output Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__OUTPUT_ARTIFACT_KEYS = PSEUDO_STATE__OUTPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Participant Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__PARTICIPANT_KEYS = PSEUDO_STATE__PARTICIPANT_KEYS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Resource Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__RESOURCE_KEYS = PSEUDO_STATE__RESOURCE_KEYS;

	/**
	 * The feature id for the '<em><b>Artifact Responsibilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__ARTIFACT_RESPONSIBILITIES = PSEUDO_STATE__ARTIFACT_RESPONSIBILITIES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Fork</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK___CREATE = PSEUDO_STATE___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK___APPLY__PACKAGEELEMENT = PSEUDO_STATE___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK___RESOLVE__PACKAGEELEMENT = PSEUDO_STATE___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Fork</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FORK_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.InputPinImpl <em>Input Pin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.InputPinImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getInputPin()
	 * @generated
	 */
	int INPUT_PIN = 33;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__MARKER = PSEUDO_STATE__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__URI = PSEUDO_STATE__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__DESCRIPTION = PSEUDO_STATE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__UUID = PSEUDO_STATE__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__NAME = PSEUDO_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__PROTOTYPE = PSEUDO_STATE__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__EXTENSIONS = PSEUDO_STATE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__EXTENDS = PSEUDO_STATE__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__DOCUMENTATION = PSEUDO_STATE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__REPRESENTATIONS = PSEUDO_STATE__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__RESPONSIBLE = PSEUDO_STATE__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__RESPONSIBLE_KEYS = PSEUDO_STATE__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__ACCOUNTABLE = PSEUDO_STATE__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__ACCOUNTABLE_KEYS = PSEUDO_STATE__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__CONSULTED = PSEUDO_STATE__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__CONSULTED_KEYS = PSEUDO_STATE__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__INFORMED = PSEUDO_STATE__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__INFORMED_KEYS = PSEUDO_STATE__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__INPUTS = PSEUDO_STATE__INPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Invocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__INVOCATIONS = PSEUDO_STATE__INVOCATIONS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Input Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__INPUT_ARTIFACT_KEYS = PSEUDO_STATE__INPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Output Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__OUTPUT_ARTIFACTS = PSEUDO_STATE__OUTPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Output Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__OUTPUT_ARTIFACT_KEYS = PSEUDO_STATE__OUTPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Participant Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__PARTICIPANT_KEYS = PSEUDO_STATE__PARTICIPANT_KEYS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Resource Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__RESOURCE_KEYS = PSEUDO_STATE__RESOURCE_KEYS;

	/**
	 * The feature id for the '<em><b>Artifact Responsibilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__ARTIFACT_RESPONSIBILITIES = PSEUDO_STATE__ARTIFACT_RESPONSIBILITIES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Input Pin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN___CREATE = PSEUDO_STATE___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN___APPLY__PACKAGEELEMENT = PSEUDO_STATE___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN___RESOLVE__PACKAGEELEMENT = PSEUDO_STATE___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Input Pin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INPUT_PIN_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.JoinImpl <em>Join</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.JoinImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getJoin()
	 * @generated
	 */
	int JOIN = 34;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__MARKER = PSEUDO_STATE__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__URI = PSEUDO_STATE__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__DESCRIPTION = PSEUDO_STATE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__UUID = PSEUDO_STATE__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__NAME = PSEUDO_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__PROTOTYPE = PSEUDO_STATE__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__EXTENSIONS = PSEUDO_STATE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__EXTENDS = PSEUDO_STATE__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__DOCUMENTATION = PSEUDO_STATE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__REPRESENTATIONS = PSEUDO_STATE__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__RESPONSIBLE = PSEUDO_STATE__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__RESPONSIBLE_KEYS = PSEUDO_STATE__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__ACCOUNTABLE = PSEUDO_STATE__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__ACCOUNTABLE_KEYS = PSEUDO_STATE__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__CONSULTED = PSEUDO_STATE__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__CONSULTED_KEYS = PSEUDO_STATE__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__INFORMED = PSEUDO_STATE__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__INFORMED_KEYS = PSEUDO_STATE__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__INPUTS = PSEUDO_STATE__INPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Invocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__INVOCATIONS = PSEUDO_STATE__INVOCATIONS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Input Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__INPUT_ARTIFACT_KEYS = PSEUDO_STATE__INPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Output Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__OUTPUT_ARTIFACTS = PSEUDO_STATE__OUTPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Output Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__OUTPUT_ARTIFACT_KEYS = PSEUDO_STATE__OUTPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Participant Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__PARTICIPANT_KEYS = PSEUDO_STATE__PARTICIPANT_KEYS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Resource Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__RESOURCE_KEYS = PSEUDO_STATE__RESOURCE_KEYS;

	/**
	 * The feature id for the '<em><b>Artifact Responsibilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__ARTIFACT_RESPONSIBILITIES = PSEUDO_STATE__ARTIFACT_RESPONSIBILITIES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Join</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN___CREATE = PSEUDO_STATE___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN___APPLY__PACKAGEELEMENT = PSEUDO_STATE___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN___RESOLVE__PACKAGEELEMENT = PSEUDO_STATE___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Join</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JOIN_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.OutputPinImpl <em>Output Pin</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.OutputPinImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getOutputPin()
	 * @generated
	 */
	int OUTPUT_PIN = 35;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__MARKER = PSEUDO_STATE__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__URI = PSEUDO_STATE__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__DESCRIPTION = PSEUDO_STATE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__UUID = PSEUDO_STATE__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__NAME = PSEUDO_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__PROTOTYPE = PSEUDO_STATE__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__EXTENSIONS = PSEUDO_STATE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__EXTENDS = PSEUDO_STATE__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__DOCUMENTATION = PSEUDO_STATE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__REPRESENTATIONS = PSEUDO_STATE__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__RESPONSIBLE = PSEUDO_STATE__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__RESPONSIBLE_KEYS = PSEUDO_STATE__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__ACCOUNTABLE = PSEUDO_STATE__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__ACCOUNTABLE_KEYS = PSEUDO_STATE__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__CONSULTED = PSEUDO_STATE__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__CONSULTED_KEYS = PSEUDO_STATE__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__INFORMED = PSEUDO_STATE__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__INFORMED_KEYS = PSEUDO_STATE__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__INPUTS = PSEUDO_STATE__INPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Invocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__INVOCATIONS = PSEUDO_STATE__INVOCATIONS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Input Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__INPUT_ARTIFACT_KEYS = PSEUDO_STATE__INPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Output Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__OUTPUT_ARTIFACTS = PSEUDO_STATE__OUTPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Output Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__OUTPUT_ARTIFACT_KEYS = PSEUDO_STATE__OUTPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Participant Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__PARTICIPANT_KEYS = PSEUDO_STATE__PARTICIPANT_KEYS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Resource Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__RESOURCE_KEYS = PSEUDO_STATE__RESOURCE_KEYS;

	/**
	 * The feature id for the '<em><b>Artifact Responsibilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__ARTIFACT_RESPONSIBILITIES = PSEUDO_STATE__ARTIFACT_RESPONSIBILITIES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Output Pin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN___CREATE = PSEUDO_STATE___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN___APPLY__PACKAGEELEMENT = PSEUDO_STATE___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN___RESOLVE__PACKAGEELEMENT = PSEUDO_STATE___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Output Pin</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OUTPUT_PIN_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.flow.impl.StartImpl <em>Start</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.flow.impl.StartImpl
	 * @see org.nasdanika.flow.impl.FlowPackageImpl#getStart()
	 * @generated
	 */
	int START = 36;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__MARKER = PSEUDO_STATE__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__URI = PSEUDO_STATE__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__DESCRIPTION = PSEUDO_STATE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__UUID = PSEUDO_STATE__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__NAME = PSEUDO_STATE__NAME;

	/**
	 * The feature id for the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__PROTOTYPE = PSEUDO_STATE__PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Extensions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__EXTENSIONS = PSEUDO_STATE__EXTENSIONS;

	/**
	 * The feature id for the '<em><b>Extends</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__EXTENDS = PSEUDO_STATE__EXTENDS;

	/**
	 * The feature id for the '<em><b>Modifiers</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__MODIFIERS = PSEUDO_STATE__MODIFIERS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__DOCUMENTATION = PSEUDO_STATE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__REPRESENTATIONS = PSEUDO_STATE__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Responsible</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__RESPONSIBLE = PSEUDO_STATE__RESPONSIBLE;

	/**
	 * The feature id for the '<em><b>Responsible Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__RESPONSIBLE_KEYS = PSEUDO_STATE__RESPONSIBLE_KEYS;

	/**
	 * The feature id for the '<em><b>Accountable</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__ACCOUNTABLE = PSEUDO_STATE__ACCOUNTABLE;

	/**
	 * The feature id for the '<em><b>Accountable Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__ACCOUNTABLE_KEYS = PSEUDO_STATE__ACCOUNTABLE_KEYS;

	/**
	 * The feature id for the '<em><b>Consulted</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__CONSULTED = PSEUDO_STATE__CONSULTED;

	/**
	 * The feature id for the '<em><b>Consulted Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__CONSULTED_KEYS = PSEUDO_STATE__CONSULTED_KEYS;

	/**
	 * The feature id for the '<em><b>Informed</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__INFORMED = PSEUDO_STATE__INFORMED;

	/**
	 * The feature id for the '<em><b>Informed Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__INFORMED_KEYS = PSEUDO_STATE__INFORMED_KEYS;

	/**
	 * The feature id for the '<em><b>Outputs</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__OUTPUTS = PSEUDO_STATE__OUTPUTS;

	/**
	 * The feature id for the '<em><b>Inputs</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__INPUTS = PSEUDO_STATE__INPUTS;

	/**
	 * The feature id for the '<em><b>Calls</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__CALLS = PSEUDO_STATE__CALLS;

	/**
	 * The feature id for the '<em><b>Invocations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__INVOCATIONS = PSEUDO_STATE__INVOCATIONS;

	/**
	 * The feature id for the '<em><b>Input Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__INPUT_ARTIFACTS = PSEUDO_STATE__INPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Input Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__INPUT_ARTIFACT_KEYS = PSEUDO_STATE__INPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Output Artifacts</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__OUTPUT_ARTIFACTS = PSEUDO_STATE__OUTPUT_ARTIFACTS;

	/**
	 * The feature id for the '<em><b>Output Artifact Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__OUTPUT_ARTIFACT_KEYS = PSEUDO_STATE__OUTPUT_ARTIFACT_KEYS;

	/**
	 * The feature id for the '<em><b>Participants</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__PARTICIPANTS = PSEUDO_STATE__PARTICIPANTS;

	/**
	 * The feature id for the '<em><b>Participant Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__PARTICIPANT_KEYS = PSEUDO_STATE__PARTICIPANT_KEYS;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__RESOURCES = PSEUDO_STATE__RESOURCES;

	/**
	 * The feature id for the '<em><b>Resource Keys</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__RESOURCE_KEYS = PSEUDO_STATE__RESOURCE_KEYS;

	/**
	 * The feature id for the '<em><b>Artifact Responsibilities</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__ARTIFACT_RESPONSIBILITIES = PSEUDO_STATE__ARTIFACT_RESPONSIBILITIES;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START__TYPE = PSEUDO_STATE__TYPE;

	/**
	 * The number of structural features of the '<em>Start</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_FEATURE_COUNT = PSEUDO_STATE_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Create</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START___CREATE = PSEUDO_STATE___CREATE;

	/**
	 * The operation id for the '<em>Apply</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START___APPLY__PACKAGEELEMENT = PSEUDO_STATE___APPLY__PACKAGEELEMENT;

	/**
	 * The operation id for the '<em>Resolve</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START___RESOLVE__PACKAGEELEMENT = PSEUDO_STATE___RESOLVE__PACKAGEELEMENT;

	/**
	 * The number of operations of the '<em>Start</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int START_OPERATION_COUNT = PSEUDO_STATE_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.PackageElement <em>Package Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package Element</em>'.
	 * @see org.nasdanika.flow.PackageElement
	 * @generated
	 */
	EClass getPackageElement();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.flow.PackageElement#getPrototype <em>Prototype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Prototype</em>'.
	 * @see org.nasdanika.flow.PackageElement#getPrototype()
	 * @see #getPackageElement()
	 * @generated
	 */
	EReference getPackageElement_Prototype();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.PackageElement#getExtensions <em>Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Extensions</em>'.
	 * @see org.nasdanika.flow.PackageElement#getExtensions()
	 * @see #getPackageElement()
	 * @generated
	 */
	EReference getPackageElement_Extensions();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.PackageElement#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Extends</em>'.
	 * @see org.nasdanika.flow.PackageElement#getExtends()
	 * @see #getPackageElement()
	 * @generated
	 */
	EReference getPackageElement_Extends();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.flow.PackageElement#getModifiers <em>Modifiers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Modifiers</em>'.
	 * @see org.nasdanika.flow.PackageElement#getModifiers()
	 * @see #getPackageElement()
	 * @generated
	 */
	EAttribute getPackageElement_Modifiers();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.flow.PackageElement#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Documentation</em>'.
	 * @see org.nasdanika.flow.PackageElement#getDocumentation()
	 * @see #getPackageElement()
	 * @generated
	 */
	EReference getPackageElement_Documentation();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.flow.PackageElement#getRepresentations <em>Representations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Representations</em>'.
	 * @see org.nasdanika.flow.PackageElement#getRepresentations()
	 * @see #getPackageElement()
	 * @generated
	 */
	EReference getPackageElement_Representations();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.flow.PackageElement#create() <em>Create</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Create</em>' operation.
	 * @see org.nasdanika.flow.PackageElement#create()
	 * @generated
	 */
	EOperation getPackageElement__Create();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.flow.PackageElement#apply(org.nasdanika.flow.PackageElement) <em>Apply</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Apply</em>' operation.
	 * @see org.nasdanika.flow.PackageElement#apply(org.nasdanika.flow.PackageElement)
	 * @generated
	 */
	EOperation getPackageElement__Apply__PackageElement();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.flow.PackageElement#resolve(org.nasdanika.flow.PackageElement) <em>Resolve</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Resolve</em>' operation.
	 * @see org.nasdanika.flow.PackageElement#resolve(org.nasdanika.flow.PackageElement)
	 * @generated
	 */
	EOperation getPackageElement__Resolve__PackageElement();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Representation Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Representation Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueType="org.nasdanika.diagram.Diagram" valueContainment="true"
	 *        valueAnnotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	EClass getRepresentationEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getRepresentationEntry()
	 * @generated
	 */
	EAttribute getRepresentationEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getRepresentationEntry()
	 * @generated
	 */
	EReference getRepresentationEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package</em>'.
	 * @see org.nasdanika.flow.Package
	 * @generated
	 */
	EClass getPackage();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Package#getSuperPackages <em>Super Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Super Packages</em>'.
	 * @see org.nasdanika.flow.Package#getSuperPackages()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_SuperPackages();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.flow.Package#getSubPackages <em>Sub Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Sub Packages</em>'.
	 * @see org.nasdanika.flow.Package#getSubPackages()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_SubPackages();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.flow.Package#getParticipants <em>Participants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Participants</em>'.
	 * @see org.nasdanika.flow.Package#getParticipants()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_Participants();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.flow.Package#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Resources</em>'.
	 * @see org.nasdanika.flow.Package#getResources()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_Resources();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.flow.Package#getArtifacts <em>Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Artifacts</em>'.
	 * @see org.nasdanika.flow.Package#getArtifacts()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_Artifacts();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.flow.Package#getActivities <em>Activities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Activities</em>'.
	 * @see org.nasdanika.flow.Package#getActivities()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_Activities();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Package Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueType="org.nasdanika.flow.Package" valueContainment="true"
	 *        valueAnnotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	EClass getPackageEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getPackageEntry()
	 * @generated
	 */
	EAttribute getPackageEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getPackageEntry()
	 * @generated
	 */
	EReference getPackageEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.ServiceProvider <em>Service Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service Provider</em>'.
	 * @see org.nasdanika.flow.ServiceProvider
	 * @generated
	 */
	EClass getServiceProvider();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.flow.ServiceProvider#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Services</em>'.
	 * @see org.nasdanika.flow.ServiceProvider#getServices()
	 * @see #getServiceProvider()
	 * @generated
	 */
	EReference getServiceProvider_Services();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Participant <em>Participant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Participant</em>'.
	 * @see org.nasdanika.flow.Participant
	 * @generated
	 */
	EClass getParticipant();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Participant#getParticipates <em>Participates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Participates</em>'.
	 * @see org.nasdanika.flow.Participant#getParticipates()
	 * @see #getParticipant()
	 * @generated
	 */
	EReference getParticipant_Participates();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Participant#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Resources</em>'.
	 * @see org.nasdanika.flow.Participant#getResources()
	 * @see #getParticipant()
	 * @generated
	 */
	EReference getParticipant_Resources();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Participant#getArtifacts <em>Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Artifacts</em>'.
	 * @see org.nasdanika.flow.Participant#getArtifacts()
	 * @see #getParticipant()
	 * @generated
	 */
	EReference getParticipant_Artifacts();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Participant#getSpecializations <em>Specializations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Specializations</em>'.
	 * @see org.nasdanika.flow.Participant#getSpecializations()
	 * @see #getParticipant()
	 * @generated
	 */
	EReference getParticipant_Specializations();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.flow.Participant#getBaseKeys <em>Base Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Base Keys</em>'.
	 * @see org.nasdanika.flow.Participant#getBaseKeys()
	 * @see #getParticipant()
	 * @generated
	 */
	EAttribute getParticipant_BaseKeys();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Participant#getBases <em>Bases</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Bases</em>'.
	 * @see org.nasdanika.flow.Participant#getBases()
	 * @see #getParticipant()
	 * @generated
	 */
	EReference getParticipant_Bases();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Participant#getResponsible <em>Responsible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Responsible</em>'.
	 * @see org.nasdanika.flow.Participant#getResponsible()
	 * @see #getParticipant()
	 * @generated
	 */
	EReference getParticipant_Responsible();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Participant#getAccountable <em>Accountable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Accountable</em>'.
	 * @see org.nasdanika.flow.Participant#getAccountable()
	 * @see #getParticipant()
	 * @generated
	 */
	EReference getParticipant_Accountable();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Participant#getConsulted <em>Consulted</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Consulted</em>'.
	 * @see org.nasdanika.flow.Participant#getConsulted()
	 * @see #getParticipant()
	 * @generated
	 */
	EReference getParticipant_Consulted();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Participant#getInformed <em>Informed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Informed</em>'.
	 * @see org.nasdanika.flow.Participant#getInformed()
	 * @see #getParticipant()
	 * @generated
	 */
	EReference getParticipant_Informed();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.flow.Participant#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Children</em>'.
	 * @see org.nasdanika.flow.Participant#getChildren()
	 * @see #getParticipant()
	 * @generated
	 */
	EReference getParticipant_Children();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Participant Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Participant Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueType="org.nasdanika.flow.Participant" valueContainment="true"
	 *        valueAnnotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	EClass getParticipantEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getParticipantEntry()
	 * @generated
	 */
	EAttribute getParticipantEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getParticipantEntry()
	 * @generated
	 */
	EReference getParticipantEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource</em>'.
	 * @see org.nasdanika.flow.Resource
	 * @generated
	 */
	EClass getResource();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Resource#getArtifacts <em>Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Artifacts</em>'.
	 * @see org.nasdanika.flow.Resource#getArtifacts()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_Artifacts();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Resource#getUsedIn <em>Used In</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Used In</em>'.
	 * @see org.nasdanika.flow.Resource#getUsedIn()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_UsedIn();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Resource#getUsedBy <em>Used By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Used By</em>'.
	 * @see org.nasdanika.flow.Resource#getUsedBy()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_UsedBy();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.flow.Resource#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Children</em>'.
	 * @see org.nasdanika.flow.Resource#getChildren()
	 * @see #getResource()
	 * @generated
	 */
	EReference getResource_Children();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Resource Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueType="org.nasdanika.flow.Resource" valueContainment="true"
	 *        valueAnnotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	EClass getResourceEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getResourceEntry()
	 * @generated
	 */
	EAttribute getResourceEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getResourceEntry()
	 * @generated
	 */
	EReference getResourceEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.ParticipantResponsibility <em>Participant Responsibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Participant Responsibility</em>'.
	 * @see org.nasdanika.flow.ParticipantResponsibility
	 * @generated
	 */
	EClass getParticipantResponsibility();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.ParticipantResponsibility#getResponsible <em>Responsible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Responsible</em>'.
	 * @see org.nasdanika.flow.ParticipantResponsibility#getResponsible()
	 * @see #getParticipantResponsibility()
	 * @generated
	 */
	EReference getParticipantResponsibility_Responsible();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.flow.ParticipantResponsibility#getResponsibleKeys <em>Responsible Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Responsible Keys</em>'.
	 * @see org.nasdanika.flow.ParticipantResponsibility#getResponsibleKeys()
	 * @see #getParticipantResponsibility()
	 * @generated
	 */
	EAttribute getParticipantResponsibility_ResponsibleKeys();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.ParticipantResponsibility#getAccountable <em>Accountable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Accountable</em>'.
	 * @see org.nasdanika.flow.ParticipantResponsibility#getAccountable()
	 * @see #getParticipantResponsibility()
	 * @generated
	 */
	EReference getParticipantResponsibility_Accountable();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.flow.ParticipantResponsibility#getAccountableKeys <em>Accountable Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Accountable Keys</em>'.
	 * @see org.nasdanika.flow.ParticipantResponsibility#getAccountableKeys()
	 * @see #getParticipantResponsibility()
	 * @generated
	 */
	EAttribute getParticipantResponsibility_AccountableKeys();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.ParticipantResponsibility#getConsulted <em>Consulted</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Consulted</em>'.
	 * @see org.nasdanika.flow.ParticipantResponsibility#getConsulted()
	 * @see #getParticipantResponsibility()
	 * @generated
	 */
	EReference getParticipantResponsibility_Consulted();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.flow.ParticipantResponsibility#getConsultedKeys <em>Consulted Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Consulted Keys</em>'.
	 * @see org.nasdanika.flow.ParticipantResponsibility#getConsultedKeys()
	 * @see #getParticipantResponsibility()
	 * @generated
	 */
	EAttribute getParticipantResponsibility_ConsultedKeys();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.ParticipantResponsibility#getInformed <em>Informed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Informed</em>'.
	 * @see org.nasdanika.flow.ParticipantResponsibility#getInformed()
	 * @see #getParticipantResponsibility()
	 * @generated
	 */
	EReference getParticipantResponsibility_Informed();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.flow.ParticipantResponsibility#getInformedKeys <em>Informed Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Informed Keys</em>'.
	 * @see org.nasdanika.flow.ParticipantResponsibility#getInformedKeys()
	 * @see #getParticipantResponsibility()
	 * @generated
	 */
	EAttribute getParticipantResponsibility_InformedKeys();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Artifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact</em>'.
	 * @see org.nasdanika.flow.Artifact
	 * @generated
	 */
	EClass getArtifact();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Artifact#getRepositories <em>Repositories</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Repositories</em>'.
	 * @see org.nasdanika.flow.Artifact#getRepositories()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_Repositories();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.flow.Artifact#getRepositoryKeys <em>Repository Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Repository Keys</em>'.
	 * @see org.nasdanika.flow.Artifact#getRepositoryKeys()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_RepositoryKeys();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Artifact#getInputFor <em>Input For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Input For</em>'.
	 * @see org.nasdanika.flow.Artifact#getInputFor()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_InputFor();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Artifact#getOutputFor <em>Output For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Output For</em>'.
	 * @see org.nasdanika.flow.Artifact#getOutputFor()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_OutputFor();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Artifact#getPayloadFor <em>Payload For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Payload For</em>'.
	 * @see org.nasdanika.flow.Artifact#getPayloadFor()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_PayloadFor();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Artifact#getResponseFor <em>Response For</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Response For</em>'.
	 * @see org.nasdanika.flow.Artifact#getResponseFor()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_ResponseFor();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Artifact#getUsedBy <em>Used By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Used By</em>'.
	 * @see org.nasdanika.flow.Artifact#getUsedBy()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_UsedBy();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Artifact#getResponsibilities <em>Responsibilities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Responsibilities</em>'.
	 * @see org.nasdanika.flow.Artifact#getResponsibilities()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_Responsibilities();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.flow.Artifact#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Children</em>'.
	 * @see org.nasdanika.flow.Artifact#getChildren()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_Children();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.flow.Artifact#getOutboundRelationships <em>Outbound Relationships</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Outbound Relationships</em>'.
	 * @see org.nasdanika.flow.Artifact#getOutboundRelationships()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_OutboundRelationships();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Artifact#getInboundRelationships <em>Inbound Relationships</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Inbound Relationships</em>'.
	 * @see org.nasdanika.flow.Artifact#getInboundRelationships()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_InboundRelationships();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.flow.Artifact#isPartition <em>Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Partition</em>'.
	 * @see org.nasdanika.flow.Artifact#isPartition()
	 * @see #getArtifact()
	 * @generated
	 */
	EAttribute getArtifact_Partition();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.flow.Artifact#getStyle <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Style</em>'.
	 * @see org.nasdanika.flow.Artifact#getStyle()
	 * @see #getArtifact()
	 * @generated
	 */
	EReference getArtifact_Style();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.ArtifactParticipantResponsibility <em>Artifact Participant Responsibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact Participant Responsibility</em>'.
	 * @see org.nasdanika.flow.ArtifactParticipantResponsibility
	 * @generated
	 */
	EClass getArtifactParticipantResponsibility();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.flow.ArtifactParticipantResponsibility#getArtifactKey <em>Artifact Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Artifact Key</em>'.
	 * @see org.nasdanika.flow.ArtifactParticipantResponsibility#getArtifactKey()
	 * @see #getArtifactParticipantResponsibility()
	 * @generated
	 */
	EAttribute getArtifactParticipantResponsibility_ArtifactKey();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.flow.ArtifactParticipantResponsibility#getArtifact <em>Artifact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Artifact</em>'.
	 * @see org.nasdanika.flow.ArtifactParticipantResponsibility#getArtifact()
	 * @see #getArtifactParticipantResponsibility()
	 * @generated
	 */
	EReference getArtifactParticipantResponsibility_Artifact();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.flow.ArtifactParticipantResponsibility#isSuppress <em>Suppress</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Suppress</em>'.
	 * @see org.nasdanika.flow.ArtifactParticipantResponsibility#isSuppress()
	 * @see #getArtifactParticipantResponsibility()
	 * @generated
	 */
	EAttribute getArtifactParticipantResponsibility_Suppress();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Relationship <em>Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relationship</em>'.
	 * @see org.nasdanika.flow.Relationship
	 * @generated
	 */
	EClass getRelationship();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.flow.Relationship#getTargetKey <em>Target Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Key</em>'.
	 * @see org.nasdanika.flow.Relationship#getTargetKey()
	 * @see #getRelationship()
	 * @generated
	 */
	EAttribute getRelationship_TargetKey();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.flow.Relationship#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.nasdanika.flow.Relationship#getTarget()
	 * @see #getRelationship()
	 * @generated
	 */
	EReference getRelationship_Target();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.flow.Relationship#getStyle <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Style</em>'.
	 * @see org.nasdanika.flow.Relationship#getStyle()
	 * @see #getRelationship()
	 * @generated
	 */
	EReference getRelationship_Style();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Relationship Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relationship Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueType="org.nasdanika.flow.Relationship" valueContainment="true"
	 *        valueAnnotation="urn:org.nasdanika homogenous='true' strict-containment='true'"
	 * @generated
	 */
	EClass getRelationshipEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getRelationshipEntry()
	 * @generated
	 */
	EAttribute getRelationshipEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getRelationshipEntry()
	 * @generated
	 */
	EReference getRelationshipEntry_Value();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Artifact Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Artifact Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueType="org.nasdanika.flow.Artifact" valueContainment="true"
	 *        valueAnnotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	EClass getArtifactEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getArtifactEntry()
	 * @generated
	 */
	EAttribute getArtifactEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getArtifactEntry()
	 * @generated
	 */
	EReference getArtifactEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.FlowElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see org.nasdanika.flow.FlowElement
	 * @generated
	 */
	EClass getFlowElement();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.flow.FlowElement#getOutputs <em>Outputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Outputs</em>'.
	 * @see org.nasdanika.flow.FlowElement#getOutputs()
	 * @see #getFlowElement()
	 * @generated
	 */
	EReference getFlowElement_Outputs();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.FlowElement#getInputs <em>Inputs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Inputs</em>'.
	 * @see org.nasdanika.flow.FlowElement#getInputs()
	 * @see #getFlowElement()
	 * @generated
	 */
	EReference getFlowElement_Inputs();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.flow.FlowElement#getCalls <em>Calls</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Calls</em>'.
	 * @see org.nasdanika.flow.FlowElement#getCalls()
	 * @see #getFlowElement()
	 * @generated
	 */
	EReference getFlowElement_Calls();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.FlowElement#getInvocations <em>Invocations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Invocations</em>'.
	 * @see org.nasdanika.flow.FlowElement#getInvocations()
	 * @see #getFlowElement()
	 * @generated
	 */
	EReference getFlowElement_Invocations();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.flow.FlowElement#getInputArtifactKeys <em>Input Artifact Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Input Artifact Keys</em>'.
	 * @see org.nasdanika.flow.FlowElement#getInputArtifactKeys()
	 * @see #getFlowElement()
	 * @generated
	 */
	EAttribute getFlowElement_InputArtifactKeys();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.FlowElement#getInputArtifacts <em>Input Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Input Artifacts</em>'.
	 * @see org.nasdanika.flow.FlowElement#getInputArtifacts()
	 * @see #getFlowElement()
	 * @generated
	 */
	EReference getFlowElement_InputArtifacts();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.FlowElement#getOutputArtifacts <em>Output Artifacts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Output Artifacts</em>'.
	 * @see org.nasdanika.flow.FlowElement#getOutputArtifacts()
	 * @see #getFlowElement()
	 * @generated
	 */
	EReference getFlowElement_OutputArtifacts();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.flow.FlowElement#getOutputArtifactKeys <em>Output Artifact Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Output Artifact Keys</em>'.
	 * @see org.nasdanika.flow.FlowElement#getOutputArtifactKeys()
	 * @see #getFlowElement()
	 * @generated
	 */
	EAttribute getFlowElement_OutputArtifactKeys();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.FlowElement#getParticipants <em>Participants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Participants</em>'.
	 * @see org.nasdanika.flow.FlowElement#getParticipants()
	 * @see #getFlowElement()
	 * @generated
	 */
	EReference getFlowElement_Participants();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.flow.FlowElement#getParticipantKeys <em>Participant Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Participant Keys</em>'.
	 * @see org.nasdanika.flow.FlowElement#getParticipantKeys()
	 * @see #getFlowElement()
	 * @generated
	 */
	EAttribute getFlowElement_ParticipantKeys();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.FlowElement#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Resources</em>'.
	 * @see org.nasdanika.flow.FlowElement#getResources()
	 * @see #getFlowElement()
	 * @generated
	 */
	EReference getFlowElement_Resources();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.flow.FlowElement#getResourceKeys <em>Resource Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Resource Keys</em>'.
	 * @see org.nasdanika.flow.FlowElement#getResourceKeys()
	 * @see #getFlowElement()
	 * @generated
	 */
	EAttribute getFlowElement_ResourceKeys();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.flow.FlowElement#getArtifactResponsibilities <em>Artifact Responsibilities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Artifact Responsibilities</em>'.
	 * @see org.nasdanika.flow.FlowElement#getArtifactResponsibilities()
	 * @see #getFlowElement()
	 * @generated
	 */
	EReference getFlowElement_ArtifactResponsibilities();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Element Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueType="org.nasdanika.flow.FlowElement&lt;?&gt;" valueContainment="true"
	 * @generated
	 */
	EClass getFlowElementEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getFlowElementEntry()
	 * @generated
	 */
	EAttribute getFlowElementEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getFlowElementEntry()
	 * @generated
	 */
	EReference getFlowElementEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Activity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity</em>'.
	 * @see org.nasdanika.flow.Activity
	 * @generated
	 */
	EClass getActivity();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Activity#getServices <em>Services</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Services</em>'.
	 * @see org.nasdanika.flow.Activity#getServices()
	 * @see #getActivity()
	 * @generated
	 */
	EReference getActivity_Services();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Activity Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueType="org.nasdanika.flow.Activity&lt;?&gt;" valueContainment="true"
	 * @generated
	 */
	EClass getActivityEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getActivityEntry()
	 * @generated
	 */
	EAttribute getActivityEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getActivityEntry()
	 * @generated
	 */
	EReference getActivityEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Service <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Service</em>'.
	 * @see org.nasdanika.flow.Service
	 * @generated
	 */
	EClass getService();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.flow.Service#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.nasdanika.flow.Service#getTarget()
	 * @see #getService()
	 * @generated
	 */
	EReference getService_Target();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.flow.Service#getTargetKey <em>Target Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Key</em>'.
	 * @see org.nasdanika.flow.Service#getTargetKey()
	 * @see #getService()
	 * @generated
	 */
	EAttribute getService_TargetKey();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition</em>'.
	 * @see org.nasdanika.flow.Transition
	 * @generated
	 */
	EClass getTransition();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Transition#getPayload <em>Payload</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Payload</em>'.
	 * @see org.nasdanika.flow.Transition#getPayload()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Payload();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.flow.Transition#getPayloadKeys <em>Payload Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Payload Keys</em>'.
	 * @see org.nasdanika.flow.Transition#getPayloadKeys()
	 * @see #getTransition()
	 * @generated
	 */
	EAttribute getTransition_PayloadKeys();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.flow.Transition#getTargetKey <em>Target Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Target Key</em>'.
	 * @see org.nasdanika.flow.Transition#getTargetKey()
	 * @see #getTransition()
	 * @generated
	 */
	EAttribute getTransition_TargetKey();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.flow.Transition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.nasdanika.flow.Transition#getTarget()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Target();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Transition Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueType="org.nasdanika.flow.Transition" valueContainment="true"
	 *        valueAnnotation="urn:org.nasdanika homogenous='true' strict-containment='true'"
	 * @generated
	 */
	EClass getTransitionEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getTransitionEntry()
	 * @generated
	 */
	EAttribute getTransitionEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getTransitionEntry()
	 * @generated
	 */
	EReference getTransitionEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Call <em>Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call</em>'.
	 * @see org.nasdanika.flow.Call
	 * @generated
	 */
	EClass getCall();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.flow.Call#getResponse <em>Response</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Response</em>'.
	 * @see org.nasdanika.flow.Call#getResponse()
	 * @see #getCall()
	 * @generated
	 */
	EReference getCall_Response();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.flow.Call#getResponseKeys <em>Response Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Response Keys</em>'.
	 * @see org.nasdanika.flow.Call#getResponseKeys()
	 * @see #getCall()
	 * @generated
	 */
	EAttribute getCall_ResponseKeys();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Call Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueType="org.nasdanika.flow.Call" valueContainment="true"
	 *        valueAnnotation="urn:org.nasdanika homogenous='true'"
	 * @generated
	 */
	EClass getCallEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getCallEntry()
	 * @generated
	 */
	EAttribute getCallEntry_Key();

	/**
	 * Returns the meta object for the containment reference '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getCallEntry()
	 * @generated
	 */
	EReference getCallEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Flow <em>Flow</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Flow</em>'.
	 * @see org.nasdanika.flow.Flow
	 * @generated
	 */
	EClass getFlow();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.flow.Flow#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Elements</em>'.
	 * @see org.nasdanika.flow.Flow#getElements()
	 * @see #getFlow()
	 * @generated
	 */
	EReference getFlow_Elements();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.flow.Flow#isPartition <em>Partition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Partition</em>'.
	 * @see org.nasdanika.flow.Flow#isPartition()
	 * @see #getFlow()
	 * @generated
	 */
	EAttribute getFlow_Partition();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.PseudoState <em>Pseudo State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pseudo State</em>'.
	 * @see org.nasdanika.flow.PseudoState
	 * @generated
	 */
	EClass getPseudoState();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.flow.PseudoState#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.nasdanika.flow.PseudoState#getType()
	 * @see #getPseudoState()
	 * @generated
	 */
	EAttribute getPseudoState_Type();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Choice <em>Choice</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Choice</em>'.
	 * @see org.nasdanika.flow.Choice
	 * @generated
	 */
	EClass getChoice();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.End <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>End</em>'.
	 * @see org.nasdanika.flow.End
	 * @generated
	 */
	EClass getEnd();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.EntryPoint <em>Entry Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entry Point</em>'.
	 * @see org.nasdanika.flow.EntryPoint
	 * @generated
	 */
	EClass getEntryPoint();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.ExitPoint <em>Exit Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Exit Point</em>'.
	 * @see org.nasdanika.flow.ExitPoint
	 * @generated
	 */
	EClass getExitPoint();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.ExpansionInput <em>Expansion Input</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expansion Input</em>'.
	 * @see org.nasdanika.flow.ExpansionInput
	 * @generated
	 */
	EClass getExpansionInput();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.ExpansionOutput <em>Expansion Output</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expansion Output</em>'.
	 * @see org.nasdanika.flow.ExpansionOutput
	 * @generated
	 */
	EClass getExpansionOutput();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Fork <em>Fork</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fork</em>'.
	 * @see org.nasdanika.flow.Fork
	 * @generated
	 */
	EClass getFork();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.InputPin <em>Input Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Input Pin</em>'.
	 * @see org.nasdanika.flow.InputPin
	 * @generated
	 */
	EClass getInputPin();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Join <em>Join</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Join</em>'.
	 * @see org.nasdanika.flow.Join
	 * @generated
	 */
	EClass getJoin();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.OutputPin <em>Output Pin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Output Pin</em>'.
	 * @see org.nasdanika.flow.OutputPin
	 * @generated
	 */
	EClass getOutputPin();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.flow.Start <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Start</em>'.
	 * @see org.nasdanika.flow.Start
	 * @generated
	 */
	EClass getStart();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	FlowFactory getFlowFactory();

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
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.PackageElementImpl <em>Package Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.PackageElementImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getPackageElement()
		 * @generated
		 */
		EClass PACKAGE_ELEMENT = eINSTANCE.getPackageElement();

		/**
		 * The meta object literal for the '<em><b>Prototype</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_ELEMENT__PROTOTYPE = eINSTANCE.getPackageElement_Prototype();

		/**
		 * The meta object literal for the '<em><b>Extensions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_ELEMENT__EXTENSIONS = eINSTANCE.getPackageElement_Extensions();

		/**
		 * The meta object literal for the '<em><b>Extends</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_ELEMENT__EXTENDS = eINSTANCE.getPackageElement_Extends();

		/**
		 * The meta object literal for the '<em><b>Modifiers</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_ELEMENT__MODIFIERS = eINSTANCE.getPackageElement_Modifiers();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_ELEMENT__DOCUMENTATION = eINSTANCE.getPackageElement_Documentation();

		/**
		 * The meta object literal for the '<em><b>Representations</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_ELEMENT__REPRESENTATIONS = eINSTANCE.getPackageElement_Representations();

		/**
		 * The meta object literal for the '<em><b>Create</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation PACKAGE_ELEMENT___CREATE = eINSTANCE.getPackageElement__Create();

		/**
		 * The meta object literal for the '<em><b>Apply</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation PACKAGE_ELEMENT___APPLY__PACKAGEELEMENT = eINSTANCE.getPackageElement__Apply__PackageElement();

		/**
		 * The meta object literal for the '<em><b>Resolve</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation PACKAGE_ELEMENT___RESOLVE__PACKAGEELEMENT = eINSTANCE.getPackageElement__Resolve__PackageElement();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.RepresentationEntryImpl <em>Representation Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.RepresentationEntryImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getRepresentationEntry()
		 * @generated
		 */
		EClass REPRESENTATION_ENTRY = eINSTANCE.getRepresentationEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPRESENTATION_ENTRY__KEY = eINSTANCE.getRepresentationEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REPRESENTATION_ENTRY__VALUE = eINSTANCE.getRepresentationEntry_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.PackageImpl <em>Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.PackageImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getPackage()
		 * @generated
		 */
		EClass PACKAGE = eINSTANCE.getPackage();

		/**
		 * The meta object literal for the '<em><b>Super Packages</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__SUPER_PACKAGES = eINSTANCE.getPackage_SuperPackages();

		/**
		 * The meta object literal for the '<em><b>Sub Packages</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__SUB_PACKAGES = eINSTANCE.getPackage_SubPackages();

		/**
		 * The meta object literal for the '<em><b>Participants</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__PARTICIPANTS = eINSTANCE.getPackage_Participants();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__RESOURCES = eINSTANCE.getPackage_Resources();

		/**
		 * The meta object literal for the '<em><b>Artifacts</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__ARTIFACTS = eINSTANCE.getPackage_Artifacts();

		/**
		 * The meta object literal for the '<em><b>Activities</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__ACTIVITIES = eINSTANCE.getPackage_Activities();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.PackageEntryImpl <em>Package Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.PackageEntryImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getPackageEntry()
		 * @generated
		 */
		EClass PACKAGE_ENTRY = eINSTANCE.getPackageEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_ENTRY__KEY = eINSTANCE.getPackageEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE_ENTRY__VALUE = eINSTANCE.getPackageEntry_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ServiceProviderImpl <em>Service Provider</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ServiceProviderImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getServiceProvider()
		 * @generated
		 */
		EClass SERVICE_PROVIDER = eINSTANCE.getServiceProvider();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE_PROVIDER__SERVICES = eINSTANCE.getServiceProvider_Services();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ParticipantImpl <em>Participant</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ParticipantImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getParticipant()
		 * @generated
		 */
		EClass PARTICIPANT = eINSTANCE.getParticipant();

		/**
		 * The meta object literal for the '<em><b>Participates</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT__PARTICIPATES = eINSTANCE.getParticipant_Participates();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT__RESOURCES = eINSTANCE.getParticipant_Resources();

		/**
		 * The meta object literal for the '<em><b>Artifacts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT__ARTIFACTS = eINSTANCE.getParticipant_Artifacts();

		/**
		 * The meta object literal for the '<em><b>Specializations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT__SPECIALIZATIONS = eINSTANCE.getParticipant_Specializations();

		/**
		 * The meta object literal for the '<em><b>Base Keys</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT__BASE_KEYS = eINSTANCE.getParticipant_BaseKeys();

		/**
		 * The meta object literal for the '<em><b>Bases</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT__BASES = eINSTANCE.getParticipant_Bases();

		/**
		 * The meta object literal for the '<em><b>Responsible</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT__RESPONSIBLE = eINSTANCE.getParticipant_Responsible();

		/**
		 * The meta object literal for the '<em><b>Accountable</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT__ACCOUNTABLE = eINSTANCE.getParticipant_Accountable();

		/**
		 * The meta object literal for the '<em><b>Consulted</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT__CONSULTED = eINSTANCE.getParticipant_Consulted();

		/**
		 * The meta object literal for the '<em><b>Informed</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT__INFORMED = eINSTANCE.getParticipant_Informed();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT__CHILDREN = eINSTANCE.getParticipant_Children();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ParticipantEntryImpl <em>Participant Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ParticipantEntryImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getParticipantEntry()
		 * @generated
		 */
		EClass PARTICIPANT_ENTRY = eINSTANCE.getParticipantEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT_ENTRY__KEY = eINSTANCE.getParticipantEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT_ENTRY__VALUE = eINSTANCE.getParticipantEntry_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ResourceImpl <em>Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ResourceImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getResource()
		 * @generated
		 */
		EClass RESOURCE = eINSTANCE.getResource();

		/**
		 * The meta object literal for the '<em><b>Artifacts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE__ARTIFACTS = eINSTANCE.getResource_Artifacts();

		/**
		 * The meta object literal for the '<em><b>Used In</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE__USED_IN = eINSTANCE.getResource_UsedIn();

		/**
		 * The meta object literal for the '<em><b>Used By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE__USED_BY = eINSTANCE.getResource_UsedBy();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE__CHILDREN = eINSTANCE.getResource_Children();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ResourceEntryImpl <em>Resource Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ResourceEntryImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getResourceEntry()
		 * @generated
		 */
		EClass RESOURCE_ENTRY = eINSTANCE.getResourceEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_ENTRY__KEY = eINSTANCE.getResourceEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_ENTRY__VALUE = eINSTANCE.getResourceEntry_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ParticipantResponsibilityImpl <em>Participant Responsibility</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ParticipantResponsibilityImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getParticipantResponsibility()
		 * @generated
		 */
		EClass PARTICIPANT_RESPONSIBILITY = eINSTANCE.getParticipantResponsibility();

		/**
		 * The meta object literal for the '<em><b>Responsible</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT_RESPONSIBILITY__RESPONSIBLE = eINSTANCE.getParticipantResponsibility_Responsible();

		/**
		 * The meta object literal for the '<em><b>Responsible Keys</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT_RESPONSIBILITY__RESPONSIBLE_KEYS = eINSTANCE.getParticipantResponsibility_ResponsibleKeys();

		/**
		 * The meta object literal for the '<em><b>Accountable</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE = eINSTANCE.getParticipantResponsibility_Accountable();

		/**
		 * The meta object literal for the '<em><b>Accountable Keys</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT_RESPONSIBILITY__ACCOUNTABLE_KEYS = eINSTANCE.getParticipantResponsibility_AccountableKeys();

		/**
		 * The meta object literal for the '<em><b>Consulted</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT_RESPONSIBILITY__CONSULTED = eINSTANCE.getParticipantResponsibility_Consulted();

		/**
		 * The meta object literal for the '<em><b>Consulted Keys</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT_RESPONSIBILITY__CONSULTED_KEYS = eINSTANCE.getParticipantResponsibility_ConsultedKeys();

		/**
		 * The meta object literal for the '<em><b>Informed</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTICIPANT_RESPONSIBILITY__INFORMED = eINSTANCE.getParticipantResponsibility_Informed();

		/**
		 * The meta object literal for the '<em><b>Informed Keys</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARTICIPANT_RESPONSIBILITY__INFORMED_KEYS = eINSTANCE.getParticipantResponsibility_InformedKeys();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ArtifactImpl <em>Artifact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ArtifactImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getArtifact()
		 * @generated
		 */
		EClass ARTIFACT = eINSTANCE.getArtifact();

		/**
		 * The meta object literal for the '<em><b>Repositories</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__REPOSITORIES = eINSTANCE.getArtifact_Repositories();

		/**
		 * The meta object literal for the '<em><b>Repository Keys</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__REPOSITORY_KEYS = eINSTANCE.getArtifact_RepositoryKeys();

		/**
		 * The meta object literal for the '<em><b>Input For</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__INPUT_FOR = eINSTANCE.getArtifact_InputFor();

		/**
		 * The meta object literal for the '<em><b>Output For</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__OUTPUT_FOR = eINSTANCE.getArtifact_OutputFor();

		/**
		 * The meta object literal for the '<em><b>Payload For</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__PAYLOAD_FOR = eINSTANCE.getArtifact_PayloadFor();

		/**
		 * The meta object literal for the '<em><b>Response For</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__RESPONSE_FOR = eINSTANCE.getArtifact_ResponseFor();

		/**
		 * The meta object literal for the '<em><b>Used By</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__USED_BY = eINSTANCE.getArtifact_UsedBy();

		/**
		 * The meta object literal for the '<em><b>Responsibilities</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__RESPONSIBILITIES = eINSTANCE.getArtifact_Responsibilities();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__CHILDREN = eINSTANCE.getArtifact_Children();

		/**
		 * The meta object literal for the '<em><b>Outbound Relationships</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__OUTBOUND_RELATIONSHIPS = eINSTANCE.getArtifact_OutboundRelationships();

		/**
		 * The meta object literal for the '<em><b>Inbound Relationships</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__INBOUND_RELATIONSHIPS = eINSTANCE.getArtifact_InboundRelationships();

		/**
		 * The meta object literal for the '<em><b>Partition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT__PARTITION = eINSTANCE.getArtifact_Partition();

		/**
		 * The meta object literal for the '<em><b>Style</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT__STYLE = eINSTANCE.getArtifact_Style();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ArtifactParticipantResponsibilityImpl <em>Artifact Participant Responsibility</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ArtifactParticipantResponsibilityImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getArtifactParticipantResponsibility()
		 * @generated
		 */
		EClass ARTIFACT_PARTICIPANT_RESPONSIBILITY = eINSTANCE.getArtifactParticipantResponsibility();

		/**
		 * The meta object literal for the '<em><b>Artifact Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT_KEY = eINSTANCE.getArtifactParticipantResponsibility_ArtifactKey();

		/**
		 * The meta object literal for the '<em><b>Artifact</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT_PARTICIPANT_RESPONSIBILITY__ARTIFACT = eINSTANCE.getArtifactParticipantResponsibility_Artifact();

		/**
		 * The meta object literal for the '<em><b>Suppress</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_PARTICIPANT_RESPONSIBILITY__SUPPRESS = eINSTANCE.getArtifactParticipantResponsibility_Suppress();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.RelationshipImpl <em>Relationship</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.RelationshipImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getRelationship()
		 * @generated
		 */
		EClass RELATIONSHIP = eINSTANCE.getRelationship();

		/**
		 * The meta object literal for the '<em><b>Target Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATIONSHIP__TARGET_KEY = eINSTANCE.getRelationship_TargetKey();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATIONSHIP__TARGET = eINSTANCE.getRelationship_Target();

		/**
		 * The meta object literal for the '<em><b>Style</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATIONSHIP__STYLE = eINSTANCE.getRelationship_Style();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.RelationshipEntryImpl <em>Relationship Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.RelationshipEntryImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getRelationshipEntry()
		 * @generated
		 */
		EClass RELATIONSHIP_ENTRY = eINSTANCE.getRelationshipEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RELATIONSHIP_ENTRY__KEY = eINSTANCE.getRelationshipEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATIONSHIP_ENTRY__VALUE = eINSTANCE.getRelationshipEntry_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ArtifactEntryImpl <em>Artifact Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ArtifactEntryImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getArtifactEntry()
		 * @generated
		 */
		EClass ARTIFACT_ENTRY = eINSTANCE.getArtifactEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARTIFACT_ENTRY__KEY = eINSTANCE.getArtifactEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARTIFACT_ENTRY__VALUE = eINSTANCE.getArtifactEntry_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.FlowElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.FlowElementImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getFlowElement()
		 * @generated
		 */
		EClass FLOW_ELEMENT = eINSTANCE.getFlowElement();

		/**
		 * The meta object literal for the '<em><b>Outputs</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT__OUTPUTS = eINSTANCE.getFlowElement_Outputs();

		/**
		 * The meta object literal for the '<em><b>Inputs</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT__INPUTS = eINSTANCE.getFlowElement_Inputs();

		/**
		 * The meta object literal for the '<em><b>Calls</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT__CALLS = eINSTANCE.getFlowElement_Calls();

		/**
		 * The meta object literal for the '<em><b>Invocations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT__INVOCATIONS = eINSTANCE.getFlowElement_Invocations();

		/**
		 * The meta object literal for the '<em><b>Input Artifact Keys</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOW_ELEMENT__INPUT_ARTIFACT_KEYS = eINSTANCE.getFlowElement_InputArtifactKeys();

		/**
		 * The meta object literal for the '<em><b>Input Artifacts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT__INPUT_ARTIFACTS = eINSTANCE.getFlowElement_InputArtifacts();

		/**
		 * The meta object literal for the '<em><b>Output Artifacts</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT__OUTPUT_ARTIFACTS = eINSTANCE.getFlowElement_OutputArtifacts();

		/**
		 * The meta object literal for the '<em><b>Output Artifact Keys</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOW_ELEMENT__OUTPUT_ARTIFACT_KEYS = eINSTANCE.getFlowElement_OutputArtifactKeys();

		/**
		 * The meta object literal for the '<em><b>Participants</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT__PARTICIPANTS = eINSTANCE.getFlowElement_Participants();

		/**
		 * The meta object literal for the '<em><b>Participant Keys</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOW_ELEMENT__PARTICIPANT_KEYS = eINSTANCE.getFlowElement_ParticipantKeys();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT__RESOURCES = eINSTANCE.getFlowElement_Resources();

		/**
		 * The meta object literal for the '<em><b>Resource Keys</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOW_ELEMENT__RESOURCE_KEYS = eINSTANCE.getFlowElement_ResourceKeys();

		/**
		 * The meta object literal for the '<em><b>Artifact Responsibilities</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT__ARTIFACT_RESPONSIBILITIES = eINSTANCE.getFlowElement_ArtifactResponsibilities();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.FlowElementEntryImpl <em>Element Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.FlowElementEntryImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getFlowElementEntry()
		 * @generated
		 */
		EClass FLOW_ELEMENT_ENTRY = eINSTANCE.getFlowElementEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOW_ELEMENT_ENTRY__KEY = eINSTANCE.getFlowElementEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW_ELEMENT_ENTRY__VALUE = eINSTANCE.getFlowElementEntry_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ActivityImpl <em>Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ActivityImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getActivity()
		 * @generated
		 */
		EClass ACTIVITY = eINSTANCE.getActivity();

		/**
		 * The meta object literal for the '<em><b>Services</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY__SERVICES = eINSTANCE.getActivity_Services();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ActivityEntryImpl <em>Activity Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ActivityEntryImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getActivityEntry()
		 * @generated
		 */
		EClass ACTIVITY_ENTRY = eINSTANCE.getActivityEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVITY_ENTRY__KEY = eINSTANCE.getActivityEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVITY_ENTRY__VALUE = eINSTANCE.getActivityEntry_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ServiceImpl <em>Service</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ServiceImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getService()
		 * @generated
		 */
		EClass SERVICE = eINSTANCE.getService();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SERVICE__TARGET = eINSTANCE.getService_Target();

		/**
		 * The meta object literal for the '<em><b>Target Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SERVICE__TARGET_KEY = eINSTANCE.getService_TargetKey();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.TransitionImpl <em>Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.TransitionImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getTransition()
		 * @generated
		 */
		EClass TRANSITION = eINSTANCE.getTransition();

		/**
		 * The meta object literal for the '<em><b>Payload</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__PAYLOAD = eINSTANCE.getTransition_Payload();

		/**
		 * The meta object literal for the '<em><b>Payload Keys</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION__PAYLOAD_KEYS = eINSTANCE.getTransition_PayloadKeys();

		/**
		 * The meta object literal for the '<em><b>Target Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION__TARGET_KEY = eINSTANCE.getTransition_TargetKey();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__TARGET = eINSTANCE.getTransition_Target();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.TransitionEntryImpl <em>Transition Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.TransitionEntryImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getTransitionEntry()
		 * @generated
		 */
		EClass TRANSITION_ENTRY = eINSTANCE.getTransitionEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION_ENTRY__KEY = eINSTANCE.getTransitionEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION_ENTRY__VALUE = eINSTANCE.getTransitionEntry_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.CallImpl <em>Call</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.CallImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getCall()
		 * @generated
		 */
		EClass CALL = eINSTANCE.getCall();

		/**
		 * The meta object literal for the '<em><b>Response</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL__RESPONSE = eINSTANCE.getCall_Response();

		/**
		 * The meta object literal for the '<em><b>Response Keys</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CALL__RESPONSE_KEYS = eINSTANCE.getCall_ResponseKeys();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.CallEntryImpl <em>Call Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.CallEntryImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getCallEntry()
		 * @generated
		 */
		EClass CALL_ENTRY = eINSTANCE.getCallEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CALL_ENTRY__KEY = eINSTANCE.getCallEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL_ENTRY__VALUE = eINSTANCE.getCallEntry_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.FlowImpl <em>Flow</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.FlowImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getFlow()
		 * @generated
		 */
		EClass FLOW = eINSTANCE.getFlow();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FLOW__ELEMENTS = eINSTANCE.getFlow_Elements();

		/**
		 * The meta object literal for the '<em><b>Partition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FLOW__PARTITION = eINSTANCE.getFlow_Partition();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.PseudoStateImpl <em>Pseudo State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.PseudoStateImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getPseudoState()
		 * @generated
		 */
		EClass PSEUDO_STATE = eINSTANCE.getPseudoState();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PSEUDO_STATE__TYPE = eINSTANCE.getPseudoState_Type();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ChoiceImpl <em>Choice</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ChoiceImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getChoice()
		 * @generated
		 */
		EClass CHOICE = eINSTANCE.getChoice();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.EndImpl <em>End</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.EndImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getEnd()
		 * @generated
		 */
		EClass END = eINSTANCE.getEnd();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.EntryPointImpl <em>Entry Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.EntryPointImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getEntryPoint()
		 * @generated
		 */
		EClass ENTRY_POINT = eINSTANCE.getEntryPoint();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ExitPointImpl <em>Exit Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ExitPointImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getExitPoint()
		 * @generated
		 */
		EClass EXIT_POINT = eINSTANCE.getExitPoint();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ExpansionInputImpl <em>Expansion Input</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ExpansionInputImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getExpansionInput()
		 * @generated
		 */
		EClass EXPANSION_INPUT = eINSTANCE.getExpansionInput();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ExpansionOutputImpl <em>Expansion Output</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ExpansionOutputImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getExpansionOutput()
		 * @generated
		 */
		EClass EXPANSION_OUTPUT = eINSTANCE.getExpansionOutput();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.ForkImpl <em>Fork</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.ForkImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getFork()
		 * @generated
		 */
		EClass FORK = eINSTANCE.getFork();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.InputPinImpl <em>Input Pin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.InputPinImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getInputPin()
		 * @generated
		 */
		EClass INPUT_PIN = eINSTANCE.getInputPin();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.JoinImpl <em>Join</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.JoinImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getJoin()
		 * @generated
		 */
		EClass JOIN = eINSTANCE.getJoin();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.OutputPinImpl <em>Output Pin</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.OutputPinImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getOutputPin()
		 * @generated
		 */
		EClass OUTPUT_PIN = eINSTANCE.getOutputPin();

		/**
		 * The meta object literal for the '{@link org.nasdanika.flow.impl.StartImpl <em>Start</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.flow.impl.StartImpl
		 * @see org.nasdanika.flow.impl.FlowPackageImpl#getStart()
		 * @generated
		 */
		EClass START = eINSTANCE.getStart();

	}

} //FlowPackage
