package org.nasdanika.exec.input;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnosable;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.descriptors.ChoicesPropertyDescriptor;
import org.nasdanika.common.descriptors.Descriptor;
import org.nasdanika.common.descriptors.ValueDescriptor;
import org.nasdanika.common.descriptors.ValueDescriptor.Control;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.exec.Loader;

public class Property implements Marked {
	
	// Derived from arity, defaults to an optional single value property.
	private int lowerBound = 0;
	private int upperBound = 1;
		
	private Marker marker;
		
	private String description;
	
	private String name;
	
	private String icon;
	
	private String label;

	private boolean editable;

	private Object defaultValue;

	private String type;
	
	private ValueDescriptor.Control controlHint;

	private static final String ARITY_KEY = "arity";
	private static final String CHOICES_KEY = "choices";
	private static final String CONDITIONS_KEY = "conditions";
	private static final String CONTROL_KEY = "control";
	private static final String DEFAULT_VALUE_KEY = "default-value";
	private static final String DESCRIPTION_KEY = "description";
	private static final String ICON_KEY = "icon";
	private static final String LABEL_KEY = "label";
	private static final String NAME_KEY = "name";
	private static final String EDITABLE_KEY = "editable";
	private static final String TYPE_KEY = "type";
	private static final String VALIDATE_KEY = "validate";
	
	private static final String VALIDATION_CONDITION_KEY = "condition";
	private static final String VALIDATION_SEVERITY_KEY = "severity";
	private static final String VALIDATION_MESSAGE_KEY = "message";	
	
	private List<SupplierFactory<Diagnosable>> validations = new ArrayList<>();
		
	public Property(ObjectLoader loader, String prefix, String name, Object config, URL base, Marker marker, ProgressMonitor monitor) throws Exception {
		if (config instanceof Map) {
			this.marker = marker;
			@SuppressWarnings("unchecked")
			Map<String,Object> configMap = (Map<String,Object>) config;
			Util.checkUnsupportedKeys(
					configMap,
					ARITY_KEY,
					CHOICES_KEY,
					CONDITIONS_KEY,
					CONTROL_KEY,
					DEFAULT_VALUE_KEY,
					DESCRIPTION_KEY,
					ICON_KEY,
					LABEL_KEY,
					name == null ? NAME_KEY : null,
					EDITABLE_KEY,
					TYPE_KEY,
					VALIDATE_KEY);
			
			description = Util.getString(configMap, DESCRIPTION_KEY, null);
			this.name = (prefix == null ? "" : prefix) + Util.getString(configMap, NAME_KEY, name);
			icon = Util.getString(configMap, ICON_KEY, null);
			label = Util.getString(configMap, LABEL_KEY, Util.nameToLabel(name));
			editable = !Boolean.FALSE.equals(configMap.get(EDITABLE_KEY));
			type = Util.getString(configMap, TYPE_KEY, String.class.getName());
			defaultValue = configMap.get(DEFAULT_VALUE_KEY);
	
			if (configMap.containsKey(ARITY_KEY)) {
				Object arity = configMap.get(ARITY_KEY);
				if (arity instanceof Integer) {
					lowerBound = (Integer) arity;
					upperBound = (Integer) arity;
				} else if (arity instanceof String) {
					String arityStr = (String) arity;
					int idx = arityStr.indexOf("..");
					if (idx == -1) {
						if ("*".equals(arity)) {
							lowerBound = 0;
							upperBound = -1;
						} else {
							lowerBound = Integer.parseInt(arityStr);
							upperBound = lowerBound;
						}
					} else {
						lowerBound = Integer.parseInt(arityStr.substring(0, idx));
						String ub = arityStr.substring(idx + 2);
						upperBound = "*".equals(ub) ? -1 : Integer.parseInt(ub);
					}
				} else {
					throw new ConfigurationException("Arity value must be a string or an integer", Util.getMarker(configMap, ARITY_KEY));		
				}
			} 
			
			if (configMap.containsKey(CHOICES_KEY)) {
//				* ``choices`` - property value choices. List or map - see details below.    
				throw new UnsupportedOperationException("Not yet supported");
			} 
			
			if (configMap.containsKey(CONDITIONS_KEY)) {
//				* ``conditions`` - a single value or a list of JavaScript expressions which should all evaluate to true for this property to be displayed, validated, and injected into the context. Script bindings:
//			    * ``context`` - ${javadoc/org.nasdanika.common.Context} constructed from the group context and user input collected so far.
				throw new UnsupportedOperationException("Not yet supported");
			} 
			
			if (configMap.containsKey(CONTROL_KEY)) {
				String control = Util.getString(configMap, CONTROL_KEY, null);
				switch (control) {
				case "date":
					controlHint = Control.DATE;
					break;
				case "time":
					controlHint = Control.TIME;
					break;
				case "drop-down":
					controlHint = Control.DROP_DOWN;
					break;
				case "checkbox":
					controlHint = Control.CHECKBOX;
					break;
				case "file":
					controlHint = Control.FILE;
					break;
				case "number":
					controlHint = Control.NUMBER;
					break;
				case "password":
					controlHint = Control.PASSWORD;
					break;
				case "radio":
					controlHint = Control.RADIO;
					break;
				case "text":
					controlHint = Control.TEXT;
					break;
				case "text-area":
					controlHint = Control.TEXT_AREA;
					break;
				default:
					throw new ConfigurationException("Invalid control type: " + control, Util.getMarker(configMap, ARITY_KEY));							
				}
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
						
						SupplierFactory<InputStream> scriptFactory = Loader.asSupplierFactory(loader.load(vSpecMap.get(VALIDATION_CONDITION_KEY), base, monitor));
						
						FunctionFactory<String, Diagnosable> diagnosableFactory = new FunctionFactory<String, Diagnosable>() {

							@Override
							public Function<String, Diagnosable> create(Context dCtx) throws Exception {
								
								return new Function<String, Diagnosable>() {

									@Override
									public double size() {
										return 1;
									}

									@Override
									public String name() {
										return "Validation condition " + (vSpecMap instanceof Marked ? ((Marked) vSpecMap).getMarker() : "");
									}

									@Override
									public Diagnosable execute(String script, ProgressMonitor progressMonitor) throws Exception {
										return new Diagnosable() {
										
											@Override
											public Diagnostic diagnose(ProgressMonitor progressMonitor) {
												if (!isEnabled(dCtx)) {
													return new BasicDiagnostic(Status.SUCCESS, String.valueOf(vSpecMap.get(VALIDATION_MESSAGE_KEY)), Property.this);													
												}
												Map<String, Object> bindings = new HashMap<>();
												bindings.put("context", dCtx);
												bindings.put("value", dCtx.get(getName()));
												try {
													Object result = Util.eval(script, bindings);
													return new BasicDiagnostic(Boolean.TRUE.equals(result) ? Status.SUCCESS : severity, String.valueOf(vSpecMap.get(VALIDATION_MESSAGE_KEY)), Property.this);
												} catch (Exception e) {
													monitor.worked(Status.ERROR, 1, "Exception while evaluating validation condition at " + (vSpecMap instanceof Marked ? ((Marked) vSpecMap).getMarker() : "") + ": " + e, Property.this, e); 
													return new BasicDiagnostic(Status.ERROR, "Exception while evaluating validation condition", Property.this);
												}
											}
											
										};
									
									}
									
								};
								
							}
							
						};
																
						validations.add(scriptFactory.then(Util.TO_STRING).then(diagnosableFactory));
					} else {
						throw new ConfigurationException("Validation specification shall be a map, got " + config.getClass(), Util.getMarker(vSpecs, index));						
					}
					++index;
				}
			} 
			
		} else {
			throw new ConfigurationException("Property set configuration shall be a map, got " + config.getClass(), marker);
		}		
	}
	@Override
	public Marker getMarker() {
		return marker;
	}
	
	public Diagnostic diagnose(Context context) {
		BasicDiagnostic ret = new BasicDiagnostic(Status.SUCCESS, "Diagnostic of property '"+name+"'", this);
		
		// TODO - conditions, validate only if true
		
		// Values as a list
		List<Object> values = new ArrayList<>();
		Object pv = context.get(name);
		if (pv != null) {
			if (pv.getClass().isArray()) {
				for (int i=0; i < Array.getLength(pv); ++i) {
					values.add(Array.get(pv, i));
				}
			} else if (pv instanceof Collection) {
				values.addAll((Collection<?>) pv);
			} else {
				values.add(pv);
			}
		}
		
		// Arity
		if (lowerBound == 1 && upperBound ==1 && values.isEmpty()) {
			if (lowerBound > values.size()) {
				ret.add(new BasicDiagnostic(Status.ERROR, "Required property", this));
			}			
		} else {
			if (lowerBound > values.size()) {
				ret.add(new BasicDiagnostic(Status.ERROR, "Number of values " + values.size() + " is less than the lower bound " + lowerBound, this));
			}
			
			if (upperBound != -1 && upperBound < values.size()) {
				ret.add(new BasicDiagnostic(Status.ERROR, "Number of values " + values.size() + " is greater than the upper bound " + upperBound, this));
			}
		}		
		
		// TODO Type - convertable using context converter or default converter
		
		// TODO Choices
		
		
		// Validations
		NullProgressMonitor progressMonitor = new NullProgressMonitor();
		for (SupplierFactory<Diagnosable> validation: validations) {
			try {
				ret.add(validation.create(context).execute(progressMonitor).diagnose(progressMonitor));
			} catch (Exception e) {
				ret.add(new BasicDiagnostic(Status.ERROR, "Could not create validation: " + e, this, e));				
			}
		}
		
		return ret;
	}
	public Object getDefaultValue() {
		return defaultValue;
	}
	public String getName() {
		return name;
	}
	
	protected boolean isEnabled(Context context) {
		return true; // TODO - conditions
	}
	
	public ChoicesPropertyDescriptor createPropertyDescriptor(MutableContext context) {
		return new ChoicesPropertyDescriptor() {

			@Override
			public String getIcon() {
				return icon;
			}

			@Override
			public String getLabel() {
				return label;
			}

			@Override
			public String getDescription() {
				return description;
			}

			@Override
			public boolean isEnabled() {
				return Property.this.isEnabled(context);
			}

			@Override
			public String getName() {
				return name;
			}

			@Override
			public Diagnostic set(Object value) {
				Diagnostic diagnostic = Property.this.diagnose(Context.singleton(getName(), value).compose(context));
//				if (diagnostic.getStatus() != Status.ERROR) {
					context.put(getName(), value);
//				}
				return diagnostic;
			}
						
			private void setSource(Diagnostic diagnostic) {
				List<Object> data = diagnostic.getData();
				data.replaceAll(e -> e == Property.this ? this : e);
				diagnostic.getChildren().forEach(this::setSource);			
			}			
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				Diagnostic diagnostic = Property.this.diagnose(context);
				setSource(diagnostic);
				return diagnostic;
			}

			@Override
			public Object get() {
				Object ret = context.get(getName());
				return ret == null ? defaultValue : ret;
			}

			@Override
			public int getLowerBound() {
				return lowerBound;
			}

			@Override
			public int getUpperBound() {
				return upperBound;
			}

			@Override
			public boolean isEditable() {
				return editable;
			}

			@Override
			public List<Descriptor> getChoices() {
				// TODO 
				return null;
			}

			@Override
			public Control getControlHint() {
				return controlHint;
			}

		};
	}
	
	@Override
	public String toString() {
		return getClass().getName() + "(" + getName() + ", " + marker + ")";
	}
	
}
