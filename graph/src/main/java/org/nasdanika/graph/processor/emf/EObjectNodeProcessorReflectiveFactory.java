package org.nasdanika.graph.processor.emf;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reflector;
import org.nasdanika.common.Reflector.Factory;
import org.nasdanika.common.Util;
import org.nasdanika.graph.emf.EObjectNode;
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
	
	protected List<AnnotatedElementRecord> annotatedElementRecords = new ArrayList<>();

	public EObjectNodeProcessorReflectiveFactory(Object... targets) {
		for (Object target: targets) {
			getAnnotatedElementRecords(target).forEach(annotatedElementRecords::add);
		}
	}
	
	protected class MethodEntry {
		
		AnnotatedElementRecord annotatedElementRecord;
		URI identifierBase;
		public MethodEntry(AnnotatedElementRecord annotatedElementRecord, URI identifierBase) {
			super();
			this.annotatedElementRecord = annotatedElementRecord;
			this.identifierBase = identifierBase;
		}

		public AnnotatedElementRecord getAnnotatedElementRecord() {
			return annotatedElementRecord;
		}
		
		public URI getIdentifierBase() {
			return identifierBase;
		}
		
	}
	
	@Processor(type = EObjectNode.class)
	public Object createEObjectNodeProcessor(NodeProcessorConfig<H,E> config, ProgressMonitor progressMonitor) {
		EObject eObj = ((EObjectNode) config.getElement()).getTarget();
		
		Optional<MethodEntry> factoryMethodEntryOptional = annotatedElementRecords
				.stream()
				.filter(aer -> aer.test(config.getElement()) && aer.getAnnotatedElement() instanceof Method)
				.map(aer -> createMethodEntry(aer, eObj))
				.filter(Objects::nonNull)
				.filter(me -> isFactoryMethod(me, eObj))
				.sorted((a,b) -> compareFactoryMethods(a.getAnnotatedElementRecord().getAnnotatedElement(), b.getAnnotatedElementRecord().getAnnotatedElement()))
				.findFirst();
		
		if (factoryMethodEntryOptional.isEmpty()) {
			return null;
		}
		
		MethodEntry factoryMethodEntry = factoryMethodEntryOptional.get();		
		AnnotatedElementRecord factoryAnnotatedElementRecord = factoryMethodEntry.getAnnotatedElementRecord();		
		return ((Method) factoryAnnotatedElementRecord.getAnnotatedElement()).getParameterCount() == 2 ? factoryAnnotatedElementRecord.invoke(config, progressMonitor) : factoryAnnotatedElementRecord.invoke(config); 
	}
	
	protected MethodEntry createMethodEntry(AnnotatedElementRecord aer, EObject eObj) {		
		URI identifierBase = null;
		Class<?> declaringClass = aer.getDeclaringClass();
		if (declaringClass == null) {
			return new MethodEntry(aer, identifierBase);
		}
		EObjectNodeProcessor classAnnotation = declaringClass.getAnnotation(EObjectNodeProcessor.class);
		if (classAnnotation == null) {
			return new MethodEntry(aer, identifierBase);
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
		return new MethodEntry(aer, identifierBase);
	}
	
	protected boolean isFactoryMethod(MethodEntry methodEntry, EObject eObj) {
		EObjectNodeProcessor annotation = methodEntry.getAnnotatedElementRecord().getAnnotation(EObjectNodeProcessor.class);
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
		URI identifierBase = methodEntry.getIdentifierBase();
		if (identifierBase != null && !identifierBase.isRelative()) {
			identifier = identifier.resolve(identifierBase);
		}
		return NcoreUtil.getIdentifiers(eObj).contains(identifier) ? matchPredicate(eObj, annotation.value()) : false;		
	}

	protected int compareFactoryMethods(AnnotatedElement a, AnnotatedElement b) {
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
