/**
 */
package org.nasdanika.telemetry.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Span Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.nasdanika.telemetry.model.ModelPackage#getSpanKind()
 * @model
 * @generated
 */
public enum SpanKind implements Enumerator {
	/**
	 * The '<em><b>SPAN KIND UNSPECIFIED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SPAN_KIND_UNSPECIFIED_VALUE
	 * @generated
	 * @ordered
	 */
	SPAN_KIND_UNSPECIFIED(0, "SPAN_KIND_UNSPECIFIED", "SPAN_KIND_UNSPECIFIED"),

	/**
	 * The '<em><b>SPAN KIND INTERNAL</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SPAN_KIND_INTERNAL_VALUE
	 * @generated
	 * @ordered
	 */
	SPAN_KIND_INTERNAL(1, "SPAN_KIND_INTERNAL", "SPAN_KIND_INTERNAL"),

	/**
	 * The '<em><b>SPAN KIND SERVER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SPAN_KIND_SERVER_VALUE
	 * @generated
	 * @ordered
	 */
	SPAN_KIND_SERVER(2, "SPAN_KIND_SERVER", "SPAN_KIND_SERVER"),

	/**
	 * The '<em><b>SPAN KIND CLIENT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SPAN_KIND_CLIENT_VALUE
	 * @generated
	 * @ordered
	 */
	SPAN_KIND_CLIENT(3, "SPAN_KIND_CLIENT", "SPAN_KIND_CLIENT"),

	/**
	 * The '<em><b>SPAN KIND PRODUCER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SPAN_KIND_PRODUCER_VALUE
	 * @generated
	 * @ordered
	 */
	SPAN_KIND_PRODUCER(4, "SPAN_KIND_PRODUCER", "SPAN_KIND_PRODUCER"),

	/**
	 * The '<em><b>SPAN KIND CONSUMER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SPAN_KIND_CONSUMER_VALUE
	 * @generated
	 * @ordered
	 */
	SPAN_KIND_CONSUMER(5, "SPAN_KIND_CONSUMER", "SPAN_KIND_CONSUMER");

	/**
	 * The '<em><b>SPAN KIND UNSPECIFIED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SPAN_KIND_UNSPECIFIED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SPAN_KIND_UNSPECIFIED_VALUE = 0;

	/**
	 * The '<em><b>SPAN KIND INTERNAL</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SPAN_KIND_INTERNAL
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SPAN_KIND_INTERNAL_VALUE = 1;

	/**
	 * The '<em><b>SPAN KIND SERVER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SPAN_KIND_SERVER
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SPAN_KIND_SERVER_VALUE = 2;

	/**
	 * The '<em><b>SPAN KIND CLIENT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SPAN_KIND_CLIENT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SPAN_KIND_CLIENT_VALUE = 3;

	/**
	 * The '<em><b>SPAN KIND PRODUCER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SPAN_KIND_PRODUCER
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SPAN_KIND_PRODUCER_VALUE = 4;

	/**
	 * The '<em><b>SPAN KIND CONSUMER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SPAN_KIND_CONSUMER
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SPAN_KIND_CONSUMER_VALUE = 5;

	/**
	 * An array of all the '<em><b>Span Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SpanKind[] VALUES_ARRAY =
		new SpanKind[] {
			SPAN_KIND_UNSPECIFIED,
			SPAN_KIND_INTERNAL,
			SPAN_KIND_SERVER,
			SPAN_KIND_CLIENT,
			SPAN_KIND_PRODUCER,
			SPAN_KIND_CONSUMER,
		};

	/**
	 * A public read-only list of all the '<em><b>Span Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<SpanKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Span Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SpanKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SpanKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Span Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SpanKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SpanKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Span Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SpanKind get(int value) {
		switch (value) {
			case SPAN_KIND_UNSPECIFIED_VALUE: return SPAN_KIND_UNSPECIFIED;
			case SPAN_KIND_INTERNAL_VALUE: return SPAN_KIND_INTERNAL;
			case SPAN_KIND_SERVER_VALUE: return SPAN_KIND_SERVER;
			case SPAN_KIND_CLIENT_VALUE: return SPAN_KIND_CLIENT;
			case SPAN_KIND_PRODUCER_VALUE: return SPAN_KIND_PRODUCER;
			case SPAN_KIND_CONSUMER_VALUE: return SPAN_KIND_CONSUMER;
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
	private SpanKind(int value, String name, String literal) {
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
	
} //SpanKind
