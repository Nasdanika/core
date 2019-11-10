package org.nasdanika.common.descriptors;

import org.nasdanika.common.Supplier;

/**
 * This descriptor can be used to collect information needed to execute work.
 * @author Pavel
 *
 * @param <T>
 */
public interface WorkDescriptor<T> extends Supplier<T>, CommandDescriptor<T> {

}
