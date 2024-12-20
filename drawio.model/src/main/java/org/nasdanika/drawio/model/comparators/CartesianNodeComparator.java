package org.nasdanika.drawio.model.comparators;

import java.util.Comparator;
import java.util.Objects;

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
		rightUp,
		leftDown,
		leftUp,
		downRight,
		downLeft,
		upRight,
		upLeft
	}
	
	private Direction direction;
	private Comparator<? super Node> fallback;

	public CartesianNodeComparator(Direction direction, Comparator<? super Node> fallback) {
		this.direction = direction;
		this.fallback = fallback;
	}
	
	// Modes - right-down, right-up, left-down, left-up, down-right, ... - 3 boolean flags, 8 combinations. enum?

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
		
		switch (direction) {
		case downLeft: {
			// Vertical first - to the left is smaller than to the right
			int vcmp = compareVertical(o1, o2);
			if (vcmp != 0) {
				return vcmp;
			}
			// Horizontal 
			int hcmp = compareHorizontal(o1, o2);
			return hcmp == 0 ? fallback.compare(o1, o2) : -hcmp;
		}
		case downRight: {
			// Vertical first - to the left is smaller than to the right
			int vcmp = compareVertical(o1, o2);
			if (vcmp != 0) {
				return vcmp;
			}
			// Horizontal 
			int hcmp = compareHorizontal(o1, o2);
			return hcmp == 0 ? fallback.compare(o1, o2) : hcmp;
		}
		case leftDown: {
			// Horizontal first - to the left is smaller than to the right
			int hcmp = compareHorizontal(o1, o2);
			if (hcmp != 0) {
				return -hcmp;
			}
			// Vertical 
			int vcmp = compareVertical(o1, o2);
			return vcmp == 0 ? fallback.compare(o1, o2) : vcmp;
		}
		case leftUp: {
			// Horizontal first - to the left is smaller than to the right
			int hcmp = compareHorizontal(o1, o2);
			if (hcmp != 0) {
				return -hcmp;
			}
			// Vertical 
			int vcmp = compareVertical(o1, o2);
			return vcmp == 0 ? fallback.compare(o1, o2) : -vcmp;
		}
		case rightUp: {
			// Horizontal first - to the left is smaller than to the right
			int hcmp = compareHorizontal(o1, o2);
			if (hcmp != 0) {
				return hcmp;
			}
			// Vertical 
			int vcmp = compareVertical(o1, o2);
			return vcmp == 0 ? fallback.compare(o1, o2) : -vcmp;
		}
		case rightDown: {
			// Horizontal first - to the left is smaller than to the right
			int hcmp = compareHorizontal(o1, o2);
			if (hcmp != 0) {
				return hcmp;
			}
			// Vertical 
			int vcmp = compareVertical(o1, o2);
			return vcmp == 0 ? fallback.compare(o1, o2) : vcmp;
		}
		case upLeft: {
			// Vertical first - to the left is smaller than to the right
			int vcmp = compareVertical(o1, o2);
			if (vcmp != 0) {
				return -vcmp;
			}
			// Horizontal 
			int hcmp = compareHorizontal(o1, o2);
			return hcmp == 0 ? fallback.compare(o1, o2) : -hcmp;
		}
		case upRight: {
			// Vertical first - to the left is smaller than to the right
			int vcmp = compareVertical(o1, o2);
			if (vcmp != 0) {
				return -vcmp;
			}
			// Horizontal 
			int hcmp = compareHorizontal(o1, o2);
			return hcmp == 0 ? fallback.compare(o1, o2) : hcmp;
		}
		default:
			break;
		
		}
		return fallback.compare(o1, o2);
	}
	
	/**
	 * Returns true if the second node is below the first node 
	 * @param o1
	 * @param o2
	 */
	private static boolean below(Node o1, Node o2) {
//		System.out.println("Below: " + Jsoup.parse(o1.getLabel()).text() + " - " + Jsoup.parse(o2.getLabel()).text());
		
		Geometry g1 = AngularNodeComparator.getAbsoluteGeometry(o1);
		return g1.getY() + g1.getHeight() < AngularNodeComparator.getAbsoluteGeometry(o2).getY();		
	}	
	
	/**
	 * Returns true if the second node is below the first node 
	 * @param o1
	 * @param o2
	 */
	private static boolean after(Node o1, Node o2) {
//		System.out.println("After " + Jsoup.parse(o1.getLabel()).text() + " - " + Jsoup.parse(o2.getLabel()).text());
		
		Geometry g1 = AngularNodeComparator.getAbsoluteGeometry(o1);
		return g1.getX() + g1.getWidth() < AngularNodeComparator.getAbsoluteGeometry(o2).getX();		
	}
	
	protected int compareVertical(Node o1, Node o2) {
		if (below(o1, o2)) {
			return -1;
		}
		if (below(o2, o1)) {
			return 1;
		}
		return 0;
	}
	
	protected int compareHorizontal(Node o1, Node o2) {
		if (after(o1, o2)) {
			return -1;
		}
		if (after(o2, o1)) {
			return 1;
		}
		return 0;
	}

}
