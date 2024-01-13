package org.nasdanika.drawio.emf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Function;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.model.ModelFactory;
import org.nasdanika.persistence.Marker;
import org.xml.sax.SAXException;

/**
 * Loads {@link EObject}s from Drawio {@link Document}s. 
 * @author Pavel
 *
 */
public class DrawioResource extends ResourceImpl {
	
	public DrawioResource(URI uri) {
		super(uri);
	}
		
	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		try {
			Document document = loadDocument(inputStream);
			getContents().add(document.toModelDocument(getFactory(), getMarkerFactory()));
		} catch (ParserConfigurationException | SAXException | TransformerException e) {
			throw new NasdanikaException(e);
		}
	}

	protected Function<Marker, org.nasdanika.ncore.Marker> getMarkerFactory() {
		return null;
	}

	protected ModelFactory getFactory() {
		return org.nasdanika.drawio.model.ModelFactory.eINSTANCE;
	}

	/**
	 * Loads document from an {@link InputStream}. This implementation calls Document.load().
	 * Override to implement document pre-processing, e.g. token replacement.
	 * @param inputStream
	 * @return
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	protected Document loadDocument(InputStream inputStream) throws IOException, ParserConfigurationException, SAXException {
		return Document.load(inputStream, getURI().trimFragment(), getURIHandler());
	}
	
	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		if (getContents().size() == 0) {
			return;
		}
		if (getContents().size() > 1) {
			throw new IllegalStateException("More than one root element");
		}
		
		for (EObject root: getContents()) {
			if (root instanceof org.nasdanika.drawio.model.Document) {
				Document document = Document.load((org.nasdanika.drawio.model.Document) root);
				try (Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
					try {
						writer.write(document.save(null));
					} catch (TransformerException e) {
						throw new NasdanikaException(e);
					}
				}
			} else {
				throw new IllegalStateException("Root element shall be an instance of org.nasdanika.drawio.model.Document, found: " + root);
			}
		}
	}
	
	/**
	 * Override to support loading from non-URL URI's.
	 * @return
	 */
	protected Function<URI, InputStream> getURIHandler() {
		URIConverter uriConverter = getURIConverter();
		if (uriConverter == null) {
			return null;
		}
		
		return uri -> {
			try {
				return uriConverter.createInputStream(uri);
			} catch (IOException e) {
				throw new NasdanikaException("Error creating input stream from '" + uri +  "': " + e, e);
			}
		};
	}
			
}