package org.nasdanika.engineering.util;

import org.nasdanika.emf.Palette;
import org.nasdanika.engineering.EngineeringPackage;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * Registers party palette.
 * 
 * @author Pavel
 *
 */
public class Activator implements BundleActivator {

	public static final Palette ENGINEERING_PALETTE = Palette.Registry.INSTANCE.create(
			"org.nasdanika.engineering",
			"Engineering",
			"Engineering model elements", 
			true);

	@Override
	public void start(BundleContext context) throws Exception {
		ENGINEERING_PALETTE.add(
				EngineeringPackage.Literals.ENGINEERING_ORGANIZATION,
				EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT);	
		
		org.nasdanika.party.util.Activator.ROLES_PALETTE.add(
				EngineeringPackage.Literals.ENGINEER);
		
		org.nasdanika.party.util.Activator.ORG_UNITS_PALETTE.add(
				EngineeringPackage.Literals.ENGINEERING_ORGANIZATIONAL_UNIT);	
		
		org.nasdanika.party.util.Activator.ORGANIZATIONS_PALETTE.add(
				EngineeringPackage.Literals.ENGINEERING_ORGANIZATION);	
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		// NOP

	}

}
