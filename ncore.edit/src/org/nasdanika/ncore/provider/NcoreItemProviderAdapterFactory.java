/**
 */
package org.nasdanika.ncore.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.nasdanika.ncore.util.NcoreAdapterFactory;

/**
 * This is the factory that is used to provide the interfaces needed to support Viewers.
 * The adapters generated by this factory convert EMF adapter notifications into calls to {@link #fireNotifyChanged fireNotifyChanged}.
 * The adapters also support Eclipse property sheets.
 * Note that most of the adapters are shared among multiple instances.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class NcoreItemProviderAdapterFactory extends NcoreAdapterFactory implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {
	/**
	 * This keeps track of the root adapter factory that delegates to this adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ComposedAdapterFactory parentAdapterFactory;

	/**
	 * This is used to implement {@link org.eclipse.emf.edit.provider.IChangeNotifier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	/**
	 * This keeps track of all the supported types checked by {@link #isFactoryForType isFactoryForType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	/**
	 * This constructs an instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NcoreItemProviderAdapterFactory() {
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(IItemPropertySource.class);
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.ContactMethod} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContactMethodItemProvider contactMethodItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.ContactMethod}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createContactMethodAdapter() {
		if (contactMethodItemProvider == null) {
			contactMethodItemProvider = new ContactMethodItemProvider(this);
		}

		return contactMethodItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.EMail} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EMailItemProvider eMailItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.EMail}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createEMailAdapter() {
		if (eMailItemProvider == null) {
			eMailItemProvider = new EMailItemProvider(this);
		}

		return eMailItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.Phone} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PhoneItemProvider phoneItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.Phone}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPhoneAdapter() {
		if (phoneItemProvider == null) {
			phoneItemProvider = new PhoneItemProvider(this);
		}

		return phoneItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.PostalAddress} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PostalAddressItemProvider postalAddressItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.PostalAddress}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPostalAddressAdapter() {
		if (postalAddressItemProvider == null) {
			postalAddressItemProvider = new PostalAddressItemProvider(this);
		}

		return postalAddressItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.WebAddress} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WebAddressItemProvider webAddressItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.WebAddress}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createWebAddressAdapter() {
		if (webAddressItemProvider == null) {
			webAddressItemProvider = new WebAddressItemProvider(this);
		}

		return webAddressItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.SupplierFactoryReference} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SupplierFactoryReferenceItemProvider supplierFactoryReferenceItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.SupplierFactoryReference}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSupplierFactoryReferenceAdapter() {
		if (supplierFactoryReferenceItemProvider == null) {
			supplierFactoryReferenceItemProvider = new SupplierFactoryReferenceItemProvider(this);
		}

		return supplierFactoryReferenceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.TypedElement} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypedElementItemProvider typedElementItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.TypedElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTypedElementAdapter() {
		if (typedElementItemProvider == null) {
			typedElementItemProvider = new TypedElementItemProvider(this);
		}

		return typedElementItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.Supplier} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SupplierItemProvider supplierItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.Supplier}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSupplierAdapter() {
		if (supplierItemProvider == null) {
			supplierItemProvider = new SupplierItemProvider(this);
		}

		return supplierItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.Resource} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ResourceItemProvider resourceItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.Resource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createResourceAdapter() {
		if (resourceItemProvider == null) {
			resourceItemProvider = new ResourceItemProvider(this);
		}

		return resourceItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.Value} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValueItemProvider valueItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.Value}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createValueAdapter() {
		if (valueItemProvider == null) {
			valueItemProvider = new ValueItemProvider(this);
		}

		return valueItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.Null} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NullItemProvider nullItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.Null}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createNullAdapter() {
		if (nullItemProvider == null) {
			nullItemProvider = new NullItemProvider(this);
		}

		return nullItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.Operation} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationItemProvider operationItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.Operation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createOperationAdapter() {
		if (operationItemProvider == null) {
			operationItemProvider = new OperationItemProvider(this);
		}

		return operationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.Array} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ArrayItemProvider arrayItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.Array}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createArrayAdapter() {
		if (arrayItemProvider == null) {
			arrayItemProvider = new ArrayItemProvider(this);
		}

		return arrayItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.Context} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContextItemProvider contextItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.Context}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createContextAdapter() {
		if (contextItemProvider == null) {
			contextItemProvider = new ContextItemProvider(this);
		}

		return contextItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.TypedEntry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypedEntryItemProvider typedEntryItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.TypedEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createTypedEntryAdapter() {
		if (typedEntryItemProvider == null) {
			typedEntryItemProvider = new TypedEntryItemProvider(this);
		}

		return typedEntryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.SupplierEntry} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SupplierEntryItemProvider supplierEntryItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.SupplierEntry}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createSupplierEntryAdapter() {
		if (supplierEntryItemProvider == null) {
			supplierEntryItemProvider = new SupplierEntryItemProvider(this);
		}

		return supplierEntryItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.Map} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MapItemProvider mapItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.Map}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createMapAdapter() {
		if (mapItemProvider == null) {
			mapItemProvider = new MapItemProvider(this);
		}

		return mapItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.Property} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyItemProvider propertyItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.Property}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createPropertyAdapter() {
		if (propertyItemProvider == null) {
			propertyItemProvider = new PropertyItemProvider(this);
		}

		return propertyItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.Function} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FunctionItemProvider functionItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.Function}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createFunctionAdapter() {
		if (functionItemProvider == null) {
			functionItemProvider = new FunctionItemProvider(this);
		}

		return functionItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.List} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ListItemProvider listItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.List}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createListAdapter() {
		if (listItemProvider == null) {
			listItemProvider = new ListItemProvider(this);
		}

		return listItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.Object} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ObjectItemProvider objectItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.Object}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createObjectAdapter() {
		if (objectItemProvider == null) {
			objectItemProvider = new ObjectItemProvider(this);
		}

		return objectItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.HttpCall} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HttpCallItemProvider httpCallItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.HttpCall}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createHttpCallAdapter() {
		if (httpCallItemProvider == null) {
			httpCallItemProvider = new HttpCallItemProvider(this);
		}

		return httpCallItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.RestOperation} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RestOperationItemProvider restOperationItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.RestOperation}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRestOperationAdapter() {
		if (restOperationItemProvider == null) {
			restOperationItemProvider = new RestOperationItemProvider(this);
		}

		return restOperationItemProvider;
	}

	/**
	 * This keeps track of the one adapter used for all {@link org.nasdanika.ncore.RestFunction} instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RestFunctionItemProvider restFunctionItemProvider;

	/**
	 * This creates an adapter for a {@link org.nasdanika.ncore.RestFunction}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter createRestFunctionAdapter() {
		if (restFunctionItemProvider == null) {
			restFunctionItemProvider = new RestFunctionItemProvider(this);
		}

		return restFunctionItemProvider;
	}

	/**
	 * This returns the root adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	/**
	 * This sets the composed adapter factory that contains this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) || super.isFactoryForType(type);
	}

	/**
	 * This implementation substitutes the factory itself as the key for the adapter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Adapter adapt(Notifier notifier, Object type) {
		return super.adapt(notifier, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object adapt(Object object, Object type) {
		if (isFactoryForType(type)) {
			Object adapter = super.adapt(object, type);
			if (!(type instanceof Class<?>) || (((Class<?>)type).isInstance(adapter))) {
				return adapter;
			}
		}

		return null;
	}

	/**
	 * This adds a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	/**
	 * This removes a listener.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	/**
	 * This delegates to {@link #changeNotifier} and to {@link #parentAdapterFactory}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	/**
	 * This disposes all of the item providers created by this factory. 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void dispose() {
		if (contactMethodItemProvider != null) contactMethodItemProvider.dispose();
		if (eMailItemProvider != null) eMailItemProvider.dispose();
		if (phoneItemProvider != null) phoneItemProvider.dispose();
		if (postalAddressItemProvider != null) postalAddressItemProvider.dispose();
		if (webAddressItemProvider != null) webAddressItemProvider.dispose();
		if (supplierFactoryReferenceItemProvider != null) supplierFactoryReferenceItemProvider.dispose();
		if (typedElementItemProvider != null) typedElementItemProvider.dispose();
		if (supplierItemProvider != null) supplierItemProvider.dispose();
		if (resourceItemProvider != null) resourceItemProvider.dispose();
		if (valueItemProvider != null) valueItemProvider.dispose();
		if (nullItemProvider != null) nullItemProvider.dispose();
		if (operationItemProvider != null) operationItemProvider.dispose();
		if (arrayItemProvider != null) arrayItemProvider.dispose();
		if (contextItemProvider != null) contextItemProvider.dispose();
		if (typedEntryItemProvider != null) typedEntryItemProvider.dispose();
		if (supplierEntryItemProvider != null) supplierEntryItemProvider.dispose();
		if (mapItemProvider != null) mapItemProvider.dispose();
		if (propertyItemProvider != null) propertyItemProvider.dispose();
		if (functionItemProvider != null) functionItemProvider.dispose();
		if (listItemProvider != null) listItemProvider.dispose();
		if (objectItemProvider != null) objectItemProvider.dispose();
		if (httpCallItemProvider != null) httpCallItemProvider.dispose();
		if (restOperationItemProvider != null) restOperationItemProvider.dispose();
		if (restFunctionItemProvider != null) restFunctionItemProvider.dispose();
	}

}
