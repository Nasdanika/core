/**
 */
package org.nasdanika.ncore.impl;

import java.lang.Boolean;
import java.lang.Integer;
import java.lang.String;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stack Trace Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.impl.StackTraceElementImpl#getClassName <em>Class Name</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.StackTraceElementImpl#getFileName <em>File Name</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.StackTraceElementImpl#getMethodName <em>Method Name</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.StackTraceElementImpl#getLineNumber <em>Line Number</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.StackTraceElementImpl#isNative <em>Native</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StackTraceElementImpl extends MinimalEObjectImpl.Container implements org.nasdanika.ncore.StackTraceElement {
	/**
	 * The default value of the '{@link #getClassName() <em>Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getFileName() <em>File Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileName()
	 * @generated
	 * @ordered
	 */
	protected static final String FILE_NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getMethodName() <em>Method Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMethodName()
	 * @generated
	 * @ordered
	 */
	protected static final String METHOD_NAME_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getLineNumber() <em>Line Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineNumber()
	 * @generated
	 * @ordered
	 */
	protected static final int LINE_NUMBER_EDEFAULT = 0;

	/**
	 * The default value of the '{@link #isNative() <em>Native</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isNative()
	 * @generated
	 * @ordered
	 */
	protected static final boolean NATIVE_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StackTraceElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NcorePackage.Literals.STACK_TRACE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getClassName() {
		return (String)eDynamicGet(NcorePackage.STACK_TRACE_ELEMENT__CLASS_NAME, NcorePackage.Literals.STACK_TRACE_ELEMENT__CLASS_NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setClassName(String newClassName) {
		eDynamicSet(NcorePackage.STACK_TRACE_ELEMENT__CLASS_NAME, NcorePackage.Literals.STACK_TRACE_ELEMENT__CLASS_NAME, newClassName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getFileName() {
		return (String)eDynamicGet(NcorePackage.STACK_TRACE_ELEMENT__FILE_NAME, NcorePackage.Literals.STACK_TRACE_ELEMENT__FILE_NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setFileName(String newFileName) {
		eDynamicSet(NcorePackage.STACK_TRACE_ELEMENT__FILE_NAME, NcorePackage.Literals.STACK_TRACE_ELEMENT__FILE_NAME, newFileName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMethodName() {
		return (String)eDynamicGet(NcorePackage.STACK_TRACE_ELEMENT__METHOD_NAME, NcorePackage.Literals.STACK_TRACE_ELEMENT__METHOD_NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMethodName(String newMethodName) {
		eDynamicSet(NcorePackage.STACK_TRACE_ELEMENT__METHOD_NAME, NcorePackage.Literals.STACK_TRACE_ELEMENT__METHOD_NAME, newMethodName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getLineNumber() {
		return (Integer)eDynamicGet(NcorePackage.STACK_TRACE_ELEMENT__LINE_NUMBER, NcorePackage.Literals.STACK_TRACE_ELEMENT__LINE_NUMBER, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLineNumber(int newLineNumber) {
		eDynamicSet(NcorePackage.STACK_TRACE_ELEMENT__LINE_NUMBER, NcorePackage.Literals.STACK_TRACE_ELEMENT__LINE_NUMBER, newLineNumber);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isNative() {
		return (Boolean)eDynamicGet(NcorePackage.STACK_TRACE_ELEMENT__NATIVE, NcorePackage.Literals.STACK_TRACE_ELEMENT__NATIVE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNative(boolean newNative) {
		eDynamicSet(NcorePackage.STACK_TRACE_ELEMENT__NATIVE, NcorePackage.Literals.STACK_TRACE_ELEMENT__NATIVE, newNative);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NcorePackage.STACK_TRACE_ELEMENT__CLASS_NAME:
				return getClassName();
			case NcorePackage.STACK_TRACE_ELEMENT__FILE_NAME:
				return getFileName();
			case NcorePackage.STACK_TRACE_ELEMENT__METHOD_NAME:
				return getMethodName();
			case NcorePackage.STACK_TRACE_ELEMENT__LINE_NUMBER:
				return getLineNumber();
			case NcorePackage.STACK_TRACE_ELEMENT__NATIVE:
				return isNative();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case NcorePackage.STACK_TRACE_ELEMENT__CLASS_NAME:
				setClassName((String)newValue);
				return;
			case NcorePackage.STACK_TRACE_ELEMENT__FILE_NAME:
				setFileName((String)newValue);
				return;
			case NcorePackage.STACK_TRACE_ELEMENT__METHOD_NAME:
				setMethodName((String)newValue);
				return;
			case NcorePackage.STACK_TRACE_ELEMENT__LINE_NUMBER:
				setLineNumber((Integer)newValue);
				return;
			case NcorePackage.STACK_TRACE_ELEMENT__NATIVE:
				setNative((Boolean)newValue);
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
			case NcorePackage.STACK_TRACE_ELEMENT__CLASS_NAME:
				setClassName(CLASS_NAME_EDEFAULT);
				return;
			case NcorePackage.STACK_TRACE_ELEMENT__FILE_NAME:
				setFileName(FILE_NAME_EDEFAULT);
				return;
			case NcorePackage.STACK_TRACE_ELEMENT__METHOD_NAME:
				setMethodName(METHOD_NAME_EDEFAULT);
				return;
			case NcorePackage.STACK_TRACE_ELEMENT__LINE_NUMBER:
				setLineNumber(LINE_NUMBER_EDEFAULT);
				return;
			case NcorePackage.STACK_TRACE_ELEMENT__NATIVE:
				setNative(NATIVE_EDEFAULT);
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
			case NcorePackage.STACK_TRACE_ELEMENT__CLASS_NAME:
				return CLASS_NAME_EDEFAULT == null ? getClassName() != null : !CLASS_NAME_EDEFAULT.equals(getClassName());
			case NcorePackage.STACK_TRACE_ELEMENT__FILE_NAME:
				return FILE_NAME_EDEFAULT == null ? getFileName() != null : !FILE_NAME_EDEFAULT.equals(getFileName());
			case NcorePackage.STACK_TRACE_ELEMENT__METHOD_NAME:
				return METHOD_NAME_EDEFAULT == null ? getMethodName() != null : !METHOD_NAME_EDEFAULT.equals(getMethodName());
			case NcorePackage.STACK_TRACE_ELEMENT__LINE_NUMBER:
				return getLineNumber() != LINE_NUMBER_EDEFAULT;
			case NcorePackage.STACK_TRACE_ELEMENT__NATIVE:
				return isNative() != NATIVE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //StackTraceElementImpl
