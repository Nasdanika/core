/**
 */
package org.nasdanika.telemetry.model.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.nasdanika.telemetry.model.ModelFactory;
import org.nasdanika.telemetry.model.ScopeLogs;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Scope Logs</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ScopeLogsTest extends TestCase {

	/**
	 * The fixture for this Scope Logs test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScopeLogs fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScopeLogsTest.class);
	}

	/**
	 * Constructs a new Scope Logs test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScopeLogsTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Scope Logs test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ScopeLogs fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Scope Logs test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScopeLogs getFixture() {
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
		setFixture(ModelFactory.eINSTANCE.createScopeLogs());
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

} //ScopeLogsTest
