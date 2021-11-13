/**
 */
package org.nasdanika.ncore.impl;

import java.time.Duration;
import java.time.Instant;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.nasdanika.common.Adaptable;
import org.nasdanika.ncore.List;
import org.nasdanika.ncore.ListProperty;
import org.nasdanika.ncore.Map;
import org.nasdanika.ncore.MapProperty;
import org.nasdanika.ncore.Marked;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcoreFactory;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Period;
import org.nasdanika.ncore.Property;
import org.nasdanika.ncore.Reference;
import org.nasdanika.ncore.StringProperty;
import org.nasdanika.ncore.Temporal;
import org.nasdanika.ncore.util.NcoreValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NcorePackageImpl extends EPackageImpl implements NcorePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass adaptableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iMarkedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass markedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iMarkerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass markerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass temporalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass periodEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mapEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass mapPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass listPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType instantEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType durationEDataType = null;

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
	 * @see org.nasdanika.ncore.NcorePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private NcorePackageImpl() {
		super(eNS_URI, NcoreFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link NcorePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static NcorePackage init() {
		if (isInited) return (NcorePackage)EPackage.Registry.INSTANCE.getEPackage(NcorePackage.eNS_URI);

		// Obtain or create and register package
		Object registeredNcorePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		NcorePackageImpl theNcorePackage = registeredNcorePackage instanceof NcorePackageImpl ? (NcorePackageImpl)registeredNcorePackage : new NcorePackageImpl();

		isInited = true;

		// Create package meta-data objects
		theNcorePackage.createPackageContents();

		// Initialize created meta-data
		theNcorePackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theNcorePackage,
			 new EValidator.Descriptor() {
				 @Override
				 public EValidator getEValidator() {
					 return NcoreValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theNcorePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(NcorePackage.eNS_URI, theNcorePackage);
		return theNcorePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAdaptable() {
		return adaptableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIMarked() {
		return iMarkedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMarked() {
		return markedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMarked_Marker() {
		return (EReference)markedEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIMarker() {
		return iMarkerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMarker() {
		return markerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMarker_Location() {
		return (EAttribute)markerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMarker_Line() {
		return (EAttribute)markerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMarker_Column() {
		return (EAttribute)markerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTemporal() {
		return temporalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTemporal_Instant() {
		return (EAttribute)temporalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemporal_Base() {
		return (EReference)temporalEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTemporal_Offset() {
		return (EAttribute)temporalEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemporal_Derivatives() {
		return (EReference)temporalEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemporal_LowerBounds() {
		return (EReference)temporalEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemporal_UpperBounds() {
		return (EReference)temporalEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getTemporal__After__Temporal() {
		return temporalEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getTemporal__Before__Temporal() {
		return temporalEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getTemporal__Coincides__Temporal() {
		return temporalEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getTemporal__Normalize() {
		return temporalEClass.getEOperations().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getTemporal__Minus__Temporal() {
		return temporalEClass.getEOperations().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getTemporal__Minus__Duration() {
		return temporalEClass.getEOperations().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getTemporal__Plus__Duration() {
		return temporalEClass.getEOperations().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getTemporal__Copy() {
		return temporalEClass.getEOperations().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPeriod() {
		return periodEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPeriod_Start() {
		return (EReference)periodEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPeriod_End() {
		return (EReference)periodEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPeriod_Duration() {
		return (EAttribute)periodEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getModelElement() {
		return modelElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getModelElement_Uri() {
		return (EAttribute)modelElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getModelElement_Description() {
		return (EAttribute)modelElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getModelElement_Uuid() {
		return (EAttribute)modelElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNamedElement() {
		return namedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNamedElement_Name() {
		return (EAttribute)namedElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getReference() {
		return referenceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReference_Target() {
		return (EReference)referenceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getString() {
		return stringEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getString_Value() {
		return (EAttribute)stringEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getList() {
		return listEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getList_Values() {
		return (EReference)listEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMap() {
		return mapEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getMap_Values() {
		return (EReference)mapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProperty() {
		return propertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getProperty_Name() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStringProperty() {
		return stringPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMapProperty() {
		return mapPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getListProperty() {
		return listPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getInstant() {
		return instantEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getDuration() {
		return durationEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NcoreFactory getNcoreFactory() {
		return (NcoreFactory)getEFactoryInstance();
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
		adaptableEClass = createEClass(ADAPTABLE);

		iMarkedEClass = createEClass(IMARKED);

		markedEClass = createEClass(MARKED);
		createEReference(markedEClass, MARKED__MARKER);

		iMarkerEClass = createEClass(IMARKER);

		markerEClass = createEClass(MARKER);
		createEAttribute(markerEClass, MARKER__LOCATION);
		createEAttribute(markerEClass, MARKER__LINE);
		createEAttribute(markerEClass, MARKER__COLUMN);

		temporalEClass = createEClass(TEMPORAL);
		createEAttribute(temporalEClass, TEMPORAL__INSTANT);
		createEReference(temporalEClass, TEMPORAL__BASE);
		createEAttribute(temporalEClass, TEMPORAL__OFFSET);
		createEReference(temporalEClass, TEMPORAL__DERIVATIVES);
		createEReference(temporalEClass, TEMPORAL__LOWER_BOUNDS);
		createEReference(temporalEClass, TEMPORAL__UPPER_BOUNDS);
		createEOperation(temporalEClass, TEMPORAL___AFTER__TEMPORAL);
		createEOperation(temporalEClass, TEMPORAL___BEFORE__TEMPORAL);
		createEOperation(temporalEClass, TEMPORAL___COINCIDES__TEMPORAL);
		createEOperation(temporalEClass, TEMPORAL___NORMALIZE);
		createEOperation(temporalEClass, TEMPORAL___MINUS__TEMPORAL);
		createEOperation(temporalEClass, TEMPORAL___MINUS__DURATION);
		createEOperation(temporalEClass, TEMPORAL___PLUS__DURATION);
		createEOperation(temporalEClass, TEMPORAL___COPY);

		periodEClass = createEClass(PERIOD);
		createEReference(periodEClass, PERIOD__START);
		createEReference(periodEClass, PERIOD__END);
		createEAttribute(periodEClass, PERIOD__DURATION);

		modelElementEClass = createEClass(MODEL_ELEMENT);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__URI);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__DESCRIPTION);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__UUID);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);

		referenceEClass = createEClass(REFERENCE);
		createEReference(referenceEClass, REFERENCE__TARGET);

		stringEClass = createEClass(STRING);
		createEAttribute(stringEClass, STRING__VALUE);

		listEClass = createEClass(LIST);
		createEReference(listEClass, LIST__VALUES);

		mapEClass = createEClass(MAP);
		createEReference(mapEClass, MAP__VALUES);

		propertyEClass = createEClass(PROPERTY);
		createEAttribute(propertyEClass, PROPERTY__NAME);

		stringPropertyEClass = createEClass(STRING_PROPERTY);

		mapPropertyEClass = createEClass(MAP_PROPERTY);

		listPropertyEClass = createEClass(LIST_PROPERTY);

		// Create data types
		instantEDataType = createEDataType(INSTANT);
		durationEDataType = createEDataType(DURATION);
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
		ETypeParameter referenceEClass_T = addETypeParameter(referenceEClass, "T");

		// Set bounds for type parameters

		// Add supertypes to classes
		markedEClass.getESuperTypes().add(this.getIMarked());
		markerEClass.getESuperTypes().add(this.getIMarker());
		temporalEClass.getESuperTypes().add(this.getModelElement());
		periodEClass.getESuperTypes().add(this.getModelElement());
		modelElementEClass.getESuperTypes().add(this.getMarked());
		modelElementEClass.getESuperTypes().add(this.getAdaptable());
		namedElementEClass.getESuperTypes().add(this.getModelElement());
		stringPropertyEClass.getESuperTypes().add(this.getProperty());
		stringPropertyEClass.getESuperTypes().add(this.getString());
		mapPropertyEClass.getESuperTypes().add(this.getProperty());
		mapPropertyEClass.getESuperTypes().add(this.getMap());
		listPropertyEClass.getESuperTypes().add(this.getProperty());
		listPropertyEClass.getESuperTypes().add(this.getList());

		// Initialize classes, features, and operations; add parameters
		initEClass(adaptableEClass, Adaptable.class, "Adaptable", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(iMarkedEClass, org.nasdanika.common.persistence.Marked.class, "IMarked", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(markedEClass, Marked.class, "Marked", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMarked_Marker(), this.getMarker(), null, "marker", null, 0, 1, Marked.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iMarkerEClass, org.nasdanika.common.persistence.Marker.class, "IMarker", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(markerEClass, Marker.class, "Marker", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMarker_Location(), ecorePackage.getEString(), "location", null, 0, 1, Marker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMarker_Line(), ecorePackage.getEInt(), "line", null, 0, 1, Marker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMarker_Column(), ecorePackage.getEInt(), "column", null, 0, 1, Marker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(temporalEClass, Temporal.class, "Temporal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTemporal_Instant(), this.getInstant(), "instant", null, 0, 1, Temporal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTemporal_Base(), this.getTemporal(), this.getTemporal_Derivatives(), "base", null, 0, 1, Temporal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTemporal_Offset(), this.getDuration(), "offset", null, 0, 1, Temporal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTemporal_Derivatives(), this.getTemporal(), this.getTemporal_Base(), "derivatives", null, 0, -1, Temporal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getTemporal_LowerBounds(), this.getTemporal(), null, "lowerBounds", null, 0, -1, Temporal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTemporal_UpperBounds(), this.getTemporal(), null, "upperBounds", null, 0, -1, Temporal.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = initEOperation(getTemporal__After__Temporal(), ecorePackage.getEBooleanObject(), "after", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getTemporal(), "when", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getTemporal__Before__Temporal(), ecorePackage.getEBooleanObject(), "before", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getTemporal(), "when", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getTemporal__Coincides__Temporal(), ecorePackage.getEBooleanObject(), "coincides", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getTemporal(), "when", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getTemporal__Normalize(), this.getTemporal(), "normalize", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getTemporal__Minus__Temporal(), this.getDuration(), "minus", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getTemporal(), "when", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getTemporal__Minus__Duration(), this.getTemporal(), "minus", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getDuration(), "offset", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getTemporal__Plus__Duration(), this.getTemporal(), "plus", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getDuration(), "offset", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getTemporal__Copy(), this.getTemporal(), "copy", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(periodEClass, Period.class, "Period", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPeriod_Start(), this.getTemporal(), null, "start", null, 0, 1, Period.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPeriod_End(), this.getTemporal(), null, "end", null, 0, 1, Period.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPeriod_Duration(), this.getDuration(), "duration", null, 0, 1, Period.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(modelElementEClass, ModelElement.class, "ModelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelElement_Uri(), ecorePackage.getEString(), "uri", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelElement_Uuid(), ecorePackage.getEString(), "uuid", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referenceEClass, Reference.class, "Reference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		EGenericType g1 = createEGenericType(referenceEClass_T);
		initEReference(getReference_Target(), g1, null, "target", null, 1, 1, Reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringEClass, org.nasdanika.ncore.String.class, "String", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getString_Value(), ecorePackage.getEString(), "value", null, 0, 1, org.nasdanika.ncore.String.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(listEClass, List.class, "List", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getList_Values(), ecorePackage.getEObject(), null, "values", null, 0, -1, List.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mapEClass, Map.class, "Map", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMap_Values(), this.getProperty(), null, "values", null, 0, -1, Map.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getMap_Values().getEKeys().add(this.getProperty_Name());

		initEClass(propertyEClass, Property.class, "Property", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProperty_Name(), ecorePackage.getEString(), "name", null, 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringPropertyEClass, StringProperty.class, "StringProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(mapPropertyEClass, MapProperty.class, "MapProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(listPropertyEClass, ListProperty.class, "ListProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Initialize data types
		initEDataType(instantEDataType, Instant.class, "Instant", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);
		initEDataType(durationEDataType, Duration.class, "Duration", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// urn:org.nasdanika
		createUrnorgAnnotations();
		// http://www.eclipse.org/emf/2002/GenModel
		createGenModelAnnotations();
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
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
		  (markedEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/marked.md"
		   });
		addAnnotation
		  (temporalEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/temporal.md"
		   });
		addAnnotation
		  (getTemporal_Instant(),
		   source,
		   new String[] {
			   "default-feature", "true",
			   "exclusive-with", "base"
		   });
		addAnnotation
		  (getTemporal_Base(),
		   source,
		   new String[] {
			   "exclusive-with", "instant"
		   });
		addAnnotation
		  (getTemporal_LowerBounds(),
		   source,
		   new String[] {
			   "homogenous", "true",
			   "strict-containment", "true"
		   });
		addAnnotation
		  (getTemporal_UpperBounds(),
		   source,
		   new String[] {
			   "homogenous", "true",
			   "strict-containment", "true"
		   });
		addAnnotation
		  (periodEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/period.md"
		   });
		addAnnotation
		  (getPeriod_Start(),
		   source,
		   new String[] {
			   "homogenous", "true",
			   "strict-containment", "true"
		   });
		addAnnotation
		  (getPeriod_End(),
		   source,
		   new String[] {
			   "homogenous", "true",
			   "strict-containment", "true"
		   });
		addAnnotation
		  (modelElementEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/model-element.md"
		   });
		addAnnotation
		  (namedElementEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/named-element.md"
		   });
		addAnnotation
		  (getReference_Target(),
		   source,
		   new String[] {
			   "default-feature", "true"
		   });
		addAnnotation
		  (getString_Value(),
		   source,
		   new String[] {
			   "default-feature", "true"
		   });
		addAnnotation
		  (getList_Values(),
		   source,
		   new String[] {
			   "reference-type", "map: Map\nlist: List\nstring: String",
			   "value-feature", "true"
		   });
		addAnnotation
		  (getMap_Values(),
		   source,
		   new String[] {
			   "reference-type", "map: MapProperty\nlist: ListProperty\nstring: StringProperty",
			   "value-feature", "true"
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
		  (instantEDataType,
		   source,
		   new String[] {
			   "documentation", "[Instant](https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html) is a single instantaneous point on the time-line. \nIf instant specification contains ``Z``, then it is parsed using [Instant.parse()](https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html#parse-java.lang.CharSequence-) method.\nOtherwise the specification is used to construct ``java.util.Date`` which is then converted to instant.\n\nExamples:\n\n* ``2021-12-03T10:15:30.00Z`` - loaded using ``Instant.parse()``\n* ``10/1/2021`` - loaded using ``new Date()`` and then converted to instant.\n\n"
		   });
		addAnnotation
		  (durationEDataType,
		   source,
		   new String[] {
			   "documentation", "[Duration](https://docs.oracle.com/javase/8/docs/api/java/time/Duration.html) is an amount of time in the [ISO-8601 Duration format](https://en.wikipedia.org/wiki/ISO_8601#Duration). Supports days and smaller units. Weeks, months and years are not supported. Example: ``P28DT10H``."
		   });
		addAnnotation
		  (getTemporal__After__Temporal(),
		   source,
		   new String[] {
			   "documentation", "Tests if this temporal is after the specified temporal. Returns null if unknown, e.g. two unrelated events."
		   });
		addAnnotation
		  (getTemporal__Before__Temporal(),
		   source,
		   new String[] {
			   "documentation", "Tests if this temporal is before the specified temporal. Returns null if unknown, e.g. two unrelated events."
		   });
		addAnnotation
		  (getTemporal__Coincides__Temporal(),
		   source,
		   new String[] {
			   "documentation", "Tests if this temporal occurs at the same point on the time-line as the specified temporal. Returns null if unknown, e.g. two unrelated events."
		   });
		addAnnotation
		  (getTemporal__Normalize(),
		   source,
		   new String[] {
			   "documentation", "Returns a normalized instance of this temporal not contained in the model. Normalization walks through the temporal chain to the root temporal. If that root temporal is instant/absolute then the normalized instance would be instant/absolute. Otherwise the normalized instance would contain the root temporal as its base and offset would be the sum of all offsets."
		   });
		addAnnotation
		  (getTemporal__Minus__Temporal(),
		   source,
		   new String[] {
			   "documentation", "Returns [duration](Duration.html) difference between this temporal and the argument temporal - how much this temporal is after the argument on the time-line, if difference can be computed, e.g. both this temporal and the argument temporal are instant or trace to the same base temporal. Returns null otherwise."
		   });
		addAnnotation
		  (getTemporal__Minus__Duration(),
		   source,
		   new String[] {
			   "documentation", "Returns a temporal based on this one offset by negation of the argument [duration](Duration.html). Null duration is treated as zero and set as-is (not negated)."
		   });
		addAnnotation
		  (getTemporal__Plus__Duration(),
		   source,
		   new String[] {
			   "documentation", "Returns a temporal based on this one offset by the argument [duration](Duration.html). Duration can be null."
		   });
		addAnnotation
		  (getTemporal__Copy(),
		   source,
		   new String[] {
			   "documentation", "Returns a deep copy of self with bounds copied. Other containment references are not set."
		   });
		addAnnotation
		  (getTemporal_Instant(),
		   source,
		   new String[] {
			   "documentation", "An absolute point on the time-line. E.g. ``2021/07/04``."
		   });
		addAnnotation
		  (getTemporal_Base(),
		   source,
		   new String[] {
			   "documentation", "Base of this temporal."
		   });
		addAnnotation
		  (getTemporal_Offset(),
		   source,
		   new String[] {
			   "documentation", "Time offset from the base in [ISO-8601 durations](https://en.wikipedia.org/wiki/ISO_8601#Durations) format. \n\nExamples:\n\n* ``P1H`` for one hour later.\n* ``-P20D`` or ``P-20D`` for 20 days before. Can be null (zero), e.g. if one [period](Period.html) starts right after another period ends."
		   });
		addAnnotation
		  (getTemporal_Derivatives(),
		   source,
		   new String[] {
			   "documentation", "Temporals which are based on this temporal."
		   });
		addAnnotation
		  (getTemporal_LowerBounds(),
		   source,
		   new String[] {
			   "documentation", "Lower bounds of a temporal. E.g. exact time of some temporal might not be known at a moment, but it might be known that it should not be before than some other temporal - a lower bound. Lower bounds are used in validations and before/after computations. "
		   });
		addAnnotation
		  (getTemporal_UpperBounds(),
		   source,
		   new String[] {
			   "documentation", "Upper bounds of a temporal. E.g. exact time of some temporal might not be known at a moment, but it might be known that it should not be after some other temporal - an upper bound. Upper bounds are used in validations and before/after computations. "
		   });
		addAnnotation
		  (getPeriod_Start(),
		   source,
		   new String[] {
			   "documentation", "Period start."
		   });
		addAnnotation
		  (getPeriod_End(),
		   source,
		   new String[] {
			   "documentation", "Period end."
		   });
		addAnnotation
		  (getPeriod_Duration(),
		   source,
		   new String[] {
			   "documentation", "Positive period duration in [ISO-8601 durations](https://en.wikipedia.org/wiki/ISO_8601#Durations) format. E.g. ``P1M`` for one month or ``P20D`` for 20 days."
		   });
		addAnnotation
		  (getModelElement_Uri(),
		   source,
		   new String[] {
			   "documentation", "If element\'s URI is not set then its default value is derived from the container URI and containment reference. \nThis is a logical URI and it can be used for cross-referencing of elements in a resource-independent fashion.\n"
		   });
		addAnnotation
		  (getModelElement_Description(),
		   source,
		   new String[] {
			   "documentation", "Description in HTML."
		   });
		addAnnotation
		  (getNamedElement_Name(),
		   source,
		   new String[] {
			   "documentation", "Element name."
		   });
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations() {
		String source = "http://www.eclipse.org/emf/2002/Ecore";
		addAnnotation
		  (temporalEClass,
		   source,
		   new String[] {
			   "constraints", "bounds offset"
		   });
		addAnnotation
		  (periodEClass,
		   source,
		   new String[] {
			   "constraints", "start_end"
		   });
	}

} //NcorePackageImpl
