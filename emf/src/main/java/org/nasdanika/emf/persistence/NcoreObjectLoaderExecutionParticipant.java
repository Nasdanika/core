package org.nasdanika.emf.persistence;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.ncore.NcorePackage;

/**
 * Registers {@link NcorePackage} with the {@link ResourceSet}.
 * @author Pavel
 *
 */
public abstract class NcoreObjectLoaderExecutionParticipant extends ObjectLoaderExecutionParticipant {

	public NcoreObjectLoaderExecutionParticipant(Context context, boolean parallel) {
		super(context, parallel);
	}

	@Override
	protected Collection<EPackage> getEPackages() {
		Collection<EPackage> ret = new ArrayList<>(); 
		ret.add(NcorePackage.eINSTANCE);
		return ret;
	}
	
}
