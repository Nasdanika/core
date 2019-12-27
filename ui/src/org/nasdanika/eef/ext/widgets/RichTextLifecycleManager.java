package org.nasdanika.eef.ext.widgets;

/**
 * Adaptation of org.eclipse.eef.ide.ui.internal.widgets.EEFTextLifecycleManager to use
 * Eclipse Nebula Rich text component - https://www.eclipse.org/nebula/widgets/richtext/richtext.php
 */
/*******************************************************************************
 * Copyright (c) 2015, 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors: Obeo - initial API and implementation
 *******************************************************************************/


import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.eef.EEFControlDescription;
import org.eclipse.eef.EEFCustomWidgetDescription;
import org.eclipse.eef.EEFTextDescription;
import org.eclipse.eef.EEFTextStyle;
import org.eclipse.eef.EEFWidgetDescription;
import org.eclipse.eef.EEFWidgetStyle;
import org.eclipse.eef.common.api.utils.Util;
import org.eclipse.eef.common.ui.api.EEFWidgetFactory;
import org.eclipse.eef.common.ui.api.IEEFFormContainer;
import org.eclipse.eef.common.ui.api.SWTUtils;
import org.eclipse.eef.core.api.EEFExpressionUtils;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.eef.core.api.controllers.EEFControllersFactory;
import org.eclipse.eef.core.api.controllers.IEEFTextController;
import org.eclipse.eef.core.api.controllers.IEEFWidgetController;
import org.eclipse.eef.core.api.utils.EvalFactory;
import org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager;
import org.eclipse.eef.ide.ui.api.widgets.EEFHyperlinkListener;
import org.eclipse.eef.ide.ui.api.widgets.EEFStyleHelper;
import org.eclipse.eef.ide.ui.api.widgets.EEFStyleHelper.IEEFTextStyleCallback;
import org.eclipse.eef.ide.ui.ext.widgets.reference.internal.EEFExtEObjectCreationWizard;
import org.eclipse.eef.ide.ui.ext.widgets.reference.internal.EEFExtReferenceUIPlugin;
import org.eclipse.eef.ide.ui.ext.widgets.reference.internal.EEFExtStyleHelper;
import org.eclipse.eef.ide.ui.internal.EEFIdeUiPlugin;
import org.eclipse.eef.ide.ui.internal.Messages;
import org.eclipse.eef.ide.ui.internal.preferences.EEFPreferences;
import org.eclipse.eef.ide.ui.internal.widgets.EEFStyledTextStyleCallback;
import org.eclipse.eef.ide.ui.internal.widgets.EEFTextMemento;
import org.eclipse.eef.ide.ui.internal.widgets.styles.EEFColor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Hyperlink;
import org.nasdanika.eef.ext.widgets.reference.ReferenceController;
import org.nasdanika.eef.ext.widgets.reference.AbstractNasdanikaExtReferenceLifecycleManager.ButtonSelectionListener;
import org.nasdanika.emf.edit.SingleReferenceSelectionDialog;

/**
 * This class will be used in order to manager the lifecycle of a text.
 *
 * @author sbegaudeau
 * @author Pavel Vlasov
 */
public class RichTextLifecycleManager extends AbstractEEFWidgetLifecycleManager {
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

		if (Util.isBlank(this.description.getLabelExpression())) {
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
	
	
	// ----
	
	public RichTextLifecycleManager(
			EEFCustomWidgetDescription description, 
			EObject target,
			EStructuralFeature eStructuralFeature, 
			IVariableManager variableManager, 
			IInterpreter interpreter,
			EditingContextAdapter editingContextAdapter) {
		
		super(variableManager, interpreter, editingContextAdapter);
		this.description = description;
	}
		
	/**
	 * The image of the current value.
	 */
	private Label image;

	/**
	 * The label showing the current value.
	 */
	private Label text;

	/**
	 * The hyperlink showing the current value.
	 */
	private Hyperlink hyperlink;

	/**
	 * The listener on the hyperlink.
	 */
	private MouseListener hyperlinkListener;

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager#createMainControl(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.eef.common.ui.api.IEEFFormContainer)
	 */
	@Override
	protected void createMainControl(Composite parent, IEEFFormContainer formContainer) {
		this.widgetFactory = formContainer.getWidgetFactory();

		Composite referenceComposite = this.widgetFactory.createFlatFormComposite(parent);
		GridLayout gridLayout = new GridLayout(3, false);
		gridLayout.verticalSpacing = 0;
		gridLayout.marginHeight = 0;
		referenceComposite.setLayout(gridLayout);

		GridData referenceCompositeGridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
		referenceComposite.setLayoutData(referenceCompositeGridData);

		this.composedAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		this.composedAdapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		this.createLabel(referenceComposite);

		Composite buttonsComposite = this.widgetFactory.createFlatFormComposite(referenceComposite);
		GridData buttonCompositeGridData = new GridData();
		buttonsComposite.setLayoutData(buttonCompositeGridData);

		if (!this.eReference.isContainment()) {
			buttonsComposite.setLayout(new GridLayout(3, true));

			Image browseImage = ExtendedImageRegistry.INSTANCE
					.getImage(EEFExtReferenceUIPlugin.getPlugin().getImage(EEFExtReferenceUIPlugin.Implementation.BROWSE_ICON_PATH));
			this.browseButton = this.createButton(buttonsComposite, browseImage);
		} else {
			buttonsComposite.setLayout(new GridLayout(2, true));
		}

		Image addImage = ExtendedImageRegistry.INSTANCE
				.getImage(EEFExtReferenceUIPlugin.getPlugin().getImage(EEFExtReferenceUIPlugin.Implementation.ADD_ICON_PATH));
		Image removeImage = ExtendedImageRegistry.INSTANCE
				.getImage(EEFExtReferenceUIPlugin.getPlugin().getImage(EEFExtReferenceUIPlugin.Implementation.REMOVE_ICON_PATH));
		this.addButton = this.createButton(buttonsComposite, addImage);
		this.removeButton = this.createButton(buttonsComposite, removeImage);

		this.widgetFactory.paintBordersFor(parent);

		this.controller = new ReferenceController(this.description, this.variableManager, this.interpreter, this.editingContextAdapter);
	}

	/**
	 * Creates the label showing the value of the reference.
	 *
	 * @param parent
	 *            The parent composite
	 */
	private void createLabel(Composite parent) {
		this.image = this.widgetFactory.createLabel(parent, "", SWT.NONE); //$NON-NLS-1$

		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = SWT.FILL;

		String onClickExpression = ""; // Optional.ofNullable(this.description.getOnClickExpression()).orElse(""); //$NON-NLS-1$
		if (onClickExpression.isEmpty()) {
			this.text = this.widgetFactory.createLabel(parent, "", SWT.NONE); //$NON-NLS-1$
			this.text.setLayoutData(gridData);
		} else {
			this.hyperlink = this.widgetFactory.createHyperlink(parent, "", SWT.NONE); //$NON-NLS-1$
			this.hyperlink.setLayoutData(gridData);
		}

	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager#getLabelVerticalAlignment()
	 */
	@Override
	protected int getLabelVerticalAlignment() {
		return GridData.VERTICAL_ALIGN_CENTER;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.ext.widgets.reference.internal.AbstractEEFExtReferenceLifecycleManager#browseButtonCallback()
	 */
	@Override
	protected void browseButtonCallback() {
		Object adapter = this.composedAdapterFactory.adapt(this.target, IItemPropertySource.class);
		if (adapter instanceof IItemPropertySource) {
			IItemPropertySource propertySource = (IItemPropertySource) adapter;
			IItemPropertyDescriptor propertyDescriptor = propertySource.getPropertyDescriptor(this.target, this.eReference);
			if (propertyDescriptor != null) {
				ArrayList<Object> choiceOfValues = new ArrayList<Object>(propertyDescriptor.getChoiceOfValues(this.target));

				final AdapterFactoryLabelProvider adapterFactoryLabelProvider = new AdapterFactoryLabelProvider(this.composedAdapterFactory);
				LabelProvider labelProvider = new LabelProvider() {
					@Override
					public String getText(Object object) {
						return adapterFactoryLabelProvider.getText(object);
					}

					@Override
					public Image getImage(Object object) {
						return ExtendedImageRegistry.getInstance().getImage(adapterFactoryLabelProvider.getImage(object));
					}
				};
				
				ITreeContentProvider contentProvider = new AdapterFactoryContentProvider(composedAdapterFactory);
				Object value = this.target.eGet(this.eReference);
				ElementTreeSelectionDialog dialog = new SingleReferenceSelectionDialog(
						this.text.getShell(), 
						labelProvider, 
						contentProvider, 
						choiceOfValues, 
						value);				
				
				dialog.setTitle(propertyDescriptor.getDisplayName(value));
				// TODO icon for the reference type
				dialog.open();
				
				Object[] result = dialog.getResult();
				if (result != null) {
					if (result.length == 0) {
						this.target.eUnset(this.eReference);
					} else if (result.length == 1 && choiceOfValues.contains(result[0])) {						
						this.target.eSet(this.eReference, result[0]);
					}
				}

			}
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.ext.widgets.reference.internal.AbstractEEFExtReferenceLifecycleManager#addButtonCallback()
	 */
	@Override
	protected void addButtonCallback() {
		IWizard wizard = new EEFExtEObjectCreationWizard(this.target, this.eReference, this.editingContextAdapter);
		WizardDialog wizardDialog = new WizardDialog(this.image.getShell(), wizard);
		wizardDialog.open();
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.ext.widgets.reference.internal.AbstractEEFExtReferenceLifecycleManager#removeButtonCallback()
	 */
	@Override
	protected void removeButtonCallback() {
		this.editingContextAdapter.performModelChange(() -> target.eUnset(eReference));
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.ext.widgets.reference.internal.AbstractEEFExtReferenceLifecycleManager#refresh()
	 */
	@Override
	public void refresh() {
		super.refresh();

		Object value = this.target.eGet(this.eReference);
		if (value instanceof EObject) {
			Adapter adapter = this.composedAdapterFactory.adapt((EObject) value, IItemLabelProvider.class);
			if (adapter instanceof IItemLabelProvider) {
				IItemLabelProvider labelProvider = (IItemLabelProvider) adapter;
				this.image.setImage(ExtendedImageRegistry.INSTANCE.getImage(labelProvider.getImage(value)));

				String onClickExpression = ""; // Optional.ofNullable(this.description.getOnClickExpression()).orElse(""); //$NON-NLS-1$
				if (onClickExpression.isEmpty()) {
					this.text.setText(labelProvider.getText(value));
				} else {
					this.hyperlink.setText(labelProvider.getText(value));
					this.hyperlink.setData(value);
				}
			}
		} else if (value == null) {
			this.image.setImage(null);

			String onClickExpression = ""; // Optional.ofNullable(this.description.getOnClickExpression()).orElse(""); //$NON-NLS-1$
			if (onClickExpression.isEmpty()) {
				this.text.setText(Messages.SingleReference_noValue);
			} else {
				this.hyperlink.setText(Messages.SingleReference_noValue);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.ext.widgets.reference.internal.AbstractEEFExtReferenceLifecycleManager#setEnabled(boolean)
	 */
	@Override
	protected void setEnabled(boolean isEnabled) {
		super.setEnabled(isEnabled);

		if (this.browseButton != null && !this.browseButton.isDisposed()) {
			this.browseButton.setEnabled(isEnabled);
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.api.widgets.AbstractEEFLifecycleManager#getValidationControl()
	 */
	@Override
	protected Control getValidationControl() {
		return this.image;
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.ext.widgets.reference.internal.AbstractEEFExtReferenceLifecycleManager#aboutToBeShown()
	 */
	@Override
	public void aboutToBeShown() {
		super.aboutToBeShown();

		String onClickExpression = ""; // Optional.ofNullable(this.description.getOnClickExpression()).orElse(""); //$NON-NLS-1$
		if (!onClickExpression.isEmpty()) {
			this.hyperlinkListener = new EEFHyperlinkListener(this, this.hyperlink, this.container, this.controller);
			hyperlink.addMouseListener(hyperlinkListener);
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.ext.widgets.reference.internal.AbstractEEFExtReferenceLifecycleManager#aboutToBeHidden()
	 */
	@Override
	public void aboutToBeHidden() {
		super.aboutToBeHidden();

		if (this.hyperlink != null && !this.hyperlink.isDisposed() && this.hyperlinkListener != null) {
			this.hyperlink.removeMouseListener(this.hyperlinkListener);
		}
	}
	
	// ---
//	/**
//	 * The different ways an edition conflict can be resolved. Used by the default implementation of
//	 * {@link RichTextLifecycleManager#resolveEditionConflict(Shell, String, String, String)}.
//	 */
//	public enum ConflictResolutionMode {
//	/**
//	 * Use the version being edited in the widget, overriding the new version computed from the current model state.
//	 */
//	USE_LOCAL_VERSION,
//	/**
//	 * Use the version computed from the current model state, replacing the text being edited by the user in the widget.
//	 */
//	USE_MODEL_VERSION,
//	/**
//	 * Ask the user through a simple dialog which version to keep.
//	 */
//	ASK_USER
//	}
//	
//	/**
//	 * This constant is used in order to tell SWT that the text area should be 300px wide even if it is not useful. The
//	 * layout data should work by themselves but it seems that there is a bug with SWT so, this useless information on
//	 * the width of the text area make it work. Don't ask me why :)
//	 */
//	private static final int TEXT_AREA_WIDTH_HINT = 300;
//	
//	/**
//	 * The description.
//	 */
//	private EEFCustomWidgetDescription description;
//	
//	/**
//	 * The text.
//	 */
//	private StyledText text;
//	
//	/**
//	 * The controller.
//	 */
//	private IEEFTextController controller;
//	
//	/**
//	 * The listener on the text field.
//	 */
//	private FocusListener focusListener;
//	
//	/**
//	 * The key listener on the text field (unused for a multi-line text field).
//	 */
//	private KeyListener keyListener;
//	
//	/**
//	 * The widget factory.
//	 */
//	private EEFWidgetFactory widgetFactory;
//	
//	/**
//	 * The default background color of the text field.
//	 */
//	private Color defaultBackgroundColor;
//	
//	/**
//	 * The listener used to indicate that the text field is dirty.
//	 */
//	private ModifyListener modifyListener;
//	
//	/**
//	 * Used to make the SelectionListener reentrant, to avoid infinite loops when we need to revert the UI state on
//	 * error (as reverting the UI re-triggers the SelectionListener).
//	 */
//	private AtomicBoolean updateInProgress = new AtomicBoolean(false);
//	
//	/**
//	 * True only while we are reacting to a notification that the underlying element has been locked by someone else.
//	 * When this is the case, we must avoid any attempt to apply our current widget state to the model (it will fail).
//	 */
//	private AtomicBoolean lockedByOtherInProgress = new AtomicBoolean(false);
//	
//	/**
//	 * The reference value of the text, as last rendered from the state of the actual model.
//	 */
//	private String referenceValue = ""; //$NON-NLS-1$
//	
//	/**
//	 * Indicates that the text field is dirty.
//	 */
//	private boolean isDirty;
//
//
//	/**
//	 * {@inheritDoc}
//	 *
//	 * @see org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager#createMainControl(org.eclipse.swt.widgets.Composite,
//	 *      org.eclipse.eef.common.ui.api.IEEFFormContainer)
//	 */
//	@Override
//	protected void createMainControl(Composite parent, IEEFFormContainer formContainer) {
//		widgetFactory = formContainer.getWidgetFactory();
//		defaultBackgroundColor = parent.getBackground();
//
//		// Get text area line count
//		int lineCount = getLineCount();
//
//		// Create text or text area according to the defined line count
//		if (lineCount > 1) {
//			this.text = widgetFactory.createStyledText(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.WRAP | SWT.MULTI);
//			GridData gridData = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
//			gridData.heightHint = lineCount * text.getLineHeight();
//			gridData.widthHint = TEXT_AREA_WIDTH_HINT;
//			gridData.horizontalIndent = VALIDATION_MARKER_OFFSET;
//			this.text.setLayoutData(gridData);
//		} else {
//			this.text = widgetFactory.createStyledText(parent, SWT.SINGLE);
//			GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, false);
//			gridData.horizontalIndent = VALIDATION_MARKER_OFFSET;
//			this.text.setLayoutData(gridData);
//		}
//
//		this.text.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
//		widgetFactory.paintBordersFor(parent);
//
////		this.controller = new EEFControllersFactory().createTextController(this.description, this.variableManager, this.interpreter,
////				this.editingContextAdapter);
//	}
//
//	private int getLineCount() {
//		return 10;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 *
//	 * @see org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager#getLabelVerticalAlignment()
//	 */
//	@Override
//	protected int getLabelVerticalAlignment() {
//		if (getLineCount() > 1) {
//			return GridData.VERTICAL_ALIGN_BEGINNING;
//		}
//		return GridData.VERTICAL_ALIGN_CENTER;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 *
//	 * @see org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager#getController()
//	 */
//	@Override
//	protected IEEFWidgetController getController() {
//		return this.controller;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 *
//	 * @see org.eclipse.eef.ide.ui.internal.widgets.AbstractEEFWidgetLifecycleManager#getWidgetDescription()
//	 */
//	@Override
//	protected EEFWidgetDescription getWidgetDescription() {
//		return this.description;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 *
//	 * @see org.eclipse.eef.ide.ui.api.widgets.IEEFLifecycleManager#aboutToBeShown()
//	 */
//	@Override
//	public void aboutToBeShown() {
//		super.aboutToBeShown();
//
//		this.modifyListener = (event) -> {
//			if (!this.container.isRenderingInProgress() && !updateInProgress.get()) {
//				this.isDirty = true;
//				Object self = this.variableManager.getVariables().get(EEFExpressionUtils.SELF);
//				String userInput = ((StyledText) event.widget).getText();
//				EEFTextMemento memento = new EEFTextMemento(this.description, self, this.referenceValue, userInput);
//				memento.store(event.widget);
//			}
//		};
//		this.text.addModifyListener(this.modifyListener);
//
//		this.focusListener = SWTUtils.focusLostAdapter((event) -> {
//			if (!this.lockedByOtherInProgress.get() && !this.container.isRenderingInProgress() && this.isDirty) {
//				this.updateValue(false);
//			}
//		});
//		this.text.addFocusListener(this.focusListener);
//
//		if (getLineCount() <= 1) {
//			this.keyListener = SWTUtils.keyReleasedAdapter((event) -> {
//				if (event.character == '\r' || event.character == '\n') {
//					this.updateValue(false);
//				}
//			});
//			this.text.addKeyListener(this.keyListener);
//		}
//
//		this.controller.onNewValue((value) -> {
//			if (!text.isDisposed()) {
//				String newDisplayText = computeNewText(value);
//				if (!(text.getText() != null && text.getText().equals(newDisplayText))) {
//					text.setText(newDisplayText);
//					referenceValue = text.getText();
//				}
//				this.setStyle();
//				if (!text.isEnabled()) {
//					text.setEnabled(true);
//				}
//			}
//
//		});
//	}
//
//	/**
//	 * Determine the new textual value to display in the widget.
//	 *
//	 * @param value
//	 *            the value computed from the model.
//	 * @return the textual value to display in the widget.
//	 */
//	private String computeNewText(Object value) {
//		String[] newDisplayText = { "" }; //$NON-NLS-1$
//		if (value != null) {
//			newDisplayText[0] = Util.firstNonNull(value.toString(), newDisplayText[0]);
//		}
//		EEFTextMemento memento = EEFTextMemento.of(text);
//		if (memento != null) {
//			boolean resettingToPreviousReferenceValue = Objects.equals(newDisplayText[0], memento.getReferenceValue());
//			boolean userHasUncommitedInput = !Objects.equals(newDisplayText[0], memento.getUserInput());
//			if (memento.appliesTo(this.description, this.variableManager.getVariables()) && userHasUncommitedInput) {
//				if (resettingToPreviousReferenceValue) {
//					// Custom user input overrides resetting the same previous referenceValue.
//					newDisplayText[0] = memento.getUserInput();
//				} else if (!Objects.equals(memento.getUserInput(), newDisplayText[0])) {
//					// Conflict must be resolved somehow.
//					newDisplayText[0] = resolveEditionConflict(this.text.getShell(), memento.getReferenceValue(), memento.getUserInput(),
//							newDisplayText[0]);
//				}
//			}
//			EEFTextMemento.remove(text);
//		}
//		return newDisplayText[0];
//	}
//
//	/**
//	 * Handle conflicts between un-commited changes in the widget and concurrent changes in the model that produce a
//	 * different value than the original one seen by the user.
//	 *
//	 * @param shell
//	 *            the shell to use if user interaction is needed.
//	 * @param originalValue
//	 *            the original, common value, before the user started editing and before the concurrent model change
//	 *            produced a new text.
//	 * @param localEditedVersion
//	 *            the value as edited by the user, and seen in the UI.
//	 * @param newValueFromModel
//	 *            the new value produced from the new model state.
//	 * @return the new value to use in the text field.
//	 */
//	protected String resolveEditionConflict(Shell shell, String originalValue, String localEditedVersion, final String newValueFromModel) {
//		String result;
//		switch (EEFPreferences.getTextConflictResolutionMode()) {
//		case USE_LOCAL_VERSION:
//			result = localEditedVersion;
//			break;
//		case USE_MODEL_VERSION:
//			result = newValueFromModel;
//			break;
//		case ASK_USER:
//			result = askUserToResolveConflict(shell, originalValue, localEditedVersion, newValueFromModel);
//			break;
//		default:
//			throw new IllegalStateException();
//		}
//		return result;
//	}
//
//	/**
//	 * Open a simple dialog to inform the user of a conflict and ask him which version to keep.
//	 *
//	 * @param shell
//	 *            the shell to use if user interaction is needed.
//	 * @param originalValue
//	 *            the original, common value, before the user started editing and before the concurrent model change
//	 *            produced a new text.
//	 * @param localEditedVersion
//	 *            the value as edited by the user, and seen in the UI.
//	 * @param newValueFromModel
//	 *            the new value produced from the new model state.
//	 * @return the value chosen by the user.
//	 */
//	protected String askUserToResolveConflict(Shell shell, String originalValue, String localEditedVersion, String newValueFromModel) {
//		final String[] result = { localEditedVersion };
//		// @formatter:off
//		final String[] choices = {
//				Messages.EEFTextLifecycleManager_conflictDialog_choiceNewModelValue,
//				Messages.EEFTextLifecycleManager_conflictDialog_choiceLocalEditedValue,
//		};
//		// @formatter:on
//		shell.getDisplay().syncExec(() -> {
//			String title = Messages.EEFTextLifecycleManager_conflictDialog_title;
//			String message = MessageFormat.format(Messages.EEFTextLifecycleManager_conflictDialog_message, newValueFromModel, localEditedVersion);
//			Image img = shell.getDisplay().getSystemImage(SWT.ICON_QUESTION);
//			MessageDialog dialog = new MessageDialog(shell, title, img, message, MessageDialog.QUESTION, 0, choices);
//			switch (dialog.open()) {
//			case 0:
//				result[0] = newValueFromModel;
//				break;
//			case 1:
//				result[0] = localEditedVersion;
//				break;
//			default:
//				throw new IllegalStateException();
//			}
//		});
//		return result[0];
//	}
//
//	/**
//	 * Updates the value.
//	 *
//	 * @param force
//	 *            if <code>true</code>, update even if we are in the render phase.
//	 */
//	private void updateValue(boolean force) {
//		boolean shouldUpdateWhileRendering = !RichTextLifecycleManager.this.container.isRenderingInProgress() || force;
//		if (!this.text.isDisposed() && this.isDirty && shouldUpdateWhileRendering && updateInProgress.compareAndSet(false, true)) {
//			try {
//				IStatus result = controller.updateValue(text.getText());
//				if (result != null && result.getSeverity() == IStatus.ERROR) {
//					EEFIdeUiPlugin.INSTANCE.log(result);
//					text.setText(referenceValue);
//				} else {
//					referenceValue = text.getText();
//					refresh();
//				}
//				this.isDirty = false;
//				EEFTextMemento.remove(this.text);
//				this.setStyle();
//			} finally {
//				updateInProgress.set(false);
//			}
//		}
//	}
//
//	/**
//	 * Set the style.
//	 */
//	private void setStyle() {
//		EEFStyleHelper styleHelper = new EEFStyleHelper(this.interpreter, this.variableManager);
//		EEFWidgetStyle widgetStyle = styleHelper.getWidgetStyle(this.description);
//		if (widgetStyle instanceof EEFTextStyle) {
//			EEFTextStyle textStyle = (EEFTextStyle) widgetStyle;
//
//			Font defaultFont = this.text.getShell().getFont();
//
//			IEEFTextStyleCallback callback = new EEFStyledTextStyleCallback(this.text);
//			styleHelper.applyTextStyle(textStyle.getFontNameExpression(), textStyle.getFontSizeExpression(), textStyle.getFontStyleExpression(),
//					defaultFont, textStyle.getBackgroundColorExpression(), textStyle.getForegroundColorExpression(), callback);
//		}
//	}
//
//	/**
//	 * {@inheritDoc}
//	 *
//	 * @see org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager#getValidationControl()
//	 */
//	@Override
//	protected Control getValidationControl() {
//		return this.text;
//	}
//
//	/**
//	 * {@inheritDoc}
//	 *
//	 * @see org.eclipse.eef.ide.ui.api.widgets.IEEFLifecycleManager#aboutToBeHidden()
//	 */
//	@Override
//	public void aboutToBeHidden() {
//		if (this.isDirty) {
//			this.updateValue(true);
//		}
//
//		super.aboutToBeHidden();
//
//		if (!text.isDisposed()) {
//			this.text.removeFocusListener(this.focusListener);
//		}
//		this.controller.removeNewValueConsumer();
//
//		if (!this.text.isDisposed()) {
//			this.text.removeModifyListener(this.modifyListener);
//		}
//
//		if (!this.text.isDisposed() && this.description.getLineCount() <= 1) {
//			this.text.removeKeyListener(this.keyListener);
//		}
//	}
//
//	@Override
//	protected void lockedByOther() {
//		this.lockedByOtherInProgress.set(true);
//		try {
//			/*
//			 * Disabling the widget will prevent the user to recover any local version of the text widget. Detect this
//			 * case and open a popup with the option to copy the local text to the clipboard.
//			 */
//			String textFromModel = computeTextFromModel();
//			EEFTextMemento memento = EEFTextMemento.of(text);
//			if (memento != null) {
//				boolean userHasUncommitedInput = !Objects.equals(textFromModel, memento.getUserInput());
//				if (memento.appliesTo(this.description, this.variableManager.getVariables()) && userHasUncommitedInput) {
//					notifyTextLossOnLock(memento.getUserInput(), textFromModel);
//					// Update the displayed text to avoid confusion.
//					this.text.setText(textFromModel);
//				}
//				EEFTextMemento.remove(text);
//			}
//			super.lockedByOther();
//		} finally {
//			this.lockedByOtherInProgress.set(false);
//		}
//	}
//
//	/**
//	 * Notify the end user that his current input will be lost as the underlying widget (and model element) has been
//	 * locked by a remote/async change.
//	 *
//	 * @param userInput
//	 *            the current text entered by the user.
//	 * @param textFromModel
//	 *            the text that will replace the current input.
//	 */
//	protected void notifyTextLossOnLock(String userInput, String textFromModel) {
//		Shell shell = this.text.getShell();
//		if (MessageDialog.openQuestion(shell, Messages.EEFTextLifecycleManager_textLossByLocking_title,
//				MessageFormat.format(Messages.EEFTextLifecycleManager_textLossByLocking_message, userInput))) {
//			Clipboard clipboard = new Clipboard(shell.getDisplay());
//			clipboard.setContents(new Object[] { userInput }, new Transfer[] { TextTransfer.getInstance() });
//			clipboard.dispose();
//		}
//	}
//
//	/**
//	 * Compute the text the widget should display given the current state of the underlying model (independently of any
//	 * potential user input in the widget).
//	 *
//	 * @return the text to display according to the model's current state.
//	 */
//	private String computeTextFromModel() {
//		String valueExpression = this.description.getValueExpression();
//		Object result = EvalFactory.of(this.interpreter, this.variableManager).evaluate(valueExpression);
//
//		// @formatter:off
//		return Optional.ofNullable(result)
//				.filter(String.class::isInstance)
//				.map(String.class::cast)
//				.orElse(""); //$NON-NLS-1$
//		// @formatter:on
//	}
//
//	/**
//	 * {@inheritDoc}
//	 *
//	 * @see org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager#setEnabled(boolean)
//	 */
//	@Override
//	protected void setEnabled(boolean isEnabled) {
//		if (!this.text.isDisposed()) {
//			this.text.setEnabled(isEnabled);
//			this.text.setEditable(isEnabled);
//			this.text.setBackground(this.getBackgroundColor(isEnabled));
//		}
//	}
//
//	/**
//	 * Get the background color according to the current valid style.
//	 *
//	 * @param isEnabled
//	 *            <code>true</code> to indicate that the widget is currently enabled, <code>false</code> otherwise
//	 *
//	 * @return The background color to use in the text field.
//	 */
//	private Color getBackgroundColor(boolean isEnabled) {
//		Color color = defaultBackgroundColor;
//		if (!isEnabled) {
//			color = widgetFactory.getColors().getInactiveBackground();
//		} else {
//			EEFWidgetStyle widgetStyle = new EEFStyleHelper(this.interpreter, this.variableManager).getWidgetStyle(this.description);
//			if (widgetStyle instanceof EEFTextStyle) {
//				EEFTextStyle style = (EEFTextStyle) widgetStyle;
//				String backgroundColorCode = style.getBackgroundColorExpression();
//				if (!Util.isBlank(backgroundColorCode)) {
//					EEFColor backgroundColor = new EEFColor(backgroundColorCode);
//					color = backgroundColor.getColor();
//				}
//			}
//		}
//		return color;
//	}
//	
//	// ----
	
}
