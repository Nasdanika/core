/**
 */
package org.nasdanika.graph.model.adapters;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.BiConsumer;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.ObjectNode;
import org.nasdanika.graph.model.Connection;
import org.nasdanika.graph.model.ConnectionSource;
import org.nasdanika.graph.model.ConnectionTarget;
import org.nasdanika.graph.model.Graph;
import org.nasdanika.graph.model.GraphElement;

/**
 * Adapter for {@link Graph} and {@link GraphElement}
 * @param <T>
 */
public class NodeAdapter extends ObjectNode<EObject> implements ElementAdapter<EObject> {

	private NodeAdapter parent;

	public NodeAdapter(EObject value, BiConsumer<EObject, BiConsumer<Element, ProgressMonitor>> elementProvider) {
		super(value);
		
		if (value instanceof Graph) {
			for (GraphElement graphElement: ((Graph<?>) value).getElements()) {
				elementProvider.accept(graphElement, (child, pm) -> {
					children.add((Element) child);
					if (child instanceof NodeAdapter) {
						((NodeAdapter) child).setParent(this);
					}
				});
			}			
		}
		
		if (value instanceof ConnectionSource) {
			for (Connection<?> outgoingConnection: ((ConnectionSource<?>) value).getOutgoingConnections()) {
				elementProvider.accept(outgoingConnection, (ocAdapter, pm) -> {
					ConnectionAdapter connectionAdapter = (ConnectionAdapter) ocAdapter;
					getOutgoingConnections().add(connectionAdapter);
				});
			}			
		}
		
		if (value instanceof ConnectionTarget) {
			for (Connection<?> incomingConnection: ((ConnectionTarget<?>) value).getIncomingConnections()) {
				elementProvider.accept(incomingConnection, (icAdapter, pm) -> {
					ConnectionAdapter connectionAdapter = (ConnectionAdapter) icAdapter;
					getIncomingConnections().add(connectionAdapter);
				});
			}			
		}
	}
	
	void setParent(NodeAdapter parent) {
		this.parent = parent;
	}
	
	public NodeAdapter getParent() {
		return parent;
	}
	
	private Collection<Element> children = Collections.synchronizedCollection(new ArrayList<>());
	
	@Override
	public Collection<Element> getChildren() {
		Collection<Element> ret = new ArrayList<Element>(super.getChildren());
		ret.addAll(children);
		return Collections.unmodifiableCollection(ret);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ConnectionAdapter> getOutgoingConnections() {
		return (Collection<ConnectionAdapter>) super.getOutgoingConnections();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<ConnectionAdapter> getIncomingConnections() {
		return (Collection<ConnectionAdapter>) super.getIncomingConnections();
	}
		
} 
