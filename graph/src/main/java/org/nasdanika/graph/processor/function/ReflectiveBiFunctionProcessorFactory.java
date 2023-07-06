package org.nasdanika.graph.processor.function;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reflector;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;

/**
 * A processor factory with {@link BiFunction} handler and endpoint
 * @param <P> Processor type
 * @param <T> Handler parameter type
 * @param <U> Handler return type
 * @param <V> Endpoint parameter type
 * @param <W> Endpoint return type
 * @param <R> Registry type
 */
public abstract class ReflectiveBiFunctionProcessorFactory<T,U,V,W,R> extends Reflector implements BiFunctionProcessorFactory<T,U,V,W,R> {
	
	@Retention(RUNTIME)
	@Target({ METHOD, TYPE })
	public @interface NodeProcessorFactory {
		
		/**
		 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/docs/5.3.22/reference/html/core.html#expressions">Spring boolean expression</a>
		 * which is evaluated in the context of an element. 
		 * @return
		 */
		String value() default "";
		
		/**
		 * Matching priority. Defaults to 0. Higher priorities are matched before lower priorities.
		 * @return
		 */
		int priority() default 0;
		
		/**
		 * Node's object type
		 * @return
		 */
		Class<? extends Node> type() default Node.class; 

	}
	
	@Retention(RUNTIME)
	@Target({ METHOD, TYPE })
	public @interface ConnectionProcessorFactory {
		
		/**
		 * If not blank, the value shall be a <a href="https://docs.spring.io/spring-framework/docs/5.3.22/reference/html/core.html#expressions">Spring boolean expression</a>
		 * which is evaluated in the context of an element. 
		 * @return
		 */
		String value() default "";
		
		/**
		 * Matching priority. Defaults to 0. Higher priorities are matched before lower priorities.
		 * @return
		 */
		int priority() default 0;
		
		/**
		 * Node's object type
		 * @return
		 */
		Class<? extends Connection> type() default Connection.class; 

	}
	
	protected List<AnnotatedElementRecord> annotatedElementRecords = new ArrayList<>();

	protected ReflectiveBiFunctionProcessorFactory(Object... targets) {
		for (Object target: targets) {
			getAnnotatedElementRecords(target).forEach(annotatedElementRecords::add);
		}
	}	

	@SuppressWarnings("unchecked")
	@Override
	public ConnectionProcessor<T,U,V,W> createConnectionProcessor(
			ConnectionProcessorConfig<BiFunction<T,ProgressMonitor,U>, BiFunction<T,ProgressMonitor,U>, BiFunction<V,ProgressMonitor,W>,R> connectionProcessorConfig,
			ProgressMonitor progressMonitor) {
		
		Connection connection = connectionProcessorConfig.getElement();
		
		Optional<AnnotatedElementRecord> factoryMethodEntryOptional = annotatedElementRecords
				.stream()
				.filter(aer -> aer.test(connectionProcessorConfig.getElement()) && aer.getAnnotatedElement() instanceof Method && isConnectionProcessorFactoryMethod(aer, connection))
				.sorted((a,b) -> compareConnectionProcessorFactoryMethods(a.getAnnotatedElement(), b.getAnnotatedElement()))
				.findFirst();
		
		if (factoryMethodEntryOptional.isEmpty()) {
			return null;
		}
		
		AnnotatedElementRecord factoryAnnotatedElementRecord = factoryMethodEntryOptional.get();		
		return (ConnectionProcessor<T, U, V, W>) factoryAnnotatedElementRecord.invoke(connectionProcessorConfig, progressMonitor); 		
		
	}
	
	protected boolean isConnectionProcessorFactoryMethod(AnnotatedElementRecord annotatedElementRecord, Connection connection) {
		ConnectionProcessorFactory annotation = annotatedElementRecord.getAnnotation(ConnectionProcessorFactory.class);
		if (annotation == null) {
			return false;
		}
		
		if (!annotation.type().isInstance(connection)) {
			return false;
		}
		
		return matchPredicate(connection, annotation.value());		
	}

	protected int compareConnectionProcessorFactoryMethods(AnnotatedElement a, AnnotatedElement b) {
		ConnectionProcessorFactory aAnnotation = a.getAnnotation(ConnectionProcessorFactory.class);
		ConnectionProcessorFactory bAnnotation = b.getAnnotation(ConnectionProcessorFactory.class);
		int priorityCmp = bAnnotation.priority() - aAnnotation.priority();
		if (priorityCmp != 0) {
			return priorityCmp;
		}
		Class<? extends Connection> aType = aAnnotation.type(); 
		Class<? extends Connection> bType = bAnnotation.type();
		if (aType == bType) {
			return 0;
		}
		if (aType.isAssignableFrom(bType)) {
			return 1;
		}
		if (bType.isAssignableFrom(aType)) {
			return -1;
		}
		return a.hashCode() - b.hashCode();
	}			

	@SuppressWarnings("unchecked")
	@Override
	public NodeProcessor<T,U,V,W> createNodeProcessor(
			 NodeProcessorConfig<BiFunction<T,ProgressMonitor,U>, BiFunction<T,ProgressMonitor,U>, BiFunction<V,ProgressMonitor,W>, R> nodeProcessorConfig,
			 Map<Connection, BiFunction<V,ProgressMonitor,W>> incomingEndpoints,
			 Map<Connection, BiFunction<V,ProgressMonitor,W>> outgoingEndpoints,			 
			 ProgressMonitor progressMonitor) {
		
		Node node = nodeProcessorConfig.getElement();
		
		Optional<AnnotatedElementRecord> factoryMethodEntryOptional = annotatedElementRecords
				.stream()
				.filter(aer -> aer.test(nodeProcessorConfig.getElement()) && aer.getAnnotatedElement() instanceof Method && isNodeProcessorFactoryMethod(aer, node))
				.sorted((a,b) -> compareNodeProcessorFactoryMethods(a.getAnnotatedElement(), b.getAnnotatedElement()))
				.findFirst();
		
		if (factoryMethodEntryOptional.isEmpty()) {
			return null;
		}
		
		AnnotatedElementRecord factoryAnnotatedElementRecord = factoryMethodEntryOptional.get();		
		return (NodeProcessor<T, U, V, W>) factoryAnnotatedElementRecord.invoke(nodeProcessorConfig, incomingEndpoints, outgoingEndpoints, progressMonitor); 		
	}
	
	protected boolean isNodeProcessorFactoryMethod(AnnotatedElementRecord annotatedElementRecord, Node node) {
		NodeProcessorFactory annotation = annotatedElementRecord.getAnnotation(NodeProcessorFactory.class);
		if (annotation == null) {
			return false;
		}
		
		if (!annotation.type().isInstance(node)) {
			return false;
		}
		
		return matchPredicate(node, annotation.value());		
	}

	protected int compareNodeProcessorFactoryMethods(AnnotatedElement a, AnnotatedElement b) {
		NodeProcessorFactory aAnnotation = a.getAnnotation(NodeProcessorFactory.class);
		NodeProcessorFactory bAnnotation = b.getAnnotation(NodeProcessorFactory.class);
		int priorityCmp = bAnnotation.priority() - aAnnotation.priority();
		if (priorityCmp != 0) {
			return priorityCmp;
		}
		Class<? extends Node> aType = aAnnotation.type(); 
		Class<? extends Node> bType = bAnnotation.type();
		if (aType == bType) {
			return 0;
		}
		if (aType.isAssignableFrom(bType)) {
			return 1;
		}
		if (bType.isAssignableFrom(aType)) {
			return -1;
		}
		return a.hashCode() - b.hashCode();
	}			
		
}
