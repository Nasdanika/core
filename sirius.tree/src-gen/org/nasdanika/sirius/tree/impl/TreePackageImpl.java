/**
 */
package org.nasdanika.sirius.tree.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.sirius.viewpoint.ViewpointPackage;

import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.nasdanika.sirius.tree.AdapterFactoryTree;
import org.nasdanika.sirius.tree.AdapterFactoryTreeDescription;
import org.nasdanika.sirius.tree.TreeFactory;
import org.nasdanika.sirius.tree.TreePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TreePackageImpl extends EPackageImpl implements TreePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adapterFactoryTreeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adapterFactoryTreeDescriptionEClass = null;

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
	 * @see org.nasdanika.sirius.tree.TreePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TreePackageImpl() {
		super(eNS_URI, TreeFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link TreePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TreePackage init() {
		if (isInited)
			return (TreePackage) EPackage.Registry.INSTANCE.getEPackage(TreePackage.eNS_URI);

		// Obtain or create and register package
		Object registeredTreePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		TreePackageImpl theTreePackage = registeredTreePackage instanceof TreePackageImpl
				? (TreePackageImpl) registeredTreePackage
				: new TreePackageImpl();

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();
		ViewpointPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theTreePackage.createPackageContents();

		// Initialize created meta-data
		theTreePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTreePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TreePackage.eNS_URI, theTreePackage);
		return theTreePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAdapterFactoryTree() {
		return adapterFactoryTreeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getAdapterFactoryTree_Description() {
		return (EReference) adapterFactoryTreeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAdapterFactoryTreeDescription() {
		return adapterFactoryTreeDescriptionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TreeFactory getTreeFactory() {
		return (TreeFactory) getEFactoryInstance();
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
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		adapterFactoryTreeEClass = createEClass(ADAPTER_FACTORY_TREE);
		createEReference(adapterFactoryTreeEClass, ADAPTER_FACTORY_TREE__DESCRIPTION);

		adapterFactoryTreeDescriptionEClass = createEClass(ADAPTER_FACTORY_TREE_DESCRIPTION);
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
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		ViewpointPackage theViewpointPackage = (ViewpointPackage) EPackage.Registry.INSTANCE
				.getEPackage(ViewpointPackage.eNS_URI);
		DescriptionPackage theDescriptionPackage = (DescriptionPackage) EPackage.Registry.INSTANCE
				.getEPackage(DescriptionPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		adapterFactoryTreeEClass.getESuperTypes().add(theViewpointPackage.getDRepresentation());
		adapterFactoryTreeEClass.getESuperTypes().add(theViewpointPackage.getDSemanticDecorator());
		adapterFactoryTreeDescriptionEClass.getESuperTypes().add(theDescriptionPackage.getRepresentationDescription());

		// Initialize classes, features, and operations; add parameters
		initEClass(adapterFactoryTreeEClass, AdapterFactoryTree.class, "AdapterFactoryTree", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAdapterFactoryTree_Description(), this.getAdapterFactoryTreeDescription(), null,
				"description", null, 0, 1, AdapterFactoryTree.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(adapterFactoryTreeDescriptionEClass, AdapterFactoryTreeDescription.class,
				"AdapterFactoryTreeDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //TreePackageImpl
