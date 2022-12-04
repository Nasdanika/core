package org.nasdanika.common.descriptors;

/**
 * Descriptor of something with a name.
 * @author Pavel
 *
 */
public interface NamedDescriptor extends Descriptor {
	
	String getName();
	
//	/**
//	 * 
//	 * @param mapper
//	 * @return Descriptor with the name mapped to a different name by the mapper.
//	 */
//	NamedDescriptor mapName(Function<String, String> mapper);

}
