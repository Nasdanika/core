package org.nasdanika.exec;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.CancellationException;

import org.nasdanika.common.Adaptable;
import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Command;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ExecutionParticipant;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reference;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.resources.BinaryEntityContainer;

/**
 * Try-catch-finally block.
 * @author Pavel
 *
 */
public class Block implements Adaptable, Marked {
	
	private static final String TRY_KEY = "try";
	private static final String CATCH_KEY = "catch";
	private static final String FINALLY_KEY = "finally";
	
	// Error injected into the catch context.
	private static final String ERROR_KEY = "error";
	private static final String ERROR_TYPE_KEY = "error/type";
	private static final String ERROR_MESSAGE_KEY = "error/message";
//	private static final String ERROR_STACK_TRACE_KEY = "error/stack-trace";
	
	private Marker marker;
	private Object tryBlock;
	private Object catchBlock;
	private Object finallyBlock;
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	@SuppressWarnings("unchecked")
	public Block(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof Map) {
			this.marker = marker;
			Map<String,Object> configMap = (Map<String,Object>) config;
			Util.checkUnsupportedKeys(configMap, TRY_KEY, CATCH_KEY, FINALLY_KEY);
			if (configMap.containsKey(TRY_KEY)) {
				tryBlock = loader.load(configMap.get(TRY_KEY), base, progressMonitor);
			} else {
				throw new ConfigurationException("Try block is required", marker);
			}
			if (configMap.containsKey(CATCH_KEY)) {
				catchBlock = loader.load(configMap.get(CATCH_KEY), base, progressMonitor);
			}
			if (configMap.containsKey(FINALLY_KEY)) {
				finallyBlock = loader.load(configMap.get(FINALLY_KEY), base, progressMonitor);
			}
		} else {
			throw new ConfigurationException(getClass().getName() + " configuration shall be a map, got " + config.getClass(), marker);
		}
	}
	
	public Block(Marker marker, Object tryBlock, Object catchBlock, Object finallyBlock) {
		this.marker = marker;
		this.tryBlock = tryBlock;
		this.catchBlock = catchBlock;
		this.finallyBlock = finallyBlock;
	}

	/**
	 * Base class for command, consumer, and supplier participants.
	 * @author Pavel
	 *
	 * @param <E>
	 */
	private static abstract class BlockExecutionParticipant<E extends ExecutionParticipant> implements ExecutionParticipant {
		
		protected E tryParticipant;
		protected E catchParticipant;
		protected E finallyParticipant;
		
		BlockExecutionParticipant(E tryParticipant, E catchParticipant, E finallyParticipant) {
			this.tryParticipant = tryParticipant;
			this.catchParticipant = catchParticipant;
			this.finallyParticipant = finallyParticipant;
		}

		@Override
		public String name() {
			return "Block";
		}	
		
		@Override
		public double size() {
			double ret = tryParticipant.size();
			if (catchParticipant != null) {
				ret += catchParticipant.size();
			}
			if (finallyParticipant != null) {
				ret += finallyParticipant.size();
			}
			return ret;
		}

		@Override
		public Diagnostic diagnose(ProgressMonitor progressMonitor) {		
			if (progressMonitor.isCancelled()) {
				progressMonitor.worked(1, "Cancelled");
				return new BasicDiagnostic(Status.CANCEL, "Progress monitor is cancelled", this);
			}
			BasicDiagnostic ret = new BasicDiagnostic(Status.INFO, name());
			progressMonitor.setWorkRemaining(size());
			ret.add(tryParticipant.splitAndDiagnose(progressMonitor));
			if (catchParticipant != null) {
				ret.add(catchParticipant.splitAndDiagnose(progressMonitor));
			}
			if (finallyParticipant != null) {
				ret.add(finallyParticipant.splitAndDiagnose(progressMonitor));
			}
			return ret;
		}
		
		@Override
		public void commit(ProgressMonitor progressMonitor) throws Exception {
			if (progressMonitor.isCancelled()) {
				progressMonitor.worked(1, "Cancelled");
				throw new CancellationException();
			}
			progressMonitor.setWorkRemaining(size());
			tryParticipant.splitAndCommit(progressMonitor);
			if (catchParticipant != null) {
				catchParticipant.splitAndCommit(progressMonitor);
			}
			if (finallyParticipant != null) {
				finallyParticipant.splitAndCommit(progressMonitor);
			}
		}
		
		@Override
		public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
			if (progressMonitor.isCancelled()) {
				progressMonitor.worked(1, "Cancelled");
				throw new CancellationException();
			}
			progressMonitor.setWorkRemaining(size());
			boolean result = true;
			if (finallyParticipant != null) {
				result = finallyParticipant.splitAndRollback(progressMonitor) && result;
			}
			if (catchParticipant != null) {
				result = catchParticipant.splitAndRollback(progressMonitor) && result;
			}
			result = tryParticipant.splitAndRollback(progressMonitor) && result;
			return result;
		}
		
		@Override
		public void close() throws Exception {
			tryParticipant.close();
			if (catchParticipant != null) {
				catchParticipant.close();
			}
			if (finallyParticipant != null) {
				finallyParticipant.close();
			}
		}
		
		@Override
		public String toString() {
			return super.toString() + " " + name() + " " + size();
		}

	}
	
	/**
	 * Adapts to either {@link ConsumerFactory} or {@link SupplierFactory}.
	 * In the first case the consumer is expected to take {@link BinaryEntityContainer}. 
	 * In the latter case supplied results are expected to be {@link InputStream} and are joined into a single input stream.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <T> T adaptTo(Class<T> type) {		
		if (type == CommandFactory.class) {
			return (T) (CommandFactory) this::createCommand;															
		}
		
		if (type == ConsumerFactory.class) {
			return (T) (ConsumerFactory<BinaryEntityContainer>) this::createConsumer;															
		}
		
		if (type == SupplierFactory.class) {
			return (T) (SupplierFactory<InputStream>) this::createSupplier;															
		}
		
		return Adaptable.super.adaptTo(type);
	}	
		
	/**
	 * Supplier of context with {@link Exception} "service" indicating that the catch block shall be executed. 
	 * @param context
	 * @param eRef
	 * @return
	 */
	protected org.nasdanika.common.Supplier<Context> createCatchContextSupplier(Context context, Reference<Exception> eRef) {
		return new Supplier<Context>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Injecting error info";
			}

			@Override
			public Context execute(ProgressMonitor progressMonitor) throws Exception {
				return new Context() {

					@Override
					public Object get(String key) {
						Exception exception = eRef.get();

						if (ERROR_KEY.equals(key)) {
							return exception;
						}
						if (exception != null) {
							if (ERROR_TYPE_KEY.equals(key)) {
								return exception.getClass().getName();
							}
							if (ERROR_MESSAGE_KEY.equals(key)) {
								return exception.getMessage();
							}
						}
						
						return null;
					}

					@SuppressWarnings("unchecked")
					@Override
					public <T> T get(Class<T> type) {
						return type == Exception.class ? (T) eRef.get() : null;
					}
					
				}.compose(context);
				
			}
			
		};
		
	}
	
	// --- Command ---	
	
	private static class BlockCommand extends BlockExecutionParticipant<Command> implements Command {

		private Reference<Exception> eRef;

		BlockCommand(
				Command tryParticipant,
				Command catchParticipant,
				Command finallyParticipant,
				Reference<Exception> eRef) {
			super(tryParticipant, catchParticipant, finallyParticipant);
			this.eRef = eRef;
		}

		@Override
		public void execute(ProgressMonitor progressMonitor) throws Exception {
			try {
				tryParticipant.splitAndExecute(progressMonitor);
			} catch (Exception e) {
				tryParticipant.splitAndRollback(progressMonitor);
				eRef.set(e);
				if (catchParticipant == null) {
					throw e;
				}
				catchParticipant.splitAndExecute(progressMonitor);
			} finally {
				if (finallyParticipant != null) {
					finallyParticipant.splitAndExecute(progressMonitor);
				}
			}			
		}
		
	}	
	
	private Command createCommand(Context context) throws Exception {
		Reference<Exception> eRef = new Reference<Exception>();
		Command tryCommand = Loader.asCommandFactory(tryBlock).create(context);
		Command catchCommand = catchBlock == null ? null : Loader.asCommandFactory(catchBlock).contextify(ctx -> createCatchContextSupplier(ctx, eRef)).create(context); 
		Command finallyCommand = finallyBlock == null ? null : Loader.asCommandFactory(finallyBlock).create(context);
		return new BlockCommand(tryCommand, catchCommand, finallyCommand, eRef);		
	}
	
	// --- Consumer ---
	
	private static class BlockConsumer extends BlockExecutionParticipant<Consumer<BinaryEntityContainer>> implements Consumer<BinaryEntityContainer> {

		private Reference<Exception> eRef;

		BlockConsumer(
				Consumer<BinaryEntityContainer> tryParticipant,
				Consumer<BinaryEntityContainer> catchParticipant,
				Consumer<BinaryEntityContainer> finallyParticipant,
				Reference<Exception> eRef) {
			super(tryParticipant, catchParticipant, finallyParticipant);
			this.eRef = eRef;
		}

		@Override
		public void execute(BinaryEntityContainer container, ProgressMonitor progressMonitor) throws Exception {
			try {
				tryParticipant.splitAndExecute(container, progressMonitor);
			} catch (Exception e) {
				tryParticipant.splitAndRollback(progressMonitor);
				eRef.set(e);
				if (catchParticipant == null) {
					throw e;
				}
				catchParticipant.splitAndExecute(container, progressMonitor);
			} finally {
				if (finallyParticipant != null) {
					finallyParticipant.splitAndExecute(container, progressMonitor);
				}
			}			
		}
		
	}	
	
	private Consumer<BinaryEntityContainer> createConsumer(Context context) throws Exception {
		Reference<Exception> eRef = new Reference<Exception>();
		Consumer<BinaryEntityContainer> tryConsumer = Loader.asConsumerFactory(tryBlock).create(context);
		Consumer<BinaryEntityContainer> catchConsumer = catchBlock == null ? null : Loader.asConsumerFactory(catchBlock).contextify(ctx -> createCatchContextSupplier(ctx, eRef)).create(context); 
		Consumer<BinaryEntityContainer> finallyConsumer = finallyBlock == null ? null : Loader.asConsumerFactory(finallyBlock).create(context);
		return new BlockConsumer(tryConsumer, catchConsumer, finallyConsumer, eRef);		
	}

	// --- Supplier ---
		
	private static class BlockSupplier extends BlockExecutionParticipant<Supplier<InputStream>> implements Supplier<InputStream> {

		private Reference<Exception> eRef;

		BlockSupplier(
				Supplier<InputStream> tryParticipant,
				Supplier<InputStream> catchParticipant,
				Supplier<InputStream> finallyParticipant,
				Reference<Exception> eRef) {
			super(tryParticipant, catchParticipant, finallyParticipant);
			this.eRef = eRef;
		}

		private InputStream executeTryCatch(ProgressMonitor progressMonitor) throws Exception {
			try {
				return tryParticipant.splitAndExecute(progressMonitor);
			} catch (Exception e) {
				tryParticipant.splitAndRollback(progressMonitor);
				eRef.set(e);
				if (catchParticipant == null) {
					throw e;
				}
				return catchParticipant.splitAndExecute(progressMonitor);
			}			
		}

		@Override
		public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
			if (finallyParticipant == null) {
				return executeTryCatch(progressMonitor);
			} 
			
			InputStream tcResult = null;
			Exception ex = null;
			InputStream finallyResult = null;
			try {
				tcResult = executeTryCatch(progressMonitor);
			} catch (Exception e) {
				ex = e;
			} finally {
				finallyResult = finallyParticipant.splitAndExecute(progressMonitor);
			}
			if (ex == null) {
				return Util.join(tcResult, finallyResult);
			}
			throw ex;			
		}
		
	}	
	
	private Supplier<InputStream> createSupplier(Context context) throws Exception {
		Reference<Exception> eRef = new Reference<Exception>();
		Supplier<InputStream>  trySupplier = Loader.asSupplierFactory(tryBlock).create(context);
		Supplier<InputStream> catchSupplier = catchBlock == null ? null : Loader.asSupplierFactory(catchBlock).contextify(ctx -> createCatchContextSupplier(ctx, eRef)).create(context); 
		Supplier<InputStream>  finallySupplier = finallyBlock == null ? null : Loader.asSupplierFactory(finallyBlock).create(context);
		return new BlockSupplier(trySupplier, catchSupplier, finallySupplier, eRef);		
	}

}
