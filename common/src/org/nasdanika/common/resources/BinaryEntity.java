package org.nasdanika.common.resources;

import java.io.InputStream;

/**
 * Binding of entity state to {@link InputStream}.
 * @author Pavel
 *
 * @param <E>
 */
public interface BinaryEntity extends Entity<InputStream,BinaryEntity>, BinaryResource {

	/**
	 * Narrowing return type.
	 */
	@Override
	BinaryEntityContainer getParent();

}
