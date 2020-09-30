/**
 */
package org.nasdanika.engineering;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Persona</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Description from internet, e.g. Wikipedia. Abstraction of parties who benefit from organization offerings. Not necessarily pay for them or use them. Weight - manual or computed with decision analysis. Manual may be sales volume, budget contribution for internal customers, population, percentage in profit generation. E.g. a grant size is dependent on how many personas adopt a product. References parties are persona representatives. Such parties may be involved in need identification and prioritization. Defined at EngineeringOrgUnit level - may be internal personas and internal products. May have base personas and personas can be organized into a hierarchy. Base personas define common needs, what everybody needs. Base personas may be abstract - no representatives and no own weight.
 * Reference roles in addition to representatives - internal clients.
 * 
 * 
 * Persona benefits from engineering organization outputs (offerings). Not necessarily buys or uses.
 * Persona is an engineered element - owner, issues, ... representatives
 * 
 * Resources reference and palette - markdown docs, rigel flows, ... - party level. Embedded and references. Folders
 * 
 * 
 * Rigel activities defined in roles (actor). Prof extensions report which flows role participates in.
 * 
 * Needs, scenarios, offerings
 * 
 * Customer value and strategic value/alignment - objectives.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.engineering.Persona#getNeeds <em>Needs</em>}</li>
 *   <li>{@link org.nasdanika.engineering.Persona#getExtends <em>Extends</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getPersona()
 * @model
 * @generated
 */
public interface Persona extends ModelElement {

	/**
	 * Returns the value of the '<em><b>Needs</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.NeedCategoryElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Needs</em>' containment reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getPersona_Needs()
	 * @model containment="true"
	 * @generated
	 */
	EList<NeedCategoryElement> getNeeds();

	/**
	 * Returns the value of the '<em><b>Extends</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.engineering.Persona}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Description from internet, e.g. Wikipedia. Abstraction of parties who benefit from organization offerings. Not necessarily pay for them or use them. Weight - manual or computed with decision analysis. Manual may be sales volume, budget contribution for internal customers, population, percentage in profit generation. E.g. a grant size is dependent on how many personas adopt a product. References parties are persona representatives. Such parties may be involved in need identification and prioritization. Defined at EngineeringOrgUnit level - may be internal personas and internal products. May have base personas and personas can be organized into a hierarchy. Base personas define common needs, what everybody needs. Base personas may be abstract - no representatives and no own weight.
	 * Reference roles in addition to representatives - internal clients.
	 * 
	 * 
	 * Persona benefits from engineering organization outputs (offerings). Not necessarily buys or uses.
	 * Persona is an engineered element - owner, issues, ... representatives
	 * 
	 * Resources reference and palette - markdown docs, rigel flows, ... - party level. Embedded and references. Folders
	 * 
	 * 
	 * Rigel activities defined in roles (actor). Prof extensions report which flows role participates in.
	 * 
	 * Needs, scenarios, offerings
	 * 
	 * Customer value and strategic value/alignment - objectives.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extends</em>' reference list.
	 * @see org.nasdanika.engineering.EngineeringPackage#getPersona_Extends()
	 * @model
	 * @generated
	 */
	EList<Persona> getExtends();
} // Persona
