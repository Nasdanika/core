/**
 */
package org.nasdanika.exec.content.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nasdanika.exec.ExecPackage;

import org.nasdanika.exec.content.Base64;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.ContentPackage;
import org.nasdanika.exec.content.Filter;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Resource;

import org.nasdanika.exec.content.Text;
import org.nasdanika.exec.impl.ExecPackageImpl;
import org.nasdanika.exec.resources.ResourcesPackage;
import org.nasdanika.exec.resources.impl.ResourcesPackageImpl;
import org.nasdanika.ncore.NcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ContentPackageImpl extends EPackageImpl implements ContentPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass base64EClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass resourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass textEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass filterEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass interpolatorEClass = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass markdownEClass = null;

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
	 * @see org.nasdanika.exec.content.ContentPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ContentPackageImpl() {
		super(eNS_URI, ContentFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ContentPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ContentPackage init() {
		if (isInited) return (ContentPackage)EPackage.Registry.INSTANCE.getEPackage(ContentPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredContentPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ContentPackageImpl theContentPackage = registeredContentPackage instanceof ContentPackageImpl ? (ContentPackageImpl)registeredContentPackage : new ContentPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		NcorePackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ExecPackage.eNS_URI);
		ExecPackageImpl theExecPackage = (ExecPackageImpl)(registeredPackage instanceof ExecPackageImpl ? registeredPackage : ExecPackage.eINSTANCE);
		registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ResourcesPackage.eNS_URI);
		ResourcesPackageImpl theResourcesPackage = (ResourcesPackageImpl)(registeredPackage instanceof ResourcesPackageImpl ? registeredPackage : ResourcesPackage.eINSTANCE);

		// Create package meta-data objects
		theContentPackage.createPackageContents();
		theExecPackage.createPackageContents();
		theResourcesPackage.createPackageContents();

		// Initialize created meta-data
		theContentPackage.initializePackageContents();
		theExecPackage.initializePackageContents();
		theResourcesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theContentPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ContentPackage.eNS_URI, theContentPackage);
		return theContentPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBase64() {
		return base64EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBase64_Sources() {
		return (EReference)base64EClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getResource() {
		return resourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getResource_Location() {
		return (EAttribute)resourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getResource_Interpolate() {
		return (EAttribute)resourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getText() {
		return textEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getText_Content() {
		return (EAttribute)textEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getText_Interpolate() {
		return (EAttribute)textEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFilter() {
		return filterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getFilter_Source() {
		return (EReference)filterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getInterpolator() {
		return interpolatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInterpolator_ProcessIncludes() {
		return (EAttribute)interpolatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getInterpolator_Base() {
		return (EAttribute)interpolatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMarkdown() {
		return markdownEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMarkdown_Style() {
		return (EAttribute)markdownEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContentFactory getContentFactory() {
		return (ContentFactory)getEFactoryInstance();
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
		base64EClass = createEClass(BASE64);
		createEReference(base64EClass, BASE64__SOURCES);

		resourceEClass = createEClass(RESOURCE);
		createEAttribute(resourceEClass, RESOURCE__LOCATION);
		createEAttribute(resourceEClass, RESOURCE__INTERPOLATE);

		textEClass = createEClass(TEXT);
		createEAttribute(textEClass, TEXT__CONTENT);
		createEAttribute(textEClass, TEXT__INTERPOLATE);

		filterEClass = createEClass(FILTER);
		createEReference(filterEClass, FILTER__SOURCE);

		interpolatorEClass = createEClass(INTERPOLATOR);
		createEAttribute(interpolatorEClass, INTERPOLATOR__PROCESS_INCLUDES);
		createEAttribute(interpolatorEClass, INTERPOLATOR__BASE);

		markdownEClass = createEClass(MARKDOWN);
		createEAttribute(markdownEClass, MARKDOWN__STYLE);
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

		// Obtain other dependent packages
		NcorePackage theNcorePackage = (NcorePackage)EPackage.Registry.INSTANCE.getEPackage(NcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		base64EClass.getESuperTypes().add(theNcorePackage.getModelElement());
		resourceEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		textEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		filterEClass.getESuperTypes().add(theNcorePackage.getModelElement());
		interpolatorEClass.getESuperTypes().add(this.getFilter());
		markdownEClass.getESuperTypes().add(this.getFilter());

		// Initialize classes, features, and operations; add parameters
		initEClass(base64EClass, Base64.class, "Base64", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBase64_Sources(), ecorePackage.getEObject(), null, "sources", null, 0, -1, Base64.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceEClass, Resource.class, "Resource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getResource_Location(), ecorePackage.getEString(), "location", null, 1, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResource_Interpolate(), ecorePackage.getEBoolean(), "interpolate", null, 0, 1, Resource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(textEClass, Text.class, "Text", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getText_Content(), ecorePackage.getEString(), "content", null, 1, 1, Text.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getText_Interpolate(), ecorePackage.getEBoolean(), "interpolate", "true", 0, 1, Text.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(filterEClass, Filter.class, "Filter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFilter_Source(), ecorePackage.getEObject(), null, "source", null, 1, 1, Filter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(interpolatorEClass, Interpolator.class, "Interpolator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getInterpolator_ProcessIncludes(), ecorePackage.getEBoolean(), "processIncludes", "true", 0, 1, Interpolator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getInterpolator_Base(), ecorePackage.getEString(), "base", "", 0, 1, Interpolator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(markdownEClass, Markdown.class, "Markdown", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMarkdown_Style(), ecorePackage.getEBoolean(), "style", null, 0, 1, Markdown.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
			   "documentation-reference", "doc/content/package-summary.md"
		   });
		addAnnotation
		  (base64EClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/content/base-64.md"
		   });
		addAnnotation
		  (resourceEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/content/resource.md"
		   });
		addAnnotation
		  (getResource_Location(),
		   source,
		   new String[] {
			   "default-feature", "true",
			   "resolve-uri", "true"
		   });
		addAnnotation
		  (textEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/content/text.md"
		   });
		addAnnotation
		  (getText_Content(),
		   source,
		   new String[] {
			   "default-feature", "true"
		   });
		addAnnotation
		  (getInterpolator_ProcessIncludes(),
		   source,
		   new String[] {
			   "documentation-reference", "doc/content/interpolator--process-includes.md"
		   });
		addAnnotation
		  (getInterpolator_Base(),
		   source,
		   new String[] {
			   "resolve-uri", "true"
		   });
		addAnnotation
		  (markdownEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/content/markdown.md"
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
		  (getResource_Location(),
		   source,
		   new String[] {
			   "documentation", "Resource location resolved relative to the model resource location."
		   });
		addAnnotation
		  (getResource_Interpolate(),
		   source,
		   new String[] {
			   "documentation", "If true, location is interpolated. Default is false."
		   });
		addAnnotation
		  (getText_Content(),
		   source,
		   new String[] {
			   "documentation", "Text content."
		   });
		addAnnotation
		  (getText_Interpolate(),
		   source,
		   new String[] {
			   "documentation", "If ``true`` (default), content is interpolated."
		   });
		addAnnotation
		  (interpolatorEClass,
		   source,
		   new String[] {
			   "documentation", "Converts input stream to String using context charset, which defaults to ``UTF-8``, interpolates, and converts back to input stream using context charset."
		   });
		addAnnotation
		  (getInterpolator_Base(),
		   source,
		   new String[] {
			   "documentation", "Optional base URL for resolving include and image URL\'s. \nIf the base is blank then marker location is used as the base, if marker is present and location is not null. \nOtherwise, resource URL is used as the base. \nMarker location is the URL of a YAML resource from which interpolator specification was loaded. \nIt may be different from the resource URL if a model was loaded from YAML and then saved to XML.\n\nIf the base is not blank then it is resolved relative to the marker location if it starts with ``./`` and marker location is not null or the resource base otherwise.\n"
		   });
	}

} //ContentPackageImpl
