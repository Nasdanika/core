package org.nasdanika.drawio.model.comparators;

import java.util.Comparator;

import org.nasdanika.drawio.model.Geometry;
import org.nasdanika.drawio.model.Node;

/**
 * Compares {@link Node}s by rows/columns. 
 * @author Pavel
 *
 */
public class CartesianNodeComparator implements Comparator<Node> {
	
	public enum Direction {
		rightDown,
		reightUp,
		leftDown,
		leftUp,
		downRight,
		downLeft,
		upRight,
		upLeft
	}
	
	private Direction direction;

	public CartesianNodeComparator(Direction direction) {
		this.direction = direction;
	}
	
	// Modes - right-down, right-up, left-down, left-up, down-right, ... - 3 boolean flags, 8 combinations. enum?

	@Override
	public int compare(Node o1, Node o2) {
		switch (direction) {
		case downLeft:
			if (below(o1, o2)) {
				return -1;
			}
			if (below(o2, o1)) {
				return 1;
			}
			if (after(o1, o2)) {
				return 1;
			}
			if (after(o2, o1)) {
				return -1;
			}
			break;
		case downRight:
			if (below(o1, o2)) {
				return -1;
			}
			if (below(o2, o1)) {
				return 1;
			}
			if (after(o1, o2)) {
				return -1;
			}
			if (after(o2, o1)) {
				return 1;
			}
			break;
		case leftDown:
			if (after(o1, o2)) {
				return 1;
			}
			if (after(o2, o1)) {
				return -1;
			}
			if (below(o1, o2)) {
				return -1;
			}
			if (below(o2, o1)) {
				return 1;
			}
			break;
		case leftUp:
			if (after(o1, o2)) {
				return 1;
			}
			if (after(o2, o1)) {
				return -1;
			}
			if (below(o1, o2)) {
				return -1;
			}
			if (below(o2, o1)) {
				return 1;
			}
			break;
		case reightUp:
			if (after(o1, o2)) {
				return -11;
			}
			if (after(o2, o1)) {
				return 1;
			}
			if (below(o1, o2)) {
				return -1;
			}
			if (below(o2, o1)) {
				return 1;
			}
			break;
		case rightDown:
			if (after(o1, o2)) {
				return -1;
			}
			if (after(o2, o1)) {
				return 1;
			}
			if (below(o1, o2)) {
				return -1;
			}
			if (below(o2, o1)) {
				return 1;
			}
			break;
		case upLeft:
			if (below(o1, o2)) {
				return 1;
			}
			if (below(o2, o1)) {
				return -1;
			}
			if (after(o1, o2)) {
				return 1;
			}
			if (after(o2, o1)) {
				return -1;
			}
			break;
		case upRight:
			if (below(o1, o2)) {
				return 1;
			}
			if (below(o2, o1)) {
				return -1;
			}
			if (after(o1, o2)) {
				return -1;
			}
			if (after(o2, o1)) {
				return 1;
			}
			break;
		default:
			break;
		
		}
		return o1.hashCode() - o2.hashCode();
	}
	
	/**
	 * Returns true if the second node is below the first node 
	 * @param o1
	 * @param o2
	 */
	private static boolean below(Node o1, Node o2) {
		Geometry g1 = o1.getGeometry();
		return g1.getY() + g1.getHeight() < o2.getGeometry().getY();		
	}
	
	
	/**
	 * Returns true if the second node is below the first node 
	 * @param o1
	 * @param o2
	 */
	private static boolean after(Node o1, Node o2) {
		Geometry g1 = o1.getGeometry();
		return g1.getX() + g1.getWidth() < o2.getGeometry().getX();		
	}


}
