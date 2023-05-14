package org.nasdanika.graph.emf;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.emf.EObjectNode.ResultRecord;

/**
 * Creates a graph of {@link EObjectNode}'s from a set of root objects. 
 * @author Pavel
 *
 */
public class EObjectGraphFactory {
	
	protected ResultRecord createNodeResultRecord(EObject eObject, Function<EObject, EObjectNode> existingNodeResolver) {
		if (existingNodeResolver != null) {
			EObjectNode node = existingNodeResolver.apply(eObject);
			if (node != null) {
				return new ResultRecord(node, false);
			}
		}
		
		Function<EObject, ResultRecord> nodeFactory = eObj -> createNodeResultRecord(eObj, existingNodeResolver);
		EObjectNode node = createNode(eObject, nodeFactory);
		
		return new ResultRecord(node, true);		
	}

	protected EObjectNode createNode(EObject eObject, Function<EObject, ResultRecord> nodeFactory) {
		return new EObjectNode(
				eObject, 
				nodeFactory,
				this::createReferenceConnection, 
				this::createOperationConnections);
	}	
	
	protected void createReferenceConnection(EObjectNode source, EObjectNode target, int index, EReference reference) {
		new EReferenceConnection(source, target, index, referencePath(source, target, reference, index), reference);
	}
	
	protected void createOperationConnection(EObjectNode source, EObjectNode target, int index, EOperation operation, List<Object> arguments, boolean visitTarget) {
		new EOperationConnection(source, target, index, operationPath(source, target, operation, arguments, index), operation, arguments, visitTarget);
	}
	
	protected void createOperationConnections(EObjectNode source, EOperation operation, Function<EObject,EObjectNode.ResultRecord> nodeFactory) {
		for (EList<Object> arguments: createBindings(source, operation)) {
			createOperationConnections(source, operation, arguments, nodeFactory);
		}
	}	
	
	/**
	 * Invokes operation with given arguments and creates a connection
	 * @param source
	 * @param operation
	 * @param arguments
	 * @return
	 */
	protected void createOperationConnections(EObjectNode source, EOperation operation, EList<Object> arguments, Function<EObject,EObjectNode.ResultRecord> nodeFactory) {
		try {
			Object result = source.getTarget().eInvoke(operation, arguments);
			
			if (operation.isMany()) {
				int index = 0;
				for (Object e: ((Iterable<?>) result)) {
					if (e instanceof EObject) {
						EObjectNode.ResultRecord resultRecord = nodeFactory.apply((EObject) e);
						createOperationConnection(source, resultRecord.node(), index++, operation, arguments, resultRecord.isNew());
					} else if (e != null) {
						throw new ExecutionException("Cannot create an operation connection for non-EObject result: " + operation + " " + arguments + " -> " + e);
					}
				}
			} else if (result instanceof EObject) {
				EObjectNode.ResultRecord resultRecord = nodeFactory.apply((EObject) result);
				createOperationConnection(source, resultRecord.node(), -1, operation, arguments, resultRecord.isNew());
			} else {			
				throw new ExecutionException("Cannot create an operation connection for non-EObject result: " + operation + " " + arguments + " -> " + result);
			}
		} catch (InvocationTargetException e) {
			throw new ExecutionException(e);
		}
	}
	
	public List<EObjectNode> createGraph(Iterable<? extends EObject> elements) {
		List<EObjectNode> contents = new ArrayList<>();
		Map<EObject, EObjectNode> registry = new HashMap<>();
		for (EObject element: elements) {
			EObjectNode node = createNodeResultRecord(element, registry::get).node();
			contents.add(node);
			registry.putAll(node.accept(this::buildRegistry));					
		}
		
		for (EObjectNode node: contents) {
			node.accept(e -> {
				if (e instanceof EObjectNode) {
					((EObjectNode) e).resolve(registry::get, this::createReferenceConnection);
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
	
}
