package org.nasdanika.capability.requirements;

import java.util.function.Consumer;

import org.nasdanika.common.Invocable;

/**
 * Requirement for an {@link Invocable} - Java class, script, diagram
 */
public record InvocableRequirement(
		/**
		 * URI of a diagram to be loaded. For diagrams fragments are URI-encoded name/value pairs like the query part to be passed to the document as properties.
		 * Fragment properties override this spec properties.
		 */
		DiagramRecord diagram,   
		
		/**
		 * Java class to create. Can reference a factory method ::&ltstatic method name&gt;
		 */
		String type,
		
		/**
		 * URI of script source relative to the URI of this spec.
		 * If script language is not specified, it is derived from extension.
		 */
		String source,
		
		/**
		 * Inline script. Ignored if source is not null. Requires scriptLanguage. 
		 */
		String script,
		
		/**
		 * Script language MIME type. e.g. application/groovy
		 */
		String scriptLanguage,
		
		/**
		 * Invocable URI's of bindings.
		 */
		String[] bind,		
		
		// Dependency loading configuration
		String[] modulePath, // Module path. If null, is derived from root modules
		String[] rootModules,
		ModuleLayer[] parentModuleLayers,
		ClassLoader parentClassLoader,
		boolean oneLayerClassLoader,
		String[] dependencies,
		String[] managedDependencies,
		RemoteRepoRecord[] remoteRepositories,
		String localRepository,
		Consumer<ModuleLayer> moduleLayerConsumer) {
	
	/**
	 * 
	 * @return {@link ClassLoaderRequirement} or null if dependencies and managed dependencies are null. 
	 */
	public ClassLoaderRequirement getClassLoaderRequirement() {
		if (dependencies == null && managedDependencies == null) {
			return null;
		}
		return new ClassLoaderRequirement(
				modulePath(), 
				rootModules(), 
				parentModuleLayers(), 
				parentClassLoader(),
				oneLayerClassLoader(), 
				dependencies(), 
				managedDependencies(),
				remoteRepositories(),
				localRepository(), 
				moduleLayerConsumer());		
	}

}
