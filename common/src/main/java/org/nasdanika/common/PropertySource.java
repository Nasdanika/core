package org.nasdanika.common;

public interface PropertySource<K,V> {

	V getProperty(K name);
	
}
