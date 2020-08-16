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
public class Factory implements ObjectLoader.Factory {
	
	private org.nasdanika.common.ObjectLoader.Factory chain;

	public Factory(ObjectLoader.Factory chain) {
		this.chain = chain;
	}
	
	public Factory() {	}	

	@Override
	public Object create(ObjectLoader.Factory factory, String type, Object config, URL base, ProgressMonitor progressMonitor) throws Exception {
		switch (type) {
		// General
		case "for-each":
			return new Iterator(factory, type, config, base, progressMonitor);
		case "configure":
			return new Configurator(factory, type, config, base, progressMonitor);
		case "map":
			return new Mapper(factory, type, config, base, progressMonitor);
		case "refernce": // Referencing another spec to load
			return new Reference(factory, type, config, base, progressMonitor);
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
			return new Container(factory, type, config, base, progressMonitor);
		case "file":
			return new File(factory, type, config, base, progressMonitor);
		case "zip-resource-collection":
			return new ZipResourceCollection(factory, type, config, base, progressMonitor);
		
		
		// Content
		case "resource":
			return new Resource(factory, type, config, base, progressMonitor);
		case "zip-archive":
			return new ZipArchive(factory, type, config, base, progressMonitor);
		case "interpolator":
			return new Interpolator(factory, type, config, base, progressMonitor);
		case "mustache":
			return new Mustache(factory, type, config, base, progressMonitor);
		case "free-marker":
			return new FreeMarker(factory, type, config, base, progressMonitor);
		case "http":
			return new HttpCall(factory, type, config, base, progressMonitor);
		case "script": // string value or bindings and source. Bindings are converted to String if streams.
			return new ScriptEvaluator(factory, type, config, base, progressMonitor);
			
		// Java
		case "source-folder": 
			return new SourceFolder(factory, type, config, base, progressMonitor);
		case "package": 
			return new org.nasdanika.exec.java.Package(factory, type, config, base, progressMonitor);
		case "compilation-unit": // Merger is external - passed by Codegen 
			return new CompilationUnit(factory, type, config, base, progressMonitor);
		case "annotation": 
			return new Annotation(factory, type, config, base, progressMonitor);
		case "class": 
			return new org.nasdanika.exec.java.Class(factory, type, config, base, progressMonitor);
		case "enum": 
			return new org.nasdanika.exec.java.Enum(factory, type, config, base, progressMonitor);
		case "interface": 
			return new Interface(factory, type, config, base, progressMonitor);
		case "method": 
			return new Method(factory, type, config, base, progressMonitor);
		case "constructor": 
			return new Constructor(factory, type, config, base, progressMonitor);
		case "field": 
			return new Field(factory, type, config, base, progressMonitor);
		
		default:
			if (chain == null) {
				throw new IllegalArgumentException("Unsupported type: " + type);
			}
			
			return chain.create(factory, type, config, base, progressMonitor);
		}
	}

}
