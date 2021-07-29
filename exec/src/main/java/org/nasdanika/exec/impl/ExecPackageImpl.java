/**
 */
package org.nasdanika.exec.impl;

import java.util.Map;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.nasdanika.exec.Block;
import org.nasdanika.exec.Call;
import org.nasdanika.exec.Configurator;
import org.nasdanika.exec.Eval;
import org.nasdanika.exec.ExecFactory;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.exec.ModelElement;

import org.nasdanika.exec.content.ContentPackage;

import org.nasdanika.exec.content.impl.ContentPackageImpl;
import org.nasdanika.exec.util.ExecValidator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExecPackageImpl extends EPackageImpl implements ExecPackage {
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
	private EClass blockEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass callEClass = null;

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
	private EClass configuratorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass evalEClass = null;

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
	 * @see org.nasdanika.exec.ExecPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ExecPackageImpl() {
		super(eNS_URI, ExecFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link ExecPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ExecPackage init() {
		if (isInited) return (ExecPackage)EPackage.Registry.INSTANCE.getEPackage(ExecPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredExecPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		ExecPackageImpl theExecPackage = registeredExecPackage instanceof ExecPackageImpl ? (ExecPackageImpl)registeredExecPackage : new ExecPackageImpl();

		isInited = true;

		// Obtain or create and register interdependencies
		Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ContentPackage.eNS_URI);
		ContentPackageImpl theContentPackage = (ContentPackageImpl)(registeredPackage instanceof ContentPackageImpl ? registeredPackage : ContentPackage.eINSTANCE);

		// Create package meta-data objects
		theExecPackage.createPackageContents();
		theContentPackage.createPackageContents();

		// Initialize created meta-data
		theExecPackage.initializePackageContents();
		theContentPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theExecPackage,
			 new EValidator.Descriptor() {
				 @Override
				 public EValidator getEValidator() {
					 return ExecValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theExecPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ExecPackage.eNS_URI, theExecPackage);
		return theExecPackage;
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
	public EAttribute getModelElement_Description() {
		return (EAttribute)modelElementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBlock() {
		return blockEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBlock_Try() {
		return (EReference)blockEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBlock_Catch() {
		return (EReference)blockEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBlock_Finally() {
		return (EReference)blockEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCall() {
		return callEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCall_Type() {
		return (EAttribute)callEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCall_Property() {
		return (EAttribute)callEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCall_Service() {
		return (EAttribute)callEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCall_Method() {
		return (EAttribute)callEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCall_Properties() {
		return (EReference)callEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCall_Init() {
		return (EReference)callEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getCall_Arguments() {
		return (EReference)callEClass.getEStructuralFeatures().get(6);
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
	public EAttribute getProperty_Key() {
		return (EAttribute)propertyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProperty_Value() {
		return (EReference)propertyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConfigurator() {
		return configuratorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConfigurator_Target() {
		return (EReference)configuratorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConfigurator_Properties() {
		return (EReference)configuratorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEval() {
		return evalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEval_Script() {
		return (EReference)evalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEval_Bindings() {
		return (EReference)evalEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExecFactory getExecFactory() {
		return (ExecFactory)getEFactoryInstance();
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
		modelElementEClass = createEClass(MODEL_ELEMENT);
		createEAttribute(modelElementEClass, MODEL_ELEMENT__DESCRIPTION);

		blockEClass = createEClass(BLOCK);
		createEReference(blockEClass, BLOCK__TRY);
		createEReference(blockEClass, BLOCK__CATCH);
		createEReference(blockEClass, BLOCK__FINALLY);

		callEClass = createEClass(CALL);
		createEAttribute(callEClass, CALL__TYPE);
		createEAttribute(callEClass, CALL__PROPERTY);
		createEAttribute(callEClass, CALL__SERVICE);
		createEAttribute(callEClass, CALL__METHOD);
		createEReference(callEClass, CALL__PROPERTIES);
		createEReference(callEClass, CALL__INIT);
		createEReference(callEClass, CALL__ARGUMENTS);

		propertyEClass = createEClass(PROPERTY);
		createEAttribute(propertyEClass, PROPERTY__KEY);
		createEReference(propertyEClass, PROPERTY__VALUE);

		configuratorEClass = createEClass(CONFIGURATOR);
		createEReference(configuratorEClass, CONFIGURATOR__TARGET);
		createEReference(configuratorEClass, CONFIGURATOR__PROPERTIES);

		evalEClass = createEClass(EVAL);
		createEReference(evalEClass, EVAL__SCRIPT);
		createEReference(evalEClass, EVAL__BINDINGS);
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
		ContentPackage theContentPackage = (ContentPackage)EPackage.Registry.INSTANCE.getEPackage(ContentPackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theContentPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		blockEClass.getESuperTypes().add(this.getModelElement());
		callEClass.getESuperTypes().add(this.getModelElement());
		configuratorEClass.getESuperTypes().add(this.getModelElement());
		evalEClass.getESuperTypes().add(this.getModelElement());

		// Initialize classes, features, and operations; add parameters
		initEClass(modelElementEClass, ModelElement.class, "ModelElement", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getModelElement_Description(), ecorePackage.getEString(), "description", null, 0, 1, ModelElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(blockEClass, Block.class, "Block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBlock_Try(), ecorePackage.getEObject(), null, "try", null, 1, -1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBlock_Catch(), ecorePackage.getEObject(), null, "catch", null, 0, -1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBlock_Finally(), ecorePackage.getEObject(), null, "finally", null, 0, -1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(callEClass, Call.class, "Call", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCall_Type(), ecorePackage.getEString(), "type", null, 0, 1, Call.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCall_Property(), ecorePackage.getEString(), "property", null, 0, 1, Call.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCall_Service(), ecorePackage.getEString(), "service", null, 0, 1, Call.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCall_Method(), ecorePackage.getEString(), "method", null, 0, 1, Call.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCall_Properties(), this.getProperty(), null, "properties", null, 0, -1, Call.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCall_Init(), ecorePackage.getEObject(), null, "init", null, 0, -1, Call.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCall_Arguments(), ecorePackage.getEObject(), null, "arguments", null, 0, -1, Call.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertyEClass, Map.Entry.class, "Property", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProperty_Key(), ecorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProperty_Value(), ecorePackage.getEObject(), null, "value", null, 1, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(configuratorEClass, Configurator.class, "Configurator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConfigurator_Target(), ecorePackage.getEObject(), null, "target", null, 1, 1, Configurator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConfigurator_Properties(), this.getProperty(), null, "properties", null, 0, -1, Configurator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(evalEClass, Eval.class, "Eval", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getEval_Script(), ecorePackage.getEObject(), null, "script", null, 1, 1, Eval.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getEval_Bindings(), this.getProperty(), null, "bindings", null, 0, -1, Eval.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		  (modelElementEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/model-element.md"
		   });
		addAnnotation
		  (blockEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/block.md"
		   });
		addAnnotation
		  (callEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/call.md"
		   });
		addAnnotation
		  (getCall_Type(),
		   source,
		   new String[] {
			   "load-key", "class",
			   "exclusive-with", "service property",
			   "default-feature", "true"
		   });
		addAnnotation
		  (getCall_Property(),
		   source,
		   new String[] {
			   "exclusive-with", "class service"
		   });
		addAnnotation
		  (getCall_Service(),
		   source,
		   new String[] {
			   "exclusive-with", "class property"
		   });
		addAnnotation
		  (getCall_Init(),
		   source,
		   new String[] {
			   "exclusive-with", "service property"
		   });
		addAnnotation
		  (configuratorEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/configurator.md"
		   });
		addAnnotation
		  (evalEClass,
		   source,
		   new String[] {
			   "documentation-reference", "doc/configurator.md"
		   });
		addAnnotation
		  (getEval_Script(),
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
		  (getModelElement_Description(),
		   source,
		   new String[] {
			   "documentation", "Element description for documentation purposes. Not used in generation."
		   });
		addAnnotation
		  (getCall_Type(),
		   source,
		   new String[] {
			   "documentation", "Fully qualified class name. Mutually exclusive with ``service`` and ``property``. One of ``class``, ``property``, or ``service`` is required.\n\nFor SupplierFactory adapter, if the class implements SupplierFactory then it is instantiated and then its ``create()`` method is invoked to create a supplier.\nIf the class implements Supplier, then it is instantiated. If there are no ``init`` arguments, then the supplier is diagnosed as part of ``diagnose()``. \nOtherwise it is diagnosed in ``execute()`` and diagnostic results are discarded.\n\nSimilar processing will be implemented for Consumer and Command.\n"
		   });
		addAnnotation
		  (getCall_Property(),
		   source,
		   new String[] {
			   "documentation", "Context property name. Mutually exclusive with ``class`` and ``service``. One of ``class``, ``property``, or ``service`` is required."
		   });
		addAnnotation
		  (getCall_Service(),
		   source,
		   new String[] {
			   "documentation", "Fully qualified context service class name. Mutually exclusive with ``class`` and ``property``. One of ``class``, ``property``, or ``service`` is required."
		   });
		addAnnotation
		  (getCall_Method(),
		   source,
		   new String[] {
			   "documentation", "An optional method to call. In the ``class`` case the method can be static. If the method is static the class is not instantiated and if ``init`` or ``properties`` are present it results in an exception."
		   });
		addAnnotation
		  (getCall_Properties(),
		   source,
		   new String[] {
			   "documentation", "A map injected into the instance in the ``class`` case if the instance implements ${javadoc/java.util.function.BiConsumer} or in the service or property case if they implement SupplierFactory. If elements implement SupplierFactory then the supplier factory is used to produce value to be injected. Otherwise elements are injected AS-IS."
		   });
		addAnnotation
		  (getCall_Init(),
		   source,
		   new String[] {
			   "documentation", "An optional array of constructor arguments for the ``class``. Not applicable for ``property`` and ``service``. If elements implement SupplierFactory then the factory is used to produce argument values. Then arguments get converted to constructor parameter types if conversion is available. If conversion is not available, an exception is thrown."
		   });
		addAnnotation
		  (getCall_Arguments(),
		   source,
		   new String[] {
			   "documentation", "An optional array of method arguments. If elements implement SupplierFactory, then the factory is used to produce argument value. Then arguments get converted to method parameter types if conversion is available. If conversion is not available, an exception is thrown."
		   });
		addAnnotation
		  (getConfigurator_Target(),
		   source,
		   new String[] {
			   "documentation", "Component to execute with the augmented context."
		   });
		addAnnotation
		  (getConfigurator_Properties(),
		   source,
		   new String[] {
			   "documentation", "A map injected into the instance in the ``class`` case if the instance implements java.util.function.BiConsumer or in the service or property case if they implement SupplierFactory. \nIf elements implement SupplierFactory then the supplier factory is used to produce value to be injected. Otherwise elements are injected AS-IS."
		   });
		addAnnotation
		  (getEval_Script(),
		   source,
		   new String[] {
			   "documentation", "Script source"
		   });
		addAnnotation
		  (getEval_Bindings(),
		   source,
		   new String[] {
			   "documentation", "Script bindings. Context is available as ``context`` binding and progress monitor as ``progressMonitor`` binding."
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
		  (callEClass,
		   source,
		   new String[] {
			   "constraints", "service_property_class method_arguments"
		   });
	}

} //ExecPackageImpl
