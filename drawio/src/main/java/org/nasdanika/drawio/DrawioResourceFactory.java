package org.nasdanika.drawio;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.function.Function;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

/**
 * Creates {@link DrawioResource}
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
	
	private ConnectionBase connectionBase;

	protected DrawioResourceFactory(ConnectionBase connectionBase) {
		this.connectionBase = connectionBase;
	}
	
	@Override
	public Resource createResource(URI uri) {
		return new DrawioResource<T>(uri, connectionBase) {

			@Override
			protected ElementEntry<T> createEntry(Element element, Map<Element, ElementEntry<T>> childMappings) {
				if (childMappings != null) {
					Comparator<Element> childComparator = getChildComparator(element);
					if (childComparator != null) {
						Map<Element, ElementEntry<T>> orderedChildMappings = new LinkedHashMap<>();
						childMappings.entrySet().stream().sorted((a, b) -> childComparator.compare(a.getKey(), b.getKey())).forEachOrdered(e -> orderedChildMappings.put(e.getKey(), e.getValue()));
						childMappings = orderedChildMappings;
					}
				}
				Map<Element, ElementEntry<T>> theChildMappings = childMappings;
				T semanticElement = DrawioResourceFactory.this.createSemanticElement(this, element, theChildMappings);
				return new ElementEntry<T>() {
					
					public T getSemanticElement() {
						return semanticElement;
					}
					
					public Map<Element, ElementEntry<T>> getChildEntries() {
						return theChildMappings;
					}
					
				};
			}

			@Override
			protected void resolve(
					Element element, 
					T semanticElement, 
					Map<Element, ElementEntry<T>> childEntries,
					Function<Element, T> resolver) {
				
				DrawioResourceFactory.this.resolve(this, element, semanticElement, childEntries, resolver);				
			}			
			
		};
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
		
		if (parent instanceof Layer) {
			return loadChildComparator((ModelElement) parent, "label");
		}
		
		return null;
	}

	protected Comparator<Element> loadChildComparator(ModelElement parent, String defaultSort) {
		String sortProperty = getSortProperty(parent);
		String sort = null;
		if (!org.nasdanika.common.Util.isBlank(sortProperty)) {
			sort = ((ModelElement) parent).getProperty(sortProperty);
		}
		if (org.nasdanika.common.Util.isBlank(sort)) {
			sort = defaultSort;
		}
		
		if (org.nasdanika.common.Util.isBlank(sort)) {
			return null;
		}
		
		String config = null;
		int colonIdx = sort.indexOf(":");
		if (colonIdx != -1) {
			config = sort.substring(colonIdx + 1);
			sort = sort.substring(0, colonIdx);
		}
		
		for (ElementComparator.Factory comparatorFactory: ServiceLoader.load(ElementComparator.Factory.class)) {
			if (comparatorFactory.isForType(sort)) {
				return comparatorFactory.create(sort, config, parent);
			}
		}			
		throw new IllegalArgumentException("Unsupported sort type: " + sort);
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
	protected abstract T createSemanticElement(Resource resource, Element element, Map<Element, ElementEntry<T>> childEntries);
	
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
			Function<Element, T> resolver);
	
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
		
}
