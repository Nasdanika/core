package org.nasdanika.capability.test;

import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;
import org.nasdanika.capability.requirements.ClassLoaderRequirement;
import org.nasdanika.capability.requirements.DependencyRequestRecord;
import org.nasdanika.capability.requirements.ProxyRecord;
import org.nasdanika.capability.requirements.RemoteRepoRecord;
import org.nasdanika.common.Invocable;
import org.yaml.snakeyaml.Yaml;

/**
 * Tests for Maven record loading
 */
public class TestMaven {
	
	@Test
	public void testProxyRecord() {
		String spec = """
				type: http
				host: my-host
				port: 8080
				""";
		
		Yaml yaml = new Yaml();
		Map<?,?> config = yaml.load(spec);
		Constructor<?> constructor = ProxyRecord.class.getConstructors()[0];
		Invocable ci = Invocable.of(constructor);
		Object result = ci.call(config);
		System.out.println(result);		
	}
		
	@Test
	public void testRemoteRepoRecord() {
		String spec = """
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
		Constructor<?> constructor = RemoteRepoRecord.class.getConstructors()[0];
		Invocable ci = Invocable.of(constructor);
		Object result = ci.call(config);
		System.out.println(result);
		System.out.println(((RemoteRepoRecord) result).mirroredRepositories()[0]);		
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
		BiFunction<Object,Class<?>, Optional<Object>> converter = (source, type) -> {
//			System.out.println(source + " -> " + type);
			if (source instanceof String && type == URL.class) {
				try {
					return Optional.of(new URL((String) source));
				} catch (MalformedURLException e) {
					throw new IllegalArgumentException(e);
				}
			}
			return null;
		};
		Object result = ci.call(config, converter);
		System.out.println(result);		
	}
	
	@Test
	public void testClassLoaderRequirement() {
		String spec = """
				localRepository: purum
				""";
		
		Yaml yaml = new Yaml();
		Map<?,?> config = yaml.load(spec);
		Invocable ci = Invocable.of(ClassLoaderRequirement.class);
		Object result = ci.call(config);
		System.out.println(result);		
	}

}
