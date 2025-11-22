package org.nasdanika.graph.processor;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reflector;
import org.nasdanika.common.Util;
import org.nasdanika.graph.Element;

/**
 * Base class with methods to wire registry and registry entries using annotations.
 *
 * @param <P>
 */
public class ReflectiveRegistryWirer<H,E,P> extends Reflector {
		
	/**
	 * Records don't work with non-static types.
	 */
	protected class RegistryMatch {
		RegistryEntry annotation; 
		AnnotatedElementRecord setterRecord;
		ProcessorConfig<H,E> config;
		
		public RegistryMatch(
				RegistryEntry annotation, 
				AnnotatedElementRecord setterRecord, 
				ProcessorConfig<H,E> config) {
			super();
			this.annotation = annotation;
			this.setterRecord = setterRecord;
			this.config = config;
		}		
		
	}
		
	protected void wireRegistryEntry(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Collection<ProcessorConfig<H,E>> registry,
			Map<String,Object> variables,
			BiConsumer<Element,BiConsumer<ProcessorInfo<H,E,P>,ProgressMonitor>> infoProvider) {
		
		processorAnnotatedElementRecords
			.filter(ae -> ae.getAnnotation(RegistryEntry.class) != null)
			.filter(ae -> ae.mustSet(null, "Fields/methods annotated with RegistryEntry must have (parameter) type assignable from the processor type, ProcessorInfo or Synapse: " + ae.getAnnotatedElement()))
			.flatMap(setterRecord -> registry.stream().filter(Objects::nonNull).map(re -> new RegistryMatch(setterRecord.getAnnotation(RegistryEntry.class), setterRecord, re)))
			.filter(rm -> evaluatePredicate(rm.config.getElement(), rm.annotation.value(), variables))
			.forEach(rm -> {
				infoProvider.accept(rm.config.getElement(), (rpInfo, pMonitor) -> {
					AnnotatedElementRecord setterRecord = rm.setterRecord;
					P processor = rpInfo.getProcessor();
					if (setterRecord.canSet(Synapse.class) &&
							(rm.annotation.type() == RegistryEntry.Type.SYNAPSE || setterRecord.getSetterType() == Synapse.class)) {
						
						String clientKeyExpr = rm.annotation.clientKey();
						Object clientKey = Util.isBlank(clientKeyExpr) ? setterRecord.getTarget() : evaluate(setterRecord.getTarget(), clientKeyExpr, variables, Object.class);
						Synapse<H, E> synapse = rpInfo.getClientSynapse(clientKey);
						setterRecord.set(synapse);
					} else if (setterRecord.canSet(rpInfo.getClass()) &&
							(rm.annotation.type() == RegistryEntry.Type.INFO || ProcessorInfo.class.isAssignableFrom(setterRecord.getSetterType()))) {
						
						setterRecord.set(rpInfo);											
					} else if (processor != null && rm.annotation.type() == RegistryEntry.Type.PROCESSOR && setterRecord.canSet(processor.getClass())) {
						setterRecord.set(processor);						
					}
				});
			});
	}

	protected void wireRegistry(
			Stream<AnnotatedElementRecord> processorAnnotatedElementRecords,
			Collection<ProcessorConfig<H,E>> registry, 
			BiConsumer<Element,BiConsumer<ProcessorInfo<H,E,P>,ProgressMonitor>> infoProvider) {

		processorAnnotatedElementRecords
				.filter(aer -> aer.getAnnotation(Registry.class) != null)
				.filter(aer -> aer.mustSet(Map.class, "Fields/methods annotated with Registry must have (parameter) type assignable from Map: " + aer.getAnnotatedElement()))
				.forEach(setterRecord -> {
					// Sets a map which is populated as processors get created
					Map<Element,ProcessorInfo<H,E,P>> r = Collections.synchronizedMap(new LinkedHashMap<>());
					setterRecord.set(r);
					for (ProcessorConfig<H,E> re: registry) {
						if (re != null) {
							infoProvider.accept(re.getElement(), (rp, pm) -> r.put(re.getElement(), rp));
						}
					}
				});
	}
		
}
