package org.nasdanika.drawio.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import org.nasdanika.common.AbstractSplitJoinSet;
import org.nasdanika.common.DelimitedStringMap;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.ModelElement;

class ModelElementImpl extends ElementImpl implements ModelElement {
	
	private static final String ATTRIBUTE_TAGS = "tags";
	static final String ATTRIBUTE_VALUE = "value";
	static final String ATTRIBUTE_PARENT = "parent";
	static final String ATTRIBUTE_LABEL = "label";
	static final String ATTRIBUTE_LINK = "link";
	static final String ATTRIBUTE_TOOLTIP = "tooltip";
	static final String ATTRIBUTE_TARGET = "target";
	static final String ATTRIBUTE_SOURCE = "source";
	static final String ATTRIBUTE_STYLE = "style";
	static final String ATTRIBUTE_VISIBLE = "visible";	
	
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
			return model.find(getCellElement().getAttribute(ATTRIBUTE_PARENT));
		}
		return null;
	}
	
	@Override
	protected List<? extends Element> getChildren() {
		if (element.hasAttribute(ATTRIBUTE_ID)) {
			return model.collect(e -> ModelElementImpl.getCellElement(e).hasAttribute(ATTRIBUTE_PARENT) && ModelElementImpl.getCellElement(e).getAttribute(ATTRIBUTE_PARENT).equals(element.getAttribute(ATTRIBUTE_ID)));
		}
		return Collections.emptyList();
	}
	
	protected org.w3c.dom.Element getCellElement() {
		return getCellElement(element);
	}	
	
	@Override
	public String getId() {
		org.w3c.dom.Element cellElement = getCellElement();
		return cellElement.hasAttribute(ATTRIBUTE_ID) ? cellElement.getAttribute(ATTRIBUTE_ID) : null;
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
			if (cellElement.hasAttribute(ATTRIBUTE_ID)) {
				element.setAttribute(ATTRIBUTE_ID, cellElement.getAttribute(ATTRIBUTE_ID));
				cellElement.removeAttribute(ATTRIBUTE_ID);
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
		return getProperty(ATTRIBUTE_LINK);
	}

	@Override
	public void setLink(String link) {
		setProperty(ATTRIBUTE_LINK, link);
	}

	@Override
	public String getTooltip() {
		return getProperty(ATTRIBUTE_TOOLTIP);
	}

	@Override
	public void setTooltip(String tooltip) {
		setProperty(ATTRIBUTE_TOOLTIP, tooltip);
	}

	@Override
	public Map<String, String> getStyle() {
		return new DelimitedStringMap(";", "=") {

			@Override
			protected String getState() {
				return getCellElement().getAttribute(ATTRIBUTE_STYLE);
			}

			@Override
			protected void setState(String state) {
				getCellElement().setAttribute(ATTRIBUTE_STYLE, state);
			}
			
		};
	}

	@Override
	public String getProperty(String name) {
		return "mxCell".equals(element.getTagName()) ? null : element.getAttribute(name);
	}

	@Override
	public void setProperty(String name, String value) {
		if (Util.isBlank(value)) {
			asObjectElement().removeAttribute(name);			
		} else {
			asObjectElement().setAttribute(name, value);
		}
	}

	@Override
	public Set<String> getTags() {
		return new AbstractSplitJoinSet<String,String,String>() {

			@Override
			protected String getState() {
				return getProperty(ATTRIBUTE_TAGS);
			}

			@Override
			protected void setState(String state) {
				setProperty(ATTRIBUTE_TAGS, state);
			}

			@Override
			protected List<String> split(String state) {
				if (Util.isBlank(state)) {
					return Collections.emptyList();
				}
				return Arrays.asList(state.split(" "));
			}

			@Override			
			protected String join(List<String> chunks) {
				if (chunks == null || chunks.isEmpty()) {
					return null;
				}
				return chunks.stream().filter(e -> !Util.isBlank(e)).collect(Collectors.joining(" "));
			}

			@Override
			protected String load(String chunk) {
				return chunk;
			}

			@Override
			protected String store(String element) {
				return element;
			}
			
		};
	}

	@Override
	public boolean isVisible() {
		org.w3c.dom.Element cellElement = getCellElement(getElement());
		return !cellElement.hasAttribute(ATTRIBUTE_VISIBLE) || "1".equals(cellElement.getAttribute(ATTRIBUTE_VISIBLE));
	}

	@Override
	public void setVisible(boolean visible) {
		org.w3c.dom.Element cellElement = getCellElement(getElement());
		if (visible) {
			cellElement.removeAttribute(ATTRIBUTE_VISIBLE);
		} else {
			cellElement.setAttribute(ATTRIBUTE_VISIBLE, "0");
		}		
	}
	
	public <T> T resolve(T base, BiFunction<? super ModelElement,T,T> resolver, ConnectionBase connectionBase) {
		ModelElement logicalParent = getLogicalParent(connectionBase);
		return resolver.apply(this, logicalParent == null ? base : logicalParent.resolve(base, resolver, connectionBase)); 
	}
	
	protected ModelElement getLogicalParent(ConnectionBase connectionBase) {
		return getParent();
	}
	
}
