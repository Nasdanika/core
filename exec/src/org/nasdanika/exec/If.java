package org.nasdanika.exec;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.CancellationException;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

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
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.resources.BinaryEntityContainer;

/**
 * Conditional execution
 * @author Pavel
 *
 */
public class If implements Adaptable, Marked {
	
	private static final String CONDITION_KEY = "condition";
	private static final String THEN_KEY = "then";
	private static final String ELSE_KEY = "else";
	
	private Marker marker;
	private Object condition;
	private Object thenBlock;
	private Object elseBlock;
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	@SuppressWarnings("unchecked")
	public If(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof Map) {
			this.marker = marker;
			Map<String,Object> configMap = (Map<String,Object>) config;
			Loader.checkUnsupportedKeys(configMap, CONDITION_KEY, THEN_KEY, ELSE_KEY);
			if (configMap.containsKey(CONDITION_KEY)) {
				condition = configMap.get(CONDITION_KEY);
				if (!(condition instanceof Boolean || condition instanceof String)) {
					throw new ConfigurationException("Condition value shall be either boolean or string", Util.getMarker(configMap, CONDITION_KEY));					
				}
			} else {
				throw new ConfigurationException("Condition is required", marker);
			}
			if (configMap.containsKey(THEN_KEY)) {
				thenBlock = loader.load(configMap.get(THEN_KEY), base, progressMonitor);
			} else {
				throw new ConfigurationException("'then' is required", marker);
			}
			if (configMap.containsKey(ELSE_KEY)) {
				elseBlock = loader.load(configMap.get(ELSE_KEY), base, progressMonitor);
			}
		} else {
			throw new ConfigurationException(getClass().getName() + " configuration shall be a map, got " + config.getClass(), marker);
		}
	}
	
	public If(Marker marker, String condition, Object thenBlock, Object elseBlock) {
		this.marker = marker;
		this.condition = condition;
		this.thenBlock = thenBlock;
		this.elseBlock = elseBlock;
	}

	/**
	 * Base class for command, consumer, and supplier participants.
	 * @author Pavel
	 *
	 * @param <E>
	 */
	private abstract class IfExecutionParticipant<E extends ExecutionParticipant> implements ExecutionParticipant {
		
		protected E thenParticipant;
		protected E elseParticipant;
		private Context context;
		
		IfExecutionParticipant(Context context, E thenParticipant, E elseParticipant) {
			this.context = context;
			this.thenParticipant = thenParticipant;
			this.elseParticipant = elseParticipant;
		}
				
		protected boolean eval(ProgressMonitor progressMonitor) throws Exception {
			if (condition instanceof Boolean) {
				return (Boolean) condition;
			}
			if (Boolean.TRUE.equals(context.interpolate((String) condition))) {
				return true;
			}
			ScriptEngine engine = new ScriptEngineManager().getEngineByMimeType("application/javascript");
			Bindings bindings = engine.createBindings();
			bindings.put("context", context);
			bindings.put("progressMonitor", progressMonitor);
			return Boolean.TRUE.equals(engine.eval(context.interpolateToString((String) condition), bindings));
		}		

		@Override
		public String name() {
			return "If";
		}	
		
		@Override
		public double size() {
			double ret = thenParticipant.size();
			if (elseParticipant == null) {
				return ret;
			}
			return Math.max(ret,  elseParticipant.size());
		}

		@Override
		public Diagnostic diagnose(ProgressMonitor progressMonitor) {		
			if (progressMonitor.isCancelled()) {
				progressMonitor.worked(1, "Cancelled");
				return new BasicDiagnostic(Status.CANCEL, "Progress monitor is cancelled", this);
			}
			BasicDiagnostic ret = new BasicDiagnostic(Status.INFO, name());
			progressMonitor.setWorkRemaining(size());
			ret.add(thenParticipant.splitAndDiagnose(progressMonitor));
			if (elseParticipant != null) {
				ret.add(elseParticipant.splitAndDiagnose(progressMonitor));
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
			thenParticipant.splitAndCommit(progressMonitor);
			if (elseParticipant != null) {
				elseParticipant.splitAndCommit(progressMonitor);
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
			if (elseParticipant != null) {
				result = elseParticipant.splitAndRollback(progressMonitor) && result;
			}
			result = thenParticipant.splitAndRollback(progressMonitor) && result;
			return result;
		}
		
		@Override
		public void close() throws Exception {
			thenParticipant.close();
			if (elseParticipant != null) {
				elseParticipant.close();
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
	
	// --- Command ---	
	
	private class IfCommand extends IfExecutionParticipant<Command> implements Command {

		IfCommand(Context context, Command thenParticipant, Command elseParticipant) {
			super(context, thenParticipant, elseParticipant);
		}

		@Override
		public void execute(ProgressMonitor progressMonitor) throws Exception {
			if (eval(progressMonitor)) {
				thenParticipant.splitAndExecute(progressMonitor);
			} else if (elseParticipant != null) {
				elseParticipant.splitAndExecute(progressMonitor);
			}
		}
		
	}	
	
	private Command createCommand(Context context) throws Exception {
		Command thenCommand = Loader.asCommandFactory(thenBlock).create(context);
		Command elseCommand = elseBlock == null ? null : Loader.asCommandFactory(elseBlock).create(context);
		return new IfCommand(context, thenCommand, elseCommand);		
	}
	
	// --- Consumer ---
	
	private class IfConsumer extends IfExecutionParticipant<Consumer<BinaryEntityContainer>> implements Consumer<BinaryEntityContainer> {

		IfConsumer(Context context, Consumer<BinaryEntityContainer> thenParticipant, Consumer<BinaryEntityContainer> elseParticipant) {
			super(context, thenParticipant, elseParticipant);
		}

		@Override
		public void execute(BinaryEntityContainer container, ProgressMonitor progressMonitor) throws Exception {
			if (eval(progressMonitor)) {
				thenParticipant.splitAndExecute(container, progressMonitor);
			} else if (elseParticipant != null) {
				elseParticipant.splitAndExecute(container, progressMonitor);
			}
		}
				
	}	
	
	private Consumer<BinaryEntityContainer> createConsumer(Context context) throws Exception {
		Consumer<BinaryEntityContainer> thenConsumer = Loader.asConsumerFactory(thenBlock).create(context);
		Consumer<BinaryEntityContainer> elseConsumer = elseBlock == null ? null : Loader.asConsumerFactory(elseBlock).create(context);
		return new IfConsumer(context, thenConsumer, elseConsumer);		
	}

	// --- Supplier ---
		
	private class IfSupplier extends IfExecutionParticipant<Supplier<InputStream>> implements Supplier<InputStream> {
		
		IfSupplier(Context context, Supplier<InputStream> thenParticipant, Supplier<InputStream> elseParticipant) {
			super(context, thenParticipant, elseParticipant);
		}
				
		@Override
		public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
			if (eval(progressMonitor)) {
				return thenParticipant.splitAndExecute(progressMonitor);
			} 
			
			if (elseParticipant == null) {
				return null;
			}
			
			return elseParticipant.splitAndExecute(progressMonitor);
		}
		
	}	
	
	private Supplier<InputStream> createSupplier(Context context) throws Exception {
		Supplier<InputStream>  thenSupplier = Loader.asSupplierFactory(thenBlock).create(context);
		Supplier<InputStream>  elseSupplier = elseBlock == null ? null : Loader.asSupplierFactory(elseBlock).create(context);
		return new IfSupplier(context, thenSupplier, elseSupplier);		
	}
}
