package org.nasdanika.common;

/**
 * Interface to adapt to for access decisions.
 * @author Pavel Vlasov
 *
 */
public interface AccessController extends Composeable<AccessController> {
	
	public static final String PERMISSION_READ = "read";

	public static final String PERMISSION_CREATE = "create";

	public static final String PERMISSION_DELETE = "delete";

	public static final String PERMISSION_UPDATE = "update";

	public static final String PERMISSION_EXECUTE = "execute";

	/**
	 * Access controller which permits all actions.
	 */
	AccessController ALLOW_ALL = (action, qualifier, context) -> true; 
	
	/**
	 * Access controller which permits nothing.
	 */
	AccessController DENY_ALL = (action, qualifier, context) -> false; 
	
	/**
	 * Checks for a permission to perform an action for a given qualifier with a given context.
	 * @param action Action to perform. E.g. "read" or "execute", "transfer-funds".
	 * @param qualifier Qualifier, e.g. "firstName" for read or "transferFunds" for execute.
	 * @param context Context for context-conditional permissions, e.g. for execute transferFunds it may contain "amount" key with transfer amount as a value, or more generally a map of parameter names to argument 
	 * values for {@link EOperation}. 
	 * @return
	 */
	boolean hasPermission(String action, String qualifier, Context context);
	
	/**
	 * Checks for a permission to perform an action for a given qualifier with empty context.
	 * @param action
	 * @param qualifier
	 * @return
	 */
	default boolean hasPermission(String action, String qualifier) {
		return hasPermission(action, qualifier, null);
	}
	
	/**
	 * Checks "read" permissions
	 * @param qualifier
	 * @return
	 */
	default boolean canRead(String qualifier, Context context) {
		return hasPermission(PERMISSION_READ, qualifier, context);
	}	
	
	/**
	 * Checks "read" permissions
	 * @param qualifier
	 * @return
	 */
	default boolean canRead(String qualifier) {
		return canRead(qualifier, null);
	}	
	
	/**
	 * Checks "create" permission
	 * @param qualifier
	 * @return
	 */
	default boolean canCreate(String qualifier, Context context) {
		return hasPermission(PERMISSION_CREATE, qualifier, context);
	}
	
	/**
	 * Checks "create" permissions
	 * @param qualifier
	 * @return
	 */
	default boolean canCreate(String qualifier) {
		return canCreate(qualifier, null);
	}	
	
	/**
	 * Checks "delete" permission
	 * @param qualifier
	 * @return
	 */
	default boolean canDelete(String qualifier, Context context) {
		return hasPermission(PERMISSION_DELETE, qualifier, context);
	}
	
	/**
	 * Checks "delete" permissions
	 * @param qualifier
	 * @return
	 */
	default boolean canDelete(String qualifier) {
		return canDelete(qualifier, null);
	}	
	
	/**
	 * Checks "update" permission
	 * @param qualifier
	 * @return
	 */
	default boolean canUpdate(String qualifier, Context context) {
		return hasPermission(PERMISSION_UPDATE, qualifier, context);
	}
	
	/**
	 * Checks "update" permissions
	 * @param qualifier
	 * @return
	 */
	default boolean canUpdate(String qualifier) {
		return canUpdate(qualifier, null);
	}	
	
	/**
	 * Checks "execute" permission
	 * @param qualifier
	 * @param context
	 * @return
	 */
	default boolean canExecute(String qualifier, Context context) {
		return hasPermission(PERMISSION_EXECUTE, qualifier, context);
	}

	/**
	 * Checks "execute" permissions
	 * @param qualifier
	 * @return
	 */
	default boolean canExecute(String qualifier) {
		return canExecute(qualifier, null);
	}	
	
	/**
	 * @param enclosingContext Context to fall-back to.
	 * @return Access controller which chains the context passed to the <code>hasPermission(action, qualifier, context)</code> with the enclosing context passed to this method. 
	 */
	default AccessController contextify(Context enclosingContext) {
		return enclosingContext == null ? this : (action,qualifier,context) -> hasPermission(action, qualifier, context == null ? enclosingContext : context.compose(enclosingContext)); 
	}
	
	@Override
	default AccessController compose(AccessController other) {
		return and(other);
	}
	
	/**
	 * @param accessControllers
	 * @return An "intersection" access controller which allows a permission only if all elements allow it. In the case of an empty list all permissions are denied.
	 */
	default AccessController and(AccessController other) {
		if (other == null) {
			return this;
		}
		
		return (action, qualifier, context) -> {
			return AccessController.this.hasPermission(action, qualifier, context) && other.hasPermission(action, qualifier, context); 
		}; 
	}
		
	/**
	 * @param accessControllers
	 * @return A "union" access controller which allows a permission if any of elements allows. In the case of an empty list all permissions are denied.
	 */
	default AccessController or(AccessController other) {
		if (other == null) {
			return this;
		}
		
		return (action, qualifier, context) -> {
			return AccessController.this.hasPermission(action, qualifier, context) || other.hasPermission(action, qualifier, context); 
		}; 
	}
	
}
