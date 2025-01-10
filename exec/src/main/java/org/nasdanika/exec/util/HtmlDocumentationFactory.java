package org.nasdanika.exec.util;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletionStage;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.DocumentationFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertySource;
import org.nasdanika.common.Util;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.exec.content.Text;

public class HtmlDocumentationFactory extends ServiceCapabilityFactory<Void, DocumentationFactory> {
	
	@Override
	public boolean isFor(Class<?> type, Object requirement) {
		return DocumentationFactory.class == type && requirement == null;
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<DocumentationFactory>>> createService(
			Class<DocumentationFactory> serviceType, 
			Void serviceRequirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		DocumentationFactory markdownDocumentationFactory = new DocumentationFactory() {
			
			@Override
			public boolean canHandle(String contentType) {				
				return "html".equalsIgnoreCase(contentType) || "text/html".equalsIgnoreCase(contentType);
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public Collection<EObject> createDocumentation(Object context, String doc, URI baseUri, ProgressMonitor progressMonitor) {
				if (context instanceof PropertySource) {
					doc = Util.interpolate(doc, ((PropertySource<String,String>) context)::getProperty);
				}				
				Text text = ContentFactory.eINSTANCE.createText();				
				text.setContent(doc);
				return Collections.singleton(text);
			}
		};
		
		return wrap(markdownDocumentationFactory);
	}
	
}