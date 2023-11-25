package org.nasdanika.drawio.model.util;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Mapper;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.model.Document;
import org.nasdanika.drawio.model.Page;
import org.nasdanika.drawio.model.Root;
import org.nasdanika.ncore.Marker;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.Marked;

/**
 * Base class for classes which map/transform Drawio model to a specific semantic model. For example, architecture model or flow/process model.
 * @author Pavel
 *
 * @param <D> Document element type
 * @param <S> Semantic model element type
 */
public abstract class AbstractDrawioFactory<D extends EObject, S extends EObject> {
	
	public String getPropertyNamespace() {
		return "";
	}
	
	protected String getTopLevelPageProperty() {
		return getPropertyNamespace() + "top-level-page";
	}		
	
	/**
	 * Indicates a top level page which shall be a child of document element
	 * @param page
	 * @return
	 */
	public boolean isTopLevelPage(Page page) {
		String topLevelPageProperty = getTopLevelPageProperty();
		if (!Util.isBlank(topLevelPageProperty)) {
			String topLevelPage = page.getModel().getRoot().getProperties().get(topLevelPageProperty);
			if (!Util.isBlank(topLevelPage)) {
				return "true".equals(topLevelPage.trim());
			}
		}

		return page.getLinks().isEmpty();
	}
	
	/**
	 * Property containing domain class name.
	 * The name can be simple <code>class  name</code>, qualified <code>package name.class name</code>, or be a URI <code>package namespace uri#//class name</code>  
	 * @return
	 */
	protected String getTypeProperty() {
		return getPropertyNamespace() + "type";
	}	
		
	/**
	 * A map of package aliases to {@link EPackage}
	 * @return
	 */
	protected abstract Map<String,EPackage> getEPackages();
		
	public EClassifier getType(String type, Marked source) {
		if (Util.isBlank(type)) {
			return null;
		}
		URI typeURI = URI.createURI(type);
		if (typeURI.hasFragment()) {
			URI ePackageNsURI = typeURI.trimFragment();
			for (EPackage ePackage: getEPackages().values()) {
				if (ePackageNsURI.equals(URI.createURI(ePackage.getNsURI()))) {
					String eClassifierName = typeURI.fragment().substring(2);
					EClassifier eClassifier = ePackage.getEClassifier(eClassifierName);
					if (eClassifier == null) {
						throw new ConfigurationException("EClassifier " + eClassifierName + " not found in EPackage: " + ePackageNsURI, source); 				
					}
					return eClassifier;
				}
			}
			throw new IllegalArgumentException("EPackage not found: " + ePackageNsURI); 
		}
		
		int dotIdx = type.indexOf('.');
		if (dotIdx == -1) {
			Optional<EClassifier> typeOpt = getEPackages()
				.values()
				.stream()
				.map(ep -> ep.getEClassifier(type))
				.filter(Objects::nonNull)
				.findFirst();
			
			if (typeOpt.isPresent()) {
				return typeOpt.get();
			}
			throw new ConfigurationException("Unknown type: " + type, source); 				
		}
		
		Optional<EClassifier> typeOpt = getEPackages()
				.entrySet()
				.stream()
				.filter(pe -> pe.getKey().equals(type.substring(0,dotIdx)))
				.map(Map.Entry::getValue)
				.map(ep -> ep.getEClassifier(type.substring(dotIdx + 1)))
				.filter(Objects::nonNull)
				.findFirst();
			
		if (typeOpt.isPresent()) {
			return typeOpt.get();
		}
		throw new ConfigurationException("Unknown type: " + type, source); 				
	}
	
	/**
	 * Creates an instance of specified type.
	 * This implementation uses flow model EClass names as types to instantiate respective {@link EClass}es using factory
	 * @param kind
	 * @return
	 */
	protected EObject create(String type, Marked source) {
		EClassifier classifier = getType(type, source);
		if (classifier instanceof EClass) {
			return create((EClass) classifier);
		}
		
		throw new IllegalArgumentException("Unsupported element type: " + type);
	}
	
	/**
	 * Creates an instance of argument EClass. This implementation delegates to the EPackage factory.
	 * Override to customize, e.g. use specializations (sub-classes) instead of the argument EClass.
	 * @param type
	 * @return
	 */
	protected EObject create(EClass type) {
		return type.getEPackage().getEFactoryInstance().create(type);
	}
	
	// ---

	/**
	 * Override to return content mappers for different phases and passes
	 * @param phase
	 * @param pass
	 * @return
	 */
	protected Mapper<EObject, EObject> getMapper(int phase, int pass) {
		return null;
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
	public D createDocumentElement(
			org.nasdanika.drawio.model.Document document,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {
		
		D documentElement = createDocumentElement(document, elementProvider, registry, progressMonitor);
		String source = document.getSource();
		if (!Util.isBlank(source) && documentElement instanceof org.nasdanika.ncore.ModelElement) {
			((org.nasdanika.ncore.ModelElement) documentElement).getRepresentations().put("drawio", source);
		}
		if (documentElement instanceof org.nasdanika.ncore.Marked) {
			for (Marker marker: document.getMarkers()) {
				((org.nasdanika.ncore.ModelElement) documentElement).getMarkers().add(EcoreUtil.copy(marker));
			}
		}
		return documentElement;
	}
	
	/**
	 * 
	 * @param document
	 * @param elementProvider
	 * @param registry
	 * @param progressMonitor
	 * @return
	 */
	protected abstract D createDocumentElement(
			org.nasdanika.drawio.model.Document document,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor);
	
	/**
	 * Semantic ID is used instead of the Drawio element ID if provided. E.g. "my-process" instead of "HP_1aCX3hRniy5izKEYK"  
	 * @return
	 */
	protected String getSemanticIdProperty() {
		return getPropertyNamespace() + "semantic-id";
	}	
	
	protected String getDocumentationProperty() {
		return getPropertyNamespace() + "documentation";
	}	
		
	protected String getDocumentationRefProperty() {
		return getPropertyNamespace() + "documentation-ref";
	}	
	
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
	public S createSemanticElement(
			org.nasdanika.drawio.model.ModelElement modelElement,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<EObject,ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, EObject>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {
	
		String typeProperty = getTypeProperty();
		if (Util.isBlank(typeProperty)) {
			return null;
		}
		
		String type = modelElement.getProperties().get(typeProperty);
		if (Util.isBlank(type)) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		S semanticElement = (S) create(type.trim(), modelElement);
		if (semanticElement instanceof org.nasdanika.ncore.Marked) {
			for (Marker marker: modelElement.getMarkers()) {
				((org.nasdanika.ncore.Marked) semanticElement).getMarkers().add(EcoreUtil.copy(marker));
			}
		}		
		
		// Root is logically "merged" with the containing page
		if (modelElement instanceof Root) {
			Page page = (Page) modelElement.eContainer().eContainer();
			if (semanticElement instanceof org.nasdanika.ncore.NamedElement) {
				((org.nasdanika.ncore.NamedElement) semanticElement).setName(page.getName());
			}
			if (semanticElement instanceof org.nasdanika.ncore.ModelElement) {
				((org.nasdanika.ncore.ModelElement) semanticElement).setAnnotation("page-id", page.getId());
			}
		}
		
		String elementId = modelElement.getId();
		if (semanticElement instanceof org.nasdanika.ncore.StringIdentity) {
			String semanticIdProperty = getSemanticIdProperty();
			if (!Util.isBlank(semanticIdProperty)) {
				String semanticId = modelElement.getProperties().get(semanticIdProperty);
				((org.nasdanika.ncore.StringIdentity) semanticElement).setId(Util.isBlank(semanticId) ? elementId : semanticId);
			}
		}
		if (semanticElement instanceof org.nasdanika.ncore.ModelElement) {
			((org.nasdanika.ncore.ModelElement) semanticElement).setAnnotation("model-element-id", elementId);
			
			if (isPageElement(modelElement)) {
				for (EObject eContainer = modelElement.eContainer(); eContainer != null; eContainer = eContainer.eContainer()) {
					if (eContainer instanceof Page) {
						((org.nasdanika.ncore.ModelElement) semanticElement).setAnnotation("page-id", ((Page) eContainer).getId());						
					}
				}
			}						
		}
		
		String label = modelElement.getLabel();
		if (!Util.isBlank(label) && semanticElement instanceof org.nasdanika.ncore.NamedElement) {
			((org.nasdanika.ncore.NamedElement) semanticElement).setName(label);
		}
		String tooltip = modelElement.getTooltip();
		if (!Util.isBlank(tooltip) && semanticElement instanceof org.nasdanika.ncore.ModelElement) {
			((org.nasdanika.ncore.ModelElement) semanticElement).setDescription(tooltip);
		}

		if (semanticElement instanceof org.nasdanika.ncore.Documented) {
//			String documentation = null;			
//			String documentationProperty = getDocumentationProperty();
//			if (!Util.isBlank(documentationProperty)) {
//				documentation = modelElement.getProperties().get(documentationProperty);
//			}
//			
//			// TODO - doc ref, different formats - text, markdown
//		
//			if (!Util.isBlank(documentation)) {
//				((org.nasdanika.ncore.Documented) semanticElement).getDocumentation(documentation);
//			}
		}		
		
		return semanticElement;
	}
	
	// === Wiring ===
	
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
	@org.nasdanika.common.Transformer.Wire
	public void wireDocumentContainment(
			org.nasdanika.drawio.model.Document document,
			D documentElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		Mapper<EObject,EObject> mapper = getMapper(0, pass);
		if (mapper != null) {
			mapper.wire(document, registry, progressMonitor);
		}
	}
	
	/**
	 * Adds contained elements into containment references.
	 * Traverses page links.
	 * 
	 * @param drawioModelElement
	 * @param flowModelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire
	public void wireModelElementContainment(
			org.nasdanika.drawio.model.ModelElement drawioModelElement,
			S semanticElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		Mapper<EObject,EObject> mapper = getMapper(0, pass);
		if (mapper != null) {
			mapper.wire(drawioModelElement, registry, progressMonitor);
		}
				
		Page linkedPage = drawioModelElement.getLinkedPage();
		if (linkedPage != null) {
			TreeIterator<EObject> pcit = linkedPage.eAllContents();
			while (pcit.hasNext()) {
				EObject next = pcit.next();
				if (next instanceof org.nasdanika.drawio.model.ModelElement && isPageElement((org.nasdanika.drawio.model.ModelElement) next)) {
					registry.put(next, semanticElement);
					return;
				}
			}
			registry.put(linkedPage, semanticElement);
			registry.put(linkedPage.getModel().getRoot(), semanticElement);
		}		
	}
	
	protected String getPageElementProperty() {
		return getPropertyNamespace() + "page-element";
	}
	
	public boolean isPageElement(org.nasdanika.drawio.model.ModelElement drawioModelElement) {		
		String pageElementProperty = getPageElementProperty();
		if (Util.isBlank(pageElementProperty)) {
			return false;
		}
		
		String pev = drawioModelElement.getProperties().get(pageElementProperty);
		return !Util.isBlank(pev) && "true".equals(pev.trim());
	}

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
	@org.nasdanika.common.Transformer.Wire(phase = 1)
	public void wireDocumentNonContainment(
			org.nasdanika.drawio.model.Document document,
			D documentElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		Mapper<EObject,EObject> mapper = getMapper(1, pass);
		if (mapper != null) {
			mapper.wire(document, registry, progressMonitor);
		}
	}
	
	@org.nasdanika.common.Transformer.Wire(phase = 1)
	public void wireModelElementNonContainment(
			org.nasdanika.drawio.model.ModelElement drawioModelElement,
			S semanticElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
						
		Mapper<EObject,EObject> mapper = getMapper(1, pass);
		if (mapper != null) {
			mapper.wire(drawioModelElement, registry, progressMonitor);
		}
	}
	
	protected String getRefIdProperty() {
		return "ref-id";
	}
	
	/**
	 * Resolves semantic element by reference id. 
	 * @param refId
	 * @param wiring pass
	 * @return Null if element cannot be resolved yet
	 * @throws IllegalArgumentException If refId does not resolve to a semantic element
	 */
	protected abstract S getByRefId(String refId, int pass, Map<EObject, EObject> registry);
	
	/**
	 * Wires elements with ref-id property. Remaps which triggers wireContainment.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class)
	public boolean wireRefIds(
			org.nasdanika.drawio.model.ModelElement modelElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {

		String refIdPropertyName = getRefIdProperty();
		if (Util.isBlank(refIdPropertyName)) {
			return true;
		}
		String refId = modelElement.getProperties().get(refIdPropertyName);
		if (Util.isBlank(refId)) {
			return true;
		}
		
		S refTarget = getByRefId(refId, pass, registry);
		if (refTarget == null) {
			return false;
		}
		
		registry.put(modelElement, refTarget); // Resolved refId triggers a new wave of wiring
		return true;
	}
	
	/**
	 * Feature maps null semantic elements, which is needed for connections as they might be pass-through.
	 * @param modelElement
	 * @param registry
	 * @param pass
	 * @param progressMonitor
	 */
	@org.nasdanika.common.Transformer.Wire(targetType = Void.class, phase = 2)
	public boolean featurMapNulls(
			org.nasdanika.drawio.model.ModelElement modelElement,
			Map<EObject, EObject> registry,
			int pass,
			ProgressMonitor progressMonitor) {
		
		Mapper<EObject,EObject> mapper = getMapper(0, pass);
		if (mapper != null) {
			mapper.wire(modelElement, registry, progressMonitor);
		}		
		return true;
	}	
	
}
