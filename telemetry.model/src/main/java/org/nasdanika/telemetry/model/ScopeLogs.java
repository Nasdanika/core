/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scope Logs</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A collection of Logs produced by a Scope.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.ScopeLogs#getScope <em>Scope</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.ScopeLogs#getLogRecords <em>Log Records</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.ScopeLogs#getSchemaUrl <em>Schema Url</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.telemetry.model.ModelPackage#getScopeLogs()
 * @model
 * @generated
 */
public interface ScopeLogs extends EObject {
	/**
	 * Returns the value of the '<em><b>Scope</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scope</em>' containment reference.
	 * @see #setScope(InstrumentationScope)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getScopeLogs_Scope()
	 * @model containment="true"
	 * @generated
	 */
	InstrumentationScope getScope();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.ScopeLogs#getScope <em>Scope</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scope</em>' containment reference.
	 * @see #getScope()
	 * @generated
	 */
	void setScope(InstrumentationScope value);

	/**
	 * Returns the value of the '<em><b>Log Records</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.telemetry.model.LogRecord}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Log Records</em>' containment reference list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getScopeLogs_LogRecords()
	 * @model containment="true"
	 * @generated
	 */
	EList<LogRecord> getLogRecords();

	/**
	 * Returns the value of the '<em><b>Schema Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Schema Url</em>' attribute.
	 * @see #setSchemaUrl(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getScopeLogs_SchemaUrl()
	 * @model
	 * @generated
	 */
	String getSchemaUrl();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.ScopeLogs#getSchemaUrl <em>Schema Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema Url</em>' attribute.
	 * @see #getSchemaUrl()
	 * @generated
	 */
	void setSchemaUrl(String value);

} // ScopeLogs
