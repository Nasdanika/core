package org.nasdanika.drawio.emf;

import java.io.IOException;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.model.util.AbstractDrawioFactory;
import org.nasdanika.persistence.ConfigurationException;
import org.xml.sax.SAXException;

/**
 * Filters representations and sets semantic UUID's so representation elements and be linked to semantic elements.
 * @param <D>
 * @param <S>
 */
public abstract class SemanticDrawioFactory<S extends EObject> extends AbstractDrawioFactory<S> {
	
	public static final String SEMANTIC_UUID_KEY = "semantic-uuid";
		
	@Override
	protected void addRepresentationPage(
			org.nasdanika.ncore.ModelElement semanticModelElement, 
			org.nasdanika.drawio.model.Page modelPage,
			Map<EObject, EObject> registry,
			ProgressMonitor progressMonitor) {
		
		
		org.nasdanika.drawio.model.Document modelDocument = (org.nasdanika.drawio.model.Document) modelPage.eContainer();
		String source = modelDocument.getSource();
		URI sourceURI = URI.createURI(source);		
		try {
			org.nasdanika.drawio.Document sourceDocument = org.nasdanika.drawio.Document.load(sourceURI);
			Z: for (Page page: sourceDocument.getPages()) {
				if (page.getId().equals(modelPage.getId())) {
					page.accept(pageElement -> setSemanticUUIDs(pageElement, modelPage, registry));
					String semanticElementRepresentation = semanticModelElement.getRepresentations().get(DRAWIO_REPRESENTATION);
					org.nasdanika.drawio.Document semanticElementRepresentationDocument;
					if (Util.isBlank(semanticElementRepresentation)) {
						semanticElementRepresentationDocument = org.nasdanika.drawio.Document.create(true, sourceDocument.getURI());
					} else {
						semanticElementRepresentationDocument = org.nasdanika.drawio.Document.load(URI.createURI(semanticElementRepresentation));
					}
					for (Page serdp: semanticElementRepresentationDocument.getPages()) {
						if (serdp.getId().equals(page.getId())) {
							continue Z;
						}
					}
					semanticElementRepresentationDocument.getPages().add(page);
					semanticModelElement.getRepresentations().put(
							DRAWIO_REPRESENTATION, 
							semanticElementRepresentationDocument.toDataURI(true).toString());
				}
			}
		} catch (IOException | ParserConfigurationException | SAXException | TransformerException e) {
			throw new ConfigurationException("Error filtering source document: " + source, modelPage); 				
		}
	}

	/**
	 * Sets semantic UUIDs for the contents of eObj
	 * @param documentElement Element of drawio document to set semantic UUID property 
	 * @param modelElement Element of drawio model to iterate over contents to match id with the document element and get a semantic element from the registry
	 * @param registry
	 * @return
	 */
	protected void setSemanticUUIDs(
			org.nasdanika.graph.Element documentElement, 
			EObject modelElement, 
			Map<EObject, EObject> registry) {
		
		if (documentElement instanceof org.nasdanika.drawio.ModelElement) {
			org.nasdanika.drawio.ModelElement documentModelElement = (org.nasdanika.drawio.ModelElement) documentElement;
			String documentElementID = documentModelElement.getId();
			TreeIterator<EObject> mpit = modelElement.eAllContents();
			while (mpit.hasNext()) {
				EObject next = mpit.next();
				if (next instanceof org.nasdanika.drawio.model.ModelElement && documentElementID.equals(((org.nasdanika.drawio.model.ModelElement) next).getId())) {
					EObject semanticElement = registry.get(next);
					if (semanticElement instanceof org.nasdanika.ncore.ModelElement) {
						String uuid = ((org.nasdanika.ncore.ModelElement) semanticElement).getUuid();
						if (!Util.isBlank(uuid)) {
							documentModelElement.setProperty(SEMANTIC_UUID_KEY, uuid);
						}
					}
					break;
				}
			}
		}
	}
	
}
