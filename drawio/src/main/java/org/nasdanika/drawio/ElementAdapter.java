package org.nasdanika.drawio;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;

/**
 * Adapter providing access to the {@link Element} from which a particular {@link EObject} was created.
 * @author Pavel
 *
 */
public interface ElementAdapter extends Adapter {
	
	Element getElement();

}
