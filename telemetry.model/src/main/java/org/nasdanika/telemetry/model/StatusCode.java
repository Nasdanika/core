/**
 */
package org.nasdanika.telemetry.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Status Code</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * For the semantics of status codes see https://github.com/open-telemetry/opentelemetry-specification/blob/main/specification/trace/api.md#set-status
 * <!-- end-model-doc -->
 * @see org.nasdanika.telemetry.model.ModelPackage#getStatusCode()
 * @model
 * @generated
 */
public enum StatusCode implements Enumerator {
	/**
	 * The '<em><b>STATUS CODE UNSET</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default status.
	 * <!-- end-model-doc -->
	 * @see #STATUS_CODE_UNSET_VALUE
	 * @generated
	 * @ordered
	 */
	STATUS_CODE_UNSET(0, "STATUS_CODE_UNSET", "STATUS_CODE_UNSET"),

	/**
	 * The '<em><b>STATUS CODE OK</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Span has been validated by an Application developer or Operator to have completed successfully.
	 * <!-- end-model-doc -->
	 * @see #STATUS_CODE_OK_VALUE
	 * @generated
	 * @ordered
	 */
	STATUS_CODE_OK(1, "STATUS_CODE_OK", "STATUS_CODE_OK"),

	/**
	 * The '<em><b>STATUS CODE ERROR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Span contains an error.
	 * <!-- end-model-doc -->
	 * @see #STATUS_CODE_ERROR_VALUE
	 * @generated
	 * @ordered
	 */
	STATUS_CODE_ERROR(2, "STATUS_CODE_ERROR", "STATUS_CODE_ERROR");

	/**
	 * The '<em><b>STATUS CODE UNSET</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The default status.
	 * <!-- end-model-doc -->
	 * @see #STATUS_CODE_UNSET
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int STATUS_CODE_UNSET_VALUE = 0;

	/**
	 * The '<em><b>STATUS CODE OK</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Span has been validated by an Application developer or Operator to have completed successfully.
	 * <!-- end-model-doc -->
	 * @see #STATUS_CODE_OK
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int STATUS_CODE_OK_VALUE = 1;

	/**
	 * The '<em><b>STATUS CODE ERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Span contains an error.
	 * <!-- end-model-doc -->
	 * @see #STATUS_CODE_ERROR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int STATUS_CODE_ERROR_VALUE = 2;

	/**
	 * An array of all the '<em><b>Status Code</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final StatusCode[] VALUES_ARRAY =
		new StatusCode[] {
			STATUS_CODE_UNSET,
			STATUS_CODE_OK,
			STATUS_CODE_ERROR,
		};

	/**
	 * A public read-only list of all the '<em><b>Status Code</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<StatusCode> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Status Code</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static StatusCode get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			StatusCode result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Status Code</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static StatusCode getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			StatusCode result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Status Code</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static StatusCode get(int value) {
		switch (value) {
			case STATUS_CODE_UNSET_VALUE: return STATUS_CODE_UNSET;
			case STATUS_CODE_OK_VALUE: return STATUS_CODE_OK;
			case STATUS_CODE_ERROR_VALUE: return STATUS_CODE_ERROR;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private StatusCode(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //StatusCode
