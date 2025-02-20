package org.nasdanika.exec.util;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
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

public class HtmlDocumentationFactory extends ServiceCapabilityFactory<DocumentationFactory.Requirement, DocumentationFactory> {
	
	@Override
	public boolean isFor(Class<?> type, Object requirement) {
		return DocumentationFactory.class == type && requirement instanceof DocumentationFactory.Requirement;
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<DocumentationFactory>>> createService(
			Class<DocumentationFactory> serviceType, 
			DocumentationFactory.Requirement serviceRequirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		DocumentationFactory htmlDocumentationFactory = new DocumentationFactory() {
			
			@Override
			public Collection<String> getExtensions() {
				return List.of("html", "htm");
			}
			
			@Override
			public boolean canHandle(String contentType) {				
				return "html".equalsIgnoreCase(contentType) || "text/html".equalsIgnoreCase(contentType);
			}
			
			@Override
			public Collection<EObject> createDocumentation(Object context, URI docRef, ProgressMonitor progressMonitor) {
				if (serviceRequirement.inline()) {
					return DocumentationFactory.super.createDocumentation(context, docRef, progressMonitor);
				}
				Resource ret = ContentFactory.eINSTANCE.createResource();
				ret.setLocation(docRef.toString());
				ret.setErrorMessage("Error loading documentation from '" + docRef.toString() + "' (${url}): ${exception}");
				return Collections.singleton(ret);
			}
			
			
			@SuppressWarnings("unchecked")
			@Override
			public Collection<EObject> createDocumentation(
					Object context, 
					String doc, 
					String contentType, 
					URI baseUri, 
					ProgressMonitor progressMonitor) {
				if (context instanceof PropertySource) {
					doc = Util.interpolate(doc, ((PropertySource<String,String>) context)::getProperty);
				}				
				Text text = ContentFactory.eINSTANCE.createText();				
				text.setContent(doc);
				return Collections.singleton(text);
			}
		};
		
		return wrap(htmlDocumentationFactory);
	}
	
}