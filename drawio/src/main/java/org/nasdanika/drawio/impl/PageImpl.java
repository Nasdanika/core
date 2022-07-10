package org.nasdanika.drawio.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Model;
import org.nasdanika.drawio.Page;
import org.xml.sax.SAXException;


class PageImpl extends ElementImpl implements Page {
	
	private ModelImpl model;
	
	PageImpl(org.w3c.dom.Element element) throws IOException, ParserConfigurationException, SAXException {
		this.element = element;
		List<org.w3c.dom.Element> modelElements = DocumentImpl.getChildrenElements(element, "mxGraphModel");
		if (modelElements.isEmpty()) {
			model = new ModelImpl(element.getTextContent());
		} else if (modelElements.size() == 1) {
			model = new ModelImpl(modelElements.get(0));
		} else {
			throw new IllegalArgumentException("Expected one model element, got " + modelElements.size());
		}
	}
	
	@Override
	protected List<? extends Element> getChildren() {
		return Collections.singletonList(getModel());
	}

	@Override
	public Model getModel() {
		return model;
	}

	@Override
	public String getName() {
		return element.getAttribute("name");
	}

	@Override
	public void setName(String name) {
		element.setAttribute("name", name);
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
					model = new ModelImpl(modelElement);
				}
			}			
		}
		
	}	
	
	@Override
	public String getId() {
		return element.hasAttribute(ATTRIBUTE_ID) ? element.getAttribute(ATTRIBUTE_ID) : null;
	}

}
