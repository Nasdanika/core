package org.nasdanika.drawio.emf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Util;
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
	
	private boolean png;
	
	protected static boolean isPng(URI uri) {
		return uri != null && !Util.isBlank(uri.lastSegment()) && uri.lastSegment().toLowerCase().endsWith(".png");
	}
	
	public DrawioResource(URI uri) {
		this(uri, isPng(uri));
	}

	public DrawioResource(URI uri, boolean png) {
		super(uri);
		this.png = png;
	}
		
	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		try {
			Iterable<Document> documents = loadDocuments(inputStream);
			for (Document document: documents) {
				getContents().add(document.toModelDocument(getFactory(), getMarkerFactory()));
			}
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
	protected Iterable<Document> loadDocuments(InputStream inputStream) throws IOException, ParserConfigurationException, SAXException {
		if (png) {
			return Document.loadFromPngMetadata(inputStream, getURI().trimFragment(), getURIHandler(), this::getProperty);			
		}
		return Collections.singleton(Document.load(inputStream, getURI().trimFragment(), getURIHandler(), this::getProperty));
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
	
	/**
	 * This implementation returns null. Override as needed.
	 * @param name
	 * @return Property value for placeholder expansion.  
	 */
	protected String getProperty(String name) {
		return null;
	}
			
}