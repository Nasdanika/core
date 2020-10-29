package org.nasdanika.exec.input;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.exec.Loader;

public class PropertySet implements Marked {
	
	private Marker marker;
	
	private String description;
	
	private String name;
	
	private String icon;
	
	private String label;
	
	private List<Object> properties = new ArrayList<>();

	private static final String CONDITIONS_KEY = "conditions";
	private static final String DESCRIPTION_KEY = "description";
	private static final String ICON_KEY = "icon";
	private static final String INCLUDE_KEY = "include";
	private static final String LABEL_KEY = "label";
	private static final String NAME_KEY = "name";
	private static final String PROPERTIES_KEY = "properties";
	private static final String SERVICES_KEY = "services";
	private static final String VALIDATIONS_KEY = "validations";

	@SuppressWarnings("unchecked")
	public PropertySet(String name, Object config, URL base, Marker marker) {
		if (config instanceof Map) {
			this.marker = marker;
			Map<String,Object> configMap = (Map<String,Object>) config;
			Loader.checkUnsupportedKeys(
					configMap, 
					CONDITIONS_KEY,
					DESCRIPTION_KEY,
					ICON_KEY,
					INCLUDE_KEY,
					LABEL_KEY,
					name == null ? NAME_KEY : null,
					PROPERTIES_KEY,
					SERVICES_KEY,
					VALIDATIONS_KEY);
			
			description = Util.getString(configMap, DESCRIPTION_KEY, null);
			this.name = Util.getString(configMap, NAME_KEY, name);
			icon = Util.getString(configMap, ICON_KEY, null);
			label = Util.getString(configMap, LABEL_KEY, null);
			
			if (configMap.containsKey(CONDITIONS_KEY)) {
				//* ``conditions`` - a single value or a list of JavaScript expressions which should all evaluate to true for this property set to be displayed, validated, and injected into the context. Script bindings:
//			    * ``context`` - ${javadoc/org.nasdanika.common.Context} constructed from the group context and user input collected so far.
//			    * This property set properties.
				throw new UnsupportedOperationException("Not yet supported");
			} 
			
			if (configMap.containsKey(INCLUDE_KEY)) {
				//* ``include`` - single value or a list of relative URL's of property set specifications to include into this specification.
				throw new UnsupportedOperationException("Not yet supported");
			} 
			
			if (configMap.containsKey(PROPERTIES_KEY)) {				
				//* ``properties`` - a list or a map.
//			    * List - shall contain ``property`` and ``property-set`` maps.
//			    * Map - mapping of a property name to a property spec. This form does not support nested property sets.
				Object props = configMap.get(PROPERTIES_KEY);
				if (props instanceof Map) {
					for (Entry<String, Object> pe: ((Map<String,Object>) props).entrySet()) {
						Marker propMarker = Util.getMarker((Map<?, ?>) props, pe.getKey());
						if (pe.getValue() instanceof Map) {
							Map<String, Object> propMap = (Map<String,Object>) pe.getValue();
							if (propMap.containsKey(PROPERTIES_KEY)) {
								properties.add(new PropertySet(pe.getKey(), propMap, base, propMarker));
							} else {
								properties.add(new Property(pe.getKey(), propMap, base, propMarker));
							}
						} else {
							throw new ConfigurationException("Properties values shall be maps, got " + props.getClass(), propMarker);							
						}
					}
				} else if (props instanceof Collection) {
					int idx = 0;
					for (Object pe: (Collection<Object>) props) {
						Marker propMarker = Util.getMarker((Collection<Object>) props, idx++);
						if (pe instanceof Map) {
							Map<String, Object> propMap = (Map<String,Object>) pe;
							if (propMap.containsKey(PROPERTIES_KEY)) {
								properties.add(new PropertySet(null, pe, base, propMarker));
							} else {
								properties.add(new Property(null, pe, base, propMarker));
							}
						} else {
							throw new ConfigurationException("Properties values shall be maps, got " + props.getClass(), propMarker);							
						}
					}										
				} else {
					throw new ConfigurationException("Properties shall be a map or a list, got " + props.getClass(), Util.getMarker(configMap,  PROPERTIES_KEY));
				}
			} 
			
			if (configMap.containsKey(SERVICES_KEY)) {
				//* ``services`` - fully qualified name(s) of services which have to be present in the context for successful group execution. String or list.
				throw new UnsupportedOperationException("Not yet supported");
			} 
			
			if (configMap.containsKey(VALIDATIONS_KEY)) {
				//* ``validations`` - a map or a list of maps containing cross-property validations. Map keys:
//			    * ``condition`` - JavaScript condition which must evaluate to true for the validation to pass. Has the same bindings as ``conditions``.
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

	public Diagnostic diagnose(Context arg) {
		return new BasicDiagnostic(Status.ERROR, "Diagnostic of "+this, this);
	}	

}
