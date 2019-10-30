/**
 */
package org.nasdanika.ncore.impl;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Work;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.TypedElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Typed Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.impl.TypedElementImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.TypedElementImpl#isRequired <em>Required</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TypedElementImpl extends ModelElementImpl implements TypedElement {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isRequired() <em>Required</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRequired()
	 * @generated
	 * @ordered
	 */
	protected static final boolean REQUIRED_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypedElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NcorePackage.Literals.TYPED_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getType() {
		return (String)eDynamicGet(NcorePackage.TYPED_ELEMENT__TYPE, NcorePackage.Literals.TYPED_ELEMENT__TYPE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setType(String newType) {
		eDynamicSet(NcorePackage.TYPED_ELEMENT__TYPE, NcorePackage.Literals.TYPED_ELEMENT__TYPE, newType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRequired() {
		return (Boolean)eDynamicGet(NcorePackage.TYPED_ELEMENT__REQUIRED, NcorePackage.Literals.TYPED_ELEMENT__REQUIRED, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRequired(boolean newRequired) {
		eDynamicSet(NcorePackage.TYPED_ELEMENT__REQUIRED, NcorePackage.Literals.TYPED_ELEMENT__REQUIRED, newRequired);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NcorePackage.TYPED_ELEMENT__TYPE:
				return getType();
			case NcorePackage.TYPED_ELEMENT__REQUIRED:
				return isRequired();
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
			case NcorePackage.TYPED_ELEMENT__TYPE:
				setType((String)newValue);
				return;
			case NcorePackage.TYPED_ELEMENT__REQUIRED:
				setRequired((Boolean)newValue);
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
			case NcorePackage.TYPED_ELEMENT__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case NcorePackage.TYPED_ELEMENT__REQUIRED:
				setRequired(REQUIRED_EDEFAULT);
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
			case NcorePackage.TYPED_ELEMENT__TYPE:
				return TYPE_EDEFAULT == null ? getType() != null : !TYPE_EDEFAULT.equals(getType());
			case NcorePackage.TYPED_ELEMENT__REQUIRED:
				return isRequired() != REQUIRED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	@Override
	public Work<Object> create(Context context) throws Exception {
		return new Work<Object>() {

			@Override
			public Object execute(ProgressMonitor progressMonitor) throws Exception {
				ClassLoader classLoader = context.get(ClassLoader.class);
				if (classLoader == null) {
					classLoader = getClass().getClassLoader();
				}
				return context.get(classLoader.loadClass(getType()));
			}

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String getName() {
				return getTitle();
			}
			
		};
	}

} //TypedElementImpl
