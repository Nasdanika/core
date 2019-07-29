/**
 * Customization of org.eclipse.eef.ide.ui.ext.widgets.reference.internal.AbstractEEFExtReferenceLifecycleManager to be used
 * as a custom widget in order to replace dialogs.
 */
/*******************************************************************************
 * Copyright (c) 2016, 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: Obeo - initial API and implementation
 *******************************************************************************/
package org.nasdanika.eef.ext.widgets.reference;

import java.util.List;

import org.eclipse.eef.EEFCustomWidgetDescription;
import org.eclipse.eef.EEFWidgetDescription;
import org.eclipse.eef.common.ui.api.EEFWidgetFactory;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.eef.core.api.controllers.IEEFWidgetController;
import org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager;
import org.eclipse.eef.ide.ui.api.widgets.EEFStyleHelper;
import org.eclipse.eef.ide.ui.ext.widgets.reference.internal.EEFExtStyleHelper;
import org.eclipse.eef.ide.ui.ext.widgets.reference.internal.Messages;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

/**
 * Common superclass of both the single and multi-valued reference lifecycle managers.
 *
 * @author sbegaudeau
 * @author Pavel Vlasov
 */
@SuppressWarnings("restriction")
public abstract class AbstractNasdanikaExtReferenceLifecycleManager extends AbstractEEFWidgetLifecycleManager {
	/**
	 * The minimum width of the button.
	 */
	private static final int MINIMUM_BUTTON_WIDTH = 80;

	/**
	 * The description.
	 */
	protected final EEFCustomWidgetDescription description;

	/**
	 * The target.
	 */
	protected final EObject target;

	/**
	 * The EReference.
	 */
	protected final EReference eReference;

	/**
	 * The controller.
	 */
	protected ReferenceController controller;

	/**
	 * The composed adapter factory.
	 */
	protected ComposedAdapterFactory composedAdapterFactory;

	/**
	 * The widget factory.
	 */
	protected EEFWidgetFactory widgetFactory;

	/**
	 * The browse button.
	 */
	protected Button browseButton;

	/**
	 * The listener for the browse button.
	 */
	protected ButtonSelectionListener browseButtonListener;

	/**
	 * The add button.
	 */
	protected Button addButton;

	/**
	 * The listener for the add button.
	 */
	protected ButtonSelectionListener addButtonListener;

	/**
	 * The remove button.
	 */
	protected Button removeButton;

	/**
	 * The listener for the remove button.
	 */
	protected ButtonSelectionListener removeButtonListener;

	/**
	 * The constructor.
	 *
	 * @param description
	 *            The description of the reference
	 * @param target
	 *            The target
	 * @param eReference
	 *            The EReference to display
	 * @param variableManager
	 *            The variable manager
	 * @param interpreter
	 *            The interpreter
	 * @param editingContextAdapter
	 *            The context adapter
	 */
	public AbstractNasdanikaExtReferenceLifecycleManager(
			EEFCustomWidgetDescription description, EObject target, EReference eReference,
			IVariableManager variableManager, 
			IInterpreter interpreter, 
			EditingContextAdapter editingContextAdapter) {
		
		super(variableManager, interpreter, editingContextAdapter);
		this.description = description;
		this.target = target;
		this.eReference = eReference;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager#getController()
	 */
	@Override
	protected IEEFWidgetController getController() {
		return this.controller;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager#getWidgetDescription()
	 */
	@Override
	protected EEFWidgetDescription getWidgetDescription() {
		return this.description;
	}

	/**
	 * Creates a button used to edit the reference.
	 *
	 * @param parent
	 *            The parent composite
	 * @param image
	 *            The image of the button
	 * @return The button created
	 */
	protected Button createButton(Composite parent, Image image) {
		Button button = this.widgetFactory.createButton(parent, "", SWT.NONE); //$NON-NLS-1$
		button.setImage(image);

		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.minimumWidth = MINIMUM_BUTTON_WIDTH;
		button.setLayoutData(gridData);

		return button;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager#aboutToBeShown()
	 */
	@Override
	public void aboutToBeShown() {
		super.aboutToBeShown();

		if (!this.eReference.isContainment()) {
			this.initializeBrowseButton();
		}
		this.initializeAddButton();
		this.initializeRemoveButton();
	}

	/**
	 * Initializes the browse button.
	 */
	private void initializeBrowseButton() {
		this.browseButtonListener = new ButtonSelectionListener(this.editingContextAdapter, () -> this.browseButtonCallback());
		this.browseButton.addSelectionListener(this.browseButtonListener);
		this.browseButton.setToolTipText(Messages.ReferenceBrowseButton_tooltipText);
	}

	/**
	 * This method is called once the browse button is clicked to select an existing element from the resource set.
	 */
	protected abstract void browseButtonCallback();

	/**
	 * Initializes the add button.
	 */
	private void initializeAddButton() {
		this.addButtonListener = new ButtonSelectionListener(this.editingContextAdapter, () -> this.addButtonCallback());
		this.addButton.addSelectionListener(this.addButtonListener);
		this.addButton.setToolTipText(Messages.ReferenceAddButton_tooltipText);
	}

	/**
	 * This method is called once the add button is clicked in order to open the "add dialog".
	 */
	protected abstract void addButtonCallback();

	/**
	 * Initializes the remove button.
	 */
	private void initializeRemoveButton() {
		this.removeButtonListener = new ButtonSelectionListener(this.editingContextAdapter, () -> this.removeButtonCallback());
		this.removeButton.addSelectionListener(this.removeButtonListener);
		if (this.eReference.isContainment()) {
			this.removeButton.setToolTipText(Messages.ReferenceRemoveButton_containmentTooltipText);
		} else {
			this.removeButton.setToolTipText(Messages.ReferenceRemoveButton_nonContainmentTooltipText);
		}
	}

	/**
	 * This method is called once the remove button is clicked in order to remove the selected element.
	 */
	protected abstract void removeButtonCallback();

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.api.widgets.AbstractEEFLifecycleManager#refresh()
	 */
	@Override
	public void refresh() {
		super.refresh();

		if (ReferenceLifecycleManagerProvider.isBlank(this.description.getLabelExpression())) {
			Adapter adapter = this.composedAdapterFactory.adapt(this.target, IItemPropertySource.class);
			if (adapter instanceof IItemPropertySource) {
				IItemPropertySource propertySource = (IItemPropertySource) adapter;
				IItemPropertyDescriptor propertyDescriptor = propertySource.getPropertyDescriptor(this.target, this.eReference);
				if (propertyDescriptor != null) {
					String displayName = propertyDescriptor.getDisplayName(this.eReference);
					this.label.setText(displayName);
				} else {
					this.label.setText(this.eReference.getName());
				}
			}
		}
		this.setLabelFontStyle();

	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager#setEnabled(boolean)
	 */
	@Override
	protected void setEnabled(boolean isEnabled) {
		if (this.addButton != null && !this.addButton.isDisposed()) {
			this.addButton.setEnabled(isEnabled);
		}
		if (this.removeButton != null && !this.removeButton.isDisposed()) {
			this.removeButton.setEnabled(isEnabled);
		}
		if (this.browseButton != null && !this.browseButton.isDisposed()) {
			this.browseButton.setEnabled(isEnabled);
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager#isEnabled()
	 */
	@Override
	protected boolean isEnabled() {
		return super.isEnabled() && this.eReference.isChangeable();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager#aboutToBeHidden()
	 */
	@Override
	public void aboutToBeHidden() {
		super.aboutToBeHidden();

		if (!this.eReference.isContainment()) {
			this.removeListener(this.browseButton, this.browseButtonListener);
		}
		this.removeListener(this.addButton, this.addButtonListener);
		this.removeListener(this.removeButton, this.removeButtonListener);
	}

	/**
	 * Removes the given listener from the given button.
	 *
	 * @param button
	 *            The button
	 * @param listener
	 *            The listener to remove
	 */
	protected void removeListener(Button button, ButtonSelectionListener listener) {
		if (!button.isDisposed()) {
			button.removeSelectionListener(listener);
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
		this.composedAdapterFactory.dispose();
	}

	/**
	 * Utility class used to encapsulate the selection listener.
	 *
	 * @author sbegaudeau
	 */
	protected static class ButtonSelectionListener implements SelectionListener {

		/**
		 * The context adapter.
		 */
		private EditingContextAdapter editingContextAdapter;

		/**
		 * The behavior to execute when the button is clicked.
		 */
		private Runnable runnable;

		/**
		 * The constructor.
		 *
		 * @param editingContextAdapter
		 *            The {@link EditingContextAdapter}
		 * @param runnable
		 *            The behavior to execute when the button is clicked
		 */
		public ButtonSelectionListener(EditingContextAdapter editingContextAdapter, Runnable runnable) {
			this.editingContextAdapter = editingContextAdapter;
			this.runnable = runnable;
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
		 */
		@Override
		public void widgetSelected(SelectionEvent event) {
			this.editingContextAdapter.performModelChange(this.runnable);
		}

		/**
		 * {@inheritDoc}
		 *
		 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
		 */
		@Override
		public void widgetDefaultSelected(SelectionEvent event) {
			this.editingContextAdapter.performModelChange(this.runnable);
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager#getEEFStyleHelper()
	 */
	@Override
	protected EEFStyleHelper getEEFStyleHelper() {
		return new EEFExtStyleHelper(this.interpreter, this.variableManager);
	}
	
	protected Object commonAncestor(List<Object> choiceOfValues) {
		if (!choiceOfValues.isEmpty() && choiceOfValues.get(0) instanceof EObject) {
			Z: for (Object candidate = ((EObject) choiceOfValues.get(0)).eContainer(); candidate instanceof EObject; candidate = ((EObject) candidate).eContainer()) {
				for (Object value: choiceOfValues) {
					if (value != candidate && value instanceof EObject)  {
						if (((EObject) value).eContainer() == null || !EcoreUtil.isAncestor((EObject) candidate, ((EObject) value).eContainer())) {
							continue Z;
						}
					}					
				}
				return candidate;
			}
		}
		return target.eResource().getResourceSet();
	}
	
}
