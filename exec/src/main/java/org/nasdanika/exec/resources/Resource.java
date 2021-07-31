/**
 */
package org.nasdanika.exec.resources;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.nasdanika.exec.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.exec.resources.Resource#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.exec.resources.Resource#getContents <em>Contents</em>}</li>
 *   <li>{@link org.nasdanika.exec.resources.Resource#getReconcileAction <em>Reconcile Action</em>}</li>
 *   <li>{@link org.nasdanika.exec.resources.Resource#getMerger <em>Merger</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.exec.resources.ResourcesPackage#getResource()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/resources/resource.md'"
 * @generated
 */
public interface Resource extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resource name. Interpolated.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.nasdanika.exec.resources.ResourcesPackage#getResource_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.resources.Resource#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Contents</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resource contents. File contents elements are adapted to ``SupplierFactory`` and produced ``InputStream``s are contcatenated. Container contents elements are adapted to ConsumerFactory and the container is passed to their ``execute`` method.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Contents</em>' containment reference list.
	 * @see org.nasdanika.exec.resources.ResourcesPackage#getResource_Contents()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getContents();

	/**
	 * Returns the value of the '<em><b>Reconcile Action</b></em>' attribute.
	 * The default value is <code>"Overwrite"</code>.
	 * The literals are from the enumeration {@link org.nasdanika.exec.resources.ReconcileAction}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Reconcile action - what to do if a resource with the same name already exists. Defalut is ``Overwrite``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Reconcile Action</em>' attribute.
	 * @see org.nasdanika.exec.resources.ReconcileAction
	 * @see #setReconcileAction(ReconcileAction)
	 * @see org.nasdanika.exec.resources.ResourcesPackage#getResource_ReconcileAction()
	 * @model default="Overwrite"
	 * @generated
	 */
	ReconcileAction getReconcileAction();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.resources.Resource#getReconcileAction <em>Reconcile Action</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reconcile Action</em>' attribute.
	 * @see org.nasdanika.exec.resources.ReconcileAction
	 * @see #getReconcileAction()
	 * @generated
	 */
	void setReconcileAction(ReconcileAction value);

	/**
	 * Returns the value of the '<em><b>Merger</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Merger for ``Merge`` reconcile action. Adapted to ``org.nasdanika.common.resources.Merger`` to execute actual merge.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Merger</em>' containment reference.
	 * @see #setMerger(EObject)
	 * @see org.nasdanika.exec.resources.ResourcesPackage#getResource_Merger()
	 * @model containment="true"
	 * @generated
	 */
	EObject getMerger();

	/**
	 * Sets the value of the '{@link org.nasdanika.exec.resources.Resource#getMerger <em>Merger</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Merger</em>' containment reference.
	 * @see #getMerger()
	 * @generated
	 */
	void setMerger(EObject value);

} // Resource
