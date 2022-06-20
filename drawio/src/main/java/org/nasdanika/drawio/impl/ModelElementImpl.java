package org.nasdanika.drawio.impl;

import java.util.Collections;
import java.util.List;

import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.ModelElement;

class ModelElementImpl extends ElementImpl implements ModelElement {
	
	static final String ATTRIBUTE_VALUE = "value";
	static final String ATTRIBUTE_ID = "id";
	static final String ATTRIBUTE_PARENT = "parent";
	static final String ATTRIBUTE_LABEL = "label";
	static final String ATTRIBUTE_LINK = "link";
	static final String ATTRIBUTE_TOOLTIP = "tooltip";
	static final String ATTRIBUTE_TARGET = "target";
	static final String ATTRIBUTE_SOURCE = "source";
	
	/**
	 * For element resolution.
	 */
	protected ModelImpl model;

	/**
	 * @param element XML element for this page element
	 * @param rootElement root element containing all model elements to use for lookup of parent and children.
	 */
	ModelElementImpl(org.w3c.dom.Element element, ModelImpl model) {
		this.element = element;
		this.model = model;
	}

	@Override
	public ModelElement getParent() {
		if (getCellElement().hasAttribute(ATTRIBUTE_PARENT)) {
			return model.find(pe -> pe.getElement().hasAttribute(ATTRIBUTE_ID) && pe.getElement().getAttribute("ID").equals(getCellElement().getAttribute(ATTRIBUTE_PARENT)));
		}
		return null;
	}
	
	@Override
	protected List<? extends Element> getChildren() {
		if (element.hasAttribute(ATTRIBUTE_ID)) {
			return model.collect(pe -> ModelElementImpl.getCellElement(pe.getElement()).hasAttribute(ATTRIBUTE_PARENT) && ModelElementImpl.getCellElement(pe.getElement()).getAttribute(ATTRIBUTE_PARENT).equals(element.getAttribute(ATTRIBUTE_ID)));
		}
		return Collections.emptyList();
	}
	
	protected org.w3c.dom.Element getCellElement() {
		return getCellElement(element);
	}	
	
	static org.w3c.dom.Element getCellElement(org.w3c.dom.Element element) {
		return "mxCell".equals(element.getTagName()) ? element : DocumentImpl.getChildrenElements(element, "mxCell").get(0);
	}
	
	/**
	 * Wraps mxCell into object element if not already
	 * @return
	 */
	protected org.w3c.dom.Element asObjectElement() {
		if ("mxCell".equals(element.getTagName())) {
			org.w3c.dom.Element objectElement = element.getOwnerDocument().createElement("object");
			element.getParentNode().replaceChild(objectElement, element);
			org.w3c.dom.Element cellElement = element;
			element = objectElement;
			objectElement.appendChild(cellElement);
			if (cellElement.hasAttribute(ATTRIBUTE_VALUE)) {
				element.setAttribute(ATTRIBUTE_LABEL, cellElement.getAttribute(ATTRIBUTE_VALUE));
				cellElement.removeAttribute(ATTRIBUTE_VALUE);
			}
		}
		return element;
	}

	@Override
	public String getLabel() {
		return "mxCell".equals(element.getTagName()) ? element.getAttribute(ATTRIBUTE_VALUE) : element.getAttribute(ATTRIBUTE_LABEL);
	}

	@Override
	public void setLabel(String label) {
		element.setAttribute("mxCell".equals(element.getTagName()) ? ATTRIBUTE_VALUE : ATTRIBUTE_LABEL, label);
	}

	@Override
	public String getLink() {
		return "mxCell".equals(element.getTagName()) ? null : element.getAttribute(ATTRIBUTE_LINK);
	}

	@Override
	public void setLink(String link) {
		asObjectElement().setAttribute(ATTRIBUTE_LINK, link);
	}

	@Override
	public String getTooltip() {
		return "mxCell".equals(element.getTagName()) ? null : element.getAttribute(ATTRIBUTE_TOOLTIP);
	}

	@Override
	public void setTooltip(String tooltip) {
		asObjectElement().setAttribute(ATTRIBUTE_TOOLTIP, tooltip);
	}

}
