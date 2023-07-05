package org.nasdanika.graph.emf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.emf.EObjectNode.ResultRecord;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * Creates a graph of {@link EObjectNode}'s from a set of root objects. 
 * @author Pavel
 *
 */
public class EObjectGraphFactory {
	
	protected boolean parallelAccept;

	public EObjectGraphFactory(boolean parallelAccept) {
		this.parallelAccept = parallelAccept;
	}
	
	protected ResultRecord createNodeResultRecord(EObject eObject, Function<EObject, EObjectNode> existingNodeResolver, ProgressMonitor progressMonitor) {
		if (existingNodeResolver != null) {
			EObjectNode node = existingNodeResolver.apply(eObject);
			if (node != null) {
				return new ResultRecord(node, false);
			}
		}
		
		BiFunction<EObject, ProgressMonitor, ResultRecord> nodeFactory = (eObj, pMonitor) -> createNodeResultRecord(eObj, existingNodeResolver, pMonitor);
		EObjectNode node = createNode(eObject, nodeFactory, progressMonitor);
		
		return new ResultRecord(node, true);		
	}

	protected EObjectNode createNode(EObject eObject, BiFunction<EObject, ProgressMonitor, ResultRecord> nodeFactory, ProgressMonitor progressMonitor) {
		for (Method method: getClass().getMethods()) { // TODO - targets?
			if (matchNodeFactoryMethod(eObject, method)) {
				try {
					return (EObjectNode) method.invoke(this, eObject, nodeFactory, progressMonitor);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new ExecutionException(e);
				}
			}
		}
				
		return new EObjectNode(
				eObject, 
				nodeFactory,
				this::createReferenceConnection, 
				this::createOperationConnections,
				parallelAccept,
				progressMonitor);
	}	
	
	protected void createReferenceConnection(EObjectNode source, EObjectNode target, int index, EReference reference, ProgressMonitor progressMonitor) {
		new EReferenceConnection(source, target, index, referencePath(source, target, reference, index), reference);
	}
	
	protected void createOperationConnection(EObjectNode source, EObjectNode target, int index, EOperation operation, List<Object> arguments, boolean visitTarget, ProgressMonitor progressMonitor) {
		new EOperationConnection(source, target, index, operationPath(source, target, operation, arguments, index), operation, arguments, visitTarget);
	}
	
	protected void createOperationConnections(EObjectNode source, EOperation operation, BiFunction<EObject, ProgressMonitor, EObjectNode.ResultRecord> nodeFactory, ProgressMonitor progressMonitor) {
		for (EList<Object> arguments: createBindings(source, operation)) {
			createOperationConnections(source, operation, arguments, nodeFactory, progressMonitor);
		}
	}	
	
	/**
	 * Invokes operation with given arguments and creates a connection
	 * @param source
	 * @param operation
	 * @param arguments
	 * @return
	 */
	protected void createOperationConnections(EObjectNode source, EOperation operation, EList<Object> arguments, BiFunction<EObject,ProgressMonitor,EObjectNode.ResultRecord> nodeFactory, ProgressMonitor progressMonitor) {
		try {
			Object result = source.getTarget().eInvoke(operation, arguments);
			
			if (operation.isMany()) {
				int index = 0;
				for (Object e: ((Iterable<?>) result)) {
					if (e instanceof EObject) {
						EObjectNode.ResultRecord resultRecord = nodeFactory.apply((EObject) e, progressMonitor);
						createOperationConnection(source, resultRecord.node(), index++, operation, arguments, resultRecord.isNew(), progressMonitor);
					} else if (e != null) {
						throw new ExecutionException("Cannot create an operation connection for non-EObject result: " + operation + " " + arguments + " -> " + e);
					}
				}
			} else if (result instanceof EObject) {
				EObjectNode.ResultRecord resultRecord = nodeFactory.apply((EObject) result, progressMonitor);
				createOperationConnection(source, resultRecord.node(), -1, operation, arguments, resultRecord.isNew(), progressMonitor);
			} else {			
				throw new ExecutionException("Cannot create an operation connection for non-EObject result: " + operation + " " + arguments + " -> " + result);
			}
		} catch (InvocationTargetException e) {
			throw new ExecutionException(e);
		}
	}
	
	public List<EObjectNode> createGraph(Iterable<? extends EObject> elements, ProgressMonitor progressMonitor) {
		List<EObjectNode> contents = new ArrayList<>();
		Map<EObject, EObjectNode> registry = new HashMap<>();
		for (EObject element: elements) {
			EObjectNode node = createNodeResultRecord(element, registry::get, progressMonitor).node();
			contents.add(node);
			registry.putAll(node.accept(this::buildRegistry));					
		}
		
		for (EObjectNode node: contents) {
			node.accept(e -> {
				if (e instanceof EObjectNode) {
					((EObjectNode) e).resolve(registry::get, this::createReferenceConnection, progressMonitor);
				}
			});
		}
		
		return contents;
	}

	/**
	 * Traverses elements and collects mapping from {@link EObject}s to {@link EObjectNode}s. 
	 * @param element
	 * @param childRegistries
	 * @return
	 */
	protected Map<EObject, EObjectNode> buildRegistry(Element element, Map<? extends Element, Map<EObject, EObjectNode>> childRegistries) {
		Map<EObject, EObjectNode> result = new HashMap<>();
		if (element instanceof EObjectNode) {
			result.put(((EObjectNode) element).getTarget(), (EObjectNode) element);
		}
		for (Map<EObject, EObjectNode> cr: childRegistries.values()) {
			result.putAll(cr);
		}
		return result;
	}	
	
	protected String referencePath(EObjectNode source, EObjectNode target, EReference reference, int index) {
		String position = String.valueOf(index);
		if (reference.isMany()) {
			EList<EAttribute> eKeys = reference.getEKeys();
			if (eKeys.isEmpty()) {
				return position;
			} else {
				StringBuilder pathBuilder = new StringBuilder();
				for (EAttribute eKey: eKeys) {
					Object value = eKeyToPathSegment(eKey, target.getTarget().eGet(eKey));
					if (value == null || (value instanceof String && org.nasdanika.common.Util.isBlank((String) value))) {
						return pathBuilder.length() == 0 ? position : pathBuilder.toString();
					}
					if (pathBuilder.length() > 0) {
						pathBuilder.append("/");
					}
					pathBuilder.append(value);
				}
				return pathBuilder.toString();		
			}
		}
		return null;		
	}
		
	protected Object eKeyToPathSegment(EAttribute keyAttribute, Object keyValue) {
		return keyValue;
	}
	
	protected String operationPath(EObjectNode source, EObjectNode target, EOperation operation, List<Object> arguments, int index) {
		EList<EParameter> parameters = operation.getEParameters();
		StringBuilder pathBuilder = new StringBuilder();
		if (!parameters.isEmpty()) {
			for (int i = 0; i < parameters.size(); ++ i) {
				if (pathBuilder.length() > 0) {
					pathBuilder.append("/");
				}
				pathBuilder.append(argumentToPathSegment(parameters.get(i), arguments.get(i)));
			}
		}
		if (operation.isMany()) {
			if (pathBuilder.length() > 0) {
				pathBuilder.append("/");
			}
			pathBuilder.append(index);			
		}
		return pathBuilder.toString();		
	}
		
	protected Object argumentToPathSegment(EParameter parameter, Object argument) {
		return argument;
	}		
	
	protected Collection<EList<Object>> createBindings(EObjectNode node, EOperation eOperation) {
		return Collections.emptyList();
	}
	
	// --- Reflective annotations wirinig ---
	
	protected boolean matchNodeFactoryMethod(EObject eObj, Method method) {
		if (Modifier.isAbstract(method.getModifiers())) {
			return false;
		}
		
		NodeFactory nodeFactoryAnnotation = method.getAnnotation(NodeFactory.class);
		if (nodeFactoryAnnotation == null) {
			return false;
		}
		
		if (!nodeFactoryAnnotation.type().isInstance(eObj)) {
			return false;
		}
		
		return matchPredicate(eObj, nodeFactoryAnnotation.value());
	}

	/**
	 * Parses and evaluates expression using <a href="https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#expressions">Spring Expression Language</a> 
	 * @param obj
	 * @param expr 
	 * @return true if expression is blank or evaluates to true, false if the expression evaluates to false or throws EvaluationException.
	 */
	protected boolean matchPredicate(Object obj, String expr) {
		if (Util.isBlank(expr)) {
			return true;
		}
		
		ExpressionParser parser = getExpressionParser();
		Expression exp = parser.parseExpression(expr);
		EvaluationContext evaluationContext = getEvaluationContext();
		try {			
			return evaluationContext == null ? exp.getValue(obj, Boolean.class) : exp.getValue(evaluationContext, obj, Boolean.class);
		} catch (EvaluationException e) {
			onEvaluationException(obj, expr, evaluationContext, e);
			return false;
		}
	}
	
	protected ThreadLocal<SpelExpressionParser> expressionParserThreadLocal = new ThreadLocal<>() {
		
		@Override
		protected SpelExpressionParser initialValue() {
			return new SpelExpressionParser();			
		}
		
	};

	protected SpelExpressionParser getExpressionParser() {
		return expressionParserThreadLocal.get();
	}
	
	/**
	 * Override to troubleshoot SPEL predicates.
	 * @param obj
	 * @param expr
	 * @param evaluationContext
	 */
	protected void onEvaluationException(Object obj, String expr, EvaluationContext evaluationContext, EvaluationException exception) {
		
	}
		
	protected EvaluationContext getEvaluationContext() {
		return null;
	}	
	
}
