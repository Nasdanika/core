package org.nasdanika.exec;

import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.nasdanika.common.Adaptable;
import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Command;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.FilterSupplier;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.descriptors.Descriptor;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.exec.input.PropertySet;

/**
 * Group is a collection of components with input specification and validation.
 * Can be adapted to supplier, consumer, and command factories. Can also be adapted
 * to {@link Descriptor} for use by UI generators.
 * @author Pavel
 *
 */
public class Group implements Adaptable, Marked {
	
	private static final String INPUT_KEY = "input";
	private static final String ELEMENTS_KEY = "elements";
	
	private Marker marker;
	private Object elements;
	private PropertySet input;
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	@SuppressWarnings("unchecked")
	public Group(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof Map) {
			this.marker = marker;
			Map<String,Object> configMap = (Map<String,Object>) config;
			Loader.checkUnsupportedKeys(configMap, INPUT_KEY, ELEMENTS_KEY);
			if (configMap.containsKey(ELEMENTS_KEY)) {
				elements = loader.load(configMap.get(ELEMENTS_KEY), base, progressMonitor);
			} else {
				throw new ConfigurationException("Elements key is missing", marker);
			}
			if (configMap.containsKey(INPUT_KEY)) {
				input = new PropertySet(null, configMap.get(INPUT_KEY), base, marker);
			}
		} else {
			throw new ConfigurationException(getClass().getName() + " configuration shall be a map, got " + config.getClass(), marker);
		}
	}
	
	public Group(Marker marker, Object elements, PropertySet input) {
		this.marker = marker;
		this.elements = elements;
		this.input = input;
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
		
		if (type == Descriptor.class) {
			// TODO
		}
		
		return Adaptable.super.adaptTo(type);
	}	
		
	/**
	 * Supplier of context with {@link Exception} "service" indicating that the catch block shall be executed. 
	 * @param context
	 * @param eRef
	 * @return
	 */
	protected org.nasdanika.common.Supplier<Context> createInputContextSupplier(Context context) {
		return new Supplier<Context>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Input context";
			}

			@Override
			public Context execute(ProgressMonitor progressMonitor) throws Exception {
				return new Context() {

					@Override
					public Object get(String key) {
						// TODO
						return null;
					}

					@Override
					public <T> T get(Class<T> type) {
						// TODO?
						return null;
					}
					
				}.compose(context);
				
			}
			
		};
		
	}
	
	// --- Command ---	
		
	private Command createCommand(Context context) throws Exception {
		return Loader.asCommandFactory(elements).contextify(this::createInputContextSupplier).create(context);
	}
	
	// --- Consumer ---	
		
	private Consumer<BinaryEntityContainer> createConsumer(Context context) throws Exception {
		return Loader.asConsumerFactory(elements).contextify(this::createInputContextSupplier).create(context);
	}
		
	// --- Supplier ---	
		
	private Supplier<InputStream> createSupplier(Context context) throws Exception {
		Supplier<InputStream> elementsSupplier = Loader.asSupplierFactory(elements).contextify(this::createInputContextSupplier).create(context);
		return new FilterSupplier<InputStream>(elementsSupplier) {
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				if (input == null) {
					return super.diagnose(progressMonitor);
				}
				
				Diagnostic inputDiagnostic = input.diagnose(context);
				if (inputDiagnostic == null) {
					return super.diagnose(progressMonitor);
				}
				
				BasicDiagnostic ret = new BasicDiagnostic(Status.SUCCESS, "Diagnostic of "+this, this);
				ret.add(inputDiagnostic);
				ret.add(super.diagnose(progressMonitor));
				return ret;
			}
			
		};
	}
	
}
