package org.nasdanika.common;

import java.util.function.Function;

/**
 * Something which can be executed with reporting progress to a {@link ProgressMonitor}.
 * The command doesn't split the monitor for itself, i.e. it expects the calling code to split the monitor.
 * However, it may split it further for sub-tasks.
 * 
 * @author Pavel Vlasov
 * @param T result type.
 */
public interface Command<T>  extends Diagnosable, AutoCloseable {
			
	/**
	 * Executes the command.
	 * @param monitor Monitor to use.
	 * @return
	 * @throws Exception
	 */
	T execute(ProgressMonitor progressMonitor) throws Exception;	
	
	default <R> Command<R> adapt(Function<T,R> adapter) {
		return new Command<R>() {
			
			@Override
			public R execute(ProgressMonitor progressMonitor) throws Exception {
				return adapter.apply(Command.this.execute(progressMonitor));
			}
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				return Command.this.diagnose(progressMonitor);
			}
			
			@Override
			public void close() throws Exception {
				Command.this.close();
			}
			
		};
	}
	
	@Override
	default void close() throws Exception {
		
	}

		
}
