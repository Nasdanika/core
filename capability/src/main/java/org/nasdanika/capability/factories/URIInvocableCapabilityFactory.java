package org.nasdanika.capability.factories;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Modifier;
import java.util.Base64;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

/**
 * Creates {@link Invocable} from {@link URI} 
 */
public class URIInvocableCapabilityFactory extends ServiceCapabilityFactory<URI, Invocable> {
	
	private static final String METHOD_REF = "::";
	private static final String BASE_64 = ";base64";
	private static final String VALUE_MEDIA_TYPE_PREFIX = "value/"; // Value wrapped into invocable
	private static final String JAVA_MEDIA_TYPE_PREFIX = "java/"; // Construction of a given type - binding data as "data" argument 
	private static final String SPEC_MEDIA_TYPE = "application/yaml/invocable";
		
	private static final ScriptEngineManager SCRIPT_ENGINE_MANAGER = new ScriptEngineManager();

	@Override
	public boolean isFor(Class<?> type, Object serviceRequirement) {
		return type == Invocable.class && serviceRequirement instanceof URI;
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<Invocable>>> createService(
			Class<Invocable> serviceType,
			URI serviceRequirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		if ("data".equals(serviceRequirement.scheme())) {
			return handleDataScheme(serviceRequirement, loader, progressMonitor);
		}
		
		if (serviceRequirement.lastSegment() != null) {
			String lastSegment = serviceRequirement.lastSegment();
			int lastDot = lastSegment.lastIndexOf('.');
			String extension = lastSegment.substring(lastDot + 1);
			if (lastDot != -1) {
				if (extension.equalsIgnoreCase("yml") || extension.equalsIgnoreCase("yaml") ) {			
					return handleYamlSpec(serviceRequirement, loader, progressMonitor);
				}
				
				ScriptEngine scriptEngine = SCRIPT_ENGINE_MANAGER.getEngineByExtension(extension);
				if (scriptEngine == null) {
					return wrapError(new IllegalArgumentException("No script engine found for extension '" + extension + "': " + serviceRequirement));
				}
				
				try {
					Reader reader = read(serviceRequirement);
					String script = DefaultConverter.INSTANCE.toString(reader);					
					
					Invocable result = new Invocable() {
						
						@Override
						public Object invoke(Object... args) {
							scriptEngine.put("args", args);
							try {
								return scriptEngine.eval(script);
							} catch (ScriptException e) {
								throw new NasdanikaException("Error evaluating script at '" + serviceRequirement + "': " + e, e);
							}
						}
						
					};
					
					return wrap(result.bind(loader, progressMonitor, serviceRequirement.fragment()));
				} catch (IOException e) {
					return wrapError(e);
				}				
			}				
		}
		return empty();
	}
	
	protected Reader read(URI uri) throws IOException {
		return DefaultConverter.INSTANCE.toReader(uri);
	}
	
	protected CompletionStage<Iterable<CapabilityProvider<Invocable>>> handleYamlSpec(
			URI serviceRequirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		
		throw new UnsupportedOperationException();
	}	

	protected CompletionStage<Iterable<CapabilityProvider<Invocable>>> handleDataScheme(
			URI serviceRequirement,
			Loader loader,
			ProgressMonitor progressMonitor) {
		String opaquePart = serviceRequirement.opaquePart();
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
		ScriptEngine scriptEngine = SCRIPT_ENGINE_MANAGER.getEngineByMimeType(mediaType);
		if (scriptEngine == null) {
			return wrapError(new IllegalArgumentException("No script engine found for MIME type '" + mediaType + "': " + serviceRequirement));
		}
		
		Invocable result = new Invocable() {
			
			@Override
			public Object invoke(Object... args) {
				scriptEngine.put("args", args);
				try {
					return scriptEngine.eval(dataPart);
				} catch (ScriptException e) {
					throw new NasdanikaException("Error evaluating script at '" + serviceRequirement + "': " + e, e);
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
