package org.nasdanika.drawio.impl;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.ConnectionPoint;
import org.nasdanika.drawio.ConnectionPointSpec;
import org.nasdanika.drawio.Document.Context;
import org.nasdanika.drawio.Geometry;
import org.nasdanika.drawio.LayerElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Rectangle;
import org.nasdanika.drawio.Tag;
import org.nasdanika.drawio.impl.ConnectionImpl.ConnectionPointSpecAdapter;
import org.nasdanika.drawio.model.ModelFactory;
import org.w3c.dom.Element;

class NodeImpl extends LayerImpl<Node> implements Node {
	
	NodeImpl(
			Element element, 
			ModelImpl model, 
			int position,
			Context context) {
		super(element, model, position, context);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<LayerElement<?>> getChildren() {
		return (List) super.getChildren();
	}

	@Override
	public List<Connection> getAllIncomingConnections() {
		return getIncomingConnections(c -> true);
	}
		
	public List<Connection> getIncomingConnections(Predicate<Connection> connectionPredicate) {	
		List<Connection> incomingConnections = new ArrayList<>();

		Predicate<org.w3c.dom.Element> predicate = 
				me -> element.hasAttribute(ATTRIBUTE_ID) 
				&& getCellElement(me).hasAttribute(ATTRIBUTE_TARGET) 
				&& getCellElement(me).getAttribute(ATTRIBUTE_TARGET).equals(element.getAttribute(ATTRIBUTE_ID));
		
		model
			.collect(predicate)
			.stream()
			.filter(Connection.class::isInstance)
			.map(Connection.class::cast)
			.filter(connectionPredicate)
			.forEach(incomingConnections::add);
		
		return new AbstractList<Connection>() {

			@Override
			public Connection get(int index) {
				return incomingConnections.get(index);
			}

			@Override
			public int size() {
				return incomingConnections.size();
			}
			
			@Override
			public Connection remove(int index) {
				Connection removed = incomingConnections.remove(index);
				if (removed != null) {
					Rectangle geometry = NodeImpl.this.getGeometry();
					if (geometry != null) {
						removed.setTargetPoint(
								geometry.getX() + geometry.getWidth()/2, 
								geometry.getY() + geometry.getHeight()/2);
					}					
					removed.setTarget(null);					
				}
				return removed;
			}
			
		};		
		
	}

	@Override
	public List<Connection> getAllOutgoingConnections() {
		return getOutgoingConnections(c -> true);
	}
		
	public List<Connection> getOutgoingConnections(Predicate<Connection> connectionPredicate) {
		
		List<Connection> outgoingConnections = new ArrayList<>();

		Predicate<org.w3c.dom.Element> predicate = 
				me -> element.hasAttribute(ATTRIBUTE_ID) 
				&& getCellElement(me).hasAttribute(ATTRIBUTE_SOURCE) 
				&& getCellElement(me).getAttribute(ATTRIBUTE_SOURCE).equals(element.getAttribute(ATTRIBUTE_ID));
				
		model
			.collect(predicate)
			.stream()
			.filter(Connection.class::isInstance)
			.map(Connection.class::cast)
			.filter(connectionPredicate)
			.forEach(outgoingConnections::add);
		
		return new AbstractList<Connection>() {

			@Override
			public Connection get(int index) {
				return outgoingConnections.get(index);
			}

			@Override
			public int size() {
				return outgoingConnections.size();
			}
			
			@Override
			public Connection remove(int index) {
				Connection removed = outgoingConnections.remove(index);
				if (removed != null) {
					Rectangle geometry = NodeImpl.this.getGeometry();
					if (geometry != null) {
						removed.setSourcePoint(
								geometry.getX() + geometry.getWidth()/2, 
								geometry.getY() + geometry.getHeight()/2);
					}
					removed.setSource(null);
				}
				return removed;
			}
			
		};		
		
	}

	@Override
	public Geometry getGeometry() {
		return new GeometryImpl(this::getGeometryElement);
	}

	@Override
	protected List<? extends org.nasdanika.drawio.Element<?>> getLogicalChildren(ConnectionBase connectionBase) {
		List<org.nasdanika.drawio.Element<?>> logicalChildren = new ArrayList<>(super.getLogicalChildren(connectionBase));
		if (connectionBase == ConnectionBase.SOURCE) {
			logicalChildren.addAll(getAllOutgoingConnections());
		} else if (connectionBase == ConnectionBase.TARGET) {
			logicalChildren.addAll(getAllIncomingConnections());			
		}
		return logicalChildren;
	}
	
	org.nasdanika.drawio.model.Node toModelNode(
			ModelFactory factory, 
			Function<org.nasdanika.persistence.Marker, org.nasdanika.ncore.Marker> markerFactory,
			Function<org.nasdanika.drawio.Element<?>, CompletableFuture<EObject>> modelElementProvider,
			Function<Tag, org.nasdanika.drawio.model.Tag> tagProvider) {
		org.nasdanika.drawio.model.Node mNode = toModelLayer(factory, factory.createNode(), markerFactory, modelElementProvider, tagProvider);
		Rectangle geometry = getGeometry();
		if (geometry != null) {
			org.nasdanika.drawio.model.Geometry mGeometry = factory.createGeometry();
			mGeometry.setX(geometry.getX());
			mGeometry.setY(geometry.getY());
			mGeometry.setHeight(geometry.getHeight());
//			mGeometry.setOffsetPoint(Point);
//			mGeometry.setRelative(boolean);
//			mGeometry.setSourcePoint(Point);
//			mGeometry.setTargetPoint(Point);
			mGeometry.setWidth(geometry.getWidth());
			mNode.setGeometry(mGeometry);
		}
		return mNode;
	}
	
	@Override
	protected void onRemove() {
		getAllIncomingConnections().clear();
		getAllOutgoingConnections().clear();
		super.onRemove();
	}

	@Override
	public List<Connection> getIncomingConnections() {				
		return getIncomingConnections(c -> !((ConnectionImpl) c).getEntryPoint().isSet());
	}

	@Override
	public List<Connection> getOutgoingConnections() {
		return getOutgoingConnections(c -> !((ConnectionImpl) c).getExitPoint().isSet());
	}
	
	private class ConnectionPointImpl extends ConnectionPointSpecImpl implements ConnectionPoint {
		
		protected ConnectionPointImpl() {
			super();
		}

		protected ConnectionPointImpl(ConnectionPointSpec spec) {
			super(spec);
		}

		@Override
		public List<Connection> getIncomingConnections() {
			return NodeImpl.this.getIncomingConnections(c -> ((ConnectionImpl) c).getEntryPoint().specEquals(this));
		}

		@Override
		public List<Connection> getOutgoingConnections() {
			return NodeImpl.this.getOutgoingConnections(c -> ((ConnectionImpl) c).getExitPoint().specEquals(this));
		}

		@Override
		public Node getNode() {
			return NodeImpl.this;
		}
		
	}

	@Override
	public List<ConnectionPoint> getConnectionPoints() {
		Stream<ConnectionPointSpec> icp = getAllIncomingConnections()
			.stream()
			.map(c -> ((ConnectionImpl) c).getEntryPoint())
			.filter(ConnectionPointSpecAdapter::isSet)
			.map(ConnectionPointSpecImpl::new);
		
		Stream<ConnectionPointSpec> ocp = getAllOutgoingConnections()
				.stream()
				.map(c -> ((ConnectionImpl) c).getExitPoint())
				.filter(ConnectionPointSpecAdapter::isSet)
				.map(ConnectionPointSpecImpl::new);
		
		return Stream.concat(icp, ocp)
				.distinct()
				.map(ConnectionPointImpl::new)
				.map(ConnectionPoint.class::cast) // For type compatibility
				.toList();
	}

	@Override
	public ConnectionPoint createConnectionPoint() {
		return new ConnectionPointImpl();
	}

}
