package org.nasdanika.exec.gen;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;

/**
 * Loads root element from a resource URI passed to constructor.
 * @author Pavel
 *
 */
public class ExecGenObjectLoaderSupplier extends ExecGenObjectLoaderExecutionParticipant implements Supplier<EObject> {

	private URI uri;
	
	public ExecGenObjectLoaderSupplier(URI uri, Context context) {
		super(context);
		this.uri = uri;
	}

	@Override
	public EObject execute(ProgressMonitor progressMonitor) {
		return resourceSet.getResource(uri, false).getContents().get(0);
	}

	@Override
	protected Collection<URI> getResources() {
		return Collections.singletonList(uri);
	}

}
