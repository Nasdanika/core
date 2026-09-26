/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Span Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.SpanEvent#getTimeUnixNano <em>Time Unix Nano</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.SpanEvent#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.SpanEvent#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.SpanEvent#getDroppedAttributesCount <em>Dropped Attributes Count</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.telemetry.model.ModelPackage#getSpanEvent()
 * @model
 * @generated
 */
public interface SpanEvent extends EObject {
	/**
	 * Returns the value of the '<em><b>Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Unix Nano</em>' attribute.
	 * @see #setTimeUnixNano(long)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpanEvent_TimeUnixNano()
	 * @model unique="false"
	 * @generated
	 */
	long getTimeUnixNano();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.SpanEvent#getTimeUnixNano <em>Time Unix Nano</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Unix Nano</em>' attribute.
	 * @see #getTimeUnixNano()
	 * @generated
	 */
	void setTimeUnixNano(long value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpanEvent_Name()
	 * @model unique="false" required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.SpanEvent#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.telemetry.model.KeyValue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' containment reference list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpanEvent_Attributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<KeyValue> getAttributes();

	/**
	 * Returns the value of the '<em><b>Dropped Attributes Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dropped Attributes Count</em>' attribute.
	 * @see #setDroppedAttributesCount(int)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getSpanEvent_DroppedAttributesCount()
	 * @model unique="false"
	 * @generated
	 */
	int getDroppedAttributesCount();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.SpanEvent#getDroppedAttributesCount <em>Dropped Attributes Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dropped Attributes Count</em>' attribute.
	 * @see #getDroppedAttributesCount()
	 * @generated
	 */
	void setDroppedAttributesCount(int value);

} // SpanEvent
