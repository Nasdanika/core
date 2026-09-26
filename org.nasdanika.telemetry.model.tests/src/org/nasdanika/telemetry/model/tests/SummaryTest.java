/**
 */
package org.nasdanika.telemetry.model.tests;

import junit.textui.TestRunner;

import org.nasdanika.telemetry.model.ModelFactory;
import org.nasdanika.telemetry.model.Summary;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Summary</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class SummaryTest extends MetricTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(SummaryTest.class);
	}

	/**
	 * Constructs a new Summary test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SummaryTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Summary test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Summary getFixture() {
		return (Summary)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createSummary());
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

} //SummaryTest
