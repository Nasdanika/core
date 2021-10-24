/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Service Provider</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.ServiceProvider#getServices <em>Services</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getServiceProvider()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/service-provider.md'"
 * @generated
 */
public interface ServiceProvider<T extends PackageElement<T>> extends PackageElement<T> {
	/**
	 * Returns the value of the '<em><b>Services</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.nasdanika.flow.Activity<?>},
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Services provided by a participant. Participant service activities imply the containing participant. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Services</em>' map.
	 * @see org.nasdanika.flow.FlowPackage#getServiceProvider_Services()
	 * @model mapType="org.nasdanika.flow.ActivityEntry&lt;org.eclipse.emf.ecore.EString, org.nasdanika.flow.Activity&lt;?&gt;&gt;"
	 * @generated
	 */
	EMap<String, Activity<?>> getServices();

} // ServiceProvider
