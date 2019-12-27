package org.nasdanika.eef.ext.widgets;

import java.util.function.Consumer;

import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.eef.EEFCustomWidgetDescription;
import org.eclipse.eef.EEFWidgetDescription;
import org.eclipse.eef.common.ui.api.IEEFFormContainer;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.eef.core.api.controllers.IEEFWidgetController;
import org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.databinding.swt.typed.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.nebula.widgets.richtext.RichTextEditor;
import org.eclipse.nebula.widgets.richtext.RichTextEditorConfiguration;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;


public class RichTextLifecycleManager extends AbstractEEFWidgetLifecycleManager {

    private EEFCustomWidgetDescription description;

    private RichTextController controller;

    private Consumer<Object> newValueConsumer;

	private TabFolder tabFolder;

	private EObject target;

	private EStructuralFeature feature;

	private Text htmlSource;

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
		tabFolder = new TabFolder(parent, SWT.BOTTOM);
		
		TabItem tbtmDesign = new TabItem(tabFolder, SWT.NONE);
		tbtmDesign.setText("Design");
		
		TabItem tbtmSource = new TabItem(tabFolder, SWT.NONE);
		tbtmSource.setText("Source");
		
		GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 800).applyTo(tabFolder);    	
    	
		RichTextEditorConfiguration config = new RichTextEditorConfiguration();
		config.setOption(RichTextEditorConfiguration.TOOLBAR_GROUPS, "["
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
		editor = new RichTextEditor(tabFolder, config);
    	
		tbtmDesign.setControl(editor);
		
//		GridDataFactory grabBoth = GridDataFactory.fillDefaults().grab(true, true);
//		grabBoth.applyTo(tabFolder);
//		grabBoth.applyTo(editor);

		htmlSource = new Text(tabFolder, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		GridDataFactory.fillDefaults().grab(true, false).hint(SWT.DEFAULT, 100).applyTo(htmlSource);
		
		tbtmSource.setControl(htmlSource);		
		
//		WritableValue<String> data = new WritableValue<String>();
//		data.
		
		IObservableValue<String> textModifyObservable = WidgetProperties.text(SWT.Modify).observeDelayed(700, htmlSource);
		
		IChangeListener listener = new IChangeListener() {
			
			@Override
			public void handleChange(ChangeEvent event) {
				setValue(htmlSource.getText());				
			}
			
		};
		
		textModifyObservable.addChangeListener(listener);
		
		editor.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (editor.isFocusControl()) {
					htmlSource.setText(editor.getText());
				}
			}
			
		});
		
		htmlSource.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (htmlSource.isFocusControl()) {
					editor.setText(htmlSource.getText());
				}
			}
			
		});

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
			// Updates are ignored if any of the controls is in focus.
			if (!editor.isFocusControl() && !htmlSource.isFocusControl()) {
				editor.setText(text);
				htmlSource.setText(text);				
			}
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
        return tabFolder;
    }

    @Override
    public void dispose() {
        super.dispose();
        tabFolder.dispose();
    }
    
    @Override
    protected void setEnabled(boolean isEnabled) {
    	tabFolder.setEnabled(isEnabled);
    }
}
