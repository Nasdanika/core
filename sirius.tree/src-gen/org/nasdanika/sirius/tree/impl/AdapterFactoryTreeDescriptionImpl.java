/**
 */
package org.nasdanika.sirius.tree.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

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
	 * The cached value of the '{@link #getEndUserDocumentation() <em>End User Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEndUserDocumentation()
	 * @generated
	 * @ordered
	 */
	protected String endUserDocumentation = END_USER_DOCUMENTATION_EDEFAULT;

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
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

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
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

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
	 * The cached value of the '{@link #getTitleExpression() <em>Title Expression</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTitleExpression()
	 * @generated
	 * @ordered
	 */
	protected String titleExpression = TITLE_EXPRESSION_EDEFAULT;

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
	 * The cached value of the '{@link #isInitialisation() <em>Initialisation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInitialisation()
	 * @generated
	 * @ordered
	 */
	protected boolean initialisation = INITIALISATION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMetamodel() <em>Metamodel</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMetamodel()
	 * @generated
	 * @ordered
	 */
	protected EList<EPackage> metamodel;

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
	 * The cached value of the '{@link #isShowOnStartup() <em>Show On Startup</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isShowOnStartup()
	 * @generated
	 * @ordered
	 */
	protected boolean showOnStartup = SHOW_ON_STARTUP_EDEFAULT;

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
	public String getEndUserDocumentation() {
		return endUserDocumentation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEndUserDocumentation(String newEndUserDocumentation) {
		String oldEndUserDocumentation = endUserDocumentation;
		endUserDocumentation = newEndUserDocumentation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__END_USER_DOCUMENTATION, oldEndUserDocumentation,
					endUserDocumentation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__NAME,
					oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(String newLabel) {
		String oldLabel = label;
		label = newLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__LABEL,
					oldLabel, label));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTitleExpression() {
		return titleExpression;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTitleExpression(String newTitleExpression) {
		String oldTitleExpression = titleExpression;
		titleExpression = newTitleExpression;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__TITLE_EXPRESSION, oldTitleExpression,
					titleExpression));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInitialisation() {
		return initialisation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInitialisation(boolean newInitialisation) {
		boolean oldInitialisation = initialisation;
		initialisation = newInitialisation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__INITIALISATION, oldInitialisation, initialisation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EPackage> getMetamodel() {
		if (metamodel == null) {
			metamodel = new EObjectResolvingEList<EPackage>(EPackage.class, this,
					TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__METAMODEL);
		}
		return metamodel;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isShowOnStartup() {
		return showOnStartup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setShowOnStartup(boolean newShowOnStartup) {
		boolean oldShowOnStartup = showOnStartup;
		showOnStartup = newShowOnStartup;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__SHOW_ON_STARTUP, oldShowOnStartup, showOnStartup));
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
			return END_USER_DOCUMENTATION_EDEFAULT == null ? endUserDocumentation != null
					: !END_USER_DOCUMENTATION_EDEFAULT.equals(endUserDocumentation);
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__LABEL:
			return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__TITLE_EXPRESSION:
			return TITLE_EXPRESSION_EDEFAULT == null ? titleExpression != null
					: !TITLE_EXPRESSION_EDEFAULT.equals(titleExpression);
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__INITIALISATION:
			return initialisation != INITIALISATION_EDEFAULT;
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__METAMODEL:
			return metamodel != null && !metamodel.isEmpty();
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__SHOW_ON_STARTUP:
			return showOnStartup != SHOW_ON_STARTUP_EDEFAULT;
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (endUserDocumentation: ");
		result.append(endUserDocumentation);
		result.append(", name: ");
		result.append(name);
		result.append(", label: ");
		result.append(label);
		result.append(", titleExpression: ");
		result.append(titleExpression);
		result.append(", initialisation: ");
		result.append(initialisation);
		result.append(", showOnStartup: ");
		result.append(showOnStartup);
		result.append(')');
		return result.toString();
	}

} //AdapterFactoryTreeDescriptionImpl
