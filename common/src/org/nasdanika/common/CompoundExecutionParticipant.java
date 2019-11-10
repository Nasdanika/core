package org.nasdanika.common;

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

	protected CompoundExecutionParticipant(String name) {
		this.name = name;
	}

	@Override
	public String name() {
		return name;
	}	
	
	protected abstract List<E> getElements();

	@Override
	public double size() {
		return getElements().stream().mapToDouble(ExecutionParticipant::size).sum();
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
	public void commit(ProgressMonitor progressMonitor) throws Exception {
		if (progressMonitor.isCancelled()) {
			progressMonitor.worked(1, "Cancelled");
			throw new CancellationException();
		}
		progressMonitor.setWorkRemaining(size());
		for (E e: getElements()) {
			e.splitAndCommit(progressMonitor);
		}
	}
	
	@Override
	public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
		if (progressMonitor.isCancelled()) {
			progressMonitor.worked(1, "Cancelled");
			throw new CancellationException();
		}
		progressMonitor.setWorkRemaining(size());
		boolean result = true;
		List<E> elements = getElements();
		Collections.reverse(getElements());
		for (E e: elements) {
			result = e.splitAndRollback(progressMonitor) && result; 
		}
		return result;
	}
	
	@Override
	public void close() throws Exception {
		for (E e: getElements()) {
			e.close();
		}
	}

}
