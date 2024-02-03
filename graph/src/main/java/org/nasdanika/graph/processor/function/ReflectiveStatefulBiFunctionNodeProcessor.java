package org.nasdanika.graph.processor.function;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reflector;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.function.InvocationRequestBiFunctionProcessorFactory.InvocationType;

/**
 * Dispatches invocations to targets
 */
public abstract class ReflectiveStatefulBiFunctionNodeProcessor<S> extends Reflector implements StatefulBiFunctionNodeProcessor<InvocationRequest, Object, InvocationRequest, Object, S> {
	
	private List<AnnotatedElementRecord> annotatedElementRecords = new ArrayList<>();
		
	public ReflectiveStatefulBiFunctionNodeProcessor(Collection<Object> targets) {
		for (Object target: targets) {
			getAnnotatedElementRecords(target, Collections.singletonList(target)).forEach(annotatedElementRecords::add);
		}
	}

	@Override
	public Object applyIncoming(
			Connection connection, 
			InvocationRequest input, 
			List<S> history,
			ProgressMonitor progressMonitor) {
		Object[] args = new Object[input.args().length + 4];
		args[0] = InvocationType.INCOMING;
		args[1] = connection;
		args[2] = history;
		args[args.length - 1] = progressMonitor;
		System.arraycopy(input.args(), 0, args, 3, input.args().length);
		return invoke(annotatedElementRecords.stream(), input.name(), args);
	}

	@Override
	public Object applyOutgoing(
			Connection connection, 
			InvocationRequest input, 
			List<S> history,
			ProgressMonitor progressMonitor) {
		Object[] args = new Object[input.args().length + 4];
		args[0] = InvocationType.OUTGOING;
		args[1] = connection;
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
		Object[] args = new Object[input.args().length + 3];
		args[0] = InvocationType.PROCESSOR;
		args[1] = history;
		args[args.length - 1] = progressMonitor;
		System.arraycopy(input.args(), 0, args, 2, input.args().length);
		return invoke(annotatedElementRecords.stream(), input.name(), args);
	}
	
}