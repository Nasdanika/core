package org.nasdanika.groovy;

import org.codehaus.groovy.tools.shell.IO;
import org.nasdanika.common.Invocable;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

public class GroovyShellInvocable implements Invocable {
	
	private Binding binding = new Binding();

	@Override
	public Invocable bindByName(String name, Object binding) {
		this.binding.setProperty(name, binding);
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T invoke(Object... args) {
		binding.setProperty("args", args);
		GroovyShell groovysh = new GroovyShell(binding, new IO());
		return (T) (Object) groovysh.run(null);
	}
	
};
