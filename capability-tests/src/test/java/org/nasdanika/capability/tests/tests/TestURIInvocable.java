package org.nasdanika.capability.tests.tests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.Test;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.requirements.DependencyRequestRecord;
import org.nasdanika.capability.requirements.ScriptRecord;
import org.nasdanika.capability.requirements.URIInvocableRequirement;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.yaml.snakeyaml.Yaml;

public class TestURIInvocable {
		
	@Test
	public void testStringInvocable() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		URI requirement = URI.createURI("data:value/String,Hello+World");
		Iterable<CapabilityProvider<Object>> cpi = capabilityLoader.load(
				ServiceCapabilityFactory.createRequirement(Invocable.class, null, requirement),
				progressMonitor);
		for (CapabilityProvider<Object> cp: cpi) {
			Invocable invocable = (Invocable) cp.getPublisher().blockFirst();
			Object result = invocable.invoke();
			System.out.println(result);
		}
	}
	
	@Test
	public void testStringBase64Invocable() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		URI requirement = URI.createURI("data:value/String;base64,SGVsbG8=");
		Iterable<CapabilityProvider<Object>> cpi = capabilityLoader.load(
				ServiceCapabilityFactory.createRequirement(Invocable.class, null, requirement),
				progressMonitor);
		for (CapabilityProvider<Object> cp: cpi) {
			Invocable invocable = (Invocable) cp.getPublisher().blockFirst();
			Object result = invocable.invoke();
			System.out.println(result);
		}
	}
	
	@Test
	public void testConstructorInvocable() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		URI requirement = URI.createURI("data:java/org.nasdanika.capability.tests.MyTestClass;base64,SGVsbG8=");
		Iterable<CapabilityProvider<Object>> cpi = capabilityLoader.load(
				ServiceCapabilityFactory.createRequirement(Invocable.class, null, requirement),
				progressMonitor);
		for (CapabilityProvider<Object> cp: cpi) {
			Invocable invocable = (Invocable) cp.getPublisher().blockFirst();
			Object result = invocable.invoke();
			System.out.println(result);
		}
	}
	
	@Test
	public void testStaticMethodInvocable() {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		URI requirement = URI.createURI("data:java/org.nasdanika.capability.tests.MyTestClass::factory;base64,SGVsbG8=");
		Iterable<CapabilityProvider<Object>> cpi = capabilityLoader.load(
				ServiceCapabilityFactory.createRequirement(Invocable.class, null, requirement),
				progressMonitor);
		for (CapabilityProvider<Object> cp: cpi) {
			Invocable invocable = (Invocable) cp.getPublisher().blockFirst();
			Object result = invocable.invoke();
			System.out.println(result);
		}
	}
	
	@Test
	public void testJavaJsonSpec() throws IOException {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		URI specUri = URI.createFileURI(new File("test-specs/java.json").getCanonicalPath());
		Invocable invocable = capabilityLoader.loadOne(
				ServiceCapabilityFactory.createRequirement(Invocable.class, null, new URIInvocableRequirement(specUri)),
				progressMonitor);
		Object result = invocable.invoke();
		System.out.println(result);
	}
	
	@Test
	public void testScriptRecord() {
		String spec = """
				bindings: 
				  a: b
				""";
		
		Yaml yaml = new Yaml();
		Map<?,?> config = yaml.load(spec);
		Invocable ci = Invocable.of(ScriptRecord.class);
		Object result = ci.call(config);
		System.out.println(result);		
	}
		
	@Test
	public void testGroovyYamlSpec() throws IOException {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor(true);
		URI specUri = URI.createFileURI(new File("test-specs/groovy.yml").getCanonicalPath());
		Invocable invocable = capabilityLoader.loadOne(
				ServiceCapabilityFactory.createRequirement(Invocable.class, null, new URIInvocableRequirement(specUri)),
				progressMonitor);
		Object result = invocable.invoke();
		System.out.println(result);
	}
	
//	"""
//    c2NyaXB0OgogIGVuZ2luZUZhY3Rvcnk6IG9yZy5jb2RlaGF1cy5ncm9vdnkuanNyMjIzLkdyb292
//    eVNjcmlwdEVuZ2luZUZhY3RvcnkKICBzb3VyY2U6IHwKICAgICJIZWxsbywgd29ybGQhIgpkZXBl
//    bmRlbmNpZXM6IG9yZy5hcGFjaGUuZ3Jvb3Z5Omdyb292eS1hbGw6cG9tOjQuMC4yMgpsb2NhbFJl
//    cG9zaXRvcnk6IHRhcmdldC9ncm9vdnktdGVzdC1yZXBv				
//	"""	
	
	//"";
	
	@Test
	public void testGroovyDataYamlSpec() throws IOException {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		URI specUri = URI.createURI("data:application/yaml/invocable;base64,c2NyaXB0OgogIGVuZ2luZUZhY3Rvcnk6IG9yZy5jb2RlaGF1cy5ncm9vdnkuanNyMjIzLkdyb292eVNjcmlwdEVuZ2luZUZhY3RvcnkKICBzb3VyY2U6IHwKICAgICJIZWxsbywgd29ybGQhIgpkZXBlbmRlbmNpZXM6IG9yZy5hcGFjaGUuZ3Jvb3Z5Omdyb292eS1hbGw6cG9tOjQuMC4yMgpsb2NhbFJlcG9zaXRvcnk6IHRhcmdldC9ncm9vdnktdGVzdC1yZXBv");
		Invocable invocable = capabilityLoader.loadOne(
				ServiceCapabilityFactory.createRequirement(Invocable.class, null, new URIInvocableRequirement(specUri)),
				progressMonitor);
		Object result = invocable.invoke();
		System.out.println(result);
	}
	
	@Test
	public void testDependenciesRequestRecord() {
		String spec = """
				dependencies: org.apache.groovy:groovy-all:pom:4.0.23
				remoteRepositories:
				  id: central
				  type: default
				  url: https://repo.maven.apache.org/maven2/
				  proxy:
				    type: http
				    host: my-host
				    port: 8080
				  auth:
				    username: Joe
				    password: Doe  
				  mirroredRepositories:
				    id: not-so-central  
				""";
		
		Yaml yaml = new Yaml();
		Map<?,?> config = yaml.load(spec);
		Invocable ci = Invocable.of(DependencyRequestRecord.class);
//		BiFunction<Object,Class<?>, Optional<Object>> converter = (source, type) -> {
//			if (type == URL.class && source instanceof String) {
//				try {
//					return Optional.of(new URL((String) source)); // Possibly resolve
//				} catch (MalformedURLException e) {
//					throw new IllegalArgumentException(e);
//				}  
//			}
//			return null;
//		};
		Object result = ci.call(config /*, converter */);
		System.out.println(result);		
	}
	
	@Test
	public void testDependenciesRequestRecordLIst() {
		String spec = """
				- org.apache.groovy:groovy-all:pom:4.0.23
				- 
				- id: central
				  type: default
				  url: https://repo.maven.apache.org/maven2/
				  proxy:
				    type: http
				    host: my-host
				    port: 8080
				  auth:
				    - Joe
				    - Doe  
				  mirroredRepositories:
				    id: not-so-central  
				-  
				""";
		
		Yaml yaml = new Yaml();
		List<?> config = yaml.load(spec);
		Invocable ci = Invocable.of(DependencyRequestRecord.class);
		Object result = ci.call(config);
		System.out.println(result);		
	}
	
//	@Test
//	public void testDependenciesRequestRecord() {
//		String spec = """
//				dependencies: purum
//				id: central
//				type: default
//				url: https://repo.maven.apache.org/maven2/
//				proxy:
//				  type: http
//				  host: my-host
//				  port: 8080
//				auth:
//				  username: Joe
//				  password: Doe  
//				mirroredRepositories:
//				  id: not-so-central  
//				""";
//		
//		Yaml yaml = new Yaml();
//		Map<?,?> config = yaml.load(spec);
//		Invocable ci = Invocable.of(DependencyRequestRecord.class);
//		Object result = ci.call(config);
//		System.out.println(result);		
//	}
		
	@Test
	public void testScriptInvocable() {
//		CapabilityLoader capabilityLoader = new CapabilityLoader();
//		ClassLoaderRequirement requirement = new ClassLoaderRequirement(
//				null, // String[] modulePath,
//				null, // String[] rootModules,
//				new ModuleLayer[] { getClass().getModule().getLayer() }, 
//				getClass().getClassLoader(), // ClassLoader parentClassLoader,
//				true, // boolean singleLayerClassLoader,				
//				new String[] { "org.apache.groovy:groovy-all:pom:4.0.23" }, 
//				null, 
//				null, 
//				"target/test-repo",
//				System.out::println);
//		
//		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
//		Iterable<CapabilityProvider<Object>> cpi = capabilityLoader.load(
//				ServiceCapabilityFactory.createRequirement(ClassLoader.class, null, requirement),
//				progressMonitor);
//		for (CapabilityProvider<Object> cp: cpi) {
//			ClassLoader result = (ClassLoader) cp.getPublisher().blockFirst();
//			System.out.println(result == requirement.parentClassLoader());
//		}
	}

}
