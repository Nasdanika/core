package org.nasdanika.exec.resources;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.jgit.lib.StoredConfig;
import org.eclipse.jgit.transport.PushResult;
import org.nasdanika.common.Command;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.exec.Loader;
import org.nasdanika.exec.git.GitBinaryEntityContainerSupplierFactory;

/**
 * Git component executes resource components in the context of a Git repository.
 * Before executing resource components it can pull or clone repository from a remote origin.
 * It can also checkout a branch create it if doesn't exist off the current HEAD.
 * 
 * After executing the resource components this component commits changes to the repository.
 * It can tag the commit and optionally force and force push.
 * If repository was cloned or pulled then upon execution of resource components the commit is pushed to the origin.
 * 
 * If working directory was not specified and origin was specified then execution is performed in a temporary directory.
 * 
 * Usage scenarios:
 * 
 * * Generation of a new code and push to a remote repository for subsequent build and deploy. on-push components can trigger a build or other actions, e.g. setting up a build job on a build server or sending notifications.
 * * Automated update of previously generated code - pull, generate, merge/patch, push. This scenario has two sub-scenarios: a) Generators or generator specifications have changes - e.g. new functionality. 
 * b) Input has changed - e.g. change in a choice of a database or a messaging system or adding/removing solution components.
 *   
 * TODO - History service - returns previous contents of a resource - tag or committer based search for revisions.
 * History service can be used by patch mergers - compute a patch between the ancestor revision which was the previous generation and the new 
 * generated contents. Apply the patch to the current revision. If the ancestor revision is the same as the current revision (no manual changes) then simply overwrite.
 * 
 * @author Pavel
 *
 */
public class Git implements CommandFactory, Marked {
		
	private static final String ORIGIN_KEY = "origin";
	private static final String CREDENTIALS_KEY = "credentials";
	private static final String USER_KEY = "user";
	private static final String PASSWORD_KEY = "password";
	private static final String COMMIT_MESSAGE_KEY = "commit-message";
	private static final String AUTHOR_KEY = "author";
	private static final String AUTHOR_NAME_KEY = "name";
	private static final String AUTHOR_EMAIL_KEY = "e-mail";
	private static final String TAG_KEY = "tag";
	private static final String FORCE_TAG_KEY = "force-tag";
	private static final String REPOSITORY_KEY = "repository";
	private static final String ADD_PATTERN_KEY = "add-pattern";
//	private static final String RECONCILE_ACTION_KEY = "reconcile-action";
	private static final String BRANCH_KEY = "branch";
	private static final String BRANCH_NAME_KEY = "name";
	private static final String BRANCH_START_POINT_KEY = "start-point";
	private static final String CONTENTS_KEY = "contents";
	private static final String MERGER_KEY = "merger";
	private static final String CLEAN_KEY = "clean";
	private static final String ON_PUSH_KEY = "on-push";
	private static final String CONFIG_KEY = "config";
	
	protected Collection<String> getSupportedKeys() {
		Collection<String> ret = new ArrayList<>();
		ret.add(ORIGIN_KEY);
		ret.add(CREDENTIALS_KEY);
		ret.add(COMMIT_MESSAGE_KEY);
		ret.add(AUTHOR_KEY);
		ret.add(TAG_KEY);
		ret.add(FORCE_TAG_KEY);
		ret.add(REPOSITORY_KEY);
		ret.add(ADD_PATTERN_KEY);
//		ret.add(RECONCILE_ACTION_KEY);
		ret.add(BRANCH_KEY);
		ret.add(CONTENTS_KEY);
		ret.add(MERGER_KEY);
		ret.add(CLEAN_KEY);
		ret.add(ON_PUSH_KEY);
		ret.add(CONFIG_KEY);
		return ret;
	}	
		
//	protected ReconcileAction reconcileAction = ReconcileAction.APPEND;
	private Marker marker;
	
	private CommandFactory factory;
	
	@Override
	public Marker getMarker() {
		return marker;
	}
	
	@SuppressWarnings("unchecked")
	public Git(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof Map) {
			this.marker = marker;
			Map<String,Object> configMap = (Map<String,Object>) config;
			Util.checkUnsupportedKeys(configMap, getSupportedKeys());
			if (configMap.containsKey(CONTENTS_KEY)) {
				ConsumerFactory<BinaryEntityContainer> contentsFactory = Loader.asConsumerFactory(loader.load(configMap.get(CONTENTS_KEY), base, progressMonitor), Util.getMarker(configMap, CONTENTS_KEY));
				
				Map<?, ?> credentials = Util.checkUnsupportedKeys(Util.getMap(configMap, CREDENTIALS_KEY, null), USER_KEY, PASSWORD_KEY);
				Map<?, ?> author = Util.checkUnsupportedKeys(Util.getMap(configMap, AUTHOR_KEY, null), AUTHOR_NAME_KEY, AUTHOR_EMAIL_KEY);
				
				Collection<String> addPatterns = new ArrayList<>();
				if (configMap.containsKey(ADD_PATTERN_KEY)) {
					Object ap = configMap.get(ADD_PATTERN_KEY);
					if (ap instanceof String) {
						addPatterns.add((String) ap);
					} else if (ap instanceof Collection) {
						addPatterns.addAll((Collection<String>) ap);
					} else {
						throw new ConfigurationException(ADD_PATTERN_KEY + " value shall be a string or a list", Util.getMarker(configMap, ADD_PATTERN_KEY));
					}
				} else {
					addPatterns.add(".");
				}
				
				Object branchConfig = configMap.get(BRANCH_KEY);
				String branch = null;
				String branchStartPoint = null;
				if (branchConfig instanceof String) {
					branch = (String) branchConfig;
				} else if (branchConfig instanceof Map) {
					Map<String, Object> branchConfigMap = Util.checkUnsupportedKeys((Map<String,Object>) branchConfig, BRANCH_NAME_KEY, BRANCH_START_POINT_KEY);
					branch = Util.getString(branchConfigMap, BRANCH_NAME_KEY, null);
					branchStartPoint = Util.getString(branchConfigMap, BRANCH_START_POINT_KEY, null);
				} else if (branchConfig != null) {
					throw new ConfigurationException("Branch value shall be a string or map", Util.getMarker(configMap, BRANCH_KEY));
				}
				
				ConsumerFactory<BinaryEntityContainer> onPushFactory = configMap.containsKey(ON_PUSH_KEY) ? Loader.asConsumerFactory(loader.load(configMap.get(ON_PUSH_KEY), base, progressMonitor), Util.getMarker(configMap, ON_PUSH_KEY)) : null;
				
				java.util.function.Consumer<org.eclipse.jgit.api.Git> gitConfigurator = null;
				
				if (configMap.containsKey(CONFIG_KEY)) {
					Map<String, Map<String, Map<String, Object>>> gitConfigMap = Util.getMap(configMap, CONFIG_KEY, null);
					gitConfigurator = git -> {						
						StoredConfig gitConfig = git.getRepository().getConfig();
						for (Entry<String, Map<String, Map<String, Object>>> se: gitConfigMap.entrySet()) {							
							for (Entry<String, Map<String, Object>> sse: se.getValue().entrySet()) {
								for (Entry<String, Object> ne: sse.getValue().entrySet()) {
									Object value = ne.getValue();
									if (value instanceof Boolean) {
										gitConfig.setBoolean(se.getKey(), sse.getKey(), ne.getKey(), (Boolean) value);
									} else if (value instanceof Integer) {
										gitConfig.setInt(se.getKey(), sse.getKey(), ne.getKey(), (Integer) value);										
									} else if (value instanceof Long) {
										gitConfig.setLong(se.getKey(), sse.getKey(), ne.getKey(), (Long) value);
									} else if (value instanceof String) {
										gitConfig.setString(se.getKey(), sse.getKey(), ne.getKey(), (String) value);										
									} else if (value instanceof List) {
										gitConfig.setStringList(se.getKey(), sse.getKey(), ne.getKey(), (List<String>) value);										
									} else {
										throw new ConfigurationException("Unsupported configuration value type: " + value.getClass(), Util.getMarker(sse.getValue(), ne.getKey()));
									}
								}
							}
						}						
					};
				}
								
				SupplierFactory<BinaryEntityContainer> repoFactory = new GitBinaryEntityContainerSupplierFactory(
						"Git", 
						Util.getString(configMap, REPOSITORY_KEY, null), 
						Util.getString(configMap, ORIGIN_KEY, null), 
						branch,
						branchStartPoint,
						credentials == null ? null : Util.getString(credentials, USER_KEY, null), 
						credentials == null ? null : Util.getString(credentials, PASSWORD_KEY, null), 
						configMap.get(CLEAN_KEY), 
						addPatterns, 
						Util.getString(configMap, COMMIT_MESSAGE_KEY, null), 
						author == null ? null : Util.getString(credentials, AUTHOR_NAME_KEY, null), 
						author == null ? null : Util.getString(credentials, AUTHOR_EMAIL_KEY, null), 
						Util.getString(configMap, TAG_KEY, null), 
						configMap.get(FORCE_TAG_KEY),
						gitConfigurator,
						onPushFactory) {
					
					@Override
					protected void onPush(Context context, Iterable<PushResult> pushResults, Iterable<PushResult> tagPushResults, ProgressMonitor progressMonitor) throws Exception {
						super.onPush(context, pushResults, tagPushResults, progressMonitor);
						Git.this.onPush(context, pushResults, tagPushResults, progressMonitor);
					}
				};
						
				this.factory = repoFactory.then(contentsFactory);
			} else {
				throw new ConfigurationException("Repository contents is required", marker);				
			}
		} else {
			throw new ConfigurationException(getClass().getName() + " configuration shall be a map, got " + config.getClass(), marker);
		}
	}
	
	@Override
	public Command create(Context context) throws Exception {
		return factory.create(context);
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
