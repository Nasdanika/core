package org.nasdanika.graph.processor;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reflector;
import org.nasdanika.common.Util;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.RegistryEntry.Type;

/**
 * Base class with methods to wire registry and registry entries using annotations.
 *
 * @param <P>
 */
public class ReflectiveRegistryWirer<H,E,K,P> extends Reflector {
		
	/**
	 * Records don't work with non-static types.
	 */
	protected class RegistryMatch {
		RegistryEntry annotation; 
		AnnotatedElementRecord setterRecord;
		ProcessorConfig<H,E,K> config;
		
		public RegistryMatch(
				RegistryEntry annotation, 
				AnnotatedElementRecord setterRecord, 
				ProcessorConfig<H,E,K> config) {
			super();
			this.annotation = annotation;
			this.setterRecord = setterRecord;
			this.config = config;
		}		
		
	}
		
	@SuppressWarnings("unchecked")
	protected void wireRegistryEntry(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Collection<ProcessorConfig<H,E,K>> registry,
			Map<String,Object> variables,
			BiConsumer<Element,BiConsumer<ProcessorInfo<H,E,K,P>,ProgressMonitor>> infoProvider) {
		
		processorAnnotatedElementRecords
			.filter(ae -> ae.getAnnotation(RegistryEntry.class) != null)
			.flatMap(setterRecord -> registry.stream().filter(Objects::nonNull).map(re -> new RegistryMatch(setterRecord.getAnnotation(RegistryEntry.class), setterRecord, re)))
			.filter(rm -> evaluatePredicate(rm.config.getElement(), rm.annotation.value(), variables))
			.forEach(rm -> {
				infoProvider.accept(rm.config.getElement(), (rpInfo, pMonitor) -> {
					AnnotatedElementRecord reRecord = rm.setterRecord;															
					switch (rm.annotation.type()) {
					case ENDPOINT:
					case HANDLER:
					case SYNAPSE: {
						String clientKeyExpr = rm.annotation.clientKey();
						Object clientKey = Util.isBlank(clientKeyExpr) ? rm.config.getElement() : evaluate(rm.config.getElement(), clientKeyExpr, variables, Object.class);
						Synapse<H,E> synapse = rpInfo.getClientSynapse((K) clientKey);
						if (synapse != null) {						
							if (rm.annotation.type() == Type.SYNAPSE) {
								if (reRecord.canSet(synapse.getClass())) {
									reRecord.set(synapse);																
								} else {
									reRecord.invoke(clientKey, synapse);
								}
							} else if (rm.annotation.type() == Type.ENDPOINT) {
								CompletionStage<E> endpoint = synapse.getEndpoint();
								if (reRecord.canSet(endpoint.getClass())) {
									reRecord.set(endpoint);																
								} else {
									reRecord.invoke(clientKey, endpoint);
								}
							} else {
								RegistryEntry rea = reRecord.getAnnotation(RegistryEntry.class);
								if (rea.proxy().length == 0) {
									if (reRecord.canGet(null)) {
										synapse.setHandler((H) reRecord.get());
									} else {
										synapse.setHandler((H) reRecord.invoke(clientKey));
									}
								} else {
									Object proxy = reRecord.createFunctionalProxy(rea.proxy());
									synapse.setHandler((H) proxy);
								}
							}
						}						
						break;
					}
					case INFO:
						break;
					case PROCESSOR:
						P processor = rpInfo.getProcessor();
						if (reRecord.canSet(Synapse.class) && reRecord.getSetterType() == Synapse.class) {							
							String clientKeyExpr = rm.annotation.clientKey();
							Object clientKey = Util.isBlank(clientKeyExpr) ? rm.config.getElement() : evaluate(rm.config.getElement(), clientKeyExpr, variables, Object.class);
							Synapse<H,E> synapse = rpInfo.getClientSynapse((K) clientKey);
							reRecord.set(synapse);
						} else if (reRecord.canSet(rpInfo.getClass()) && ProcessorInfo.class.isAssignableFrom(reRecord.getSetterType())) {							
							reRecord.set(rpInfo);											
						} else if (processor != null && reRecord.canSet(processor.getClass())) {
							reRecord.set(processor);						
						}						
						break;
					default:
						throw new IllegalArgumentException("Unsupported type: " + rm.annotation.type());					
					}
					
				});
			});
	}

	protected void wireRegistry(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Collection<ProcessorConfig<H,E,K>> registry, 
			BiConsumer<Element,BiConsumer<ProcessorInfo<H,E,K,P>,ProgressMonitor>> infoProvider) {

		processorAnnotatedElementRecords
				.filter(aer -> aer.getAnnotation(Registry.class) != null)
				.filter(aer -> aer.mustSet(Map.class, "Fields/methods annotated with Registry must have (parameter) type assignable from Map: " + aer.getAnnotatedElement()))
				.forEach(setterRecord -> {
					// Sets a map which is populated as processors get created
					Map<Element,ProcessorInfo<H,E,K,P>> r = Collections.synchronizedMap(new LinkedHashMap<>());
					setterRecord.set(r);
					for (ProcessorConfig<H,E,K> re: registry) {
						if (re != null) {
							infoProvider.accept(re.getElement(), (rp, pm) -> r.put(re.getElement(), rp));
						}
					}
				});
	}
		
}
