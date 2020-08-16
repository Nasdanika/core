package org.nasdanika.common.persistence;

/**
 * Position in a text file - yaml, json, ...
 * @author Pavel Vlasov
 *
 */
public interface Marker {
	
	int getLine();
	
	int getColumn();
	
	String getLocation();

}
