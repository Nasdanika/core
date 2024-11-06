package org.nasdanika.capability.factories;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.eclipse.emf.common.util.URI;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.requirements.ClassLoaderRequirement;
import org.nasdanika.capability.requirements.DiagramRequirement;
import org.nasdanika.capability.requirements.InvocableRequirement;
import org.nasdanika.capability.requirements.ScriptRecord;
import org.nasdanika.capability.requirements.URIInvocableRequirement;
import org.nasdanika.common.Context;
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
	
	private static final String JSON_EXTENSION = "json";
	private static final String YAML_EXTENSION = "yaml";
	private static final String YML_EXTENSION = "yml";
	private static final String METHOD_REF = "::";
	private static final String BASE_64 = ";base64";
	private static final String VALUE_MEDIA_TYPE_PREFIX = "value/"; // Value wrapped into invocable
	private static final String SPEL_MEDIA_TYPE_PREFIX = "spel/"; // Spring Expression
	private static final String JAVA_MEDIA_TYPE_PREFIX = "java/"; // Construction of a given type - binding data as "data" argument 
	private static final String YAML_SPEC_MEDIA_TYPE = "application/yaml/invocable";
	private static final String JSON_SPEC_MEDIA_TYPE = "application/json/invocable";
		
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
					if (extension.equalsIgnoreCase(YML_EXTENSION) || extension.equalsIgnoreCase(YAML_EXTENSION)) {			
						return handleYamlSpec(requirement, loader, progressMonitor);
					}
					
					if (extension.equalsIgnoreCase(JSON_EXTENSION)) {			
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

		// Interpolation
		Context systemPropertiesContext = Context.wrap(System.getProperties()::get);
		Context envContext = Context.wrap(System.getenv()::get);
		Context combinedContext = systemPropertiesContext.mount(envContext, "env.");
		
		Map<Object,Object> fullSpec = new HashMap<>(combinedContext.interpolate(spec));
		fullSpec.put("parentClassLoader", requirement.classLoader());
		fullSpec.put("parentModuleLayers", requirement.parentModuleLayers());
		Invocable ci = Invocable.of(InvocableRequirement.class);
		InvocableRequirement invocableRequirement = ci.call(fullSpec);	
		ClassLoaderRequirement classLoaderRequirement = invocableRequirement.getClassLoaderRequirement();
		CompletionStage<ClassLoader> classLoaderCS;		
		if (classLoaderRequirement == null) {
			classLoaderCS = CompletableFuture.completedStage(invocableRequirement.parentClassLoader());
		} else {		
			classLoaderCS = loader.loadOne(
				ServiceCapabilityFactory.createRequirement(ClassLoader.class, null, classLoaderRequirement),
				progressMonitor);
		}
								
		// Diagram
		if (invocableRequirement.diagram() != null) {
			if (invocableRequirement.type() != null) {
				return wrapError(new IllegalArgumentException("Diagram and type are mutually exclusive"));
			}
			if (invocableRequirement.bind() != null) {
				return wrapError(new IllegalArgumentException("Diagram and bind are mutually exclusive"));
			}
			if (invocableRequirement.script() != null) {
				return wrapError(new IllegalArgumentException("Diagram and script are mutually exclusive"));
			}
			
			CompletionStage<DiagramRequirement> diagramRequirementCS = classLoaderCS.thenApply(classLoader -> createDiagramRequirement(requirement, invocableRequirement, classLoader, loader, progressMonitor));
			CompletionStage<Object> diagramCS = diagramRequirementCS.thenCompose(diagramRequirement -> loader.loadOne(diagramRequirement, progressMonitor));
			return wrapCompletionStage(diagramCS.thenApply(Invocable::ofValue));
		}
		
		// Type
		if (invocableRequirement.type() != null) {
			if (invocableRequirement.script() != null) {
				return wrapError(new IllegalArgumentException("Type and script are mutually exclusive"));
			}
			if (invocableRequirement.bind() == null) {
				return classLoaderCS.thenCompose(classLoader -> handleType(
						requirement,
						invocableRequirement,
						classLoader,
						Collections.emptyList(),
						loader, 
						progressMonitor));
			}
			
			CompletionStage<List<Invocable>> bindingsCS = CompletableFuture.completedStage(new ArrayList<>());
			for (String binding: invocableRequirement.bind()) {
				URI bindingURI = requirement.uriHandler().normalize(URI.createURI(binding));
				CompletionStage<Object> bindingRequirementCS = classLoaderCS.thenApply(classLoader -> {
					URIInvocableRequirement req = new URIInvocableRequirement(bindingURI, requirement.uriHandler(), classLoader, null);						
					return ServiceCapabilityFactory.createRequirement(Invocable.class, null, req);
				});
				CompletionStage<Invocable> bindingCS = bindingRequirementCS.thenCompose(bindingRequirement -> {
					return loader.loadOne(bindingRequirement, progressMonitor);	
				});
				bindingsCS = bindingsCS.thenCombine(bindingCS, (bindings, value) -> {
					bindings.add(value);
					return bindings;
				});
			}
			
			record ClassLoaderAndBindingsListRecord(ClassLoader classLoader, List<Invocable> bindings) {};
			CompletionStage<ClassLoaderAndBindingsListRecord> classLoaderAndBindingsListCS = classLoaderCS.thenCombine(bindingsCS, ClassLoaderAndBindingsListRecord::new);
			return classLoaderAndBindingsListCS.thenCompose(classLoaderAndBindingsMap -> handleType(
						requirement,
						invocableRequirement,
						classLoaderAndBindingsMap.classLoader(),
						classLoaderAndBindingsMap.bindings(),
						loader, 
						progressMonitor));			
		}
		
		// Script
		if (invocableRequirement.script() != null) {
			if (invocableRequirement.bind() != null) {
				return wrapError(new IllegalArgumentException("Script and bind are mutually exclusive"));
			}
			if (invocableRequirement.script().bindings() == null) {
				return classLoaderCS.thenCompose(classLoader -> handleScript(
						requirement,
						invocableRequirement,
						classLoader,
						Collections.emptyMap(),
						loader, 
						progressMonitor));
			}
			
			CompletionStage<Map<String, Invocable>> bindingsCS = CompletableFuture.completedStage(new HashMap<>());
			for (Entry<String, String> be: invocableRequirement.script().bindings().entrySet()) {
				URI bindingURI = requirement.uriHandler().normalize(URI.createURI(be.getValue()));
				CompletionStage<Object> bindingRequirementCS = classLoaderCS.thenApply(classLoader -> {
					URIInvocableRequirement req = new URIInvocableRequirement(bindingURI, requirement.uriHandler(), classLoader, null);						
					return ServiceCapabilityFactory.createRequirement(Invocable.class, null, req);
				});
				CompletionStage<Invocable> bindingCS = bindingRequirementCS.thenCompose(bindingRequirement -> {
					return loader.loadOne(bindingRequirement, progressMonitor);	
				});
				bindingsCS = bindingsCS.thenCombine(bindingCS, (bindings, value) -> {
					bindings.put(be.getKey(), value);
					return bindings;
				});
			}

			record ClassLoaderAndBindingsMapRecord(ClassLoader classLoader, Map<String,Invocable> bindings) {};
			CompletionStage<ClassLoaderAndBindingsMapRecord> classLoaderAndBindingsMapCS = classLoaderCS.thenCombine(bindingsCS, ClassLoaderAndBindingsMapRecord::new);
			return classLoaderAndBindingsMapCS.thenCompose(classLoaderAndBindingsMap -> handleScript(
						requirement,
						invocableRequirement,
						classLoaderAndBindingsMap.classLoader(),
						classLoaderAndBindingsMap.bindings(),
						loader, 
						progressMonitor));			
		} 
		
		return wrapError(new IllegalArgumentException("Not supported spec: " + invocableRequirement));
	}	
	
	protected String getProperty(Map<?,?> properties, String name) {
		if (properties == null) {
			return null;
		}
		if (properties.containsKey(name)) {
			Object value = properties.get(name);
			return value == null ? null : value.toString();
		}
		int dotIdx = name.indexOf('.');
		if (dotIdx != -1) {
			Object group = properties.get(name.substring(0, dotIdx));
			if (group instanceof Map) {
				return getProperty((Map<?,?>) group, name.substring(dotIdx + 1));
			}
			if (group instanceof Iterable) {
				Map<Object,Object> indexMap = new HashMap<>();
				((Iterable<?>) group).forEach(e -> indexMap.put(String.valueOf(indexMap.size()), e));
				return getProperty(indexMap, name.substring(dotIdx + 1));
			}
		}
		return null;
	}
	
	private static String decode(final String str) {
		return Util.isBlank(str) ? str : URLDecoder.decode(str, StandardCharsets.UTF_8);
	}	
	
	protected DiagramRequirement createDiagramRequirement(
			URIInvocableRequirement requirement, 
			InvocableRequirement spec, 
			ClassLoader classLoader,
			Loader loader,
			ProgressMonitor progressMonitor) {
		String[] diagramInterfaces = spec.diagram().interfaces();
		Class<?>[] interfaces;
		if (diagramInterfaces == null) {
			interfaces = new Class[0];  
		} else {
			 interfaces = new Class[diagramInterfaces.length];
			 for (int i = 0; i < interfaces.length; ++i) {
				 try {
					interfaces[i] = classLoader.loadClass(diagramInterfaces[i]);
				} catch (ClassNotFoundException e) {
					throw new NasdanikaException(e);
				}
			 }
		}
		
		Map<String,Object> properties = spec.diagram().properties() == null ? new HashMap<>() :  new HashMap<>(spec.diagram().properties());
		String fragment = requirement.uri().fragment();
		if (fragment != null) {
			Pattern.compile("&")
			   .splitAsStream(fragment)
			   .map(s -> Arrays.copyOf(s.split("=", 2), 2))
			   .forEach(entry -> properties.put(decode(entry[0]), (Object) decode(entry[1])));
		}
		
		DiagramRequirement diagramRequirement = new DiagramRequirement(
				spec.diagram().location() == null ? null : requirement.uriHandler().normalize(URI.createURI(spec.diagram().location()).resolve(requirement.uri())),
				spec.diagram().source(),
				spec.diagram().base() == null ? requirement.uri() : requirement.uriHandler().normalize(URI.createURI(spec.diagram().base()).resolve(requirement.uri())),				
				spec.diagram()::select,		
				pName -> getProperty(properties, pName), 
				uri -> {
					try {
						return requirement.uriHandler().openStream(uri); 
					} catch (IOException e) {
						throw new NasdanikaException(e);
					}
				},
				spec.diagram().processor(), 
				spec.diagram().bind(), 
				classLoader, 
				interfaces);
		
		return diagramRequirement;
	}

	protected CompletionStage<Iterable<CapabilityProvider<Invocable>>> handleScript(
			URIInvocableRequirement requirement,
			InvocableRequirement spec,
			ClassLoader classLoader,	
			Map<String,Invocable> bindings,
			Loader loader, 
			ProgressMonitor progressMonitor) {
		
		ScriptRecord scriptRecord = spec.script();
		ScriptEngine scriptEngine;
		URI locationURI = scriptRecord.location() == null ? null : requirement.uriHandler().normalize(URI.createURI(scriptRecord.location()));
		if (scriptRecord.engineFactory() == null) {
			ScriptEngineManager scriptEngineManager = new ScriptEngineManager(classLoader);
			if (scriptRecord.language() != null) {
				scriptEngine = scriptEngineManager.getEngineByMimeType(scriptRecord.language());
				if (scriptEngine == null) {
					return wrapError(new IllegalArgumentException("No script engine found for MIME type '" + scriptRecord.language() + "': " + requirement.uri()));						
				}
			} else if (locationURI == null) {
				return wrapError(new IllegalArgumentException("Script language is required: " + requirement.uri()));											
			} else {	
				String lastSegment = locationURI.lastSegment();
				int lastDot = lastSegment.lastIndexOf('.');
				String extension = lastSegment.substring(lastDot + 1);
				if (lastDot == -1) {
					return wrapError(new IllegalArgumentException("Script language is required for " + locationURI));																					
				}
				
				scriptEngine = scriptEngineManager.getEngineByExtension(extension);
				if (scriptEngine == null) {
					return wrapError(new IllegalArgumentException("No script engine found for extension '" + extension + "': " + locationURI));
				}
			}
		} else {
			try {
				ScriptEngineFactory scriptEngineFactory = (ScriptEngineFactory) classLoader.loadClass(scriptRecord.engineFactory()).getConstructor().newInstance();
				scriptEngine = scriptEngineFactory.getScriptEngine();
			} catch (InstantiationException 
					| IllegalAccessException 
					| IllegalArgumentException
					| InvocationTargetException 
					| NoSuchMethodException 
					| SecurityException
					| ClassNotFoundException e) {
				
				throw new NasdanikaException("Could not load script engine factory " + scriptRecord.engineFactory() + ": " + e, e);
			}
		}
		
		String scriptSource;
		if (scriptRecord.source() != null) {
			scriptSource = scriptRecord.source();
		} else {
			URIInvocableRequirement sourceRequirement = new URIInvocableRequirement(locationURI, requirement.uriHandler(), classLoader, null);
			try {
				scriptSource = DefaultConverter.INSTANCE.toString(openStream(sourceRequirement));
			} catch (IOException e) {
				return wrapError(new NasdanikaException("Error loading script at '" + locationURI + "': " + e, e));
			}
		}
		
		Invocable result = Invocable.of(scriptEngine, scriptSource);
		bindings.forEach((name, valueInvocable) -> result.bindByName(name, valueInvocable.invoke()));		
		return wrap(result.bind(loader, progressMonitor, decode(requirement.uri().fragment())));  				
	}

	protected CompletionStage<Iterable<CapabilityProvider<Invocable>>> handleType(
			URIInvocableRequirement requirement,
			InvocableRequirement spec, 
			ClassLoader classLoader,
			List<Invocable> bindings,
			Loader loader, 
			ProgressMonitor progressMonitor) {
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
			
			result = result.bind(
					loader, 
					progressMonitor, 
					decode(requirement.uri().fragment()));
			
			for (Invocable toBind: bindings) {
				Object value = toBind.invoke();
				result = result.bind(value);
			}
			return wrap(result);
		} catch (ClassNotFoundException e) {
			return wrapError(e); 
		}
	}

	protected CompletionStage<Iterable<CapabilityProvider<Invocable>>> handleDataScheme(
			URIInvocableRequirement requirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		String opaquePart = decode(requirement.uri().opaquePart());
		int commaIdx = opaquePart.indexOf(",");
		if (commaIdx == -1) {
			commaIdx = opaquePart.length();
			opaquePart += ",";
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
		
		if (mediaType.startsWith(SPEL_MEDIA_TYPE_PREFIX)) {
			String expression = mediaType.substring(SPEL_MEDIA_TYPE_PREFIX.length());
			return wrapSupplier(() -> Invocable.ofExpression(expression));
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
				
				return wrap(result.bind(loader, progressMonitor, data, decode(requirement.uri().fragment())));
			} catch (ClassNotFoundException e) {
				return wrapError(e);
			}
		}
		
		if (mediaType.equals(YAML_SPEC_MEDIA_TYPE)) {
			Yaml yaml = new Yaml();
			Map<?,?> spec = yaml.load(new ByteArrayInputStream(data));
			return handleSpec(requirement, spec, loader, progressMonitor);
		}
		
		if (mediaType.equals(JSON_SPEC_MEDIA_TYPE)) {
			JSONObject spec = new JSONObject(new JSONTokener(new ByteArrayInputStream(data)));
			return handleSpec(requirement, spec.toMap(), loader, progressMonitor);
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
		if (data == null) {
			return null;
		}
		if (type.isInstance(data)) {
			return data;
		}
		if (String.class == type) {
			return new String(data);
		}
		Object result = DefaultConverter.INSTANCE.convert(data, type);
		if (result == null) {
			result = DefaultConverter.INSTANCE.convert(new String(data), type);
		}
		return result;
	}

}
