package org.nasdanika.cli;

import java.io.File;
import java.io.PrintStream;
import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor.Requires;
import java.lang.module.ModuleDescriptor.Requires.Modifier;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.net.URI;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
		description = "Generates Java command line from directories of modules/jars",
		name = "launcher")
public class LauncherCommand extends CommandBase {
	
	@Option(names = {"-o", "--output"}, description = "Output file")
	private File output;
	
	@Option(
			names = {"-p", "--path-separator"}, 
			description = {
					"Path separator, " ,					
					"defaults to the system path separator"})
	private String pathSeparator;
	
	@Parameters(description = { 
			"Directories to scan for modules,",
			"defaults to lib"
		})
	private File[] repositories;
	
	@Option(
			names = {"-m", "--module"}, 
			description = {
					"Application module,",
					"defaults to ${DEFAULT-VALUE}"
				}, 
			defaultValue = "org.nasdanika.cli")
	private String moduleName;
	
	@Option(
			names = {"-c", "--class"}, 
			description = {
					"Application class,",
					"defaults to ${DEFAULT-VALUE}"
				}, 
			defaultValue = "org.nasdanika.cli.Application")
	private String className;
	
	@Option(
			names = {"-a", "--args"}, 
			description = {
					"Arguments,",
					"defaults to ${DEFAULT-VALUE}"
				}, 
			defaultValue = "%*")
	private String args;	
	
	@Option(names = {"-v", "--verbose"}, description = "Output debug invformation")
	private boolean verbose;
	
	@Option(
			names = {"-j", "--java"}, 
			description = {
					"Java command,",
					"defaults to ${DEFAULT-VALUE}"
				}, 
			defaultValue = "java")
	private String javaCommand;
		
	@Override
	public Integer call() throws Exception {
		if (output == null) {
			System.out.println(generateLauncherCommand());
		} else {
			try (PrintStream out = new PrintStream(output)) {
				out.print(generateLauncherCommand());
			}
		}
		return 0;
	}
	
	public static void walk(String path, BiConsumer<File,String> listener, File... files) {
		if (files != null) {
			for (File file: files) {
				String filePath = path == null ? file.getName() : path + "/" + file.getName();
				if (file.isDirectory()) {
					walk(filePath, listener, file.listFiles());
				} else if (file.isFile() && listener != null) {
					listener.accept(file, filePath);
				}
			}
		}
	}
	
	/**
	 * Collects non-automatic modules and modules required by non-automatic modules. All other modules(jars) are added to the classpath. 
	 */
	private void buildModulePath(ModuleReference moduleReference, Function<String, ModuleReference> resolver, Map<String, ModuleReference> modulePath) {
		String moduleName = moduleReference.descriptor().name();
		if (!modulePath.containsKey(moduleName)) {
			modulePath.put(moduleName, moduleReference);
			if (!moduleReference.descriptor().isAutomatic()) {
				for (Requires req: moduleReference.descriptor().requires()) {
					Set<Modifier> modifiers = req.modifiers();
					if (modifiers.contains(Requires.Modifier.MANDATED) || modifiers.contains(Requires.Modifier.STATIC)) {
						// Mandated and static are not needed to be added to the module path.
						if (verbose) {
							System.out.println("[" + moduleName + "] Skipping requirement: " + req.name() + " " + modifiers);							
						}
						continue; 
					}
					
					ModuleReference mr = resolver.apply(req.name());
					if (mr != null) {
						buildModulePath(mr, resolver, modulePath);
					} else if (verbose) {
						System.out.println("[" + moduleName + "] Required module not found: " + req.name() + " " + modifiers);
					}
				}
			}
		}
	}
	
	private String generateLauncherCommand() {
		if (repositories == null || repositories.length == 0) {
			repositories = new File[] { new File("lib") };
		}
		StringBuilder builder = new StringBuilder(javaCommand).append(" ");
		
		Map<String,File> moduleMap = new LinkedHashMap<>();
		
		if (verbose) {
			System.out.println("--- Jar files ---");
		}
		
	    BiConsumer<File,String> repoListener = (file,path) -> {
	    	if (file.getName().endsWith(".jar")) {
	    		moduleMap.put(path, file);
	    		if (verbose) {
	    			System.out.println(path);
	    		}
	    	}
	    };
	    walk(null, repoListener, repositories);
	    
	    // Adding non-automatic and required modules to the module path and other dependencies to the classpath
	    ModuleFinder repoFinder = ModuleFinder.of(moduleMap.values().stream().map(File::toPath).toArray(size -> new Path[size]));

		Map<String, ModuleReference> repoModules = new TreeMap<>();
		for (ModuleReference ref : repoFinder.findAll()) {
			repoModules.putIfAbsent(ref.descriptor().name(), ref);
		}

		if (verbose) {
			System.out.println("--- Modules ---");
			for (ModuleReference ref : repoModules.values()) {
				System.out.print(ref.descriptor().name() + " ");
				if (ref.descriptor().isAutomatic()) {
					System.out.println("*");
				} else {
					System.out.println();
					for (Requires re : ref.descriptor().requires()) {
						String name = re.name();
						Set<Modifier> modifiers = re.modifiers();
						if (!modifiers.isEmpty()) {
							name += modifiers;
						}
						if (!repoModules.containsKey(re.name())) {
							name = "(" + name + ")";
						}
						System.out.println("\t" + name);
					}
				}
			}
		}	   
		
		Map<String, ModuleReference> modulePath = new TreeMap<>();
		for (Entry<String, ModuleReference> me : repoModules.entrySet()) {
			if (!me.getValue().descriptor().isAutomatic()) {
				buildModulePath(me.getValue(), repoModules::get, modulePath);
			}
		}

		if (!modulePath.isEmpty()) {
			boolean firstModuleEntry = true;
			for (ModuleReference e: modulePath.values()) {
				if (firstModuleEntry) {
					builder.append(" -p \"");
					if (verbose) {
						System.out.println("--- Module path ---");
					}
					firstModuleEntry = false;
				} else {
					builder.append(pathSeparator == null ? File.pathSeparatorChar : pathSeparator);
				}
				String mp = modulePath(e, moduleMap);
				if (verbose) {
					System.out.println(e.descriptor().name() + " " + mp);
				}
				builder.append(mp);
			}
			builder.append("\"");
		}
		
		List<ModuleReference> classPath = repoModules.values().stream().filter(k -> !modulePath.containsKey(k.descriptor().name())).toList();
		if (!classPath.isEmpty()) {
			boolean firstEntry = true;
			for (ModuleReference e: classPath) {
				if (firstEntry) {
					builder.append(" -classpath \"");
					if (verbose) {
						System.out.println("--- Class path ---");
					}
					firstEntry = false;
				} else {
					builder.append(pathSeparator == null ? File.pathSeparatorChar : pathSeparator);
				}
				String mp = modulePath(e, moduleMap);
				if (verbose) {
					System.out.println(e.descriptor().name() + " " + mp);
				}
				builder.append(mp);
			}
			builder.append("\"");
		}
		
		builder
			.append(" -m ")
			.append(moduleName)
			.append("/")
			.append(className)
			.append(" ")
			.append(args);
		
		return builder.toString();
	}
	
	/**
	 * Finds path by matching module reference location to file URI.
	 * @param ref
	 * @param moduleMap
	 * @return
	 */
	private static String modulePath(ModuleReference ref, Map<String,File> moduleMap) {
		Optional<URI> lopt = ref.location();
		if (lopt.isPresent()) {
			URI location = lopt.get();
			for (Entry<String, File> mme: moduleMap.entrySet()) {
				if (location.equals(mme.getValue().toURI())) {
					return mme.getKey();
				}
			}
		}		
		return null;
	}

	public static void main(String[] args) throws Exception {
//        
//        Map<String, ModuleReference> libModules = new TreeMap<>();
//        for (ModuleReference ref: libFinder.findAll()) {
//        	libModules.put(ref.descriptor().name(), ref);
//        }
//        
//    	if (debug) {
//    		System.out.println("--- Library Modules ---");
//    		for (ModuleReference ref: libModules.values()) {
//	    		System.out.print(ref.descriptor().name() + " ");
//	    		if (ref.descriptor().isAutomatic()) {
//	    			System.out.println("*");
//	    		} else {
//	    			StringBuilder reqBuilder = new StringBuilder();
//    				for (Requires re: ref.descriptor().requires()) {
//						String name = re.name();
//    					Set<Modifier> modifiers = re.modifiers();
//    					if (!modifiers.isEmpty()) {
//    						name +=  modifiers;
//    					}
//    					if (!libModules.containsKey(name)) {
//    						name = "(" + name + ")";
//    					}
//						if (reqBuilder.isEmpty()) {
//							reqBuilder.append(" <- ");
//						}
//						reqBuilder.append(name).append(" ");
//    				}
//    				System.out.println(reqBuilder);
//	    		}
//    		}
//    	}        	       
//        
//                
//		Set<Path> layerPaths = new HashSet<>();
//        Consumer<File> layerPathsCollector = lib -> {
//			URI libURI = lib.toURI();
//			for (ModuleReference lmr: layerModules.values()) {
//				URI moduleURI = lmr.location().get();
//				if (moduleURI.equals(libURI)) {
//					layerPaths.add(lib.toPath());
//					break;
//				}
//			}
//        };
//        walk(layerPathsCollector, libDir);
//		        
//		if (debug) {
//			System.out.println("--- Layer Paths ---");
//			layerPaths.forEach(System.out::println);
//		}        	       
//                
//        ModuleFinder layerFinder = ModuleFinder.of(layerPaths.toArray(new Path[layerPaths.size()]));
//        List<String> layerRoots = layerFinder.findAll()
//                .stream()
//                .map(m -> m.descriptor().name())
//                .toList();
//
//        Configuration appConfig = Configuration.resolve(
//                allFinder,
//                parentLayers.stream().map(ModuleLayer::configuration).collect(Collectors.toList()),
//                ModuleFinder.of(),
//                layerRoots);
//        
//        List<URL> urls = new ArrayList<>();
//        for (URI uri: libModules.values().stream().filter(mr -> !layerModules.containsKey(mr.descriptor().name())).map(mr -> mr.location().get()).toList()) {
//        	urls.add(uri.toURL());
//        }        
//        
//		if (debug) {
//			System.out.println("--- Class loader URL's ---");
//			urls.forEach(System.out::println);
//		}
        
        		
//		String moduleName = System.getProperty(Launcher.class.getName()+":module", MODULE);
//		if (debug) {
//			System.out.println("Module: " + moduleName);
//		}
//		
//        Configuration parentConfig = ModuleLayer.boot().configuration();
//        Configuration appConfig = parentConfig.resolveAndBind(
//        		ModuleFinder.compose(libFinder, ModuleFinder.ofSystem()), 
//        		ModuleFinder.of(), 
//        		Set.of(moduleName));
//               
//		ClassLoader classLoader = ClassLoader.getSystemClassLoader(); //new URLClassLoader(urls.toArray(size -> new URL[size]));                
//		ModuleLayer layer = ModuleLayer.defineModulesWithOneLoader(appConfig, parentLayers, classLoader).layer();		
//		
//		String className = System.getProperty(Launcher.class.getName()+":class", CLASS);
//		if (debug) {
//			System.out.println("Class: " + className);
//		}
//		Class<?> appClass = layer.findLoader(moduleName).loadClass(className);
//		String methodName = System.getProperty(Launcher.class.getName()+":method", METHOD);
//		if (debug) {
//			System.out.println("Method: " + methodName);
//		}
//		Method mainMethod = appClass.getMethod(methodName, String[].class);
//		Object result = mainMethod.invoke(null, (Object) args);
//		if (result instanceof Integer) {
//			System.exit((Integer) result);
//		}
	}
	

}
