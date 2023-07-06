package org.nasdanika.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;

import org.jgrapht.Graph;
import org.nasdanika.common.Util;

/**
 * Populates {@link Graph} by visiting {@link Element}s.
 * @author Pavel
 *
 * @param <V>
 * @param <E>
 */
public abstract class JGraphTAdapter<V,E> implements Consumer<Element> {
	
	protected Map<Node, V> node2vertexMap = Collections.synchronizedMap(new HashMap<>());
	
	private Graph<V, E> graph;

	private boolean groupConnections;

	private boolean createEdges;

	protected JGraphTAdapter(Graph<V,E> graph, boolean createEdges, boolean groupConnections) {
		this.graph = graph;
		this.groupConnections = groupConnections;
		this.createEdges = createEdges;
	}
	
	/**
	 * Operates on nodes and outgoing connections.
	 */
	@Override
	public void accept(Element element) {
		if (element instanceof Node) {
			Node node = (Node) element;
			V vertex;
			synchronized (node2vertexMap) {
				vertex = node2vertexMap.computeIfAbsent(node, this::createAndAddVertex);
			}
			if (vertex != null) {
				if (groupConnections) {
					for (Entry<Node, List<Connection>> group: Util.<Node,Connection>groupBy(node.getOutgoingConnections(), Connection::getTarget).entrySet()) {
						V targetVertex = node2vertexMap.computeIfAbsent(group.getKey(), this::createAndAddVertex);
						if (targetVertex != null) {
							if (createEdges) {
								E edge = createEdge(vertex, targetVertex, group.getValue());
								if (edge != null) {
									synchronized (graph) {
										graph.addEdge(vertex, targetVertex, edge);
									}
								}
							} else {
								synchronized (graph) {
									graph.addEdge(vertex, targetVertex);
								}
							}
						}
					}
				} else {
					for (Connection connection: node.getOutgoingConnections()) {
						Node connectionTarget = connection.getTarget();
						if (connectionTarget != null) {
							V targetVertex;
							synchronized(node2vertexMap) {
								targetVertex = node2vertexMap.computeIfAbsent(connectionTarget, this::createAndAddVertex);
							}
							if (targetVertex != null) {
								if (createEdges) {
									E edge = createEdge(vertex, targetVertex, Collections.singleton(connection));
									if (edge != null) {
										synchronized (graph) {
											graph.addEdge(vertex, targetVertex, edge);
										}
									}
								} else {
									synchronized (graph) {
										graph.addEdge(vertex, targetVertex);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	protected V createAndAddVertex(Node node) {
		V vertex = createVertex(node);
		if (vertex != null) {
			synchronized (graph) {
				graph.addVertex(vertex);
			}
		}
		return vertex;
	}
	
	/**
	 * Creates a vertex from {@link Node}. May return null for nodes which shall not be adapted to vertices.
	 * @param node
	 * @return
	 */
	protected abstract V createVertex(Node node);
	
	/**
	 * Crates an edge from a collection of {@link Connection}s. May return null for connections to be ignored.
	 * @param connections Connections. Singleton if groupConnections is false.
	 * @return
	 */
	protected E createEdge(V source, V target, Collection<Connection> connections) {
		throw new UnsupportedOperationException("Override for createEdges = false");
	}

}
