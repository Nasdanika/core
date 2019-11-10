package org.nasdanika.repository;

import java.util.Collection;

/**
 * Commands are executed in the context of a resource.
 * @author Pavel
 *
 * @param <R>
 */
public interface Command<R> {
	
	/**
	 * Executes a _LegacyCommandToRemove in the context of a resource.
	 * @param resource Context resource.
	 * @param resourcePath Resource path.
	 * @param commandPath _LegacyCommandToRemove path - the tail of the full path passed to the executor after the resource path.
	 * @return
	 */
	R execute(Object resource, String resourcePath, String commandPath) throws Exception;
	
	/**
	 * Resource requirements for _LegacyCommandToRemove execution.
	 * _LegacyCommandToRemove is executed is the executing identity has permissions to execute requested 
	 * actions and no other _LegacyCommandToRemove being executed prevents requested resource access. E.g. if this _LegacyCommandToRemove requests
	 * read access to resource XYZ then no other _LegacyCommandToRemove shall be writing to that resource while this _LegacyCommandToRemove is executing.  
	 * @return
	 */
	Collection<ResourceRequirement> getRequirements();	
	
}
