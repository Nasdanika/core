package org.nasdanika.drawio;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.nasdanika.common.Identifiable;
import org.nasdanika.common.Realm;
import org.nasdanika.persistence.Marked;

/**
 * {@link Document} element, including the Document itself, backed by {@link org.w3c.dom.Element}.
 * @author Pavel
 *
 */
public interface Element<E extends Element<E>> extends org.nasdanika.graph.Element, Marked, Identifiable {
	
	/**
	 * @return The underlying XML element.
	 */
	org.w3c.dom.Element getElement();
	
	Realm.Element<E> getRealmElement();
	
	@Override
	List<? extends Element<?>> getChildren() ;
	
	/**
	 * Accepts the visitor in children first (bottom-up) way.
	 * @param visitor
	 */
	default void accept(Consumer<? super Element<?>> visitor, ConnectionBase connectionBase) {
		accept((e, cr) -> { visitor.accept(e); return null; }, connectionBase);
	}
	
	/**
	 * Accepts a result producing visitor {@link BiFunction}. Passes to it a map of results collected from children, nulls are not included.   
	 * @param <T>
	 * @param visitor
	 * @return result returned by the visitor.
	 */
	<T> T accept(BiFunction<Element<?>, Map<Element<?>, T>, T> visitor, ConnectionBase connectionBase);
		
	@Override
	default <T> T accept(BiFunction<? super org.nasdanika.graph.Element, Map<? extends org.nasdanika.graph.Element, T>, T> visitor) {
		BiFunction<Element<?>, Map<Element<?>, T>, T> visitorAdapter = (element, childMappings) -> {
			return visitor.apply(element, childMappings);
		};
		return accept(visitorAdapter, ConnectionBase.PARENT);
	}
	
	/**
	 * @return Stream containing this element and its children.
	 */
	default Stream<Element<?>> stream(ConnectionBase connectionBase) {
		BiFunction<Element<?>, Map<Element<?>, Stream<Element<?>>>, Stream<Element<?>>> visitor = (element, childMappings) -> {
			return childMappings.values().stream().reduce(Stream.of(element), (a,b) -> Stream.concat(a, b));
		};
		return accept(visitor, connectionBase);
	}; 
		
	default ModelElement<?> getModelElementById(String modelElementId) {
		if (org.nasdanika.common.Util.isBlank(modelElementId)) {
			return null;
		}
		return stream()
			.filter(ModelElement.class::isInstance)
			.map(ModelElement.class::cast)
			.filter(me -> modelElementId.equals(me.getId()))
			.findFirst()
			.orElse(null);
	}
	
	@SuppressWarnings("unchecked")
	default ModelElement<?> getModelElementByProperty(String name, String value) {
		if (org.nasdanika.common.Util.isBlank(name) || org.nasdanika.common.Util.isBlank(value)) {
			return null;
		}
		return stream()
			.filter(ModelElement.class::isInstance)
			.map(ModelElement.class::cast)
			.filter(me -> value.equals(me.getProperty(name)))
			.findFirst()
			.orElse(null);
	}
	
	@SuppressWarnings("unchecked")
	default <ME extends ModelElement<ME>> List<ME> getModelElementsByProperty(String name, String value) {
		if (org.nasdanika.common.Util.isBlank(name) || org.nasdanika.common.Util.isBlank(value)) {
			return Collections.emptyList();
		}
		return stream()
			.filter(ModelElement.class::isInstance)
			.map(ModelElement.class::cast)
			.map(me -> (ME) me)
			.filter(me -> value.equals(me.getProperty(name)))
			.toList();
	}		

}
