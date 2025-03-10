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
import org.nasdanika.ncore.BooleanProperty;
import org.nasdanika.ncore.Composite;
import org.nasdanika.ncore.Date;
import org.nasdanika.ncore.DateProperty;
import org.nasdanika.ncore.Directory;
import org.nasdanika.ncore.Documented;
import org.nasdanika.ncore.DocumentedNamedElement;
import org.nasdanika.ncore.DocumentedNamedStringIdentity;
import org.nasdanika.ncore.DoubleProperty;
import org.nasdanika.ncore.EObjectProperty;
import org.nasdanika.ncore.File;
import org.nasdanika.ncore.GitMarker;
import org.nasdanika.ncore.IntegerProperty;
import org.nasdanika.ncore.List;
import org.nasdanika.ncore.ListProperty;
import org.nasdanika.ncore.LongProperty;
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
import org.nasdanika.ncore.StringIdentity;
import org.nasdanika.ncore.StringProperty;
import org.nasdanika.ncore.Temporal;
import org.nasdanika.ncore.Tree;
import org.nasdanika.ncore.TreeItem;
import org.nasdanika.ncore.TreeItemReference;
import org.nasdanika.ncore.ValueObject;
import org.nasdanika.ncore.ValueObjectProperty;
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
	private EClass representationEntryEClass = null;

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
	private EClass valueObjectEClass = null;

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
	private EClass integerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doubleEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass longEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanEClass = null;

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
	private EClass valueObjectPropertyEClass = null;

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
	private EClass integerPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass doublePropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass longPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass datePropertyEClass = null;

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
	private EClass booleanPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass eObjectPropertyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringEntryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass gitMarkerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentedNamedElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass compositeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringIdentityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentedNamedStringIdentityEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass treeItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass treeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass fileEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass directoryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass throwableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stackTraceElementEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass treeItemReferenceEClass = null;

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
	public EReference getMarked_Markers() {
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
	public EAttribute getMarker_Position() {
		return (EAttribute)markerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMarker_Comment() {
		return (EAttribute)markerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMarker_Date() {
		return (EAttribute)markerEClass.getEStructuralFeatures().get(3);
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
	public EAttribute getModelElement_Uris() {
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
	public EReference getModelElement_LabelPrototype() {
		return (EReference)modelElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getModelElement_Representations() {
		return (EReference)modelElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getModelElement_Annotations() {
		return (EReference)modelElementEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRepresentationEntry() {
		return representationEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRepresentationEntry_Key() {
		return (EAttribute)representationEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getRepresentationEntry_Value() {
		return (EAttribute)representationEntryEClass.getEStructuralFeatures().get(1);
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
	public EClass getValueObject() {
		return valueObjectEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getValueObject_Value() {
		return (EAttribute)valueObjectEClass.getEStructuralFeatures().get(0);
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
	public EClass getList() {
		return listEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getList_Value() {
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
	public EReference getMap_Value() {
		return (EReference)mapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInteger() {
		return integerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDouble() {
		return doubleEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDate() {
		return dateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLong() {
		return longEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBoolean() {
		return booleanEClass;
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
	public EClass getValueObjectProperty() {
		return valueObjectPropertyEClass;
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
	public EClass getIntegerProperty() {
		return integerPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDoubleProperty() {
		return doublePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLongProperty() {
		return longPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDateProperty() {
		return datePropertyEClass;
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
	public EClass getBooleanProperty() {
		return booleanPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEObjectProperty() {
		return eObjectPropertyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEObjectProperty_Value() {
		return (EReference)eObjectPropertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStringEntry() {
		return stringEntryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStringEntry_Key() {
		return (EAttribute)stringEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStringEntry_Value() {
		return (EAttribute)stringEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getGitMarker() {
		return gitMarkerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitMarker_Path() {
		return (EAttribute)gitMarkerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getGitMarker_Remotes() {
		return (EReference)gitMarkerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitMarker_Branch() {
		return (EAttribute)gitMarkerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitMarker_Head() {
		return (EAttribute)gitMarkerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getGitMarker_HeadRefs() {
		return (EAttribute)gitMarkerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDocumented() {
		return documentedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDocumented_Documentation() {
		return (EReference)documentedEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getDocumented_ContextHelp() {
		return (EReference)documentedEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDocumentedNamedElement() {
		return documentedNamedElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getComposite() {
		return compositeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getComposite_Id() {
		return (EAttribute)compositeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getComposite_Children() {
		return (EReference)compositeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStringIdentity() {
		return stringIdentityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStringIdentity_Id() {
		return (EAttribute)stringIdentityEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDocumentedNamedStringIdentity() {
		return documentedNamedStringIdentityEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTreeItem() {
		return treeItemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTreeItem_Name() {
		return (EAttribute)treeItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTree() {
		return treeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTree_TreeItems() {
		return (EReference)treeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFile() {
		return fileEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFile_Length() {
		return (EAttribute)fileEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getFile_LastModified() {
		return (EAttribute)fileEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDirectory() {
		return directoryEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getThrowable() {
		return throwableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getThrowable_Type() {
		return (EAttribute)throwableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getThrowable_Message() {
		return (EAttribute)throwableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getThrowable_StackTrace() {
		return (EReference)throwableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getThrowable_Supressed() {
		return (EReference)throwableEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getThrowable_Cause() {
		return (EReference)throwableEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStackTraceElement() {
		return stackTraceElementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStackTraceElement_ClassName() {
		return (EAttribute)stackTraceElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStackTraceElement_FileName() {
		return (EAttribute)stackTraceElementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStackTraceElement_MethodName() {
		return (EAttribute)stackTraceElementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStackTraceElement_LineNumber() {
		return (EAttribute)stackTraceElementEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStackTraceElement_Native() {
		return (EAttribute)stackTraceElementEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTreeItemReference() {
		return treeItemReferenceEClass;
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
		createEReference(markedEClass, MARKED__MARKERS);

		iMarkerEClass = createEClass(IMARKER);

		markerEClass = createEClass(MARKER);
		createEAttribute(markerEClass, MARKER__LOCATION);
		createEAttribute(markerEClass, MARKER__POSITION);
		createEAttribute(markerEClass, MARKER__COMMENT);
		createEAttribute(markerEClass, MARKER__DATE);

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
		createEAttribute(modelElementEClass, MODEL_ELEMENT__URIS);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__DESCRIPTION);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__UUID);
		createEReference(modelElementEClass, MODEL_ELEMENT__LABEL_PROTOTYPE);
		createEReference(modelElementEClass, MODEL_ELEMENT__REPRESENTATIONS);
		createEReference(modelElementEClass, MODEL_ELEMENT__ANNOTATIONS);

		representationEntryEClass = createEClass(REPRESENTATION_ENTRY);
		createEAttribute(representationEntryEClass, REPRESENTATION_ENTRY__KEY);
		createEAttribute(representationEntryEClass, REPRESENTATION_ENTRY__VALUE);

		namedElementEClass = createEClass(NAMED_ELEMENT);
		createEAttribute(namedElementEClass, NAMED_ELEMENT__NAME);

		referenceEClass = createEClass(REFERENCE);
		createEReference(referenceEClass, REFERENCE__TARGET);

		valueObjectEClass = createEClass(VALUE_OBJECT);
		createEAttribute(valueObjectEClass, VALUE_OBJECT__VALUE);

		stringEClass = createEClass(STRING);

		booleanEClass = createEClass(BOOLEAN);

		doubleEClass = createEClass(DOUBLE);

		dateEClass = createEClass(DATE);

		integerEClass = createEClass(INTEGER);

		listEClass = createEClass(LIST);
		createEReference(listEClass, LIST__VALUE);

		longEClass = createEClass(LONG);

		mapEClass = createEClass(MAP);
		createEReference(mapEClass, MAP__VALUE);

		propertyEClass = createEClass(PROPERTY);
		createEAttribute(propertyEClass, PROPERTY__NAME);

		valueObjectPropertyEClass = createEClass(VALUE_OBJECT_PROPERTY);

		booleanPropertyEClass = createEClass(BOOLEAN_PROPERTY);

		datePropertyEClass = createEClass(DATE_PROPERTY);

		doublePropertyEClass = createEClass(DOUBLE_PROPERTY);

		eObjectPropertyEClass = createEClass(EOBJECT_PROPERTY);
		createEReference(eObjectPropertyEClass, EOBJECT_PROPERTY__VALUE);

		integerPropertyEClass = createEClass(INTEGER_PROPERTY);

		listPropertyEClass = createEClass(LIST_PROPERTY);

		longPropertyEClass = createEClass(LONG_PROPERTY);

		mapPropertyEClass = createEClass(MAP_PROPERTY);

		stringPropertyEClass = createEClass(STRING_PROPERTY);

		stringEntryEClass = createEClass(STRING_ENTRY);
		createEAttribute(stringEntryEClass, STRING_ENTRY__KEY);
		createEAttribute(stringEntryEClass, STRING_ENTRY__VALUE);

		gitMarkerEClass = createEClass(GIT_MARKER);
		createEAttribute(gitMarkerEClass, GIT_MARKER__PATH);
		createEReference(gitMarkerEClass, GIT_MARKER__REMOTES);
		createEAttribute(gitMarkerEClass, GIT_MARKER__BRANCH);
		createEAttribute(gitMarkerEClass, GIT_MARKER__HEAD);
		createEAttribute(gitMarkerEClass, GIT_MARKER__HEAD_REFS);

		documentedEClass = createEClass(DOCUMENTED);
		createEReference(documentedEClass, DOCUMENTED__DOCUMENTATION);
		createEReference(documentedEClass, DOCUMENTED__CONTEXT_HELP);

		documentedNamedElementEClass = createEClass(DOCUMENTED_NAMED_ELEMENT);

		compositeEClass = createEClass(COMPOSITE);
		createEAttribute(compositeEClass, COMPOSITE__ID);
		createEReference(compositeEClass, COMPOSITE__CHILDREN);

		stringIdentityEClass = createEClass(STRING_IDENTITY);
		createEAttribute(stringIdentityEClass, STRING_IDENTITY__ID);

		documentedNamedStringIdentityEClass = createEClass(DOCUMENTED_NAMED_STRING_IDENTITY);

		treeItemEClass = createEClass(TREE_ITEM);
		createEAttribute(treeItemEClass, TREE_ITEM__NAME);

		treeEClass = createEClass(TREE);
		createEReference(treeEClass, TREE__TREE_ITEMS);

		fileEClass = createEClass(FILE);
		createEAttribute(fileEClass, FILE__LENGTH);
		createEAttribute(fileEClass, FILE__LAST_MODIFIED);

		directoryEClass = createEClass(DIRECTORY);

		throwableEClass = createEClass(THROWABLE);
		createEAttribute(throwableEClass, THROWABLE__TYPE);
		createEAttribute(throwableEClass, THROWABLE__MESSAGE);
		createEReference(throwableEClass, THROWABLE__STACK_TRACE);
		createEReference(throwableEClass, THROWABLE__SUPRESSED);
		createEReference(throwableEClass, THROWABLE__CAUSE);

		stackTraceElementEClass = createEClass(STACK_TRACE_ELEMENT);
		createEAttribute(stackTraceElementEClass, STACK_TRACE_ELEMENT__CLASS_NAME);
		createEAttribute(stackTraceElementEClass, STACK_TRACE_ELEMENT__FILE_NAME);
		createEAttribute(stackTraceElementEClass, STACK_TRACE_ELEMENT__METHOD_NAME);
		createEAttribute(stackTraceElementEClass, STACK_TRACE_ELEMENT__LINE_NUMBER);
		createEAttribute(stackTraceElementEClass, STACK_TRACE_ELEMENT__NATIVE);

		treeItemReferenceEClass = createEClass(TREE_ITEM_REFERENCE);

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
		ETypeParameter valueObjectEClass_T = addETypeParameter(valueObjectEClass, "T");
		ETypeParameter valueObjectPropertyEClass_T = addETypeParameter(valueObjectPropertyEClass, "T");

		// Set bounds for type parameters

		// Add supertypes to classes
		markedEClass.getESuperTypes().add(this.getIMarked());
		markerEClass.getESuperTypes().add(this.getIMarker());
		temporalEClass.getESuperTypes().add(this.getModelElement());
		periodEClass.getESuperTypes().add(this.getModelElement());
		modelElementEClass.getESuperTypes().add(this.getMarked());
		modelElementEClass.getESuperTypes().add(this.getAdaptable());
		namedElementEClass.getESuperTypes().add(this.getModelElement());
		valueObjectEClass.getESuperTypes().add(this.getMarked());
		EGenericType g1 = createEGenericType(this.getValueObject());
		EGenericType g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		stringEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getValueObject());
		g2 = createEGenericType(ecorePackage.getEBooleanObject());
		g1.getETypeArguments().add(g2);
		booleanEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getValueObject());
		g2 = createEGenericType(ecorePackage.getEDoubleObject());
		g1.getETypeArguments().add(g2);
		doubleEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getValueObject());
		g2 = createEGenericType(ecorePackage.getEDate());
		g1.getETypeArguments().add(g2);
		dateEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getValueObject());
		g2 = createEGenericType(ecorePackage.getEIntegerObject());
		g1.getETypeArguments().add(g2);
		integerEClass.getEGenericSuperTypes().add(g1);
		listEClass.getESuperTypes().add(this.getMarked());
		g1 = createEGenericType(this.getValueObject());
		g2 = createEGenericType(ecorePackage.getELongObject());
		g1.getETypeArguments().add(g2);
		longEClass.getEGenericSuperTypes().add(g1);
		mapEClass.getESuperTypes().add(this.getMarked());
		propertyEClass.getESuperTypes().add(this.getMarked());
		g1 = createEGenericType(this.getValueObject());
		g2 = createEGenericType(valueObjectPropertyEClass_T);
		g1.getETypeArguments().add(g2);
		valueObjectPropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getProperty());
		valueObjectPropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getBoolean());
		booleanPropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getValueObjectProperty());
		g2 = createEGenericType(ecorePackage.getEBooleanObject());
		g1.getETypeArguments().add(g2);
		booleanPropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getDate());
		datePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getValueObjectProperty());
		g2 = createEGenericType(ecorePackage.getEDate());
		g1.getETypeArguments().add(g2);
		datePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getDouble());
		doublePropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getValueObjectProperty());
		g2 = createEGenericType(ecorePackage.getEDoubleObject());
		g1.getETypeArguments().add(g2);
		doublePropertyEClass.getEGenericSuperTypes().add(g1);
		eObjectPropertyEClass.getESuperTypes().add(this.getProperty());
		g1 = createEGenericType(this.getInteger());
		integerPropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getValueObjectProperty());
		g2 = createEGenericType(ecorePackage.getEIntegerObject());
		g1.getETypeArguments().add(g2);
		integerPropertyEClass.getEGenericSuperTypes().add(g1);
		listPropertyEClass.getESuperTypes().add(this.getProperty());
		listPropertyEClass.getESuperTypes().add(this.getList());
		g1 = createEGenericType(this.getLong());
		longPropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getValueObjectProperty());
		g2 = createEGenericType(ecorePackage.getELongObject());
		g1.getETypeArguments().add(g2);
		longPropertyEClass.getEGenericSuperTypes().add(g1);
		mapPropertyEClass.getESuperTypes().add(this.getProperty());
		mapPropertyEClass.getESuperTypes().add(this.getMap());
		g1 = createEGenericType(this.getString());
		stringPropertyEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getValueObjectProperty());
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		stringPropertyEClass.getEGenericSuperTypes().add(g1);
		gitMarkerEClass.getESuperTypes().add(this.getMarker());
		documentedNamedElementEClass.getESuperTypes().add(this.getNamedElement());
		documentedNamedElementEClass.getESuperTypes().add(this.getDocumented());
		compositeEClass.getESuperTypes().add(this.getDocumentedNamedElement());
		documentedNamedStringIdentityEClass.getESuperTypes().add(this.getDocumentedNamedElement());
		documentedNamedStringIdentityEClass.getESuperTypes().add(this.getStringIdentity());
		treeEClass.getESuperTypes().add(this.getTreeItem());
		fileEClass.getESuperTypes().add(this.getTreeItem());
		directoryEClass.getESuperTypes().add(this.getFile());
		directoryEClass.getESuperTypes().add(this.getTree());
		g1 = createEGenericType(this.getTreeItem());
		treeItemReferenceEClass.getEGenericSuperTypes().add(g1);
		g1 = createEGenericType(this.getReference());
		g2 = createEGenericType(this.getTreeItem());
		g1.getETypeArguments().add(g2);
		treeItemReferenceEClass.getEGenericSuperTypes().add(g1);

		// Initialize classes, features, and operations; add parameters
		initEClass(adaptableEClass, Adaptable.class, "Adaptable", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(iMarkedEClass, org.nasdanika.persistence.Marked.class, "IMarked", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(markedEClass, Marked.class, "Marked", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMarked_Markers(), this.getMarker(), null, "markers", null, 0, -1, Marked.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iMarkerEClass, org.nasdanika.persistence.Marker.class, "IMarker", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);

		initEClass(markerEClass, Marker.class, "Marker", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMarker_Location(), ecorePackage.getEString(), "location", null, 0, 1, Marker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMarker_Position(), ecorePackage.getEString(), "position", null, 0, 1, Marker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMarker_Comment(), ecorePackage.getEString(), "comment", null, 0, 1, Marker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMarker_Date(), ecorePackage.getEDate(), "date", null, 0, 1, Marker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		initEAttribute(getModelElement_Uris(), ecorePackage.getEString(), "uris", null, 0, -1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getModelElement_Uuid(), ecorePackage.getEString(), "uuid", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelElement_LabelPrototype(), ecorePackage.getEObject(), null, "labelPrototype", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelElement_Representations(), this.getRepresentationEntry(), null, "representations", null, 0, -1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getModelElement_Annotations(), this.getProperty(), null, "annotations", null, 0, -1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getModelElement_Annotations().getEKeys().add(this.getProperty_Name());

		initEClass(representationEntryEClass, java.util.Map.Entry.class, "RepresentationEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRepresentationEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, java.util.Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepresentationEntry_Value(), ecorePackage.getEString(), "value", null, 0, 1, java.util.Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(namedElementEClass, NamedElement.class, "NamedElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getNamedElement_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(referenceEClass, Reference.class, "Reference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(referenceEClass_T);
		initEReference(getReference_Target(), g1, null, "target", null, 1, 1, Reference.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(valueObjectEClass, ValueObject.class, "ValueObject", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		g1 = createEGenericType(valueObjectEClass_T);
		initEAttribute(getValueObject_Value(), g1, "value", null, 0, 1, ValueObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stringEClass, org.nasdanika.ncore.String.class, "String", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(booleanEClass, org.nasdanika.ncore.Boolean.class, "Boolean", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(doubleEClass, org.nasdanika.ncore.Double.class, "Double", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(dateEClass, Date.class, "Date", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(integerEClass, org.nasdanika.ncore.Integer.class, "Integer", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(listEClass, List.class, "List", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getList_Value(), ecorePackage.getEObject(), null, "value", null, 0, -1, List.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(longEClass, org.nasdanika.ncore.Long.class, "Long", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(mapEClass, Map.class, "Map", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMap_Value(), this.getProperty(), null, "value", null, 0, -1, Map.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getMap_Value().getEKeys().add(this.getProperty_Name());

		initEClass(propertyEClass, Property.class, "Property", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProperty_Name(), ecorePackage.getEString(), "name", null, 1, 1, Property.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(valueObjectPropertyEClass, ValueObjectProperty.class, "ValueObjectProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(booleanPropertyEClass, BooleanProperty.class, "BooleanProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(datePropertyEClass, DateProperty.class, "DateProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(doublePropertyEClass, DoubleProperty.class, "DoubleProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(eObjectPropertyEClass, EObjectProperty.class, "EObjectProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEObjectProperty_Value(), ecorePackage.getEObject(), null, "value", null, 1, 1, EObjectProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(integerPropertyEClass, IntegerProperty.class, "IntegerProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(listPropertyEClass, ListProperty.class, "ListProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(longPropertyEClass, LongProperty.class, "LongProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(mapPropertyEClass, MapProperty.class, "MapProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stringPropertyEClass, StringProperty.class, "StringProperty", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(stringEntryEClass, java.util.Map.Entry.class, "StringEntry", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringEntry_Key(), ecorePackage.getEString(), "key", null, 1, 1, java.util.Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStringEntry_Value(), ecorePackage.getEString(), "value", null, 0, 1, java.util.Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(gitMarkerEClass, GitMarker.class, "GitMarker", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGitMarker_Path(), ecorePackage.getEString(), "path", null, 0, 1, GitMarker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGitMarker_Remotes(), this.getStringEntry(), null, "remotes", null, 0, -1, GitMarker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitMarker_Branch(), ecorePackage.getEString(), "branch", null, 0, 1, GitMarker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitMarker_Head(), ecorePackage.getEString(), "head", null, 0, 1, GitMarker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGitMarker_HeadRefs(), ecorePackage.getEString(), "headRefs", null, 0, -1, GitMarker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentedEClass, Documented.class, "Documented", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDocumented_Documentation(), ecorePackage.getEObject(), null, "documentation", null, 0, -1, Documented.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumented_ContextHelp(), ecorePackage.getEObject(), null, "contextHelp", null, 0, -1, Documented.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentedNamedElementEClass, DocumentedNamedElement.class, "DocumentedNamedElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(compositeEClass, Composite.class, "Composite", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComposite_Id(), ecorePackage.getEString(), "id", null, 0, 1, Composite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComposite_Children(), this.getComposite(), null, "children", null, 0, -1, Composite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getComposite_Children().getEKeys().add(this.getComposite_Id());

		initEClass(stringIdentityEClass, StringIdentity.class, "StringIdentity", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStringIdentity_Id(), ecorePackage.getEString(), "id", null, 1, 1, StringIdentity.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentedNamedStringIdentityEClass, DocumentedNamedStringIdentity.class, "DocumentedNamedStringIdentity", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(treeItemEClass, TreeItem.class, "TreeItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTreeItem_Name(), ecorePackage.getEString(), "name", null, 0, 1, TreeItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(treeEClass, Tree.class, "Tree", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getTree_TreeItems(), this.getTreeItem(), null, "treeItems", null, 0, -1, Tree.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getTree_TreeItems().getEKeys().add(this.getTreeItem_Name());

		initEClass(fileEClass, File.class, "File", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFile_Length(), ecorePackage.getELong(), "length", null, 0, 1, File.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getFile_LastModified(), ecorePackage.getEDate(), "lastModified", null, 0, 1, File.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(directoryEClass, Directory.class, "Directory", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(throwableEClass, org.nasdanika.ncore.Throwable.class, "Throwable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getThrowable_Type(), ecorePackage.getEString(), "type", null, 0, 1, org.nasdanika.ncore.Throwable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getThrowable_Message(), ecorePackage.getEString(), "message", null, 0, 1, org.nasdanika.ncore.Throwable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getThrowable_StackTrace(), this.getStackTraceElement(), null, "stackTrace", null, 0, -1, org.nasdanika.ncore.Throwable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getThrowable_Supressed(), this.getThrowable(), null, "supressed", null, 0, -1, org.nasdanika.ncore.Throwable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getThrowable_Cause(), this.getThrowable(), null, "cause", null, 0, 1, org.nasdanika.ncore.Throwable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stackTraceElementEClass, org.nasdanika.ncore.StackTraceElement.class, "StackTraceElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStackTraceElement_ClassName(), ecorePackage.getEString(), "className", null, 0, 1, org.nasdanika.ncore.StackTraceElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStackTraceElement_FileName(), ecorePackage.getEString(), "fileName", null, 0, 1, org.nasdanika.ncore.StackTraceElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStackTraceElement_MethodName(), ecorePackage.getEString(), "methodName", null, 0, 1, org.nasdanika.ncore.StackTraceElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStackTraceElement_LineNumber(), ecorePackage.getEInt(), "lineNumber", null, 0, 1, org.nasdanika.ncore.StackTraceElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getStackTraceElement_Native(), ecorePackage.getEBoolean(), "native", null, 0, 1, org.nasdanika.ncore.StackTraceElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(treeItemReferenceEClass, TreeItemReference.class, "TreeItemReference", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

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
		  (getMarked_Markers(),
		   source,
		   new String[] {
			   "loadable", "false"
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
			   "homogeneous", "true",
			   "strict-containment", "true"
		   });
		addAnnotation
		  (getTemporal_UpperBounds(),
		   source,
		   new String[] {
			   "homogeneous", "true",
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
			   "homogeneous", "true",
			   "strict-containment", "true"
		   });
		addAnnotation
		  (getPeriod_End(),
		   source,
		   new String[] {
			   "homogeneous", "true",
			   "strict-containment", "true"
		   });
		addAnnotation
		  (getModelElement_Representations(),
		   source,
		   new String[] {
			   "homogeneous", "true",
			   "load-doc-reference", "doc/model-element--representations.md"
		   });
		addAnnotation
		  (getModelElement_Annotations(),
		   source,
		   new String[] {
			   "reference-type", "map: MapProperty\nlist: ListProperty\nstring: StringProperty\ninteger: IntegerProperty",
			   "value-feature", "value"
		   });
		addAnnotation
		  (getRepresentationEntry_Value(),
		   source,
		   new String[] {
			   "content-type", "resource-uri"
		   });
		addAnnotation
		  (getReference_Target(),
		   source,
		   new String[] {
			   "default-feature", "true"
		   });
		addAnnotation
		  (getValueObject_Value(),
		   source,
		   new String[] {
			   "default-feature", "true"
		   });
		addAnnotation
		  (getList_Value(),
		   source,
		   new String[] {
			   "reference-type", "map: Map\nlist: List\nstring: String"
		   });
		addAnnotation
		  (getMap_Value(),
		   source,
		   new String[] {
			   "reference-type", "map: MapProperty\nlist: ListProperty\nstring: StringProperty\ninteger: IntegerProperty",
			   "value-feature", "value"
		   });
		addAnnotation
		  (getEObjectProperty_Value(),
		   source,
		   new String[] {
			   "default-feature", "true"
		   });
		addAnnotation
		  (getComposite_Id(),
		   source,
		   new String[] {
			   "default-feature", "true"
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
		  (getMarked_Markers(),
		   source,
		   new String[] {
			   "documentation", "Markers pointing to where this model element was loaded from - location, line and column numbers. \nMultiple markers are supported for situations when a single model element is loaded from multiple locations, e.g. a row in an Excel document or a database and then pom.xml and readme.md is a source repository.\nAnother possiblity is an element being created from a prototype and then loaded - in this case the object will inherit markers from its prototype and will have a marker point to the location where it was loaded from.\nMarkers are listed in the reverse order, i.e. the prototype marker would be after the load marker in the list."
		   });
		addAnnotation
		  (markerEClass,
		   source,
		   new String[] {
			   "documentation", "Provides information about location for a model element definition - URI, line and column numbers."
		   });
		addAnnotation
		  (getMarker_Location(),
		   source,
		   new String[] {
			   "documentation", "URI of a resource."
		   });
		addAnnotation
		  (getMarker_Position(),
		   source,
		   new String[] {
			   "documentation", "Marker position withing the resource identified by location - line and column in text documents, sheet and range in Excel, ..."
		   });
		addAnnotation
		  (getMarker_Comment(),
		   source,
		   new String[] {
			   "documentation", "Optional marker comment providing additional information about the marker. E.g. for locations which do not support lines and colums it may provide position inside the resource identified by the location attribute, such as an XPath for XML documents or sheet name and a range for Excel documents."
		   });
		addAnnotation
		  (getMarker_Date(),
		   source,
		   new String[] {
			   "documentation", "When this marker was created, source data timestamp "
		   });
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
			   "documentation", "Tests if this temporal is after the specified temporal. Returns null if unknown, i.e. two unrelated points in time."
		   });
		addAnnotation
		  (getTemporal__Before__Temporal(),
		   source,
		   new String[] {
			   "documentation", "Tests if this temporal is before the specified temporal. Returns null if unknown, i.e. two unrelated points in time."
		   });
		addAnnotation
		  (getTemporal__Coincides__Temporal(),
		   source,
		   new String[] {
			   "documentation", "Tests if this temporal occurs at the same point on the time-line as the specified temporal. Returns null if unknown, i.e. two unrelated points in time."
		   });
		addAnnotation
		  (getTemporal__Normalize(),
		   source,
		   new String[] {
			   "documentation", "Returns a normalized instance of this temporal not contained in the model. Normalization walks through the temporal chain from a temporal to its base to the root temporal. If that root temporal is instant/absolute then the normalized instance would be instant/absolute. Otherwise the normalized instance would contain the root temporal as its base and the offset would be the sum of all offsets."
		   });
		addAnnotation
		  (getTemporal__Minus__Temporal(),
		   source,
		   new String[] {
			   "documentation", "Returns [duration](Duration.html) difference between this temporal and the argument temporal - how much this temporal is after the argument on the time-line, if difference can be computed, i.e. both this temporal and the argument temporal are instant or trace to the same base temporal. \nReturns null otherwise."
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
			   "documentation", "Returns a temporal based on this one offset by the argument [duration](Duration.html). Duration can be null - in this case it is treated as zero."
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
		  (modelElementEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for many Nasdanika model classes."
		   });
		addAnnotation
		  (getModelElement_Uris(),
		   source,
		   new String[] {
			   "documentation", "Element URI\'s - there might be more than one. See ``NcoreUtil.getUris()`` for more details.\nURI\'s can be used for cross-referencing of elements in a resource-independent fashion.\n"
		   });
		addAnnotation
		  (getModelElement_Description(),
		   source,
		   new String[] {
			   "documentation", "Description in HTML."
		   });
		addAnnotation
		  (getModelElement_Uuid(),
		   source,
		   new String[] {
			   "documentation", "Optional unique identifier for this model element. For root objects UUID is used to compute URI, if the URI is not set."
		   });
		addAnnotation
		  (getModelElement_LabelPrototype(),
		   source,
		   new String[] {
			   "documentation", "If this reference is not set then EObjectLabelBuilder creates a new Label of its subclass (Link or Action) using AppFactory in newLabel() method. \nIf this reference is set and is and instanceof Label then a copy of the label is created and returned.\nOtherwise the reference value it is adapted to LabelProvider which is used to create a label. \nThis allows to merge labels and chain label generation. \n"
		   });
		addAnnotation
		  (getModelElement_Representations(),
		   source,
		   new String[] {
			   "documentation", "Mapping of representation keys to URI\'s of representation resources. URI\'s are resolved relative to the model element resource URI.\nDuring object loading resources are loaded and linked to the object. Additional processing depends on the resource type.\nIn case of Drawio diagrams, diagram elements are semantically mapped to model elements and representation resource root elements are added as children to the object.\nSee [Nasdanika Core Drawio](../drawio/index.html) and [Nasdanika Core EMF](../emf/index.html) documentation for more information.\n\n"
		   });
		addAnnotation
		  (getModelElement_Annotations(),
		   source,
		   new String[] {
			   "documentation", "Annotations are used to store custom data similar to Java and Ecore annotations. "
		   });
		addAnnotation
		  (representationEntryEClass,
		   source,
		   new String[] {
			   "documentation", "Mapping of representation key to a representation resource uri. Null values are supported to suppress inherited entries."
		   });
		addAnnotation
		  (getRepresentationEntry_Key(),
		   source,
		   new String[] {
			   "documentation", "Representation key."
		   });
		addAnnotation
		  (getRepresentationEntry_Value(),
		   source,
		   new String[] {
			   "documentation", "Representation resource URI. E.g. and URI of a Drawio diagram. "
		   });
		addAnnotation
		  (namedElementEClass,
		   source,
		   new String[] {
			   "documentation", "A model element with name."
		   });
		addAnnotation
		  (getNamedElement_Name(),
		   source,
		   new String[] {
			   "documentation", "Element name."
		   });
		addAnnotation
		  (referenceEClass,
		   source,
		   new String[] {
			   "documentation", "Object reference. May be used in containment collections to point to an object contained elsewhere."
		   });
		addAnnotation
		  (getReference_Target(),
		   source,
		   new String[] {
			   "documentation", "Reference target."
		   });
		addAnnotation
		  (getValueObject_Value(),
		   source,
		   new String[] {
			   "documentation", "String value."
		   });
		addAnnotation
		  (stringEClass,
		   source,
		   new String[] {
			   "documentation", "Text/string."
		   });
		addAnnotation
		  (booleanEClass,
		   source,
		   new String[] {
			   "documentation", "Represents boolean."
		   });
		addAnnotation
		  (doubleEClass,
		   source,
		   new String[] {
			   "documentation", "Represents integer number."
		   });
		addAnnotation
		  (dateEClass,
		   source,
		   new String[] {
			   "documentation", "Represents integer number."
		   });
		addAnnotation
		  (integerEClass,
		   source,
		   new String[] {
			   "documentation", "Represents integer number."
		   });
		addAnnotation
		  (listEClass,
		   source,
		   new String[] {
			   "documentation", "A list of objects."
		   });
		addAnnotation
		  (getList_Value(),
		   source,
		   new String[] {
			   "documentation", "List elements."
		   });
		addAnnotation
		  (longEClass,
		   source,
		   new String[] {
			   "documentation", "Represents integer number."
		   });
		addAnnotation
		  (mapEClass,
		   source,
		   new String[] {
			   "documentation", "A set of key-value pairs."
		   });
		addAnnotation
		  (getMap_Value(),
		   source,
		   new String[] {
			   "documentation", "Map entries"
		   });
		addAnnotation
		  (propertyEClass,
		   source,
		   new String[] {
			   "documentation", "Base class for keyed values."
		   });
		addAnnotation
		  (getProperty_Name(),
		   source,
		   new String[] {
			   "documentation", "Property name/key."
		   });
		addAnnotation
		  (booleanPropertyEClass,
		   source,
		   new String[] {
			   "documentation", "A named/keyed boolean."
		   });
		addAnnotation
		  (datePropertyEClass,
		   source,
		   new String[] {
			   "documentation", "A named/keyed integer."
		   });
		addAnnotation
		  (doublePropertyEClass,
		   source,
		   new String[] {
			   "documentation", "A named/keyed integer."
		   });
		addAnnotation
		  (eObjectPropertyEClass,
		   source,
		   new String[] {
			   "documentation", "A named/keyed object."
		   });
		addAnnotation
		  (getEObjectProperty_Value(),
		   source,
		   new String[] {
			   "documentation", "Property value."
		   });
		addAnnotation
		  (integerPropertyEClass,
		   source,
		   new String[] {
			   "documentation", "A named/keyed integer."
		   });
		addAnnotation
		  (listPropertyEClass,
		   source,
		   new String[] {
			   "documentation", "A named/keyed list."
		   });
		addAnnotation
		  (longPropertyEClass,
		   source,
		   new String[] {
			   "documentation", "A named/keyed integer."
		   });
		addAnnotation
		  (mapPropertyEClass,
		   source,
		   new String[] {
			   "documentation", "A named/keyed map."
		   });
		addAnnotation
		  (stringPropertyEClass,
		   source,
		   new String[] {
			   "documentation", "A named/keyed string."
		   });
		addAnnotation
		  (stringEntryEClass,
		   source,
		   new String[] {
			   "documentation", "EMap entry with String value"
		   });
		addAnnotation
		  (getStringEntry_Key(),
		   source,
		   new String[] {
			   "documentation", "Entry key."
		   });
		addAnnotation
		  (getStringEntry_Value(),
		   source,
		   new String[] {
			   "documentation", "Entry value."
		   });
		addAnnotation
		  (gitMarkerEClass,
		   source,
		   new String[] {
			   "documentation", "Marker with Git information such as a map of remotes."
		   });
		addAnnotation
		  (getGitMarker_Path(),
		   source,
		   new String[] {
			   "documentation", "Path in the repository"
		   });
		addAnnotation
		  (getGitMarker_Remotes(),
		   source,
		   new String[] {
			   "documentation", "A map of remote names to url\'s."
		   });
		addAnnotation
		  (getGitMarker_Branch(),
		   source,
		   new String[] {
			   "documentation", "Short branch name"
		   });
		addAnnotation
		  (getGitMarker_Head(),
		   source,
		   new String[] {
			   "documentation", "Commit ID of the head"
		   });
		addAnnotation
		  (getGitMarker_HeadRefs(),
		   source,
		   new String[] {
			   "documentation", "Refs on the head commit excluding the head itself. E.g. branch, tags."
		   });
		addAnnotation
		  (documentedEClass,
		   source,
		   new String[] {
			   "documentation", "Mix-in interface for classess with documentation."
		   });
		addAnnotation
		  (getDocumented_Documentation(),
		   source,
		   new String[] {
			   "documentation", "Element documentation."
		   });
		addAnnotation
		  (getDocumented_ContextHelp(),
		   source,
		   new String[] {
			   "documentation", "Context help. It may be different from documentation."
		   });
		addAnnotation
		  (compositeEClass,
		   source,
		   new String[] {
			   "documentation", "A simple class for building hierarchies of documented entities. "
		   });
		addAnnotation
		  (getComposite_Id(),
		   source,
		   new String[] {
			   "documentation", "A unique identifier of this composite among its siblings."
		   });
		addAnnotation
		  (getComposite_Children(),
		   source,
		   new String[] {
			   "documentation", "Composite\'s children."
		   });
		addAnnotation
		  (stringIdentityEClass,
		   source,
		   new String[] {
			   "documentation", "Base for classes which are unquely identified by a string in their containment reference"
		   });
		addAnnotation
		  (getStringIdentity_Id(),
		   source,
		   new String[] {
			   "documentation", "Element ID used as eKey in containment references"
		   });
		addAnnotation
		  (throwableEClass,
		   source,
		   new String[] {
			   "documentation", "Models java.lang.Throwable."
		   });
		addAnnotation
		  (getThrowable_Type(),
		   source,
		   new String[] {
			   "documentation", "Throwable class name."
		   });
		addAnnotation
		  (getThrowable_Message(),
		   source,
		   new String[] {
			   "documentation", "Error message."
		   });
		addAnnotation
		  (getThrowable_StackTrace(),
		   source,
		   new String[] {
			   "documentation", "Stack trace."
		   });
		addAnnotation
		  (getThrowable_Supressed(),
		   source,
		   new String[] {
			   "documentation", "Stack trace."
		   });
		addAnnotation
		  (getThrowable_Cause(),
		   source,
		   new String[] {
			   "documentation", "Stack trace."
		   });
		addAnnotation
		  (stackTraceElementEClass,
		   source,
		   new String[] {
			   "documentation", ""
		   });
		addAnnotation
		  (getStackTraceElement_ClassName(),
		   source,
		   new String[] {
			   "documentation", "Class name."
		   });
		addAnnotation
		  (getStackTraceElement_FileName(),
		   source,
		   new String[] {
			   "documentation", "File name."
		   });
		addAnnotation
		  (getStackTraceElement_MethodName(),
		   source,
		   new String[] {
			   "documentation", "Method name."
		   });
		addAnnotation
		  (getStackTraceElement_LineNumber(),
		   source,
		   new String[] {
			   "documentation", "Line number."
		   });
		addAnnotation
		  (getStackTraceElement_Native(),
		   source,
		   new String[] {
			   "documentation", "\'true\' for native methods."
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
