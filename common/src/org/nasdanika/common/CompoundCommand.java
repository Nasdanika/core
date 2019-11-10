package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

import org.nasdanika.common._legacy._LegacyCommandToRemove;

/**
 * _LegacyCommandToRemove containing other commands and executing them in the executor if one is passed to the constructor or in the caller thread otherwise. The last child _LegacyCommandToRemove is always
 * executed in the caller thread.
 * @author Pavel
 *
 * @param <T> _LegacyCommandToRemove result type.
 * @param <E> Child _LegacyCommandToRemove result type.
 */
public abstract class CompoundCommand<T, E> implements _LegacyCommandToRemove<T> {
	
	private Executor executor;
	
	/**
	 * @return Executor passed to the constructor. Override to return null to force sequential execution.
	 */
	protected Executor getExecutor() {
		return executor;
	}
	
	private static class CommandEntry<E> {
		_LegacyCommandToRemove<E> _LegacyCommandToRemove;
		CommandCallable<E> callable;
		String name;
		double size;
		List<Object> data = new ArrayList<>();
		
		CommandEntry(
				_LegacyCommandToRemove<E> command, 
				CommandCallable<E> callable, 
				String name, 
				double size,
				List<Object> data) {
			this._LegacyCommandToRemove = command;
			this.callable = callable;
			this.name = name;
			this.size = size;
			if (data != null) {
				this.data.addAll(data);
			}
		}		
		
	}
	
	private List<CommandEntry<E>> children = new ArrayList<>();
	
	private List<CommandEntry<?>> noExecChildren = new ArrayList<>();

	private boolean reverse;

	/**
	 * 
	 * @param executor Executor to submit sub-commands execution to.
	 * @param reverse If true, sub-commands are executed in the reverse order.
	 */
	public CompoundCommand(Executor executor, boolean reverse) {
		this.executor = executor;
		this.reverse = reverse;
	}
	
	/**
	 * Adds a child _LegacyCommandToRemove
	 * @param child
	 * @param name
	 * @param size
	 * @return {@link CommandCallable} wrapping the _LegacyCommandToRemove.
	 */
	public Callable<E> add(_LegacyCommandToRemove<E> child, String name, double size, Object... data) {
		CommandEntry<E> childEntry = new CommandEntry<E>(child, new CommandCallable<E>(child, size), name, size, Arrays.asList(data));
		children.add(childEntry);
		return childEntry.callable;
	}
	
	/**
	 * Adds a child _LegacyCommandToRemove which is not executed as part of this _LegacyCommandToRemove execute(), it just obtains a monitor for reporting progress - this behavior may be used if the child execution is triggered by some other
	 * code, but the child needs to report its progress under this compound _LegacyCommandToRemove.
	 * @param child
	 * @param name
	 * @param size
	 * @return {@link CommandCallable} wrapping the _LegacyCommandToRemove.
	 */
	public <R> Callable<R> addNoExec(_LegacyCommandToRemove<R> child, String name, double size, Object... data) {
		CommandEntry<R> childEntry = new CommandEntry<R>(child, new CommandCallable<R>(child, size), name, size, Arrays.asList(data));
		noExecChildren.add(childEntry);
		return childEntry.callable;
	}	
	
	@Override
	public Diagnostic diagnose(ProgressMonitor progressMonitor) {
		if (progressMonitor.isCancelled()) {
			progressMonitor.worked(1, "Cancelled");
			return new BasicDiagnostic(Status.CANCEL, "Progress monitor is cancelled", this);
		}
		Diagnostic ret = _LegacyCommandToRemove.super.diagnose(progressMonitor);
		progressMonitor.setWorkRemaining(children.size() + noExecChildren.size());
		for (CommandEntry<?> ce: children) {
			((DiagnosticChain) ret).add(ce._LegacyCommandToRemove.diagnose(progressMonitor.split(ce.name, 1, ce.data)));
		}
		for (CommandEntry<?> ce: noExecChildren) {
			((DiagnosticChain) ret).add(ce._LegacyCommandToRemove.diagnose(progressMonitor.split(ce.name, 1, ce.data)));
		}
		return ret;
	}

	/**
	 * Splits the monitor between the children and then executes them in the executor or the current thread. Returns a combined result upon completion of all executions.
	 * Results 
	 */
	@Override
	public T execute(ProgressMonitor progressMonitor) throws Exception {
		if (progressMonitor.isCancelled()) {
			throw new CancellationException("Execution has been cancelled");
		}
		try {
			// Split the monitor
			for (CommandEntry<?> ce: noExecChildren) {
				ce.callable.setMonitor(progressMonitor.split(ce.name, ce.size, ce.data));
			}
			for (CommandEntry<E> ce: children) {
				ce.callable.setMonitor(progressMonitor.split(ce.name, ce.size, ce.data));
			}
	
			List<CommandEntry<E>> theChildren = new ArrayList<>(children);
			if (reverse) {
				Collections.reverse(theChildren);
			}
			// Submitting for asynchronous execution, leaving the last element for execution in the current thread.
			if (getExecutor() != null) {
				Iterator<CommandEntry<E>> ceit = theChildren.iterator();
				while (ceit.hasNext()) {
					CommandEntry<E> ce = ceit.next();
					if (ceit.hasNext()) {
						getExecutor().execute(() -> {
							try {
								ce.callable.call();
							} catch (Exception e) {
								handleException(ce.name, ce._LegacyCommandToRemove, e, ce.data);
							}
						});					
					} else {
						// Calling the last in the current thread while other entries are executed by the executor (if there are other entries)
						ce.callable.call(); 
					}
				}
			}
	
			// Collecting results or executing if there is no executor.
			List<E> results = new ArrayList<>();
			for (CommandEntry<E> ce: theChildren) {
				results.add(ce.callable.call());
			}
			return combine(results, progressMonitor.split("Combining execution results", results.size(), results));
		} finally {
			progressMonitor.close();
		}
	}
	
	/**
	 * Override to handle exception thrown from child _LegacyCommandToRemove's execute() during asynchronous execution.
	 * The default behavior implemented by {@link CommandCallable} is to record the thrown exception and re-throw when results are collected.
	 * @param name
	 * @param _LegacyCommandToRemove
	 * @param e
	 */
	protected void handleException(String name, _LegacyCommandToRemove<E> command, Exception e, List<Object> data) {
		
	}

	/**
	 * Combines children results.
	 * @param results
	 * @return
	 */
	protected abstract T combine(List<E> results, ProgressMonitor progressMonitor) throws Exception;

}
