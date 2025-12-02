package org.nasdanika.drawio.impl;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Element;
import org.nasdanika.persistence.Marker;
import org.nasdanika.persistence.MarkerImpl;

abstract class ElementImpl<E extends Element<E>> implements Element<E> {
	
	static final String ATTRIBUTE_ID = "id";
	
	protected org.w3c.dom.Element element; 

	@Override
	public org.w3c.dom.Element getElement() {
		return element;
	}

	@Override
	public <T> T accept(BiFunction<Element<?>, Map<Element<?>, T>, T> visitor, ConnectionBase connectionBase) {
		Map<org.nasdanika.drawio.Element<?>, T> childResults = new LinkedHashMap<>();
		for (Element<?> child: getLogicalChildren(connectionBase)) {
			if (child != null) {
				childResults.put(child, child.accept(visitor, connectionBase));
			}
		}
		return visitor.apply(this, childResults);
	}
	
	protected List<? extends Element<?>> getLogicalChildren(ConnectionBase connectionBase) {
		if (connectionBase == null || connectionBase == ConnectionBase.PARENT) {
			return getChildren();
		}
		
		Predicate<Element<?>> isConnection = Connection.class::isInstance;
		return getChildren().stream().filter(isConnection.negate()).toList();
	}
	
	@Override
	public int hashCode() {
		if (element == null) {
			return super.hashCode();
		}
		int hc = element.hasAttribute(ATTRIBUTE_ID) ? element.getAttribute(ATTRIBUTE_ID).hashCode() : element.hashCode();  
		return hc ^ getClass().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElementImpl<?> other = (ElementImpl<?>) obj;
		
		if (element == other.element) {
			return true;
		}
		
		if (element == null || other.element == null) {
			return super.equals(obj);
		}
		
		if (element.hasAttribute(ATTRIBUTE_ID) && other.element.hasAttribute(ATTRIBUTE_ID)) {
			return Objects.equals(element.getAttribute(ATTRIBUTE_ID), other.element.getAttribute(ATTRIBUTE_ID));			
		}

		return false;
	}
	
	@Override
	public List<Marker> getMarkers() {
		String markerLocation = getMarkerLocation();
		String markerPosition = getMarkerPosition();
		if (org.nasdanika.common.Util.isBlank(markerLocation) && org.nasdanika.common.Util.isBlank(markerPosition)) {
			return Collections.emptyList();
		}
		return Collections.singletonList(new MarkerImpl(markerLocation, markerPosition));
	}
		
	protected String getMarkerPosition() {
		return null;
	}	
	
	protected String getMarkerLocation() {
		return null;
	}
	
	@Override
	public org.nasdanika.common.Realm.Element<E> getRealmElement() {
		// TODO Auto-generated method stub
		return null;
	}

}
