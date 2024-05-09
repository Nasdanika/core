package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Progress recorder storing progress information for subsequent processing or replaying.
 * @author Pavel
 *
 */
public class ProgressRecorder implements ProgressMonitor {
	
	private ProgressRecorder parent;

	/**
	 * 
	 * @param totalWork
	 */
	protected ProgressRecorder(ProgressRecorder parent) {
		this.parent = parent;
	}
	/**
	 * 
	 * @param totalWork
	 */
	public ProgressRecorder() {
		this(null);
	}

	/**
	 * Supplier step reported by worked() methods.
	 * @author Pavel
	 *
	 */
	public interface Step extends Diagnostic {
		
		long getTime();
		
		double getWorked();
		
	}
	
	private boolean closed;
	
	private List<Object> entries = new ArrayList<>();

	private boolean cancelled;
	
	protected void cancel() {
		cancelled = true;
	}
	
	/**
	 * @return Steps and child entries created by split().
	 */
	public List<Object> getEntries() {
		return entries;
	}
	
	/**
	 * @return Child entries created by split().
	 */
	public List<ProgressEntry> getChildren() {
		return entries.stream().filter(e -> e instanceof ProgressEntry).map(e -> (ProgressEntry) e).toList();
	}	
	
	public List<Step> getSteps() {
		return entries.stream().filter(e -> e instanceof Step).map(e -> (Step) e).toList();
	}
	
	@Override
	public void close() {
		closed = true;
	}

	@Override
	public boolean isCancelled() {
		return parent == null ? cancelled : parent.isCancelled();
	}

	@Override
	public ProgressMonitor split(String taskName, double size, Object... data) {
		if (isCancelled()) {
			throw new CancellationException();
		}
		if (closed) {
			throw new IllegalStateException("Monitor is closed: "+hashCode());
		}
		ProgressRecorder child = new ProgressEntry(this, taskName, size, Arrays.asList(data));
		entries.add(child);
		return child;
	}

	@Override
	public void worked(Status status, double work, String progressMessage, Object... data) {
		if (closed) {
//			throw new IllegalStateException("Monitor is closed");
			progressMessage += " (Warning - Reporting progress to a closed monitor)";
		}
		String theProgressMessage = progressMessage;
		
		class BasicStep extends BasicDiagnostic implements Step {

			public BasicStep() {
				super(status, theProgressMessage, data);
			}

			long now = System.currentTimeMillis();			

			@Override
			public long getTime() {
				return now;
			}

			@Override
			public double getWorked() {
				return work;
			}
			
		}
		
		entries.add(new BasicStep());
		if (status == Status.CANCEL) {
			if (parent == null) {
				cancelled = true;
			} else {
				parent.cancel();
			}
		}
	}
	
	/**
	 * Outputs to JSON.
	 * @return
	 */
	public JSONObject toJSON(int depth, boolean withData) {
		JSONObject ret = new JSONObject();

		if (depth != 1) {
			List<ProgressEntry> children = getChildren();
			if (!children.isEmpty()) {
				JSONArray jChildren = new JSONArray();
				int cDepth = depth - 1;
				children.forEach(child -> jChildren.put(child.toJSON(cDepth, withData)));
				ret.put("children", jChildren);
			}
		}
		
		List<Step> steps = getSteps();
		if (!steps.isEmpty()) {
			JSONArray jSteps = new JSONArray();
			steps.forEach(we -> {
				JSONObject jwe = new JSONObject();
				jwe.put("status", we.getStatus().name());
				jwe.put("worked", we.getWorked());
				jwe.put("time", we.getTime());
				jwe.put("message", we.getMessage());
				if (withData && we.getData().size() > 0) {
					JSONArray jd = new JSONArray();
					for (Object d: we.getData()) {
						jd.put(detailToJSON(d));
					}
					jwe.put("data", jd); 					
				}
				jSteps.put(jwe);
			});
			ret.put("steps", jSteps);		
		}
		
		return ret;
	}
	
	/**
	 * Converts detail to JSON. This implementation delegates to the parent or calls {@link DefaultConverter#}.INSTANCE.convert
	 * if parent is null.
	 * @param detail
	 * @return
	 */
	protected Object detailToJSON(Object detail) {
		return parent == null ? String.valueOf(detail) /* DefaultConverter.INSTANCE.convert(detail, JSONObject.class) */ : parent.detailToJSON(detail);
	}

	@Override
	public String toString() {
		return toJSON(0, true).toString(4);
	}
	
	/**
	 * Outputs to Map, which can be then used to output to YAML.
	 * @param depth output depth. 0 means infinite.
	 * @return 
	 */
	public Map<String, Object> toMap(int depth, boolean withData) {
		Map<String,Object> ret = new LinkedHashMap<>();

		if (depth != 1) {
			List<ProgressEntry> children = getChildren();
			if (!children.isEmpty()) {
				List<Map<String, Object>> mChildren = new ArrayList<>();
				int cDepth = depth - 1;
				children.forEach(child -> mChildren.add(child.toMap(cDepth, withData)));
				ret.put("children", mChildren);
			}
		}
		
		List<Step> steps = getSteps();
		if (!steps.isEmpty()) {
			List<Map<String, Object>> mSteps = new ArrayList<>();
			steps.forEach(we -> {
				Map<String, Object> mwe = new LinkedHashMap<>();
				mwe.put("status", we.getStatus().name());
				mwe.put("worked", we.getWorked());
				mwe.put("time", we.getTime());
				mwe.put("message", we.getMessage());
				if (withData && we.getData().size() > 0) {
					mwe.put("data", we.getData());					
				}
			});
			ret.put("steps", mSteps);		
		}			
		
		return ret;
	}
	
	/**
	 * Replays recorded progress to the target monitor.  
	 * @param target
	 */
	public void replay(ProgressMonitor monitor) {
		for (Object entry: entries) {
			if (entry instanceof Step) {
				Step step = (Step) entry;
				monitor.worked(step.getStatus(),  step.getWorked(),  step.getMessage(), step.getData());
			} else {
				@SuppressWarnings("resource")
				ProgressEntry child = (ProgressEntry) entry;
				try (ProgressMonitor subMonitor = monitor.split(child.getName(), child.getTotalWork(), child.getData())) {
					child.replay(subMonitor);
				}
			}
		}
	}
	@Override
	public ProgressRecorder setWorkRemaining(double size) {
		// TODO - record too?
		return this;
	}

}
