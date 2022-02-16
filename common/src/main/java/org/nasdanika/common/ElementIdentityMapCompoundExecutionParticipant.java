package org.nasdanika.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Compound participant where elements execution results are keyed by the element identity
 * @author Pavel
 *
 * @param <E> Element type
 * @param <R> Execution result type. 
 */
public abstract class ElementIdentityMapCompoundExecutionParticipant<E extends ExecutionParticipant, R> extends CompoundExecutionParticipant<E> {

	protected ElementIdentityMapCompoundExecutionParticipant(String name) {
		super(name, false);
	}

	protected List<E> elements = new ArrayList<>();
	
	private Double size;
	
	@Override
	public double size() {
		if (size == null) { 
			size = super.size();
		}
		return size;
	}

	@Override
	protected List<E> getElements() {
		return elements;
	}
	
	public void put(E element) {
		if (!elements.contains(element)) {
			elements.add(element);
			size = null;
		}
	}
	
}
