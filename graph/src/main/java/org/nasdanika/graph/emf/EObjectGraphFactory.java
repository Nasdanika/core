package org.nasdanika.graph.emf;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
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

/**
 * Reflective delegation target of {@link GraphFactory}.
 * Creates a graph of {@link EObjectNode}'s from a set of root objects.
 * Subclass to specialize. 
 * @author Pavel
 *
 */
public class EObjectGraphFactory {
	
	@org.nasdanika.common.Transformer.Factory(type=EObject.class)
	public EObjectNode createEObjectNode(
			EObject element,
			boolean parallel,
			BiConsumer<EObject, BiConsumer<Element, ProgressMonitor>> elementProvider, 
			Consumer<BiConsumer<Map<EObject, Element>,ProgressMonitor>> registry,
			ProgressMonitor progressMonitor) {

		return new EObjectNode(
				element, 
				parallel, 
				elementProvider, 
				registry,
				this,
				progressMonitor);
	}
	
	public void createEReferenceConnection(
			EObjectNode source, 
			EObjectNode target, 
			int index, 
			EReference reference, 
			ProgressMonitor progressMonitor) {
		
		new EReferenceConnection(source, target, reference, index, referencePath(source, target, reference, index));
	}
	
	protected boolean isCompactPath(EObjectNode source, EObjectNode target, EReference reference, int index) {
		return false;
	}
	
	protected String referencePath(EObjectNode source, EObjectNode target, EReference reference, int index) {
		if (reference.isMany()) {
			if (isCompactPath(source, target, reference, index)) {
				return Integer.toString(index, Character.MAX_RADIX);
			}
			
			EList<EAttribute> eKeys = reference.getEKeys();
			String position = String.valueOf(index);
			if (eKeys.isEmpty()) {
				return position;
			} else {
				StringBuilder pathBuilder = new StringBuilder();
				for (EAttribute eKey: eKeys) {
					Object value = eKeyToPathSegment(eKey, target.get().eGet(eKey));
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
	
	public void createEContainerConnection(
			EObjectNode source, 
			EObjectNode target, 
			ProgressMonitor progressMonitor) {
		
		new EContainerConnection(source, target);
	}	
	
	/**
	 * Binds arguments. If bindings is not null, invokes the operation. For each result of the operation creates a connection. 
	 * @param source
	 * @param eOperation
	 * @param parallel
	 * @param elementProvider
	 * @param progressMonitor
	 */
	public void createEOperationConnections(
			EObjectNode source, 
			EOperation eOperation, 
			boolean parallel,
			BiConsumer<EObject, BiConsumer<Element, ProgressMonitor>> elementProvider, 
			ProgressMonitor progressMonitor) {
		
		Stream<EList<Object>> bindingsStream = parallel ? createBindings(source, eOperation).parallelStream() : createBindings(source, eOperation).stream();
	
		bindingsStream.forEach(arguments -> 
			createEOperationConnections(
					source, 
					eOperation, 
					arguments, 
					parallel,
					elementProvider,
					progressMonitor));
	}	
	
	/**
	 * 
	 * @param source
	 * @param eOperation
	 * @return A list of bindings to invoke the target operation zero or more times.
	 */
	protected Collection<EList<Object>> createBindings(EObjectNode source, EOperation eOperation) {
		return Collections.emptyList();
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
	protected void createEOperationConnections(
			EObjectNode source, 
			EOperation operation, 
			EList<Object> arguments, 
			boolean parallel,
			BiConsumer<EObject, BiConsumer<Element, ProgressMonitor>> elementProvider, 
			ProgressMonitor progressMonitor) {
		try {
			Object result = source.get().eInvoke(operation, arguments);			
			boolean visitResults = isVisitOperationResults(operation);
			record ResultRecord(int index, EObject value) {};
			List<ResultRecord> resultRecords = new ArrayList<>();
			if (operation.isMany()) {
				int index = 0;
				for (Object e: ((Iterable<?>) result)) {
					if (e instanceof EObject) {
						resultRecords.add(new ResultRecord(index++, (EObject) e));
					} else if (e != null) {
						throw new ExecutionException("Cannot create an operation connection for non-EObject result: " + operation + " " + arguments + " -> " + e);
					}
				}
			} else if (result instanceof EObject) {
				resultRecords.add(new ResultRecord(-1, (EObject) result));
			} else {			
				throw new ExecutionException("Cannot create an operation connection for non-EObject result: " + operation + " " + arguments + " -> " + result);
			}
			
			(parallel ? resultRecords.parallelStream() : resultRecords.stream())
				.forEach(resultRecord -> {
					elementProvider.accept(resultRecord.value(), (resultElement, pm) -> {
						createEOperationConnection(
								source, 
								(EObjectNode) resultElement, 
								visitResults,
								operation, 
								arguments, 
								resultRecord.index(), 
								pm);
					});
				});			
		} catch (InvocationTargetException e) {
			throw new ExecutionException(e);
		}
	}
	
	protected void createEOperationConnection(
			EObjectNode source, 
			EObjectNode target, 
			boolean visitTarget, 
			EOperation operation, 
			List<Object> arguments, 
			int index, 
			ProgressMonitor progressMonitor) {
		
		new EOperationConnection(
				source, 
				target, 
				visitTarget,
				operation, 
				arguments, 
				index, 
				operationPath(source, target, operation, arguments, index));
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
		
}
