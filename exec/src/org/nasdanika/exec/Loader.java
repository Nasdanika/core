package org.nasdanika.exec;

import java.io.InputStream;
import java.net.URL;
import java.util.Collection;

import org.nasdanika.common.Adaptable;
import org.nasdanika.common.CompoundConsumerFactory;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.resources.BinaryEntityContainer;
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
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		switch (type) {
		// General
		case "for-each":
			return new Iterator(loader, type, config, base, progressMonitor, marker);
		case "configure":
			return new Configurator(loader, type, config, base, progressMonitor, marker);
		case "map":
			return new Mapper(loader, type, config, base, progressMonitor, marker);
		case "reference": // Referencing another spec to load
			return new Reference(loader, type, config, base, progressMonitor, marker);
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
			return new Container(loader, type, config, base, progressMonitor, marker);
		case "file":
			return new File(loader, type, config, base, progressMonitor, marker);
		case "zip-resource-collection":
			return new ZipResourceCollection(loader, type, config, base, progressMonitor, marker);
		
		
		// Content
		case "resource":
			return new Resource(loader, type, config, base, progressMonitor, marker);
		case "interpolator":
			return new Interpolator(loader, type, config, base, progressMonitor, marker);
		case "mustache":
			return new Mustache(loader, type, config, base, progressMonitor, marker);
		case "free-marker":
			return new FreeMarker(loader, type, config, base, progressMonitor, marker);
		case "http":
			return new HttpCall(loader, type, config, base, progressMonitor, marker);
		case "zip-archive":
			return new ZipArchive(loader, type, config, base, progressMonitor, marker);
		case "script": // string value or bindings and source. Bindings are converted to String if streams.
			return new ScriptEvaluator(loader, type, config, base, progressMonitor, marker);
			
		// Java
		case "source-folder": 
			return new SourceFolder(loader, type, config, base, progressMonitor, marker);
		case "package": 
			return new org.nasdanika.exec.java.Package(loader, type, config, base, progressMonitor, marker);
		case "compilation-unit": // Merger is external - passed by Codegen 
			return new CompilationUnit(loader, type, config, base, progressMonitor, marker);
		case "annotation": 
			return new Annotation(loader, type, config, base, progressMonitor, marker);
		case "class": 
			return new org.nasdanika.exec.java.Class(loader, type, config, base, progressMonitor, marker);
		case "enum": 
			return new org.nasdanika.exec.java.Enum(loader, type, config, base, progressMonitor, marker);
		case "interface": 
			return new Interface(loader, type, config, base, progressMonitor, marker);
		case "method": 
			return new Method(loader, type, config, base, progressMonitor, marker);
		case "constructor": 
			return new Constructor(loader, type, config, base, progressMonitor, marker);
		case "field": 
			return new Field(loader, type, config, base, progressMonitor, marker);
		
		default:
			if (chain == null) {
				throw new IllegalArgumentException("Unsupported type: " + type);
			}
			
			return chain.create(loader, type, config, base, progressMonitor, marker);
		}
	}
	
	// --- Utility methods ---
	
	/**
	 * Wraps object into an {@link InputStream} supplier factory. Handles adapters, and collection and scalar cases.
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static SupplierFactory<InputStream> asSupplierFactory(Object obj) throws Exception {
		if (obj instanceof Collection) {
			ListCompoundSupplierFactory<InputStream> ret = new ListCompoundSupplierFactory<>("Supplier collection");
			for (Object e: (Collection<?>) obj) {
				ret.add(asSupplierFactory(e));
			}
			return ret.then(Util.JOIN_STREAMS_FACTORY);
		}
		
		if (obj instanceof SupplierFactory) {		
			return (SupplierFactory<InputStream>) obj;
		}
		
		if (obj instanceof Adaptable) {
			SupplierFactory<InputStream> adapter = ((Adaptable) obj).adaptTo(SupplierFactory.class);
			if (adapter != null) {
				return adapter;
			}
		}
		
		// Converting to string, interpolating, streaming
		SupplierFactory<String> textFactory = context -> Supplier.from(context.interpolateToString(String.valueOf(obj)), "Scalar");
		return textFactory.then(Util.TO_STREAM);
	};		
		
	/**
	 * Wraps object into an {@link BinaryEntityContainer} consumer factory. Handles adapters and collections.
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static ConsumerFactory<BinaryEntityContainer> asConsumerFactory(Object obj) throws Exception {
		if (obj instanceof Collection) {
			CompoundConsumerFactory<BinaryEntityContainer> ret = new CompoundConsumerFactory<>("Consumer collection");
			for (Object e: (Collection<?>) obj) {
				ret.add(asConsumerFactory(e));
			}
			return ret;
		}
		
		if (obj instanceof ConsumerFactory) {		
			return (ConsumerFactory<BinaryEntityContainer>) obj;
		}		
		
		if (obj instanceof Adaptable) {
			ConsumerFactory<BinaryEntityContainer> adapter = ((Adaptable) obj).adaptTo(ConsumerFactory.class);
			if (adapter != null) {
				return adapter;
			}
		}
		
		throw new IllegalArgumentException(obj.getClass() + " cannot be wrapped/adapted to a consumer factory");
	};		

}
