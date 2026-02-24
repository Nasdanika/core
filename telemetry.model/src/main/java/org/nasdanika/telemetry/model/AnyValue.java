/**
 */
package org.nasdanika.telemetry.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Any Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * AnyValue is used to represent any type of attribute value. AnyValue may contain a primitive value such as a string or integer or it may contain an arbitrary nested object containing arrays, key-value lists and primitives.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.telemetry.model.AnyValue#getStringValue <em>String Value</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.AnyValue#getBoolValue <em>Bool Value</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.AnyValue#getIntValue <em>Int Value</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.AnyValue#getDoubleValue <em>Double Value</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.AnyValue#getArrayValue <em>Array Value</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.AnyValue#getKvlistValue <em>Kvlist Value</em>}</li>
 *   <li>{@link org.nasdanika.telemetry.model.AnyValue#getBytesValue <em>Bytes Value</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.telemetry.model.ModelPackage#getAnyValue()
 * @model
 * @generated
 */
public interface AnyValue extends EObject {
	/**
	 * Returns the value of the '<em><b>String Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>String Value</em>' attribute.
	 * @see #setStringValue(String)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getAnyValue_StringValue()
	 * @model
	 * @generated
	 */
	String getStringValue();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.AnyValue#getStringValue <em>String Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>String Value</em>' attribute.
	 * @see #getStringValue()
	 * @generated
	 */
	void setStringValue(String value);

	/**
	 * Returns the value of the '<em><b>Bool Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bool Value</em>' attribute.
	 * @see #setBoolValue(Boolean)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getAnyValue_BoolValue()
	 * @model
	 * @generated
	 */
	Boolean getBoolValue();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.AnyValue#getBoolValue <em>Bool Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bool Value</em>' attribute.
	 * @see #getBoolValue()
	 * @generated
	 */
	void setBoolValue(Boolean value);

	/**
	 * Returns the value of the '<em><b>Int Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Int Value</em>' attribute.
	 * @see #setIntValue(Long)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getAnyValue_IntValue()
	 * @model
	 * @generated
	 */
	Long getIntValue();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.AnyValue#getIntValue <em>Int Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Int Value</em>' attribute.
	 * @see #getIntValue()
	 * @generated
	 */
	void setIntValue(Long value);

	/**
	 * Returns the value of the '<em><b>Double Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Double Value</em>' attribute.
	 * @see #setDoubleValue(Double)
	 * @see org.nasdanika.telemetry.model.ModelPackage#getAnyValue_DoubleValue()
	 * @model
	 * @generated
	 */
	Double getDoubleValue();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.AnyValue#getDoubleValue <em>Double Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Double Value</em>' attribute.
	 * @see #getDoubleValue()
	 * @generated
	 */
	void setDoubleValue(Double value);

	/**
	 * Returns the value of the '<em><b>Array Value</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.telemetry.model.AnyValue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Represents an array of any value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Array Value</em>' containment reference list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getAnyValue_ArrayValue()
	 * @model containment="true"
	 * @generated
	 */
	EList<AnyValue> getArrayValue();

	/**
	 * Returns the value of the '<em><b>Kvlist Value</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.telemetry.model.KeyValue}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Represents a list of key-value pairs.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Kvlist Value</em>' containment reference list.
	 * @see org.nasdanika.telemetry.model.ModelPackage#getAnyValue_KvlistValue()
	 * @model containment="true"
	 * @generated
	 */
	EList<KeyValue> getKvlistValue();

	/**
	 * Returns the value of the '<em><b>Bytes Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Represents raw bytes value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Bytes Value</em>' attribute.
	 * @see #setBytesValue(byte[])
	 * @see org.nasdanika.telemetry.model.ModelPackage#getAnyValue_BytesValue()
	 * @model
	 * @generated
	 */
	byte[] getBytesValue();

	/**
	 * Sets the value of the '{@link org.nasdanika.telemetry.model.AnyValue#getBytesValue <em>Bytes Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bytes Value</em>' attribute.
	 * @see #getBytesValue()
	 * @generated
	 */
	void setBytesValue(byte[] value);

} // AnyValue
