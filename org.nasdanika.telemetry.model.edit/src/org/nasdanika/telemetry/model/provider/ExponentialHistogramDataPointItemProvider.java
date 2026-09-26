/**
 */
package org.nasdanika.telemetry.model.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import org.nasdanika.telemetry.model.ExponentialHistogramDataPoint;
import org.nasdanika.telemetry.model.ModelFactory;
import org.nasdanika.telemetry.model.ModelPackage;

/**
 * This is the item provider adapter for a {@link org.nasdanika.telemetry.model.ExponentialHistogramDataPoint} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ExponentialHistogramDataPointItemProvider 
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExponentialHistogramDataPointItemProvider(AdapterFactory adapterFactory) {
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

			addStartTimeUnixNanoPropertyDescriptor(object);
			addTimeUnixNanoPropertyDescriptor(object);
			addCountPropertyDescriptor(object);
			addSumPropertyDescriptor(object);
			addScalePropertyDescriptor(object);
			addZeroCountPropertyDescriptor(object);
			addFlagsPropertyDescriptor(object);
			addMinPropertyDescriptor(object);
			addMaxPropertyDescriptor(object);
			addZeroThresholdPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Start Time Unix Nano feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStartTimeUnixNanoPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ExponentialHistogramDataPoint_startTimeUnixNano_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ExponentialHistogramDataPoint_startTimeUnixNano_feature", "_UI_ExponentialHistogramDataPoint_type"),
				 ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__START_TIME_UNIX_NANO,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Time Unix Nano feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTimeUnixNanoPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ExponentialHistogramDataPoint_timeUnixNano_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ExponentialHistogramDataPoint_timeUnixNano_feature", "_UI_ExponentialHistogramDataPoint_type"),
				 ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__TIME_UNIX_NANO,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Count feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addCountPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ExponentialHistogramDataPoint_count_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ExponentialHistogramDataPoint_count_feature", "_UI_ExponentialHistogramDataPoint_type"),
				 ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__COUNT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Sum feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSumPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ExponentialHistogramDataPoint_sum_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ExponentialHistogramDataPoint_sum_feature", "_UI_ExponentialHistogramDataPoint_type"),
				 ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__SUM,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Scale feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addScalePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ExponentialHistogramDataPoint_scale_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ExponentialHistogramDataPoint_scale_feature", "_UI_ExponentialHistogramDataPoint_type"),
				 ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__SCALE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Zero Count feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addZeroCountPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ExponentialHistogramDataPoint_zeroCount_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ExponentialHistogramDataPoint_zeroCount_feature", "_UI_ExponentialHistogramDataPoint_type"),
				 ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__ZERO_COUNT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Flags feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFlagsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ExponentialHistogramDataPoint_flags_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ExponentialHistogramDataPoint_flags_feature", "_UI_ExponentialHistogramDataPoint_type"),
				 ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__FLAGS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Min feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMinPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ExponentialHistogramDataPoint_min_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ExponentialHistogramDataPoint_min_feature", "_UI_ExponentialHistogramDataPoint_type"),
				 ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__MIN,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Max feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMaxPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ExponentialHistogramDataPoint_max_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ExponentialHistogramDataPoint_max_feature", "_UI_ExponentialHistogramDataPoint_type"),
				 ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__MAX,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Zero Threshold feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addZeroThresholdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ExponentialHistogramDataPoint_zeroThreshold_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ExponentialHistogramDataPoint_zeroThreshold_feature", "_UI_ExponentialHistogramDataPoint_type"),
				 ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__ZERO_THRESHOLD,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.REAL_VALUE_IMAGE,
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
			childrenFeatures.add(ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__ATTRIBUTES);
			childrenFeatures.add(ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__POSITIVE);
			childrenFeatures.add(ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__NEGATIVE);
			childrenFeatures.add(ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__EXEMPLARS);
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
	 * This returns ExponentialHistogramDataPoint.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ExponentialHistogramDataPoint"));
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
		ExponentialHistogramDataPoint exponentialHistogramDataPoint = (ExponentialHistogramDataPoint)object;
		return getString("_UI_ExponentialHistogramDataPoint_type") + " " + exponentialHistogramDataPoint.getStartTimeUnixNano();
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

		switch (notification.getFeatureID(ExponentialHistogramDataPoint.class)) {
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT__START_TIME_UNIX_NANO:
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT__TIME_UNIX_NANO:
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT__COUNT:
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT__SUM:
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT__SCALE:
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT__ZERO_COUNT:
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT__FLAGS:
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT__MIN:
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT__MAX:
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT__ZERO_THRESHOLD:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT__ATTRIBUTES:
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT__POSITIVE:
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT__NEGATIVE:
			case ModelPackage.EXPONENTIAL_HISTOGRAM_DATA_POINT__EXEMPLARS:
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
				(ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__ATTRIBUTES,
				 ModelFactory.eINSTANCE.createKeyValue()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__POSITIVE,
				 ModelFactory.eINSTANCE.createExponentialHistogramDataPointBuckets()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__NEGATIVE,
				 ModelFactory.eINSTANCE.createExponentialHistogramDataPointBuckets()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__EXEMPLARS,
				 ModelFactory.eINSTANCE.createExemplar()));
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
			childFeature == ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__POSITIVE ||
			childFeature == ModelPackage.Literals.EXPONENTIAL_HISTOGRAM_DATA_POINT__NEGATIVE;

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
		return TelemetryEditPlugin.INSTANCE;
	}

}
