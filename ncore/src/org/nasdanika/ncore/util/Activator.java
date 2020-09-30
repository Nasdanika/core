package org.nasdanika.ncore.util;

import org.nasdanika.emf.Palette;
import org.nasdanika.ncore.NcorePackage;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Registers ncore palettes.
 * 
 * @author Pavel
 *
 */
public class Activator implements BundleActivator {

	/**
	 * Anonymous (unnamed) model elements which compute results.
	 */
	public static final Palette EXPRESSIONS_PALETTE = Palette.Registry.INSTANCE.create(
			"org.nasdanika.ncore:expressions",
			"General",
			"General purpose elements", true);

	/**
	 * Named model elements which compute results. 
	 */
	public static final Palette NAMED_EXPRESSIONS_PALETTE = Palette.Registry.INSTANCE.create(
			"org.nasdanika.ncore:named-expressions",
			"Named expressions",
			"Named expressions can be members of maps and contexts", true);
	
	// TODO - Control flow

	@Override
	public void start(BundleContext context) throws Exception {
		EXPRESSIONS_PALETTE.add(
				NcorePackage.Literals.ARRAY,
				NcorePackage.Literals.HTTP_CALL,
				NcorePackage.Literals.MAP,
				NcorePackage.Literals.NULL,
				NcorePackage.Literals.OPERATION,
				NcorePackage.Literals.SUPPLIER,
				NcorePackage.Literals.SUPPLIER,
				NcorePackage.Literals.SERVICE,
				NcorePackage.Literals.SCRIPT_TEXT,				
				NcorePackage.Literals.SCRIPT_RESOURCE,
				NcorePackage.Literals.REFERENCE,
				NcorePackage.Literals.RESOURCE,
				NcorePackage.Literals.HTML,
				NcorePackage.Literals.VALUE);	
		
		NAMED_EXPRESSIONS_PALETTE.add(
			NcorePackage.Literals.FUNCTION,
			NcorePackage.Literals.LIST,	
			NcorePackage.Literals.ENTRY,	
			NcorePackage.Literals.OBJECT,	
			NcorePackage.Literals.PROPERTY);
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// NOP

	}

}
