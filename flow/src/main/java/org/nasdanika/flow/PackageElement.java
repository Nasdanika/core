/**
 */
package org.nasdanika.flow;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.ncore.NamedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.flow.PackageElement#getPrototype <em>Prototype</em>}</li>
 *   <li>{@link org.nasdanika.flow.PackageElement#getExtensions <em>Extensions</em>}</li>
 *   <li>{@link org.nasdanika.flow.PackageElement#getExtends <em>Extends</em>}</li>
 *   <li>{@link org.nasdanika.flow.PackageElement#getModifiers <em>Modifiers</em>}</li>
 *   <li>{@link org.nasdanika.flow.PackageElement#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.flow.FlowPackage#getPackageElement()
 * @model abstract="true"
 *        annotation="urn:org.nasdanika documentation-reference='doc/package-element.md'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='final extension'"
 * @generated
 */
public interface PackageElement<T extends PackageElement<T>> extends NamedElement {
	/**
	 * Returns the value of the '<em><b>Prototype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This reference is set by ``create()`` method and points to a package element which created and configured this element.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Prototype</em>' reference.
	 * @see #setPrototype(PackageElement)
	 * @see org.nasdanika.flow.FlowPackage#getPackageElement_Prototype()
	 * @model
	 * @generated
	 */
	T getPrototype();

	/**
	 * Sets the value of the '{@link org.nasdanika.flow.PackageElement#getPrototype <em>Prototype</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Prototype</em>' reference.
	 * @see #getPrototype()
	 * @generated
	 */
	void setPrototype(T value);

	/**
	 * Returns the value of the '<em><b>Extensions</b></em>' reference list.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.PackageElement#getExtends <em>Extends</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Derived reference - known elements extending this element.
	 * Known means elements in the same resource set.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extensions</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getPackageElement_Extensions()
	 * @see org.nasdanika.flow.PackageElement#getExtends
	 * @model opposite="extends" transient="true" changeable="false" derived="true"
	 * @generated
	 */
	EList<T> getExtensions();

	/**
	 * Returns the value of the '<em><b>Extends</b></em>' reference list.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.flow.PackageElement#getExtensions <em>Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Elements can  extend other elements and inherit their configuration - set attributes and references. 
	 * For top-level packages inheritance shall be explicitly set using ``superPackages`` reference.
	 * For nested packages and other package elements ``extends`` is derived from containing packages.
	 * For nested packages super-packages derived from containment precede explicitly specified super-packages, 
	 * i.e. the explicitly specified super-packages (mix-ins) can override configuration inherited via containment.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extends</em>' reference list.
	 * @see org.nasdanika.flow.FlowPackage#getPackageElement_Extends()
	 * @see org.nasdanika.flow.PackageElement#getExtensions
	 * @model opposite="extensions" changeable="false" derived="true"
	 * @generated
	 */
	EList<T> getExtends();

	/**
	 * Returns the value of the '<em><b>Modifiers</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A collection of boolean flags:
	 * 
	 * * ``abstract`` - Specifies that this package element is abstract. For packages and flows it means that they contain abstract elements and must be extended to become concrete. If a package or a flow contains abstract elements and does not have abstract modifier, it is diagnosed as an error. If concrete packages and flows extend abstract ones they must override (implement) all abstract elements.
	 * * ``explicit-end`` - Applies to [flows](Flow.html). Specifies that the [end](End.html) [pseudo-state](PseudoState.html) shall not be inferred by finding flow elements with no outputs. End will either be explicitly specified or the diagram will not have an end pseudo-state.
	 * * ``explicit-start`` - Applies to flows. Specifies that the [start](Start.html) pseudo-state shall not be inferred by finding flow elements with no inputs. Start will either be explicitly specified or the diagram will not have a start pseudo-state.
	 * * ``final`` - Specifies that this journey element cannot be overriden in journeys extending this journey. Overriding a final elemen will be diagnosed as an error. For example, in an organization some processes can be defined as journeys at higher levels of the orgnization and extended at lower levels. ``final`` modifier allows to specify what can be extended and what cannot. Specifying a top-level journey as final indicates that it cannot have extensions.
	 * * ``optional`` - Specifies that this element is optional. Optional elements have different apperance on diagrams.
	 * * ``extension`` - Specifies that this element is an extension for an element in one of extended packages/flows. If this modifier is present and ``extends`` reference is empty, then it results in a diagnostic error.
	 * * ``partition`` - Applies to flows and specifies that the flow shall be rendered as a partition (e.g. a composite state) on a digarm.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Modifiers</em>' attribute list.
	 * @see org.nasdanika.flow.FlowPackage#getPackageElement_Modifiers()
	 * @model
	 * @generated
	 */
	EList<String> getModifiers();

	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documentation</em>' containment reference list.
	 * @see org.nasdanika.flow.FlowPackage#getPackageElement_Documentation()
	 * @model containment="true"
	 * @generated
	 */
	EList<EObject> getDocumentation();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Creates a new package element of the same type as this element with ``prototype`` reference to this package element.
	 * For top-level packages this method also calls ``apply()`` and then ``resolve()``.
	 * 
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	T create();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Applies configuration of this element, including inherited configuration, to the argument. 
	 * This method shall be called after ``create()`` and shall create contained elements.
	 * Cross-reference resolution shall be done in ``resolve()`` which is called after ``apply()`` and as such contained elements are already created.
	 * 
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void apply(T instance);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Resolves cross-references.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void resolve(T instance);

} // PackageElement
