package org.nasdanika.exec.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.SpecLoadingDrawioFactory;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.exec.content.Text;

/**
 * This factory implements createXXXDoc methods.
 * @param <F>
 * @param <P>
 */
public abstract class DocLoadingDrawioFactory<S extends EObject> extends SpecLoadingDrawioFactory<S> {
	

	@Override
	protected EObject createHtmlDoc(String doc, URI baseUri, ProgressMonitor progressMonitor) {
		Text text = ContentFactory.eINSTANCE.createText(); // Interpolate with element properties?
		text.setContent(doc);
		return text;
	}

	@Override
	protected EObject createTextDoc(String doc, URI baseUri,  ProgressMonitor progressMonitor) {
		return createHtmlDoc("<PRE>" + System.lineSeparator() + doc + System.lineSeparator() + "</PRE>", baseUri, progressMonitor);
	}

	@Override
	protected EObject createMarkdownDoc(String doc, URI baseUri, ProgressMonitor progressMonitor) {
		Markdown ret = ContentFactory.eINSTANCE.createMarkdown();
		Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();
		Text text = ContentFactory.eINSTANCE.createText(); // Interpolate with element properties?
		text.setContent(doc);
		interpolator.setSource(text);
		ret.setSource(interpolator);
		ret.setStyle(true);
		
		// Creating a marker with EObject resource location for resource resolution in Markdown
//		if (location != null) {
//			org.nasdanika.ncore.Marker marker = context.get(MarkerFactory.class, MarkerFactory.INSTANCE).createMarker(location.toString(), progressMonitor);
//			ret.getMarkers().add(marker); 
//		}
		
		return ret;
	}

	@Override
	protected EObject createHtmlDoc(URI docRef,  ProgressMonitor progressMonitor) {
		Resource ret = ContentFactory.eINSTANCE.createResource();
		ret.setLocation(docRef.toString());
		return ret;
	}

	@Override
	protected EObject createTextDoc(URI docRef,  ProgressMonitor progressMonitor) {		
		throw new UnsupportedOperationException();
	}

	@Override
	protected EObject createMarkdownDoc(URI docRef,  ProgressMonitor progressMonitor) {
		Markdown ret = ContentFactory.eINSTANCE.createMarkdown();
		Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();
		Resource resource = ContentFactory.eINSTANCE.createResource();
		resource.setLocation(docRef.toString());
		interpolator.setSource(resource);
		ret.setSource(interpolator);
		ret.setStyle(true);
		return ret;
	}
		
}
