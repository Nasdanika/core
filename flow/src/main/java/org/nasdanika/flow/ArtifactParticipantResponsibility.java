/**
 */
package org.nasdanika.flow;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Artifact Participant Responsibility</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.ArtifactParticipantResponsibility#getArtifactKey <em>Artifact Key</em>}</li>
 *   <li>{@link org.nasdanika.flow.ArtifactParticipantResponsibility#getArtifact <em>Artifact</em>}</li>
 *   <li>{@link org.nasdanika.flow.ArtifactParticipantResponsibility#isSuppress <em>Suppress</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getArtifactParticipantResponsibility()
 * @model
 * @generated
 */
public interface ArtifactParticipantResponsibility extends ParticipantResponsibility<ArtifactParticipantResponsibility> {
	/**
	 * Returns the value of the '<em><b>Artifact Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Key of the responsibility artifact relative to the containing package ``artifacts/`` reference.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Artifact Key</em>' attribute.
	 * @see #setArtifactKey(String)
	 * @see org.nasdanika.flow.FlowPackage#getArtifactParticipantResponsibility_ArtifactKey()
	 * @model required="true"
	 *        annotation="urn:org.nasdanika load-key='artifact'"
	 * @generated
	 */
	String getArtifactKey();

	/**
	 * Sets the value of the '{@link org.nasdanika.flow.ArtifactParticipantResponsibility#getArtifactKey <em>Artifact Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Artifact Key</em>' attribute.
	 * @see #getArtifactKey()
	 * @generated
	 */
	void setArtifactKey(String value);

	/**
	 * Returns the value of the '<em><b>Artifact</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.Artifact#getResponsibilities <em>Responsibilities</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Transition target.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Artifact</em>' reference.
	 * @see org.nasdanika.flow.FlowPackage#getArtifactParticipantResponsibility_Artifact()
	 * @see org.nasdanika.flow.Artifact#getResponsibilities
	 * @model opposite="responsibilities" changeable="false" derived="true"
	 * @generated
	 */
	Artifact getArtifact();

	/**
	 * Returns the value of the '<em><b>Suppress</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, suppresses inherited responsibility for this responsibility's artifact.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Suppress</em>' attribute.
	 * @see #setSuppress(boolean)
	 * @see org.nasdanika.flow.FlowPackage#getArtifactParticipantResponsibility_Suppress()
	 * @model
	 * @generated
	 */
	boolean isSuppress();

	/**
	 * Sets the value of the '{@link org.nasdanika.flow.ArtifactParticipantResponsibility#isSuppress <em>Suppress</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Suppress</em>' attribute.
	 * @see #isSuppress()
	 * @generated
	 */
	void setSuppress(boolean value);

} // ArtifactParticipantResponsibility
