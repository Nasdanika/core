/**
 */
package org.nasdanika.telemetry.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Aggregation Temporality</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * AggregationTemporality defines how a metric aggregator reports aggregated values. It describes how those values relate to the time interval over which they are aggregated.
 * <!-- end-model-doc -->
 * @see org.nasdanika.telemetry.model.ModelPackage#getAggregationTemporality()
 * @model
 * @generated
 */
public enum AggregationTemporality implements Enumerator {
	/**
	 * The '<em><b>AGGREGATION TEMPORALITY UNSPECIFIED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * UNSPECIFIED is the default AggregationTemporality, it MUST not be used.
	 * <!-- end-model-doc -->
	 * @see #AGGREGATION_TEMPORALITY_UNSPECIFIED_VALUE
	 * @generated
	 * @ordered
	 */
	AGGREGATION_TEMPORALITY_UNSPECIFIED(0, "AGGREGATION_TEMPORALITY_UNSPECIFIED", "AGGREGATION_TEMPORALITY_UNSPECIFIED"),

	/**
	 * The '<em><b>AGGREGATION TEMPORALITY DELTA</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * DELTA is an AggregationTemporality for a metric aggregator which reports changes since last report time.
	 * <!-- end-model-doc -->
	 * @see #AGGREGATION_TEMPORALITY_DELTA_VALUE
	 * @generated
	 * @ordered
	 */
	AGGREGATION_TEMPORALITY_DELTA(1, "AGGREGATION_TEMPORALITY_DELTA", "AGGREGATION_TEMPORALITY_DELTA"),

	/**
	 * The '<em><b>AGGREGATION TEMPORALITY CUMULATIVE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * CUMULATIVE is an AggregationTemporality for a metric aggregator which reports changes since a fixed start time.
	 * <!-- end-model-doc -->
	 * @see #AGGREGATION_TEMPORALITY_CUMULATIVE_VALUE
	 * @generated
	 * @ordered
	 */
	AGGREGATION_TEMPORALITY_CUMULATIVE(2, "AGGREGATION_TEMPORALITY_CUMULATIVE", "AGGREGATION_TEMPORALITY_CUMULATIVE");

	/**
	 * The '<em><b>AGGREGATION TEMPORALITY UNSPECIFIED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * UNSPECIFIED is the default AggregationTemporality, it MUST not be used.
	 * <!-- end-model-doc -->
	 * @see #AGGREGATION_TEMPORALITY_UNSPECIFIED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int AGGREGATION_TEMPORALITY_UNSPECIFIED_VALUE = 0;

	/**
	 * The '<em><b>AGGREGATION TEMPORALITY DELTA</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * DELTA is an AggregationTemporality for a metric aggregator which reports changes since last report time.
	 * <!-- end-model-doc -->
	 * @see #AGGREGATION_TEMPORALITY_DELTA
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int AGGREGATION_TEMPORALITY_DELTA_VALUE = 1;

	/**
	 * The '<em><b>AGGREGATION TEMPORALITY CUMULATIVE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * CUMULATIVE is an AggregationTemporality for a metric aggregator which reports changes since a fixed start time.
	 * <!-- end-model-doc -->
	 * @see #AGGREGATION_TEMPORALITY_CUMULATIVE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int AGGREGATION_TEMPORALITY_CUMULATIVE_VALUE = 2;

	/**
	 * An array of all the '<em><b>Aggregation Temporality</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final AggregationTemporality[] VALUES_ARRAY =
		new AggregationTemporality[] {
			AGGREGATION_TEMPORALITY_UNSPECIFIED,
			AGGREGATION_TEMPORALITY_DELTA,
			AGGREGATION_TEMPORALITY_CUMULATIVE,
		};

	/**
	 * A public read-only list of all the '<em><b>Aggregation Temporality</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<AggregationTemporality> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Aggregation Temporality</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AggregationTemporality get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AggregationTemporality result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Aggregation Temporality</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AggregationTemporality getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			AggregationTemporality result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Aggregation Temporality</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static AggregationTemporality get(int value) {
		switch (value) {
			case AGGREGATION_TEMPORALITY_UNSPECIFIED_VALUE: return AGGREGATION_TEMPORALITY_UNSPECIFIED;
			case AGGREGATION_TEMPORALITY_DELTA_VALUE: return AGGREGATION_TEMPORALITY_DELTA;
			case AGGREGATION_TEMPORALITY_CUMULATIVE_VALUE: return AGGREGATION_TEMPORALITY_CUMULATIVE;
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
	private AggregationTemporality(int value, String name, String literal) {
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
	
} //AggregationTemporality
