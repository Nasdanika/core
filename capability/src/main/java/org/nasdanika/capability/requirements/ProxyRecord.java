package org.nasdanika.capability.requirements;


public record ProxyRecord(
		String type, 
		String host, 
		Integer port,
		AuthenticationRecord auth) {

}
