package org.nasdanika.emf.edit;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class MultipleReferenceDialogCellEditor extends ExtendedDialogCellEditor {

	private AdapterFactory adapterFactory;
	private ILabelProvider labelProvider;
	private String title;
	private List<Object> choiceOfValues;
	private List<Object> selectedValues;

	public MultipleReferenceDialogCellEditor(
			Composite composite, 
			ILabelProvider labelProvider, 
			String title, 
			AdapterFactory adapterFactory,
			List<Object> choiceOfValues,
			List<Object> selectedValues) {
		
		super(composite, labelProvider);
		this.labelProvider = labelProvider;
		this.title = title;
		this.adapterFactory = adapterFactory;
		this.choiceOfValues = choiceOfValues;
		this.selectedValues = selectedValues;
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		ITreeContentProvider contentProvider = new AdapterFactoryContentProvider(adapterFactory instanceof ComposeableAdapterFactory ? ((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory() : adapterFactory);
		MultipleReferenceSelectionDialog dialog = new MultipleReferenceSelectionDialog(cellEditorWindow.getShell(), labelProvider, contentProvider, choiceOfValues, selectedValues);
		dialog.setTitle(title);
		
		dialog.open();		
		return dialog.getResult();
	}
	
};
