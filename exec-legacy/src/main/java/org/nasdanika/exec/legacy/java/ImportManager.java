package org.nasdanika.exec.legacy.java;

import java.util.Collection;
import java.util.function.Function;

/**
 * Service interface used by generators contributing to a compilation unit.
 * Import manager is a function so it can be conveniently used by interpolators, e.g. if it is put under <code>import</code> key, then <code>{{import:java.io.InputStream}}</code>
 * will add <code>java.io.InputStream</code> import to the import manager and expand into the return value, e.g. <code>InputStream</code>.
 * @author Pavel
 *
 */
public interface ImportManager extends Function<String, Object> {
	
	/**
	 * Adds fully qualified name to the list of imports. 
	 * @param fullyQualifiedTypeName Fully qualified type name.
	 * @return Short type name if import was added or fully qualified name if there is a name clash.
	 */
	String addImport(String fullyQualifiedTypeName);
	
	@Override
	default Object apply(String name) {
		return addImport(name);
	}
	
	/**
	 * 
	 * @return List of imports.
	 */
	Collection<String> getImports();

}
