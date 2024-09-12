package org.nasdanika.capability.factories;

import java.lang.reflect.Modifier;
import java.util.Base64;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;

/**
 * Creates {@link Invocable} from {@link URI} 
 */
public class URIInvocableCapabilityFactory extends ServiceCapabilityFactory<URI, Invocable> {
	
	private static final String METHOD_REF = "::";
	private static final String BASE_64 = ";base64";
	private static final String VALUE_MEDIA_TYPE_PREFIX = "value/"; // Value wrapped into invocable
	private static final String JAVA_MEDIA_TYPE_PREFIX = "java/"; // Construction of a given type - binding data as "data" argument if present (length > 0) 
	private static final String APPLICATION_MEDIA_TYPE_PREFIX = "application/";

	@Override
	public boolean isFor(Class<?> type, Object serviceRequirement) {
		return type == Invocable.class && serviceRequirement instanceof URI;
	}

	@Override
	protected CompletionStage<Iterable<CapabilityProvider<Invocable>>> createService(
			Class<Invocable> serviceType,
			URI serviceRequirement,
			BiFunction<Object, ProgressMonitor, CompletionStage<Iterable<CapabilityProvider<Object>>>> resolver,
			ProgressMonitor progressMonitor) {
		
		if ("data".equals(serviceRequirement.scheme())) {
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
					throw new IllegalArgumentException("Invalid type name: " + typeName + ": " + e, e);
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
					
					return wrap(result.bind(data));
				} catch (ClassNotFoundException e) {
					throw new IllegalArgumentException("Invalid type name: " + typeName + ": " + e, e);
				}
			}
			
			// TODO - application prefix
			
			return empty();
		}
		
		// TODO - loader
		
		// TODO Auto-generated method stub
		return empty();
	}
	
	protected Object loadValue(Class<?> type, byte[] data) {
		if (String.class == type) {
			return new String(data);
		}
		
		// TODO - numbers, date, ...
		
		throw new UnsupportedOperationException();
	}

}
