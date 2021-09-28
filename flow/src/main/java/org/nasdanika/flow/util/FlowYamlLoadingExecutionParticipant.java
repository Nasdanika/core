package org.nasdanika.flow.util;

import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.persistence.YamlResourceFactory;
import org.nasdanika.exec.gen.ExecutionParticpantAdapterFactory;
import org.nasdanika.exec.util.ExecYamlLoadingExecutionParticipant;
import org.nasdanika.flow.FlowPackage;

/**
 * {@link YamlLoadingSupplier} for Flow {@link EPackage}.
 * @author Pavel
 *
 */
public abstract class FlowYamlLoadingExecutionParticipant extends ExecYamlLoadingExecutionParticipant {

	public FlowYamlLoadingExecutionParticipant(Context context) {
		super(context);
	}

	@Override
	protected Collection<EPackage> getEPackages() {
		Collection<EPackage> ret = super.getEPackages();
		ret.add(FlowPackage.eINSTANCE);
		return ret;
	}
	
	@Override
	protected YamlResourceFactory createYamlResorceFactory(ResourceSet resourceSet, ProgressMonitor progressMonitor) {
		resourceSet.getAdapterFactories().add(new ExecutionParticpantAdapterFactory());
		return super.createYamlResorceFactory(resourceSet, progressMonitor);
	}
	
}
