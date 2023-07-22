package org.nasdanika.drawio.emf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.URIEncodable;
import org.nasdanika.drawio.Document;
import org.xml.sax.SAXException;

/**
 * Loads {@link EObject}s from Drawio {@link Document}s. 
 * @author Pavel
 *
 */
public abstract class DrawioResource extends ResourceImpl implements URIEncodable {
	
	protected Document document;
	
	protected DrawioResource(URI uri) {
		super(uri);
	}
		
	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		try {
			document = loadDocument(inputStream);
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
	
	/**
	 * Loads resource content from the document.
	 */
	protected abstract void loadDocumentContent();
	
	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		if (document == null) {
			try {
				document = Document.create(false, getURI());
			} catch (ParserConfigurationException e) {
				throw new NasdanikaException(e);
			}
		}
		updateDocument();
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
	protected void updateDocument() {}
	
	@Override
	public URI encode() {
		try {
			return document.toDataURI(true);
		} catch (TransformerException | IOException e) {
			throw new NasdanikaException("Error encoding document to URI: " + e, e);
		}
	}
			
}