package org.nasdanika.graph.emf;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

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

		return new EObjectNode(
				element, 
				parallel, 
				elementProvider, 
				stageConsumer,
				this,
				progressMonitor);
	}
	
	public void createEReferenceConnection(
			EObjectNode source, 
			EObjectNode target, 
			int index, 
			EReference reference, 
			ProgressMonitor progressMonitor) {
		
		new EReferenceConnection(source, target, index, referencePath(source, target, reference, index), reference);
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
	
	public void createEClassConnection(
			EObjectNode source, 
			EObjectNode target, 
			ProgressMonitor progressMonitor) {
		
		new EClassConnection(source, target);
	}	
	
	/**
	 * Binds arguments. If bindings is not null, invokes the operation. For each result of the operation creates a connection. 
	 * @param source
	 * @param eOperation
	 * @param parallel
	 * @param elementProvider
	 * @param stageConsumer
	 * @param progressMonitor
	 */
	public void createEOperationConnections(
			EObjectNode source, 
			EOperation eOperation, 
			boolean parallel,
			Function<EObject, CompletionStage<Element>> elementProvider, 
			Consumer<CompletionStage<?>> stageConsumer,
			ProgressMonitor progressMonitor) {
		
		Stream<EList<Object>> bindingsStream = parallel ? createBindings(source, eOperation).parallelStream() : createBindings(source, eOperation).stream();
	
		bindingsStream.forEach(arguments -> 
			createOperationConnections(
					source, 
					eOperation, 
					arguments, 
					parallel,
					elementProvider,
					stageConsumer,
					progressMonitor));
	}	
	
	/**
	 * 
	 * @param operation
	 * @return true if operation connection shall pass visitor to its target.
	 */
	protected boolean isVisitOperationResults(EOperation operation) {
		return false;
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
			EList<Object> arguments, 
			boolean parallel,
			Function<EObject, CompletionStage<Element>> elementProvider, 
			Consumer<CompletionStage<?>> stageConsumer,			
			ProgressMonitor progressMonitor) {
		try {
			Object result = source.getTarget().eInvoke(operation, arguments);
			
			if (operation.isMany()) {
				int counter = 0;
				for (Object e: ((Iterable<?>) result)) {
					if (e instanceof EObject) {
						int index = counter++;
						stageConsumer.accept(elementProvider.apply((EObject) e).thenAccept(resultElement -> {
							createOperationConnection(
									source, 
									resultElement, 
									index, 
									operation, 
									arguments, 
									progressMonitor);							
						}));
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
	
	/**
	 * 
	 * @param source
	 * @param eOperation
	 * @return A list of bindings to invoke the target operation zero or more times.
	 */
	protected Collection<EList<Object>> createBindings(EObjectNode source, EOperation eOperation) {
		return null;
	}
		
}
