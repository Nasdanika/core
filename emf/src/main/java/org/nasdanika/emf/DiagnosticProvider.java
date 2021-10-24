package org.nasdanika.emf;

import java.util.Collection;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Adapter interface providing diagnostics for the target and its features.
 * @author Pavel
 *
 */
public interface DiagnosticProvider {
	
	/**
	 * @return Target diagnostic. 
	 */
	Collection<Diagnostic> getDiagnostic();
	
	/**
	 * @return Diagnostics for a {@link EStructuralFeature} of the target. 
	 */
	Collection<Diagnostic> getFeatureDiagnostic(EStructuralFeature feature);

}
