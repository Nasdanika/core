package org.nasdanika.emf.presentation;

import org.eclipse.eef.properties.ui.api.EEFTabbedPropertySheetPage;
import org.eclipse.eef.properties.ui.api.IEEFTabbedPropertySheetPageContributor;
import org.eclipse.sirius.ui.properties.internal.ContributorWrapper;
import org.eclipse.sirius.ui.tools.api.properties.ISiriusPropertySheetPageProvider;
import org.eclipse.ui.views.properties.IPropertySheetPage;

@SuppressWarnings("restriction")
public class NasdanikaPropertySheetPageProvider implements ISiriusPropertySheetPageProvider {

	@Override
	public IPropertySheetPage getPropertySheetPage(Object source, String contributorId) {
		IEEFTabbedPropertySheetPageContributor contributor = new ContributorWrapper(this, contributorId);
		
		return new EEFTabbedPropertySheetPage(contributor);

	}
}
