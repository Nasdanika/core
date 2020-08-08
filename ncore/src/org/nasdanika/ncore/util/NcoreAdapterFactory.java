/**
 */
package org.nasdanika.ncore.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.ncore.Array;
import org.nasdanika.ncore.Configurable;
import org.nasdanika.ncore.Entity;
import org.nasdanika.ncore.AbstractEntry;
import org.nasdanika.ncore.Function;
import org.nasdanika.ncore.Html;
import org.nasdanika.ncore.HttpCall;
import org.nasdanika.ncore.List;
import org.nasdanika.ncore.Map;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Null;
import org.nasdanika.ncore.Operation;
import org.nasdanika.ncore.Property;
import org.nasdanika.ncore.Reference;
import org.nasdanika.ncore.Resource;
import org.nasdanika.ncore.RestFunction;
import org.nasdanika.ncore.RestOperation;
import org.nasdanika.ncore.Script;
import org.nasdanika.ncore.ScriptResource;
import org.nasdanika.ncore.ScriptText;
import org.nasdanika.ncore.Service;
import org.nasdanika.ncore.ServiceEntry;
import org.nasdanika.ncore.Supplier;
import org.nasdanika.ncore.SupplierEntry;
import org.nasdanika.ncore.Value;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.nasdanika.ncore.NcorePackage
 * @generated
 */
public class NcoreAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static NcorePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NcoreAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = NcorePackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NcoreSwitch<Adapter> modelSwitch =
		new NcoreSwitch<Adapter>() {
			@Override
			public Adapter caseModelElement(ModelElement object) {
				return createModelElementAdapter();
			}
			@Override
			public Adapter caseNamedElement(NamedElement object) {
				return createNamedElementAdapter();
			}
			@Override
			public Adapter caseEntity(Entity object) {
				return createEntityAdapter();
			}
			@Override
			public Adapter caseConfigurable(Configurable object) {
				return createConfigurableAdapter();
			}
			@Override
			public Adapter caseService(Service object) {
				return createServiceAdapter();
			}
			@Override
			public Adapter caseSupplier(Supplier object) {
				return createSupplierAdapter();
			}
			@Override
			public Adapter caseResource(Resource object) {
				return createResourceAdapter();
			}
			@Override
			public Adapter caseReference(Reference object) {
				return createReferenceAdapter();
			}
			@Override
			public Adapter caseValue(Value object) {
				return createValueAdapter();
			}
			@Override
			public Adapter caseNull(Null object) {
				return createNullAdapter();
			}
			@Override
			public Adapter caseOperation(Operation object) {
				return createOperationAdapter();
			}
			@Override
			public Adapter caseArray(Array object) {
				return createArrayAdapter();
			}
			@Override
			public Adapter caseAbstractEntry(AbstractEntry object) {
				return createAbstractEntryAdapter();
			}
			@Override
			public Adapter caseServiceEntry(ServiceEntry object) {
				return createServiceEntryAdapter();
			}
			@Override
			public Adapter caseSupplierEntry(SupplierEntry object) {
				return createSupplierEntryAdapter();
			}
			@Override
			public Adapter caseMap(Map object) {
				return createMapAdapter();
			}
			@Override
			public Adapter caseProperty(Property object) {
				return createPropertyAdapter();
			}
			@Override
			public Adapter caseFunction(Function object) {
				return createFunctionAdapter();
			}
			@Override
			public Adapter caseList(List object) {
				return createListAdapter();
			}
			@Override
			public Adapter caseObject(org.nasdanika.ncore.Object object) {
				return createObjectAdapter();
			}
			@Override
			public Adapter caseHttpCall(HttpCall object) {
				return createHttpCallAdapter();
			}
			@Override
			public Adapter caseRestOperation(RestOperation object) {
				return createRestOperationAdapter();
			}
			@Override
			public Adapter caseRestFunction(RestFunction object) {
				return createRestFunctionAdapter();
			}
			@Override
			public Adapter caseHtml(Html object) {
				return createHtmlAdapter();
			}
			@Override
			public Adapter caseScript(Script object) {
				return createScriptAdapter();
			}
			@Override
			public Adapter caseScriptText(ScriptText object) {
				return createScriptTextAdapter();
			}
			@Override
			public Adapter caseScriptResource(ScriptResource object) {
				return createScriptResourceAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.ModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.ModelElement
	 * @generated
	 */
	public Adapter createModelElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.Entity <em>Entity</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.Entity
	 * @generated
	 */
	public Adapter createEntityAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.Configurable <em>Configurable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.Configurable
	 * @generated
	 */
	public Adapter createConfigurableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.Service <em>Service</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.Service
	 * @generated
	 */
	public Adapter createServiceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.Supplier <em>Supplier</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.Supplier
	 * @generated
	 */
	public Adapter createSupplierAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.Resource
	 * @generated
	 */
	public Adapter createResourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.Reference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.Reference
	 * @generated
	 */
	public Adapter createReferenceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.Value <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.Value
	 * @generated
	 */
	public Adapter createValueAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.Null <em>Null</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.Null
	 * @generated
	 */
	public Adapter createNullAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.Operation
	 * @generated
	 */
	public Adapter createOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.Array <em>Array</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.Array
	 * @generated
	 */
	public Adapter createArrayAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.AbstractEntry <em>Abstract Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.AbstractEntry
	 * @generated
	 */
	public Adapter createAbstractEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.ServiceEntry <em>Service Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.ServiceEntry
	 * @generated
	 */
	public Adapter createServiceEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.SupplierEntry <em>Supplier Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.SupplierEntry
	 * @generated
	 */
	public Adapter createSupplierEntryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.Map <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.Map
	 * @generated
	 */
	public Adapter createMapAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.Property
	 * @generated
	 */
	public Adapter createPropertyAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.Function
	 * @generated
	 */
	public Adapter createFunctionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.List <em>List</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.List
	 * @generated
	 */
	public Adapter createListAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.Object <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.Object
	 * @generated
	 */
	public Adapter createObjectAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.HttpCall <em>Http Call</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.HttpCall
	 * @generated
	 */
	public Adapter createHttpCallAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.RestOperation <em>Rest Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.RestOperation
	 * @generated
	 */
	public Adapter createRestOperationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.RestFunction <em>Rest Function</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.RestFunction
	 * @generated
	 */
	public Adapter createRestFunctionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.Html <em>Html</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.Html
	 * @generated
	 */
	public Adapter createHtmlAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.Script <em>Script</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.Script
	 * @generated
	 */
	public Adapter createScriptAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.ScriptText <em>Script Text</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.ScriptText
	 * @generated
	 */
	public Adapter createScriptTextAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.nasdanika.ncore.ScriptResource <em>Script Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.nasdanika.ncore.ScriptResource
	 * @generated
	 */
	public Adapter createScriptResourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //NcoreAdapterFactory
