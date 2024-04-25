package org.nasdanika.launcher;

import java.io.File;
import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.lang.module.ModuleDescriptor.Requires;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Launcher {

	private static final String METHOD = "main";
	private static final String CLASS = "org.nasdanika.cli.Application";
	private static final String MODULE = "org.nasdanika.cli";
	private static final String LIB_DIR = "lib";

	public static void main(String[] args) throws Exception {
        List<ModuleLayer> parentLayers = List.of(ModuleLayer.boot());        
        File libsDir = new File(System.getProperty(Launcher.class.getName()+":lib", LIB_DIR));
        if (!libsDir.isDirectory()) {
        	System.err.println("Libs dir is not a directory: " + libsDir.getName());
        	System.exit(-1);
        }
        
        ModuleFinder finder = ModuleFinder.of(libsDir.toPath());
        Map<String, ModuleReference> allModules = new HashMap<>();
        for (ModuleReference ref: finder.findAll()) {
        	allModules.put(ref.descriptor().name(), ref);
        }                
        
        Set<URI> properModules = new HashSet<>();
        
        for (Entry<String, ModuleReference> me: new ArrayList<>(allModules.entrySet())) {
        	if (!me.getValue().descriptor().isAutomatic()) {
        		properModules.add(me.getValue().location().get());
        		for (Requires req: me.getValue().descriptor().requires()) {
        			ModuleReference reqModule = allModules.remove(req.name());
        			if (reqModule != null) {
        				properModules.add(reqModule.location().get());
        			}
        		}
        		allModules.remove(me.getKey());
        	}
        }
        
		List<Path> properPaths = new ArrayList<>();
		for (File lib : libsDir.listFiles()) {
			if (properModules.contains(lib.toURI())) {
				properPaths.add(lib.toPath());
			}
		}
		
                
        ModuleFinder properFinder = ModuleFinder.of(properPaths.toArray(new Path[properPaths.size()]));
        List<String> properRoots = properFinder.findAll()
                .stream()
                .map(m -> m.descriptor().name())
                .toList();

        Configuration appConfig = Configuration.resolve(
                properFinder,
                parentLayers.stream().map(ModuleLayer::configuration).collect(Collectors.toList()),
                ModuleFinder.of(),
                properRoots);
        
        List<URL> urls = new ArrayList<>();
        for (URI uri: allModules.values().stream().map(mr -> mr.location().get()).toList()) {
        	urls.add(uri.toURL());
        }        
		URLClassLoader classLoader = new URLClassLoader(urls.toArray(size -> new URL[size]));                
		ModuleLayer layer = ModuleLayer.defineModulesWithOneLoader(appConfig, parentLayers, classLoader).layer();
		
		String moduleName = System.getProperty(Launcher.class.getName()+":module", MODULE);
		String className = System.getProperty(Launcher.class.getName()+":class", CLASS);
		Class<?> appClass = layer.findLoader(moduleName).loadClass(className);
		String methodName = System.getProperty(Launcher.class.getName()+":method", METHOD);
		Method mainMethod = appClass.getMethod(methodName, String[].class);
		Object result = mainMethod.invoke(null, (Object) args);
		if (result instanceof Integer) {
			System.exit((Integer) result);
		}
	}

}
