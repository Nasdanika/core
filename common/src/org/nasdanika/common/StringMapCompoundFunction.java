package org.nasdanika.common;

/**
 * Provides a convenience method putting an element keyed by its name.
 * @author Pavel
 *
 * @param <T>
 */
public class StringMapCompoundFunction<T,R> extends MapCompoundFunction<String,T,R> {

	public StringMapCompoundFunction(String name) {
		super(name);
	}
	
	public void put(Function<T,R> element) {
		super.put(element.name(), element);
	}

}
