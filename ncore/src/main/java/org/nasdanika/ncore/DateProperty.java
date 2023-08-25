/**
 */
package org.nasdanika.ncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Date Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A named/keyed integer.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.ncore.NcorePackage#getDateProperty()
 * @model superTypes="org.nasdanika.ncore.Date org.nasdanika.ncore.ValueObjectProperty&lt;org.eclipse.emf.ecore.EDate&gt;"
 * @generated
 */
public interface DateProperty extends Date, ValueObjectProperty<java.util.Date> {
	
	static DateProperty wrap(java.lang.String name, java.util.Date val) {
		DateProperty ret = NcoreFactory.eINSTANCE.createDateProperty();
		ret.setName(name);
		ret.setValue(val);
		return ret;
	}	

} // DateProperty
