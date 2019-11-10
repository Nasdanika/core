package org.nasdanika.common.descriptors;

import org.nasdanika.common._LegacyCommandToRemove;

/**
 * This descriptor can be used to collect input data needed for _LegacyCommandToRemove execution.
 * @author Pavel
 *
 * @param <T>
 */
public interface CommandDescriptor<T> extends DescriptorSet, _LegacyCommandToRemove<T> {

}
