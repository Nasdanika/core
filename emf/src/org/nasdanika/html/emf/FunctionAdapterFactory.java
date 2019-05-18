package org.nasdanika.html.emf;

import java.util.function.Function;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * @param <T> Adapter type.
 * @param <N> Notifier type.
 */
public class FunctionAdapterFactory<T, N> extends DelegatingAdapterFactory<T> {
	
	private Function<N, T> function;
	
	/**
	 * Uses {@link EObject}'s {@link EClass} as eClass argument
	 * @param type
	 * @param proxyClassLoader
	 * @param function
	 */
	public FunctionAdapterFactory(Class<T> type, ClassLoader proxyClassLoader, Function<N, T> function) {
		this(EcorePackage.Literals.EOBJECT, type, proxyClassLoader, function);
	} 
	
	public FunctionAdapterFactory(EClass eClass, Class<T> type, ClassLoader proxyClassLoader, Function<N, T> function) {
		super(eClass, type, proxyClassLoader);
		this.function = function;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T doCreateAdapter(Notifier target) {
		return function.apply((N) target);
	}

}
