package org.nasdanika.repository;

import java.util.Collection;
import java.util.function.Function;

/**
 * Finds a resource and executes a command. May delegate execution to another executor.
 * @author Pavel Vlasov
 *
 * @param <E>
 * @param <R>
 * @param <X>
 */
public interface Executor extends AutoCloseable {
	
	/**
	 * Submits command for execution. 
	 * @param <R>
	 * @param command Command to be executed on a resource.
	 * @param resourcePath Resource path. 
	 * @return
	 */
	<R> R execute(Command<R> command, String resourcePath) throws Exception;
	
	/**
	 * Closes the executor and releases any resources held by it.
	 */
	default void close() {
		
	};
	
	/**
	 * @return true for executors created with credentials and false for unauthenticated executor created by createExecutor() method without parameters.
	 */
	boolean isAuthenticated();
	
	/**
	 * @param adapter
	 * @return Executor which adapts resource to a different type by invoking the adapter function.
	 */
	default Executor adapt(Function<Object,Object> adapter) {
		return new Executor() {

			@Override
			public <R> R execute(Command<R> command, String entityPath) throws Exception {
				return Executor.this.execute(new Command<R>() {

					@Override
					public R execute(Object resource, String entityPath, String commandPath) throws Exception {
						return command.execute(adapter.apply(resource), entityPath, commandPath);
					}

					@Override
					public Collection<ResourceRequirement> getRequirements() {
						return command.getRequirements();
					}
					
				}, entityPath);
			}

			@Override
			public long getTimestamp() {
				return Executor.this.getTimestamp();
			}

			@Override
			public boolean isAuthenticated() {
				return Executor.this.isAuthenticated();
			}
			
		};
	}
	
	/**
	 * Last modified time of resources visible by this executor.
	 * @return
	 */
	long getTimestamp();
	
}
