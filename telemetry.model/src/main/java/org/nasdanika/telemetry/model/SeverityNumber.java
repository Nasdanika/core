/**
 */
package org.nasdanika.telemetry.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Severity Number</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Possible values for LogRecord.SeverityNumber.
 * <!-- end-model-doc -->
 * @see org.nasdanika.telemetry.model.ModelPackage#getSeverityNumber()
 * @model
 * @generated
 */
public enum SeverityNumber implements Enumerator {
	/**
	 * The '<em><b>SEVERITY NUMBER UNSPECIFIED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * UNSPECIFIED is the default SeverityNumber, it MUST not be used.
	 * <!-- end-model-doc -->
	 * @see #SEVERITY_NUMBER_UNSPECIFIED_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_UNSPECIFIED(0, "SEVERITY_NUMBER_UNSPECIFIED", "SEVERITY_NUMBER_UNSPECIFIED"),

	/**
	 * The '<em><b>SEVERITY NUMBER TRACE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_TRACE_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_TRACE(1, "SEVERITY_NUMBER_TRACE", "SEVERITY_NUMBER_TRACE"),

	/**
	 * The '<em><b>SEVERITY NUMBER TRACE2</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_TRACE2_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_TRACE2(2, "SEVERITY_NUMBER_TRACE2", "SEVERITY_NUMBER_TRACE2"),

	/**
	 * The '<em><b>SEVERITY NUMBER TRACE3</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_TRACE3_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_TRACE3(3, "SEVERITY_NUMBER_TRACE3", "SEVERITY_NUMBER_TRACE3"),

	/**
	 * The '<em><b>SEVERITY NUMBER TRACE4</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_TRACE4_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_TRACE4(4, "SEVERITY_NUMBER_TRACE4", "SEVERITY_NUMBER_TRACE4"),

	/**
	 * The '<em><b>SEVERITY NUMBER DEBUG</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_DEBUG_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_DEBUG(5, "SEVERITY_NUMBER_DEBUG", "SEVERITY_NUMBER_DEBUG"),

	/**
	 * The '<em><b>SEVERITY NUMBER DEBUG2</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_DEBUG2_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_DEBUG2(6, "SEVERITY_NUMBER_DEBUG2", "SEVERITY_NUMBER_DEBUG2"),

	/**
	 * The '<em><b>SEVERITY NUMBER DEBUG3</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_DEBUG3_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_DEBUG3(7, "SEVERITY_NUMBER_DEBUG3", "SEVERITY_NUMBER_DEBUG3"),

	/**
	 * The '<em><b>SEVERITY NUMBER DEBUG4</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_DEBUG4_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_DEBUG4(8, "SEVERITY_NUMBER_DEBUG4", "SEVERITY_NUMBER_DEBUG4"),

	/**
	 * The '<em><b>SEVERITY NUMBER INFO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_INFO_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_INFO(9, "SEVERITY_NUMBER_INFO", "SEVERITY_NUMBER_INFO"),

	/**
	 * The '<em><b>SEVERITY NUMBER INFO2</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_INFO2_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_INFO2(10, "SEVERITY_NUMBER_INFO2", "SEVERITY_NUMBER_INFO2"),

	/**
	 * The '<em><b>SEVERITY NUMBER INFO3</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_INFO3_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_INFO3(11, "SEVERITY_NUMBER_INFO3", "SEVERITY_NUMBER_INFO3"),

	/**
	 * The '<em><b>SEVERITY NUMBER INFO4</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_INFO4_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_INFO4(12, "SEVERITY_NUMBER_INFO4", "SEVERITY_NUMBER_INFO4"),

	/**
	 * The '<em><b>SEVERITY NUMBER WARN</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_WARN_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_WARN(13, "SEVERITY_NUMBER_WARN", "SEVERITY_NUMBER_WARN"),

	/**
	 * The '<em><b>SEVERITY NUMBER WARN2</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_WARN2_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_WARN2(14, "SEVERITY_NUMBER_WARN2", "SEVERITY_NUMBER_WARN2"),

	/**
	 * The '<em><b>SEVERITY NUMBER WARN3</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_WARN3_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_WARN3(15, "SEVERITY_NUMBER_WARN3", "SEVERITY_NUMBER_WARN3"),

	/**
	 * The '<em><b>SEVERITY NUMBER WARN4</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_WARN4_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_WARN4(16, "SEVERITY_NUMBER_WARN4", "SEVERITY_NUMBER_WARN4"),

	/**
	 * The '<em><b>SEVERITY NUMBER ERROR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_ERROR_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_ERROR(17, "SEVERITY_NUMBER_ERROR", "SEVERITY_NUMBER_ERROR"),

	/**
	 * The '<em><b>SEVERITY NUMBER ERROR2</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_ERROR2_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_ERROR2(18, "SEVERITY_NUMBER_ERROR2", "SEVERITY_NUMBER_ERROR2"),

	/**
	 * The '<em><b>SEVERITY NUMBER ERROR3</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_ERROR3_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_ERROR3(19, "SEVERITY_NUMBER_ERROR3", "SEVERITY_NUMBER_ERROR3"),

	/**
	 * The '<em><b>SEVERITY NUMBER ERROR4</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_ERROR4_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_ERROR4(20, "SEVERITY_NUMBER_ERROR4", "SEVERITY_NUMBER_ERROR4"),

	/**
	 * The '<em><b>SEVERITY NUMBER FATAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_FATAL_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_FATAL(21, "SEVERITY_NUMBER_FATAL", "SEVERITY_NUMBER_FATAL"),

	/**
	 * The '<em><b>SEVERITY NUMBER FATAL2</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_FATAL2_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_FATAL2(22, "SEVERITY_NUMBER_FATAL2", "SEVERITY_NUMBER_FATAL2"),

	/**
	 * The '<em><b>SEVERITY NUMBER FATAL3</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_FATAL3_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_FATAL3(23, "SEVERITY_NUMBER_FATAL3", "SEVERITY_NUMBER_FATAL3"),

	/**
	 * The '<em><b>SEVERITY NUMBER FATAL4</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_FATAL4_VALUE
	 * @generated
	 * @ordered
	 */
	SEVERITY_NUMBER_FATAL4(24, "SEVERITY_NUMBER_FATAL4", "SEVERITY_NUMBER_FATAL4");

	/**
	 * The '<em><b>SEVERITY NUMBER UNSPECIFIED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * UNSPECIFIED is the default SeverityNumber, it MUST not be used.
	 * <!-- end-model-doc -->
	 * @see #SEVERITY_NUMBER_UNSPECIFIED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_UNSPECIFIED_VALUE = 0;

	/**
	 * The '<em><b>SEVERITY NUMBER TRACE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_TRACE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_TRACE_VALUE = 1;

	/**
	 * The '<em><b>SEVERITY NUMBER TRACE2</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_TRACE2
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_TRACE2_VALUE = 2;

	/**
	 * The '<em><b>SEVERITY NUMBER TRACE3</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_TRACE3
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_TRACE3_VALUE = 3;

	/**
	 * The '<em><b>SEVERITY NUMBER TRACE4</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_TRACE4
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_TRACE4_VALUE = 4;

	/**
	 * The '<em><b>SEVERITY NUMBER DEBUG</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_DEBUG
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_DEBUG_VALUE = 5;

	/**
	 * The '<em><b>SEVERITY NUMBER DEBUG2</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_DEBUG2
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_DEBUG2_VALUE = 6;

	/**
	 * The '<em><b>SEVERITY NUMBER DEBUG3</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_DEBUG3
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_DEBUG3_VALUE = 7;

	/**
	 * The '<em><b>SEVERITY NUMBER DEBUG4</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_DEBUG4
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_DEBUG4_VALUE = 8;

	/**
	 * The '<em><b>SEVERITY NUMBER INFO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_INFO
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_INFO_VALUE = 9;

	/**
	 * The '<em><b>SEVERITY NUMBER INFO2</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_INFO2
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_INFO2_VALUE = 10;

	/**
	 * The '<em><b>SEVERITY NUMBER INFO3</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_INFO3
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_INFO3_VALUE = 11;

	/**
	 * The '<em><b>SEVERITY NUMBER INFO4</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_INFO4
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_INFO4_VALUE = 12;

	/**
	 * The '<em><b>SEVERITY NUMBER WARN</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_WARN
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_WARN_VALUE = 13;

	/**
	 * The '<em><b>SEVERITY NUMBER WARN2</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_WARN2
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_WARN2_VALUE = 14;

	/**
	 * The '<em><b>SEVERITY NUMBER WARN3</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_WARN3
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_WARN3_VALUE = 15;

	/**
	 * The '<em><b>SEVERITY NUMBER WARN4</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_WARN4
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_WARN4_VALUE = 16;

	/**
	 * The '<em><b>SEVERITY NUMBER ERROR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_ERROR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_ERROR_VALUE = 17;

	/**
	 * The '<em><b>SEVERITY NUMBER ERROR2</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_ERROR2
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_ERROR2_VALUE = 18;

	/**
	 * The '<em><b>SEVERITY NUMBER ERROR3</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_ERROR3
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_ERROR3_VALUE = 19;

	/**
	 * The '<em><b>SEVERITY NUMBER ERROR4</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_ERROR4
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_ERROR4_VALUE = 20;

	/**
	 * The '<em><b>SEVERITY NUMBER FATAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_FATAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_FATAL_VALUE = 21;

	/**
	 * The '<em><b>SEVERITY NUMBER FATAL2</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_FATAL2
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_FATAL2_VALUE = 22;

	/**
	 * The '<em><b>SEVERITY NUMBER FATAL3</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_FATAL3
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_FATAL3_VALUE = 23;

	/**
	 * The '<em><b>SEVERITY NUMBER FATAL4</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SEVERITY_NUMBER_FATAL4
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SEVERITY_NUMBER_FATAL4_VALUE = 24;

	/**
	 * An array of all the '<em><b>Severity Number</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SeverityNumber[] VALUES_ARRAY =
		new SeverityNumber[] {
			SEVERITY_NUMBER_UNSPECIFIED,
			SEVERITY_NUMBER_TRACE,
			SEVERITY_NUMBER_TRACE2,
			SEVERITY_NUMBER_TRACE3,
			SEVERITY_NUMBER_TRACE4,
			SEVERITY_NUMBER_DEBUG,
			SEVERITY_NUMBER_DEBUG2,
			SEVERITY_NUMBER_DEBUG3,
			SEVERITY_NUMBER_DEBUG4,
			SEVERITY_NUMBER_INFO,
			SEVERITY_NUMBER_INFO2,
			SEVERITY_NUMBER_INFO3,
			SEVERITY_NUMBER_INFO4,
			SEVERITY_NUMBER_WARN,
			SEVERITY_NUMBER_WARN2,
			SEVERITY_NUMBER_WARN3,
			SEVERITY_NUMBER_WARN4,
			SEVERITY_NUMBER_ERROR,
			SEVERITY_NUMBER_ERROR2,
			SEVERITY_NUMBER_ERROR3,
			SEVERITY_NUMBER_ERROR4,
			SEVERITY_NUMBER_FATAL,
			SEVERITY_NUMBER_FATAL2,
			SEVERITY_NUMBER_FATAL3,
			SEVERITY_NUMBER_FATAL4,
		};

	/**
	 * A public read-only list of all the '<em><b>Severity Number</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<SeverityNumber> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Severity Number</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SeverityNumber get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SeverityNumber result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Severity Number</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SeverityNumber getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SeverityNumber result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Severity Number</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SeverityNumber get(int value) {
		switch (value) {
			case SEVERITY_NUMBER_UNSPECIFIED_VALUE: return SEVERITY_NUMBER_UNSPECIFIED;
			case SEVERITY_NUMBER_TRACE_VALUE: return SEVERITY_NUMBER_TRACE;
			case SEVERITY_NUMBER_TRACE2_VALUE: return SEVERITY_NUMBER_TRACE2;
			case SEVERITY_NUMBER_TRACE3_VALUE: return SEVERITY_NUMBER_TRACE3;
			case SEVERITY_NUMBER_TRACE4_VALUE: return SEVERITY_NUMBER_TRACE4;
			case SEVERITY_NUMBER_DEBUG_VALUE: return SEVERITY_NUMBER_DEBUG;
			case SEVERITY_NUMBER_DEBUG2_VALUE: return SEVERITY_NUMBER_DEBUG2;
			case SEVERITY_NUMBER_DEBUG3_VALUE: return SEVERITY_NUMBER_DEBUG3;
			case SEVERITY_NUMBER_DEBUG4_VALUE: return SEVERITY_NUMBER_DEBUG4;
			case SEVERITY_NUMBER_INFO_VALUE: return SEVERITY_NUMBER_INFO;
			case SEVERITY_NUMBER_INFO2_VALUE: return SEVERITY_NUMBER_INFO2;
			case SEVERITY_NUMBER_INFO3_VALUE: return SEVERITY_NUMBER_INFO3;
			case SEVERITY_NUMBER_INFO4_VALUE: return SEVERITY_NUMBER_INFO4;
			case SEVERITY_NUMBER_WARN_VALUE: return SEVERITY_NUMBER_WARN;
			case SEVERITY_NUMBER_WARN2_VALUE: return SEVERITY_NUMBER_WARN2;
			case SEVERITY_NUMBER_WARN3_VALUE: return SEVERITY_NUMBER_WARN3;
			case SEVERITY_NUMBER_WARN4_VALUE: return SEVERITY_NUMBER_WARN4;
			case SEVERITY_NUMBER_ERROR_VALUE: return SEVERITY_NUMBER_ERROR;
			case SEVERITY_NUMBER_ERROR2_VALUE: return SEVERITY_NUMBER_ERROR2;
			case SEVERITY_NUMBER_ERROR3_VALUE: return SEVERITY_NUMBER_ERROR3;
			case SEVERITY_NUMBER_ERROR4_VALUE: return SEVERITY_NUMBER_ERROR4;
			case SEVERITY_NUMBER_FATAL_VALUE: return SEVERITY_NUMBER_FATAL;
			case SEVERITY_NUMBER_FATAL2_VALUE: return SEVERITY_NUMBER_FATAL2;
			case SEVERITY_NUMBER_FATAL3_VALUE: return SEVERITY_NUMBER_FATAL3;
			case SEVERITY_NUMBER_FATAL4_VALUE: return SEVERITY_NUMBER_FATAL4;
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
	private SeverityNumber(int value, String name, String literal) {
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
	
} //SeverityNumber
