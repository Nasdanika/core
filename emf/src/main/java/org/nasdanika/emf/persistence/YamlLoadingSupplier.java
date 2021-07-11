package org.nasdanika.emf.persistence;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;

/**
 * Supplier of loaded root objects.
 * @author Pavel
 *
 */
public abstract class YamlLoadingSupplier extends YamlLoadingExecutionParticipant implements Supplier<List<EObject>> {

	@Override
	public List<EObject> execute(ProgressMonitor progressMonitor) throws Exception {
		return roots;
	}

}
