package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

/**
 * Command containing other commands and executing them in the executor if one is passed to the constructor or in the caller thread otherwise. The last child command is always
 * executed in the caller thread.
 * @author Pavel
 *
 * @param <T> Command result type.
 * @param <E> Child command result type.
 */
public abstract class CompoundCommand<T, E> implements Command<T> {
	
	private Executor executor;
	
	private static class CommandEntry<E> {
		Command<E> command;
		CommandCallable<E> callable;
		String name;
		double size;
		List<Object> data = new ArrayList<>();
		
		CommandEntry(
				Command<E> command, 
				CommandCallable<E> callable, 
				String name, 
				double size,
				List<Object> data) {
			this.command = command;
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
	 * Adds a child command
	 * @param child
	 * @param name
	 * @param size
	 * @return {@link CommandCallable} wrapping the command.
	 */
	public Callable<E> add(Command<E> child, String name, double size, Object... data) {
		CommandEntry<E> childEntry = new CommandEntry<E>(child, new CommandCallable<E>(child, size), name, size, Arrays.asList(data));
		children.add(childEntry);
		return childEntry.callable;
	}
	
	/**
	 * Adds a child command which is not executed as part of this command execute(), it just obtains a monitor for reporting progress - this behavior may be used if the child execution is triggered by some other
	 * code, but the child needs to report its progress under this compound command.
	 * @param child
	 * @param name
	 * @param size
	 * @return {@link CommandCallable} wrapping the command.
	 */
	public <R> Callable<R> addNoExec(Command<R> child, String name, double size, Object... data) {
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
		Diagnostic ret = Command.super.diagnose(progressMonitor);
		progressMonitor.setWorkRemaining(children.size() + noExecChildren.size());
		for (CommandEntry<?> ce: children) {
			((DiagnosticChain) ret).add(ce.command.diagnose(progressMonitor.split(ce.name, 1, ce.data)));
		}
		for (CommandEntry<?> ce: noExecChildren) {
			((DiagnosticChain) ret).add(ce.command.diagnose(progressMonitor.split(ce.name, 1, ce.data)));
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
			if (executor != null) {
				Iterator<CommandEntry<E>> ceit = theChildren.iterator();
				while (ceit.hasNext()) {
					CommandEntry<E> ce = ceit.next();
					if (ceit.hasNext()) {
						executor.execute(() -> {
							try {
								ce.callable.call();
							} catch (Exception e) {
								handleException(ce.name, ce.command, e, ce.data);
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
	 * Override to handle exception thrown from child command's execute() during asynchronous execution.
	 * The default behavior implemented by {@link CommandCallable} is to record the thrown exception and re-throw when results are collected.
	 * @param name
	 * @param command
	 * @param e
	 */
	protected void handleException(String name, Command<E> command, Exception e, List<Object> data) {
		
	}

	/**
	 * Combines children results.
	 * @param results
	 * @return
	 */
	protected abstract T combine(List<E> results, ProgressMonitor progressMonitor) throws Exception;

}
