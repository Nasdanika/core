package org.nasdanika.graph;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Util;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * Graph element accepts visitors and supports streaming. 
 * @author Pavel
 *
 */
public interface Element {
	
	default Collection<? extends Element> getChildren() {
		return Collections.emptyList();
	}
	
	/**
	 * Accepts the visitor in children first way.
	 * @param visitor
	 */
	default void accept(Consumer<? super Element> visitor) {
		accept((e, cr) -> { 
			visitor.accept(e); 
			return null; 
		});
	}
	
	/**
	 * Accepts a result producing visitor {@link BiFunction}. Passes to it a map of results collected from children, nulls are not included.   
	 * @param <T>
	 * @param visitor
	 * @return result returned by the visitor.
	 */
	default <T> T accept(BiFunction<? super Element, Map<? extends Element, T>, T> visitor) {
		Map<Element, T> childResults = new LinkedHashMap<>(); 
		getChildren()
			.stream()
			.map(child -> new AbstractMap.SimpleEntry<>(child, child.accept(visitor)))
			.forEach(e -> childResults.put(e.getKey(), e.getValue()));
		
		return visitor.apply(this, childResults);
	}
	
	/**
	 * Creates a visitor dispatching elements to methods annotated with {@link Handler}.
	 * Methods may have one or two parameters taking Element and a map of children.
	 */
	default Object dispatch(Object... targets) {
		return dispatch(Arrays.asList(targets));
	}
	
	private static int compareHandlers(Handler a, Handler b) {
		int ap = a.priority();
		int bp = b.priority();
		if (ap == bp) {
			return b.value().length() - a.value().length();
		}
		return bp - ap;
	}
	
	default Object dispatch(Collection<Object> targets) {
		return accept((element, childResults) -> {
			for (Object target: targets) {
				Optional<Method> handlerOptional = Arrays.stream(target.getClass().getMethods())
					.filter(m -> !Modifier.isAbstract(m.getModifiers()))	
					.filter(m -> m.getAnnotation(Handler.class) != null)
					.filter(m -> m.getParameterCount() == 1 || m.getParameterCount() == 2)
					.filter(m -> m.getParameters()[0].getType().isInstance(element))
					.filter(m -> m.getParameterCount() == 1 ? true : m.getParameters()[1].getType().isInstance(childResults))
					.filter(m -> matchPredicate(element, m.getAnnotation(Handler.class).value()))
					.sorted((a, b) -> compareHandlers(a.getAnnotation(Handler.class), b.getAnnotation(Handler.class)))
					.findFirst();
				
				if (!handlerOptional.isEmpty()) {
					Method handler = handlerOptional.get();
					try {
						if (handler.getParameterCount() == 1) {
							return handler.invoke(target, element);
						}
						return handler.invoke(target, element, childResults);					
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						throw new NasdanikaException("Error invoking handler: " + e, e);
					}
				}
			}
			return null;
		});
	}
	
	/**
	 * @return Stream containing this element and its children.
	 */
	default Stream<Element> stream() {
		BiFunction<Element, Map<? extends Element, Stream<Element>>, Stream<Element>> visitor = (element, childMappings) -> {
			return childMappings.values().stream().reduce(Stream.of(element), (a,b) -> Stream.concat(a, b));
		};
		return accept(visitor);
	}; 
	
	/**
	 * Parses and evaluates expression using <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions">Spring Expression Language</a> 
	 * @param obj
	 * @param expr 
	 * @return true if expression is blank or evaluates to true, false if the expression evaluates to false or throws EvaluationException.
	 */
	private boolean matchPredicate(Object obj, String expr) {
		if (Util.isBlank(expr)) {
			return true;
		}
		
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression(expr);			
		try {
			return exp.getValue(obj, Boolean.class);
		} catch (EvaluationException e) {
			return false;
		}
	}
	
	static <R,V> Element from(ContentProvider<R,V> contentProvider) {
		return from(contentProvider, (ref,root) -> new UnresolvedReferenceNode<R>(ref));
	}	
						
	static <R,V> Element from(ContentProvider<R,V> contentProvider, BiFunction<R,Map<ContentProvider<R,V>,Element>,Node> resolver) {
		Map<R,CompletableFuture<Node>> sourceRefs = new HashMap<>();
		Map<R,CompletableFuture<Node>> targetRefs = new HashMap<>();
		
		// Collecting references so we know which elements shall be nodes even if they don't have incoming/outgoing connections defined
		contentProvider.accept(cp -> {
			R sourceRef = cp.getSourceReference();
			if (sourceRef != null) {
				sourceRefs.computeIfAbsent(sourceRef, sr -> new CompletableFuture<>());
			}
			R targetRef = cp.getTargetReference();
			if (targetRef != null) {
				targetRefs.computeIfAbsent(targetRef, tr -> new CompletableFuture<>());
			}			
		});
				
		List<CompletableFuture<?>> futures = new ArrayList<>();		
		Map<ContentProvider<R,V>,Element> registry = new HashMap<>();
		CompletableFuture<Element> rootCF = from(contentProvider, sourceRefs, targetRefs, registry::put, futures::add);
				
		sourceRefs.entrySet()
			.stream()
			.filter(e -> e.getValue().isCompletedExceptionally())
			.forEach(e -> {
				CompletableFuture<Node> newValue = e.getValue().handle((n, th) -> {
					ThrowableNode<Throwable> throwableNode = new ThrowableNode<>(th);
					if (n != null) { // Can it be non-null?
						new SimpleConnection(throwableNode, n, true);
					}
					return throwableNode;
				});
				e.setValue(newValue);
			});
		
		targetRefs.entrySet()
			.stream()
			.filter(e -> e.getValue().isCompletedExceptionally())
			.forEach(e -> {
				CompletableFuture<Node> newValue = e.getValue().handle((n, th) -> {
					ThrowableNode<Throwable> throwableNode = new ThrowableNode<>(th);
					if (n != null) { // Can it be non-null?
						new SimpleConnection(throwableNode, n, true);
					}
					return throwableNode;
				});
				e.setValue(newValue);
			});
		
		// Resolving using the resolver
		CompletableFuture<Void> rrcf = rootCF.thenAccept(root -> {
			sourceRefs.entrySet()
				.stream()
				.filter(e -> !e.getValue().isDone())
				.forEach(e -> {
					R refId = e.getKey();
					Node resolvedNode = resolver.apply(refId, registry);
					e.getValue().complete(resolvedNode);
					CompletableFuture<Node> tRef = targetRefs.get(refId);
					if (tRef != null) {
						if (!tRef.complete(resolvedNode)) {
							throw new IllegalArgumentException("RefId was missing in the source, but present in the target: " + refId);
						}					
					}
				});
			
			targetRefs.entrySet()
				.stream()
				.filter(e -> !e.getValue().isDone())
				.forEach(e -> {
					e.getValue().complete(resolver.apply(e.getKey(), registry));
				});
		});	
		
		futures.add(rrcf);				
		
		// join completion 
		List<Throwable> thrown = new ArrayList<>();
		futures
			.stream()
			.filter(CompletableFuture::isCompletedExceptionally)
			.forEach(cf -> cf.handle((r,e) -> {
				if (e == null) {
					throw new IllegalArgumentException("Error shall not be null");
				}
				thrown.add(e);
				return null;
			}));
		
		sourceRefs.values()
			.stream()
			.filter(CompletableFuture::isCompletedExceptionally)
			.forEach(cf -> cf.handle((r,e) -> {
				if (e == null) {
					throw new IllegalArgumentException("Error shall not be null");
				}
				thrown.add(e);
				return null;
			}));
		
		targetRefs.values()
			.stream()
			.filter(CompletableFuture::isCompletedExceptionally)
			.forEach(cf -> cf.handle((r,e) -> {
				if (e == null) {
					throw new IllegalArgumentException("Error shall not be null");
				}
				thrown.add(e);
				return null;
			}));
		
		if (!thrown.isEmpty()) {
			NasdanikaException ne = new NasdanikaException("There's been errors during graph creation");
			for (Throwable th: thrown) {
				ne.addSuppressed(th);
			}
			throw ne;
		}

		int pending = 0;
		for (CompletableFuture<?> future: futures) {
			if (!future.isDone()) {
				++pending;
			}
		}
		
		if (pending > 0) {
			new NasdanikaException("There are not completed futures: " + pending);
		}
			
		return rootCF.join();
	}
	
	private static <R,V> CompletableFuture<Element> from(
			ContentProvider<R,V> contentProvider,
			Map<R,CompletableFuture<Node>> sourceRefs,
			Map<R,CompletableFuture<Node>> targetRefs,
			BiConsumer<ContentProvider<R,V>, Element> collector,
			Consumer<CompletableFuture<?>> futures) {
				
		ContentProvider<R,V> contentProviderFilter = new ContentProviderFilter<R,V>(contentProvider) {
			
			@Override
			public boolean isSource() {
				return super.isSource() || sourceRefs
					.keySet()
					.stream()
					.anyMatch(this::matchReference);				
			}
			
			@Override
			public boolean isTarget() {
				return super.isTarget() || targetRefs
						.keySet()
						.stream()
						.anyMatch(this::matchReference);				
			}
			
		};
		
		CompletableFuture<Element> result = create(
				contentProviderFilter,
				sourceRefs,
				targetRefs,
				collector,
				futures);
		
		futures.accept(result.thenAccept(r -> wire(
				sourceRefs, 
				targetRefs, 
				contentProviderFilter, 
				contentProviderFilter, 
				collector, 
				r)));
		
		return result;
	}

	private static <R, V> void wire(
			Map<R, CompletableFuture<Node>> sourceRefs, 
			Map<R, CompletableFuture<Node>> targetRefs,
			ContentProvider<R,V> contentProvider,
			ContentProvider<R, V> contentProviderFilter, 
			BiConsumer<ContentProvider<R,V>, Element> collector,
			Element result) {
		
		collector.accept(contentProviderFilter, result);		
		if (result instanceof Node) {
			for (Entry<R, CompletableFuture<Node>> e: sourceRefs.entrySet()) {
				if (contentProviderFilter.matchReference(e.getKey())) {
					if (!e.getValue().complete((Node) result)) {
						throw new IllegalArgumentException("Duplicate refId: " + e.getKey());
					}
				}
			}
			for (Entry<R, CompletableFuture<Node>> e: targetRefs.entrySet()) {
				if (contentProviderFilter.matchReference(e.getKey())) {
					if (!e.getValue().complete((Node) result)) {
						throw new IllegalArgumentException("Duplicate refId: " + e.getKey());
					}
				}
			}
		}
	}
	
	private static Connection createConnection(Node source, Node target, Object value, Supplier<Collection<? extends Element>> childrenSupplier) {
		if (value == null) {
			return new SimpleConnection(source, target, false, childrenSupplier);
		}
		
		return new ObjectConnection<>(source, target, false, value, childrenSupplier);							
	}
	
	private static <R,V> CompletableFuture<Element> create(
			ContentProvider<R,V> contentProvider,
			Map<R,CompletableFuture<Node>> sourceRefs,
			Map<R,CompletableFuture<Node>> targetRefs,
			BiConsumer<ContentProvider<R,V>, Element> collector,
			Consumer<CompletableFuture<?>> futures) {
		
		V value = contentProvider.getValue();
		Collection<CompletableFuture<Element>> childrenFutures = contentProvider
				.getChildren()
				.stream()
				.map(ccp -> from(ccp, sourceRefs, targetRefs, collector, futures))
				.toList();
		
		Supplier<Collection<? extends Element>> childrenSupplier = () -> {
			return childrenFutures
				.stream()
				.map(CompletableFuture::join)
				.toList();			
		};
		
		// Node
		Collection<ContentProvider<R, V>> outgoingConnectionProviders = contentProvider.getOutgoingConnections();
		Collection<ContentProvider<R, V>> incomingConnectionProviders = contentProvider.getOutgoingConnections();
		if (outgoingConnectionProviders != null && !outgoingConnectionProviders.isEmpty()
				|| incomingConnectionProviders != null && !incomingConnectionProviders.isEmpty()
				|| contentProvider.isSource()
				|| contentProvider.isTarget()) {

			Node node = value == null ? new SimpleNode(childrenSupplier) : new ObjectNode<>(value, childrenSupplier);

			for (ContentProvider<R, V> ocp: outgoingConnectionProviders) {
				ContentProvider<R, V> targetConnectionProvider = ocp.getTarget();
				if (targetConnectionProvider != null) {
					CompletableFuture<Element> tncf = from(targetConnectionProvider, sourceRefs, targetRefs, collector, futures);
					futures.accept(tncf.thenAccept(t -> {
						Node targetNode = t instanceof Node ? (Node) t : new ObjectNode<>(t);
						Connection connection = createConnection(node, targetNode, value, childrenSupplier);
						collector.accept(ocp, connection);
					}));
				} else {
					R targetRef = ocp.getTargetReference();
					if (targetRef == null) {
						Connection connection = createConnection(node, new DanglingNode(), value, childrenSupplier);							
						collector.accept(ocp, connection);
					} else {
						futures.accept(targetRefs.get(targetRef).thenAccept(t -> {
							Connection connection = createConnection(node, t, value, childrenSupplier); 
							collector.accept(ocp, connection);
						}));
					}
				}
			}

			for (ContentProvider<R, V> icp: incomingConnectionProviders) {
				ContentProvider<R, V> sourceConnectionProvider = icp.getTarget();
				if (sourceConnectionProvider != null) {
					CompletableFuture<Element> sncf = from(sourceConnectionProvider, sourceRefs, targetRefs, collector, futures);
					futures.accept(sncf.thenAccept(s -> {
						Node sourceNode = s instanceof Node ? (Node) s : new ObjectNode<>(s);
						Connection connection = createConnection(sourceNode, node, value, childrenSupplier);
						collector.accept(icp, connection);
					}));
				} else {
					R sourceRef = icp.getTargetReference();
					if (sourceRef == null) {
						Connection connection = createConnection(new DanglingNode(), node, value, childrenSupplier);							
						collector.accept(icp, connection);
					} else {
						futures.accept(targetRefs.get(sourceRef).thenAccept(s -> {
							Connection connection = createConnection(s, node, value, childrenSupplier);
							collector.accept(icp, connection);
						}));
					}
				}
			}
			
			return CompletableFuture.completedFuture(node);			
		}
		
		// Connection
		R sourceRef = contentProvider.getSourceReference();
		ContentProvider<R, V> sourceContentProvider = contentProvider.getSource();
		
		R targetRef = contentProvider.getTargetReference();
		ContentProvider<R, V> targetContentProvider = contentProvider.getTarget();
		
		if (sourceRef != null 
				|| sourceContentProvider != null
				|| targetRef != null
				|| targetContentProvider != null) {
			
			CompletableFuture<?> scf;
			if (sourceRef != null) {
				scf = sourceRefs.get(sourceRef);
			} else if (sourceContentProvider != null) {
				ContentProvider<R, V> sourceContentProviderFilter = new ContentProviderFilter<>(sourceContentProvider) {
					
					public boolean isSource() {
						return true;
					};
					
				};
				scf = from(sourceContentProviderFilter, sourceRefs, targetRefs, collector, futures);
			} else {
				scf = CompletableFuture.completedFuture(new DanglingNode());						
			}
						
			CompletableFuture<?> tcf;
			if (targetRef != null) {
				tcf = targetRefs.get(targetRef);
			} else if (targetContentProvider != null) {
				ContentProvider<R, V> targetContentProviderFilter = new ContentProviderFilter<>(targetContentProvider) {
					
					public boolean isTarget() {
						return true;
					};
					
				};
				tcf = from(targetContentProviderFilter, sourceRefs, targetRefs, collector, futures);
			} else {
				tcf = CompletableFuture.completedFuture(new DanglingNode());						
			}
			
			return scf.thenCombine(tcf, (s,t) -> {
				Node sNode = s instanceof Node ? (Node) s : new ObjectNode<>(s);
				Node tNode = t instanceof Node ? (Node) t : new ObjectNode<>(t);				
				Connection connection = createConnection(sNode, tNode, value, childrenSupplier);
				return connection;
			});
		}		
		
		// Element		
		if (value == null) {
			Element ret = new Element() {
				
				@Override
				public Collection<? extends Element> getChildren() {
					return childrenSupplier.get(); 
				}
				
			};
			return CompletableFuture.completedFuture(ret);
		}
		
		ObjectElement<V> objectElement = new ObjectElement<V>(value, childrenSupplier);
		return CompletableFuture.completedFuture(objectElement);		
	}
	
}
