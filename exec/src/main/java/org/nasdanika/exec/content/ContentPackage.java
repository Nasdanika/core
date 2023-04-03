/**
 */
package org.nasdanika.exec.content;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.ncore.NcorePackage;

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
 * @see org.nasdanika.exec.content.ContentFactory
 * @model kind="package"
 *        annotation="urn:org.nasdanika documentation-reference='doc/content/package-summary.md'"
 * @generated
 */
public interface ContentPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "content";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "ecore://nasdanika.org/core/exec/content";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.nasdanika.exec.content";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ContentPackage eINSTANCE = org.nasdanika.exec.content.impl.ContentPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.nasdanika.exec.content.impl.Base64Impl <em>Base64</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.exec.content.impl.Base64Impl
	 * @see org.nasdanika.exec.content.impl.ContentPackageImpl#getBase64()
	 * @generated
	 */
	int BASE64 = 1;

	/**
	 * The meta object id for the '{@link org.nasdanika.exec.content.impl.ResourceImpl <em>Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.exec.content.impl.ResourceImpl
	 * @see org.nasdanika.exec.content.impl.ContentPackageImpl#getResource()
	 * @generated
	 */
	int RESOURCE = 2;

	/**
	 * The meta object id for the '{@link org.nasdanika.exec.content.impl.TextImpl <em>Text</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.exec.content.impl.TextImpl
	 * @see org.nasdanika.exec.content.impl.ContentPackageImpl#getText()
	 * @generated
	 */
	int TEXT = 3;

	/**
	 * The meta object id for the '{@link org.nasdanika.exec.content.impl.FilterImpl <em>Filter</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.exec.content.impl.FilterImpl
	 * @see org.nasdanika.exec.content.impl.ContentPackageImpl#getFilter()
	 * @generated
	 */
	int FILTER = 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__MARKERS = NcorePackage.MODEL_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__URIS = NcorePackage.MODEL_ELEMENT__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__UUID = NcorePackage.MODEL_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Action Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__ACTION_PROTOTYPE = NcorePackage.MODEL_ELEMENT__ACTION_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__REPRESENTATIONS = NcorePackage.MODEL_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__ANNOTATIONS = NcorePackage.MODEL_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER__SOURCE = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Filter</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FILTER_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE64__MARKERS = FILTER__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE64__URIS = FILTER__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE64__DESCRIPTION = FILTER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE64__UUID = FILTER__UUID;

	/**
	 * The feature id for the '<em><b>Action Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE64__ACTION_PROTOTYPE = FILTER__ACTION_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE64__REPRESENTATIONS = FILTER__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE64__ANNOTATIONS = FILTER__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE64__SOURCE = FILTER__SOURCE;

	/**
	 * The number of structural features of the '<em>Base64</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE64_FEATURE_COUNT = FILTER_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Base64</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BASE64_OPERATION_COUNT = FILTER_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__MARKERS = NcorePackage.MODEL_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__URIS = NcorePackage.MODEL_ELEMENT__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__UUID = NcorePackage.MODEL_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Action Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__ACTION_PROTOTYPE = NcorePackage.MODEL_ELEMENT__ACTION_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__REPRESENTATIONS = NcorePackage.MODEL_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__ANNOTATIONS = NcorePackage.MODEL_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__LOCATION = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Interpolate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__INTERPOLATE = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__MARKERS = NcorePackage.MODEL_ELEMENT__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__URIS = NcorePackage.MODEL_ELEMENT__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__DESCRIPTION = NcorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__UUID = NcorePackage.MODEL_ELEMENT__UUID;

	/**
	 * The feature id for the '<em><b>Action Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__ACTION_PROTOTYPE = NcorePackage.MODEL_ELEMENT__ACTION_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__REPRESENTATIONS = NcorePackage.MODEL_ELEMENT__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__ANNOTATIONS = NcorePackage.MODEL_ELEMENT__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__CONTENT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Interpolate</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT__INTERPOLATE = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Text</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_FEATURE_COUNT = NcorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Text</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEXT_OPERATION_COUNT = NcorePackage.MODEL_ELEMENT_OPERATION_COUNT + 0;


	/**
	 * The meta object id for the '{@link org.nasdanika.exec.content.impl.InterpolatorImpl <em>Interpolator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.exec.content.impl.InterpolatorImpl
	 * @see org.nasdanika.exec.content.impl.ContentPackageImpl#getInterpolator()
	 * @generated
	 */
	int INTERPOLATOR = 4;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERPOLATOR__MARKERS = FILTER__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERPOLATOR__URIS = FILTER__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERPOLATOR__DESCRIPTION = FILTER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERPOLATOR__UUID = FILTER__UUID;

	/**
	 * The feature id for the '<em><b>Action Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERPOLATOR__ACTION_PROTOTYPE = FILTER__ACTION_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERPOLATOR__REPRESENTATIONS = FILTER__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERPOLATOR__ANNOTATIONS = FILTER__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERPOLATOR__SOURCE = FILTER__SOURCE;

	/**
	 * The feature id for the '<em><b>Process Includes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERPOLATOR__PROCESS_INCLUDES = FILTER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Base</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERPOLATOR__BASE = FILTER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Interpolator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERPOLATOR_FEATURE_COUNT = FILTER_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Interpolator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERPOLATOR_OPERATION_COUNT = FILTER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.nasdanika.exec.content.impl.MarkdownImpl <em>Markdown</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.nasdanika.exec.content.impl.MarkdownImpl
	 * @see org.nasdanika.exec.content.impl.ContentPackageImpl#getMarkdown()
	 * @generated
	 */
	int MARKDOWN = 5;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKDOWN__MARKERS = FILTER__MARKERS;

	/**
	 * The feature id for the '<em><b>Uris</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKDOWN__URIS = FILTER__URIS;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKDOWN__DESCRIPTION = FILTER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKDOWN__UUID = FILTER__UUID;

	/**
	 * The feature id for the '<em><b>Action Prototype</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKDOWN__ACTION_PROTOTYPE = FILTER__ACTION_PROTOTYPE;

	/**
	 * The feature id for the '<em><b>Representations</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKDOWN__REPRESENTATIONS = FILTER__REPRESENTATIONS;

	/**
	 * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKDOWN__ANNOTATIONS = FILTER__ANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKDOWN__SOURCE = FILTER__SOURCE;

	/**
	 * The feature id for the '<em><b>Style</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKDOWN__STYLE = FILTER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Markdown</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKDOWN_FEATURE_COUNT = FILTER_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Markdown</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKDOWN_OPERATION_COUNT = FILTER_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link org.nasdanika.exec.content.Base64 <em>Base64</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Base64</em>'.
	 * @see org.nasdanika.exec.content.Base64
	 * @generated
	 */
	EClass getBase64();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.exec.content.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource</em>'.
	 * @see org.nasdanika.exec.content.Resource
	 * @generated
	 */
	EClass getResource();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.exec.content.Resource#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see org.nasdanika.exec.content.Resource#getLocation()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Location();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.exec.content.Resource#isInterpolate <em>Interpolate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interpolate</em>'.
	 * @see org.nasdanika.exec.content.Resource#isInterpolate()
	 * @see #getResource()
	 * @generated
	 */
	EAttribute getResource_Interpolate();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.exec.content.Text <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Text</em>'.
	 * @see org.nasdanika.exec.content.Text
	 * @generated
	 */
	EClass getText();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.exec.content.Text#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Content</em>'.
	 * @see org.nasdanika.exec.content.Text#getContent()
	 * @see #getText()
	 * @generated
	 */
	EAttribute getText_Content();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.exec.content.Text#isInterpolate <em>Interpolate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interpolate</em>'.
	 * @see org.nasdanika.exec.content.Text#isInterpolate()
	 * @see #getText()
	 * @generated
	 */
	EAttribute getText_Interpolate();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.exec.content.Filter <em>Filter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Filter</em>'.
	 * @see org.nasdanika.exec.content.Filter
	 * @generated
	 */
	EClass getFilter();

	/**
	 * Returns the meta object for the containment reference '{@link org.nasdanika.exec.content.Filter#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Source</em>'.
	 * @see org.nasdanika.exec.content.Filter#getSource()
	 * @see #getFilter()
	 * @generated
	 */
	EReference getFilter_Source();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.exec.content.Interpolator <em>Interpolator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interpolator</em>'.
	 * @see org.nasdanika.exec.content.Interpolator
	 * @generated
	 */
	EClass getInterpolator();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.exec.content.Interpolator#isProcessIncludes <em>Process Includes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Process Includes</em>'.
	 * @see org.nasdanika.exec.content.Interpolator#isProcessIncludes()
	 * @see #getInterpolator()
	 * @generated
	 */
	EAttribute getInterpolator_ProcessIncludes();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.exec.content.Interpolator#getBase <em>Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Base</em>'.
	 * @see org.nasdanika.exec.content.Interpolator#getBase()
	 * @see #getInterpolator()
	 * @generated
	 */
	EAttribute getInterpolator_Base();

	/**
	 * Returns the meta object for class '{@link org.nasdanika.exec.content.Markdown <em>Markdown</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Markdown</em>'.
	 * @see org.nasdanika.exec.content.Markdown
	 * @generated
	 */
	EClass getMarkdown();

	/**
	 * Returns the meta object for the attribute '{@link org.nasdanika.exec.content.Markdown#isStyle <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Style</em>'.
	 * @see org.nasdanika.exec.content.Markdown#isStyle()
	 * @see #getMarkdown()
	 * @generated
	 */
	EAttribute getMarkdown_Style();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ContentFactory getContentFactory();

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
		 * The meta object literal for the '{@link org.nasdanika.exec.content.impl.Base64Impl <em>Base64</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.exec.content.impl.Base64Impl
		 * @see org.nasdanika.exec.content.impl.ContentPackageImpl#getBase64()
		 * @generated
		 */
		EClass BASE64 = eINSTANCE.getBase64();

		/**
		 * The meta object literal for the '{@link org.nasdanika.exec.content.impl.ResourceImpl <em>Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.exec.content.impl.ResourceImpl
		 * @see org.nasdanika.exec.content.impl.ContentPackageImpl#getResource()
		 * @generated
		 */
		EClass RESOURCE = eINSTANCE.getResource();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE__LOCATION = eINSTANCE.getResource_Location();

		/**
		 * The meta object literal for the '<em><b>Interpolate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE__INTERPOLATE = eINSTANCE.getResource_Interpolate();

		/**
		 * The meta object literal for the '{@link org.nasdanika.exec.content.impl.TextImpl <em>Text</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.exec.content.impl.TextImpl
		 * @see org.nasdanika.exec.content.impl.ContentPackageImpl#getText()
		 * @generated
		 */
		EClass TEXT = eINSTANCE.getText();

		/**
		 * The meta object literal for the '<em><b>Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT__CONTENT = eINSTANCE.getText_Content();

		/**
		 * The meta object literal for the '<em><b>Interpolate</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEXT__INTERPOLATE = eINSTANCE.getText_Interpolate();

		/**
		 * The meta object literal for the '{@link org.nasdanika.exec.content.impl.FilterImpl <em>Filter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.exec.content.impl.FilterImpl
		 * @see org.nasdanika.exec.content.impl.ContentPackageImpl#getFilter()
		 * @generated
		 */
		EClass FILTER = eINSTANCE.getFilter();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FILTER__SOURCE = eINSTANCE.getFilter_Source();

		/**
		 * The meta object literal for the '{@link org.nasdanika.exec.content.impl.InterpolatorImpl <em>Interpolator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.exec.content.impl.InterpolatorImpl
		 * @see org.nasdanika.exec.content.impl.ContentPackageImpl#getInterpolator()
		 * @generated
		 */
		EClass INTERPOLATOR = eINSTANCE.getInterpolator();

		/**
		 * The meta object literal for the '<em><b>Process Includes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERPOLATOR__PROCESS_INCLUDES = eINSTANCE.getInterpolator_ProcessIncludes();

		/**
		 * The meta object literal for the '<em><b>Base</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERPOLATOR__BASE = eINSTANCE.getInterpolator_Base();

		/**
		 * The meta object literal for the '{@link org.nasdanika.exec.content.impl.MarkdownImpl <em>Markdown</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.nasdanika.exec.content.impl.MarkdownImpl
		 * @see org.nasdanika.exec.content.impl.ContentPackageImpl#getMarkdown()
		 * @generated
		 */
		EClass MARKDOWN = eINSTANCE.getMarkdown();

		/**
		 * The meta object literal for the '<em><b>Style</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MARKDOWN__STYLE = eINSTANCE.getMarkdown_Style();

	}

} //ContentPackage
