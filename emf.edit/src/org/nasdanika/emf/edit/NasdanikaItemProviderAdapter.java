package org.nasdanika.emf.edit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
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
public class NasdanikaItemProviderAdapter extends ItemProviderAdapter implements EReferencePredicate {
	
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
	 * @param object TODO
	 * @param newChildDescriptors
	 * @param eReference
	 */
	protected void collectEReferenceChildDescriptors(Object object, Collection<Object> newChildDescriptors, EReference eReference) {
		for (EObject child: collectTypes((EObject) object, eReference.getEReferenceType())) {
			if (accept((EObject) object, eReference, child)) {					
				EReferencePredicate eReferencePredicate = (EReferencePredicate) getRootAdapterFactory().adapt(child, EReferencePredicate.class);
				if (eReferencePredicate == null || eReferencePredicate.accept((EObject) object, eReference, child)) {
					newChildDescriptors.add(createChildParameter(eReference, child));			
				}
			}
		}		
	}
	
	/**
	 * Iterates over registered ecore packages. Collects and instantiates concrete subclasses. 
	 * @return
	 */
	public static List<EObject> collectTypes(EObject object, EClass type) {
//		AdapterFactory rootAdapterFactory = getRootAdapterFactory();
		Registry ePackageRegistry = EPackage.Registry.INSTANCE;
		Resource resource = type.eResource();
		if (resource != null) {
			ResourceSet resourceSet = resource.getResourceSet();
			if (resourceSet != null) {
				ePackageRegistry = resourceSet.getPackageRegistry();
			}
		}		
		List<EObject> ret = new ArrayList<>();
		for (String nsUri: new ArrayList<String>(ePackageRegistry.keySet())) {
			EPackage epkg = ePackageRegistry.getEPackage(nsUri);
			for (EClassifier eClassifier: epkg.getEClassifiers()) {
				if (eClassifier instanceof EClass 
						&& !((EClass) eClassifier).isAbstract() 
						&& type.isSuperTypeOf((EClass) eClassifier)) {
					
					EFactory eFactory = epkg.getEFactoryInstance();
					ret.add(eFactory.create((EClass) eClassifier));						
				}
			}
		}
//		IExtensionRegistry registry = RegistryFactory.getRegistry();
//		if (registry != null) {
//			IConfigurationElement[] configElems = registry.getConfigurationElementsFor("org.eclipse.emf.ecore.generated_package");
//			for (IConfigurationElement elem : configElems) {
//				String uri = elem.getAttribute("uri");
//				if (!isBlank(uri)) {
//					EPackage epkg = ePackageRegistry.getEPackage(uri);
//					if (epkg != null) {
//						for (EClassifier eClassifier: epkg.getEClassifiers()) {
//							if (eClassifier instanceof EClass 
//									&& !((EClass) eClassifier).isAbstract() 
//									&& type.isSuperTypeOf((EClass) eClassifier)
//									&& rootAdapterFactory.isFactoryForType(eClassifier)) {
//
//								
//								EFactory eFactory = epkg.getEFactoryInstance();
//								ret.add(eFactory.create((EClass) eClassifier));						
//							}
//						}
//					}
//				}
//			}
//		}
		return ret;
	}

	@Override
	public boolean accept(EObject source, EReference eReference, EObject target) {
		return true;
	}
	
	/**
	 * Always qualify containment references.
	 */
	@Override
	public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection) {
		if (feature instanceof EReference && ((EReference) feature).isContainment()) {
			return getString("_UI_CreateChild_text2", new Object[] { getTypeText(child), getFeatureText(feature), getTypeText(owner) });
		}
		return super.getCreateChildText(owner, feature, child, selection);
	}
	
	/**
	 * @param eReference
	 * @return EReferenceItemProvider if this item provider groups children by their containment references.
	 */
	protected EReferenceItemProvider getEReferenceItemProvider(EReference eReference) {
		return null;
	}
	
	@Override
	public Object getParent(Object object) {
		Object parent = super.getParent(object);
		if (object instanceof EObject && ((EObject) object).eContainer() != null) {
			Object provider = getAdapterFactory().adapt(parent, IEditingDomainItemProvider.class);
			if (provider instanceof NasdanikaItemProviderAdapter) {
			    EObject eObject = (EObject) object;
			    EReference containmentFeature = eObject.eContainmentFeature();
			    if (containmentFeature != null) {
			    	EReferenceItemProvider containmentFeatureProvider = getEReferenceItemProvider(containmentFeature);
			    	if (containmentFeatureProvider != null) {
			    		return containmentFeatureProvider;
			    	}
			    }
			}
		}
		return parent;
	}
	
	@Override
	protected Command createRemoveCommand(
			EditingDomain domain, 
			EObject owner, 
			EStructuralFeature feature,
			Collection<?> collection) {
		Command removeCommand = super.createRemoveCommand(domain, owner, feature, collection);
		if (feature instanceof EReference) {
			EReferenceItemProvider eReferenceItemProvider = getEReferenceItemProvider((EReference) feature);
			if (eReferenceItemProvider != null) {
				return wrap(removeCommand, owner, eReferenceItemProvider);
			}
		}
		return removeCommand;
	}
	
	@Override
	protected Command createAddCommand(
			EditingDomain domain, 
			EObject owner, 
			EStructuralFeature feature,
			Collection<?> collection, 
			int index) {
		Command addCommand = super.createAddCommand(domain, owner, feature, collection, index);
		if (feature instanceof EReference) {
			EReferenceItemProvider eReferenceItemProvider = getEReferenceItemProvider((EReference) feature);
			if (eReferenceItemProvider != null) {
				return wrap(addCommand, owner, eReferenceItemProvider);
			}
		}		
		return addCommand;
	}
		
	public static Command wrap(Command command, EObject owner, EReferenceItemProvider eReferenceItemProvider) {
		return new CommandWrapper(command) {
			
			@Override
			public Collection<?> getAffectedObjects() {
				Collection<?> affected = super.getAffectedObjects();
				if (affected.contains(owner)) {
					return Collections.singleton(eReferenceItemProvider);
				}
				return affected;
			}
			
		};
	}
	
	protected List<EReferenceItemProvider> eReferenceItemProviders = new ArrayList<>();
	
	@Override
	public void dispose() {
		super.dispose();
		eReferenceItemProviders.forEach(ItemProviderAdapter::dispose);
	}
	
}
