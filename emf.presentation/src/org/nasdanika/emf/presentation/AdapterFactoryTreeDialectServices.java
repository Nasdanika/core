package org.nasdanika.emf.presentation;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.dialect.AbstractRepresentationDialectServices;
import org.eclipse.sirius.business.api.dialect.description.DefaultInterpretedExpressionQuery;
import org.eclipse.sirius.business.api.dialect.description.IInterpretedExpressionQuery;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.nasdanika.sirius.tree.AdapterFactoryTree;
import org.nasdanika.sirius.tree.AdapterFactoryTreeDescription;
import org.nasdanika.sirius.tree.TreeFactory;

/**
 * This class is an adaptation of org.eclipse.sirius.table.business.internal.dialect.TableDialectServices
 * @author Pavel
 *
 */
public class AdapterFactoryTreeDialectServices extends AbstractRepresentationDialectServices {

    @Override
    protected boolean isSupported(DRepresentation representation) {
        return representation instanceof AdapterFactoryTree;
    }

    @Override
    protected boolean isSupported(DRepresentationDescriptor representationDescriptor) {
        return representationDescriptor.getDescription() instanceof AdapterFactoryTreeDescription;
    }

    @Override
    protected boolean isSupported(RepresentationDescription description) {
        return description instanceof AdapterFactoryTreeDescription;
    }

    /**
     * This representation works for any domain class with containment references.
     */
    @Override
    public boolean canCreate(EObject semantic, RepresentationDescription desc, boolean checkSelectedViewpoint) {
        if (semantic != null && isSupported(desc)) {
            Session session = new EObjectQuery(semantic).getSession();
            if (session == null || (checkSelectedViewpoint && isRelatedViewpointSelected(session, desc)) || !checkSelectedViewpoint) {
            	for (EReference ref: semantic.eClass().getEAllReferences()) {
            		if (ref.isContainment()) {
            			return true;
            		}
            	}
            }
        }
        return false;
    }

    @Override
    public DRepresentation createRepresentation(String name, EObject semantic, RepresentationDescription description, IProgressMonitor monitor) {
        try {
            monitor.beginTask("Creating adapter factory tree " + name, 11);
            monitor.subTask("Creating adapter factory tree " + name);
            AdapterFactoryTree tree = TreeFactory.eINSTANCE.createAdapterFactoryTree();
//            tree.setName(name);
            tree.setTarget(semantic);
            tree.setDescription((AdapterFactoryTreeDescription) description);
            monitor.worked(1);

            refresh(tree, SubMonitor.convert(monitor, 10));
            return tree;
        } finally {
            monitor.done();
        }
    }

    @Override
    public void refresh(final DRepresentation representation, final boolean fullRefresh, final IProgressMonitor monitor) {
//        try {
//            monitor.beginTask("Refreshing tree", 1);
//            AdapterFactoryTree tree = (AdapterFactoryTree) representation;
//            ModelAccessor accessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(representation);
//            AdapterFactoryTreeDescription description = tree.getDescription();
//            // TODO if needed
////            DTableSynchronizer sync = new DTableSynchronizerImpl(description, accessor, interpreter);
////            sync.setTable(table);
////            sync.refresh(new SubProgressMonitor(monitor, 1));
//        } finally {
//            monitor.done();
//        }
    }

    @Override
    public RepresentationDescription getDescription(DRepresentation representation) {
        if (isSupported(representation)) {
            return ((AdapterFactoryTree) representation).getDescription();
        } else {
            return null;
        }
    }

    @Override
    public void initRepresentations(Viewpoint vp, EObject semantic, IProgressMonitor monitor) {
        super.initRepresentations(semantic, vp, AdapterFactoryTreeDescription.class, monitor);
    }

    @Override
    protected <T extends RepresentationDescription> void initRepresentationForElement(T representationDescription, EObject semanticElement, IProgressMonitor monitor) {
    	// NOP
    }
//
//    @Override
//    public DRepresentation copyRepresentation(DRepresentation representation, String name, Session session, IProgressMonitor monitor) {
//        DRepresentation newRepresentation = super.copyRepresentation(representation, name, session, monitor);
//        session.getServices().putCustomData(CustomDataConstants.DREPRESENTATION, ((DSemanticDecorator) representation).getTarget(), newRepresentation);
//        return newRepresentation;
//    }

    @Override
    public IInterpretedExpressionQuery createInterpretedExpressionQuery(EObject target, EStructuralFeature feature) {
        return new DefaultInterpretedExpressionQuery(target, feature);
    }

    @Override
    public boolean handles(RepresentationDescription representationDescription) {
        return representationDescription instanceof AdapterFactoryTreeDescription;
    }

    @Override
    public boolean handles(RepresentationExtensionDescription representationExtensionDescription) {
        return false;
    }

}
