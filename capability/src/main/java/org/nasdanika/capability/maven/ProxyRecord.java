package org.nasdanika.capability.maven;


public record ProxyRecord(
		String type, 
		String host, 
		Integer port,
		AuthenticationRecord auth) {

}
