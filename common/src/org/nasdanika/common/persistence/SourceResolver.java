package org.nasdanika.common.persistence;

/**
 * Resolves marker to a URL in the source repository.
 * @author Pavel
 */
public interface SourceResolver {
	
	/**
	 * Link to source location
	 * @author Pavel
	 * @since 2015.4.2
	 */
	interface Link {
		
		/**
		 * @return Link text. This method returns location value. 
		 */
		default String getText() {
			return getLocation();
		};
		
		/**
		 * @return Source URL. If null then link shall be rendered as plain text returned by getText().
		 */
		String getLocation();
		
	}
	
	/**
	 * @param marker Marker.
	 * @return URL of the marker in the source repository or null.
	 */
	Link getSource(Marker marker);

}
