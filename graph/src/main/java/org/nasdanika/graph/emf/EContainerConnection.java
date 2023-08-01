package org.nasdanika.graph.emf;

import org.eclipse.emf.ecore.EObject;

/**
 * Connection from an {@link EObject} instance node to its container.
 * @author Pavel
 *
 */
public class EContainerConnection extends Connection {
	
	/**
	 * @param source
	 * @param target
	 */
	protected EContainerConnection(EObjectNode source, EObjectNode target) {
		super(source, target, false);
	}
	
}
