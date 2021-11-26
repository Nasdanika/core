/**
 */
package org.nasdanika.ncore;

import java.lang.String;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.nasdanika.ncore.NcoreFactory
 * @model kind="package"
 *        annotation="urn:org.nasdanika documentation-reference='doc/package-summary.md'"
 * @generated
 */
public interface NcorePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ncore";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "urn:org.nasdanika.ncore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.ncore";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	NcorePackage eINSTANCE = org.nasdanika.ncore.impl.NcorePackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.common.Adaptable <em>Adaptable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.common.Adaptable
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getAdaptable()
	 * @generated
	 */
	int ADAPTABLE = 0;

	/**
	 * The number of structural features of the '<em>Adaptable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTABLE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Adaptable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ADAPTABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.common.persistence.Marked <em>IMarked</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.common.persistence.Marked
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getIMarked()
	 * @generated
	 */
	int IMARKED = 1;

	/**
	 * The number of structural features of the '<em>IMarked</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKED_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>IMarked</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKED_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.Marked <em>Marked</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.Marked
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getMarked()
	 * @generated
	 */
	int MARKED = 2;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKED__MARKER = IMARKED_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Marked</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKED_FEATURE_COUNT = IMARKED_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Marked</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKED_OPERATION_COUNT = IMARKED_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.common.persistence.Marker <em>IMarker</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.common.persistence.Marker
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getIMarker()
	 * @generated
	 */
	int IMARKER = 3;

	/**
	 * The number of structural features of the '<em>IMarker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>IMarker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.MarkerImpl <em>Marker</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.MarkerImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getMarker()
	 * @generated
	 */
	int MARKER = 4;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER__LOCATION = IMARKER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER__LINE = IMARKER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER__COLUMN = IMARKER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Origin</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER__ORIGIN = IMARKER_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Marker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_FEATURE_COUNT = IMARKER_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Marker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_OPERATION_COUNT = IMARKER_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.TemporalImpl <em>Temporal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.TemporalImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getTemporal()
	 * @generated
	 */
	int TEMPORAL = 5;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.ModelElementImpl <em>Model Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.ModelElementImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getModelElement()
	 * @generated
	 */
	int MODEL_ELEMENT = 7;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__MARKER = MARKED__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__URI = MARKED_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__DESCRIPTION = MARKED_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__UUID = MARKED_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT_FEATURE_COUNT = MARKED_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT_OPERATION_COUNT = MARKED_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL__MARKER = MODEL_ELEMENT__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL__URI = MODEL_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL__UUID = MODEL_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Instant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL__INSTANT = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Base</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL__BASE = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL__OFFSET = MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Derivatives</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL__DERIVATIVES = MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Lower Bounds</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL__LOWER_BOUNDS = MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Upper Bounds</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL__UPPER_BOUNDS = MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Temporal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The operation id for the '<em>After</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL___AFTER__TEMPORAL = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Before</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL___BEFORE__TEMPORAL = MODEL_ELEMENT_OPERATION_COUNT + 1;

	/**
	 * The operation id for the '<em>Coincides</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL___COINCIDES__TEMPORAL = MODEL_ELEMENT_OPERATION_COUNT + 2;

	/**
	 * The operation id for the '<em>Normalize</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL___NORMALIZE = MODEL_ELEMENT_OPERATION_COUNT + 3;

	/**
	 * The operation id for the '<em>Minus</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL___MINUS__TEMPORAL = MODEL_ELEMENT_OPERATION_COUNT + 4;

	/**
	 * The operation id for the '<em>Minus</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL___MINUS__DURATION = MODEL_ELEMENT_OPERATION_COUNT + 5;

	/**
	 * The operation id for the '<em>Plus</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL___PLUS__DURATION = MODEL_ELEMENT_OPERATION_COUNT + 6;

	/**
	 * The operation id for the '<em>Copy</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL___COPY = MODEL_ELEMENT_OPERATION_COUNT + 7;

	/**
	 * The number of operations of the '<em>Temporal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 8;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.PeriodImpl <em>Period</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.PeriodImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getPeriod()
	 * @generated
	 */
	int PERIOD = 6;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIOD__MARKER = MODEL_ELEMENT__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIOD__URI = MODEL_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIOD__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIOD__UUID = MODEL_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Start</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIOD__START = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>End</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIOD__END = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIOD__DURATION = MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Period</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIOD_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Period</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIOD_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.NamedElementImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 8;

	/**
	 * The feature id for the '<em><b>Marker</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__MARKER = MODEL_ELEMENT__MARKER;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__URI = MODEL_ELEMENT__URI;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__DESCRIPTION = MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__UUID = MODEL_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__NAME = MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_FEATURE_COUNT = MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT_OPERATION_COUNT = MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.ReferenceImpl <em>Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.ReferenceImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getReference()
	 * @generated
	 */
	int REFERENCE = 9;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE__TARGET = 0;

	/**
	 * The number of structural features of the '<em>Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REFERENCE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.StringImpl <em>String</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.StringImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getString()
	 * @generated
	 */
	int STRING = 10;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING__VALUE = 0;

	/**
	 * The number of structural features of the '<em>String</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>String</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.ListImpl <em>List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.ListImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getList()
	 * @generated
	 */
	int LIST = 11;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST__VALUE = 0;

	/**
	 * The number of structural features of the '<em>List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.MapImpl <em>Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.MapImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getMap()
	 * @generated
	 */
	int MAP = 12;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.IntegerImpl <em>Integer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.IntegerImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getInteger()
	 * @generated
	 */
	int INTEGER = 13;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER__VALUE = 0;

	/**
	 * The number of structural features of the '<em>Integer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Integer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.PropertyImpl <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.PropertyImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__NAME = 0;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.StringPropertyImpl <em>String Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.StringPropertyImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getStringProperty()
	 * @generated
	 */
	int STRING_PROPERTY = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_PROPERTY__NAME = PROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_PROPERTY__VALUE = PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_PROPERTY_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>String Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_PROPERTY_OPERATION_COUNT = PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.IntegerPropertyImpl <em>Integer Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.IntegerPropertyImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getIntegerProperty()
	 * @generated
	 */
	int INTEGER_PROPERTY = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_PROPERTY__NAME = PROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_PROPERTY__VALUE = PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_PROPERTY_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Integer Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_PROPERTY_OPERATION_COUNT = PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.MapPropertyImpl <em>Map Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.MapPropertyImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getMapProperty()
	 * @generated
	 */
	int MAP_PROPERTY = 17;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_PROPERTY__NAME = PROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_PROPERTY__VALUE = PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Map Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_PROPERTY_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Map Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_PROPERTY_OPERATION_COUNT = PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.ListPropertyImpl <em>List Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.ListPropertyImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getListProperty()
	 * @generated
	 */
	int LIST_PROPERTY = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_PROPERTY__NAME = PROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_PROPERTY__VALUE = PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>List Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_PROPERTY_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>List Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_PROPERTY_OPERATION_COUNT = PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '<em>Instant</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.time.Instant
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getInstant()
	 * @generated
	 */
	int INSTANT = 19;

	/**
	 * The meta object id for the '<em>Duration</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.time.Duration
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDuration()
	 * @generated
	 */
	int DURATION = 20;

	/**
	 * Returns the meta object for class '{@link org.nasdanika.common.Adaptable <em>Adaptable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Adaptable</em>'.
	 * @see org.nasdanika.common.Adaptable
	 * @model instanceClass="org.nasdanika.common.Adaptable"
	 * @generated
	 */
	EClass getAdaptable();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.common.persistence.Marked <em>IMarked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IMarked</em>'.
	 * @see org.nasdanika.common.persistence.Marked
	 * @model instanceClass="org.nasdanika.common.persistence.Marked"
	 * @generated
	 */
	EClass getIMarked();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Marked <em>Marked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Marked</em>'.
	 * @see org.nasdanika.ncore.Marked
	 * @generated
	 */
	EClass getMarked();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.ncore.Marked#getMarker <em>Marker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Marker</em>'.
	 * @see org.nasdanika.ncore.Marked#getMarker()
	 * @see #getMarked()
	 * @generated
	 */
	EReference getMarked_Marker();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.common.persistence.Marker <em>IMarker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IMarker</em>'.
	 * @see org.nasdanika.common.persistence.Marker
	 * @model instanceClass="org.nasdanika.common.persistence.Marker"
	 * @generated
	 */
	EClass getIMarker();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Marker <em>Marker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Marker</em>'.
	 * @see org.nasdanika.ncore.Marker
	 * @generated
	 */
	EClass getMarker();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Marker#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.nasdanika.ncore.Marker#getLocation()
	 * @see #getMarker()
	 * @generated
	 */
	EAttribute getMarker_Location();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Marker#getLine <em>Line</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line</em>'.
	 * @see org.nasdanika.ncore.Marker#getLine()
	 * @see #getMarker()
	 * @generated
	 */
	EAttribute getMarker_Line();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Marker#getColumn <em>Column</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Column</em>'.
	 * @see org.nasdanika.ncore.Marker#getColumn()
	 * @see #getMarker()
	 * @generated
	 */
	EAttribute getMarker_Column();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Marker#getOrigin <em>Origin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Origin</em>'.
	 * @see org.nasdanika.ncore.Marker#getOrigin()
	 * @see #getMarker()
	 * @generated
	 */
	EAttribute getMarker_Origin();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Temporal <em>Temporal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Temporal</em>'.
	 * @see org.nasdanika.ncore.Temporal
	 * @generated
	 */
	EClass getTemporal();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Temporal#getInstant <em>Instant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instant</em>'.
	 * @see org.nasdanika.ncore.Temporal#getInstant()
	 * @see #getTemporal()
	 * @generated
	 */
	EAttribute getTemporal_Instant();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.ncore.Temporal#getBase <em>Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Base</em>'.
	 * @see org.nasdanika.ncore.Temporal#getBase()
	 * @see #getTemporal()
	 * @generated
	 */
	EReference getTemporal_Base();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Temporal#getOffset <em>Offset</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Offset</em>'.
	 * @see org.nasdanika.ncore.Temporal#getOffset()
	 * @see #getTemporal()
	 * @generated
	 */
	EAttribute getTemporal_Offset();

	/**
	 * Returns the meta object for the reference list '{@link org.nasdanika.ncore.Temporal#getDerivatives <em>Derivatives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Derivatives</em>'.
	 * @see org.nasdanika.ncore.Temporal#getDerivatives()
	 * @see #getTemporal()
	 * @generated
	 */
	EReference getTemporal_Derivatives();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.Temporal#getLowerBounds <em>Lower Bounds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Lower Bounds</em>'.
	 * @see org.nasdanika.ncore.Temporal#getLowerBounds()
	 * @see #getTemporal()
	 * @generated
	 */
	EReference getTemporal_LowerBounds();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.Temporal#getUpperBounds <em>Upper Bounds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Upper Bounds</em>'.
	 * @see org.nasdanika.ncore.Temporal#getUpperBounds()
	 * @see #getTemporal()
	 * @generated
	 */
	EReference getTemporal_UpperBounds();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.ncore.Temporal#after(org.nasdanika.ncore.Temporal) <em>After</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>After</em>' operation.
	 * @see org.nasdanika.ncore.Temporal#after(org.nasdanika.ncore.Temporal)
	 * @generated
	 */
	EOperation getTemporal__After__Temporal();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.ncore.Temporal#before(org.nasdanika.ncore.Temporal) <em>Before</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Before</em>' operation.
	 * @see org.nasdanika.ncore.Temporal#before(org.nasdanika.ncore.Temporal)
	 * @generated
	 */
	EOperation getTemporal__Before__Temporal();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.ncore.Temporal#coincides(org.nasdanika.ncore.Temporal) <em>Coincides</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Coincides</em>' operation.
	 * @see org.nasdanika.ncore.Temporal#coincides(org.nasdanika.ncore.Temporal)
	 * @generated
	 */
	EOperation getTemporal__Coincides__Temporal();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.ncore.Temporal#normalize() <em>Normalize</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Normalize</em>' operation.
	 * @see org.nasdanika.ncore.Temporal#normalize()
	 * @generated
	 */
	EOperation getTemporal__Normalize();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.ncore.Temporal#minus(org.nasdanika.ncore.Temporal) <em>Minus</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Minus</em>' operation.
	 * @see org.nasdanika.ncore.Temporal#minus(org.nasdanika.ncore.Temporal)
	 * @generated
	 */
	EOperation getTemporal__Minus__Temporal();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.ncore.Temporal#minus(java.time.Duration) <em>Minus</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Minus</em>' operation.
	 * @see org.nasdanika.ncore.Temporal#minus(java.time.Duration)
	 * @generated
	 */
	EOperation getTemporal__Minus__Duration();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.ncore.Temporal#plus(java.time.Duration) <em>Plus</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Plus</em>' operation.
	 * @see org.nasdanika.ncore.Temporal#plus(java.time.Duration)
	 * @generated
	 */
	EOperation getTemporal__Plus__Duration();

	/**
	 * Returns the meta object for the '{@link org.nasdanika.ncore.Temporal#copy() <em>Copy</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Copy</em>' operation.
	 * @see org.nasdanika.ncore.Temporal#copy()
	 * @generated
	 */
	EOperation getTemporal__Copy();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Period <em>Period</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Period</em>'.
	 * @see org.nasdanika.ncore.Period
	 * @generated
	 */
	EClass getPeriod();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.ncore.Period#getStart <em>Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Start</em>'.
	 * @see org.nasdanika.ncore.Period#getStart()
	 * @see #getPeriod()
	 * @generated
	 */
	EReference getPeriod_Start();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.ncore.Period#getEnd <em>End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>End</em>'.
	 * @see org.nasdanika.ncore.Period#getEnd()
	 * @see #getPeriod()
	 * @generated
	 */
	EReference getPeriod_End();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Period#getDuration <em>Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration</em>'.
	 * @see org.nasdanika.ncore.Period#getDuration()
	 * @see #getPeriod()
	 * @generated
	 */
	EAttribute getPeriod_Duration();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.ModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model Element</em>'.
	 * @see org.nasdanika.ncore.ModelElement
	 * @generated
	 */
	EClass getModelElement();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.ModelElement#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.nasdanika.ncore.ModelElement#getUri()
	 * @see #getModelElement()
	 * @generated
	 */
	EAttribute getModelElement_Uri();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.ModelElement#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.nasdanika.ncore.ModelElement#getDescription()
	 * @see #getModelElement()
	 * @generated
	 */
	EAttribute getModelElement_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.ModelElement#getUuid <em>Uuid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uuid</em>'.
	 * @see org.nasdanika.ncore.ModelElement#getUuid()
	 * @see #getModelElement()
	 * @generated
	 */
	EAttribute getModelElement_Uuid();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see org.nasdanika.ncore.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.nasdanika.ncore.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reference</em>'.
	 * @see org.nasdanika.ncore.Reference
	 * @generated
	 */
	EClass getReference();

	/**
	 * Returns the meta object for the reference '{@link org.nasdanika.ncore.Reference#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.nasdanika.ncore.Reference#getTarget()
	 * @see #getReference()
	 * @generated
	 */
	EReference getReference_Target();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.String <em>String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String</em>'.
	 * @see org.nasdanika.ncore.String
	 * @generated
	 */
	EClass getString();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.String#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.nasdanika.ncore.String#getValue()
	 * @see #getString()
	 * @generated
	 */
	EAttribute getString_Value();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.List <em>List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List</em>'.
	 * @see org.nasdanika.ncore.List
	 * @generated
	 */
	EClass getList();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.List#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Value</em>'.
	 * @see org.nasdanika.ncore.List#getValue()
	 * @see #getList()
	 * @generated
	 */
	EReference getList_Value();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Map <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map</em>'.
	 * @see org.nasdanika.ncore.Map
	 * @generated
	 */
	EClass getMap();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.Map#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Value</em>'.
	 * @see org.nasdanika.ncore.Map#getValue()
	 * @see #getMap()
	 * @generated
	 */
	EReference getMap_Value();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Integer <em>Integer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer</em>'.
	 * @see org.nasdanika.ncore.Integer
	 * @generated
	 */
	EClass getInteger();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Integer#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.nasdanika.ncore.Integer#getValue()
	 * @see #getInteger()
	 * @generated
	 */
	EAttribute getInteger_Value();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see org.nasdanika.ncore.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Property#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.nasdanika.ncore.Property#getName()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_Name();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.StringProperty <em>String Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Property</em>'.
	 * @see org.nasdanika.ncore.StringProperty
	 * @generated
	 */
	EClass getStringProperty();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.IntegerProperty <em>Integer Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Property</em>'.
	 * @see org.nasdanika.ncore.IntegerProperty
	 * @generated
	 */
	EClass getIntegerProperty();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.MapProperty <em>Map Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map Property</em>'.
	 * @see org.nasdanika.ncore.MapProperty
	 * @generated
	 */
	EClass getMapProperty();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.ListProperty <em>List Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>List Property</em>'.
	 * @see org.nasdanika.ncore.ListProperty
	 * @generated
	 */
	EClass getListProperty();

	/**
	 * Returns the meta object for data type '{@link java.time.Instant <em>Instant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * [Instant](https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html) is a single instantaneous point on the time-line. 
     * If instant specification contains ``Z``, then it is parsed using [Instant.parse()](https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html#parse-java.lang.CharSequence-) method.
     * Otherwise the specification is used to construct ``java.util.Date`` which is then converted to instant.
     * 
     * Examples:
     * 
     * * ``2021-12-03T10:15:30.00Z`` - loaded using ``Instant.parse()``
     * * ``10/1/2021`` - loaded using ``new Date()`` and then converted to instant.
     * 
     * 
     * <!-- end-model-doc -->
	 * @return the meta object for data type '<em>Instant</em>'.
	 * @see java.time.Instant
	 * @model instanceClass="java.time.Instant"
	 * @generated
	 */
	EDataType getInstant();

	/**
	 * Returns the meta object for data type '{@link java.time.Duration <em>Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
     * <!-- begin-model-doc -->
     * [Duration](https://docs.oracle.com/javase/8/docs/api/java/time/Duration.html) is an amount of time in the [ISO-8601 Duration format](https://en.wikipedia.org/wiki/ISO_8601#Duration). Supports days and smaller units. Weeks, months and years are not supported. Example: ``P28DT10H``.
     * <!-- end-model-doc -->
	 * @return the meta object for data type '<em>Duration</em>'.
	 * @see java.time.Duration
	 * @model instanceClass="java.time.Duration"
	 * @generated
	 */
	EDataType getDuration();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	NcoreFactory getNcoreFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.nasdanika.common.Adaptable <em>Adaptable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.common.Adaptable
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getAdaptable()
		 * @generated
		 */
		EClass ADAPTABLE = eINSTANCE.getAdaptable();

		/**
		 * The meta object literal for the '{@link org.nasdanika.common.persistence.Marked <em>IMarked</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.common.persistence.Marked
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getIMarked()
		 * @generated
		 */
		EClass IMARKED = eINSTANCE.getIMarked();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.Marked <em>Marked</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.Marked
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getMarked()
		 * @generated
		 */
		EClass MARKED = eINSTANCE.getMarked();

		/**
		 * The meta object literal for the '<em><b>Marker</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MARKED__MARKER = eINSTANCE.getMarked_Marker();

		/**
		 * The meta object literal for the '{@link org.nasdanika.common.persistence.Marker <em>IMarker</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.common.persistence.Marker
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getIMarker()
		 * @generated
		 */
		EClass IMARKER = eINSTANCE.getIMarker();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.MarkerImpl <em>Marker</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.MarkerImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getMarker()
		 * @generated
		 */
		EClass MARKER = eINSTANCE.getMarker();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MARKER__LOCATION = eINSTANCE.getMarker_Location();

		/**
		 * The meta object literal for the '<em><b>Line</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MARKER__LINE = eINSTANCE.getMarker_Line();

		/**
		 * The meta object literal for the '<em><b>Column</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MARKER__COLUMN = eINSTANCE.getMarker_Column();

		/**
		 * The meta object literal for the '<em><b>Origin</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MARKER__ORIGIN = eINSTANCE.getMarker_Origin();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.TemporalImpl <em>Temporal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.TemporalImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getTemporal()
		 * @generated
		 */
		EClass TEMPORAL = eINSTANCE.getTemporal();

		/**
		 * The meta object literal for the '<em><b>Instant</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPORAL__INSTANT = eINSTANCE.getTemporal_Instant();

		/**
		 * The meta object literal for the '<em><b>Base</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPORAL__BASE = eINSTANCE.getTemporal_Base();

		/**
		 * The meta object literal for the '<em><b>Offset</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEMPORAL__OFFSET = eINSTANCE.getTemporal_Offset();

		/**
		 * The meta object literal for the '<em><b>Derivatives</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPORAL__DERIVATIVES = eINSTANCE.getTemporal_Derivatives();

		/**
		 * The meta object literal for the '<em><b>Lower Bounds</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPORAL__LOWER_BOUNDS = eINSTANCE.getTemporal_LowerBounds();

		/**
		 * The meta object literal for the '<em><b>Upper Bounds</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPORAL__UPPER_BOUNDS = eINSTANCE.getTemporal_UpperBounds();

		/**
		 * The meta object literal for the '<em><b>After</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation TEMPORAL___AFTER__TEMPORAL = eINSTANCE.getTemporal__After__Temporal();

		/**
		 * The meta object literal for the '<em><b>Before</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation TEMPORAL___BEFORE__TEMPORAL = eINSTANCE.getTemporal__Before__Temporal();

		/**
		 * The meta object literal for the '<em><b>Coincides</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation TEMPORAL___COINCIDES__TEMPORAL = eINSTANCE.getTemporal__Coincides__Temporal();

		/**
		 * The meta object literal for the '<em><b>Normalize</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation TEMPORAL___NORMALIZE = eINSTANCE.getTemporal__Normalize();

		/**
		 * The meta object literal for the '<em><b>Minus</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation TEMPORAL___MINUS__TEMPORAL = eINSTANCE.getTemporal__Minus__Temporal();

		/**
		 * The meta object literal for the '<em><b>Minus</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation TEMPORAL___MINUS__DURATION = eINSTANCE.getTemporal__Minus__Duration();

		/**
		 * The meta object literal for the '<em><b>Plus</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation TEMPORAL___PLUS__DURATION = eINSTANCE.getTemporal__Plus__Duration();

		/**
		 * The meta object literal for the '<em><b>Copy</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation TEMPORAL___COPY = eINSTANCE.getTemporal__Copy();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.PeriodImpl <em>Period</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.PeriodImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getPeriod()
		 * @generated
		 */
		EClass PERIOD = eINSTANCE.getPeriod();

		/**
		 * The meta object literal for the '<em><b>Start</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERIOD__START = eINSTANCE.getPeriod_Start();

		/**
		 * The meta object literal for the '<em><b>End</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERIOD__END = eINSTANCE.getPeriod_End();

		/**
		 * The meta object literal for the '<em><b>Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PERIOD__DURATION = eINSTANCE.getPeriod_Duration();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.ModelElementImpl <em>Model Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.ModelElementImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getModelElement()
		 * @generated
		 */
		EClass MODEL_ELEMENT = eINSTANCE.getModelElement();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_ELEMENT__URI = eINSTANCE.getModelElement_Uri();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_ELEMENT__DESCRIPTION = eINSTANCE.getModelElement_Description();

		/**
		 * The meta object literal for the '<em><b>Uuid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_ELEMENT__UUID = eINSTANCE.getModelElement_Uuid();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.NamedElementImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.ReferenceImpl <em>Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.ReferenceImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getReference()
		 * @generated
		 */
		EClass REFERENCE = eINSTANCE.getReference();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REFERENCE__TARGET = eINSTANCE.getReference_Target();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.StringImpl <em>String</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.StringImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getString()
		 * @generated
		 */
		EClass STRING = eINSTANCE.getString();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING__VALUE = eINSTANCE.getString_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.ListImpl <em>List</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.ListImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getList()
		 * @generated
		 */
		EClass LIST = eINSTANCE.getList();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIST__VALUE = eINSTANCE.getList_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.MapImpl <em>Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.MapImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getMap()
		 * @generated
		 */
		EClass MAP = eINSTANCE.getMap();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP__VALUE = eINSTANCE.getMap_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.IntegerImpl <em>Integer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.IntegerImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getInteger()
		 * @generated
		 */
		EClass INTEGER = eINSTANCE.getInteger();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER__VALUE = eINSTANCE.getInteger_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.PropertyImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__NAME = eINSTANCE.getProperty_Name();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.StringPropertyImpl <em>String Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.StringPropertyImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getStringProperty()
		 * @generated
		 */
		EClass STRING_PROPERTY = eINSTANCE.getStringProperty();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.IntegerPropertyImpl <em>Integer Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.IntegerPropertyImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getIntegerProperty()
		 * @generated
		 */
		EClass INTEGER_PROPERTY = eINSTANCE.getIntegerProperty();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.MapPropertyImpl <em>Map Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.MapPropertyImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getMapProperty()
		 * @generated
		 */
		EClass MAP_PROPERTY = eINSTANCE.getMapProperty();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.ListPropertyImpl <em>List Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.ListPropertyImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getListProperty()
		 * @generated
		 */
		EClass LIST_PROPERTY = eINSTANCE.getListProperty();

		/**
		 * The meta object literal for the '<em>Instant</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.time.Instant
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getInstant()
		 * @generated
		 */
		EDataType INSTANT = eINSTANCE.getInstant();

		/**
		 * The meta object literal for the '<em>Duration</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.time.Duration
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDuration()
		 * @generated
		 */
		EDataType DURATION = eINSTANCE.getDuration();

	}

} //NcorePackage
