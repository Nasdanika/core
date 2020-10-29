package org.nasdanika.exec.input;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.exec.Loader;

public class Property implements Marked {
		
	private Marker marker;
		
	private String description;
	
	private String name;
	
	private String icon;
	
	private String label;
	
	private List<Object> properties = new ArrayList<>();

	private boolean editable;

	private Object defaultValue;

	private String type;

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
	
	public Property(String name, Object config, URL base, Marker marker) {
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
			this.name = Util.getString(configMap, NAME_KEY, name);
			icon = Util.getString(configMap, ICON_KEY, null);
			label = Util.getString(configMap, LABEL_KEY, null);
			editable = !Boolean.FALSE.equals(configMap.get(EDITABLE_KEY));
			type = Util.getString(configMap, TYPE_KEY, String.class.getName());
			defaultValue = configMap.get(DEFAULT_VALUE_KEY);
	
			if (configMap.containsKey(ARITY_KEY)) {
//				* ``arity`` - Specifies exact number of property values or a range with a minimum an maximum values. Defaults to ``0..1`` which means an optional property. Examples:
//			    * ``1`` - one property value - the same as a required single-value property.
//			    * ``0..*`` - any number of values.
//			    * ``2..*`` - at least two values.
//			    * ``3..5`` - between three and five values.
				throw new UnsupportedOperationException("Not yet supported");
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
//				* ``control`` - an optional hint to the UI generator specifying which UI control to use to show and collect property value. Control may also evaluate to the UI-specific control generator, e.g. by using a context property. Supported string values:
//			    * ``date`` - date control.
//			    * ``time`` - time control.
//			    * ``drop-down`` - for fixed arity properties with choices.
//			    * ``checkbox`` - for boolean properties and for multi-value properties with choices.
//			    * ``file`` - file attachment.
//			    * ``number`` - for numeric properties.
//			    * ``password`` - single-line masked text.
//			    * ``radio`` - for fixed arity properties with choices.
//			    * ``text`` - for fixed arity properties without choices. Optionally can specify number of lines after a dash. E.g. ``text-5``.
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

}
