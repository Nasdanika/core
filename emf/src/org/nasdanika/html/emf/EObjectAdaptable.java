package org.nasdanika.html.emf;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.html.app.Adaptable;

/**
 * Bridges {@link Adaptable} and EObject adaptation framework - {@link Adapter}.
 * This class also adapts to {@link AccessController} by delegating to the container and adding containment feature to the 
 * qualifier. E.g. if an object Account is contained by Customer under accounts reference and Account is not adaptable to AccessController
 * then this adaptable would adapt Customer to AccessController and if successful will append 'accounts' suffix to the qualifier. 
 * This behavior allows to bubble-up permissions checks. 
 * @author Pavel
 *
 * @param <T>
 */
public class EObjectAdaptable<T extends EObject> implements Adaptable {
	
	protected T target;

	public EObjectAdaptable(T target) {
		this.target = target;
	}
	
	@Override
	public <A> A adaptTo(Class<A> type) {
		return adaptTo(target, type);
	}
	
	@SuppressWarnings("unchecked")
	public static <A> A adaptTo(EObject target, Class<A> type) {
		if (target == null) {
			return null;
		}
		if (type.isInstance(target)) {
			return (A) target;
		}
		A adapter = (A) EcoreUtil.getRegisteredAdapter(target, type);
		
		if (adapter == null && AccessController.class == type) {
			// Delegating to the container with containment reference qualifier
			EObject container = target.eContainer();
			if (container != null) {
				EReference containmentReference = target.eContainmentFeature();
				if (containmentReference != null) {
					AccessController cap = adaptTo(container, AccessController.class);
					if (cap != null) {
						return (A) new AccessController() {
							
							@Override
							public boolean hasPermission(String action, String qualifier, Map<?,?> context) {
								String cQualifier;
								if (qualifier == null) {
									cQualifier = qualifier;
								} else {
									String suffix = containmentReference.getName();
									if (containmentReference.isMany()) {
										int pos = ((List<?>) container.eGet(containmentReference)).indexOf(target);
										suffix += "[" + pos + "]";
									}
									if (qualifier.endsWith("/")) {
										cQualifier = qualifier + suffix;
									} else {
										cQualifier = qualifier + "/" + suffix;
									}
								}
								return cap.hasPermission(action, cQualifier, context);
							}
						};
					}
				}
			}
		}
		return adapter;		
	}
	
	// --- Convenience methods for checking access permissions ---
	
	public static AccessController adaptToAccessController(EObject target) {
		AccessController ac = adaptTo(target, AccessController.class);
		return ac == null ? AccessController.ALLOW_ALL : ac;
	}

	/**
	 * Checks for a permission to perform an action for a given qualifier.
	 * @param action
	 * @param qualifier
	 * @return
	 */
	public static boolean hasPermission(EObject obj, String action, String qualifier) {
		return adaptToAccessController(obj).hasPermission(action, qualifier);
	}
	
	/**
	 * Checks "read" permissions
	 * @param qualifier
	 * @return
	 */
	public static boolean canRead(EObject obj, String qualifier) {
		return adaptToAccessController(obj).canRead(qualifier);
	}
	
	/**
	 * Checks "create" permission
	 * @param qualifier
	 * @return
	 */
	public static boolean canCreate(EObject obj, String qualifier) {
		return adaptToAccessController(obj).canCreate(qualifier);
	}
	
	/**
	 * Checks "delete" permission
	 * @param qualifier
	 * @return
	 */
	public static boolean canDelete(EObject obj, String qualifier) {
		return adaptToAccessController(obj).canDelete(qualifier);
	}
	
	/**
	 * Checks "update" permission
	 * @param qualifier
	 * @return
	 */
	public static boolean canUpdate(EObject obj, String qualifier) {
		return adaptToAccessController(obj).canUpdate(qualifier);
	}
	
	/**
	 * Checks "execute" permission
	 * @param qualifier
	 * @param context
	 * @return
	 */
	public static boolean canExecute(EObject obj, String qualifier) {
		return adaptToAccessController(obj).canExecute(qualifier);
	}

	/**
	 * Checks for a permission to perform an action for a given qualifier.
	 * @param action
	 * @param qualifier
	 * @return
	 */
	public static boolean hasPermission(EObject obj, String action, String qualifier, Map<?,?> context) {
		return adaptToAccessController(obj).hasPermission(action, qualifier, context);
	}
	
	/**
	 * Checks "read" permissions
	 * @param qualifier
	 * @return
	 */
	public static boolean canRead(EObject obj, String qualifier, Map<?,?> context) {
		return adaptToAccessController(obj).canRead(qualifier, context);
	}
	
	/**
	 * Checks "create" permission
	 * @param qualifier
	 * @return
	 */
	public static boolean canCreate(EObject obj, String qualifier, Map<?,?> context) {
		return adaptToAccessController(obj).canCreate(qualifier, context);
	}
	
	/**
	 * Checks "delete" permission
	 * @param qualifier
	 * @return
	 */
	public static boolean canDelete(EObject obj, String qualifier, Map<?,?> context) {
		return adaptToAccessController(obj).canDelete(qualifier, context);
	}
	
	/**
	 * Checks "update" permission
	 * @param qualifier
	 * @return
	 */
	public static boolean canUpdate(EObject obj, String qualifier, Map<?,?> context) {
		return adaptToAccessController(obj).canUpdate(qualifier, context);
	}
	
	/**
	 * Checks "execute" permission
	 * @param qualifier
	 * @param context
	 * @return
	 */
	public static boolean canExecute(EObject obj, String qualifier, Map<?,?> context) {
		return adaptToAccessController(obj).canExecute(qualifier, context);
	}
	
	// --- Instance methods ---
		
	protected AccessController adaptToAccessController() {
		AccessController ac = adaptToAccessController(target);
		return ac == null ? AccessController.ALLOW_ALL : ac;
	}

	/**
	 * Checks for a permission to perform an action for a given qualifier.
	 * @param action
	 * @param qualifier
	 * @return
	 */
	protected boolean hasPermission(String action, String qualifier) {
		return adaptToAccessController().hasPermission(action, qualifier);
	}
	
	/**
	 * Checks "read" permissions
	 * @param qualifier
	 * @return
	 */
	protected boolean canRead(String qualifier) {
		return adaptToAccessController().canRead(qualifier);
	}
	
	/**
	 * Checks "create" permission
	 * @param qualifier
	 * @return
	 */
	protected boolean canCreate(String qualifier) {
		return adaptToAccessController().canCreate(qualifier);
	}
	
	/**
	 * Checks "delete" permission
	 * @param qualifier
	 * @return
	 */
	protected boolean canDelete(String qualifier) {
		return adaptToAccessController().canDelete(qualifier);
	}
	
	/**
	 * Checks "update" permission
	 * @param qualifier
	 * @return
	 */
	protected boolean canUpdate(String qualifier) {
		return adaptToAccessController().canUpdate(qualifier);
	}
	
	/**
	 * Checks "execute" permission
	 * @param qualifier
	 * @param context
	 * @return
	 */
	protected boolean canExecute(String qualifier) {
		return adaptToAccessController().canExecute(qualifier);
	}
	
	/**
	 * Checks for a permission to perform an action for a given qualifier.
	 * @param action
	 * @param qualifier
	 * @return
	 */
	protected boolean hasPermission(String action, String qualifier, Map<?,?> context) {
		return adaptToAccessController().hasPermission(action, qualifier, context);
	}
	
	/**
	 * Checks "read" permissions
	 * @param qualifier
	 * @return
	 */
	protected boolean canRead(String qualifier, Map<?,?> context) {
		return adaptToAccessController().canRead(qualifier, context);
	}
	
	/**
	 * Checks "create" permission
	 * @param qualifier
	 * @return
	 */
	protected boolean canCreate(String qualifier, Map<?,?> context) {
		return adaptToAccessController().canCreate(qualifier, context);
	}
	
	/**
	 * Checks "delete" permission
	 * @param qualifier
	 * @return
	 */
	protected boolean canDelete(String qualifier, Map<?,?> context) {
		return adaptToAccessController().canDelete(qualifier, context);
	}
	
	/**
	 * Checks "update" permission
	 * @param qualifier
	 * @return
	 */
	protected boolean canUpdate(String qualifier, Map<?,?> context) {
		return adaptToAccessController().canUpdate(qualifier, context);
	}
	
	/**
	 * Checks "execute" permission
	 * @param qualifier
	 * @param context
	 * @return
	 */
	protected boolean canExecute(String qualifier, Map<?,?> context) {
		return adaptToAccessController().canExecute(qualifier, context);
	}

}
