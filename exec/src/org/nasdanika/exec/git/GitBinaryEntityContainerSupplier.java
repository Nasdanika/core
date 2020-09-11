package org.nasdanika.exec.git;

import java.io.File;
import java.util.Collection;

import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.FileSystemContainer;

/**
 * Wraps the repository directory into {@link BinaryEntityContainer}.
 * @author Pavel
 *
 */
public class GitBinaryEntityContainerSupplier extends GitExecutionParticipant implements Supplier<BinaryEntityContainer> {

	protected GitBinaryEntityContainerSupplier(
			String name, 
			File repoDir, 
			String origin, 
			String branch,
			CredentialsProvider credentialsProvider, 
			boolean clean, 
			Collection<String> addPatterns, 
			String commitMessage,
			PersonIdent author, 
			String tag, 
			boolean forceTag) {
		
		super(name, repoDir, origin, branch, credentialsProvider, clean, addPatterns, commitMessage, author, tag, forceTag);
	}

	@Override
	public BinaryEntityContainer execute(ProgressMonitor progressMonitor) throws Exception {
		return new FileSystemContainer(repositoryDirectory);
	}
	
}
