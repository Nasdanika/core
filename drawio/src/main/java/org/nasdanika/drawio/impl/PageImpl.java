package org.nasdanika.drawio.impl;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Root;
import org.w3c.dom.Element;

public class PageImpl implements Page {
	
	private Element element;
	private String id;

	PageImpl(org.w3c.dom.Element element) {
		this.element = element;
		if (element.hasAttribute("id")) {
			id = element.getAttribute("id");
		}
	}

	@Override
	public Element getElement() {
		return element;
	}

	@Override
	public <T> T accept(BiFunction<org.nasdanika.drawio.Element, Map<org.nasdanika.drawio.Element, T>, T> visitor) {
//		Map<org.nasdanika.drawio.Element, T> layerResults = new LinkedHashMap<>();  // TODO - change to root - singleton map
//		for (Page page: getPages()) {
//			pageResults.put(page, page.accept(visitor));
//		}
		Root root = getRoot();
		return visitor.apply(this, root == null ? Collections.emptyMap() : Collections.singletonMap(root, root.accept(visitor)));
	}

	@Override
	public int hashCode() {
		return id == null ? super.hashCode() : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageImpl other = (PageImpl) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public Root getRoot() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
