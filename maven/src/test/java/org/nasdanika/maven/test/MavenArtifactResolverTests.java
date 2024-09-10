package org.nasdanika.maven.test;

import org.apache.maven.repository.internal.MavenRepositorySystemUtils;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.collection.CollectRequest;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.graph.DependencyNode;
import org.eclipse.aether.repository.Authentication;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.Proxy;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.DependencyRequest;
import org.eclipse.aether.supplier.RepositorySystemSupplier;
import org.eclipse.aether.util.graph.visitor.PreorderNodeListGenerator;
import org.eclipse.aether.util.repository.AuthenticationBuilder;
import org.junit.jupiter.api.Test;

public class MavenArtifactResolverTests {
		
	private static final String COORDINATES = "org.apache.groovy:groovy-all:pom:4.0.22";
	
	@Test
	public void testResolve() throws Exception {
		DefaultArtifact artifact = new DefaultArtifact(COORDINATES);
		Dependency dependency = new Dependency(artifact, "compile");
		
		CollectRequest collectRequest = new CollectRequest();
		collectRequest.setRoot(dependency);
		
		// Proxy
//		Proxy proxy = new Proxy(Proxy.TYPE_HTTP, "todo", 8888);
		
		// Authentication
//		Authentication authentication = new AuthenticationBuilder().addUsername("todo").addPassword("todo").build();
		
		RemoteRepository remoteRepo = new RemoteRepository.Builder("central", "default", "https://repo.maven.apache.org/maven2/")
//			.setAuthentication(authentication)
//			.setProxy(proxy)
//			.set...	
			.build();
		
		collectRequest.addRepository(remoteRepo);
		
		RepositorySystemSupplier repositorySystemSupplier = new RepositorySystemSupplier();
		RepositorySystem repositorySystem = repositorySystemSupplier.get();
		DefaultRepositorySystemSession session = MavenRepositorySystemUtils.newSession();
		LocalRepository localRepo = new LocalRepository("target/test-repository");
		session.setLocalRepositoryManager(repositorySystem.newLocalRepositoryManager(session, localRepo));
		
		DependencyNode node = repositorySystem.collectDependencies(session, collectRequest).getRoot();
		DependencyRequest request = new DependencyRequest();
		request.setRoot(node);
		repositorySystem.resolveDependencies(session, request);
		
		PreorderNodeListGenerator preorderNodeListGenerator = new PreorderNodeListGenerator();
		node.accept(preorderNodeListGenerator);
		
		for (Artifact art: preorderNodeListGenerator.getArtifacts(false)) {
			System.out.println(art.getFile().getAbsolutePath());
		}
		
		// Can build URL classloader or layer here - url.sadd(artifact.getFile().toURI().toURL()); new URLClassLoader(urls.toArray(new URL[urls.size()], parent);
		
	}

}
