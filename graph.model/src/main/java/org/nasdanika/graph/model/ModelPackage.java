/**
 */
package org.nasdanika.graph.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * @see org.nasdanika.graph.model.ModelFactory
 * @model kind="package"
 *        annotation="urn:org.nasdanika load-key='graph'"
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
	String eNS_URI = "ecore://nasdanika.org/core/gaph/model";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.graph.model";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = org.nasdanika.graph.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.GraphElementImpl <em>Graph Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.GraphElementImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getGraphElement()
	 * @generated
	 */
	int GRAPH_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__ID = NcorePackage.STRING_IDENTITY__ID;

	/**
	 * The feature id for the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT__SEMANTIC_MAPPINGS = NcorePackage.STRING_IDENTITY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Graph Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT_FEATURE_COUNT = NcorePackage.STRING_IDENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Graph Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_ELEMENT_OPERATION_COUNT = NcorePackage.STRING_IDENTITY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.GraphImpl <em>Graph</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.GraphImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getGraph()
	 * @generated
	 */
	int GRAPH = 1;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH__ELEMENTS = 0;

	/**
	 * The number of structural features of the '<em>Graph</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Graph</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAPH_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.SubGraphImpl <em>Sub Graph</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.SubGraphImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getSubGraph()
	 * @generated
	 */
	int SUB_GRAPH = 2;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_GRAPH__ID = GRAPH_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_GRAPH__SEMANTIC_MAPPINGS = GRAPH_ELEMENT__SEMANTIC_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_GRAPH__ELEMENTS = GRAPH_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Sub Graph</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_GRAPH_FEATURE_COUNT = GRAPH_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Sub Graph</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUB_GRAPH_OPERATION_COUNT = GRAPH_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.ConnectionTargetImpl <em>Connection Target</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.ConnectionTargetImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getConnectionTarget()
	 * @generated
	 */
	int CONNECTION_TARGET = 3;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_TARGET__ID = GRAPH_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_TARGET__SEMANTIC_MAPPINGS = GRAPH_ELEMENT__SEMANTIC_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_TARGET__INCOMING_CONNECTIONS = GRAPH_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Connection Target</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_TARGET_FEATURE_COUNT = GRAPH_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Connection Target</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_TARGET_OPERATION_COUNT = GRAPH_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.CompositeConnectionTargetImpl <em>Composite Connection Target</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.CompositeConnectionTargetImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getCompositeConnectionTarget()
	 * @generated
	 */
	int COMPOSITE_CONNECTION_TARGET = 4;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_CONNECTION_TARGET__ID = SUB_GRAPH__ID;

	/**
	 * The feature id for the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_CONNECTION_TARGET__SEMANTIC_MAPPINGS = SUB_GRAPH__SEMANTIC_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_CONNECTION_TARGET__ELEMENTS = SUB_GRAPH__ELEMENTS;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_CONNECTION_TARGET__INCOMING_CONNECTIONS = SUB_GRAPH_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Composite Connection Target</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_CONNECTION_TARGET_FEATURE_COUNT = SUB_GRAPH_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Composite Connection Target</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_CONNECTION_TARGET_OPERATION_COUNT = SUB_GRAPH_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.ConnectionImpl <em>Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.ConnectionImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getConnection()
	 * @generated
	 */
	int CONNECTION = 5;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__ID = NcorePackage.STRING_IDENTITY__ID;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__TARGET = NcorePackage.STRING_IDENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Bidirectional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION__BIDIRECTIONAL = NcorePackage.STRING_IDENTITY_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_FEATURE_COUNT = NcorePackage.STRING_IDENTITY_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_OPERATION_COUNT = NcorePackage.STRING_IDENTITY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.ConnectionSourceImpl <em>Connection Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.ConnectionSourceImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getConnectionSource()
	 * @generated
	 */
	int CONNECTION_SOURCE = 6;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_SOURCE__ID = GRAPH_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_SOURCE__SEMANTIC_MAPPINGS = GRAPH_ELEMENT__SEMANTIC_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_SOURCE__OUTGOING_CONNECTIONS = GRAPH_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Connection Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_SOURCE_FEATURE_COUNT = GRAPH_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Connection Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTION_SOURCE_OPERATION_COUNT = GRAPH_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.CompositeConnectionSourceImpl <em>Composite Connection Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.CompositeConnectionSourceImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getCompositeConnectionSource()
	 * @generated
	 */
	int COMPOSITE_CONNECTION_SOURCE = 7;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_CONNECTION_SOURCE__ID = SUB_GRAPH__ID;

	/**
	 * The feature id for the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_CONNECTION_SOURCE__SEMANTIC_MAPPINGS = SUB_GRAPH__SEMANTIC_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_CONNECTION_SOURCE__ELEMENTS = SUB_GRAPH__ELEMENTS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_CONNECTION_SOURCE__OUTGOING_CONNECTIONS = SUB_GRAPH_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Composite Connection Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_CONNECTION_SOURCE_FEATURE_COUNT = SUB_GRAPH_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Composite Connection Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_CONNECTION_SOURCE_OPERATION_COUNT = SUB_GRAPH_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.NodeImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__ID = CONNECTION_SOURCE__ID;

	/**
	 * The feature id for the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__SEMANTIC_MAPPINGS = CONNECTION_SOURCE__SEMANTIC_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__OUTGOING_CONNECTIONS = CONNECTION_SOURCE__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__INCOMING_CONNECTIONS = CONNECTION_SOURCE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = CONNECTION_SOURCE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_OPERATION_COUNT = CONNECTION_SOURCE_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.CompositeNodeImpl <em>Composite Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.CompositeNodeImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getCompositeNode()
	 * @generated
	 */
	int COMPOSITE_NODE = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__ID = SUB_GRAPH__ID;

	/**
	 * The feature id for the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__SEMANTIC_MAPPINGS = SUB_GRAPH__SEMANTIC_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__ELEMENTS = SUB_GRAPH__ELEMENTS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__OUTGOING_CONNECTIONS = SUB_GRAPH_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE__INCOMING_CONNECTIONS = SUB_GRAPH_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Composite Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE_FEATURE_COUNT = SUB_GRAPH_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Composite Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_NODE_OPERATION_COUNT = SUB_GRAPH_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.TunnelImpl <em>Tunnel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.TunnelImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getTunnel()
	 * @generated
	 */
	int TUNNEL = 10;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNNEL__ID = CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNNEL__TARGET = CONNECTION__TARGET;

	/**
	 * The feature id for the '<em><b>Bidirectional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNNEL__BIDIRECTIONAL = CONNECTION__BIDIRECTIONAL;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNNEL__CONNECTIONS = CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tunnel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNNEL_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Tunnel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TUNNEL_OPERATION_COUNT = CONNECTION_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedGraphElementImpl <em>Documented Named Graph Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.DocumentedNamedGraphElementImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedGraphElement()
	 * @generated
	 */
	int DOCUMENTED_NAMED_GRAPH_ELEMENT = 11;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH_ELEMENT__ID = GRAPH_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH_ELEMENT__SEMANTIC_MAPPINGS = GRAPH_ELEMENT__SEMANTIC_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH_ELEMENT__MARKERS = GRAPH_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH_ELEMENT__URIS = GRAPH_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH_ELEMENT__DESCRIPTION = GRAPH_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH_ELEMENT__UUID = GRAPH_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH_ELEMENT__LABEL_PROTOTYPE = GRAPH_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH_ELEMENT__REPRESENTATIONS = GRAPH_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH_ELEMENT__ANNOTATIONS = GRAPH_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH_ELEMENT__NAME = GRAPH_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH_ELEMENT__DOCUMENTATION = GRAPH_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Context Help</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH_ELEMENT__CONTEXT_HELP = GRAPH_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Documented Named Graph Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH_ELEMENT_FEATURE_COUNT = GRAPH_ELEMENT_FEATURE_COUNT + 10;

	/**
	 * The number of operations of the '<em>Documented Named Graph Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH_ELEMENT_OPERATION_COUNT = GRAPH_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedGraphImpl <em>Documented Named Graph</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.DocumentedNamedGraphImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedGraph()
	 * @generated
	 */
	int DOCUMENTED_NAMED_GRAPH = 12;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH__ELEMENTS = GRAPH__ELEMENTS;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH__MARKERS = GRAPH_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH__URIS = GRAPH_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH__DESCRIPTION = GRAPH_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH__UUID = GRAPH_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH__LABEL_PROTOTYPE = GRAPH_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH__REPRESENTATIONS = GRAPH_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH__ANNOTATIONS = GRAPH_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH__NAME = GRAPH_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH__DOCUMENTATION = GRAPH_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Context Help</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH__CONTEXT_HELP = GRAPH_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Documented Named Graph</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH_FEATURE_COUNT = GRAPH_FEATURE_COUNT + 10;

	/**
	 * The number of operations of the '<em>Documented Named Graph</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_GRAPH_OPERATION_COUNT = GRAPH_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedSubGraphImpl <em>Documented Named Sub Graph</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.DocumentedNamedSubGraphImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedSubGraph()
	 * @generated
	 */
	int DOCUMENTED_NAMED_SUB_GRAPH = 13;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_SUB_GRAPH__ID = DOCUMENTED_NAMED_GRAPH_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_SUB_GRAPH__SEMANTIC_MAPPINGS = DOCUMENTED_NAMED_GRAPH_ELEMENT__SEMANTIC_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_SUB_GRAPH__MARKERS = DOCUMENTED_NAMED_GRAPH_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_SUB_GRAPH__URIS = DOCUMENTED_NAMED_GRAPH_ELEMENT__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_SUB_GRAPH__DESCRIPTION = DOCUMENTED_NAMED_GRAPH_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_SUB_GRAPH__UUID = DOCUMENTED_NAMED_GRAPH_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_SUB_GRAPH__LABEL_PROTOTYPE = DOCUMENTED_NAMED_GRAPH_ELEMENT__LABEL_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_SUB_GRAPH__REPRESENTATIONS = DOCUMENTED_NAMED_GRAPH_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_SUB_GRAPH__ANNOTATIONS = DOCUMENTED_NAMED_GRAPH_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_SUB_GRAPH__NAME = DOCUMENTED_NAMED_GRAPH_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_SUB_GRAPH__DOCUMENTATION = DOCUMENTED_NAMED_GRAPH_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Context Help</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_SUB_GRAPH__CONTEXT_HELP = DOCUMENTED_NAMED_GRAPH_ELEMENT__CONTEXT_HELP;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_SUB_GRAPH__ELEMENTS = DOCUMENTED_NAMED_GRAPH_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Documented Named Sub Graph</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_SUB_GRAPH_FEATURE_COUNT = DOCUMENTED_NAMED_GRAPH_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Documented Named Sub Graph</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_SUB_GRAPH_OPERATION_COUNT = DOCUMENTED_NAMED_GRAPH_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedConnectionTargetImpl <em>Documented Named Connection Target</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.DocumentedNamedConnectionTargetImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedConnectionTarget()
	 * @generated
	 */
	int DOCUMENTED_NAMED_CONNECTION_TARGET = 14;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_TARGET__ID = CONNECTION_TARGET__ID;

	/**
	 * The feature id for the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_TARGET__SEMANTIC_MAPPINGS = CONNECTION_TARGET__SEMANTIC_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_TARGET__INCOMING_CONNECTIONS = CONNECTION_TARGET__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_TARGET__MARKERS = CONNECTION_TARGET_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_TARGET__URIS = CONNECTION_TARGET_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_TARGET__DESCRIPTION = CONNECTION_TARGET_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_TARGET__UUID = CONNECTION_TARGET_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_TARGET__LABEL_PROTOTYPE = CONNECTION_TARGET_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_TARGET__REPRESENTATIONS = CONNECTION_TARGET_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_TARGET__ANNOTATIONS = CONNECTION_TARGET_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_TARGET__NAME = CONNECTION_TARGET_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_TARGET__DOCUMENTATION = CONNECTION_TARGET_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Context Help</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_TARGET__CONTEXT_HELP = CONNECTION_TARGET_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Documented Named Connection Target</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_TARGET_FEATURE_COUNT = CONNECTION_TARGET_FEATURE_COUNT + 10;

	/**
	 * The number of operations of the '<em>Documented Named Connection Target</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_TARGET_OPERATION_COUNT = CONNECTION_TARGET_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedCompositeConnectionTargetImpl <em>Documented Named Composite Connection Target</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.DocumentedNamedCompositeConnectionTargetImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedCompositeConnectionTarget()
	 * @generated
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET = 15;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET__ID = DOCUMENTED_NAMED_SUB_GRAPH__ID;

	/**
	 * The feature id for the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET__SEMANTIC_MAPPINGS = DOCUMENTED_NAMED_SUB_GRAPH__SEMANTIC_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET__MARKERS = DOCUMENTED_NAMED_SUB_GRAPH__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET__URIS = DOCUMENTED_NAMED_SUB_GRAPH__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET__DESCRIPTION = DOCUMENTED_NAMED_SUB_GRAPH__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET__UUID = DOCUMENTED_NAMED_SUB_GRAPH__UUID;

	/**
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET__LABEL_PROTOTYPE = DOCUMENTED_NAMED_SUB_GRAPH__LABEL_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET__REPRESENTATIONS = DOCUMENTED_NAMED_SUB_GRAPH__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET__ANNOTATIONS = DOCUMENTED_NAMED_SUB_GRAPH__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET__NAME = DOCUMENTED_NAMED_SUB_GRAPH__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET__DOCUMENTATION = DOCUMENTED_NAMED_SUB_GRAPH__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Context Help</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET__CONTEXT_HELP = DOCUMENTED_NAMED_SUB_GRAPH__CONTEXT_HELP;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET__ELEMENTS = DOCUMENTED_NAMED_SUB_GRAPH__ELEMENTS;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET__INCOMING_CONNECTIONS = DOCUMENTED_NAMED_SUB_GRAPH_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Documented Named Composite Connection Target</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET_FEATURE_COUNT = DOCUMENTED_NAMED_SUB_GRAPH_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Documented Named Composite Connection Target</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET_OPERATION_COUNT = DOCUMENTED_NAMED_SUB_GRAPH_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedConnectionImpl <em>Documented Named Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.DocumentedNamedConnectionImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedConnection()
	 * @generated
	 */
	int DOCUMENTED_NAMED_CONNECTION = 16;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION__ID = CONNECTION__ID;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION__TARGET = CONNECTION__TARGET;

	/**
	 * The feature id for the '<em><b>Bidirectional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION__BIDIRECTIONAL = CONNECTION__BIDIRECTIONAL;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION__MARKERS = CONNECTION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION__URIS = CONNECTION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION__DESCRIPTION = CONNECTION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION__UUID = CONNECTION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION__LABEL_PROTOTYPE = CONNECTION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION__REPRESENTATIONS = CONNECTION_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION__ANNOTATIONS = CONNECTION_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION__NAME = CONNECTION_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION__DOCUMENTATION = CONNECTION_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Context Help</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION__CONTEXT_HELP = CONNECTION_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Documented Named Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_FEATURE_COUNT = CONNECTION_FEATURE_COUNT + 10;

	/**
	 * The number of operations of the '<em>Documented Named Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_OPERATION_COUNT = CONNECTION_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedConnectionSourceImpl <em>Documented Named Connection Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.DocumentedNamedConnectionSourceImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedConnectionSource()
	 * @generated
	 */
	int DOCUMENTED_NAMED_CONNECTION_SOURCE = 17;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_SOURCE__ID = DOCUMENTED_NAMED_GRAPH_ELEMENT__ID;

	/**
	 * The feature id for the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_SOURCE__SEMANTIC_MAPPINGS = DOCUMENTED_NAMED_GRAPH_ELEMENT__SEMANTIC_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_SOURCE__MARKERS = DOCUMENTED_NAMED_GRAPH_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_SOURCE__URIS = DOCUMENTED_NAMED_GRAPH_ELEMENT__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_SOURCE__DESCRIPTION = DOCUMENTED_NAMED_GRAPH_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_SOURCE__UUID = DOCUMENTED_NAMED_GRAPH_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_SOURCE__LABEL_PROTOTYPE = DOCUMENTED_NAMED_GRAPH_ELEMENT__LABEL_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_SOURCE__REPRESENTATIONS = DOCUMENTED_NAMED_GRAPH_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_SOURCE__ANNOTATIONS = DOCUMENTED_NAMED_GRAPH_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_SOURCE__NAME = DOCUMENTED_NAMED_GRAPH_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_SOURCE__DOCUMENTATION = DOCUMENTED_NAMED_GRAPH_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Context Help</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_SOURCE__CONTEXT_HELP = DOCUMENTED_NAMED_GRAPH_ELEMENT__CONTEXT_HELP;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_SOURCE__OUTGOING_CONNECTIONS = DOCUMENTED_NAMED_GRAPH_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Documented Named Connection Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_SOURCE_FEATURE_COUNT = DOCUMENTED_NAMED_GRAPH_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Documented Named Connection Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_CONNECTION_SOURCE_OPERATION_COUNT = DOCUMENTED_NAMED_GRAPH_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedCompositeConnectionSourceImpl <em>Documented Named Composite Connection Source</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.DocumentedNamedCompositeConnectionSourceImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedCompositeConnectionSource()
	 * @generated
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE = 18;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE__ID = DOCUMENTED_NAMED_SUB_GRAPH__ID;

	/**
	 * The feature id for the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE__SEMANTIC_MAPPINGS = DOCUMENTED_NAMED_SUB_GRAPH__SEMANTIC_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE__MARKERS = DOCUMENTED_NAMED_SUB_GRAPH__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE__URIS = DOCUMENTED_NAMED_SUB_GRAPH__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE__DESCRIPTION = DOCUMENTED_NAMED_SUB_GRAPH__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE__UUID = DOCUMENTED_NAMED_SUB_GRAPH__UUID;

	/**
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE__LABEL_PROTOTYPE = DOCUMENTED_NAMED_SUB_GRAPH__LABEL_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE__REPRESENTATIONS = DOCUMENTED_NAMED_SUB_GRAPH__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE__ANNOTATIONS = DOCUMENTED_NAMED_SUB_GRAPH__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE__NAME = DOCUMENTED_NAMED_SUB_GRAPH__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE__DOCUMENTATION = DOCUMENTED_NAMED_SUB_GRAPH__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Context Help</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE__CONTEXT_HELP = DOCUMENTED_NAMED_SUB_GRAPH__CONTEXT_HELP;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE__ELEMENTS = DOCUMENTED_NAMED_SUB_GRAPH__ELEMENTS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE__OUTGOING_CONNECTIONS = DOCUMENTED_NAMED_SUB_GRAPH_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Documented Named Composite Connection Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE_FEATURE_COUNT = DOCUMENTED_NAMED_SUB_GRAPH_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Documented Named Composite Connection Source</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE_OPERATION_COUNT = DOCUMENTED_NAMED_SUB_GRAPH_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedNodeImpl <em>Documented Named Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.DocumentedNamedNodeImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedNode()
	 * @generated
	 */
	int DOCUMENTED_NAMED_NODE = 19;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_NODE__ID = NODE__ID;

	/**
	 * The feature id for the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_NODE__SEMANTIC_MAPPINGS = NODE__SEMANTIC_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_NODE__OUTGOING_CONNECTIONS = NODE__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_NODE__INCOMING_CONNECTIONS = NODE__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_NODE__MARKERS = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_NODE__URIS = NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_NODE__DESCRIPTION = NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_NODE__UUID = NODE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_NODE__LABEL_PROTOTYPE = NODE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_NODE__REPRESENTATIONS = NODE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_NODE__ANNOTATIONS = NODE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_NODE__NAME = NODE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_NODE__DOCUMENTATION = NODE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Context Help</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_NODE__CONTEXT_HELP = NODE_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Documented Named Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_NODE_FEATURE_COUNT = NODE_FEATURE_COUNT + 10;

	/**
	 * The number of operations of the '<em>Documented Named Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_NODE_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedCompositeNodeImpl <em>Documented Named Composite Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.DocumentedNamedCompositeNodeImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedCompositeNode()
	 * @generated
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE = 20;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE__ID = COMPOSITE_NODE__ID;

	/**
	 * The feature id for the '<em><b>Semantic Mappings</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE__SEMANTIC_MAPPINGS = COMPOSITE_NODE__SEMANTIC_MAPPINGS;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE__ELEMENTS = COMPOSITE_NODE__ELEMENTS;

	/**
	 * The feature id for the '<em><b>Outgoing Connections</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE__OUTGOING_CONNECTIONS = COMPOSITE_NODE__OUTGOING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Incoming Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE__INCOMING_CONNECTIONS = COMPOSITE_NODE__INCOMING_CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE__MARKERS = COMPOSITE_NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE__URIS = COMPOSITE_NODE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE__DESCRIPTION = COMPOSITE_NODE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE__UUID = COMPOSITE_NODE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE__LABEL_PROTOTYPE = COMPOSITE_NODE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE__REPRESENTATIONS = COMPOSITE_NODE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE__ANNOTATIONS = COMPOSITE_NODE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE__NAME = COMPOSITE_NODE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE__DOCUMENTATION = COMPOSITE_NODE_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Context Help</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE__CONTEXT_HELP = COMPOSITE_NODE_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Documented Named Composite Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE_FEATURE_COUNT = COMPOSITE_NODE_FEATURE_COUNT + 10;

	/**
	 * The number of operations of the '<em>Documented Named Composite Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_COMPOSITE_NODE_OPERATION_COUNT = COMPOSITE_NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedTunnelImpl <em>Documented Named Tunnel</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.graph.model.impl.DocumentedNamedTunnelImpl
	 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedTunnel()
	 * @generated
	 */
	int DOCUMENTED_NAMED_TUNNEL = 21;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_TUNNEL__ID = TUNNEL__ID;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_TUNNEL__TARGET = TUNNEL__TARGET;

	/**
	 * The feature id for the '<em><b>Bidirectional</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_TUNNEL__BIDIRECTIONAL = TUNNEL__BIDIRECTIONAL;

	/**
	 * The feature id for the '<em><b>Connections</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_TUNNEL__CONNECTIONS = TUNNEL__CONNECTIONS;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_TUNNEL__MARKERS = TUNNEL_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_TUNNEL__URIS = TUNNEL_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_TUNNEL__DESCRIPTION = TUNNEL_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_TUNNEL__UUID = TUNNEL_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_TUNNEL__LABEL_PROTOTYPE = TUNNEL_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_TUNNEL__REPRESENTATIONS = TUNNEL_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_TUNNEL__ANNOTATIONS = TUNNEL_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_TUNNEL__NAME = TUNNEL_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_TUNNEL__DOCUMENTATION = TUNNEL_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Context Help</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_TUNNEL__CONTEXT_HELP = TUNNEL_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>Documented Named Tunnel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_TUNNEL_FEATURE_COUNT = TUNNEL_FEATURE_COUNT + 10;

	/**
	 * The number of operations of the '<em>Documented Named Tunnel</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_TUNNEL_OPERATION_COUNT = TUNNEL_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.GraphElement <em>Graph Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graph Element</em>'.
	 * @see org.nasdanika.graph.model.GraphElement
	 * @generated
	 */
	EClass getGraphElement();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.Graph <em>Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Graph</em>'.
	 * @see org.nasdanika.graph.model.Graph
	 * @generated
	 */
	EClass getGraph();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.graph.model.Graph#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see org.nasdanika.graph.model.Graph#getElements()
	 * @see #getGraph()
	 * @generated
	 */
	EReference getGraph_Elements();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.SubGraph <em>Sub Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sub Graph</em>'.
	 * @see org.nasdanika.graph.model.SubGraph
	 * @generated
	 */
	EClass getSubGraph();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.ConnectionTarget <em>Connection Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection Target</em>'.
	 * @see org.nasdanika.graph.model.ConnectionTarget
	 * @generated
	 */
	EClass getConnectionTarget();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.graph.model.ConnectionTarget#getIncomingConnections <em>Incoming Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming Connections</em>'.
	 * @see org.nasdanika.graph.model.ConnectionTarget#getIncomingConnections()
	 * @see #getConnectionTarget()
	 * @generated
	 */
	EReference getConnectionTarget_IncomingConnections();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.CompositeConnectionTarget <em>Composite Connection Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite Connection Target</em>'.
	 * @see org.nasdanika.graph.model.CompositeConnectionTarget
	 * @generated
	 */
	EClass getCompositeConnectionTarget();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.Connection <em>Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection</em>'.
	 * @see org.nasdanika.graph.model.Connection
	 * @generated
	 */
	EClass getConnection();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.graph.model.Connection#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.nasdanika.graph.model.Connection#getTarget()
	 * @see #getConnection()
	 * @generated
	 */
	EReference getConnection_Target();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.graph.model.Connection#isBidirectional <em>Bidirectional</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Bidirectional</em>'.
	 * @see org.nasdanika.graph.model.Connection#isBidirectional()
	 * @see #getConnection()
	 * @generated
	 */
	EAttribute getConnection_Bidirectional();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.ConnectionSource <em>Connection Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection Source</em>'.
	 * @see org.nasdanika.graph.model.ConnectionSource
	 * @generated
	 */
	EClass getConnectionSource();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.graph.model.ConnectionSource#getOutgoingConnections <em>Outgoing Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Outgoing Connections</em>'.
	 * @see org.nasdanika.graph.model.ConnectionSource#getOutgoingConnections()
	 * @see #getConnectionSource()
	 * @generated
	 */
	EReference getConnectionSource_OutgoingConnections();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.CompositeConnectionSource <em>Composite Connection Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite Connection Source</em>'.
	 * @see org.nasdanika.graph.model.CompositeConnectionSource
	 * @generated
	 */
	EClass getCompositeConnectionSource();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see org.nasdanika.graph.model.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.CompositeNode <em>Composite Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite Node</em>'.
	 * @see org.nasdanika.graph.model.CompositeNode
	 * @generated
	 */
	EClass getCompositeNode();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.Tunnel <em>Tunnel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tunnel</em>'.
	 * @see org.nasdanika.graph.model.Tunnel
	 * @generated
	 */
	EClass getTunnel();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.graph.model.Tunnel#getConnections <em>Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Connections</em>'.
	 * @see org.nasdanika.graph.model.Tunnel#getConnections()
	 * @see #getTunnel()
	 * @generated
	 */
	EReference getTunnel_Connections();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.DocumentedNamedGraphElement <em>Documented Named Graph Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documented Named Graph Element</em>'.
	 * @see org.nasdanika.graph.model.DocumentedNamedGraphElement
	 * @generated
	 */
	EClass getDocumentedNamedGraphElement();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.DocumentedNamedGraph <em>Documented Named Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documented Named Graph</em>'.
	 * @see org.nasdanika.graph.model.DocumentedNamedGraph
	 * @generated
	 */
	EClass getDocumentedNamedGraph();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.DocumentedNamedSubGraph <em>Documented Named Sub Graph</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documented Named Sub Graph</em>'.
	 * @see org.nasdanika.graph.model.DocumentedNamedSubGraph
	 * @generated
	 */
	EClass getDocumentedNamedSubGraph();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.DocumentedNamedConnectionTarget <em>Documented Named Connection Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documented Named Connection Target</em>'.
	 * @see org.nasdanika.graph.model.DocumentedNamedConnectionTarget
	 * @generated
	 */
	EClass getDocumentedNamedConnectionTarget();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.DocumentedNamedCompositeConnectionTarget <em>Documented Named Composite Connection Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documented Named Composite Connection Target</em>'.
	 * @see org.nasdanika.graph.model.DocumentedNamedCompositeConnectionTarget
	 * @generated
	 */
	EClass getDocumentedNamedCompositeConnectionTarget();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.DocumentedNamedConnection <em>Documented Named Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documented Named Connection</em>'.
	 * @see org.nasdanika.graph.model.DocumentedNamedConnection
	 * @generated
	 */
	EClass getDocumentedNamedConnection();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.DocumentedNamedConnectionSource <em>Documented Named Connection Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documented Named Connection Source</em>'.
	 * @see org.nasdanika.graph.model.DocumentedNamedConnectionSource
	 * @generated
	 */
	EClass getDocumentedNamedConnectionSource();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.DocumentedNamedCompositeConnectionSource <em>Documented Named Composite Connection Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documented Named Composite Connection Source</em>'.
	 * @see org.nasdanika.graph.model.DocumentedNamedCompositeConnectionSource
	 * @generated
	 */
	EClass getDocumentedNamedCompositeConnectionSource();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.DocumentedNamedNode <em>Documented Named Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documented Named Node</em>'.
	 * @see org.nasdanika.graph.model.DocumentedNamedNode
	 * @generated
	 */
	EClass getDocumentedNamedNode();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.DocumentedNamedCompositeNode <em>Documented Named Composite Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documented Named Composite Node</em>'.
	 * @see org.nasdanika.graph.model.DocumentedNamedCompositeNode
	 * @generated
	 */
	EClass getDocumentedNamedCompositeNode();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.graph.model.DocumentedNamedTunnel <em>Documented Named Tunnel</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documented Named Tunnel</em>'.
	 * @see org.nasdanika.graph.model.DocumentedNamedTunnel
	 * @generated
	 */
	EClass getDocumentedNamedTunnel();

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
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.GraphElementImpl <em>Graph Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.GraphElementImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getGraphElement()
		 * @generated
		 */
		EClass GRAPH_ELEMENT = eINSTANCE.getGraphElement();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.GraphImpl <em>Graph</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.GraphImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getGraph()
		 * @generated
		 */
		EClass GRAPH = eINSTANCE.getGraph();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAPH__ELEMENTS = eINSTANCE.getGraph_Elements();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.SubGraphImpl <em>Sub Graph</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.SubGraphImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getSubGraph()
		 * @generated
		 */
		EClass SUB_GRAPH = eINSTANCE.getSubGraph();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.ConnectionTargetImpl <em>Connection Target</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.ConnectionTargetImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getConnectionTarget()
		 * @generated
		 */
		EClass CONNECTION_TARGET = eINSTANCE.getConnectionTarget();

		/**
		 * The meta object literal for the '<em><b>Incoming Connections</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION_TARGET__INCOMING_CONNECTIONS = eINSTANCE.getConnectionTarget_IncomingConnections();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.CompositeConnectionTargetImpl <em>Composite Connection Target</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.CompositeConnectionTargetImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getCompositeConnectionTarget()
		 * @generated
		 */
		EClass COMPOSITE_CONNECTION_TARGET = eINSTANCE.getCompositeConnectionTarget();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.ConnectionImpl <em>Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.ConnectionImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getConnection()
		 * @generated
		 */
		EClass CONNECTION = eINSTANCE.getConnection();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION__TARGET = eINSTANCE.getConnection_Target();

		/**
		 * The meta object literal for the '<em><b>Bidirectional</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONNECTION__BIDIRECTIONAL = eINSTANCE.getConnection_Bidirectional();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.ConnectionSourceImpl <em>Connection Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.ConnectionSourceImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getConnectionSource()
		 * @generated
		 */
		EClass CONNECTION_SOURCE = eINSTANCE.getConnectionSource();

		/**
		 * The meta object literal for the '<em><b>Outgoing Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION_SOURCE__OUTGOING_CONNECTIONS = eINSTANCE.getConnectionSource_OutgoingConnections();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.CompositeConnectionSourceImpl <em>Composite Connection Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.CompositeConnectionSourceImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getCompositeConnectionSource()
		 * @generated
		 */
		EClass COMPOSITE_CONNECTION_SOURCE = eINSTANCE.getCompositeConnectionSource();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.NodeImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.CompositeNodeImpl <em>Composite Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.CompositeNodeImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getCompositeNode()
		 * @generated
		 */
		EClass COMPOSITE_NODE = eINSTANCE.getCompositeNode();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.TunnelImpl <em>Tunnel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.TunnelImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getTunnel()
		 * @generated
		 */
		EClass TUNNEL = eINSTANCE.getTunnel();

		/**
		 * The meta object literal for the '<em><b>Connections</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUNNEL__CONNECTIONS = eINSTANCE.getTunnel_Connections();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedGraphElementImpl <em>Documented Named Graph Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.DocumentedNamedGraphElementImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedGraphElement()
		 * @generated
		 */
		EClass DOCUMENTED_NAMED_GRAPH_ELEMENT = eINSTANCE.getDocumentedNamedGraphElement();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedGraphImpl <em>Documented Named Graph</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.DocumentedNamedGraphImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedGraph()
		 * @generated
		 */
		EClass DOCUMENTED_NAMED_GRAPH = eINSTANCE.getDocumentedNamedGraph();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedSubGraphImpl <em>Documented Named Sub Graph</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.DocumentedNamedSubGraphImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedSubGraph()
		 * @generated
		 */
		EClass DOCUMENTED_NAMED_SUB_GRAPH = eINSTANCE.getDocumentedNamedSubGraph();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedConnectionTargetImpl <em>Documented Named Connection Target</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.DocumentedNamedConnectionTargetImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedConnectionTarget()
		 * @generated
		 */
		EClass DOCUMENTED_NAMED_CONNECTION_TARGET = eINSTANCE.getDocumentedNamedConnectionTarget();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedCompositeConnectionTargetImpl <em>Documented Named Composite Connection Target</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.DocumentedNamedCompositeConnectionTargetImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedCompositeConnectionTarget()
		 * @generated
		 */
		EClass DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET = eINSTANCE.getDocumentedNamedCompositeConnectionTarget();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedConnectionImpl <em>Documented Named Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.DocumentedNamedConnectionImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedConnection()
		 * @generated
		 */
		EClass DOCUMENTED_NAMED_CONNECTION = eINSTANCE.getDocumentedNamedConnection();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedConnectionSourceImpl <em>Documented Named Connection Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.DocumentedNamedConnectionSourceImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedConnectionSource()
		 * @generated
		 */
		EClass DOCUMENTED_NAMED_CONNECTION_SOURCE = eINSTANCE.getDocumentedNamedConnectionSource();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedCompositeConnectionSourceImpl <em>Documented Named Composite Connection Source</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.DocumentedNamedCompositeConnectionSourceImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedCompositeConnectionSource()
		 * @generated
		 */
		EClass DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE = eINSTANCE.getDocumentedNamedCompositeConnectionSource();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedNodeImpl <em>Documented Named Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.DocumentedNamedNodeImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedNode()
		 * @generated
		 */
		EClass DOCUMENTED_NAMED_NODE = eINSTANCE.getDocumentedNamedNode();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedCompositeNodeImpl <em>Documented Named Composite Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.DocumentedNamedCompositeNodeImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedCompositeNode()
		 * @generated
		 */
		EClass DOCUMENTED_NAMED_COMPOSITE_NODE = eINSTANCE.getDocumentedNamedCompositeNode();

		/**
		 * The meta object literal for the '{@link org.nasdanika.graph.model.impl.DocumentedNamedTunnelImpl <em>Documented Named Tunnel</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.graph.model.impl.DocumentedNamedTunnelImpl
		 * @see org.nasdanika.graph.model.impl.ModelPackageImpl#getDocumentedNamedTunnel()
		 * @generated
		 */
		EClass DOCUMENTED_NAMED_TUNNEL = eINSTANCE.getDocumentedNamedTunnel();

	}

} //ModelPackage
