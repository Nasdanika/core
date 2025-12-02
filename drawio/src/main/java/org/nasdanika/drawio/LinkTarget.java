package org.nasdanika.drawio;

/**
 * Base interface for diagram elements which can be linked from {@link ModelElement}s - {@link Page} and ModelElement.
 */
public interface LinkTarget<L extends LinkTarget<L>> extends Element<L> {	

}
