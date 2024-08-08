package org.nasdanika.drawio;

import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.PropertySource;

/**
 * Base interface for {@link Model} elements
 * @author Pavel
 *
 */
public interface ModelElement extends LinkTarget, PropertySource<String, String> {
	
	/**
	 * Containing model
	 * @return
	 */
	Model getModel();
	
	/**
	 * Containing model element
	 * @return
	 */
	ModelElement getParent();
	
	String getLabel();
	
	void setLabel(String label);

	String getLink();
	
	void setLink(String link);
	
	String getTooltip();
	
	void setTooltip(String tooltip);
	
	/**
	 * @return style property represented as a map
	 */
	Map<String,String> getStyle();
	
	/**
	 * A fluent method equivalent to getStyle().put(key, value)
	 * @param key
	 * @param value
	 * @return
	 */
	default ModelElement style(String key, String value) {
		getStyle().put(key, value);
		return this;
	}
	
	void setProperty(String name, String value);
	
	Set<String> getPropertyNames();
	
	Set<String> getTags();
	
	boolean isVisible();
	
	void setVisible(boolean visible);
	
	String getId();
	
	/**
	 * Resolves some value for the model element by traversing its logical containment hierarchy and resolving that value against logical parents.
	 * For the containment root the value is resolved against the base.
	 * @param <T>
	 * @param base Base value is used to resolve the parent value
	 * @param resolver Resolver {@link BiFunction}
	 * @param connectionBase Indicates logical "mounting" of connections.
	 * @return
	 */
	<T> T resolve(T base, BiFunction<? super ModelElement,T,T> resolver, ConnectionBase connectionBase);
	
	/**
	 * Resolves URI
	 * @param base Base URI for the root object.
	 * @param uriProvider Extracts URI from the model element.
	 * @param connectionBase Connection base (logical parent)
	 * @return
	 */
	default URI resolveURI(URI base, Function<? super ModelElement,URI> uriProvider, ConnectionBase connectionBase) {
		return resolve(base, (modelElement, parentURI) -> {			
			URI uri = uriProvider.apply(modelElement);
			if (uri == null) {
				return parentURI;
			}
			if (parentURI == null) {
				return uri;
			}
			
			return uri.resolve(parentURI);			
		}, connectionBase);
	}
	
	/**
	 * @return Linked {@link Page} or {@link ModelElement}.
	 */
	LinkTarget getLinkTarget();
		
	/**
	 * @return True if there is a link and it is in one of {@link LinkTarget} ({@link Page} / {@link ModelElement}) formats.
	 */
	boolean isTargetLink();
	
}
