package org.nasdanika.common;

/**
 * Creates work to be executed.
 * Provides descriptor of what is needed in the context for work execution. 
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface WorkFactory<T> extends DescriptorProvider<DescriptorSet, Context> {
		
	/**
	 * Creates work for a given context.
	 * @param context
	 * @return
	 * @throws Exception
	 */
	Work<T> createWork(Context context) throws Exception;
	
}
