package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Progress entry storing progress information for subsequent processing.
 * @author Pavel
 *
 */
public class ProgressEntry extends ProgressRecorder {

	/**
	 * 
	 * @param totalWork
	 */
	ProgressEntry(ProgressRecorder parent, String name, double totalWork, List<Object> data) {
		super(parent);
		this.name = name;		
		this.totalWork = totalWork;
		if (data != null) {
			this.data.addAll(data);
		}
		this.start = System.currentTimeMillis();
	}
	/**
	 * 
	 * @param totalWork
	 */
	public ProgressEntry(String name, long totalWork, Object... data) {
		this(null, name, totalWork, Arrays.asList(data));
	}
	
	private String name;
	
	public String getName() {
		return name;
	}
	
	private List<Object> data = new ArrayList<>();
	
	public List<Object> getData() {
		return data;
	}
	
	private double totalWork;
			
	/**
	 * @return Entry size passed to split() or constructor, not total work reported by worked() calls.
	 */
	public double getTotalWork() {
		return totalWork;
	}
	
	private long start;
	
	private long finish;
	
	/**
	 * @return Time when this entry was created.
	 */
	public long getStart() {
		return start;
	}
	
	/**
	 * @return Time when this entry was closed.
	 */
	public long getFinish() {
		return finish;
	}
	
	/**
	 * Outputs to JSON.
	 * @return
	 */
	public JSONObject toJSON() {
		JSONObject ret = super.toJSON();
		ret.put("name", getName());
		ret.put("totalWork", totalWork);
		if (start > 0) {
			ret.put("start", start);
		}
		if (finish > 0) {
			ret.put("finish", finish);
		}
		
		if (getData() != null && getData().size() > 0) {
			JSONArray jd = new JSONArray();
			for (Object d: getData()) {
				jd.put(detailToJSON(d));
			}
			ret.put("data", jd); 					
		}						
		
		return ret;
	}
	
	@Override
	public String toString() {
		return toJSON().toString(4);
	}
	
	/**
	 * Outputs to Map, which can be then used to output to YAML.
	 * @return
	 */
	public Map<String, Object> toMap() {
		Map<String,Object> ret = super.toMap();
		ret.put("name", getName());
		ret.put("totalWork", totalWork);
		if (start > 0) {
			ret.put("start", start);
		}
		if (finish > 0) {
			ret.put("finish", finish);
		}
		
		ret.put("data", getData()); 					
		
		return ret;
	}

}
