package org.nasdanika.html.emf;

import java.util.List;

import org.eclipse.emf.cdo.CDOObject;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.html.app.Delta;
import org.nasdanika.html.app.Diagnostic;
import org.nasdanika.html.app.SingleValueDataSource;

/**
 * Wraps {@link EObject} into a {@link SingleValueDataSource}.
 * @author Pavel
 *
 */
public class EObjectSingleValueDataSource<T extends EObject> extends EObjectAdaptable<T> implements SingleValueDataSource {
	
	public EObjectSingleValueDataSource(T target) {
		super(target);
	}

	@Override
	public Object getVersion(Object obj) {
		if (obj instanceof CDOObject) {
			return ((CDOObject) obj).cdoRevision();			
		}
		
		if (obj instanceof EObject) {
			return ((EObject) obj).eResource().getTimeStamp();
		}
		
		return null;
	}

	@Override
	public Diagnostic update(Object obj, Object version, List<Delta> deltas) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public Object getValue() {
		return target;
	}
	
	@Override
	public String toString() {
		return super.toString() + ", value="+target;
	}

}
