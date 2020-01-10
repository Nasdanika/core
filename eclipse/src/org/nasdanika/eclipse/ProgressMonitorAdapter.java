package org.nasdanika.eclipse;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;

/**
 * Adapts Eclipse {@link IProgressMonitor} to Nasdanika {@link ProgressMonitor}
 * @author Pavel
 *
 */
public class ProgressMonitorAdapter implements ProgressMonitor {

	private SubMonitor target;
	private double scale;

	/**
	 * 
	 * @param target
	 * @param scale size/work is multiplied by scale and then cast to int. Scale can be use if either double numbers are too large (e.g. long files) or too small (e.g. whole work is 1 so steps are fractions of 1) to be represented by int. 
	 */
	public ProgressMonitorAdapter(IProgressMonitor target, double scale) {
		this.target = SubMonitor.convert(target);
		this.scale = scale;
	}

	@Override
	public void close() {
		target.done();		
	}

	@Override
	public boolean isCancelled() {
		return target.isCanceled();
	}

	@Override
	public ProgressMonitor split(String taskName, double size, Object... data) {
		SubMonitor subMonitor = target.split((int) (size * scale));
		subMonitor.subTask(taskName);
		return new ProgressMonitorAdapter(subMonitor, scale);
	}

	@Override
	public void worked(Status status, double work, String progressMessage, Object... data) {
		if (progressMessage != null) {
			target.subTask(progressMessage);
		}
		target.worked((int) (work * scale));
	}

	@Override
	public ProgressMonitorAdapter setWorkRemaining(double size) {
		target.setWorkRemaining((int) (size * scale));
		return this;
	}
	
	/**
	 * Provides access to target.
	 * @return
	 */
	public SubMonitor getTarget() {
		return target;
	}

}
