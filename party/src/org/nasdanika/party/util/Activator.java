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
			"Party model elements", 
			true);

	public static final Palette RESOURCES_PALETTE = Palette.Registry.INSTANCE.create(
			"org.nasdanika.party.resources",
			"Party resources",
			"Elements which can be added to resources collections of party, role, and member", 
			false);

	public static final Palette ROLES_PALETTE = Palette.Registry.INSTANCE.create(
			"org.nasdanika.party.roles",
			"Party roles",
			"Role types", 
			false);

	public static final Palette ORG_UNITS_PALETTE = Palette.Registry.INSTANCE.create(
			"org.nasdanika.party.org-unit",
			"Organizational units",
			"Types of organizational units", 
			false);

	public static final Palette ORGANIZATIONS_PALETTE = Palette.Registry.INSTANCE.create(
			"org.nasdanika.party.organization",
			"Organizations",
			"Types of organizations", 
			false);

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
		
		RESOURCES_PALETTE.add(
				PartyPackage.Literals.RESOURCE_CATEGORY,
				PartyPackage.Literals.MARKDOWN_TEXT,
				PartyPackage.Literals.MARKDOWN_RESOURCE,
				PartyPackage.Literals.HTML_TEXT,
				PartyPackage.Literals.HTML_RESOURCE,
				PartyPackage.Literals.RESOURCE_REFERENCE);	

		ROLES_PALETTE.add(
				PartyPackage.Literals.ROLE);
		
		ORG_UNITS_PALETTE.add(
				PartyPackage.Literals.ORGANIZATIONAL_UNIT);	
		
		ORGANIZATIONS_PALETTE.add(
				PartyPackage.Literals.ORGANIZATION);	
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// NOP

	}

}
