package org.nasdanika.graph.emf;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

import org.eclipse.emf.ecore.EReference;
import org.nasdanika.graph.Element;

public class EReferenceConnection extends Connection {
	
	public interface Factory {
		
		void create(EObjectNode source, EObjectNode target, int index, EReference reference);
		
	}
	
	private EReference reference;

	/**
	 * 
	 * @param source
	 * @param target
	 * @param eReference
	 * @param index -1 for single references.
	 */
	EReferenceConnection(EObjectNode source, EObjectNode target, int index, String path, EReference reference) {
		super(source, target, index, path);
		this.reference = reference;
	}

	@Override
	public <T> T accept(BiFunction<? super Element, Map<? extends Element, T>, T> visitor) {
		return visitor.apply(this, reference.isContainment() ? Collections.singletonMap(getTarget(), getTarget().accept(visitor)) : Collections.emptyMap());
	}
	
	public EReference getReference() {
		return reference;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + reference.getName();
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), reference);
	}

	@Override
	public boolean equals(Object obj) {
		if (super.equals(obj)) {
			EReferenceConnection other = (EReferenceConnection) obj;
			return Objects.equals(reference, other.reference);			
		}
		return false;
	}

}
