package org.nasdanika.graph.emf;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.ElementFactory;
import org.nasdanika.graph.GraphFactory;

/**
 * Reflective delegation target of {@link GraphFactory}.
 * Creates a graph of {@link EObjectNode}'s from a set of root objects.
 * Subclass to specialize. 
 * @author Pavel
 *
 */
public class EObjectGraphFactory {
	
	@ElementFactory(type=EObject.class)
	public EObjectNode createEObjectNode(
			EObject element,
			boolean parallel,
			Function<EObject, CompletionStage<Element>> elementProvider, 
			Consumer<CompletionStage<?>> stageConsumer,
			ProgressMonitor progressMonitor) {

		return new EObjectNode(element, parallel, elementProvider, stageConsumer, progressMonitor);
	}
	
	protected void createReferenceConnection(EObjectNode source, EObjectNode target, int index, EReference reference, ProgressMonitor progressMonitor) {
		new EReferenceConnection(source, target, index, referencePath(source, target, reference, index), reference);
	}
	
	protected void createOperationConnection(
			EObjectNode source, 
			EObjectNode target, 
			int index, 
			EOperation operation, 
			List<Object> arguments, 
			boolean visitTarget, 
			ProgressMonitor progressMonitor) {
		
		new EOperationConnection(
				source, 
				target, 
				index, 
				operationPath(source, target, operation, arguments, index), 
				operation, 
				arguments, 
				visitTarget);
	}
	
	protected void createOperationConnections(EObjectNode source, EOperation operation, ProgressMonitor progressMonitor) {
		for (EList<Object> arguments: createBindings(source, operation)) {
			createOperationConnections(source, operation, arguments, progressMonitor);
		}
	}	
	
	/**
	 * Invokes operation with given arguments and creates a connection
	 * @param source
	 * @param operation
	 * @param arguments
	 * @return
	 */
	protected void createOperationConnections(
			EObjectNode source, 
			EOperation operation, 
			EList<Object> arguments, ProgressMonitor progressMonitor) {
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
