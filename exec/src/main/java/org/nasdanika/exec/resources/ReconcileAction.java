/**
 */
package org.nasdanika.exec.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Reconcile Action</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Defines an action to take when a resource with the same name already exists.
 * <!-- end-model-doc -->
 * @see org.nasdanika.exec.resources.ResourcesPackage#getReconcileAction()
 * @model
 * @generated
 */
public enum ReconcileAction implements Enumerator {
	/**
	 * The '<em><b>Append</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Appends new content to the existing content. For containers it means that new resources will be placed in the container next to the existing resources. For files it means that the new content will be appended after the existing content.
	 * <!-- end-model-doc -->
	 * @see #APPEND_VALUE
	 * @generated
	 * @ordered
	 */
	APPEND(0, "Append", "Append"),

	/**
	 * The '<em><b>Cancel</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Cancels execution if resource already exists.
	 * <!-- end-model-doc -->
	 * @see #CANCEL_VALUE
	 * @generated
	 * @ordered
	 */
	CANCEL(1, "Cancel", "Cancel"),

	/**
	 * The '<em><b>Keep</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Keeps the existing resource intact.
	 * <!-- end-model-doc -->
	 * @see #KEEP_VALUE
	 * @generated
	 * @ordered
	 */
	KEEP(2, "Keep", "Keep"),

	/**
	 * The '<em><b>Merge</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Merges old and new content of a file using a built-in or provided ``org.nasdanika.common.resources.Merger``. Merger is obtained from ``merger`` reference. For containers Merge is the same as Append.
	 * <!-- end-model-doc -->
	 * @see #MERGE_VALUE
	 * @generated
	 * @ordered
	 */
	MERGE(3, "Merge", "Merge"),

	/**
	 * The '<em><b>Overwrite</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Overwrites existing resource. For containers - deletes their contents.
	 * <!-- end-model-doc -->
	 * @see #OVERWRITE_VALUE
	 * @generated
	 * @ordered
	 */
	OVERWRITE(5, "Overwrite", "Overwrite");

	/**
	 * The '<em><b>Append</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Appends new content to the existing content. For containers it means that new resources will be placed in the container next to the existing resources. For files it means that the new content will be appended after the existing content.
	 * <!-- end-model-doc -->
	 * @see #APPEND
	 * @model name="Append"
	 * @generated
	 * @ordered
	 */
	public static final int APPEND_VALUE = 0;

	/**
	 * The '<em><b>Cancel</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Cancels execution if resource already exists.
	 * <!-- end-model-doc -->
	 * @see #CANCEL
	 * @model name="Cancel"
	 * @generated
	 * @ordered
	 */
	public static final int CANCEL_VALUE = 1;

	/**
	 * The '<em><b>Keep</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Keeps the existing resource intact.
	 * <!-- end-model-doc -->
	 * @see #KEEP
	 * @model name="Keep"
	 * @generated
	 * @ordered
	 */
	public static final int KEEP_VALUE = 2;

	/**
	 * The '<em><b>Merge</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Merges old and new content of a file using a built-in or provided ``org.nasdanika.common.resources.Merger``. Merger is obtained from ``merger`` reference. For containers Merge is the same as Append.
	 * <!-- end-model-doc -->
	 * @see #MERGE
	 * @model name="Merge"
	 * @generated
	 * @ordered
	 */
	public static final int MERGE_VALUE = 3;

	/**
	 * The '<em><b>Overwrite</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Overwrites existing resource. For containers - deletes their contents.
	 * <!-- end-model-doc -->
	 * @see #OVERWRITE
	 * @model name="Overwrite"
	 * @generated
	 * @ordered
	 */
	public static final int OVERWRITE_VALUE = 5;

	/**
	 * An array of all the '<em><b>Reconcile Action</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ReconcileAction[] VALUES_ARRAY =
		new ReconcileAction[] {
			APPEND,
			CANCEL,
			KEEP,
			MERGE,
			OVERWRITE,
		};

	/**
	 * A public read-only list of all the '<em><b>Reconcile Action</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ReconcileAction> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Reconcile Action</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ReconcileAction get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReconcileAction result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Reconcile Action</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ReconcileAction getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReconcileAction result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Reconcile Action</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static ReconcileAction get(int value) {
		switch (value) {
			case APPEND_VALUE: return APPEND;
			case CANCEL_VALUE: return CANCEL;
			case KEEP_VALUE: return KEEP;
			case MERGE_VALUE: return MERGE;
			case OVERWRITE_VALUE: return OVERWRITE;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ReconcileAction(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ReconcileAction
