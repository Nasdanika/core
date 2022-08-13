package org.nasdanika.drawio.processor;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.function.Supplier;

import org.nasdanika.drawio.Connection;

/**
 * Mix-in interface which creates handler proxies as dynamic proxies.
 * @author Pavel
 *
 * @param <P>
 * @param <H>
 * @param <E>
 */
public interface DynamicProxyProcessorFactory<P, H, E> extends ProcessorFactory<P, H, E> {
	
	@SuppressWarnings("unchecked")
	@Override
	default H createHandlerProxy(Connection connection, Supplier<H> handlerSupplier, HandlerType type) {
		return (H) Proxy.newProxyInstance(getClassLoader(), new Class[] { getHandlerInterface(connection, type) }, (Object proxy, Method method, Object[] args) -> {
			return method.invoke(handlerSupplier.get(), args);
		});
	}
	
	/**
	 * @return Handler interface class to create a dynamic proxy.
	 */
	Class<?> getHandlerInterface(Connection connection, HandlerType type);
	
	/**
	 * @return Classloader to create a proxy. This implementation returns current {@link Thread}'s context classloader.
	 */
	default ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}

}
