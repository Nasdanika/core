package org.nasdanika.emf.edit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateProvider;
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
import org.eclipse.ui.dialogs.CheckedTreeSelectionDialog;

public class MultipleReferenceSelectionDialog extends CheckedTreeSelectionDialog {
	
	private List<Object> selectedValues;
	private List<Object> choiceOfValues;
	private ILabelProvider labelProvider;
	private String[] filterText = { null };
	private Predicate<Object> predicate; 

	public MultipleReferenceSelectionDialog(
			Shell parent, 
			ILabelProvider labelProvider,
			ITreeContentProvider contentProvider,
			List<Object> choiceOfValues,
			List<Object> selectedValues) {
		super(parent, labelProvider, contentProvider);
		this.labelProvider = labelProvider;
		this.choiceOfValues = choiceOfValues;
		this.selectedValues = selectedValues;		
		this.predicate = element -> {
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
								
				if (element instanceof EReferenceItemProvider) {
					EReferenceItemProvider eReferenceItemProvider = (EReferenceItemProvider) element;
					for (Object child: eReferenceItemProvider.getChildren(eReferenceItemProvider.getTarget())) {
						if (select(viewer, element, child)) {
							return true;
						}
					}
				}
				
				return false;
			}
			
		};
		addFilter(filter);
						
		setInput(SingleReferenceSelectionDialog.commonAncestor(choiceOfValues));
		setInitialElementSelections(selectedValues);
		
		setValidator(selection -> {
			if (selection != null) {
				for (Object e: selection) {
					if (!choiceOfValues.contains(e)) {
						return new Status(IStatus.ERROR, "org.nasdanika.emf", "Invalid selection: " + e);						
					}
				}
			}
			return Status.OK_STATUS;
		}); 		
		
		Set<Object> toExpand = new HashSet<>();
		for (Object value: selectedValues) {
			if (value instanceof EObject) {
				EObject ev = (EObject) value;
				Resource eResource = ev.eResource();
				if (eResource != null) {
					toExpand.add(eResource);
				}
				if (eResource.getResourceSet() != null) {
					toExpand.add(eResource.getResourceSet());
				}
				for (EObject container = ev.eContainer(); container != null; container = container.eContainer()) {
					toExpand.add(container);
				}
			}
		}
		
		setExpandedElements(new ArrayList<Object>(toExpand).toArray());
		
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
				MultipleReferenceSelectionDialog.this.filterText[0] = value;
				Predicate<Object> predicate = element -> {
					if (element == null) {
						return false;
					}
					String eText = labelProvider.getText(element);
					return eText == null || value == null || eText.toLowerCase().contains(value.trim().toLowerCase());									
				};
				List<Object> filtered = choiceOfValues.stream().filter(predicate).collect(Collectors.toList());
				Object newInput = filtered.isEmpty() ? null : SingleReferenceSelectionDialog.commonAncestor(filtered);
				setInput(newInput);
				getTreeViewer().setInput(newInput);
				getTreeViewer().refresh();
			}
			
		};
		textModifyObservable.addValueChangeListener(listener);
		
		return messageArea;
	}	

	@Override
	protected CheckboxTreeViewer createTreeViewer(Composite parent) {
		CheckboxTreeViewer treeViewer = super.createTreeViewer(parent);
		ICheckStateProvider checkStateProvider = new ICheckStateProvider() {
			
			@Override
			public boolean isGrayed(Object element) {
				return element instanceof EObject ? !predicate.test(element) : true;
			}
			
			@Override
			public boolean isChecked(Object element) {
				return selectedValues.contains(element);
			}
			
		};
		treeViewer.setCheckStateProvider(checkStateProvider);
		return treeViewer;
	}
}