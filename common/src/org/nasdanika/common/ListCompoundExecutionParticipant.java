package org.nasdanika.common;

import java.util.ArrayList;
import java.util.List;

public abstract class ListCompoundExecutionParticipant<E extends ExecutionParticipant> extends CompoundExecutionParticipant<E> {

	protected ListCompoundExecutionParticipant(String name) {
		super(name);
	}

	protected List<E> elements = new ArrayList<>();

	@Override
	protected List<E> getElements() {
		return elements;
	}
	
	public void add(E element) {
		elements.add(element);
	}

}
