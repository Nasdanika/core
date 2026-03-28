package org.nasdanika.common;

import org.eclipse.emf.ecore.EModelElement;

/**
 * Interface for commands loading/producing Ecore models.
 * @param <T>
 */
public interface EModelElementSupplier<T extends EModelElement> extends EObjectSupplier<T> {

}
