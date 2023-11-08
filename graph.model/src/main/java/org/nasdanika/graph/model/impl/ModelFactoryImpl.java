/**
 */
package org.nasdanika.graph.model.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.nasdanika.graph.model.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelFactoryImpl extends EFactoryImpl implements ModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ModelFactory init() {
		try {
			ModelFactory theModelFactory = (ModelFactory)EPackage.Registry.INSTANCE.getEFactory(ModelPackage.eNS_URI);
			if (theModelFactory != null) {
				return theModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ModelPackage.GRAPH: return createGraph();
			case ModelPackage.SUB_GRAPH: return createSubGraph();
			case ModelPackage.CONNECTION_TARGET: return createConnectionTarget();
			case ModelPackage.COMPOSITE_CONNECTION_TARGET: return createCompositeConnectionTarget();
			case ModelPackage.CONNECTION: return createConnection();
			case ModelPackage.CONNECTION_SOURCE: return createConnectionSource();
			case ModelPackage.COMPOSITE_CONNECTION_SOURCE: return createCompositeConnectionSource();
			case ModelPackage.NODE: return createNode();
			case ModelPackage.COMPOSITE_NODE: return createCompositeNode();
			case ModelPackage.TUNNEL: return createTunnel();
			case ModelPackage.DOCUMENTED_NAMED_GRAPH_ELEMENT: return createDocumentedNamedGraphElement();
			case ModelPackage.DOCUMENTED_NAMED_GRAPH: return createDocumentedNamedGraph();
			case ModelPackage.DOCUMENTED_NAMED_SUB_GRAPH: return createDocumentedNamedSubGraph();
			case ModelPackage.DOCUMENTED_NAMED_CONNECTION_TARGET: return createDocumentedNamedConnectionTarget();
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET: return createDocumentedNamedCompositeConnectionTarget();
			case ModelPackage.DOCUMENTED_NAMED_CONNECTION: return createDocumentedNamedConnection();
			case ModelPackage.DOCUMENTED_NAMED_CONNECTION_SOURCE: return createDocumentedNamedConnectionSource();
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE: return createDocumentedNamedCompositeConnectionSource();
			case ModelPackage.DOCUMENTED_NAMED_NODE: return createDocumentedNamedNode();
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE: return createDocumentedNamedCompositeNode();
			case ModelPackage.DOCUMENTED_NAMED_TUNNEL: return createDocumentedNamedTunnel();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <E extends GraphElement> Graph<E> createGraph() {
		GraphImpl<E> graph = new GraphImpl<E>();
		return graph;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <E extends GraphElement> SubGraph<E> createSubGraph() {
		SubGraphImpl<E> subGraph = new SubGraphImpl<E>();
		return subGraph;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <C extends Connection<?>> ConnectionTarget<C> createConnectionTarget() {
		ConnectionTargetImpl<C> connectionTarget = new ConnectionTargetImpl<C>();
		return connectionTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <E extends GraphElement, C extends Connection<?>> CompositeConnectionTarget<E, C> createCompositeConnectionTarget() {
		CompositeConnectionTargetImpl<E, C> compositeConnectionTarget = new CompositeConnectionTargetImpl<E, C>();
		return compositeConnectionTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <T extends ConnectionTarget<?>> Connection<T> createConnection() {
		ConnectionImpl<T> connection = new ConnectionImpl<T>();
		return connection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <C extends Connection<?>> ConnectionSource<C> createConnectionSource() {
		ConnectionSourceImpl<C> connectionSource = new ConnectionSourceImpl<C>();
		return connectionSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <E extends GraphElement, C extends Connection<?>> CompositeConnectionSource<E, C> createCompositeConnectionSource() {
		CompositeConnectionSourceImpl<E, C> compositeConnectionSource = new CompositeConnectionSourceImpl<E, C>();
		return compositeConnectionSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <C extends Connection<?>> Node<C> createNode() {
		NodeImpl<C> node = new NodeImpl<C>();
		return node;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <E extends GraphElement, C extends Connection<?>> CompositeNode<E, C> createCompositeNode() {
		CompositeNodeImpl<E, C> compositeNode = new CompositeNodeImpl<E, C>();
		return compositeNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <T extends ConnectionTarget<?>, C extends Connection<?>> Tunnel<T, C> createTunnel() {
		TunnelImpl<T, C> tunnel = new TunnelImpl<T, C>();
		return tunnel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DocumentedNamedGraphElement createDocumentedNamedGraphElement() {
		DocumentedNamedGraphElementImpl documentedNamedGraphElement = new DocumentedNamedGraphElementImpl();
		return documentedNamedGraphElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <E extends GraphElement> DocumentedNamedGraph<E> createDocumentedNamedGraph() {
		DocumentedNamedGraphImpl<E> documentedNamedGraph = new DocumentedNamedGraphImpl<E>();
		return documentedNamedGraph;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <E extends GraphElement> DocumentedNamedSubGraph<E> createDocumentedNamedSubGraph() {
		DocumentedNamedSubGraphImpl<E> documentedNamedSubGraph = new DocumentedNamedSubGraphImpl<E>();
		return documentedNamedSubGraph;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <C extends Connection<?>> DocumentedNamedConnectionTarget<C> createDocumentedNamedConnectionTarget() {
		DocumentedNamedConnectionTargetImpl<C> documentedNamedConnectionTarget = new DocumentedNamedConnectionTargetImpl<C>();
		return documentedNamedConnectionTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <E extends GraphElement, C extends Connection<?>> DocumentedNamedCompositeConnectionTarget<E, C> createDocumentedNamedCompositeConnectionTarget() {
		DocumentedNamedCompositeConnectionTargetImpl<E, C> documentedNamedCompositeConnectionTarget = new DocumentedNamedCompositeConnectionTargetImpl<E, C>();
		return documentedNamedCompositeConnectionTarget;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <T extends ConnectionTarget<?>> DocumentedNamedConnection<T> createDocumentedNamedConnection() {
		DocumentedNamedConnectionImpl<T> documentedNamedConnection = new DocumentedNamedConnectionImpl<T>();
		return documentedNamedConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <C extends Connection<?>> DocumentedNamedConnectionSource<C> createDocumentedNamedConnectionSource() {
		DocumentedNamedConnectionSourceImpl<C> documentedNamedConnectionSource = new DocumentedNamedConnectionSourceImpl<C>();
		return documentedNamedConnectionSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <E extends GraphElement, C extends Connection<?>> DocumentedNamedCompositeConnectionSource<E, C> createDocumentedNamedCompositeConnectionSource() {
		DocumentedNamedCompositeConnectionSourceImpl<E, C> documentedNamedCompositeConnectionSource = new DocumentedNamedCompositeConnectionSourceImpl<E, C>();
		return documentedNamedCompositeConnectionSource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <C extends Connection<?>> DocumentedNamedNode<C> createDocumentedNamedNode() {
		DocumentedNamedNodeImpl<C> documentedNamedNode = new DocumentedNamedNodeImpl<C>();
		return documentedNamedNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <E extends GraphElement, C extends Connection<?>> DocumentedNamedCompositeNode<E, C> createDocumentedNamedCompositeNode() {
		DocumentedNamedCompositeNodeImpl<E, C> documentedNamedCompositeNode = new DocumentedNamedCompositeNodeImpl<E, C>();
		return documentedNamedCompositeNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <T extends ConnectionTarget<?>, C extends Connection<?>> DocumentedNamedTunnel<T, C> createDocumentedNamedTunnel() {
		DocumentedNamedTunnelImpl<T, C> documentedNamedTunnel = new DocumentedNamedTunnelImpl<T, C>();
		return documentedNamedTunnel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ModelPackage getModelPackage() {
		return (ModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModelPackage getPackage() {
		return ModelPackage.eINSTANCE;
	}

} //ModelFactoryImpl
