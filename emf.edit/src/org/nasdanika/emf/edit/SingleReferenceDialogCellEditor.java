package org.nasdanika.emf.edit;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class SingleReferenceDialogCellEditor extends ExtendedDialogCellEditor {

	private AdapterFactory adapterFactory;
	private ILabelProvider labelProvider;
	private String title;
	private List<Object> choiceOfValues;
	private Object selectedValue;

	public SingleReferenceDialogCellEditor(
			Composite composite, 
			ILabelProvider labelProvider, 
			String title, 
			AdapterFactory adapterFactory,
			List<Object> choiceOfValues,
			Object selectedValue) {
		
		super(composite, labelProvider);
		this.labelProvider = labelProvider;
		this.title = title;
		this.adapterFactory = adapterFactory;
		this.choiceOfValues = choiceOfValues;
		this.selectedValue = selectedValue;
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		ITreeContentProvider contentProvider = new AdapterFactoryContentProvider(adapterFactory instanceof ComposeableAdapterFactory ? ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory() : adapterFactory);
		SingleReferenceSelectionDialog dialog = new SingleReferenceSelectionDialog(cellEditorWindow.getShell(), labelProvider, contentProvider, choiceOfValues, selectedValue);
		dialog.setTitle(title);
		
		dialog.open();		
		if (dialog.getReturnCode() == Window.OK) {
			Object[] result = dialog.getResult();
			if (result == null) {
				return null;
			}
			if (result.length == 0) {
				markDirty();
				doSetValue(null);
				return null;
			}
			return Arrays.stream(result).filter(choiceOfValues::contains).findFirst().orElse(null);
		}
		return null;
	}
	
};
