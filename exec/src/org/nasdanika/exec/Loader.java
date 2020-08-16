package org.nasdanika.exec;

import java.net.URL;

import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.exec.content.FreeMarker;
import org.nasdanika.exec.content.HttpCall;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Mustache;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.exec.content.ScriptEvaluator;
import org.nasdanika.exec.content.ZipArchive;
import org.nasdanika.exec.java.Annotation;
import org.nasdanika.exec.java.CompilationUnit;
import org.nasdanika.exec.java.Constructor;
import org.nasdanika.exec.java.Field;
import org.nasdanika.exec.java.Interface;
import org.nasdanika.exec.java.Method;
import org.nasdanika.exec.java.SourceFolder;
import org.nasdanika.exec.resources.Container;
import org.nasdanika.exec.resources.File;
import org.nasdanika.exec.resources.ZipResourceCollection;

/**
 * This loader supports a pre-defined set of execution participants. 
 * @author Pavel
 *
 */
public class Loader extends ObjectLoader {
	
	private org.nasdanika.common.ObjectLoader chain;

	public Loader(ObjectLoader chain) {
		this.chain = chain;
	}
	
	public Loader() {	}	

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor) throws Exception {
		switch (type) {
		// General
		case "for-each":
			return new Iterator(loader, type, config, base, progressMonitor);
		case "configure":
			return new Configurator(loader, type, config, base, progressMonitor);
		case "map":
			return new Mapper(loader, type, config, base, progressMonitor);
		case "refernce": // Referencing another spec to load
			return new Reference(loader, type, config, base, progressMonitor);
		case "group": // Both resource and content, is it needed - iterator and map shall do?
			throw new UnsupportedOperationException();
		/*
		 * Command taking resource generators - 
		 * - pulls or clones a repo from git to a pre-defined or temporary folder, from a specific branch or creates a branch off a specific branch  
		 * - executes resource generation, pushes to the repo
		 * - if temporary folder - deletes it
		 * 
		 * Gist showing how to work with jGit - https://gist.github.com/pvlasov/48dc0178feca6e74fa1d99d489f4400c
		 */
		case "git": 
			throw new UnsupportedOperationException();					
		
		// Resources
		case "container":
			return new Container(loader, type, config, base, progressMonitor);
		case "file":
			return new File(loader, type, config, base, progressMonitor);
		case "zip-resource-collection":
			return new ZipResourceCollection(loader, type, config, base, progressMonitor);
		
		
		// Content
		case "resource":
			return new Resource(loader, type, config, base, progressMonitor);
		case "zip-archive":
			return new ZipArchive(loader, type, config, base, progressMonitor);
		case "interpolator":
			return new Interpolator(loader, type, config, base, progressMonitor);
		case "mustache":
			return new Mustache(loader, type, config, base, progressMonitor);
		case "free-marker":
			return new FreeMarker(loader, type, config, base, progressMonitor);
		case "http":
			return new HttpCall(loader, type, config, base, progressMonitor);
		case "script": // string value or bindings and source. Bindings are converted to String if streams.
			return new ScriptEvaluator(loader, type, config, base, progressMonitor);
			
		// Java
		case "source-folder": 
			return new SourceFolder(loader, type, config, base, progressMonitor);
		case "package": 
			return new org.nasdanika.exec.java.Package(loader, type, config, base, progressMonitor);
		case "compilation-unit": // Merger is external - passed by Codegen 
			return new CompilationUnit(loader, type, config, base, progressMonitor);
		case "annotation": 
			return new Annotation(loader, type, config, base, progressMonitor);
		case "class": 
			return new org.nasdanika.exec.java.Class(loader, type, config, base, progressMonitor);
		case "enum": 
			return new org.nasdanika.exec.java.Enum(loader, type, config, base, progressMonitor);
		case "interface": 
			return new Interface(loader, type, config, base, progressMonitor);
		case "method": 
			return new Method(loader, type, config, base, progressMonitor);
		case "constructor": 
			return new Constructor(loader, type, config, base, progressMonitor);
		case "field": 
			return new Field(loader, type, config, base, progressMonitor);
		
		default:
			if (chain == null) {
				throw new IllegalArgumentException("Unsupported type: " + type);
			}
			
			return chain.create(loader, type, config, base, progressMonitor);
		}
	}

}
