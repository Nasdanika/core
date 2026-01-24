package org.nasdanika.drawio.comparators;

import java.util.Comparator;
import java.util.Objects;
import java.util.Stack;
import java.util.function.Predicate;

import org.nasdanika.drawio.Connectable;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ConnectionPoint;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;

/**
 * @author Pavel
 *
 */
public class FlowComparator implements Comparator<ModelElement<?>> {
	
	private Predicate<? super Connection> connectionPredicate;

	public FlowComparator() {
		this(null);
	}
	
	public FlowComparator(Predicate<? super Connection> connectionPredicate) {
		this.connectionPredicate = connectionPredicate;
	}
	
	@Override
	public int compare(ModelElement<?> o1, ModelElement<?> o2) {
		if (Objects.equals(o1, o2)) {
			return 0;
		}
		
		if (o1 == null) {
			return 1;
		}
		
		if (o2 == null) {
			return -1;
		}
		
		Page p1 = o1.getModel().getPage();
		Page p2 = o2.getModel().getPage();
		if (Objects.equals(p1, p2)) {
			Integer r1 = traverse(o1, o2, new Stack<>());
			Integer r2 = traverse(o2, o1, new Stack<>());
			if (r1 == null) {
				return r2 == null ? 0 : 1;
			}
			if (r2 == null) {
				return -1;
			}
			return r1 - r2;
		}		
		return 0;
	}
		
	private static Node getConnectableNode(Connectable connectable) {
		if (connectable instanceof ConnectionPoint) {
			return ((ConnectionPoint) connectable).getNode();
		}
		return (Node) connectable;
	}	
	
	/**
	 * Returns number of connections traversed from o1 to o2. Integer.MAX_VALUE if not reacheable. 0 if o1 is the same as o2 
	 * @param o1
	 * @param o2
	 */
	private Integer traverse(ModelElement<?> source, ModelElement<?> target, Stack<ModelElement<?>> tracker) {
		if (Objects.equals(source, target)) {
			return 0;
		}
		if (source == null || target == null) {
			return null;
		}
		Integer result = null;
		if (!tracker.contains(source)) {
			tracker.push(source);
			if (source instanceof Connection) {				
				Connection sc = (Connection) source;
				if (connectionPredicate == null || connectionPredicate.test(sc)) {				
					Node st = getConnectableNode(sc.getTarget());
					if (st != null) {
						Integer targetTraverse = traverse(st, target, tracker);
						result = targetTraverse == null ? null : targetTraverse + 1;
					}
				}
			} else if (source instanceof Node) {				
				for (Connection oc: ((Node) source).getAllOutgoingConnections()) {
					Integer ocTraverse = traverse(oc, target, tracker);
					if (ocTraverse != null && (result == null || result > ocTraverse + 1)) {
						result = ocTraverse + 1;
						if (ocTraverse == 0) {
							break;
						}
					}
				}			
			}
			tracker.pop();
		}
		return result;
	}	

}
