/**
 */
package org.nasdanika.telemetry.model.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.nasdanika.telemetry.model.Exemplar;
import org.nasdanika.telemetry.model.ModelFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Exemplar</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExemplarTest extends TestCase {

	/**
	 * The fixture for this Exemplar test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Exemplar fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ExemplarTest.class);
	}

	/**
	 * Constructs a new Exemplar test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExemplarTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Exemplar test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Exemplar fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Exemplar test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Exemplar getFixture() {
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
		setFixture(ModelFactory.eINSTANCE.createExemplar());
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

} //ExemplarTest
