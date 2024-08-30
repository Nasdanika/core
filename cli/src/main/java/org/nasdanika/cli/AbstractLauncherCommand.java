package org.nasdanika.cli;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.module.ModuleDescriptor.Requires;
import java.lang.module.ModuleDescriptor.Requires.Modifier;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Stream;

import org.nasdanika.common.Util;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

/**
 * Base class for launcher generators, override and implement getModuleName() and getClassName()
 */
public abstract class AbstractLauncherCommand extends CommandBase {
	
	private static final String SINGLE_SUFFIX = ".*";
	private static final String DOUBLE_SUFFIX = ".**";

	@Option(names = {"-o", "--output"}, description = "Output file")
	private String output;
	
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
	private String[] repositories;
	
	protected abstract String getModuleName();

	@Option(names = {"-b", "--base"}, description = "Base repositories directory")
	private File base;
	
	@Option(names = {"-P", "--prefix"},	description = "Module path prefix")
	private String prefix;	

	protected abstract String getClassName();
	
	@Option(
			names = {"-a", "--args"}, 
			description = {
					"Arguments,",
					"defaults to ${DEFAULT-VALUE}"
				}, 
			defaultValue = "%*")
	private String args;	
	
	@Option(names = {"-v", "--verbose"}, description = "Output debug information")
	private boolean verbose;
	
	@Option(names = {"-s", "--absolute"}, description = "Use absolute paths")
	private boolean absolute;
	
	@Option(names = {"-t", "--options"}, description = "Output only options")
	private boolean options;
		
	@Option(
			names = {"-f", "--options-file"}, 
			description = "File to output options to")
	private String optionsFile;
		
	@Option(
			names = {"-r", "--root-modules"}, 
			description = {
					"Comma-separated list of root modules",
					"Supports .* and .** patterns"					
			})
	private String rootModules;	
	
	@Option(
			names = {"-M", "--modules"}, 
			description = "Modules to add to the module path"
			)
	private File modulesFile;	
	
	@Option(
			names = {"-C", "--claspath-modules"}, 
			description = "Comma-separated list of classpath modules")
	private String classPathModules;		
	
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
		    if (base == null) {
		    	base = new File(".");
		    }
			
			try (Writer out = new FileWriter(new File(base, output))) {
				out.write(generateLauncherCommand());
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
	 * Collects non-automatic modules and modules required by non-automatic modules. 
	 * All other modules(jars) are added to the classpath.
	 */
	private void buildModulePath(
			ModuleReference moduleReference, 
			Function<String, ModuleReference> resolver, 
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
						if (verbose) {
							System.out.println("[" + moduleName + "] Skipping requirement: " + req.name() + " " + modifiers);							
						}
						continue; 
					}
					
					ModuleReference mr = resolver.apply(req.name());
					if (mr != null) {
						buildModulePath(mr, resolver, modulePath, modulesToAdd);
					} else {
						modulesToAdd.add(req.name());
						if (verbose) {
							System.out.println("[" + moduleName + "] Required module not found: " + req.name() + " " + modifiers);
						}
					}
				}
			}
		}
	}
	
	private String generateLauncherCommand() throws IOException {
		if (repositories == null || repositories.length == 0) {
			repositories = new String[] { "lib" };
		}
		
		if (options) {
			return generateOptions();
		}
		
		StringBuilder builder = new StringBuilder();
		
		builder.append(javaCommand).append(" ");
		
		String optionsStr = generateOptions();
		if (Util.isBlank(optionsFile)) {
			builder.append(optionsStr);			
		} else {
			File oFile = new File(base, optionsFile).getCanonicalFile();
			try (Writer out = new FileWriter(oFile)) {
				out.write(optionsStr);
			}
			builder.append("@");
			if (absolute) {
				builder.append(oFile.getAbsolutePath());
			} else if (Util.isBlank(prefix)) {
				builder.append(oFile.getName());				
			} else {
				builder.append(prefix).append(oFile.getName());
			}
		}
		
		builder
			.append(" ")
			.append(args);
		
		return builder.toString();
	}
		
	private String generateOptions() throws IOException {
		if (repositories == null || repositories.length == 0) {
			repositories = new String[] { "lib" };
		}
		StringBuilder builder = new StringBuilder();
		
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
	    
	    if (base == null) {
	    	base = new File(".");
	    }
	    
	    walk(
	    	null, 
	    	repoListener, 
	    	Stream.of(repositories).map(r -> new File(base, r)).toArray(size -> new File[size]));
	    
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
		
		if (modulesFile == null) {
			// Inferring module place - module path, add module, or classpath
			Collection<String> modulesToAdd = new TreeSet<>();
			Map<String, ModuleReference> modulePath = new TreeMap<>();
			for (Entry<String, ModuleReference> me : repoModules.entrySet()) {
				if (!me.getValue().descriptor().isAutomatic() && matchRootModule(me.getKey())) {
					buildModulePath(me.getValue(), repoModules::get, modulePath, modulesToAdd);
				}
			}
	
			buildModulePath(builder, moduleMap, modulePath);
			buildAddModules(modulesToAdd, builder);			
			buildClasspath(moduleMap, repoModules, modulePath, builder);
		} else {
			// Layer modules are known, adding them either to module path or add module. 
			// The rest is added to the classpath
			Collection<String> modules = Files.readAllLines(modulesFile.toPath());
			Map<String, ModuleReference> modulePath = new TreeMap<>();
			for (Entry<String, ModuleReference> me : repoModules.entrySet()) {
				if (modules.contains(me.getKey())) {
					modulePath.put(me.getKey(), me.getValue());
					modules.remove(me.getKey());
				}
			}
	
			buildModulePath(builder, moduleMap, modulePath);
			Iterator<String> mit = modules.iterator();
			while (mit.hasNext()) {
				String mName = mit.next();
				if (mName.startsWith("java.")) {
					mit.remove();
				}
			}
			buildAddModules(modules, builder);			
			buildClasspath(moduleMap, repoModules, modulePath, builder);			
		}
		
		builder
			.append(" -m ")
			.append(getModuleName())
			.append("/")
			.append(getClassName());
		
		return builder.toString();
	}

	protected void buildClasspath(Map<String, File> moduleMap, Map<String, ModuleReference> repoModules,
			Map<String, ModuleReference> modulePath, StringBuilder builder) throws IOException {
		// Modules which did not make it to the module path are in the classpath		
		List<ModuleReference> classPath = new ArrayList<>(); 		
		repoModules.values().stream().filter(k -> !modulePath.containsKey(k.descriptor().name())).forEach(classPath::add);
		if (classPathModules != null) {
			String[] cpma = classPathModules.split(",");
			for (String cpm: cpma) {
				ModuleReference mr = repoModules.get(cpm);
				if (mr != null && !classPath.contains(mr)) {
					classPath.add(mr);
				}
			}
		}		

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
	}

	protected void buildAddModules(Collection<String> modulesToAdd, StringBuilder builder) {
		if (!modulesToAdd.isEmpty()) {
			boolean firstModuleEntry = true;
			for (String m: modulesToAdd) {
				if (firstModuleEntry) {
					builder.append(" --add-modules ");
					if (verbose) {
						System.out.println("--- Modules to add ---");
					}
					firstModuleEntry = false;
				} else {
					builder.append(",");
				}
				if (verbose) {
					System.out.println(m);
				}
				builder.append(m);
			}
		}
	}

	protected void buildModulePath(StringBuilder builder, Map<String, File> moduleMap,
			Map<String, ModuleReference> modulePath) throws IOException {
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
	}
		
	protected boolean matchRootModule(String moduleName) {
		if (rootModules == null) {
			return true;
		}
		
		String[] rma = rootModules.split(",");
		for (String rm: rma) {
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
	 * Finds path by matching module reference location to file URI.
	 * @param ref
	 * @param moduleMap
	 * @return
	 * @throws IOException 
	 */
	private String modulePath(ModuleReference ref, Map<String,File> moduleMap) throws IOException {
		Optional<URI> lopt = ref.location();
		if (lopt.isPresent()) {
			URI location = lopt.get();
			for (Entry<String, File> mme: moduleMap.entrySet()) {
				if (location.equals(mme.getValue().toURI())) {
					String path = absolute ? mme.getValue().getCanonicalFile().getAbsolutePath().replace(File.separatorChar, '/') : mme.getKey();					
					return prefix == null ? path : prefix + path;
				}
			}
		}		
		return null;
	}	

}
