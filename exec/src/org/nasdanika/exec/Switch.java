package org.nasdanika.exec;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
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
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.resources.BinaryEntityContainer;

/**
 * Conditional execution of one of case blocks or a default block based on expression evaluation result.
 * @author Pavel
 *
 */
public class Switch implements Adaptable, Marked {
	
	private static final String EXPRESSION_KEY = "expression";
	private static final String CASE_KEY = "case";
	private static final String DEFAULT_KEY = "default";
	
	private Marker marker;
	private String expression;
	private Map<Object, Object> cases = new LinkedHashMap<>();
	private Object defaultBlock;
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	@SuppressWarnings("unchecked")
	public Switch(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof Map) {
			this.marker = marker;
			Map<String,Object> configMap = (Map<String,Object>) config;
			Util.checkUnsupportedKeys(configMap, EXPRESSION_KEY, CASE_KEY, DEFAULT_KEY);
			if (configMap.containsKey(EXPRESSION_KEY)) {
				expression = Util.getString(configMap, EXPRESSION_KEY, null);
			} else {
				throw new ConfigurationException("Expression is required", marker);
			}
			if (configMap.containsKey(CASE_KEY)) {
				Map<Object, Object> caseConfig = Util.getMap(configMap, CASE_KEY, null);
				for (Entry<Object, Object> ce: caseConfig.entrySet()) {
					cases.put(ce.getKey(), loader.load(ce.getValue(), base, progressMonitor));					
				}
			}
			if (configMap.containsKey(DEFAULT_KEY)) {
				defaultBlock = loader.load(configMap.get(DEFAULT_KEY), base, progressMonitor);
			}
		} else {
			throw new ConfigurationException(getClass().getName() + " configuration shall be a map, got " + config.getClass(), marker);
		}
	}
	
	public Switch(Marker marker, String expression, Map<Object,Object> cases, Object defaultBlock) {
		this.marker = marker;
		this.expression = expression;
		this.cases = cases;
		this.defaultBlock = defaultBlock;
	}

	/**
	 * Base class for command, consumer, and supplier participants.
	 * @author Pavel
	 *
	 * @param <E>
	 */
	private abstract class SwitchExecutionParticipant<E extends ExecutionParticipant> implements ExecutionParticipant {
		
		protected Map<Object, E> caseParticipants;
		protected E defaultParticipant;
		private Context context;
		
		SwitchExecutionParticipant(Context context, Map<Object, E> caseParticipants, E defaultParticipant) {
			this.context = context;
			this.caseParticipants = caseParticipants;
			this.defaultParticipant = defaultParticipant;
		}
				
		protected Object eval(ProgressMonitor progressMonitor) throws Exception {
			Map<String,Object> bindings = new HashMap<>();
			bindings.put("context", context);
			bindings.put("progressMonitor", progressMonitor);
			return Util.eval(context.interpolateToString(expression), bindings);
		}		

		@Override
		public String name() {
			return "Switch";
		}	
		
		@Override
		public double size() {
			return 1;
		}

		@Override
		public Diagnostic diagnose(ProgressMonitor progressMonitor) {		
			if (progressMonitor.isCancelled()) {
				progressMonitor.worked(1, "Cancelled");
				return new BasicDiagnostic(Status.CANCEL, "Progress monitor is cancelled", this);
			}
			BasicDiagnostic ret = new BasicDiagnostic(Status.INFO, name());
			progressMonitor.setWorkRemaining(size());
			for (E cp: caseParticipants.values()) {
				ret.add(cp.splitAndDiagnose(progressMonitor));
			}
			if (defaultParticipant != null) {
				ret.add(defaultParticipant.splitAndDiagnose(progressMonitor));
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
			for (E cp: caseParticipants.values()) {
				cp.splitAndCommit(progressMonitor);
			}
			if (defaultParticipant != null) {
				defaultParticipant.splitAndCommit(progressMonitor);
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
			if (defaultParticipant != null) {
				result = defaultParticipant.splitAndRollback(progressMonitor) && result;
			}
			for (E cp: caseParticipants.values()) {			
				result = cp.splitAndRollback(progressMonitor) && result;
			}
			return result;
		}
		
		@Override
		public void close() throws Exception {
			for (E cp: caseParticipants.values()) {
				cp.close();
			}
			if (defaultParticipant != null) {
				defaultParticipant.close();
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
	
	private class SwitchCommand extends SwitchExecutionParticipant<Command> implements Command {

		SwitchCommand(Context context, Map<Object, Command> caseParticipants, Command defaultParticipant) {
			super(context, caseParticipants, defaultParticipant);
		}

		@Override
		public void execute(ProgressMonitor progressMonitor) throws Exception {
			Object expr = eval(progressMonitor);
			Command cp = caseParticipants.get(expr);
			if (cp != null) {
				cp.splitAndExecute(progressMonitor);
			} else if (defaultParticipant != null) {
				defaultParticipant.splitAndExecute(progressMonitor);
			}
		}
		
	}	
	
	private Command createCommand(Context context) throws Exception {
		Map<Object, Command> caseCommands = new LinkedHashMap<>();
		for (Entry<Object, Object> ce: cases.entrySet()) {
			caseCommands.put(ce.getKey(), Loader.asCommandFactory(ce.getValue()).create(context));			
		}
		
		Command defaultCommand = defaultBlock == null ? null : Loader.asCommandFactory(defaultBlock).create(context);
		return new SwitchCommand(context, caseCommands, defaultCommand);		
	}
	
	// --- Consumer ---
	
	private class SwitchConsumer extends SwitchExecutionParticipant<Consumer<BinaryEntityContainer>> implements Consumer<BinaryEntityContainer> {

		SwitchConsumer(Context context, Map<Object, Consumer<BinaryEntityContainer>> caseParticipants, Consumer<BinaryEntityContainer> defaultParticipant) {
			super(context, caseParticipants, defaultParticipant);
		}

		@Override
		public void execute(BinaryEntityContainer container, ProgressMonitor progressMonitor) throws Exception {
			Object expr = eval(progressMonitor);
			Consumer<BinaryEntityContainer> cp = caseParticipants.get(expr);
			if (cp != null) {
				cp.splitAndExecute(container, progressMonitor);
			} else if (defaultParticipant != null) {
				defaultParticipant.splitAndExecute(container, progressMonitor);
			}
		}
		
	}	
	
	private Consumer<BinaryEntityContainer> createConsumer(Context context) throws Exception {
		Map<Object, Consumer<BinaryEntityContainer>> caseConsumers = new LinkedHashMap<>();
		for (Entry<Object, Object> ce: cases.entrySet()) {
			caseConsumers.put(ce.getKey(), Loader.asConsumerFactory(ce.getValue()).create(context));			
		}
		
		Consumer<BinaryEntityContainer> defaultConsumer = defaultBlock == null ? null : Loader.asConsumerFactory(defaultBlock).create(context);
		return new SwitchConsumer(context, caseConsumers, defaultConsumer);		
	}

	// --- Supplier ---
	
	private class SwitchSupplier extends SwitchExecutionParticipant<Supplier<InputStream>> implements Supplier<InputStream> {

		SwitchSupplier(Context context, Map<Object, Supplier<InputStream>> caseParticipants, Supplier<InputStream> defaultParticipant) {
			super(context, caseParticipants, defaultParticipant);
		}

		@Override
		public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
			Object expr = eval(progressMonitor);
			Supplier<InputStream> cp = caseParticipants.get(expr);
			if (cp != null) {
				return cp.splitAndExecute(progressMonitor);				
			} 
			
			if (defaultParticipant != null) {
				return defaultParticipant.splitAndExecute(progressMonitor);
			}
			
			return null;
		}
		
	}	
	
	private Supplier<InputStream> createSupplier(Context context) throws Exception {
		Map<Object, Supplier<InputStream>> caseSuppliers = new LinkedHashMap<>();
		for (Entry<Object, Object> ce: cases.entrySet()) {
			caseSuppliers.put(ce.getKey(), Loader.asSupplierFactory(ce.getValue()).create(context));			
		}
		
		Supplier<InputStream> defaultSupplier = defaultBlock == null ? null : Loader.asSupplierFactory(defaultBlock).create(context);
		return new SwitchSupplier(context, caseSuppliers, defaultSupplier);		
	}

}
