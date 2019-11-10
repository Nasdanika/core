package org.nasdanika.common.descriptors;

import java.util.List;

public interface QueryValueDescriptor<T> extends ValueDescriptor<T> {
	
	/**
	 * @return A _LegacyCommandToRemove descriptor to execute query/lookup _LegacyCommandToRemove returning a list of choices.
	 */
	CommandDescriptor<List<Descriptor>> getQuery();	

}
