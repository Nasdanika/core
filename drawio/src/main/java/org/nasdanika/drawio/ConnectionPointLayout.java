package org.nasdanika.drawio;

import org.jgrapht.Graph;
import org.jgrapht.alg.drawing.LayoutAlgorithm2D;
import org.jgrapht.alg.drawing.model.LayoutModel2D;

public class ConnectionPointLayout implements LayoutAlgorithm2D<Node, Connection> {

	@Override
	public void layout(Graph<Node, Connection> graph, LayoutModel2D<Node> model) {

		// TODO - send messages along connections to calculate node relative positions 
		// Pair-wise positioning - double loop to calculate relative right/left and up/down positions for each pair of nodes
		
	}

}
