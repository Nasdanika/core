package org.nasdanika.graph.processor;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;

/**
 * {@link Supplier} of a {@link ProcessorFactory} which reflecitvely creates  processors and wires hanlders and endpoints using annotations. 
 * @author Pavel
 *
 * @param <P> Processor type
 * @param <H> Handler type
 * @param <E> Endpoint type
 */
public class ReflectiveProcessorFactoryProvider<P, H, E> extends ReflectiveProcessorWirer<P, H, E> {
	
	private List<AnnotatedElementRecord> annotatedElementRecords = new ArrayList<>();
		
	public ReflectiveProcessorFactoryProvider(Object... targets) {
		for (Object target: targets) {
			getAnnotatedElementRecords(target, Collections.singletonList(target)).forEach(annotatedElementRecords::add);
		}
	}
	
	public ProcessorFactory<P> getFactory(Object... registryTargets) {
		return new ProcessorFactory<P>() {

			@Override
			protected ProcessorInfo<P> createProcessor(
					ProcessorConfig config, 
					boolean parallel,
					BiConsumer<Element,BiConsumer<ProcessorInfo<P>,ProgressMonitor>> infoProvider,
					Consumer<CompletionStage<?>> endpointWiringStageConsumer,
					ProgressMonitor progressMonitor) {
				
				P processor = ReflectiveProcessorFactoryProvider.this.createProcessor(config, parallel, infoProvider, endpointWiringStageConsumer, progressMonitor);
				return ProcessorInfo.of(config, processor);
			}
			
			@Override
			public Map<Element, ProcessorInfo<P>> createProcessors(Collection<ProcessorConfig> configs, boolean parallel, ProgressMonitor progressMonitor) {
				Map<Element, ProcessorInfo<P>> registry = super.createProcessors(configs, parallel, progressMonitor);
				for (Object registryTarget: registryTargets) {
					BiConsumer<Element, BiConsumer<ProcessorInfo<P>,ProgressMonitor>> infoProvider = (e, bc) -> {
						bc.accept(registry.get(e), progressMonitor);
					};
					
					List<AnnotatedElementRecord> registryTargetAnnotatedElementRecords = getAnnotatedElementRecords(registryTarget, Collections.singletonList(registryTarget)).toList();
					wireRegistryEntry(parallel ? registryTargetAnnotatedElementRecords.parallelStream() : registryTargetAnnotatedElementRecords.stream(), configs, infoProvider);
					wireRegistry(parallel ? registryTargetAnnotatedElementRecords.parallelStream() : registryTargetAnnotatedElementRecords.stream(), configs, infoProvider);
				}				
				
				return registry;
			}
			
		};
	}
	
	protected boolean matchFactoryMethod(ProcessorConfig elementProcessorConfig, Method method) {
		if (Modifier.isAbstract(method.getModifiers())) {
			return false;
		}
		
		Processor elementProcessorAnnotation = method.getAnnotation(Processor.class);
		if (elementProcessorAnnotation == null) {
			return false;
		}
		
		Element element = elementProcessorConfig.getElement();
		if (!elementProcessorAnnotation.type().isInstance(element)) {
			return false;
		}
		
		if (method.getParameterCount() != 4 ||
				!method.getParameterTypes()[1].isAssignableFrom(boolean.class) ||
				!method.getParameterTypes()[2].isAssignableFrom(BiConsumer.class) ||
				!method.getParameterTypes()[3].isAssignableFrom(ProgressMonitor.class)) {
			throw new IllegalArgumentException("Factory method shall have 4 parameters compatible with: "
					+ "ProcessorConfig config, "
					+ "boolean parallel, "
					+ "BiConsumer<Element, BiConsumer<ProcessorInfo<P>,ProgressMonitor>> infoProvider, "
					+ "ProgressMonitor progressMonitor: " + method);
		}
		
		if (!method.getParameterTypes()[0].isInstance(elementProcessorConfig)) {
			return false;
		}
		
		return matchPredicate(elementProcessorConfig.getElement(), elementProcessorAnnotation.value());
	}

	protected int compareProcessorMethods(Method a, Method b) {
		int priorityCmp = b.getAnnotation(Processor.class).priority() - a.getAnnotation(Processor.class).priority();
		if (priorityCmp != 0) {
			return priorityCmp;
		}
		
		Class<? extends Element> aType = a.getAnnotation(Processor.class).type();
		Class<? extends Element> bType = b.getAnnotation(Processor.class).type();
		if (!Objects.equals(aType, bType)) {
			if (aType.isAssignableFrom(bType)) {
				// b is more specific
				return 1;
			}
			if (bType.isAssignableFrom(aType)) {
				// a is more specific
				return -1;
			}
		}
		
		// Method in a sub-class is more specific
		Class<?> adc = a.getDeclaringClass();
		Class<?> bdc = b.getDeclaringClass();
		if (adc.isAssignableFrom(bdc)) {
			return adc == bdc ? 0 : 1;
		} 
		
		if (bdc.isAssignableFrom(adc)) {
			return -1;
		}
		
		// Taking config is more specific 
		int paramCountCmp = b.getParameterCount() - a.getParameterCount();
		if (paramCountCmp != 0) {
			return paramCountCmp;
		}
		
		return a.getName().compareTo(b.getName());
	}
	
	@SuppressWarnings("unchecked")
	protected P createProcessor(
			ProcessorConfig config, 
			boolean parallel, 
			BiConsumer<Element,BiConsumer<ProcessorInfo<P>,ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			ProgressMonitor progressMonitor) {
		
		// TODO - progress steps.
		Optional<AnnotatedElementRecord> match = (parallel ? annotatedElementRecords.parallelStream() : annotatedElementRecords.stream())
			.filter(r -> r.test(config.getElement()) && r.getAnnotatedElement() instanceof Method && matchFactoryMethod(config, (Method) r.getAnnotatedElement()))
			.sorted((a, b) -> compareProcessorMethods((Method) a.getAnnotatedElement(), (Method) b.getAnnotatedElement()))
			.findFirst();
		
		if (match.isEmpty()) {
			return null;			
		}
		
		AnnotatedElementRecord matchedRecord = match.get();
		Method method = (Method) matchedRecord.getAnnotatedElement();
		P processor = (P) matchedRecord.invoke(config, parallel, infoProvider, progressMonitor);
		if (processor == null) {
			return processor;			
		}		
		Processor elementProcessorAnnotation = method.getAnnotation(Processor.class);
		if (elementProcessorAnnotation.wire()) {
			wireProcessor(
					config, 
					processor, 
					elementProcessorAnnotation.hideWired(), 
					parallel, 
					infoProvider, 
					endpointWiringStageConsumer, 
					progressMonitor);
		}
		
		return (P) processor;
	}
	
}
