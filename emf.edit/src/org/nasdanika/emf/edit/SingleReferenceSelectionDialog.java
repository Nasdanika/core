package org.nasdanika.emf.edit;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;

public class SingleReferenceSelectionDialog extends ElementTreeSelectionDialog {
	private ILabelProvider labelProvider;
	private List<Object> choiceOfValues;
	private String[] filterText = { null };

	public SingleReferenceSelectionDialog(
			Shell parent, 
			ILabelProvider labelProvider,
			ITreeContentProvider contentProvider, 
			List<Object> choiceOfValues,
			Object value) {
		super(parent, labelProvider, contentProvider);
		this.labelProvider = labelProvider;
		this.choiceOfValues = choiceOfValues;
		Predicate<Object> predicate = element -> {
			if (choiceOfValues.contains(element)) {
				if (filterText[0] != null) {
					String eText = labelProvider.getText(element);
					return eText == null || eText.toLowerCase().contains(filterText[0].trim().toLowerCase());																
				}
				return true;
			}
			return false;
		};		
		
		ViewerFilter filter = new ViewerFilter() {
			
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (element instanceof Resource) {
					TreeIterator<EObject> tit = ((Resource) element).getAllContents();
					while (tit.hasNext()) {
						if (choiceOfValues.contains(tit.next())) {
							return true;
						}
					}
					return false;
				}
				
				if (element instanceof EObject) {
					if (predicate.test(element)) {
						return true;
					}
					TreeIterator<EObject> tit = ((EObject) element).eAllContents();
					while (tit.hasNext()) {
						if (predicate.test(tit.next())) {
							return true;
						}
					}
				}
				return false;
			}
			
		};
		addFilter(filter);
		setAllowMultiple(false);	
		setInput(commonAncestor(choiceOfValues));
		setInitialSelection(value);
	}

	@Override
	protected Label createMessageArea(Composite composite) {
		Label messageArea = super.createMessageArea(composite);

		Composite filterComposite = new Composite(composite, SWT.NONE);

		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginWidth = 0;
		gridLayout.marginHeight = 0;
		
		filterComposite.setLayout(gridLayout);
		filterComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		
		Label lblFilter = new Label(filterComposite, SWT.NONE);
		lblFilter.setText("Filter:");
		
		Text filterText = new Text(filterComposite, SWT.BORDER);
		filterText.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));

		IObservableValue<String> textModifyObservable = WidgetProperties.text(SWT.Modify).observeDelayed(700, filterText);
		IValueChangeListener<String> listener = new IValueChangeListener<String>() {
			
			@Override
			public void handleValueChange(ValueChangeEvent<? extends String> event) {
				String value = event.getObservableValue().getValue();
				SingleReferenceSelectionDialog.this.filterText[0] = value;
				Predicate<Object> predicate = element -> {
					if (element == null) {
						return false;
					}
					String eText = labelProvider.getText(element);
					return eText == null || value == null || eText.toLowerCase().contains(value.trim().toLowerCase());									
				};
				List<Object> filtered = choiceOfValues.stream().filter(predicate).collect(Collectors.toList());
				Object newInput = filtered.isEmpty() ? null : commonAncestor(filtered);
				setInput(newInput);
				getTreeViewer().setInput(newInput);
				getTreeViewer().refresh();
			}
			
		};
		textModifyObservable.addValueChangeListener(listener);
		
		return messageArea;
	}
		
	static Object commonAncestor(List<Object> choiceOfValues) {
		if (!choiceOfValues.isEmpty() && choiceOfValues.get(0) instanceof EObject) {
			Z: for (Object candidate = ((EObject) choiceOfValues.get(0)).eContainer(); candidate instanceof EObject; candidate = ((EObject) candidate).eContainer()) {
				for (Object value: choiceOfValues) {
					if (value != candidate && value instanceof EObject)  {
						if (((EObject) value).eContainer() == null || !EcoreUtil.isAncestor((EObject) candidate, ((EObject) value).eContainer())) {
							continue Z;
						}
					}					
				}
				return candidate;
			}
		}
		
		// Common resource
		Resource candidate = null;
		for (Object cv: choiceOfValues) {
			if (cv instanceof EObject) {
				Resource res = ((EObject) cv).eResource();
				if (candidate == null) {
					candidate = res;
				} else if (res != null && res != candidate) {
					return candidate.getResourceSet();
				}
			}
		}
		
		return candidate;
	}
	
}