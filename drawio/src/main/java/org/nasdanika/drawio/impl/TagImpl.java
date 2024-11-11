package org.nasdanika.drawio.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Tag;

public class TagImpl implements Tag {

	@Override
	public int hashCode() {
		return Objects.hash(name, page);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TagImpl other = (TagImpl) obj;
		return Objects.equals(name, other.name) && Objects.equals(page, other.page);
	}

	private String name;
	private Page page;

	public TagImpl(String name, Page page) {
		this.name = name;
		this.page = page;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Collection<ModelElement> getElements() {
		Collection<ModelElement> ret = new ArrayList<>();
		page.accept(e -> {
			if (e instanceof ModelElement) {
				ModelElement modelElement = (ModelElement) e;
				for (Tag tag: modelElement.getTags()) {
					if (name.equals(tag.getName())) {
						ret.add(modelElement);
					}
				}
			}
		});
		return ret;
	}

}
