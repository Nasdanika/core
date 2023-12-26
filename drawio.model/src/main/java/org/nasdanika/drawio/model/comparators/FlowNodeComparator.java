package org.nasdanika.drawio.model.comparators;

import java.util.Comparator;
import java.util.Objects;
import java.util.Stack;
import java.util.function.Predicate;

import org.nasdanika.drawio.model.Connection;
import org.nasdanika.drawio.model.Node;

/**
 * @author Pavel
 *
 */
public class FlowNodeComparator implements Comparator<Node> {
	
	private Comparator<? super Node> fallback;
	private Predicate<? super Connection> connectionPredicate;


	public FlowNodeComparator(Predicate<? super Connection> connectionPredicate, Comparator<? super Node> fallback) {
		this.fallback = fallback;
		this.connectionPredicate = connectionPredicate;
	}
	
	@Override
	public int compare(Node o1, Node o2) {
		if (Objects.equals(o1, o2)) {
			return 0;
		}
		
		if (o1 == null) {
			return 1;
		}
		
		if (o2 == null) {
			return -1;
		}
		
		if (!Objects.equals(o1.getPage(), o2.getPage())) {
			throw new IllegalArgumentException("Nodes belong to different pages");
		}		
		
		int r1 = traverse(o1, o2, new Stack<>());
		int r2 = traverse(o2, o1, new Stack<>());
		
		if (r1 == r2) {
			return fallback.compare(o1, o2);
		}
		
		return r1 - r2;
	}
	
	/**
	 * Returns number of connections traversed from o1 to o2. Integer.MAX_VALUE if not reacheable. 0 if o1 is the same as o2 
	 * @param o1
	 * @param o2
	 */
	private int traverse(Node source, Node target, Stack<Node> tracker) {
		if (Objects.equals(source, target)) {
			return 0;
		}
		if (source == null || target == null) {
			return Integer.MAX_VALUE;
		}
		int result = Integer.MAX_VALUE;
		if (!tracker.contains(source)) {
			tracker.push(source);
			for (Connection oc: source.getOutgoing()) {
				if (connectionPredicate == null || connectionPredicate.test(oc)) {
					Node cTarget = oc.getTarget();
					if (Objects.equals(cTarget, target)) {
						result = 1;
						break;
					}
					int tr = traverse(cTarget, target, tracker);
					if (tr != Integer.MAX_VALUE && tr + 1 < result) {
						result = tr + 1;
					}
				}
			}			
			tracker.pop();
		}
		return result;
	}	

}
