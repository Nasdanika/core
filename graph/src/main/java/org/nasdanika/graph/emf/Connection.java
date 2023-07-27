package org.nasdanika.graph.emf;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;

import org.nasdanika.graph.Element;

public class Connection implements org.nasdanika.graph.Connection {
	
	private EObjectNode source;
	private EObjectNode target;

	/**
	 * @param source
	 * @param target
	 */
	public Connection(EObjectNode source, EObjectNode target) {
		this.source = source;
		this.target = target;
		source.addOutgoingConnection(this);
		target.addIncomingConnection(this);
	}

	@Override
	public EObjectNode getSource() {
		return source;
	}

	@Override
	public EObjectNode getTarget() {
		return target;
	}
	
	@Override
	public <T> T accept(BiFunction<? super Element, Map<? extends Element, T>, T> visitor) {
		return visitor.apply(this, Collections.emptyMap());
	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(getIndex(), getSource(), getTarget());
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Connection other = (Connection) obj;
//		if (getIndex() != other.getIndex()) {
//			return false;
//		}		
//		if (!Objects.equals(getSource(), other.getSource())) {
//			return false;
//		}
//		return Objects.equals(getTarget(), other.getTarget());
//	}	
	
}
