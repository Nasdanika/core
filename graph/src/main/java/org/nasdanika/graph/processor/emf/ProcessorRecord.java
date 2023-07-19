package org.nasdanika.graph.processor.emf;

import org.nasdanika.graph.processor.ProcessorConfig;

public record ProcessorRecord<P>(ProcessorConfig config, P processor) {

}
