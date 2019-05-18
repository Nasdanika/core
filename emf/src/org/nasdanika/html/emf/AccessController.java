package org.nasdanika.html.emf;

import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.EOperation;

/**
 * Interface to adapt to for access decisions.
 * @author Pavel Vlasov
 *
 */
public interface AccessController {
	
	/**
	 * Access controller which permits all actions.
	 */
	AccessController ALLOW_ALL = new AccessController() {
		
		@Override
		public boolean hasPermission(String action, String qualifier, Map<?,?> context) {
			return true;
		}
		
	};
	
	/**
	 * Access controller which permits nothing.
	 */
	AccessController DENY_ALL = new AccessController() {
		
		@Override
		public boolean hasPermission(String action, String qualifier, Map<?,?> context) {
			return false;
		}
		
	};	
	
	/**
	 * Checks for a permission to perform an action for a given qualifier with a given context.
	 * @param action Action to perform. E.g. "read" or "execute", "transfer-funds".
	 * @param qualifier Qualifier, e.g. "firstName" for read or "transferFunds" for execute.
	 * @param context Context for context-conditional permissions, e.g. for execute transferFunds it may contain "amount" key with transfer amount as a value, or more generally a map of parameter names to argument 
	 * values for {@link EOperation}. 
	 * @return
	 */
	boolean hasPermission(String action, String qualifier, Map<?,?> context);
	
	/**
	 * Checks for a permission to perform an action for a given qualifier with empty context.
	 * @param action
	 * @param qualifier
	 * @return
	 */
	default boolean hasPermission(String action, String qualifier) {
		return hasPermission(action, qualifier, Collections.emptyMap());
	}
	
	/**
	 * Checks "read" permissions
	 * @param qualifier
	 * @return
	 */
	default boolean canRead(String qualifier, Map<?,?> context) {
		return hasPermission("read", qualifier, context);
	}	
	
	/**
	 * Checks "read" permissions
	 * @param qualifier
	 * @return
	 */
	default boolean canRead(String qualifier) {
		return canRead(qualifier, Collections.emptyMap());
	}	
	
	/**
	 * Checks "create" permission
	 * @param qualifier
	 * @return
	 */
	default boolean canCreate(String qualifier, Map<?,?> context) {
		return hasPermission("create", qualifier, context);
	}
	
	/**
	 * Checks "create" permissions
	 * @param qualifier
	 * @return
	 */
	default boolean canCreate(String qualifier) {
		return canCreate(qualifier, Collections.emptyMap());
	}	
	
	/**
	 * Checks "delete" permission
	 * @param qualifier
	 * @return
	 */
	default boolean canDelete(String qualifier, Map<?,?> context) {
		return hasPermission("delete", qualifier, context);
	}
	
	/**
	 * Checks "delete" permissions
	 * @param qualifier
	 * @return
	 */
	default boolean canDelete(String qualifier) {
		return canDelete(qualifier, Collections.emptyMap());
	}	
	
	/**
	 * Checks "update" permission
	 * @param qualifier
	 * @return
	 */
	default boolean canUpdate(String qualifier, Map<?,?> context) {
		return hasPermission("update", qualifier, context);
	}
	
	/**
	 * Checks "update" permissions
	 * @param qualifier
	 * @return
	 */
	default boolean canUpdate(String qualifier) {
		return canUpdate(qualifier, Collections.emptyMap());
	}	
	
	/**
	 * Checks "execute" permission
	 * @param qualifier
	 * @param context
	 * @return
	 */
	default boolean canExecute(String qualifier, Map<?,?> context) {
		return hasPermission("execute", qualifier, context);
	}

	/**
	 * Checks "execute" permissions
	 * @param qualifier
	 * @return
	 */
	default boolean canExecute(String qualifier) {
		return canExecute(qualifier, Collections.emptyMap());
	}	
	
}
