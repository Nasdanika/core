/**
 */
package org.nasdanika.emf.presentation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.ControlAction;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.edit.ui.action.CreateSiblingAction;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.emf.edit.ui.provider.DiagnosticDecorator;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;

/**
 * Action bar contributor which groups create children actions by feature.
 */
public class NasdanikaActionBarContributor extends EditingDomainActionBarContributor implements ISelectionChangedListener {
	
	/**
	 * This keeps track of the active editor. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected IEditorPart activeEditorPart;

	/**
	 * This keeps track of the current selection provider. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ISelectionProvider selectionProvider;

	/**
	 * This action opens the Properties view. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected IAction showPropertiesViewAction = new Action(
			NasdanikaEditorPlugin.INSTANCE.getString("_UI_ShowPropertiesView_menu_item")) {
		@Override
		public void run() {
			try {
				getPage().showView("org.eclipse.ui.views.PropertySheet");
			} catch (PartInitException exception) {
				NasdanikaEditorPlugin.INSTANCE.log(exception);
			}
		}
	};

	/**
	 * This action refreshes the viewer of the current editor if the editor
	 * implements {@link org.eclipse.emf.common.ui.viewer.IViewerProvider}. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IAction refreshViewerAction = new Action(
			NasdanikaEditorPlugin.INSTANCE.getString("_UI_RefreshViewer_menu_item")) {
		@Override
		public boolean isEnabled() {
			return activeEditorPart instanceof IViewerProvider;
		}

		@Override
		public void run() {
			if (activeEditorPart instanceof IViewerProvider) {
				Viewer viewer = ((IViewerProvider) activeEditorPart).getViewer();
				if (viewer != null) {
					viewer.refresh();
				}
			}
		}
	};

	protected Map<String, Object> createChildActions;

	/**
	 * This is the menu manager into which menu contribution items should be added
	 * for CreateChild actions. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IMenuManager createChildMenuManager;

	protected Map<String, Object> createSiblingActions;

	/**
	 * This is the menu manager into which menu contribution items should be added
	 * for CreateSibling actions. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IMenuManager createSiblingMenuManager;

	/**
	 * @param plugin provides string resources
	 * @param menuId Menu id, e.g. 
	 */
	public NasdanikaActionBarContributor() {
		super(ADDITIONS_LAST_STYLE);
		loadResourceAction = new LoadResourceAction();
		validateAction = new ValidateAction();
		liveValidationAction = new DiagnosticDecorator.LiveValidator.LiveValidationAction(NasdanikaEditorPlugin.getPlugin().getDialogSettings());
		controlAction = new ControlAction();
	}

	/**
	 * This adds Separators for editor additions to the tool bar. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		super.contributeToToolBar(toolBarManager);
		toolBarManager.add(new Separator("app-settings"));
		toolBarManager.add(new Separator("app-additions"));
	}

	/**
	 * This adds to the menu bar a menu and some separators for editor additions, as
	 * well as the sub-menus for object creation items. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void contributeToMenu(IMenuManager menuManager) {
		super.contributeToMenu(menuManager);

		IMenuManager submenuManager = new MenuManager(NasdanikaEditorPlugin.INSTANCE.getString("_UI_NasdanikaEditor_menu"), "org.nasdanika.emf.editor.appMenuID");
		menuManager.insertAfter("additions", submenuManager);
		submenuManager.add(new Separator("settings"));
		submenuManager.add(new Separator("actions"));
		submenuManager.add(new Separator("additions"));
		submenuManager.add(new Separator("additions-end"));

		// Prepare for CreateChild item addition or removal.
		//
		createChildMenuManager = new MenuManager(NasdanikaEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item"));
		submenuManager.insertBefore("additions", createChildMenuManager);

		// Prepare for CreateSibling item addition or removal.
		//
		createSiblingMenuManager = new MenuManager(NasdanikaEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item"));
		submenuManager.insertBefore("additions", createSiblingMenuManager);

		// Force an update because Eclipse hides empty menus now.
		//
		submenuManager.addMenuListener(new IMenuListener() {
			@Override
			public void menuAboutToShow(IMenuManager menuManager) {
				menuManager.updateAll(true);
			}
		});

		addGlobalActions(submenuManager);
	}

	/**
	 * When the active editor changes, this remembers the change and registers with
	 * it as a selection provider. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setActiveEditor(IEditorPart part) {
		super.setActiveEditor(part);
		activeEditorPart = part;

		// Switch to the new selection provider.
		//
		if (selectionProvider != null) {
			selectionProvider.removeSelectionChangedListener(this);
		}
		if (part == null) {
			selectionProvider = null;
		} else {
			selectionProvider = part.getSite().getSelectionProvider();
			selectionProvider.addSelectionChangedListener(this);

			// Fake a selection changed event to update the menus.
			//
			if (selectionProvider.getSelection() != null) {
				selectionChanged(new SelectionChangedEvent(selectionProvider, selectionProvider.getSelection()));
			}
		}
	}

	/**
	 * This implements {@link org.eclipse.jface.viewers.ISelectionChangedListener},
	 * handling {@link org.eclipse.jface.viewers.SelectionChangedEvent}s by querying
	 * for the children and siblings that can be added to the selected object and
	 * updating the menus accordingly. <!-- begin-user-doc --> <!-- end-user-doc -->
	 */
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		// Remove any menu items for old selection.
		//
		if (createChildMenuManager != null) {
			depopulateManager(createChildMenuManager, createChildActions);
		}
		if (createSiblingMenuManager != null) {
			depopulateManager(createSiblingMenuManager, createSiblingActions);
		}

		// Query the new selection for appropriate new child/sibling descriptors
		//
		Collection<?> newChildDescriptors = null;
		Collection<?> newSiblingDescriptors = null;

		ISelection selection = event.getSelection();
		if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() == 1) {
			Object object = ((IStructuredSelection) selection).getFirstElement();
			EditingDomain domain = ((IEditingDomainProvider) activeEditorPart).getEditingDomain();

			newChildDescriptors = domain.getNewChildDescriptors(object, null);
			newSiblingDescriptors = domain.getNewChildDescriptors(null, object);
		}

		// Generate actions for selection; populate and redraw the menus.
		//
		createChildActions = groupActions(generateCreateChildActions(newChildDescriptors, selection));
		createSiblingActions = groupActions(generateCreateSiblingActions(newSiblingDescriptors, selection));

		if (createChildMenuManager != null) {
			populateManager(createChildMenuManager, createChildActions, null);
			createChildMenuManager.update(true);
		}
		if (createSiblingMenuManager != null) {
			populateManager(createSiblingMenuManager, createSiblingActions, null);
			createSiblingMenuManager.update(true);
		}
	}

	/**
	 * This generates a {@link org.eclipse.emf.edit.ui.action.CreateChildAction} for
	 * each object in <code>descriptors</code>, and returns the collection of these
	 * actions. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Collection<IAction> generateCreateChildActions(Collection<?> descriptors, ISelection selection) {
		Collection<IAction> actions = new ArrayList<IAction>();
		if (descriptors != null) {
			for (Object descriptor : descriptors) {
				actions.add(new CreateChildAction(activeEditorPart, selection, descriptor));
			}
		}
		return actions;
	}

	/**
	 * This generates a {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction}
	 * for each object in <code>descriptors</code>, and returns the collection of
	 * these actions. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Collection<IAction> generateCreateSiblingActions(Collection<?> descriptors, ISelection selection) {
		Collection<IAction> actions = new ArrayList<IAction>();
		if (descriptors != null) {
			for (Object descriptor : descriptors) {
				actions.add(new CreateSiblingAction(activeEditorPart, selection, descriptor));
			}
		}
		return actions;
	}

	/**
	 * Organizes actions into groups using | separator in action name. 
	 * Then "squashes" single-entry groups. For groups with a single leaf action actions get the name of the containing group and replace it in the parent. 
	 */
	protected Map<String, Object> groupActions(Collection<IAction> createActions) {
		Map<String, Object> group = new TreeMap<>();
		if (createActions != null) {
			for (IAction action: createActions) {
				groupAction(action, group);
			}
		}
		
		return collapseGroups(group);
	}
	
	@SuppressWarnings("unchecked")
	private static void groupAction(IAction action, Map<String, Object> group) {
		String actionText = action.getText();
		int idx = actionText.indexOf("|");
		if (idx == -1) {
			if (group.containsKey(actionText)) {
				//throw new IllegalStateException("Duplicate entry name: "+actionText);
				System.err.println("Duplicate entry name: "+actionText);
			} else {
				group.put(actionText, action);
			}
		} else {
			String subGroupName = actionText.substring(0, idx);
			Object obj = group.get(subGroupName);			
			if (obj == null) {
				Map<String, Object> subGroup = new TreeMap<>();
				group.put(subGroupName, subGroup);
				action.setText(actionText.substring(idx + 1));
				groupAction(action, subGroup);
			} else if (obj instanceof Map) {
				action.setText(actionText.substring(idx + 1));
				groupAction(action, (Map<String, Object>) obj);				
			} else {
				throw new IllegalStateException("Duplicate entry name: "+subGroupName);				
			}
		}		
	}
	
	@SuppressWarnings("unchecked")
	private static Map<String, Object> collapseGroups(Map<String, Object> group) {
		for (Entry<String, Object> e: group.entrySet()) {
			if (e.getValue() instanceof Map) {				
				Map<String,Object> subGroup = (Map<String,Object>) e.getValue();
				if (!subGroup.isEmpty()) {
					Object firstElement = subGroup.values().iterator().next();
					if (subGroup.size() == 1 && firstElement instanceof IAction) {
						// Renaming action and replacing subgroup with the action.
						IAction action = (IAction) firstElement;
						action.setToolTipText(action.getText());
						action.setText(e.getKey());
						e.setValue(firstElement);
					} else {
						e.setValue(collapseGroups(subGroup));
					}
				}
			}
		}
		if (group.size() == 1) {
			Object value = group.values().iterator().next();
			if (value instanceof Map) {
				return (Map<String,Object>) value;
			}
		}
		return group;
	}
	

	/**
	 * This populates the specified <code>manager</code> with
	 * {@link org.eclipse.jface.action.MenuManager}s containing
	 * {@link org.eclipse.jface.action.ActionContributionItem}s based on the
	 * {@link org.eclipse.jface.action.IAction}s contained in the
	 * <code>submenuActions</code> collection, by inserting them before the
	 * specified contribution item <code>contributionID</code>. If
	 * <code>contributionID</code> is <code>null</code>, they are simply added. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 */
	@SuppressWarnings("unchecked")
	protected void populateManager(IContributionManager manager, Map<String, Object> items, String contributionID) {
		if (items != null) {
			for (Map.Entry<String, Object> entry : items.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				if (value instanceof Map) {
					MenuManager submenuManager = new MenuManager(entry.getKey());
					if (contributionID != null) {
						manager.insertBefore(contributionID, submenuManager);
					} else {
						manager.add(submenuManager);
					}
					populateManager(submenuManager, (Map<String,Object>) value, null);					
				} else if (value instanceof IAction) {
					IAction action = (IAction) value;
					if (contributionID != null) {
						manager.insertBefore(contributionID, action);
					} else {
						manager.add(action);
					}					
				} else {
					throw new IllegalArgumentException("Unexpected item type at "+key+": "+value);
				}
			}
		}
	}

	/**
	 * This removes from the specified <code>manager</code> all
	 * {@link org.eclipse.jface.action.MenuManager}s and their
	 * {@link org.eclipse.jface.action.ActionContributionItem}s based on the
	 * {@link org.eclipse.jface.action.IAction}s contained in the
	 * <code>submenuActions</code> map. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 */
	@SuppressWarnings("unchecked")
	protected void depopulateManager(IContributionManager manager, Map<String, Object> items) {
		if (items != null) {
			IContributionItem[] managerItems = manager.getItems();
			for (int i = 0; i < managerItems.length; i++) {
				IContributionItem contributionItem = managerItems[i];
				if (contributionItem instanceof MenuManager) {
					MenuManager submenuManager = (MenuManager) contributionItem;
					String menuText = submenuManager.getMenuText();
					Object subItem = items.get(menuText);
					if (subItem instanceof Map) {
						depopulateManager(submenuManager, (Map<String,Object>) subItem);
						manager.remove(contributionItem);
					}
				} else {
					while (contributionItem instanceof SubContributionItem) {
						contributionItem = ((SubContributionItem) contributionItem).getInnerItem();
					}

					// Delete the ActionContributionItems with matching action.
					//
					if (contributionItem instanceof ActionContributionItem) {
						IAction action = ((ActionContributionItem) contributionItem).getAction();
						if (items.containsKey(action.getText())) {
							manager.remove(contributionItem);
						}
					}					
				}
			}
		}
	}
	
	/**
	 * This populates the pop-up menu before it appears. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	@Override
	public void menuAboutToShow(IMenuManager menuManager) {
		super.menuAboutToShow(menuManager);
		MenuManager submenuManager = null;

		submenuManager = new MenuManager(NasdanikaEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item"));
		populateManager(submenuManager, createChildActions, null);
		menuManager.insertBefore("edit", submenuManager);

		submenuManager = new MenuManager(NasdanikaEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item"));
		populateManager(submenuManager, createSiblingActions, null);
		menuManager.insertBefore("edit", submenuManager);
	}

	/**
	 * This inserts global actions before the "additions-end" separator. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected void addGlobalActions(IMenuManager menuManager) {
		menuManager.insertAfter("additions-end", new Separator("ui-actions"));
		menuManager.insertAfter("ui-actions", showPropertiesViewAction);

		refreshViewerAction.setEnabled(refreshViewerAction.isEnabled());
		menuManager.insertAfter("ui-actions", refreshViewerAction);

		super.addGlobalActions(menuManager);
	}

	/**
	 * This ensures that a delete action will clean up all references to deleted
	 * objects. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected boolean removeAllReferencesOnDelete() {
		return true;
	}

}
