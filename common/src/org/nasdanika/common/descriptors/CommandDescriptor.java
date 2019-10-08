package org.nasdanika.common.descriptors;

import org.nasdanika.common.Command;

/**
 * This descriptor can be used to collect input data needed for command execution.
 * @author Pavel
 *
 * @param <T>
 */
public interface CommandDescriptor<T> extends DescriptorSet, Command<T> {

}
