package org.nasdanika.capability.maven;

public record DependencyRequestRecord(
		String[] dependencies,
		String[] managedDependencies,
		RemoteRepoRecord[] remoteRepositories,
		String localRepository) {
	
	
}
