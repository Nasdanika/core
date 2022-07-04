package org.nasdanika.emf;

import java.io.IOException;
import java.util.Collection;

import org.eclipse.emf.ecore.EClassifier;

/**
 * Interface for generators of diagrams from text. 
 * @author Pavel
 *
 */
public interface DiagramTextGenerator {
	
	enum RelationshipDirection { in, out, both } 
	
	void appendWithRelationships(Collection<? extends EClassifier> coreClassifiers,	RelationshipDirection direction, int depth) throws IOException;
}
