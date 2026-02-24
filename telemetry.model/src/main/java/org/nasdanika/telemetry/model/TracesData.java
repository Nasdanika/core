/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Traces Data</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * TracesData represents the traces export payload which can be serialized to different formats, e.g., OTLP/gRPC, JSON, and proto.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.TracesData#getResourceSpans <em>Resource Spans</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.telemetry.model.ModelPackage#getTracesData()
 * @model
 * @generated
 */
public interface TracesData extends EObject {
	/**
	 * Returns the value of the '<em><b>Resource Spans</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.telemetry.model.ResourceSpans}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An array of ResourceSpans. For data coming from a single resource this array will typically contain one element. Intermediary nodes that receive data from multiple origins typically batch the data before forwarding further.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resource Spans</em>' containment reference list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getTracesData_ResourceSpans()
	 * @model containment="true"
	 * @generated
	 */
	EList<ResourceSpans> getResourceSpans();

} // TracesData
