package org.nasdanika.common.resources;

import java.io.InputStream;
import java.net.URL;

/**
 * 
 * @author Pavel
 *
 */
public interface Repository {
	
	/**
	 * Stores content into a repository. Returns content URL relative to the base URL.
	 * @param content Resource content.
	 * @param base URL which will be referring to the stored resource, e.g. a web page URL which would contain a link to the resource.
	 * @param nameHint Hint for a resource name, e.g. report.pdf
	 * @return URL of the stored resource relative to the base URL.
	 */
	URL store(InputStream content, URL base, String nameHint);

}
