/**
 */
package org.nasdanika.telemetry.model.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import org.nasdanika.telemetry.model.ModelFactory;
import org.nasdanika.telemetry.model.SpanEvent;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Span Event</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class SpanEventTest extends TestCase {

	/**
	 * The fixture for this Span Event test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpanEvent fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(SpanEventTest.class);
	}

	/**
	 * Constructs a new Span Event test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SpanEventTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Span Event test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(SpanEvent fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Span Event test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SpanEvent getFixture() {
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
		setFixture(ModelFactory.eINSTANCE.createSpanEvent());
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

} //SpanEventTest
