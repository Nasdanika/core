package org.nasdanika.emf.edit;

import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.EMFEditUIPropertyEditorFactory;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

public class MultiReferenceDialogCellEditorFactory extends EMFEditUIPropertyEditorFactory {

	private AdapterFactory adapterFactory;

	public MultiReferenceDialogCellEditorFactory(
		URI propertyEditorFactoryURI,
		AdapterFactory adapterFactory) {
		super(propertyEditorFactoryURI);
		
		this.adapterFactory = adapterFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CellEditor createEditor(Object object, IItemPropertyDescriptor propertyDescriptor, Composite composite) {
	    ILabelProvider labelProvider = new org.eclipse.emf.edit.ui.provider.PropertyDescriptor(object, propertyDescriptor).getLabelProvider();
	    IItemPropertySource propertyValue = (IItemPropertySource) propertyDescriptor.getPropertyValue(object);
		return new MultipleReferenceDialogCellEditor(
			composite, 
			labelProvider, 
			propertyDescriptor.getDisplayName(object), 
			adapterFactory, 
			(List<Object>) propertyDescriptor.getChoiceOfValues(object), 
			(List<Object>) propertyValue.getEditableValue(object));
	}
	
};
