package org.nasdanika.graph.processor.function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reflector;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory.ConnectionProcessor;
import org.nasdanika.graph.processor.function.InvocationRequestBiFunctionProcessorFactory.InvocationType;

/**
 * Dispatches invocations to targets
 */
public class ReflectiveBiFunctionConnectionProcessor extends Reflector implements ConnectionProcessor<InvocationRequest, Object, InvocationRequest, Object> {
	
	private List<AnnotatedElementRecord> annotatedElementRecords = new ArrayList<>();
		
	public ReflectiveBiFunctionConnectionProcessor(Collection<Object> targets) {
		for (Object target: targets) {
			getAnnotatedElementRecords(target, Collections.singletonList(target)).forEach(annotatedElementRecords::add);
		}
	}

	@Override
	public Object apply(InvocationRequest input, ProgressMonitor progressMonitor) {
		Object[] args = new Object[input.args().length + 2];
		args[0] = InvocationType.PROCESSOR;
		args[args.length - 1] = progressMonitor;
		System.arraycopy(input.args(), 0, args, 1, input.args().length);
		return invoke(annotatedElementRecords.stream(), input.name(), args);
	}

	@Override
	public Object targetApply(
			InvocationRequest input, 
			ProgressMonitor progressMonitor,
			BiFunction<InvocationRequest, ProgressMonitor, Object> sourceEndpoint) {
		Object[] args = new Object[input.args().length + 3];
		args[0] = InvocationType.TARGET;
		args[1] = sourceEndpoint;
		args[args.length - 1] = progressMonitor;
		System.arraycopy(input.args(), 0, args, 2, input.args().length);
		return invoke(annotatedElementRecords.stream(), input.name(), args);
	}

	@Override
	public Object sourceApply(
			InvocationRequest input, 
			ProgressMonitor progressMonitor,
			BiFunction<InvocationRequest, ProgressMonitor, Object> targetEndpoint) {
		Object[] args = new Object[input.args().length + 3];
		args[0] = InvocationType.SOURCE;
		args[1] = targetEndpoint;
		args[args.length - 1] = progressMonitor;
		System.arraycopy(input.args(), 0, args, 2, input.args().length);
		return invoke(annotatedElementRecords.stream(), input.name(), args);
	}
	
}