package org.nasdanika.graph.processor;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * Used to associate linked resources with {@link EObject} to pass them from one loading/processing stage to another.
 * @return
 */
public class LinkedResourcesAdapter extends AdapterImpl {

	private Collection<Resource> linkedResources = new ArrayList<>();
	
	public LinkedResourcesAdapter() {}
	
	public Collection<Resource> getLinkedResources() {
		return linkedResources;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return LinkedResourcesAdapter.class == type;
	}

}
