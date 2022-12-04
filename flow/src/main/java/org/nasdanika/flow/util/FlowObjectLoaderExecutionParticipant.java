package org.nasdanika.flow.util;

import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.exec.gen.ExecutionParticpantAdapterFactory;
import org.nasdanika.exec.util.ExecObjectLoaderExecutionParticipant;
import org.nasdanika.flow.FlowPackage;
import org.nasdanika.persistence.ObjectLoaderResourceFactory;

/**
 * Registers flow {@link EPackage} and {@link ExecutionParticpantAdapterFactory}
 * @author Pavel
 *
 */
public abstract class FlowObjectLoaderExecutionParticipant extends ExecObjectLoaderExecutionParticipant {

	public FlowObjectLoaderExecutionParticipant(Context context) {
		super(context);
	}

	@Override
	protected Collection<EPackage> getEPackages() {
		Collection<EPackage> ret = super.getEPackages();
		ret.add(FlowPackage.eINSTANCE);
		return ret;
	}
	
	@Override
	protected ObjectLoaderResourceFactory createObjectLoaderResorceFactory(ResourceSet resourceSet, ProgressMonitor progressMonitor) {
		resourceSet.getAdapterFactories().add(new ExecutionParticpantAdapterFactory());
		return super.createObjectLoaderResorceFactory(resourceSet, progressMonitor);
	}
	
}
