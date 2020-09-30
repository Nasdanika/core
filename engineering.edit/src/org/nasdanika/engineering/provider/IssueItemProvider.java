/**
 */
package org.nasdanika.engineering.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.nasdanika.emf.edit.EReferenceItemProvider;
import org.nasdanika.engineering.EngineeringFactory;
import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.engineering.Issue;
import org.nasdanika.ncore.provider.EntityItemProvider;

/**
 * This is the item provider adapter for a {@link org.nasdanika.engineering.Issue} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class IssueItemProvider 
	extends EntityItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IssueItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addAssignedToPropertyDescriptor(object);
			addSizePropertyDescriptor(object);
			addCostPropertyDescriptor(object);
			addBenefitPropertyDescriptor(object);
			addPlannedForPropertyDescriptor(object);
			addTypePropertyDescriptor(object);
			addStatusPropertyDescriptor(object);
			addResolutionPropertyDescriptor(object);
			addCategoriesPropertyDescriptor(object);
			addReleasesPropertyDescriptor(object);
			addRequiresPropertyDescriptor(object);
			addActionablePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Assigned To feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addAssignedToPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor(
				 getResourceLocator(),
				 getString("_UI_Issue_assignedTo_feature"),
				 EngineeringPackage.Literals.ISSUE__ASSIGNED_TO,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Size feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addSizePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor(
				 getResourceLocator(),
				 getString("_UI_Issue_size_feature"),
				 EngineeringPackage.Literals.ISSUE__SIZE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Cost feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addCostPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor(
				 getResourceLocator(),
				 getString("_UI_Issue_cost_feature"),
				 EngineeringPackage.Literals.ISSUE__COST,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Benefit feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addBenefitPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor(
				 getResourceLocator(),
				 getString("_UI_Issue_benefit_feature"),
				 EngineeringPackage.Literals.ISSUE__BENEFIT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Planned For feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addPlannedForPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor(
				 getResourceLocator(),
				 getString("_UI_Issue_plannedFor_feature"),
				 EngineeringPackage.Literals.ISSUE__PLANNED_FOR,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Type feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor(
				 getResourceLocator(),
				 getString("_UI_Issue_type_feature"),
				 EngineeringPackage.Literals.ISSUE__TYPE,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Status feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addStatusPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor(
				 getResourceLocator(),
				 getString("_UI_Issue_status_feature"),
				 EngineeringPackage.Literals.ISSUE__STATUS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Resolution feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addResolutionPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor(
				 getResourceLocator(),
				 getString("_UI_Issue_resolution_feature"),
				 EngineeringPackage.Literals.ISSUE__RESOLUTION,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Categories feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addCategoriesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor(
				 getResourceLocator(),
				 getString("_UI_Issue_categories_feature"),
				 EngineeringPackage.Literals.ISSUE__CATEGORIES,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Releases feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addReleasesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor(
				 getResourceLocator(),
				 getString("_UI_Issue_releases_feature"),
				 EngineeringPackage.Literals.ISSUE__RELEASES,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Requires feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addRequiresPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor(
				 getResourceLocator(),
				 getString("_UI_Issue_requires_feature"),
				 EngineeringPackage.Literals.ISSUE__REQUIRES,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Actionable feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addActionablePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor(
				 getResourceLocator(),
				 getString("_UI_Issue_actionable_feature"),
				 EngineeringPackage.Literals.ISSUE__ACTIONABLE,
				 false,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null,
				 null));
	}
	
	/**
	 * Called from getChildren(), adds EReferenceItemProvider children.
	 * @param children
	 */
	protected void addEReferenceItemProviderChildren(Object object, Collection<EReferenceItemProvider> children) {
		children.add(new EReferenceItemProvider(this, (EObject) object, EngineeringPackage.Literals.ISSUE__CHILDREN));		
		children.add(new EReferenceItemProvider(this, (EObject) object, EngineeringPackage.Literals.ISSUE__NOTES));		
		children.add(new EReferenceItemProvider(this, (EObject) object, EngineeringPackage.Literals.ISSUE__RELATIONSHIPS));		
	}
	
	@Override
	public final Collection<?> getChildren(Object object) {
		List<EReferenceItemProvider> children = eReferenceItemProviders.get(object);
		if (children == null) {
			children = new ArrayList<>();
			eReferenceItemProviders.put(object, children);
			addEReferenceItemProviderChildren(object, children);
			children.sort((a,b) -> a.getText(object).compareTo(b.getText(object)));
		}
		Collection<Object> ret = new ArrayList<>(children);
		ret.addAll(super.getChildren(object));
		return ret;
	}
	

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
//			childrenFeatures.add(EngineeringPackage.Literals.ISSUE__CHILDREN);
//			childrenFeatures.add(EngineeringPackage.Literals.ISSUE__NOTES);
//			childrenFeatures.add(EngineeringPackage.Literals.ISSUE__RELATIONSHIPS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns Issue.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Issue"));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean shouldComposeCreationImage() {
		return true;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((Issue)object).getId();
		return label == null || label.length() == 0 ?
			getString("_UI_Issue_type") :
			getString("_UI_Issue_type") + " " + label;
	}


	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(Issue.class)) {
			case EngineeringPackage.ISSUE__SIZE:
			case EngineeringPackage.ISSUE__COST:
			case EngineeringPackage.ISSUE__BENEFIT:
			case EngineeringPackage.ISSUE__ACTIONABLE:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case EngineeringPackage.ISSUE__CHILDREN:
			case EngineeringPackage.ISSUE__NOTES:
			case EngineeringPackage.ISSUE__RELATIONSHIPS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(EngineeringPackage.Literals.ISSUE__CHILDREN,
				 EngineeringFactory.eINSTANCE.createIssue()));

		newChildDescriptors.add
			(createChildParameter
				(EngineeringPackage.Literals.ISSUE__NOTES,
				 EngineeringFactory.eINSTANCE.createIssueNote()));

		newChildDescriptors.add
			(createChildParameter
				(EngineeringPackage.Literals.ISSUE__RELATIONSHIPS,
				 EngineeringFactory.eINSTANCE.createIssueRelationship()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return EngineeringEditPlugin.INSTANCE;
	}

}
