package org.nasdanika.emf.presentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.nasdanika.common.Reference;
import org.nasdanika.common.Util;
import org.nasdanika.emf.Palette;
import org.nasdanika.emf.edit.NasdanikaItemProviderAdapter;

public class InitialObjectCreationPage extends WizardPage {
	
	private ILabelProvider labelProvider;
	private ITreeContentProvider contentProvider;
	private ComposedAdapterFactory adapterFactory;
	private Object value;	
	private List<InitialObjectConfigurator> configurators = new ArrayList<>();

	private static final String WIZARD_EXTENSION_POINT_ID = "org.nasdanika.emf.presentation.wizard";
	
	/**
	 * @generated
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	protected List<String> encodings;
	private Tree tree;
	private Label elementDescriptionLabel;
	private Collection<Palette> palettes;

	/**
	 * Pass in the selection.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public InitialObjectCreationPage(String pageId) {
		super(pageId);
		
		configurators = new ArrayList<>(); 
		for (IConfigurationElement ce: Platform.getExtensionRegistry().getConfigurationElementsFor(WIZARD_EXTENSION_POINT_ID)) {
			if ("configurator".equals(ce.getName())) {
				
				try {
					InitialObjectConfigurator configurator = (InitialObjectConfigurator) ce.createExecutableExtension("class");
					configurators.add(configurator);
					for (IConfigurationElement parameter: ce.getChildren("parameter")) {
						configurator.addParameter(parameter.getAttribute("name"), parameter.getAttribute("value"));
					}
				} catch (Exception e) {
					NasdanikaEditorPlugin.INSTANCE.log(e);
				}
			}
		}
		
		adapterFactory = NasdanikaEditorPlugin.createVinciAdapterFactory();
		
		palettes = Palette.Registry.INSTANCE.getPalettes().stream().sorted((a,b) -> a.getName().compareTo(b.getName())).collect(Collectors.toList());
		
		contentProvider = new ITreeContentProvider() {
			
			@Override
			public boolean hasChildren(Object element) {
				if (element == palettes) {
					return !palettes.isEmpty();
				}
				if (element instanceof Palette) {
					return !((Palette) element).getElements().isEmpty();
				}
				return false;
			}
			
			@Override
			public Object getParent(Object element) {
				for (Palette palette: palettes) {
					if (palette == element) {
						return palettes;
					}
					if (palette.getElements().contains(element)) {
						return palette;
					}
				}
				
				return null;
			}
			
			@Override
			public Object[] getElements(Object inputElement) {
				return getChildren(inputElement);
			}
			
			@Override
			public Object[] getChildren(Object parentElement) {
				if (parentElement == palettes) {
					return palettes.toArray();
				}
				
				if (parentElement instanceof Palette) {
					return ((Palette) parentElement).getElements().toArray();
				}
				
				return new Object[0];
			}
		}; 
		
		labelProvider = new AdapterFactoryLabelProvider(adapterFactory) {
			
			@Override
			public Image getImage(Object object) {
				if (object instanceof Palette) {
					return ExtendedImageRegistry.INSTANCE.getImage(NasdanikaEditorPlugin.INSTANCE.getImage("full/obj16/Palette.png"));
				}
				return super.getImage(object);
			};
			
			@Override
			public String getText(Object object) {
				if (object instanceof Palette) {
					return ((Palette) object).getName();
				}
				return super.getText(object);
			};
			
		};
		
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		{
			GridLayout layout = new GridLayout();
			layout.verticalSpacing = 12;
			composite.setLayout(layout);

			GridData data = new GridData();
			data.verticalAlignment = GridData.FILL;
			data.grabExcessVerticalSpace = true;
			data.horizontalAlignment = GridData.FILL;
			composite.setLayoutData(data);
		}

		Label filterLabel = new Label(composite, SWT.LEFT);
		{
			filterLabel.setText(NasdanikaEditorPlugin.INSTANCE.getString("_UI_Filter"));

			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			filterLabel.setLayoutData(data);
		}
		
		Text filterText = new Text(composite, SWT.BORDER);
		filterText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label modelLabel = new Label(composite, SWT.LEFT);
		{
			modelLabel.setText(NasdanikaEditorPlugin.INSTANCE.getString("_UI_ModelObject"));

			GridData data = new GridData();
			data.horizontalAlignment = GridData.FILL;
			modelLabel.setLayoutData(data);
		}

		TreeViewer treeViewer = new TreeViewer(composite, SWT.BORDER);
		tree = treeViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Reference<String> filterTextRef = new Reference<>();
		
		IObservableValue<String> textModifyObservable = WidgetProperties.text(SWT.Modify).observeDelayed(700, filterText);
		IValueChangeListener<String> listener = new IValueChangeListener<String>() {
			
			@Override
			public void handleValueChange(ValueChangeEvent<? extends String> event) {
				String value = event.getObservableValue().getValue();
				filterTextRef.set(value);
				treeViewer.refresh();
			}
			
		};
		textModifyObservable.addValueChangeListener(listener);
		
		ViewerFilter filter = new ViewerFilter() {
		
			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				String fText = filterTextRef.get();
				if (Util.isBlank(fText)) {
					return true;
				}
				
				if (element == null) {
					return false;
				}
				
				String eText = labelProvider.getText(element);
				if (eText == null || fText == null || eText.toLowerCase().contains(fText.trim().toLowerCase())) {
					return true;
				}
				
				for (Object child: contentProvider.getChildren(element)) {
					if (select(viewer, element, child)) {
						return true;
					}
				}
				
				return false;
			}
			
		};
		
		treeViewer.addFilter(filter);
		treeViewer.addSelectionChangedListener(event -> {
			ISelection selection = event.getSelection();
			if (selection instanceof IStructuredSelection) {
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				if (structuredSelection.size() == 1) {
					setValue(structuredSelection.getFirstElement());
				}
			}
		});
				
		Group grpDescription = new Group(composite, SWT.NONE);
		grpDescription.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_grpDescription = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_grpDescription.heightHint = 50;
		grpDescription.setLayoutData(gd_grpDescription);
		grpDescription.setText("Description");
		
		elementDescriptionLabel = new Label(grpDescription, SWT.WRAP);
		elementDescriptionLabel.setText("");
		
		treeViewer.setContentProvider(contentProvider);
		treeViewer.setLabelProvider(labelProvider);
		treeViewer.setInput(palettes);
						
		setPageComplete(validatePage());
		setControl(composite);
	}
	
	private void setValue(Object selected) {
		value = selected;
		if (value instanceof Palette) {
			String descr = ((Palette) value).getDescription();
			elementDescriptionLabel.setText(Util.isBlank(descr) ? "" : descr);
		} else if (value instanceof EObject) {
			Adapter adapter = adapterFactory.adapt((EObject) value, IEditingDomainItemProvider.class);
			if (adapter instanceof NasdanikaItemProviderAdapter) {
				String tooltip = ((NasdanikaItemProviderAdapter) adapter).getTooltip(((EObject) value).eClass());
				elementDescriptionLabel.setText(Util.isBlank(tooltip) ? "" : tooltip);				
			} else {
				elementDescriptionLabel.setText("");
			}
		}
		setPageComplete(validatePage());		
	}

	protected boolean validatePage() {
		return value instanceof EObject;
	}
	
	@Override
	public boolean canFlipToNextPage() {
		return !getValueConfigurators().isEmpty();
	}
	
	public List<InitialObjectConfigurator> getValueConfigurators() {
		if (value instanceof EObject) {
			return configurators.stream().filter(c -> c.accept((EObject) value)).sorted((a,b) -> a.getLabel().compareTo(b.getLabel())).collect(Collectors.toList());			
		}
		
		return Collections.emptyList();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible) {
			tree.setFocus();
		}
	}

	EObject createInitialModel() {
		if (value instanceof EObject) {
			return EcoreUtil.copy((EObject) value);
		}
		return null;
	}
}
