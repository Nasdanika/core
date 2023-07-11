package org.nasdanika.emf.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.jsoup.Jsoup;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.emf.DrawioEObjectFactory;
import org.nasdanika.drawio.emf.DrawioResource;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.emf.AbstractEObjectFactoryProcessorResource;
import org.nasdanika.graph.processor.emf.SemanticProcessor;
import org.nasdanika.ncore.Documented;
import org.nasdanika.ncore.Marked;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.util.NcoreUtil;

/**
 * This factory uses {@link DrawioResource} with {@link DrawioEObjectFactory}. 
 * It injects name, description, and markers in configureSemanticElement(). 
 * @author Pavel
 *
 * @param <T>
 */
public abstract class NcoreDrawioResourceFactory<T extends EObject, R> extends ResourceFactoryImpl {
	
	public static final String SEMANTIC_UUID_KEY = "semantic-uuid";
	private boolean parallel;	
	
	public NcoreDrawioResourceFactory(boolean parallel) {
		this.parallel = parallel;
	}
	
	private class NcoreDrawioResource extends DrawioResource<SemanticProcessor<T>, T, R> implements AbstractEObjectFactoryProcessorResource<T> {

		private DrawioEObjectFactory<T, SemanticProcessor<T>, R> processorFactory;
		private ProgressMonitor progressMonitor;

		protected NcoreDrawioResource(URI uri, boolean parallel, DrawioEObjectFactory<T, SemanticProcessor<T>, R> processorFactory, ProgressMonitor progressMonitor) {
			super(uri, parallel);
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
		protected DrawioEObjectFactory<T, SemanticProcessor<T>, R> getProcessorFactory() {
			return processorFactory;
		}
		
		@Override
		protected ProgressMonitor getProgressMonitor() {
			return progressMonitor;
		}

		@Override
		protected ProcessorInfo<SemanticProcessor<T>, R> getProcessorInfo(R registry, Element element) {
			return NcoreDrawioResourceFactory.this.getProcessorInfo(registry, element);
		}

		@Override
		protected Stream<T> getRegistrySemanticElements(R registry) {
			return NcoreDrawioResourceFactory.this.getRegistrySemanticElements(registry);
		};
		
	}
	
	protected abstract ProcessorInfo<SemanticProcessor<T>, R> getProcessorInfo(R registry, Element element);

	protected abstract Stream<T> getRegistrySemanticElements(R registry);
	
	protected abstract R createRegistry(Map<Element, ProcessorInfo<SemanticProcessor<T>, R>> registry);
	
	protected abstract Collection<Entry<Element, ProcessorInfo<SemanticProcessor<T>, R>>> registryEntries(R registry);
	
	@Override
	public Resource createResource(URI uri) {
		return new NcoreDrawioResource(uri, parallel, createProcessorFactory(uri), getProgressMonitor(uri));
	}
	
	/**
	 * Allows to override the default behavior of using spec, spec-uri, and semantic-uri properties for creation of semantic elements.
	 * @param config
	 * @param progressMonitor
	 * @param fallback
	 * @return
	 */
	protected Collection<T> createSemanticElements(
			ProcessorConfig<SemanticProcessor<T>, R> config, 
			ProgressMonitor progressMonitor, 
			BiFunction<ProcessorConfig<SemanticProcessor<T>, R>, ProgressMonitor, Collection<T>> defaultFactory) {
		return defaultFactory.apply(config, progressMonitor);
	}
		
	protected DrawioEObjectFactory<T, SemanticProcessor<T>, R> createProcessorFactory(URI uri) {
		return new DrawioEObjectFactory<T, SemanticProcessor<T>, R>() {

			@Override
			protected ResourceSet getResourceSet() {
				return NcoreDrawioResourceFactory.this.getResourceSet();
			}

			@Override
			protected SemanticProcessor<T> createProcessor(ProcessorConfig<SemanticProcessor<T>, R> config, Collection<T> semanticElements, ProgressMonitor progressMonitor) {
				return NcoreDrawioResourceFactory.this.createProcessor(uri, config, semanticElements, progressMonitor);
			}

			@Override
			protected URI getBaseURI() {
				return uri;
			}
			
			@Override
			protected Collection<T> createSemanticElements(ProcessorConfig<SemanticProcessor<T>, R> config, ProgressMonitor progressMonitor) {
				Collection<T> semanticElements = NcoreDrawioResourceFactory.this.createSemanticElements(config, progressMonitor, super::createSemanticElements);
				Collection<T> configuredSemanticElements = NcoreDrawioResourceFactory.this.configureSemanticElements(uri, config, semanticElements, progressMonitor);
				return configuredSemanticElements;
			}
			
			@Override
			public R createProcessors(Stream<? extends Element> elements, boolean parallel, ProgressMonitor progressMonitor) {
				R registry = super.createProcessors(elements, parallel, progressMonitor);
								
				String representationPropertyName = getRepresentationPropertyName(uri);
								
				registryEntries(registry).forEach(re -> configureRegistryEntry(re.getKey(), re.getValue(), getPropertyValue(re.getKey(), representationPropertyName)));
				return registry;
			}

			@Override
			public R createRegistry(Map<Element, ProcessorInfo<SemanticProcessor<T>, R>> registry) {
				return NcoreDrawioResourceFactory.this.createRegistry(registry);
			}

			@Override
			protected ProcessorInfo<SemanticProcessor<T>, R> getProcessorInfo(R registry, Element element) {
				return NcoreDrawioResourceFactory.this.getProcessorInfo(registry, element);
			}

			@Override
			protected Collection<Entry<Element, ProcessorInfo<SemanticProcessor<T>, R>>> registryEntries(R registry) {
				return NcoreDrawioResourceFactory.this.registryEntries(registry);
			}
			
		};
	}
	
	

	/**
	 * Injects encoded document into semantic elements of model elements with representation property.
	 * Sorts references.
	 * @param element
	 * @param info
	 */
	protected void configureRegistryEntry(Element element, ProcessorInfo<SemanticProcessor<T>, R> info, String representationKey) {
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
		
		// TODO - sort - YAML map of references to comparators. Comparator either String - comparator name or a map - name to configuration. 
		// Use ECollections to sort. Expression (SPEL) comparator.

	}
	
	protected abstract ResourceSet getResourceSet();
	
	protected abstract ProgressMonitor getProgressMonitor(URI uri);
	
	protected SemanticProcessor<T> createProcessor(URI uri, ProcessorConfig<SemanticProcessor<T>, R> config, Collection<T> semanticElements, ProgressMonitor progressMonitor) {		
		if (semanticElements == null || semanticElements.isEmpty()) {
			return null; // For pass-through to parent.
		}
		
		// To avoid concurrent modification exception for situations when processor is backed by a resource and semantic elements get reparented and move out of the resource. 
		Collection<T> semanticElementsSnapshot = Collections.unmodifiableCollection(new ArrayList<>(semanticElements));
		
		return new SemanticProcessor<T>() {

			@Override
			public Collection<T> getSemanticElements() {
				return semanticElementsSnapshot;
			}
			
		};
	}	

	protected MarkerFactory getMarkerFactory() {
		return MarkerFactory.INSTANCE;
	}
		
	protected Collection<T> configureSemanticElements(
			URI uri,
			ProcessorConfig<SemanticProcessor<T>, R> config, 
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
			if (uri != null && semanticElement instanceof Marked && element instanceof Marked) {
				for (org.nasdanika.persistence.Marker elementMarker: ((Marked) element).getMarkers()) {
					org.nasdanika.ncore.Marker marker = getMarkerFactory().createMarker(elementMarker.getLocation(), progressMonitor);
					marker.setPosition(elementMarker.getPosition());
					((Marked) semanticElement).getMarkers().add(marker);
				}
			}
			
			if (element instanceof ModelElement) {
				ModelElement modelElement = (ModelElement) element;
				String tooltip = modelElement.getTooltip();
				if (!Util.isBlank(tooltip)) {
					if (semanticElement instanceof org.nasdanika.ncore.ModelElement && !semanticElement.eIsSet(NcorePackage.Literals.MODEL_ELEMENT__DESCRIPTION)) {
						String firstTooltipSentence = org.nasdanika.common.Util.firstPlainTextSentence(tooltip, 50, 250);
						((org.nasdanika.ncore.ModelElement) semanticElement).setDescription(firstTooltipSentence);						
					}
					if (semanticElement instanceof Documented && ((Documented) semanticElement).getDocumentation().isEmpty()) {
						((Documented) semanticElement).getDocumentation().add(NcoreUtil.wrapString("<pre style='white-space:pre-wrap'>" + System.lineSeparator() + tooltip + System.lineSeparator() + "</pre>"));
					}
				}
				
				String label = modelElement.getLabel();
				if (!Util.isBlank(label) && semanticElement instanceof NamedElement && !semanticElement.eIsSet(NcorePackage.Literals.NAMED_ELEMENT__NAME)) {
					((NamedElement) semanticElement).setName(Jsoup.parse(label).text());				
				}
				
				String id = modelElement.getId();
				if (!Util.isBlank(id) && semanticElement instanceof org.nasdanika.ncore.Composite && !semanticElement.eIsSet(NcorePackage.Literals.COMPOSITE__ID)) {
					((org.nasdanika.ncore.Composite) semanticElement).setId(id);				
				}
				
				if (semanticElement instanceof org.nasdanika.ncore.ModelElement) {
					String semanticUuid = ((org.nasdanika.ncore.ModelElement) semanticElement).getUuid();
					if (!Util.isBlank(semanticUuid)) {
						modelElement.setProperty(SEMANTIC_UUID_KEY, semanticUuid);
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
