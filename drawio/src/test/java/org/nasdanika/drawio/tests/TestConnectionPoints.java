package org.nasdanika.drawio.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Model;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Point;
import org.nasdanika.drawio.PointList;
import org.nasdanika.drawio.Rectangle;
import org.nasdanika.drawio.Root;

public class TestConnectionPoints {
	
	@Test
	public void testC4ConnectionPoints() throws Exception {
		Document document = Document.load(getClass().getResource("connections.drawio"));
		Connection c4 = document
			.stream()
			.filter(Connection.class::isInstance)
			.map(Connection.class::cast)
			.filter(c -> "c4".equals(c.getId()))
			.findAny()
			.get();
		
		assertEquals(5, c4.getPoints().size());		
	}
	
	@Test
	public void testC1ConnectionPoints() throws Exception {
		Document document = Document.load(getClass().getResource("connections.drawio"));
		Connection c1 = document
			.stream()
			.filter(Connection.class::isInstance)
			.map(Connection.class::cast)
			.filter(c -> "c1".equals(c.getLabel()))
			.findAny()
			.get();
		
		assertEquals(1, c1.getPoints().size());		
	}	
	
	@Test
	public void testC2ConnectionPoints() throws Exception {
		Document document = Document.load(getClass().getResource("connections.drawio"));
		Connection c2 = document
			.stream()
			.filter(Connection.class::isInstance)
			.map(Connection.class::cast)
			.filter(c -> "c2".equals(c.getLabel()))
			.findAny()
			.get();
		
		assertEquals(0, c2.getPoints().size());		
	}	
	
	@Test
	public void testAddConnectionPoints() throws Exception {
		Document document = Document.load(getClass().getResource("connections.drawio"));
		Connection c3 = document
			.stream()
			.filter(Connection.class::isInstance)
			.map(Connection.class::cast)
			.filter(c -> "c3".equals(c.getLabel()))
			.findAny()
			.get();
		
		assertEquals(1, c3.getPoints().size());
		
		c3.getPoints().add(650, 1100);
		c3.getPoints().add(0, 630, 1080);
		
		Connection c2 = document
				.stream()
				.filter(Connection.class::isInstance)
				.map(Connection.class::cast)
				.filter(c -> "c2".equals(c.getLabel()))
				.findAny()
				.get();
			
			assertEquals(0, c2.getPoints().size());
			
		c2.getPoints().add(700, 900);
			
		Files.writeString(new File("target/connection-add-points.drawio").toPath(), document.save(null));
	}
		
	@Test
	public void testRemoveConnectionPoints() throws Exception {
		Document document = Document.load(getClass().getResource("connections.drawio"));
		Connection c4 = document
			.stream()
			.filter(Connection.class::isInstance)
			.map(Connection.class::cast)
			.filter(c -> "c4".equals(c.getId()))
			.findAny()
			.get();
		
		assertEquals(5, c4.getPoints().size());
		
		Point removedPoint = c4.getPoints().remove(1);
		
		assertEquals(4, c4.getPoints().size());
		assertEquals(230, removedPoint.getX());
		assertEquals(1260, removedPoint.getY());		
		
		removedPoint = c4.getPoints().remove(3);		
		assertEquals(40, removedPoint.getX());
		assertEquals(980, removedPoint.getY());		
		
		Files.writeString(new File("target/connection-remove-points.drawio").toPath(), document.save(null));
	}
	
	@Test
	public void testPointsLayout() throws Exception {
		Document document = Document.create(false, null);
		Page page = document.createPage();
		page.setName("My first new page with points");

		Model model = page.getModel();
		Root root = model.getRoot();
		List<Layer<?>> layers = root.getLayers();
		assertThat(layers).singleElement();

		// Add layer
		Layer<?> newLayer = root.createLayer();
		newLayer.setLabel("My new layer");

		// Add nodes
		Node source = newLayer.createNode();
		source.setLabel("My source node");
		Rectangle sourceGeometry = source.getGeometry();
		sourceGeometry.setWidth(120);
		sourceGeometry.setHeight(50);
		source.getTags().add(page.createTag("aws"));

		Node target = newLayer.createNode();
		target.setLabel("My target node");
		target.getGeometry().setBounds(60, 140, 100, 30);

		// Add connection
		Connection connection = newLayer.createConnection(source, target);
		connection.setLabel("My connection");
		Map<String, String> connectionStyle = connection.getStyle();
		connectionStyle.put("rounded", "1");
		connectionStyle.put("orthogonalLoop", "1");
		connectionStyle.put("jettySize", "auto");
		connectionStyle.put("html", "1");
		
		PointList points = connection.getPoints();
		points.add(80,110);
		points.add(310,110);
		points.add(310,260);
		points.add(125,260);

		org.nasdanika.drawio.Util.layout(root, 20);

		Files.writeString(new File("target/layout_points.drawio").toPath(), document.save(null));
	}
	
}
