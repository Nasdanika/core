package org.nasdanika.drawio.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Test;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;

public class TestLinkTargets {
	
	// --- Page links ---
		
	@Test 
	public void testInternalPageLink() throws Exception {
		Document document = Document.load(getClass().getResource("links.drawio"));
		Optional<Node> linkToPage2 = document
				.stream(null).
				filter(Node.class::isInstance)
				.map(Node.class::cast)
				.filter(n -> "Link to Page 2".equals(n.getLabel()))
				.findFirst();
		
		assertTrue(linkToPage2.isPresent());		
		Page linkedPage = (Page) linkToPage2.get().getLinkTarget();
		assertThat(linkedPage).isNotNull();
		assertThat(linkedPage.getName()).isEqualTo("Page 2");
		assertTrue(document == linkedPage.getDocument());
	}
	
	@Test 
	public void testExternalPageLink() throws Exception {
		Document document = Document.load(getClass().getResource("links.drawio"));
		Optional<Node> linkToPage = document
				.stream(null)
				.filter(Node.class::isInstance)
				.map(Node.class::cast)
				.filter(n -> "Link to compressed second page".equals(n.getLabel()))
				.findFirst();
		
		assertTrue(linkToPage.isPresent());		
		Page linkedPage = (Page) linkToPage.get().getLinkTarget();
		assertThat(linkedPage).isNotNull();
		assertThat(linkedPage.getName()).isEqualTo("Page 2");
		URI linkedDocURI = linkedPage.getDocument().getURI();
		assertEquals("compressed.drawio", linkedDocURI.lastSegment());
	}
	
	@Test 
	public void testDocumentFirstPageLink() throws Exception {
		Document document = Document.load(getClass().getResource("links.drawio"));
		Optional<Node> linkToPage = document
				.stream(null)
				.filter(Node.class::isInstance)
				.map(Node.class::cast)
				.filter(n -> "Link to compressed first page".equals(n.getLabel()))
				.findFirst();
		assertTrue(linkToPage.isPresent());		
		Page linkedPage = (Page) ((Node) linkToPage.get()).getLinkTarget();
		assertThat(linkedPage).isNotNull();
		assertThat(linkedPage.getName()).isEqualTo("Page-1");
		assertEquals("compressed.drawio", linkedPage.getDocument().getURI().lastSegment());
	}
			
	@Test 
	public void testLinkedPagesTraversal() throws Exception {
		Document document = Document.load(getClass().getResource("links.drawio"));
		Consumer<Element> visitor = e -> {
			if (e instanceof ModelElement) {
//				System.out.println(((ModelElement) e).getLabel());
			} else if (e instanceof Page) {
				Page page = (Page) e;
				System.out.println(page.getName() + " " + page.getId() + " " + page.getDocument().getURI());				
			}
		};
		
		document.accept(visitor, null);
		System.out.println("===");
		document.accept(org.nasdanika.drawio.Util.withLinkTargets(visitor, null), null);
	}
	
	// --- Element links ---
	
	@Test 
	public void testSamePageElementLink() throws Exception {
		Document document = Document.load(getClass().getResource("links.drawio"));
		Optional<Node> linkSource = document
				.stream(null)
				.filter(Node.class::isInstance)
				.map(Node.class::cast)
				.filter(n -> "Link to Linked on this page".equals(n.getLabel()))
				.findFirst();
		
		assertTrue(linkSource.isPresent());		
		LinkTarget linkTarget = linkSource.get().getLinkTarget();
		assertNotNull(linkTarget);
		assertEquals("Linked", ((Node) linkTarget).getLabel());
		assertTrue(linkSource.get().getModel().getPage() == ((Node) linkTarget).getModel().getPage());
	}
		
	@Test 
	public void testInternalElementLink() throws Exception {
		Document document = Document.load(getClass().getResource("links.drawio"));
		Optional<Node> linkSource = document
				.stream(null)
				.filter(Node.class::isInstance)
				.map(Node.class::cast)
				.filter(n -> "Link to Linked on Page 2".equals(n.getLabel()))
				.findFirst();
		
		assertTrue(linkSource.isPresent());		
		LinkTarget linkTarget = linkSource.get().getLinkTarget();
		assertNotNull(linkTarget);
		Node targetNode = (Node) linkTarget;
		assertEquals("Linked", targetNode.getLabel());
		assertEquals("Page 2", targetNode.getModel().getPage().getName());
		assertTrue(linkSource.get().getModel().getPage().getDocument() == targetNode.getModel().getPage().getDocument());
	}
		
	@Test 
	public void testExternalElementLink() throws Exception {
		Document document = Document.load(getClass().getResource("links.drawio"));
		Optional<Node> linkSource = document
				.stream(null)
				.filter(Node.class::isInstance)
				.map(Node.class::cast)
				.filter(n -> "Link to linked on compressed second page".equals(n.getLabel()))
				.findFirst();
		
		assertTrue(linkSource.isPresent());		
		LinkTarget linkTarget = linkSource.get().getLinkTarget();
		assertNotNull(linkTarget);
		Node targetNode = (Node) linkTarget;
		assertEquals("Linked", targetNode.getLabel());
		assertEquals("Page 2", targetNode.getModel().getPage().getName());
		Document targetDocument = targetNode.getModel().getPage().getDocument();
		assertTrue(linkSource.get().getModel().getPage().getDocument() != targetDocument);						
		assertEquals("compressed.drawio", targetDocument.getURI().lastSegment());
		
		// Linking back to make sure that back document is the same as the source document
		
		Optional<Node> backLinkSource = targetDocument
				.getPages()
				.get(0)
				.stream(null)
				.filter(Node.class::isInstance)
				.map(Node.class::cast)
				.filter(n -> "Link to Linked on linked Page 2".equals(n.getLabel()))
				.findFirst();
		
		assertTrue(backLinkSource.isPresent());		
		LinkTarget backLinkTarget = backLinkSource.get().getLinkTarget();
		assertNotNull(backLinkTarget);
		Node backLinkTargetNode = (Node) backLinkTarget;
		assertEquals("Linked", backLinkTargetNode.getLabel());
		assertEquals("Page 2", backLinkTargetNode.getModel().getPage().getName());
		Document backLinkTargetDocument = backLinkTargetNode.getModel().getPage().getDocument();
		assertTrue(backLinkSource.get().getModel().getPage().getDocument() != backLinkTargetDocument);						
		assertEquals("links.drawio", backLinkTargetDocument.getURI().lastSegment());
		assertEquals(document.getURI(), backLinkTargetDocument.getURI());								
		assertTrue(document == backLinkTargetDocument);								
	}
	
	
	// TODO - link back
	
	// -SEOLJj5ORxnoyrb6d9t-1 - Linked on compressed page 2
	
	
	
	
}
