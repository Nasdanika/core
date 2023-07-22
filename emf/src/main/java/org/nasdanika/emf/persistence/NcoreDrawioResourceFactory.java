package org.nasdanika.emf.persistence;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.emf.DrawioResource;

/**
 * This factory uses {@link DrawioResource} with {@link DrawioEObjectFactory}. 
 * It injects name, description, and markers in configureSemanticElement(). 
 * @author Pavel
 *
 * @param <T>
 */
public abstract class NcoreDrawioResourceFactory extends ResourceFactoryImpl {
		
	private class NcoreDrawioResource extends DrawioResource {

		protected NcoreDrawioResource(URI uri) {
			super(uri);
		}

		@Override
		protected void loadDocumentContent() {
			NcoreDrawioResourceFactory.this.loadDocumentContent(document, this);
		}
		
		@Override
		protected void updateDocument() {
			NcoreDrawioResourceFactory.this.updateDocumentContent(document, this);
		}
		
	}
	
	protected abstract void loadDocumentContent(Document document, Resource resource);

	protected void updateDocumentContent(Document document, Resource resource) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Resource createResource(URI uri) {
		return new NcoreDrawioResource(uri);
	}

}
