package org.nasdanika.graph.processor.emf;

import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Reflector;
import org.nasdanika.common.Util;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.processor.Factory;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.Processor;
import org.nasdanika.ncore.util.NcoreUtil;

/**
 * Uses 
 * @author Pavel
 *
 */
@Factory(type = EObjectNode.class)
public class EObjectNodeProcessorReflectiveFactory<P,H,E,R> extends Reflector {
	
	TODO
	
	protected Object[] targets; // TODO - Target record with predicate
	
	public EObjectNodeProcessorReflectiveFactory(Object... targets) {
		// TODO - all targets - recurse through @Factory and @Factories
		this.targets = targets;
	}
	
	record MethodRecord(Object target, URI identifierBase, Method method) {} 
		
	@Processor(type = EObjectNode.class)
	public Object createEObjectNodeProcessor(NodeProcessorConfig<P,H,E,R> config) {
		EObject eObj = ((EObjectNode) config.getElement()).getTarget();
		
		Optional<MethodRecord> factoryMethodRecordOptional = Stream.of(targets)
				.flatMap(t -> Util.getMethods(t.getClass()).map(m -> createMethodRecord(t, m, eObj)))
				.filter(Objects::nonNull)
				.filter(mr -> isFactoryMethod(mr.method(), mr.identifierBase, eObj))
				.sorted((a,b) -> compareFactoryMethods(a.method(), b.method()))
				.findFirst();
		
		if (factoryMethodRecordOptional.isEmpty()) {
			return null;
		}
		
		MethodRecord factoryMethodRecord = factoryMethodRecordOptional.get();
		return invokeMethod(factoryMethodRecord.target(), factoryMethodRecord.method(), config);
	}
	
	protected MethodRecord createMethodRecord(Object target, Method method, EObject eObj) {
		URI identifierBase = null;
		EObjectNodeProcessor classAnnotation = getClass().getAnnotation(EObjectNodeProcessor.class);
		if (classAnnotation == null) {
			return new MethodRecord(target, null, method);
		}
		if (!classAnnotation.type().isInstance(eObj)) {
			return null;
		}			
		if (!matchPredicate(eObj, classAnnotation.value())) {
			return null;
		}
			
		String identifierStr = classAnnotation.identifier();
		if (!Util.isBlank(identifierStr)) {
			identifierBase = URI.createURI(identifierStr); 
		}
		return new MethodRecord(target, identifierBase, method);		
	}
	
	protected boolean isFactoryMethod(Method method, URI identifierBase, EObject eObj) {
		EObjectNodeProcessor annotation = method.getAnnotation(EObjectNodeProcessor.class);
		if (annotation == null) {
			return false;
		}
		
		if (!annotation.type().isInstance(eObj)) {
			return false;
		}
		
		String identifierStr = annotation.identifier();
		if (Util.isBlank(identifierStr)) {
			return true;
		}
		URI identifier = URI.createURI(identifierStr);
		return NcoreUtil.getIdentifiers(eObj).contains(identifier) ? matchPredicate(eObj, annotation.value()) : false;
		
	}

	protected int compareFactoryMethods(Method a, Method b) {
		EObjectNodeProcessor aAnnotation = a.getAnnotation(EObjectNodeProcessor.class);
		EObjectNodeProcessor bAnnotation = b.getAnnotation(EObjectNodeProcessor.class);
		int priorityCmp = bAnnotation.priority() - aAnnotation.priority();
		if (priorityCmp != 0) {
			return priorityCmp;
		}
		Class<? extends EObject> aType = aAnnotation.type(); 
		Class<? extends EObject> bType = bAnnotation.type();
		if (aType == bType) {
			return 0;
		}
		if (aType.isAssignableFrom(bType)) {
			return 1;
		}
		if (bType.isAssignableFrom(aType)) {
			return -1;
		}
		return a.hashCode() - b.hashCode();
	}			
	
}
