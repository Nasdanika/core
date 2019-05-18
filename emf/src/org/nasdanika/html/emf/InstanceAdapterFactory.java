package org.nasdanika.html.emf;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * Adapter factory delegating to a single shared adapter instance for all notifiers.
 * @author Pavel Vlasov
 *
 */
public class InstanceAdapterFactory<T> extends DelegatingAdapterFactory<T> {
	
	private T adapter;
	
	public InstanceAdapterFactory(Class<T> type, ClassLoader proxyClassLoader, T adapter) {
		this(EcorePackage.Literals.EOBJECT, type, proxyClassLoader, adapter);
	}	

	public InstanceAdapterFactory(EClass eClass, Class<T> type, ClassLoader proxyClassLoader, T adapter) {
		super(eClass, type, proxyClassLoader);
		this.adapter = adapter;
	}

	@Override
	protected T doCreateAdapter(Notifier target) {
		return adapter;
	}	

}
