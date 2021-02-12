package org.nasdanika.common.persistence;

/**
 * Resolves marker to a URL in the source repository.
 * @author Pavel
 *
 */
public interface SourceResolver {
	
	/**
	 * @param marker Marker.
	 * @return URL of the marker in the source repository or null.
	 */
	String getSource(Marker marker);

}
