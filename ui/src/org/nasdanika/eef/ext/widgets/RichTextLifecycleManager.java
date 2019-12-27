package org.nasdanika.eef.ext.widgets;

import java.util.function.Consumer;

import org.eclipse.eef.EEFCustomWidgetDescription;
import org.eclipse.eef.EEFWidgetDescription;
import org.eclipse.eef.common.ui.api.IEEFFormContainer;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.eef.core.api.controllers.IEEFWidgetController;
import org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.nebula.widgets.richtext.RichTextEditor;
import org.eclipse.nebula.widgets.richtext.RichTextEditorConfiguration;
import org.eclipse.nebula.widgets.richtext.toolbar.ToolbarConfiguration;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;


public class RichTextLifecycleManager extends AbstractEEFWidgetLifecycleManager {

    private EEFCustomWidgetDescription description;

    private TableViewer tableViewer;

    private ComposedAdapterFactory composedAdapterFactory;

    private SelectionListener onClickListener;

    private RichTextController controller;

    private Consumer<Object> newValueConsumer;
    
    public RichTextLifecycleManager(
    		EEFCustomWidgetDescription description, 
    		IVariableManager variableManager, 
    		IInterpreter interpreter,
            EditingContextAdapter contextAdapter) {
    	
        super(variableManager, interpreter, contextAdapter);
        this.description = description;
    }

    @Override
    protected void createMainControl(Composite parent, IEEFFormContainer formContainer) {    	
		RichTextEditorConfiguration config = new RichTextEditorConfiguration();
		config.setOption(RichTextEditorConfiguration.TOOLBAR_GROUPS, "["
//				+ "{ name: 'clipboard', groups: [ 'clipboard', 'undo', 'find' ] },"
//				+ "{ name: 'other' },"
//				+ "'/',"
				+ "{ name: 'paragraph', groups: [ 'list', 'indent', 'align' ] },"
				+ "{ name: 'colors' },"
//				+ "'/',"
				+ "{ name: 'styles' },"
				+ "{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] }"
				+ "]");
		final RichTextEditor editor = new RichTextEditor(parent, config);
		GridDataFactory.fillDefaults().grab(true, true).hint(SWT.DEFAULT, 500).applyTo(editor);
    	
    	
//        Table table = formContainer.getWidgetFactory().createTable(parent,
//                SWT.READ_ONLY | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER | SWT.SINGLE);
//        this.tableViewer = new TableViewer(table);
        this.composedAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

//        this.tableViewer.setContentProvider(ArrayContentProvider.getInstance());
//        this.tableViewer.setLabelProvider(new DelegatingStyledCellLabelProvider(new AdapterFactoryLabelProvider.StyledLabelProvider(
//                this.composedAdapterFactory, this.tableViewer)));

        this.controller = new RichTextController(description, variableManager, interpreter, editingContextAdapter);
    }

    @Override
    public void aboutToBeShown() {
        super.aboutToBeShown();

//        this.newValueConsumer = (newValue) -> this.tableViewer.setInput(newValue);
//        this.controller.onNewValue(this.newValueConsumer);

//        this.onClickListener = new SelectionListener() {
//            @Override
//            public void widgetSelected(SelectionEvent event) {
//                Object selection = ((IStructuredSelection) RichTextLifecycleManager.this.tableViewer.getSelection()).getFirstElement();
//                RichTextLifecycleManager.this.controller.handleClick(selection);
//            }
//
//            @Override
//            public void widgetDefaultSelected(SelectionEvent event) {
//                Object selection = ((IStructuredSelection) RichTextLifecycleManager.this.tableViewer.getSelection()).getFirstElement();
//                RichTextLifecycleManager.this.controller.handleClick(selection);
//            }
//        };
//        this.tableViewer.getTable().addSelectionListener(this.onClickListener);
    }

    @Override
    public void refresh() {
        super.refresh();

        this.controller.refresh();
    }

    @Override
    public void aboutToBeHidden() {
        super.aboutToBeHidden();
//        this.controller.removeValueConsumer();
//        this.newValueConsumer = null;

//        this.tableViewer.getTable().removeSelectionListener(this.onClickListener);
//        this.onClickListener = null;
    }

    @Override
    protected IEEFWidgetController getController() {
        return this.controller;
    }

    @Override
    protected EEFWidgetDescription getWidgetDescription() {
        return this.description;
    }

    @Override
    protected Control getValidationControl() {
        return this.tableViewer.getTable();
    }

    @Override
    public void dispose() {
        super.dispose();

        this.composedAdapterFactory.dispose();
    }
    
    @Override
    protected void setEnabled(boolean isEnabled) {
//        this.tableViewer.getTable().setEnabled(isEnabled);
    }
}
