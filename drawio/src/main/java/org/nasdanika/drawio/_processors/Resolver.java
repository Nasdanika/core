package org.nasdanika.drawio._processors;

import java.util.function.Predicate;
import java.util.stream.Stream;

import org.nasdanika.drawio.Element;

public interface Resolver<T> {
	
	Stream<T> select(Predicate<Element> predicate);
	
	T resolve(Element element);

}
