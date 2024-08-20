/**
 */
package org.nasdanika.exec.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.nasdanika.exec.Block;
import org.nasdanika.exec.Call;
import org.nasdanika.exec.Configurator;
import org.nasdanika.exec.Eval;
import org.nasdanika.exec.ExecFactory;
import org.nasdanika.exec.ExecPackage;
import org.nasdanika.exec.Fail;
import org.nasdanika.exec.List;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExecFactoryImpl extends EFactoryImpl implements ExecFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ExecFactory init() {
		try {
			ExecFactory theExecFactory = (ExecFactory)EPackage.Registry.INSTANCE.getEFactory(ExecPackage.eNS_URI);
			if (theExecFactory != null) {
				return theExecFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ExecFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExecFactoryImpl() {
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
			case ExecPackage.BLOCK: return createBlock();
			case ExecPackage.CALL: return createCall();
			case ExecPackage.PROPERTY: return (EObject)createProperty();
			case ExecPackage.CONFIGURATOR: return createConfigurator();
			case ExecPackage.EVAL: return createEval();
			case ExecPackage.FAIL: return createFail();
			case ExecPackage.LIST: return createList();
			case ExecPackage.MAP: return createMap();
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
	public Block createBlock() {
		BlockImpl block = new BlockImpl();
		return block;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Call createCall() {
		CallImpl call = new CallImpl();
		return call;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, EObject> createProperty() {
		PropertyImpl property = new PropertyImpl();
		return property;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Configurator createConfigurator() {
		ConfiguratorImpl configurator = new ConfiguratorImpl();
		return configurator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Eval createEval() {
		EvalImpl eval = new EvalImpl();
		return eval;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Fail createFail() {
		FailImpl fail = new FailImpl();
		return fail;
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
	public org.nasdanika.exec.Map createMap() {
		MapImpl map = new MapImpl();
		return map;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExecPackage getExecPackage() {
		return (ExecPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ExecPackage getPackage() {
		return ExecPackage.eINSTANCE;
	}

} //ExecFactoryImpl
