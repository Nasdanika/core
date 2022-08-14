package org.nasdanika.drawio;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.function.Function;
import java.util.function.Predicate;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.drawio.comparators.AngularModelElementComparatorFactory;
import org.nasdanika.drawio.comparators.LabelModelElementComparatorFactory;
import org.nasdanika.drawio.comparators.PropertyModelElementComparatorFactory;

/**
 * Base class for factories which load {@link EObject}'s from Drawio diagrams by performing semantic mapping from diagram elements to model elements. 
 * @author Pavel
 *
 */
public abstract class DrawioResourceFactory<T> extends ResourceFactoryImpl {

	public interface ElementEntry<T> {
		
		/**
		 * Semantic element created from a given Drawio element. Can be null
		 * @return
		 */
		T getSemanticElement();
		
		Map<Element, ElementEntry<T>> getChildEntries();
				
	}
	
	public interface UpdateAdapter extends Adapter {
		
		/**
		 * Updates the model.
		 */
		void update(Document document);
		
	}
	
	protected ConnectionBase connectionBase;

	protected DrawioResourceFactory(ConnectionBase connectionBase) {
		this.connectionBase = connectionBase;
	}
	
	@Override
	public Resource createResource(URI uri) {
		return new DrawioResource<T>(uri, connectionBase) {

			@Override
			protected ElementEntry<T> createEntry(Element element, Map<Element, ElementEntry<T>> childMappings) {
				return DrawioResourceFactory.this.createEntry(this, element, childMappings);
			}

			@Override
			protected void resolve(
					Element element, 
					T semanticElement, 
					Map<Element, ElementEntry<T>> childEntries,
					Function<Predicate<Element>, T> resolver) {
				
				DrawioResourceFactory.this.resolve(this, element, semanticElement, childEntries, resolver);				
			}			
			
		};
	}		

	/**
	 * Override to add child mappings from a linked page (if any).
	 * @param modelElement
	 * @return
	 */
	protected Map<Element, ElementEntry<T>> getLinkedPageChildMappings(Resource resource, Page linkedPage, ElementEntry<T> linkedPageEntry) {
		return Collections.emptyMap();
	}
	
	/**
	 * @param parent
	 * @return Child element comparator for a specific given parent. This implementation orders {@link Layer}s top to bottom 
	 * and {@link ModelElement} children using {@link ElementComparator.Factory} service with a name matching sort property value before colon, if it is present. 
	 * If there is no sort property then elements are sorted by label text by default using "label" comparator.
	 */
	protected Comparator<Element> getChildComparator(Element parent) {
		if (parent instanceof Root) {
			Comparator<Element> ret = loadChildComparator((ModelElement) parent, null);
			if (ret != null) {
				return ret;
			}
			
			return new Comparator<Element>() {
				
				@Override
				public int compare(Element l1, Element l2) {
					if (Objects.equals(l1, l2)) {
						return 0;
					}
					return ((Root) parent).getLayers().indexOf(l2) - ((Root) parent).getLayers().indexOf(l1); // Reverse order 
				}
			};
			
		}
		
		String defaultSort = getDefaultSort();
		if (parent instanceof ModelElement) {
			return loadChildComparator((ModelElement) parent, defaultSort);
		}
		
		for (ElementComparator.Factory comparatorFactory: ServiceLoader.load(ElementComparator.Factory.class)) {
			if (comparatorFactory.isForType(defaultSort)) {
				return comparatorFactory.create(defaultSort, null, parent);
			}
		}					
		
		return null;
	}
	
	protected String getDefaultSort() {
		return "label";
	}
		
	/**
	 * Semantic parent is used for resolution of documentation.
	 * Element documentation URI is resolved relative to semantic ancestor documentation URI if it is set and relative to the
	 * document otherwise.
	 * @param element
	 * @return
	 */
	protected ModelElement getSemanticParent(ModelElement element) {
		if (element instanceof Connection) {
			if (connectionBase == ConnectionBase.SOURCE) {
				return ((Connection) element).getSource();
			}
			if (connectionBase == ConnectionBase.TARGET) {
				return ((Connection) element).getTarget();
			}
		} 
		return element.getParent();
	}
	
	protected BiSupplier<String, String> getComparatorConfig(ModelElement parent, String defaultSort) {
		String sortProperty = getSortProperty(parent);
		String sort = null;
		if (!org.nasdanika.common.Util.isBlank(sortProperty)) {
			sort = ((ModelElement) parent).getProperty(sortProperty);
		}
		if (org.nasdanika.common.Util.isBlank(sort)) {
			ModelElement semanticParent = getSemanticParent(parent);
			if (semanticParent != null) {
				return getComparatorConfig(semanticParent, defaultSort);
			}
			sort = defaultSort;
		}
		
		if (org.nasdanika.common.Util.isBlank(sort) || "none".equals(sort)) {
			return null;
		}
		
		String config = null;
		int colonIdx = sort.indexOf(":");
		if (colonIdx != -1) {
			config = sort.substring(colonIdx + 1);
			sort = sort.substring(0, colonIdx);
		}
		
		return BiSupplier.of(sort, config);
	}
	
	protected Comparator<Element> loadChildComparator(ModelElement parent, String defaultSort) {
		BiSupplier<String, String> config = getComparatorConfig(parent, defaultSort);
		if (config == null) {
			return null;
		}
		String sortConfig = config.getSecond();
		if (sortConfig == null) {
			sortConfig = getDefaultSortConfig(parent, config.getFirst());
		}
		
		return getChildComparator(parent, config.getFirst(), sortConfig);
	}
	
	protected static final ElementComparator.Factory[] ELEMENT_COMPARATOR_FACTORIES = {
		new AngularModelElementComparatorFactory(),
		new LabelModelElementComparatorFactory(),
		new PropertyModelElementComparatorFactory()
	};

	/**
	 * @param parent Parent model element.
	 * @param type Sort type.
	 * @param config Sort configuration.
	 * @return Child comparator for a given parent and type with a given configuration. This implementation loads comparators using {@link ServiceLoader} and then goes through factories shipped with this module - they should have been loaded by the service loader, but just in case it doesn't happen. 
	 */
	protected Comparator<Element> getChildComparator(ModelElement parent, String type, String config) {
		for (ElementComparator.Factory comparatorFactory: ServiceLoader.load(ElementComparator.Factory.class)) {
			if (comparatorFactory.isForType(type)) {
				return comparatorFactory.create(type, config, parent);
			}
		}			
		for (ElementComparator.Factory comparatorFactory: ELEMENT_COMPARATOR_FACTORIES) {
			if (comparatorFactory.isForType(type)) {
				return comparatorFactory.create(type, config, parent);
			}
		}			
		throw new IllegalArgumentException("Unsupported sort type: " + type);
	}
	
	/**
	 * @param parent
	 * @param sort
	 * @return Default configuration for a sort type. For example, for a agle sort (clockwise/counterclockwise) the default configuration can the an angle from the semantic parent of this model element.  
	 */
	protected String getDefaultSortConfig(ModelElement modelElement, String sort) {
		if (modelElement instanceof Node && (AngularModelElementComparatorFactory.CLOCKWISE.equals(sort) || AngularModelElementComparatorFactory.COUNTERCLOCKWISE.equals(sort))) {
			ModelElement semanticParent = getSemanticParent(modelElement);
			if (semanticParent instanceof Node) {
				return String.valueOf(Math.toDegrees(AngularModelElementComparatorFactory.angle((Node) modelElement, (Node) semanticParent)));
			}
		}
		return null;
	}

	protected String getSortProperty(Element parent) {
		return "sort";
	}
	
	/**
	 * Create an element, add top level elements to resource content.
	 * May return null for model/diagram elements without semantic mapping.
	 * @param resource
	 * @param document
	 * @param element
	 * @param childEntries
	 */
	protected abstract T createSemanticElement(Resource resource, Element element, Map<? extends Element, ElementEntry<T>> childEntries);
	
	/**
	 * Resolve references after all elements have been created.
	 * @param resource Resource
	 * @param element Drawio element
	 * @param semanticElement EObject created from this element, can be null
	 * @param childEntries Child entries
	 * @param resolver Resolves Element to EObject. Can be used, for example, to resolve connection target or source.
	 * @return
	 */
	protected abstract void resolve(
			Resource resource, 
			Element element, 
			T semanticElement, 
			Map<Element,ElementEntry<T>> childEntries, 
			Function<Predicate<Element>, T> resolver);
	
	/**
	 * Creates an {@link ElementAdapter}.
	 * @param element
	 * @return
	 */
	protected ElementAdapter createElementAdapter(Element element) {
		class ElementAdapterImpl extends AdapterImpl implements ElementAdapter {
			
			@Override
			public Element getElement() {
				return element;
			}
			
			@Override
			public boolean isAdapterForType(Object type) {
				return ElementAdapter.class == type;
			}

		}

		return new ElementAdapterImpl();
	}

	protected ElementEntry<T> createEntry(Resource resource, Element element, Map<Element, ElementEntry<T>> childResults) {
		if (childResults != null && !childResults.isEmpty()) {
			Comparator<Element> childComparator = getChildComparator(element);
			if (childComparator != null) {
				Map<Element, ElementEntry<T>> orderedChildMappings = new LinkedHashMap<>();
				childResults.entrySet().stream().sorted((a, b) -> childComparator.compare(a.getKey(), b.getKey())).forEachOrdered(e -> orderedChildMappings.put(e.getKey(), e.getValue()));
				childResults = orderedChildMappings;
			}
		}
		
		if (element instanceof ModelElement) {
			Page linkedPage = ((ModelElement) element).getLinkedPage();
			if (linkedPage != null) {
				@SuppressWarnings("unchecked")
				DrawioResource<T> drawioResource = (DrawioResource<T>) resource;
				ElementEntry<T> linkedPageMapping = linkedPage.accept(drawioResource::createEntry, connectionBase);
				Map<Element, ElementEntry<T>> linkedPageChildMappings = getLinkedPageChildMappings(resource, linkedPage, linkedPageMapping);
				if (linkedPageChildMappings != null && !linkedPageChildMappings.isEmpty()) {
					childResults = childResults == null ? new LinkedHashMap<>() : new LinkedHashMap<>(childResults);
					childResults.putAll(linkedPageChildMappings);
				}				
			}
		}

		Map<Element, ElementEntry<T>> theChildMappings = childResults;
		T semanticElement = createSemanticElement(resource, element, theChildMappings);
		return new ElementEntry<T>() {
			
			public T getSemanticElement() {
				return semanticElement;
			}
			
			public Map<Element, ElementEntry<T>> getChildEntries() {
				return theChildMappings;
			}
			
		};
	}
		
}
