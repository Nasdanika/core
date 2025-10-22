package org.nasdanika.emf;

import org.eclipse.emf.ecore.change.ChangeDescription;

/**
 * Applies change description to the mapping source or some underlying data behind a resource
 */
public interface ChangeDescriptionApplier {
	
	/**
	 * 
	 * @param changeDescription
	 * @param target Mapping source and target for applying changes
	 */
	void apply(ChangeDescription changeDescription, Object target);

}
