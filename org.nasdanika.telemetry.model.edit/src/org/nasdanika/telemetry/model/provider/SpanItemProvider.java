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

import org.nasdanika.telemetry.model.ModelFactory;
import org.nasdanika.telemetry.model.ModelPackage;
import org.nasdanika.telemetry.model.Span;

/**
 * This is the item provider adapter for a {@link org.nasdanika.telemetry.model.Span} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class SpanItemProvider 
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
	public SpanItemProvider(AdapterFactory adapterFactory) {
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

			addTraceIdPropertyDescriptor(object);
			addSpanIdPropertyDescriptor(object);
			addTraceStatePropertyDescriptor(object);
			addParentSpanIdPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
			addKindPropertyDescriptor(object);
			addStartTimeUnixNanoPropertyDescriptor(object);
			addEndTimeUnixNanoPropertyDescriptor(object);
			addDroppedAttributesCountPropertyDescriptor(object);
			addDroppedEventsCountPropertyDescriptor(object);
			addDroppedLinksCountPropertyDescriptor(object);
			addFlagsPropertyDescriptor(object);
			addLogRecordsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Trace Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTraceIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Span_traceId_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Span_traceId_feature", "_UI_Span_type"),
				 ModelPackage.Literals.SPAN__TRACE_ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Span Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addSpanIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Span_spanId_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Span_spanId_feature", "_UI_Span_type"),
				 ModelPackage.Literals.SPAN__SPAN_ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Trace State feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addTraceStatePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Span_traceState_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Span_traceState_feature", "_UI_Span_type"),
				 ModelPackage.Literals.SPAN__TRACE_STATE,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Parent Span Id feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addParentSpanIdPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Span_parentSpanId_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Span_parentSpanId_feature", "_UI_Span_type"),
				 ModelPackage.Literals.SPAN__PARENT_SPAN_ID,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Span_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Span_name_feature", "_UI_Span_type"),
				 ModelPackage.Literals.SPAN__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Kind feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addKindPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Span_kind_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Span_kind_feature", "_UI_Span_type"),
				 ModelPackage.Literals.SPAN__KIND,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
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
				 getString("_UI_Span_startTimeUnixNano_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Span_startTimeUnixNano_feature", "_UI_Span_type"),
				 ModelPackage.Literals.SPAN__START_TIME_UNIX_NANO,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the End Time Unix Nano feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addEndTimeUnixNanoPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Span_endTimeUnixNano_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Span_endTimeUnixNano_feature", "_UI_Span_type"),
				 ModelPackage.Literals.SPAN__END_TIME_UNIX_NANO,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Dropped Attributes Count feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDroppedAttributesCountPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Span_droppedAttributesCount_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Span_droppedAttributesCount_feature", "_UI_Span_type"),
				 ModelPackage.Literals.SPAN__DROPPED_ATTRIBUTES_COUNT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Dropped Events Count feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDroppedEventsCountPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Span_droppedEventsCount_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Span_droppedEventsCount_feature", "_UI_Span_type"),
				 ModelPackage.Literals.SPAN__DROPPED_EVENTS_COUNT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Dropped Links Count feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDroppedLinksCountPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Span_droppedLinksCount_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Span_droppedLinksCount_feature", "_UI_Span_type"),
				 ModelPackage.Literals.SPAN__DROPPED_LINKS_COUNT,
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
				 getString("_UI_Span_flags_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Span_flags_feature", "_UI_Span_type"),
				 ModelPackage.Literals.SPAN__FLAGS,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Log Records feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addLogRecordsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_Span_logRecords_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_Span_logRecords_feature", "_UI_Span_type"),
				 ModelPackage.Literals.SPAN__LOG_RECORDS,
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
			childrenFeatures.add(ModelPackage.Literals.SPAN__ATTRIBUTES);
			childrenFeatures.add(ModelPackage.Literals.SPAN__EVENTS);
			childrenFeatures.add(ModelPackage.Literals.SPAN__LINKS);
			childrenFeatures.add(ModelPackage.Literals.SPAN__STATUS);
			childrenFeatures.add(ModelPackage.Literals.SPAN__CHANGE_DESCRIPTION);
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
	 * This returns Span.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/Span"));
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
		String label = ((Span)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_Span_type") :
			getString("_UI_Span_type") + " " + label;
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

		switch (notification.getFeatureID(Span.class)) {
			case ModelPackage.SPAN__TRACE_ID:
			case ModelPackage.SPAN__SPAN_ID:
			case ModelPackage.SPAN__TRACE_STATE:
			case ModelPackage.SPAN__PARENT_SPAN_ID:
			case ModelPackage.SPAN__NAME:
			case ModelPackage.SPAN__KIND:
			case ModelPackage.SPAN__START_TIME_UNIX_NANO:
			case ModelPackage.SPAN__END_TIME_UNIX_NANO:
			case ModelPackage.SPAN__DROPPED_ATTRIBUTES_COUNT:
			case ModelPackage.SPAN__DROPPED_EVENTS_COUNT:
			case ModelPackage.SPAN__DROPPED_LINKS_COUNT:
			case ModelPackage.SPAN__FLAGS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ModelPackage.SPAN__ATTRIBUTES:
			case ModelPackage.SPAN__EVENTS:
			case ModelPackage.SPAN__LINKS:
			case ModelPackage.SPAN__STATUS:
			case ModelPackage.SPAN__CHANGE_DESCRIPTION:
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
				(ModelPackage.Literals.SPAN__ATTRIBUTES,
				 ModelFactory.eINSTANCE.createKeyValue()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.SPAN__EVENTS,
				 ModelFactory.eINSTANCE.createSpanEvent()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.SPAN__LINKS,
				 ModelFactory.eINSTANCE.createSpanLink()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.SPAN__STATUS,
				 ModelFactory.eINSTANCE.createSpanStatus()));
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
