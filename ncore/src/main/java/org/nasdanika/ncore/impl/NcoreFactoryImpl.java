/**
 */
package org.nasdanika.ncore.impl;

import java.time.Duration;
import java.time.Instant;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.ncore.BooleanProperty;
import org.nasdanika.ncore.Composite;
import org.nasdanika.ncore.Date;
import org.nasdanika.ncore.DateProperty;
import org.nasdanika.ncore.DocumentedNamedElement;
import org.nasdanika.ncore.DoubleProperty;
import org.nasdanika.ncore.EObjectProperty;
import org.nasdanika.ncore.GitMarker;
import org.nasdanika.ncore.IntegerProperty;
import org.nasdanika.ncore.List;
import org.nasdanika.ncore.ListProperty;
import org.nasdanika.ncore.LongProperty;
import org.nasdanika.ncore.Map;
import org.nasdanika.ncore.MapProperty;
import org.nasdanika.ncore.Marker;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcoreFactory;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Period;
import org.nasdanika.ncore.Reference;
import org.nasdanika.ncore.StringProperty;
import org.nasdanika.ncore.Temporal;
import org.nasdanika.ncore.ValueObjectProperty;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class NcoreFactoryImpl extends EFactoryImpl implements NcoreFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NcoreFactory init() {
		try {
			NcoreFactory theNcoreFactory = (NcoreFactory)EPackage.Registry.INSTANCE.getEFactory(NcorePackage.eNS_URI);
			if (theNcoreFactory != null) {
				return theNcoreFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new NcoreFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NcoreFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case NcorePackage.MARKER: return createMarker();
			case NcorePackage.TEMPORAL: return createTemporal();
			case NcorePackage.PERIOD: return createPeriod();
			case NcorePackage.REPRESENTATION_ENTRY: return (EObject)createRepresentationEntry();
			case NcorePackage.NAMED_ELEMENT: return createNamedElement();
			case NcorePackage.REFERENCE: return createReference();
			case NcorePackage.STRING: return createString();
			case NcorePackage.BOOLEAN: return createBoolean();
			case NcorePackage.DOUBLE: return createDouble();
			case NcorePackage.DATE: return createDate();
			case NcorePackage.INTEGER: return createInteger();
			case NcorePackage.LIST: return createList();
			case NcorePackage.LONG: return createLong();
			case NcorePackage.MAP: return createMap();
			case NcorePackage.VALUE_OBJECT_PROPERTY: return createValueObjectProperty();
			case NcorePackage.BOOLEAN_PROPERTY: return createBooleanProperty();
			case NcorePackage.DATE_PROPERTY: return createDateProperty();
			case NcorePackage.DOUBLE_PROPERTY: return createDoubleProperty();
			case NcorePackage.EOBJECT_PROPERTY: return createEObjectProperty();
			case NcorePackage.INTEGER_PROPERTY: return createIntegerProperty();
			case NcorePackage.LIST_PROPERTY: return createListProperty();
			case NcorePackage.LONG_PROPERTY: return createLongProperty();
			case NcorePackage.MAP_PROPERTY: return createMapProperty();
			case NcorePackage.STRING_PROPERTY: return createStringProperty();
			case NcorePackage.STRING_ENTRY: return (EObject)createStringEntry();
			case NcorePackage.GIT_MARKER: return createGitMarker();
			case NcorePackage.DOCUMENTED_NAMED_ELEMENT: return createDocumentedNamedElement();
			case NcorePackage.COMPOSITE: return createComposite();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case NcorePackage.INSTANT:
				return createInstantFromString(eDataType, initialValue);
			case NcorePackage.DURATION:
				return createDurationFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case NcorePackage.INSTANT:
				return convertInstantToString(eDataType, instanceValue);
			case NcorePackage.DURATION:
				return convertDurationToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Marker createMarker() {
		MarkerImpl marker = new MarkerImpl();
		return marker;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Temporal createTemporal() {
		TemporalImpl temporal = new TemporalImpl();
		return temporal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Period createPeriod() {
		PeriodImpl period = new PeriodImpl();
		return period;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public java.util.Map.Entry<String, String> createRepresentationEntry() {
		RepresentationEntryImpl representationEntry = new RepresentationEntryImpl();
		return representationEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NamedElement createNamedElement() {
		NamedElementImpl namedElement = new NamedElementImpl();
		return namedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <T> Reference<T> createReference() {
		ReferenceImpl<T> reference = new ReferenceImpl<T>();
		return reference;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.nasdanika.ncore.String createString() {
		StringImpl string = new StringImpl();
		return string;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List createList() {
		ListImpl list = new ListImpl();
		return list;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Map createMap() {
		MapImpl map = new MapImpl();
		return map;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public <T> ValueObjectProperty<T> createValueObjectProperty() {
		ValueObjectPropertyImpl<T> valueObjectProperty = new ValueObjectPropertyImpl<T>();
		return valueObjectProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.nasdanika.ncore.Integer createInteger() {
		IntegerImpl integer = new IntegerImpl();
		return integer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.nasdanika.ncore.Double createDouble() {
		DoubleImpl double_ = new DoubleImpl();
		return double_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date createDate() {
		DateImpl date = new DateImpl();
		return date;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.nasdanika.ncore.Long createLong() {
		LongImpl long_ = new LongImpl();
		return long_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public org.nasdanika.ncore.Boolean createBoolean() {
		BooleanImpl boolean_ = new BooleanImpl();
		return boolean_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StringProperty createStringProperty() {
		StringPropertyImpl stringProperty = new StringPropertyImpl();
		return stringProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IntegerProperty createIntegerProperty() {
		IntegerPropertyImpl integerProperty = new IntegerPropertyImpl();
		return integerProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DoubleProperty createDoubleProperty() {
		DoublePropertyImpl doubleProperty = new DoublePropertyImpl();
		return doubleProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LongProperty createLongProperty() {
		LongPropertyImpl longProperty = new LongPropertyImpl();
		return longProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DateProperty createDateProperty() {
		DatePropertyImpl dateProperty = new DatePropertyImpl();
		return dateProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MapProperty createMapProperty() {
		MapPropertyImpl mapProperty = new MapPropertyImpl();
		return mapProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ListProperty createListProperty() {
		ListPropertyImpl listProperty = new ListPropertyImpl();
		return listProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BooleanProperty createBooleanProperty() {
		BooleanPropertyImpl booleanProperty = new BooleanPropertyImpl();
		return booleanProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObjectProperty createEObjectProperty() {
		EObjectPropertyImpl eObjectProperty = new EObjectPropertyImpl();
		return eObjectProperty;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public java.util.Map.Entry<String, String> createStringEntry() {
		StringEntryImpl stringEntry = new StringEntryImpl();
		return stringEntry;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public GitMarker createGitMarker() {
		GitMarkerImpl gitMarker = new GitMarkerImpl();
		return gitMarker;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DocumentedNamedElement createDocumentedNamedElement() {
		DocumentedNamedElementImpl documentedNamedElement = new DocumentedNamedElementImpl();
		return documentedNamedElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Composite createComposite() {
		CompositeImpl composite = new CompositeImpl();
		return composite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Instant createInstantFromString(EDataType eDataType, String initialValue) {
		return DefaultConverter.INSTANCE.toInstant(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertInstantToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Duration createDurationFromString(EDataType eDataType, String initialValue) {
		return DefaultConverter.INSTANCE.toDuration(initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDurationToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NcorePackage getNcorePackage() {
		return (NcorePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static NcorePackage getPackage() {
		return NcorePackage.eINSTANCE;
	}

} //NcoreFactoryImpl
