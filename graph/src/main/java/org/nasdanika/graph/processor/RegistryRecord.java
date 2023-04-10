package org.nasdanika.graph.processor;

import java.util.Map;

import org.nasdanika.graph.Element;

/**
 * A wrapper record for a registry map
 * @author Pavel
 *
 * @param <P>
 */
public record RegistryRecord<P>(Map<Element, ProcessorInfo<P,RegistryRecord<P>>> processorInfoMap) {

}
