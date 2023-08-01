package org.nasdanika.graph.emf;

import java.util.List;

import org.eclipse.emf.ecore.EOperation;

/**
 * Connection representing a result or invocation of {@link EOperation}
 * @author Pavel
 *
 */
public class EOperationConnection extends QualifiedConnection<EOperationConnectionQualifier> {

	/**
	 * 
	 * @param source
	 * @param target
	 * @param eReference
	 * @param index -1 for single references.
	 */
	EOperationConnection(
			EObjectNode source, 
			EObjectNode target, 
			boolean visitTargetNode,
			EOperation operation, 
			List<Object> arguments, 
			int index, 
			String path) {
		
		super(source, target, visitTargetNode, new EOperationConnectionQualifier(operation, arguments, index), path);
	}
	
	/**
	 * Convenience accessor
	 * @return
	 */
	public EOperation getOperation() {
		return get().operation();
	}
	
	/**
	 * Convenience accessor
	 * @return
	 */
	public int getIndex() {
		return get().index();
	}

	/**
	 * Convenience accessor
	 * @return
	 */
	public List<Object> getArguments() {
		return get().arguments();
	}

}
