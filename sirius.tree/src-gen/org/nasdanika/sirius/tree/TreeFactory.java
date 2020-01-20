/**
 */
package org.nasdanika.sirius.tree;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.nasdanika.sirius.tree.TreePackage
 * @generated
 */
public interface TreeFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TreeFactory eINSTANCE = org.nasdanika.sirius.tree.impl.TreeFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Adapter Factory Tree</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Adapter Factory Tree</em>'.
	 * @generated
	 */
	AdapterFactoryTree createAdapterFactoryTree();

	/**
	 * Returns a new object of class '<em>Adapter Factory Tree Description</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Adapter Factory Tree Description</em>'.
	 * @generated
	 */
	AdapterFactoryTreeDescription createAdapterFactoryTreeDescription();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TreePackage getTreePackage();

} //TreeFactory
