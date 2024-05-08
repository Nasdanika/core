package org.nasdanika.cli;

/**
 * This interface can be implemented by commands and mix-ins 
 * to select one command/mix-in out of several with the same name.
 */
public interface Overrider {
	
	boolean overrides(Object other);

}
