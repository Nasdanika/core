package org.nasdanika.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.json.JSONArray;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

/**
 * Provider of "raw" content to build a graph element from and 
 * resolve references.
 * @param <R,V> Reference type
 */
public interface ContentProvider<R,V> extends Element {
	
	/**
	 * Matches reference ID returned by getSourceReference() or getTargetReference()
	 * @param refId
	 * @return
	 */
	default boolean matchReference(R refId) {
		return false;
	}
	
	default V getValue() {
		return null;
	}
	
	/**
	 * @return Content providers to create {@link Element} children
	 */
	@Override
	default Collection<ContentProvider<R,V>> getChildren() {
		return Collections.emptyList();
	}
	
	// --- Connection methods ---
	
	/**
	 * @return Reference to the {@link Connection} source.
	 */
	default R getSourceReference() {
		return null;
	}
	
	/**
	 * @return {@link Connection} source
	 */
	default ContentProvider<R,V> getSource() {
		return null;
	}
	
	/**
	 * @return {@link Connection} target reference
	 */
	default R getTargetReference() {
		return null;
	}
	
	/**
	 * @return {@link Connection} target
	 */
	default ContentProvider<R,V> getTarget() {
		return null;
	}
	
	// --- Node methods ---
		
	/**
	 * @return {@link Node} outgoing connections
	 */
	default Collection<ContentProvider<R,V>> getOutgoingConnections() {
		return Collections.emptyList(); 
	}

	/**
	 * @return {@link Node} incoming connections
	 */	
	default Collection<ContentProvider<R,V>> getIncomingConnections() {
		return Collections.emptyList(); 
	}
	
	/**
	 * Return true if this content provider is an inline source for an incoming connection
	 * @return
	 */
	default boolean isSource() {
		return false;
	}
	
	/**
	 * Return true if this content provider is an inline target for an outcoming connection
	 * @return
	 */
	default boolean isTarget() {
		return false;
	}
	
	// ---
	
	private static <R,V> ContentProvider<R,V> fromValue(V value) {
		if (value == null) {
			return null;
		}
		
		return new ContentProvider<R,V>() {
			
			@Override
			public V getValue() {
				return value;
			}
			
		};
	}
		
	enum Key {
		value,
		children,
		source,
		target,
		incomingConnections,
		outgoingConnections,
		refId
	}

	static ContentProvider<Object,Object> from(JSONObject jsonObject) {
		return from(jsonObject, (m,k) -> m.get(k.name()));
	}	
	
	static ContentProvider<Object,Object> from(JSONObject jsonObject, BiFunction<Map<?,?>,Key,Object> accessor) {
		return from(jsonObject.toMap());
	}
	
	static ContentProvider<Object,Object> from(JSONArray jsonArray) {
		return from(jsonArray, (m,k) -> m.get(k.name()));
	}
	
	static ContentProvider<Object,Object> from(JSONArray jsonArray, BiFunction<Map<?,?>,Key,Object> accessor) {
		return from(jsonArray.toList());
	}
	
	static ContentProvider<Object,Object> from(Iterable<?> iterable) {
		return from(iterable, (m,k) -> m.get(k.name()));
	}
		
	static ContentProvider<Object,Object> from(Iterable<?> iterable, BiFunction<Map<?,?>,Key,Object> accessor) {
		return new ContentProvider<Object, Object>() {
			
			@Override
			public Collection<ContentProvider<Object, Object>> getChildren() {
				return StreamSupport.stream(iterable.spliterator(), false)
					.map(ContentProvider::from)
					.toList();
			}
			
		};
		
	}
	
	static ContentProvider<Object,Object> from(Map<?,?> map) {
		return from(map, (m,k) -> m.get(k.name()));
	}
	
	static ContentProvider<Object,Object> from(Map<?,?> map, BiFunction<Map<?,?>,Key,Object> accessor) {
		boolean hasKeys = Stream.of(Key.values())
				.map(k -> accessor.apply(map, k))
				.filter(Objects::nonNull)
				.findAny()
				.isPresent();
		
		if (!hasKeys) {
			// Treat the map as the value
			return fromValue(map);
		}
		
		return new ContentProvider<Object, Object>() {
			
			@Override
			public boolean matchReference(Object refId) {
				if (refId == null) {
					return false;
				}
				Object thisRefId = accessor.apply(map, Key.refId);
				if (thisRefId == null) {
					return false;
				}
				return refId.equals(thisRefId);
			}
			
			@Override
			public Object getValue() {
				return accessor.apply(map, Key.value);
			}
			
			@Override
			public Collection<ContentProvider<Object,Object>> getChildren() {
				Object children = accessor.apply(map, Key.children);
				if (children == null) {
					return Collections.emptyList();
				}
				if (!(children instanceof Iterable)) {
					children = Collections.singleton(children);
				}
				return StreamSupport.stream(((Iterable<?>) children).spliterator(), false)
						.map(ContentProvider::from)
						.toList();
			}
			
			@Override
			public Object getSourceReference() {
				Object source = accessor.apply(map, Key.source);
				// Map or JSONObject are inline definitions
				if (source instanceof JSONObject || source instanceof Map) {
					return null;
				};
				return source;
			}
			
			@Override
			public ContentProvider<Object,Object> getSource() {
				Object source = accessor.apply(map, Key.source);
				// Map or JSONObject are inline definitions
				if (source instanceof JSONObject || source instanceof Map) {
					return from(source);
				};
				return null;
			}
			
			@Override
			public Object getTargetReference() {
				Object target = accessor.apply(map, Key.target);
				// Map or JSONObject are inline definitions
				if (target instanceof JSONObject || target instanceof Map) {
					return null;
				};
				return target;
			}
			
			@Override
			public ContentProvider<Object,Object> getTarget() {
				Object target = accessor.apply(map, Key.target);
				// Map or JSONObject are inline definitions
				if (target instanceof JSONObject || target instanceof Map) {
					return from(target);
				};
				return null;
			}
			
			private ContentProvider<Object,Object> createOutgoingConnectionContentProvider(Object spec) {		
				ContentProvider<Object,Object> source = this;
				
				return new ContentProviderFilter<Object,Object>(from(spec)) {
					
					@Override
					public Object getSourceReference() {
						return null;
					}
					
					@Override
					public ContentProvider<Object,Object> getSource() {
						return source;
					}
					
					@Override
					public ContentProvider<Object,Object> getTarget() {
						ContentProvider<Object, Object> targetContentProvider = super.getTarget();
						if (targetContentProvider == null) {
							return targetContentProvider;
						}
						
						return new ContentProviderFilter<Object, Object>(targetContentProvider) {
							
							@Override
							public boolean isTarget() {
								return true;
							}
							
						};						
					}
					
				};
			}
			
			@Override
			public Collection<ContentProvider<Object,Object>> getOutgoingConnections() {
				Object outgoingConnections = accessor.apply(map, Key.outgoingConnections);
				if (outgoingConnections == null) {
					return Collections.emptyList();
				}
				if (!(outgoingConnections instanceof Iterable)) {
					outgoingConnections = Collections.singleton(outgoingConnections);
				}
				return StreamSupport.stream(((Iterable<?>) outgoingConnections).spliterator(), false)
						.map(this::createOutgoingConnectionContentProvider)
						.toList();
			}
						
			private ContentProvider<Object,Object> createIncomingConnectionContentProvider(Object spec) {		
				ContentProvider<Object,Object> connectionTarget = this;
				
				return new ContentProviderFilter<Object,Object>(from(spec)) {
					
					@Override
					public Object getTargetReference() {
						return null;
					}
					
					@Override
					public ContentProvider<Object,Object> getTarget() {
						return connectionTarget;
					}
										
					@Override
					public ContentProvider<Object,Object> getSource() {
						ContentProvider<Object, Object> sourceContentProvider = super.getSource();
						if (sourceContentProvider == null) {
							return sourceContentProvider;
						}
						
						return new ContentProviderFilter<Object, Object>(sourceContentProvider) {
							
							@Override
							public boolean isSource() {
								return true;
							}
							
						};						
					}
										
				};
			}
			
			@Override
			public Collection<ContentProvider<Object,Object>> getIncomingConnections() {
				Object incomingConnections = accessor.apply(map, Key.incomingConnections);
				if (incomingConnections == null) {
					return Collections.emptyList();
				}
				if (!(incomingConnections instanceof Iterable)) {
					incomingConnections = Collections.singleton(incomingConnections);
				}
				return StreamSupport.stream(((Iterable<?>) incomingConnections).spliterator(), false)
						.map(this::createIncomingConnectionContentProvider)
						.toList();
			}
			
		};				
	}
	
	static ContentProvider<Object,Object> from(Object obj) {
		return from(obj, (m,k) -> m.get(k.name()));
	}
	
	static ContentProvider<Object,Object> from(Object obj, BiFunction<Map<?,?>,Key,Object> accessor) {
		return switch (obj) {
			case JSONObject jObj -> from(jObj, accessor);
			case JSONArray jArr -> from(jArr, accessor);
			case Map<?,?> map -> from(map, accessor);
			case Iterable<?> it -> from(it, accessor);
			default -> fromValue(obj);
		};
	}	
		
	static ContentProvider<Object,Object> fromYaml(String yamlStr) {
		return fromYaml(yamlStr, (m,k) -> m.get(k.name()));
	}
	
	static ContentProvider<Object,Object> fromYaml(String yamlStr, BiFunction<Map<?,?>,Key,Object> accessor) {
		Yaml yaml = new Yaml();
		return yaml.load(yamlStr);
	}	

}
