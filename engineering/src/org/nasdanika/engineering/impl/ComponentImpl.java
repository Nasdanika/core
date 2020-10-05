/**
 */
package org.nasdanika.engineering.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.engineering.AbstractComponent;
import org.nasdanika.engineering.AbstractEngineer;
import org.nasdanika.engineering.Component;
import org.nasdanika.engineering.ComponentCategoryElement;
import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.engineering.Issue;
import org.nasdanika.engineering.Release;
import org.nasdanika.ncore.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.impl.ComponentImpl#getOwners <em>Owners</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.ComponentImpl#getIssues <em>Issues</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.ComponentImpl#getReleases <em>Releases</em>}</li>
 *   <li>{@link org.nasdanika.engineering.impl.ComponentImpl#getComponents <em>Components</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ComponentImpl extends ModelElementImpl implements Component {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EngineeringPackage.Literals.COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<AbstractEngineer> getOwners() {
		return (EList<AbstractEngineer>)eDynamicGet(EngineeringPackage.COMPONENT__OWNERS, EngineeringPackage.Literals.ABSTRACT_COMPONENT__OWNERS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Issue> getIssues() {
		return (EList<Issue>)eDynamicGet(EngineeringPackage.COMPONENT__ISSUES, EngineeringPackage.Literals.ABSTRACT_COMPONENT__ISSUES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Release> getReleases() {
		return (EList<Release>)eDynamicGet(EngineeringPackage.COMPONENT__RELEASES, EngineeringPackage.Literals.ABSTRACT_COMPONENT__RELEASES, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<ComponentCategoryElement> getComponents() {
		return (EList<ComponentCategoryElement>)eDynamicGet(EngineeringPackage.COMPONENT__COMPONENTS, EngineeringPackage.Literals.COMPONENT__COMPONENTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EngineeringPackage.COMPONENT__ISSUES:
				return ((InternalEList<?>)getIssues()).basicRemove(otherEnd, msgs);
			case EngineeringPackage.COMPONENT__RELEASES:
				return ((InternalEList<?>)getReleases()).basicRemove(otherEnd, msgs);
			case EngineeringPackage.COMPONENT__COMPONENTS:
				return ((InternalEList<?>)getComponents()).basicRemove(otherEnd, msgs);
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
			case EngineeringPackage.COMPONENT__OWNERS:
				return getOwners();
			case EngineeringPackage.COMPONENT__ISSUES:
				return getIssues();
			case EngineeringPackage.COMPONENT__RELEASES:
				return getReleases();
			case EngineeringPackage.COMPONENT__COMPONENTS:
				return getComponents();
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
			case EngineeringPackage.COMPONENT__OWNERS:
				getOwners().clear();
				getOwners().addAll((Collection<? extends AbstractEngineer>)newValue);
				return;
			case EngineeringPackage.COMPONENT__ISSUES:
				getIssues().clear();
				getIssues().addAll((Collection<? extends Issue>)newValue);
				return;
			case EngineeringPackage.COMPONENT__RELEASES:
				getReleases().clear();
				getReleases().addAll((Collection<? extends Release>)newValue);
				return;
			case EngineeringPackage.COMPONENT__COMPONENTS:
				getComponents().clear();
				getComponents().addAll((Collection<? extends ComponentCategoryElement>)newValue);
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
			case EngineeringPackage.COMPONENT__OWNERS:
				getOwners().clear();
				return;
			case EngineeringPackage.COMPONENT__ISSUES:
				getIssues().clear();
				return;
			case EngineeringPackage.COMPONENT__RELEASES:
				getReleases().clear();
				return;
			case EngineeringPackage.COMPONENT__COMPONENTS:
				getComponents().clear();
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
			case EngineeringPackage.COMPONENT__OWNERS:
				return !getOwners().isEmpty();
			case EngineeringPackage.COMPONENT__ISSUES:
				return !getIssues().isEmpty();
			case EngineeringPackage.COMPONENT__RELEASES:
				return !getReleases().isEmpty();
			case EngineeringPackage.COMPONENT__COMPONENTS:
				return !getComponents().isEmpty();
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
		if (baseClass == ComponentCategoryElement.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == AbstractComponent.class) {
			switch (derivedFeatureID) {
				case EngineeringPackage.COMPONENT__OWNERS: return EngineeringPackage.ABSTRACT_COMPONENT__OWNERS;
				case EngineeringPackage.COMPONENT__ISSUES: return EngineeringPackage.ABSTRACT_COMPONENT__ISSUES;
				case EngineeringPackage.COMPONENT__RELEASES: return EngineeringPackage.ABSTRACT_COMPONENT__RELEASES;
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
		if (baseClass == ComponentCategoryElement.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == AbstractComponent.class) {
			switch (baseFeatureID) {
				case EngineeringPackage.ABSTRACT_COMPONENT__OWNERS: return EngineeringPackage.COMPONENT__OWNERS;
				case EngineeringPackage.ABSTRACT_COMPONENT__ISSUES: return EngineeringPackage.COMPONENT__ISSUES;
				case EngineeringPackage.ABSTRACT_COMPONENT__RELEASES: return EngineeringPackage.COMPONENT__RELEASES;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ComponentImpl
