/**
 */
package org.nasdanika.engineering;

import org.nasdanika.ncore.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Risk</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * TODO - ISO 31000 definition. Positive flag, risk category, quantitative and qualitative.
 * For qualitatieve - likelihood and impact reference value in the engineering org with quantification in percents. E.g. High likelihood is 50%, High impact is 50%. 
 * Qualitative risk is in numbers - percents for likelihood, absolute numbers for impact, e.g. 8 hours impact on effort, $1000 dollars impact on cost.
 * Risks affect effective value (benefit/cost). 
 * 
 * Probability of an adverse event which will affect effort, cost, or totally prevent issue implementation. Attributes such as probability, affect on effort and cost, show-stopper flag.
 * <!-- end-model-doc -->
 *
 *
 * @see org.nasdanika.engineering.EngineeringPackage#getRisk()
 * @model
 * @generated
 */
public interface Risk extends ModelElement {
} // Risk
