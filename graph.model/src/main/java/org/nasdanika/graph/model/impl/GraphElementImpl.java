/**
 */
package org.nasdanika.graph.model.impl;

import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.nasdanika.drawio.model.SemanticElement;
import org.nasdanika.drawio.model.SemanticMapping;
import org.nasdanika.graph.model.GraphElement;
import org.nasdanika.graph.model.ModelPackage;

import org.nasdanika.ncore.impl.StringIdentityImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Graph Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.graph.model.impl.GraphElementImpl#getSemanticMappings <em>Semantic Mappings</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class GraphElementImpl extends StringIdentityImpl implements GraphElement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GraphElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.GRAPH_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<SemanticMapping> getSemanticMappings() {
		return (EList<SemanticMapping>)eDynamicGet(ModelPackage.GRAPH_ELEMENT__SEMANTIC_MAPPINGS, org.nasdanika.drawio.model.ModelPackage.Literals.SEMANTIC_ELEMENT__SEMANTIC_MAPPINGS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.GRAPH_ELEMENT__SEMANTIC_MAPPINGS:
				return ((InternalEList<?>)getSemanticMappings()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.GRAPH_ELEMENT__SEMANTIC_MAPPINGS:
				return getSemanticMappings();
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
			case ModelPackage.GRAPH_ELEMENT__SEMANTIC_MAPPINGS:
				getSemanticMappings().clear();
				getSemanticMappings().addAll((Collection<? extends SemanticMapping>)newValue);
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
			case ModelPackage.GRAPH_ELEMENT__SEMANTIC_MAPPINGS:
				getSemanticMappings().clear();
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
			case ModelPackage.GRAPH_ELEMENT__SEMANTIC_MAPPINGS:
				return !getSemanticMappings().isEmpty();
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
		if (baseClass == SemanticElement.class) {
			switch (derivedFeatureID) {
				case ModelPackage.GRAPH_ELEMENT__SEMANTIC_MAPPINGS: return org.nasdanika.drawio.model.ModelPackage.SEMANTIC_ELEMENT__SEMANTIC_MAPPINGS;
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
		if (baseClass == SemanticElement.class) {
			switch (baseFeatureID) {
				case org.nasdanika.drawio.model.ModelPackage.SEMANTIC_ELEMENT__SEMANTIC_MAPPINGS: return ModelPackage.GRAPH_ELEMENT__SEMANTIC_MAPPINGS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //GraphElementImpl
