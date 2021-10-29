/**
 */
package org.nasdanika.diagram.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nasdanika.diagram.Connection;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.diagram.DiagramFactory;
import org.nasdanika.diagram.DiagramPackage;
import org.nasdanika.diagram.End;
import org.nasdanika.diagram.Link;
import org.nasdanika.diagram.Note;
import org.nasdanika.diagram.NotePlacement;
import org.nasdanika.diagram.Start;
import org.nasdanika.diagram.Style;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DiagramPackageImpl extends EPackageImpl implements DiagramPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass linkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass noteEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass styleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagramElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass diagramEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass connectionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass startEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass endEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum notePlacementEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.nasdanika.diagram.DiagramPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DiagramPackageImpl() {
		super(eNS_URI, DiagramFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link DiagramPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DiagramPackage init() {
		if (isInited) return (DiagramPackage)EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredDiagramPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		DiagramPackageImpl theDiagramPackage = registeredDiagramPackage instanceof DiagramPackageImpl ? (DiagramPackageImpl)registeredDiagramPackage : new DiagramPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theDiagramPackage.createPackageContents();

		// Initialize created meta-data
		theDiagramPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDiagramPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(DiagramPackage.eNS_URI, theDiagramPackage);
		return theDiagramPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLink() {
		return linkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLink_Text() {
		return (EAttribute)linkEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLink_Location() {
		return (EAttribute)linkEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLink_Tooltip() {
		return (EAttribute)linkEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNote() {
		return noteEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNote_Text() {
		return (EAttribute)noteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getNote_Content() {
		return (EReference)noteEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNote_Placement() {
		return (EAttribute)noteEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStyle() {
		return styleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStyle_Type() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStyle_Notes() {
		return (EReference)styleEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStyle_Description() {
		return (EReference)styleEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStyle_Color() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStyle_Dashed() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStyle_Dotted() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStyle_Bold() {
		return (EAttribute)styleEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDiagramElement() {
		return diagramElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDiagramElement_Id() {
		return (EAttribute)diagramElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDiagramElement_Name() {
		return (EReference)diagramElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDiagramElement_Elements() {
		return (EReference)diagramElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDiagramElement_Connections() {
		return (EReference)diagramElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDiagramElement_Stereotype() {
		return (EAttribute)diagramElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDiagramElement_Gradient() {
		return (EAttribute)diagramElementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDiagramElement_Border() {
		return (EAttribute)diagramElementEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDiagram() {
		return diagramEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDiagram_Elements() {
		return (EReference)diagramEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDiagram_Vertical() {
		return (EAttribute)diagramEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDiagram_HideEmptyDescription() {
		return (EAttribute)diagramEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDiagram_HideFootbox() {
		return (EAttribute)diagramEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConnection() {
		return connectionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConnection_Target() {
		return (EReference)connectionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConnection_Thickness() {
		return (EAttribute)connectionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStart() {
		return startEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEnd() {
		return endEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getNotePlacement() {
		return notePlacementEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DiagramFactory getDiagramFactory() {
		return (DiagramFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		linkEClass = createEClass(LINK);
		createEAttribute(linkEClass, LINK__TEXT);
		createEAttribute(linkEClass, LINK__LOCATION);
		createEAttribute(linkEClass, LINK__TOOLTIP);

		noteEClass = createEClass(NOTE);
		createEAttribute(noteEClass, NOTE__TEXT);
		createEReference(noteEClass, NOTE__CONTENT);
		createEAttribute(noteEClass, NOTE__PLACEMENT);

		styleEClass = createEClass(STYLE);
		createEAttribute(styleEClass, STYLE__TYPE);
		createEReference(styleEClass, STYLE__NOTES);
		createEReference(styleEClass, STYLE__DESCRIPTION);
		createEAttribute(styleEClass, STYLE__COLOR);
		createEAttribute(styleEClass, STYLE__DASHED);
		createEAttribute(styleEClass, STYLE__DOTTED);
		createEAttribute(styleEClass, STYLE__BOLD);

		diagramElementEClass = createEClass(DIAGRAM_ELEMENT);
		createEAttribute(diagramElementEClass, DIAGRAM_ELEMENT__ID);
		createEReference(diagramElementEClass, DIAGRAM_ELEMENT__NAME);
		createEReference(diagramElementEClass, DIAGRAM_ELEMENT__ELEMENTS);
		createEReference(diagramElementEClass, DIAGRAM_ELEMENT__CONNECTIONS);
		createEAttribute(diagramElementEClass, DIAGRAM_ELEMENT__STEREOTYPE);
		createEAttribute(diagramElementEClass, DIAGRAM_ELEMENT__GRADIENT);
		createEAttribute(diagramElementEClass, DIAGRAM_ELEMENT__BORDER);

		diagramEClass = createEClass(DIAGRAM);
		createEReference(diagramEClass, DIAGRAM__ELEMENTS);
		createEAttribute(diagramEClass, DIAGRAM__VERTICAL);
		createEAttribute(diagramEClass, DIAGRAM__HIDE_EMPTY_DESCRIPTION);
		createEAttribute(diagramEClass, DIAGRAM__HIDE_FOOTBOX);

		connectionEClass = createEClass(CONNECTION);
		createEReference(connectionEClass, CONNECTION__TARGET);
		createEAttribute(connectionEClass, CONNECTION__THICKNESS);

		startEClass = createEClass(START);

		endEClass = createEClass(END);

		// Create enums
		notePlacementEEnum = createEEnum(NOTE_PLACEMENT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		diagramElementEClass.getESuperTypes().add(this.getLink());
		diagramElementEClass.getESuperTypes().add(this.getStyle());
		connectionEClass.getESuperTypes().add(this.getStyle());
		startEClass.getESuperTypes().add(this.getDiagramElement());
		endEClass.getESuperTypes().add(this.getDiagramElement());

		// Initialize classes, features, and operations; add parameters
		initEClass(linkEClass, Link.class, "Link", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLink_Text(), ecorePackage.getEString(), "text", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLink_Location(), ecorePackage.getEString(), "location", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLink_Tooltip(), ecorePackage.getEString(), "tooltip", null, 0, 1, Link.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(noteEClass, Note.class, "Note", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNote_Text(), ecorePackage.getEString(), "text", null, 0, 1, Note.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNote_Content(), ecorePackage.getEObject(), null, "content", null, 0, -1, Note.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getNote_Placement(), this.getNotePlacement(), "placement", "Right", 0, 1, Note.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(styleEClass, Style.class, "Style", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStyle_Type(), ecorePackage.getEString(), "type", "", 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStyle_Notes(), this.getNote(), null, "notes", null, 0, -1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getStyle_Description(), ecorePackage.getEObject(), null, "description", null, 0, -1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStyle_Color(), ecorePackage.getEString(), "color", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStyle_Dashed(), ecorePackage.getEBoolean(), "dashed", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStyle_Dotted(), ecorePackage.getEBoolean(), "dotted", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStyle_Bold(), ecorePackage.getEBoolean(), "bold", null, 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(diagramElementEClass, DiagramElement.class, "DiagramElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDiagramElement_Id(), ecorePackage.getEString(), "id", null, 0, 1, DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDiagramElement_Name(), ecorePackage.getEObject(), null, "name", null, 0, -1, DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDiagramElement_Elements(), this.getDiagramElement(), null, "elements", null, 0, -1, DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDiagramElement_Connections(), this.getConnection(), null, "connections", null, 0, -1, DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDiagramElement_Stereotype(), ecorePackage.getEString(), "stereotype", null, 0, 1, DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDiagramElement_Gradient(), ecorePackage.getEString(), "gradient", null, 0, 1, DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDiagramElement_Border(), ecorePackage.getEString(), "border", null, 0, 1, DiagramElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(diagramEClass, Diagram.class, "Diagram", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDiagram_Elements(), this.getDiagramElement(), null, "elements", null, 0, -1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDiagram_Vertical(), ecorePackage.getEBoolean(), "vertical", "true", 0, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDiagram_HideEmptyDescription(), ecorePackage.getEBoolean(), "hideEmptyDescription", null, 0, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDiagram_HideFootbox(), ecorePackage.getEBoolean(), "hideFootbox", "true", 0, 1, Diagram.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(connectionEClass, Connection.class, "Connection", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConnection_Target(), this.getDiagramElement(), null, "target", null, 0, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getConnection_Thickness(), ecorePackage.getEInt(), "thickness", null, 0, 1, Connection.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(startEClass, Start.class, "Start", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(endEClass, End.class, "End", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize enums and add enum literals
		initEEnum(notePlacementEEnum, NotePlacement.class, "NotePlacement");
		addEEnumLiteral(notePlacementEEnum, NotePlacement.TOP);
		addEEnumLiteral(notePlacementEEnum, NotePlacement.BOTTOM);
		addEEnumLiteral(notePlacementEEnum, NotePlacement.LEFT);
		addEEnumLiteral(notePlacementEEnum, NotePlacement.RIGHT);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// urn:org.nasdanika
		createUrnorgAnnotations();
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
	}

	/**
	 * Initializes the annotations for <b>urn:org.nasdanika</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createUrnorgAnnotations() {
		String source = "urn:org.nasdanika";
		addAnnotation
		  (this,
		   source,
		   new String[] {
			   "documentation-reference", "doc/package-summary.md"
		   });
		addAnnotation
		  (linkEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/link.md"
		   });
		addAnnotation
		  (noteEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/note.md"
		   });
		addAnnotation
		  (getNote_Text(),
		   source,
		   new String[] {
			   "default-feature", "true"
		   });
		addAnnotation
		  (styleEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/style.md"
		   });
		addAnnotation
		  (getStyle_Notes(),
		   source,
		   new String[] {
			   "homogenous", "true",
			   "strict-containment", "true"
		   });
		addAnnotation
		  (diagramElementEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/diagram-element.md"
		   });
		addAnnotation
		  (diagramEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/diagram.md"
		   });
		addAnnotation
		  (connectionEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/connection.md"
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/GenModel</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGenModelAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/GenModel";
		addAnnotation
		  (getLink_Text(),
		   source,
		   new String[] {
			   "documentation", "Link text. Can be empty for diagram elements where text is supplied by the ``name`` reference. If not empty for diagram elements - precedes the name elements."
		   });
		addAnnotation
		  (getLink_Location(),
		   source,
		   new String[] {
			   "documentation", "Link URL. If blank linik is rendered as plain text and tooltip is ignored."
		   });
		addAnnotation
		  (getLink_Tooltip(),
		   source,
		   new String[] {
			   "documentation", "Optional link tooltip."
		   });
		addAnnotation
		  (notePlacementEEnum,
		   source,
		   new String[] {
			   "documentation", "Note placement for diagram elements. Ignored for connections."
		   });
		addAnnotation
		  (getNote_Text(),
		   source,
		   new String[] {
			   "documentation", "Note text. Can be empty for notes where text is supplied by the ``content`` reference. If not empty  - precedes the content elements."
		   });
		addAnnotation
		  (getNote_Content(),
		   source,
		   new String[] {
			   "documentation", "Composite note content to support having links. Elements shall be of type Link of Exec.content.Text."
		   });
		addAnnotation
		  (getNote_Placement(),
		   source,
		   new String[] {
			   "documentation", "Note placement for diagram elements. Ignored for connections."
		   });
		addAnnotation
		  (getStyle_Type(),
		   source,
		   new String[] {
			   "documentation", "Connection type. E.g. ``-->`` or ``..>``. To support styling connection type shall contain ``${style}`` token which will be expanded during generation. E.g. ``-${style}->``."
		   });
		addAnnotation
		  (getStyle_Notes(),
		   source,
		   new String[] {
			   "documentation", "Element notes."
		   });
		addAnnotation
		  (getStyle_Description(),
		   source,
		   new String[] {
			   "documentation", "Composite connection description to support having links in the description. Elements shall be of type Link of Exec.content.Text."
		   });
		addAnnotation
		  (getStyle_Color(),
		   source,
		   new String[] {
			   "documentation", "Element color. E.g. ``lightblue`` or ``DDDDDD``."
		   });
		addAnnotation
		  (getStyle_Dashed(),
		   source,
		   new String[] {
			   "documentation", "Dashed border style."
		   });
		addAnnotation
		  (getStyle_Dotted(),
		   source,
		   new String[] {
			   "documentation", "Dotted border style."
		   });
		addAnnotation
		  (getStyle_Bold(),
		   source,
		   new String[] {
			   "documentation", "Bold border style."
		   });
		addAnnotation
		  (getDiagramElement_Name(),
		   source,
		   new String[] {
			   "documentation", "Composite element name to support having links in the name. Elements shall be of type Link of Exec.content.Text."
		   });
		addAnnotation
		  (getDiagramElement_Elements(),
		   source,
		   new String[] {
			   "documentation", "Nested diagram elements."
		   });
		addAnnotation
		  (getDiagramElement_Connections(),
		   source,
		   new String[] {
			   "documentation", "Outbound connections."
		   });
		addAnnotation
		  (getDiagramElement_Stereotype(),
		   source,
		   new String[] {
			   "documentation", "Element stereotype. E.g. ``choice`` or ``fork``."
		   });
		addAnnotation
		  (getDiagramElement_Gradient(),
		   source,
		   new String[] {
			   "documentation", "Gradient color - if set the node is displayed with a gradient from ``color`` to ``gradient``."
		   });
		addAnnotation
		  (getDiagramElement_Border(),
		   source,
		   new String[] {
			   "documentation", "Border color. E.g. ``lightblue`` or ``DDDDDD``."
		   });
		addAnnotation
		  (getDiagram_HideEmptyDescription(),
		   source,
		   new String[] {
			   "documentation", "Hides empty descriptions on state diagrams."
		   });
		addAnnotation
		  (getDiagram_HideFootbox(),
		   source,
		   new String[] {
			   "documentation", "Hides footbox on sequence diagrams."
		   });
		addAnnotation
		  (getConnection_Thickness(),
		   source,
		   new String[] {
			   "documentation", "Connection thickness."
		   });
		addAnnotation
		  (startEClass,
		   source,
		   new String[] {
			   "documentation", "Start pseudo-state."
		   });
		addAnnotation
		  (endEClass,
		   source,
		   new String[] {
			   "documentation", "End pseudo-state."
		   });
	}

} //DiagramPackageImpl
