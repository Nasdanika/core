package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CancellationException;

/**
 * TODO - support of concurrent execution.
 * @author Pavel
 *
 * @param <E>
 */
public abstract class CompoundExecutionParticipant<E extends ExecutionParticipant> implements ExecutionParticipant {
		
	private String name;
	private boolean reverseCommitClose;

	/**
	 * 
	 * @param name
	 * @param reverseCommitClose If true commit and close are executed in reverse order. 
	 * It can be required in participant chaining scenarios where one participant supplies arguments to another participant and
	 * therefore the lifecycle of the second participant shall be enclosed into the lifecycle of the first paricipant.
	 */
	protected CompoundExecutionParticipant(String name, boolean reverseCommitClose) {
		this.name = name;
		this.reverseCommitClose = reverseCommitClose;
	}

	@Override
	public String name() {
		return name;
	}	
	
	protected abstract Collection<E> getElements();
	
	protected Double size; // Set to null to clear cached value

	@Override
	public double size() {
		if (size == null) {
			size = 0.0;
			for (E e: getElements()) {
				size += e.size();
			}
		}
		return size;
	}

	@Override
	public Diagnostic diagnose(ProgressMonitor progressMonitor) {		
		if (progressMonitor.isCancelled()) {
			progressMonitor.worked(1, "Cancelled");
			return new BasicDiagnostic(Status.CANCEL, "Progress monitor is cancelled", this);
		}
		BasicDiagnostic ret = new BasicDiagnostic(Status.INFO, name());
		progressMonitor.setWorkRemaining(size());
		for (E e: getElements()) {
			ret.add(e.splitAndDiagnose(progressMonitor));
		}
		return ret;
	}
	
	@Override
	public void commit(ProgressMonitor progressMonitor) {
		if (progressMonitor.isCancelled()) {
			progressMonitor.worked(1, "Cancelled");
			throw new CancellationException();
		}
		progressMonitor.setWorkRemaining(size());
		List<E> elements = new ArrayList<E>(getElements());
		if (reverseCommitClose) {
			Collections.reverse(elements);
		}
		for (E e: elements) {
			e.splitAndCommit(progressMonitor);
		}
	}
	
	@Override
	public boolean rollback(ProgressMonitor progressMonitor) {
		if (progressMonitor.isCancelled()) {
			progressMonitor.worked(1, "Cancelled");
			throw new CancellationException();
		}
		progressMonitor.setWorkRemaining(size());
		boolean result = true;
		List<E> elements = new ArrayList<E>(getElements());
		Collections.reverse(elements);
		for (E e: elements) {
			result = e.splitAndRollback(progressMonitor) && result; 
		}
		return result;
	}
	
	@Override
	public void close() throws Exception {
		List<E> elements = new ArrayList<E>(getElements());
		if (reverseCommitClose) {
			Collections.reverse(elements);
		}
		for (E e: elements) {
			e.close();
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + name() + " " + size();
	}

}
