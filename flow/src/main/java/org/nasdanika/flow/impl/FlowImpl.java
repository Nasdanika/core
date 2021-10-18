/**
 */
package org.nasdanika.flow.impl;

import java.util.Map.Entry;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.flow.Flow;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Flow</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.FlowImpl#getElements <em>Elements</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.FlowImpl#isPartition <em>Partition</em>}</li>
 * </ul>
 *
 * @generated
 */
public class FlowImpl extends ActivityImpl<Flow> implements Flow {
	/**
	 * The default value of the '{@link #isPartition() <em>Partition</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPartition()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PARTITION_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FlowImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.FLOW;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EMap<String, FlowElement<?>> getElements() {
		return (EMap<String, FlowElement<?>>)eDynamicGet(FlowPackage.FLOW__ELEMENTS, FlowPackage.Literals.FLOW__ELEMENTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isPartition() {
		return (Boolean)eDynamicGet(FlowPackage.FLOW__PARTITION, FlowPackage.Literals.FLOW__PARTITION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPartition(boolean newPartition) {
		eDynamicSet(FlowPackage.FLOW__PARTITION, FlowPackage.Literals.FLOW__PARTITION, newPartition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FlowPackage.FLOW__ELEMENTS:
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
			case FlowPackage.FLOW__ELEMENTS:
				if (coreType) return getElements();
				else return getElements().map();
			case FlowPackage.FLOW__PARTITION:
				return isPartition();
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
			case FlowPackage.FLOW__ELEMENTS:
				((EStructuralFeature.Setting)getElements()).set(newValue);
				return;
			case FlowPackage.FLOW__PARTITION:
				setPartition((Boolean)newValue);
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
			case FlowPackage.FLOW__ELEMENTS:
				getElements().clear();
				return;
			case FlowPackage.FLOW__PARTITION:
				setPartition(PARTITION_EDEFAULT);
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
			case FlowPackage.FLOW__ELEMENTS:
				return !getElements().isEmpty();
			case FlowPackage.FLOW__PARTITION:
				return isPartition() != PARTITION_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void apply(Flow instance) {
		super.apply(instance);

		// Elements
		for (Entry<String, FlowElement<?>> elementsEntry: getElements().entrySet()) {
			FlowElement element = elementsEntry.getValue();
			EMap<String, FlowElement<?>> instanceElements = instance.getElements();
			String elementKey = elementsEntry.getKey();
			if (element == null) {
				instanceElements.removeKey(elementKey);
			} else {
				FlowElement instanceElement = (FlowElement) element.create();
				instanceElements.put(elementKey, instanceElement);
				element.apply(instanceElement);
			}
		}
		
	}

} //FlowImpl
