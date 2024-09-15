package org.nasdanika.capability.requirements;

import java.util.function.Consumer;

/**
 * Requirement for a classloader, possibly backed by a module layer
 */
public record ClassLoaderRequirement(		
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

}
