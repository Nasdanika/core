package org.nasdanika.exec.util;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.emf.persistence.YamlLoadingExecutionParticipant;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.exec.content.ContentPackage;
import org.nasdanika.exec.resources.ResourcesPackage;

/**
 * {@link YamlLoadingSupplier} for Engineering {@link EPackage}s.
 * Registers exec- loader. 
 * @author Pavel
 *
 */
public abstract class ExecYamlLoadingExecutionParticipant extends YamlLoadingExecutionParticipant {

	public ExecYamlLoadingExecutionParticipant(Context context) {
		super(context);
	}

	@Override
	protected Collection<EPackage> getEPackages() {
		Collection<EPackage> ret = new ArrayList<>(); 
		ret.add(ExecPackage.eINSTANCE);
		ret.add(ContentPackage.eINSTANCE);
		ret.add(ResourcesPackage.eINSTANCE);
		return ret;
	}
	
}