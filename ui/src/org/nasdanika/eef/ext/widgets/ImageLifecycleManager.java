package org.nasdanika.eef.ext.widgets;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Consumer;

import org.eclipse.eef.EEFCustomWidgetDescription;
import org.eclipse.eef.EEFWidgetDescription;
import org.eclipse.eef.common.ui.api.EEFWidgetFactory;
import org.eclipse.eef.common.ui.api.IEEFFormContainer;
import org.eclipse.eef.core.api.EditingContextAdapter;
import org.eclipse.eef.core.api.controllers.IEEFWidgetController;
import org.eclipse.eef.ide.ui.api.widgets.AbstractEEFWidgetLifecycleManager;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.common.interpreter.api.IInterpreter;
import org.eclipse.sirius.common.interpreter.api.IVariableManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.nasdanika.common.DefaultConverter;


public class ImageLifecycleManager extends AbstractEEFWidgetLifecycleManager {

    private EEFCustomWidgetDescription description;

    private ImageController controller;

    private Consumer<Object> newValueConsumer;

	private EObject target;

	private EStructuralFeature feature;

	private Composite control;
	private Label label;

    public ImageLifecycleManager(
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
    	control = new Composite(parent, SWT.NONE);
		control.setLayout(new GridLayout(1, false));
		control.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
   	    	
		Button btnCapture = new Button(control, SWT.NONE);
		btnCapture.setText("Capture");
		
		btnCapture.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				try {
					setValue(DefaultConverter.INSTANCE.toByteArray(new FileInputStream(new File("C:\\Users\\Pavel\\Pictures\\Pavel headsot S.jpg"))));
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
			
		});
				
		EEFWidgetFactory widgetFactory = formContainer.getWidgetFactory();
		label = widgetFactory.createLabel(control, "",  SWT.H_SCROLL | SWT.V_SCROLL );
		GridData gridData = new GridData(SWT.FILL, SWT.BEGINNING, true, true, 1, 1);
		gridData.heightHint = 500;
//		gridData.widthHint = 300;
		gridData.horizontalIndent = VALIDATION_MARKER_OFFSET;
		label.setLayoutData(gridData);

		label.setData(FormToolkit.KEY_DRAW_BORDER, FormToolkit.TEXT_BORDER);
		widgetFactory.paintBordersFor(parent);

        this.controller = new ImageController(description, variableManager, interpreter, editingContextAdapter, target, feature);
    }
    
    private void setValue(byte[] value) {
    	editingContextAdapter.performModelChange(() -> target.eSet(feature, value));     		    	
    }

    @Override
    public void aboutToBeShown() {
        super.aboutToBeShown();

        this.newValueConsumer = (newValue) -> {
			Object text = newValue == null ? "" : String.valueOf(newValue);
			Display.getCurrent().asyncExec(() -> {

				if (newValue instanceof byte[]) {
					Image image = new Image(label.getDisplay(), new ByteArrayInputStream((byte[]) newValue));						
					label.setImage(image);
				} else {
					label.setImage(null);
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
        return label;
    }

    @Override
    public void dispose() {
        super.dispose();
        control.dispose();
    }
    
    @Override
    protected void setEnabled(boolean isEnabled) {
    	control.setEnabled(isEnabled);
    }
}
