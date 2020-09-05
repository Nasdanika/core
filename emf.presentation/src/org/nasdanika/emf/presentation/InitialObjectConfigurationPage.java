package org.nasdanika.emf.presentation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class InitialObjectConfigurationPage extends WizardPage {
	
	private InitialObjectCreationPage creationPage;

	public InitialObjectConfigurationPage(String pageId, InitialObjectCreationPage creationPage) {
		super(pageId);				
		this.creationPage = creationPage;
	}
	
	private TabFolder tabFolder;
	
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
		
		tabFolder = new TabFolder(composite, SWT.NONE);
		
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
						
		setPageComplete(validatePage());
		setControl(composite);
	}

	protected boolean validatePage() {
		if (configurators != null) {
			// TODO - iterate, validate
		}
		return true; 
	}
	
	private List<InitialObjectConfigurator> configurators;
	
	private EObject initialModel;
	
	public void setVisible(boolean visible) {
		if (visible) {
			TabItem[] items = tabFolder.getItems();
			if (items != null) {
				for (TabItem item: items) {
					item.dispose();
				}
			}
			
			initialModel = creationPage.createInitialModel();
			configurators = new ArrayList<>();
			
			for (InitialObjectConfigurator configurator: creationPage.getValueConfigurators()) {
				TabItem configuratorItem = new TabItem(tabFolder, SWT.NONE);
				configuratorItem.setText(configurator.getLabel());						
				configuratorItem.setControl(configurator.createControl(tabFolder, initialModel));
				configurators.add(configurator);
			}
		}
		super.setVisible(visible);		
	};

	public EObject getInitialModel() {
		if (initialModel == null) {
			return creationPage.createInitialModel();			
		}
		EObject ret = initialModel;
		if (configurators != null) {
			for (InitialObjectConfigurator configurator: configurators) {
				ret = configurator.configure(ret);
			}
		}
		return ret;
	}
}
