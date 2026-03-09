package org.nasdanika.cli;

import java.io.File;
import java.io.IOException;
import java.lang.module.ModuleReference;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import picocli.CommandLine.Option;

/**
 * Base class for launcher generators - java and jlink
 */
public abstract class AbstractModuleResolverCommand extends CommandBase {
		
	@Option(names = {"-v", "--verbose"}, description = "Output debug information")
	protected boolean verbose;	
	
	@Option(
			names = {"-p", "--path-separator"}, 
			description = {
					"Path separator, " ,					
					"defaults to the system path separator"})
	protected String pathSeparator;

	protected void buildModulePath(
			StringBuilder builder, 
			Map<String, File> moduleMap,
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
	
	
	@Option(names = {"-s", "--absolute"}, description = "Use absolute paths")
	protected boolean absolute;		
		
	@Option(names = {"-P", "--prefix"},	description = "Module path prefix")
	protected String prefix;		

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

	/**
	 * 
	 * @param ref
	 * @param moduleMap
	 * @return
	 * @throws IOException
	 */
	private String nonModulePath(Map.Entry<String,File> nonModuleEntry) throws IOException {
		String path = absolute ? nonModuleEntry.getValue().getCanonicalFile().getAbsolutePath().replace(File.separatorChar, '/') : nonModuleEntry.getKey();					
		return prefix == null ? path : prefix + path;
	}	
	
	protected void buildClasspath(
			Map<String, File> moduleMap, 
			Map<String,File> nonModuleMap,			
			Map<String, ModuleReference> repoModules,
			Map<String, ModuleReference> modulePath, 
			StringBuilder builder) throws IOException {
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

		if (!classPath.isEmpty() || !nonModuleMap.isEmpty()) {
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
			for (Entry<String, File> nonModuleEntry: nonModuleMap.entrySet()) {
				if (firstEntry) {
					builder.append(" -classpath \"");
					if (verbose) {
						System.out.println("--- Class path ---");
					}
					firstEntry = false;
				} else {
					builder.append(pathSeparator == null ? File.pathSeparatorChar : pathSeparator);
				}
				String mp = nonModulePath(nonModuleEntry);
				if (verbose) {
					System.out.println("(no module) " + mp);
				}
				builder.append(mp);
			}
			builder.append("\"");
		}
	}
		
	@Option(
			names = {"-C", "--claspath-modules"}, 
			description = "Comma-separated list of classpath modules")
	private String classPathModules;		

}
