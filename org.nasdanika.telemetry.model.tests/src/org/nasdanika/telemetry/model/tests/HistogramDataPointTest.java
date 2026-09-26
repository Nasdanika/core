/**
 */
package org.nasdanika.telemetry.model.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.nasdanika.telemetry.model.HistogramDataPoint;
import org.nasdanika.telemetry.model.ModelFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Histogram Data Point</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class HistogramDataPointTest extends TestCase {

	/**
	 * The fixture for this Histogram Data Point test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HistogramDataPoint fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(HistogramDataPointTest.class);
	}

	/**
	 * Constructs a new Histogram Data Point test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public HistogramDataPointTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Histogram Data Point test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(HistogramDataPoint fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Histogram Data Point test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected HistogramDataPoint getFixture() {
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
		setFixture(ModelFactory.eINSTANCE.createHistogramDataPoint());
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

} //HistogramDataPointTest
