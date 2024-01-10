package org.nasdanika.graph.model.util;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.script.ScriptEngine;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.model.ModelFactory;
import org.nasdanika.drawio.model.util.AbstractDrawioFactory;
import org.nasdanika.persistence.Marker;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Uses {@link FamilyDrawioFactory} to load family model from a Drawio diagram. 
 * @author Pavel
 *
 */
public class GraphDrawioResourceFactory implements Resource.Factory {
	
	protected Function<URI,EObject> uriResolver;
	
	public GraphDrawioResourceFactory(Function<URI,EObject> uriResolver) {
		this.uriResolver = uriResolver;
	}
		
	@Override
	public Resource createResource(URI uri) {
		return new GraphDrawioResource(uri, uriResolver) {
			
			@Override
			protected EvaluationContext createEvaluationContext(EObject context) {
				return GraphDrawioResourceFactory.this.createEvaluationContext(context);				
			};
			
			@Override
			protected ClassLoader getClassLoader(EObject context, URI baseURI, Supplier<ClassLoader> logicalParentClassLoaderSupplier) {
				return GraphDrawioResourceFactory.this.getClassLoader(context, baseURI, logicalParentClassLoaderSupplier);
			};
			
			@Override
			protected void configureScriptEngine(
					ScriptEngine engine, 
					EObject diagramElement, 
					EObject semanticElement,
					Map<EObject, EObject> registry, 
					int pass, 
					ProgressMonitor progressMonitor) {
				
				super.configureScriptEngine(
						engine, 
						diagramElement, 
						semanticElement, 
						registry, 
						pass, 
						progressMonitor);
				
				GraphDrawioResourceFactory.this.configureScriptEngine(
						engine, 
						this,
						diagramElement, 
						semanticElement, 
						registry, 
						pass, 
						progressMonitor);
			}
			
			@Override
			protected URI getAppBase() {
				return GraphDrawioResourceFactory.this.getAppBase();
			}
			
			@Override
			protected void filterRepresentationElement(
					org.nasdanika.drawio.ModelElement representationElement, 
					EObject semanticElement,
					Map<EObject, EObject> registry,
					ProgressMonitor progressMonitor) {
				
				GraphDrawioResourceFactory.this.filterRepresentationElement(representationElement, semanticElement, registry, progressMonitor);			
			}
			
		};
	}
	
	protected Function<Marker, org.nasdanika.ncore.Marker> getMarkerFactory() {
		return null;
	}
	
	protected ModelFactory getDrawioFactory() {
		return org.nasdanika.drawio.model.ModelFactory.eINSTANCE;
	}
	
	protected ProgressMonitor getProgressMonitor() {
		return new NullProgressMonitor();
	}
	
	protected EvaluationContext createEvaluationContext(Object context) {
		return new StandardEvaluationContext();
	}	
	
	protected ClassLoader getClassLoader(EObject context, URI baseURI, Supplier<ClassLoader> logicalParentClassLoaderSupplier) {
		return logicalParentClassLoaderSupplier == null ? getClass().getClassLoader() : logicalParentClassLoaderSupplier.get();
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
			org.nasdanika.drawio.ModelElement representationElement, 
			EObject semanticElement,
			Map<EObject, EObject> registry,
			ProgressMonitor progressMonitor) {
		
	}
	
	protected void configureScriptEngine(
			ScriptEngine engine, 
			GraphDrawioResource resource,
			EObject diagramElement, 
			EObject semanticElement,
			Map<EObject, EObject> registry, 
			int pass, 
			ProgressMonitor progressMonitor) {		
		
	}	
			
}
