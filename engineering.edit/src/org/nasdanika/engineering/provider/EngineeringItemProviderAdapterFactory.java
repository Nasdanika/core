/**
 */
package org.nasdanika.engineering.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.nasdanika.engineering.util.EngineeringAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EngineeringItemProviderAdapterFactory extends EngineeringAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EngineeringItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.ComponentCategoryElement} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentCategoryElementItemProvider componentCategoryElementItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.ComponentCategoryElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createComponentCategoryElementAdapter() {
		if (componentCategoryElementItemProvider == null) {
			componentCategoryElementItemProvider = new ComponentCategoryElementItemProvider(this);
		}

		return componentCategoryElementItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.AbstractEngineer} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractEngineerItemProvider abstractEngineerItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.AbstractEngineer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createAbstractEngineerAdapter() {
		if (abstractEngineerItemProvider == null) {
			abstractEngineerItemProvider = new AbstractEngineerItemProvider(this);
		}

		return abstractEngineerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.Engineer} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EngineerItemProvider engineerItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.Engineer}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEngineerAdapter() {
		if (engineerItemProvider == null) {
			engineerItemProvider = new EngineerItemProvider(this);
		}

		return engineerItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.IssueType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IssueTypeItemProvider issueTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.IssueType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIssueTypeAdapter() {
		if (issueTypeItemProvider == null) {
			issueTypeItemProvider = new IssueTypeItemProvider(this);
		}

		return issueTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.IssueResolution} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IssueResolutionItemProvider issueResolutionItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.IssueResolution}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIssueResolutionAdapter() {
		if (issueResolutionItemProvider == null) {
			issueResolutionItemProvider = new IssueResolutionItemProvider(this);
		}

		return issueResolutionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.IssueCategory} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IssueCategoryItemProvider issueCategoryItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.IssueCategory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIssueCategoryAdapter() {
		if (issueCategoryItemProvider == null) {
			issueCategoryItemProvider = new IssueCategoryItemProvider(this);
		}

		return issueCategoryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.IssueStatus} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IssueStatusItemProvider issueStatusItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.IssueStatus}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIssueStatusAdapter() {
		if (issueStatusItemProvider == null) {
			issueStatusItemProvider = new IssueStatusItemProvider(this);
		}

		return issueStatusItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.IssueNote} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IssueNoteItemProvider issueNoteItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.IssueNote}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIssueNoteAdapter() {
		if (issueNoteItemProvider == null) {
			issueNoteItemProvider = new IssueNoteItemProvider(this);
		}

		return issueNoteItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.IssueRelationshipType} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IssueRelationshipTypeItemProvider issueRelationshipTypeItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.IssueRelationshipType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIssueRelationshipTypeAdapter() {
		if (issueRelationshipTypeItemProvider == null) {
			issueRelationshipTypeItemProvider = new IssueRelationshipTypeItemProvider(this);
		}

		return issueRelationshipTypeItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.IssueRelationship} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IssueRelationshipItemProvider issueRelationshipItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.IssueRelationship}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIssueRelationshipAdapter() {
		if (issueRelationshipItemProvider == null) {
			issueRelationshipItemProvider = new IssueRelationshipItemProvider(this);
		}

		return issueRelationshipItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.Issue} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IssueItemProvider issueItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.Issue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIssueAdapter() {
		if (issueItemProvider == null) {
			issueItemProvider = new IssueItemProvider(this);
		}

		return issueItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.Increment} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IncrementItemProvider incrementItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.Increment}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createIncrementAdapter() {
		if (incrementItemProvider == null) {
			incrementItemProvider = new IncrementItemProvider(this);
		}

		return incrementItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.EngineeringOrganizationalUnit} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EngineeringOrganizationalUnitItemProvider engineeringOrganizationalUnitItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.EngineeringOrganizationalUnit}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEngineeringOrganizationalUnitAdapter() {
		if (engineeringOrganizationalUnitItemProvider == null) {
			engineeringOrganizationalUnitItemProvider = new EngineeringOrganizationalUnitItemProvider(this);
		}

		return engineeringOrganizationalUnitItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.EngineeringOrganization} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EngineeringOrganizationItemProvider engineeringOrganizationItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.EngineeringOrganization}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEngineeringOrganizationAdapter() {
		if (engineeringOrganizationItemProvider == null) {
			engineeringOrganizationItemProvider = new EngineeringOrganizationItemProvider(this);
		}

		return engineeringOrganizationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.Release} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReleaseItemProvider releaseItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.Release}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createReleaseAdapter() {
		if (releaseItemProvider == null) {
			releaseItemProvider = new ReleaseItemProvider(this);
		}

		return releaseItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.Objective} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObjectiveItemProvider objectiveItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.Objective}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createObjectiveAdapter() {
		if (objectiveItemProvider == null) {
			objectiveItemProvider = new ObjectiveItemProvider(this);
		}

		return objectiveItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.KeyResult} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected KeyResultItemProvider keyResultItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.KeyResult}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createKeyResultAdapter() {
		if (keyResultItemProvider == null) {
			keyResultItemProvider = new KeyResultItemProvider(this);
		}

		return keyResultItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.Product} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProductItemProvider productItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.Product}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createProductAdapter() {
		if (productItemProvider == null) {
			productItemProvider = new ProductItemProvider(this);
		}

		return productItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.Offering} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OfferingItemProvider offeringItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.Offering}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOfferingAdapter() {
		if (offeringItemProvider == null) {
			offeringItemProvider = new OfferingItemProvider(this);
		}

		return offeringItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.Edition} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EditionItemProvider editionItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.Edition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEditionAdapter() {
		if (editionItemProvider == null) {
			editionItemProvider = new EditionItemProvider(this);
		}

		return editionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.FeatureCategoryElement} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureCategoryElementItemProvider featureCategoryElementItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.FeatureCategoryElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFeatureCategoryElementAdapter() {
		if (featureCategoryElementItemProvider == null) {
			featureCategoryElementItemProvider = new FeatureCategoryElementItemProvider(this);
		}

		return featureCategoryElementItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.Feature} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureItemProvider featureItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.Feature}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFeatureAdapter() {
		if (featureItemProvider == null) {
			featureItemProvider = new FeatureItemProvider(this);
		}

		return featureItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.Component} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComponentItemProvider componentItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.Component}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createComponentAdapter() {
		if (componentItemProvider == null) {
			componentItemProvider = new ComponentItemProvider(this);
		}

		return componentItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.Persona} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PersonaItemProvider personaItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.Persona}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPersonaAdapter() {
		if (personaItemProvider == null) {
			personaItemProvider = new PersonaItemProvider(this);
		}

		return personaItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.NeedCategoryElement} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NeedCategoryElementItemProvider needCategoryElementItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.NeedCategoryElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNeedCategoryElementAdapter() {
		if (needCategoryElementItemProvider == null) {
			needCategoryElementItemProvider = new NeedCategoryElementItemProvider(this);
		}

		return needCategoryElementItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.Need} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NeedItemProvider needItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.Need}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNeedAdapter() {
		if (needItemProvider == null) {
			needItemProvider = new NeedItemProvider(this);
		}

		return needItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.Scenario} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScenarioItemProvider scenarioItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.Scenario}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createScenarioAdapter() {
		if (scenarioItemProvider == null) {
			scenarioItemProvider = new ScenarioItemProvider(this);
		}

		return scenarioItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.Criterion} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CriterionItemProvider criterionItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.Criterion}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createCriterionAdapter() {
		if (criterionItemProvider == null) {
			criterionItemProvider = new CriterionItemProvider(this);
		}

		return criterionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.Comparison} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComparisonItemProvider comparisonItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.Comparison}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createComparisonAdapter() {
		if (comparisonItemProvider == null) {
			comparisonItemProvider = new ComparisonItemProvider(this);
		}

		return comparisonItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.engineering.Risk} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RiskItemProvider riskItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.engineering.Risk}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRiskAdapter() {
		if (riskItemProvider == null) {
			riskItemProvider = new RiskItemProvider(this);
		}

		return riskItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void dispose() {
		if (componentCategoryElementItemProvider != null) componentCategoryElementItemProvider.dispose();
		if (abstractEngineerItemProvider != null) abstractEngineerItemProvider.dispose();
		if (engineeringOrganizationalUnitItemProvider != null) engineeringOrganizationalUnitItemProvider.dispose();
		if (engineeringOrganizationItemProvider != null) engineeringOrganizationItemProvider.dispose();
		if (engineerItemProvider != null) engineerItemProvider.dispose();
		if (issueTypeItemProvider != null) issueTypeItemProvider.dispose();
		if (issueResolutionItemProvider != null) issueResolutionItemProvider.dispose();
		if (issueCategoryItemProvider != null) issueCategoryItemProvider.dispose();
		if (issueStatusItemProvider != null) issueStatusItemProvider.dispose();
		if (issueNoteItemProvider != null) issueNoteItemProvider.dispose();
		if (issueRelationshipTypeItemProvider != null) issueRelationshipTypeItemProvider.dispose();
		if (issueRelationshipItemProvider != null) issueRelationshipItemProvider.dispose();
		if (issueItemProvider != null) issueItemProvider.dispose();
		if (incrementItemProvider != null) incrementItemProvider.dispose();
		if (releaseItemProvider != null) releaseItemProvider.dispose();
		if (objectiveItemProvider != null) objectiveItemProvider.dispose();
		if (keyResultItemProvider != null) keyResultItemProvider.dispose();
		if (componentItemProvider != null) componentItemProvider.dispose();
		if (offeringItemProvider != null) offeringItemProvider.dispose();
		if (productItemProvider != null) productItemProvider.dispose();
		if (editionItemProvider != null) editionItemProvider.dispose();
		if (featureCategoryElementItemProvider != null) featureCategoryElementItemProvider.dispose();
		if (featureItemProvider != null) featureItemProvider.dispose();
		if (personaItemProvider != null) personaItemProvider.dispose();
		if (needCategoryElementItemProvider != null) needCategoryElementItemProvider.dispose();
		if (needItemProvider != null) needItemProvider.dispose();
		if (scenarioItemProvider != null) scenarioItemProvider.dispose();
		if (criterionItemProvider != null) criterionItemProvider.dispose();
		if (comparisonItemProvider != null) comparisonItemProvider.dispose();
		if (riskItemProvider != null) riskItemProvider.dispose();
	}

}
