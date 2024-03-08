/**
 */
package org.nasdanika.ncore;

import java.lang.String;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Throwable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Models java.lang.Throwable.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Throwable#getType <em>Type</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Throwable#getMessage <em>Message</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Throwable#getStackTrace <em>Stack Trace</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Throwable#getSupressed <em>Supressed</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Throwable#getCause <em>Cause</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getThrowable()
 * @model
 * @generated
 */
public interface Throwable extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Throwable class name.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.nasdanika.ncore.NcorePackage#getThrowable_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Throwable#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Error message.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Message</em>' attribute.
	 * @see #setMessage(String)
	 * @see org.nasdanika.ncore.NcorePackage#getThrowable_Message()
	 * @model
	 * @generated
	 */
	String getMessage();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Throwable#getMessage <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Message</em>' attribute.
	 * @see #getMessage()
	 * @generated
	 */
	void setMessage(String value);

	/**
	 * Returns the value of the '<em><b>Stack Trace</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.StackTraceElement}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Stack trace.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Stack Trace</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getThrowable_StackTrace()
	 * @model containment="true"
	 * @generated
	 */
	EList<org.nasdanika.ncore.StackTraceElement> getStackTrace();

	/**
	 * Returns the value of the '<em><b>Supressed</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.Throwable}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Stack trace.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Supressed</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getThrowable_Supressed()
	 * @model containment="true"
	 * @generated
	 */
	EList<Throwable> getSupressed();

	/**
	 * Returns the value of the '<em><b>Cause</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Stack trace.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cause</em>' containment reference.
	 * @see #setCause(Throwable)
	 * @see org.nasdanika.ncore.NcorePackage#getThrowable_Cause()
	 * @model containment="true"
	 * @generated
	 */
	Throwable getCause();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Throwable#getCause <em>Cause</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cause</em>' containment reference.
	 * @see #getCause()
	 * @generated
	 */
	void setCause(Throwable value);
	
	static Throwable wrap(java.lang.Throwable th) {
		Throwable ret = NcoreFactory.eINSTANCE.createThrowable();
		ret.setType(th.getClass().getName());
		if (th.getMessage()!=null) {
			ret.setMessage(th.getMessage());
		}
		for (java.lang.StackTraceElement ste: th.getStackTrace()) {
			StackTraceElement stackTraceEntry = NcoreFactory.eINSTANCE.createStackTraceElement();
			ret.getStackTrace().add(stackTraceEntry);
			stackTraceEntry.setClassName(ste.getClassName());
			if (ste.getFileName()!=null) {
				stackTraceEntry.setFileName(ste.getFileName());
			}
			if (ste.getLineNumber()>=0) {
				stackTraceEntry.setLineNumber(ste.getLineNumber());
			}
			stackTraceEntry.setMethodName(ste.getMethodName());
			stackTraceEntry.setNative(ste.isNativeMethod());
		}
		if (th.getCause() != null) {
			ret.setCause(wrap(th.getCause()));
		}
		for (java.lang.Throwable s: th.getSuppressed()) {
			ret.getSupressed().add(wrap(s));
		}
		return ret;
	}
	
	
} // Throwable
