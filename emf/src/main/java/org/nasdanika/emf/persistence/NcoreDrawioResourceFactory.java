package org.nasdanika.emf.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;

import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.emf.DrawioEObjectFactory;
import org.nasdanika.drawio.emf.DrawioResource;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.emf.AbstractEObjectFactoryProcessorResource;
import org.nasdanika.graph.processor.emf.SemanticProcessor;
import org.nasdanika.ncore.Marked;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcorePackage;

/**
 * This factory uses {@link DrawioResource} with {@link DrawioEObjectFactory}. 
 * It injects name, description, and markers in configureSemanticElement(). It 
 * @author Pavel
 *
 * @param <T>
 */
public abstract class NcoreDrawioResourceFactory<T extends EObject> extends ResourceFactoryImpl {
	
	private class NcoreDrawioResource extends DrawioResource<SemanticProcessor<T>, T> implements AbstractEObjectFactoryProcessorResource<T> {

		private DrawioEObjectFactory<T, SemanticProcessor<T>> processorFactory;
		private ProgressMonitor progressMonitor;

		protected NcoreDrawioResource(URI uri, DrawioEObjectFactory<T, SemanticProcessor<T>> processorFactory, ProgressMonitor progressMonitor) {
			super(uri);
			this.processorFactory = processorFactory;
			this.progressMonitor = progressMonitor;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void setParent(T parent) {
			for (EObject root: new ArrayList<>(getContents())) {
				processorFactory.setParent((T) root, parent, getProgressMonitor());
			}
		}

		@Override
		protected DrawioEObjectFactory<T, SemanticProcessor<T>> getProcessorFactory() {
			return processorFactory;
		}
		
		@Override
		protected ProgressMonitor getProgressMonitor() {
			return progressMonitor;
		};
		
	}
	
	@Override
	public Resource createResource(URI uri) {
		return new NcoreDrawioResource(uri, createProcessorFactory(uri), getProgressMonitor(uri));
	}
		
	protected DrawioEObjectFactory<T, SemanticProcessor<T>> createProcessorFactory(URI uri) {
		return new DrawioEObjectFactory<T, SemanticProcessor<T>>() {

			@Override
			protected ResourceSet getResourceSet() {
				return NcoreDrawioResourceFactory.this.getResourceSet();
			}

			@Override
			protected SemanticProcessor<T> createProcessor(ProcessorConfig<SemanticProcessor<T>> config, Collection<T> semanticElements, ProgressMonitor progressMonitor) {
				return NcoreDrawioResourceFactory.this.createProcessor(uri, config, semanticElements, progressMonitor);
			}

			@Override
			protected URI getBaseURI() {
				return uri;
			}
			
			@Override
			protected Collection<T> createSemanticElements(ProcessorConfig<SemanticProcessor<T>> config, ProgressMonitor progressMonitor) {
				Collection<T> semanticElements = super.createSemanticElements(config, progressMonitor);
				Collection<T> configuredSemanticElements = NcoreDrawioResourceFactory.this.configureSemanticElements(uri, config, semanticElements, progressMonitor);
				return configuredSemanticElements;
			}
			
			/**
			 * Injecting representations after all processors have been created. 
			 */
			@Override
			public Map<Element, ProcessorInfo<SemanticProcessor<T>>> createProcessors(Stream<? extends Element> elements, ProgressMonitor progressMonitor) {
				Map<Element, ProcessorInfo<SemanticProcessor<T>>> registry = super.createProcessors(elements, progressMonitor);
				String representationPropertyName = getRepresentationPropertyName(uri);
								
				registry.entrySet().forEach(re -> configureRegistryEntry(re.getKey(), re.getValue(), getPropertyValue(re.getKey(), representationPropertyName)));
				return registry;
			}
			
		};
	}

	/**
	 * Injects encoded document into semantic elements of model elements with representation property.
	 * @param element
	 * @param info
	 */
	protected void configureRegistryEntry(Element element, ProcessorInfo<SemanticProcessor<T>> info, String representationKey) {
		if (info != null && !Util.isBlank(representationKey)) {
			SemanticProcessor<T> processor = info.getProcessor();
			if (processor != null && element instanceof ModelElement) {
				Collection<T> semanticElements = processor.getSemanticElements();
				if (semanticElements != null && !semanticElements.isEmpty()) {
					for (T semanticElement: semanticElements) {
						if (semanticElement instanceof org.nasdanika.ncore.ModelElement) {
							Document document = ((ModelElement) element).getModel().getPage().getDocument();
							try {
								((org.nasdanika.ncore.ModelElement) semanticElement).getRepresentations().put(representationKey, document.toDataURI(true).toString());
							} catch (TransformerException | IOException e) {
								throw new NasdanikaException("Error encoding document: " + e, e);
							}
						}
					}
				}
			}
		}
	}
	
	protected abstract ResourceSet getResourceSet();
	
	protected abstract ProgressMonitor getProgressMonitor(URI uri);
	
	protected SemanticProcessor<T> createProcessor(URI uri, ProcessorConfig<SemanticProcessor<T>> config, Collection<T> semanticElements, ProgressMonitor progressMonitor) {		
		
		return new SemanticProcessor<T>() {

			@Override
			public Collection<T> getSemanticElements() {
				return semanticElements;
			}
			
		};
	}	

	protected MarkerFactory getMarkerFactory() {
		return MarkerFactory.INSTANCE;
	}
		
	protected String getMarkerPosition(Element element) {
		if (element instanceof Page) {
			return "name: " + ((Page) element).getName() + ", id: " + ((Page) element).getId();
		} else if (element instanceof ModelElement) {
			StringBuilder positionBuilder = new StringBuilder();
			ModelElement modelElement = (ModelElement) element;
			Page page = modelElement.getModel().getPage();
			positionBuilder.append("page-name: " + page.getName() + ", page-id: " + page.getId());
			String label = modelElement.getLabel();
			if (!Util.isBlank(label)) {
				positionBuilder.append(", label: "+ label);
			}
			String id = modelElement.getId();
			if (!Util.isBlank(id)) {
				positionBuilder.append(", id:" + id);
			}
			return positionBuilder.toString();
		}		
		return null;
	}	
		
	protected Collection<T> configureSemanticElements(
			URI uri,
			ProcessorConfig<SemanticProcessor<T>> config, 
			Collection<T> semanticElements, 
			ProgressMonitor progressMonitor) {

		if (config == null) {
			return semanticElements;
		}
		Element element = config.getElement();
		if (semanticElements == null) {
			return semanticElements;
		}
		for (T semanticElement: semanticElements) {
			if (uri != null && semanticElement instanceof Marked) {
				org.nasdanika.ncore.Marker marker = getMarkerFactory().createMarker(uri.toString(), progressMonitor);
				marker.setPosition(getMarkerPosition(element));
				((Marked) semanticElement).getMarkers().add(marker);
			}
			
			if (element instanceof ModelElement) {
				ModelElement modelElement = (ModelElement) element;
				String tooltip = modelElement.getTooltip();
				if (!Util.isBlank(tooltip) && semanticElement instanceof org.nasdanika.ncore.ModelElement && !semanticElement.eIsSet(NcorePackage.Literals.MODEL_ELEMENT__DESCRIPTION)) {
					((org.nasdanika.ncore.ModelElement) semanticElement).setDescription(tooltip);				
				}
				
				String label = modelElement.getLabel();
				if (!Util.isBlank(label) && semanticElement instanceof NamedElement && !semanticElement.eIsSet(NcorePackage.Literals.NAMED_ELEMENT__NAME)) {
					((NamedElement) semanticElement).setName(label);				
				}	
				
				if (semanticElement instanceof org.nasdanika.ncore.ModelElement) {
					String semanticUuid = ((org.nasdanika.ncore.ModelElement) semanticElement).getUuid();
					if (!Util.isBlank(semanticUuid)) {
						modelElement.setProperty("semantic-uuid", semanticUuid);
					}
				}
			}
			
		}
		
		return semanticElements;		
	}
	
	/**
	 * If this property is set and the semantic element is {@link org.nasdanika.ncore.ModelElement} then {@link Document} is encoded and stored into the representation with the specified name.
	 * @param uri Resource {@link URI}
	 * @return
	 */
	protected String getRepresentationPropertyName(URI uri) {
		return "representation";
	}

}