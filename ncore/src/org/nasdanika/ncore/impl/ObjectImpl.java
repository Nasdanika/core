/**
 */
package org.nasdanika.ncore.impl;

import org.eclipse.emf.ecore.EClass;

import org.nasdanika.ncore.Entry;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.impl.ObjectImpl#getTitle <em>Title</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.ObjectImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.ObjectImpl#getName <em>Name</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ObjectImpl extends MapImpl implements org.nasdanika.ncore.Object {
	/**
	 * The default value of the '{@link #getTitle() <em>Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String TITLE_EDEFAULT = null;
	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NcorePackage.Literals.OBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getTitle() {
		return (String)eDynamicGet(NcorePackage.OBJECT__TITLE, NcorePackage.Literals.MODEL_ELEMENT__TITLE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTitle(String newTitle) {
		eDynamicSet(NcorePackage.OBJECT__TITLE, NcorePackage.Literals.MODEL_ELEMENT__TITLE, newTitle);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDescription() {
		return (String)eDynamicGet(NcorePackage.OBJECT__DESCRIPTION, NcorePackage.Literals.MODEL_ELEMENT__DESCRIPTION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		eDynamicSet(NcorePackage.OBJECT__DESCRIPTION, NcorePackage.Literals.MODEL_ELEMENT__DESCRIPTION, newDescription);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return (String)eDynamicGet(NcorePackage.OBJECT__NAME, NcorePackage.Literals.NAMED_ELEMENT__NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		eDynamicSet(NcorePackage.OBJECT__NAME, NcorePackage.Literals.NAMED_ELEMENT__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NcorePackage.OBJECT__TITLE:
				return getTitle();
			case NcorePackage.OBJECT__DESCRIPTION:
				return getDescription();
			case NcorePackage.OBJECT__NAME:
				return getName();
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
			case NcorePackage.OBJECT__TITLE:
				setTitle((String)newValue);
				return;
			case NcorePackage.OBJECT__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case NcorePackage.OBJECT__NAME:
				setName((String)newValue);
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
			case NcorePackage.OBJECT__TITLE:
				setTitle(TITLE_EDEFAULT);
				return;
			case NcorePackage.OBJECT__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case NcorePackage.OBJECT__NAME:
				setName(NAME_EDEFAULT);
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
			case NcorePackage.OBJECT__TITLE:
				return TITLE_EDEFAULT == null ? getTitle() != null : !TITLE_EDEFAULT.equals(getTitle());
			case NcorePackage.OBJECT__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? getDescription() != null : !DESCRIPTION_EDEFAULT.equals(getDescription());
			case NcorePackage.OBJECT__NAME:
				return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ModelElement.class) {
			switch (derivedFeatureID) {
				case NcorePackage.OBJECT__TITLE: return NcorePackage.MODEL_ELEMENT__TITLE;
				case NcorePackage.OBJECT__DESCRIPTION: return NcorePackage.MODEL_ELEMENT__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class) {
			switch (derivedFeatureID) {
				case NcorePackage.OBJECT__NAME: return NcorePackage.NAMED_ELEMENT__NAME;
				default: return -1;
			}
		}
		if (baseClass == Entry.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ModelElement.class) {
			switch (baseFeatureID) {
				case NcorePackage.MODEL_ELEMENT__TITLE: return NcorePackage.OBJECT__TITLE;
				case NcorePackage.MODEL_ELEMENT__DESCRIPTION: return NcorePackage.OBJECT__DESCRIPTION;
				default: return -1;
			}
		}
		if (baseClass == NamedElement.class) {
			switch (baseFeatureID) {
				case NcorePackage.NAMED_ELEMENT__NAME: return NcorePackage.OBJECT__NAME;
				default: return -1;
			}
		}
		if (baseClass == Entry.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ObjectImpl
