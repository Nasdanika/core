package org.nasdanika.common.descriptors;

import org.nasdanika.common.Work;

/**
 * This descriptor can be used to collect information needed to execute work.
 * @author Pavel
 *
 * @param <T>
 */
public interface WorkDescriptor<T> extends Work<T>, CommandDescriptor<T> {

}
