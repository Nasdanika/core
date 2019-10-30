/**
 */
package org.nasdanika.ncore.impl;

import java.lang.Object;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.common.CompoundWork;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Work;
import org.nasdanika.common.WorkFactory;
import org.nasdanika.ncore.Array;
import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Array</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.impl.ArrayImpl#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ArrayImpl extends ModelElementImpl implements Array {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrayImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NcorePackage.Literals.ARRAY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<WorkFactory<Object>> getElements() {
		return (EList<WorkFactory<Object>>)eDynamicGet(NcorePackage.ARRAY__ELEMENTS, NcorePackage.Literals.ARRAY__ELEMENTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NcorePackage.ARRAY__ELEMENTS:
				return ((InternalEList<?>)getElements()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NcorePackage.ARRAY__ELEMENTS:
				return getElements();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case NcorePackage.ARRAY__ELEMENTS:
				getElements().clear();
				getElements().addAll((Collection<? extends WorkFactory<Object>>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case NcorePackage.ARRAY__ELEMENTS:
				getElements().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case NcorePackage.ARRAY__ELEMENTS:
				return !getElements().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	@Override
	public Work<EList<Object>> create(Context context) throws Exception {
		CompoundWork<EList<Object>, Object> ret = new CompoundWork<EList<Object>, Object>(getTitle(), context.get(Executor.class)) {
						
			@Override
			protected EList<Object> combine(List<Object> results, ProgressMonitor progressMonitor) throws Exception {
				return new BasicEList<Object>(results);
			}

			
		};
		for (WorkFactory<Object> e: getElements()) {
			Work<Object> child = e.create(context);
			ret.add(child);
		}
		return ret;
	}

} //ArrayImpl
