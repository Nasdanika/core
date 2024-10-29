package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.nasdanika.common.Adaptable;
import org.nasdanika.common.Invocable;
import org.nasdanika.graph.Element;

/**
 * Invokes handlers asynchronously
 */
public class AsyncInvocableConnectionProcessor implements AutoCloseable {
	
	private Executor executor;
	private boolean shutdownExecutor;
	private long terminationTimeout;
	private TimeUnit terminationTimeoutUnit;
	

	public AsyncInvocableConnectionProcessor(int threads, long terminationTimeout, TimeUnit terminationTimeoutUnit) {
		this.executor = Executors.newFixedThreadPool(threads);
		this.terminationTimeout = terminationTimeout;
		this.terminationTimeoutUnit = terminationTimeoutUnit;
		shutdownExecutor = true;
	}
	

	public AsyncInvocableConnectionProcessor(Executor executor) {
		this.executor = executor;
	}
	
	public AsyncInvocableConnectionProcessor() {
		
	}
	
	@ParentProcessor(true)
	public ProcessorInfo<Object> parentInfo;
	
	/**
	 * Finds parent processor of type {@link Executor} or {@link Adaptable} to executor
	 * @param parentInfo
	 */
	@Registry
	public void setRegistry(Map<Element, ProcessorInfo<?>> registry) {
		for (ProcessorInfo<?> pInfo = parentInfo; executor == null && pInfo != null; pInfo = registry.get(pInfo.getParentProcessorConfig().getElement())) {
			executor = Adaptable.adaptTo(parentInfo.getProcessor(), Executor.class);
		}
	}
	
	@SourceEndpoint
	public Invocable sourceEndpoint;
	
	@TargetEndpoint
	public Invocable targetEndpoint;
	
	@SourceHandler
	public Invocable sourceHandler = targetEndpoint.async(executor);
	
	@TargetHandler
	public Invocable targetHandler = sourceEndpoint.async(executor);

	@Override
	public void close() throws Exception {
		if (shutdownExecutor && executor instanceof ExecutorService) {
			ExecutorService executorService = (ExecutorService) executor;
			executorService.shutdown();
			executorService.awaitTermination(terminationTimeout, terminationTimeoutUnit);
		}		
	}	
	
}