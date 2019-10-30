package org.nasdanika.emf;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * Helper class to reduce amount of diagnostic/validation code.
 * @author Pavel Vlasov
 *
 */
public class DiagnosticHelper {
	
	private String source;
	private int code;
	private DiagnosticChain diagnostics;
	private Object owner;

	/**
	 * DiagnosticHelper shall typically be created at the top of validate() method.
	 * @param diagnostics
	 * @param source
	 * @param code
	 * @param owner
	 */
	public DiagnosticHelper(DiagnosticChain diagnostics, String source, int code, Object owner) {
		this.diagnostics = diagnostics;
		this.source = source;
		this.code = code;
		this.owner = owner;
	}
	
	private boolean success = true;
	
	/**
	 * @return true if there were no error level diagnostics.
	 */
	public boolean isSuccess() {
		return success;
	}
	
	/**
	 * Adds diagnostic.
	 * @param severity
	 * @param message Diagnostic message.
	 * @param messageKey Message key for localization, can be null.
	 * @param feature Feature under diagnostic. Can be null.
	 */
	public void addDiagnostic(int severity, String message, String messageKey, EStructuralFeature feature) {
		if (diagnostics != null) {
			if (feature != null && owner instanceof EObject && !((EObject) owner).eClass().getEAllStructuralFeatures().contains(feature)) {
				throw new IllegalArgumentException("EStructuralFeature "+feature+" does not belong to owner's EClass "+ ((EObject) owner).eClass());
			}
			List<Object> data = new ArrayList<>();
			data.add(owner);
			if (feature != null) {
				data.add(feature);
			}
			if (messageKey != null) {
				data.add(messageKey);
			}
			diagnostics.add(new BasicDiagnostic(severity, source, code, message, data.toArray()));
		
			if (Diagnostic.ERROR == severity) {
				success = false;
			}
			
		}
	}
	
	public void error(String message, String messageKey, EStructuralFeature feature) {
		addDiagnostic(Diagnostic.ERROR, message, messageKey, feature);
	}
	
	public void error(String message, String messageKey) {
		error(message, messageKey, null);
	}
	
	public void error(String message, EStructuralFeature feature) {
		error(message, null, feature);
	}
	
	public void error(String message) {
		error(message, null, null);
	}
	
	public void warning(String message, String messageKey, EStructuralFeature feature) {
		addDiagnostic(Diagnostic.WARNING, message, messageKey, feature);
	}
	
	public void warning(String message, String messageKey) {
		warning(message, messageKey, null);
	}
	
	public void warning(String message, EStructuralFeature feature) {
		warning(message, null, feature);
	}
	
	public void warning(String message) {
		warning(message, null, null);
	}
	
	public void info(String message, String messageKey, EStructuralFeature feature) {
		addDiagnostic(Diagnostic.INFO, message, messageKey, feature);
	}
	
	public void info(String message, String messageKey) {
		info(message, messageKey, null);
	}
	
	public void info(String message, EStructuralFeature feature) {
		info(message, null, feature);
	}
	
	public void info(String message) {
		info(message, null, null);
	}
	
}