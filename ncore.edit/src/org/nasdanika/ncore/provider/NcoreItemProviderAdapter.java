package org.nasdanika.ncore.provider;

import java.util.UUID;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.command.InitializeCopyCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.nasdanika.emf.edit.NasdanikaItemProviderAdapter;
import org.nasdanika.ncore.Entity;

public class NcoreItemProviderAdapter extends NasdanikaItemProviderAdapter {

	public NcoreItemProviderAdapter(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
		
	@Override
	protected Command createInitializeCopyCommand(EditingDomain domain, EObject owner, Helper helper) {
	    return new InitializeCopyCommand(domain, owner, helper) {
	    	
	    	@Override
	    	public void doExecute() {
	    		super.doExecute();
	    		if (getCopy() instanceof Entity) {
	    			((Entity) getCopy()).setId(UUID.randomUUID().toString());
	    		}
	    	}	    	
	    	
	    };
	}	
	

}
