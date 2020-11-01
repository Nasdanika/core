package org.nasdanika.exec.input;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.Status;
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
	private static final String VALIDATIONS_KEY = "validations";	
	
	public Property(String prefix, String name, Object config, URL base, Marker marker) {
		if (config instanceof Map) {
			this.marker = marker;
			@SuppressWarnings("unchecked")
			Map<String,Object> configMap = (Map<String,Object>) config;
			Loader.checkUnsupportedKeys(
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
					VALIDATIONS_KEY);
			
			description = Util.getString(configMap, DESCRIPTION_KEY, null);
			this.name = (prefix == null ? "" : prefix) + Util.getString(configMap, NAME_KEY, name);
			icon = Util.getString(configMap, ICON_KEY, null);
			label = Util.getString(configMap, LABEL_KEY, null);
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
				throw new UnsupportedOperationException("Not yet supported");
			} 
			
			if (configMap.containsKey(VALIDATIONS_KEY)) {
//				* ``validations`` - a map or a list of maps containing cross-property validations. Map keys:
//			    * ``condition`` - JavaScript condition which must evaluate to true for the validation to pass. Has the same bindings as ``conditions`` plus ``value`` binding containing property value.
//			    * ``severity`` - ``WARNING`` or ``ERROR``.
//			    * ``message`` - diagnostic message. 	
				throw new UnsupportedOperationException("Not yet supported");
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
				ret.add(new BasicDiagnostic(Status.ERROR, "Required property"));
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
		
		
		// TODO Validations
		
		return ret;
	}
	public Object getDefaultValue() {
		return defaultValue;
	}
	public String getName() {
		return name;
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
				// TODO - conditions
				return true;
			}

			@Override
			public String getName() {
				return name;
			}

			@Override
			public Diagnostic set(Object value) {
				Diagnostic diagnostic = Property.this.diagnose(Context.singleton(getName(), value).compose(context));
				if (diagnostic.getStatus() != Status.ERROR) {
					context.put(getName(), value);
				}
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
	
}
