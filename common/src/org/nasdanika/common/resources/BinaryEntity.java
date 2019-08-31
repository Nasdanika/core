package org.nasdanika.common.resources;

import java.io.InputStream;

/**
 * Binding of entity state to {@link InputStream}.
 * @author Pavel
 *
 * @param <E>
 */
public interface BinaryEntity<E> extends Entity<InputStream, E> {

}
