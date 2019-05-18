package org.nasdanika.html.emf;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;

public interface ComposeableAdapterFactory extends AdapterFactory {
	
	  ComposeableAdapterFactory getRootAdapterFactory();

	  void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory);	
	  
	  /**
	   * {@link EClass}es supported by this factory. This method is used during registration with the parent factory.
	   * @return
	   */
	  Collection<EClass> getEClasses();


}
