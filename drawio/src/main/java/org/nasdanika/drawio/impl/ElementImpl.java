package org.nasdanika.drawio.impl;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

import org.nasdanika.drawio.Element;

class ElementImpl implements Element {
	
	protected org.w3c.dom.Element element; 

	@Override
	public org.w3c.dom.Element getElement() {
		return element;
	}
	
	/**
	 * @return Child elements to pass to the visitor. This implementation returns empty list.
	 */
	protected List<? extends Element> getChildren() {
		return Collections.emptyList();
	}

	@Override
	public <T> T accept(BiFunction<Element, Map<Element, T>, T> visitor) {
		Map<org.nasdanika.drawio.Element, T> childResults = new LinkedHashMap<>();
		for (Element child: getChildren()) {
			if (child != null) {
				childResults.put(child, child.accept(visitor));
			}
		}
		return visitor.apply(this, childResults);
	}
	
	@Override
	public int hashCode() {
		if (element == null || !element.hasAttribute("id")) {
			return super.hashCode();
		}
		String id = element.getAttribute("id");
		return id.hashCode() ^ getClass().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElementImpl other = (ElementImpl) obj;
		
		if (element == null || other.element == null) {
			return super.equals(obj);
		}
		String id = element.getAttribute("id");		
		return Objects.equals(id, other.element.getAttribute("id"));
	}

}
