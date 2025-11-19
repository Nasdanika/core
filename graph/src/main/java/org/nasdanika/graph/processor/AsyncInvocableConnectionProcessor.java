package org.nasdanika.graph.processor;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.capability.CapabilityFactory.Loader;
import org.nasdanika.common.Adaptable;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.graph.Element;

/**
 * Invokes handlers asynchronously
 */
public class AsyncInvocableConnectionProcessor<H,E> implements AutoCloseable {
	
	protected Executor executor;
	private boolean shutdownExecutor;
	protected long terminationTimeout;
	protected TimeUnit terminationTimeoutUnit;
	
	/**
	 * For reflective construction. Fragment, if present, is parsed as number of threads.
	 * Termination timeout is set to one minute - override as needed.
	 * @param loader
	 * @param loaderProgressMonitor
	 * @param data
	 * @param fragment
	 * @param config
	 * @param infoProvider
	 * @param endpointWiringStageConsumer
	 * @param wiringProgressMonitor
	 */
	public AsyncInvocableConnectionProcessor(
			Loader loader,
			ProgressMonitor loaderProgressMonitor,
			Object data,
			String fragment,
			ProcessorConfig<H,E> config,
			BiConsumer<Element, BiConsumer<ProcessorInfo<H,E,Invocable>, ProgressMonitor>> infoProvider,
			Consumer<CompletionStage<?>> endpointWiringStageConsumer,
			ProgressMonitor wiringProgressMonitor) {

		if (!Util.isBlank(fragment)) {
			this.executor = Executors.newFixedThreadPool(Integer.parseInt(fragment));
			this.terminationTimeout = 1;
			this.terminationTimeoutUnit = TimeUnit.MINUTES;
			shutdownExecutor = true;
			
		}
	}	
	
	public AsyncInvocableConnectionProcessor(
			int threads, 
			long terminationTimeout, 
			TimeUnit terminationTimeoutUnit) {
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
	public ProcessorInfo<H,E,Object> parentInfo;
	
	/**
	 * Finds parent processor of type {@link Executor} or {@link Adaptable} to executor
	 * @param parentInfo
	 */
	@Registry
	public void setRegistry(Map<Element, ProcessorInfo<H,E,?>> registry) {
		for (ProcessorInfo<H,E,?> pInfo = parentInfo; executor == null && pInfo != null; pInfo = registry.get(pInfo.getParentProcessorConfig().getElement())) {
			executor = Adaptable.adaptTo(parentInfo.getProcessor(), Executor.class);
		}
	}
	
	protected Invocable asyncTargetEndpoint;
	
	@TargetEndpoint
	public void setTargetEndpoint(Invocable targetEndpoint) {
		if (targetEndpoint != null) {
			asyncTargetEndpoint = targetEndpoint.async(executor);
		}
	}
	
	protected Invocable asyncSourceEndpoint;
	
	@SourceEndpoint
	public void setSourceEndpoint(Invocable sourceEndpoint) {
		if (sourceEndpoint != null) {
			asyncSourceEndpoint = sourceEndpoint.async(executor);
		}
	}
	
	@SourceHandler
	public Invocable sourceHandler = new Invocable() {
		
		@Override
		public <T> T invoke(Object... args) {
			return Objects.requireNonNull(asyncTargetEndpoint).invoke(args);
		}
		
	};
	
	@TargetHandler
	public Invocable targetHandler = new Invocable() {
		
		@Override
		public <T> T invoke(Object... args) {
			return Objects.requireNonNull(asyncSourceEndpoint).invoke(args);
		}
		
	};

	@Override
	public void close() throws Exception {
		if (shutdownExecutor && executor instanceof ExecutorService) {
			ExecutorService executorService = (ExecutorService) executor;
			executorService.shutdown();
			executorService.awaitTermination(terminationTimeout, terminationTimeoutUnit);
		}		
	}	
	
}
