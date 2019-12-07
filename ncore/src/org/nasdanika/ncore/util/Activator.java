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
	public static final Palette EXPRESSIONS_PALETTE = Palette.Registry.INSTANCE.get("org.nasdanika.ncore:expressions");

	/**
	 * Named model elements which compute results. 
	 */
	public static final Palette NAMED_EXPRESSIONS_PALETTE = Palette.Registry.INSTANCE.get("org.nasdanika.ncore:named-expressions");
	
	// TODO - Control flow

	@Override
	public void start(BundleContext context) throws Exception {
		EXPRESSIONS_PALETTE.add(
//				NcorePackage.Literals.ARRAY,
//				NcorePackage.Literals.CONTEXT,
//				NcorePackage.Literals.HTTP_CALL,
//				NcorePackage.Literals.MAP,
//				NcorePackage.Literals.NULL,
//				NcorePackage.Literals.OPERATION,
//				NcorePackage.Literals.REST_OPERATION,
//				NcorePackage.Literals.TYPED_ELEMENT,
				NcorePackage.Literals.SUPPLIER_FACTORY_REFERENCE,
				NcorePackage.Literals.RESOURCE,
				NcorePackage.Literals.VALUE);	

		// TODO -> FunctionFactory
		// TODO Resource
		
//		NAMED_EXPRESSIONS_PALETTE.add(
//			NcorePackage.Literals.FUNCTION,
//			NcorePackage.Literals.LIST,	
//			NcorePackage.Literals.OBJECT,	
//			NcorePackage.Literals.PROPERTY,	
//			NcorePackage.Literals.REST_FUNCTION);	

		// TODO HTTP FunctionFactory
		// TODO Link
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// NOP

	}

}
