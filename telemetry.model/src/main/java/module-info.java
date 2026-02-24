import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.telemetry.model.util.TelemetryEPackageResourceSetCapabilityFactory;

module org.nasdanika.telemetry.model {
	exports org.nasdanika.telemetry.model;
	exports org.nasdanika.telemetry.model.impl;
	exports org.nasdanika.telemetry.model.util;

	requires org.eclipse.emf.common;
	requires org.eclipse.emf.ecore;
	requires org.nasdanika.capability;	
	
	provides CapabilityFactory with
		TelemetryEPackageResourceSetCapabilityFactory;
	
}