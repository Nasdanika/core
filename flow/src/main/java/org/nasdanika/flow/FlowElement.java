/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.ncore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.FlowElement#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getCalls <em>Calls</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getInputArtifacts <em>Input Artifacts</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getDeliverables <em>Deliverables</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getParticipants <em>Participants</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getOverrides <em>Overrides</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getOverridenBy <em>Overriden By</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getModifiers <em>Modifiers</em>}</li>
 *   <li>{@link org.nasdanika.flow.FlowElement#getResources <em>Resources</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getFlowElement()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/flow/journey-element.md'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='final override suppress suppressAndOverride'"
 * @generated
 */
public interface FlowElement extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Outputs</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Transition}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Activity outbound transitions to other activities.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Outputs</em>' containment reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Outputs()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true' strict-containment='true'"
	 * @generated
	 */
	EList<Transition> getOutputs();

	/**
	 * Returns the value of the '<em><b>Calls</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Call}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Calls to other activities.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Calls</em>' containment reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Calls()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true' strict-containment='true'"
	 * @generated
	 */
	EList<Call> getCalls();

	/**
	 * Returns the value of the '<em><b>Input Artifacts</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Artifact}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Input artifacts required to start working on this activity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Input Artifacts</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_InputArtifacts()
	 * @model
	 * @generated
	 */
	EList<Artifact> getInputArtifacts();

	/**
	 * Returns the value of the '<em><b>Deliverables</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Artifact}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Output artifacts of the activity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Deliverables</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Deliverables()
	 * @model
	 * @generated
	 */
	EList<Artifact> getDeliverables();

	/**
	 * Returns the value of the '<em><b>Participants</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Participant}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Personas performing this journey element if it is an activity. Applies to all journey elements in order to group them into persona partitions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Participants</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Participants()
	 * @model
	 * @generated
	 */
	EList<Participant> getParticipants();

	/**
	 * Returns the value of the '<em><b>Overrides</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.FlowElement#getOverridenBy <em>Overriden By</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A journey element which is overriden by this element. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Overrides</em>' reference.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Overrides()
	 * @see org.nasdanika.flow.FlowElement#getOverridenBy
	 * @model opposite="overridenBy" changeable="false" derived="true"
	 * @generated
	 */
	FlowElement getOverrides();

	/**
	 * Returns the value of the '<em><b>Overriden By</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.FlowElement}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.FlowElement#getOverrides <em>Overrides</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Elements of the extension journeys which override this element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Overriden By</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_OverridenBy()
	 * @see org.nasdanika.flow.FlowElement#getOverrides
	 * @model opposite="overrides" changeable="false" derived="true"
	 * @generated
	 */
	EList<FlowElement> getOverridenBy();

	/**
	 * Returns the value of the '<em><b>Modifiers</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A collection of boolean flags:
	 * 
	 * * ``abstract`` - Specifies that this journey element is abstract. For journeys it means that they contain abstract elements and must be extended to become concrete. If a journey contains abstract elements and does not have abstract modifier, it is diagnosed as an error. If concrete journeys extend abstract journeys they must override (implement) all abstract elements.
	 * * ``explicit-end`` - Applies to [journeys](Journey.html). Specifies that the [end](End.html) [pseudo-state](PseudoState.html) shall not be inferred by finding journey elements with no outputs. End will either be explicitly specified or the diagram will not have an end pseudo-state.
	 * * ``explicit-start`` - Applies to journeys. Specifies that the [start](Start.html) pseudo-state shall not be inferred by finding journey elements with no inputs. Start will either be explicitly specified or the diagram will not have a start pseudo-state.
	 * * ``final`` - Specifies that this journey element cannot be overriden in journeys extending this journey. Overriding a final elemen will be diagnosed as an error. For example, in an organization some processes can be defined as journeys at higher levels of the orgnization and extended at lower levels. ``final`` modifier allows to specify what can be extended and what cannot. Specifying a top-level journey as final indicates that it cannot have extensions.
	 * * ``optional`` - Specifies that this journey element is optional. Optional elements have different apperance on diagrams.
	 * * ``override`` - Specifies that this journey element overrides an element in one of extended journeys. If this modifier is present and overrides reference is null, then it results in a diagnostic error.
	 * * ``partition`` - Applies to journeys and specifies that the journey shall be rendered as a partition on a digarm.
	 * * ``suppress`` - Specifies that this element supprresses its overriden element, i.e. removes it from the list of journey elements.
	 * * ``vertical`` - Specifies that journey element direction top-down instead of the default left-to-right.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Modifiers</em>' attribute list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Modifiers()
	 * @model
	 * @generated
	 */
	EList<String> getModifiers();

	/**
	 * Returns the value of the '<em><b>Resources</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.flow.Resource}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resources</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getFlowElement_Resources()
	 * @model
	 * @generated
	 */
	EList<Resource> getResources();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Transitions which have this journey element as its target in a given journey path. 
	 * @param journeyPath Journey nesting path to resolve transitions target elements. May be different from the containment path in the case of nested journeys of extended journeys.
	 * <!-- end-model-doc -->
	 * @model journeyPathRequired="true" journeyPathMany="true"
	 * @generated
	 */
	EList<Transition> getInputs(EList<Flow> journeyPath);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Calls which have this journey element as its target in a given journey path. 
	 * @param journeyPath Journey nesting path to resolve calls target elements. May be different from the containment path in the case of nested journeys of extended journeys.
	 * <!-- end-model-doc -->
	 * @model journeyPathRequired="true" journeyPathMany="true"
	 * @generated
	 */
	EList<Call> getInvocations(EList<Flow> journeyPath);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Opposite to all outputs, i.e. all inbound transitions, in a given journey path.
	 * @param journeyPath Journey nesting path to resolve transitions target elements. May be different from the containment path in the case of nested journeys of extended journeys.
	 * <!-- end-model-doc -->
	 * @model journeyPathRequired="true" journeyPathMany="true"
	 * @generated
	 */
	EList<Transition> getAllInputs(EList<Flow> journeyPath);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Opposite to all calls, i.e. all incoming calls, in a given journey path.
	 * @param journeyPath Journey nesting path to resolve calls target elements. May be different from the containment path in the case of nested journeys of extended journeys.
	 * <!-- end-model-doc -->
	 * @model journeyPathRequired="true" journeyPathMany="true"
	 * @generated
	 */
	EList<Call> getAllInvocations(EList<Flow> journeyPath);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Includes own outputs and outputs inherited from the base journey of the containing journey. This element outputs override inherited outputs with the same path.
	 * @param journeyPath Journey nesting path to resolve transitions target elements. May be different from the containment path in the case of nested journeys of extended journeys.
	 * <!-- end-model-doc -->
	 * @model journeyPathRequired="true" journeyPathMany="true"
	 * @generated
	 */
	EList<Transition> getAllOutputs(EList<Flow> journeyPath);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Includes own calls and calls inherited from the base journey of the containing journey. This element calls override inherited calls with the same path.
	 * @param journeyPath Journey nesting path to resolve calls target elements. May be different from the containment path in the case of nested journeys of extended journeys.
	 * <!-- end-model-doc -->
	 * @model journeyPathRequired="true" journeyPathMany="true"
	 * @generated
	 */
	EList<Call> getAllCalls(EList<Flow> journeyPath);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns ``true`` if this journey element directly or transitively overrided the argument journey element.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean overrides(FlowElement journeyElement);

} // FlowElement
