package org.nasdanika.exec.git;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CheckoutCommand;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.CreateBranchCommand.SetupUpstreamMode;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand.ListMode;
import org.eclipse.jgit.api.PullCommand;
import org.eclipse.jgit.api.PullResult;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ExecutionParticipant;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;

/**
 * This execution participant clones or pulls a repository in diagnose() and commits and pushes changes in commit().
 * Commit is done only if there are changes and push is done only if origin is not blank. 
 * As such execute() of subclasses has access to the repository directory and to an instance of {@link Git}.
 * This class clones or pulls depending on whether the working directory contains .git directory.
 * It may operate in a temporary directory and delete it after execution.
 * It can also check out a branch and create a new branch if one doesn't already exist.  
 * @author Pavel
 *
 */
public abstract class GitExecutionParticipant implements ExecutionParticipant {
	
	protected String origin;
	protected CredentialsProvider credentialsProvider;
	protected String commitMessage;
	protected PersonIdent author;
	protected String authorEMail;
	protected String tag;
	protected boolean forceTag;
	protected File repoDir;
	protected String name;
	protected Collection<String> addPatterns = new ArrayList<>();
	protected boolean clean;
	protected String branch;
	protected String branchStartPoint;

	/**
	 * @return Temporary directory prefix. This implementation returns "git-supplier-", override if needed.
	 */
	protected String getTempDirPrefix() {
		return "git-supplier-"; 
	}
		
	protected File createTemporaryWorkingDirectory() throws IOException {
		return Files.createTempDirectory(getTempDirPrefix()).toFile();		
	}	

	/**
	 * 
	 * @param name Participant name
	 * @param repoDir Git repository working directory. If null then a temporary directory is created. In this case origin must be provided for cloning.
	 * @param origin Origin to pull from if repoDir is null or does not exist. If origin is not null then a push is performed after commit.
	 * @param branch Branch to check out. If remote branch does not exist a new one is created off the current HEAD or branchStartPoint if it is not blank.
	 * @param branchStartPoint Branch start point used to create a new branch. If it is blank then a new branch is created off the current HEAD.
	 * @param credentialsProvider Credentials provider for clone/pull/push.
	 * @param clean If true the repo directory is cleaned up before any other operation, i.e. it is always clone.
	 * @param addPatterns Add file patterns.
	 * @param commitMessage Commit message.
	 * @param author Commit author. Can be null if execute() doesn't introduce changes to be committed.
	 * @param tag Optional tag for the commit.
	 * @param forceTag If true the tag is forced and force pushed.
	 */
	protected GitExecutionParticipant(
			String name, 
			File repoDir, 
			String origin, 
			String branch,
			String branchStartPoint,
			CredentialsProvider credentialsProvider,
			boolean clean,
			Collection<String> addPatterns,
			String commitMessage, 
			PersonIdent author,
			String tag, 
			boolean forceTag) {

		// TODO Check for correct argument combinations
		this.name = name;
		this.repoDir = repoDir;
		this.origin = origin;
		this.branch = branch;
		this.branchStartPoint = branchStartPoint;
		this.credentialsProvider = credentialsProvider;
		this.clean = clean;
		if (addPatterns != null) {
			this.addPatterns.addAll(addPatterns);
		}
		this.commitMessage = commitMessage;
		this.author = author;
		this.tag = tag;
		this.forceTag = forceTag;
	}
		
	protected org.eclipse.jgit.lib.ProgressMonitor wrap(ProgressMonitor progressMonitor) {
		Stack<ProgressMonitor> monitors = new Stack<>(); 
		
		return new org.eclipse.jgit.lib.ProgressMonitor() {

			@Override
			public void beginTask(String name, int size) {
				if (!monitors.isEmpty()) {
					ProgressMonitor subMonitor = monitors.peek().split(name, 1);
					subMonitor.setWorkRemaining(size);
					monitors.push(subMonitor);
				}				
			}

			@Override
			public void endTask() {
				if (!monitors.isEmpty()) {
					monitors.pop().close();
				}
			}

			@Override
			public boolean isCancelled() {
				return monitors.isEmpty() ? false : monitors.peek().isCancelled();
			}

			@Override
			public void start(int totalWork) {
				if (!monitors.isEmpty()) {
					monitors.peek().setWorkRemaining(totalWork);
				}
			}

			@Override
			public void update(int completed) {
				if (!monitors.isEmpty()) {
					monitors.peek().worked(completed, null); 
				}				
			}
			
		};
	}

	@Override
	public double size() {
		return 1; 
	}

	@Override
	public String name() {
		return name;
	}
	
	protected File repositoryDirectory;
	protected File gitDir;
	protected Git git;
	
	/**
	 * Checks configuration and clones or pulls repository. Checks out a branch.
	 * @param progressMonitor
	 * @return
	 */
	@Override
	public Diagnostic diagnose(ProgressMonitor progressMonitor) {				
		try {
			repositoryDirectory = repoDir == null ? createTemporaryWorkingDirectory() : repoDir;
		} catch (IOException e) {
			return new BasicDiagnostic(Status.ERROR, "Could not create a temporary working directory: "+ e, e);
		}
		if (clean) {
			try {
				delete(repositoryDirectory.listFiles());
			} catch (IOException e) {
				return new BasicDiagnostic(Status.ERROR, "Could not clean repository directory: "+ e, e);
			}
		}
		gitDir = new File(repositoryDirectory, ".git");
		if (Util.isBlank(origin) && !gitDir.isDirectory()) {
			return new BasicDiagnostic(Status.ERROR, "Origin is not provided and .git directory does not exist", repositoryDirectory.getAbsolutePath());
		}
		
		try {
			if (gitDir.exists()) {
				FileRepositoryBuilder repositoryBuilder = new FileRepositoryBuilder();
				Repository repo = repositoryBuilder
						.setGitDir(gitDir)
				        .setMustExist(true)
				        .build();
				
				git = new Git(repo);

				// Pull if origin is not blank.
				if (!Util.isBlank(origin)) {
					PullCommand pullCommand = git.pull();
					if (credentialsProvider != null) {
						pullCommand.setCredentialsProvider(credentialsProvider);
					}
					pullCommand.setRemote(origin);
					try (ProgressMonitor cloneMonitor = progressMonitor.split("Pulling repository", 1, origin, repositoryDirectory.getAbsolutePath())) {
						pullCommand.setProgressMonitor(wrap(progressMonitor));
						PullResult pullResult = pullCommand.call();
						progressMonitor.worked(1, "Pulled", pullResult);								
					}
				}
			} else {
				// Clone
				CloneCommand cloneCommand = Git.cloneRepository();
				cloneCommand.setURI(origin);							
				if (credentialsProvider != null) {
					cloneCommand.setCredentialsProvider(credentialsProvider);
				}
				cloneCommand.setDirectory(repositoryDirectory);
				try (ProgressMonitor cloneMonitor = progressMonitor.split("Cloning repository", 1, origin, repositoryDirectory.getAbsolutePath())) {
					cloneCommand.setProgressMonitor(wrap(progressMonitor));
					git = cloneCommand.call();
					progressMonitor.worked(1, "Cloned", git);								
				}																		
			}
			
			if (!Util.isBlank(branch)) {
				boolean startingFromHead = Util.isBlank(branchStartPoint);
				try (ProgressMonitor checkoutMonitor = progressMonitor.split("Checking out", 1, branch)) {
					CheckoutCommand checkoutCommand = git.checkout()
							.setProgressMonitor(wrap(checkoutMonitor))
					        .setCreateBranch(true)
					        .setName(branch);

					if (!Util.isBlank(branchStartPoint)) {
						checkoutCommand.setStartPoint(branchStartPoint);
					}
					List<Ref> branchList = git.branchList().setListMode(ListMode.REMOTE).call();
					for (Ref remoteBranch: branchList) {
						if (remoteBranch.getName().equals("refs/remotes/origin/"+branch)) {
							checkoutCommand
					        	.setUpstreamMode(SetupUpstreamMode.TRACK)
					        	.setStartPoint("origin/" + branch);								
						}
					}
					if (startingFromHead) {
						ObjectId head = git.getRepository().resolve(Constants.HEAD);
						if (head == null) {
							// No current head, e.g. an empty repository. Can't create a branch.
							return new BasicDiagnostic(Status.WARNING, "Could not create a branch as there is no current head");
						}
					}
					Ref ref = checkoutCommand.call();
					progressMonitor.worked(1, "Checked out", branch, ref);								
				}
			}					
		} catch (Exception e) {
			return new BasicDiagnostic(Status.ERROR, "Could not clone or pull repository: "+ e, e);
		}
		return ExecutionParticipant.super.diagnose(progressMonitor);
	}
	
	@Override
	public void commit(ProgressMonitor progressMonitor) throws Exception {
		AddCommand addCommand = git.add();
		for (String addPattern: addPatterns) {
			addCommand.addFilepattern(addPattern);
		}
		addCommand.call();
		if (git.status().call().hasUncommittedChanges()) {
			RevCommit revCommit = git.commit()
				.setMessage(commitMessage)
				.setAuthor(author)
				.call();
			
			progressMonitor.worked(1, "Commited", revCommit);
								
			if (!Util.isBlank(tag)) {
				Ref ref = git.tag()
					.setName(tag)
					.setForceUpdate(forceTag)
					.call();
				
				progressMonitor.worked(1, "Tagged", tag, ref);
			}

			if (!Util.isBlank(origin)) {
				PushCommand pushCommand = git.push();
				if (credentialsProvider != null) {
					pushCommand.setCredentialsProvider(credentialsProvider);
				}					
				try (ProgressMonitor pushMonitor = progressMonitor.split("Pushing changes", 1, origin, repositoryDirectory.getAbsolutePath())) {
					pushCommand.setProgressMonitor(wrap(pushMonitor));
					Iterable<PushResult> pushResults = pushCommand.call();
					for (PushResult pushResult: pushResults) {
						progressMonitor.worked(1, "Pushed", pushResult);
					}
				}
				if (!Util.isBlank(tag)) {
					PushCommand pushTagCommand = git.push();
					pushTagCommand.setPushTags();
					pushTagCommand.setForce(forceTag);
					if (credentialsProvider != null) {
						pushTagCommand.setCredentialsProvider(credentialsProvider);
					}					
					try (ProgressMonitor pushMonitor = progressMonitor.split("Pushing tag", 1, origin, repositoryDirectory.getAbsolutePath())) {
						pushTagCommand.setProgressMonitor(wrap(pushMonitor));
						Iterable<PushResult> pushResults = pushTagCommand.call();
						for (PushResult pushResult: pushResults) {
							progressMonitor.worked(1, "Pushed tag", pushResult);
						}
					}
				}
			}
		}
	}
	
	@Override
	public void close() throws Exception {
		if (git != null) {
			git.close();
		}
		if (repositoryDirectory != null && repoDir == null) {
			delete(repositoryDirectory);
		}
	}
	
	static void delete(File... files) throws IOException {
		for (File file: files) {
			if (file.exists()) {
				if (file.isDirectory()) {
					for (File child: file.listFiles()) {
						delete(child);
					}
				}
				file.setWritable(true);
				if (!file.delete()) {
					file.deleteOnExit();
				}
			}
		}
	}				

}
