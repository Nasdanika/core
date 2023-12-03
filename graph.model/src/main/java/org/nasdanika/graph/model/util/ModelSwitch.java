/**
 */
package org.nasdanika.graph.model.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.nasdanika.common.Adaptable;
import org.nasdanika.drawio.model.SemanticElement;
import org.nasdanika.graph.model.*;

import org.nasdanika.ncore.Documented;
import org.nasdanika.ncore.DocumentedNamedElement;
import org.nasdanika.ncore.DocumentedNamedStringIdentity;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.StringIdentity;
import org.nasdanika.persistence.Marked;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.nasdanika.graph.model.ModelPackage
 * @generated
 */
public class ModelSwitch<T1> extends Switch<T1> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelSwitch() {
		if (modelPackage == null) {
			modelPackage = ModelPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T1 doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case ModelPackage.GRAPH_ELEMENT: {
				GraphElement graphElement = (GraphElement)theEObject;
				T1 result = caseGraphElement(graphElement);
				if (result == null) result = caseStringIdentity(graphElement);
				if (result == null) result = caseSemanticElement(graphElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.GRAPH: {
				Graph<?> graph = (Graph<?>)theEObject;
				T1 result = caseGraph(graph);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.SUB_GRAPH: {
				SubGraph<?> subGraph = (SubGraph<?>)theEObject;
				T1 result = caseSubGraph(subGraph);
				if (result == null) result = caseGraphElement(subGraph);
				if (result == null) result = caseGraph(subGraph);
				if (result == null) result = caseStringIdentity(subGraph);
				if (result == null) result = caseSemanticElement(subGraph);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.CONNECTION_TARGET: {
				ConnectionTarget<?> connectionTarget = (ConnectionTarget<?>)theEObject;
				T1 result = caseConnectionTarget(connectionTarget);
				if (result == null) result = caseGraphElement(connectionTarget);
				if (result == null) result = caseStringIdentity(connectionTarget);
				if (result == null) result = caseSemanticElement(connectionTarget);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.COMPOSITE_CONNECTION_TARGET: {
				CompositeConnectionTarget<?, ?> compositeConnectionTarget = (CompositeConnectionTarget<?, ?>)theEObject;
				T1 result = caseCompositeConnectionTarget(compositeConnectionTarget);
				if (result == null) result = caseSubGraph(compositeConnectionTarget);
				if (result == null) result = caseConnectionTarget(compositeConnectionTarget);
				if (result == null) result = caseGraphElement(compositeConnectionTarget);
				if (result == null) result = caseGraph(compositeConnectionTarget);
				if (result == null) result = caseStringIdentity(compositeConnectionTarget);
				if (result == null) result = caseSemanticElement(compositeConnectionTarget);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.CONNECTION: {
				Connection<?> connection = (Connection<?>)theEObject;
				T1 result = caseConnection(connection);
				if (result == null) result = caseStringIdentity(connection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.CONNECTION_SOURCE: {
				ConnectionSource<?> connectionSource = (ConnectionSource<?>)theEObject;
				T1 result = caseConnectionSource(connectionSource);
				if (result == null) result = caseGraphElement(connectionSource);
				if (result == null) result = caseStringIdentity(connectionSource);
				if (result == null) result = caseSemanticElement(connectionSource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.COMPOSITE_CONNECTION_SOURCE: {
				CompositeConnectionSource<?, ?> compositeConnectionSource = (CompositeConnectionSource<?, ?>)theEObject;
				T1 result = caseCompositeConnectionSource(compositeConnectionSource);
				if (result == null) result = caseSubGraph(compositeConnectionSource);
				if (result == null) result = caseConnectionSource(compositeConnectionSource);
				if (result == null) result = caseGraphElement(compositeConnectionSource);
				if (result == null) result = caseGraph(compositeConnectionSource);
				if (result == null) result = caseStringIdentity(compositeConnectionSource);
				if (result == null) result = caseSemanticElement(compositeConnectionSource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.NODE: {
				Node<?> node = (Node<?>)theEObject;
				T1 result = caseNode(node);
				if (result == null) result = caseConnectionSource(node);
				if (result == null) result = caseConnectionTarget(node);
				if (result == null) result = caseGraphElement(node);
				if (result == null) result = caseStringIdentity(node);
				if (result == null) result = caseSemanticElement(node);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.COMPOSITE_NODE: {
				CompositeNode<?, ?> compositeNode = (CompositeNode<?, ?>)theEObject;
				T1 result = caseCompositeNode(compositeNode);
				if (result == null) result = caseSubGraph(compositeNode);
				if (result == null) result = caseNode(compositeNode);
				if (result == null) result = caseGraph(compositeNode);
				if (result == null) result = caseConnectionSource(compositeNode);
				if (result == null) result = caseConnectionTarget(compositeNode);
				if (result == null) result = caseGraphElement(compositeNode);
				if (result == null) result = caseStringIdentity(compositeNode);
				if (result == null) result = caseSemanticElement(compositeNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.TUNNEL: {
				Tunnel<?, ?> tunnel = (Tunnel<?, ?>)theEObject;
				T1 result = caseTunnel(tunnel);
				if (result == null) result = caseConnection(tunnel);
				if (result == null) result = caseStringIdentity(tunnel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DOCUMENTED_NAMED_GRAPH_ELEMENT: {
				DocumentedNamedGraphElement documentedNamedGraphElement = (DocumentedNamedGraphElement)theEObject;
				T1 result = caseDocumentedNamedGraphElement(documentedNamedGraphElement);
				if (result == null) result = caseGraphElement(documentedNamedGraphElement);
				if (result == null) result = caseDocumentedNamedStringIdentity(documentedNamedGraphElement);
				if (result == null) result = caseStringIdentity(documentedNamedGraphElement);
				if (result == null) result = caseSemanticElement(documentedNamedGraphElement);
				if (result == null) result = caseDocumentedNamedElement(documentedNamedGraphElement);
				if (result == null) result = caseNamedElement(documentedNamedGraphElement);
				if (result == null) result = caseDocumented(documentedNamedGraphElement);
				if (result == null) result = caseModelElement(documentedNamedGraphElement);
				if (result == null) result = caseMarked(documentedNamedGraphElement);
				if (result == null) result = caseAdaptable(documentedNamedGraphElement);
				if (result == null) result = caseIMarked(documentedNamedGraphElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DOCUMENTED_NAMED_GRAPH: {
				DocumentedNamedGraph<?> documentedNamedGraph = (DocumentedNamedGraph<?>)theEObject;
				T1 result = caseDocumentedNamedGraph(documentedNamedGraph);
				if (result == null) result = caseGraph(documentedNamedGraph);
				if (result == null) result = caseDocumentedNamedElement(documentedNamedGraph);
				if (result == null) result = caseNamedElement(documentedNamedGraph);
				if (result == null) result = caseDocumented(documentedNamedGraph);
				if (result == null) result = caseModelElement(documentedNamedGraph);
				if (result == null) result = caseMarked(documentedNamedGraph);
				if (result == null) result = caseAdaptable(documentedNamedGraph);
				if (result == null) result = caseIMarked(documentedNamedGraph);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DOCUMENTED_NAMED_SUB_GRAPH: {
				DocumentedNamedSubGraph<?> documentedNamedSubGraph = (DocumentedNamedSubGraph<?>)theEObject;
				T1 result = caseDocumentedNamedSubGraph(documentedNamedSubGraph);
				if (result == null) result = caseDocumentedNamedGraphElement(documentedNamedSubGraph);
				if (result == null) result = caseSubGraph(documentedNamedSubGraph);
				if (result == null) result = caseGraphElement(documentedNamedSubGraph);
				if (result == null) result = caseDocumentedNamedStringIdentity(documentedNamedSubGraph);
				if (result == null) result = caseGraph(documentedNamedSubGraph);
				if (result == null) result = caseStringIdentity(documentedNamedSubGraph);
				if (result == null) result = caseSemanticElement(documentedNamedSubGraph);
				if (result == null) result = caseDocumentedNamedElement(documentedNamedSubGraph);
				if (result == null) result = caseNamedElement(documentedNamedSubGraph);
				if (result == null) result = caseDocumented(documentedNamedSubGraph);
				if (result == null) result = caseModelElement(documentedNamedSubGraph);
				if (result == null) result = caseMarked(documentedNamedSubGraph);
				if (result == null) result = caseAdaptable(documentedNamedSubGraph);
				if (result == null) result = caseIMarked(documentedNamedSubGraph);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DOCUMENTED_NAMED_CONNECTION_TARGET: {
				DocumentedNamedConnectionTarget<?> documentedNamedConnectionTarget = (DocumentedNamedConnectionTarget<?>)theEObject;
				T1 result = caseDocumentedNamedConnectionTarget(documentedNamedConnectionTarget);
				if (result == null) result = caseConnectionTarget(documentedNamedConnectionTarget);
				if (result == null) result = caseDocumentedNamedGraphElement(documentedNamedConnectionTarget);
				if (result == null) result = caseGraphElement(documentedNamedConnectionTarget);
				if (result == null) result = caseDocumentedNamedStringIdentity(documentedNamedConnectionTarget);
				if (result == null) result = caseStringIdentity(documentedNamedConnectionTarget);
				if (result == null) result = caseSemanticElement(documentedNamedConnectionTarget);
				if (result == null) result = caseDocumentedNamedElement(documentedNamedConnectionTarget);
				if (result == null) result = caseNamedElement(documentedNamedConnectionTarget);
				if (result == null) result = caseDocumented(documentedNamedConnectionTarget);
				if (result == null) result = caseModelElement(documentedNamedConnectionTarget);
				if (result == null) result = caseMarked(documentedNamedConnectionTarget);
				if (result == null) result = caseAdaptable(documentedNamedConnectionTarget);
				if (result == null) result = caseIMarked(documentedNamedConnectionTarget);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_CONNECTION_TARGET: {
				DocumentedNamedCompositeConnectionTarget<?, ?> documentedNamedCompositeConnectionTarget = (DocumentedNamedCompositeConnectionTarget<?, ?>)theEObject;
				T1 result = caseDocumentedNamedCompositeConnectionTarget(documentedNamedCompositeConnectionTarget);
				if (result == null) result = caseDocumentedNamedSubGraph(documentedNamedCompositeConnectionTarget);
				if (result == null) result = caseDocumentedNamedConnectionTarget(documentedNamedCompositeConnectionTarget);
				if (result == null) result = caseDocumentedNamedGraphElement(documentedNamedCompositeConnectionTarget);
				if (result == null) result = caseSubGraph(documentedNamedCompositeConnectionTarget);
				if (result == null) result = caseConnectionTarget(documentedNamedCompositeConnectionTarget);
				if (result == null) result = caseGraphElement(documentedNamedCompositeConnectionTarget);
				if (result == null) result = caseDocumentedNamedStringIdentity(documentedNamedCompositeConnectionTarget);
				if (result == null) result = caseGraph(documentedNamedCompositeConnectionTarget);
				if (result == null) result = caseStringIdentity(documentedNamedCompositeConnectionTarget);
				if (result == null) result = caseSemanticElement(documentedNamedCompositeConnectionTarget);
				if (result == null) result = caseDocumentedNamedElement(documentedNamedCompositeConnectionTarget);
				if (result == null) result = caseNamedElement(documentedNamedCompositeConnectionTarget);
				if (result == null) result = caseDocumented(documentedNamedCompositeConnectionTarget);
				if (result == null) result = caseModelElement(documentedNamedCompositeConnectionTarget);
				if (result == null) result = caseMarked(documentedNamedCompositeConnectionTarget);
				if (result == null) result = caseAdaptable(documentedNamedCompositeConnectionTarget);
				if (result == null) result = caseIMarked(documentedNamedCompositeConnectionTarget);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DOCUMENTED_NAMED_CONNECTION: {
				DocumentedNamedConnection<?> documentedNamedConnection = (DocumentedNamedConnection<?>)theEObject;
				T1 result = caseDocumentedNamedConnection(documentedNamedConnection);
				if (result == null) result = caseConnection(documentedNamedConnection);
				if (result == null) result = caseDocumentedNamedStringIdentity(documentedNamedConnection);
				if (result == null) result = caseStringIdentity(documentedNamedConnection);
				if (result == null) result = caseDocumentedNamedElement(documentedNamedConnection);
				if (result == null) result = caseNamedElement(documentedNamedConnection);
				if (result == null) result = caseDocumented(documentedNamedConnection);
				if (result == null) result = caseModelElement(documentedNamedConnection);
				if (result == null) result = caseMarked(documentedNamedConnection);
				if (result == null) result = caseAdaptable(documentedNamedConnection);
				if (result == null) result = caseIMarked(documentedNamedConnection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DOCUMENTED_NAMED_CONNECTION_SOURCE: {
				DocumentedNamedConnectionSource<?> documentedNamedConnectionSource = (DocumentedNamedConnectionSource<?>)theEObject;
				T1 result = caseDocumentedNamedConnectionSource(documentedNamedConnectionSource);
				if (result == null) result = caseDocumentedNamedGraphElement(documentedNamedConnectionSource);
				if (result == null) result = caseConnectionSource(documentedNamedConnectionSource);
				if (result == null) result = caseGraphElement(documentedNamedConnectionSource);
				if (result == null) result = caseDocumentedNamedStringIdentity(documentedNamedConnectionSource);
				if (result == null) result = caseStringIdentity(documentedNamedConnectionSource);
				if (result == null) result = caseSemanticElement(documentedNamedConnectionSource);
				if (result == null) result = caseDocumentedNamedElement(documentedNamedConnectionSource);
				if (result == null) result = caseNamedElement(documentedNamedConnectionSource);
				if (result == null) result = caseDocumented(documentedNamedConnectionSource);
				if (result == null) result = caseModelElement(documentedNamedConnectionSource);
				if (result == null) result = caseMarked(documentedNamedConnectionSource);
				if (result == null) result = caseAdaptable(documentedNamedConnectionSource);
				if (result == null) result = caseIMarked(documentedNamedConnectionSource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_CONNECTION_SOURCE: {
				DocumentedNamedCompositeConnectionSource<?, ?> documentedNamedCompositeConnectionSource = (DocumentedNamedCompositeConnectionSource<?, ?>)theEObject;
				T1 result = caseDocumentedNamedCompositeConnectionSource(documentedNamedCompositeConnectionSource);
				if (result == null) result = caseDocumentedNamedSubGraph(documentedNamedCompositeConnectionSource);
				if (result == null) result = caseCompositeConnectionSource(documentedNamedCompositeConnectionSource);
				if (result == null) result = caseDocumentedNamedGraphElement(documentedNamedCompositeConnectionSource);
				if (result == null) result = caseSubGraph(documentedNamedCompositeConnectionSource);
				if (result == null) result = caseConnectionSource(documentedNamedCompositeConnectionSource);
				if (result == null) result = caseGraphElement(documentedNamedCompositeConnectionSource);
				if (result == null) result = caseDocumentedNamedStringIdentity(documentedNamedCompositeConnectionSource);
				if (result == null) result = caseGraph(documentedNamedCompositeConnectionSource);
				if (result == null) result = caseStringIdentity(documentedNamedCompositeConnectionSource);
				if (result == null) result = caseSemanticElement(documentedNamedCompositeConnectionSource);
				if (result == null) result = caseDocumentedNamedElement(documentedNamedCompositeConnectionSource);
				if (result == null) result = caseNamedElement(documentedNamedCompositeConnectionSource);
				if (result == null) result = caseDocumented(documentedNamedCompositeConnectionSource);
				if (result == null) result = caseModelElement(documentedNamedCompositeConnectionSource);
				if (result == null) result = caseMarked(documentedNamedCompositeConnectionSource);
				if (result == null) result = caseAdaptable(documentedNamedCompositeConnectionSource);
				if (result == null) result = caseIMarked(documentedNamedCompositeConnectionSource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DOCUMENTED_NAMED_NODE: {
				DocumentedNamedNode<?> documentedNamedNode = (DocumentedNamedNode<?>)theEObject;
				T1 result = caseDocumentedNamedNode(documentedNamedNode);
				if (result == null) result = caseNode(documentedNamedNode);
				if (result == null) result = caseDocumentedNamedConnectionSource(documentedNamedNode);
				if (result == null) result = caseDocumentedNamedConnectionTarget(documentedNamedNode);
				if (result == null) result = caseConnectionSource(documentedNamedNode);
				if (result == null) result = caseConnectionTarget(documentedNamedNode);
				if (result == null) result = caseDocumentedNamedGraphElement(documentedNamedNode);
				if (result == null) result = caseGraphElement(documentedNamedNode);
				if (result == null) result = caseDocumentedNamedStringIdentity(documentedNamedNode);
				if (result == null) result = caseStringIdentity(documentedNamedNode);
				if (result == null) result = caseSemanticElement(documentedNamedNode);
				if (result == null) result = caseDocumentedNamedElement(documentedNamedNode);
				if (result == null) result = caseNamedElement(documentedNamedNode);
				if (result == null) result = caseDocumented(documentedNamedNode);
				if (result == null) result = caseModelElement(documentedNamedNode);
				if (result == null) result = caseMarked(documentedNamedNode);
				if (result == null) result = caseAdaptable(documentedNamedNode);
				if (result == null) result = caseIMarked(documentedNamedNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DOCUMENTED_NAMED_COMPOSITE_NODE: {
				DocumentedNamedCompositeNode<?, ?> documentedNamedCompositeNode = (DocumentedNamedCompositeNode<?, ?>)theEObject;
				T1 result = caseDocumentedNamedCompositeNode(documentedNamedCompositeNode);
				if (result == null) result = caseCompositeNode(documentedNamedCompositeNode);
				if (result == null) result = caseDocumentedNamedNode(documentedNamedCompositeNode);
				if (result == null) result = caseSubGraph(documentedNamedCompositeNode);
				if (result == null) result = caseNode(documentedNamedCompositeNode);
				if (result == null) result = caseDocumentedNamedConnectionSource(documentedNamedCompositeNode);
				if (result == null) result = caseDocumentedNamedConnectionTarget(documentedNamedCompositeNode);
				if (result == null) result = caseGraph(documentedNamedCompositeNode);
				if (result == null) result = caseConnectionSource(documentedNamedCompositeNode);
				if (result == null) result = caseConnectionTarget(documentedNamedCompositeNode);
				if (result == null) result = caseDocumentedNamedGraphElement(documentedNamedCompositeNode);
				if (result == null) result = caseGraphElement(documentedNamedCompositeNode);
				if (result == null) result = caseSemanticElement(documentedNamedCompositeNode);
				if (result == null) result = caseDocumentedNamedStringIdentity(documentedNamedCompositeNode);
				if (result == null) result = caseStringIdentity(documentedNamedCompositeNode);
				if (result == null) result = caseDocumentedNamedElement(documentedNamedCompositeNode);
				if (result == null) result = caseNamedElement(documentedNamedCompositeNode);
				if (result == null) result = caseDocumented(documentedNamedCompositeNode);
				if (result == null) result = caseModelElement(documentedNamedCompositeNode);
				if (result == null) result = caseMarked(documentedNamedCompositeNode);
				if (result == null) result = caseAdaptable(documentedNamedCompositeNode);
				if (result == null) result = caseIMarked(documentedNamedCompositeNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case ModelPackage.DOCUMENTED_NAMED_TUNNEL: {
				DocumentedNamedTunnel<?, ?> documentedNamedTunnel = (DocumentedNamedTunnel<?, ?>)theEObject;
				T1 result = caseDocumentedNamedTunnel(documentedNamedTunnel);
				if (result == null) result = caseTunnel(documentedNamedTunnel);
				if (result == null) result = caseDocumentedNamedConnection(documentedNamedTunnel);
				if (result == null) result = caseConnection(documentedNamedTunnel);
				if (result == null) result = caseDocumentedNamedStringIdentity(documentedNamedTunnel);
				if (result == null) result = caseStringIdentity(documentedNamedTunnel);
				if (result == null) result = caseDocumentedNamedElement(documentedNamedTunnel);
				if (result == null) result = caseNamedElement(documentedNamedTunnel);
				if (result == null) result = caseDocumented(documentedNamedTunnel);
				if (result == null) result = caseModelElement(documentedNamedTunnel);
				if (result == null) result = caseMarked(documentedNamedTunnel);
				if (result == null) result = caseAdaptable(documentedNamedTunnel);
				if (result == null) result = caseIMarked(documentedNamedTunnel);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Graph Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Graph Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseGraphElement(GraphElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Graph</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Graph</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <E extends GraphElement> T1 caseGraph(Graph<E> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sub Graph</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sub Graph</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <E extends GraphElement> T1 caseSubGraph(SubGraph<E> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection Target</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection Target</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <C extends Connection<?>> T1 caseConnectionTarget(ConnectionTarget<C> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composite Connection Target</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composite Connection Target</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <E extends GraphElement, C extends Connection<?>> T1 caseCompositeConnectionTarget(CompositeConnectionTarget<E, C> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T extends ConnectionTarget<?>> T1 caseConnection(Connection<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connection Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connection Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <C extends Connection<?>> T1 caseConnectionSource(ConnectionSource<C> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composite Connection Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composite Connection Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <E extends GraphElement, C extends Connection<?>> T1 caseCompositeConnectionSource(CompositeConnectionSource<E, C> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <C extends Connection<?>> T1 caseNode(Node<C> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Composite Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Composite Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <E extends GraphElement, C extends Connection<?>> T1 caseCompositeNode(CompositeNode<E, C> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tunnel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tunnel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T extends ConnectionTarget<?>, C extends Connection<?>> T1 caseTunnel(Tunnel<T, C> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented Named Graph Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented Named Graph Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDocumentedNamedGraphElement(DocumentedNamedGraphElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented Named Graph</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented Named Graph</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <E extends GraphElement> T1 caseDocumentedNamedGraph(DocumentedNamedGraph<E> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented Named Sub Graph</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented Named Sub Graph</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <E extends GraphElement> T1 caseDocumentedNamedSubGraph(DocumentedNamedSubGraph<E> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented Named Connection Target</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented Named Connection Target</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <C extends Connection<?>> T1 caseDocumentedNamedConnectionTarget(DocumentedNamedConnectionTarget<C> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented Named Composite Connection Target</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented Named Composite Connection Target</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <E extends GraphElement, C extends Connection<?>> T1 caseDocumentedNamedCompositeConnectionTarget(DocumentedNamedCompositeConnectionTarget<E, C> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented Named Connection</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented Named Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T extends ConnectionTarget<?>> T1 caseDocumentedNamedConnection(DocumentedNamedConnection<T> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented Named Connection Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented Named Connection Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <C extends Connection<?>> T1 caseDocumentedNamedConnectionSource(DocumentedNamedConnectionSource<C> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented Named Composite Connection Source</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented Named Composite Connection Source</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <E extends GraphElement, C extends Connection<?>> T1 caseDocumentedNamedCompositeConnectionSource(DocumentedNamedCompositeConnectionSource<E, C> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented Named Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented Named Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <C extends Connection<?>> T1 caseDocumentedNamedNode(DocumentedNamedNode<C> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented Named Composite Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented Named Composite Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <E extends GraphElement, C extends Connection<?>> T1 caseDocumentedNamedCompositeNode(DocumentedNamedCompositeNode<E, C> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented Named Tunnel</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented Named Tunnel</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public <T extends ConnectionTarget<?>, C extends Connection<?>> T1 caseDocumentedNamedTunnel(DocumentedNamedTunnel<T, C> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>String Identity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>String Identity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseStringIdentity(StringIdentity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Semantic Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Semantic Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseSemanticElement(SemanticElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IMarked</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IMarked</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseIMarked(Marked object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Marked</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Marked</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseMarked(org.nasdanika.ncore.Marked object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Adaptable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Adaptable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseAdaptable(Adaptable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseModelElement(ModelElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDocumented(Documented object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDocumentedNamedElement(DocumentedNamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documented Named String Identity</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documented Named String Identity</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T1 caseDocumentedNamedStringIdentity(DocumentedNamedStringIdentity object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T1 defaultCase(EObject object) {
		return null;
	}

} //ModelSwitch
