package org.nasdanika.emf;

import java.util.function.BiFunction;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * Adapter factory which creates adapter using a {@link BiFunction}, e.g. constructor with two arguments.
 * The first argument is the notifier, the second is the factory. The second argument can be used to get access to the root adapter factory, 
 * e.g. for retrieval of contextual information.
 * @param <T> Adapter type.
 * @param <N> Notifier type.
 */
public class BiFunctionAdapterFactory<T, N> extends DelegatingAdapterFactory<T> {
	
	private BiFunction<N, BiFunctionAdapterFactory<T, N> ,  T> biFunction;
	
	/**
	 * Uses {@link EObject}'s {@link EClass} as eClass argument
	 * @param type
	 * @param proxyClassLoader
	 * @param biFunction
	 */
	public BiFunctionAdapterFactory(Class<T> type, ClassLoader proxyClassLoader, BiFunction<N, BiFunctionAdapterFactory<T, N> ,  T> biFunction) {
		this(EcorePackage.Literals.EOBJECT, type, proxyClassLoader, biFunction);
	} 
	
	public BiFunctionAdapterFactory(EClass eClass, Class<T> type, ClassLoader proxyClassLoader, BiFunction<N, BiFunctionAdapterFactory<T, N> ,  T> biFunction) {
		super(eClass, type, proxyClassLoader);
		this.biFunction = biFunction;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected T doCreateAdapter(Notifier target) {
		return biFunction.apply((N) target, this);
	}

}
