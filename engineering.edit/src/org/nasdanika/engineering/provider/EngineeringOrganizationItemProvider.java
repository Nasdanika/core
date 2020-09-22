/**
 */
package org.nasdanika.engineering.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;

import org.nasdanika.engineering.EngineeringFactory;
import org.nasdanika.engineering.EngineeringOrganization;

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
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		String label = ((EngineeringOrganization)object).getName();
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
				(PartyPackage.Literals.ORGANIZATIONAL_UNIT__ORGANIZATIONAL_UNITS,
				 EngineeringFactory.eINSTANCE.createEngineeringOrganizationalUnit()));

		newChildDescriptors.add
			(createChildParameter
				(PartyPackage.Literals.ORGANIZATIONAL_UNIT__ORGANIZATIONAL_UNITS,
				 EngineeringFactory.eINSTANCE.createEngineeringOrganization()));

		newChildDescriptors.add
			(createChildParameter
				(PartyPackage.Literals.ORGANIZATIONAL_UNIT__ROLES,
				 EngineeringFactory.eINSTANCE.createEngineer()));

		newChildDescriptors.add
			(createChildParameter
				(PartyPackage.Literals.ORGANIZATION__DIRECTORY,
				 EngineeringFactory.eINSTANCE.createEngineeringOrganizationalUnit()));

		newChildDescriptors.add
			(createChildParameter
				(PartyPackage.Literals.ORGANIZATION__DIRECTORY,
				 EngineeringFactory.eINSTANCE.createEngineeringOrganization()));
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
