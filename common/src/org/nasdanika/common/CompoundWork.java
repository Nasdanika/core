package org.nasdanika.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/**
 * Work composed from other work and executing its children in the executor if one is passed to the constructor or in the caller thread otherwise. The last child is always
 * executed in the caller thread. Execution order is reversed for undo().
 * @author Pavel
 *
 * @param <T> Work result type.
 * @param <E> Child result type.
 */
public abstract class CompoundWork<T,E> implements Work<T>, CompoundWorkInfo {
	
	private CompoundCommand<Boolean, Boolean> undoCommand;
	private CompoundCommand<T, E> executeCommand;
	private String name;
	private List<WorkInfo> children = new ArrayList<>(); 

	public CompoundWork(String name, Executor executor) {
		this.name = name;
		this.executeCommand = new CompoundCommand<T, E>(executor, false) {

			@Override
			protected T combine(List<E> results) {
				return CompoundWork.this.combine(results);
			}
			
			@SuppressWarnings("unchecked")
			@Override
			protected void handleException(String name, Command<E> command, Exception e, Object[] details) {
				CompoundWork.this.handleException((Work<E>) details[0], e, false);
			}
			
		};
		
		this.undoCommand = new CompoundCommand<Boolean,Boolean>(executor, true) {

			@Override
			protected Boolean combine(List<Boolean> results) {
				return results.stream().reduce(true, (a,b) -> a && b);
			}
			
			@SuppressWarnings("unchecked")
			@Override
			protected void handleException(String name, Command<Boolean> command, Exception e, Object[] details) {
				CompoundWork.this.handleException((Work<E>) details[0], e, true);
			}
			
		};
	}
	
	/**
	 * Adds a child work
	 * @param child
	 * @return {@link CommandCallable} wrapping the work's execute() method.
	 */
	public Callable<E> add(Work<E> child) {
		children.add(child);
		undoCommand.add(child::undo, child.getName(), child.size(), child);
		return executeCommand.add(child::execute, child.getName(), child.size(), child);
	}
	
	/**
	 * Adds a child work which is not executed as part of this workd execute(), it just obtains a monitor for reporting progress - this behavior may be used if the child execution is triggered by some other
	 * code, but the child needs to report its progress under this compound work. Child's undo() method is invoked by this work undo().
	 * @param child
	 * @return {@link CommandCallable} wrapping the command.
	 */
	public <R> Callable<R> addNoExec(Work<R> child) {
		children.add(child);
		undoCommand.add(child::undo, child.getName(), child.size(), child);
		return executeCommand.addNoExec(child::execute, child.getName(), child.size(), child);
	}	

	@Override
	public long size() {
		return children.stream().mapToLong(WorkInfo::size).sum();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<WorkInfo> getChildren() {
		return children;
	}

	@Override
	public T execute(ProgressMonitor progressMonitor) throws Exception {
		return executeCommand.execute(progressMonitor);
	}

	@Override
	public boolean undo(ProgressMonitor progressMonitor) throws Exception {
		return undoCommand.execute(progressMonitor);
	}
	
	/**
	 * Override to handle exception thrown from child command's execute() during asynchronous execution.
	 * The default behavior implemented by {@link CommandCallable} is to record the thrown exception and re-throw when results are collected.
	 * @param child Child work
	 * @param e Exception thrown
	 * @param undo to indicate whether the exception was thrown by undo() or execute().
	 */
	protected void handleException(Work<E> child, Exception e, boolean undo) {
		
	}

	/**
	 * Combines children results.
	 * @param results
	 * @return
	 */
	protected abstract T combine(List<E> results);

}
