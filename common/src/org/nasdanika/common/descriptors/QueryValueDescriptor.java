package org.nasdanika.common.descriptors;

import java.util.List;

public interface QueryValueDescriptor<T> extends ValueDescriptor<T> {
	
	/**
	 * @return A command descriptor to execute query/lookup command returning a list of choices.
	 */
	CommandDescriptor<List<Descriptor>> getQuery();	

}
