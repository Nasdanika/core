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
	String eNS_URI = "ecore://nasdanika.org/core/ncore";

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
	 * The meta object id for the '{@link org.nasdanika.persistence.Marked <em>IMarked</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.persistence.Marked
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
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKED__MARKERS = IMARKED_FEATURE_COUNT + 0;

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
	 * The meta object id for the '{@link org.nasdanika.persistence.Marker <em>IMarker</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.persistence.Marker
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
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER__POSITION = IMARKER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER__COMMENT = IMARKER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER__DATE = IMARKER_FEATURE_COUNT + 3;

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
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__MARKERS = MARKED__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__URIS = MARKED_FEATURE_COUNT + 0;

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
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__LABEL_PROTOTYPE = MARKED_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__REPRESENTATIONS = MARKED_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT__ANNOTATIONS = MARKED_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT_FEATURE_COUNT = MARKED_FEATURE_COUNT + 6;

	/**
	 * The number of operations of the '<em>Model Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MODEL_ELEMENT_OPERATION_COUNT = MARKED_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL__MARKERS = MODEL_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL__URIS = MODEL_ELEMENT__URIS;

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
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL__LABEL_PROTOTYPE = MODEL_ELEMENT__LABEL_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL__REPRESENTATIONS = MODEL_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPORAL__ANNOTATIONS = MODEL_ELEMENT__ANNOTATIONS;

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
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIOD__MARKERS = MODEL_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIOD__URIS = MODEL_ELEMENT__URIS;

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
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIOD__LABEL_PROTOTYPE = MODEL_ELEMENT__LABEL_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIOD__REPRESENTATIONS = MODEL_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERIOD__ANNOTATIONS = MODEL_ELEMENT__ANNOTATIONS;

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
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.RepresentationEntryImpl <em>Representation Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.RepresentationEntryImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getRepresentationEntry()
	 * @generated
	 */
	int REPRESENTATION_ENTRY = 8;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPRESENTATION_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPRESENTATION_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Representation Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPRESENTATION_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Representation Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPRESENTATION_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.NamedElementImpl <em>Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.NamedElementImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getNamedElement()
	 * @generated
	 */
	int NAMED_ELEMENT = 9;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__MARKERS = MODEL_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__URIS = MODEL_ELEMENT__URIS;

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
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__LABEL_PROTOTYPE = MODEL_ELEMENT__LABEL_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__REPRESENTATIONS = MODEL_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMED_ELEMENT__ANNOTATIONS = MODEL_ELEMENT__ANNOTATIONS;

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
	int REFERENCE = 10;

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
	 * The meta object id for the '{@link org.nasdanika.ncore.ValueObject <em>Value Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.ValueObject
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getValueObject()
	 * @generated
	 */
	int VALUE_OBJECT = 11;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OBJECT__MARKERS = MARKED__MARKERS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OBJECT__VALUE = MARKED_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Value Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OBJECT_FEATURE_COUNT = MARKED_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Value Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OBJECT_OPERATION_COUNT = MARKED_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.StringImpl <em>String</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.StringImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getString()
	 * @generated
	 */
	int STRING = 12;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING__MARKERS = VALUE_OBJECT__MARKERS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING__VALUE = VALUE_OBJECT__VALUE;

	/**
	 * The number of structural features of the '<em>String</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_FEATURE_COUNT = VALUE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>String</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_OPERATION_COUNT = VALUE_OBJECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.ListImpl <em>List</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.ListImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getList()
	 * @generated
	 */
	int LIST = 17;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.MapImpl <em>Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.MapImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getMap()
	 * @generated
	 */
	int MAP = 19;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.IntegerImpl <em>Integer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.IntegerImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getInteger()
	 * @generated
	 */
	int INTEGER = 16;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.DoubleImpl <em>Double</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.DoubleImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDouble()
	 * @generated
	 */
	int DOUBLE = 14;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.DateImpl <em>Date</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.DateImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDate()
	 * @generated
	 */
	int DATE = 15;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.LongImpl <em>Long</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.LongImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getLong()
	 * @generated
	 */
	int LONG = 18;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.BooleanImpl <em>Boolean</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.BooleanImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getBoolean()
	 * @generated
	 */
	int BOOLEAN = 13;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN__MARKERS = VALUE_OBJECT__MARKERS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN__VALUE = VALUE_OBJECT__VALUE;

	/**
	 * The number of structural features of the '<em>Boolean</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_FEATURE_COUNT = VALUE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Boolean</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_OPERATION_COUNT = VALUE_OBJECT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE__MARKERS = VALUE_OBJECT__MARKERS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE__VALUE = VALUE_OBJECT__VALUE;

	/**
	 * The number of structural features of the '<em>Double</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_FEATURE_COUNT = VALUE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Double</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_OPERATION_COUNT = VALUE_OBJECT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE__MARKERS = VALUE_OBJECT__MARKERS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE__VALUE = VALUE_OBJECT__VALUE;

	/**
	 * The number of structural features of the '<em>Date</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_FEATURE_COUNT = VALUE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Date</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_OPERATION_COUNT = VALUE_OBJECT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER__MARKERS = VALUE_OBJECT__MARKERS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER__VALUE = VALUE_OBJECT__VALUE;

	/**
	 * The number of structural features of the '<em>Integer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_FEATURE_COUNT = VALUE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Integer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_OPERATION_COUNT = VALUE_OBJECT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST__MARKERS = MARKED__MARKERS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST__VALUE = MARKED_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_FEATURE_COUNT = MARKED_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>List</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_OPERATION_COUNT = MARKED_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG__MARKERS = VALUE_OBJECT__MARKERS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG__VALUE = VALUE_OBJECT__VALUE;

	/**
	 * The number of structural features of the '<em>Long</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_FEATURE_COUNT = VALUE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Long</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_OPERATION_COUNT = VALUE_OBJECT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP__MARKERS = MARKED__MARKERS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP__VALUE = MARKED_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_FEATURE_COUNT = MARKED_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_OPERATION_COUNT = MARKED_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.Property <em>Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.Property
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getProperty()
	 * @generated
	 */
	int PROPERTY = 20;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__MARKERS = MARKED__MARKERS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY__NAME = MARKED_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_FEATURE_COUNT = MARKED_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROPERTY_OPERATION_COUNT = MARKED_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.ValueObjectPropertyImpl <em>Value Object Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.ValueObjectPropertyImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getValueObjectProperty()
	 * @generated
	 */
	int VALUE_OBJECT_PROPERTY = 21;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OBJECT_PROPERTY__MARKERS = VALUE_OBJECT__MARKERS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OBJECT_PROPERTY__VALUE = VALUE_OBJECT__VALUE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OBJECT_PROPERTY__NAME = VALUE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Value Object Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OBJECT_PROPERTY_FEATURE_COUNT = VALUE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Value Object Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_OBJECT_PROPERTY_OPERATION_COUNT = VALUE_OBJECT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.StringPropertyImpl <em>String Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.StringPropertyImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getStringProperty()
	 * @generated
	 */
	int STRING_PROPERTY = 30;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.IntegerPropertyImpl <em>Integer Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.IntegerPropertyImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getIntegerProperty()
	 * @generated
	 */
	int INTEGER_PROPERTY = 26;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.DoublePropertyImpl <em>Double Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.DoublePropertyImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDoubleProperty()
	 * @generated
	 */
	int DOUBLE_PROPERTY = 24;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.LongPropertyImpl <em>Long Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.LongPropertyImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getLongProperty()
	 * @generated
	 */
	int LONG_PROPERTY = 28;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.DatePropertyImpl <em>Date Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.DatePropertyImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDateProperty()
	 * @generated
	 */
	int DATE_PROPERTY = 23;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.MapPropertyImpl <em>Map Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.MapPropertyImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getMapProperty()
	 * @generated
	 */
	int MAP_PROPERTY = 29;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.ListPropertyImpl <em>List Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.ListPropertyImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getListProperty()
	 * @generated
	 */
	int LIST_PROPERTY = 27;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.BooleanPropertyImpl <em>Boolean Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.BooleanPropertyImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getBooleanProperty()
	 * @generated
	 */
	int BOOLEAN_PROPERTY = 22;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_PROPERTY__MARKERS = BOOLEAN__MARKERS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_PROPERTY__VALUE = BOOLEAN__VALUE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_PROPERTY__NAME = BOOLEAN_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_PROPERTY_FEATURE_COUNT = BOOLEAN_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Boolean Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BOOLEAN_PROPERTY_OPERATION_COUNT = BOOLEAN_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_PROPERTY__MARKERS = DATE__MARKERS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_PROPERTY__VALUE = DATE__VALUE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_PROPERTY__NAME = DATE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Date Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_PROPERTY_FEATURE_COUNT = DATE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Date Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATE_PROPERTY_OPERATION_COUNT = DATE_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_PROPERTY__MARKERS = DOUBLE__MARKERS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_PROPERTY__VALUE = DOUBLE__VALUE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_PROPERTY__NAME = DOUBLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Double Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_PROPERTY_FEATURE_COUNT = DOUBLE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Double Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOUBLE_PROPERTY_OPERATION_COUNT = DOUBLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.EObjectPropertyImpl <em>EObject Property</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.EObjectPropertyImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getEObjectProperty()
	 * @generated
	 */
	int EOBJECT_PROPERTY = 25;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_PROPERTY__MARKERS = PROPERTY__MARKERS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_PROPERTY__NAME = PROPERTY__NAME;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_PROPERTY__VALUE = PROPERTY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>EObject Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_PROPERTY_FEATURE_COUNT = PROPERTY_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>EObject Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EOBJECT_PROPERTY_OPERATION_COUNT = PROPERTY_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_PROPERTY__MARKERS = INTEGER__MARKERS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_PROPERTY__VALUE = INTEGER__VALUE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_PROPERTY__NAME = INTEGER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_PROPERTY_FEATURE_COUNT = INTEGER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Integer Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTEGER_PROPERTY_OPERATION_COUNT = INTEGER_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LIST_PROPERTY__MARKERS = PROPERTY__MARKERS;

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
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_PROPERTY__MARKERS = LONG__MARKERS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_PROPERTY__VALUE = LONG__VALUE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_PROPERTY__NAME = LONG_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Long Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_PROPERTY_FEATURE_COUNT = LONG_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Long Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LONG_PROPERTY_OPERATION_COUNT = LONG_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_PROPERTY__MARKERS = PROPERTY__MARKERS;

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
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_PROPERTY__MARKERS = STRING__MARKERS;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_PROPERTY__VALUE = STRING__VALUE;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_PROPERTY__NAME = STRING_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_PROPERTY_FEATURE_COUNT = STRING_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>String Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_PROPERTY_OPERATION_COUNT = STRING_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.StringEntryImpl <em>String Entry</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.StringEntryImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getStringEntry()
	 * @generated
	 */
	int STRING_ENTRY = 31;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ENTRY__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ENTRY__VALUE = 1;

	/**
	 * The number of structural features of the '<em>String Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ENTRY_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>String Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_ENTRY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.GitMarkerImpl <em>Git Marker</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.GitMarkerImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getGitMarker()
	 * @generated
	 */
	int GIT_MARKER = 32;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_MARKER__LOCATION = MARKER__LOCATION;

	/**
	 * The feature id for the '<em><b>Position</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_MARKER__POSITION = MARKER__POSITION;

	/**
	 * The feature id for the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_MARKER__COMMENT = MARKER__COMMENT;

	/**
	 * The feature id for the '<em><b>Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_MARKER__DATE = MARKER__DATE;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_MARKER__PATH = MARKER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Remotes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_MARKER__REMOTES = MARKER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Branch</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_MARKER__BRANCH = MARKER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Head</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_MARKER__HEAD = MARKER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Head Refs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_MARKER__HEAD_REFS = MARKER_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Git Marker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_MARKER_FEATURE_COUNT = MARKER_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Git Marker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GIT_MARKER_OPERATION_COUNT = MARKER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.Documented <em>Documented</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.Documented
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDocumented()
	 * @generated
	 */
	int DOCUMENTED = 33;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED__DOCUMENTATION = 0;

	/**
	 * The feature id for the '<em><b>Context Help</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED__CONTEXT_HELP = 1;

	/**
	 * The number of structural features of the '<em>Documented</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Documented</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.DocumentedNamedElementImpl <em>Documented Named Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.DocumentedNamedElementImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDocumentedNamedElement()
	 * @generated
	 */
	int DOCUMENTED_NAMED_ELEMENT = 34;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_ELEMENT__MARKERS = NAMED_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_ELEMENT__URIS = NAMED_ELEMENT__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_ELEMENT__DESCRIPTION = NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_ELEMENT__UUID = NAMED_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_ELEMENT__LABEL_PROTOTYPE = NAMED_ELEMENT__LABEL_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_ELEMENT__REPRESENTATIONS = NAMED_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_ELEMENT__ANNOTATIONS = NAMED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_ELEMENT__NAME = NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_ELEMENT__DOCUMENTATION = NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Context Help</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_ELEMENT__CONTEXT_HELP = NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Documented Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_ELEMENT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Documented Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_ELEMENT_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.CompositeImpl <em>Composite</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.CompositeImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getComposite()
	 * @generated
	 */
	int COMPOSITE = 35;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE__MARKERS = DOCUMENTED_NAMED_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE__URIS = DOCUMENTED_NAMED_ELEMENT__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE__DESCRIPTION = DOCUMENTED_NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE__UUID = DOCUMENTED_NAMED_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE__LABEL_PROTOTYPE = DOCUMENTED_NAMED_ELEMENT__LABEL_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE__REPRESENTATIONS = DOCUMENTED_NAMED_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE__ANNOTATIONS = DOCUMENTED_NAMED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE__NAME = DOCUMENTED_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE__DOCUMENTATION = DOCUMENTED_NAMED_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Context Help</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE__CONTEXT_HELP = DOCUMENTED_NAMED_ELEMENT__CONTEXT_HELP;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE__ID = DOCUMENTED_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE__CHILDREN = DOCUMENTED_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Composite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_FEATURE_COUNT = DOCUMENTED_NAMED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Composite</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPOSITE_OPERATION_COUNT = DOCUMENTED_NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.StringIdentityImpl <em>String Identity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.StringIdentityImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getStringIdentity()
	 * @generated
	 */
	int STRING_IDENTITY = 36;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_IDENTITY__ID = 0;

	/**
	 * The number of structural features of the '<em>String Identity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_IDENTITY_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>String Identity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRING_IDENTITY_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.DocumentedNamedStringIdentityImpl <em>Documented Named String Identity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.DocumentedNamedStringIdentityImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDocumentedNamedStringIdentity()
	 * @generated
	 */
	int DOCUMENTED_NAMED_STRING_IDENTITY = 37;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_STRING_IDENTITY__MARKERS = DOCUMENTED_NAMED_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_STRING_IDENTITY__URIS = DOCUMENTED_NAMED_ELEMENT__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_STRING_IDENTITY__DESCRIPTION = DOCUMENTED_NAMED_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_STRING_IDENTITY__UUID = DOCUMENTED_NAMED_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Label Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_STRING_IDENTITY__LABEL_PROTOTYPE = DOCUMENTED_NAMED_ELEMENT__LABEL_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_STRING_IDENTITY__REPRESENTATIONS = DOCUMENTED_NAMED_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_STRING_IDENTITY__ANNOTATIONS = DOCUMENTED_NAMED_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_STRING_IDENTITY__NAME = DOCUMENTED_NAMED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_STRING_IDENTITY__DOCUMENTATION = DOCUMENTED_NAMED_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Context Help</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_STRING_IDENTITY__CONTEXT_HELP = DOCUMENTED_NAMED_ELEMENT__CONTEXT_HELP;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_STRING_IDENTITY__ID = DOCUMENTED_NAMED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Documented Named String Identity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_STRING_IDENTITY_FEATURE_COUNT = DOCUMENTED_NAMED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Documented Named String Identity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTED_NAMED_STRING_IDENTITY_OPERATION_COUNT = DOCUMENTED_NAMED_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.TreeItemImpl <em>Tree Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.TreeItemImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getTreeItem()
	 * @generated
	 */
	int TREE_ITEM = 38;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ITEM__NAME = 0;

	/**
	 * The number of structural features of the '<em>Tree Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ITEM_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Tree Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ITEM_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.TreeImpl <em>Tree</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.TreeImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getTree()
	 * @generated
	 */
	int TREE = 39;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__NAME = TREE_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Tree Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE__TREE_ITEMS = TREE_ITEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tree</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_FEATURE_COUNT = TREE_ITEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Tree</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_OPERATION_COUNT = TREE_ITEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.FileImpl <em>File</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.FileImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getFile()
	 * @generated
	 */
	int FILE = 40;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE__NAME = TREE_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE__LENGTH = TREE_ITEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Last Modified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE__LAST_MODIFIED = TREE_ITEM_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_FEATURE_COUNT = TREE_ITEM_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILE_OPERATION_COUNT = TREE_ITEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.DirectoryImpl <em>Directory</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.DirectoryImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDirectory()
	 * @generated
	 */
	int DIRECTORY = 41;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECTORY__NAME = FILE__NAME;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECTORY__LENGTH = FILE__LENGTH;

	/**
	 * The feature id for the '<em><b>Last Modified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECTORY__LAST_MODIFIED = FILE__LAST_MODIFIED;

	/**
	 * The feature id for the '<em><b>Tree Items</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECTORY__TREE_ITEMS = FILE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Directory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECTORY_FEATURE_COUNT = FILE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Directory</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIRECTORY_OPERATION_COUNT = FILE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.ThrowableImpl <em>Throwable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.ThrowableImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getThrowable()
	 * @generated
	 */
	int THROWABLE = 42;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THROWABLE__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THROWABLE__MESSAGE = 1;

	/**
	 * The feature id for the '<em><b>Stack Trace</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THROWABLE__STACK_TRACE = 2;

	/**
	 * The feature id for the '<em><b>Supressed</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THROWABLE__SUPRESSED = 3;

	/**
	 * The feature id for the '<em><b>Cause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THROWABLE__CAUSE = 4;

	/**
	 * The number of structural features of the '<em>Throwable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THROWABLE_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Throwable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int THROWABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.StackTraceElementImpl <em>Stack Trace Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.StackTraceElementImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getStackTraceElement()
	 * @generated
	 */
	int STACK_TRACE_ELEMENT = 43;

	/**
	 * The feature id for the '<em><b>Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STACK_TRACE_ELEMENT__CLASS_NAME = 0;

	/**
	 * The feature id for the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STACK_TRACE_ELEMENT__FILE_NAME = 1;

	/**
	 * The feature id for the '<em><b>Method Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STACK_TRACE_ELEMENT__METHOD_NAME = 2;

	/**
	 * The feature id for the '<em><b>Line Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STACK_TRACE_ELEMENT__LINE_NUMBER = 3;

	/**
	 * The feature id for the '<em><b>Native</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STACK_TRACE_ELEMENT__NATIVE = 4;

	/**
	 * The number of structural features of the '<em>Stack Trace Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STACK_TRACE_ELEMENT_FEATURE_COUNT = 5;

	/**
	 * The number of operations of the '<em>Stack Trace Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STACK_TRACE_ELEMENT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.ncore.impl.TreeItemReferenceImpl <em>Tree Item Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.ncore.impl.TreeItemReferenceImpl
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getTreeItemReference()
	 * @generated
	 */
	int TREE_ITEM_REFERENCE = 44;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ITEM_REFERENCE__NAME = TREE_ITEM__NAME;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ITEM_REFERENCE__TARGET = TREE_ITEM_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tree Item Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ITEM_REFERENCE_FEATURE_COUNT = TREE_ITEM_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Tree Item Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TREE_ITEM_REFERENCE_OPERATION_COUNT = TREE_ITEM_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '<em>Instant</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.time.Instant
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getInstant()
	 * @generated
	 */
	int INSTANT = 45;

	/**
	 * The meta object id for the '<em>Duration</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.time.Duration
	 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDuration()
	 * @generated
	 */
	int DURATION = 46;

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
	 * Returns the meta object for class '{@link org.nasdanika.persistence.Marked <em>IMarked</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IMarked</em>'.
	 * @see org.nasdanika.persistence.Marked
	 * @model instanceClass="org.nasdanika.persistence.Marked"
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
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.Marked#getMarkers <em>Markers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Markers</em>'.
	 * @see org.nasdanika.ncore.Marked#getMarkers()
	 * @see #getMarked()
	 * @generated
	 */
	EReference getMarked_Markers();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.persistence.Marker <em>IMarker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IMarker</em>'.
	 * @see org.nasdanika.persistence.Marker
	 * @model instanceClass="org.nasdanika.persistence.Marker"
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
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Marker#getPosition <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Position</em>'.
	 * @see org.nasdanika.ncore.Marker#getPosition()
	 * @see #getMarker()
	 * @generated
	 */
	EAttribute getMarker_Position();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Marker#getComment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Comment</em>'.
	 * @see org.nasdanika.ncore.Marker#getComment()
	 * @see #getMarker()
	 * @generated
	 */
	EAttribute getMarker_Comment();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Marker#getDate <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Date</em>'.
	 * @see org.nasdanika.ncore.Marker#getDate()
	 * @see #getMarker()
	 * @generated
	 */
	EAttribute getMarker_Date();

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
	 * Returns the meta object for the attribute list '{@link org.nasdanika.ncore.ModelElement#getUris <em>Uris</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Uris</em>'.
	 * @see org.nasdanika.ncore.ModelElement#getUris()
	 * @see #getModelElement()
	 * @generated
	 */
	EAttribute getModelElement_Uris();

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
	 * Returns the meta object for the containment reference '{@link org.nasdanika.ncore.ModelElement#getLabelPrototype <em>Label Prototype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Label Prototype</em>'.
	 * @see org.nasdanika.ncore.ModelElement#getLabelPrototype()
	 * @see #getModelElement()
	 * @generated
	 */
	EReference getModelElement_LabelPrototype();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.ncore.ModelElement#getRepresentations <em>Representations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Representations</em>'.
	 * @see org.nasdanika.ncore.ModelElement#getRepresentations()
	 * @see #getModelElement()
	 * @generated
	 */
	EReference getModelElement_Representations();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.ModelElement#getAnnotations <em>Annotations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Annotations</em>'.
	 * @see org.nasdanika.ncore.ModelElement#getAnnotations()
	 * @see #getModelElement()
	 * @generated
	 */
	EReference getModelElement_Annotations();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Representation Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Representation Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 *        valueAnnotation="urn:org.nasdanika content-type='resource-uri'"
	 * @generated
	 */
	EClass getRepresentationEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getRepresentationEntry()
	 * @generated
	 */
	EAttribute getRepresentationEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getRepresentationEntry()
	 * @generated
	 */
	EAttribute getRepresentationEntry_Value();

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
	 * Returns the meta object for class '{@link org.nasdanika.ncore.ValueObject <em>Value Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Object</em>'.
	 * @see org.nasdanika.ncore.ValueObject
	 * @generated
	 */
	EClass getValueObject();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.ValueObject#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.nasdanika.ncore.ValueObject#getValue()
	 * @see #getValueObject()
	 * @generated
	 */
	EAttribute getValueObject_Value();

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
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Double <em>Double</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double</em>'.
	 * @see org.nasdanika.ncore.Double
	 * @generated
	 */
	EClass getDouble();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Date <em>Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Date</em>'.
	 * @see org.nasdanika.ncore.Date
	 * @generated
	 */
	EClass getDate();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Long <em>Long</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Long</em>'.
	 * @see org.nasdanika.ncore.Long
	 * @generated
	 */
	EClass getLong();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Boolean <em>Boolean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean</em>'.
	 * @see org.nasdanika.ncore.Boolean
	 * @generated
	 */
	EClass getBoolean();

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
	 * Returns the meta object for class '{@link org.nasdanika.ncore.ValueObjectProperty <em>Value Object Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Object Property</em>'.
	 * @see org.nasdanika.ncore.ValueObjectProperty
	 * @generated
	 */
	EClass getValueObjectProperty();

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
	 * Returns the meta object for class '{@link org.nasdanika.ncore.DoubleProperty <em>Double Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Double Property</em>'.
	 * @see org.nasdanika.ncore.DoubleProperty
	 * @generated
	 */
	EClass getDoubleProperty();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.LongProperty <em>Long Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Long Property</em>'.
	 * @see org.nasdanika.ncore.LongProperty
	 * @generated
	 */
	EClass getLongProperty();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.DateProperty <em>Date Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Date Property</em>'.
	 * @see org.nasdanika.ncore.DateProperty
	 * @generated
	 */
	EClass getDateProperty();

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
	 * Returns the meta object for class '{@link org.nasdanika.ncore.BooleanProperty <em>Boolean Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Property</em>'.
	 * @see org.nasdanika.ncore.BooleanProperty
	 * @generated
	 */
	EClass getBooleanProperty();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.EObjectProperty <em>EObject Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>EObject Property</em>'.
	 * @see org.nasdanika.ncore.EObjectProperty
	 * @generated
	 */
	EClass getEObjectProperty();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.ncore.EObjectProperty#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see org.nasdanika.ncore.EObjectProperty#getValue()
	 * @see #getEObjectProperty()
	 * @generated
	 */
	EReference getEObjectProperty_Value();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>String Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Entry</em>'.
	 * @see java.util.Map.Entry
	 * @model keyDataType="org.eclipse.emf.ecore.EString" keyRequired="true"
	 *        valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getStringEntry();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringEntry()
	 * @generated
	 */
	EAttribute getStringEntry_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getStringEntry()
	 * @generated
	 */
	EAttribute getStringEntry_Value();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.GitMarker <em>Git Marker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Git Marker</em>'.
	 * @see org.nasdanika.ncore.GitMarker
	 * @generated
	 */
	EClass getGitMarker();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.GitMarker#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see org.nasdanika.ncore.GitMarker#getPath()
	 * @see #getGitMarker()
	 * @generated
	 */
	EAttribute getGitMarker_Path();

	/**
	 * Returns the meta object for the map '{@link org.nasdanika.ncore.GitMarker#getRemotes <em>Remotes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Remotes</em>'.
	 * @see org.nasdanika.ncore.GitMarker#getRemotes()
	 * @see #getGitMarker()
	 * @generated
	 */
	EReference getGitMarker_Remotes();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.GitMarker#getBranch <em>Branch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Branch</em>'.
	 * @see org.nasdanika.ncore.GitMarker#getBranch()
	 * @see #getGitMarker()
	 * @generated
	 */
	EAttribute getGitMarker_Branch();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.GitMarker#getHead <em>Head</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Head</em>'.
	 * @see org.nasdanika.ncore.GitMarker#getHead()
	 * @see #getGitMarker()
	 * @generated
	 */
	EAttribute getGitMarker_Head();

	/**
	 * Returns the meta object for the attribute list '{@link org.nasdanika.ncore.GitMarker#getHeadRefs <em>Head Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Head Refs</em>'.
	 * @see org.nasdanika.ncore.GitMarker#getHeadRefs()
	 * @see #getGitMarker()
	 * @generated
	 */
	EAttribute getGitMarker_HeadRefs();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Documented <em>Documented</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documented</em>'.
	 * @see org.nasdanika.ncore.Documented
	 * @generated
	 */
	EClass getDocumented();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.Documented#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Documentation</em>'.
	 * @see org.nasdanika.ncore.Documented#getDocumentation()
	 * @see #getDocumented()
	 * @generated
	 */
	EReference getDocumented_Documentation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.Documented#getContextHelp <em>Context Help</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Context Help</em>'.
	 * @see org.nasdanika.ncore.Documented#getContextHelp()
	 * @see #getDocumented()
	 * @generated
	 */
	EReference getDocumented_ContextHelp();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.DocumentedNamedElement <em>Documented Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documented Named Element</em>'.
	 * @see org.nasdanika.ncore.DocumentedNamedElement
	 * @generated
	 */
	EClass getDocumentedNamedElement();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Composite <em>Composite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Composite</em>'.
	 * @see org.nasdanika.ncore.Composite
	 * @generated
	 */
	EClass getComposite();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Composite#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.nasdanika.ncore.Composite#getId()
	 * @see #getComposite()
	 * @generated
	 */
	EAttribute getComposite_Id();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.Composite#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
	 * @see org.nasdanika.ncore.Composite#getChildren()
	 * @see #getComposite()
	 * @generated
	 */
	EReference getComposite_Children();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.StringIdentity <em>String Identity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Identity</em>'.
	 * @see org.nasdanika.ncore.StringIdentity
	 * @generated
	 */
	EClass getStringIdentity();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.StringIdentity#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.nasdanika.ncore.StringIdentity#getId()
	 * @see #getStringIdentity()
	 * @generated
	 */
	EAttribute getStringIdentity_Id();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.DocumentedNamedStringIdentity <em>Documented Named String Identity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documented Named String Identity</em>'.
	 * @see org.nasdanika.ncore.DocumentedNamedStringIdentity
	 * @generated
	 */
	EClass getDocumentedNamedStringIdentity();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.TreeItem <em>Tree Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tree Item</em>'.
	 * @see org.nasdanika.ncore.TreeItem
	 * @generated
	 */
	EClass getTreeItem();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.TreeItem#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.nasdanika.ncore.TreeItem#getName()
	 * @see #getTreeItem()
	 * @generated
	 */
	EAttribute getTreeItem_Name();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Tree <em>Tree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tree</em>'.
	 * @see org.nasdanika.ncore.Tree
	 * @generated
	 */
	EClass getTree();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.Tree#getTreeItems <em>Tree Items</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tree Items</em>'.
	 * @see org.nasdanika.ncore.Tree#getTreeItems()
	 * @see #getTree()
	 * @generated
	 */
	EReference getTree_TreeItems();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.File <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>File</em>'.
	 * @see org.nasdanika.ncore.File
	 * @generated
	 */
	EClass getFile();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.File#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see org.nasdanika.ncore.File#getLength()
	 * @see #getFile()
	 * @generated
	 */
	EAttribute getFile_Length();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.File#getLastModified <em>Last Modified</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Modified</em>'.
	 * @see org.nasdanika.ncore.File#getLastModified()
	 * @see #getFile()
	 * @generated
	 */
	EAttribute getFile_LastModified();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Directory <em>Directory</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Directory</em>'.
	 * @see org.nasdanika.ncore.Directory
	 * @generated
	 */
	EClass getDirectory();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.Throwable <em>Throwable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Throwable</em>'.
	 * @see org.nasdanika.ncore.Throwable
	 * @generated
	 */
	EClass getThrowable();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Throwable#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.nasdanika.ncore.Throwable#getType()
	 * @see #getThrowable()
	 * @generated
	 */
	EAttribute getThrowable_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.Throwable#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see org.nasdanika.ncore.Throwable#getMessage()
	 * @see #getThrowable()
	 * @generated
	 */
	EAttribute getThrowable_Message();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.Throwable#getStackTrace <em>Stack Trace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Stack Trace</em>'.
	 * @see org.nasdanika.ncore.Throwable#getStackTrace()
	 * @see #getThrowable()
	 * @generated
	 */
	EReference getThrowable_StackTrace();

	/**
	 * Returns the meta object for the containment reference list '{@link org.nasdanika.ncore.Throwable#getSupressed <em>Supressed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Supressed</em>'.
	 * @see org.nasdanika.ncore.Throwable#getSupressed()
	 * @see #getThrowable()
	 * @generated
	 */
	EReference getThrowable_Supressed();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.ncore.Throwable#getCause <em>Cause</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Cause</em>'.
	 * @see org.nasdanika.ncore.Throwable#getCause()
	 * @see #getThrowable()
	 * @generated
	 */
	EReference getThrowable_Cause();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.StackTraceElement <em>Stack Trace Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stack Trace Element</em>'.
	 * @see org.nasdanika.ncore.StackTraceElement
	 * @generated
	 */
	EClass getStackTraceElement();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.StackTraceElement#getClassName <em>Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Class Name</em>'.
	 * @see org.nasdanika.ncore.StackTraceElement#getClassName()
	 * @see #getStackTraceElement()
	 * @generated
	 */
	EAttribute getStackTraceElement_ClassName();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.StackTraceElement#getFileName <em>File Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Name</em>'.
	 * @see org.nasdanika.ncore.StackTraceElement#getFileName()
	 * @see #getStackTraceElement()
	 * @generated
	 */
	EAttribute getStackTraceElement_FileName();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.StackTraceElement#getMethodName <em>Method Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Method Name</em>'.
	 * @see org.nasdanika.ncore.StackTraceElement#getMethodName()
	 * @see #getStackTraceElement()
	 * @generated
	 */
	EAttribute getStackTraceElement_MethodName();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.StackTraceElement#getLineNumber <em>Line Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Number</em>'.
	 * @see org.nasdanika.ncore.StackTraceElement#getLineNumber()
	 * @see #getStackTraceElement()
	 * @generated
	 */
	EAttribute getStackTraceElement_LineNumber();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.ncore.StackTraceElement#isNative <em>Native</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Native</em>'.
	 * @see org.nasdanika.ncore.StackTraceElement#isNative()
	 * @see #getStackTraceElement()
	 * @generated
	 */
	EAttribute getStackTraceElement_Native();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.ncore.TreeItemReference <em>Tree Item Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tree Item Reference</em>'.
	 * @see org.nasdanika.ncore.TreeItemReference
	 * @generated
	 */
	EClass getTreeItemReference();

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
		 * The meta object literal for the '{@link org.nasdanika.persistence.Marked <em>IMarked</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.persistence.Marked
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
		 * The meta object literal for the '<em><b>Markers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MARKED__MARKERS = eINSTANCE.getMarked_Markers();

		/**
		 * The meta object literal for the '{@link org.nasdanika.persistence.Marker <em>IMarker</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.persistence.Marker
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
		 * The meta object literal for the '<em><b>Position</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MARKER__POSITION = eINSTANCE.getMarker_Position();

		/**
		 * The meta object literal for the '<em><b>Comment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MARKER__COMMENT = eINSTANCE.getMarker_Comment();

		/**
		 * The meta object literal for the '<em><b>Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MARKER__DATE = eINSTANCE.getMarker_Date();

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
		 * The meta object literal for the '<em><b>Uris</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL_ELEMENT__URIS = eINSTANCE.getModelElement_Uris();

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
		 * The meta object literal for the '<em><b>Label Prototype</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_ELEMENT__LABEL_PROTOTYPE = eINSTANCE.getModelElement_LabelPrototype();

		/**
		 * The meta object literal for the '<em><b>Representations</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_ELEMENT__REPRESENTATIONS = eINSTANCE.getModelElement_Representations();

		/**
		 * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL_ELEMENT__ANNOTATIONS = eINSTANCE.getModelElement_Annotations();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.RepresentationEntryImpl <em>Representation Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.RepresentationEntryImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getRepresentationEntry()
		 * @generated
		 */
		EClass REPRESENTATION_ENTRY = eINSTANCE.getRepresentationEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPRESENTATION_ENTRY__KEY = eINSTANCE.getRepresentationEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REPRESENTATION_ENTRY__VALUE = eINSTANCE.getRepresentationEntry_Value();

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
		 * The meta object literal for the '{@link org.nasdanika.ncore.ValueObject <em>Value Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.ValueObject
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getValueObject()
		 * @generated
		 */
		EClass VALUE_OBJECT = eINSTANCE.getValueObject();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VALUE_OBJECT__VALUE = eINSTANCE.getValueObject_Value();

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
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.DoubleImpl <em>Double</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.DoubleImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDouble()
		 * @generated
		 */
		EClass DOUBLE = eINSTANCE.getDouble();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.DateImpl <em>Date</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.DateImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDate()
		 * @generated
		 */
		EClass DATE = eINSTANCE.getDate();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.LongImpl <em>Long</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.LongImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getLong()
		 * @generated
		 */
		EClass LONG = eINSTANCE.getLong();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.BooleanImpl <em>Boolean</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.BooleanImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getBoolean()
		 * @generated
		 */
		EClass BOOLEAN = eINSTANCE.getBoolean();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.Property <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.Property
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
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.ValueObjectPropertyImpl <em>Value Object Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.ValueObjectPropertyImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getValueObjectProperty()
		 * @generated
		 */
		EClass VALUE_OBJECT_PROPERTY = eINSTANCE.getValueObjectProperty();

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
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.DoublePropertyImpl <em>Double Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.DoublePropertyImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDoubleProperty()
		 * @generated
		 */
		EClass DOUBLE_PROPERTY = eINSTANCE.getDoubleProperty();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.LongPropertyImpl <em>Long Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.LongPropertyImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getLongProperty()
		 * @generated
		 */
		EClass LONG_PROPERTY = eINSTANCE.getLongProperty();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.DatePropertyImpl <em>Date Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.DatePropertyImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDateProperty()
		 * @generated
		 */
		EClass DATE_PROPERTY = eINSTANCE.getDateProperty();

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
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.BooleanPropertyImpl <em>Boolean Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.BooleanPropertyImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getBooleanProperty()
		 * @generated
		 */
		EClass BOOLEAN_PROPERTY = eINSTANCE.getBooleanProperty();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.EObjectPropertyImpl <em>EObject Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.EObjectPropertyImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getEObjectProperty()
		 * @generated
		 */
		EClass EOBJECT_PROPERTY = eINSTANCE.getEObjectProperty();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EOBJECT_PROPERTY__VALUE = eINSTANCE.getEObjectProperty_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.StringEntryImpl <em>String Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.StringEntryImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getStringEntry()
		 * @generated
		 */
		EClass STRING_ENTRY = eINSTANCE.getStringEntry();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_ENTRY__KEY = eINSTANCE.getStringEntry_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_ENTRY__VALUE = eINSTANCE.getStringEntry_Value();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.GitMarkerImpl <em>Git Marker</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.GitMarkerImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getGitMarker()
		 * @generated
		 */
		EClass GIT_MARKER = eINSTANCE.getGitMarker();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_MARKER__PATH = eINSTANCE.getGitMarker_Path();

		/**
		 * The meta object literal for the '<em><b>Remotes</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GIT_MARKER__REMOTES = eINSTANCE.getGitMarker_Remotes();

		/**
		 * The meta object literal for the '<em><b>Branch</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_MARKER__BRANCH = eINSTANCE.getGitMarker_Branch();

		/**
		 * The meta object literal for the '<em><b>Head</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_MARKER__HEAD = eINSTANCE.getGitMarker_Head();

		/**
		 * The meta object literal for the '<em><b>Head Refs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GIT_MARKER__HEAD_REFS = eINSTANCE.getGitMarker_HeadRefs();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.Documented <em>Documented</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.Documented
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDocumented()
		 * @generated
		 */
		EClass DOCUMENTED = eINSTANCE.getDocumented();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENTED__DOCUMENTATION = eINSTANCE.getDocumented_Documentation();

		/**
		 * The meta object literal for the '<em><b>Context Help</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENTED__CONTEXT_HELP = eINSTANCE.getDocumented_ContextHelp();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.DocumentedNamedElementImpl <em>Documented Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.DocumentedNamedElementImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDocumentedNamedElement()
		 * @generated
		 */
		EClass DOCUMENTED_NAMED_ELEMENT = eINSTANCE.getDocumentedNamedElement();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.CompositeImpl <em>Composite</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.CompositeImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getComposite()
		 * @generated
		 */
		EClass COMPOSITE = eINSTANCE.getComposite();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPOSITE__ID = eINSTANCE.getComposite_Id();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPOSITE__CHILDREN = eINSTANCE.getComposite_Children();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.StringIdentityImpl <em>String Identity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.StringIdentityImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getStringIdentity()
		 * @generated
		 */
		EClass STRING_IDENTITY = eINSTANCE.getStringIdentity();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_IDENTITY__ID = eINSTANCE.getStringIdentity_Id();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.DocumentedNamedStringIdentityImpl <em>Documented Named String Identity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.DocumentedNamedStringIdentityImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDocumentedNamedStringIdentity()
		 * @generated
		 */
		EClass DOCUMENTED_NAMED_STRING_IDENTITY = eINSTANCE.getDocumentedNamedStringIdentity();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.TreeItemImpl <em>Tree Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.TreeItemImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getTreeItem()
		 * @generated
		 */
		EClass TREE_ITEM = eINSTANCE.getTreeItem();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TREE_ITEM__NAME = eINSTANCE.getTreeItem_Name();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.TreeImpl <em>Tree</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.TreeImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getTree()
		 * @generated
		 */
		EClass TREE = eINSTANCE.getTree();

		/**
		 * The meta object literal for the '<em><b>Tree Items</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TREE__TREE_ITEMS = eINSTANCE.getTree_TreeItems();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.FileImpl <em>File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.FileImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getFile()
		 * @generated
		 */
		EClass FILE = eINSTANCE.getFile();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE__LENGTH = eINSTANCE.getFile_Length();

		/**
		 * The meta object literal for the '<em><b>Last Modified</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FILE__LAST_MODIFIED = eINSTANCE.getFile_LastModified();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.DirectoryImpl <em>Directory</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.DirectoryImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getDirectory()
		 * @generated
		 */
		EClass DIRECTORY = eINSTANCE.getDirectory();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.ThrowableImpl <em>Throwable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.ThrowableImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getThrowable()
		 * @generated
		 */
		EClass THROWABLE = eINSTANCE.getThrowable();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute THROWABLE__TYPE = eINSTANCE.getThrowable_Type();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute THROWABLE__MESSAGE = eINSTANCE.getThrowable_Message();

		/**
		 * The meta object literal for the '<em><b>Stack Trace</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference THROWABLE__STACK_TRACE = eINSTANCE.getThrowable_StackTrace();

		/**
		 * The meta object literal for the '<em><b>Supressed</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference THROWABLE__SUPRESSED = eINSTANCE.getThrowable_Supressed();

		/**
		 * The meta object literal for the '<em><b>Cause</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference THROWABLE__CAUSE = eINSTANCE.getThrowable_Cause();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.StackTraceElementImpl <em>Stack Trace Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.StackTraceElementImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getStackTraceElement()
		 * @generated
		 */
		EClass STACK_TRACE_ELEMENT = eINSTANCE.getStackTraceElement();

		/**
		 * The meta object literal for the '<em><b>Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STACK_TRACE_ELEMENT__CLASS_NAME = eINSTANCE.getStackTraceElement_ClassName();

		/**
		 * The meta object literal for the '<em><b>File Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STACK_TRACE_ELEMENT__FILE_NAME = eINSTANCE.getStackTraceElement_FileName();

		/**
		 * The meta object literal for the '<em><b>Method Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STACK_TRACE_ELEMENT__METHOD_NAME = eINSTANCE.getStackTraceElement_MethodName();

		/**
		 * The meta object literal for the '<em><b>Line Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STACK_TRACE_ELEMENT__LINE_NUMBER = eINSTANCE.getStackTraceElement_LineNumber();

		/**
		 * The meta object literal for the '<em><b>Native</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STACK_TRACE_ELEMENT__NATIVE = eINSTANCE.getStackTraceElement_Native();

		/**
		 * The meta object literal for the '{@link org.nasdanika.ncore.impl.TreeItemReferenceImpl <em>Tree Item Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.ncore.impl.TreeItemReferenceImpl
		 * @see org.nasdanika.ncore.impl.NcorePackageImpl#getTreeItemReference()
		 * @generated
		 */
		EClass TREE_ITEM_REFERENCE = eINSTANCE.getTreeItemReference();

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
