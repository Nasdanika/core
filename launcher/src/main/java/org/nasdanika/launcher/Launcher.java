package org.nasdanika.launcher;

import java.io.File;
import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor.Requires;
import java.lang.module.ModuleDescriptor.Requires.Modifier;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Launcher {

	private static final String METHOD = "main";
	private static final String CLASS = "org.nasdanika.cli.Application";
	private static final String MODULE = "org.nasdanika.cli";
	private static final String LIB_DIR = "lib";
	
	private static void collectLayerModules(ModuleReference moduleReference, Function<String, Optional<ModuleReference>> resolver, Map<String, ModuleReference> layerModules, boolean debug) {
		String moduleName = moduleReference.descriptor().name();
		if (!layerModules.containsKey(moduleName)) {
			layerModules.put(moduleName, moduleReference);
			if (!moduleReference.descriptor().isAutomatic()) {
				for (Requires req: moduleReference.descriptor().requires()) {
					Set<Modifier> modifiers = req.modifiers();
					if (modifiers.contains(Requires.Modifier.MANDATED) || modifiers.contains(Requires.Modifier.STATIC)) {
						// Mandated and static are not needed to be added to the layer
						if (debug) {
							System.out.println("[" + moduleName + "] Skipping requirement: " + req.name() + " " + modifiers);							
						}
						continue; 
					}
					
					Optional<ModuleReference> mrOpt = resolver.apply(req.name());
					if (mrOpt.isPresent()) {
						collectLayerModules(mrOpt.get(), resolver, layerModules, debug);
					} else if (debug) {
						System.out.println("[" + moduleName + "] Required module not found: " + req.name() + " " + modifiers);
					}
				}
			}
		}
	}
	
	private static boolean walk(Consumer<File> consumer, File... files) {
		if (files != null) {
			for (File file: files) {
				if (file.isDirectory()) {
					walk(consumer, file.listFiles());
				} else if (file.isFile()) {
					consumer.accept(file);
				}
			}
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
        List<ModuleLayer> parentLayers = List.of(ModuleLayer.boot());        
        File libDir = new File(System.getProperty(Launcher.class.getName()+":lib", LIB_DIR));
        if (!libDir.isDirectory()) {
        	System.err.println("Lib dir is not a directory: " + libDir.getName());
        	System.exit(-1);
        }
        
        boolean debug = "true".equals(System.getProperty(Launcher.class.getName()+":debug"));
        
        if (debug) {
        	System.out.println("--- Loading modules from " + libDir.getAbsolutePath());
        }
        List<Path> libPaths = new ArrayList<>();
        Consumer<File> fileConsumer = file -> {
        	if (file.getName().endsWith(".jar")) {
        		Path jarPath = file.toPath();
				libPaths.add(jarPath);
        		if (debug) {
        			System.out.println(jarPath);
        		}
        	}
        };
        walk(fileConsumer, libDir);
        
        ModuleFinder libFinder = ModuleFinder.of(libPaths.toArray(size -> new Path[size]));
        
        Map<String, ModuleReference> libModules = new TreeMap<>();
        for (ModuleReference ref: libFinder.findAll()) {
        	libModules.put(ref.descriptor().name(), ref);
        }
        
    	if (debug) {
    		System.out.println("--- All Modules ---");
    		for (ModuleReference ref: libModules.values()) {
	    		System.out.print(ref.descriptor().name() + " ");
	    		if (ref.descriptor().isAutomatic()) {
	    			System.out.println("*");
	    		} else {
	    			StringBuilder reqBuilder = new StringBuilder();
    				for (Requires re: ref.descriptor().requires()) {
						String name = re.name();
    					Set<Modifier> modifiers = re.modifiers();
    					if (!modifiers.isEmpty()) {
    						name +=  modifiers;
    					}
    					if (!libModules.containsKey(name)) {
    						name = "(" + name + ")";
    					}
						if (reqBuilder.isEmpty()) {
							reqBuilder.append(" <- ");
						}
						reqBuilder.append(name).append(" ");
    				}
    				System.out.println(reqBuilder);
	    		}
    		}
    	}        	       
        
    	Map<String, ModuleReference> layerModules = new TreeMap<>();
        ModuleFinder systemFinder = ModuleFinder.ofSystem();        
    	ModuleFinder allFinder = ModuleFinder.compose(libFinder, systemFinder);
        for (Entry<String, ModuleReference> me: new ArrayList<>(libModules.entrySet())) {
        	if (!me.getValue().descriptor().isAutomatic()) {
        		collectLayerModules(me.getValue(), allFinder::find, layerModules, debug);
        	}
        }
                
    	if (debug) {
    		System.out.println("--- Layer Modules ---");
    		for (ModuleReference ref: layerModules.values()) {
	    		System.out.println(ref.descriptor().name());
    		}
    	}        	       
                
		Set<Path> layerPaths = new HashSet<>();
        Consumer<File> layerPathsCollector = lib -> {
			URI libURI = lib.toURI();
			for (ModuleReference lmr: layerModules.values()) {
				URI moduleURI = lmr.location().get();
				if (moduleURI.equals(libURI)) {
					layerPaths.add(lib.toPath());
					break;
				}
			}
        };
        walk(layerPathsCollector, libDir);
		        
		if (debug) {
			System.out.println("--- Layer Paths ---");
			layerPaths.forEach(System.out::println);
		}        	       
                
        ModuleFinder layerFinder = ModuleFinder.of(layerPaths.toArray(new Path[layerPaths.size()]));
        List<String> layerRoots = layerFinder.findAll()
                .stream()
                .map(m -> m.descriptor().name())
                .toList();

        Configuration appConfig = Configuration.resolve(
                allFinder,
                parentLayers.stream().map(ModuleLayer::configuration).collect(Collectors.toList()),
                ModuleFinder.of(),
                layerRoots);
        
        List<URL> urls = new ArrayList<>();
        for (URI uri: libModules.values().stream().filter(mr -> !layerModules.containsKey(mr.descriptor().name())).map(mr -> mr.location().get()).toList()) {
        	urls.add(uri.toURL());
        }        
        
		if (debug) {
			System.out.println("--- Class loader URL's ---");
			urls.forEach(System.out::println);
		}        	               
        
		URLClassLoader classLoader = new URLClassLoader(urls.toArray(size -> new URL[size]));                
		ModuleLayer layer = ModuleLayer.defineModulesWithOneLoader(appConfig, parentLayers, classLoader).layer();
		
		String moduleName = System.getProperty(Launcher.class.getName()+":module", MODULE);
		if (debug) {
			System.out.println("Module: " + moduleName);
		}
		String className = System.getProperty(Launcher.class.getName()+":class", CLASS);
		if (debug) {
			System.out.println("Class: " + className);
		}
		Class<?> appClass = layer.findLoader(moduleName).loadClass(className);
		String methodName = System.getProperty(Launcher.class.getName()+":method", METHOD);
		if (debug) {
			System.out.println("Method: " + methodName);
		}
		Method mainMethod = appClass.getMethod(methodName, String[].class);
		Object result = mainMethod.invoke(null, (Object) args);
		if (result instanceof Integer) {
			System.exit((Integer) result);
		}
	}

}
