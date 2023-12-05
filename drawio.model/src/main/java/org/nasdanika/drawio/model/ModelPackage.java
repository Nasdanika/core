/**
 */
package org.nasdanika.drawio.model;

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
 * @see org.nasdanika.drawio.model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "ecore://nasdanika.org/core/drawio/model";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.drawio.model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = org.nasdanika.drawio.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.drawio.model.impl.DocumentImpl <em>Document</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.drawio.model.impl.DocumentImpl
	 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getDocument()
	 * @generated
	 */
	int DOCUMENT = 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__MARKERS = NcorePackage.MARKED__MARKERS;

	/**
	 * The feature id for the '<em><b>Pages</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__PAGES = NcorePackage.MARKED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__URI = NcorePackage.MARKED_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__SOURCE = NcorePackage.MARKED_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Document</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_FEATURE_COUNT = NcorePackage.MARKED_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Page By Name</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT___GET_PAGE_BY_NAME__STRING = NcorePackage.MARKED_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Page By Id</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT___GET_PAGE_BY_ID__STRING = NcorePackage.MARKED_OPERATION_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Model Element By Id</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT___GET_MODEL_ELEMENT_BY_ID__STRING = NcorePackage.MARKED_OPERATION_COUNT + 2;

	/**
	 * The number of operations of the '<em>Document</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_OPERATION_COUNT = NcorePackage.MARKED_OPERATION_COUNT + 3;


	/**
	 * The meta object id for the '{@link org.nasdanika.drawio.model.impl.PageImpl <em>Page</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.drawio.model.impl.PageImpl
	 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getPage()
	 * @generated
	 */
	int PAGE = 1;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__MARKERS = NcorePackage.MARKED__MARKERS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__ID = NcorePackage.MARKED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Model</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__MODEL = NcorePackage.MARKED_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__NAME = NcorePackage.MARKED_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Links</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE__LINKS = NcorePackage.MARKED_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_FEATURE_COUNT = NcorePackage.MARKED_FEATURE_COUNT + 4;

	/**
	 * The operation id for the '<em>Get Model Element By Id</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE___GET_MODEL_ELEMENT_BY_ID__STRING = NcorePackage.MARKED_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>Page</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PAGE_OPERATION_COUNT = NcorePackage.MARKED_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.nasdanika.drawio.model.impl.ModelImpl <em>Model</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.drawio.model.impl.ModelImpl
	 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getModel()
	 * @generated
	 */
	int MODEL = 2;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__MARKERS = NcorePackage.MARKED__MARKERS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL__ROOT = NcorePackage.MARKED_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_FEATURE_COUNT = NcorePackage.MARKED_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Model</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_OPERATION_COUNT = NcorePackage.MARKED_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.drawio.model.impl.ModelElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.drawio.model.impl.ModelElementImpl
	 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getModelElement()
	 * @generated
	 */
	int MODEL_ELEMENT = 3;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__MARKERS = NcorePackage.MARKED__MARKERS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__ID = NcorePackage.MARKED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__PROPERTIES = NcorePackage.MARKED_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__LABEL = NcorePackage.MARKED_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__LINK = NcorePackage.MARKED_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Linked Page</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__LINKED_PAGE = NcorePackage.MARKED_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Style</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__STYLE = NcorePackage.MARKED_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__TAGS = NcorePackage.MARKED_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__TOOLTIP = NcorePackage.MARKED_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__VISIBLE = NcorePackage.MARKED_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT_FEATURE_COUNT = NcorePackage.MARKED_FEATURE_COUNT + 9;

	/**
	 * The operation id for the '<em>Get Document</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT___GET_DOCUMENT = NcorePackage.MARKED_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get Page</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT___GET_PAGE = NcorePackage.MARKED_OPERATION_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Model Element By Id</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT___GET_MODEL_ELEMENT_BY_ID__STRING = NcorePackage.MARKED_OPERATION_COUNT + 2;

	/**
	 * The number of operations of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT_OPERATION_COUNT = NcorePackage.MARKED_OPERATION_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.nasdanika.drawio.model.impl.RootImpl <em>Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.drawio.model.impl.RootImpl
	 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getRoot()
	 * @generated
	 */
	int ROOT = 4;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__MARKERS = MODEL_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__ID = MODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__PROPERTIES = MODEL_ELEMENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__LABEL = MODEL_ELEMENT__LABEL;

	/**
	 * The feature id for the '<em><b>Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__LINK = MODEL_ELEMENT__LINK;

	/**
	 * The feature id for the '<em><b>Linked Page</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__LINKED_PAGE = MODEL_ELEMENT__LINKED_PAGE;

	/**
	 * The feature id for the '<em><b>Style</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__STYLE = MODEL_ELEMENT__STYLE;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__TAGS = MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__TOOLTIP = MODEL_ELEMENT__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__VISIBLE = MODEL_ELEMENT__VISIBLE;

	/**
	 * The feature id for the '<em><b>Layers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__LAYERS = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Document</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT___GET_DOCUMENT = MODEL_ELEMENT___GET_DOCUMENT;

	/**
	 * The operation id for the '<em>Get Page</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT___GET_PAGE = MODEL_ELEMENT___GET_PAGE;

	/**
	 * The operation id for the '<em>Get Model Element By Id</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT___GET_MODEL_ELEMENT_BY_ID__STRING = MODEL_ELEMENT___GET_MODEL_ELEMENT_BY_ID__STRING;

	/**
	 * The number of operations of the '<em>Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.drawio.model.impl.LayerImpl <em>Layer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.drawio.model.impl.LayerImpl
	 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getLayer()
	 * @generated
	 */
	int LAYER = 5;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__MARKERS = MODEL_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__ID = MODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__PROPERTIES = MODEL_ELEMENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__LABEL = MODEL_ELEMENT__LABEL;

	/**
	 * The feature id for the '<em><b>Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__LINK = MODEL_ELEMENT__LINK;

	/**
	 * The feature id for the '<em><b>Linked Page</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__LINKED_PAGE = MODEL_ELEMENT__LINKED_PAGE;

	/**
	 * The feature id for the '<em><b>Style</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__STYLE = MODEL_ELEMENT__STYLE;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__TAGS = MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__TOOLTIP = MODEL_ELEMENT__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__VISIBLE = MODEL_ELEMENT__VISIBLE;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER__ELEMENTS = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Layer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Document</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER___GET_DOCUMENT = MODEL_ELEMENT___GET_DOCUMENT;

	/**
	 * The operation id for the '<em>Get Page</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER___GET_PAGE = MODEL_ELEMENT___GET_PAGE;

	/**
	 * The operation id for the '<em>Get Model Element By Id</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER___GET_MODEL_ELEMENT_BY_ID__STRING = MODEL_ELEMENT___GET_MODEL_ELEMENT_BY_ID__STRING;

	/**
	 * The number of operations of the '<em>Layer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.drawio.model.impl.LayerElementImpl <em>Layer Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.drawio.model.impl.LayerElementImpl
	 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getLayerElement()
	 * @generated
	 */
	int LAYER_ELEMENT = 6;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_ELEMENT__MARKERS = MODEL_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_ELEMENT__ID = MODEL_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_ELEMENT__PROPERTIES = MODEL_ELEMENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_ELEMENT__LABEL = MODEL_ELEMENT__LABEL;

	/**
	 * The feature id for the '<em><b>Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_ELEMENT__LINK = MODEL_ELEMENT__LINK;

	/**
	 * The feature id for the '<em><b>Linked Page</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_ELEMENT__LINKED_PAGE = MODEL_ELEMENT__LINKED_PAGE;

	/**
	 * The feature id for the '<em><b>Style</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_ELEMENT__STYLE = MODEL_ELEMENT__STYLE;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_ELEMENT__TAGS = MODEL_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_ELEMENT__TOOLTIP = MODEL_ELEMENT__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_ELEMENT__VISIBLE = MODEL_ELEMENT__VISIBLE;

	/**
	 * The feature id for the '<em><b>Geometry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_ELEMENT__GEOMETRY = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Layer Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_ELEMENT_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The operation id for the '<em>Get Document</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_ELEMENT___GET_DOCUMENT = MODEL_ELEMENT___GET_DOCUMENT;

	/**
	 * The operation id for the '<em>Get Page</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_ELEMENT___GET_PAGE = MODEL_ELEMENT___GET_PAGE;

	/**
	 * The operation id for the '<em>Get Model Element By Id</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_ELEMENT___GET_MODEL_ELEMENT_BY_ID__STRING = MODEL_ELEMENT___GET_MODEL_ELEMENT_BY_ID__STRING;

	/**
	 * The number of operations of the '<em>Layer Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LAYER_ELEMENT_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.drawio.model.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.drawio.model.impl.NodeImpl
	 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 7;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__MARKERS = LAYER__MARKERS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__ID = LAYER__ID;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__PROPERTIES = LAYER__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__LABEL = LAYER__LABEL;

	/**
	 * The feature id for the '<em><b>Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__LINK = LAYER__LINK;

	/**
	 * The feature id for the '<em><b>Linked Page</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__LINKED_PAGE = LAYER__LINKED_PAGE;

	/**
	 * The feature id for the '<em><b>Style</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__STYLE = LAYER__STYLE;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__TAGS = LAYER__TAGS;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__TOOLTIP = LAYER__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__VISIBLE = LAYER__VISIBLE;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__ELEMENTS = LAYER__ELEMENTS;

	/**
	 * The feature id for the '<em><b>Geometry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__GEOMETRY = LAYER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Incoming</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__INCOMING = LAYER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Outgoing</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__OUTGOING = LAYER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = LAYER_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Document</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE___GET_DOCUMENT = LAYER___GET_DOCUMENT;

	/**
	 * The operation id for the '<em>Get Page</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE___GET_PAGE = LAYER___GET_PAGE;

	/**
	 * The operation id for the '<em>Get Model Element By Id</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE___GET_MODEL_ELEMENT_BY_ID__STRING = LAYER___GET_MODEL_ELEMENT_BY_ID__STRING;

	/**
	 * The number of operations of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_OPERATION_COUNT = LAYER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.drawio.model.impl.ConnectionImpl <em>Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.drawio.model.impl.ConnectionImpl
	 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getConnection()
	 * @generated
	 */
	int CONNECTION = 8;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__MARKERS = LAYER_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__ID = LAYER_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__PROPERTIES = LAYER_ELEMENT__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__LABEL = LAYER_ELEMENT__LABEL;

	/**
	 * The feature id for the '<em><b>Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__LINK = LAYER_ELEMENT__LINK;

	/**
	 * The feature id for the '<em><b>Linked Page</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__LINKED_PAGE = LAYER_ELEMENT__LINKED_PAGE;

	/**
	 * The feature id for the '<em><b>Style</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__STYLE = LAYER_ELEMENT__STYLE;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__TAGS = LAYER_ELEMENT__TAGS;

	/**
	 * The feature id for the '<em><b>Tooltip</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__TOOLTIP = LAYER_ELEMENT__TOOLTIP;

	/**
	 * The feature id for the '<em><b>Visible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__VISIBLE = LAYER_ELEMENT__VISIBLE;

	/**
	 * The feature id for the '<em><b>Geometry</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__GEOMETRY = LAYER_ELEMENT__GEOMETRY;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__SOURCE = LAYER_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__TARGET = LAYER_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_FEATURE_COUNT = LAYER_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The operation id for the '<em>Get Document</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION___GET_DOCUMENT = LAYER_ELEMENT___GET_DOCUMENT;

	/**
	 * The operation id for the '<em>Get Page</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION___GET_PAGE = LAYER_ELEMENT___GET_PAGE;

	/**
	 * The operation id for the '<em>Get Model Element By Id</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION___GET_MODEL_ELEMENT_BY_ID__STRING = LAYER_ELEMENT___GET_MODEL_ELEMENT_BY_ID__STRING;

	/**
	 * The number of operations of the '<em>Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_OPERATION_COUNT = LAYER_ELEMENT_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link org.nasdanika.drawio.model.impl.PointImpl <em>Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.drawio.model.impl.PointImpl
	 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getPoint()
	 * @generated
	 */
	int POINT = 9;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__X = 0;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__Y = 1;

	/**
	 * The number of structural features of the '<em>Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.drawio.model.impl.GeometryImpl <em>Geometry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.drawio.model.impl.GeometryImpl
	 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getGeometry()
	 * @generated
	 */
	int GEOMETRY = 10;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY__X = POINT__X;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY__Y = POINT__Y;

	/**
	 * The feature id for the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY__WIDTH = POINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY__HEIGHT = POINT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Relative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY__RELATIVE = POINT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Source Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY__SOURCE_POINT = POINT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Target Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY__TARGET_POINT = POINT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Offset Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY__OFFSET_POINT = POINT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY__POINTS = POINT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Geometry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY_FEATURE_COUNT = POINT_FEATURE_COUNT + 7;

	/**
	 * The number of operations of the '<em>Geometry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GEOMETRY_OPERATION_COUNT = POINT_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link org.nasdanika.drawio.model.impl.SemanticMappingImpl <em>Semantic Mapping</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.drawio.model.impl.SemanticMappingImpl
	 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getSemanticMapping()
	 * @generated
	 */
	int SEMANTIC_MAPPING = 11;

	/**
	 * The feature id for the '<em><b>Document URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MAPPING__DOCUMENT_URI = 0;

	/**
	 * The feature id for the '<em><b>Page ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MAPPING__PAGE_ID = 1;

	/**
	 * The feature id for the '<em><b>Model Element ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MAPPING__MODEL_ELEMENT_ID = 2;

	/**
	 * The feature id for the '<em><b>Page Element</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MAPPING__PAGE_ELEMENT = 3;

	/**
	 * The number of structural features of the '<em>Semantic Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MAPPING_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Semantic Mapping</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_MAPPING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.drawio.model.SemanticElement <em>Semantic Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.drawio.model.SemanticElement
	 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getSemanticElement()
	 * @generated
	 */
	int SEMANTIC_ELEMENT = 12;

	/**
	 * The feature id for the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_ELEMENT__SEMANTIC_MAPPINGS = 0;

	/**
	 * The number of structural features of the '<em>Semantic Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Semantic Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMANTIC_ELEMENT_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link org.nasdanika.drawio.model.Document <em>Document</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document</em>'.
	 * @see org.nasdanika.drawio.model.Document
	 * @generated
	 */
	EClass getDocument();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.drawio.model.Document#getPages <em>Pages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Pages</em>'.
	 * @see org.nasdanika.drawio.model.Document#getPages()
	 * @see #getDocument()
	 * @generated
	 */
	EReference getDocument_Pages();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.drawio.model.Document#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.nasdanika.drawio.model.Document#getUri()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_Uri();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.drawio.model.Document#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see org.nasdanika.drawio.model.Document#getSource()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_Source();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.drawio.model.Document#getPageByName(java.lang.String) <em>Get Page By Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Page By Name</em>' operation.
	 * @see org.nasdanika.drawio.model.Document#getPageByName(java.lang.String)
	 * @generated
	 */
	EOperation getDocument__GetPageByName__String();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.drawio.model.Document#getPageById(java.lang.String) <em>Get Page By Id</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Page By Id</em>' operation.
	 * @see org.nasdanika.drawio.model.Document#getPageById(java.lang.String)
	 * @generated
	 */
	EOperation getDocument__GetPageById__String();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.drawio.model.Document#getModelElementById(java.lang.String) <em>Get Model Element By Id</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Model Element By Id</em>' operation.
	 * @see org.nasdanika.drawio.model.Document#getModelElementById(java.lang.String)
	 * @generated
	 */
	EOperation getDocument__GetModelElementById__String();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.drawio.model.Page <em>Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Page</em>'.
	 * @see org.nasdanika.drawio.model.Page
	 * @generated
	 */
	EClass getPage();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.drawio.model.Page#getModel <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Model</em>'.
	 * @see org.nasdanika.drawio.model.Page#getModel()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_Model();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.drawio.model.Page#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.nasdanika.drawio.model.Page#getName()
	 * @see #getPage()
	 * @generated
	 */
	EAttribute getPage_Name();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.drawio.model.Page#getLinks <em>Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Links</em>'.
	 * @see org.nasdanika.drawio.model.Page#getLinks()
	 * @see #getPage()
	 * @generated
	 */
	EReference getPage_Links();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.drawio.model.Page#getModelElementById(java.lang.String) <em>Get Model Element By Id</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Model Element By Id</em>' operation.
	 * @see org.nasdanika.drawio.model.Page#getModelElementById(java.lang.String)
	 * @generated
	 */
	EOperation getPage__GetModelElementById__String();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.drawio.model.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see org.nasdanika.drawio.model.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.drawio.model.Model#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Root</em>'.
	 * @see org.nasdanika.drawio.model.Model#getRoot()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_Root();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.drawio.model.ModelElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see org.nasdanika.drawio.model.ModelElement
	 * @generated
	 */
	EClass getModelElement();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.drawio.model.ModelElement#getProperties <em>Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Properties</em>'.
	 * @see org.nasdanika.drawio.model.ModelElement#getProperties()
	 * @see #getModelElement()
	 * @generated
	 */
	EReference getModelElement_Properties();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.drawio.model.ModelElement#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see org.nasdanika.drawio.model.ModelElement#getLabel()
	 * @see #getModelElement()
	 * @generated
	 */
	EAttribute getModelElement_Label();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.drawio.model.ModelElement#getLink <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Link</em>'.
	 * @see org.nasdanika.drawio.model.ModelElement#getLink()
	 * @see #getModelElement()
	 * @generated
	 */
	EAttribute getModelElement_Link();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.drawio.model.ModelElement#getLinkedPage <em>Linked Page</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Linked Page</em>'.
	 * @see org.nasdanika.drawio.model.ModelElement#getLinkedPage()
	 * @see #getModelElement()
	 * @generated
	 */
	EReference getModelElement_LinkedPage();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.drawio.model.ModelElement#getStyle <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Style</em>'.
	 * @see org.nasdanika.drawio.model.ModelElement#getStyle()
	 * @see #getModelElement()
	 * @generated
	 */
	EReference getModelElement_Style();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.drawio.model.ModelElement#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Tags</em>'.
	 * @see org.nasdanika.drawio.model.ModelElement#getTags()
	 * @see #getModelElement()
	 * @generated
	 */
	EAttribute getModelElement_Tags();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.drawio.model.ModelElement#getTooltip <em>Tooltip</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tooltip</em>'.
	 * @see org.nasdanika.drawio.model.ModelElement#getTooltip()
	 * @see #getModelElement()
	 * @generated
	 */
	EAttribute getModelElement_Tooltip();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.drawio.model.ModelElement#isVisible <em>Visible</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Visible</em>'.
	 * @see org.nasdanika.drawio.model.ModelElement#isVisible()
	 * @see #getModelElement()
	 * @generated
	 */
	EAttribute getModelElement_Visible();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.drawio.model.ModelElement#getDocument() <em>Get Document</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Document</em>' operation.
	 * @see org.nasdanika.drawio.model.ModelElement#getDocument()
	 * @generated
	 */
	EOperation getModelElement__GetDocument();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.drawio.model.ModelElement#getPage() <em>Get Page</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Page</em>' operation.
	 * @see org.nasdanika.drawio.model.ModelElement#getPage()
	 * @generated
	 */
	EOperation getModelElement__GetPage();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.drawio.model.ModelElement#getModelElementById(java.lang.String) <em>Get Model Element By Id</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Model Element By Id</em>' operation.
	 * @see org.nasdanika.drawio.model.ModelElement#getModelElementById(java.lang.String)
	 * @generated
	 */
	EOperation getModelElement__GetModelElementById__String();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.drawio.model.Root <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Root</em>'.
	 * @see org.nasdanika.drawio.model.Root
	 * @generated
	 */
	EClass getRoot();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.drawio.model.Root#getLayers <em>Layers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Layers</em>'.
	 * @see org.nasdanika.drawio.model.Root#getLayers()
	 * @see #getRoot()
	 * @generated
	 */
	EReference getRoot_Layers();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.drawio.model.Layer <em>Layer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Layer</em>'.
	 * @see org.nasdanika.drawio.model.Layer
	 * @generated
	 */
	EClass getLayer();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.drawio.model.Layer#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see org.nasdanika.drawio.model.Layer#getElements()
	 * @see #getLayer()
	 * @generated
	 */
	EReference getLayer_Elements();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.drawio.model.LayerElement <em>Layer Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Layer Element</em>'.
	 * @see org.nasdanika.drawio.model.LayerElement
	 * @generated
	 */
	EClass getLayerElement();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.drawio.model.LayerElement#getGeometry <em>Geometry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Geometry</em>'.
	 * @see org.nasdanika.drawio.model.LayerElement#getGeometry()
	 * @see #getLayerElement()
	 * @generated
	 */
	EReference getLayerElement_Geometry();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.drawio.model.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see org.nasdanika.drawio.model.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.drawio.model.Node#getIncoming <em>Incoming</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming</em>'.
	 * @see org.nasdanika.drawio.model.Node#getIncoming()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Incoming();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.drawio.model.Node#getOutgoing <em>Outgoing</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outgoing</em>'.
	 * @see org.nasdanika.drawio.model.Node#getOutgoing()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Outgoing();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.drawio.model.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection</em>'.
	 * @see org.nasdanika.drawio.model.Connection
	 * @generated
	 */
	EClass getConnection();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.drawio.model.Connection#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.nasdanika.drawio.model.Connection#getSource()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_Source();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.drawio.model.Connection#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.nasdanika.drawio.model.Connection#getTarget()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_Target();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.drawio.model.Point <em>Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Point</em>'.
	 * @see org.nasdanika.drawio.model.Point
	 * @generated
	 */
	EClass getPoint();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.drawio.model.Point#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see org.nasdanika.drawio.model.Point#getX()
	 * @see #getPoint()
	 * @generated
	 */
	EAttribute getPoint_X();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.drawio.model.Point#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see org.nasdanika.drawio.model.Point#getY()
	 * @see #getPoint()
	 * @generated
	 */
	EAttribute getPoint_Y();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.drawio.model.Geometry <em>Geometry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Geometry</em>'.
	 * @see org.nasdanika.drawio.model.Geometry
	 * @generated
	 */
	EClass getGeometry();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.drawio.model.Geometry#getWidth <em>Width</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Width</em>'.
	 * @see org.nasdanika.drawio.model.Geometry#getWidth()
	 * @see #getGeometry()
	 * @generated
	 */
	EAttribute getGeometry_Width();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.drawio.model.Geometry#getHeight <em>Height</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Height</em>'.
	 * @see org.nasdanika.drawio.model.Geometry#getHeight()
	 * @see #getGeometry()
	 * @generated
	 */
	EAttribute getGeometry_Height();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.drawio.model.Geometry#isRelative <em>Relative</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Relative</em>'.
	 * @see org.nasdanika.drawio.model.Geometry#isRelative()
	 * @see #getGeometry()
	 * @generated
	 */
	EAttribute getGeometry_Relative();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.drawio.model.Geometry#getSourcePoint <em>Source Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Source Point</em>'.
	 * @see org.nasdanika.drawio.model.Geometry#getSourcePoint()
	 * @see #getGeometry()
	 * @generated
	 */
	EReference getGeometry_SourcePoint();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.drawio.model.Geometry#getTargetPoint <em>Target Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Target Point</em>'.
	 * @see org.nasdanika.drawio.model.Geometry#getTargetPoint()
	 * @see #getGeometry()
	 * @generated
	 */
	EReference getGeometry_TargetPoint();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.drawio.model.Geometry#getOffsetPoint <em>Offset Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Offset Point</em>'.
	 * @see org.nasdanika.drawio.model.Geometry#getOffsetPoint()
	 * @see #getGeometry()
	 * @generated
	 */
	EReference getGeometry_OffsetPoint();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.drawio.model.Geometry#getPoints <em>Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Points</em>'.
	 * @see org.nasdanika.drawio.model.Geometry#getPoints()
	 * @see #getGeometry()
	 * @generated
	 */
	EReference getGeometry_Points();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.drawio.model.SemanticMapping <em>Semantic Mapping</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Semantic Mapping</em>'.
	 * @see org.nasdanika.drawio.model.SemanticMapping
	 * @generated
	 */
	EClass getSemanticMapping();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.drawio.model.SemanticMapping#getDocumentURI <em>Document URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Document URI</em>'.
	 * @see org.nasdanika.drawio.model.SemanticMapping#getDocumentURI()
	 * @see #getSemanticMapping()
	 * @generated
	 */
	EAttribute getSemanticMapping_DocumentURI();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.drawio.model.SemanticMapping#getPageID <em>Page ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page ID</em>'.
	 * @see org.nasdanika.drawio.model.SemanticMapping#getPageID()
	 * @see #getSemanticMapping()
	 * @generated
	 */
	EAttribute getSemanticMapping_PageID();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.drawio.model.SemanticMapping#getModelElementID <em>Model Element ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Model Element ID</em>'.
	 * @see org.nasdanika.drawio.model.SemanticMapping#getModelElementID()
	 * @see #getSemanticMapping()
	 * @generated
	 */
	EAttribute getSemanticMapping_ModelElementID();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.drawio.model.SemanticMapping#isPageElement <em>Page Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Element</em>'.
	 * @see org.nasdanika.drawio.model.SemanticMapping#isPageElement()
	 * @see #getSemanticMapping()
	 * @generated
	 */
	EAttribute getSemanticMapping_PageElement();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.drawio.model.SemanticElement <em>Semantic Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Semantic Element</em>'.
	 * @see org.nasdanika.drawio.model.SemanticElement
	 * @generated
	 */
	EClass getSemanticElement();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.drawio.model.SemanticElement#getSemanticMappings <em>Semantic Mappings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Semantic Mappings</em>'.
	 * @see org.nasdanika.drawio.model.SemanticElement#getSemanticMappings()
	 * @see #getSemanticElement()
	 * @generated
	 */
	EReference getSemanticElement_SemanticMappings();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

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
		 * The meta object literal for the '{@link org.nasdanika.drawio.model.impl.DocumentImpl <em>Document</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.drawio.model.impl.DocumentImpl
		 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getDocument()
		 * @generated
		 */
		EClass DOCUMENT = eINSTANCE.getDocument();
		/**
		 * The meta object literal for the '<em><b>Pages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT__PAGES = eINSTANCE.getDocument_Pages();
		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__URI = eINSTANCE.getDocument_Uri();
		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__SOURCE = eINSTANCE.getDocument_Source();
		/**
		 * The meta object literal for the '<em><b>Get Page By Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOCUMENT___GET_PAGE_BY_NAME__STRING = eINSTANCE.getDocument__GetPageByName__String();
		/**
		 * The meta object literal for the '<em><b>Get Page By Id</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOCUMENT___GET_PAGE_BY_ID__STRING = eINSTANCE.getDocument__GetPageById__String();
		/**
		 * The meta object literal for the '<em><b>Get Model Element By Id</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DOCUMENT___GET_MODEL_ELEMENT_BY_ID__STRING = eINSTANCE.getDocument__GetModelElementById__String();
		/**
		 * The meta object literal for the '{@link org.nasdanika.drawio.model.impl.PageImpl <em>Page</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.drawio.model.impl.PageImpl
		 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getPage()
		 * @generated
		 */
		EClass PAGE = eINSTANCE.getPage();
		/**
		 * The meta object literal for the '<em><b>Model</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__MODEL = eINSTANCE.getPage_Model();
		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PAGE__NAME = eINSTANCE.getPage_Name();
		/**
		 * The meta object literal for the '<em><b>Links</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PAGE__LINKS = eINSTANCE.getPage_Links();
		/**
		 * The meta object literal for the '<em><b>Get Model Element By Id</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation PAGE___GET_MODEL_ELEMENT_BY_ID__STRING = eINSTANCE.getPage__GetModelElementById__String();
		/**
		 * The meta object literal for the '{@link org.nasdanika.drawio.model.impl.ModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.drawio.model.impl.ModelImpl
		 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getModel()
		 * @generated
		 */
		EClass MODEL = eINSTANCE.getModel();
		/**
		 * The meta object literal for the '<em><b>Root</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__ROOT = eINSTANCE.getModel_Root();
		/**
		 * The meta object literal for the '{@link org.nasdanika.drawio.model.impl.ModelElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.drawio.model.impl.ModelElementImpl
		 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getModelElement()
		 * @generated
		 */
		EClass MODEL_ELEMENT = eINSTANCE.getModelElement();
		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_ELEMENT__PROPERTIES = eINSTANCE.getModelElement_Properties();
		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_ELEMENT__LABEL = eINSTANCE.getModelElement_Label();
		/**
		 * The meta object literal for the '<em><b>Link</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_ELEMENT__LINK = eINSTANCE.getModelElement_Link();
		/**
		 * The meta object literal for the '<em><b>Linked Page</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_ELEMENT__LINKED_PAGE = eINSTANCE.getModelElement_LinkedPage();
		/**
		 * The meta object literal for the '<em><b>Style</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_ELEMENT__STYLE = eINSTANCE.getModelElement_Style();
		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_ELEMENT__TAGS = eINSTANCE.getModelElement_Tags();
		/**
		 * The meta object literal for the '<em><b>Tooltip</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_ELEMENT__TOOLTIP = eINSTANCE.getModelElement_Tooltip();
		/**
		 * The meta object literal for the '<em><b>Visible</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_ELEMENT__VISIBLE = eINSTANCE.getModelElement_Visible();
		/**
		 * The meta object literal for the '<em><b>Get Document</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation MODEL_ELEMENT___GET_DOCUMENT = eINSTANCE.getModelElement__GetDocument();
		/**
		 * The meta object literal for the '<em><b>Get Page</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation MODEL_ELEMENT___GET_PAGE = eINSTANCE.getModelElement__GetPage();
		/**
		 * The meta object literal for the '<em><b>Get Model Element By Id</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation MODEL_ELEMENT___GET_MODEL_ELEMENT_BY_ID__STRING = eINSTANCE.getModelElement__GetModelElementById__String();
		/**
		 * The meta object literal for the '{@link org.nasdanika.drawio.model.impl.RootImpl <em>Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.drawio.model.impl.RootImpl
		 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getRoot()
		 * @generated
		 */
		EClass ROOT = eINSTANCE.getRoot();
		/**
		 * The meta object literal for the '<em><b>Layers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROOT__LAYERS = eINSTANCE.getRoot_Layers();
		/**
		 * The meta object literal for the '{@link org.nasdanika.drawio.model.impl.LayerImpl <em>Layer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.drawio.model.impl.LayerImpl
		 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getLayer()
		 * @generated
		 */
		EClass LAYER = eINSTANCE.getLayer();
		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LAYER__ELEMENTS = eINSTANCE.getLayer_Elements();
		/**
		 * The meta object literal for the '{@link org.nasdanika.drawio.model.impl.LayerElementImpl <em>Layer Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.drawio.model.impl.LayerElementImpl
		 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getLayerElement()
		 * @generated
		 */
		EClass LAYER_ELEMENT = eINSTANCE.getLayerElement();
		/**
		 * The meta object literal for the '<em><b>Geometry</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LAYER_ELEMENT__GEOMETRY = eINSTANCE.getLayerElement_Geometry();
		/**
		 * The meta object literal for the '{@link org.nasdanika.drawio.model.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.drawio.model.impl.NodeImpl
		 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();
		/**
		 * The meta object literal for the '<em><b>Incoming</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__INCOMING = eINSTANCE.getNode_Incoming();
		/**
		 * The meta object literal for the '<em><b>Outgoing</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__OUTGOING = eINSTANCE.getNode_Outgoing();
		/**
		 * The meta object literal for the '{@link org.nasdanika.drawio.model.impl.ConnectionImpl <em>Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.drawio.model.impl.ConnectionImpl
		 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getConnection()
		 * @generated
		 */
		EClass CONNECTION = eINSTANCE.getConnection();
		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__SOURCE = eINSTANCE.getConnection_Source();
		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__TARGET = eINSTANCE.getConnection_Target();
		/**
		 * The meta object literal for the '{@link org.nasdanika.drawio.model.impl.PointImpl <em>Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.drawio.model.impl.PointImpl
		 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getPoint()
		 * @generated
		 */
		EClass POINT = eINSTANCE.getPoint();
		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POINT__X = eINSTANCE.getPoint_X();
		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POINT__Y = eINSTANCE.getPoint_Y();
		/**
		 * The meta object literal for the '{@link org.nasdanika.drawio.model.impl.GeometryImpl <em>Geometry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.drawio.model.impl.GeometryImpl
		 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getGeometry()
		 * @generated
		 */
		EClass GEOMETRY = eINSTANCE.getGeometry();
		/**
		 * The meta object literal for the '<em><b>Width</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEOMETRY__WIDTH = eINSTANCE.getGeometry_Width();
		/**
		 * The meta object literal for the '<em><b>Height</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEOMETRY__HEIGHT = eINSTANCE.getGeometry_Height();
		/**
		 * The meta object literal for the '<em><b>Relative</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GEOMETRY__RELATIVE = eINSTANCE.getGeometry_Relative();
		/**
		 * The meta object literal for the '<em><b>Source Point</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GEOMETRY__SOURCE_POINT = eINSTANCE.getGeometry_SourcePoint();
		/**
		 * The meta object literal for the '<em><b>Target Point</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GEOMETRY__TARGET_POINT = eINSTANCE.getGeometry_TargetPoint();
		/**
		 * The meta object literal for the '<em><b>Offset Point</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GEOMETRY__OFFSET_POINT = eINSTANCE.getGeometry_OffsetPoint();
		/**
		 * The meta object literal for the '<em><b>Points</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GEOMETRY__POINTS = eINSTANCE.getGeometry_Points();
		/**
		 * The meta object literal for the '{@link org.nasdanika.drawio.model.impl.SemanticMappingImpl <em>Semantic Mapping</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.drawio.model.impl.SemanticMappingImpl
		 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getSemanticMapping()
		 * @generated
		 */
		EClass SEMANTIC_MAPPING = eINSTANCE.getSemanticMapping();
		/**
		 * The meta object literal for the '<em><b>Document URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEMANTIC_MAPPING__DOCUMENT_URI = eINSTANCE.getSemanticMapping_DocumentURI();
		/**
		 * The meta object literal for the '<em><b>Page ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEMANTIC_MAPPING__PAGE_ID = eINSTANCE.getSemanticMapping_PageID();
		/**
		 * The meta object literal for the '<em><b>Model Element ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEMANTIC_MAPPING__MODEL_ELEMENT_ID = eINSTANCE.getSemanticMapping_ModelElementID();
		/**
		 * The meta object literal for the '<em><b>Page Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SEMANTIC_MAPPING__PAGE_ELEMENT = eINSTANCE.getSemanticMapping_PageElement();
		/**
		 * The meta object literal for the '{@link org.nasdanika.drawio.model.SemanticElement <em>Semantic Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.drawio.model.SemanticElement
		 * @see org.nasdanika.drawio.model.impl.ModelPackageImpl#getSemanticElement()
		 * @generated
		 */
		EClass SEMANTIC_ELEMENT = eINSTANCE.getSemanticElement();
		/**
		 * The meta object literal for the '<em><b>Semantic Mappings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEMANTIC_ELEMENT__SEMANTIC_MAPPINGS = eINSTANCE.getSemanticElement_SemanticMappings();

	}

} //ModelPackage
