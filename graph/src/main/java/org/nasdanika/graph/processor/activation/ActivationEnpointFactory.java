package org.nasdanika.graph.processor.activation;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Consumer;

import org.nasdanika.common.Invocable;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.EndpointFactory;
import org.nasdanika.graph.processor.HandlerType;

/**
 * Base class for endpoints which chunk/parallelize execution into "activations" which are executed as "units of work" 
 * 
 */
public abstract class ActivationEnpointFactory implements EndpointFactory<Object, Object> {
	
	
	protected Collection<ActivationParticipant> activationParticipants = Collections.synchronizedList(new ArrayList<>());

	/**
	 * If isActivator(handler) return true then an endpoint is created for a delayed activation. 
	 */
	@Override
	public Object createEndpoint(Connection connection, Object handler, HandlerType type) {
		if (!isActivator(handler)) {
			return handler;
		}
		
		ActivationParticipant activationParticipant = createActivationParticipant(connection, handler, type);
		activationParticipants.add(activationParticipant);
		return activationParticipant.getEndpoint();
	}
	
	/**
	 * Creates an endpoint for an activator handler.
	 * The endpoint shall be "attachable" to activations. E.g. Supplier or {@link Consumer} or {@link OutputStream}  
	 * @param connection
	 * @param handler
	 * @param type
	 * @return
	 */
	protected abstract ActivationParticipant createActivationParticipant(Connection connection, Object handler, HandlerType type);
	
	/**
	 * If this method returns true, an {@link ActivationParticipant} is created for activating the handler at some later point in time.
	 */
	protected abstract boolean isActivator(Object handler);
	
	/**
	 * Wraps runnable into an activation
	 * @param task
	 * @param taskConsumer
	 * @return
	 */
	protected Runnable wrapTask(Runnable task, Consumer<Runnable> taskConsumer) {
		return new Runnable() {
			
			@Override
			public void run() {
				try (Activation activation = new Activation(activationParticipants)) {
					try {
						task.run();
						activation.submit(taskConsumer);
					} catch (Exception e) {
						activation.error(e);
					}
				}
			}
		};		
	}

	/**
	 * Wraps an {@link Invocable} into an {@link Activation}.
	 * Nested activations are queued and executed in the caller thread.
	 * @param invocable
	 * @return
	 */
	public Invocable wrap(Invocable invocable) {
		return new Invocable() {
			
			@Override
			public Parameter[] getParameters() {
				return invocable.getParameters();
			}
			
			@Override
			public Parameter[][] getAllParameters() {
				return invocable.getAllParameters();
			}
			
			@Override
			public Class<?> getReturnType() {
				return invocable.getReturnType();
			}

			@Override
			public <T> T invoke(Object... args) {
				Queue<Runnable> taskQueue = new ConcurrentLinkedQueue<>(); // "Raw" tasks - not wrapped into activations
				try (Activation activation = new Activation(activationParticipants)) {
					try {
						T result = invocable.invoke(args);
						activation.submit(taskQueue::add);
						return result;
					} catch (Exception e) {
						activation.error(e);
						throw e;
					}
				} finally {
					// queue draining
					Runnable task;
					while ((task = taskQueue.poll()) != null) {
						wrapTask(task, taskQueue::add).run();
					}
				}
			}
			
		};
	}
	
	/**
	 * Wraps an {@link Invocable} into an {@link Activation}.
	 * Nested activations are executed by the executor service.
	 * The call returns when all nested activations complete.
	 * @param invocable
	 * @param executorService
	 * @return
	 */
	public Invocable wrap(Invocable invocable, ExecutorService executorService) {
		return new Invocable() {
			
			@Override
			public Parameter[] getParameters() {
				return invocable.getParameters();
			}
			
			@Override
			public Parameter[][] getAllParameters() {
				return invocable.getAllParameters();
			}
			
			@Override
			public Class<?> getReturnType() {
				return invocable.getReturnType();
			}

			@Override
			public <T> T invoke(Object... args) {
				Queue<Future<?>> futureQueue = new ConcurrentLinkedQueue<>();				
				Consumer<Runnable> taskConsumer = new Consumer<Runnable>() {
					
					@Override
					public void accept(Runnable task) {
						futureQueue.add(executorService.submit(wrapTask(task, this)));
					}
					
				};

				try (Activation activation = new Activation(activationParticipants)) {
					try {
						T result = invocable.invoke(args);
						activation.submit(taskConsumer);
						
						// queue draining
						Future<?> future;
						while ((future = futureQueue.poll()) != null) {
							future.get();
						}
						
						return result;
					} catch (Exception e) {
						activation.error(e);
						NasdanikaException nex = new NasdanikaException(e);
						
						// queue draining
						Future<?> future;
						while ((future = futureQueue.poll()) != null) {
							try {
								future.get();
							} catch (Exception ex) {
								nex.addSuppressed(ex);
							}
						}
						
						throw nex;
					}
				}
			}
			
		};
	}

}
