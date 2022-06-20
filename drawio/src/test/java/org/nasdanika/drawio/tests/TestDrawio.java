package org.nasdanika.drawio.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import org.junit.Test;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Model;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Root;

public class TestDrawio {

	@Test
	public void testCompressed() throws Exception {
		Document document = Document.load(getClass().getResource("compressed.drawio"));
		assertThat(document.getElement().getTagName()).isEqualTo("mxfile");
		BiFunction<Element, Map<Element, String>, String> visitor = (element, childResults) -> {
			return element.getElement().getTagName() + "[" + element.getElement().getAttribute("id") + "] " + childResults;
		};
		String result = document.accept(visitor);
		System.out.println(result);
	}
	
	@Test
	public void testUncompressed() throws Exception {
		Document document = Document.load(getClass().getResource("uncompressed.drawio"));
		assertThat(document.getElement().getTagName()).isEqualTo("mxfile");
		assertThat(document.getPages().size()).isEqualTo(2);
		
		BiFunction<Element, Map<Element, String>, String> visitor = (element, childResults) -> {
			return element.getElement().getTagName() + "[" + element.getElement().getAttribute("id") + "] " + childResults;
		};
		String result = document.accept(visitor);
		System.out.println(result);
		
		for (Page page: document.getPages()) {
			System.out.println("Page: " + page.getName());
			for (Model model: page.getModels()) {
				Root root = model.getRoot();
				for (Layer layer: root.getLayers()) {
					System.out.println("\tLayer: " + layer.getLabel());
					for (ModelElement modelElement: layer.getElements()) {
						dump(modelElement, "\t\t");
					}
					
				}
			}
		}
	}
	
	private void dump(ModelElement modelElement, String indent) {
		System.out.print(indent);
		System.out.println(modelElement.getClass() + " " + modelElement.getLabel());
		if (modelElement instanceof Node) {
			Node node = (Node) modelElement;
			List<Connection> outboundConnections = node.getOutboundConnections();
			if (!outboundConnections.isEmpty()) {
				System.out.println(indent + "\tOutbound connections:");
				for (Connection connection: outboundConnections) {
					System.out.println(indent + "\t\t" + connection.getLabel() + " -> " + connection.getTarget().getLabel());
				}
			}
			List<Connection> inboundConnections = node.getInboundConnections();
			if (!inboundConnections.isEmpty()) {
				System.out.println(indent + "\tInbound connections:");
				for (Connection connection: inboundConnections) {
					System.out.println(indent + "\t\t" + connection.getLabel() + " <- " + connection.getSource().getLabel());
				}
			}
			List<ModelElement> children = node.getChildren();
			if (!children.isEmpty()) {
				System.out.println(indent + "\tChildren:");
				for (ModelElement child: children) {
					dump(child, indent + "\t\t");
				}
			}
		} else if (modelElement instanceof Connection) {
			Connection connection = (Connection) modelElement;
			System.out.println(indent + "\tSource: " + connection.getSource().getLabel());
			System.out.println(indent + "\tTarget: " + connection.getTarget().getLabel());			
		}
				
	}

}
