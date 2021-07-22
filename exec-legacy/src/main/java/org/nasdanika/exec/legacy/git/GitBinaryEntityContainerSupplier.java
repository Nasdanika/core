package org.nasdanika.exec.legacy.git;

import java.io.File;
import java.util.Collection;
import java.util.concurrent.CancellationException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.PushResult;
import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.FileSystemContainer;

/**
 * Wraps the repository directory into {@link BinaryEntityContainer}.
 * @author Pavel
 *
 */
public class GitBinaryEntityContainerSupplier extends GitExecutionParticipant implements Supplier<BinaryEntityContainer> {
	
	protected FileSystemContainer fileSystemContainer;
	private Consumer<BinaryEntityContainer> onPushConsumer; 
	
	/**
	 * 
	 * @param name
	 * @param repoDir
	 * @param origin
	 * @param branch
	 * @param branchStartPoint
	 * @param credentialsProvider
	 * @param clean
	 * @param addPatterns
	 * @param commitMessage
	 * @param author
	 * @param tag
	 * @param forceTag
	 * @param gitConfigurator if not null this consumer's accept() method is called with the Git instance. Allows to configure Git, e.g. set sslVerify to false.
	 * @param onPushConsumer Executed upon successful push with repository directory container argument.
	 */
	protected GitBinaryEntityContainerSupplier(
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
			boolean forceTag,
			java.util.function.Consumer<Git> gitConfigurator,
			Consumer<BinaryEntityContainer> onPushConsumer) {
		
		super(name, repoDir, origin, branch, branchStartPoint, credentialsProvider, clean, addPatterns, commitMessage, author, tag, forceTag, gitConfigurator);
		this.onPushConsumer = onPushConsumer;
	}

	@Override
	public BinaryEntityContainer execute(ProgressMonitor progressMonitor) throws Exception {
		fileSystemContainer = new FileSystemContainer(repositoryDirectory);
		return fileSystemContainer;
	}
	
	@Override
	protected void onPush(
			Iterable<PushResult> pushResults, 
			Iterable<PushResult> tagPushResults,
			ProgressMonitor progressMonitor) throws Exception {
		super.onPush(pushResults, tagPushResults, progressMonitor);
		if (onPushConsumer != null) {
			onPushConsumer.execute(fileSystemContainer, progressMonitor);
		}
	}
	
	@Override
	public double size() {
		return onPushConsumer == null ? super.size() : super.size() + onPushConsumer.size();
	}

	@Override
	public Diagnostic diagnose(ProgressMonitor progressMonitor) {	
		if (onPushConsumer == null) {
			return super.diagnose(progressMonitor);
		}
		if (progressMonitor.isCancelled()) {
			progressMonitor.worked(1, "Cancelled");
			return new BasicDiagnostic(Status.CANCEL, "Progress monitor is cancelled", this);
		}
		BasicDiagnostic ret = new BasicDiagnostic(Status.INFO, name());
		progressMonitor.setWorkRemaining(size());
		ret.add(super.diagnose(progressMonitor));
		ret.add(onPushConsumer.splitAndDiagnose(progressMonitor));
		return ret;
	}
	
	@Override
	public void commit(ProgressMonitor progressMonitor) throws Exception {
		if (onPushConsumer == null) {
			super.commit(progressMonitor);
		} else {
			if (progressMonitor.isCancelled()) {
				progressMonitor.worked(1, "Cancelled");
				throw new CancellationException();
			}
			progressMonitor.setWorkRemaining(size());
			super.commit(progressMonitor);
			onPushConsumer.splitAndCommit(progressMonitor);
		}
	}
	
	@Override
	public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
		if (onPushConsumer == null) {
			return super.rollback(progressMonitor);
		}
		if (progressMonitor.isCancelled()) {
			progressMonitor.worked(1, "Cancelled");
			throw new CancellationException();
		}
		progressMonitor.setWorkRemaining(size());
		boolean result = onPushConsumer.splitAndRollback(progressMonitor);
		return super.rollback(progressMonitor) && result; 
	}
	
	@Override
	public void close() throws Exception {
		if (onPushConsumer != null) {
			onPushConsumer.close();
		}
		super.close();
	}	
	
}
