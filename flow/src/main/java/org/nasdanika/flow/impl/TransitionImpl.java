/**
 */
package org.nasdanika.flow.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.flow.Artifact;
import org.nasdanika.flow.Flow;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.Transition;
import org.nasdanika.ncore.impl.NamedElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Transition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.TransitionImpl#getPayload <em>Payload</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.TransitionImpl#isSuppress <em>Suppress</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.TransitionImpl#getTarget <em>Target</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TransitionImpl extends NamedElementImpl implements Transition {
	/**
	 * The default value of the '{@link #isSuppress() <em>Suppress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSuppress()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUPPRESS_EDEFAULT = false;

	/**
	 * The default value of the '{@link #getTarget() <em>Target</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected static final String TARGET_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TransitionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.TRANSITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Artifact> getPayload() {
		return (EList<Artifact>)eDynamicGet(FlowPackage.TRANSITION__PAYLOAD, FlowPackage.Literals.TRANSITION__PAYLOAD, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSuppress() {
		return (Boolean)eDynamicGet(FlowPackage.TRANSITION__SUPPRESS, FlowPackage.Literals.TRANSITION__SUPPRESS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSuppress(boolean newSuppress) {
		eDynamicSet(FlowPackage.TRANSITION__SUPPRESS, FlowPackage.Literals.TRANSITION__SUPPRESS, newSuppress);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTarget() {
		return (String)eDynamicGet(FlowPackage.TRANSITION__TARGET, FlowPackage.Literals.TRANSITION__TARGET, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTarget(String newTarget) {
		eDynamicSet(FlowPackage.TRANSITION__TARGET, FlowPackage.Literals.TRANSITION__TARGET, newTarget);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FlowElement getTarget(EList<Flow> journeyPath) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case FlowPackage.TRANSITION__PAYLOAD:
				return getPayload();
			case FlowPackage.TRANSITION__SUPPRESS:
				return isSuppress();
			case FlowPackage.TRANSITION__TARGET:
				return getTarget();
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
			case FlowPackage.TRANSITION__PAYLOAD:
				getPayload().clear();
				getPayload().addAll((Collection<? extends Artifact>)newValue);
				return;
			case FlowPackage.TRANSITION__SUPPRESS:
				setSuppress((Boolean)newValue);
				return;
			case FlowPackage.TRANSITION__TARGET:
				setTarget((String)newValue);
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
			case FlowPackage.TRANSITION__PAYLOAD:
				getPayload().clear();
				return;
			case FlowPackage.TRANSITION__SUPPRESS:
				setSuppress(SUPPRESS_EDEFAULT);
				return;
			case FlowPackage.TRANSITION__TARGET:
				setTarget(TARGET_EDEFAULT);
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
			case FlowPackage.TRANSITION__PAYLOAD:
				return !getPayload().isEmpty();
			case FlowPackage.TRANSITION__SUPPRESS:
				return isSuppress() != SUPPRESS_EDEFAULT;
			case FlowPackage.TRANSITION__TARGET:
				return TARGET_EDEFAULT == null ? getTarget() != null : !TARGET_EDEFAULT.equals(getTarget());
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case FlowPackage.TRANSITION___GET_TARGET__ELIST:
				return getTarget((EList<Flow>)arguments.get(0));
		}
		return super.eInvoke(operationID, arguments);
	}

} //TransitionImpl
