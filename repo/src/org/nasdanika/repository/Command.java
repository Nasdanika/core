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
	 * Executes a command in the context of a resource.
	 * @param resource Context resource.
	 * @param resourcePath Resource path.
	 * @param commandPath Command path - the tail of the full path passed to the executor after the resource path.
	 * @return
	 */
	R execute(Object resource, String resourcePath, String commandPath) throws Exception;
	
	/**
	 * Resource requirements for command execution.
	 * Command is executed is the executing identity has permissions to execute requested 
	 * actions and no other command being executed prevents requested resource access. E.g. if this command requests
	 * read access to resource XYZ then no other command shall be writing to that resource while this command is executing.  
	 * @return
	 */
	Collection<ResourceRequirement> getRequirements();	
	
}
