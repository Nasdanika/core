/**
 */
package org.nasdanika.rigel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Base class for other model elements.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.rigel.ModelElement#getName <em>Name</em>}</li>
 *   <li>{@link org.nasdanika.rigel.ModelElement#getUrl <em>Url</em>}</li>
 *   <li>{@link org.nasdanika.rigel.ModelElement#getDescription <em>Description</em>}</li>
 *   <li>{@link org.nasdanika.rigel.ModelElement#getConfiguration <em>Configuration</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.rigel.RigelPackage#getModelElement()
 * @model abstract="true"
 *        annotation="urn:org.nasdanika label_ru='\u042d\u043b\u0435\u043c\u0435\u043d\u0442 \u043c\u043e\u0434\u0435\u043b\u0438' Documentation_ru='\u0411\u0430\u0437\u043e\u0432\u044b\u0439 \u043a\u043b\u0430\u0441\u0441 \u0434\u043b\u044f \u0434\u0440\u0443\u0433\u0438\u0445 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u043e\u0432 \u043c\u043e\u0434\u0435\u043b\u0438'"
 * @generated
 */
public interface ModelElement extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Element name. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.nasdanika.rigel.RigelPackage#getModelElement_Name()
	 * @model annotation="urn:org.nasdanika label_ru='\u041d\u0430\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u043d\u0438\u0435' Documentation_ru='\u041d\u0430\u0438\u043c\u0435\u043d\u043e\u0432\u0430\u043d\u0438\u0435 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430 \u043c\u043e\u0434\u0435\u043b\u0438'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.nasdanika.rigel.ModelElement#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Optional element URL. Resolved relative to the containing element URL. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see org.nasdanika.rigel.RigelPackage#getModelElement_Url()
	 * @model
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link org.nasdanika.rigel.ModelElement#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Element description in [markdown](https://en.wikipedia.org/wiki/Markdown).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.nasdanika.rigel.RigelPackage#getModelElement_Description()
	 * @model annotation="urn:org.nasdanika label_ru='\u041e\u043f\u0438\u0441\u0430\u043d\u0438\u0435' Documentation_ru='\u041e\u043f\u0438\u0441\u0430\u043d\u0438\u0435 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430 \u043c\u043e\u0434\u0435\u043b\u0438 \u0441 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u043d\u0438\u0435\u043c \u0441\u0438\u043d\u0442\u0430\u043a\u0441\u0438\u0441\u0430 [markdown](https://en.wikipedia.org/wiki/Markdown).'"
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link org.nasdanika.rigel.ModelElement#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Configuration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Element configuration in [YAML](https://en.wikipedia.org/wiki/YAML). Configuration is element-specific and it can be used for, say, analysis or runtime configuration. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Configuration</em>' attribute.
	 * @see #setConfiguration(String)
	 * @see org.nasdanika.rigel.RigelPackage#getModelElement_Configuration()
	 * @model annotation="urn:org.nasdanika label_ru='\u041a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u044f' Documentation_ru='*\u041a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u044f* \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430 \u0432 [YAML] (https://en.wikipedia.org/wiki/YAML). \u041a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u044f \u0437\u0430\u0432\u0438\u0441\u0438\u0442 \u043e\u0442 \u044d\u043b\u0435\u043c\u0435\u043d\u0442\u0430 \u0438 \u043c\u043e\u0436\u0435\u0442 \u0438\u0441\u043f\u043e\u043b\u044c\u0437\u043e\u0432\u0430\u0442\u044c\u0441\u044f, \u043d\u0430\u043f\u0440\u0438\u043c\u0435\u0440, \u0434\u043b\u044f \u0430\u043d\u0430\u043b\u0438\u0437\u0430 \u0438\u043b\u0438 \u043a\u043e\u043d\u0444\u0438\u0433\u0443\u0440\u0430\u0446\u0438\u0438 \u0432\u043e \u0432\u0440\u0435\u043c\u044f \u0432\u044b\u043f\u043e\u043b\u043d\u0435\u043d\u0438\u044f.'"
	 * @generated
	 */
	String getConfiguration();

	/**
	 * Sets the value of the '{@link org.nasdanika.rigel.ModelElement#getConfiguration <em>Configuration</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Configuration</em>' attribute.
	 * @see #getConfiguration()
	 * @generated
	 */
	void setConfiguration(String value);

} // ModelElement
