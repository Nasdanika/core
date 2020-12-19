package org.nasdanika.exec.input;

import java.lang.reflect.Array;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Diagnosable;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.common.descriptors.ChoicesPropertyDescriptor;
import org.nasdanika.common.descriptors.Descriptor;
import org.nasdanika.common.descriptors.ValueDescriptor;
import org.nasdanika.common.descriptors.ValueDescriptor.Control;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;

public class Property extends AbstractProperty {
	
	// Derived from arity, defaults to an optional single value property.
	private int lowerBound = 0;
	private int upperBound = 1;
	private Object editable;

	private Object defaultValue;

	private java.util.function.Function<Object,Object> parser = java.util.function.Function.identity();
	private java.util.function.Function<Object,Object> formatter = java.util.function.Function.identity();
	
	private ValueDescriptor.Control controlHint;

	private static final String ARITY_KEY = "arity";
	private static final String CHOICES_KEY = "choices";
	private static final String CONTROL_KEY = "control";
	private static final String DEFAULT_VALUE_KEY = "default-value";
	private static final String EDITABLE_KEY = "editable";
	private static final String TYPE_KEY = "type";
	
	private List<Choice> choices;
		
	public Property(ObjectLoader loader, String prefix, String name, Object config, URL base, Marker marker, ProgressMonitor monitor) throws Exception {
		super(loader, prefix, name, config, base, marker, monitor);
		@SuppressWarnings("unchecked")
		Map<String,Object> configMap = (Map<String,Object>) config;
		Util.checkUnsupportedKeys(
				configMap,
				ARITY_KEY,
				CHOICES_KEY,
				CONDITION_KEY,
				CONTROL_KEY,
				DEFAULT_VALUE_KEY,
				DESCRIPTION_KEY,
				ICON_KEY,
				LABEL_KEY,
				name == null ? NAME_KEY : null,
				EDITABLE_KEY,
				TYPE_KEY,
				VALIDATE_KEY);
		
		editable = configMap.get(EDITABLE_KEY);
		if (configMap.containsKey(TYPE_KEY)) {
			String typeName = Util.getString(configMap, TYPE_KEY, null);				
			if (typeName.startsWith("date(") && typeName.endsWith(")")) {
				SimpleDateFormat format = new SimpleDateFormat(typeName.substring("date(".length(), typeName.length() -1));
				parser = val -> {
					try {
						return val instanceof Date ? val : format.parse((String) val);
					} catch (ParseException e) {
						throw new NasdanikaException(e);
					}
				};
				formatter = val -> format.format((Date) val);
			} else {				
				if ("int".equals(typeName)) {
					typeName = Integer.class.getName();
				} else if ("date".equals(typeName)) {
					typeName = Date.class.getName();					
				}
				Class<?> type = getClass().getClassLoader().loadClass(typeName);
				parser = val -> DefaultConverter.INSTANCE.convert(val, type);
			}
		}
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
			choices = Choice.loadChoices(configMap.get(CHOICES_KEY), Util.getMarker(configMap, CHOICES_KEY), parser);
		} 
		
		if (configMap.containsKey(CONTROL_KEY)) {
			String control = Util.getString(configMap, CONTROL_KEY, null);
			switch (control) {
			case "date":
				controlHint = Control.DATE;
				break;
			case "date-time":
				controlHint = Control.DATE_TIME;
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
	}
	
	public Diagnostic diagnose(Context context) {
		BasicDiagnostic ret = new BasicDiagnostic(Status.SUCCESS, "Diagnostic of property '"+name+"'", this);
		try {
			if (isEnabled(context)) {								
				// Values as a list
				List<Object> values = new ArrayList<>();
				Object pv = context.get(name);
				if (pv != null) {
					if (pv.getClass().isArray()) {
						for (int i=0; i < Array.getLength(pv); ++i) {
							values.add(parser.apply(Array.get(pv, i)));
						}
					} else if (pv instanceof Collection) {
						for (Object pvv: (Collection<?>) pv) {
							values.add(parser.apply(pvv));							
						}
					} else {
						values.add(parser.apply(pv));
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
												
				if (choices != null) {
					for (Object value: values) {
						boolean isValidChoice = false;
						for (Choice cc: choices) {
							if (cc.isEnabled(context) && isValidChoice(value, cc)) {
								isValidChoice = true;
								break;
							}
						}
						if (!isValidChoice) {
							ret.add(new BasicDiagnostic(Status.ERROR, "Invalid choice: " + formatter.apply(value), this));							
						}
					}
					
				}
				
				// Validations
				Map<String, Object> bindings = new HashMap<>();
				bindings.put("context", context);
				bindings.put("values", values);
				
				NullProgressMonitor progressMonitor = new NullProgressMonitor();
				for (Object value: values) {
					for (FunctionFactory<Map<String, Object>, Diagnosable> validation: validations) {
						try {
							bindings.put("value", value);
							ret.add(validation.create(context).execute(bindings, progressMonitor).diagnose(progressMonitor));
						} catch (Exception e) {
							ret.add(new BasicDiagnostic(Status.ERROR, "Could not create validation: " + e, this, e));				
						}
					}
				}
				
			}
		} catch (Exception e) {
			ret.add(new BasicDiagnostic(Status.ERROR, "Exception in isEnabled(): " + e, this, e));				
		}
				
		return ret;
	}
	
	private boolean isValidChoice(Object value, Choice choice) {
		if (choice.getChoices() == null) {
			return choice.getValue().equals(value);
		}
		for (Choice cc: choice.getChoices()) {
			if (isValidChoice(value, cc)) {
				return true;
			}
		}
		return false;
	}
	
	public Object getDefaultValue() {
		return defaultValue;
	}
	
	public ChoicesPropertyDescriptor createPropertyDescriptor(MutableContext context) {
		return new ChoicesPropertyDescriptor() {

			@Override
			public String getIcon() {
				return context.interpolateToString(icon);
			}

			@Override
			public String getLabel() {
				return context.interpolateToString(label);
			}

			@Override
			public String getDescription() {
				return context.interpolateToString(description);
			}

			@Override
			public boolean isEnabled() {
				try {
					return Property.this.isEnabled(context);
				} catch (Exception e) {
					throw new NasdanikaException(e);
				}
			}

			@Override
			public String getName() {
				return name;
			}

			@Override
			public Diagnostic set(Object value) {
				Object parsedValue = parser.apply(value);
				Diagnostic diagnostic = Property.this.diagnose(Context.singleton(getName(), parsedValue).compose(context));
				context.put(getName(), parsedValue);
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

			@SuppressWarnings({ "unchecked" })
			@Override
			public Object get() {
				Object ret = context.get(getName());
				if (ret != null) {
					return formatter.apply(ret);
				}
				
				if (defaultValue instanceof String) {
					return context.interpolate((String) defaultValue);
				}
				
				if (defaultValue instanceof Map) {
					return context.interpolate((Map<String,?>) defaultValue);
				}
				
				if (defaultValue instanceof Collection) {
					return context.interpolate((Collection<?>) defaultValue);
				}
				
				return formatter.apply(defaultValue);
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
				if (editable instanceof Boolean) {
					return (Boolean) editable;
				}
				
				if (editable instanceof String) {
					Map<String,Object> bindings = Collections.singletonMap("context", context);
					try {
						return Boolean.TRUE.equals(Util.eval((String) editable, bindings));
					} catch (Exception e) {
						throw new NasdanikaException(e);
					}
				}
				return true;
			}

			@Override
			public List<Descriptor> getChoices() {
				if (choices == null) {
					return null;
				}
				return choices.stream().map(c -> c.toDescriptor(context)).filter(d -> d.isEnabled()).collect(Collectors.toList());
			}

			@Override
			public Control getControlHint() {
				return controlHint;
			}

		};
	}
	
}
