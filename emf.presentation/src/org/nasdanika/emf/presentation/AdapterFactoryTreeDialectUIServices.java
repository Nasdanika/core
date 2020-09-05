package org.nasdanika.emf.presentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIServices;
import org.eclipse.sirius.ui.business.api.dialect.ExportFormat;
import org.eclipse.sirius.ui.business.api.dialect.ExportResult;
import org.eclipse.sirius.ui.business.api.dialect.HierarchyLabelProvider;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.RepresentationExtensionDescription;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.nasdanika.common.Util;
import org.nasdanika.sirius.tree.AdapterFactoryTree;
import org.nasdanika.sirius.tree.AdapterFactoryTreeDescription;
import org.nasdanika.sirius.tree.provider.TreeItemProviderAdapterFactory;

/**
 * This class is an adaptation of org.eclipse.sirius.tree.ui.business.internal.dialect.TreeDialectUIServices
 * @author Pavel
 *
 */
public class AdapterFactoryTreeDialectUIServices implements DialectUIServices {

    @Override
    public boolean canHandle(DRepresentation representation) {
        return representation instanceof AdapterFactoryTree;
    }

    @Override
    public boolean canHandle(DRepresentationDescriptor representationDescriptor) {
        return representationDescriptor.getDescription() instanceof AdapterFactoryTreeDescription;
    }

    @Override
    public boolean canHandle(final RepresentationDescription representation) {
        return representation instanceof AdapterFactoryTreeDescription;
    }

    @Override
    public boolean canHandle(final RepresentationExtensionDescription description) {
        return false;
    }

    @Override
    public boolean canHandleEditor(IEditorPart editorPart) {
        return editorPart instanceof AdapterFactoryTreeEditor;
    }

    @Override
    public IEditorPart openEditor(Session session, DRepresentation dRepresentation, IProgressMonitor monitor) {
        IEditorPart editorPart = null;
        try {
            monitor.beginTask("Opening tree", 10);
            if (dRepresentation instanceof AdapterFactoryTree) {
//                DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.OPEN_TREE_KEY);
                URI uri = EcoreUtil.getURI(dRepresentation);
                DRepresentationQuery query = new DRepresentationQuery(dRepresentation);
                URI repDescURI = Optional.ofNullable(query.getRepresentationDescriptor()).map(repDesc -> EcoreUtil.getURI(repDesc)).orElse(null);
                SessionEditorInput editorInput = new SessionEditorInput(uri, repDescURI, getEditorName(dRepresentation), session);
                AdapterFactoryTreeDescription description = ((AdapterFactoryTree) dRepresentation).getDescription();
                String editorId = description == null ? AdapterFactoryTreeEditor.ID : description.getEditorId();
                monitor.worked(2);
                monitor.subTask("Opening tree: " + dRepresentation.getName()); //$NON-NLS-1$
                RunnableWithResult<IEditorPart> runnable = new RunnableWithResult.Impl<IEditorPart>() {
                    @Override
                    public void run() {
                        final IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                        try {
                            setResult(page.openEditor(editorInput, editorId));
                        } catch (final PartInitException e) {
                            NasdanikaEditorPlugin.getPlugin().log(new Status(IStatus.ERROR, NasdanikaEditorPlugin.ID, "Error opening editor", e));
                        }
                    }
                };
                EclipseUIUtil.displaySyncExec(runnable);
                monitor.worked(8);
                IEditorPart result = runnable.getResult();
                if (canHandleEditor(result)) {
//                    DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.OPEN_TREE_KEY);
                    editorPart = result;
                }
            }
        } finally {
            monitor.done();
        }
        return editorPart;
    }

    @Override
    public String getEditorName(DRepresentation representation) {
        String editorName = representation.getName();
        if (Util.isBlank(editorName)) {
            editorName = "New adapter factory tree";
        }
        return editorName;
    }

    @Override
    public boolean closeEditor(IEditorPart editorPart, boolean save) {
        boolean result = false;
        if (canHandleEditor(editorPart)) {
            try {
                ((AdapterFactoryTreeEditor) editorPart).close(save);
            } catch (NullPointerException e) {
                NasdanikaEditorPlugin.getPlugin().log(new Status(IStatus.ERROR, NasdanikaEditorPlugin.ID, "Error opening editor", e));
            }
            result = true;
        }
        return result;
    }

    @Override
    public boolean isRepresentationManagedByEditor(DRepresentation representation, IEditorPart editorPart) {
        boolean isRepresentationManagedByEditor = false;
        if (canHandleEditor(editorPart)) {
        	AdapterFactoryTreeEditor adapterFactoryTreeEditor = (AdapterFactoryTreeEditor) editorPart;
            isRepresentationManagedByEditor = adapterFactoryTreeEditor.getRepresentation() != null && adapterFactoryTreeEditor.getRepresentation().equals(representation);
        }
        return isRepresentationManagedByEditor;
    }

    @Override
    public boolean isRepresentationDescriptionManagedByEditor(RepresentationDescription representationDescription, IEditorPart editorPart) {
        if (canHandleEditor(editorPart)) {
        	AdapterFactoryTreeEditor adapterFactoryTreeEditor = (AdapterFactoryTreeEditor) editorPart;
            return EcoreUtil.equals(adapterFactoryTreeEditor.getTreeModel().getDescription(), representationDescription);
        } else {
            return false;
        }
    }

    @Override
    public AdapterFactory createAdapterFactory() {
        final ComposedAdapterFactory factory = new ComposedAdapterFactory();
        factory.addAdapterFactory(new TreeItemProviderAdapterFactory());
        return factory;
    }

    @Override
    public boolean canExport(ExportFormat format) {
        return false;
    }

    @Override
    public ExportResult exportWithResult(DRepresentation representation, Session session, IPath path, ExportFormat format, IProgressMonitor monitor) {
        // Nothing to do for trees.
        return new ExportResult(representation, Collections.emptySet());
    }

    @Override
    public Collection<CommandParameter> provideNewChildDescriptors() {
        Collection<CommandParameter> newChildren = new ArrayList<>();
        AdapterFactoryTreeDescription treeDescription = org.nasdanika.sirius.tree.TreeFactory.eINSTANCE.createAdapterFactoryTreeDescription();
        newChildren.add(new CommandParameter(null, DescriptionPackage.Literals.VIEWPOINT__OWNED_REPRESENTATIONS, treeDescription));
        return newChildren;
    }

    @Override
    public Collection<CommandParameter> provideRepresentationCreationToolDescriptors(Object feature) {
        Collection<CommandParameter> newChildren = new ArrayList<>();
//        TreeCreationDescription treeCreationDescription = DescriptionFactory.eINSTANCE.createTreeCreationDescription();
//        new TreeToolVariables().doSwitch(treeCreationDescription);
//        newChildren.add(new CommandParameter(null, feature, treeCreationDescription));
        return newChildren;
    }

    @Override
    public Collection<CommandParameter> provideRepresentationNavigationToolDescriptors(Object feature) {
        Collection<CommandParameter> newChildren = new ArrayList<>();
//        TreeNavigationDescription treeNavigationDescription = DescriptionFactory.eINSTANCE.createTreeNavigationDescription();
//        new TreeToolVariables().doSwitch(treeNavigationDescription);
//        newChildren.add(new CommandParameter(null, feature, treeNavigationDescription));
        return newChildren;
    }

    @Override
    public Collection<CommandParameter> provideTools(EObject object) {
        return Collections.emptyList();
    }

    @Override
    public Collection<CommandParameter> provideAdditionalMappings(EObject object) {
        return Collections.emptyList();
    }

    @Override
    public ILabelProvider getHierarchyLabelProvider(ILabelProvider currentLabelProvider) {
        return new HierarchyLabelProvider(currentLabelProvider);
    }

    @Override
    public void setSelection(DialectEditor dialectEditor, List<DRepresentationElement> selection) {
        if (dialectEditor instanceof AdapterFactoryTreeEditor) {
            Viewer viewer = ((AdapterFactoryTreeEditor) dialectEditor).getViewer();
//            Iterable<DTreeItem> treeElements = Iterables.filter(selection, DTreeItem.class);
//            if (viewer != null) {
//                viewer.setSelection(new StructuredSelection(Lists.newArrayList(treeElements)));
//            }
        }
    }

    @Override
    public void selectAndReveal(DialectEditor dialectEditor, List<DRepresentationElement> selection) {
        setSelection(dialectEditor, selection);
    }

    @Override
    public Collection<DSemanticDecorator> getSelection(DialectEditor editor) {
        Collection<DSemanticDecorator> selection = new LinkedHashSet<>();
        if (editor instanceof AdapterFactoryTreeEditor) {
        	AdapterFactoryTreeEditor dEditor = (AdapterFactoryTreeEditor) editor;
            if (editor.getSite() != null && editor.getSite().getSelectionProvider() != null) {
                ISelection sel = dEditor.getSite().getSelectionProvider().getSelection();
                if (sel instanceof IStructuredSelection) {
//                    Iterables.addAll(selection, Iterables.filter(((IStructuredSelection) sel).toList(), DSemanticDecorator.class));
                }
            }
        }
        return selection;
    }

    @Override
    public String completeToolTipText(String toolTipText, EObject eObject, EStructuralFeature feature) {
        return toolTipText;
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.ui.business.api.dialect.DialectUIServices#completeToolTipText(String, EObject)
     * @deprecated this method has not access to the feature of eObject. This is supported in
     *             org.eclipse.sirius.tree.ui.business.internal.dialect
     *             .TreeDialectUIServices.completeToolTipText(String, EObject, EStructuralFeature)
     */
    @Deprecated
    @Override
    public String completeToolTipText(String toolTipText, EObject eObject) {
        return toolTipText;
    }

    @Override
    public void refreshEditor(DialectEditor dialectEditor, IProgressMonitor monitor) {
        if (dialectEditor instanceof AdapterFactoryTreeEditor) {
            final AdapterFactoryTreeEditor treeEditor = (AdapterFactoryTreeEditor) dialectEditor;
//            TreeRefresherHelper.refreshEditor(treeEditor, new StructuredSelection(), monitor);
        }
    }

}
