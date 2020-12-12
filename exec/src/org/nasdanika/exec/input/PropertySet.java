package org.nasdanika.exec.input;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.Adaptable;
import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Command;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.descriptors.Descriptor;
import org.nasdanika.common.descriptors.DescriptorSet;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;

public class PropertySet implements Marked {
	
	private Marker marker;
	
	private String description;
	
	private String name;
	
	private String icon;
	
	private String label;
	
	private List<Object> properties = new ArrayList<>();

	private Object factory;

	private static final String CONDITION_KEY = "condition";
	private static final String DESCRIPTION_KEY = "description";
	private static final String ICON_KEY = "icon";
	private static final String INCLUDE_KEY = "include";
	private static final String LABEL_KEY = "label";
	private static final String NAME_KEY = "name";
	private static final String PROPERTIES_KEY = "properties";
	private static final String SERVICES_KEY = "services";
	private static final String VALIDATIONS_KEY = "validations";

	/**
	 * 
	 * @param factory Factory to use when adapting {@link DescriptorSet} returned from createDescriptor() to {@link Supplier} or {@link ConsumerFactory} by binding the context.  
	 * @param loader
	 * @param prefix
	 * @param name
	 * @param config
	 * @param base
	 * @param marker
	 * @param monitor
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public PropertySet(Object factory, ObjectLoader loader, String prefix, String name, Object config, URL base, Marker marker, ProgressMonitor monitor) throws Exception {
		this.factory = factory;
		if (config instanceof Map) {
			this.marker = marker;
			Map<String,Object> configMap = (Map<String,Object>) config;
			Util.checkUnsupportedKeys(
					configMap, 
					CONDITION_KEY,
					DESCRIPTION_KEY,
					ICON_KEY,
					INCLUDE_KEY,
					LABEL_KEY,
					name == null ? NAME_KEY : null,
					PROPERTIES_KEY,
					SERVICES_KEY,
					VALIDATIONS_KEY);
			
			description = Util.getString(configMap, DESCRIPTION_KEY, null);
			String theName = Util.getString(configMap, NAME_KEY, name);
			this.name = theName == null ? prefix : (prefix == null ? "" : prefix) + theName;
			icon = Util.getString(configMap, ICON_KEY, null);
			label = Util.getString(configMap, LABEL_KEY, null);
			
			if (configMap.containsKey(CONDITION_KEY)) {
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
								properties.add(new PropertySet(null, loader, name, pe.getKey(), propMap, base, propMarker, monitor));
							} else {
								properties.add(new Property(loader, name, pe.getKey(), propMap, base, propMarker, monitor));
							}
						} else {
							throw new ConfigurationException("Properties values shall be maps, got " + props.getClass(), propMarker);							
						}
					}
				} else if (props instanceof Collection) {
					// TODO - detect duplicate property names.
					int idx = 0;
					for (Object pe: (Collection<Object>) props) {
						Marker propMarker = Util.getMarker((Collection<Object>) props, idx++);
						if (pe instanceof Map) {
							Map<String, Object> propMap = (Map<String,Object>) pe;
							if (propMap.containsKey(PROPERTIES_KEY)) {
								properties.add(new PropertySet(null, loader, name, null, pe, base, propMarker, monitor));
							} else {
								properties.add(new Property(loader, name, null, pe, base, propMarker, monitor));
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
	
	public Object getPropertyDefaultValue(Context context, String propertyName) {
		// TODO - conditions?
		// TODO - interpolation of the default value
		for (Object p: properties) {
			if (p instanceof Property) {
				Property property = (Property) p;
				if (property.getName().equals(propertyName)) {
					return property.getDefaultValue();
				}				
			} else if (p instanceof PropertySet) {
				PropertySet propertySet = (PropertySet) p;
				String psn = propertySet.getName();
				if (Util.isBlank(psn)) {
					Object ret = propertySet.getPropertyDefaultValue(context, propertyName);
					if (ret != null) {
						return ret;
					}
				} else if (propertyName.startsWith(psn)) {
					Object ret = propertySet.getPropertyDefaultValue(context, propertyName.substring(psn.length()));
					if (ret != null) {
						return ret;
					}					
				}
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public Diagnostic diagnose(Context context) {
		BasicDiagnostic ret = new BasicDiagnostic(Status.SUCCESS, "Diagnostic of property set "+(name == null ? "" : name), this);
		// TODO - conditions
		
		for (Object p: properties) {
			if (p instanceof Property) {
				ret.add(((Property) p).diagnose(context));				
			} else if (p instanceof PropertySet) {
				ret.add(((PropertySet) p).diagnose(context));
			}
		}
		
		// TODO - validations
		
		// Testing
//		ret.add(new BasicDiagnostic(Status.WARNING, "Warning diagnostic", this));
//		ret.add(new BasicDiagnostic(Status.ERROR, "Error diagnostic", this));
		
		// TODO - services
		
		return ret;
	}	
	
	private class DescriptorSetImpl implements DescriptorSet, Adaptable {
		
		private MutableContext context;

		public DescriptorSetImpl(MutableContext context) {
			this.context = context;
		}
		
		@Override
		public boolean isEnabled() {
			// TODO evaluate conditions
			return true;
		}
		
		@Override
		public String getLabel() {
			return label;
		}
		
		@Override
		public String getIcon() {
			return icon;
		}
		
		@Override
		public String getDescription() {
			return description;
		}
		
		@Override
		public List<Descriptor> getDescriptors() {
			List<Descriptor> ret = new ArrayList<>();
			for (Object p: properties) {
				if (p instanceof Property) {
					ret.add(((Property) p).createPropertyDescriptor(context));				
				} else if (p instanceof PropertySet) {
					ret.add(((PropertySet) p).createDescriptorSet(context));
				}
			}
			
			return ret;
		}
		
		private void setSource(Diagnostic diagnostic) {
			List<Object> data = diagnostic.getData();
			data.replaceAll(e -> e == PropertySet.this ? this : e);
			diagnostic.getChildren().forEach(this::setSource);			
		}
		
		@Override
		public Diagnostic diagnose(ProgressMonitor progressMonitor) {
			Diagnostic diagnostic = PropertySet.this.diagnose(context);
			setSource(diagnostic);
			return diagnostic;
		}
		
		/**
		 * This descriptor set can be adapted to execution participants if the owning property set was created with a factory which is an instance or can be adapted to 
		 * corresponding execution participant factories.
		 */
		@SuppressWarnings("unchecked")
		@Override
		public <T> T adaptTo(Class<T> type) {
			if (factory != null) {
				if (type == Command.class) {
					CommandFactory commandFactory = Adaptable.adaptTo(factory, CommandFactory.class);
					if (commandFactory != null) {
						try {
							return (T) commandFactory.create(context);
						} catch (Exception e) {
							throw new NasdanikaException(e);
						}		
					}
				}
				
				if (type == Consumer.class) {
					ConsumerFactory<?> consumerFactory = Adaptable.adaptTo(factory, ConsumerFactory.class);
					if (consumerFactory != null) {
						try {
							return (T) consumerFactory.create(context);
						} catch (Exception e) {
							throw new NasdanikaException(e);
						}		
					}
				}
				
				if (type == Supplier.class) {
					SupplierFactory<?> supplierFactory = Adaptable.adaptTo(factory, SupplierFactory.class);
					if (supplierFactory != null) {
						try {
							return (T) supplierFactory.create(context);
						} catch (Exception e) {
							throw new NasdanikaException(e);
						}		
					}
				}
				
			}
			
			return Adaptable.super.adaptTo(type);
		}
		
	};
		
	/**
	 * Creates a {@link DescriptorSet} from this property set backed by the {@link MutableContext} passed as an argument.
	 * @param context
	 * @return
	 */
	public DescriptorSet createDescriptorSet(MutableContext context) {				
		return new DescriptorSetImpl(context); 
	}	

}
