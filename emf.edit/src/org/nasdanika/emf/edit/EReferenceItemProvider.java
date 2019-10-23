package org.nasdanika.emf.edit;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.nasdanika.common.Util;

/**
 * This class is used to create non-model view objects representing containment references. 
 * @author Pavel
 *
 */
public class EReferenceItemProvider 
	extends ItemProviderAdapter
	implements 
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource
{

	private NasdanikaItemProviderAdapter parent;
	private EReference eReference;

	public EReferenceItemProvider(NasdanikaItemProviderAdapter parent, EObject target, EReference eReference) {
		super(parent.getAdapterFactory());
		this.parent = parent;
		target.eAdapters().add(this);
		this.eReference = eReference;
	}
	
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		for (EObject child: NasdanikaItemProviderAdapter.collectTypes((EObject) object, eReference.getEReferenceType())) {
			if (parent.accept((EObject) object, eReference, child)) {					
				EReferencePredicate eReferencePredicate = (EReferencePredicate) getRootAdapterFactory().adapt(child, EReferencePredicate.class);
				if (eReferencePredicate == null || eReferencePredicate.accept((EObject) object, eReference, child)) {
					newChildDescriptors.add(createChildParameter(eReference, child));			
				}
			}
		}		
	}
	
	@Override
	public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling) {
		return Collections.emptyList(); // super.getNewChildDescriptors(target, editingDomain, sibling); - Create through parent for now.
	}		
	
	@Override
	protected ResourceLocator getResourceLocator() {
		return parent.getResourceLocator();
	}
	
	@Override
	protected Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(eReference);
		}
		return childrenFeatures;
	}
	
	@Override
	public String getText(Object object) {
		return Util.nameToLabel(eReference.getName());
	}
	
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, EReferenceItemProvider.class.getResource("Folder.gif"));
	}
	
	@Override
	public Collection<?> getChildren(Object object) {
		return super.getChildren(target);
	}
	
	@Override
	public Object getParent(Object object) {
		return target;
	}
	
	@Override
	protected Command createAddCommand(
			EditingDomain domain, 
			EObject owner, 
			EStructuralFeature feature,
			Collection<?> collection, 
			int index) {
		return wrap(super.createAddCommand(domain, owner, feature, collection, index), owner);
	}
	
	@Override
	protected Command createRemoveCommand(
			EditingDomain domain, 
			EObject owner, 
			EStructuralFeature feature,
			Collection<?> collection) {
		return wrap(super.createRemoveCommand(domain, owner, feature, collection), owner);
	}

	protected Command wrap(Command command, EObject owner) {	
		return new CommandWrapper(command) {
			@Override
			public Collection<?> getAffectedObjects() {
				Collection<?> affected = super.getAffectedObjects();
				if (affected.contains(owner)) {
					return Collections.singleton(EReferenceItemProvider.this);
				}
				return affected;
			}
		};
	}
	
	@Override
	protected Command createDragAndDropCommand(
			EditingDomain domain, 
			Object owner, 
			float location, 
			int operations,
			int operation, 
			Collection<?> collection) {
		
		if (owner instanceof EReferenceItemProvider) {
			owner = ((EReferenceItemProvider) owner).target;
		}
		if (new AddCommand(domain, (EObject) owner, eReference, collection).canExecute()) {
			return super.createDragAndDropCommand(domain, owner, location, operations, operation, collection);
		}
		
		return UnexecutableCommand.INSTANCE;
	}
	
	public EReference geteReference() {
		return eReference;
	}

}
