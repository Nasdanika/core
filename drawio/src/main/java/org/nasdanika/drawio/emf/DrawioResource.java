package org.nasdanika.drawio.emf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Stream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.URIEncodable;
import org.nasdanika.drawio.Document;
import org.nasdanika.graph.processor.emf.GraphProcessorResource;
import org.xml.sax.SAXException;

/**
 * Loads {@link EObject}s from Drawio {@link Document}s. 
 * @author Pavel
 *
 */
public abstract class DrawioResource<P, T extends EObject> extends GraphProcessorResource<P, T> implements URIEncodable {
	
	protected Document document;
	
	protected DrawioResource(URI uri, boolean parallel) {
		super(uri, parallel);
	}
	
	@Override
	protected Stream<? extends org.nasdanika.graph.Element> loadElements(InputStream inputStream, Map<?, ?> options) throws IOException {
		try {
			document = loadDocument(inputStream);
			URI resourceURI = getURI();
			return document.stream()
				.filter(org.nasdanika.drawio.Element.class::isInstance)
				.map(org.nasdanika.drawio.Element.class::cast)
				.filter(e -> {
					URI elementURI = e.getURI();
					return elementURI != null && resourceURI.equals(elementURI);
				});
		} catch (ParserConfigurationException | SAXException e) {
			throw new NasdanikaException(e);
		}
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
		return Document.load(inputStream, getURI().trimFragment());
	}

	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		if (document == null) {
			try {
				document = Document.create(false, getURI());
			} catch (ParserConfigurationException e) {
				throw new NasdanikaException(e);
			}
		}
		update(document);
		try (Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
			try {
				writer.write(document.save(null));
			} catch (TransformerException e) {
				throw new NasdanikaException(e);
			}
		}
	}

	/**
	 * Override to update document with model data.
	 * @param document Document to update. It may be a previously loaded document or a new empty document for new resources.
	 */
	protected void update(Document document) {}
	
	@Override
	public URI encode() {
		try {
			return document.toDataURI(true);
		} catch (TransformerException | IOException e) {
			throw new NasdanikaException("Error encoding document to URI: " + e, e);
		}
	}
			
}