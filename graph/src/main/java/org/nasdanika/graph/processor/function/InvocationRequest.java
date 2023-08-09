package org.nasdanika.graph.processor.function;

import java.util.function.BiFunction;

/**
 * {@link BiFunction} argument to request an invocation on the receiving end. 
 */
public record InvocationRequest(String name, Object... args) {}
