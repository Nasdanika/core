/**
 */
package org.nasdanika.rigel.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.nasdanika.rigel.Flow;
import org.nasdanika.rigel.RigelFactory;
import org.nasdanika.rigel.RigelPackage;

/**
 * This is the item provider adapter for a {@link org.nasdanika.rigel.Flow} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FlowItemProvider extends PackageElementItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FlowItemProvider(AdapterFactory adapterFactory) {
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

			addOwnersPropertyDescriptor(object);
			addRequiredCapabilitiesPropertyDescriptor(object);
			addParicipantsPropertyDescriptor(object);
			addTotalSizePropertyDescriptor(object);
			addTotalProgressPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Owners feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addOwnersPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_EngineeredElement_owners_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_EngineeredElement_owners_feature", "_UI_EngineeredElement_type"),
				 RigelPackage.Literals.ENGINEERED_ELEMENT__OWNERS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Required Capabilities feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addRequiredCapabilitiesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor(
				 getResourceLocator(),
				 getString("_UI_Requirement_requiredCapabilities_feature"),
				 RigelPackage.Literals.REQUIREMENT__REQUIRED_CAPABILITIES,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Paricipants feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addParicipantsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
		(createItemPropertyDescriptor
		  (getResourceLocator(),
		   getString("_UI_Flow_paricipants_feature"),
		   RigelPackage.Literals.FLOW__PARICIPANTS,
		   true,
		   false,
		   true,
		   null,
		   null,
		   null,
		   null));
	}

	/**
	 * This adds a property descriptor for the Total Size feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTotalSizePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Flow_totalSize_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Flow_totalSize_feature", "_UI_Flow_type"),
				 RigelPackage.Literals.FLOW__TOTAL_SIZE,
				 false,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Total Progress feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTotalProgressPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Flow_totalProgress_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Flow_totalProgress_feature", "_UI_Flow_type"),
				 RigelPackage.Literals.FLOW__TOTAL_PROGRESS,
				 false,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(RigelPackage.Literals.ENGINEERED_ELEMENT__ISSUES);
			childrenFeatures.add(RigelPackage.Literals.FLOW__ELEMENTS);
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
		String label = ((Flow)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Flow_type") :
			getString("_UI_Flow_type") + " " + label;
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

		switch (notification.getFeatureID(Flow.class)) {
			case RigelPackage.FLOW__TOTAL_SIZE:
			case RigelPackage.FLOW__TOTAL_PROGRESS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case RigelPackage.FLOW__ISSUES:
			case RigelPackage.FLOW__ELEMENTS:
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

		collectEReferenceChildDescriptors(object, newChildDescriptors, RigelPackage.Literals.REQUIREMENT__REQUIRED_CAPABILITIES);		

		newChildDescriptors.add
			(createChildParameter
				(RigelPackage.Literals.ENGINEERED_ELEMENT__ISSUES,
				 RigelFactory.eINSTANCE.createIssue()));

		newChildDescriptors.add
			(createChildParameter
				(RigelPackage.Literals.FLOW__ELEMENTS,
				 RigelFactory.eINSTANCE.createStart()));

		newChildDescriptors.add
			(createChildParameter
				(RigelPackage.Literals.FLOW__ELEMENTS,
				 RigelFactory.eINSTANCE.createEnd()));

		newChildDescriptors.add
			(createChildParameter
				(RigelPackage.Literals.FLOW__ELEMENTS,
				 RigelFactory.eINSTANCE.createPartition()));

		newChildDescriptors.add
			(createChildParameter
				(RigelPackage.Literals.FLOW__ELEMENTS,
				 RigelFactory.eINSTANCE.createActivity()));

		newChildDescriptors.add
			(createChildParameter
				(RigelPackage.Literals.FLOW__ELEMENTS,
				 RigelFactory.eINSTANCE.createActivityReference()));
	}

}
