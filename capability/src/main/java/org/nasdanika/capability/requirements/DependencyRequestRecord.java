package org.nasdanika.capability.requirements;

public record DependencyRequestRecord(
		String[] dependencies,
		String[] managedDependencies,
		RemoteRepoRecord[] remoteRepositories,
		String localRepository) {
	
	
}
