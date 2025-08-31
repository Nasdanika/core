package org.nasdanika.exec.util;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletionStage;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.Content;
import org.nasdanika.common.DocumentationFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.exec.content.Text;
import org.nasdanika.ncore.NcoreFactory;

public class MarkdownDocumentationFactory extends ServiceCapabilityFactory<DocumentationFactory.Requirement, DocumentationFactory> {
	
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
		
		DocumentationFactory markdownDocumentationFactory = new DocumentationFactory() {
			
			@Override
			public Collection<String> getExtensions() {
				return Collections.singleton("md");
			}
			
			@Override
			public boolean canHandle(String contentType) {				
				return Content.MARKDOWN.equalsIgnoreCase(contentType) || "text/markdown".equalsIgnoreCase(contentType);
			}
			
			@Override
			public Collection<EObject> createDocumentation(
					Object context, 
					URI docRef,
					java.util.function.Function<String, String> tokenSource,
					ProgressMonitor progressMonitor) {
				if (serviceRequirement.inline()) {
					return DocumentationFactory.super.createDocumentation(context, docRef, tokenSource, progressMonitor);
				}
				Resource resource = ContentFactory.eINSTANCE.createResource();
				resource.setLocation(docRef.toString());
				resource.setErrorMessage("Error loading documentation from '" + docRef.toString() + "' (${url}): ${exception}");				
				return Collections.singleton(createMarkdown(resource));
			}
			
			@Override
			public Collection<EObject> createDocumentation(
					Object context, 
					String doc, 
					String contentType, 
					URI baseUri, 
					java.util.function.Function<String, String> tokenSource,
					ProgressMonitor progressMonitor) {
				if (tokenSource != null) {
					doc = Util.interpolate(doc, tokenSource);
				}
				Text text = ContentFactory.eINSTANCE.createText(); 
				text.setContent(doc);
				
				Markdown markdown = createMarkdown(text);
				// Creating a marker with EObject resource location for resource resolution in Markdown
				if (baseUri != null) {
					org.nasdanika.ncore.Marker marker = NcoreFactory.eINSTANCE.createMarker();
					marker.setLocation(baseUri.toString());
					markdown.getMarkers().add(marker); 
				}
				
				return Collections.singleton(markdown);
			}
		};
		
		return wrap(markdownDocumentationFactory);
	}
	
	protected Markdown createMarkdown(EObject source) {
		Markdown ret = ContentFactory.eINSTANCE.createMarkdown();
		Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();
		ret.setSource(interpolator);
		ret.setStyle(true);
		interpolator.setSource(source);
		return ret;
	}			
	
	
}