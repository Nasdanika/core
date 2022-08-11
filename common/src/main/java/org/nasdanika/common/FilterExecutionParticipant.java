package org.nasdanika.common;

public class FilterExecutionParticipant<T extends ExecutionParticipant> implements ExecutionParticipant {
		
	protected T target;

	public FilterExecutionParticipant(T target) {
		this.target = target;
	}

	@Override
	public double size() {
		return target.size();
	}

	@Override
	public String name() {
		return target.name();
	}
	
	@Override
	public Diagnostic diagnose(ProgressMonitor progressMonitor) {
		return target.diagnose(progressMonitor);
	}
	
	@Override
	public void commit(ProgressMonitor progressMonitor) {
		target.commit(progressMonitor);
	}
	
	@Override
	public boolean rollback(ProgressMonitor progressMonitor) {
		return target.rollback(progressMonitor);
	}
	
	@Override
	public void close() throws Exception {
		target.close();
	}

}
