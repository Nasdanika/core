/**
 */
package org.nasdanika.telemetry.model.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.nasdanika.telemetry.model.ExponentialHistogramDataPoint;
import org.nasdanika.telemetry.model.ModelFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Exponential Histogram Data Point</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExponentialHistogramDataPointTest extends TestCase {

	/**
	 * The fixture for this Exponential Histogram Data Point test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExponentialHistogramDataPoint fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ExponentialHistogramDataPointTest.class);
	}

	/**
	 * Constructs a new Exponential Histogram Data Point test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExponentialHistogramDataPointTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Exponential Histogram Data Point test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ExponentialHistogramDataPoint fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Exponential Histogram Data Point test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExponentialHistogramDataPoint getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createExponentialHistogramDataPoint());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //ExponentialHistogramDataPointTest
