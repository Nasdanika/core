package org.nasdanika.maven;

import java.io.File;
import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleDescriptor.Requires;
import java.lang.module.ModuleDescriptor.Requires.Modifier;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.requirements.ClassLoaderRequirement;
import org.nasdanika.capability.requirements.DependencyRequestRecord;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;

import reactor.core.publisher.Flux;

/**
 * Creates a classloader from resolved modules, backed by a module layer if an array of root modules is provided.
 * May return the parent classloader if there are no modules
 */
public class ClassLoaderCapabilityFactory extends ServiceCapabilityFactory<ClassLoaderRequirement, ClassLoader> {

	@Override
	public boolean isFor(Class<?> type, Object serviceRequirement) {
		return type == ClassLoader.class && serviceRequirement instanceof ClassLoaderRequirement;
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<ClassLoader>>> createService(
			Class<ClassLoader> serviceType,
			ClassLoaderRequirement serviceRequirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		if (serviceRequirement.dependencies() == null && serviceRequirement.managedDependencies() == null) {
			// Nothing to resolve, returning the parent classloader
			return wrap(serviceRequirement.parentClassLoader());			
		}
		
		DependencyRequestRecord dependencyRequest = new DependencyRequestRecord(
				serviceRequirement.dependencies(), 
				serviceRequirement.managedDependencies(), 
				serviceRequirement.remoteRepositories(), 
				serviceRequirement.localRepository());
		
		CompletionStage<Iterable<CapabilityProvider<Object>>> dependencyCS = loader.load(dependencyRequest, progressMonitor);
		CompletionStage<ClassLoader> clcs = dependencyCS.thenApply(cp -> createClassLoader(serviceRequirement, cp, progressMonitor));
		return wrapCompletionStage(clcs);
	}
	
	@SuppressWarnings("unchecked")
	protected ClassLoader createClassLoader(
			ClassLoaderRequirement requirement, 
			Iterable<CapabilityProvider<Object>> contributorProviders, 
			ProgressMonitor progressMonitor) {

		List<File> dependencies = new ArrayList<>();
		
		for (CapabilityProvider<Object> cp: contributorProviders) {
			cp.getPublisher()
				.map(e -> (Collection<File>) e)
				.flatMap(Flux::fromIterable)
				.filter(f -> !isAlreadyLoaded(f, requirement.parentClassLoader()))
				.subscribe(dependencies::add);
		}
		
		if (dependencies.isEmpty() && requirement.parentModuleLayers() == null) {
			return requirement.parentClassLoader();
		}
		
		Set<String> allModules = new HashSet<>();
		
		if (requirement.parentModuleLayers() != null) {
			for (ModuleLayer parentModuleLayer: requirement.parentModuleLayers()) {
				for (Module module: parentModuleLayer.modules()) {
					allModules.add(module.getName());
				}
			}
		}
				
		ModuleFinder moduleFinder = ModuleFinder.of(dependencies.stream().map(File::toPath).toArray(size -> new Path[size]));
		List<Path> ownModules = new ArrayList<>();
		for(ModuleReference ref: moduleFinder.findAll()) {
			String name = ref.descriptor().name();
			if (allModules.add(name)) {
				try {
					ownModules.add(new File(ref.location().get().toURL().getFile()).toPath());
				} catch (MalformedURLException e) {
					throw new NasdanikaException(e);
				}
			}
		}
		moduleFinder = ModuleFinder.of(ownModules.toArray(size -> new Path[size]));
		Collection<String> modulesToAdd = new TreeSet<>();
		Set<ModuleReference> allFoundModules = moduleFinder.findAll();
		Map<String, ModuleReference> modulePath = new TreeMap<>();
		if (requirement.modulePath() == null) {
			// Building from root modules
			for (ModuleReference moduleReference: allFoundModules) {
				ModuleDescriptor descriptor = moduleReference.descriptor();
				if (!descriptor.isAutomatic() && matchRootModule(descriptor.name(), requirement.rootModules())) {
					buildModulePath(moduleReference, moduleFinder, modulePath, modulesToAdd);
				}
			}
		} else {
			for (ModuleReference moduleReference: allFoundModules) {
				String moduleName = moduleReference.descriptor().name();
				for (String modulePathElementName: requirement.modulePath()) {
					if (moduleName.equals(modulePathElementName)) {
						modulePath.put(moduleName, moduleReference);
					}
				}
			}			
		}
		
		List<URL> classPath = new ArrayList<>();
		for (ModuleReference mr: allFoundModules) {
			if (!modulePath.containsKey(mr.descriptor().name())) {
				try {
					classPath.add(mr.location().get().toURL());
				} catch (MalformedURLException e) {
					throw new NasdanikaException(e);
				}
			}
		}
		
		ClassLoader parentClassLoader = requirement.parentClassLoader();
		if (!classPath.isEmpty()) {
			parentClassLoader = new URLClassLoader(classPath.toArray(size -> new URL[size]), parentClassLoader);
		}
		
		if (modulePath.isEmpty()) {
			return parentClassLoader;
		}
						
		Configuration config = Configuration.resolve(
				moduleFinder, 
				Stream.of(requirement.parentModuleLayers()).map(ModuleLayer::configuration).toList(), 
				ModuleFinder.of(), 
				modulePath.keySet());
		
		ModuleLayer moduleLayer;
		if (requirement.oneLayerClassLoader()) {
			moduleLayer = ModuleLayer.defineModulesWithOneLoader(
					config, 
					Stream.of(requirement.parentModuleLayers()).toList(), 
					parentClassLoader).layer();
		} else {
			moduleLayer = ModuleLayer.defineModulesWithManyLoaders(
					config, 
					Stream.of(requirement.parentModuleLayers()).toList(), 
					parentClassLoader).layer();
		}
		
		if (requirement.moduleLayerConsumer() != null) {
			requirement.moduleLayerConsumer().accept(moduleLayer);
		}
		
		moduleLayer.modules().forEach(System.out::println);
		String firstModuleName = moduleLayer.modules().iterator().next().getName();		
		return moduleLayer.findLoader(firstModuleName);
	}
	
	private boolean isAlreadyLoaded(File file, ClassLoader classLoader) {
		try {
			if (classLoader instanceof URLClassLoader) {
				URL fileURL = file.toURI().toURL();
				URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
				for (URL url: urlClassLoader.getURLs()) {
					if (fileURL.equals(url)) {
						return true;
					}
				}
			}
			if (classLoader != null) {
				ClassLoader parentClassLoader = classLoader.getParent();
				if (parentClassLoader != null && isAlreadyLoaded(file, parentClassLoader)) {
					return true;
				}
			}
			return false;
		} catch (MalformedURLException e) {
			throw new NasdanikaException(e);
		}
	}
		
	private static final String SINGLE_SUFFIX = ".*";
	private static final String DOUBLE_SUFFIX = ".**";	
		
	protected boolean matchRootModule(String moduleName, String[] rootModules) {
		if (rootModules == null) {
			return false;
		}
		
		for (String rm: rootModules) {
			if (moduleName.equals(rm)) {
				return true;
			}
			
			if (rm.endsWith(SINGLE_SUFFIX)) {
				String prefix = rm.substring(0, rm.length() - 1);
				if (moduleName.startsWith(prefix) && moduleName.indexOf('.', prefix.length()) == -1) {
					return true;
				}
			}
						
			if (rm.endsWith(DOUBLE_SUFFIX)) {
				String prefix = rm.substring(0, rm.length() - DOUBLE_SUFFIX.length());
				if (moduleName.equals(prefix) || moduleName.startsWith(prefix + ".")) {
					return true;
				}
			}
			
		}		
		
		return false;
	}
	
	/**
	 * Collects non-automatic modules and modules required by non-automatic modules. 
	 * All other modules(jars) are added to the classpath.
	 */
	private void buildModulePath(
			ModuleReference moduleReference, 
			ModuleFinder moduleFinder, 
			Map<String, ModuleReference> modulePath, 
			Collection<String> modulesToAdd) {
		
		String moduleName = moduleReference.descriptor().name();
		if (!modulePath.containsKey(moduleName)) {
			modulePath.put(moduleName, moduleReference);
			if (!moduleReference.descriptor().isAutomatic()) {
				for (Requires req: moduleReference.descriptor().requires()) {
					Set<Modifier> modifiers = req.modifiers();
					if (modifiers.contains(Requires.Modifier.MANDATED) || modifiers.contains(Requires.Modifier.STATIC)) {
						// Mandated and static are not needed to be added to the module path.
						continue; 
					}
					
					Optional<ModuleReference> mr = moduleFinder.find(req.name());
					if (mr.isPresent()) {
						buildModulePath(mr.get(), moduleFinder, modulePath, modulesToAdd);
					} else {
						modulesToAdd.add(req.name());
					}
				}
			}
		}
	}

}
