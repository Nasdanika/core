/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Logs Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * LogsData represents the logs export payload which can be serialized to different formats, e.g., OTLP/gRPC, JSON, and proto.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.LogsData#getResourceLogs <em>Resource Logs</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.telemetry.model.ModelPackage#getLogsData()
 * @model
 * @generated
 */
public interface LogsData extends EObject {
	/**
	 * Returns the value of the '<em><b>Resource Logs</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.telemetry.model.ResourceLogs}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An array of ResourceLogs.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resource Logs</em>' containment reference list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getLogsData_ResourceLogs()
	 * @model containment="true"
	 * @generated
	 */
	EList<ResourceLogs> getResourceLogs();

} // LogsData
