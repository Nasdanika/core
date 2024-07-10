/**
 */
package org.nasdanika.graph.model.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypeParameter;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nasdanika.graph.model.CompositeConnectionSource;
import org.nasdanika.graph.model.CompositeConnectionTarget;
import org.nasdanika.graph.model.CompositeNode;
import org.nasdanika.graph.model.Connection;
import org.nasdanika.graph.model.ConnectionSource;
import org.nasdanika.graph.model.ConnectionTarget;
import org.nasdanika.graph.model.DocumentedNamedCompositeConnectionSource;
import org.nasdanika.graph.model.DocumentedNamedCompositeConnectionTarget;
import org.nasdanika.graph.model.DocumentedNamedCompositeNode;
import org.nasdanika.graph.model.DocumentedNamedConnection;
import org.nasdanika.graph.model.DocumentedNamedConnectionSource;
import org.nasdanika.graph.model.DocumentedNamedConnectionTarget;
import org.nasdanika.graph.model.DocumentedNamedGraph;
import org.nasdanika.graph.model.DocumentedNamedGraphElement;
import org.nasdanika.graph.model.DocumentedNamedNode;
import org.nasdanika.graph.model.DocumentedNamedSubGraph;
import org.nasdanika.graph.model.DocumentedNamedTunnel;
import org.nasdanika.graph.model.Graph;
import org.nasdanika.graph.model.GraphElement;
import org.nasdanika.graph.model.ModelFactory;
import org.nasdanika.graph.model.ModelPackage;
import org.nasdanika.graph.model.Node;

import org.nasdanika.graph.model.SubGraph;
import org.nasdanika.graph.model.Tunnel;
import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelPackageImpl extends EPackageImpl implements ModelPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass graphEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subGraphEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionTargetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeConnectionTargetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeConnectionSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tunnelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentedNamedGraphElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentedNamedGraphEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentedNamedSubGraphEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentedNamedConnectionTargetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentedNamedCompositeConnectionTargetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentedNamedConnectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentedNamedConnectionSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentedNamedCompositeConnectionSourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentedNamedNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentedNamedCompositeNodeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentedNamedTunnelEClass = null;

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
	 * @see org.nasdanika.graph.model.ModelPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ModelPackageImpl() {
		super(eNS_URI, ModelFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ModelPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ModelPackage init() {
		if (isInited) return (ModelPackage)EPackage.Registry.INSTANCE.getEPackage(ModelPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredModelPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ModelPackageImpl theModelPackage = registeredModelPackage instanceof ModelPackageImpl ? (ModelPackageImpl)registeredModelPackage : new ModelPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		org.nasdanika.drawio.model.ModelPackage.eINSTANCE.eClass();
		NcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theModelPackage.createPackageContents();

		// Initialize created meta-data
		theModelPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theModelPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, theModelPackage);
		return theModelPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGraphElement() {
		return graphElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGraph() {
		return graphEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getGraph_Elements() {
		return (EReference)graphEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSubGraph() {
		return subGraphEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConnectionTarget() {
		return connectionTargetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConnectionTarget_IncomingConnections() {
		return (EReference)connectionTargetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCompositeConnectionTarget() {
		return compositeConnectionTargetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConnection() {
		return connectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConnection_Target() {
		return (EReference)connectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConnection_Bidirectional() {
		return (EAttribute)connectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConnectionSource() {
		return connectionSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConnectionSource_OutgoingConnections() {
		return (EReference)connectionSourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCompositeConnectionSource() {
		return compositeConnectionSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNode() {
		return nodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCompositeNode() {
		return compositeNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTunnel() {
		return tunnelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTunnel_Connections() {
		return (EReference)tunnelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDocumentedNamedGraphElement() {
		return documentedNamedGraphElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDocumentedNamedGraph() {
		return documentedNamedGraphEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDocumentedNamedSubGraph() {
		return documentedNamedSubGraphEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDocumentedNamedConnectionTarget() {
		return documentedNamedConnectionTargetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDocumentedNamedCompositeConnectionTarget() {
		return documentedNamedCompositeConnectionTargetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDocumentedNamedConnection() {
		return documentedNamedConnectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDocumentedNamedConnectionSource() {
		return documentedNamedConnectionSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDocumentedNamedCompositeConnectionSource() {
		return documentedNamedCompositeConnectionSourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDocumentedNamedNode() {
		return documentedNamedNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDocumentedNamedCompositeNode() {
		return documentedNamedCompositeNodeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDocumentedNamedTunnel() {
		return documentedNamedTunnelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ModelFactory getModelFactory() {
		return (ModelFactory)getEFactoryInstance();
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
		graphElementEClass = createEClass(GRAPH_ELEMENT);

		graphEClass = createEClass(GRAPH);
		createEReference(graphEClass, GRAPH__ELEMENTS);

		subGraphEClass = createEClass(SUB_GRAPH);

		connectionTargetEClass = createEClass(CONNECTION_TARGET);
		createEReference(connectionTargetEClass, CONNECTION_TARGET__INCOMING_CONNECTIONS);

		compositeConnectionTargetEClass = createEClass(COMPOSITE_CONNECTION_TARGET);

		connectionEClass = createEClass(CONNECTION);
		createEReference(connectionEClass, CONNECTION__TARGET);
		createEAttribute(connectionEClass, CONNECTION__BIDIRECTIONAL);

		connectionSourceEClass = createEClass(CONNECTION_SOURCE);
		createEReference(connectionSourceEClass, CONNECTION_SOURCE__OUTGOING_CONNECTIONS);

		compositeConnectionSourceEClass = createEClass(COMPOSITE_CONNECTION_SOURCE);

		nodeEClass = createEClass(NODE);

		compositeNodeEClass = createEClass(COMPOSITE_NODE);

		tunnelEClass = createEClass(TUNNEL);
		createEReference(tunnelEClass, TUNNEL__CONNECTIONS);

		documentedNamedGraphElementEClass = createEClass(DOCUMENTED_NAMED_GRAPH_ELEMENT);

		documentedNamedGraphEClass = createEClass(DOCUMENTED_NAMED_GRAPH);

		documentedNamedSubGraphEClass = createEClass(DOCUMENTED_NAMED_SUB_GRAPH);

		documentedNamedConnectionTargetEClass = createEClass(DOCUMENTED_NAMED_CONNECTION_TARGET);

		documentedNamedCompositeConnectionTargetEClass = createEClass(DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET);

		documentedNamedConnectionEClass = createEClass(DOCUMENTED_NAMED_CONNECTION);

		documentedNamedConnectionSourceEClass = createEClass(DOCUMENTED_NAMED_CONNECTION_SOURCE);

		documentedNamedCompositeConnectionSourceEClass = createEClass(DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE);

		documentedNamedNodeEClass = createEClass(DOCUMENTED_NAMED_NODE);

		documentedNamedCompositeNodeEClass = createEClass(DOCUMENTED_NAMED_COMPOSITE_NODE);

		documentedNamedTunnelEClass = createEClass(DOCUMENTED_NAMED_TUNNEL);
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
		org.nasdanika.drawio.model.ModelPackage theModelPackage_1 = (org.nasdanika.drawio.model.ModelPackage)EPackage.Registry.INSTANCE.getEPackage(org.nasdanika.drawio.model.ModelPackage.eNS_URI);

		// Create type parameters
		ETypeParameter graphEClass_E = addETypeParameter(graphEClass, "E");
		ETypeParameter subGraphEClass_E = addETypeParameter(subGraphEClass, "E");
		ETypeParameter connectionTargetEClass_C = addETypeParameter(connectionTargetEClass, "C");
		ETypeParameter compositeConnectionTargetEClass_E = addETypeParameter(compositeConnectionTargetEClass, "E");
		ETypeParameter compositeConnectionTargetEClass_C = addETypeParameter(compositeConnectionTargetEClass, "C");
		ETypeParameter connectionEClass_T = addETypeParameter(connectionEClass, "T");
		ETypeParameter connectionSourceEClass_C = addETypeParameter(connectionSourceEClass, "C");
		ETypeParameter compositeConnectionSourceEClass_E = addETypeParameter(compositeConnectionSourceEClass, "E");
		ETypeParameter compositeConnectionSourceEClass_C = addETypeParameter(compositeConnectionSourceEClass, "C");
		ETypeParameter nodeEClass_C = addETypeParameter(nodeEClass, "C");
		ETypeParameter compositeNodeEClass_E = addETypeParameter(compositeNodeEClass, "E");
		ETypeParameter compositeNodeEClass_C = addETypeParameter(compositeNodeEClass, "C");
		ETypeParameter tunnelEClass_T = addETypeParameter(tunnelEClass, "T");
		ETypeParameter tunnelEClass_C = addETypeParameter(tunnelEClass, "C");
		ETypeParameter documentedNamedGraphEClass_E = addETypeParameter(documentedNamedGraphEClass, "E");
		ETypeParameter documentedNamedSubGraphEClass_E = addETypeParameter(documentedNamedSubGraphEClass, "E");
		ETypeParameter documentedNamedConnectionTargetEClass_C = addETypeParameter(documentedNamedConnectionTargetEClass, "C");
		ETypeParameter documentedNamedCompositeConnectionTargetEClass_E = addETypeParameter(documentedNamedCompositeConnectionTargetEClass, "E");
		ETypeParameter documentedNamedCompositeConnectionTargetEClass_C = addETypeParameter(documentedNamedCompositeConnectionTargetEClass, "C");
		ETypeParameter documentedNamedConnectionEClass_T = addETypeParameter(documentedNamedConnectionEClass, "T");
		ETypeParameter documentedNamedConnectionSourceEClass_C = addETypeParameter(documentedNamedConnectionSourceEClass, "C");
		ETypeParameter documentedNamedCompositeConnectionSourceEClass_E = addETypeParameter(documentedNamedCompositeConnectionSourceEClass, "E");
		ETypeParameter documentedNamedCompositeConnectionSourceEClass_C = addETypeParameter(documentedNamedCompositeConnectionSourceEClass, "C");
		ETypeParameter documentedNamedNodeEClass_C = addETypeParameter(documentedNamedNodeEClass, "C");
		ETypeParameter documentedNamedCompositeNodeEClass_E = addETypeParameter(documentedNamedCompositeNodeEClass, "E");
		ETypeParameter documentedNamedCompositeNodeEClass_C = addETypeParameter(documentedNamedCompositeNodeEClass, "C");
		ETypeParameter documentedNamedTunnelEClass_T = addETypeParameter(documentedNamedTunnelEClass, "T");
		ETypeParameter documentedNamedTunnelEClass_C = addETypeParameter(documentedNamedTunnelEClass, "C");

		// Set bounds for type parameters
		EGenericType g1 = createEGenericType(this.getGraphElement());
		graphEClass_E.getEBounds().add(g1);
		g1 = createEGenericType(this.getGraphElement());
		subGraphEClass_E.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnection());
		EGenericType g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		connectionTargetEClass_C.getEBounds().add(g1);
		g1 = createEGenericType(this.getGraphElement());
		compositeConnectionTargetEClass_E.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnection());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		compositeConnectionTargetEClass_C.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnectionTarget());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		connectionEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnection());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		connectionSourceEClass_C.getEBounds().add(g1);
		g1 = createEGenericType(this.getGraphElement());
		compositeConnectionSourceEClass_E.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnection());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		compositeConnectionSourceEClass_C.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnection());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		nodeEClass_C.getEBounds().add(g1);
		g1 = createEGenericType(this.getGraphElement());
		compositeNodeEClass_E.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnection());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		compositeNodeEClass_C.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnectionTarget());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		tunnelEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnection());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		tunnelEClass_C.getEBounds().add(g1);
		g1 = createEGenericType(this.getGraphElement());
		documentedNamedGraphEClass_E.getEBounds().add(g1);
		g1 = createEGenericType(this.getGraphElement());
		documentedNamedSubGraphEClass_E.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnection());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		documentedNamedConnectionTargetEClass_C.getEBounds().add(g1);
		g1 = createEGenericType(this.getGraphElement());
		documentedNamedCompositeConnectionTargetEClass_E.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnection());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		documentedNamedCompositeConnectionTargetEClass_C.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnectionTarget());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		documentedNamedConnectionEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnection());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		documentedNamedConnectionSourceEClass_C.getEBounds().add(g1);
		g1 = createEGenericType(this.getGraphElement());
		documentedNamedCompositeConnectionSourceEClass_E.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnection());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		documentedNamedCompositeConnectionSourceEClass_C.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnection());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		documentedNamedNodeEClass_C.getEBounds().add(g1);
		g1 = createEGenericType(this.getGraphElement());
		documentedNamedCompositeNodeEClass_E.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnection());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		documentedNamedCompositeNodeEClass_C.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnectionTarget());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		documentedNamedTunnelEClass_T.getEBounds().add(g1);
		g1 = createEGenericType(this.getConnection());
		g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		documentedNamedTunnelEClass_C.getEBounds().add(g1);

		// Add supertypes to classes
		graphElementEClass.getESuperTypes().add(theNcorePackage.getStringIdentity());
		graphElementEClass.getESuperTypes().add(theModelPackage_1.getSemanticElement());
		g1 = createEGenericType(this.getGraphElement());
		subGraphEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getGraph());
		g2 = createEGenericType(subGraphEClass_E);
		g1.getETypeArguments().add(g2);
		subGraphEClass.getEGenericSuperTypes().add(g1);
		connectionTargetEClass.getESuperTypes().add(this.getGraphElement());
		g1 = createEGenericType(this.getSubGraph());
		g2 = createEGenericType(compositeConnectionTargetEClass_E);
		g1.getETypeArguments().add(g2);
		compositeConnectionTargetEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getConnectionTarget());
		g2 = createEGenericType(compositeConnectionTargetEClass_C);
		g1.getETypeArguments().add(g2);
		compositeConnectionTargetEClass.getEGenericSuperTypes().add(g1);
		connectionEClass.getESuperTypes().add(theNcorePackage.getStringIdentity());
		connectionSourceEClass.getESuperTypes().add(this.getGraphElement());
		g1 = createEGenericType(this.getSubGraph());
		g2 = createEGenericType(compositeConnectionSourceEClass_E);
		g1.getETypeArguments().add(g2);
		compositeConnectionSourceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getConnectionSource());
		g2 = createEGenericType(compositeConnectionSourceEClass_C);
		g1.getETypeArguments().add(g2);
		compositeConnectionSourceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getConnectionSource());
		g2 = createEGenericType(nodeEClass_C);
		g1.getETypeArguments().add(g2);
		nodeEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getConnectionTarget());
		g2 = createEGenericType(nodeEClass_C);
		g1.getETypeArguments().add(g2);
		nodeEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getSubGraph());
		g2 = createEGenericType(compositeNodeEClass_E);
		g1.getETypeArguments().add(g2);
		compositeNodeEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getNode());
		g2 = createEGenericType(compositeNodeEClass_C);
		g1.getETypeArguments().add(g2);
		compositeNodeEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getConnection());
		g2 = createEGenericType(tunnelEClass_T);
		g1.getETypeArguments().add(g2);
		tunnelEClass.getEGenericSuperTypes().add(g1);
		documentedNamedGraphElementEClass.getESuperTypes().add(this.getGraphElement());
		documentedNamedGraphElementEClass.getESuperTypes().add(theNcorePackage.getDocumentedNamedStringIdentity());
		g1 = createEGenericType(this.getGraph());
		g2 = createEGenericType(documentedNamedGraphEClass_E);
		g1.getETypeArguments().add(g2);
		documentedNamedGraphEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theNcorePackage.getDocumentedNamedElement());
		documentedNamedGraphEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getDocumentedNamedGraphElement());
		documentedNamedSubGraphEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getSubGraph());
		g2 = createEGenericType(documentedNamedSubGraphEClass_E);
		g1.getETypeArguments().add(g2);
		documentedNamedSubGraphEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getConnectionTarget());
		g2 = createEGenericType(documentedNamedConnectionTargetEClass_C);
		g1.getETypeArguments().add(g2);
		documentedNamedConnectionTargetEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getDocumentedNamedGraphElement());
		documentedNamedConnectionTargetEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getDocumentedNamedSubGraph());
		g2 = createEGenericType(documentedNamedCompositeConnectionTargetEClass_E);
		g1.getETypeArguments().add(g2);
		documentedNamedCompositeConnectionTargetEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getDocumentedNamedConnectionTarget());
		g2 = createEGenericType(documentedNamedCompositeConnectionTargetEClass_C);
		g1.getETypeArguments().add(g2);
		documentedNamedCompositeConnectionTargetEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getConnection());
		g2 = createEGenericType(documentedNamedConnectionEClass_T);
		g1.getETypeArguments().add(g2);
		documentedNamedConnectionEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(theNcorePackage.getDocumentedNamedStringIdentity());
		documentedNamedConnectionEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getDocumentedNamedGraphElement());
		documentedNamedConnectionSourceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getConnectionSource());
		g2 = createEGenericType(documentedNamedConnectionSourceEClass_C);
		g1.getETypeArguments().add(g2);
		documentedNamedConnectionSourceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getDocumentedNamedSubGraph());
		g2 = createEGenericType(documentedNamedCompositeConnectionSourceEClass_E);
		g1.getETypeArguments().add(g2);
		documentedNamedCompositeConnectionSourceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getCompositeConnectionSource());
		g2 = createEGenericType(documentedNamedCompositeConnectionSourceEClass_E);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(documentedNamedCompositeConnectionSourceEClass_C);
		g1.getETypeArguments().add(g2);
		documentedNamedCompositeConnectionSourceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getNode());
		g2 = createEGenericType(documentedNamedNodeEClass_C);
		g1.getETypeArguments().add(g2);
		documentedNamedNodeEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getDocumentedNamedConnectionSource());
		g2 = createEGenericType(documentedNamedNodeEClass_C);
		g1.getETypeArguments().add(g2);
		documentedNamedNodeEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getDocumentedNamedConnectionTarget());
		g2 = createEGenericType(documentedNamedNodeEClass_C);
		g1.getETypeArguments().add(g2);
		documentedNamedNodeEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getCompositeNode());
		g2 = createEGenericType(documentedNamedCompositeNodeEClass_E);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(documentedNamedCompositeNodeEClass_C);
		g1.getETypeArguments().add(g2);
		documentedNamedCompositeNodeEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getDocumentedNamedNode());
		g2 = createEGenericType(documentedNamedCompositeNodeEClass_C);
		g1.getETypeArguments().add(g2);
		documentedNamedCompositeNodeEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getTunnel());
		g2 = createEGenericType(documentedNamedTunnelEClass_T);
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(documentedNamedTunnelEClass_C);
		g1.getETypeArguments().add(g2);
		documentedNamedTunnelEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getDocumentedNamedConnection());
		g2 = createEGenericType(documentedNamedTunnelEClass_T);
		g1.getETypeArguments().add(g2);
		documentedNamedTunnelEClass.getEGenericSuperTypes().add(g1);

		// Initialize classes, features, and operations; add parameters
		initEClass(graphElementEClass, GraphElement.class, "GraphElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(graphEClass, Graph.class, "Graph", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(graphEClass_E);
		initEReference(getGraph_Elements(), g1, null, "elements", null, 0, -1, Graph.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getGraph_Elements().getEKeys().add(theNcorePackage.getStringIdentity_Id());

		initEClass(subGraphEClass, SubGraph.class, "SubGraph", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(connectionTargetEClass, ConnectionTarget.class, "ConnectionTarget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(connectionTargetEClass_C);
		initEReference(getConnectionTarget_IncomingConnections(), g1, this.getConnection_Target(), "incomingConnections", null, 0, -1, ConnectionTarget.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(compositeConnectionTargetEClass, CompositeConnectionTarget.class, "CompositeConnectionTarget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(connectionEClass, Connection.class, "Connection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(connectionEClass_T);
		initEReference(getConnection_Target(), g1, this.getConnectionTarget_IncomingConnections(), "target", null, 1, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnection_Bidirectional(), ecorePackage.getEBoolean(), "bidirectional", null, 0, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(connectionSourceEClass, ConnectionSource.class, "ConnectionSource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(connectionSourceEClass_C);
		initEReference(getConnectionSource_OutgoingConnections(), g1, null, "outgoingConnections", null, 0, -1, ConnectionSource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getConnectionSource_OutgoingConnections().getEKeys().add(theNcorePackage.getStringIdentity_Id());

		initEClass(compositeConnectionSourceEClass, CompositeConnectionSource.class, "CompositeConnectionSource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(nodeEClass, Node.class, "Node", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(compositeNodeEClass, CompositeNode.class, "CompositeNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(tunnelEClass, Tunnel.class, "Tunnel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(tunnelEClass_C);
		initEReference(getTunnel_Connections(), g1, null, "connections", null, 0, -1, Tunnel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentedNamedGraphElementEClass, DocumentedNamedGraphElement.class, "DocumentedNamedGraphElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(documentedNamedGraphEClass, DocumentedNamedGraph.class, "DocumentedNamedGraph", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(documentedNamedSubGraphEClass, DocumentedNamedSubGraph.class, "DocumentedNamedSubGraph", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(documentedNamedConnectionTargetEClass, DocumentedNamedConnectionTarget.class, "DocumentedNamedConnectionTarget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(documentedNamedCompositeConnectionTargetEClass, DocumentedNamedCompositeConnectionTarget.class, "DocumentedNamedCompositeConnectionTarget", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(documentedNamedConnectionEClass, DocumentedNamedConnection.class, "DocumentedNamedConnection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(documentedNamedConnectionSourceEClass, DocumentedNamedConnectionSource.class, "DocumentedNamedConnectionSource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(documentedNamedCompositeConnectionSourceEClass, DocumentedNamedCompositeConnectionSource.class, "DocumentedNamedCompositeConnectionSource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(documentedNamedNodeEClass, DocumentedNamedNode.class, "DocumentedNamedNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(documentedNamedCompositeNodeEClass, DocumentedNamedCompositeNode.class, "DocumentedNamedCompositeNode", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(documentedNamedTunnelEClass, DocumentedNamedTunnel.class, "DocumentedNamedTunnel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// urn:org.nasdanika
		createUrnorgAnnotations();
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
			   "load-key", "graph"
		   });
	}

} //ModelPackageImpl
