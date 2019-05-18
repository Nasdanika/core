package org.nasdanika.html.emf;

import java.util.function.Supplier;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * Adapter factory delegating to {@link Supplier}, which can be a constructor.
 * @author Pavel Vlasov
 *
 */
public class SupplierAdapterFactory<T> extends DelegatingAdapterFactory<T> {
	
	private Supplier<T> supplier;
	
	public SupplierAdapterFactory(Class<T> type, ClassLoader proxyClassLoader, Supplier<T> supplier) {
		this(EcorePackage.Literals.EOBJECT, type, proxyClassLoader, supplier);
	}	

	public SupplierAdapterFactory(EClass eClass, Class<T> type, ClassLoader proxyClassLoader, Supplier<T> supplier) {
		super(eClass, type, proxyClassLoader);
		this.supplier = supplier;
	}

	@Override
	protected T doCreateAdapter(Notifier target) {
		return supplier.get();
	}	

}
