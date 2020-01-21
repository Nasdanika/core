/**
 */
package org.nasdanika.sirius.tree;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

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
 * @see org.nasdanika.sirius.tree.TreeFactory
 * @model kind="package"
 * @generated
 */
public interface TreePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "tree";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "urn:org.nasdanika.sirius.tree";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.sirius.tree";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TreePackage eINSTANCE = org.nasdanika.sirius.tree.impl.TreePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.sirius.tree.impl.AdapterFactoryTreeImpl <em>Adapter Factory Tree</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.sirius.tree.impl.AdapterFactoryTreeImpl
	 * @see org.nasdanika.sirius.tree.impl.TreePackageImpl#getAdapterFactoryTree()
	 * @generated
	 */
	int ADAPTER_FACTORY_TREE = 0;

	/**
	 * The feature id for the '<em><b>Uid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE__UID = ViewpointPackage.DREPRESENTATION__UID;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE__EANNOTATIONS = ViewpointPackage.DREPRESENTATION__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Owned Representation Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE__OWNED_REPRESENTATION_ELEMENTS = ViewpointPackage.DREPRESENTATION__OWNED_REPRESENTATION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Representation Elements</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE__REPRESENTATION_ELEMENTS = ViewpointPackage.DREPRESENTATION__REPRESENTATION_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Owned Annotation Entries</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE__OWNED_ANNOTATION_ENTRIES = ViewpointPackage.DREPRESENTATION__OWNED_ANNOTATION_ENTRIES;

	/**
	 * The feature id for the '<em><b>Ui State</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE__UI_STATE = ViewpointPackage.DREPRESENTATION__UI_STATE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE__NAME = ViewpointPackage.DREPRESENTATION__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE__DOCUMENTATION = ViewpointPackage.DREPRESENTATION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE__TARGET = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE__DESCRIPTION = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Adapter Factory Tree</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE_FEATURE_COUNT = ViewpointPackage.DREPRESENTATION_FEATURE_COUNT + 2;

//	/**
//	 * The operation id for the '<em>Get DAnnotation</em>' operation.
//	 * <!-- begin-user-doc -->
//	 * <!-- end-user-doc -->
//	 * @generated
//	 * @ordered
//	 */
//	int ADAPTER_FACTORY_TREE___GET_DANNOTATION__STRING = ViewpointPackage.DREPRESENTATION___GET_DANNOTATION__STRING;
//
//	/**
//	 * The number of operations of the '<em>Adapter Factory Tree</em>' class.
//	 * <!-- begin-user-doc -->
//	 * <!-- end-user-doc -->
//	 * @generated
//	 * @ordered
//	 */
//	int ADAPTER_FACTORY_TREE_OPERATION_COUNT = ViewpointPackage.DREPRESENTATION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.sirius.tree.impl.AdapterFactoryTreeDescriptionImpl <em>Adapter Factory Tree Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.sirius.tree.impl.AdapterFactoryTreeDescriptionImpl
	 * @see org.nasdanika.sirius.tree.impl.TreePackageImpl#getAdapterFactoryTreeDescription()
	 * @generated
	 */
	int ADAPTER_FACTORY_TREE_DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE_DESCRIPTION__DOCUMENTATION = DescriptionPackage.REPRESENTATION_DESCRIPTION__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>End User Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE_DESCRIPTION__END_USER_DOCUMENTATION = DescriptionPackage.REPRESENTATION_DESCRIPTION__END_USER_DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE_DESCRIPTION__NAME = DescriptionPackage.REPRESENTATION_DESCRIPTION__NAME;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE_DESCRIPTION__LABEL = DescriptionPackage.REPRESENTATION_DESCRIPTION__LABEL;

	/**
	 * The feature id for the '<em><b>Title Expression</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE_DESCRIPTION__TITLE_EXPRESSION = DescriptionPackage.REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION;

	/**
	 * The feature id for the '<em><b>Initialisation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE_DESCRIPTION__INITIALISATION = DescriptionPackage.REPRESENTATION_DESCRIPTION__INITIALISATION;

	/**
	 * The feature id for the '<em><b>Metamodel</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE_DESCRIPTION__METAMODEL = DescriptionPackage.REPRESENTATION_DESCRIPTION__METAMODEL;

	/**
	 * The feature id for the '<em><b>Show On Startup</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE_DESCRIPTION__SHOW_ON_STARTUP = DescriptionPackage.REPRESENTATION_DESCRIPTION__SHOW_ON_STARTUP;

	/**
	 * The number of structural features of the '<em>Adapter Factory Tree Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTER_FACTORY_TREE_DESCRIPTION_FEATURE_COUNT = DescriptionPackage.REPRESENTATION_DESCRIPTION_FEATURE_COUNT
			+ 0;

//	/**
//	 * The number of operations of the '<em>Adapter Factory Tree Description</em>' class.
//	 * <!-- begin-user-doc -->
//	 * <!-- end-user-doc -->
//	 * @generated
//	 * @ordered
//	 */
//	int ADAPTER_FACTORY_TREE_DESCRIPTION_OPERATION_COUNT = DescriptionPackage.REPRESENTATION_DESCRIPTION_OPERATION_COUNT
//			+ 0;

	/**
	 * Returns the meta object for class '{@link org.nasdanika.sirius.tree.AdapterFactoryTree <em>Adapter Factory Tree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Adapter Factory Tree</em>'.
	 * @see org.nasdanika.sirius.tree.AdapterFactoryTree
	 * @generated
	 */
	EClass getAdapterFactoryTree();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.sirius.tree.AdapterFactoryTree#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Description</em>'.
	 * @see org.nasdanika.sirius.tree.AdapterFactoryTree#getDescription()
	 * @see #getAdapterFactoryTree()
	 * @generated
	 */
	EReference getAdapterFactoryTree_Description();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.sirius.tree.AdapterFactoryTreeDescription <em>Adapter Factory Tree Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Adapter Factory Tree Description</em>'.
	 * @see org.nasdanika.sirius.tree.AdapterFactoryTreeDescription
	 * @generated
	 */
	EClass getAdapterFactoryTreeDescription();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TreeFactory getTreeFactory();

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
		 * The meta object literal for the '{@link org.nasdanika.sirius.tree.impl.AdapterFactoryTreeImpl <em>Adapter Factory Tree</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.sirius.tree.impl.AdapterFactoryTreeImpl
		 * @see org.nasdanika.sirius.tree.impl.TreePackageImpl#getAdapterFactoryTree()
		 * @generated
		 */
		EClass ADAPTER_FACTORY_TREE = eINSTANCE.getAdapterFactoryTree();
		/**
		 * The meta object literal for the '<em><b>Description</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ADAPTER_FACTORY_TREE__DESCRIPTION = eINSTANCE.getAdapterFactoryTree_Description();
		/**
		 * The meta object literal for the '{@link org.nasdanika.sirius.tree.impl.AdapterFactoryTreeDescriptionImpl <em>Adapter Factory Tree Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.sirius.tree.impl.AdapterFactoryTreeDescriptionImpl
		 * @see org.nasdanika.sirius.tree.impl.TreePackageImpl#getAdapterFactoryTreeDescription()
		 * @generated
		 */
		EClass ADAPTER_FACTORY_TREE_DESCRIPTION = eINSTANCE.getAdapterFactoryTreeDescription();

	}

} //TreePackage
