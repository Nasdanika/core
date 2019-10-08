package org.nasdanika.common.descriptors;

import java.util.List;

public interface ChoicesValueDescriptor<T> extends ValueDescriptor<T> {
	
	/**
	 * Choices for the descriptor value. Choices may be organized into a hierarchy with {@link DescriptorSet}s.
	 * @return
	 */
	List<Descriptor> getChoices();	

}
