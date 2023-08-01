package org.nasdanika.graph.emf;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * Connection from an {@link EObject} instance node to its {@link EClass} node.
 * @author Pavel
 *
 */
public class EClassConnection extends Connection {
	
	/**
	 * @param source
	 * @param target
	 */
	protected EClassConnection(EObjectNode source, EObjectNode target) {
		super(source, target, false);
	}
	
}
