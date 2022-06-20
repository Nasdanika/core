package org.nasdanika.drawio.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.Model;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Root;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class ModelImpl extends ElementImpl implements Model {
	
	private static final String ATTRIBUTE_VERTEX = "vertex";
	private static final String ATTRIBUTE_EDGE = "edge";
	private Map<org.w3c.dom.Element, ModelElement> cache = new IdentityHashMap<>();
	
	ModelImpl(org.w3c.dom.Element element) {
		this.element = element;		
	}
	
	@Override
	protected List<? extends Element> getChildren() {
		return Collections.singletonList(getRoot());
	}

	@Override
	public Root getRoot() {
		return (Root) find(Root.class::isInstance);
	}
	
	private ModelElement create(org.w3c.dom.Element element) {
		org.w3c.dom.Element cellElement = ModelElementImpl.getCellElement(element);
		if (!cellElement.hasAttribute(ModelElementImpl.ATTRIBUTE_PARENT)) {
			return new RootImpl(element, this);
		}
		ModelElement parent = find(cellElement.getAttribute(ModelElementImpl.ATTRIBUTE_PARENT));
		if (parent instanceof Root) {
			return new LayerImpl(element, this);			
		}
		if (cellElement.hasAttribute(ATTRIBUTE_VERTEX) && "1".equals(cellElement.getAttribute(ATTRIBUTE_VERTEX))) {
			return new NodeImpl(element, this);
		}
		if (cellElement.hasAttribute(ATTRIBUTE_EDGE) && "1".equals(cellElement.getAttribute(ATTRIBUTE_EDGE))) {
			return new ConnectionImpl(element, this);
		}
		return new ModelElementImpl(element, this); // Generic model element, shall it ever happen?
	}
	
	ModelElement find(String id) {
		return find(e -> e.getElement().hasAttribute(ModelElementImpl.ATTRIBUTE_ID) && id.equals(e.getElement().getAttribute(ModelElementImpl.ATTRIBUTE_ID)));
	}
	
	List<ModelElement> collect(Predicate<ModelElement> predicate) {
		List<org.w3c.dom.Element> rootElements = DocumentImpl.getChildrenElements(element, "root");
		if (rootElements.size() != 1) {
			throw new IllegalArgumentException("There should be only one root XML element, found " + rootElements.size());
		}
		
		List<ModelElement> result = new ArrayList<>();
		org.w3c.dom.Element rootElement = rootElements.get(0);
		NodeList childNodes = rootElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); ++i) {
			Node child = childNodes.item(i);
			if (child instanceof org.w3c.dom.Element) {
				org.w3c.dom.Element childElement = (org.w3c.dom.Element) child;
				ModelElement modelElement = cache.get(childElement);
				if (modelElement == null) {						
					modelElement = create(childElement);
					cache.put(childElement, modelElement);
				}
				if (predicate.test(modelElement)) {
					result.add(modelElement);
				}
			}
		}
		
		return result;
	}
	
	ModelElement find(Predicate<ModelElement> predicate) {
		List<org.w3c.dom.Element> rootElements = DocumentImpl.getChildrenElements(element, "root");
		if (rootElements.size() != 1) {
			throw new IllegalArgumentException("There should be only one root XML element, found " + rootElements.size());
		}
		
		org.w3c.dom.Element rootElement = rootElements.get(0);
		NodeList childNodes = rootElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); ++i) {
			Node child = childNodes.item(i);
			if (child instanceof org.w3c.dom.Element) {
				org.w3c.dom.Element childElement = (org.w3c.dom.Element) child;
				ModelElement modelElement = cache.get(childElement);
				if (modelElement == null) {						
					modelElement = create(childElement);
					cache.put(childElement, modelElement);
				}
				if (predicate.test(modelElement)) {
					return modelElement;
				}
			}
		}
		
		return null;
	}
	

}
