package org.nasdanika.common;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/**
 * Supplier composed from other work and executing its children in the executor if one is passed to the constructor or in the caller thread otherwise. The last child is always
 * executed in the caller thread. Execution order is reversed for undo().
 * @author Pavel
 *
 * @param <T> Supplier result type.
 * @param <E> Child result type.
 */
public abstract class CompoundSupplier<T,E> implements Supplier<T>, CompoundExecutionParticipantInfo {
	
	private CompoundCommand<Void, Void> commitCommand;
	private CompoundCommand<Boolean, Boolean> rollbackCommand;
	private CompoundCommand<T, E> executeCommand;
	private String name;
	private List<ExecutionParticipantInfo> children = new ArrayList<>(); 

	public CompoundSupplier(String name, Executor executor) {
		this.name = name;
		this.executeCommand = new CompoundCommand<T, E>(executor, false) {

			@Override
			protected T combine(List<E> results, ProgressMonitor progressMonitor) throws Exception {
				return CompoundSupplier.this.combine(results, progressMonitor);
			}
			
			@SuppressWarnings("unchecked")
			@Override
			protected void handleException(String name, _LegacyCommandToRemove<E> command, Exception e, List<Object> data) {
				CompoundSupplier.this.handleException((Supplier<E>) data.get(0), e, false);
			}
			
		};
		
		this.commitCommand = new CompoundCommand<Void,Void>(executor, true) {

			@Override
			protected Void combine(List<Void> results, ProgressMonitor progressMonitor) {
				return null;
			}
			
			@SuppressWarnings("unchecked")
			@Override
			protected void handleException(String name, _LegacyCommandToRemove<Void> command, Exception e, List<Object> data) {
				CompoundSupplier.this.handleException((Supplier<E>) data.get(0), e, true);
			}
			
		};
		
		this.rollbackCommand = new CompoundCommand<Boolean,Boolean>(executor, true) {

			@Override
			protected Boolean combine(List<Boolean> results, ProgressMonitor progressMonitor) {
				return results.stream().reduce(true, (a,b) -> a && b);
			}
			
			@SuppressWarnings("unchecked")
			@Override
			protected void handleException(String name, _LegacyCommandToRemove<Boolean> command, Exception e, List<Object> data) {
				CompoundSupplier.this.handleException((Supplier<E>) data.get(0), e, true);
			}
			
		};
	}
	
	/**
	 * Adds a child work
	 * @param child
	 * @return {@link CommandCallable} wrapping the work's execute() method.
	 */
	public Callable<E> add(Supplier<E> child) {
		children.add(child);
		rollbackCommand.add(child::rollback, child.getName(), child.size(), child);
		return executeCommand.add(child, child.getName(), child.size(), child);
	}
	
	/**
	 * Adds a child work which is not executed as part of this workd execute(), it just obtains a monitor for reporting progress - this behavior may be used if the child execution is triggered by some other
	 * code, but the child needs to report its progress under this compound work. Child's undo() method is invoked by this work undo().
	 * @param child
	 * @return {@link CommandCallable} wrapping the _LegacyCommandToRemove.
	 */
	public <R> Callable<R> addNoExec(Supplier<R> child) {
		children.add(child);
		rollbackCommand.add(child::rollback, child.getName(), child.size(), child);
		return executeCommand.addNoExec(child::execute, child.getName(), child.size(), child);
	}	

	@Override
	public double size() {
		return children.stream().mapToDouble(ExecutionParticipantInfo::size).sum();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public List<ExecutionParticipantInfo> getChildren() {
		return children;
	}

	@Override
	public T execute(ProgressMonitor progressMonitor) throws Exception {
		return executeCommand.execute(progressMonitor);
	}
	
	@Override
	public Diagnostic diagnose(ProgressMonitor progressMonitor) {
		return executeCommand.diagnose(progressMonitor);
	}

	@Override
	public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
		return rollbackCommand.execute(progressMonitor);
	}
	
	@Override
	public void commit(ProgressMonitor progressMonitor) throws Exception {
		commitCommand.execute(progressMonitor);
	}
	
	/**
	 * Override to handle exception thrown from child _LegacyCommandToRemove's execute() during asynchronous execution.
	 * The default behavior implemented by {@link CommandCallable} is to record the thrown exception and re-throw when results are collected.
	 * @param child Child work
	 * @param e Exception thrown
	 * @param undo to indicate whether the exception was thrown by undo() or execute().
	 */
	protected void handleException(Supplier<E> child, Exception e, boolean undo) {
		
	}

	/**
	 * Combines children results.
	 * @param results
	 * @return
	 */
	protected abstract T combine(List<E> results, ProgressMonitor progressMonitor) throws Exception;
	
	@Override
	public void close() throws Exception {
		for (ExecutionParticipantInfo child: children) {
			if (child instanceof AutoCloseable) {
				((AutoCloseable) child).close();
			}
		}
	}

}
