package org.nasdanika.graph.processor;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.nasdanika.common.Invocable;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wires processor using annotations.
 *
 * @param <P>
 * @param <T>
 * @param <R>
 * @param <U>
 * @param <S>
 */
public class ReflectiveProcessorWirer<H,E,P> extends ReflectiveRegistryWirer<H,E,P> {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ReflectiveProcessorWirer.class);
	
	private static <K,H,E> Map<K,CompletionStage<E>> endpointsMap(Map<K,Synapse<H,E>> synapses) {
		return synapses
				.entrySet()
				.stream()
				.collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getEndpoint()));
	}
	
	private static <K,H,E> Map<K,Consumer<H>> handlerConsumersMap(Map<K,Synapse<H,E>> synapses) {
		return synapses
				.entrySet()
				.stream()
				.collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue()::setHandler));
	}
	
	public void wireProcessor(
			ProcessorConfig<H,E> config,
			P processor,
			boolean hideWired,
			boolean parallel, 
			BiConsumer<Element,BiConsumer<ProcessorInfo<H,E,P>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			ProgressMonitor progressMonitor) {
		
		List<AnnotatedElementRecord> processorAnnotatedElementRecords = getAnnotatedElementRecords(processor, Collections.emptyList()).toList();
		Supplier<Stream<AnnotatedElementRecord>> processorAnnotatedElementRecordsStreamSupplier = () -> parallel ? processorAnnotatedElementRecords.parallelStream() : processorAnnotatedElementRecords.stream();
		
		wireProcessorElement(processorAnnotatedElementRecordsStreamSupplier.get(), config.getElement());		
		
		Map<Element, ProcessorConfig<H,E>> childProcessorConfigsCopy = new LinkedHashMap<>(config.getChildProcessorConfigs());
		wireChildProcessor(
				processorAnnotatedElementRecordsStreamSupplier.get(), 
				config.getChildProcessorConfigs(), 
				infoProvider,
				childProcessorConfigsCopy::remove);
		
		wireChildProcessors(
				processorAnnotatedElementRecordsStreamSupplier.get(), 
				childProcessorConfigsCopy , 
				infoProvider);
		
		ProcessorConfig<H,E> parentConfig = config.getParentProcessorConfig();
		if (parentConfig != null) {			
			Consumer<Consumer<ProcessorInfo<H,E,P>>> parentProcessorInfoConsumerCallback = parentProcessorInfoConsumer -> {
				infoProvider.accept(parentConfig.getElement(), (pInfo, pMonitor) -> parentProcessorInfoConsumer.accept(pInfo));
			};
			wireParentProcessor(processorAnnotatedElementRecordsStreamSupplier.get(), parentProcessorInfoConsumerCallback);
		}
		
		wireRegistryEntry(
				processorAnnotatedElementRecordsStreamSupplier.get(), 
				config.getRegistry().values(), 
				Map.of(
					"config", config,
					"element", config.getElement(),
					"processor", processor
				),		
				infoProvider);
		
		wireRegistry(
				processorAnnotatedElementRecordsStreamSupplier.get(), 
				config.getRegistry().values(), 
				infoProvider);
		
		Synapse<H, E> parentSynapse = config.getParentSynapse();
		if (parentSynapse != null) {
			Consumer<E> parentEndpointConsumer = wireParentEndpoint(processorAnnotatedElementRecordsStreamSupplier.get());
			if (parentEndpointConsumer != null) {
				endpointWiringStageConsumer.accept(parentSynapse.getEndpoint().thenAccept(parentEndpointConsumer));
			}			
			wireParentHandler(processorAnnotatedElementRecordsStreamSupplier.get(), parentSynapse);
		}
				
		Map<Element, CompletionStage<E>> unwiredChildEndpoints = new LinkedHashMap<>(endpointsMap(config.getChildSynapses())); // Making a copy to remove wired on completion 
		wireChildEndpoint(
				processorAnnotatedElementRecordsStreamSupplier.get(), 
				endpointsMap(config.getChildSynapses()), 
				progressMonitor)
		.forEach(r -> {
			if (r.consume()) {
				unwiredChildEndpoints.remove(r.element());
			}
			endpointWiringStageConsumer.accept(r.result());
		});
		
		wireChildEndpoints(processorAnnotatedElementRecordsStreamSupplier.get(), unwiredChildEndpoints);
		
		Map<Element, Consumer<H>> childHandlerConsumers = handlerConsumersMap(config.getChildSynapses());
		Collection<Object> wiredHandlerChildren = wireChildHandler(processorAnnotatedElementRecordsStreamSupplier.get(), childHandlerConsumers);
		Map<Element, Consumer<H>> unwiredChildHandlerConsumers;
		if (hideWired) {
			unwiredChildHandlerConsumers = new LinkedHashMap<>(childHandlerConsumers);
			unwiredChildHandlerConsumers.keySet().removeAll(wiredHandlerChildren);
		} else {
			unwiredChildHandlerConsumers = childHandlerConsumers;
		}
		wireChildHandlerConsumers(processorAnnotatedElementRecordsStreamSupplier.get(), unwiredChildHandlerConsumers);
		
		config.setProcessorSynapseConsumer((clientKey, synapse) -> {			
			wireClientEndpoint(
					processorAnnotatedElementRecordsStreamSupplier.get(), 
					clientKey,
					synapse.getEndpoint(), 
					progressMonitor)
			.forEach(r -> r.result().whenComplete((rs, e) -> {
				if (e != null) {
					LOGGER.error("Exception in client endpoint wiring for client key '" + clientKey + "': " + e, e);
				}
			}));
			
			wireClientHandler(processorAnnotatedElementRecordsStreamSupplier.get(), clientKey, synapse::setHandler);
		});
		
		if (config instanceof NodeProcessorConfig) {
			NodeProcessorConfig<H, E> nodeProcessorConfig = (NodeProcessorConfig<H, E>) config;
			Map<Connection, CompletionStage<E>> unwiredIncomingEndpoints = new LinkedHashMap<>(endpointsMap(nodeProcessorConfig.getIncomingSynapses())); // Making a copy to remove wired on completion 
			wireIncomingEndpoint(
					processorAnnotatedElementRecordsStreamSupplier.get(), 
					endpointsMap(nodeProcessorConfig.getIncomingSynapses()), 
					progressMonitor)
			.forEach(r -> {
				if (r.consume()) {
					unwiredIncomingEndpoints.remove(r.element());
				}
				endpointWiringStageConsumer.accept(r.result());
			});
			
			wireIncomingEndpoints(processorAnnotatedElementRecordsStreamSupplier.get(), unwiredIncomingEndpoints);
			
			Map<Connection, Consumer<H>> incomingHandlerConsumers = handlerConsumersMap(nodeProcessorConfig.getIncomingSynapses());
			Collection<Object> wiredHandlerIncomingConnections = wireIncomingHandler(processorAnnotatedElementRecordsStreamSupplier.get(), incomingHandlerConsumers);
			Map<Connection, Consumer<H>> unwiredIncomingHandlerConsumers;
			if (hideWired) {
				unwiredIncomingHandlerConsumers = new LinkedHashMap<>(incomingHandlerConsumers);
				unwiredIncomingHandlerConsumers.keySet().removeAll(wiredHandlerIncomingConnections);
			} else {
				unwiredIncomingHandlerConsumers = incomingHandlerConsumers;
			}
			wireIncomingHandlerConsumers(processorAnnotatedElementRecordsStreamSupplier.get(), unwiredIncomingHandlerConsumers);
			
			Map<Connection, CompletionStage<E>> outgoingEndpoints = new LinkedHashMap<>(endpointsMap(nodeProcessorConfig.getOutgoingSynapses())); // Making a copy to removed wired on completion
			wireOutgoingEndpoint(
					processorAnnotatedElementRecordsStreamSupplier.get(), 
					outgoingEndpoints, 
					progressMonitor)
			.forEach(r -> {
				if (r.consume()) {
					outgoingEndpoints.remove(r.element());
				}
				endpointWiringStageConsumer.accept(r.result());
			});
			wireOutgoingEndpoints(processorAnnotatedElementRecordsStreamSupplier.get(), outgoingEndpoints);
			
			Map<Connection, Consumer<H>> outgoingHandlerConsumers = handlerConsumersMap(nodeProcessorConfig.getOutgoingSynapses()); 
			Collection<Object> wiredHandlerOutgoingConnections = wireOutgoingHandler(processorAnnotatedElementRecordsStreamSupplier.get(), outgoingHandlerConsumers);
			Map<Connection, Consumer<H>> unwiredOutgoingHandlerConsumers;
			if (hideWired) {
				unwiredOutgoingHandlerConsumers = new LinkedHashMap<>(outgoingHandlerConsumers);
				unwiredOutgoingHandlerConsumers.keySet().removeAll(wiredHandlerOutgoingConnections);
			} else {
				unwiredOutgoingHandlerConsumers = outgoingHandlerConsumers;
			}			
			wireOutgoingHandlerConsumers(processorAnnotatedElementRecordsStreamSupplier.get(), unwiredOutgoingHandlerConsumers);
		} else if (config instanceof ConnectionProcessorConfig) {
			ConnectionProcessorConfig<H, E> connectionProcessorConfig = (ConnectionProcessorConfig<H, E>) config;
			Consumer<E> sourceEndpointConsumer = wireSourceEndpoint(processorAnnotatedElementRecordsStreamSupplier.get());
			if (sourceEndpointConsumer != null) {
				endpointWiringStageConsumer.accept(connectionProcessorConfig.getSourceSynapse().getEndpoint().thenAccept(sourceEndpointConsumer));
			}			
			wireSourceHandler(processorAnnotatedElementRecordsStreamSupplier.get(), connectionProcessorConfig);
			
			Consumer<E> targetEndpointConsumer = wireTargetEndpoint(processorAnnotatedElementRecordsStreamSupplier.get());
			if (targetEndpointConsumer != null) {			
				endpointWiringStageConsumer.accept(connectionProcessorConfig.getTargetSynapse().getEndpoint().thenAccept(targetEndpointConsumer));
			}
			wireTargetHandler(processorAnnotatedElementRecordsStreamSupplier.get(), connectionProcessorConfig);
		}
	}

	protected void wireProcessorElement(Stream<AnnotatedElementRecord> processorAnnotatedElementRecords, Element element) {
		processorAnnotatedElementRecords
				.filter(aer -> aer.getAnnotation(ProcessorElement.class) != null)
				.filter(aer -> aer.mustSet(element.getClass(), "Methods annotated with ProcessorElement must have one parameter compatible with the processor element type (" + element.getClass() + "): " + aer.getAnnotatedElement()))
				.forEach(aer -> aer.set(element));						
	}

	protected void wireParentProcessor(			
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Consumer<Consumer<ProcessorInfo<H,E,P>>> parentProcessorInfoProvider) {

		processorAnnotatedElementRecords
				.filter(aer -> aer.getAnnotation(ParentProcessor.class) != null)
				.filter(aer -> aer.mustSet(null, "Fields/methods annotated with ParentProcessor must have (parameter) type assignable from the processor type or ProcessorInfo if value is set to true: " + aer.getAnnotatedElement()))
				.forEach(aer -> parentProcessorInfoProvider.accept(parentProcessorInfo -> aer.set(aer.getAnnotation(ParentProcessor.class).value() ? parentProcessorInfo : parentProcessorInfo.getProcessor())));
	}
	
	/**
	 * 
	 * @param processorAnnotatedElementRecords
	 * @param childProcessorConfigs
	 * @param infoProvider
	 * @param wiredChildrenConsumer accepts wired children
	 */
	private void wireChildProcessor(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Map<Element, ProcessorConfig<H,E>> childProcessorConfigs, 
			BiConsumer<Element,BiConsumer<ProcessorInfo<H,E,P>,ProgressMonitor>> infoProvider,
			Consumer<Element> wiredChildrenConsumer) {		

		processorAnnotatedElementRecords
			.filter(aer -> aer.getAnnotation(ChildProcessor.class) != null)
			.filter(aer -> aer.mustSet(null, "Fields/methods annotated with ChildProcessor must have (parameter) type assignable from the processor type or ProcessorConfig if info is set to true: " + aer.getAnnotatedElement()))
			.forEach(aer -> {
				for (Entry<Element, ProcessorConfig<H,E>> ce: childProcessorConfigs.entrySet()) {
					ChildProcessor childProcessorAnnotation = aer.getAnnotation(ChildProcessor.class);
					if (matchPredicate(ce.getKey(), childProcessorAnnotation.value())) {
						infoProvider.accept(ce.getKey(), (childProcessorInfo, pMonitor) -> {
							aer.set(childProcessorAnnotation.info() ? childProcessorInfo : childProcessorInfo.getProcessor());
							wiredChildrenConsumer.accept(ce.getKey());
						});
					};						
				}		
			});
	}	

	protected void wireChildProcessors(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Map<Element, ProcessorConfig<H,E>> childProcessorConfigs,
			BiConsumer<Element,BiConsumer<ProcessorInfo<H,E,P>,ProgressMonitor>> infoProvider) {		
		
		processorAnnotatedElementRecords
				.filter(aer -> aer.getAnnotation(ChildProcessors.class) != null)
				.filter(aer -> aer.mustSet(Map.class, "Fields/methods annotated with ChildProcessors must have (parameter) type assignable from Map: " + aer.getAnnotatedElement()))
				.forEach(aer -> {
					// Sets a map which is populated as processors get created
					Map<Element,ProcessorInfo<H,E,P>> childProcessorInfoMap = Collections.synchronizedMap(new LinkedHashMap<>());
					aer.set(childProcessorInfoMap);
					for (ProcessorConfig<H,E> childConfig: childProcessorConfigs.values()) {
						infoProvider.accept(childConfig.getElement(), (childInfo, cpm) -> childProcessorInfoMap.put(childConfig.getElement(), childInfo));
					}
				});
	}
	
	/**
	 * Matches processor field or method and incoming connection.
	 * @return
	 */
	protected boolean matchIncomingHandler(AnnotatedElement handlerMember, Object incomingConnection) {
		IncomingHandler incomingHandlerAnnotation = handlerMember.getAnnotation(IncomingHandler.class);
		if (incomingHandlerAnnotation == null) {
			return false;
		}
		
		if (handlerMember instanceof Method && incomingHandlerAnnotation.wrap() == HandlerWrapper.NONE && incomingHandlerAnnotation.proxy().length == 0) {
			Method handlerMethod = (Method) handlerMember;
			int pc = handlerMethod.getParameterCount();
			if (pc > 1) {
				throw new NasdanikaException("A method annotated with IncomingHandler shall have zero or one parameter: " + handlerMethod);
			}
			if (pc == 1 && !handlerMethod.getParameterTypes()[0].isInstance(incomingConnection)) {
				return false;				
			}
		}
				
		return matchPredicate(incomingConnection, incomingHandlerAnnotation.value());
	}
	
	// Node wiring
	
	/**
	 * 
	 * @param processor
	 * @param incomingHandlerConsumers
	 * @param parallel
	 * @return Wired connections
	 */
	@SuppressWarnings("unchecked")
	protected Collection<Object> wireIncomingHandler(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Map<Connection, Consumer<H>> incomingHandlerConsumers) {		

		Set<Object> wired = Collections.synchronizedSet(new HashSet<>());

		// Streaming fields and methods and then flat mapping them to all permutations with incoming handler consumers.
		// then filtering using matchIncomingHandler, sorting by priority, for all matching - wiring and removing from ret.
		processorAnnotatedElementRecords
			.filter(aer -> aer.getAnnotatedElement().isAnnotationPresent(IncomingHandler.class) && !Modifier.isAbstract(((Member) aer.getAnnotatedElement()).getModifiers()))
			.flatMap(aer -> incomingHandlerConsumers.entrySet().stream().map(ihce -> new Match<Consumer<H>>(
					aer, 
					ihce.getKey(), 
					ihce.getValue(), 
					ao -> ao.getAnnotation(IncomingHandler.class).priority(), 
					ao -> ao.getAnnotation(IncomingHandler.class).value())))
			.filter(mr -> matchIncomingHandler(mr.annotatedElementRecord.getAnnotatedElement(), mr.key))
			.sorted()
			.forEach(mr -> {
				AnnotatedElement handlerMember = mr.annotatedElementRecord.getAnnotatedElement();
				Object incomingConnection = mr.key;
				if (wired.add(incomingConnection)) { // Wiring once
					IncomingHandler incomingHandlerAnnotation = mr.annotatedElementRecord.getAnnotation(IncomingHandler.class);
					switch (incomingHandlerAnnotation.wrap()) {
					case ASYNC_INVOCABLE:
						mr.value.accept((H) asInvocable(mr.annotatedElementRecord, incomingHandlerAnnotation.parameterNames()).asAsync());						
						break;
					case INVOCABLE:
						mr.value.accept((H) asInvocable(mr.annotatedElementRecord, incomingHandlerAnnotation.parameterNames()));						
						break;
					case NONE:						
						if (handlerMember instanceof Field) {					
							mr.value.accept((H) mr.annotatedElementRecord.get());
						} else {
							if (incomingHandlerAnnotation.proxy().length == 0) {
								Method handlerSupplierMethod = (Method) handlerMember;
								Object incomingHandler = handlerSupplierMethod.getParameterCount() == 0 ? mr.annotatedElementRecord.get() : mr.annotatedElementRecord.invoke(incomingConnection);
								mr.value.accept((H) incomingHandler);
							} else {
								Object proxy = mr.annotatedElementRecord.createFunctionalProxy(incomingHandlerAnnotation.proxy());
								mr.value.accept((H) proxy);								
							}	
						}
						break;
					default:
						throw new UnsupportedOperationException(incomingHandlerAnnotation.wrap().name() + " wrap type is not supported (yet)");						
					}												
				}
			});
				
		return wired;
	}	
	
	protected void wireIncomingHandlerConsumers(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Map<Connection, Consumer<H>> incomingHandlerConsumers) {				
		
		processorAnnotatedElementRecords
				.filter(aer -> aer.getAnnotation(IncomingHandlerConsumers.class) != null)
				.filter(aer -> aer.mustSet(Map.class, "Fields/methods annotated with IncomingHandlersConsumers must have (parameter) type assignable from Map: " + aer.getAnnotatedElement()))
				.forEach(aer -> aer.set(incomingHandlerConsumers));
	}
	
	/**
	 * Matches processor field or method and incoming connection.
	 * @return
	 */
	protected boolean matchIncomingEndpoint(AnnotatedElement endpointMember, Object incomingConnection) {
		IncomingEndpoint incomingEndpointAnnotation = endpointMember.getAnnotation(IncomingEndpoint.class);
		if (incomingEndpointAnnotation == null) {
			return false;
		}
		
		if (endpointMember instanceof Method) {
			Method endpointMethod = (Method) endpointMember;
			int pc = endpointMethod.getParameterCount();
			if (pc == 0 || pc > 3) {
				throw new NasdanikaException("A method annotated with IncomingEndpoint shall have 1 - 3 parameters: " + endpointMethod);
			}
			if (pc > 1 && !endpointMethod.getParameterTypes()[0].isInstance(incomingConnection)) {
				return false;				
			}
		}
				
		return matchPredicate(incomingConnection, incomingEndpointAnnotation.value());
	}	
	
	protected class Match<T> implements Comparable<Match<T>> {
		
		AnnotatedElementRecord annotatedElementRecord; 
		Object key; 
		T value; 
		Function<AnnotatedElement, Integer> priorityGetter; 
		Function<AnnotatedElement, String> selectorGetter;
		
		Match(
				AnnotatedElementRecord annotatedElementRecord, 
				Object key, 
				T value,
				Function<AnnotatedElement, Integer> priorityGetter, 
				Function<AnnotatedElement, String> selectorGetter) {
			super();
			this.annotatedElementRecord = annotatedElementRecord;
			this.key = key;
			this.value = value;
			this.priorityGetter = priorityGetter;
			this.selectorGetter = selectorGetter;
		}

		@Override
		public int compareTo(Match<T> o) {
			AnnotatedElement a = annotatedElementRecord.getAnnotatedElement();
			AnnotatedElement b = o.annotatedElementRecord.getAnnotatedElement();
			
			if (priorityGetter != null) {
				Integer pa = priorityGetter.apply(a);
				Integer pb = priorityGetter.apply(b);
				if (!Objects.equals(pa, pb)) {
					return pb - pa;
				}
			}			
			if (a instanceof Member && b instanceof Member) {
				Class<?> adc = ((Member) a).getDeclaringClass();
				Class<?> bdc = ((Member) b).getDeclaringClass();
				if (adc.isAssignableFrom(bdc)) {
					return adc == bdc ? 0 : 1;
				} 
				
				if (bdc.isAssignableFrom(adc)) {
					return -1;
				}
			}
			if (selectorGetter != null) {
				String sa = selectorGetter.apply(a);
				String sb = selectorGetter.apply(b);
				if (sa.length() != sb.length()) {
					return sb.length() - sa.length();
				}
			}
			
			return a.hashCode() - b.hashCode();
		}
		
	}
	
	private record EndpointWireRecord(Object element, CompletionStage<Void> result, boolean consume) {};

	/**
	 * @param processor
	 * @param incomingEndpoints
	 * @param progressMonitor
	 * @return Wired incoming endpoints for collection of failures
	 */
	protected Stream<EndpointWireRecord> wireIncomingEndpoint(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Map<Connection, CompletionStage<E>> incomingEndpoints,
			ProgressMonitor progressMonitor) {
						
		Set<Field> wiredFields = Collections.synchronizedSet(new HashSet<>()); // For setting a field once, setter methods may be invoked multiple times.
		Set<Object> consumedConnections = Collections.synchronizedSet(new HashSet<>()); // For wiring a connection once.
				
		// Streaming fields and methods and then flat mapping them to all permutations with incoming endpoints.
		// then filtering using matchIncomingEndpoint, sorting by priority, wiring all matching and removing from ret.
		return processorAnnotatedElementRecords
			.filter(aer -> aer.getAnnotatedElement().isAnnotationPresent(IncomingEndpoint.class) && !Modifier.isAbstract(((Member) aer.getAnnotatedElement()).getModifiers()))
			.flatMap(aer -> incomingEndpoints.entrySet().stream().map(iee -> new Match<CompletionStage<E>>( 
					aer, 
					iee.getKey(), 
					iee.getValue(), 
					ao -> ao.getAnnotation(IncomingEndpoint.class).priority(), 
					ao -> ao.getAnnotation(IncomingEndpoint.class).value())))
			.filter(mr -> matchIncomingEndpoint(mr.annotatedElementRecord.getAnnotatedElement(), mr.key))
			.sorted()
			.map(mr -> {
				AnnotatedElement endpointMember = mr.annotatedElementRecord.getAnnotatedElement();
				Object incomingConnection = mr.key;
				IncomingEndpoint incomingEndpointAnnotation = endpointMember.getAnnotation(IncomingEndpoint.class);
				boolean consumed = incomingEndpointAnnotation.consume() && !consumedConnections.add(incomingConnection);
				boolean fieldWired = endpointMember instanceof Field && !wiredFields.add((Field) endpointMember);
				if (!consumed && !fieldWired) { // Wiring an endpoint once is consumed and setting a field once
					CompletionStage<Void> result = mr.value.thenAccept(incomingEndpoint -> {
						if (endpointMember instanceof Field) {
							mr.annotatedElementRecord.set(incomingEndpoint);
						} else {
							Method endpointMethod = (Method) endpointMember;
							switch (endpointMethod.getParameterCount()) {
							case 1:
								mr.annotatedElementRecord.invoke(incomingEndpoint);
								break;
							case 2:
								mr.annotatedElementRecord.invoke(incomingConnection, incomingEndpoint);
								break;
							case 3:
								mr.annotatedElementRecord.invoke(incomingConnection, incomingEndpoint, progressMonitor);
								break;								
							default:
								throw new NasdanikaException("Incoming endpoint method shall have 1 to 3 parameters: " + endpointMethod);
							}							
						}
					});
					return new EndpointWireRecord(incomingConnection, result, incomingEndpointAnnotation.consume());
				}
				return null;
			})
			.filter(Objects::nonNull);
	}
	
	protected void wireIncomingEndpoints(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Map<Connection, CompletionStage<E>> incomingEndpoints) {
		processorAnnotatedElementRecords
				.filter(aer -> aer.getAnnotation(IncomingEndpoints.class) != null)
				.filter(aer -> aer.mustSet(Map.class, "Fields/methods annotated with IncomingEndpoints must have (parameter) type assignable from Map: " + aer.getAnnotatedElement()))
				.forEach(aer -> aer.set(incomingEndpoints));
	}
	
	/**
	 * Matches processor field or method and outgoing connection.
	 * @return
	 */
	protected boolean matchOutgoingHandler(AnnotatedElement handlerMember, Object outgoingConnection) {
		OutgoingHandler outgoingHandlerAnnotation = handlerMember.getAnnotation(OutgoingHandler.class);
		if (outgoingHandlerAnnotation == null) {
			return false;
		}
		
		if (handlerMember instanceof Method && outgoingHandlerAnnotation.wrap() == HandlerWrapper.NONE && outgoingHandlerAnnotation.proxy().length == 0) {
			Method handlerMethod = (Method) handlerMember;
			int pc = handlerMethod.getParameterCount();
			if (pc > 1) {
				throw new NasdanikaException("A method annotated with OutgoingHandler shall have zero or one parameter: " + handlerMethod);
			}
			if (pc == 1 && !handlerMethod.getParameterTypes()[0].isInstance(outgoingConnection)) {
				return false;				
			}
		}
				
		return matchPredicate(outgoingConnection, outgoingHandlerAnnotation.value());
	}
	
	/**
	 * @param processor
	 * @param outgoingHandlerConsumers
	 * @param parallel
	 * @return Wired connections
	 */
	@SuppressWarnings("unchecked")
	protected Collection<Object> wireOutgoingHandler(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Map<Connection, Consumer<H>> outgoingHandlerConsumers) {
		
		Set<Object> wired = Collections.synchronizedSet(new HashSet<>());

		// Streaming fields and methods and then flat mapping them to all permutations with outgoing handler consumers.
		// then filtering using matchOutgoingHandler, sorting by priority, wiring all matching and removing from ret.
		processorAnnotatedElementRecords
			.filter(aer -> aer.getAnnotatedElement().isAnnotationPresent(OutgoingHandler.class) && !Modifier.isAbstract(((Member) aer.getAnnotatedElement()).getModifiers()))
			.flatMap(aer -> outgoingHandlerConsumers.entrySet().stream().map(ihce -> new Match<Consumer<H>>(
					aer, 
					ihce.getKey(), 
					ihce.getValue(), 
					ao -> ao.getAnnotation(OutgoingHandler.class).priority(), 
					ao -> ao.getAnnotation(OutgoingHandler.class).value())))
			.filter(mr -> matchOutgoingHandler(mr.annotatedElementRecord.getAnnotatedElement(), mr.key))
			.sorted()
			.forEach(mr -> {
				AnnotatedElement handlerMember = mr.annotatedElementRecord.getAnnotatedElement();
				Object outgoingConnection = mr.key;
				if (wired.add(outgoingConnection)) {
					OutgoingHandler outgoingHandlerAnnotation = mr.annotatedElementRecord.getAnnotation(OutgoingHandler.class);
					switch (outgoingHandlerAnnotation.wrap()) {
					case ASYNC_INVOCABLE:
						mr.value.accept((H) asInvocable(mr.annotatedElementRecord, outgoingHandlerAnnotation.parameterNames()).asAsync());						
						break;
					case INVOCABLE:
						mr.value.accept((H) asInvocable(mr.annotatedElementRecord, outgoingHandlerAnnotation.parameterNames()));						
						break;
					case NONE:
						if (handlerMember instanceof Field) {		
							mr.value.accept((H) mr.annotatedElementRecord.get());
						} else {
							if (outgoingHandlerAnnotation.proxy().length == 0) {
								Method handlerSupplierMethod = (Method) handlerMember;
								Object outgoinggHandler = handlerSupplierMethod.getParameterCount() == 0 ? mr.annotatedElementRecord.get() : mr.annotatedElementRecord.invoke(outgoingConnection);
								mr.value.accept((H) outgoinggHandler);
							} else {
								Object proxy = mr.annotatedElementRecord.createFunctionalProxy(outgoingHandlerAnnotation.proxy());
								mr.value.accept((H) proxy);								
							}	
						}
						break;
					default:
						throw new UnsupportedOperationException(outgoingHandlerAnnotation.wrap().name() + " wrap type is not supported (yet)");						
					}												
				}
			});
		return wired;
	}
	
	protected Invocable asInvocable(AnnotatedElementRecord aer, String[] parameterNames) {
		AnnotatedElement annotatedElement = aer.getAnnotatedElement();
		if (annotatedElement instanceof Field) {
			return Invocable.of(aer.getTarget(), (Field) annotatedElement);
		}
		
		return Invocable.of(aer.getTarget(), (Method) annotatedElement, parameterNames);
	}
	
	protected void wireOutgoingHandlerConsumers(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Map<Connection, Consumer<H>> outgoingHandlerConsumers) {
		
		processorAnnotatedElementRecords
				.filter(aer -> aer.getAnnotation(OutgoingHandlerConsumers.class) != null)
				.filter(aer -> aer.mustSet(Map.class, "Fields/methods annotated with OutgoingHandlersConsumers must have (parameter) type assignable from Map: " + aer.getAnnotatedElement()))
				.forEach(aer -> aer.set(outgoingHandlerConsumers));
	}
	
	/**
	 * Matches processor field or method and outgoing connection.
	 * @return
	 */
	protected boolean matchOutgoingEndpoint(AnnotatedElement endpointMember, Object outgoingConnection) {
		OutgoingEndpoint outgoingEndpointAnnotation = endpointMember.getAnnotation(OutgoingEndpoint.class);
		if (outgoingEndpointAnnotation == null) {
			return false;
		}
		
		if (endpointMember instanceof Method) {
			Method endpointMethod = (Method) endpointMember;
			int pc = endpointMethod.getParameterCount();
			if (pc == 0 || pc > 3) {
				throw new NasdanikaException("A method annotated with OutgoingEndpoint shall have 1 - 3 parameters: " + endpointMethod);
			}
			if (pc > 1 && !endpointMethod.getParameterTypes()[0].isInstance(outgoingConnection)) {
				return false;				
			}
		}
				
		return matchPredicate(outgoingConnection, outgoingEndpointAnnotation.value());
	}	
	
	protected Stream<EndpointWireRecord> wireOutgoingEndpoint(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Map<Connection, CompletionStage<E>> outgoingEndpoints,
			ProgressMonitor progressMonitor) {
		
		Set<Field> wiredFields = Collections.synchronizedSet(new HashSet<>()); // For setting a field once, setter methods may be invoked multiple times.
		Set<Object> consumedConnections = Collections.synchronizedSet(new HashSet<>()); // For wiring a connection once.

		// Streaming fields and methods and then flat mapping them to all permutations with outgoing endpoints.
		// then filtering using matchOutgoingEndpoint, sorting by priority, wiring all matching and removing from ret.
		return processorAnnotatedElementRecords
			.filter(aer -> aer.getAnnotatedElement().isAnnotationPresent(OutgoingEndpoint.class) && !Modifier.isAbstract(((Member) aer.getAnnotatedElement()).getModifiers()))
			.flatMap(aer -> outgoingEndpoints.entrySet().stream().map(iee -> new Match<CompletionStage<E>>(
					aer, 
					iee.getKey(), 
					iee.getValue(), 
					ao -> ao.getAnnotation(OutgoingEndpoint.class).priority(), 
					ao -> ao.getAnnotation(OutgoingEndpoint.class).value())))
			.filter(mr -> matchOutgoingEndpoint(mr.annotatedElementRecord.getAnnotatedElement(), mr.key))
			.sorted()
			.map(mr -> {				
				AnnotatedElement endpointMember = mr.annotatedElementRecord.getAnnotatedElement();
				Object outgoingConnection = mr.key;
				OutgoingEndpoint outgoingEndpointAnnotation = endpointMember.getAnnotation(OutgoingEndpoint.class);
				boolean consumed = outgoingEndpointAnnotation.consume() && !consumedConnections.add(outgoingConnection);
				boolean fieldWired = endpointMember instanceof Field && !wiredFields.add((Field) endpointMember);
				if (!consumed && !fieldWired) { // Setting a field once
					CompletionStage<Void> result = mr.value.thenAccept(outgoingEndpoint -> {
						if (endpointMember instanceof Field) {
							mr.annotatedElementRecord.set(outgoingEndpoint);
						} else {
							Method endpointMethod = (Method) endpointMember;
							switch (endpointMethod.getParameterCount()) {
							case 1:
								mr.annotatedElementRecord.invoke(outgoingEndpoint);
								break;
							case 2:
								mr.annotatedElementRecord.invoke(outgoingConnection, outgoingEndpoint);
								break;
							case 3:
								mr.annotatedElementRecord.invoke(outgoingConnection, outgoingEndpoint, progressMonitor);
								break;								
							default:
								throw new NasdanikaException("Outgoing endpoint method shall have 1 to 3 parameters: " + endpointMethod);
							}
						}					
					});
					return new EndpointWireRecord(outgoingConnection, result, outgoingEndpointAnnotation.consume());
				}
				return null;
			})
			.filter(Objects::nonNull);				
	}

	protected void wireOutgoingEndpoints(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Map<Connection, CompletionStage<E>> outgoingEndpoints) {
		processorAnnotatedElementRecords
				.filter(aer -> aer.getAnnotation(OutgoingEndpoints.class) != null)
				.filter(aer -> aer.mustSet(Map.class, "Fields/methods annotated with OutgoingEndpoints must have (parameter) type assignable from Map: " + aer.getAnnotatedElement()))
				.forEach(aer -> aer.set(outgoingEndpoints));
	}
	
	// Connection wiring
	@SuppressWarnings("unchecked")
	protected void wireTargetHandler(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			ConnectionProcessorConfig<H,E> connectionProcessorConfig) {
		Optional<AnnotatedElementRecord> getter = processorAnnotatedElementRecords
			.filter(aer -> aer.getAnnotation(TargetHandler.class) != null)
			.filter(aer -> aer.getAnnotation(TargetHandler.class).proxy().length > 0 || aer.mustGet(null, "Cannot use " + aer.getAnnotatedElement() + " to get target connection handler"))
			.findFirst();
		
		if (getter.isPresent()) {
			AnnotatedElementRecord aer = getter.get();
			TargetHandler tha = aer.getAnnotation(TargetHandler.class);
			Synapse<H, E> targetSynapse = connectionProcessorConfig.getTargetSynapse();
			if (tha.proxy().length == 0) {
				targetSynapse.setHandler((H) getter.get().get());
			} else {
				Object proxy = aer.createFunctionalProxy(tha.proxy());
				targetSynapse.setHandler((H) proxy);
			}
		}
	}

	protected Consumer<E> wireTargetEndpoint(Stream<AnnotatedElementRecord> processorAnnotatedElementRecords) {
		
		Optional<AnnotatedElementRecord> setter = processorAnnotatedElementRecords
			.filter(aer -> aer.getAnnotation(TargetEndpoint.class) != null)
			.filter(aer -> aer.mustSet(null, "Cannot use " + aer.getAnnotatedElement() + " to set target connection endpoint"))
			.findFirst();
		
		
		if (setter.isPresent()) {
			return targetEndpoint -> setter.get().set(targetEndpoint);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	protected void wireSourceHandler(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			ConnectionProcessorConfig<H,E> connectionProcessorConfig) {
		Optional<AnnotatedElementRecord> getter = processorAnnotatedElementRecords
			.filter(aer -> aer.getAnnotation(SourceHandler.class) != null)
			.filter(aer -> aer.getAnnotation(SourceHandler.class).proxy().length > 0 || aer.mustGet(null, "Cannot use " + aer.getAnnotatedElement() + " to get source connection handler"))
			.findFirst();
		
		if (getter.isPresent()) {
			Synapse<H, E> sourceSynapse = connectionProcessorConfig.getSourceSynapse();
			AnnotatedElementRecord aer = getter.get();
			SourceHandler sha = aer.getAnnotation(SourceHandler.class);
			if (sha.proxy().length == 0) {
				sourceSynapse.setHandler((H) getter.get().get());
			} else {
				Object proxy = aer.createFunctionalProxy(sha.proxy());
				sourceSynapse.setHandler((H) proxy);				
			}			 
		}
	}

	protected Consumer<E> wireSourceEndpoint(Stream<AnnotatedElementRecord> processorAnnotatedElementRecords) {
		
		Optional<AnnotatedElementRecord> setter = processorAnnotatedElementRecords
			.filter(aer -> aer.getAnnotation(SourceEndpoint.class) != null)
			.filter(aer -> aer.mustSet(null, "Cannot use " + aer.getAnnotatedElement() + " to set source connection endpoint"))
			.findFirst();
				
		if (setter.isPresent()) {			
			return sourceEndpoint -> setter.get().set(sourceEndpoint);
		}
		return null;
	}
	
	// Children
		
	/**
	 * Matches processor field or method and child
	 * @return
	 */
	protected boolean matchChildEndpoint(AnnotatedElement endpointMember, Object child) {
		ChildEndpoint childEndpointAnnotation = endpointMember.getAnnotation(ChildEndpoint.class);
		if (childEndpointAnnotation == null) {
			return false;
		}
		
		if (endpointMember instanceof Method) {
			Method endpointMethod = (Method) endpointMember;
			int pc = endpointMethod.getParameterCount();
			if (pc == 0 || pc > 3) {
				throw new NasdanikaException("A method annotated with ChildEndpoint shall have 1 - 3 parameters: " + endpointMethod);
			}
			if (pc > 1 && !endpointMethod.getParameterTypes()[0].isInstance(child)) {
				return false;				
			}
		}
				
		return matchPredicate(child, childEndpointAnnotation.value());
	}		
	
	/**
	 * @param processor
	 * @param incomingEndpoints
	 * @param progressMonitor
	 * @return Wired child endpoints for collection of failures
	 */
	protected Stream<EndpointWireRecord> wireChildEndpoint(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Map<Element, CompletionStage<E>> childEndpoints,
			ProgressMonitor progressMonitor) {
						
		Set<Field> wiredFields = Collections.synchronizedSet(new HashSet<>()); // For setting a field once, setter methods may be invoked multiple times.
		Set<Object> consumedChildren = Collections.synchronizedSet(new HashSet<>()); // For wiring a connection once.
				
		// Streaming fields and methods and then flat mapping them to all permutations with incoming endpoints.
		// then filtering using matchIncomingEndpoint, sorting by priority, wiring all matching and removing from ret.
		return processorAnnotatedElementRecords
			.filter(aer -> aer.getAnnotatedElement().isAnnotationPresent(ChildEndpoint.class) && !Modifier.isAbstract(((Member) aer.getAnnotatedElement()).getModifiers()))
			.flatMap(aer -> childEndpoints.entrySet().stream().map(cee -> new Match<CompletionStage<E>>( 
					aer, 
					cee.getKey(), 
					cee.getValue(), 
					ao -> ao.getAnnotation(ChildEndpoint.class).priority(), 
					ao -> ao.getAnnotation(ChildEndpoint.class).value())))
			.filter(mr -> matchChildEndpoint(mr.annotatedElementRecord.getAnnotatedElement(), mr.key))
			.sorted()
			.map(mr -> {
				AnnotatedElement endpointMember = mr.annotatedElementRecord.getAnnotatedElement();
				Object child = mr.key;
				ChildEndpoint childEndpointAnnotation = endpointMember.getAnnotation(ChildEndpoint.class);
				boolean consumed = childEndpointAnnotation.consume() && !consumedChildren.add(child);
				boolean fieldWired = endpointMember instanceof Field && !wiredFields.add((Field) endpointMember);
				if (!consumed && !fieldWired) { // Wiring an endpoint once is consumed and setting a field once
					CompletionStage<Void> result = mr.value.thenAccept(childEndpoint -> {
						if (endpointMember instanceof Field) {
							mr.annotatedElementRecord.set(childEndpoint);
						} else {
							Method endpointMethod = (Method) endpointMember;
							switch (endpointMethod.getParameterCount()) {
							case 1:
								mr.annotatedElementRecord.invoke(childEndpoint);
								break;
							case 2:
								mr.annotatedElementRecord.invoke(child, childEndpoint);
								break;
							case 3:
								mr.annotatedElementRecord.invoke(child, childEndpoint, progressMonitor);
								break;								
							default:
								throw new NasdanikaException("Incoming endpoint method shall have 1 to 3 parameters: " + endpointMethod);
							}							
						}
					});
					return new EndpointWireRecord(child, result, childEndpointAnnotation.consume());
				}
				return null;
			})
			.filter(Objects::nonNull);
	}
	
	protected void wireChildEndpoints(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Map<Element, CompletionStage<E>> childEndpoints) {
		processorAnnotatedElementRecords
				.filter(aer -> aer.getAnnotatedElement().isAnnotationPresent(ChildEndpoints.class))
				.filter(aer -> aer.mustSet(Map.class, "Fields/methods annotated with ChildEndpoints must have (parameter) type assignable from Map: " + aer.getAnnotatedElement()))
				.forEach(aer -> aer.set(childEndpoints));
	}
		
	/**
	 * 
	 * @param processor
	 * @param childHandlerConsumers
	 * @param parallel
	 * @return Wired element
	 */
	@SuppressWarnings("unchecked")
	protected Collection<Object> wireChildHandler(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Map<Element, Consumer<H>> childHandlerConsumers) {		

		Set<Object> wired = Collections.synchronizedSet(new HashSet<>());

		// Streaming fields and methods and then flat mapping them to all permutations with incoming handler consumers.
		// then filtering using matchIncomingHandler, sorting by priority, for all matching - wiring and removing from ret.
		processorAnnotatedElementRecords
			.filter(aer -> aer.getAnnotatedElement().isAnnotationPresent(ChildHandler.class) && !Modifier.isAbstract(((Member) aer.getAnnotatedElement()).getModifiers()))
			.flatMap(aer -> childHandlerConsumers.entrySet().stream().map(chce -> new Match<Consumer<H>>(
					aer, 
					chce.getKey(), 
					chce.getValue(), 
					ao -> ao.getAnnotation(ChildHandler.class).priority(), 
					ao -> ao.getAnnotation(ChildHandler.class).value())))
			.filter(mr -> matchChildHandler(mr.annotatedElementRecord.getAnnotatedElement(), mr.key))
			.sorted()
			.forEach(mr -> {
				AnnotatedElement handlerMember = mr.annotatedElementRecord.getAnnotatedElement();
				Object child = mr.key;
				if (wired.add(child)) { // Wiring once
					ChildHandler childHandlerAnnotation = mr.annotatedElementRecord.getAnnotation(ChildHandler.class);
					switch (childHandlerAnnotation.wrap()) {
					case ASYNC_INVOCABLE:
						mr.value.accept((H) asInvocable(mr.annotatedElementRecord, childHandlerAnnotation.parameterNames()).asAsync());						
						break;
					case INVOCABLE:
						mr.value.accept((H) asInvocable(mr.annotatedElementRecord, childHandlerAnnotation.parameterNames()));						
						break;
					case NONE:						
						if (handlerMember instanceof Field) {					
							mr.value.accept((H) mr.annotatedElementRecord.get());
						} else {
							if (childHandlerAnnotation.proxy().length == 0) {							
								Method handlerSupplierMethod = (Method) handlerMember;
								Object incomingHandler = handlerSupplierMethod.getParameterCount() == 0 ? mr.annotatedElementRecord.get() : mr.annotatedElementRecord.invoke(child);
								mr.value.accept((H) incomingHandler);
							} else {
								Object proxy = mr.annotatedElementRecord.createFunctionalProxy(childHandlerAnnotation.proxy());
								mr.value.accept((H) proxy);								
							}
						}
						break;
					default:
						throw new UnsupportedOperationException(childHandlerAnnotation.wrap().name() + " wrap type is not supported (yet)");						
					}												
				}
			});
				
		return wired;
	}	
	
	/**
	 * Matches processor field or method and outgoing connection.
	 * @return
	 */
	protected boolean matchChildHandler(AnnotatedElement handlerMember, Object child) {
		ChildHandler childHandlerAnnotation = handlerMember.getAnnotation(ChildHandler.class);
		if (childHandlerAnnotation == null) {
			return false;
		}
		
		if (handlerMember instanceof Method && childHandlerAnnotation.wrap() == HandlerWrapper.NONE && childHandlerAnnotation.proxy().length == 0) {
			Method handlerMethod = (Method) handlerMember;
			int pc = handlerMethod.getParameterCount();
			if (pc > 1) {
				throw new NasdanikaException("A method annotated with ChildHandler shall have zero or one parameter: " + handlerMethod);
			}
			if (pc == 1 && !handlerMethod.getParameterTypes()[0].isInstance(child)) {
				return false;				
			}
		}
				
		return matchPredicate(child, childHandlerAnnotation.value());
	}	
		
	protected void wireChildHandlerConsumers(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Map<Element, Consumer<H>> childHandlerConsumers) {				
		
		processorAnnotatedElementRecords
				.filter(aer -> aer.getAnnotation(ChildHandlerConsumers.class) != null)
				.filter(aer -> aer.mustSet(Map.class, "Fields/methods annotated with ChildHandlersConsumers must have (parameter) type assignable from Map: " + aer.getAnnotatedElement()))
				.forEach(aer -> aer.set(childHandlerConsumers));
	}	

	@SuppressWarnings("unchecked")
	protected void wireParentHandler(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Synapse<H,E> parentSynapse) {
		Optional<AnnotatedElementRecord> getter = processorAnnotatedElementRecords
			.filter(aer -> aer.getAnnotation(ParentHandler.class) != null)
			.filter(aer -> aer.getAnnotation(ParentHandler.class).proxy().length > 0 || aer.mustGet(null, "Cannot use " + aer.getAnnotatedElement() + " to get parent handler"))
			.findFirst();
		
		if (getter.isPresent()) {
			AnnotatedElementRecord aer = getter.get();
			ParentHandler pha = aer.getAnnotation(ParentHandler.class);
			if (pha.proxy().length == 0) {
				parentSynapse.setHandler((H) getter.get().get());
			} else {
				Object proxy = aer.createFunctionalProxy(pha.proxy());
				parentSynapse.setHandler((H) proxy);
			}
		}
	}

	protected Consumer<E> wireParentEndpoint(Stream<AnnotatedElementRecord> processorAnnotatedElementRecords) {		
		Optional<AnnotatedElementRecord> setter = processorAnnotatedElementRecords
			.filter(aer -> aer.getAnnotation(ParentEndpoint.class) != null)
			.filter(aer -> aer.mustSet(null, "Cannot use " + aer.getAnnotatedElement() + " to set parent endpoint"))
			.findFirst();
				
		if (setter.isPresent()) {			
			return sourceEndpoint -> setter.get().set(sourceEndpoint);
		}
		return null;
	}	
	
	// Clients 
		
	/**
	 * Matches processor field or method and client
	 * @return
	 */
	protected boolean matchClientEndpoint(AnnotatedElement endpointMember, Object clientKey) {
		ClientEndpoint clientEndpointAnnotation = endpointMember.getAnnotation(ClientEndpoint.class);
		if (clientEndpointAnnotation == null) {
			return false;
		}
		
		if (endpointMember instanceof Method) {
			Method endpointMethod = (Method) endpointMember;
			int pc = endpointMethod.getParameterCount();
			if (pc == 0 || pc > 3) {
				throw new NasdanikaException("A method annotated with ChildEndpoint shall have 1 - 3 parameters: " + endpointMethod);
			}
			if (pc > 1 && !endpointMethod.getParameterTypes()[0].isInstance(clientKey)) {
				return false;				
			}
		}
				
		return matchPredicate(clientKey, clientEndpointAnnotation.value());
	}		
	
	/**
	 * @param processor
	 * @param incomingEndpoints
	 * @param progressMonitor
	 * @return Wired client endpoints for collection of failures
	 */
	protected Stream<EndpointWireRecord> wireClientEndpoint(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Object clientKey,
			CompletionStage<E> clientEndpointCS,
			ProgressMonitor progressMonitor) {
						
		Set<Field> wiredFields = Collections.synchronizedSet(new HashSet<>()); // For setting a field once, setter methods may be invoked multiple times.
		Set<Object> consumedClients = Collections.synchronizedSet(new HashSet<>()); // For wiring a connection once.
				
		// Streaming fields and methods and then flat mapping them to all permutations with incoming endpoints.
		// then filtering using matchIncomingEndpoint, sorting by priority, wiring all matching and removing from ret.
		return processorAnnotatedElementRecords
			.filter(aer -> aer.getAnnotatedElement().isAnnotationPresent(ClientEndpoint.class) && !Modifier.isAbstract(((Member) aer.getAnnotatedElement()).getModifiers()))
			.map(aer -> new Match<CompletionStage<E>>( 
					aer, 
					clientKey, 
					clientEndpointCS, 
					ao -> ao.getAnnotation(ClientEndpoint.class).priority(), 
					ao -> ao.getAnnotation(ClientEndpoint.class).value()))
			.filter(mr -> matchClientEndpoint(mr.annotatedElementRecord.getAnnotatedElement(), mr.key))
			.sorted()
			.map(mr -> {
				AnnotatedElement endpointMember = mr.annotatedElementRecord.getAnnotatedElement();
				ClientEndpoint clientEndpointAnnotation = endpointMember.getAnnotation(ClientEndpoint.class);
				boolean consumed = clientEndpointAnnotation.consume() && !consumedClients.add(clientKey);
				boolean fieldWired = endpointMember instanceof Field && !wiredFields.add((Field) endpointMember);
				if (!consumed && !fieldWired) { // Wiring an endpoint once is consumed and setting a field once
					CompletionStage<Void> result = mr.value.thenAccept(clientEndpoint -> {
						if (endpointMember instanceof Field) {
							mr.annotatedElementRecord.set(clientEndpoint);
						} else {
							Method endpointMethod = (Method) endpointMember;
							switch (endpointMethod.getParameterCount()) {
							case 1:
								mr.annotatedElementRecord.invoke(clientEndpoint);
								break;
							case 2:
								mr.annotatedElementRecord.invoke(clientKey, clientEndpoint);
								break;
							case 3:
								mr.annotatedElementRecord.invoke(clientKey, clientEndpoint, progressMonitor);
								break;								
							default:
								throw new NasdanikaException("Incoming endpoint method shall have 1 to 3 parameters: " + endpointMethod);
							}							
						}
					});
					return new EndpointWireRecord(clientKey, result, clientEndpointAnnotation.consume());
				}
				return null;
			})
			.filter(Objects::nonNull);
	}
		
	/**
	 * 
	 * @param processor
	 * @param incomingHandlerConsumers
	 * @param parallel
	 * @return Wired connections
	 */
	@SuppressWarnings("unchecked")
	protected Collection<Object> wireClientHandler(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Object clientKey,
			Consumer<H> clientHandlerConsumer) {		

		Set<Object> wired = Collections.synchronizedSet(new HashSet<>());

		// Streaming fields and methods and then flat mapping them to all permutations with incoming handler consumers.
		// then filtering using matchIncomingHandler, sorting by priority, for all matching - wiring and removing from ret.
		processorAnnotatedElementRecords
			.filter(aer -> aer.getAnnotatedElement().isAnnotationPresent(ClientHandler.class) && !Modifier.isAbstract(((Member) aer.getAnnotatedElement()).getModifiers()))
			.map(aer -> new Match<Consumer<H>>(
					aer, 
					clientKey, 
					clientHandlerConsumer, 
					ao -> ao.getAnnotation(ClientHandler.class).priority(), 
					ao -> ao.getAnnotation(ClientHandler.class).value()))
			.filter(mr -> matchClientHandler(mr.annotatedElementRecord.getAnnotatedElement(), mr.key))
			.sorted()
			.forEach(mr -> {
				AnnotatedElement handlerMember = mr.annotatedElementRecord.getAnnotatedElement();
				if (wired.add(clientKey)) { // Wiring once
					ClientHandler clientHandlerAnnotation = mr.annotatedElementRecord.getAnnotation(ClientHandler.class);
					switch (clientHandlerAnnotation.wrap()) {
					case ASYNC_INVOCABLE:
						mr.value.accept((H) asInvocable(mr.annotatedElementRecord, clientHandlerAnnotation.parameterNames()).asAsync());						
						break;
					case INVOCABLE:
						mr.value.accept((H) asInvocable(mr.annotatedElementRecord, clientHandlerAnnotation.parameterNames()));						
						break;
					case NONE:						
						if (handlerMember instanceof Field) {					
							mr.value.accept((H) mr.annotatedElementRecord.get());
						} else {
							if (clientHandlerAnnotation.proxy().length == 0) {
								Method handlerSupplierMethod = (Method) handlerMember;
								Object incomingHandler = handlerSupplierMethod.getParameterCount() == 0 ? mr.annotatedElementRecord.get() : mr.annotatedElementRecord.invoke(clientKey);
								mr.value.accept((H) incomingHandler);
							} else {
								Object proxy = mr.annotatedElementRecord.createFunctionalProxy(clientHandlerAnnotation.proxy());
								mr.value.accept((H) proxy);								
							}
						}
						break;
					default:
						throw new UnsupportedOperationException(clientHandlerAnnotation.wrap().name() + " wrap type is not supported (yet)");						
					}												
				}
			});
				
		return wired;
	}	
		
	/**
	 * Matches processor field or method and outgoing connection.
	 * @return
	 */
	protected boolean matchClientHandler(AnnotatedElement handlerMember, Object clientKey) {
		ClientHandler clientHandlerAnnotation = handlerMember.getAnnotation(ClientHandler.class);
		if (clientHandlerAnnotation == null) {
			return false;
		}
		
		if (handlerMember instanceof Method && clientHandlerAnnotation.wrap() == HandlerWrapper.NONE && clientHandlerAnnotation.proxy().length == 0) {
			Method handlerMethod = (Method) handlerMember;
			int pc = handlerMethod.getParameterCount();
			if (pc > 1) {
				throw new NasdanikaException("A method annotated with ClientHandler shall have zero or one parameter: " + handlerMethod);
			}
			if (pc == 1 && !handlerMethod.getParameterTypes()[0].isInstance(clientKey)) {
				return false;				
			}
		}
				
		return matchPredicate(clientKey, clientHandlerAnnotation.value());
	}		
		
}
