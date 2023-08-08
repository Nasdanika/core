/**
 */
package org.nasdanika.drawio.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Geometry</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.Geometry#getWidth <em>Width</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.Geometry#getHeight <em>Height</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.Geometry#isRelative <em>Relative</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.Geometry#getSourcePoint <em>Source Point</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.Geometry#getTargetPoint <em>Target Point</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.Geometry#getOffsetPoint <em>Offset Point</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.Geometry#getPoints <em>Points</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.drawio.model.ModelPackage#getGeometry()
 * @model
 * @generated
 */
public interface Geometry extends Point {
	/**
	 * Returns the value of the '<em><b>Width</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Width</em>' attribute.
	 * @see #setWidth(Double)
	 * @see org.nasdanika.drawio.model.ModelPackage#getGeometry_Width()
	 * @model
	 * @generated
	 */
	Double getWidth();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.Geometry#getWidth <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Width</em>' attribute.
	 * @see #getWidth()
	 * @generated
	 */
	void setWidth(Double value);

	/**
	 * Returns the value of the '<em><b>Height</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Height</em>' attribute.
	 * @see #setHeight(Double)
	 * @see org.nasdanika.drawio.model.ModelPackage#getGeometry_Height()
	 * @model
	 * @generated
	 */
	Double getHeight();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.Geometry#getHeight <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Height</em>' attribute.
	 * @see #getHeight()
	 * @generated
	 */
	void setHeight(Double value);

	/**
	 * Returns the value of the '<em><b>Relative</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Relative</em>' attribute.
	 * @see #setRelative(boolean)
	 * @see org.nasdanika.drawio.model.ModelPackage#getGeometry_Relative()
	 * @model
	 * @generated
	 */
	boolean isRelative();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.Geometry#isRelative <em>Relative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Relative</em>' attribute.
	 * @see #isRelative()
	 * @generated
	 */
	void setRelative(boolean value);

	/**
	 * Returns the value of the '<em><b>Source Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source Point</em>' containment reference.
	 * @see #setSourcePoint(Point)
	 * @see org.nasdanika.drawio.model.ModelPackage#getGeometry_SourcePoint()
	 * @model containment="true"
	 * @generated
	 */
	Point getSourcePoint();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.Geometry#getSourcePoint <em>Source Point</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Point</em>' containment reference.
	 * @see #getSourcePoint()
	 * @generated
	 */
	void setSourcePoint(Point value);

	/**
	 * Returns the value of the '<em><b>Target Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target Point</em>' containment reference.
	 * @see #setTargetPoint(Point)
	 * @see org.nasdanika.drawio.model.ModelPackage#getGeometry_TargetPoint()
	 * @model containment="true"
	 * @generated
	 */
	Point getTargetPoint();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.Geometry#getTargetPoint <em>Target Point</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target Point</em>' containment reference.
	 * @see #getTargetPoint()
	 * @generated
	 */
	void setTargetPoint(Point value);

	/**
	 * Returns the value of the '<em><b>Offset Point</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Offset Point</em>' containment reference.
	 * @see #setOffsetPoint(Point)
	 * @see org.nasdanika.drawio.model.ModelPackage#getGeometry_OffsetPoint()
	 * @model containment="true"
	 * @generated
	 */
	Point getOffsetPoint();

	/**
	 * Sets the value of the '{@link org.nasdanika.drawio.model.Geometry#getOffsetPoint <em>Offset Point</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Offset Point</em>' containment reference.
	 * @see #getOffsetPoint()
	 * @generated
	 */
	void setOffsetPoint(Point value);

	/**
	 * Returns the value of the '<em><b>Points</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.drawio.model.Point}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Points</em>' containment reference list.
	 * @see org.nasdanika.drawio.model.ModelPackage#getGeometry_Points()
	 * @model containment="true"
	 * @generated
	 */
	EList<Point> getPoints();

} // Geometry
