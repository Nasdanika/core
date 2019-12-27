/**
 * EEFExtReferenceLifecycleManagerProvider customized by Pavel Vlasov.  
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

package org.nasdanika.eef.ext.widgets;

import org.eclipse.eef.EEFControlDescription;
import org.eclipse.eef.EEFCustomWidgetDescription;
import org.eclipse.eef.core.api.EEFExpressionUtils;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.eef.ide.ui.api.widgets.IEEFLifecycleManager;
import org.eclipse.eef.ide.ui.api.widgets.IEEFLifecycleManagerProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.common.interpreter.api.IEvaluationResult;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.nasdanika.common.Util;
import org.nasdanika.eef.ext.widgets.reference.MultipleReferenceLifecycleManager;
import org.nasdanika.eef.ext.widgets.reference.SingleReferenceLifecycleManager;

/**
 * Customization of EEFExtReferenceLifecycleManagerProvider to provide tree features selection dialogs instead of FeatureEditorDialog.
 *  
 * The lifecycle manager provider is used to create a lifecycle manager for the simple reference widget.
 *
 * @author sbegaudeau
 * @author Pavel Vlasov
 */
public class NasdanikaLifecycleManagerProvider implements IEEFLifecycleManagerProvider {
	
	private static String REFERENCE_WIDGET_ID = "nasdanika_reference_widget";
	private static String RICH_TEXT_WIDGET_ID = "nasdanika_rich_text_widget";

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.api.widgets.IEEFLifecycleManagerProvider#canHandle(org.eclipse.eef.EEFControlDescription)
	 */
	@Override
	public boolean canHandle(EEFControlDescription controlDescription) {
		return controlDescription instanceof EEFCustomWidgetDescription && (
				REFERENCE_WIDGET_ID.equals(controlDescription.getIdentifier())
				|| RICH_TEXT_WIDGET_ID.equals(controlDescription.getIdentifier())
				);
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see org.eclipse.eef.ide.ui.api.widgets.IEEFLifecycleManagerProvider#getLifecycleManager(org.eclipse.eef.EEFControlDescription,
	 *      org.eclipse.sirius.common.interpreter.api.IVariableManager,
	 *      org.eclipse.sirius.common.interpreter.api.IInterpreter, org.eclipse.eef.core.api.EditingContextAdapter)
	 */
	@Override
	public IEEFLifecycleManager getLifecycleManager(
			EEFControlDescription controlDescription, 
			IVariableManager variableManager,
			IInterpreter interpreter, 
			EditingContextAdapter contextAdapter) {
		
		if (!(controlDescription instanceof EEFCustomWidgetDescription)) {
			return null;
		}

		EEFCustomWidgetDescription description = (EEFCustomWidgetDescription) controlDescription;

		EObject target = this.getTarget(description, interpreter, variableManager);
		String featureName = this.getFeatureName(description, interpreter, variableManager);
		if (target != null && !Util.isBlank(featureName)) {
			EStructuralFeature eStructuralFeature = target.eClass().getEStructuralFeature(featureName);			
			if (eStructuralFeature instanceof EReference) {
				if (REFERENCE_WIDGET_ID.equals(description.getIdentifier())) {
					EReference eReference = (EReference) eStructuralFeature;
					if (eReference.isMany()) {
						return new MultipleReferenceLifecycleManager(
								description, 
								target, 
								eReference, 
								variableManager, 
								interpreter,
								contextAdapter);
					}
					
					return new SingleReferenceLifecycleManager(
							description, 
							target, 
							eReference, 
							variableManager, 
							interpreter,
							contextAdapter);
				}
			} else if (RICH_TEXT_WIDGET_ID.equals(description.getIdentifier())) {
				return new RichTextLifecycleManager(description, variableManager, interpreter, contextAdapter, target, eStructuralFeature);
			}
		}
		throw new IllegalArgumentException("Don't know how to handle " + controlDescription.getIdentifier());
	}

	/**
	 * Returns the target to use as the current object.
	 *
	 * @param description
	 *            The description
	 * @param interpreter
	 *            The interpreter
	 * @param variableManager
	 *            The variable manager
	 * @return The target
	 */
	private EObject getTarget(EEFCustomWidgetDescription description, IInterpreter interpreter, IVariableManager variableManager) {
		EObject self = null;
		Object selfVariable = variableManager.getVariables().get(EEFExpressionUtils.SELF);
		if (selfVariable instanceof EObject) {
			self = (EObject) selfVariable;
		}

		return self;
	}

	/**
	 * Returns the name of the reference to use.
	 *
	 * @param description
	 *            The description
	 * @param interpreter
	 *            The interpreter
	 * @param variableManager
	 *            The variable manager
	 * @return The name of the reference
	 */
	private String getFeatureName(EEFCustomWidgetDescription description, IInterpreter interpreter, IVariableManager variableManager) {
		IEvaluationResult evaluationResult = interpreter.evaluateExpression(variableManager.getVariables(), "aql:eStructuralFeature.name");
		if (evaluationResult.success()) {
			return evaluationResult.asString();
		}
		return ""; //$NON-NLS-1$
	}

}
