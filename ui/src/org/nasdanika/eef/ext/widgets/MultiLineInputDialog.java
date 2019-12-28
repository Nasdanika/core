package org.nasdanika.eef.ext.widgets;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class MultiLineInputDialog extends InputDialog {
	
	public MultiLineInputDialog(
			Shell parentShell, 
			String title, 
			String message, 
			String initialValue,
			IInputValidator validator) {
		
		super(parentShell, title, message, initialValue, validator);
		setShellStyle(getShellStyle() | SWT.RESIZE | SWT.MAX);
	}
	
	@Override
	protected int getInputTextStyle() {
		return SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.WRAP;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Control control = super.createDialogArea(parent);
		GridDataFactory.fillDefaults().grab(true, true).hint(800, 600).applyTo(getText());
		return control;
	}
	
}
