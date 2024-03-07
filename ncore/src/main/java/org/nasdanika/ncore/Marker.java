/**
 */
package org.nasdanika.ncore;

import java.lang.String;
import java.util.Date;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Marker</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Provides information about location for a model element definition - URI, line and column numbers.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Marker#getLocation <em>Location</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Marker#getPosition <em>Position</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Marker#getComment <em>Comment</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Marker#getDate <em>Date</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getMarker()
 * @model superTypes="org.nasdanika.ncore.IMarker"
 * @generated
 */
public interface Marker extends EObject, org.nasdanika.persistence.Marker {
	/**
	 * Returns the value of the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * URI of a resource.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Location</em>' attribute.
	 * @see #setLocation(String)
	 * @see org.nasdanika.ncore.NcorePackage#getMarker_Location()
	 * @model
	 * @generated
	 */
	String getLocation();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Marker#getLocation <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location</em>' attribute.
	 * @see #getLocation()
	 * @generated
	 */
	void setLocation(String value);

	/**
	 * Returns the value of the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Marker position withing the resource identified by location - line and column in text documents, sheet and range in Excel, ...
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Position</em>' attribute.
	 * @see #setPosition(String)
	 * @see org.nasdanika.ncore.NcorePackage#getMarker_Position()
	 * @model
	 * @generated
	 */
	String getPosition();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Marker#getPosition <em>Position</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Position</em>' attribute.
	 * @see #getPosition()
	 * @generated
	 */
	void setPosition(String value);

	/**
	 * Returns the value of the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Optional marker comment providing additional information about the marker. E.g. for locations which do not support lines and colums it may provide position inside the resource identified by the location attribute, such as an XPath for XML documents or sheet name and a range for Excel documents.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Comment</em>' attribute.
	 * @see #setComment(String)
	 * @see org.nasdanika.ncore.NcorePackage#getMarker_Comment()
	 * @model
	 * @generated
	 */
	String getComment();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Marker#getComment <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comment</em>' attribute.
	 * @see #getComment()
	 * @generated
	 */
	void setComment(String value);

	/**
	 * Returns the value of the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When this marker was created, source data timestamp 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Date</em>' attribute.
	 * @see #setDate(Date)
	 * @see org.nasdanika.ncore.NcorePackage#getMarker_Date()
	 * @model
	 * @generated
	 */
	Date getDate();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Marker#getDate <em>Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Date</em>' attribute.
	 * @see #getDate()
	 * @generated
	 */
	void setDate(Date value);
	
	static Marker wrap(org.nasdanika.persistence.Marker marker) {
		if (marker == null) {
			return null;
		}
		Marker ret = NcoreFactory.eINSTANCE.createMarker();
		ret.setDate(new Date());
		ret.setLocation(marker.getLocation());
		ret.setPosition(marker.getPosition());		
		return ret;
	}

} // Marker
