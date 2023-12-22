package org.nasdanika.graph.model.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;
import org.nasdanika.drawio.emf.DrawioResource;
import org.nasdanika.drawio.model.ModelFactory;
import org.nasdanika.drawio.model.util.AbstractDrawioFactory;
import org.nasdanika.graph.Element;
import org.nasdanika.persistence.Marker;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Loads Drawio model using {@link DrawioResource} and then transforms it to the graph model.
 */
public class GraphDrawioResource extends ResourceImpl {
		
	protected Function<URI,EObject> uriResolver;
	
	public GraphDrawioResource(Function<URI,EObject> uriResolver) {
		super();
		this.uriResolver = uriResolver;
	}

	public GraphDrawioResource(URI uri, Function<URI,EObject> uriResolver) {
		super(uri);
		this.uriResolver = uriResolver;
	}

	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		Resource diagramResource = new DrawioResource(getURI()) {

			@Override
			protected Function<Marker, org.nasdanika.ncore.Marker> getMarkerFactory() {
				return GraphDrawioResource.this.getMarkerFactory();
			}

			@Override
			protected ModelFactory getFactory() {
				return GraphDrawioResource.this.getDrawioFactory();
			}
			
		};
		
		diagramResource.load(inputStream, options);
		
		GraphDrawioFactory<EObject> graphDrawioFactory = new GraphDrawioFactory<EObject>() {

			@Override
			protected EObject getByRefId(String refId, int pass, Map<EObject, EObject> registry) {				
				return GraphDrawioResource.this.getByRefId(refId, pass, registry);
			}
			
			@Override
			protected EvaluationContext createEvaluationContext() {
				return GraphDrawioResource.this.createEvaluationContext();
			}
			
			@Override
			protected URI getAppBase() {
				return GraphDrawioResource.this.getAppBase();
			}
			
			@Override
			protected void filterRepresentationElement(Element representationElement, EObject semanticElement, ProgressMonitor progressMonitor) {
				GraphDrawioResource.this.filterRepresentationElement(representationElement, semanticElement, progressMonitor);
			}
			
		};
		
		Transformer<EObject,EObject> graphFactory = new Transformer<>(graphDrawioFactory);
		Collection<EObject> diagramModelContents = new ArrayList<>();
		diagramResource.getAllContents().forEachRemaining(e -> {
			if (e instanceof org.nasdanika.drawio.model.Document
					|| e instanceof org.nasdanika.drawio.model.Page
					|| e instanceof org.nasdanika.drawio.model.ModelElement
					|| e instanceof org.nasdanika.drawio.model.Tag) {
				diagramModelContents.add(e);
			}
		});
		
		Map<EObject, EObject> graphElements = graphFactory.transform(diagramModelContents, false, getProgressMonitor());
		
		diagramResource.getContents().stream().map(graphElements::get).forEach(getContents()::add);
	}

	protected ProgressMonitor getProgressMonitor() {
		return new NullProgressMonitor();
	}

	protected Function<Marker, org.nasdanika.ncore.Marker> getMarkerFactory() {
		return null;
	}

	protected ModelFactory getDrawioFactory() {
		return org.nasdanika.drawio.model.ModelFactory.eINSTANCE;
	}	
	
	protected EObject getByRefId(String refId, int pass, Map<EObject, EObject> registry) {
		if (uriResolver == null) {
			return null;
		}
		
		URI refURI = URI.createURI(refId);
		if(!getURI().isRelative()) {
			refURI = refURI.resolve(getURI());
		}
		return uriResolver.apply(refURI);
	}
	
	protected EvaluationContext createEvaluationContext() {
		return new StandardEvaluationContext();
	}	

	protected URI getAppBase() {
		return AbstractDrawioFactory.DEFAULT_APP_BASE;
	}
	
	/**
	 * Override to implement filtering of a representation element. 
	 * For example, if an element represents a processing unit, its background color or image can be modified depending on the load - red for overloaded, green for OK, grey for planned offline.  
	 * @param representationElement
	 * @param registry
	 * @param progressMonitor
	 */
	protected void filterRepresentationElement(
			org.nasdanika.graph.Element representationElement, 
			EObject semanticElement,
			ProgressMonitor progressMonitor) {
		
	}
	

}
