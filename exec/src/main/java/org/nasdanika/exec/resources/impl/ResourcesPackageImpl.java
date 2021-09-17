/**
 */
package org.nasdanika.exec.resources.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nasdanika.exec.ExecPackage;

import org.nasdanika.exec.content.ContentPackage;

import org.nasdanika.exec.content.impl.ContentPackageImpl;

import org.nasdanika.exec.impl.ExecPackageImpl;

import org.nasdanika.exec.resources.File;
import org.nasdanika.exec.resources.ReconcileAction;
import org.nasdanika.exec.resources.Resource;
import org.nasdanika.exec.resources.ResourcesFactory;
import org.nasdanika.exec.resources.ResourcesPackage;
import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ResourcesPackageImpl extends EPackageImpl implements ResourcesPackage {
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
	private EClass containerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum reconcileActionEEnum = null;

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
	 * @see org.nasdanika.exec.resources.ResourcesPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ResourcesPackageImpl() {
		super(eNS_URI, ResourcesFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ResourcesPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ResourcesPackage init() {
		if (isInited) return (ResourcesPackage)EPackage.Registry.INSTANCE.getEPackage(ResourcesPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredResourcesPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ResourcesPackageImpl theResourcesPackage = registeredResourcesPackage instanceof ResourcesPackageImpl ? (ResourcesPackageImpl)registeredResourcesPackage : new ResourcesPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		NcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ExecPackage.eNS_URI);
		ExecPackageImpl theExecPackage = (ExecPackageImpl)(registeredPackage instanceof ExecPackageImpl ? registeredPackage : ExecPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ContentPackage.eNS_URI);
		ContentPackageImpl theContentPackage = (ContentPackageImpl)(registeredPackage instanceof ContentPackageImpl ? registeredPackage : ContentPackage.eINSTANCE);

		// Create package meta-data objects
		theResourcesPackage.createPackageContents();
		theExecPackage.createPackageContents();
		theContentPackage.createPackageContents();

		// Initialize created meta-data
		theResourcesPackage.initializePackageContents();
		theExecPackage.initializePackageContents();
		theContentPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theResourcesPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ResourcesPackage.eNS_URI, theResourcesPackage);
		return theResourcesPackage;
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
	public EAttribute getResource_Name() {
		return (EAttribute)resourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResource_Contents() {
		return (EReference)resourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getResource_ReconcileAction() {
		return (EAttribute)resourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getResource_Merger() {
		return (EReference)resourceEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContainer() {
		return containerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getContainer__GetContainer__String() {
		return containerEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getContainer__GetFile__String() {
		return containerEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getContainer__Find__String() {
		return containerEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFile() {
		return fileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getReconcileAction() {
		return reconcileActionEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourcesFactory getResourcesFactory() {
		return (ResourcesFactory)getEFactoryInstance();
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
		resourceEClass = createEClass(RESOURCE);
		createEAttribute(resourceEClass, RESOURCE__NAME);
		createEReference(resourceEClass, RESOURCE__CONTENTS);
		createEAttribute(resourceEClass, RESOURCE__RECONCILE_ACTION);
		createEReference(resourceEClass, RESOURCE__MERGER);

		containerEClass = createEClass(CONTAINER);
		createEOperation(containerEClass, CONTAINER___GET_CONTAINER__STRING);
		createEOperation(containerEClass, CONTAINER___GET_FILE__STRING);
		createEOperation(containerEClass, CONTAINER___FIND__STRING);

		fileEClass = createEClass(FILE);

		// Create enums
		reconcileActionEEnum = createEEnum(RECONCILE_ACTION);
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

		// Obtain other dependent packages
		NcorePackage theNcorePackage = (NcorePackage)EPackage.Registry.INSTANCE.getEPackage(NcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		resourceEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		containerEClass.getESuperTypes().add(this.getResource());
		fileEClass.getESuperTypes().add(this.getResource());

		// Initialize classes, features, and operations; add parameters
		initEClass(resourceEClass, Resource.class, "Resource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResource_Name(), ecorePackage.getEString(), "name", null, 1, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResource_Contents(), ecorePackage.getEObject(), null, "contents", null, 0, -1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResource_ReconcileAction(), this.getReconcileAction(), "reconcileAction", "Overwrite", 0, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResource_Merger(), ecorePackage.getEObject(), null, "merger", null, 0, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(containerEClass, org.nasdanika.exec.resources.Container.class, "Container", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		EOperation op = initEOperation(getContainer__GetContainer__String(), this.getContainer(), "getContainer", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "path", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getContainer__GetFile__String(), this.getFile(), "getFile", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "path", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getContainer__Find__String(), this.getResource(), "find", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "path", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(fileEClass, File.class, "File", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(reconcileActionEEnum, ReconcileAction.class, "ReconcileAction");
		addEEnumLiteral(reconcileActionEEnum, ReconcileAction.APPEND);
		addEEnumLiteral(reconcileActionEEnum, ReconcileAction.CANCEL);
		addEEnumLiteral(reconcileActionEEnum, ReconcileAction.KEEP);
		addEEnumLiteral(reconcileActionEEnum, ReconcileAction.MERGE);
		addEEnumLiteral(reconcileActionEEnum, ReconcileAction.OVERWRITE);

		// Create annotations
		// urn:org.nasdanika
		createUrnorgAnnotations();
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
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
			   "documentation-reference", "doc/resources/package-summary.md"
		   });
		addAnnotation
		  (resourceEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/resources/resource.md"
		   });
		addAnnotation
		  (containerEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/resources/container.md"
		   });
		addAnnotation
		  (fileEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/resources/file.md"
		   });
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
		  (getResource_Name(),
		   source,
		   new String[] {
			   "documentation", "Resource name. Interpolated."
		   });
		addAnnotation
		  (getResource_Contents(),
		   source,
		   new String[] {
			   "documentation", "Resource contents. File contents elements are adapted to ``SupplierFactory`` and produced ``InputStream``s are contcatenated. Container contents elements are adapted to ConsumerFactory and the container is passed to their ``execute`` method."
		   });
		addAnnotation
		  (getResource_ReconcileAction(),
		   source,
		   new String[] {
			   "documentation", "Reconcile action - what to do if a resource with the same name already exists. Defalut is ``Overwrite``."
		   });
		addAnnotation
		  (getResource_Merger(),
		   source,
		   new String[] {
			   "documentation", "Merger for ``Merge`` reconcile action. Adapted to ``org.nasdanika.common.resources.Merger`` to execute actual merge."
		   });
		addAnnotation
		  (reconcileActionEEnum,
		   source,
		   new String[] {
			   "documentation", "Defines an action to take when a resource with the same name already exists."
		   });
		addAnnotation
		  (reconcileActionEEnum.getELiterals().get(0),
		   source,
		   new String[] {
			   "documentation", "Appends new content to the existing content. For containers it means that new resources will be placed in the container next to the existing resources. For files it means that the new content will be appended after the existing content."
		   });
		addAnnotation
		  (reconcileActionEEnum.getELiterals().get(1),
		   source,
		   new String[] {
			   "documentation", "Cancels execution if resource already exists."
		   });
		addAnnotation
		  (reconcileActionEEnum.getELiterals().get(2),
		   source,
		   new String[] {
			   "documentation", "Keeps the existing resource intact."
		   });
		addAnnotation
		  (reconcileActionEEnum.getELiterals().get(3),
		   source,
		   new String[] {
			   "documentation", "Merges old and new content of a file using a built-in or provided ``org.nasdanika.common.resources.Merger``. Merger is obtained from ``merger`` reference. For containers Merge is the same as Append."
		   });
		addAnnotation
		  (reconcileActionEEnum.getELiterals().get(4),
		   source,
		   new String[] {
			   "documentation", "Overwrites existing resource. For containers - deletes their contents."
		   });
	}

} //ResourcesPackageImpl
