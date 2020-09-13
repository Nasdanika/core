package org.nasdanika.party.util;

import org.nasdanika.emf.Palette;
import org.nasdanika.party.PartyPackage;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Registers party palette.
 * 
 * @author Pavel
 *
 */
public class Activator implements BundleActivator {

	public static final Palette PARTY_PALETTE = Palette.Registry.INSTANCE.create(
			"org.nasdanika.party",
			"Party",
			"Party model elements");

	@Override
	public void start(BundleContext context) throws Exception {
		PARTY_PALETTE.add(
				PartyPackage.Literals.DIRECTORY,
				PartyPackage.Literals.PERSON,
				PartyPackage.Literals.ORGANIZATIONAL_UNIT,
				PartyPackage.Literals.ORGANIZATION,
				PartyPackage.Literals.CONTACT_METHOD,
				PartyPackage.Literals.EMAIL,
				PartyPackage.Literals.PHONE,
				PartyPackage.Literals.POSTAL_ADDRESS,
				PartyPackage.Literals.WEB_ADDRESS);	
		
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// NOP

	}

}
