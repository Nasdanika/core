package org.nasdanika.capability.maven;

public record DependenciesRequestRecord(
		String[] dependencies,
		String[] managedDependencies,
		RemoteRepoRecord[] repoteRepositories,
		String localRepository) {

}
