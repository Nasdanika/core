package org.nasdanika.emf.persistence.test;

import java.io.File;

import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.junit.jupiter.api.Test;

public class TestJGit {

	@Test
	public void testRepository() throws Exception {
		FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder().readEnvironment();
		File current = new File(".").getCanonicalFile();
		if (current.isDirectory()) {
			System.out.println(current.getAbsolutePath());
			repositoryBuilder.findGitDir(current);
			if (repositoryBuilder.getGitDir() == null) {
				System.out.println("Not found");
			} else {
				try (Repository repository = repositoryBuilder.build()) {
					System.out.println(repository.getWorkTree());
					StoredConfig config = repository.getConfig();
					for (String remote: repository.getRemoteNames()) {
						System.out.println(remote + " -> " + config.getString("remote", "origin", "url"));
					}
				}
			}
		}
	}
	
}
