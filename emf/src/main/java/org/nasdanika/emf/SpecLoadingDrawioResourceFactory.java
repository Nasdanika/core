package org.nasdanika.emf;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.emf.AbstractDrawioFactory;
import org.nasdanika.drawio.emf.DrawioContentProvider;
import org.nasdanika.drawio.model.ModelFactory;
import org.nasdanika.mapping.ContentProvider;
import org.nasdanika.persistence.Marker;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Uses {@link DocLoadingDrawioFactory} to load family model from a Drawio diagram. 
 * @author Pavel
 *
 */
public class SpecLoadingDrawioResourceFactory implements Resource.Factory {
	
	protected Function<URI,EObject> uriResolver;
	protected CapabilityLoader capabilityLoader;
	
	public SpecLoadingDrawioResourceFactory(CapabilityLoader capabilityLoader, Function<URI,EObject> uriResolver) {
		this.capabilityLoader = capabilityLoader;
		this.uriResolver = uriResolver;
	}
		
	@Override
	public Resource createResource(URI uri) {
		return new SpecLoadingDrawioResource(uri, capabilityLoader, uriResolver) {
			
			@Override
			protected ClassLoader getClassLoader(Element context, URI baseURI, Supplier<ClassLoader> ancestorClassLoaderSupplier) {
				return SpecLoadingDrawioResourceFactory.this.getClassLoader(context, baseURI, ancestorClassLoaderSupplier);
			};
			
			@Override
			protected URI getAppBase() {
				return SpecLoadingDrawioResourceFactory.this.getAppBase();
			}
			
			@Override
			protected void filterRepresentationElement(
					Element representationElement, 
					Map<Element, EObject> registry,
					ProgressMonitor progressMonitor) {
				
				SpecLoadingDrawioResourceFactory.this.filterRepresentationElement(representationElement, registry, progressMonitor);			
			}
			
			@Override
			protected Map<String, Object> getVariables(Element context) {
				return SpecLoadingDrawioResourceFactory.this.getVariables(this, context);
			}
			
			@Override
			protected ContentProvider<Element> createContentProvider(Document document) {
				// TODO Auto-generated method stub
				return super.createContentProvider(document);
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
	
	protected ClassLoader getClassLoader(Element context, URI baseURI, Supplier<ClassLoader> logicalParentClassLoaderSupplier) {
		return logicalParentClassLoaderSupplier == null ? Thread.currentThread().getContextClassLoader() : logicalParentClassLoaderSupplier.get();
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
			Element representationElement, 
			Map<org.nasdanika.drawio.Element, EObject> registry,
			ProgressMonitor progressMonitor) {
		
	}
	
	protected Map<String, Object> getVariables(SpecLoadingDrawioResource resource, Element context) {
		return Collections.emptyMap();
	}
	
	protected ContentProvider<Element> createContentProvider(SpecLoadingDrawioResource resource, Document document) {
		return new DrawioContentProvider(document);
	}
			
}
