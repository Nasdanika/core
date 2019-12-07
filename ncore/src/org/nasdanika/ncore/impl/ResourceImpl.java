/**
 */
package org.nasdanika.ncore.impl;

import java.net.URL;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.common.Context;
import org.nasdanika.common.Converter;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Supplier;
import org.nasdanika.emf.Util;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Resource;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.impl.ResourceImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.nasdanika.ncore.impl.ResourceImpl#isInterpolate <em>Interpolate</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ResourceImpl extends TypedElementImpl implements Resource {
	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCATION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isInterpolate() <em>Interpolate</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInterpolate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INTERPOLATE_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NcorePackage.Literals.RESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLocation() {
		return (String)eDynamicGet(NcorePackage.RESOURCE__LOCATION, NcorePackage.Literals.RESOURCE__LOCATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLocation(String newLocation) {
		eDynamicSet(NcorePackage.RESOURCE__LOCATION, NcorePackage.Literals.RESOURCE__LOCATION, newLocation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isInterpolate() {
		return (Boolean)eDynamicGet(NcorePackage.RESOURCE__INTERPOLATE, NcorePackage.Literals.RESOURCE__INTERPOLATE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInterpolate(boolean newInterpolate) {
		eDynamicSet(NcorePackage.RESOURCE__INTERPOLATE, NcorePackage.Literals.RESOURCE__INTERPOLATE, newInterpolate);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case NcorePackage.RESOURCE__LOCATION:
				return getLocation();
			case NcorePackage.RESOURCE__INTERPOLATE:
				return isInterpolate();
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
			case NcorePackage.RESOURCE__LOCATION:
				setLocation((String)newValue);
				return;
			case NcorePackage.RESOURCE__INTERPOLATE:
				setInterpolate((Boolean)newValue);
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
			case NcorePackage.RESOURCE__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case NcorePackage.RESOURCE__INTERPOLATE:
				setInterpolate(INTERPOLATE_EDEFAULT);
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
			case NcorePackage.RESOURCE__LOCATION:
				return LOCATION_EDEFAULT == null ? getLocation() != null : !LOCATION_EDEFAULT.equals(getLocation());
			case NcorePackage.RESOURCE__INTERPOLATE:
				return isInterpolate() != INTERPOLATE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Supplier<Object> create(Context context) throws Exception {
		return Supplier.<Object>fromCallable(() -> {
			URL url = Util.resolveReference(eResource(), context.interpolate(getLocation()));
			if (org.nasdanika.common.Util.isBlank(getType())) {
				return url;
			}
			Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
			ClassLoader classLoader = context.get(ClassLoader.class);
			if (classLoader == null) {
				classLoader = getClass().getClassLoader();
			}
			Class type = classLoader.loadClass(getType());
			Object ret = converter.convert(url, type);
			if (ret instanceof String && isInterpolate()) {
				return context.interpolate((String) ret);
			}
			return ret;
		}, getTitle(), 1);
	}

} //ResourceImpl
