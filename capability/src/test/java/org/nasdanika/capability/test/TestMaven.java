package org.nasdanika.capability.test;

import java.lang.reflect.Constructor;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.nasdanika.capability.maven.ProxyRecord;
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
	

}
