package org.nasdanika.exec.input;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Util;
import org.nasdanika.common.descriptors.ChoiceDescriptor;
import org.nasdanika.common.descriptors.Descriptor;
import org.nasdanika.common.descriptors.DescriptorSet;
import org.nasdanika.common.descriptors.ValueDescriptor;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;

public class Choice implements Marked {
	
	private Marker marker;
	private List<Choice> choices;
	private String description;
	private String icon;
	private String label;
	private Object value;
	private List<String> conditions = new ArrayList<>();

	/**
	 * Constructor from scalar
	 * @param marker
	 * @param value
	 */
	public Choice(Marker marker, Object value, Function<Object, Object> parser) {
		this.marker = marker;
		this.value = parser.apply(value);
		this.label = DefaultConverter.INSTANCE.convert(value, String.class);
	}

	/**
	 * Constructor from value -> label map
	 * @param marker
	 * @param value
	 */
	public Choice(Marker marker, Object value, String label, Function<Object, Object> parser) {
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
	public Choice(Marker marker, String value, Map<String,Object> config, Function<Object, Object> parser) {
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
			this.value = parser.apply(config.get(VALUE_KEY));
		} else {
			if (config.containsKey(VALUE_KEY)) {
				throw new ConfigurationException("Value key cannot be used in map choices", Util.getMarker(config, VALUE_KEY));
			}
			this.value = parser.apply(value);
		}
		
		icon = Util.getString(config, ICON_KEY, null);
		label = Util.getString(config, LABEL_KEY, DefaultConverter.INSTANCE.convert(value, String.class));
		description = Util.getString(config, DESCRIPTION_KEY, null);
				
		Util.loadMultiString(config, CONDITION_KEY, conditions::add);
		
		if (config.containsKey(CHOICES_KEY)) {
			Marker choicesMarker = Util.getMarker(config, CHOICES_KEY);
			if (value != null) {
				throw new ConfigurationException("Nested choices are not supported in choices with values", choicesMarker);				
			}
			choices = loadChoices(config.get(CHOICES_KEY), choicesMarker, parser);
		}		
		
	}
	
	@SuppressWarnings("unchecked")
	static List<Choice> loadChoices(Object choicesConfig, Marker choicesMarker, Function<Object, Object> parser) {
		List<Choice> ret = new ArrayList<>();
		if (choicesConfig instanceof Collection) {
			int index = 0;
			for (Object che: (Collection<?>) choicesConfig) {
				Marker eMarker = Util.getMarker((Collection<?>) choicesConfig, index);
				if (che instanceof Map) {
					ret.add(new Choice(eMarker, null, (Map<String,Object>) che, parser));
				} else {
					ret.add(new Choice(eMarker, (String) che, parser));
				}
				++index;
			}
			return ret;
		}
		
		if (choicesConfig instanceof Map) {
			for (Entry<String, Object> che: ((Map<String,Object>) choicesConfig).entrySet()) {
				Marker eMarker = Util.getMarker((Map<String,Object>) choicesConfig, che.getKey());
				if (che.getValue() instanceof Map) {
					ret.add(new Choice(eMarker, che.getKey(), (Map<String,Object>) che.getValue(), parser));
				} else {
					ret.add(new Choice(eMarker, che.getKey(), DefaultConverter.INSTANCE.convert(che.getValue(), String.class), parser));
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
	
	/**
	 * @param context
	 * @return Descriptor or null if not enabled. For choice with sub-choices {@link DescriptorSet} is returned, {@link ValueDescriptor} otherwise.
	 */
	public Descriptor toDescriptor(Context context) {
		if (choices == null) {
			return new ChoiceDescriptor() {

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
						return Choice.this.isEnabled(context);
					} catch (Exception e) {
						throw new NasdanikaException(e);
					}
				}

				@SuppressWarnings("unchecked")
				@Override
				public Object get() {
					Object ret = value;
					if (ret != null) {
						return ret;
					}
					
					if (ret instanceof String) {
						return context.interpolate((String) ret);
					}
					
					if (ret instanceof Map) {
						return context.interpolate((Map<String,?>) ret);
					}
					
					if (ret instanceof Collection) {
						return context.interpolate((Collection<?>) ret);
					}
					
					return ret;
				}
				
			};
		}
		
		return new DescriptorSet() {

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
					return Choice.this.isEnabled(context);
				} catch (Exception e) {
					throw new NasdanikaException(e);
				}
			}

			@Override
			public List<Descriptor> getDescriptors() {
				return choices.stream().map(c -> c.toDescriptor(context)).filter(d -> d.isEnabled()).collect(Collectors.toList());
			}
			
		};
		
	}

}
