/**
 */
package org.nasdanika.graph.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.nasdanika.graph.model.ModelPackage
 * @generated
 */
public interface ModelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelFactory eINSTANCE = org.nasdanika.graph.model.impl.ModelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Graph</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Graph</em>'.
	 * @generated
	 */
	<E extends GraphElement> Graph<E> createGraph();

	/**
	 * Returns a new object of class '<em>Sub Graph</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sub Graph</em>'.
	 * @generated
	 */
	<E extends GraphElement> SubGraph<E> createSubGraph();

	/**
	 * Returns a new object of class '<em>Connection Target</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connection Target</em>'.
	 * @generated
	 */
	<C extends Connection<?>> ConnectionTarget<C> createConnectionTarget();

	/**
	 * Returns a new object of class '<em>Composite Connection Target</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Composite Connection Target</em>'.
	 * @generated
	 */
	<E extends GraphElement, C extends Connection<?>> CompositeConnectionTarget<E, C> createCompositeConnectionTarget();

	/**
	 * Returns a new object of class '<em>Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connection</em>'.
	 * @generated
	 */
	<T extends ConnectionTarget<?>> Connection<T> createConnection();

	/**
	 * Returns a new object of class '<em>Connection Source</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connection Source</em>'.
	 * @generated
	 */
	<C extends Connection<?>> ConnectionSource<C> createConnectionSource();

	/**
	 * Returns a new object of class '<em>Composite Connection Source</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Composite Connection Source</em>'.
	 * @generated
	 */
	<E extends GraphElement, C extends Connection<?>> CompositeConnectionSource<E, C> createCompositeConnectionSource();

	/**
	 * Returns a new object of class '<em>Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Node</em>'.
	 * @generated
	 */
	<C extends Connection<?>> Node<C> createNode();

	/**
	 * Returns a new object of class '<em>Composite Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Composite Node</em>'.
	 * @generated
	 */
	<E extends GraphElement, C extends Connection<?>> CompositeNode<E, C> createCompositeNode();

	/**
	 * Returns a new object of class '<em>Tunnel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tunnel</em>'.
	 * @generated
	 */
	<T extends ConnectionTarget<?>, C extends Connection<?>> Tunnel<T, C> createTunnel();

	/**
	 * Returns a new object of class '<em>Documented Named Graph Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Documented Named Graph Element</em>'.
	 * @generated
	 */
	DocumentedNamedGraphElement createDocumentedNamedGraphElement();

	/**
	 * Returns a new object of class '<em>Documented Named Graph</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Documented Named Graph</em>'.
	 * @generated
	 */
	<E extends GraphElement> DocumentedNamedGraph<E> createDocumentedNamedGraph();

	/**
	 * Returns a new object of class '<em>Documented Named Sub Graph</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Documented Named Sub Graph</em>'.
	 * @generated
	 */
	<E extends GraphElement> DocumentedNamedSubGraph<E> createDocumentedNamedSubGraph();

	/**
	 * Returns a new object of class '<em>Documented Named Connection Target</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Documented Named Connection Target</em>'.
	 * @generated
	 */
	<C extends Connection<?>> DocumentedNamedConnectionTarget<C> createDocumentedNamedConnectionTarget();

	/**
	 * Returns a new object of class '<em>Documented Named Composite Connection Target</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Documented Named Composite Connection Target</em>'.
	 * @generated
	 */
	<E extends GraphElement, C extends Connection<?>> DocumentedNamedCompositeConnectionTarget<E, C> createDocumentedNamedCompositeConnectionTarget();

	/**
	 * Returns a new object of class '<em>Documented Named Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Documented Named Connection</em>'.
	 * @generated
	 */
	<T extends ConnectionTarget<?>> DocumentedNamedConnection<T> createDocumentedNamedConnection();

	/**
	 * Returns a new object of class '<em>Documented Named Connection Source</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Documented Named Connection Source</em>'.
	 * @generated
	 */
	<C extends Connection<?>> DocumentedNamedConnectionSource<C> createDocumentedNamedConnectionSource();

	/**
	 * Returns a new object of class '<em>Documented Named Composite Connection Source</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Documented Named Composite Connection Source</em>'.
	 * @generated
	 */
	<E extends GraphElement, C extends Connection<?>> DocumentedNamedCompositeConnectionSource<E, C> createDocumentedNamedCompositeConnectionSource();

	/**
	 * Returns a new object of class '<em>Documented Named Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Documented Named Node</em>'.
	 * @generated
	 */
	<C extends Connection<?>> DocumentedNamedNode<C> createDocumentedNamedNode();

	/**
	 * Returns a new object of class '<em>Documented Named Composite Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Documented Named Composite Node</em>'.
	 * @generated
	 */
	<E extends GraphElement, C extends Connection<?>> DocumentedNamedCompositeNode<E, C> createDocumentedNamedCompositeNode();

	/**
	 * Returns a new object of class '<em>Documented Named Tunnel</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Documented Named Tunnel</em>'.
	 * @generated
	 */
	<T extends ConnectionTarget<?>, C extends Connection<?>> DocumentedNamedTunnel<T, C> createDocumentedNamedTunnel();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ModelPackage getModelPackage();

} //ModelFactory
