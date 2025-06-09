package org.nasdanika.capability;

/**
 * Loads configuration with a given name for a given module. 
 * If type is not null, creates an instance of the type with injected configuration. 
 * @param module Configuration module
 * @param name Configuration name. Can be null.
 * @param type Configuration type. Can be null. If null, Function<String,String> is returned as configuration.
 */
public record ConfigurationRequirement(String moduleName, String name, Class<?> type) {
		
	/**
	 * Configuration requirement for the callers module
	 * @param name
	 * @param type
	 */
	public ConfigurationRequirement(String name, Class<?> type) {
		this(Thread.currentThread().getStackTrace()[2].getModuleName(), name, type);
	}
	
	/**
	 * Untyped configuration requirement for the caller module.
	 * @param name
	 */
	public ConfigurationRequirement(String name) {
		this(Thread.currentThread().getStackTrace()[2].getModuleName(), name, null);
	}
	
	/**
	 * Default configuration requirement for the caller module.
	 * @param type
	 */
	public ConfigurationRequirement(Class<?> type) {
		this(Thread.currentThread().getStackTrace()[2].getModuleName(), null, type);
	}
	
	/**
	 * Default untyped configuration requirement for the caller module.
	 */
	public ConfigurationRequirement() {
		this(Thread.currentThread().getStackTrace()[2].getModuleName(), null, null);
	}
	
	/**
	 * Configuration requirement for the argument module
	 * @param name
	 * @param type
	 */
	public ConfigurationRequirement(Module module, String name, Class<?> type) {
		this(module.getName(), name, type);
	}
	
	/**
	 * Untyped configuration requirement for the argument module.
	 * @param name
	 */
	public ConfigurationRequirement(Module module, String name) {
		this(module.getName(), name, null);
	}
	
	/**
	 * Default configuration requirement for the argument module.
	 * @param type
	 */
	public ConfigurationRequirement(Module module, Class<?> type) {
		this(module.getName(), null, type);
	}
	
	/**
	 * Default untyped configuration requirement for the argument module.
	 */
	public ConfigurationRequirement(Module module) {
		this(module.getName(), null, null);
	}
	

}
