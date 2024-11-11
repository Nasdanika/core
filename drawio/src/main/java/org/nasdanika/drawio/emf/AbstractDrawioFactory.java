package org.nasdanika.drawio.emf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.model.ModelFactory;
import org.nasdanika.drawio.model.SemanticMapping;
import org.nasdanika.mapping.AbstractMappingFactory;
import org.nasdanika.mapping.ContentProvider;
import org.nasdanika.mapping.Mapper;
import org.nasdanika.persistence.ConfigurationException;
import org.xml.sax.SAXException;

/**
 * Base class for classes which map/transform Drawio model to a specific semantic model. For example, architecture model or flow/process model.
 * @author Pavel
 * @param <S> Semantic element type
 */
public abstract class AbstractDrawioFactory<T extends EObject> extends AbstractMappingFactory<Element, T> {
	
	private static final String PAGE_ELEMENT_PROPERTY = "page-element";
	private static final String TAG_SPEC_PROPERTY = "tag-spec";
	private static final String TOP_LEVEL_PAGE_PROPERTY = "top-level-page";

	public static final String DRAWIO_REPRESENTATION = "drawio";
	public static final String IMAGE_REPRESENTATION = "image";
	
	private static final String DATA_URI_PNG_PREFIX_NO_BASE_64 = "data:image/png,";
	private static final String DATA_URI_JPEG_PREFIX_NO_BASE_64 = "data:image/jpeg,";

	protected AbstractDrawioFactory(ContentProvider<Element> contentProvider, CapabilityLoader capabilityLoader) {
		super(contentProvider, capabilityLoader);
	}

	protected AbstractDrawioFactory(ContentProvider<Element> contentProvider) {
		super(contentProvider);
	}
	
	protected String getTopLevelPageProperty() {
		return TOP_LEVEL_PAGE_PROPERTY;
	}	
	
	protected String getTagSpecPropertyName() {
		return TAG_SPEC_PROPERTY;
	}
		
	/**
	 * Indicates a top level page which shall be a child of document element
	 * @param page
	 * @return
	 */
	public boolean isTopLevelPage(Page page) {
		String topLevelPageProperty = getTopLevelPageProperty();
		if (!Util.isBlank(topLevelPageProperty)) {
			Object topLevelPage = getContentProvider().getProperty(page, topLevelPageProperty);
			if (Boolean.TRUE.equals(topLevelPage)) {
				return true;
			}
			if (topLevelPage instanceof String && !Util.isBlank((String) topLevelPage)) {
				return "true".equals(((String) topLevelPage).trim());
			}
		}

		return getContentProvider().getParent(page) instanceof Document; // No links
	}
	
	/**
	 * Creates a document element from {@link Document}
	 * @param document
	 * @param parallel
	 * @param elementProvider
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	@org.nasdanika.common.Transformer.Factory
	public final T createDocumentElement(
			Document document,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {
		
		T documentTarget = createDocumentTarget(document, elementProvider, registry, progressMonitor);

		if (documentTarget instanceof org.nasdanika.ncore.ModelElement) {
			org.nasdanika.ncore.ModelElement dme = (org.nasdanika.ncore.ModelElement) documentTarget;
			if (Util.isBlank(dme.getUuid())) {
				dme.setUuid(UUID.randomUUID().toString());
			}
		}
		
		return documentTarget;
	}
	
	/**
	 * 
	 * @param document
	 * @param elementProvider
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	protected T createDocumentTarget(
			Document document,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {
		return null;
	};
	
	/**
	 * Creates a semantic model element depending on the type
	 * @param page
	 * @param parallel
	 * @param elementProvider
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	@org.nasdanika.common.Transformer.Factory
	public final T createModelElementTarget(
			org.nasdanika.drawio.ModelElement modelElement,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {

		return createTarget(
				modelElement,
				elementProvider,
				registry,
				progressMonitor);
	}		

//	@org.nasdanika.common.Transformer.Factory
//	public final T createTagSemanticElement(
//			Tag tag,
//			boolean parallel,
//			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
//			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
//			ProgressMonitor progressMonitor) {
//	
//		return createSemanticElement(
//				tag,
//				elementProvider,
//				registry,
//				progressMonitor);
//	
//	}
	
	// --- Wiring ---
	
	// --- Phase 0 - semantic elements using ref-id's, page element, selector, prototype, semantic-selector ---	
	
	/**
	 * Wires elements with ref-id property. Remaps which triggers wireContainment.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wireModelElementRefIds(
			org.nasdanika.drawio.ModelElement modelElement,
			Map<Element, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		return wireRefIds(modelElement, registry, pass, progressMonitor);
	}
		
//	/**
//	 * Wires elements with ref-id property. Remaps which triggers wireContainment.
//	 * @param modelElement
//	 * @param registry
//	 * @param pass
//	 * @param progressMonitor
//	 */
//	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
//	public final boolean wireTagRefIds(
//			org.nasdanika.drawio.Tag tag,
//			Map<EObject, EObject> registry,
//			int pass,
//			ProgressMonitor progressMonitor) {
//		
//		return wireRefIds(tag, registry, pass, progressMonitor);
//	}
	
	protected String getPageElementProperty() {
		return PAGE_ELEMENT_PROPERTY;
	}	
	
	public boolean isPageElement(Element obj) {	
		String pageElementProperty = getPageElementProperty();
		if (Util.isBlank(pageElementProperty)) {
			return false;
		}
		
		Object pev = getContentProvider().getProperty(obj, pageElementProperty);
		if (Boolean.TRUE.equals(obj)) {
			return true;
		}
		return pev instanceof String && !Util.isBlank((String) pev) && "true".equals(((String) pev).trim());
	}	
	
	/**
	 * Wires page elements without semantic element
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 * @return
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wirePageElement(
			org.nasdanika.drawio.ModelElement modelElement,
			Map<Element, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		if (!isPageElement(modelElement)) {
			return true;
		}
		Page page = modelElement.getModel().getPage();
		if (isTopLevelPage(page)) {
			return true;
		}
		
		Element pageParent = getContentProvider().getParent(page);
		T pageParentTarget = registry.get(pageParent);		
		if (pageParentTarget == null) {
			return false; // Not there yet
		}
		registry.put(modelElement, pageParentTarget);
		return true;
	}
	
	/**
	 * Wires elements with selector property. Remaps which triggers wireContainment.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wireModelElementLinkTarget(
			org.nasdanika.drawio.ModelElement modelElement,
			Map<Element, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		LinkTarget linkTarget = modelElement.getLinkTarget();
		if (linkTarget instanceof org.nasdanika.drawio.ModelElement) {
			T target = registry.get(linkTarget);
			if (target == null) {
				return false; // Not there yet
			}
			registry.put(modelElement, target); // Тriggers a new wave of wiring
		}
		
		return true;						
	}	
	
	/**
	 * Wires elements with selector property. Remaps which triggers wireContainment.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wireModelElementSelector(
			org.nasdanika.drawio.ModelElement modelElement,
			Map<Element, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		return wireSelector(modelElement, registry, pass, progressMonitor);
	}	
	
//	/**
//	 * Wires elements with selector property. Remaps which triggers wireContainment.
//	 * @param modelElement
//	 * @param registry
//	 * @param pass
//	 * @param progressMonitor
//	 */
//	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
//	public final boolean wireTagSelector(
//			org.nasdanika.drawio.Tag tag,
//			Map<EObject, EObject> registry,
//			int pass,
//			ProgressMonitor progressMonitor) {
//		return wireSelector(tag, registry, pass, progressMonitor);
//	}
	
	/**
	 * Wires elements with prototype property. Remaps which triggers wireContainment.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wireModelElementPrototype(
			org.nasdanika.drawio.ModelElement modelElement,
			Map<Element, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		return wirePrototype(modelElement, registry, pass, progressMonitor);
	}
		
//	/**
//	 * Wires elements with prototype property. Remaps which triggers wireContainment.
//	 * @param modelElement
//	 * @param registry
//	 * @param pass
//	 * @param progressMonitor
//	 */
//	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
//	public final boolean wireTagPrototype(
//			org.nasdanika.drawio.Tag tag,
//			Map<Element, T> registry,
//			int pass,
//			ProgressMonitor progressMonitor) {
//		
//		return wirePrototype(tag, registry, pass, progressMonitor);
//	}
	
	/**
	 * Wires elements with semantic selector property. Remaps which triggers wireContainment.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wireModelElementTargetSelector(
			org.nasdanika.drawio.ModelElement modelElement,
			Map<Element, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		return wireTargetSelector(modelElement, registry, pass, progressMonitor);
	}
	
//	/**
//	 * Wires elements with semantic selector property. Remaps which triggers wireContainment.
//	 * @param modelElement
//	 * @param registry
//	 * @param pass
//	 * @param progressMonitor
//	 */
//	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
//	public final boolean wireTagSemanticSelector(
//			org.nasdanika.drawio.Tag tag,
//			Map<EObject, EObject> registry,
//			int pass,
//			ProgressMonitor progressMonitor) {
//
//		return wireSemanticSelector(tag, registry, pass, progressMonitor);
//	}	
		
	/**
	 * Wires document to the page element of the top level page.
	 * @param document
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 * @return
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public final boolean wireDocument(
			org.nasdanika.drawio.Document document,
			Map<Element, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		for (Page page: document.getPages()) {
			if (isTopLevelPage(page)) {
				Optional<org.nasdanika.drawio.ModelElement> pageElementOpt = page.stream()
					.filter(org.nasdanika.drawio.ModelElement.class::isInstance)
					.map(org.nasdanika.drawio.ModelElement.class::cast)
					.filter(this::isPageElement)
					.findAny();
					
				if (pageElementOpt.isPresent()) {
					org.nasdanika.drawio.ModelElement pageElement = pageElementOpt.get();
					T pageElementTarget = registry.get(pageElement);
					if (pageElementTarget == null) {
						return false; // Not there yet
					}
					registry.put(document, pageElementTarget);
					return true; 
				}
			}
		}
		
		return true;
	}
	
	// --- Phase 1: Mapping "reference" elements
			
	@SuppressWarnings("unchecked")
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class, phase = 1)
	public final void wireTargetReferences(
			org.nasdanika.drawio.Layer drawioModelElement,
			Map<Element, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		String referenceProperty = getReferenceProperty();
		if (!Util.isBlank(referenceProperty)) {		
			Object referenceSpec = getContentProvider().getProperty(drawioModelElement, referenceProperty);			
			if (referenceSpec != null && !(referenceSpec instanceof String && Util.isBlank((String) referenceSpec))) {
				ReferenceMapper referenceMapper = new ReferenceMapper(referenceSpec, drawioModelElement);
				List<Element> ancestorsPath = new ArrayList<>();
				Z: for (Element ancestor = getContentProvider().getParent(drawioModelElement); ancestor != null; ancestor = getContentProvider().getParent(ancestor)) {
					ancestorsPath.add(ancestor);					
					for (T ancestorTarget: mapper.select(ancestor, registry, progressMonitor)) {						
						if (referenceMapper.matchAncestorTarget(ancestorTarget, ancestorsPath, registry, drawioModelElement)) {
							T refObj = referenceMapper.getAncestorTargetRefObj(ancestorTarget, ancestorsPath, registry, drawioModelElement); 
							if (refObj != null) {
								EClass eClass = refObj.eClass();
								String referenceName = referenceMapper.getReferenceName();
								EStructuralFeature feature = eClass.getEStructuralFeature(referenceName);
								if (feature == null) {
									throw new ConfigurationException("Feature " + referenceName + " not found in " + eClass.getName(), drawioModelElement); 
								} else if (feature instanceof EReference) {							
									LinkedList<Element> sourcePath = new LinkedList<>();
									sourcePath.add(drawioModelElement);
									Comparator<Object> comparator = referenceMapper.getComparator(ancestorTarget, registry);
									for (T target: referenceMapper.getElements(sourcePath, registry, new HashSet<>()::add, drawioModelElement, progressMonitor)) {
										if (target != null && feature.getEType().isInstance(target)) {
											if (feature.isMany()) {
												List<EObject> fvl = (List<EObject>) ancestorTarget.eGet(feature);
												if (comparator == null || fvl.isEmpty()) {										
													fvl.add(target);
												} else {
													// Iterating and comparing
													boolean added = false;
													for (int i = 0; i < fvl.size(); ++i) {
														Object fvle = fvl.get(i);
														if (comparator.compare(target, fvle) < 0) {
															fvl.add(i, target);
															added = true;
															break;
														}
													}
													if (!added) {
														fvl.add(target);
													}
												}
											} else {
												ancestorTarget.eSet(feature, target);										
											}									
										}
									}
								} else {
									throw new ConfigurationException("Not a reference: " + referenceName + " in " + eClass.getName(), drawioModelElement); 									
								}
								break Z;								
							}
						}
					}
				}
			}
		}
	}

	// --- Phase 2: Mapping features and adding representations ---
	
	/**
	 * Adds contained elements into containment references.
	 * Traverses page links.
	 * 
	 * @param document
	 * @param pkg
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(phase = 2)
	public final void mapDocument(
			Document document,
			T documentTarget,
			Map<Element, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		Mapper<Element,T> mapper = getMapper(pass);
		if (mapper != null) {
			mapper.wire(document, registry, progressMonitor);
		}
	}
	
	@org.nasdanika.common.Transformer.Wire(phase = 2)
	public final void addDocumentReprentations(
			Document document,
			T target,
			Map<Element, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		if (target instanceof org.nasdanika.ncore.ModelElement) {
			org.nasdanika.ncore.ModelElement targetModelElement = (org.nasdanika.ncore.ModelElement) target;
			for (Page page: document.getPages()) {
				if (isTopLevelPage(page)) {
					addRepresentationPage(
							targetModelElement, 
							page, 
							registry, 
							progressMonitor);
					
				}
			}
		}
	}	
	
	protected void addRepresentationPage(
			org.nasdanika.ncore.ModelElement targetModelElement, 
			Page page,
			Map<Element, T> registry,
			ProgressMonitor progressMonitor) {		
		
		page.accept(pageElement -> {
			if (pageElement instanceof Element) {
				filterRepresentation((Element) pageElement, registry, progressMonitor);
			}
		});
		String targetRepresentation = targetModelElement.getRepresentations().get(DRAWIO_REPRESENTATION);
		try {
			Document representationDocument;
			if (Util.isBlank(targetRepresentation)) {
				representationDocument = Document.create(true, page.getDocument().getURI());
			} else {
				representationDocument = Document.load(URI.createURI(targetRepresentation));
			}
			for (Page serdp: representationDocument.getPages()) {
				if (serdp.getId().equals(page.getId())) {
					return;
				}
			}
			representationDocument.getPages().add(page);
			targetModelElement.getRepresentations().put(
					DRAWIO_REPRESENTATION, 
					representationDocument.toDataURI(true).toString());
		} catch (IOException | ParserConfigurationException | SAXException | TransformerException e) {
			throw new ConfigurationException("Error filtering representation: " + e, e, page); 				
		}			
	}
	
	/**
	 * Sets semantic UUIDs for the contents of eObj
	 * @param documentElement Element of drawio document to set semantic UUID property 
	 * @param modelPage Element of drawio model to iterate over contents to match id with the document element and get a semantic element from the registry
	 * @param registry
	 * @return
	 */
	protected void filterRepresentation(
			Element element, 
			Map<Element, T> registry,
			ProgressMonitor progressMonitor) {
		
		filterRepresentationElement(element, registry, progressMonitor);
		
		if (element instanceof org.nasdanika.drawio.ModelElement) {
			org.nasdanika.drawio.ModelElement modelElement = (org.nasdanika.drawio.ModelElement) element;
			
			// Expanding placeholders for label and tooltip
			if ("1".equals(modelElement.getProperty("placeholders"))) {
				String label = modelElement.getLabel();
				modelElement.setLabel(label);
				
				String tooltip = modelElement.getTooltip();
				modelElement.setTooltip(tooltip);				
			}
		}
	}
		
	public static final String SEMANTIC_UUID_PROPERTY = "semantic-uuid";	

	protected String getSemanticUUIDPropertyName() {
		return SEMANTIC_UUID_PROPERTY;
	}
	
	/**
	 * Service/capability interface for filtering representation elements
	 */
	public interface RepresentationElementFilter {
		
		void filterRepresentationElement(
				Element element, 
				Map<Element, ? extends EObject> registry,
				ProgressMonitor progressMonitor);	
		
	}	
	
	protected Requirement<? extends AbstractDrawioFactory<T>, RepresentationElementFilter> createRepresentationElementFilterRequirement() {
		return ServiceCapabilityFactory.createRequirement(RepresentationElementFilter.class, null, this);
	}
			
	/**
	 * Override to implement filtering of a representation element. 
	 * For example, if an element represents a processing unit, its background color or image can be modified depending on the load - red for overloaded, green for OK, grey for planned offline.
	 * When this method is called, the semantic element is not yet configured from the representation element.    
	 * This implementation sets semantic UUID and carries over a tooltip for representations which don't have a tooltip.
	 * @param representationElement
	 * @param registry
	 * @param progressMonitor
	 */
	protected void filterRepresentationElement(
			Element element, 
			Map<Element, T> registry,
			ProgressMonitor progressMonitor) {
		
		if (element instanceof org.nasdanika.drawio.ModelElement) {
			org.nasdanika.drawio.ModelElement modelElement = (org.nasdanika.drawio.ModelElement) element;
			String semanticUUIDPropertyName = getSemanticUUIDPropertyName();
			T target = registry.get(element);
			if (!Util.isBlank(semanticUUIDPropertyName) && target instanceof org.nasdanika.ncore.ModelElement) {
				String uuid = ((org.nasdanika.ncore.ModelElement) target).getUuid();
				if (!Util.isBlank(uuid)) {
					modelElement.setProperty(semanticUUIDPropertyName, uuid);
				}
			}
			
			if (Util.isBlank(modelElement.getTooltip())) {
				registry
					.entrySet()
					.stream()
					.filter(e -> e.getValue() == target)
					.filter(e -> e.getKey() instanceof org.nasdanika.drawio.model.ModelElement)
					.map(e -> ((org.nasdanika.drawio.model.ModelElement) e.getKey()).getTooltip())
					.filter(t -> !Util.isBlank(t))
					.findFirst()
					.ifPresent(modelElement::setTooltip);					
			}
		}
		
		if (element instanceof Element) {
			// Capabilities
			if (capabilityLoader != null) {
				Requirement<? extends AbstractDrawioFactory<T>, RepresentationElementFilter> requirement = createRepresentationElementFilterRequirement();
				Iterable<CapabilityProvider<Object>> cpi = capabilityLoader.load(requirement, progressMonitor);
				for (CapabilityProvider<Object> cp: cpi) {
					cp.getPublisher().subscribe(filter -> ((RepresentationElementFilter) filter).filterRepresentationElement((Element) element, registry, progressMonitor));
				}
			}
		}
	}
	
	/**
	 * Default base URI for the Drawio application to resolve library relative URL's.
	 */
	public static final URI DEFAULT_APP_BASE = URI.createURI("https://app.diagrams.net/");
	
	/**
	 * Application base for resolving relative image URL's. 
	 * This implementation returns DEFAULT_APP_BASE. 
	 * Override to customize for different (e.g. intranet) installations.
	 * App sources - https://github.com/jgraph/drawio/tree/dev/src/main/webapp.
	 * For the purposes of serving images and a diagram editor the web app can be deployed as a static site.
	 * It can also be deployed as a Docker container - https://www.drawio.com/blog/diagrams-docker-app, https://hub.docker.com/r/jgraph/drawio 
	 * @return
	 */
	protected URI getAppBase() {
		return DEFAULT_APP_BASE;
	}
		
	@org.nasdanika.common.Transformer.Wire(phase = 2)
	public final void mapModelElement(
			org.nasdanika.drawio.ModelElement modelElement,
			T target,
			Map<Element, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
						
		Mapper<Element,T> mapper = getMapper(pass);
		if (mapper != null) {
			mapper.wire(modelElement, registry, progressMonitor);
		}
		
		boolean isPageElement = isPageElement(modelElement);
		
		// Semantic mapping
		if (target instanceof org.nasdanika.drawio.model.SemanticElement) {
			SemanticMapping semanticMapping = createSemanticMapping();
			for (Element ancestor = getContentProvider().getParent(modelElement); ancestor != null; ancestor = getContentProvider().getParent(ancestor)) {
				if (ancestor instanceof Page) {
					semanticMapping.setPageID(((Page) ancestor).getId());						
				} else if (ancestor instanceof Document) {
					URI documentURI = ((Document) ancestor).getURI();
					if (documentURI != null) {
						semanticMapping.setDocumentURI(documentURI.toString());
					}
				}
			}
			semanticMapping.setModelElementID(modelElement.getId());
			semanticMapping.setPageElement(isPageElement);
			((org.nasdanika.drawio.model.SemanticElement) target).getSemanticMappings().add(semanticMapping);
		}		
		
		if (target instanceof org.nasdanika.ncore.ModelElement) {
			// Page representation
			org.nasdanika.ncore.ModelElement targetModelElement = (org.nasdanika.ncore.ModelElement) target;
			LinkTarget linkTarget = modelElement.getLinkTarget();
			if (linkTarget instanceof Page) {
				addRepresentationPage(
						targetModelElement, 
						(Page) linkTarget, 
						registry, 
						progressMonitor);
				
			}
			if (isPageElement) {
				for (Element ancestor = getContentProvider().getParent(modelElement); ancestor != null; ancestor = getContentProvider().getParent(ancestor)) {
					if (ancestor instanceof Page) {
						addRepresentationPage(
								targetModelElement, 
								(Page) ancestor, 
								registry, 
								progressMonitor);
					}
				}
			}
			
			// Image
			Map<String, String> style = modelElement.getStyle();
			String image = style.get("image");
			if (!Util.isBlank(image)) {
				// Drawio does not add ;base64 to the image URL, browsers don't understand. Fixing it here.
				if (image.startsWith(DATA_URI_PNG_PREFIX_NO_BASE_64)) {
					int insertIdx = DATA_URI_PNG_PREFIX_NO_BASE_64.length() - 1;
					image = image.substring(0, insertIdx) + ";base64" + image.substring(insertIdx);
				} else if (image.startsWith(DATA_URI_JPEG_PREFIX_NO_BASE_64)) {
					int insertIdx = DATA_URI_JPEG_PREFIX_NO_BASE_64.length() - 1;
					image = image.substring(0, insertIdx) + ";base64" + image.substring(insertIdx);
				} else {
					URI imageURI = URI.createURI(image);
					if (imageURI.isRelative()) {
						URI appBase = getAppBase();
						if (appBase != null && !appBase.isRelative()) {
							imageURI = imageURI.resolve(appBase);
						}
					}
					image = imageURI.toString();
				}

				targetModelElement.getRepresentations().put(IMAGE_REPRESENTATION,	image);					
			}
		}		
	}	

	protected SemanticMapping createSemanticMapping() {
		return ModelFactory.eINSTANCE.createSemanticMapping();
	}
	
	// --- Phase 3: mapping features of null semantic elements such as pass-through connections
	
	/**
	 * Feature maps null semantic elements, which is needed for connections as they might be pass-through.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class, phase = 3)
	public final boolean featurMapNulls(
			org.nasdanika.drawio.ModelElement modelElement,
			Map<Element, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		Mapper<Element,T> mapper = getMapper(pass);
		if (mapper != null) {
			mapper.wire(modelElement, registry, progressMonitor);
		}		
		return true;
	}
	
	// --- Phase 4: Configuration ---
	
	/**
	 * Wires document configuration
	 */
	@org.nasdanika.common.Transformer.Wire(phase = 4)
	public final void wireDocumentConfiguration(
			Document document,
			T documentTarget,
			Map<Element, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		
		configureDocumentElement(
				document,
				documentTarget,
				registry,
				progressMonitor);
	}
	
	protected void configureDocumentElement(
			Document document,
			T documentTarget,
			Map<Element,T> registry,
			ProgressMonitor progressMonitor) {
		
		if (documentTarget instanceof org.nasdanika.ncore.Marked) {
			((org.nasdanika.ncore.Marked) documentTarget).mark(document);
		}		
	}
	
	@org.nasdanika.common.Transformer.Wire(phase = 4)
	public final void wireModelElementConfiguration(
			org.nasdanika.drawio.ModelElement modelElement,
			T target,
			Map<Element,T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		configureTarget(
				modelElement,
				target,
				registry,
				false,
				progressMonitor);
	}
	
//	@org.nasdanika.common.Transformer.Wire(phase = 4)
//	public final void wireTagConfiguration(
//			Tag tag,
//			T target,
//			Map<Element,T> registry,
//			int pass,
//			ProgressMonitor progressMonitor) {
//		
//		configureTarget(
//				tag,
//				target,
//				registry,
//				false,
//				progressMonitor);
//	}
	
	
	// --- Phase 5: Operations
	
	/**
	 * Invokes {@link EOperation}s of the semantic element. 	
	 * @param diagramElement
	 * @param semanticElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(phase = 5)
	public final boolean mapOperations(
			Element element,
			T target,
			Map<Element,T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		return super.mapOperations(element, target, registry, pass, progressMonitor);
	}
	
	// --- Phase 6: Invoke
	
	@org.nasdanika.common.Transformer.Wire(phase = 6, targetType = Void.class)
	public final boolean wireInvoke(
			Element element,
			Map<Element,T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		return invoke(element, null, registry, pass, progressMonitor);
	}
	
	
	@org.nasdanika.common.Transformer.Wire(phase = 6)
	public final boolean wireInvoke(
			Element element,
			T target,
			Map<Element, T> registry,
			int pass,
			ProgressMonitor progressMonitor) {
	
		return invoke(element, target, registry, pass, progressMonitor);
	}
		
}
