package org.nasdanika.echarts.tests;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Supplier;

import org.icepear.echarts.Option;
import org.icepear.echarts.charts.line.LineEmphasis;
import org.icepear.echarts.components.dataZoom.DataZoom;
import org.icepear.echarts.components.marker.MarkArea;
import org.icepear.echarts.components.marker.MarkArea2DDataItem;
import org.icepear.echarts.components.marker.MarkArea2DDataItemDim;
import org.icepear.echarts.origin.component.marker.MarkAreaDataItemOption;
import org.junit.jupiter.api.Test;
import org.nasdanika.echarts.GraphChartGenerator;
import org.nasdanika.echarts.LineSeriesChart;
import org.nasdanika.echarts.LineSeriesChart.LineSeriesBuilder;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.ContentProvider;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.Node;
import org.nasdanika.graph.ObjectNode;
import org.nasdanika.graph.SimpleConnection;
import org.nasdanika.graph.UnresolvedReferenceNode;


public class TestECharts {
		
	@Test
	public void testLineSeriesChart() throws IOException {
		LineSeriesChart<Map.Entry<String,Number>, String, Number> lineSeriesChart = new LineSeriesChart<>((a,b) -> a.compareTo(b), Function.identity());		
		
		LineEmphasis emphasis = new LineEmphasis();
		emphasis.setFocus("series");
		
		LineSeriesBuilder<Entry<String, Number>> builder = lineSeriesChart.createSeries("Test", Map.Entry::getKey, Map.Entry::getValue);
		builder.addElement(Map.entry("Monday", 1));
		builder.addElement(Map.entry("Tuesday", 2));
		builder.addElement(Map.entry("Wednesday", 3));
		builder.addElement(Map.entry("Thursday", 8));
		builder.addElement(Map.entry("Friday", 11));
		builder.lineSeries().setEmphasis(emphasis);
		
		MarkArea2DDataItemDim startPoint = new MarkArea2DDataItemDim();
		startPoint.setName("Area");
		startPoint.setXAxis("Monday");
		
		MarkArea2DDataItem markAreaItem = new MarkArea2DDataItem();
		markAreaItem.setStartPoint(startPoint);

		MarkArea2DDataItemDim endPoint = new MarkArea2DDataItemDim();
		endPoint.setXAxis("Tuesday");
		markAreaItem.setEndPoint(endPoint);
		
		MarkArea markArea = new MarkArea();
		markArea.setData(new MarkAreaDataItemOption[] { markAreaItem });
		
		builder.lineSeries().setMarkArea(markArea);
		
		LineSeriesBuilder<Entry<String, Number>> builder2 = lineSeriesChart.createSeries("Test 2", Map.Entry::getKey, Map.Entry::getValue);
		builder2.addElement(Map.entry("Monday", 2));
		builder2.addElement(Map.entry("Tuesday", 3));
		builder2.addElement(Map.entry("Wednesday", 5));
		builder2.addElement(Map.entry("Thursday", 6));
		builder2.addElement(Map.entry("Friday", 7));
		builder2.lineSeries().setEmphasis(emphasis);		
		
		lineSeriesChart.write(
				new File("target/line.html"), 
				"Test", 
				false,
				chart -> {
					Option option = chart.getOption();
					DataZoom dataZoom = new DataZoom();
					dataZoom.setType("inside");
					option.setDataZoom(dataZoom);
				},
				null);
	}
		
	@Test
	public void testGraphChart() throws IOException {
		ObjectNode<String> alice = new ObjectNode<>("Alice"); 
		ObjectNode<String> bob = new ObjectNode<>("Bob"); 
		new SimpleConnection(alice, bob, false);
		
		GraphChartGenerator generator = new GraphChartGenerator(List.of(alice, bob));
		generator.setForceLayout(500, 500);
		generator.write(
				new File("target/graph.html"), 
				"Test Graph", 
				false);
	}	

	@Test
	public void testAliceBobConnectionGraphChart() throws IOException {
		ContentProvider<Object,Object> cp = ContentProvider.fromYaml(
				"""
				source:
				  value: Alice
				target:
				  value: Bob
				""");

		Connection connection = (Connection) Element.from(cp);
		
		Collection<Element> elements = new ArrayList<>();
		connection.traverse(elements::add);
		
		GraphChartGenerator generator = new GraphChartGenerator(elements);
		generator.setForceLayout(500, 500);
		generator.write(
				new File("target/alice-bob-connectio-graph.html"), 
				"Alice -&gt; Bob", 
				true);
	}	
	
	@Test
	public void testAliceBobGraphChart() throws IOException {
		ContentProvider<Object,Object> cp = ContentProvider.fromYaml(
				"""
				value: Alice
				outgoingConnections:
				  - value: AliceToBob
				    target:
				      value: Bob
				""");

		Node node = (Node) Element.from(cp);
		
		Connection connection = node.getOutgoingConnections().iterator().next();
		GraphChartGenerator generator = new GraphChartGenerator(List.of(node, connection, connection.getTarget()));
		generator.setForceLayout(500, 500);
		generator.write(
				new File("target/alice-bob-graph.html"), 
				"Alice -&gt; Bob", 
				false);
	}
		
	@Test
	public void testAliceBobRefGraphChart() throws IOException {
		ContentProvider<Object,Object> cp = ContentProvider.fromYaml(
				"""
				- value: Alice
				  outgoingConnections:
				    - target: bobRef
				      value: AliceToBob
				- value: Bob
				  refId: bobRef    
				""");

		Element graph = Element.from(cp);
		
		GraphChartGenerator generator = new GraphChartGenerator(graph.stream().toList());
		generator.setForceLayout(500, 500);
		generator.write(
				new File("target/alice-bob-graph.html"), 
				"Alice -&gt; Bob", 
				false);
	}	
		
	@Test
	public void testAliceBobResolverGraphChart() throws IOException {
		ContentProvider<Object,Object> cp = ContentProvider.fromYaml(
				"""
				- value: Alice
				  outgoingConnections:
				    - target: Bob
				      value: AliceToBob
				- value: Bob
				  node: true
				""");
	
		Element graph = Element.from(cp, (ref, registry) -> {
			for (Entry<ContentProvider<Object, Object>, Element> re: registry.entrySet()) {
				Element element = re.getValue();
				if (element instanceof Supplier) {
					Object value = ((Supplier<?>) element).get();
					if (value.equals(ref)) {
						return (Node) element;
					}
				}
			}
			
			return new UnresolvedReferenceNode<>(ref);
		});
		
		GraphChartGenerator generator = new GraphChartGenerator(graph.stream().toList());
		generator.setForceLayout(500, 500);
		generator.write(
				new File("target/alice-bob-resolver-graph.html"), 
				"Alice -&gt; Bob", 
				false);
	}	
	
}
