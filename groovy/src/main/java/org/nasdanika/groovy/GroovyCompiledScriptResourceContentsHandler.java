package org.nasdanika.groovy;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.capability.emf.ResourceContentsHandler;


public class GroovyCompiledScriptResourceContentsHandler implements ResourceContentsHandler<CompiledScript> {
	
	private URI uri;

	public GroovyCompiledScriptResourceContentsHandler(URI uri) {
		this.uri = uri;
	}

	@Override
	public Order getOrder() {
		return Order.of(0);
	}

	@Override
	public CompiledScript load(InputStream inputStream, Map<?, ?> options) throws IOException {
		String source = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

		ScriptEngine engine = new ScriptEngineManager().getEngineByExtension("groovy");
		if (!(engine instanceof Compilable)) {
			throw new IOException("Groovy JSR-223 engine is not Compilable on the classpath");
		}
		try {
			// compiled, NOT evaluated; syntax errors surface here, at load time
			return ((Compilable) engine).compile(source);
		} catch (ScriptException e) {
			throw new IOException("Failed to compile " + uri, e);
		}
	}	

}
