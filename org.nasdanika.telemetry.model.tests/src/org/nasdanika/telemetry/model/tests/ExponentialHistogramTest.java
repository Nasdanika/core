/**
 */
package org.nasdanika.telemetry.model.tests;

import junit.textui.TestRunner;

import org.nasdanika.telemetry.model.ExponentialHistogram;
import org.nasdanika.telemetry.model.ModelFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Exponential Histogram</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExponentialHistogramTest extends MetricTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ExponentialHistogramTest.class);
	}

	/**
	 * Constructs a new Exponential Histogram test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExponentialHistogramTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Exponential Histogram test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ExponentialHistogram getFixture() {
		return (ExponentialHistogram)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createExponentialHistogram());
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

} //ExponentialHistogramTest
