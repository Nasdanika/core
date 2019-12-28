package org.nasdanika.eef.ext.widgets;

import java.util.function.Consumer;

import org.eclipse.eef.EEFCustomWidgetDescription;
import org.eclipse.eef.EEFWidgetDescription;
import org.eclipse.eef.common.ui.api.IEEFFormContainer;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.eef.core.api.controllers.IEEFWidgetController;
import org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.nebula.widgets.richtext.RichTextEditor;
import org.eclipse.nebula.widgets.richtext.RichTextEditorConfiguration;
import org.eclipse.nebula.widgets.richtext.toolbar.ToolbarButton;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;


public class RichTextLifecycleManager extends AbstractEEFWidgetLifecycleManager {

    private EEFCustomWidgetDescription description;

    private RichTextController controller;

    private Consumer<Object> newValueConsumer;

	private EObject target;

	private EStructuralFeature feature;

	private RichTextEditor editor;
	
    public RichTextLifecycleManager(
    		EEFCustomWidgetDescription description, 
    		IVariableManager variableManager, 
    		IInterpreter interpreter,
            EditingContextAdapter contextAdapter,
            EObject target,
            EStructuralFeature feature) {
    	
        super(variableManager, interpreter, contextAdapter);
        this.description = description;
        this.target = target;
        this.feature = feature;
    }

    @Override
    protected void createMainControl(Composite parent, IEEFFormContainer formContainer) {
		RichTextEditorConfiguration config = new RichTextEditorConfiguration();
		config.setOption(RichTextEditorConfiguration.TOOLBAR_GROUPS, "["
//				+ "{ name: 'document', groups: [ 'mode' ] },"
//				+ "{ name: 'clipboard', groups: [ 'clipboard', 'undo', 'find' ] },"
//				+ "{ name: 'other' },"
//				+ "'/',"
				+ "{ name: 'paragraph', groups: [ 'list', 'indent', 'align' ] },"
				+ "{ name: 'colors' },"
//				+ "'/',"
				+ "{ name: 'styles' },"
				+ "{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },"
				+ "'/',"
				+ "{ name: 'links' },"
				+ "{ name: 'insert' }"
				+ "]");
		
		ToolbarButton sourceButton = new ToolbarButton("HTML Source", "htmlSource", "HTML Source", "insert", getClass().getResource("source.png")) {
			
			@Override
			public Object execute() {
				MultiLineInputDialog sourceDialog = new MultiLineInputDialog(editor.getShell(), "HTML Source", "Edit HTML Source", editor.getText(), null);
				int result = sourceDialog.open();
				if (result == Dialog.OK) {
					editor.setText(sourceDialog.getValue());
				}
				return super.execute();
			}
			
		};
		config.addToolbarButton(sourceButton);
		
		editor = new RichTextEditor(parent, config);
		GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 800).applyTo(editor);
		
		FocusListener focusListener = new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				setValue(editor.getText());				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				// NOP
			}
			
		};
		
		editor.addFocusListener(focusListener);

        this.controller = new RichTextController(description, variableManager, interpreter, editingContextAdapter, target, feature);
    }
    
    private void setValue(String value) {
    	editingContextAdapter.performModelChange(() -> target.eSet(feature, value));     		    	
    }

    @Override
    public void aboutToBeShown() {
        super.aboutToBeShown();

        this.newValueConsumer = (newValue) -> {
			String text = newValue == null ? "" : String.valueOf(newValue);
			Display.getCurrent().asyncExec(() -> {
				if (!text.equals(editor.getText())) {
					editor.setText(text);
				}
			});
        };
        this.controller.onNewValue(this.newValueConsumer);
    }

    @Override
    public void refresh() {
        super.refresh();
        controller.refresh();
    }

    @Override
    public void aboutToBeHidden() {
        super.aboutToBeHidden();
        this.controller.removeValueConsumer();
        this.newValueConsumer = null;
    }

    @Override
    protected IEEFWidgetController getController() {
        return controller;
    }

    @Override
    protected EEFWidgetDescription getWidgetDescription() {
        return description;
    }

    @Override
    protected Control getValidationControl() {
        return editor;
    }

    @Override
    public void dispose() {
        super.dispose();
        editor.dispose();
    }
    
    @Override
    protected void setEnabled(boolean isEnabled) {
    	editor.setEnabled(isEnabled);
    }
}
