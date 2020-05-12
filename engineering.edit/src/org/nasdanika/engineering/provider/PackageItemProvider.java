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
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.nasdanika.rigel.RigelFactory;
import org.nasdanika.rigel.RigelPackage;

/**
 * This is the item provider adapter for a {@link org.nasdanika.rigel.Package} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class PackageItemProvider extends PackageElementItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageItemProvider(AdapterFactory adapterFactory) {
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
			childrenFeatures.add(RigelPackage.Literals.IPACKAGE__ELEMENTS);
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
	 * This returns Package.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Package.png"));
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
		String label = ((org.nasdanika.rigel.Package)object).getName();
		return label == null || label.length() == 0 ? getString("_UI_Package_type") : label;
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

		switch (notification.getFeatureID(org.nasdanika.rigel.Package.class)) {
			case RigelPackage.PACKAGE__ISSUES:
			case RigelPackage.PACKAGE__ELEMENTS:
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
				(RigelPackage.Literals.ENGINEERED_ELEMENT__ISSUES,
				 RigelFactory.eINSTANCE.createIssue()));

		newChildDescriptors.add
			(createChildParameter
				(RigelPackage.Literals.IPACKAGE__ELEMENTS,
				 RigelFactory.eINSTANCE.createPackage()));

		newChildDescriptors.add
			(createChildParameter
				(RigelPackage.Literals.IPACKAGE__ELEMENTS,
				 RigelFactory.eINSTANCE.createActor()));

		newChildDescriptors.add
			(createChildParameter
				(RigelPackage.Literals.IPACKAGE__ELEMENTS,
				 RigelFactory.eINSTANCE.createStart()));

		newChildDescriptors.add
			(createChildParameter
				(RigelPackage.Literals.IPACKAGE__ELEMENTS,
				 RigelFactory.eINSTANCE.createEnd()));

		newChildDescriptors.add
			(createChildParameter
				(RigelPackage.Literals.IPACKAGE__ELEMENTS,
				 RigelFactory.eINSTANCE.createPartition()));

		newChildDescriptors.add
			(createChildParameter
				(RigelPackage.Literals.IPACKAGE__ELEMENTS,
				 RigelFactory.eINSTANCE.createActivity()));

		newChildDescriptors.add
			(createChildParameter
				(RigelPackage.Literals.IPACKAGE__ELEMENTS,
				 RigelFactory.eINSTANCE.createMilestone()));

		newChildDescriptors.add
			(createChildParameter
				(RigelPackage.Literals.IPACKAGE__ELEMENTS,
				 RigelFactory.eINSTANCE.createActivityReference()));

		newChildDescriptors.add
			(createChildParameter
				(RigelPackage.Literals.IPACKAGE__ELEMENTS,
				 RigelFactory.eINSTANCE.createArtifact()));

		newChildDescriptors.add
			(createChildParameter
				(RigelPackage.Literals.IPACKAGE__ELEMENTS,
				 RigelFactory.eINSTANCE.createResource()));

		newChildDescriptors.add
			(createChildParameter
				(RigelPackage.Literals.IPACKAGE__ELEMENTS,
				 RigelFactory.eINSTANCE.createEngineer()));
	}

}
