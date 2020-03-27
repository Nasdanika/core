package org.nasdanika.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Base class for dependency tracers. 
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public abstract class DependencyTracer<T> {

	/**
	 * Traces dependencies from a set of starting objects
	 * @param from Starting object
	 * @param depth Trace depth. 0 - just the starting objects, -1 - infinite.
	 * @param chain Combine results with the chain.
	 * @return
	 */
	public Set<T> trace(Set<T> from, int depth) {
		Map<T, Integer> distanceTracker = createDepthTracker();
		for (T f: from) {
			trace(f, depth, distanceTracker);
		}
		return distanceTracker.keySet();
		
	}
	
	public Set<T> trace(T from, int depth) {
		return trace(Collections.singleton(from), depth);
	}
	
	/**
	 * Creates depth tracker.
	 * @return HashMap, subclasses may override to return, for example, TreeMap.
	 */
	protected Map<T,Integer> createDepthTracker() {
		return new HashMap<T,Integer>();
	}
	
	private void trace(T from, int depth, Map<T, Integer> depthTracker) {
		Integer objDepth = depthTracker.get(from);
		if (objDepth == null || objDepth < depth) {
			depthTracker.put(from, depth);
			if (depth != 0) {
				for (T dep: getDependencies(from)) {
					trace(dep, depth == -1 ? depth : depth - 1, depthTracker);
				}
			}
		}
	}			
	
	/**
	 * @param obj
	 * @return Dependencies to trace.
	 */
	protected abstract Iterable<T> getDependencies(T obj);

}
