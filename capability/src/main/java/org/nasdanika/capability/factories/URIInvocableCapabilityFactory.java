package org.nasdanika.capability.factories;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.util.Base64;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.eclipse.emf.common.util.URI;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.requirements.ClassLoaderRequirement;
import org.nasdanika.capability.requirements.InvocableRequirement;
import org.nasdanika.capability.requirements.URIInvocableRequirement;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.yaml.snakeyaml.Yaml;

/**
 * Creates {@link Invocable} from {@link URI} 
 */
public class URIInvocableCapabilityFactory extends ServiceCapabilityFactory<Object, Invocable> {
	
	private static final String METHOD_REF = "::";
	private static final String BASE_64 = ";base64";
	private static final String VALUE_MEDIA_TYPE_PREFIX = "value/"; // Value wrapped into invocable
	private static final String JAVA_MEDIA_TYPE_PREFIX = "java/"; // Construction of a given type - binding data as "data" argument 
	private static final String SPEC_MEDIA_TYPE = "application/yaml/invocable";
		
	@Override
	public boolean isFor(Class<?> type, Object serviceRequirement) {
		return type == Invocable.class && (serviceRequirement instanceof URI || serviceRequirement instanceof URIInvocableRequirement);
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<Invocable>>> createService(
			Class<Invocable> serviceType,
			Object serviceRequirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		URIInvocableRequirement requirement = serviceRequirement instanceof URIInvocableRequirement ? (URIInvocableRequirement) serviceRequirement : new URIInvocableRequirement((URI) serviceRequirement);
		
		if ("data".equals(requirement.uri().scheme())) {
			return handleDataScheme(requirement, loader, progressMonitor);
		}
		
		if (requirement.uri().lastSegment() != null) {
			String lastSegment = requirement.uri().lastSegment();
			int lastDot = lastSegment.lastIndexOf('.');
			String extension = lastSegment.substring(lastDot + 1);
			if (lastDot != -1) {
				try {
					if (extension.equalsIgnoreCase("yml") || extension.equalsIgnoreCase("yaml")) {			
						return handleYamlSpec(requirement, loader, progressMonitor);
					}
					
					if (extension.equalsIgnoreCase("json")) {			
						return handleJsonSpec(requirement, loader, progressMonitor);
					}
					
					ScriptEngineManager scriptEngineManager = new ScriptEngineManager(requirement.classLoader());
					ScriptEngine scriptEngine = scriptEngineManager.getEngineByExtension(extension);
					if (scriptEngine == null) {
						return wrapError(new IllegalArgumentException("No script engine found for extension '" + extension + "': " + requirement.uri()));
					}
				
					InputStream in = openStream(requirement);
					String script = DefaultConverter.INSTANCE.toString(in);					
					
					Invocable result = new Invocable() {
						
						@SuppressWarnings("unchecked")
						@Override
						public Object invoke(Object... args) {
							scriptEngine.put("args", args);
							try {
								return scriptEngine.eval(script);
							} catch (ScriptException e) {
								throw new NasdanikaException("Error evaluating script at '" + requirement.uri() + "': " + e, e);
							}
						}
						
					};
					
					return wrap(result.bind(loader, progressMonitor, requirement.uri().fragment()));
				} catch (IOException e) {
					return wrapError(e);
				}				
			}				
		}
		return empty();
	}
	
	protected InputStream openStream(URIInvocableRequirement requirement) throws IOException {
		URI normalized = requirement.uriHandler().normalize(requirement.uri());
		if (Util.CLASSPATH_SCHEME.equals(normalized.scheme())) {
			String resource = normalized.toString().substring(Util.CLASSPATH_URL_PREFIX.length());
			return Objects.requireNonNull(requirement.classLoader().getResourceAsStream(resource), "ClassLoader resource not found: " + resource);
		}
		return requirement.uriHandler().openStream(normalized);
	}
	
	protected CompletionStage<Iterable<CapabilityProvider<Invocable>>> handleYamlSpec(
			URIInvocableRequirement requirement,
			Loader loader,
			ProgressMonitor progressMonitor) throws IOException {
		
		Yaml yaml = new Yaml();
		Map<?,?> spec = yaml.load(openStream(requirement));
		return handleSpec(requirement, spec, loader, progressMonitor);
	}	
	
	protected CompletionStage<Iterable<CapabilityProvider<Invocable>>> handleJsonSpec(
			URIInvocableRequirement requirement,
			Loader loader,
			ProgressMonitor progressMonitor) throws JSONException, IOException {
		
		JSONObject spec = new JSONObject(new JSONTokener(openStream(requirement)));
		return handleSpec(requirement, spec.toMap(), loader, progressMonitor);
	}
	
	protected CompletionStage<Iterable<CapabilityProvider<Invocable>>> handleSpec(
			URIInvocableRequirement requirement,
			Map<?,?> spec,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		Invocable ci = Invocable.of(InvocableRequirement.class);
		InvocableRequirement invocableRequirement = ci.call(spec);		
		ClassLoaderRequirement classLoaderRequirement = invocableRequirement.getClassLoaderRequirement();
		CompletionStage<ClassLoader> classLoaderCS;		
		if (classLoaderRequirement == null) {
			classLoaderCS = CompletableFuture.completedStage(invocableRequirement.parentClassLoader());
		} else {		
			classLoaderCS = loader.loadOne(
				ServiceCapabilityFactory.createRequirement(ClassLoader.class, null, classLoaderRequirement),
				progressMonitor);
		}
		return classLoaderCS.thenApply(classLoader -> handleSpec(requirement, invocableRequirement, classLoader, loader, progressMonitor));
	}	
	
	protected Iterable<CapabilityProvider<Invocable>> handleSpec(
			URIInvocableRequirement requirement,
			InvocableRequirement spec,
			ClassLoader classLoader,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		// Diagram
		if (spec.diagram() != null) {
			throw new UnsupportedOperationException();
		}
		
		// Type
		if (spec.type() != null) {
			String typeName = spec.type();
			if (typeName.indexOf('.') == -1) {
				typeName = "java.lang." + typeName;
			}
			int methodRefIdx = typeName.indexOf(METHOD_REF);
			String methodName = null;
			if (methodRefIdx != -1) {
				methodName = typeName.substring(methodRefIdx + METHOD_REF.length());
				typeName = typeName.substring(0, methodRefIdx);
			}
			try {
				Class<?> type = getClass().getClassLoader().loadClass(typeName);
				Invocable result;
				if (methodName == null) {
					result = Invocable.of(type);
				} else {
					String theMethodName = methodName;
					Invocable[] invocables = Stream.of(type.getMethods())
						.filter(m -> Modifier.isStatic(m.getModifiers()) && theMethodName.equals(m.getName()))
						.map(m -> Invocable.of(null, m))
						.toArray(size -> new Invocable[size]);
					result = Invocable.of(invocables);	
				}
				
				result = result.bind(loader, progressMonitor);
				
				Iterator<URI> bindingIterator;
				if (spec.bind() == null) {
					bindingIterator = Collections.emptyIterator();
				} else {
					bindingIterator = Stream.of(spec.bind())
							.map(URI::createURI)
							.map(uri -> requirement.uriHandler().normalize(uri))
							.iterator();
				}
				result = bind(result, bindingIterator, spec, classLoader, loader, progressMonitor);								
				return wrap(result).toCompletableFuture().join();
			} catch (ClassNotFoundException e) {
				return wrapError(e).toCompletableFuture().join(); 
			}
						
		}
		return wrapError(new IllegalArgumentException("Not supported spec: " + spec)).toCompletableFuture().join(); 
	}

	/**
	 * Loads invocable from a URI returned by the iterator, invokes it and binds. 
	 * Then calls itself recursively until the iteration is over.  
	 * @param requirement
	 * @param classLoader
	 * @param loader
	 * @param progressMonitor
	 * @return
	 */
	protected Invocable bind(
			Invocable invocable,
			Iterator<URI> bindingIterator,
			InvocableRequirement requirement,
			ClassLoader classLoader,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		if (bindingIterator.hasNext()) {
			throw new UnsupportedOperationException();
		}
		
		return invocable;
	}	

	protected CompletionStage<Iterable<CapabilityProvider<Invocable>>> handleDataScheme(
			URIInvocableRequirement requirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		String opaquePart = requirement.uri().opaquePart();
		int commaIdx = opaquePart.indexOf(",");
		if (commaIdx == -1) {
			return empty();
		}
		String dataPart = opaquePart.substring(commaIdx + 1);
		byte[] data = Util.isBlank(dataPart) ? null : dataPart.getBytes();
		String mediaType = opaquePart.substring(0, commaIdx);
		if (mediaType.endsWith(BASE_64)) {
			mediaType = mediaType.substring(0, mediaType.length() - BASE_64.length());
			data = Base64.getDecoder().decode(data);
		}
		
		// Wrapping in invocable
		if (mediaType.startsWith(VALUE_MEDIA_TYPE_PREFIX)) {
			String typeName = mediaType.substring(VALUE_MEDIA_TYPE_PREFIX.length());
			if (typeName.indexOf('.') == -1) {
				typeName = "java.lang." + typeName;
			}
			try {
				Class<?> type = getClass().getClassLoader().loadClass(typeName);
				return wrap(Invocable.ofValue(loadValue(type, data)));
			} catch (ClassNotFoundException e) {
				return wrapError(e);
			}
		}
		
		// Itself should be invocable, support of :: for method name
		if (mediaType.startsWith(JAVA_MEDIA_TYPE_PREFIX)) {
			String typeName = mediaType.substring(JAVA_MEDIA_TYPE_PREFIX.length());
			if (typeName.indexOf('.') == -1) {
				typeName = "java.lang." + typeName;
			}
			int methodRefIdx = typeName.indexOf(METHOD_REF);
			String methodName = null;
			if (methodRefIdx != -1) {
				methodName = typeName.substring(methodRefIdx + METHOD_REF.length());
				typeName = typeName.substring(0, methodRefIdx);
			}
			try {
				Class<?> type = getClass().getClassLoader().loadClass(typeName);
				Invocable result;
				if (methodName == null) {
					result = Invocable.of(type);
				} else {
					String theMethodName = methodName;
					Invocable[] invocables = Stream.of(type.getMethods())
						.filter(m -> Modifier.isStatic(m.getModifiers()) && theMethodName.equals(m.getName()))
						.map(m -> Invocable.of(null, m))
						.toArray(size -> new Invocable[size]);
					result = Invocable.of(invocables);	
				}
				
				return wrap(result.bind(loader, progressMonitor, data));
			} catch (ClassNotFoundException e) {
				return wrapError(e);
			}
		}
		
		if (mediaType.equals(SPEC_MEDIA_TYPE)) {
			throw new UnsupportedOperationException();
		}
		
		// Scripts		
		ScriptEngineManager scriptEngineManager = new ScriptEngineManager(requirement.classLoader());
		ScriptEngine scriptEngine = scriptEngineManager.getEngineByMimeType(mediaType);
		if (scriptEngine == null) {
			return wrapError(new IllegalArgumentException("No script engine found for MIME type '" + mediaType + "': " + requirement.uri()));
		}
		
		Invocable result = new Invocable() {
			
			@SuppressWarnings("unchecked")
			@Override
			public Object invoke(Object... args) {
				scriptEngine.put("args", args);
				try {
					return scriptEngine.eval(dataPart);
				} catch (ScriptException e) {
					throw new NasdanikaException("Error evaluating script at '" + requirement.uri() + "': " + e, e);
				}
			}
			
		};
		
		return wrap(result.bind(loader, progressMonitor, (String) null)); // Binding for consistency with hierarchical URL/fragment 
	}
	
	protected Object loadValue(Class<?> type, byte[] data) {
		if (String.class == type) {
			return new String(data);
		}
		
		// TODO - numbers, date, ...
		
		throw new UnsupportedOperationException();
	}

}
