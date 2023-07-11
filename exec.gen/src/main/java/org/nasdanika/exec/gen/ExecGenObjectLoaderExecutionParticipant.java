package org.nasdanika.exec.gen;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.exec.util.ExecObjectLoaderExecutionParticipant;
import org.nasdanika.persistence.ObjectLoaderResourceFactory;

/**
 * {@link YamlLoadingSupplier} for Exec {@link EPackage}s.
 * Registers exec- loader. 
 * @author Pavel
 *
 */
public abstract class ExecGenObjectLoaderExecutionParticipant extends ExecObjectLoaderExecutionParticipant {

	public ExecGenObjectLoaderExecutionParticipant(Context context, boolean parallel) {
		super(context, parallel);
	}
	
	@Override
	protected ObjectLoaderResourceFactory createObjectLoaderResorceFactory(ResourceSet resourceSet, ProgressMonitor progressMonitor) {
		resourceSet.getAdapterFactories().add(new ExecutionParticpantAdapterFactory());
		return super.createObjectLoaderResorceFactory(resourceSet, progressMonitor);
	}
	
}
