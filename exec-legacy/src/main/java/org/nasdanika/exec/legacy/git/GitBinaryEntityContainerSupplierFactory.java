package org.nasdanika.exec.legacy.git;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.transport.PushResult;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.resources.BinaryEntityContainer;

/**
 * Factory of {@link GitBinaryEntityContainerSupplier}. Interpolates constructor arguments. 
 * @author Pavel
 *
 */
public class GitBinaryEntityContainerSupplierFactory implements SupplierFactory<BinaryEntityContainer> {
	
	private String origin;
	private String user;
	private String password;
	private String commitMessage;
	private String authorName;
	private String authorEMail;
	private String tag;
	private Object forceTag;
	private String repoDir;
	private String name;
	private Collection<String> addPatterns;
	private Object clean;
	private String branch;
	private String branchStartPoint;
	private ConsumerFactory<BinaryEntityContainer> onPushConsumerFactory;
	private Consumer<Git> gitConfigurator;

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
	 * @param branch Branch to check out. If remote branch does not exist a new one is created off the current HEAD.
	 * @param user User for clone/pull/push.
	 * @param password Password for clone/pull/push.
	 * @param clean If true the repo directory is cleaned up before any other operation, i.e. it is always clone.
	 * @param addPatterns Add file patterns.
	 * @param commitMessage Commit message.
	 * @param authorName Commit author name.
	 * @param authorEMail Commit author e-mail.
	 * @param tag Optional tag for the commit.
	 * @param forceTag If true the tag is forced and force pushed.
	 * @param gitConfigurator if not null this consumer's accept() method is called with the Git instance. Allows to configure Git, e.g. set sslVerify to false.
	 * @param onPushConsumerFactory If not null creates onPush consumer executed after succesful push.
	 */
	public GitBinaryEntityContainerSupplierFactory(
			String name, 
			String repoDir, 
			String origin, 
			String branch,
			String branchStartPoint,
			String user,
			String password,
			Object clean,
			Collection<String> addPatterns,
			String commitMessage, 
			String authorName,
			String authorEMail,
			String tag, 
			Object forceTag,
			java.util.function.Consumer<Git> gitConfigurator,
			ConsumerFactory<BinaryEntityContainer> onPushConsumerFactory) {

		this.name = name;
		this.repoDir = repoDir;
		this.origin = origin;
		this.branch = branch;
		this.branchStartPoint = branchStartPoint;
		this.user = user;
		this.password = password;
		this.clean = clean;
		this.addPatterns = addPatterns; 
		this.commitMessage = commitMessage;
		this.authorName = authorName;
		this.authorEMail = authorEMail;
		this.tag = tag;
		this.forceTag = forceTag;
		this.gitConfigurator = gitConfigurator;
		this.onPushConsumerFactory = onPushConsumerFactory;
	}

	@Override
	public Supplier<BinaryEntityContainer> create(Context context) throws Exception {
		Object iClean = clean instanceof String ? context.interpolate((String) clean) : clean;
		Object iForceTag = forceTag instanceof String ? context.interpolate((String) forceTag) : forceTag;
		return new GitBinaryEntityContainerSupplier(
				name, 
				repoDir == null ? null : new File(repoDir), 
				context.interpolateToString(origin), 
				context.interpolateToString(branch), 
				context.interpolateToString(branchStartPoint),
				Util.isBlank(user) ? null : new UsernamePasswordCredentialsProvider(context.interpolateToString(user), context.interpolateToString(password)), 
				Boolean.TRUE.equals(iClean) || "true".equals(iClean), 
				addPatterns == null ? null : addPatterns.stream().map(context::interpolateToString).collect(Collectors.toList()), 
				context.interpolateToString(commitMessage), 
				Util.isBlank(authorName) ? null : new PersonIdent(context.interpolateToString(authorName), context.interpolateToString(authorEMail)), 
				context.interpolateToString(tag), 
				Boolean.TRUE.equals(iForceTag) || "true".equals(iForceTag),
				gitConfigurator,
				onPushConsumerFactory == null ? null : onPushConsumerFactory.create(context)) {
			
			@Override
			protected void onPush(Iterable<PushResult> pushResults, Iterable<PushResult> tagPushResults, ProgressMonitor progressMonitor) throws Exception {
				super.onPush(pushResults, tagPushResults, progressMonitor);
				GitBinaryEntityContainerSupplierFactory.this.onPush(context, pushResults, tagPushResults, progressMonitor);
			}
		};
	}
	
	/**
	 * Override to add some logic to execute on-push in addition to on-push consumer.
	 * @param context
	 * @param pushResults
	 * @param tagPushResults
	 * @param progressMonitor
	 * @throws Exception
	 */
	protected void onPush(Context context, Iterable<PushResult> pushResults, Iterable<PushResult> tagPushResults, ProgressMonitor progressMonitor) throws Exception {
		
	}

}
