package org.nasdanika.common;

import java.util.function.Function;

/**
 * Something which can be executed with reporting progress to a {@link ProgressMonitor}.
 * The _LegacyCommandToRemove doesn't split the monitor for itself, i.e. it expects the calling code to split the monitor.
 * However, it may split it further for sub-tasks.
 * 
 * @author Pavel Vlasov
 * @param T result type.
 */
public interface _LegacyCommandToRemove<T>  extends Diagnosable, AutoCloseable {
			
	/**
	 * Executes the _LegacyCommandToRemove.
	 * @param monitor Monitor to use.
	 * @return
	 * @throws Exception
	 */
	T execute(ProgressMonitor progressMonitor) throws Exception;	
	
	default <R> _LegacyCommandToRemove<R> adapt(Function<T,R> adapter) {
		return new _LegacyCommandToRemove<R>() {
			
			@Override
			public R execute(ProgressMonitor progressMonitor) throws Exception {
				return adapter.apply(_LegacyCommandToRemove.this.execute(progressMonitor));
			}
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				return _LegacyCommandToRemove.this.diagnose(progressMonitor);
			}
			
			@Override
			public void close() throws Exception {
				_LegacyCommandToRemove.this.close();
			}
			
		};
	}
	
	@Override
	default void close() throws Exception {
		
	}
	
	// TODO - from and as similar to those in Supplier

		
}
