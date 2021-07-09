package org.nasdanika.common.descriptors;

import java.util.List;

public interface ChoicesValueDescriptor extends ValueDescriptor {
	
	/**
	 * Choices for the descriptor value. Choices may be organized into a hierarchy with {@link DescriptorSet}s.
	 * @return null if value is not constrained by choices or a list of choices.
	 */
	List<Descriptor> getChoices();	

}
