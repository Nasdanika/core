package org.nasdanika.common;

import java.util.ArrayList;
import java.util.List;

public abstract class ListCompoundExecutionParticipant<E extends ExecutionParticipant> extends CompoundExecutionParticipant<E> {

	protected ListCompoundExecutionParticipant(String name) {
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
	
	public void add(E element) {
		elements.add(element);
		size = null;
	}

}
