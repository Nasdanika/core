package org.nasdanika.exec.util;

import java.util.Collection;

import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.Context;
import org.nasdanika.emf.persistence.NcoreObjectLoaderExecutionParticipant;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.exec.content.ContentPackage;
import org.nasdanika.exec.resources.ResourcesPackage;

/**
 * Registers Exec {@link EPackage}s. 
 * @author Pavel
 *
 */
public abstract class ExecObjectLoaderExecutionParticipant extends NcoreObjectLoaderExecutionParticipant {

	public ExecObjectLoaderExecutionParticipant(Context context) {
		super(context);
	}

	@Override
	protected Collection<EPackage> getEPackages() {
		Collection<EPackage> ret = super.getEPackages(); 
		ret.add(ExecPackage.eINSTANCE);
		ret.add(ContentPackage.eINSTANCE);
		ret.add(ResourcesPackage.eINSTANCE);
		return ret;
	}
	
}
