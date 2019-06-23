package org.nasdanika.common;

/**
 * Source of resources for a particular object. For example, localized strings. 
 * @author Pavel
 *
 * @param <T>
 */
public interface ResourceLocator<T> extends Composeable<ResourceLocator<T>> {
	
	/**
	 * @param obj
	 * @return Context containing resources or null.
	 */
	Context get(T obj);
	
	default ResourceLocator<T> compose(ResourceLocator<T> other) {
		if (other == null) {
			return this;
		}
		
		return new ResourceLocator<T>() {

			@Override
			public Context get(T obj) {
				Context a = ResourceLocator.this.get(obj);
				Context b = other.get(obj);				
				return a == null ? b : a.compose(b);
			}
			
		};
		
	}

}
