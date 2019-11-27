package org.nasdanika.eclipse;

import org.eclipse.core.runtime.IProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

/**
 * Wraps Nasdanika {@link ProgressMonitor} into Eclipse {@link IProgressMonitor}
 * @author Pavel
 *
 */
public class ProgressMonitorWrapper implements IProgressMonitor {

	private ProgressMonitor target;
	private String task;
	private String subTask;

	protected ProgressMonitorWrapper(ProgressMonitor progressMonitor) {
		this.target = progressMonitor;
	}

	@Override
	public void beginTask(String name, int totalWork) {
		task = name;
		target.setWorkRemaining(totalWork);
	}

	@Override
	public void done() {
		target.close();
	}

	@Override
	public void internalWorked(double work) {
		target.worked(work, "Internal worked");
	}

	@Override
	public boolean isCanceled() {
		return target.isCancelled();
	}

	@Override
	public void setCanceled(boolean value) {
		// NOP		
	}

	@Override
	public void setTaskName(String name) {
		task = name;		
	}

	@Override
	public void subTask(String name) {
		subTask = name;		
	}

	@Override
	public void worked(int work) {		
		String progressMessage = "";
		if (!Util.isBlank(task)) {
			progressMessage += task;
		}
		if (!Util.isBlank(subTask)) {
			if (!Util.isBlank(progressMessage)) {
				progressMessage += " / ";
			}
			progressMessage += subTask;
		}
		target.worked(work, progressMessage);
	}
	
	/**
	 * If monitor argument is instanceof {@link ProgressMonitorAdapter} then this method returns its target.
	 * Otherwise it creates an instance of this class and returns it.
	 * @param monitor
	 * @return
	 */
	public static IProgressMonitor wrap(ProgressMonitor monitor) {
		if (monitor instanceof ProgressMonitorAdapter) {
			return ((ProgressMonitorAdapter) monitor).getTarget();
		}
		return new ProgressMonitorWrapper(monitor);
	}

}
