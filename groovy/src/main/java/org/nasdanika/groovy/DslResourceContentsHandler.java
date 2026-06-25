package org.nasdanika.groovy;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.script.Bindings;
import javax.script.CompiledScript;
import javax.script.ScriptException;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.capability.emf.ResourceContentsHandler;
import org.nasdanika.groovy.dsl.DslContext;

/**
 * Transform handler for the {@code .pm} qualifier over a {@code .groovy} source. It resolves the
 * upstream source handler to obtain a {@link CompiledSource}, builds and installs the Product
 * Management Groovy DSL helpers per call, evaluates the compiled script, resolves deferred
 * id-based references, then normalizes the result to {@code EObject[]}. Mirrors
 * {@code MarkdownToProductManagementResourceContentsHandler}.
 */
public class DslResourceContentsHandler implements ResourceContentsHandler<EObject[]> {

	private ResourceContentsHandler<CompiledScript> sourceHandler;

	private Resource resource;

	public DslResourceContentsHandler(Resource resource, ResourceContentsHandler<CompiledScript> sourceHandler) {
		this.resource = resource;
		this.sourceHandler = sourceHandler;
	}

	@Override
	public EObject[] load(InputStream inputStream, Map<?, ?> options) throws IOException {
		try {
			CompiledScript compiledScript = sourceHandler.load(inputStream, options);
	
			DslContext.Resolver resolver = new DslContext.Resolver() {
				
				@Override
		        public EClass classByName(EObject base, String name) {
		        	throw new UnsupportedOperationException("Not implemented yet");
		        }
				
				@Override
		        public EClass classByInstanceClass(EObject base, Class clazz) {
		        	throw new UnsupportedOperationException("Not implemented yet");
				}
				
		        @Override
		        public List<EClass> candidates(EObject base, EClass featureType, EClass targetType) {
		        	throw new UnsupportedOperationException("Not implemented yet");
		        }
						
				@Override
				public Map<String, EClass> names() {
		        	throw new UnsupportedOperationException("Not implemented yet");
					
//		        	return resource.getResourceSet().getPackageRegistry().entrySet().stream()
//		        			.filter(e -> e.getValue() instanceof org.eclipse.emf.ecore.EPackage)
//		        			.collect(java.util.stream.Collectors.toMap(
//		        					Map.Entry::getKey, 
//		        					e -> ((org.eclipse.emf.ecore.EPackage) e.getValue()).getEClassifiers().stream()
//		        							.filter(c -> c instanceof EClass)
//		        							.map(c -> (EClass) c)
//		        							.findFirst()
//		        							.orElse(null)));
		        };
		        
			};
			
			DslContext dsl = new DslContext(resolver, resource);
			Bindings bindings = compiledScript.getEngine().createBindings();
			dsl.installInto(bindings);
	
			Object result = compiledScript.eval(bindings);
			dsl.resolveDeferred();
	
			return normalize(result, dsl);
		} catch (ScriptException e) {
			throw new IOException(e);
		}
	}

	/**
	 * Normalizes the script result into resource contents. A returned {@code EObject}, array or
	 * iterable of {@code EObject}s is flattened; if the script returned nothing usable, the elements
	 * built through the DSL entry points (e.g. {@code product { }}) are used instead.
	 */
	private EObject[] normalize(Object result, DslContext dsl) {
		List<EObject> contents = new ArrayList<>();
		collect(result, contents);
		if (contents.isEmpty()) {
			contents.addAll(dsl.getRoots());
		}
		return contents.toArray(EObject[]::new);
	}

	private void collect(Object value, List<EObject> contents) {
		if (value instanceof EObject eObject) {
			contents.add(eObject);
		} else if (value instanceof Object[] array) {
			for (Object element : array) {
				collect(element, contents);
			}
		} else if (value instanceof Iterable<?> iterable) {
			for (Object element : iterable) {
				collect(element, contents);
			}
		}
	}

	@Override
	public Order getOrder() {
		return sourceHandler.getOrder().add(0);
	}

}

///**
// * JavaBeans-style decapitalization, inlined to avoid a {@code java.desktop} ({@code java.beans})
// * module dependency in headless/CLI builds. Mirrors {@code java.beans.Introspector.decapitalize},
// * including the rule that a name whose first two characters are upper case is left unchanged
// * (so acronym-style class names like {@code URI}/{@code URL} are not mangled).
// */
//static String decapitalize(String name) {
//    if (!name) {
//        return name
//    }
//    if (name.length() > 1 && Character.isUpperCase(name.charAt(1)) && Character.isUpperCase(name.charAt(0))) {
//        return name
//    }
//    char[] chars = name.toCharArray()
//    chars[0] = Character.toLowerCase(chars[0])
//    new String(chars)
//}


//List<EClass> candidates = ePackage.EClassifiers.findAll { EClassifier c ->
//c instanceof EClass && !c.abstract && !c.interface &&
//    featureType.isSuperTypeOf((EClass) c) && targetFeature((EClass) c, targetType) != null
//} as List<EClass>
//

