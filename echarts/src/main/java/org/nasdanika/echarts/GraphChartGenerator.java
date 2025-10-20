package org.nasdanika.echarts;

import java.util.Collection;
import java.util.Objects;
import java.util.Random;
import java.util.function.Supplier;

import org.icepear.echarts.charts.graph.GraphEdgeItem;
import org.icepear.echarts.charts.graph.GraphNodeItem;
import org.icepear.echarts.charts.graph.GraphSeries;
import org.jgrapht.alg.drawing.FRLayoutAlgorithm2D;
import org.jgrapht.alg.drawing.LayoutAlgorithm2D;
import org.jgrapht.alg.drawing.model.Box2D;
import org.jgrapht.alg.drawing.model.LayoutModel2D;
import org.jgrapht.alg.drawing.model.MapLayoutModel2D;
import org.jgrapht.alg.drawing.model.Point2D;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
/**
 * Generates a chart from graph elements - Element, Node, Connection
 */
public class GraphChartGenerator extends GraphSeriesChartGenerator {
	
	private Collection<? extends Element> elements;

	public GraphChartGenerator(Collection<? extends Element> elements) {
		this.elements = elements;
	}
	
	protected GraphNodeItem createGraphNodeItem(Node node) {
		GraphNodeItem ret = new GraphNodeItem();
		if (node instanceof Supplier) {
			ret.setName(String.valueOf(((Supplier<?>) node).get()));
		} else {
			ret.setName(node.toString());			
		}
		
		ret.setSymbolSize(20);
		
		return ret;
	}

	protected GraphEdgeItem createGraphEdgeItem(Connection connection) {
		GraphEdgeItem ret = new GraphEdgeItem();
		if (connection instanceof Supplier) {
			ret.setName(String.valueOf(((Supplier<?>) connection).get()));
		}
		return ret;
	}
	
	public record NodeRecord(Node node, GraphNodeItem graphNodeItem) {}	
	
	@Override
	protected GraphSeries generateGraphSeries() {
		Random random = new Random();
		GraphSeries graphSeries = super.generateGraphSeries();
		NodeRecord[] nodeRecords = elements
			.stream()
			.flatMap(Element::stream)
			.filter(Node.class::isInstance)
			.map(Node.class::cast)
			.distinct()
			.map(n -> {
				GraphNodeItem graphNodeItem = createGraphNodeItem(n);
				if (graphNodeItem == null) {
					return null;
				}
				// Random layout
				graphNodeItem.setX(random.nextInt(1000));
				graphNodeItem.setY(random.nextInt(1000));
				return new NodeRecord(n, graphNodeItem);
			})
			.filter(Objects::nonNull)
			.toArray(NodeRecord[]::new);
			
		// TODO - categories
		
		GraphNodeItem[] nodeItems = new GraphNodeItem[nodeRecords.length];
		for (int i = 0; i < nodeRecords.length; ++i) {
			nodeItems[i] = nodeRecords[i].graphNodeItem();
		}		
		if (nodeItems.length > 0) {			
			if (layout != null) {
				// Using JGraphT for force layout
				DefaultUndirectedGraph<NodeRecord, Connection> dGraph = new DefaultUndirectedGraph<>(Connection.class);
				
				// Populating
				for (NodeRecord nodeRecord: nodeRecords) {
					dGraph.addVertex(nodeRecord);
				}	
				
				for (NodeRecord nodeRecord: nodeRecords) {
					for (Connection connection: nodeRecord.node().getOutgoingConnections()) {
						for (NodeRecord onr: nodeRecords) {
							if (onr.node() == connection.getTarget() && dGraph.getEdge(onr, nodeRecord) == null) { // Not yet connected, connect
								dGraph.addEdge(nodeRecord, onr, connection);
								break;
							}
						}
					}
				}		
				
				layout.layout(dGraph, layoutModel);
				layoutModel.forEach(ne -> {
					NodeRecord nodeRecord = ne.getKey();
					Point2D point = ne.getValue();
					nodeRecord.graphNodeItem().setX(point.getX());
					nodeRecord.graphNodeItem().setY(point.getY());
				});
			}			
			
			graphSeries.setData(nodeItems);
			record ConnectionRecord(Connection connection, int source, int target) {}			
			
			GraphEdgeItem[] links = elements
				.stream()
				.flatMap(Element::stream)
				.filter(Connection.class::isInstance)
				.map(Connection.class::cast)
				.distinct()
				.map(c -> {
					for (int i = 0; i < nodeRecords.length; ++i) {
						if (c.getSource() == nodeRecords[i].node()) {
							return new ConnectionRecord(c, i, -1);
						}
					}		
					return null;
				})
				.filter(Objects::nonNull)				
				.map(cr -> {
					for (int i = 0; i < nodeRecords.length; ++i) {
						if (cr.connection().getTarget() == nodeRecords[i].node()) {
							return new ConnectionRecord(cr.connection(), cr.source(), i);
						}
					}							
					return null;
				})
				.filter(Objects::nonNull)
				.map(cr -> {
					GraphEdgeItem gei = createGraphEdgeItem(cr.connection());
					if (gei == null) {
						return null;
					}
					gei.setSource(cr.source());
					gei.setTarget(cr.target());
					return gei;
				})
				.filter(Objects::nonNull)
				.toArray(GraphEdgeItem[]::new);
			
			if (links != null) {			
				graphSeries.setLinks(links);
			}
		}
			
		return graphSeries;
	}
	
	protected LayoutAlgorithm2D<NodeRecord, Connection> layout;
	protected LayoutModel2D<NodeRecord> layoutModel;	
	
	/**
	 * Uses JGraphT {@link FRLayoutAlgorithm2D} to force layout the graph.
	 * @param graph
	 */
	public void setLayout(
			LayoutAlgorithm2D<NodeRecord, Connection> layout,
			LayoutModel2D<NodeRecord> layoutModel) {
		this.layout = layout;
		this.layoutModel = layoutModel;
	}	
	
	/**
	 * Uses JGraphT {@link FRLayoutAlgorithm2D} to force layout the graph.
	 * @param graph
	 */
	public void setForceLayout(double layoutWidth, double layoutHeight) {		
		layout = new FRLayoutAlgorithm2D<>();
		layoutModel = new MapLayoutModel2D<NodeRecord>(new Box2D(layoutWidth, layoutHeight));
	}
	
}
