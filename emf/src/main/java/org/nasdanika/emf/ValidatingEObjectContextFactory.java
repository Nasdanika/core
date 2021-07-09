package org.nasdanika.emf;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.nasdanika.common.Context;
import org.nasdanika.common.ContextFactory;

/**
 * Validates {@link EObject} and then creates a context from it. Throws a {@link DiagnosticException} if validation level is error.
 * @author Pavel
 *
 */
public class ValidatingEObjectContextFactory implements ContextFactory<EObject> {

	@Override
	public Context createContext(EObject input) throws Exception {
		Diagnostician diagnostician = new Diagnostician();
		Diagnostic inputDiagnostic = diagnostician.validate(input);
		if (inputDiagnostic.getSeverity() == Diagnostic.ERROR) {
			throw new DiagnosticException(inputDiagnostic);
		}		
		return new EObjectContext(input);
	}

}
