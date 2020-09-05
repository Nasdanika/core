package org.nasdanika.emf.presentation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Configures model elements created by the new wizard.
 * @author Pavel
 *
 */
public interface InitialObjectConfigurator {
	
	boolean accept(EObject eObject);
	
	void addParameter(String name, String value);
	
	String getLabel();
	
	Control createControl(Composite parent, EObject initialModel);
	
	EObject configure(EObject initialModel);

}
