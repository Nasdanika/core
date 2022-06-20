package org.nasdanika.drawio.impl;

import java.util.AbstractList;
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
		List<ModelElement> result = collect(e -> !ModelElementImpl.getCellElement(e).hasAttribute(ModelElementImpl.ATTRIBUTE_PARENT));
		if (result.isEmpty()) {
			return null;
		}
		if (result.size() == 1) {
			return (Root) result.get(0);
		}
		throw new IllegalArgumentException("More than one root");		
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
		List<ModelElement> result = collect(e -> e.hasAttribute(ModelElementImpl.ATTRIBUTE_ID) && id.equals(e.getAttribute(ModelElementImpl.ATTRIBUTE_ID)));
		if (result.isEmpty()) {
			return null;
		}
		if (result.size() == 1) {
			return result.get(0);
		}
		throw new IllegalArgumentException("More than one element found with id: " + id);		
	}
	
	/**
	 * Collects elements children of root matching the predicate and transparently converts them to model elements on access.
	 * @param predicate DOM element predicate
	 * @return
	 */
	List<ModelElement> collect(Predicate<org.w3c.dom.Element> predicate) {
		List<org.w3c.dom.Element> rootElements = DocumentImpl.getChildrenElements(element, "root");
		if (rootElements.size() != 1) {
			throw new IllegalArgumentException("There should be only one root XML element, found " + rootElements.size());
		}
		
		List<org.w3c.dom.Element> elements = new ArrayList<>();
		org.w3c.dom.Element rootElement = rootElements.get(0);
		NodeList childNodes = rootElement.getChildNodes();
		for (int i = 0; i < childNodes.getLength(); ++i) {
			Node child = childNodes.item(i);
			if (child instanceof org.w3c.dom.Element) {
				org.w3c.dom.Element childElement = (org.w3c.dom.Element) child;
				if (predicate.test(childElement)) {
					elements.add(childElement);
				}
			}
		}
		
		return new AbstractList<ModelElement>() {

			@Override
			public ModelElement get(int index) {
				return cache.computeIfAbsent(elements.get(index), ModelImpl.this::create);
			}

			@Override
			public int size() {
				return elements.size();
			}
			
		};
	}	
	
}
