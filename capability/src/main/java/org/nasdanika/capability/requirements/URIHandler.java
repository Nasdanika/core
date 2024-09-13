package org.nasdanika.capability.requirements;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.Composable;

public interface URIHandler extends Composable<URIHandler> {
	
	URIHandler DEFAULT = new URIHandler() {};
	
	/**
	 * Normalizes URI. For example, converts logical URI's to physical (URL's)
	 * or resolves relative URI against a base URI. 
	 * This implementation does nothing - just returns the argument
	 * @param uri
	 * @return
	 */
	default URI normalize(URI uri) {
		return uri;
	}
	
	default InputStream openStream(URI uri) throws IOException {
		URL url = new URL(uri.toString());
		return url.openStream();
	}	
	
	@Override
	default URIHandler compose(URIHandler other) {
		if (other == null) {
			return this;
		}
		
		return new URIHandler() {
			
			@Override
			public URI normalize(URI uri) {
				URI thisNormalized = URIHandler.this.normalize(uri);
				return other.normalize(thisNormalized);
			}
			
			@Override
			public InputStream openStream(URI uri) throws IOException {
				return URIHandler.this.openStream(uri);
			}
			
		};
		
	}

}
