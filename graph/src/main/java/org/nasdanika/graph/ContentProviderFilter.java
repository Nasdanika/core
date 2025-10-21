package org.nasdanika.graph;

import java.util.Collection;

public class ContentProviderFilter<R,V> implements ContentProvider<R,V> {

	protected ContentProvider<R, V> target;

	public ContentProviderFilter(ContentProvider<R,V> target) {
		this.target = target;
	}
	
	@Override 
	public V getValue() {
		return target.getValue();
	}
	
	@Override
	public Collection<ContentProvider<R,V>> getChildren() {
		return target.getChildren();
	}
	
	@Override
	public R getSourceReference() {
		return target.getSourceReference();
	}
	
	@Override
	public ContentProvider<R,V> getSource() {
		return target.getSource();
	}
	
	@Override 
	public R getTargetReference() {
		return target.getTargetReference();
	}
	
	@Override
	public ContentProvider<R,V> getTarget() {
		return target.getTarget();
	}
	
	@Override 
	public Collection<ContentProvider<R,V>> getOutgoingConnections() {
		return target.getOutgoingConnections(); 
	}

	@Override 
	public Collection<ContentProvider<R,V>> getIncomingConnections() {
		return target.getIncomingConnections(); 
	} 
	
	@Override
	public boolean isSource() {
		return target.isSource();
	}
	
	@Override
	public boolean isTarget() {
		return target.isTarget();
	}
	
	@Override
	public boolean matchReference(R refId) {
		return target.matchReference(refId);
	}
	
	@Override
	public boolean isNode() {
		return isSource() || isTarget() || target.isNode();
	}

}
