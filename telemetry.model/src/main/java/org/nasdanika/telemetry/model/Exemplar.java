/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Exemplar</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.Exemplar#getFilteredAttributes <em>Filtered Attributes</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Exemplar#getTimeUnixNano <em>Time Unix Nano</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Exemplar#getAsDouble <em>As Double</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Exemplar#getAsInt <em>As Int</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Exemplar#getSpanId <em>Span Id</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Exemplar#getTraceId <em>Trace Id</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.Exemplar#getSpan <em>Span</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.telemetry.model.ModelPackage#getExemplar()
 * @model
 * @generated
 */
public interface Exemplar extends EObject {
	/**
	 * Returns the value of the '<em><b>Filtered Attributes</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.telemetry.model.KeyValue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filtered Attributes</em>' containment reference list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExemplar_FilteredAttributes()
	 * @model containment="true"
	 * @generated
	 */
	EList<KeyValue> getFilteredAttributes();

	/**
	 * Returns the value of the '<em><b>Time Unix Nano</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Time Unix Nano</em>' attribute.
	 * @see #setTimeUnixNano(long)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExemplar_TimeUnixNano()
	 * @model unique="false"
	 * @generated
	 */
	long getTimeUnixNano();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Exemplar#getTimeUnixNano <em>Time Unix Nano</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Time Unix Nano</em>' attribute.
	 * @see #getTimeUnixNano()
	 * @generated
	 */
	void setTimeUnixNano(long value);

	/**
	 * Returns the value of the '<em><b>As Double</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>As Double</em>' attribute.
	 * @see #setAsDouble(Double)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExemplar_AsDouble()
	 * @model unique="false"
	 * @generated
	 */
	Double getAsDouble();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Exemplar#getAsDouble <em>As Double</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>As Double</em>' attribute.
	 * @see #getAsDouble()
	 * @generated
	 */
	void setAsDouble(Double value);

	/**
	 * Returns the value of the '<em><b>As Int</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>As Int</em>' attribute.
	 * @see #setAsInt(Long)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExemplar_AsInt()
	 * @model unique="false"
	 * @generated
	 */
	Long getAsInt();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Exemplar#getAsInt <em>As Int</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>As Int</em>' attribute.
	 * @see #getAsInt()
	 * @generated
	 */
	void setAsInt(Long value);

	/**
	 * Returns the value of the '<em><b>Span Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Span Id</em>' attribute.
	 * @see #setSpanId(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExemplar_SpanId()
	 * @model unique="false"
	 * @generated
	 */
	String getSpanId();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Exemplar#getSpanId <em>Span Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Span Id</em>' attribute.
	 * @see #getSpanId()
	 * @generated
	 */
	void setSpanId(String value);

	/**
	 * Returns the value of the '<em><b>Trace Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Trace Id</em>' attribute.
	 * @see #setTraceId(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExemplar_TraceId()
	 * @model unique="false"
	 * @generated
	 */
	String getTraceId();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Exemplar#getTraceId <em>Trace Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Trace Id</em>' attribute.
	 * @see #getTraceId()
	 * @generated
	 */
	void setTraceId(String value);

	/**
	 * Returns the value of the '<em><b>Span</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Span</em>' reference.
	 * @see #setSpan(Span)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getExemplar_Span()
	 * @model
	 * @generated
	 */
	Span getSpan();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.Exemplar#getSpan <em>Span</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Span</em>' reference.
	 * @see #getSpan()
	 * @generated
	 */
	void setSpan(Span value);

} // Exemplar
