package org.nasdanika.exec.input;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnosable;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * Base class for Property and PropertySet
 * @author Pavel
 *
 */
public abstract class AbstractProperty implements Marked {
		
	protected Marker marker;
	protected String description;
	protected String name;
	protected String icon;
	protected String label;

	protected static final String CONDITION_KEY = "condition";
	protected static final String DESCRIPTION_KEY = "description";
	protected static final String ICON_KEY = "icon";
	protected static final String LABEL_KEY = "label";
	protected static final String NAME_KEY = "name";
	protected static final String VALIDATE_KEY = "validate";
	
	private static final String VALIDATION_CONDITION_KEY = "condition";
	private static final String VALIDATION_SEVERITY_KEY = "severity";
	private static final String VALIDATION_MESSAGE_KEY = "message";	
	
	protected List<FunctionFactory<Map<String, Object>, Diagnosable>> validations = new ArrayList<>();
	protected List<String> conditions = new ArrayList<>();
		
	public AbstractProperty(ObjectLoader loader, String prefix, String name, Object config, URL base, Marker marker, ProgressMonitor monitor) throws Exception {
		if (config instanceof Map) {
			this.marker = marker;
			@SuppressWarnings("unchecked")
			Map<String,Object> configMap = (Map<String,Object>) config;
			
			description = Util.getString(configMap, DESCRIPTION_KEY, null);
			this.name = (prefix == null ? "" : prefix) + Util.getString(configMap, NAME_KEY, name);
			icon = Util.getString(configMap, ICON_KEY, null);
			label = Util.getString(configMap, LABEL_KEY, Util.isBlank(name) ? name : Util.nameToLabel(name));
			
			if (configMap.containsKey(CONDITION_KEY)) {
				Util.loadMultiString(configMap, CONDITION_KEY, conditions::add);
			} 
			
			if (configMap.containsKey(VALIDATE_KEY)) {
				Object vso = configMap.get(VALIDATE_KEY);
				Collection<Object> vSpecs =  vso instanceof Map ? Collections.singleton(vso) : Util.getCollection(configMap, VALIDATE_KEY, null);
				int index = 0;
				for (Object vSpec: vSpecs) {
					if (vSpec instanceof Map) {
						@SuppressWarnings("unchecked")
						Map<String, Object> vSpecMap = (Map<String, Object>) vSpec;
						Util.checkUnsupportedKeys(vSpecMap, VALIDATION_CONDITION_KEY, VALIDATION_MESSAGE_KEY, VALIDATION_SEVERITY_KEY);
						Status severity;
						if (vSpecMap.containsKey(VALIDATION_SEVERITY_KEY)) {
							Object sevSpec = vSpecMap.get(VALIDATION_SEVERITY_KEY);
							if (Status.ERROR.name().equals(sevSpec)) {
								severity = Status.ERROR;
							} else if (Status.WARNING.name().equals(sevSpec)) {
								severity = Status.WARNING;
							} else {
								throw new ConfigurationException("Severity value shall be either " + Status.ERROR.name() + " or " + Status.WARNING.name() + ", got " + config.getClass(), Util.getMarker(vSpecMap, VALIDATION_SEVERITY_KEY));								
							}
						} else {
							severity = Status.ERROR;
						}
						Util.checkRequiredKeys(vSpecMap, VALIDATION_CONDITION_KEY, VALIDATION_MESSAGE_KEY);
						
						SupplierFactory<InputStream> scriptFactory = Util.asSupplierFactory(loader.load(vSpecMap.get(VALIDATION_CONDITION_KEY), base, monitor));
						
						FunctionFactory<BiSupplier<Map<String, Object>, String>, Diagnosable> diagnosableFactory = new FunctionFactory<BiSupplier<Map<String, Object>, String>, Diagnosable>() {

							@Override
							public Function<BiSupplier<Map<String, Object>, String>, Diagnosable> create(Context dCtx) throws Exception {
								
								return new Function<BiSupplier<Map<String, Object>, String>, Diagnosable>() {

									@Override
									public double size() {
										return 1;
									}

									@Override
									public String name() {
										return "Validation condition " + (vSpecMap instanceof Marked ? ((Marked) vSpecMap).getMarker() : "");
									}

									@Override
									public Diagnosable execute(BiSupplier<Map<String, Object>, String> scriptAndBinding, ProgressMonitor progressMonitor) throws Exception {
										return new Diagnosable() {
										
											@Override
											public Diagnostic diagnose(ProgressMonitor progressMonitor) {
												try {
													if (!isEnabled(dCtx)) {
														return new BasicDiagnostic(Status.SUCCESS, String.valueOf(vSpecMap.get(VALIDATION_MESSAGE_KEY)), AbstractProperty.this);													
													}
												} catch (Exception ex) {
													monitor.worked(Status.ERROR, 1, "Exception while evaluating condition: " + ex, AbstractProperty.this, ex); 
													return new BasicDiagnostic(Status.ERROR, "Exception while evaluating condition", AbstractProperty.this, ex);
												}
												try {
													Object result = Util.eval(scriptAndBinding.getSecond(), scriptAndBinding.getFirst());
													return new BasicDiagnostic(Boolean.TRUE.equals(result) ? Status.SUCCESS : severity, String.valueOf(vSpecMap.get(VALIDATION_MESSAGE_KEY)), AbstractProperty.this);
												} catch (Exception e) {
													monitor.worked(Status.ERROR, 1, "Exception while evaluating validation condition at " + (vSpecMap instanceof Marked ? ((Marked) vSpecMap).getMarker() : "") + ": " + e, AbstractProperty.this, e); 
													return new BasicDiagnostic(Status.ERROR, "Exception while evaluating validation condition", AbstractProperty.this, e);
												}
											}
											
										};
									
									}
									
								};
								
							}
							
						};
																
						FunctionFactory<Map<String, Object>, BiSupplier<Map<String, Object>, String>> scriptAndBindingsFactory = scriptFactory.then(Util.TO_STRING).asFunctionFactory();
						validations.add(scriptAndBindingsFactory.then(diagnosableFactory));
					} else {
						throw new ConfigurationException("Validation specification shall be a map, got " + config.getClass(), Util.getMarker(vSpecs, index));						
					}
					++index;
				}
			} 
			
		} else {
			throw new ConfigurationException("Configuration shall be a map, got " + config.getClass(), marker);
		}		
	}
	
	@Override
	public Marker getMarker() {
		return marker;
	}
	
	public abstract Diagnostic diagnose(Context context);
	
	public String getName() {
		return name;
	}
	
	public boolean isEnabled(Context context) throws Exception {
		Map<String,Object> bindings = Collections.singletonMap("context", context);
		for (String condition: conditions) {
			if (!Boolean.TRUE.equals(Util.eval(condition, bindings))) {
				return false;
			}
		}
		return true; 
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "(" + getName() + ", " + marker + ")";
	}
	
}
