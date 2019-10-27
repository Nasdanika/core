package org.nasdanika.ncore.util;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.ncore.NcorePackage;

/**
 * Groupings of model elements.
 * @author Pavel
 *
 */
public interface Palettes {
	
	/**
	 * Anonymous (unnamed) model elements which compute results. 
	 */
	EClass[] EXPRESSIONS = {
		NcorePackage.Literals.ARRAY,
		NcorePackage.Literals.CONTEXT,	
		NcorePackage.Literals.HTTP_CALL,	
		NcorePackage.Literals.MAP,	
		NcorePackage.Literals.NULL,	
		NcorePackage.Literals.OPERATION,	
		NcorePackage.Literals.REST_OPERATION, // TODO -> Function
		// TODO Resource
		NcorePackage.Literals.TYPED_ELEMENT,	
		NcorePackage.Literals.VALUE	
	};

	/**
	 * Named model elements which compute results. 
	 */
	EClass[] NAMED_EXPRESSIONS = {
		NcorePackage.Literals.FUNCTION,
		NcorePackage.Literals.LIST,	
		NcorePackage.Literals.OBJECT,	
		NcorePackage.Literals.PROPERTY,	
		// TODO HTTP Function
		// TODO Link
		NcorePackage.Literals.REST_FUNCTION	
	};
	
	
}
