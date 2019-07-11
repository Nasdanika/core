package org.nasdanika.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/**
 * Command containing other commands and executing them possibly in the caller thread or in the executor if one is passed to the constructor.
 * @author Pavel
 *
 * @param <T> Command result type.
 * @param <E> Child command result type.
 */
public abstract class CompoundCommand<T, E> implements Command<T> {
	
	private Executor executor;
	
	private class ChildEntry {
		Command<E> command;
		CommandCallable<E> callable;
		String name;
		long size;
		
		ChildEntry(Command<E> command, CommandCallable<E> callable, String name, long size) {
			this.command = command;
			this.callable = callable;
			this.name = name;
			this.size = size;
		}		
		
	}
	
	private List<ChildEntry> children = new ArrayList<>();

	public CompoundCommand(Executor executor) {
		this.executor = executor;
	}
	
	/**
	 * Adds a child command
	 * @param child
	 * @param name
	 * @param size
	 * @return {@link CommandCallable} wrapping the command.
	 */
	public Callable<E> add(Command<E> child, String name, long size) {
		ChildEntry childEntry = new ChildEntry(child, new CommandCallable<E>(child), name, size);
		children.add(childEntry);
		return childEntry.callable;
	}

	/**
	 * Splits the monitor between the children and then executes them in the executor or the current thread. Returns a combined result upon completion of all executions.
	 */
	@Override
	public T execute(ProgressMonitor progressMonitor) throws Exception {
		// Split the monitor
		for (ChildEntry ce: children) {
			ce.callable.setMonitor(progressMonitor.split(ce.name, ce.size, ce.command));
		}
		
		// Submitting for asynchronous execution
		if (executor != null) {
			for (ChildEntry ce: children) {				
				executor.execute(() -> {
					try {
						ce.callable.call();
					} catch (Exception e) {
						handleException(ce.name, ce.command, e);
					}
				});
			}
		}
		
		// Collecting results
		List<E> results = new ArrayList<>();
		for (ChildEntry ce: children) {
			results.add(ce.callable.call());
		}
		return combine(results);
	}
	
	/**
	 * Override to handle exception thrown from child command's execute() during asynchronous execution.
	 * The default behavior implemented by {@link CommandCallable} is to record the thrown exception and re-throw when results are collected.
	 * @param name
	 * @param command
	 * @param e
	 */
	protected void handleException(String name, Command<E> command, Exception e) {
		
	}

	/**
	 * Combines children results.
	 * @param results
	 * @return
	 */
	protected abstract T combine(List<E> results);

}
