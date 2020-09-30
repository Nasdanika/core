/**
 */
package org.nasdanika.engineering.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;

import org.eclipse.emf.edit.provider.ViewerNotification;
import org.nasdanika.emf.edit.EReferenceItemProvider;
import org.nasdanika.engineering.EngineeringFactory;
import org.nasdanika.engineering.EngineeringOrganization;

import org.nasdanika.engineering.EngineeringPackage;
import org.nasdanika.party.PartyPackage;

import org.nasdanika.party.provider.OrganizationItemProvider;

/**
 * This is the item provider adapter for a {@link org.nasdanika.engineering.EngineeringOrganization} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EngineeringOrganizationItemProvider extends OrganizationItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EngineeringOrganizationItemProvider(AdapterFactory adapterFactory) {
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

		}
		return itemPropertyDescriptors;
	}
	
	/**
	 * Called from getChildren(), adds EReferenceItemProvider children.
	 * @param children
	 */
	@Override
	protected void addEReferenceItemProviderChildren(Object object, Collection<EReferenceItemProvider> children) {
		children.add(new EReferenceItemProvider(this, (EObject) object, EngineeringPackage.Literals.ABSTRACT_ENGINEER__ISSUES));		
		children.add(new EReferenceItemProvider(this, (EObject) object, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__PORTFOLIO));		
		children.add(new EReferenceItemProvider(this, (EObject) object, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__TARGET_AUDIENCES));		
		children.add(new EReferenceItemProvider(this, (EObject) object, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_TYPES));
		children.add(new EReferenceItemProvider(this, (EObject) object, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RESOLUTIONS));
		children.add(new EReferenceItemProvider(this, (EObject) object, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_CATEGORIES));
		children.add(new EReferenceItemProvider(this, (EObject) object, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_STATUSES));
		children.add(new EReferenceItemProvider(this, (EObject) object, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RELATIONSHIP_TYPES));
		children.add(new EReferenceItemProvider(this, (EObject) object, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__INCREMENTS));		
		children.add(new EReferenceItemProvider(this, (EObject) object, EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__FEATURE_TYPES));		
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
//			childrenFeatures.add(EngineeringPackage.Literals.ABSTRACT_ENGINEER__ISSUES);
//			childrenFeatures.add(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__PORTFOLIO);
//			childrenFeatures.add(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__TARGET_AUDIENCES);
//			childrenFeatures.add(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_TYPES);
//			childrenFeatures.add(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RESOLUTIONS);
//			childrenFeatures.add(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_CATEGORIES);
//			childrenFeatures.add(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_STATUSES);
//			childrenFeatures.add(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RELATIONSHIP_TYPES);
//			childrenFeatures.add(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__INCREMENTS);
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
	 * This returns EngineeringOrganization.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/EngineeringOrganization"));
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
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		String label = ((EngineeringOrganization)object).getTitle();
		return label == null || label.length() == 0 ?
			getString("_UI_EngineeringOrganization_type") :
			getString("_UI_EngineeringOrganization_type") + " " + label;
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

		switch (notification.getFeatureID(EngineeringOrganization.class)) {
			case EngineeringPackage.ENGINEERING_ORGANIZATION__ISSUES:
			case EngineeringPackage.ENGINEERING_ORGANIZATION__OBJECTIVES:
			case EngineeringPackage.ENGINEERING_ORGANIZATION__PORTFOLIO:
			case EngineeringPackage.ENGINEERING_ORGANIZATION__TARGET_AUDIENCES:
			case EngineeringPackage.ENGINEERING_ORGANIZATION__ISSUE_TYPES:
			case EngineeringPackage.ENGINEERING_ORGANIZATION__ISSUE_RESOLUTIONS:
			case EngineeringPackage.ENGINEERING_ORGANIZATION__ISSUE_CATEGORIES:
			case EngineeringPackage.ENGINEERING_ORGANIZATION__ISSUE_STATUSES:
			case EngineeringPackage.ENGINEERING_ORGANIZATION__ISSUE_RELATIONSHIP_TYPES:
			case EngineeringPackage.ENGINEERING_ORGANIZATION__INCREMENTS:
			case EngineeringPackage.ENGINEERING_ORGANIZATION__FEATURE_TYPES:
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
	 * @generated NOT
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(PartyPackage.Literals.ORGANIZATION__DIRECTORY,
				 EngineeringFactory.eINSTANCE.createEngineeringOrganizationalUnit()));

		newChildDescriptors.add
			(createChildParameter
				(PartyPackage.Literals.ORGANIZATION__DIRECTORY,
				 EngineeringFactory.eINSTANCE.createEngineeringOrganization()));

		newChildDescriptors.add
			(createChildParameter
				(EngineeringPackage.Literals.ABSTRACT_ENGINEER__ISSUES,
				 EngineeringFactory.eINSTANCE.createIssue()));

		newChildDescriptors.add
			(createChildParameter
				(EngineeringPackage.Literals.ABSTRACT_ENGINEER__OBJECTIVES,
				 EngineeringFactory.eINSTANCE.createObjective()));

		newChildDescriptors.add
			(createChildParameter
				(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__PORTFOLIO,
				 EngineeringFactory.eINSTANCE.createComponentCategory()));

		newChildDescriptors.add
			(createChildParameter
				(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__PORTFOLIO,
				 EngineeringFactory.eINSTANCE.createComponent()));

		newChildDescriptors.add
			(createChildParameter
				(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__PORTFOLIO,
				 EngineeringFactory.eINSTANCE.createProduct()));

		newChildDescriptors.add
			(createChildParameter
				(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__TARGET_AUDIENCES,
				 EngineeringFactory.eINSTANCE.createPersona()));

		newChildDescriptors.add
			(createChildParameter
				(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_TYPES,
				 EngineeringFactory.eINSTANCE.createIssueType()));

		newChildDescriptors.add
			(createChildParameter
				(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RESOLUTIONS,
				 EngineeringFactory.eINSTANCE.createIssueResolution()));

		newChildDescriptors.add
			(createChildParameter
				(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_CATEGORIES,
				 EngineeringFactory.eINSTANCE.createIssueCategory()));

		newChildDescriptors.add
			(createChildParameter
				(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_STATUSES,
				 EngineeringFactory.eINSTANCE.createIssueStatus()));

		newChildDescriptors.add
			(createChildParameter
				(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__ISSUE_RELATIONSHIP_TYPES,
				 EngineeringFactory.eINSTANCE.createIssueRelationshipType()));

		newChildDescriptors.add
			(createChildParameter
				(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__INCREMENTS,
				 EngineeringFactory.eINSTANCE.createIncrement()));

		newChildDescriptors.add
			(createChildParameter
				(EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT__FEATURE_TYPES,
				 EngineeringFactory.eINSTANCE.createFeatureType()));
	}

	/**
	 * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		Object childFeature = feature;
		Object childObject = child;

		boolean qualify =
			childFeature == PartyPackage.Literals.ORGANIZATIONAL_UNIT__ORGANIZATIONAL_UNITS ||
			childFeature == PartyPackage.Literals.ORGANIZATION__DIRECTORY;

		if (qualify) {
			return getString
				("_UI_CreateChild_text2",
				 new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
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
