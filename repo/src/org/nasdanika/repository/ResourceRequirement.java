package org.nasdanika.repository;

/**
 * Resource requirement for a command. 
 * @author Pavel Vlasov
 *
 */
public interface ResourceRequirement {
	
	enum Mode {
		read,
		write,
		execute
	}
	
	/**
	 * Resource path. 
	 * @return
	 */
	String getResource();
	
	/**
	 * Action on the resource e.g. read or write. 
	 * @return
	 */
	Mode getMode();

}
