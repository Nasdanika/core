package org.nasdanika.drawio.impl;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Model;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.model.ModelFactory;
import org.nasdanika.drawio.model.Tag;
import org.nasdanika.persistence.Marker;
import org.xml.sax.SAXException;


class PageImpl extends ElementImpl implements Page {
	
	static final String ATTRIBUTE_NAME = "name";
	private ModelImpl model;
	private Document document;
	
	PageImpl(Document document, org.w3c.dom.Element element) throws IOException, ParserConfigurationException, SAXException {
		this.document = document;
		this.element = element;
		List<org.w3c.dom.Element> modelElements = DocumentImpl.getChildrenElements(element, "mxGraphModel");
		if (modelElements.isEmpty()) {
			model = new ModelImpl(this, element.getTextContent());
		} else if (modelElements.size() == 1) {
			model = new ModelImpl(this, modelElements.get(0));
		} else {
			throw new IllegalArgumentException("Expected one model element, got " + modelElements.size());
		}
	}
	
	@Override
	public Document getDocument() {
		return document;
	}
	
	@Override
	public List<Model> getChildren() {
		return Collections.singletonList(getModel());
	}

	@Override
	public Model getModel() {
		return model;
	}

	@Override
	public String getName() {
		return element.getAttribute(ATTRIBUTE_NAME);
	}

	@Override
	public void setName(String name) {
		element.setAttribute(ATTRIBUTE_NAME, name);
	}
	
	/**
	 * Saves as compressed if there are no mxGraphModel child elements.
	 * @throws TransformerException
	 * @throws IOException
	 */
	void save() throws TransformerException, IOException {
		save(DocumentImpl.getChildrenElements(element, "mxGraphModel").isEmpty());
	}

	/**
	 * Saves any modifications. Doesn't do anything of compress null and uncompressed documents.
	 * For compressed documents replaces texts with compressed model string
	 * @param compress
	 * @throws TransformerException 
	 */
	void save(Boolean compress) throws TransformerException, IOException {		
		if (model.getElement().getParentNode() == element) {
			// Uncompressed - no action is required is keeping it uncompressed
			if (compress != null && compress) {				
				// Saving uncompressed model as compressed				
				element.removeChild(model.getElement());
				element.setTextContent(model.compress());			    
			}						
		} else {
			// Compressed
			if (compress == null || compress) {
				element.setTextContent(model.compress());			    
			} else {
				element.setTextContent("");
				org.w3c.dom.Element modelElement = model.getElement();
				if (modelElement.getOwnerDocument() != element.getOwnerDocument()) {
					modelElement = (org.w3c.dom.Element) element.getOwnerDocument().importNode(modelElement, true);
					element.appendChild(modelElement);
					model = new ModelImpl(this, modelElement);
				}
			}			
		}		
	}	
	
	@Override
	public String getId() {
		return element.hasAttribute(ATTRIBUTE_ID) ? element.getAttribute(ATTRIBUTE_ID) : null;
	}

	@Override
	public void setId(String id) {
		element.setAttribute(ATTRIBUTE_ID, id);
	}
	
	@Override
	public URI getURI() {
		URI documentURI = getDocument().getURI();
		return documentURI == null ? URI.createURI(getId()) : documentURI.appendFragment(URLEncoder.encode(getName(), StandardCharsets.UTF_8));
	}
		
	@Override
	public String toString() {
		return super.toString() + " " + getName();
	}
	
	@Override
	protected String getMarkerPosition() {
		return "name: " + getName() + ", id: " + getId();
	}	
	
	@Override
	protected String getMarkerLocation() {
		return ((DocumentImpl) getDocument()).getMarkerLocation();
	}

	org.nasdanika.drawio.model.Page toModelPage(
			ModelFactory factory, 
			Function<org.nasdanika.persistence.Marker, org.nasdanika.ncore.Marker> markerFactory, 
			Function<Element, CompletableFuture<EObject>> modelElementProvider) {
		org.nasdanika.drawio.model.Page mPage = factory.createPage();
		modelElementProvider.apply(this).complete(mPage);
		mPage.setName(getName());
		mPage.setId(getId());
		
		mPage.setModel(((ModelImpl) getModel()).toModelModel(
				factory, 
				markerFactory, 
				modelElementProvider,
				tag -> {
					Tag mTag = mPage.getTag(tag.getName());
					if (mTag == null) {
						mTag = ModelFactory.eINSTANCE.createTag();
						mTag.setName(tag.getName());
						for (Marker marker: getMarkers()) {
							org.nasdanika.ncore.Marker tagMarker = markerFactory.apply(marker);
							tagMarker.setLocation(tagMarker.getLocation() + ", tag=" + tag.getName());
							mTag.getMarkers().add(tagMarker);
						}
						
						mPage.getTags().add(mTag);
					}
					return mTag;
				}));
		
		for (Marker marker: getMarkers()) {
			mPage.getMarkers().add(markerFactory.apply(marker));
		}
		return mPage;
	}

	@Override
	public Map<String, org.nasdanika.drawio.Tag> getTags() {
		Map<String, org.nasdanika.drawio.Tag> ret = new HashMap<>();
		accept(e -> {
			if (e instanceof ModelElement) {
				ModelElement modelElement = (ModelElement) e;
				for (org.nasdanika.drawio.Tag tag: modelElement.getTags()) {
					ret.put(tag.getName(), tag);
				}
			}
		});
		return ret;
	}

	@Override
	public org.nasdanika.drawio.Tag createTag(String name) {
		return new TagImpl(name, this);
	}

}
