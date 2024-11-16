package org.nasdanika.emf;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.Context;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.emf.AbstractDrawioFactory;
import org.nasdanika.drawio.emf.DrawioContentProvider;
import org.nasdanika.drawio.model.ModelFactory;
import org.nasdanika.emf.persistence.MarkerFactory;
import org.nasdanika.mapping.ContentProvider;
import org.nasdanika.persistence.Marker;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Uses {@link DocLoadingDrawioFactory} to load family model from a Drawio diagram. 
 * @author Pavel
 *
 */
public class ConfigurationLoadingDrawioResourceFactory implements Resource.Factory {
	
	protected Function<URI,EObject> uriResolver;
	protected CapabilityLoader capabilityLoader;
	protected MarkerFactory markerFactory;
	
	public ConfigurationLoadingDrawioResourceFactory(
			CapabilityLoader capabilityLoader, 
			Function<URI,EObject> uriResolver,
			MarkerFactory markerFactory) {
		this.capabilityLoader = capabilityLoader;
		this.uriResolver = uriResolver;
		this.markerFactory = markerFactory;
	}
		
	@Override
	public Resource createResource(URI uri) {
		return new ConfigurationLoadingDrawioResource(uri, capabilityLoader, uriResolver) {
			
			@Override
			protected ClassLoader getClassLoader(Element context, URI baseURI, Supplier<ClassLoader> ancestorClassLoaderSupplier) {
				return ConfigurationLoadingDrawioResourceFactory.this.getClassLoader(context, baseURI, ancestorClassLoaderSupplier);
			};
			
			@Override
			protected URI getAppBase() {
				return ConfigurationLoadingDrawioResourceFactory.this.getAppBase();
			}
			
			@Override
			protected void filterRepresentationElement(
					Element representationElement, 
					Map<Element, EObject> registry,
					ProgressMonitor progressMonitor) {
				
				ConfigurationLoadingDrawioResourceFactory.this.filterRepresentationElement(representationElement, registry, progressMonitor);			
			}
			
			@Override
			protected Map<String, Object> getVariables(Element context) {
				return ConfigurationLoadingDrawioResourceFactory.this.getVariables(this, context);
			}
			
			@Override
			protected ContentProvider<Element> createContentProvider(Document document) {
				return ConfigurationLoadingDrawioResourceFactory.this.createContentProvider(this, document);
			}			
			
			@Override
			protected org.nasdanika.ncore.Marker createTargetMarker(
					Marker sourceMarker,
					ProgressMonitor progressMonitor) {
				// TODO Auto-generated method stub
				return ConfigurationLoadingDrawioResourceFactory.this.createTargetMarker(this, sourceMarker, progressMonitor);
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
	
	protected Map<String, Object> getVariables(ConfigurationLoadingDrawioResource resource, Element context) {
		return Collections.emptyMap();
	}
	
	protected ContentProvider<Element> createContentProvider(ConfigurationLoadingDrawioResource resource, Document document) {
		return new DrawioContentProvider(
				document, 
				Context.BASE_URI_PROPERTY, 
				ConfigurationLoadingDrawioResource.MAPPING_PROPERTY, 
				ConfigurationLoadingDrawioResource.MAPPING_REF_PROPERTY, 
				ConnectionBase.SOURCE);
	}
				
	protected org.nasdanika.ncore.Marker createTargetMarker(
			ConfigurationLoadingDrawioResource resource,
			org.nasdanika.persistence.Marker sourceMarker, 
			ProgressMonitor progressMonitor) {
		
		if (sourceMarker == null) {
			return null;
		}
		org.nasdanika.ncore.Marker ret;
		if (markerFactory == null) {
			ret =  org.nasdanika.ncore.NcoreFactory.eINSTANCE.createMarker();
			ret.setLocation(sourceMarker.getLocation());
		} else {
			ret = markerFactory.createMarker(sourceMarker.getLocation(), progressMonitor);
		}
		ret.setDate(new Date());
		ret.setPosition(sourceMarker.getPosition());		
		return ret;
	}	
			
}
