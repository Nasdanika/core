/**
 */
package org.nasdanika.sirius.tree.impl;

import java.util.Collection;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.EndUserDocumentedElement;
import org.eclipse.sirius.viewpoint.description.IdentifiedElement;

import org.eclipse.sirius.viewpoint.description.impl.DocumentedElementImpl;

import org.nasdanika.sirius.tree.AdapterFactoryTreeDescription;
import org.nasdanika.sirius.tree.TreePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Adapter Factory Tree Description</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.sirius.tree.impl.AdapterFactoryTreeDescriptionImpl#getEndUserDocumentation <em>End User Documentation</em>}</li>
 *   <li>{@link org.nasdanika.sirius.tree.impl.AdapterFactoryTreeDescriptionImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.sirius.tree.impl.AdapterFactoryTreeDescriptionImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.nasdanika.sirius.tree.impl.AdapterFactoryTreeDescriptionImpl#getTitleExpression <em>Title Expression</em>}</li>
 *   <li>{@link org.nasdanika.sirius.tree.impl.AdapterFactoryTreeDescriptionImpl#isInitialisation <em>Initialisation</em>}</li>
 *   <li>{@link org.nasdanika.sirius.tree.impl.AdapterFactoryTreeDescriptionImpl#getMetamodel <em>Metamodel</em>}</li>
 *   <li>{@link org.nasdanika.sirius.tree.impl.AdapterFactoryTreeDescriptionImpl#isShowOnStartup <em>Show On Startup</em>}</li>
 *   <li>{@link org.nasdanika.sirius.tree.impl.AdapterFactoryTreeDescriptionImpl#getEditorId <em>Editor Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AdapterFactoryTreeDescriptionImpl extends DocumentedElementImpl implements AdapterFactoryTreeDescription {
	/**
	 * The default value of the '{@link #getEndUserDocumentation() <em>End User Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndUserDocumentation()
	 * @generated
	 * @ordered
	 */
	protected static final String END_USER_DOCUMENTATION_EDEFAULT = "";

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = "";

	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getTitleExpression() <em>Title Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitleExpression()
	 * @generated
	 * @ordered
	 */
	protected static final String TITLE_EXPRESSION_EDEFAULT = "";

	/**
	 * The default value of the '{@link #isInitialisation() <em>Initialisation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInitialisation()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INITIALISATION_EDEFAULT = false;

	/**
	 * The default value of the '{@link #isShowOnStartup() <em>Show On Startup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isShowOnStartup()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SHOW_ON_STARTUP_EDEFAULT = false;

	/**
	 * The default value of the '{@link #getEditorId() <em>Editor Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEditorId()
	 * @generated
	 * @ordered
	 */
	protected static final String EDITOR_ID_EDEFAULT = "org.nasdanika.emf.presentation.AdapterFactoryTreeEditor.ID";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AdapterFactoryTreeDescriptionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TreePackage.Literals.ADAPTER_FACTORY_TREE_DESCRIPTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int ESTATIC_FEATURE_COUNT = 1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return ESTATIC_FEATURE_COUNT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEndUserDocumentation() {
		return (String) eDynamicGet(
				TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__END_USER_DOCUMENTATION - ESTATIC_FEATURE_COUNT,
				DescriptionPackage.Literals.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndUserDocumentation(String newEndUserDocumentation) {
		eDynamicSet(TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__END_USER_DOCUMENTATION - ESTATIC_FEATURE_COUNT,
				DescriptionPackage.Literals.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION,
				newEndUserDocumentation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return (String) eDynamicGet(TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__NAME - ESTATIC_FEATURE_COUNT,
				DescriptionPackage.Literals.IDENTIFIED_ELEMENT__NAME, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		eDynamicSet(TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__NAME - ESTATIC_FEATURE_COUNT,
				DescriptionPackage.Literals.IDENTIFIED_ELEMENT__NAME, newName);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabel() {
		return (String) eDynamicGet(TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__LABEL - ESTATIC_FEATURE_COUNT,
				DescriptionPackage.Literals.IDENTIFIED_ELEMENT__LABEL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(String newLabel) {
		eDynamicSet(TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__LABEL - ESTATIC_FEATURE_COUNT,
				DescriptionPackage.Literals.IDENTIFIED_ELEMENT__LABEL, newLabel);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTitleExpression() {
		return (String) eDynamicGet(
				TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__TITLE_EXPRESSION - ESTATIC_FEATURE_COUNT,
				DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTitleExpression(String newTitleExpression) {
		eDynamicSet(TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__TITLE_EXPRESSION - ESTATIC_FEATURE_COUNT,
				DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION, newTitleExpression);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInitialisation() {
		return (Boolean) eDynamicGet(
				TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__INITIALISATION - ESTATIC_FEATURE_COUNT,
				DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__INITIALISATION, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitialisation(boolean newInitialisation) {
		eDynamicSet(TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__INITIALISATION - ESTATIC_FEATURE_COUNT,
				DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__INITIALISATION, newInitialisation);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	public EList<EPackage> getMetamodel() {
		return (EList<EPackage>) eDynamicGet(
				TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__METAMODEL - ESTATIC_FEATURE_COUNT,
				DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__METAMODEL, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isShowOnStartup() {
		return (Boolean) eDynamicGet(
				TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__SHOW_ON_STARTUP - ESTATIC_FEATURE_COUNT,
				DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__SHOW_ON_STARTUP, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShowOnStartup(boolean newShowOnStartup) {
		eDynamicSet(TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__SHOW_ON_STARTUP - ESTATIC_FEATURE_COUNT,
				DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__SHOW_ON_STARTUP, newShowOnStartup);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEditorId() {
		return (String) eDynamicGet(TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__EDITOR_ID - ESTATIC_FEATURE_COUNT,
				TreePackage.Literals.ADAPTER_FACTORY_TREE_DESCRIPTION__EDITOR_ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEditorId(String newEditorId) {
		eDynamicSet(TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__EDITOR_ID - ESTATIC_FEATURE_COUNT,
				TreePackage.Literals.ADAPTER_FACTORY_TREE_DESCRIPTION__EDITOR_ID, newEditorId);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__END_USER_DOCUMENTATION:
			return getEndUserDocumentation();
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__NAME:
			return getName();
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__LABEL:
			return getLabel();
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__TITLE_EXPRESSION:
			return getTitleExpression();
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__INITIALISATION:
			return isInitialisation();
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__METAMODEL:
			return getMetamodel();
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__SHOW_ON_STARTUP:
			return isShowOnStartup();
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__EDITOR_ID:
			return getEditorId();
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
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__END_USER_DOCUMENTATION:
			setEndUserDocumentation((String) newValue);
			return;
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__NAME:
			setName((String) newValue);
			return;
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__LABEL:
			setLabel((String) newValue);
			return;
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__TITLE_EXPRESSION:
			setTitleExpression((String) newValue);
			return;
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__INITIALISATION:
			setInitialisation((Boolean) newValue);
			return;
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__METAMODEL:
			getMetamodel().clear();
			getMetamodel().addAll((Collection<? extends EPackage>) newValue);
			return;
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__SHOW_ON_STARTUP:
			setShowOnStartup((Boolean) newValue);
			return;
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__EDITOR_ID:
			setEditorId((String) newValue);
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
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__END_USER_DOCUMENTATION:
			setEndUserDocumentation(END_USER_DOCUMENTATION_EDEFAULT);
			return;
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__NAME:
			setName(NAME_EDEFAULT);
			return;
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__LABEL:
			setLabel(LABEL_EDEFAULT);
			return;
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__TITLE_EXPRESSION:
			setTitleExpression(TITLE_EXPRESSION_EDEFAULT);
			return;
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__INITIALISATION:
			setInitialisation(INITIALISATION_EDEFAULT);
			return;
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__METAMODEL:
			getMetamodel().clear();
			return;
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__SHOW_ON_STARTUP:
			setShowOnStartup(SHOW_ON_STARTUP_EDEFAULT);
			return;
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__EDITOR_ID:
			setEditorId(EDITOR_ID_EDEFAULT);
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
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__END_USER_DOCUMENTATION:
			return END_USER_DOCUMENTATION_EDEFAULT == null ? getEndUserDocumentation() != null
					: !END_USER_DOCUMENTATION_EDEFAULT.equals(getEndUserDocumentation());
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__NAME:
			return NAME_EDEFAULT == null ? getName() != null : !NAME_EDEFAULT.equals(getName());
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__LABEL:
			return LABEL_EDEFAULT == null ? getLabel() != null : !LABEL_EDEFAULT.equals(getLabel());
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__TITLE_EXPRESSION:
			return TITLE_EXPRESSION_EDEFAULT == null ? getTitleExpression() != null
					: !TITLE_EXPRESSION_EDEFAULT.equals(getTitleExpression());
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__INITIALISATION:
			return isInitialisation() != INITIALISATION_EDEFAULT;
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__METAMODEL:
			return !getMetamodel().isEmpty();
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__SHOW_ON_STARTUP:
			return isShowOnStartup() != SHOW_ON_STARTUP_EDEFAULT;
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__EDITOR_ID:
			return EDITOR_ID_EDEFAULT == null ? getEditorId() != null : !EDITOR_ID_EDEFAULT.equals(getEditorId());
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
		if (baseClass == EndUserDocumentedElement.class) {
			switch (derivedFeatureID) {
			case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__END_USER_DOCUMENTATION:
				return DescriptionPackage.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION;
			default:
				return -1;
			}
		}
		if (baseClass == IdentifiedElement.class) {
			switch (derivedFeatureID) {
			case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__NAME:
				return DescriptionPackage.IDENTIFIED_ELEMENT__NAME;
			case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__LABEL:
				return DescriptionPackage.IDENTIFIED_ELEMENT__LABEL;
			default:
				return -1;
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
		if (baseClass == EndUserDocumentedElement.class) {
			switch (baseFeatureID) {
			case DescriptionPackage.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION:
				return TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__END_USER_DOCUMENTATION;
			default:
				return -1;
			}
		}
		if (baseClass == IdentifiedElement.class) {
			switch (baseFeatureID) {
			case DescriptionPackage.IDENTIFIED_ELEMENT__NAME:
				return TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__NAME;
			case DescriptionPackage.IDENTIFIED_ELEMENT__LABEL:
				return TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__LABEL;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //AdapterFactoryTreeDescriptionImpl
