/**
 */
package org.nasdanika.party.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.nasdanika.emf.edit.EReferenceItemProvider;
import org.nasdanika.party.Organization;
import org.nasdanika.party.PartyFactory;
import org.nasdanika.party.PartyPackage;

/**
 * This is the item provider adapter for a {@link org.nasdanika.party.Organization} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class OrganizationItemProvider extends OrganizationalUnitItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrganizationItemProvider(AdapterFactory adapterFactory) {
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
		super.addEReferenceItemProviderChildren(object, children);
		children.add(new EReferenceItemProvider(this, (EObject) object, PartyPackage.Literals.ORGANIZATION__MEMBERS));
		children.add(new EReferenceItemProvider(this, (EObject) object, PartyPackage.Literals.ORGANIZATION__DIRECTORY));
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
			// Added as EReferenceItemProvider
//			childrenFeatures.add(PartyPackage.Literals.ORGANIZATION__MEMBERS);
//			childrenFeatures.add(PartyPackage.Literals.ORGANIZATION__DIRECTORY);
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
	 * This returns Organization.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Organization.png"));
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
		String label = ((Organization)object).getName();
		return label == null || label.length() == 0 ? getString("_UI_Organization_type") : label;
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

		switch (notification.getFeatureID(Organization.class)) {
			case PartyPackage.ORGANIZATION__MEMBERS:
			case PartyPackage.ORGANIZATION__DIRECTORY:
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
				(PartyPackage.Literals.ORGANIZATION__MEMBERS,
				 PartyFactory.eINSTANCE.createMemberDirectory()));

		newChildDescriptors.add
			(createChildParameter
				(PartyPackage.Literals.ORGANIZATION__MEMBERS,
				 PartyFactory.eINSTANCE.createMember()));

		newChildDescriptors.add
			(createChildParameter
				(PartyPackage.Literals.ORGANIZATION__DIRECTORY,
				 PartyFactory.eINSTANCE.createDirectory()));

		newChildDescriptors.add
			(createChildParameter
				(PartyPackage.Literals.ORGANIZATION__DIRECTORY,
				 PartyFactory.eINSTANCE.createOrganizationalUnit()));

		newChildDescriptors.add
			(createChildParameter
				(PartyPackage.Literals.ORGANIZATION__DIRECTORY,
				 PartyFactory.eINSTANCE.createOrganization()));

		newChildDescriptors.add
			(createChildParameter
				(PartyPackage.Literals.ORGANIZATION__DIRECTORY,
				 PartyFactory.eINSTANCE.createPerson()));
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

}
