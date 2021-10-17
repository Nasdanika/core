package org.nasdanika.exec.legacy.java;

import org.nasdanika.common.Adaptable;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.exec.legacy.resources.ReconcileAction;

/**
 * Allows type duality - as a resource and as content. 
 * As a resource the type is wrapped into a compilation unit with the same name as the type.
 * @author Pavel
 *
 */
public class TypeAdapter implements Adaptable, Marked {

	private Type type;

	public TypeAdapter(Type type) {
		this.type = type;
	}

	@Override
	public Marker getMarker() {
		return type.getMarker();		
	}
	
	@SuppressWarnings("unchecked")
	public <T> T adaptTo(java.lang.Class<T> type) {
		// Content
		if (type == SupplierFactory.class) {
			return (T) this.type;
		}
		
		// Wrap into a compilation unit
		if (type == ConsumerFactory.class) {
			return (T) new CompilationUnit(
					getMarker(), 
					this.type.name, 
					ReconcileAction.MERGE, 
					this.type, 
					null, 
					null, 
					true);
		}
		
		return Adaptable.super.adaptTo(type);
	}		

}
