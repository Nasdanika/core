package org.nasdanika.drawio;

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
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.graph.processor.GraphProcessorResource;
import org.nasdanika.graph.processor.ProcessorFactory;
import org.xml.sax.SAXException;

/**
 * Loads EMF classes from Drawio documents. 
 * Saving is supported via {@link UpdateAdapter}. All contents is iterated and for each element with UpdateAdapter the adapter's update method is invoked.
 * The load logic is responsible for attaching adapters to model elements. 
 * @author Pavel
 *
 */
public abstract class DrawioResource<P> extends GraphProcessorResource<P> {
	
	protected Document document;
	
	public DrawioResource(URI uri, ProcessorFactory<P, ?, ?> processorFactory) {
		super(uri, processorFactory);
	}
	
	@Override
	protected Stream<org.nasdanika.graph.Element> loadElements(InputStream inputStream, Map<?, ?> options)	throws IOException {
		try {
			document = loadDocument(inputStream);
			return Stream.of(document);
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
		return Document.load(inputStream, getURI());
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
	
}