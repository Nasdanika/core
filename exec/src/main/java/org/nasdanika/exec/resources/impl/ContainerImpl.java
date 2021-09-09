/**
 */
package org.nasdanika.exec.resources.impl;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.exec.resources.File;
import org.nasdanika.exec.resources.Resource;
import org.nasdanika.exec.resources.ResourcesFactory;
import org.nasdanika.exec.resources.ResourcesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class ContainerImpl extends ResourceImpl implements org.nasdanika.exec.resources.Container {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ResourcesPackage.Literals.CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public org.nasdanika.exec.resources.Container getContainer(String path) {
		if (path.startsWith("../")) {
			EObject ec = eContainer();
			if (ec instanceof org.nasdanika.exec.resources.Container) {
				return ((org.nasdanika.exec.resources.Container) ec).getContainer(path.substring(3));
			}
			return null;
		}
		if (path.startsWith("./")) {
			path = path.substring(2);
		}
		
		Object existing = find(path);
		if (existing instanceof org.nasdanika.exec.resources.Container) {
			return (org.nasdanika.exec.resources.Container) existing;
		}
		if (existing != null) {
			// non-container - can't have another resource with the same name.
			return null;
		}
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			org.nasdanika.exec.resources.Container ret = ResourcesFactory.eINSTANCE.createContainer();
			ret.setName(path);
			getContents().add(ret);
			return ret;
		}
		
		String firstSegment = path.substring(0, sPos);
		org.nasdanika.exec.resources.Container container = getContainer(firstSegment);
		String pathTail = path.substring(sPos + 1);
		return container == null ? null : container.getContainer(pathTail);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public File getFile(String path) {
		if (path.startsWith("../")) {
			EObject ec = eContainer();
			if (ec instanceof org.nasdanika.exec.resources.Container) {
				return ((org.nasdanika.exec.resources.Container) ec).getFile(path.substring(3));
			}
			return null;
		}
		if (path.startsWith("./")) {
			path = path.substring(2);
		}
		
		Object existing = find(path);
		if (existing instanceof File) {
			return (File) existing;
		}
		if (existing != null) {
			// non-container - can't have another resource with the same name.
			return null;
		}
		
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			File ret = ResourcesFactory.eINSTANCE.createFile();
			ret.setName(path);
			getContents().add(ret);
			return ret;
		}
		
		String pathHead = path.substring(0, sPos);
		org.nasdanika.exec.resources.Container container = getContainer(pathHead);
		String pathTail = path.substring(sPos + 1);
		return container == null ? null : container.getFile(pathTail);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Resource find(String path) {
		if (path.startsWith("../")) {
			EObject ec = eContainer();
			if (ec instanceof org.nasdanika.exec.resources.Container) {
				return ((org.nasdanika.exec.resources.Container) ec).find(path.substring(3));
			}
			return null;
		}
		if (path.startsWith("./")) {
			path = path.substring(2);
		}
		
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			for (EObject c: getContents()) {
				if (c instanceof Resource && path.equals(((Resource) c).getName())) {
					return (Resource) c;
				}
			}
			return null; 
		}
		String firstSegment = path.substring(0, sPos);
		for (EObject c: getContents()) {
			if (c instanceof org.nasdanika.exec.resources.Container && firstSegment.equals(((Resource) c).getName())) {
				return ((org.nasdanika.exec.resources.Container) c).find(path.substring(sPos+1));
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case ResourcesPackage.CONTAINER___GET_CONTAINER__STRING:
				return getContainer((String)arguments.get(0));
			case ResourcesPackage.CONTAINER___GET_FILE__STRING:
				return getFile((String)arguments.get(0));
			case ResourcesPackage.CONTAINER___FIND__STRING:
				return find((String)arguments.get(0));
		}
		return super.eInvoke(operationID, arguments);
	}

} //ContainerImpl
