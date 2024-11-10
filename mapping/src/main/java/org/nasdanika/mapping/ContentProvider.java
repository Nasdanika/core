package org.nasdanika.mapping;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.persistence.Marked;

/**
 * Provider of parent, children, base URI, connection source/target, outgoing/incoming connections, etc.
 * @param <S>
 */
public interface ContentProvider<S> {
	
	S getParent(S element);
	
	/**
	 * @param element
	 * @param predicate if not null, use to prevent infinite loops - return only elements which match the predicate.
	 * @return
	 */
	Collection<? extends S> getChildren(S element /*, Predicate<S> predicate */);
		
	URI getBaseURI(S element);
	
	Object getProperty(S element, String property);
	
	Marked asMarked(S element);
		
	/**
	 * If the element is a connection/association - returns its source. 
	 * Otherwise returns null.
	 * @param connection
	 * @return
	 */
	S getConnectionSource(S element);
	
	/**
	 * If the argument is a connection/association - returns its target.
	 * Otherwise returns null.
	 * @param connection
	 * @return
	 */
	S getConnectionTarget(S element);	
	
	/**
	 * Element name, e.g. Drawio element label.
	 * @param element
	 * @return
	 */
	String getName(S element);
	
	/**
	 * Element description, e.g. Drawio element tooltip.
	 * Not documentation.
	 * @param element
	 * @return
	 */
	String getDescription(S element);

	/**
	 * Object identity such as a unique ID or a URI. 
	 * @param obj
	 * @return
	 */
	Object getIdentity(S obj);

}
