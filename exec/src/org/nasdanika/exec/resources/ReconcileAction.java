package org.nasdanika.exec.resources;

import org.nasdanika.common.resources.Merger;
import org.nasdanika.exec.java.CompilationUnit;

/**
 * What to do if a resource being generated already exists
 * @author Pavel
 *
 */
public enum ReconcileAction {
	/**
	 * Keep the existing resource, discard generated content or resources.
	 */
	KEEP,
	/**
	 * Append new content or resources to existing.
	 */
	APPEND,
	/**
	 * Merge old and new content. Requires a {@link Merger}. some resources, e.g. {@link CompilationUnit}, may support native mergers.
	 * For {@link Container} MERGE is equivalent to APPEND.
	 */
	MERGE,
	/**
	 * Replace old content with new one. For {@link Container} it means deleting all contained resources.
	 */
	OVERWRITE,
	/**
	 * Cancel generation if a resource already exists.
	 */
	CANCEL
}
