package org.nasdanika.maven;

import java.io.File;
import java.io.FileInputStream;
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
import org.nasdanika.capability.CapabilityFactory;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.maven.AuthenticationRecord;
import org.nasdanika.capability.maven.DependencyRequestRecord;
import org.nasdanika.capability.maven.ProxyRecord;
import org.nasdanika.capability.maven.RemoteRepoRecord;
import org.nasdanika.common.Context;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.yaml.snakeyaml.Yaml;

import reactor.core.publisher.Flux;

public class DependencyCapabilityFactory implements CapabilityFactory<DependencyRequestRecord, Collection<File>> {

	private static final String DEFAULT_CONFIG_NAME = "dependency-reolver-config.yml";

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

	protected List<File> resolveDependencies(DependencyRequestRecord requirement) {
		String config = System.getProperty(DependencyCapabilityFactory.class.getName()+".config");
		if (config == null) {
			config = System.getenv("NSD_DEPENDENCY_RESOLVER_CONFIG");
		}
		if (config == null) {
			File configFile = new File(DEFAULT_CONFIG_NAME);
			if (configFile.isFile()) {
				config = configFile.getName();
			}
		} else {
			File configFile = new File(config);
			if (!configFile.isFile()) {
				throw new NasdanikaException("Dependency resolver config file does not exist: " + configFile.getAbsolutePath());
			}
		}
		
		try {
			DependencyRequestRecord configRecord = null;
			if (config != null) {
				Yaml yaml = new Yaml();
				try (InputStream in = new FileInputStream(config)) {
					Map<?,?> configMap = yaml.load(in);
					Context systemPropertiesContext = Context.wrap(System.getProperties()::get);
					Context envContext = Context.wrap(System.getenv()::get);
					Context combinedContext = systemPropertiesContext.mount(envContext, "env.");
					Map<?, Object> interpolatedConfigMap = combinedContext.interpolate(configMap);
					Invocable ci = Invocable.of(DependencyRequestRecord.class);
					configRecord = (DependencyRequestRecord) ci.call(interpolatedConfigMap);
				}				
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
