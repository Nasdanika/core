package org.nasdanika.graph.emf;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

import org.eclipse.emf.ecore.EOperation;
import org.nasdanika.graph.Element;

/**
 * Connection representing a result or invocation of {@link EOperation}
 * @author Pavel
 *
 */
public class EOperationConnection extends QualifiedConnection {
	
	private EOperation operation;
	private List<Object> arguments;
	private boolean visitTargetNode;

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
			int index, 
			String path, 
			EOperation operation, 
			List<Object> arguments, 
			boolean visitTargetNode) {
		
		super(source, target, index, path);
		this.operation = operation;
		this.arguments = arguments;
		this.visitTargetNode = visitTargetNode;
	}

	@Override
	public <T> T accept(BiFunction<? super Element, Map<? extends Element, T>, T> visitor) {
		return visitor.apply(this, visitTargetNode ? Collections.singletonMap(getTarget(), getTarget().accept(visitor)) : Collections.emptyMap());
	}

	public EOperation getOperation() {
		return operation;
	}

	public List<Object> getArguments() {
		return arguments;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + operation.getName() + " " + arguments;
	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(super.hashCode(), arguments, operation);
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (super.equals(obj)) {
//			EOperationConnection other = (EOperationConnection) obj;
//			return Objects.equals(arguments,  other.getArguments()) && Objects.equals(operation, other.getOperation());			
//		}
//		return false;
//	}

}
