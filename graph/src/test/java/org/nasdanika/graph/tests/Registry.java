package org.nasdanika.graph.tests;

import java.util.Map;
import java.util.function.Supplier;

import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorInfo;

/**
 * Registry record wrapping the map to simplify generic type parameters
 * @author Pavel
 *
 * @param <P>
 */
public record Registry(Map<Element, ProcessorInfo<Supplier<EClassifierRecord>,Registry>> infoMap) {

}
