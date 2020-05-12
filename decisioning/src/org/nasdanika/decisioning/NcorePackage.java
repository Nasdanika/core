/**
 */
package org.nasdanika.ncore;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * Nasdanika core model containing common classes
 * <!-- end-model-doc -->
 * @see org.nasdanika.ncore.NcoreFactory
 * @model kind="package"
 *        annotation="urn:org.nasdanika category='General'"
 * @generated
 */
public interface NcorePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ncore";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "urn:org.nasdanika.ncore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.ncore";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	NcorePackage eINSTANCE = org.nasdanika.ncore.impl.NcorePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.ModelElementImpl <em>Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.ModelElementImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getModelElement()
	 * @generated
	 */
	int MODEL_ELEMENT = 7;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.NamedElementImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 8;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.EntityImpl <em>Entity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.EntityImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getEntity()
	 * @generated
	 */
	int ENTITY = 9;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.Configurable <em>Configurable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.Configurable
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getConfigurable()
	 * @generated
	 */
	int CONFIGURABLE = 10;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.PartyImpl <em>Party</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.PartyImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getParty()
	 * @generated
	 */
	int PARTY = 11;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.ContactMethodImpl <em>Contact Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.ContactMethodImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getContactMethod()
	 * @generated
	 */
	int CONTACT_METHOD = 12;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.EMailImpl <em>EMail</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.EMailImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getEMail()
	 * @generated
	 */
	int EMAIL = 13;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.PhoneImpl <em>Phone</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.PhoneImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getPhone()
	 * @generated
	 */
	int PHONE = 14;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.PostalAddressImpl <em>Postal Address</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.PostalAddressImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getPostalAddress()
	 * @generated
	 */
	int POSTAL_ADDRESS = 15;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.WebAddressImpl <em>Web Address</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.WebAddressImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getWebAddress()
	 * @generated
	 */
	int WEB_ADDRESS = 16;

	/**
	 * The meta object id for the '{@link org.nasdanika.common.Supplier <em>ISupplier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.common.Supplier
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getISupplier()
	 * @generated
	 */
	int ISUPPLIER = 0;

	/**
	 * The number of structural features of the '<em>ISupplier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISUPPLIER_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>ISupplier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISUPPLIER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.common.SupplierFactory <em>ISupplier Factory</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.common.SupplierFactory
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getISupplierFactory()
	 * @generated
	 */
	int ISUPPLIER_FACTORY = 1;

	/**
	 * The number of structural features of the '<em>ISupplier Factory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISUPPLIER_FACTORY_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>ISupplier Factory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ISUPPLIER_FACTORY_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__TITLE = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__DESCRIPTION = 1;

	/**
	 * The number of structural features of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.SupplierFactoryReferenceImpl <em>Supplier Factory Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.SupplierFactoryReferenceImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getSupplierFactoryReference()
	 * @generated
	 */
	int SUPPLIER_FACTORY_REFERENCE = 2;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER_FACTORY_REFERENCE__TITLE = MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER_FACTORY_REFERENCE__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER_FACTORY_REFERENCE__TARGET = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Supplier Factory Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER_FACTORY_REFERENCE_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Supplier Factory Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER_FACTORY_REFERENCE_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.SupplierImpl <em>Supplier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.SupplierImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getSupplier()
	 * @generated
	 */
	int SUPPLIER = 18;

	/**
	 * The meta object id for the '{@link org.nasdanika.common.Function <em>IFunction</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.common.Function
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getIFunction()
	 * @generated
	 */
	int IFUNCTION = 3;

	/**
	 * The number of structural features of the '<em>IFunction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFUNCTION_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>IFunction</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFUNCTION_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.common.FunctionFactory <em>IFunction Factory</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.common.FunctionFactory
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getIFunctionFactory()
	 * @generated
	 */
	int IFUNCTION_FACTORY = 4;

	/**
	 * The number of structural features of the '<em>IFunction Factory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFUNCTION_FACTORY_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>IFunction Factory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IFUNCTION_FACTORY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.common.Consumer <em>IConsumer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.common.Consumer
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getIConsumer()
	 * @generated
	 */
	int ICONSUMER = 5;

	/**
	 * The number of structural features of the '<em>IConsumer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONSUMER_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>IConsumer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONSUMER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.common.ConsumerFactory <em>IConsumer Factory</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.common.ConsumerFactory
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getIConsumerFactory()
	 * @generated
	 */
	int ICONSUMER_FACTORY = 6;

	/**
	 * The number of structural features of the '<em>IConsumer Factory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONSUMER_FACTORY_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>IConsumer Factory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICONSUMER_FACTORY_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__TITLE = MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__TITLE = MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY__ID = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Entity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTITY_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Configuration</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURABLE__CONFIGURATION = 0;

	/**
	 * The number of structural features of the '<em>Configurable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURABLE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Configurable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONFIGURABLE_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTY__TITLE = NAMED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTY__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTY__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTY__ID = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Contact Methods</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTY__CONTACT_METHODS = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Party</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTY_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Party</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARTY_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_METHOD__TITLE = NAMED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_METHOD__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_METHOD__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Contact Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_METHOD_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Contact Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_METHOD_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__TITLE = CONTACT_METHOD__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__DESCRIPTION = CONTACT_METHOD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__NAME = CONTACT_METHOD__NAME;

	/**
	 * The feature id for the '<em><b>EMail Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__EMAIL_ADDRESS = CONTACT_METHOD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>EMail</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FEATURE_COUNT = CONTACT_METHOD_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>EMail</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_OPERATION_COUNT = CONTACT_METHOD_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE__TITLE = CONTACT_METHOD__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE__DESCRIPTION = CONTACT_METHOD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE__NAME = CONTACT_METHOD__NAME;

	/**
	 * The feature id for the '<em><b>Country Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE__COUNTRY_CODE = CONTACT_METHOD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Area Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE__AREA_CODE = CONTACT_METHOD_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Phone Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE__PHONE_NUMBER = CONTACT_METHOD_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Extension</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE__EXTENSION = CONTACT_METHOD_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Phone</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_FEATURE_COUNT = CONTACT_METHOD_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Phone</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PHONE_OPERATION_COUNT = CONTACT_METHOD_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__TITLE = CONTACT_METHOD__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__DESCRIPTION = CONTACT_METHOD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__NAME = CONTACT_METHOD__NAME;

	/**
	 * The feature id for the '<em><b>Country</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__COUNTRY = CONTACT_METHOD_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>State Province</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__STATE_PROVINCE = CONTACT_METHOD_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>City</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__CITY = CONTACT_METHOD_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Postal Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__POSTAL_CODE = CONTACT_METHOD_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Line1</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__LINE1 = CONTACT_METHOD_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Line2</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS__LINE2 = CONTACT_METHOD_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Postal Address</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS_FEATURE_COUNT = CONTACT_METHOD_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Postal Address</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSTAL_ADDRESS_OPERATION_COUNT = CONTACT_METHOD_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_ADDRESS__TITLE = CONTACT_METHOD__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_ADDRESS__DESCRIPTION = CONTACT_METHOD__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_ADDRESS__NAME = CONTACT_METHOD__NAME;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_ADDRESS__URL = CONTACT_METHOD_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Web Address</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_ADDRESS_FEATURE_COUNT = CONTACT_METHOD_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Web Address</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WEB_ADDRESS_OPERATION_COUNT = CONTACT_METHOD_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.TypedElementImpl <em>Typed Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.TypedElementImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getTypedElement()
	 * @generated
	 */
	int TYPED_ELEMENT = 17;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.ValueImpl <em>Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.ValueImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getValue()
	 * @generated
	 */
	int VALUE = 20;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.NullImpl <em>Null</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.NullImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getNull()
	 * @generated
	 */
	int NULL = 21;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.OperationImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 22;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.ArrayImpl <em>Array</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.ArrayImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getArray()
	 * @generated
	 */
	int ARRAY = 23;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.ContextImpl <em>Context</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.ContextImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getContext()
	 * @generated
	 */
	int CONTEXT = 24;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.Entry <em>Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.Entry
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getEntry()
	 * @generated
	 */
	int ENTRY = 25;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.TypedEntryImpl <em>Typed Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.TypedEntryImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getTypedEntry()
	 * @generated
	 */
	int TYPED_ENTRY = 26;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.MapImpl <em>Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.MapImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getMap()
	 * @generated
	 */
	int MAP = 28;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.PropertyImpl <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.PropertyImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 29;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.FunctionImpl <em>Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.FunctionImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getFunction()
	 * @generated
	 */
	int FUNCTION = 30;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.ListImpl <em>List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.ListImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getList()
	 * @generated
	 */
	int LIST = 31;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.ObjectImpl <em>Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.ObjectImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getObject()
	 * @generated
	 */
	int OBJECT = 32;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.HttpCallImpl <em>Http Call</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.HttpCallImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getHttpCall()
	 * @generated
	 */
	int HTTP_CALL = 33;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.RestOperationImpl <em>Rest Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.RestOperationImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getRestOperation()
	 * @generated
	 */
	int REST_OPERATION = 34;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.RestFunctionImpl <em>Rest Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.RestFunctionImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getRestFunction()
	 * @generated
	 */
	int REST_FUNCTION = 35;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__TITLE = MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__TYPE = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT__REQUIRED = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Typed Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Typed Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ELEMENT_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER__TITLE = TYPED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER__DESCRIPTION = TYPED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER__TYPE = TYPED_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER__REQUIRED = TYPED_ELEMENT__REQUIRED;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER__IMPLEMENTATION = TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Supplier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER_FEATURE_COUNT = TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Supplier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER_OPERATION_COUNT = TYPED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.ResourceImpl <em>Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.ResourceImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getResource()
	 * @generated
	 */
	int RESOURCE = 19;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__TITLE = TYPED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__DESCRIPTION = TYPED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__TYPE = TYPED_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__REQUIRED = TYPED_ELEMENT__REQUIRED;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__LOCATION = TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Interpolate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__INTERPOLATE = TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FEATURE_COUNT = TYPED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPERATION_COUNT = TYPED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__TITLE = SUPPLIER__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__DESCRIPTION = SUPPLIER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__TYPE = SUPPLIER__TYPE;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__REQUIRED = SUPPLIER__REQUIRED;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__IMPLEMENTATION = SUPPLIER__IMPLEMENTATION;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__VALUE = SUPPLIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Interpolate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE__INTERPOLATE = SUPPLIER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_FEATURE_COUNT = SUPPLIER_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OPERATION_COUNT = SUPPLIER_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL__TITLE = TYPED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL__DESCRIPTION = TYPED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL__TYPE = TYPED_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL__REQUIRED = TYPED_ELEMENT__REQUIRED;

	/**
	 * The number of structural features of the '<em>Null</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_FEATURE_COUNT = TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Null</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NULL_OPERATION_COUNT = TYPED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__TITLE = SUPPLIER__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__DESCRIPTION = SUPPLIER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__TYPE = SUPPLIER__TYPE;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__REQUIRED = SUPPLIER__REQUIRED;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__IMPLEMENTATION = SUPPLIER__IMPLEMENTATION;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__ARGUMENTS = SUPPLIER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = SUPPLIER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_OPERATION_COUNT = SUPPLIER_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY__TITLE = MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY__ELEMENTS = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Array</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Array</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARRAY_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT__TITLE = MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT__ELEMENTS = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Context</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTEXT_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY__TITLE = NAMED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY__ENABLED = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ENTRY__TITLE = TYPED_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ENTRY__DESCRIPTION = TYPED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ENTRY__TYPE = TYPED_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ENTRY__REQUIRED = TYPED_ELEMENT__REQUIRED;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ENTRY__NAME = TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ENTRY__ENABLED = TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Typed Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ENTRY_FEATURE_COUNT = TYPED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Typed Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPED_ENTRY_OPERATION_COUNT = TYPED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.SupplierEntryImpl <em>Supplier Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.SupplierEntryImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getSupplierEntry()
	 * @generated
	 */
	int SUPPLIER_ENTRY = 27;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER_ENTRY__TITLE = SUPPLIER__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER_ENTRY__DESCRIPTION = SUPPLIER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER_ENTRY__TYPE = SUPPLIER__TYPE;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER_ENTRY__REQUIRED = SUPPLIER__REQUIRED;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER_ENTRY__IMPLEMENTATION = SUPPLIER__IMPLEMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER_ENTRY__NAME = SUPPLIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER_ENTRY__ENABLED = SUPPLIER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Supplier Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER_ENTRY_FEATURE_COUNT = SUPPLIER_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Supplier Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUPPLIER_ENTRY_OPERATION_COUNT = SUPPLIER_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP__TITLE = MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP__ENTRIES = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__TITLE = VALUE__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__DESCRIPTION = VALUE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__TYPE = VALUE__TYPE;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__REQUIRED = VALUE__REQUIRED;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__IMPLEMENTATION = VALUE__IMPLEMENTATION;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__VALUE = VALUE__VALUE;

	/**
	 * The feature id for the '<em><b>Interpolate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__INTERPOLATE = VALUE__INTERPOLATE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__NAME = VALUE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__ENABLED = VALUE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = VALUE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_OPERATION_COUNT = VALUE_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__TITLE = OPERATION__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__DESCRIPTION = OPERATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__TYPE = OPERATION__TYPE;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__REQUIRED = OPERATION__REQUIRED;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__IMPLEMENTATION = OPERATION__IMPLEMENTATION;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__ARGUMENTS = OPERATION__ARGUMENTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__NAME = OPERATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__ENABLED = OPERATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_OPERATION_COUNT = OPERATION_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST__TITLE = ARRAY__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST__DESCRIPTION = ARRAY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST__ELEMENTS = ARRAY__ELEMENTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST__NAME = ARRAY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST__ENABLED = ARRAY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_FEATURE_COUNT = ARRAY_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_OPERATION_COUNT = ARRAY_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__TITLE = MAP__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__DESCRIPTION = MAP__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__ENTRIES = MAP__ENTRIES;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__NAME = MAP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__ENABLED = MAP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_FEATURE_COUNT = MAP_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_OPERATION_COUNT = MAP_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTTP_CALL__TITLE = MODEL_ELEMENT__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTTP_CALL__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTTP_CALL__URL = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTTP_CALL__METHOD = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Headers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTTP_CALL__HEADERS = MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Connect Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTTP_CALL__CONNECT_TIMEOUT = MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Read Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTTP_CALL__READ_TIMEOUT = MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Success Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTTP_CALL__SUCCESS_CODE = MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Http Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTTP_CALL_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Http Call</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTTP_CALL_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_OPERATION__TITLE = HTTP_CALL__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_OPERATION__DESCRIPTION = HTTP_CALL__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_OPERATION__URL = HTTP_CALL__URL;

	/**
	 * The feature id for the '<em><b>Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_OPERATION__METHOD = HTTP_CALL__METHOD;

	/**
	 * The feature id for the '<em><b>Headers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_OPERATION__HEADERS = HTTP_CALL__HEADERS;

	/**
	 * The feature id for the '<em><b>Connect Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_OPERATION__CONNECT_TIMEOUT = HTTP_CALL__CONNECT_TIMEOUT;

	/**
	 * The feature id for the '<em><b>Read Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_OPERATION__READ_TIMEOUT = HTTP_CALL__READ_TIMEOUT;

	/**
	 * The feature id for the '<em><b>Success Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_OPERATION__SUCCESS_CODE = HTTP_CALL__SUCCESS_CODE;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_OPERATION__ARGUMENTS = HTTP_CALL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Rest Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_OPERATION_FEATURE_COUNT = HTTP_CALL_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Rest Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_OPERATION_OPERATION_COUNT = HTTP_CALL_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_FUNCTION__TITLE = REST_OPERATION__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_FUNCTION__DESCRIPTION = REST_OPERATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_FUNCTION__URL = REST_OPERATION__URL;

	/**
	 * The feature id for the '<em><b>Method</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_FUNCTION__METHOD = REST_OPERATION__METHOD;

	/**
	 * The feature id for the '<em><b>Headers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_FUNCTION__HEADERS = REST_OPERATION__HEADERS;

	/**
	 * The feature id for the '<em><b>Connect Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_FUNCTION__CONNECT_TIMEOUT = REST_OPERATION__CONNECT_TIMEOUT;

	/**
	 * The feature id for the '<em><b>Read Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_FUNCTION__READ_TIMEOUT = REST_OPERATION__READ_TIMEOUT;

	/**
	 * The feature id for the '<em><b>Success Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_FUNCTION__SUCCESS_CODE = REST_OPERATION__SUCCESS_CODE;

	/**
	 * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_FUNCTION__ARGUMENTS = REST_OPERATION__ARGUMENTS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_FUNCTION__NAME = REST_OPERATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Enabled</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_FUNCTION__ENABLED = REST_OPERATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Rest Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_FUNCTION_FEATURE_COUNT = REST_OPERATION_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Rest Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REST_FUNCTION_OPERATION_COUNT = REST_OPERATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.HtmlImpl <em>Html</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.HtmlImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getHtml()
	 * @generated
	 */
	int HTML = 36;

	/**
	 * The feature id for the '<em><b>Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML__TITLE = SUPPLIER__TITLE;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML__DESCRIPTION = SUPPLIER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML__TYPE = SUPPLIER__TYPE;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML__REQUIRED = SUPPLIER__REQUIRED;

	/**
	 * The feature id for the '<em><b>Implementation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML__IMPLEMENTATION = SUPPLIER__IMPLEMENTATION;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML__CONTENT = SUPPLIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Interpolate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML__INTERPOLATE = SUPPLIER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Html</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_FEATURE_COUNT = SUPPLIER_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Html</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HTML_OPERATION_COUNT = SUPPLIER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.HttpMethod <em>Http Method</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.HttpMethod
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getHttpMethod()
	 * @generated
	 */
	int HTTP_METHOD = 37;

	/**
	 * The meta object id for the '<em>IContext</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.common.Context
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getIContext()
	 * @generated
	 */
	int ICONTEXT = 39;


	/**
	 * The meta object id for the '<em>Exception</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.Exception
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getException()
	 * @generated
	 */
	int EXCEPTION = 38;


	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.ModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Element</em>'.
	 * @see org.nasdanika.ncore.ModelElement
	 * @generated
	 */
	EClass getModelElement();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.ModelElement#getTitle <em>Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Title</em>'.
	 * @see org.nasdanika.ncore.ModelElement#getTitle()
	 * @see #getModelElement()
	 * @generated
	 */
	EAttribute getModelElement_Title();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.ModelElement#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.nasdanika.ncore.ModelElement#getDescription()
	 * @see #getModelElement()
	 * @generated
	 */
	EAttribute getModelElement_Description();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see org.nasdanika.ncore.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.nasdanika.ncore.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entity</em>'.
	 * @see org.nasdanika.ncore.Entity
	 * @generated
	 */
	EClass getEntity();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Entity#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.nasdanika.ncore.Entity#getId()
	 * @see #getEntity()
	 * @generated
	 */
	EAttribute getEntity_Id();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Configurable <em>Configurable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Configurable</em>'.
	 * @see org.nasdanika.ncore.Configurable
	 * @generated
	 */
	EClass getConfigurable();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.Configurable#getConfiguration <em>Configuration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Configuration</em>'.
	 * @see org.nasdanika.ncore.Configurable#getConfiguration()
	 * @see #getConfigurable()
	 * @generated
	 */
	EReference getConfigurable_Configuration();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Party <em>Party</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Party</em>'.
	 * @see org.nasdanika.ncore.Party
	 * @generated
	 */
	EClass getParty();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.Party#getContactMethods <em>Contact Methods</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Contact Methods</em>'.
	 * @see org.nasdanika.ncore.Party#getContactMethods()
	 * @see #getParty()
	 * @generated
	 */
	EReference getParty_ContactMethods();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.ContactMethod <em>Contact Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contact Method</em>'.
	 * @see org.nasdanika.ncore.ContactMethod
	 * @generated
	 */
	EClass getContactMethod();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.EMail <em>EMail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EMail</em>'.
	 * @see org.nasdanika.ncore.EMail
	 * @generated
	 */
	EClass getEMail();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.EMail#getEMailAddress <em>EMail Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>EMail Address</em>'.
	 * @see org.nasdanika.ncore.EMail#getEMailAddress()
	 * @see #getEMail()
	 * @generated
	 */
	EAttribute getEMail_EMailAddress();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Phone <em>Phone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Phone</em>'.
	 * @see org.nasdanika.ncore.Phone
	 * @generated
	 */
	EClass getPhone();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Phone#getCountryCode <em>Country Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Country Code</em>'.
	 * @see org.nasdanika.ncore.Phone#getCountryCode()
	 * @see #getPhone()
	 * @generated
	 */
	EAttribute getPhone_CountryCode();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Phone#getAreaCode <em>Area Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Area Code</em>'.
	 * @see org.nasdanika.ncore.Phone#getAreaCode()
	 * @see #getPhone()
	 * @generated
	 */
	EAttribute getPhone_AreaCode();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Phone#getPhoneNumber <em>Phone Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phone Number</em>'.
	 * @see org.nasdanika.ncore.Phone#getPhoneNumber()
	 * @see #getPhone()
	 * @generated
	 */
	EAttribute getPhone_PhoneNumber();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Phone#getExtension <em>Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Extension</em>'.
	 * @see org.nasdanika.ncore.Phone#getExtension()
	 * @see #getPhone()
	 * @generated
	 */
	EAttribute getPhone_Extension();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.PostalAddress <em>Postal Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Postal Address</em>'.
	 * @see org.nasdanika.ncore.PostalAddress
	 * @generated
	 */
	EClass getPostalAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.PostalAddress#getCountry <em>Country</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Country</em>'.
	 * @see org.nasdanika.ncore.PostalAddress#getCountry()
	 * @see #getPostalAddress()
	 * @generated
	 */
	EAttribute getPostalAddress_Country();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.PostalAddress#getStateProvince <em>State Province</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>State Province</em>'.
	 * @see org.nasdanika.ncore.PostalAddress#getStateProvince()
	 * @see #getPostalAddress()
	 * @generated
	 */
	EAttribute getPostalAddress_StateProvince();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.PostalAddress#getCity <em>City</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>City</em>'.
	 * @see org.nasdanika.ncore.PostalAddress#getCity()
	 * @see #getPostalAddress()
	 * @generated
	 */
	EAttribute getPostalAddress_City();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.PostalAddress#getPostalCode <em>Postal Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Postal Code</em>'.
	 * @see org.nasdanika.ncore.PostalAddress#getPostalCode()
	 * @see #getPostalAddress()
	 * @generated
	 */
	EAttribute getPostalAddress_PostalCode();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.PostalAddress#getLine1 <em>Line1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line1</em>'.
	 * @see org.nasdanika.ncore.PostalAddress#getLine1()
	 * @see #getPostalAddress()
	 * @generated
	 */
	EAttribute getPostalAddress_Line1();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.PostalAddress#getLine2 <em>Line2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line2</em>'.
	 * @see org.nasdanika.ncore.PostalAddress#getLine2()
	 * @see #getPostalAddress()
	 * @generated
	 */
	EAttribute getPostalAddress_Line2();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.WebAddress <em>Web Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Web Address</em>'.
	 * @see org.nasdanika.ncore.WebAddress
	 * @generated
	 */
	EClass getWebAddress();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.WebAddress#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url</em>'.
	 * @see org.nasdanika.ncore.WebAddress#getUrl()
	 * @see #getWebAddress()
	 * @generated
	 */
	EAttribute getWebAddress_Url();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.common.Supplier <em>ISupplier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ISupplier</em>'.
	 * @see org.nasdanika.common.Supplier
	 * @model instanceClass="org.nasdanika.common.Supplier" typeParameters="T"
	 * @generated
	 */
	EClass getISupplier();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.common.SupplierFactory <em>ISupplier Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>ISupplier Factory</em>'.
	 * @see org.nasdanika.common.SupplierFactory
	 * @model instanceClass="org.nasdanika.common.SupplierFactory" typeParameters="T"
	 * @generated
	 */
	EClass getISupplierFactory();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.SupplierFactoryReference <em>Supplier Factory Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Supplier Factory Reference</em>'.
	 * @see org.nasdanika.ncore.SupplierFactoryReference
	 * @generated
	 */
	EClass getSupplierFactoryReference();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.ncore.SupplierFactoryReference#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.nasdanika.ncore.SupplierFactoryReference#getTarget()
	 * @see #getSupplierFactoryReference()
	 * @generated
	 */
	EReference getSupplierFactoryReference_Target();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Supplier <em>Supplier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Supplier</em>'.
	 * @see org.nasdanika.ncore.Supplier
	 * @generated
	 */
	EClass getSupplier();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Supplier#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation</em>'.
	 * @see org.nasdanika.ncore.Supplier#getImplementation()
	 * @see #getSupplier()
	 * @generated
	 */
	EAttribute getSupplier_Implementation();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource</em>'.
	 * @see org.nasdanika.ncore.Resource
	 * @generated
	 */
	EClass getResource();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Resource#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.nasdanika.ncore.Resource#getLocation()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Location();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Resource#isInterpolate <em>Interpolate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interpolate</em>'.
	 * @see org.nasdanika.ncore.Resource#isInterpolate()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Interpolate();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.common.Function <em>IFunction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IFunction</em>'.
	 * @see org.nasdanika.common.Function
	 * @model instanceClass="org.nasdanika.common.Function" typeParameters="T R"
	 * @generated
	 */
	EClass getIFunction();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.common.FunctionFactory <em>IFunction Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IFunction Factory</em>'.
	 * @see org.nasdanika.common.FunctionFactory
	 * @model instanceClass="org.nasdanika.common.FunctionFactory" typeParameters="T R"
	 * @generated
	 */
	EClass getIFunctionFactory();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.common.Consumer <em>IConsumer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IConsumer</em>'.
	 * @see org.nasdanika.common.Consumer
	 * @model instanceClass="org.nasdanika.common.Consumer" typeParameters="T"
	 * @generated
	 */
	EClass getIConsumer();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.common.ConsumerFactory <em>IConsumer Factory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IConsumer Factory</em>'.
	 * @see org.nasdanika.common.ConsumerFactory
	 * @model instanceClass="org.nasdanika.common.ConsumerFactory" typeParameters="T"
	 * @generated
	 */
	EClass getIConsumerFactory();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.TypedElement <em>Typed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Typed Element</em>'.
	 * @see org.nasdanika.ncore.TypedElement
	 * @generated
	 */
	EClass getTypedElement();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.TypedElement#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.nasdanika.ncore.TypedElement#getType()
	 * @see #getTypedElement()
	 * @generated
	 */
	EAttribute getTypedElement_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.TypedElement#isRequired <em>Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Required</em>'.
	 * @see org.nasdanika.ncore.TypedElement#isRequired()
	 * @see #getTypedElement()
	 * @generated
	 */
	EAttribute getTypedElement_Required();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value</em>'.
	 * @see org.nasdanika.ncore.Value
	 * @generated
	 */
	EClass getValue();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Value#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.nasdanika.ncore.Value#getValue()
	 * @see #getValue()
	 * @generated
	 */
	EAttribute getValue_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Value#isInterpolate <em>Interpolate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interpolate</em>'.
	 * @see org.nasdanika.ncore.Value#isInterpolate()
	 * @see #getValue()
	 * @generated
	 */
	EAttribute getValue_Interpolate();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Null <em>Null</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Null</em>'.
	 * @see org.nasdanika.ncore.Null
	 * @generated
	 */
	EClass getNull();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see org.nasdanika.ncore.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.Operation#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arguments</em>'.
	 * @see org.nasdanika.ncore.Operation#getArguments()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Arguments();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Array <em>Array</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Array</em>'.
	 * @see org.nasdanika.ncore.Array
	 * @generated
	 */
	EClass getArray();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.Array#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see org.nasdanika.ncore.Array#getElements()
	 * @see #getArray()
	 * @generated
	 */
	EReference getArray_Elements();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Context <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Context</em>'.
	 * @see org.nasdanika.ncore.Context
	 * @generated
	 */
	EClass getContext();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.Context#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see org.nasdanika.ncore.Context#getElements()
	 * @see #getContext()
	 * @generated
	 */
	EReference getContext_Elements();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Entry <em>Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entry</em>'.
	 * @see org.nasdanika.ncore.Entry
	 * @generated
	 */
	EClass getEntry();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Entry#isEnabled <em>Enabled</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Enabled</em>'.
	 * @see org.nasdanika.ncore.Entry#isEnabled()
	 * @see #getEntry()
	 * @generated
	 */
	EAttribute getEntry_Enabled();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.TypedEntry <em>Typed Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Typed Entry</em>'.
	 * @see org.nasdanika.ncore.TypedEntry
	 * @generated
	 */
	EClass getTypedEntry();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.SupplierEntry <em>Supplier Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Supplier Entry</em>'.
	 * @see org.nasdanika.ncore.SupplierEntry
	 * @generated
	 */
	EClass getSupplierEntry();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Map <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map</em>'.
	 * @see org.nasdanika.ncore.Map
	 * @generated
	 */
	EClass getMap();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.Map#getEntries <em>Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Entries</em>'.
	 * @see org.nasdanika.ncore.Map#getEntries()
	 * @see #getMap()
	 * @generated
	 */
	EReference getMap_Entries();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see org.nasdanika.ncore.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function</em>'.
	 * @see org.nasdanika.ncore.Function
	 * @generated
	 */
	EClass getFunction();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.List <em>List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List</em>'.
	 * @see org.nasdanika.ncore.List
	 * @generated
	 */
	EClass getList();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Object <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Object</em>'.
	 * @see org.nasdanika.ncore.Object
	 * @generated
	 */
	EClass getObject();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.HttpCall <em>Http Call</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Http Call</em>'.
	 * @see org.nasdanika.ncore.HttpCall
	 * @generated
	 */
	EClass getHttpCall();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.HttpCall#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url</em>'.
	 * @see org.nasdanika.ncore.HttpCall#getUrl()
	 * @see #getHttpCall()
	 * @generated
	 */
	EAttribute getHttpCall_Url();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.HttpCall#getMethod <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method</em>'.
	 * @see org.nasdanika.ncore.HttpCall#getMethod()
	 * @see #getHttpCall()
	 * @generated
	 */
	EAttribute getHttpCall_Method();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.HttpCall#getHeaders <em>Headers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Headers</em>'.
	 * @see org.nasdanika.ncore.HttpCall#getHeaders()
	 * @see #getHttpCall()
	 * @generated
	 */
	EReference getHttpCall_Headers();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.HttpCall#getConnectTimeout <em>Connect Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Connect Timeout</em>'.
	 * @see org.nasdanika.ncore.HttpCall#getConnectTimeout()
	 * @see #getHttpCall()
	 * @generated
	 */
	EAttribute getHttpCall_ConnectTimeout();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.HttpCall#getReadTimeout <em>Read Timeout</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Read Timeout</em>'.
	 * @see org.nasdanika.ncore.HttpCall#getReadTimeout()
	 * @see #getHttpCall()
	 * @generated
	 */
	EAttribute getHttpCall_ReadTimeout();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.HttpCall#getSuccessCode <em>Success Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Success Code</em>'.
	 * @see org.nasdanika.ncore.HttpCall#getSuccessCode()
	 * @see #getHttpCall()
	 * @generated
	 */
	EAttribute getHttpCall_SuccessCode();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.RestOperation <em>Rest Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rest Operation</em>'.
	 * @see org.nasdanika.ncore.RestOperation
	 * @generated
	 */
	EClass getRestOperation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.RestOperation#getArguments <em>Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Arguments</em>'.
	 * @see org.nasdanika.ncore.RestOperation#getArguments()
	 * @see #getRestOperation()
	 * @generated
	 */
	EReference getRestOperation_Arguments();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.RestFunction <em>Rest Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rest Function</em>'.
	 * @see org.nasdanika.ncore.RestFunction
	 * @generated
	 */
	EClass getRestFunction();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Html <em>Html</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Html</em>'.
	 * @see org.nasdanika.ncore.Html
	 * @generated
	 */
	EClass getHtml();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Html#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see org.nasdanika.ncore.Html#getContent()
	 * @see #getHtml()
	 * @generated
	 */
	EAttribute getHtml_Content();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Html#isInterpolate <em>Interpolate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interpolate</em>'.
	 * @see org.nasdanika.ncore.Html#isInterpolate()
	 * @see #getHtml()
	 * @generated
	 */
	EAttribute getHtml_Interpolate();

	/**
	 * Returns the meta object for enum '{@link org.nasdanika.ncore.HttpMethod <em>Http Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Http Method</em>'.
	 * @see org.nasdanika.ncore.HttpMethod
	 * @generated
	 */
	EEnum getHttpMethod();

	/**
	 * Returns the meta object for data type '{@link org.nasdanika.common.Context <em>IContext</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IContext</em>'.
	 * @see org.nasdanika.common.Context
	 * @model instanceClass="org.nasdanika.common.Context"
	 * @generated
	 */
	EDataType getIContext();

	/**
	 * Returns the meta object for data type '{@link java.lang.Exception <em>Exception</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Exception</em>'.
	 * @see java.lang.Exception
	 * @model instanceClass="java.lang.Exception"
	 * @generated
	 */
	EDataType getException();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	NcoreFactory getNcoreFactory();

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
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.ModelElementImpl <em>Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.ModelElementImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getModelElement()
		 * @generated
		 */
		EClass MODEL_ELEMENT = eINSTANCE.getModelElement();

		/**
		 * The meta object literal for the '<em><b>Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_ELEMENT__TITLE = eINSTANCE.getModelElement_Title();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_ELEMENT__DESCRIPTION = eINSTANCE.getModelElement_Description();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.NamedElementImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.EntityImpl <em>Entity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.EntityImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getEntity()
		 * @generated
		 */
		EClass ENTITY = eINSTANCE.getEntity();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTITY__ID = eINSTANCE.getEntity_Id();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.Configurable <em>Configurable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.Configurable
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getConfigurable()
		 * @generated
		 */
		EClass CONFIGURABLE = eINSTANCE.getConfigurable();

		/**
		 * The meta object literal for the '<em><b>Configuration</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONFIGURABLE__CONFIGURATION = eINSTANCE.getConfigurable_Configuration();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.PartyImpl <em>Party</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.PartyImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getParty()
		 * @generated
		 */
		EClass PARTY = eINSTANCE.getParty();

		/**
		 * The meta object literal for the '<em><b>Contact Methods</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARTY__CONTACT_METHODS = eINSTANCE.getParty_ContactMethods();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.ContactMethodImpl <em>Contact Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.ContactMethodImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getContactMethod()
		 * @generated
		 */
		EClass CONTACT_METHOD = eINSTANCE.getContactMethod();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.EMailImpl <em>EMail</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.EMailImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getEMail()
		 * @generated
		 */
		EClass EMAIL = eINSTANCE.getEMail();

		/**
		 * The meta object literal for the '<em><b>EMail Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMAIL__EMAIL_ADDRESS = eINSTANCE.getEMail_EMailAddress();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.PhoneImpl <em>Phone</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.PhoneImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getPhone()
		 * @generated
		 */
		EClass PHONE = eINSTANCE.getPhone();

		/**
		 * The meta object literal for the '<em><b>Country Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PHONE__COUNTRY_CODE = eINSTANCE.getPhone_CountryCode();

		/**
		 * The meta object literal for the '<em><b>Area Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PHONE__AREA_CODE = eINSTANCE.getPhone_AreaCode();

		/**
		 * The meta object literal for the '<em><b>Phone Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PHONE__PHONE_NUMBER = eINSTANCE.getPhone_PhoneNumber();

		/**
		 * The meta object literal for the '<em><b>Extension</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PHONE__EXTENSION = eINSTANCE.getPhone_Extension();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.PostalAddressImpl <em>Postal Address</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.PostalAddressImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getPostalAddress()
		 * @generated
		 */
		EClass POSTAL_ADDRESS = eINSTANCE.getPostalAddress();

		/**
		 * The meta object literal for the '<em><b>Country</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSTAL_ADDRESS__COUNTRY = eINSTANCE.getPostalAddress_Country();

		/**
		 * The meta object literal for the '<em><b>State Province</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSTAL_ADDRESS__STATE_PROVINCE = eINSTANCE.getPostalAddress_StateProvince();

		/**
		 * The meta object literal for the '<em><b>City</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSTAL_ADDRESS__CITY = eINSTANCE.getPostalAddress_City();

		/**
		 * The meta object literal for the '<em><b>Postal Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSTAL_ADDRESS__POSTAL_CODE = eINSTANCE.getPostalAddress_PostalCode();

		/**
		 * The meta object literal for the '<em><b>Line1</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSTAL_ADDRESS__LINE1 = eINSTANCE.getPostalAddress_Line1();

		/**
		 * The meta object literal for the '<em><b>Line2</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSTAL_ADDRESS__LINE2 = eINSTANCE.getPostalAddress_Line2();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.WebAddressImpl <em>Web Address</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.WebAddressImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getWebAddress()
		 * @generated
		 */
		EClass WEB_ADDRESS = eINSTANCE.getWebAddress();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WEB_ADDRESS__URL = eINSTANCE.getWebAddress_Url();

		/**
		 * The meta object literal for the '{@link org.nasdanika.common.Supplier <em>ISupplier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.common.Supplier
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getISupplier()
		 * @generated
		 */
		EClass ISUPPLIER = eINSTANCE.getISupplier();

		/**
		 * The meta object literal for the '{@link org.nasdanika.common.SupplierFactory <em>ISupplier Factory</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.common.SupplierFactory
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getISupplierFactory()
		 * @generated
		 */
		EClass ISUPPLIER_FACTORY = eINSTANCE.getISupplierFactory();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.SupplierFactoryReferenceImpl <em>Supplier Factory Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.SupplierFactoryReferenceImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getSupplierFactoryReference()
		 * @generated
		 */
		EClass SUPPLIER_FACTORY_REFERENCE = eINSTANCE.getSupplierFactoryReference();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SUPPLIER_FACTORY_REFERENCE__TARGET = eINSTANCE.getSupplierFactoryReference_Target();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.SupplierImpl <em>Supplier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.SupplierImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getSupplier()
		 * @generated
		 */
		EClass SUPPLIER = eINSTANCE.getSupplier();

		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUPPLIER__IMPLEMENTATION = eINSTANCE.getSupplier_Implementation();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.ResourceImpl <em>Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.ResourceImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getResource()
		 * @generated
		 */
		EClass RESOURCE = eINSTANCE.getResource();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE__LOCATION = eINSTANCE.getResource_Location();

		/**
		 * The meta object literal for the '<em><b>Interpolate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE__INTERPOLATE = eINSTANCE.getResource_Interpolate();

		/**
		 * The meta object literal for the '{@link org.nasdanika.common.Function <em>IFunction</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.common.Function
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getIFunction()
		 * @generated
		 */
		EClass IFUNCTION = eINSTANCE.getIFunction();

		/**
		 * The meta object literal for the '{@link org.nasdanika.common.FunctionFactory <em>IFunction Factory</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.common.FunctionFactory
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getIFunctionFactory()
		 * @generated
		 */
		EClass IFUNCTION_FACTORY = eINSTANCE.getIFunctionFactory();

		/**
		 * The meta object literal for the '{@link org.nasdanika.common.Consumer <em>IConsumer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.common.Consumer
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getIConsumer()
		 * @generated
		 */
		EClass ICONSUMER = eINSTANCE.getIConsumer();

		/**
		 * The meta object literal for the '{@link org.nasdanika.common.ConsumerFactory <em>IConsumer Factory</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.common.ConsumerFactory
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getIConsumerFactory()
		 * @generated
		 */
		EClass ICONSUMER_FACTORY = eINSTANCE.getIConsumerFactory();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.TypedElementImpl <em>Typed Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.TypedElementImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getTypedElement()
		 * @generated
		 */
		EClass TYPED_ELEMENT = eINSTANCE.getTypedElement();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPED_ELEMENT__TYPE = eINSTANCE.getTypedElement_Type();

		/**
		 * The meta object literal for the '<em><b>Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPED_ELEMENT__REQUIRED = eINSTANCE.getTypedElement_Required();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.ValueImpl <em>Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.ValueImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getValue()
		 * @generated
		 */
		EClass VALUE = eINSTANCE.getValue();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE__VALUE = eINSTANCE.getValue_Value();

		/**
		 * The meta object literal for the '<em><b>Interpolate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE__INTERPOLATE = eINSTANCE.getValue_Interpolate();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.NullImpl <em>Null</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.NullImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getNull()
		 * @generated
		 */
		EClass NULL = eINSTANCE.getNull();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.OperationImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__ARGUMENTS = eINSTANCE.getOperation_Arguments();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.ArrayImpl <em>Array</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.ArrayImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getArray()
		 * @generated
		 */
		EClass ARRAY = eINSTANCE.getArray();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARRAY__ELEMENTS = eINSTANCE.getArray_Elements();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.ContextImpl <em>Context</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.ContextImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getContext()
		 * @generated
		 */
		EClass CONTEXT = eINSTANCE.getContext();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTEXT__ELEMENTS = eINSTANCE.getContext_Elements();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.Entry <em>Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.Entry
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getEntry()
		 * @generated
		 */
		EClass ENTRY = eINSTANCE.getEntry();

		/**
		 * The meta object literal for the '<em><b>Enabled</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENTRY__ENABLED = eINSTANCE.getEntry_Enabled();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.TypedEntryImpl <em>Typed Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.TypedEntryImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getTypedEntry()
		 * @generated
		 */
		EClass TYPED_ENTRY = eINSTANCE.getTypedEntry();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.SupplierEntryImpl <em>Supplier Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.SupplierEntryImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getSupplierEntry()
		 * @generated
		 */
		EClass SUPPLIER_ENTRY = eINSTANCE.getSupplierEntry();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.MapImpl <em>Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.MapImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getMap()
		 * @generated
		 */
		EClass MAP = eINSTANCE.getMap();

		/**
		 * The meta object literal for the '<em><b>Entries</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP__ENTRIES = eINSTANCE.getMap_Entries();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.PropertyImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.FunctionImpl <em>Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.FunctionImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getFunction()
		 * @generated
		 */
		EClass FUNCTION = eINSTANCE.getFunction();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.ListImpl <em>List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.ListImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getList()
		 * @generated
		 */
		EClass LIST = eINSTANCE.getList();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.ObjectImpl <em>Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.ObjectImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getObject()
		 * @generated
		 */
		EClass OBJECT = eINSTANCE.getObject();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.HttpCallImpl <em>Http Call</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.HttpCallImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getHttpCall()
		 * @generated
		 */
		EClass HTTP_CALL = eINSTANCE.getHttpCall();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HTTP_CALL__URL = eINSTANCE.getHttpCall_Url();

		/**
		 * The meta object literal for the '<em><b>Method</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HTTP_CALL__METHOD = eINSTANCE.getHttpCall_Method();

		/**
		 * The meta object literal for the '<em><b>Headers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HTTP_CALL__HEADERS = eINSTANCE.getHttpCall_Headers();

		/**
		 * The meta object literal for the '<em><b>Connect Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HTTP_CALL__CONNECT_TIMEOUT = eINSTANCE.getHttpCall_ConnectTimeout();

		/**
		 * The meta object literal for the '<em><b>Read Timeout</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HTTP_CALL__READ_TIMEOUT = eINSTANCE.getHttpCall_ReadTimeout();

		/**
		 * The meta object literal for the '<em><b>Success Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HTTP_CALL__SUCCESS_CODE = eINSTANCE.getHttpCall_SuccessCode();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.RestOperationImpl <em>Rest Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.RestOperationImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getRestOperation()
		 * @generated
		 */
		EClass REST_OPERATION = eINSTANCE.getRestOperation();

		/**
		 * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REST_OPERATION__ARGUMENTS = eINSTANCE.getRestOperation_Arguments();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.RestFunctionImpl <em>Rest Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.RestFunctionImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getRestFunction()
		 * @generated
		 */
		EClass REST_FUNCTION = eINSTANCE.getRestFunction();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.HtmlImpl <em>Html</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.HtmlImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getHtml()
		 * @generated
		 */
		EClass HTML = eINSTANCE.getHtml();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HTML__CONTENT = eINSTANCE.getHtml_Content();

		/**
		 * The meta object literal for the '<em><b>Interpolate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute HTML__INTERPOLATE = eINSTANCE.getHtml_Interpolate();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.HttpMethod <em>Http Method</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.HttpMethod
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getHttpMethod()
		 * @generated
		 */
		EEnum HTTP_METHOD = eINSTANCE.getHttpMethod();

		/**
		 * The meta object literal for the '<em>IContext</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.common.Context
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getIContext()
		 * @generated
		 */
		EDataType ICONTEXT = eINSTANCE.getIContext();

		/**
		 * The meta object literal for the '<em>Exception</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Exception
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getException()
		 * @generated
		 */
		EDataType EXCEPTION = eINSTANCE.getException();

	}

} //NcorePackage
