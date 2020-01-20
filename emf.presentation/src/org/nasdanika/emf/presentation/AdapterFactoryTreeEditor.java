package org.nasdanika.emf.presentation;

import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditorDialogFactory;
import org.eclipse.sirius.ui.business.api.session.SessionEditorInput;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.nasdanika.sirius.tree.AdapterFactoryTree;

public class AdapterFactoryTreeEditor extends NasdanikaSiriusEditor implements DialectEditor {
	
	public static final String ID = AdapterFactoryTreeEditor.class.getName()+".ID";
	
	private AdapterFactoryTree representation;
	private DialectEditorDialogFactory dialogFactory;

	@Override
	public boolean needsRefresh(int propId) {
		// Assuming 
		return false;
	}

	@Override
	public void validateRepresentation() {
		// NOP
		
	}
	
	public void setRepresentation(AdapterFactoryTree representation) {
		this.representation = representation;
	}

	@Override
	public DRepresentation getRepresentation() {
		return representation;
	}

	@Override
	public void setDialogFactory(DialectEditorDialogFactory dialogFactory) {
		this.dialogFactory = dialogFactory;
		
	}

	@Override
	public DialectEditorDialogFactory getDialogFactory() {
		return dialogFactory;
	}
	
    public void close(final boolean save) {
        Display display = getSite().getShell().getDisplay();
        display.asyncExec(new Runnable() {
            @Override
            public void run() {
                getSite().getPage().closeEditor(AdapterFactoryTreeEditor.this, save);
            }
        });
    }	
    
    AdapterFactoryTree getTreeModel() {
    	if (getEditorInput() instanceof SessionEditorInput) {
    		SessionEditorInput sessionEditorInput = (SessionEditorInput) getEditorInput();
    		if (sessionEditorInput.getInput() instanceof AdapterFactoryTree) {
    			return (AdapterFactoryTree) sessionEditorInput.getInput();
    		}
    	}
    	return null;
    }
    
    @Override
    public void init(IEditorSite site, IEditorInput editorInput) {
    	super.init(site, editorInput);
    	if (editorInput instanceof SessionEditorInput && ((SessionEditorInput) editorInput).getInput() instanceof AdapterFactoryTree) {
    		setRepresentation((AdapterFactoryTree) ((SessionEditorInput) editorInput).getInput());
    	}
    }
    
    @Override
    public void setInput(IEditorInput input) {
        super.setInput(input);
        setSelectionViewerInput();
    }  
    
    @Override
    public void setFocus() {
    	super.setFocus();
    	setSelectionViewerInput();
    }

}
