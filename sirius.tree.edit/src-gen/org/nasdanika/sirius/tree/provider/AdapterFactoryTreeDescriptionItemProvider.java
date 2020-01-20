/**
 */
package org.nasdanika.sirius.tree.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

import org.eclipse.sirius.viewpoint.description.provider.DocumentedElementItemProvider;

import org.nasdanika.sirius.tree.AdapterFactoryTreeDescription;
import org.nasdanika.sirius.tree.TreePackage;

/**
 * This is the item provider adapter for a {@link org.nasdanika.sirius.tree.AdapterFactoryTreeDescription} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class AdapterFactoryTreeDescriptionItemProvider extends DocumentedElementItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AdapterFactoryTreeDescriptionItemProvider(AdapterFactory adapterFactory) {
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

			addEndUserDocumentationPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addLabelPropertyDescriptor(object);
			addTitleExpressionPropertyDescriptor(object);
			addInitialisationPropertyDescriptor(object);
			addMetamodelPropertyDescriptor(object);
			addShowOnStartupPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the End User Documentation feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEndUserDocumentationPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_EndUserDocumentedElement_endUserDocumentation_feature"),
				getString("_UI_EndUserDocumentedElement_endUserDocumentation_description"),
				DescriptionPackage.Literals.END_USER_DOCUMENTED_ELEMENT__END_USER_DOCUMENTATION, true, true, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_DocumentationPropertyCategory"), null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IdentifiedElement_name_feature"), getString("_UI_IdentifiedElement_name_description"),
				DescriptionPackage.Literals.IDENTIFIED_ELEMENT__NAME, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), null));
	}

	/**
	 * This adds a property descriptor for the Label feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLabelPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(
				((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
				getString("_UI_IdentifiedElement_label_feature"), getString("_UI_IdentifiedElement_label_description"),
				DescriptionPackage.Literals.IDENTIFIED_ELEMENT__LABEL, true, false, false,
				ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), null));
	}

	/**
	 * This adds a property descriptor for the Title Expression feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTitleExpressionPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_RepresentationDescription_titleExpression_feature"),
						getString("_UI_RepresentationDescription_titleExpression_description"),
						DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION, true, false, false,
						ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, getString("_UI_AdvancedPropertyCategory"), null));
	}

	/**
	 * This adds a property descriptor for the Initialisation feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addInitialisationPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_RepresentationDescription_initialisation_feature"),
						getString("_UI_RepresentationDescription_initialisation_description"),
						DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__INITIALISATION, true, false, false,
						ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), null));
	}

	/**
	 * This adds a property descriptor for the Metamodel feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMetamodelPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_RepresentationDescription_metamodel_feature"),
						getString("_UI_RepresentationDescription_metamodel_description"),
						DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__METAMODEL, true, false, true, null,
						getString("_UI_MetamodelsPropertyCategory"), null));
	}

	/**
	 * This adds a property descriptor for the Show On Startup feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addShowOnStartupPropertyDescriptor(Object object) {
		itemPropertyDescriptors
				.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
						getResourceLocator(), getString("_UI_RepresentationDescription_showOnStartup_feature"),
						getString("_UI_RepresentationDescription_showOnStartup_description"),
						DescriptionPackage.Literals.REPRESENTATION_DESCRIPTION__SHOW_ON_STARTUP, true, false, false,
						ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE, getString("_UI_GeneralPropertyCategory"), null));
	}

	/**
	 * This returns AdapterFactoryTreeDescription.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/AdapterFactoryTreeDescription.png"));
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
		String label = ((AdapterFactoryTreeDescription) object).getName();
		return label == null || label.length() == 0 ? getString("_UI_AdapterFactoryTreeDescription_type")
				: getString("_UI_AdapterFactoryTreeDescription_type") + " " + label;
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

		switch (notification.getFeatureID(AdapterFactoryTreeDescription.class)) {
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__END_USER_DOCUMENTATION:
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__NAME:
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__LABEL:
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__TITLE_EXPRESSION:
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__INITIALISATION:
		case TreePackage.ADAPTER_FACTORY_TREE_DESCRIPTION__SHOW_ON_STARTUP:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return TreeEditPlugin.INSTANCE;
	}

}
