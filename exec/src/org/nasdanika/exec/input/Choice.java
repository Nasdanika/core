package org.nasdanika.exec.input;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.Context;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.exec.Loader;

public class Choice implements Marked {
	
	private Marker marker;
	private List<Choice> choices;
	private String description;
	private String icon;
	private String label;
	private String value;
	private List<String> conditions = new ArrayList<>();

	/**
	 * Constructor from scalar
	 * @param marker
	 * @param value
	 */
	public Choice(Marker marker, String value) {
		this.marker = marker;
		this.value = value;
		this.label = value;
	}

	/**
	 * Constructor from value -> label map
	 * @param marker
	 * @param value
	 */
	public Choice(Marker marker, String value, String label) {
		this.marker = marker;
		this.value = value;
		this.label = label;
	}
	
	private static final String CHOICES_KEY = "choices";
	private static final String CONDITION_KEY = "condition";
	private static final String VALUE_KEY = "value";
	private static final String DESCRIPTION_KEY = "description";
	private static final String ICON_KEY = "icon";
	private static final String LABEL_KEY = "label";	
	
	/**
	 * Constructor from configuration map
	 * @param marker
	 * @param value Not null when constructed from value to spec map entry
	 * @param config
	 */
	public Choice(Marker marker, String value, Map<String,Object> config) {
		this.marker = marker;
		Util.checkUnsupportedKeys(
				config, 
				CHOICES_KEY,
				CONDITION_KEY,
				VALUE_KEY,
				DESCRIPTION_KEY,
				ICON_KEY,
				LABEL_KEY);
		
		if (value == null) {
			Util.checkRequiredKeys(config, VALUE_KEY);
			this.value = Util.getString(config, VALUE_KEY, null);
		} else {
			if (config.containsKey(VALUE_KEY)) {
				throw new ConfigurationException("Value key cannot be used in map choices", Util.getMarker(config, VALUE_KEY));
			}
			this.value = value;
		}
		
		icon = Util.getString(config, ICON_KEY, null);
		label = Util.getString(config, LABEL_KEY, value);
		description = Util.getString(config, DESCRIPTION_KEY, null);
				
		Loader.loadMultiString(config, CONDITION_KEY, conditions::add);
		
		if (config.containsKey(CHOICES_KEY)) {
			Marker choicesMarker = Util.getMarker(config, CHOICES_KEY);
			if (value != null) {
				throw new ConfigurationException("Nested choices are not supported in choices with values", choicesMarker);				
			}
			choices = loadChoices(config.get(CHOICES_KEY), choicesMarker);
		}		
		
	}
	
	@SuppressWarnings("unchecked")
	static List<Choice> loadChoices(Object choicesConfig, Marker choicesMarker) {
		List<Choice> ret = new ArrayList<>();
		if (choicesConfig instanceof Collection) {
			int index = 0;
			for (Object che: (Collection<?>) choicesConfig) {
				Marker eMarker = Util.getMarker((Collection<?>) choicesConfig, index);
				if (che instanceof String) {
					ret.add(new Choice(eMarker, (String) che));
				} else if (che instanceof Map) {
					ret.add(new Choice(eMarker, null, (Map<String,Object>) che));
				} else {
					throw new ConfigurationException("Unsupported choices element: " + che, eMarker);														
				}
				++index;
			}
			return ret;
		}
		
		if (choicesConfig instanceof Map) {
			for (Entry<String, Object> che: ((Map<String,Object>) choicesConfig).entrySet()) {
				Marker eMarker = Util.getMarker((Map<String,Object>) choicesConfig, che.getKey());
				if (che.getValue() instanceof String) {
					ret.add(new Choice(eMarker, che.getKey(), (String) che.getValue()));
				} else if (che.getValue() instanceof Map) {
					ret.add(new Choice(eMarker, che.getKey(), (Map<String,Object>) che.getValue()));
				} else {
					throw new ConfigurationException("Unsupported choices element: " + che, eMarker);														
				}
			}
			return ret;
		}
		
		throw new ConfigurationException("Unsupported choices configuration: " + choicesConfig, choicesMarker);										
	}
	
	@Override
	public Marker getMarker() {
		return marker;
	}
	
	public List<Choice> getChoices() {
		return choices;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public String getLabel() {
		return label;
	}
	
	public Object getValue() {
		return value;
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

}
