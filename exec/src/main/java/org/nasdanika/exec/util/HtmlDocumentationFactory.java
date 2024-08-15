package org.nasdanika.exec.util;

import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.exec.content.Text;
import org.nasdanika.ncore.DocumentationFactory;

public class HtmlDocumentationFactory extends ServiceCapabilityFactory<Void, DocumentationFactory> {
	
	@Override
	public boolean isFor(Class<?> type, Object requirement) {
		return DocumentationFactory.class == type && requirement == null;
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<DocumentationFactory>>> createService(
			Class<DocumentationFactory> serviceType, 
			Void serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		
		DocumentationFactory markdownDocumentationFactory = new DocumentationFactory() {
			
			@Override
			public boolean canHandle(String contentType) {				
				return "html".equalsIgnoreCase(contentType) || "text/html".equalsIgnoreCase(contentType);
			}
			
			@Override
			public EObject createDocumentation(URI docRef, ProgressMonitor progressMonitor) {
				Resource ret = ContentFactory.eINSTANCE.createResource();
				ret.setLocation(docRef.toString());
				return ret;
			}
			
			@Override
			public EObject createDocumentation(String doc, URI baseUri, ProgressMonitor progressMonitor) {
				Text text = ContentFactory.eINSTANCE.createText(); // Interpolate with element properties?
				text.setContent(doc);
				return text;
			}
		};
		
		return wrap(markdownDocumentationFactory);
	}
	
}