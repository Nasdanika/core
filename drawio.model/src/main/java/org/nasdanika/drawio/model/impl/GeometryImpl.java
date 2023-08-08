/**
 */
package org.nasdanika.drawio.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.nasdanika.drawio.model.Geometry;
import org.nasdanika.drawio.model.ModelPackage;
import org.nasdanika.drawio.model.Point;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Geometry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.impl.GeometryImpl#getWidth <em>Width</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.GeometryImpl#getHeight <em>Height</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.GeometryImpl#isRelative <em>Relative</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.GeometryImpl#getSourcePoint <em>Source Point</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.GeometryImpl#getTargetPoint <em>Target Point</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.GeometryImpl#getOffsetPoint <em>Offset Point</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.GeometryImpl#getPoints <em>Points</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GeometryImpl extends PointImpl implements Geometry {
	/**
	 * The default value of the '{@link #getWidth() <em>Width</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWidth()
	 * @generated
	 * @ordered
	 */
	protected static final Double WIDTH_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getHeight() <em>Height</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHeight()
	 * @generated
	 * @ordered
	 */
	protected static final Double HEIGHT_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isRelative() <em>Relative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isRelative()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RELATIVE_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GeometryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.GEOMETRY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getWidth() {
		return (Double)eDynamicGet(ModelPackage.GEOMETRY__WIDTH, ModelPackage.Literals.GEOMETRY__WIDTH, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWidth(Double newWidth) {
		eDynamicSet(ModelPackage.GEOMETRY__WIDTH, ModelPackage.Literals.GEOMETRY__WIDTH, newWidth);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Double getHeight() {
		return (Double)eDynamicGet(ModelPackage.GEOMETRY__HEIGHT, ModelPackage.Literals.GEOMETRY__HEIGHT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHeight(Double newHeight) {
		eDynamicSet(ModelPackage.GEOMETRY__HEIGHT, ModelPackage.Literals.GEOMETRY__HEIGHT, newHeight);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isRelative() {
		return (Boolean)eDynamicGet(ModelPackage.GEOMETRY__RELATIVE, ModelPackage.Literals.GEOMETRY__RELATIVE, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRelative(boolean newRelative) {
		eDynamicSet(ModelPackage.GEOMETRY__RELATIVE, ModelPackage.Literals.GEOMETRY__RELATIVE, newRelative);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Point getSourcePoint() {
		return (Point)eDynamicGet(ModelPackage.GEOMETRY__SOURCE_POINT, ModelPackage.Literals.GEOMETRY__SOURCE_POINT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSourcePoint(Point newSourcePoint, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newSourcePoint, ModelPackage.GEOMETRY__SOURCE_POINT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSourcePoint(Point newSourcePoint) {
		eDynamicSet(ModelPackage.GEOMETRY__SOURCE_POINT, ModelPackage.Literals.GEOMETRY__SOURCE_POINT, newSourcePoint);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Point getTargetPoint() {
		return (Point)eDynamicGet(ModelPackage.GEOMETRY__TARGET_POINT, ModelPackage.Literals.GEOMETRY__TARGET_POINT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTargetPoint(Point newTargetPoint, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newTargetPoint, ModelPackage.GEOMETRY__TARGET_POINT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTargetPoint(Point newTargetPoint) {
		eDynamicSet(ModelPackage.GEOMETRY__TARGET_POINT, ModelPackage.Literals.GEOMETRY__TARGET_POINT, newTargetPoint);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Point getOffsetPoint() {
		return (Point)eDynamicGet(ModelPackage.GEOMETRY__OFFSET_POINT, ModelPackage.Literals.GEOMETRY__OFFSET_POINT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOffsetPoint(Point newOffsetPoint, NotificationChain msgs) {
		msgs = eDynamicInverseAdd((InternalEObject)newOffsetPoint, ModelPackage.GEOMETRY__OFFSET_POINT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOffsetPoint(Point newOffsetPoint) {
		eDynamicSet(ModelPackage.GEOMETRY__OFFSET_POINT, ModelPackage.Literals.GEOMETRY__OFFSET_POINT, newOffsetPoint);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public EList<Point> getPoints() {
		return (EList<Point>)eDynamicGet(ModelPackage.GEOMETRY__POINTS, ModelPackage.Literals.GEOMETRY__POINTS, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.GEOMETRY__SOURCE_POINT:
				return basicSetSourcePoint(null, msgs);
			case ModelPackage.GEOMETRY__TARGET_POINT:
				return basicSetTargetPoint(null, msgs);
			case ModelPackage.GEOMETRY__OFFSET_POINT:
				return basicSetOffsetPoint(null, msgs);
			case ModelPackage.GEOMETRY__POINTS:
				return ((InternalEList<?>)getPoints()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.GEOMETRY__WIDTH:
				return getWidth();
			case ModelPackage.GEOMETRY__HEIGHT:
				return getHeight();
			case ModelPackage.GEOMETRY__RELATIVE:
				return isRelative();
			case ModelPackage.GEOMETRY__SOURCE_POINT:
				return getSourcePoint();
			case ModelPackage.GEOMETRY__TARGET_POINT:
				return getTargetPoint();
			case ModelPackage.GEOMETRY__OFFSET_POINT:
				return getOffsetPoint();
			case ModelPackage.GEOMETRY__POINTS:
				return getPoints();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.GEOMETRY__WIDTH:
				setWidth((Double)newValue);
				return;
			case ModelPackage.GEOMETRY__HEIGHT:
				setHeight((Double)newValue);
				return;
			case ModelPackage.GEOMETRY__RELATIVE:
				setRelative((Boolean)newValue);
				return;
			case ModelPackage.GEOMETRY__SOURCE_POINT:
				setSourcePoint((Point)newValue);
				return;
			case ModelPackage.GEOMETRY__TARGET_POINT:
				setTargetPoint((Point)newValue);
				return;
			case ModelPackage.GEOMETRY__OFFSET_POINT:
				setOffsetPoint((Point)newValue);
				return;
			case ModelPackage.GEOMETRY__POINTS:
				getPoints().clear();
				getPoints().addAll((Collection<? extends Point>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.GEOMETRY__WIDTH:
				setWidth(WIDTH_EDEFAULT);
				return;
			case ModelPackage.GEOMETRY__HEIGHT:
				setHeight(HEIGHT_EDEFAULT);
				return;
			case ModelPackage.GEOMETRY__RELATIVE:
				setRelative(RELATIVE_EDEFAULT);
				return;
			case ModelPackage.GEOMETRY__SOURCE_POINT:
				setSourcePoint((Point)null);
				return;
			case ModelPackage.GEOMETRY__TARGET_POINT:
				setTargetPoint((Point)null);
				return;
			case ModelPackage.GEOMETRY__OFFSET_POINT:
				setOffsetPoint((Point)null);
				return;
			case ModelPackage.GEOMETRY__POINTS:
				getPoints().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.GEOMETRY__WIDTH:
				return WIDTH_EDEFAULT == null ? getWidth() != null : !WIDTH_EDEFAULT.equals(getWidth());
			case ModelPackage.GEOMETRY__HEIGHT:
				return HEIGHT_EDEFAULT == null ? getHeight() != null : !HEIGHT_EDEFAULT.equals(getHeight());
			case ModelPackage.GEOMETRY__RELATIVE:
				return isRelative() != RELATIVE_EDEFAULT;
			case ModelPackage.GEOMETRY__SOURCE_POINT:
				return getSourcePoint() != null;
			case ModelPackage.GEOMETRY__TARGET_POINT:
				return getTargetPoint() != null;
			case ModelPackage.GEOMETRY__OFFSET_POINT:
				return getOffsetPoint() != null;
			case ModelPackage.GEOMETRY__POINTS:
				return !getPoints().isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //GeometryImpl
