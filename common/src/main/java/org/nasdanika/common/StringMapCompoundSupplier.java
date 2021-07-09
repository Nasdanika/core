package org.nasdanika.common;

/**
 * Provides a convenience method putting an element keyed by its name.
 * @author Pavel
 *
 * @param <T>
 */
public class StringMapCompoundSupplier<T> extends MapCompoundSupplier<String, T> {

	public StringMapCompoundSupplier(String name) {
		super(name);
	}
	
	public void put(Supplier<T> element) {
		super.put(element.name(), element);
	}

}
