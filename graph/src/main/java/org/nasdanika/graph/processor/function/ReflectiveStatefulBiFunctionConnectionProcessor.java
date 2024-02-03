package org.nasdanika.graph.processor.function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reflector;
import org.nasdanika.graph.processor.function.InvocationRequestBiFunctionProcessorFactory.InvocationType;

/**
 * Dispatches invocations to targets
 */
public abstract class ReflectiveStatefulBiFunctionConnectionProcessor<S> extends Reflector implements StatefulBiFunctionConnectionProcessor<InvocationRequest, Object, InvocationRequest, Object, S> {
	
	private List<AnnotatedElementRecord> annotatedElementRecords = new ArrayList<>();
		
	public ReflectiveStatefulBiFunctionConnectionProcessor(Collection<Object> targets) {
		for (Object target: targets) {
			getAnnotatedElementRecords(target, Collections.singletonList(target)).forEach(annotatedElementRecords::add);
		}
	}

	@Override
	public Object sourceApply(
			InvocationRequest input,
			BiFunction<InvocationRequest, ProgressMonitor, Object> targetEndpoint, 
			List<S> history,
			ProgressMonitor progressMonitor) {
		Object[] args = new Object[input.args().length + 4];
		args[0] = InvocationType.SOURCE;
		args[1] = targetEndpoint;
		args[2] = history;
		args[args.length - 1] = progressMonitor;
		System.arraycopy(input.args(), 0, args, 3, input.args().length);
		return invoke(annotatedElementRecords.stream(), input.name(), args);
	}

	@Override
	public Object targetApply(
			InvocationRequest input,
			BiFunction<InvocationRequest, ProgressMonitor, Object> sourceEndpoint, 
			List<S> history,
			ProgressMonitor progressMonitor) {
		Object[] args = new Object[input.args().length + 4];
		args[0] = InvocationType.TARGET;
		args[1] = sourceEndpoint;
		args[2] = history;
		args[args.length - 1] = progressMonitor;
		System.arraycopy(input.args(), 0, args, 3, input.args().length);
		return invoke(annotatedElementRecords.stream(), input.name(), args);
	}

	@Override
	public Object apply(
			InvocationRequest input, 
			List<S> history, 
			ProgressMonitor progressMonitor) {
		Object[] args = new Object[input.args().length + 2];
		args[0] = InvocationType.SOURCE;
		args[1] = history;
		args[args.length - 1] = progressMonitor;
		System.arraycopy(input.args(), 0, args, 2, input.args().length);
		return invoke(annotatedElementRecords.stream(), input.name(), args);
	}
	
}