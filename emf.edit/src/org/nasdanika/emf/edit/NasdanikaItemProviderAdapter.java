package org.nasdanika.emf.edit;

import java.util.Locale;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.jsoup.Jsoup;
import org.nasdanika.common.Context;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.emf.LocaleLanguageResourceLocator;
import org.nasdanika.emf.localization.RussianResourceLocator;

/**
 * Item provider adapter which loads feature labels and descriptions from the model annotation supporting localizations, and also simplifies customization of 
 * cell editor dialogs. 
 * @author Pavel
 *
 */
public class NasdanikaItemProviderAdapter extends ItemProviderAdapter {
	
	protected MarkdownHelper markdownHelper = new MarkdownHelper();

	protected NasdanikaItemProviderAdapter(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	protected String getDescription(EModelElement modelElement) {
		String markdown = getResourceLocator(modelElement).getString("documentation", EcoreUtil.getDocumentation(modelElement));

		if (markdown == null || markdown.trim().isEmpty()) {
			return null;
		}
	
		return markdownHelper.markdownToHtml(markdown);						
	}
	
	protected String getLabel(ENamedElement modelElement, String defaultLabel) {
		return getResourceLocator(modelElement).getString("label", defaultLabel);
	}
	
	public String getTooltip(EModelElement modelElement) {
		String description = getDescription(modelElement);
		if (description == null || description.trim().isEmpty()) {
			return null;
		}
		String textDoc = Jsoup.parse(description).text();
		return markdownHelper.firstSentence(textDoc);					
	}
	
	protected Context getResourceLocator(EModelElement modelElement) {
		Locale locale = Locale.getDefault();
		if (locale != null) {
			return "ru".equals(locale.getLanguage()) ? new RussianResourceLocator(modelElement) : new LocaleLanguageResourceLocator(modelElement, locale, null);
		}
		return Context.EMPTY_CONTEXT;
	}

	protected MultiReferenceDialogCellEditorFactory createMultiReferenceDialogCellEditorFactory() {
		return new MultiReferenceDialogCellEditorFactory(null, ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory());
	}
	
	protected SingleReferenceDialogCellEditorFactory createSingleReferenceDialogCellEditorFactory() {
		return new SingleReferenceDialogCellEditorFactory(null, ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory());
	}
	

	
}
