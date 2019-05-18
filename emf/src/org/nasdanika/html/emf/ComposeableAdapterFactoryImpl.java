package org.nasdanika.html.emf;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;

/**
 * Base class for composeable adapter factories for a single EClass.
 * @author Pavel Vlasov
 *
 */
public class ComposeableAdapterFactoryImpl extends AdapterFactoryImpl implements ComposeableAdapterFactory {
	
	private ComposedAdapterFactory parentAdapterFactory;
	private EClass eClass;

	/**
	 * Uses {@link EObject}'s {@link EClass} as eClass argument
	 */
	public ComposeableAdapterFactoryImpl() {
		this(EcorePackage.Literals.EOBJECT);
	}
	
	public ComposeableAdapterFactoryImpl(EClass eClass) {
		this.eClass = eClass;
	}

	@Override
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	@Override
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;		
	}
	
	@Override
	public Collection<EClass> getEClasses() {
		return Collections.singleton(eClass);
	}

}
