/**
 */
package org.nasdanika.flow.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.common.Util;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.flow.PackageElement;
import org.nasdanika.ncore.impl.NamedElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.impl.PackageElementImpl#getPrototype <em>Prototype</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageElementImpl#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageElementImpl#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageElementImpl#getModifiers <em>Modifiers</em>}</li>
 *   <li>{@link org.nasdanika.flow.impl.PackageElementImpl#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class PackageElementImpl<T extends PackageElement<T>> extends NamedElementImpl implements PackageElement<T> {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return FlowPackage.Literals.PACKAGE_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getPrototype() {
		return (T)eDynamicGet(FlowPackage.PACKAGE_ELEMENT__PROTOTYPE, FlowPackage.Literals.PACKAGE_ELEMENT__PROTOTYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public T basicGetPrototype() {
		return (T)eDynamicGet(FlowPackage.PACKAGE_ELEMENT__PROTOTYPE, FlowPackage.Literals.PACKAGE_ELEMENT__PROTOTYPE, false, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPrototype(T newPrototype) {
		eDynamicSet(FlowPackage.PACKAGE_ELEMENT__PROTOTYPE, FlowPackage.Literals.PACKAGE_ELEMENT__PROTOTYPE, newPrototype);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<T> getExtensions() {
		return getReferrers(FlowPackage.Literals.PACKAGE_ELEMENT__EXTENDS);
	}	

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<T> getExtends() {
		throw new UnsupportedOperationException("Override at subclasses");
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<String> getModifiers() {
		return (EList<String>)eDynamicGet(FlowPackage.PACKAGE_ELEMENT__MODIFIERS, FlowPackage.Literals.PACKAGE_ELEMENT__MODIFIERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<EObject> getDocumentation() {
		return (EList<EObject>)eDynamicGet(FlowPackage.PACKAGE_ELEMENT__DOCUMENTATION, FlowPackage.Literals.PACKAGE_ELEMENT__DOCUMENTATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T create() {
		T ret = (T) EcoreUtil.create(eClass());
		ret.setPrototype((T) this);
		return ret;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void apply(T instance) {		
		// Calls apply for all bases.
		for (T base: getExtends()) {
			base.apply(instance);
		}
		
		// Setting inherited attributes if they are not set yet.
		for (EAttribute attr: eClass().getEAllAttributes()) {
			if (attr.isChangeable() && eIsSet(attr)) {
				Object attrValue = eGet(attr);
				instance.eSet(attr, attrValue);
			}
		}
		
		EList<EObject> documentation = getDocumentation();
		if (!documentation.isEmpty()) {
			EList<EObject> instanceDocumentation = instance.getDocumentation();
			instanceDocumentation.clear();
			instanceDocumentation.addAll(EcoreUtil.copyAll(documentation));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void resolve(T packageElement) {
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FlowPackage.PACKAGE_ELEMENT__EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtensions()).basicAdd(otherEnd, msgs);
			case FlowPackage.PACKAGE_ELEMENT__EXTENDS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getExtends()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case FlowPackage.PACKAGE_ELEMENT__EXTENSIONS:
				return ((InternalEList<?>)getExtensions()).basicRemove(otherEnd, msgs);
			case FlowPackage.PACKAGE_ELEMENT__EXTENDS:
				return ((InternalEList<?>)getExtends()).basicRemove(otherEnd, msgs);
			case FlowPackage.PACKAGE_ELEMENT__DOCUMENTATION:
				return ((InternalEList<?>)getDocumentation()).basicRemove(otherEnd, msgs);
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
			case FlowPackage.PACKAGE_ELEMENT__PROTOTYPE:
				if (resolve) return getPrototype();
				return basicGetPrototype();
			case FlowPackage.PACKAGE_ELEMENT__EXTENSIONS:
				return getExtensions();
			case FlowPackage.PACKAGE_ELEMENT__EXTENDS:
				return getExtends();
			case FlowPackage.PACKAGE_ELEMENT__MODIFIERS:
				return getModifiers();
			case FlowPackage.PACKAGE_ELEMENT__DOCUMENTATION:
				return getDocumentation();
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
			case FlowPackage.PACKAGE_ELEMENT__PROTOTYPE:
				setPrototype((T)newValue);
				return;
			case FlowPackage.PACKAGE_ELEMENT__MODIFIERS:
				getModifiers().clear();
				getModifiers().addAll((Collection<? extends String>)newValue);
				return;
			case FlowPackage.PACKAGE_ELEMENT__DOCUMENTATION:
				getDocumentation().clear();
				getDocumentation().addAll((Collection<? extends EObject>)newValue);
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
			case FlowPackage.PACKAGE_ELEMENT__PROTOTYPE:
				setPrototype((T)null);
				return;
			case FlowPackage.PACKAGE_ELEMENT__MODIFIERS:
				getModifiers().clear();
				return;
			case FlowPackage.PACKAGE_ELEMENT__DOCUMENTATION:
				getDocumentation().clear();
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
			case FlowPackage.PACKAGE_ELEMENT__PROTOTYPE:
				return basicGetPrototype() != null;
			case FlowPackage.PACKAGE_ELEMENT__EXTENSIONS:
				return !getExtensions().isEmpty();
			case FlowPackage.PACKAGE_ELEMENT__EXTENDS:
				return !getExtends().isEmpty();
			case FlowPackage.PACKAGE_ELEMENT__MODIFIERS:
				return !getModifiers().isEmpty();
			case FlowPackage.PACKAGE_ELEMENT__DOCUMENTATION:
				return !getDocumentation().isEmpty();
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
			case FlowPackage.PACKAGE_ELEMENT___CREATE:
				return create();
			case FlowPackage.PACKAGE_ELEMENT___APPLY__PACKAGEELEMENT:
				apply((T)arguments.get(0));
				return null;
			case FlowPackage.PACKAGE_ELEMENT___RESOLVE__PACKAGEELEMENT:
				resolve((T)arguments.get(0));
				return null;
		}
		return super.eInvoke(operationID, arguments);
	}
	
	@Override
	public String getName() {
		String name = super.getName();
		if (Util.isBlank(name)) {
			// TODO - derive name from key - kebab to label.
		}
		return name;
	}

} //PackageElementImpl
