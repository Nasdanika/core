/**
 */
package org.nasdanika.ncore.impl;

import java.lang.Object;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common._legacy.CompoundSupplier;
import org.nasdanika.ncore.Entry;
import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.impl.MapImpl#getEntries <em>Entries</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MapImpl extends ModelElementImpl implements org.nasdanika.ncore.Map {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MapImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return NcorePackage.Literals.MAP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Entry<Object>> getEntries() {
		return (EList<Entry<Object>>)eDynamicGet(NcorePackage.MAP__ENTRIES, NcorePackage.Literals.MAP__ENTRIES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case NcorePackage.MAP__ENTRIES:
				return ((InternalEList<?>)getEntries()).basicRemove(otherEnd, msgs);
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
			case NcorePackage.MAP__ENTRIES:
				return getEntries();
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
			case NcorePackage.MAP__ENTRIES:
				getEntries().clear();
				getEntries().addAll((Collection<? extends Entry<Object>>)newValue);
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
			case NcorePackage.MAP__ENTRIES:
				getEntries().clear();
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
			case NcorePackage.MAP__ENTRIES:
				return !getEntries().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	@Override
	public Supplier<Map<String, Object>> create(Context context) throws Exception {
		CompoundSupplier<Map<String, Object>, Map.Entry<String, Object>> ret = new CompoundSupplier<Map<String, Object>, Map.Entry<String, Object>>(getTitle(), context.get(Executor.class)) {
						
			@Override
			protected Map<String, Object> combine(List<Map.Entry<String, Object>> results, ProgressMonitor progressMonitor) throws Exception {
				Map<String, Object> ret = new LinkedHashMap<>();
				for (Map.Entry<String, Object> e: results) {
					ret.put(e.getKey(), e.getValue());
				}
				return ret;
			}

			
		};
		for (Entry<Object> e: getEntries()) {
			ret.add(e.create(context).adapt(val -> new Map.Entry<String, Object>() {

						@Override
						public String getKey() {
							return e.name();
						}

						@Override
						public Object getValue() {
							return val;
						}

						@Override
						public Object setValue(Object arg0) {
							// TODO Auto-generated method stub
							return null;
						}
					}));
		}
		return ret;
	}

} //MapImpl
