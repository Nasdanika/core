package org.nasdanika.drawio.message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.graph.processor.RegistryEntry;

public abstract class LinkTargetProcessor<T extends LinkTarget,V> extends BaseProcessor<T,V> {

	protected Collection<BaseProcessor<?>> referrerProcessors = Collections.synchronizedList(new ArrayList<>());	
		
	@RegistryEntry("#element.linkTarget == #this")
	public void addReferrerProcessor(BaseProcessor<?> referrerProcessor) {
		if (referrerProcessor != null) {
			referrerProcessors.add(referrerProcessor);
		}
	}	
	
	public void addReferrer(ModelElement referrer) {
		referrers.add(referrer);
	}
	
	public Collection<ModelElement> getReferrers() {
		return referrers;
	}

	protected Collection<ModelElement> referrers = new ArrayList<>();	
	
	/**
	 * @param modelElement
	 * @return true if the argument is a connection which shall be a logical child of this element.
	 */
	protected boolean isLogicalChildConnection(ModelElement modelElement) {
		if (modelElement instanceof Connection) {
			Node source = ((Connection) modelElement).getSource();
			if (source != null) {
				return source == element || referrers.contains(source);
			}
			return element == modelElement.getParent() || referrers.contains(modelElement.getParent());
		}
		return false;
	}	
	
	// TODO - mention where it appears

}
