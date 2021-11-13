package org.nasdanika.emf.persistence;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.ncore.NcorePackage;

/**
 * {@link YamlLoadingSupplier} for {@link NcorePackage} {@link EPackage}.
 * Registers exec- loader. 
 * @author Pavel
 *
 */
public abstract class NcoreYamlLoadingExecutionParticipant extends YamlLoadingExecutionParticipant {

	public NcoreYamlLoadingExecutionParticipant(Context context) {
		super(context);
	}

	@Override
	protected Collection<EPackage> getEPackages() {
		Collection<EPackage> ret = new ArrayList<>(); 
		ret.add(NcorePackage.eINSTANCE);
		return ret;
	}
	
}
