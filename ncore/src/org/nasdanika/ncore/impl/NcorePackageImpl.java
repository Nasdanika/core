/**
 */
package org.nasdanika.ncore.impl;

import static org.nasdanika.ncore.NcorePackage.RESOURCE;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.nasdanika.ncore.Array;
import org.nasdanika.ncore.Configurable;
import org.nasdanika.ncore.Entity;
import org.nasdanika.ncore.Entry;
import org.nasdanika.ncore.Function;
import org.nasdanika.ncore.Html;
import org.nasdanika.ncore.HttpCall;
import org.nasdanika.ncore.HttpMethod;
import org.nasdanika.ncore.List;
import org.nasdanika.ncore.Map;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcoreFactory;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Null;
import org.nasdanika.ncore.Operation;
import org.nasdanika.ncore.Property;
import org.nasdanika.ncore.Reference;
import org.nasdanika.ncore.Resource;
import org.nasdanika.ncore.RestFunction;
import org.nasdanika.ncore.RestOperation;
import org.nasdanika.ncore.Supplier;
import org.nasdanika.ncore.SupplierEntry;
import org.nasdanika.ncore.TypedElement;
import org.nasdanika.ncore.TypedEntry;
import org.nasdanika.ncore.Value;
import org.nasdanika.ncore.util.NcoreValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NcorePackageImpl extends EPackageImpl implements NcorePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass configurableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass supplierEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nullEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass arrayEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typedEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass supplierEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mapEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass functionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass objectEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass httpCallEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass restOperationEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass restFunctionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass htmlEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum httpMethodEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.nasdanika.ncore.NcorePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private NcorePackageImpl() {
		super(eNS_URI, NcoreFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link NcorePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static NcorePackage init() {
		if (isInited) return (NcorePackage)EPackage.Registry.INSTANCE.getEPackage(NcorePackage.eNS_URI);

		// Obtain or create and register package
		Object registeredNcorePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		NcorePackageImpl theNcorePackage = registeredNcorePackage instanceof NcorePackageImpl ? (NcorePackageImpl)registeredNcorePackage : new NcorePackageImpl();

		isInited = true;

		// Create package meta-data objects
		theNcorePackage.createPackageContents();

		// Initialize created meta-data
		theNcorePackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theNcorePackage,
			 new EValidator.Descriptor() {
				 @Override
				 public EValidator getEValidator() {
					 return NcoreValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theNcorePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(NcorePackage.eNS_URI, theNcorePackage);
		return theNcorePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getModelElement() {
		return modelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getModelElement_Title() {
		return (EAttribute)modelElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getModelElement_Description() {
		return (EAttribute)modelElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNamedElement() {
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNamedElement_Name() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEntity() {
		return entityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEntity_Id() {
		return (EAttribute)entityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConfigurable() {
		return configurableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConfigurable_Configuration() {
		return (EReference)configurableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSupplier() {
		return supplierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSupplier_Implementation() {
		return (EAttribute)supplierEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getResource() {
		return resourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getResource_Location() {
		return (EAttribute)resourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getResource_Interpolate() {
		return (EAttribute)resourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getReference() {
		return referenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReference_Target() {
		return (EReference)referenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTypedElement() {
		return typedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTypedElement_Type() {
		return (EAttribute)typedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTypedElement_Required() {
		return (EAttribute)typedElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValue() {
		return valueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValue_Value() {
		return (EAttribute)valueEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValue_Interpolate() {
		return (EAttribute)valueEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNull() {
		return nullEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOperation() {
		return operationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperation_Arguments() {
		return (EReference)operationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getArray() {
		return arrayEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getArray_Elements() {
		return (EReference)arrayEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEntry() {
		return entryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEntry_Enabled() {
		return (EAttribute)entryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTypedEntry() {
		return typedEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSupplierEntry() {
		return supplierEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMap() {
		return mapEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMap_Entries() {
		return (EReference)mapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProperty() {
		return propertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFunction() {
		return functionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getList() {
		return listEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getObject() {
		return objectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHttpCall() {
		return httpCallEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHttpCall_Url() {
		return (EAttribute)httpCallEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHttpCall_Method() {
		return (EAttribute)httpCallEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getHttpCall_Headers() {
		return (EReference)httpCallEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHttpCall_ConnectTimeout() {
		return (EAttribute)httpCallEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHttpCall_ReadTimeout() {
		return (EAttribute)httpCallEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHttpCall_SuccessCode() {
		return (EAttribute)httpCallEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getHttpCall_Body() {
		return (EReference)httpCallEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRestOperation() {
		return restOperationEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRestOperation_Arguments() {
		return (EReference)restOperationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRestFunction() {
		return restFunctionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHtml() {
		return htmlEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHtml_Content() {
		return (EAttribute)htmlEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getHtml_Interpolate() {
		return (EAttribute)htmlEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getHttpMethod() {
		return httpMethodEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NcoreFactory getNcoreFactory() {
		return (NcoreFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		modelElementEClass = createEClass(MODEL_ELEMENT);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__TITLE);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__DESCRIPTION);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);

		entityEClass = createEClass(ENTITY);
		createEAttribute(entityEClass, ENTITY__ID);

		configurableEClass = createEClass(CONFIGURABLE);
		createEReference(configurableEClass, CONFIGURABLE__CONFIGURATION);

		typedElementEClass = createEClass(TYPED_ELEMENT);
		createEAttribute(typedElementEClass, TYPED_ELEMENT__TYPE);
		createEAttribute(typedElementEClass, TYPED_ELEMENT__REQUIRED);

		supplierEClass = createEClass(SUPPLIER);
		createEAttribute(supplierEClass, SUPPLIER__IMPLEMENTATION);

		resourceEClass = createEClass(RESOURCE);
		createEAttribute(resourceEClass, RESOURCE__LOCATION);
		createEAttribute(resourceEClass, RESOURCE__INTERPOLATE);

		referenceEClass = createEClass(REFERENCE);
		createEReference(referenceEClass, REFERENCE__TARGET);

		valueEClass = createEClass(VALUE);
		createEAttribute(valueEClass, VALUE__VALUE);
		createEAttribute(valueEClass, VALUE__INTERPOLATE);

		nullEClass = createEClass(NULL);

		operationEClass = createEClass(OPERATION);
		createEReference(operationEClass, OPERATION__ARGUMENTS);

		arrayEClass = createEClass(ARRAY);
		createEReference(arrayEClass, ARRAY__ELEMENTS);

		entryEClass = createEClass(ENTRY);
		createEAttribute(entryEClass, ENTRY__ENABLED);

		typedEntryEClass = createEClass(TYPED_ENTRY);

		supplierEntryEClass = createEClass(SUPPLIER_ENTRY);

		mapEClass = createEClass(MAP);
		createEReference(mapEClass, MAP__ENTRIES);

		propertyEClass = createEClass(PROPERTY);

		functionEClass = createEClass(FUNCTION);

		listEClass = createEClass(LIST);

		objectEClass = createEClass(OBJECT);

		httpCallEClass = createEClass(HTTP_CALL);
		createEAttribute(httpCallEClass, HTTP_CALL__URL);
		createEAttribute(httpCallEClass, HTTP_CALL__METHOD);
		createEReference(httpCallEClass, HTTP_CALL__HEADERS);
		createEAttribute(httpCallEClass, HTTP_CALL__CONNECT_TIMEOUT);
		createEAttribute(httpCallEClass, HTTP_CALL__READ_TIMEOUT);
		createEAttribute(httpCallEClass, HTTP_CALL__SUCCESS_CODE);
		createEReference(httpCallEClass, HTTP_CALL__BODY);

		restOperationEClass = createEClass(REST_OPERATION);
		createEReference(restOperationEClass, REST_OPERATION__ARGUMENTS);

		restFunctionEClass = createEClass(REST_FUNCTION);

		htmlEClass = createEClass(HTML);
		createEAttribute(htmlEClass, HTML__CONTENT);
		createEAttribute(htmlEClass, HTML__INTERPOLATE);

		// Create enums
		httpMethodEEnum = createEEnum(HTTP_METHOD);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		namedElementEClass.getESuperTypes().add(this.getModelElement());
		entityEClass.getESuperTypes().add(this.getModelElement());
		typedElementEClass.getESuperTypes().add(this.getModelElement());
		supplierEClass.getESuperTypes().add(this.getTypedElement());
		resourceEClass.getESuperTypes().add(this.getTypedElement());
		referenceEClass.getESuperTypes().add(this.getModelElement());
		valueEClass.getESuperTypes().add(this.getSupplier());
		nullEClass.getESuperTypes().add(this.getTypedElement());
		operationEClass.getESuperTypes().add(this.getSupplier());
		arrayEClass.getESuperTypes().add(this.getModelElement());
		entryEClass.getESuperTypes().add(this.getNamedElement());
		typedEntryEClass.getESuperTypes().add(this.getTypedElement());
		typedEntryEClass.getESuperTypes().add(this.getEntry());
		supplierEntryEClass.getESuperTypes().add(this.getSupplier());
		supplierEntryEClass.getESuperTypes().add(this.getEntry());
		mapEClass.getESuperTypes().add(this.getModelElement());
		propertyEClass.getESuperTypes().add(this.getValue());
		propertyEClass.getESuperTypes().add(this.getEntry());
		functionEClass.getESuperTypes().add(this.getOperation());
		functionEClass.getESuperTypes().add(this.getEntry());
		listEClass.getESuperTypes().add(this.getArray());
		listEClass.getESuperTypes().add(this.getEntry());
		objectEClass.getESuperTypes().add(this.getMap());
		objectEClass.getESuperTypes().add(this.getEntry());
		httpCallEClass.getESuperTypes().add(this.getModelElement());
		restOperationEClass.getESuperTypes().add(this.getHttpCall());
		restFunctionEClass.getESuperTypes().add(this.getRestOperation());
		restFunctionEClass.getESuperTypes().add(this.getEntry());
		htmlEClass.getESuperTypes().add(this.getSupplier());

		// Initialize classes, features, and operations; add parameters
		initEClass(modelElementEClass, ModelElement.class, "ModelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelElement_Title(), ecorePackage.getEString(), "title", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 1, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(entityEClass, Entity.class, "Entity", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEntity_Id(), ecorePackage.getEString(), "id", null, 0, 1, Entity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(configurableEClass, Configurable.class, "Configurable", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConfigurable_Configuration(), ecorePackage.getEObject(), null, "configuration", null, 0, -1, Configurable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typedElementEClass, TypedElement.class, "TypedElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTypedElement_Type(), ecorePackage.getEString(), "type", null, 0, 1, TypedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTypedElement_Required(), ecorePackage.getEBoolean(), "required", null, 0, 1, TypedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(supplierEClass, Supplier.class, "Supplier", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSupplier_Implementation(), ecorePackage.getEString(), "implementation", null, 0, 1, Supplier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceEClass, Resource.class, "Resource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResource_Location(), ecorePackage.getEString(), "location", null, 1, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResource_Interpolate(), ecorePackage.getEBoolean(), "interpolate", null, 0, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referenceEClass, Reference.class, "Reference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getReference_Target(), ecorePackage.getEObject(), null, "target", null, 1, 1, Reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(valueEClass, Value.class, "Value", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getValue_Value(), ecorePackage.getEString(), "value", null, 0, 1, Value.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getValue_Interpolate(), ecorePackage.getEBoolean(), "interpolate", "true", 0, 1, Value.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nullEClass, Null.class, "Null", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(operationEClass, Operation.class, "Operation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOperation_Arguments(), ecorePackage.getEObject(), null, "arguments", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(arrayEClass, Array.class, "Array", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getArray_Elements(), ecorePackage.getEObject(), null, "elements", null, 0, -1, Array.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(entryEClass, Entry.class, "Entry", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getEntry_Enabled(), ecorePackage.getEBoolean(), "enabled", "true", 0, 1, Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(typedEntryEClass, TypedEntry.class, "TypedEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(supplierEntryEClass, SupplierEntry.class, "SupplierEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(mapEClass, Map.class, "Map", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMap_Entries(), this.getEntry(), null, "entries", null, 0, -1, Map.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyEClass, Property.class, "Property", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(functionEClass, Function.class, "Function", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(listEClass, List.class, "List", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(objectEClass, org.nasdanika.ncore.Object.class, "Object", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(httpCallEClass, HttpCall.class, "HttpCall", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHttpCall_Url(), ecorePackage.getEString(), "url", null, 1, 1, HttpCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHttpCall_Method(), this.getHttpMethod(), "method", "GET", 0, 1, HttpCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHttpCall_Headers(), this.getEntry(), null, "headers", null, 0, -1, HttpCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHttpCall_ConnectTimeout(), ecorePackage.getEInt(), "connectTimeout", "60", 0, 1, HttpCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHttpCall_ReadTimeout(), ecorePackage.getEInt(), "readTimeout", "60", 0, 1, HttpCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHttpCall_SuccessCode(), ecorePackage.getEInt(), "successCode", "200", 0, 1, HttpCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getHttpCall_Body(), ecorePackage.getEObject(), null, "body", null, 0, -1, HttpCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(restOperationEClass, RestOperation.class, "RestOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRestOperation_Arguments(), this.getEntry(), null, "arguments", null, 0, -1, RestOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(restFunctionEClass, RestFunction.class, "RestFunction", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(htmlEClass, Html.class, "Html", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getHtml_Content(), ecorePackage.getEString(), "content", null, 0, 1, Html.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getHtml_Interpolate(), ecorePackage.getEBoolean(), "interpolate", "true", 0, 1, Html.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(httpMethodEEnum, HttpMethod.class, "HttpMethod");
		addEEnumLiteral(httpMethodEEnum, HttpMethod.GET);
		addEEnumLiteral(httpMethodEEnum, HttpMethod.POST);
		addEEnumLiteral(httpMethodEEnum, HttpMethod.PUT);
		addEEnumLiteral(httpMethodEEnum, HttpMethod.DELETE);
		addEEnumLiteral(httpMethodEEnum, HttpMethod.PATCH);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
		// urn:org.nasdanika
		createUrnorgAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/GenModel</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGenModelAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/GenModel";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "documentation", "Nasdanika core model containing common classes"
		   });
		addAnnotation
		  (modelElementEClass,
		   source,
		   new String[] {
			   "documentation", "Model element with a description."
		   });
		addAnnotation
		  (getModelElement_Title(),
		   source,
		   new String[] {
			   "documentation", "If title is set it is used by default as element text/label."
		   });
		addAnnotation
		  (getModelElement_Description(),
		   source,
		   new String[] {
			   "documentation", "Model element description in markdown."
		   });
		addAnnotation
		  (namedElementEClass,
		   source,
		   new String[] {
			   "documentation", "Element with a name."
		   });
		addAnnotation
		  (getNamedElement_Name(),
		   source,
		   new String[] {
			   "documentation", "Element name."
		   });
		addAnnotation
		  (entityEClass,
		   source,
		   new String[] {
			   "documentation", "Element with a unique auto-generated identity."
		   });
		addAnnotation
		  (getEntity_Id(),
		   source,
		   new String[] {
			   "documentation", "A unique auto-generated entity identifier."
		   });
		addAnnotation
		  (configurableEClass,
		   source,
		   new String[] {
			   "documentation", "Model element which contains configuration entries."
		   });
		addAnnotation
		  (getConfigurable_Configuration(),
		   source,
		   new String[] {
			   "documentation", "Configuration entries."
		   });
		addAnnotation
		  (typedElementEClass,
		   source,
		   new String[] {
			   "documentation", "Typed element is a supplier factory with specified return type, e.g. ``java.lang.Integer``. Typed element gets its result from the context\'s service with the specified type. The primary pupose of of the typed element is to serve as a specification/description."
		   });
		addAnnotation
		  (getTypedElement_Type(),
		   source,
		   new String[] {
			   "documentation", "Fully qualified Java type name. "
		   });
		addAnnotation
		  (getTypedElement_Required(),
		   source,
		   new String[] {
			   "documentation", "Indicates that the element result can not be null."
		   });
		addAnnotation
		  (supplierEClass,
		   source,
		   new String[] {
			   "documentation", "Supplier is a typed element which computes it result using an implementation operation - constructor or method. Supplier is equivalent to an operation without arguments."
		   });
		addAnnotation
		  (getSupplier_Implementation(),
		   source,
		   new String[] {
			   "documentation", "Supplier implementation. If empty, type\'s constructor is used as a provider.\n\nSupplier implementation can be defined as follows:\n\n* Fully qualified class name, e.g. ``java.lang.Integer``. If both type and implementation as defined then the implementation must be a subclass/implementation of type or there should be a Converter service in the context which converts implementation into type. An instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` or the default contructor if there is no context constructor.\n* Method reference using ``::`` as a separator between the fully qualified class name and the method name. This definition can be used if the type is a functional interface with a single method. If the method is not static then an instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` or the default contructor if there is no context constructor.\n* Provider reference using ``->`` as a separator between the fully qualified class name and the provider method. If the method is static then a method with a given name which taks a single Context argument or not arguments is used. Otherwise an instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` or the default contructor if there is no context constructor and a no-argument method with a given name is used.\n"
		   });
		addAnnotation
		  (resourceEClass,
		   source,
		   new String[] {
			   "documentation", "Resource loads content from URL and converts to the element type. If type is blank then it supplies the URL as is. "
		   });
		addAnnotation
		  (getResource_Location(),
		   source,
		   new String[] {
			   "documentation", "Resource location resolved relative to the containing model file (resource)."
		   });
		addAnnotation
		  (getResource_Interpolate(),
		   source,
		   new String[] {
			   "documentation", "If this flag is set to true and the type is ``java.lang.String`` then the string is interpolated. This flag is ignored otherwise."
		   });
		addAnnotation
		  (referenceEClass,
		   source,
		   new String[] {
			   "documentation", "Reference delegates its functionality to its target. It is primarily used to build multi-resource (file) models."
		   });
		addAnnotation
		  (getReference_Target(),
		   source,
		   new String[] {
			   "documentation", "Reference to the target supplier factory."
		   });
		addAnnotation
		  (valueEClass,
		   source,
		   new String[] {
			   "documentation", "Value computes its result from text. \n\nValue implementation can be defined as follows:\n\n* Fully qualified class name, e.g. ``java.lang.Integer``. An instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` and ``java.lang.String``, just ``java.lang.String``.\n* Method reference using ``::`` as a separator between the fully qualified class name and the method name. This definition can be used if the type is a functional interface with a single method. If the method is not static then an instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` and ``java.lang.String``, or just ``java.lang.String``.\n* Provider reference using ``->`` as a separator between the fully qualified class name and the provider method. If the method is static then it shall take Context and String or just String. Otherwise an instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` and ``java.lang.String``, or just ``java.lang.String``.\n\nWhen implementation is specified, value is equivalent to an operation with a single String argument."
		   });
		addAnnotation
		  (getValue_Value(),
		   source,
		   new String[] {
			   "documentation", "Textual representation of the value. If interpolate is ``true`` then the value is interpolated in the context. If type and implementation are empty value is returned as is. \nIf type is specified and the result is not of that type, then the result is converted to the type using the context converter service.\n"
		   });
		addAnnotation
		  (getValue_Interpolate(),
		   source,
		   new String[] {
			   "documentation", "If ``true`` (default) the value is interpolated."
		   });
		addAnnotation
		  (nullEClass,
		   source,
		   new String[] {
			   "documentation", "Typed null can be used as an operation argument."
		   });
		addAnnotation
		  (operationEClass,
		   source,
		   new String[] {
			   "documentation", "Operation computes its result the operation arguments. \n\nOperation implementation can be defined as follows:\n\n* Fully qualified class name, e.g. ``java.lang.Integer``. An instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` and the results of the arguments, or just the results of the arguments.\n* Method reference using ``::`` as a separator between the fully qualified class name and the method name. This definition can be used if the type is a functional interface with a single method. If the method is not static then an instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` and the results of the arguments, or just the results of the arguments.\n* Provider reference using ``->`` as a separator between the fully qualified class name and the provider method. If the method not static then its parameters shall be compatible with Context and as the first argument and then operation arguments, or just with operation arguments without context. Otherwise an instance of the implementation class is constructed using a contructor which takes ``org.nasdanika.common.Context`` and the results of the arguments, or just the results of the arguments and the method shall not take any arguments.\n"
		   });
		addAnnotation
		  (getOperation_Arguments(),
		   source,
		   new String[] {
			   "documentation", "Operation arguments."
		   });
		addAnnotation
		  (arrayEClass,
		   source,
		   new String[] {
			   "documentation", "A sequence of elements."
		   });
		addAnnotation
		  (getArray_Elements(),
		   source,
		   new String[] {
			   "documentation", "Array elements."
		   });
		addAnnotation
		  (entryEClass,
		   source,
		   new String[] {
			   "documentation", "Supplier factory with a name."
		   });
		addAnnotation
		  (getEntry_Enabled(),
		   source,
		   new String[] {
			   "documentation", "If this attribute is set to ``false`` the entry is \"commented out\" - not included into its container result."
		   });
		addAnnotation
		  (typedEntryEClass,
		   source,
		   new String[] {
			   "documentation", "Typed element with a name. Typed entry gets its result from the context\'s properti with the specified name converted to the specified type. The primary pupose of of the typed entry is to serve as a specification/description."
		   });
		addAnnotation
		  (supplierEntryEClass,
		   source,
		   new String[] {
			   "documentation", "Supplier with a name."
		   });
		addAnnotation
		  (mapEClass,
		   source,
		   new String[] {
			   "documentation", "A collection of entries mapping names to results."
		   });
		addAnnotation
		  (getMap_Entries(),
		   source,
		   new String[] {
			   "documentation", "Map entries."
		   });
		addAnnotation
		  (propertyEClass,
		   source,
		   new String[] {
			   "documentation", "Property is a named value."
		   });
		addAnnotation
		  (functionEClass,
		   source,
		   new String[] {
			   "documentation", "Function is a named operation."
		   });
		addAnnotation
		  (listEClass,
		   source,
		   new String[] {
			   "documentation", "List is a named array."
		   });
		addAnnotation
		  (objectEClass,
		   source,
		   new String[] {
			   "documentation", "Object is a named map."
		   });
		addAnnotation
		  (httpCallEClass,
		   source,
		   new String[] {
			   "documentation", "Makes an HTTP Call. Converts result to Map/List for ``application/json`` content type, to text for ``text/...`` content types. Returns a byte array otherwise."
		   });
		addAnnotation
		  (getHttpCall_Url(),
		   source,
		   new String[] {
			   "documentation", "URL resolved relative to the model."
		   });
		addAnnotation
		  (getHttpCall_ConnectTimeout(),
		   source,
		   new String[] {
			   "documentation", "Connect timeout in seconds"
		   });
		addAnnotation
		  (getHttpCall_ReadTimeout(),
		   source,
		   new String[] {
			   "documentation", "Read timeout in seconds"
		   });
		addAnnotation
		  (getHttpCall_SuccessCode(),
		   source,
		   new String[] {
			   "documentation", "HTTP response code indicating success."
		   });
		addAnnotation
		  (restOperationEClass,
		   source,
		   new String[] {
			   "documentation", "HTTP Call with JSON payload constructed from arguments."
		   });
		addAnnotation
		  (getRestOperation_Arguments(),
		   source,
		   new String[] {
			   "documentation", "Operation arguments."
		   });
		addAnnotation
		  (restFunctionEClass,
		   source,
		   new String[] {
			   "documentation", "REST function is a named REST operation."
		   });
		addAnnotation
		  (htmlEClass,
		   source,
		   new String[] {
			   "documentation", "HTML text."
		   });
		addAnnotation
		  (getHtml_Content(),
		   source,
		   new String[] {
			   "documentation", "HTML content"
		   });
		addAnnotation
		  (getHtml_Interpolate(),
		   source,
		   new String[] {
			   "documentation", "If ``true`` (default) the HTML content is interpolated."
		   });
	}

	/**
	 * Initializes the annotations for <b>urn:org.nasdanika</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createUrnorgAnnotations() {
		String source = "urn:org.nasdanika";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "category", "General"
		   });
		addAnnotation
		  (getHtml_Content(),
		   source,
		   new String[] {
			   "content-type", "text/html"
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";
		addAnnotation
		  (referenceEClass,
		   source,
		   new String[] {
			   "constraints", "target"
		   });
		addAnnotation
		  (mapEClass,
		   source,
		   new String[] {
			   "constraints", "entries"
		   });
	}

} //NcorePackageImpl
