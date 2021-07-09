package org.nasdanika.emf.persistence;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.common.Context;

public class ContextAdapter extends AdapterImpl implements Context {
	
	private Context context;

	public ContextAdapter(Context context) {
		this.context = context;
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return Context.class == type;
	}

	@Override
	public Object get(String key) {
		return context.get(key);
	}

	@Override
	public <T> T get(Class<T> type) {
		return context.get(type);
	}

}
