package org.nasdanika.maven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import org.apache.maven.repository.internal.MavenRepositorySystemUtils;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.collection.CollectRequest;
import org.eclipse.aether.collection.CollectResult;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.graph.DependencyNode;
import org.eclipse.aether.repository.Authentication;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.Proxy;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.repository.RemoteRepository.Builder;
import org.eclipse.aether.resolution.DependencyRequest;
import org.eclipse.aether.supplier.RepositorySystemSupplier;
import org.eclipse.aether.util.graph.visitor.PreorderNodeListGenerator;
import org.eclipse.aether.util.repository.AuthenticationBuilder;
import org.eclipse.emf.common.util.URI;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.nasdanika.capability.AbstractCapabilityFactory;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.requirements.AuthenticationRecord;
import org.nasdanika.capability.requirements.DependencyRequestRecord;
import org.nasdanika.capability.requirements.ProxyRecord;
import org.nasdanika.capability.requirements.RemoteRepoRecord;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.yaml.snakeyaml.Yaml;

import reactor.core.publisher.Flux;

public class DependencyCapabilityFactory extends AbstractCapabilityFactory<DependencyRequestRecord, Collection<File>> {

	private static final String CONFIG_YAML_PROPERTY = DependencyCapabilityFactory.class.getName()+".config.yml";
	private static final String CONFIG_JSON_PROPERTY = DependencyCapabilityFactory.class.getName()+".config.json";
	private static final String CONFIG_URL_YAML_ENV_VAR = "NSD_DEPENDENCY_RESOLVER_CONFIG_YAML_URL";
	private static final String CONFIG_URL_JSON_ENV_VAR = "NSD_DEPENDENCY_RESOLVER_CONFIG_JSON_URL";
	private static final String CONFIG_YAML_ENV_VAR = "NSD_DEPENDENCY_RESOLVER_CONFIG_YAML";
	private static final String CONFIG_JSON_ENV_VAR = "NSD_DEPENDENCY_RESOLVER_CONFIG_JSON";
	private static final String DEFAULT_CONFIG_NAME_YAML = "dependency-reolver-config.yml";
	private static final String DEFAULT_CONFIG_NAME_JSON = "dependency-reolver-config.json";

	@Override
	public boolean canHandle(Object requirement) {
		return DependencyRequestRecord.class.isInstance(requirement);
	}

	@Override
	public CompletionStage<Iterable<CapabilityProvider<Collection<File>>>> create(
			DependencyRequestRecord requirement,
			Loader loader,
			ProgressMonitor progressMonitor) {

		CapabilityProvider<Collection<File>> capabilityProvider = new CapabilityProvider<Collection<File>>() {
			
			@Override
			public Flux<Collection<File>> getPublisher() {
				return Flux.just(resolveDependencies(requirement));
			}
			
		};
		return CompletableFuture.completedStage(Collections.singleton(capabilityProvider));		
	}

	/**
	 * Loads config map from properties or environment variables
	 * @return
	 * @throws IOException 
	 */
	protected Map<?,?> loadConfigMap() throws IOException {
		URI base = URI.createFileURI(new File(".").getCanonicalPath()).appendSegment("");
		String config = System.getProperty(CONFIG_YAML_PROPERTY);
		if (!Util.isBlank(config)) {
			URI configURI = URI.createURI(config).resolve(base);
			Yaml yaml = new Yaml();
			try (InputStream in = DefaultConverter.INSTANCE.toInputStream(configURI)) {
				return yaml.load(in);
			}				
		}
		
		config = System.getProperty(CONFIG_JSON_PROPERTY);
		if (!Util.isBlank(config)) {
			URI configURI = URI.createURI(config).resolve(base);
			try (InputStream in = DefaultConverter.INSTANCE.toInputStream(configURI)) {
				JSONObject jsonConfig = new JSONObject(new JSONTokener(in));
				return jsonConfig.toMap();
			}				
		}
		
		config = System.getenv(CONFIG_URL_YAML_ENV_VAR);
		if (!Util.isBlank(config)) {
			URI configURI = URI.createURI(config);
			Yaml yaml = new Yaml();
			try (InputStream in = DefaultConverter.INSTANCE.toInputStream(configURI)) {
				return yaml.load(in);
			}				
		}
		
		config = System.getenv(CONFIG_URL_JSON_ENV_VAR);
		if (!Util.isBlank(config)) {
			URI configURI = URI.createURI(config).resolve(base);
			try (InputStream in = DefaultConverter.INSTANCE.toInputStream(configURI)) {
				JSONObject jsonConfig = new JSONObject(new JSONTokener(in));
				return jsonConfig.toMap();
			}				
		}
		
		config = System.getenv(CONFIG_YAML_ENV_VAR);
		if (!Util.isBlank(config)) {
			Yaml yaml = new Yaml();
			return yaml.load(config);
		}
		
		config = System.getenv(CONFIG_JSON_ENV_VAR);
		if (!Util.isBlank(config)) {
			JSONObject jsonConfig = new JSONObject(config);
			return jsonConfig.toMap();
		}
		
		File configFile = new File(DEFAULT_CONFIG_NAME_YAML);
		if (configFile.isFile()) {
			Yaml yaml = new Yaml();
			try (InputStream in = new FileInputStream(configFile)) {
				return yaml.load(in);
			}							
		}
		
		configFile = new File(DEFAULT_CONFIG_NAME_JSON);
		if (configFile.isFile()) {
			try (InputStream in = new FileInputStream(configFile)) {
				JSONObject jsonConfig = new JSONObject(new JSONTokener(in));
				return jsonConfig.toMap();
			}				
		}

		return null;
	}
	
	protected List<File> resolveDependencies(DependencyRequestRecord requirement) {		
		try {
			Map<?,?> configMap = loadConfigMap();
			DependencyRequestRecord configRecord = null;
			if (configMap != null) {
				Context systemPropertiesContext = Context.wrap(System.getProperties()::get);
				Context envContext = Context.wrap(System.getenv()::get);
				Context combinedContext = systemPropertiesContext.mount(envContext, "env.");
				Map<?, Object> interpolatedConfigMap = combinedContext.interpolate(configMap);
				Invocable ci = Invocable.of(DependencyRequestRecord.class);
				configRecord = ci.call(interpolatedConfigMap);
			}
			
			List<Dependency> dependencies = requirement.dependencies() == null ? Collections.emptyList() : Stream.of(requirement.dependencies())
					.map(DefaultArtifact::new)
					.map(artifact -> new Dependency(artifact, "runtime"))
					.toList();
			
			List<Dependency> managedDependencies = requirement.managedDependencies() == null ? Collections.emptyList() : Stream.of(requirement.managedDependencies())
					.map(DefaultArtifact::new)
					.map(artifact -> new Dependency(artifact, "runtime"))
					.toList();
			
			List<RemoteRepository> remoteRepos = new ArrayList<>();
			if (requirement.remoteRepositories() == null) {
				if (configRecord == null || configRecord.remoteRepositories() == null) {
					remoteRepos.add(new RemoteRepository.Builder("central", "default", "https://repo.maven.apache.org/maven2/").build());
				} else {
					for (RemoteRepoRecord remoteRepoRecord: configRecord.remoteRepositories()) {
						remoteRepos.add(buildRemoteRepo(remoteRepoRecord));
					}					
				}
			} else {
				for (RemoteRepoRecord remoteRepoRecord: requirement.remoteRepositories()) {
					remoteRepos.add(buildRemoteRepo(remoteRepoRecord));
				}
			}
			
			// TODO - trace

			CollectRequest collectRequest = new CollectRequest(dependencies, managedDependencies, remoteRepos);		
			
			// Collecting 
			
			RepositorySystemSupplier repositorySystemSupplier = new RepositorySystemSupplier();
			RepositorySystem repositorySystem = repositorySystemSupplier.get();
			DefaultRepositorySystemSession session = MavenRepositorySystemUtils.newSession();
			
			String localRepoName = requirement.localRepository();
			if (localRepoName == null) {
				if (configRecord == null || configRecord.localRepository() == null) {
					localRepoName = "repository"; 			
				} else {
					localRepoName = configRecord.localRepository();
				}
			}
			LocalRepository localRepo = new LocalRepository(localRepoName);
			session.setLocalRepositoryManager(repositorySystem.newLocalRepositoryManager(session, localRepo));
			
			// Resolving
			
			CollectResult collectResult = repositorySystem.collectDependencies(session, collectRequest);
			DependencyNode node = collectResult.getRoot();
			DependencyRequest request = new DependencyRequest();
			request.setRoot(node);
			repositorySystem.resolveDependencies(session, request);
			
			PreorderNodeListGenerator preorderNodeListGenerator = new PreorderNodeListGenerator();
			node.accept(preorderNodeListGenerator);
			
			List<File> result = new ArrayList<>();
			for (Artifact art: preorderNodeListGenerator.getArtifacts(false)) {
				if ("jar".equals(art.getExtension())) {
					result.add(art.getFile());
				}
			}
			return result;
		} catch (Exception e) {
			throw new NasdanikaException("Failed to collect dependencies for " + requirement + ": " + e, e);
		}
	}

	protected RemoteRepository buildRemoteRepo(RemoteRepoRecord remoteRepoRecord) {
		Builder builder = new RemoteRepository.Builder(remoteRepoRecord.id(), remoteRepoRecord.type(), remoteRepoRecord.url());
		ProxyRecord rProxy = remoteRepoRecord.proxy();
		if (rProxy != null) {					
			Authentication auth = null;
			if (rProxy.auth() != null) {
				auth = new AuthenticationBuilder()
						.addUsername(rProxy.auth().username())
						.addPassword(rProxy.auth().password())
						.build();
			}
			Proxy proxy = new Proxy(rProxy.type(), rProxy.host(), rProxy.port(), auth);
			builder.setProxy(proxy);
		}
		
		AuthenticationRecord rAuth = remoteRepoRecord.auth();
		if (rAuth != null) {
			Authentication auth = new AuthenticationBuilder()
					.addUsername(rAuth.username())
					.addPassword(remoteRepoRecord.auth().password())
					.build();
			builder.setAuthentication(auth);
		}
		
		RemoteRepoRecord[] mirroredRepoRecords = remoteRepoRecord.mirroredRepositories();
		if (mirroredRepoRecords != null) {
			for (RemoteRepoRecord m: mirroredRepoRecords) {
				builder.addMirroredRepository(buildRemoteRepo(m));
			}
		}
		
		return builder.build();
	}

}
