package org.nasdanika.eef.ext.widgets;

import java.util.function.Consumer;

import org.eclipse.eef.EEFCustomWidgetDescription;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.eef.core.api.controllers.AbstractEEFCustomWidgetController;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;

public class RichTextController extends AbstractEEFCustomWidgetController {

    private Consumer<Object> newValueConsumer;

	private EObject target;
	
	private EStructuralFeature feature;
    
    public RichTextController(
    		EEFCustomWidgetDescription description, 
    		IVariableManager variableManager, 
    		IInterpreter interpreter,
            EditingContextAdapter contextAdapter,
            EObject target,
            EStructuralFeature feature) {
        super(description, variableManager, interpreter, contextAdapter);
        this.target = target;
        this.feature = feature;
    }

    @Override
    public void refresh() {
        super.refresh();
        newValueConsumer.accept(target.eGet(feature));
    }

    public void onNewValue(Consumer<Object> consumer) {
        this.newValueConsumer = consumer;
    }

    public void removeValueConsumer() {
        this.newValueConsumer = null;
    }
}