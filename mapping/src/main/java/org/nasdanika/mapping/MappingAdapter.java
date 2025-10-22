package org.nasdanika.mapping;

import org.eclipse.emf.common.notify.impl.AdapterImpl;

/**
 * Adapter to track mapping source
 */
public class MappingAdapter extends AdapterImpl {
	
	private Object source;

	public MappingAdapter(Object source) {
		this.source = source;
	}
	
	/**
	 * Mapping source
	 * @return
	 */
	public Object getSource() {
		return source;
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return MappingAdapter.class == type;
	}

}
