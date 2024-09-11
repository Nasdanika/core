package org.nasdanika.maven.test;

import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
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
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.DependencyRequest;
import org.eclipse.aether.supplier.RepositorySystemSupplier;
import org.eclipse.aether.util.graph.visitor.PreorderNodeListGenerator;
import org.junit.jupiter.api.Test;

public class MavenArtifactResolverTests {
		
	private static final String[] COORDINATES = {
			"org.apache.groovy:groovy-all:pom:4.0.22",
			"org.springframework:spring-expression:6.1.12"
	};
	
	/**
	 * @param art
	 * @return true if the artifact shall be added to the classloader, not module
	 */
	protected boolean isModule(Artifact art) {		
//		if (art.getArtifactId().contains("antlr")) {
//			System.out.println(art.getGroupId() + " " + art.getArtifactId());
//			return true;
//		}
		return false;
	}
	
	@Test
	public void testResolve() throws Exception {		
		// Creating request
		
		List<Dependency> dependencies = Stream.of(COORDINATES)
				.map(DefaultArtifact::new)
				.map(artifact -> new Dependency(artifact, "runtime"))
				.toList();
		
		List<Dependency> managedDependencies = Collections.emptyList();
		
		// TODO - trace
		
		// Proxy
//		Proxy proxy = new Proxy(Proxy.TYPE_HTTP, "todo", 8888);
		
		// Authentication
//		Authentication authentication = new AuthenticationBuilder().addUsername("todo").addPassword("todo").build();
		
		RemoteRepository remoteRepo = new RemoteRepository.Builder("central", "default", "https://repo.maven.apache.org/maven2/")
//			.setAuthentication(authentication)
//			.setProxy(proxy)
//			.set...	
			.build();

		List<RemoteRepository> remoteRepos = Collections.singletonList(remoteRepo);
		CollectRequest collectRequest = new CollectRequest(dependencies, managedDependencies, remoteRepos);		
		
		// Collecting 
		
		RepositorySystemSupplier repositorySystemSupplier = new RepositorySystemSupplier();
		RepositorySystem repositorySystem = repositorySystemSupplier.get();
		DefaultRepositorySystemSession session = MavenRepositorySystemUtils.newSession();
		LocalRepository localRepo = new LocalRepository("target/test-repository");
		session.setLocalRepositoryManager(repositorySystem.newLocalRepositoryManager(session, localRepo));
		
		// Resolving
		
		CollectResult collectResult = repositorySystem.collectDependencies(session, collectRequest);
		DependencyNode node = collectResult.getRoot();
		DependencyRequest request = new DependencyRequest();
		request.setRoot(node);
		repositorySystem.resolveDependencies(session, request);
		
		PreorderNodeListGenerator preorderNodeListGenerator = new PreorderNodeListGenerator();
		node.accept(preorderNodeListGenerator);
		
		// Building classloader and module layer
		
		List<URL> classPath = new ArrayList<>();
		List<Path> modulePath = new ArrayList<>();
		for (Artifact art: preorderNodeListGenerator.getArtifacts(false)) {
			if ("jar".equals(art.getExtension())) {
				if (isModule(art)) {
					modulePath.add(art.getFile().toPath());
				} else {
					classPath.add(art.getFile().toURI().toURL());
				}
			}
		}
		
		ModuleFinder finder = ModuleFinder.of(modulePath.toArray(size -> new Path[size]));
		Module thisModule = getClass().getModule(); // From requirement defaulting to ? 
		ClassLoader parentClassLoader = thisModule.getClassLoader(); // From requirement
		if (!classPath.isEmpty()) {
			parentClassLoader = new URLClassLoader(classPath.toArray(size -> new URL[size]), parentClassLoader);
		}
		String className = "org.apache.groovy.groovysh.Groovysh";
		if (modulePath.isEmpty()) {
			Class<?> c = parentClassLoader.loadClass(className);
			System.out.println(c);			
		} else {
			ModuleLayer parentLayer = thisModule.getLayer();
			Configuration parentConfiguration = parentLayer.configuration();
			Configuration config = parentConfiguration.resolveAndBind(finder, ModuleFinder.of(), Set.of()); // Configurable from requirement
			
			// TODO - excludes
			
			ModuleLayer newLayer = parentLayer.defineModulesWithOneLoader(config, parentClassLoader);
			newLayer.modules().forEach(System.out::println);
			String firstModuleName = newLayer.modules().iterator().next().getName(); // Configurable from requirement
			Class<?> c = newLayer.findLoader(firstModuleName).loadClass(className);
			System.out.println(c);
		}
	}	

}
