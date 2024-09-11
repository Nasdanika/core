package org.nasdanika.capability.maven;

public record RemoteRepoRecord(
		String id, 
		String type, 
		String url,
		ProxyRecord proxy,
		AuthenticationRecord auth, 
		RemoteRepoRecord[] mirroredRepositories) {

}
