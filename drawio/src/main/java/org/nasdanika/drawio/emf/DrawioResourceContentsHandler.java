package org.nasdanika.drawio.emf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.capability.emf.ResourceContentsHandler;
import org.nasdanika.capability.emf.ResourceEObjectContentsHandler;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Document.Context;
import org.xml.sax.SAXException;

public class DrawioResourceContentsHandler implements ResourceContentsHandler<Document> {
	
	private Resource resource;

	public DrawioResourceContentsHandler(Resource resource) {
		this.resource = resource;
	}

	@Override
	public Order getOrder() {
		return Order.of(0);
	}

	@Override
	public Document load(InputStream inputStream, Map<?, ?> options) throws IOException {
		try {
			return Document.load(inputStream, resource.getURI(), Context.fromURIConverter(resource.getResourceSet().getURIConverter()));
		} catch (ParserConfigurationException | SAXException e) {
			throw new IOException(e);
		}
	}	
	
	@Override
	public void save(Document document, OutputStream outputStream, Map<?, ?> options) throws IOException {
		try (Writer writer = new OutputStreamWriter(outputStream)) {			
			try {
				writer.write(document.save(true));
			} catch (TransformerException e) {
				throw new IOException(e);
			}
		}
	}
	
	public ResourceEObjectContentsHandler<org.nasdanika.drawio.model.Document> adaptToModelDocumentContentsHandler() {
		return adaptToResourceEObjectContentsHandler(
				document -> {
					try {
						return document.toModelDocument();
					} catch (TransformerException | IOException e) {
						throw new NasdanikaException("Error converting to model document", e);
					}
				}, 
				modelDocument -> Document.load(modelDocument));
	}

}
