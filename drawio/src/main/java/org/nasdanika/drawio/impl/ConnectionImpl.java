package org.nasdanika.drawio.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.Connectable;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.ConnectionPointSpec;
import org.nasdanika.drawio.Document.Context;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Point;
import org.nasdanika.drawio.PointList;
import org.nasdanika.drawio.Tag;
import org.nasdanika.drawio.model.ModelFactory;
import org.w3c.dom.Element;

class ConnectionImpl extends ModelElementImpl<Connection> implements Connection {
	
	interface ConnectionPointSpecAdapter extends ConnectionPointSpec {
		
		boolean isSet();
		
		void unset();
		
		default public boolean specEquals(ConnectionPointSpec spec) {
			if (!isSet()) {
				return false;
			}
			return ConnectionPointSpec.super.specEquals(spec);
		}		
		
	}	

	private static final String SOURCE_POINT_ROLE = "sourcePoint";
	private static final String TARGET_POINT_ROLE = "targetPoint";
	private static final String OFFSET_POINT_ROLE = "offset";
	private static final String MX_POINT_ATTRIBUTE = "mxPoint";
	
	private PointList points;

	ConnectionImpl(
			Element element, 
			ModelImpl model, 
			int position,
			Context context) {
		super(element, model, position, context);
		points = new PointListImpl(this);
	}

	@Override
	public Node getSource() {
		return (Node) model.find(getCellElement().getAttribute(ATTRIBUTE_SOURCE));
	}

	@Override
	public Node getTarget() {
		return (Node) model.find(getCellElement().getAttribute(ATTRIBUTE_TARGET));
	}
	
	protected ModelElement<?> getLogicalParent(ConnectionBase connectionBase) {
		if (connectionBase == null) {
			return getParent();
		}
		
		switch (connectionBase) {
		case PARENT:
			return getParent();
		case SOURCE:
			return getSource();
		case TARGET:
			return getTarget();
		default:
			throw new UnsupportedOperationException("Unsupported connection base: " + connectionBase);		
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + getSource() + " -> " + getTarget();
	}
	
	org.nasdanika.drawio.model.Connection toModelConnection(
			ModelFactory factory, 
			Function<org.nasdanika.persistence.Marker, org.nasdanika.ncore.Marker> markerFactory,
			Function<org.nasdanika.drawio.Element<?>, CompletableFuture<EObject>> modelElementProvider,
			Function<Tag, org.nasdanika.drawio.model.Tag> tagProvider) {
		org.nasdanika.drawio.model.Connection mConnection = toModelElement(factory.createConnection(), markerFactory, modelElementProvider, tagProvider);
		
		Node sourceNode = getSource();
		if (sourceNode != null) {
			modelElementProvider.apply(sourceNode).thenAccept(sn -> mConnection.setSource((org.nasdanika.drawio.model.Node) sn));
		}
		
		Node targetNode = getTarget();
		if (targetNode != null) {
			modelElementProvider.apply(targetNode).thenAccept(tn -> mConnection.setTarget((org.nasdanika.drawio.model.Node) tn));
		}
		
		return mConnection;
	}

	@Override
	public void setSource(Connectable source) {
		if (source == null) {
			getCellElement().removeAttribute(ATTRIBUTE_SOURCE);
			getExitPoint().unset();
		} else {
			getCellElement().setAttribute(ATTRIBUTE_SOURCE, org.nasdanika.drawio.Util.getConnectableNode(source).getId());
			if (source instanceof ConnectionPointSpec) {
				getExitPoint().setSpec((ConnectionPointSpec) source);
			}
		}
	}

	@Override
	public void setTarget(Connectable target) {
		if (target == null) {
			getCellElement().removeAttribute(ATTRIBUTE_TARGET);
			getEntryPoint().unset();
		} else {
			getCellElement().setAttribute(ATTRIBUTE_TARGET, org.nasdanika.drawio.Util.getConnectableNode(target).getId());
			if (target instanceof ConnectionPointSpec) {
				getEntryPoint().setSpec((ConnectionPointSpec) target);
			}
		}
	}
	
	protected org.w3c.dom.Element getPointElement(String role, boolean create) {
		Element geometryElement = getGeometryElement(true);
		List<org.w3c.dom.Element> rolePointElements = DocumentImpl.getChildrenElements(
				geometryElement, 
				MX_POINT_ATTRIBUTE,
				pe -> role.equals(pe.getAttribute(AS_ATTRIBUTE)));
		
		if (rolePointElements.isEmpty()) {
			if (create) {
				org.w3c.dom.Element ret = element.getOwnerDocument().createElement(MX_POINT_ATTRIBUTE);
				ret.setAttribute(AS_ATTRIBUTE, role);
				geometryElement.appendChild(ret);
				return ret;
			}
		} 
		
		if (rolePointElements.size() == 1) {
			return rolePointElements.get(0);
		} 
		
		throw new IllegalArgumentException("Expected one " + role + " point element, got " + rolePointElements.size());
	}	

	@Override
	public Point setSourcePoint(double x, double y) {
		Point ret = getSourcePoint();
		ret.setX(x);
		ret.setY(y);
		return ret;		
	}

	@Override
	public Point getSourcePoint() {
		return new PointImpl(create -> getPointElement(SOURCE_POINT_ROLE, create));
	}

	@Override
	public Point setTargetPoint(double x, double y) {
		Point ret = getTargetPoint();
		ret.setX(x);
		ret.setY(y);
		return ret;		
	}

	@Override
	public Point getTargetPoint() {
		return new PointImpl(create -> getPointElement(TARGET_POINT_ROLE, create));
	}
	
	@Override
	public Point setOffset(double x, double y) {
		Point ret = getOffset();
		ret.setX(x);
		ret.setY(y);
		return ret;		
	}

	@Override
	public Point getOffset() {
		return new PointImpl(create -> getPointElement(OFFSET_POINT_ROLE, create));
	}
	
	public PointList getPoints() {
		return points;
	}
	
	private static final String X_STYLE_KEY = "X";
	private static final String DX_STYLE_KEY = "Dx";
	private static final String Y_STYLE_KEY = "Y";
	private static final String DY_STYLE_KEY = "Dy";	
	private static final String PERIMETER_STYLE_KEY = "Dy";	
		
	private class ConnectionPointSpecAdapterImpl implements ConnectionPointSpecAdapter {
				
		private Map<String, String> style;
		private String prefix;

		ConnectionPointSpecAdapterImpl(String prefix) {
			this.style = getStyle();
			this.prefix = prefix;
		}

		@Override
		public Element getElement() {
			return null; // No backing element
		}

		@Override
		public double getX() {
			String xstr = style.get(prefix + X_STYLE_KEY);
			return Util.isBlank(xstr) ? Double.NaN : Double.parseDouble(xstr);
		}

		@Override
		public double getY() {
			String ystr = style.get(prefix + Y_STYLE_KEY);
			return Util.isBlank(ystr) ? Double.NaN : Double.parseDouble(ystr);
		}

		@Override
		public void setX(double x) {
			if (Double.isNaN(x)) {
				style.remove(prefix + X_STYLE_KEY);
				style.remove(prefix + DX_STYLE_KEY);
			} else {
				style.put(prefix + X_STYLE_KEY, String.valueOf(x));
				if (!style.containsKey(DX_STYLE_KEY)) {
					setDx(0);
				}
			}			
		}

		@Override
		public void setY(double y) {
			if (Double.isNaN(y)) {
				style.remove(prefix + Y_STYLE_KEY);
				style.remove(prefix + DY_STYLE_KEY);
			} else {
				style.put(prefix + Y_STYLE_KEY, String.valueOf(y));
				if (!style.containsKey(DY_STYLE_KEY)) {
					setDy(0);
				}
			}			
		}

		@Override
		public double getDx() {
			String dxstr = style.get(prefix + DX_STYLE_KEY);
			return Util.isBlank(dxstr) ? Double.NaN : Double.parseDouble(dxstr);
		}

		@Override
		public double getDy() {
			String dystr = style.get(prefix + DY_STYLE_KEY);
			return Util.isBlank(dystr) ? Double.NaN : Double.parseDouble(dystr);
		}

		@Override
		public void setDx(double dx) {
			style.put(prefix + DX_STYLE_KEY, String.valueOf(dx));
		}

		@Override
		public void setDy(double dy) {
			style.put(prefix + DY_STYLE_KEY, String.valueOf(dy));
		}

		@Override
		public boolean isPerimeter() {
			String pstr = style.get(prefix + PERIMETER_STYLE_KEY);
			return Util.isBlank(pstr) || "1".equals(pstr);
		}

		@Override
		public void setPerimeter(boolean perimeter) {
			if (perimeter) {
				style.remove(prefix + PERIMETER_STYLE_KEY);
			} else {
				style.put(prefix + PERIMETER_STYLE_KEY, "0");				
			}
		}

		@Override
		public boolean isSet() {
			return style.containsKey(prefix + X_STYLE_KEY);
		}

		@Override
		public void unset() {
			style.remove(prefix + X_STYLE_KEY);
			style.remove(prefix + DX_STYLE_KEY);
			style.remove(prefix + Y_STYLE_KEY);
			style.remove(prefix + DY_STYLE_KEY);
			style.remove(prefix + PERIMETER_STYLE_KEY);
		}
		
	}	
		
	ConnectionPointSpecAdapter getExitPoint() {
		return new ConnectionPointSpecAdapterImpl("exit");
	}

	ConnectionPointSpecAdapter getEntryPoint() {
		return new ConnectionPointSpecAdapterImpl("entry");
	}			
	
}
