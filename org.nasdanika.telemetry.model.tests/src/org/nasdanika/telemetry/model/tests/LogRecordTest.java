/**
 */
package org.nasdanika.telemetry.model.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.nasdanika.telemetry.model.LogRecord;
import org.nasdanika.telemetry.model.ModelFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Log Record</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class LogRecordTest extends TestCase {

	/**
	 * The fixture for this Log Record test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LogRecord fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(LogRecordTest.class);
	}

	/**
	 * Constructs a new Log Record test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LogRecordTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Log Record test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(LogRecord fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Log Record test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LogRecord getFixture() {
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
		setFixture(ModelFactory.eINSTANCE.createLogRecord());
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

} //LogRecordTest
