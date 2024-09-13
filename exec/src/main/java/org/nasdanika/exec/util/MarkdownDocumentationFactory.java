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
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.exec.content.Text;

public class MarkdownDocumentationFactory extends ServiceCapabilityFactory<Void, DocumentationFactory> {
	
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
				return "markdown".equalsIgnoreCase(contentType) || "text/markdown".equalsIgnoreCase(contentType);
			}
			
			@Override
			public Collection<EObject> createDocumentation(URI docRef, ProgressMonitor progressMonitor) {
				Markdown ret = ContentFactory.eINSTANCE.createMarkdown();
				Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();
				Resource resource = ContentFactory.eINSTANCE.createResource();
				resource.setLocation(docRef.toString());
				resource.setErrorMessage("Error loading documentation from '" + docRef.toString() + "' (${url}): ${exception}");
				interpolator.setSource(resource);
				ret.setSource(interpolator);
				ret.setStyle(true);
				return Collections.singleton(ret);
			}
			
			@Override
			public Collection<EObject> createDocumentation(String doc, URI baseUri, ProgressMonitor progressMonitor) {
				Markdown ret = ContentFactory.eINSTANCE.createMarkdown();
				Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();
				Text text = ContentFactory.eINSTANCE.createText(); // Interpolate with element properties?
				text.setContent(doc);
				interpolator.setSource(text);
				ret.setSource(interpolator);
				ret.setStyle(true);
				
				// Creating a marker with EObject resource location for resource resolution in Markdown
//				if (location != null) {
//					org.nasdanika.ncore.Marker marker = context.get(MarkerFactory.class, MarkerFactory.INSTANCE).createMarker(location.toString(), progressMonitor);
//					ret.getMarkers().add(marker); 
//				}
				
				return Collections.singleton(ret);
			}
		};
		
		return wrap(markdownDocumentationFactory);
	}
	
}