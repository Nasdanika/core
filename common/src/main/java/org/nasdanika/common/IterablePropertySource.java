package org.nasdanika.common;

public interface IterablePropertySource<K,V> extends PropertySource<K,V> {
	
	Iterable<K> getPropertyNames();

}
