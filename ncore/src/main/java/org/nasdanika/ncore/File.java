/**
 */
package org.nasdanika.ncore;

import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>File</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.File#getLength <em>Length</em>}</li>
 *   <li>{@link org.nasdanika.ncore.File#getLastModified <em>Last Modified</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getFile()
 * @model
 * @generated
 */
public interface File extends TreeItem {
	/**
	 * Returns the value of the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Length</em>' attribute.
	 * @see #setLength(long)
	 * @see org.nasdanika.ncore.NcorePackage#getFile_Length()
	 * @model
	 * @generated
	 */
	long getLength();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.File#getLength <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length</em>' attribute.
	 * @see #getLength()
	 * @generated
	 */
	void setLength(long value);

	/**
	 * Returns the value of the '<em><b>Last Modified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Modified</em>' attribute.
	 * @see #setLastModified(Date)
	 * @see org.nasdanika.ncore.NcorePackage#getFile_LastModified()
	 * @model
	 * @generated
	 */
	Date getLastModified();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.File#getLastModified <em>Last Modified</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Modified</em>' attribute.
	 * @see #getLastModified()
	 * @generated
	 */
	void setLastModified(Date value);

} // File
