package org.nasdanika.drawio.comparators;

import java.util.Comparator;

import org.nasdanika.drawio.Node;

/**
 * Compares {@link Node}s by rows/columns. Not implemented yet.
 * @author Pavel
 *
 */
public class CartesianNodeComparator implements Comparator<Node> {
	
	// Modes - right-down, right-up, left-down, left-up, down-right, ... - 3 boolean flags, 8 combinations. enum?

	@Override
	public int compare(Node o1, Node o2) {
		// TODO Auto-generated method stub
		return 0;
	}


}
