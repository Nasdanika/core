package org.nasdanika.exec.gen;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.persistence.YamlResourceFactory;
import org.nasdanika.exec.util.ExecYamlLoadingExecutionParticipant;

/**
 * {@link YamlLoadingSupplier} for Engineering {@link EPackage}s.
 * Registers exec- loader. 
 * @author Pavel
 *
 */
public abstract class ExecGenYamlLoadingExecutionParticipant extends ExecYamlLoadingExecutionParticipant {

	public ExecGenYamlLoadingExecutionParticipant(Context context) {
		super(context);
	}
	
	@Override
	protected YamlResourceFactory createYamlResorceFactory(ResourceSet resourceSet, ProgressMonitor progressMonitor) {
		resourceSet.getAdapterFactories().add(new ExecutionParticpantAdapterFactory());
		return super.createYamlResorceFactory(resourceSet, progressMonitor);
	}
	
}
