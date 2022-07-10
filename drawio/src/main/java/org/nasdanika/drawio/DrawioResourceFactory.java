package org.nasdanika.drawio;

import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.common.notify.Adapter;
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
				T semanticElement = DrawioResourceFactory.this.createSemanticElement(this, element, childMappings);
				return new ElementEntry<T>() {
					
					public T getSemanticElement() {
						return semanticElement;
					}
					
					public Map<Element, ElementEntry<T>> getChildEntries() {
						return childMappings;
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
		
}
