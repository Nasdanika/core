package org.nasdanika.graph.processor;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reflector;
import org.nasdanika.graph.Element;

/**
 * Base class with methods to wire registry and registry entries using annotations.
 *
 * @param <P>
 */
public class ReflectiveRegistryWirer<P> extends Reflector {
		
	/**
	 * Records don't work with non-static types.
	 */
	protected class RegistryMatch {
		RegistryEntry annotation; 
		AnnotatedElementRecord setterRecord;
		ProcessorConfig config;
		
		public RegistryMatch(
				RegistryEntry annotation, 
				AnnotatedElementRecord setterRecord, 
				ProcessorConfig config) {
			super();
			this.annotation = annotation;
			this.setterRecord = setterRecord;
			this.config = config;
		}		
		
	}
		
	protected void wireRegistryEntry(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Collection<ProcessorConfig> registry,
			BiConsumer<Element,BiConsumer<ProcessorInfo<P>,ProgressMonitor>> infoProvider) {
		
		processorAnnotatedElementRecords
			.filter(ae -> ae.getAnnotation(RegistryEntry.class) != null)
			.filter(ae -> ae.mustSet(null, "Fields/methods annotated with RegistryEntry must have (parameter) type assignable from the processor type or ProcessorInfo if info is set to true: " + ae.getAnnotatedElement()))
			.flatMap(setterRecord -> registry.stream().map(re -> new RegistryMatch(setterRecord.getAnnotation(RegistryEntry.class), setterRecord, re)))
			.filter(rm -> matchPredicate(rm.config.getElement(), rm.annotation.value()))
			.forEach(rm -> {
				infoProvider.accept(rm.config.getElement(), (rpInfo, pMonitor) -> {
					rm.setterRecord.set(rm.annotation.info() ? rpInfo : rpInfo.getProcessor());
				});
			});
	}

	protected void wireRegistry(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Collection<ProcessorConfig> registry, 
			BiConsumer<Element,BiConsumer<ProcessorInfo<P>,ProgressMonitor>> infoProvider) {

		processorAnnotatedElementRecords
				.filter(aer -> aer.getAnnotation(Registry.class) != null)
				.filter(aer -> aer.mustSet(Map.class, "Fields/methods annotated with Registry must have (parameter) type assignable from Map: " + aer.getAnnotatedElement()))
				.forEach(setterRecord -> {
					// Sets a map which is populated as processors get created
					Map<Element,ProcessorInfo<P>> r = Collections.synchronizedMap(new LinkedHashMap<>());
					setterRecord.set(r);
					for (ProcessorConfig re: registry) {
						infoProvider.accept(re.getElement(), (rp, pm) -> r.put(re.getElement(), rp));
					}
				});
	}
		
}
