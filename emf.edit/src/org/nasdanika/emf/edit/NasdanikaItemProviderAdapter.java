package org.nasdanika.emf.edit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
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
	
	@Override
  protected ItemPropertyDescriptor createItemPropertyDescriptor(
		    AdapterFactory adapterFactory,
		    ResourceLocator resourceLocator,
		    String displayName,
		    String description,
		    EStructuralFeature feature,
		    boolean isSettable,
		    boolean multiLine,
		    boolean sortChoices,
		    Object staticImage,
		    String category,
		    String[] filterFlags)
		  {			
		    Object cellEditorFactory =  null;
		    if (feature instanceof EReference) {
		    	cellEditorFactory = feature.isMany() ? createMultiReferenceDialogCellEditorFactory() : createSingleReferenceDialogCellEditorFactory();
		    }
			return createItemPropertyDescriptor
		      (adapterFactory,
		       resourceLocator,
		       displayName,
		       description,
		       feature,
		       isSettable,
		       multiLine,
		       sortChoices,
		       staticImage,
		       category,
		       filterFlags,
		       cellEditorFactory);
		  }

	/**
	 * Creates an item property descriptor with description loaded from a model annotation, display name also loaded from a model annotation for localization.
	 * Creates a cell editor factory for {@link EReference} based on isMany().
	 * @param resourceLocator
	 * @param displayName
	 * @param feature
	 * @param isSettable
	 * @param multiLine
	 * @param sortChoices
	 * @param staticImage
	 * @param category
	 * @param filterFlags
	 * @param choiceOfValues Choice of values, the inherited choice of values is used if this one is null.
	 * @return
	 */
	  protected ItemPropertyDescriptor createItemPropertyDescriptor(
			    ResourceLocator resourceLocator,
			    String displayName,
			    EStructuralFeature feature,
			    boolean isSettable,
			    boolean multiLine,
			    boolean sortChoices,
			    Object staticImage,
			    String category,
			    String[] filterFlags, 
			    Collection<Object> choiceOfValues)
			  {			
			    Object cellEditorFactory =  null;
			    if (feature instanceof EReference) {
			    	cellEditorFactory = feature.isMany() ? createMultiReferenceDialogCellEditorFactory() : createSingleReferenceDialogCellEditorFactory();
			    }
				return 
				  new ItemPropertyDescriptor
				    (((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
				     resourceLocator,
				     getLabel(feature, displayName),
				     getTooltip(feature),
				     feature,
				     isSettable,
				     multiLine,
				     sortChoices,
				     staticImage,
				     category,
				     filterFlags,
				     cellEditorFactory) {
					
					@Override
					protected Collection<?> getComboBoxObjects(Object object) {
						return choiceOfValues == null ? super.getComboBoxObjects(object) : choiceOfValues;
					}
					
				};
	}
	  
		
		@Override
		protected boolean shouldComposeCreationImage() {
			return true;
		}
		
		public static boolean isBlank(String str) {
			return str == null || str.trim().length() == 0;
		}
		
		/**
		 * Collects child descriptors from registered ecore packages compatible with the ereference type.
		 * @param newChildDescriptors
		 * @param eReference
		 */
		protected void collectEReferenceChildDescriptors(Collection<Object> newChildDescriptors, EReference eReference) {
			for (EObject child: collectTypes(eReference.getEReferenceType())) {
				newChildDescriptors.add(createChildParameter(eReference, child));			
			}		
		}
		
		/**
		 * Iterates over registered ecore packages. Collects and instantiates concrete subclasses. 
		 * @return
		 */
		public List<EObject> collectTypes(EClass type) {
			AdapterFactory rootAdapterFactory = getRootAdapterFactory();
			List<EObject> ret = new ArrayList<>();
			IExtensionRegistry registry = RegistryFactory.getRegistry();
			if (registry != null) {
				IConfigurationElement[] configElems = registry.getConfigurationElementsFor("org.eclipse.emf.ecore.generated_package");
				for (IConfigurationElement elem : configElems) {
					String uri = elem.getAttribute("uri");
					if (!isBlank(uri)) {
						EPackage epkg = EPackage.Registry.INSTANCE.getEPackage(uri);
						if (epkg != null) {
							for (EClassifier eClassifier: epkg.getEClassifiers()) {
								if (eClassifier instanceof EClass 
										&& !((EClass) eClassifier).isAbstract() 
										&& type.isSuperTypeOf((EClass) eClassifier)
										&& rootAdapterFactory.isFactoryForType(eClassifier)) {
									
									EFactory eFactory = epkg.getEFactoryInstance();
									ret.add(eFactory.create((EClass) eClassifier));						
								}
							}
						}
					}
				}
			}
			return ret;
		}	
	  
	
}
