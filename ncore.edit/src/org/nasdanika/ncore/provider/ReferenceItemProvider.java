/**
 */
package org.nasdanika.ncore.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.nasdanika.emf.edit.NasdanikaItemProviderAdapter;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Reference;

/**
 * This is the item provider adapter for a {@link org.nasdanika.ncore.Reference} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ReferenceItemProvider extends ModelElementItemProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferenceItemProvider(AdapterFactory adapterFactory) {
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

			addTargetPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Target feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addTargetPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptorWithChoiceOfValuesSource(
				 getResourceLocator(),
				 getString("_UI_Reference_target_feature"),
				 NcorePackage.Literals.REFERENCE__TARGET,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null,
				 this::getTargetChoiceOfValues));
	}
	
	private Collection<Object> getTargetChoiceOfValues(Object object, Collection<Object> choices) {
		if (object instanceof EObject && choices != null) {
			EObject eObject = (EObject) object;
			EObject eContainer = eObject.eContainer();
			if (eContainer != null) {
				EReference eContainmentReference = eObject.eContainmentFeature();
				if (eContainmentReference != null) {
					for (Adapter adapter: eContainer.eAdapters()) {
						if (adapter instanceof NasdanikaItemProviderAdapter) {
							Collection<Object> ret = new ArrayList<>();
							for (Object choice: choices) {
								if (choice instanceof EObject && ((NasdanikaItemProviderAdapter) adapter).accept(eContainer, eContainmentReference, (EObject) choice, false)) {
									ret.add(choice);
								}
							}
							return ret;
						}
					}
				}
			}
		}
		return choices;
	}

	/**
	 * This returns Reference.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Reference"));
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
		String label = ((Reference)object).getTitle();
		return label == null || label.length() == 0 ?
			getString("_UI_Reference_type") :
			getString("_UI_Reference_type") + " " + label;
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void notifyChanged(Notification notification) {
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

}
