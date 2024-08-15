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

public class MarkdownDocumentationFactory extends ServiceCapabilityFactory<Void, DocumentationFactory> {
	
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
				return "markdown".equalsIgnoreCase(contentType) || "text/markdown".equalsIgnoreCase(contentType);
			}
			
			@Override
			public EObject createDocumentation(URI docRef, ProgressMonitor progressMonitor) {
				Markdown ret = ContentFactory.eINSTANCE.createMarkdown();
				Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();
				Resource resource = ContentFactory.eINSTANCE.createResource();
				resource.setLocation(docRef.toString());
				interpolator.setSource(resource);
				ret.setSource(interpolator);
				ret.setStyle(true);
				return ret;
			}
			
			@Override
			public EObject createDocumentation(String doc, URI baseUri, ProgressMonitor progressMonitor) {
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
				
				return ret;
			}
		};
		
		return wrap(markdownDocumentationFactory);
	}
	
}