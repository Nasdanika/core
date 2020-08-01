package org.nasdanika.emf;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.AccessController;
import org.nasdanika.common.Adaptable;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Converter;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.ResourceLocator;
import org.nasdanika.common.SupplierFactory;

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

	/**
	 * Adapts target to ResourceLocator and returns context or empty context if there is no context, i.e. the context is never null.
	 * @param target
	 * @return
	 */
	public static Context getResourceContext(EObject target) {
		return Context.EMPTY_CONTEXT.compose(EObjectAdaptable.adaptTo(target, ResourceLocator.class));
	}
	
	/**
	 * Adapts target to ResourceLocator and returns context or empty context if there is no context, i.e. the context is never null.
	 * @param target
	 * @return
	 */
	protected Context getResourceContext() {
		return getResourceContext(target);
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
							public boolean hasPermission(String action, String qualifier, Context context) {
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
	public static boolean hasPermission(EObject obj, String action, String qualifier, Context context) {
		return adaptToAccessController(obj).hasPermission(action, qualifier, context);
	}
	
	/**
	 * Checks "read" permissions
	 * @param qualifier
	 * @return
	 */
	public static boolean canRead(EObject obj, String qualifier, Context context) {
		return adaptToAccessController(obj).canRead(qualifier, context);
	}
	
	/**
	 * Checks "create" permission
	 * @param qualifier
	 * @return
	 */
	public static boolean canCreate(EObject obj, String qualifier, Context context) {
		return adaptToAccessController(obj).canCreate(qualifier, context);
	}
	
	/**
	 * Checks "delete" permission
	 * @param qualifier
	 * @return
	 */
	public static boolean canDelete(EObject obj, String qualifier, Context context) {
		return adaptToAccessController(obj).canDelete(qualifier, context);
	}
	
	/**
	 * Checks "update" permission
	 * @param qualifier
	 * @return
	 */
	public static boolean canUpdate(EObject obj, String qualifier, Context context) {
		return adaptToAccessController(obj).canUpdate(qualifier, context);
	}
	
	/**
	 * Checks "execute" permission
	 * @param qualifier
	 * @param context
	 * @return
	 */
	public static boolean canExecute(EObject obj, String qualifier, Context context) {
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
	protected boolean hasPermission(String action, String qualifier, Context context) {
		return adaptToAccessController().hasPermission(action, qualifier, context);
	}
	
	/**
	 * Checks "read" permissions
	 * @param qualifier
	 * @return
	 */
	protected boolean canRead(String qualifier, Context context) {
		return adaptToAccessController().canRead(qualifier, context);
	}
	
	/**
	 * Checks "create" permission
	 * @param qualifier
	 * @return
	 */
	protected boolean canCreate(String qualifier, Context context) {
		return adaptToAccessController().canCreate(qualifier, context);
	}
	
	/**
	 * Checks "delete" permission
	 * @param qualifier
	 * @return
	 */
	protected boolean canDelete(String qualifier, Context context) {
		return adaptToAccessController().canDelete(qualifier, context);
	}
	
	/**
	 * Checks "update" permission
	 * @param qualifier
	 * @return
	 */
	protected boolean canUpdate(String qualifier, Context context) {
		return adaptToAccessController().canUpdate(qualifier, context);
	}
	
	/**
	 * Checks "execute" permission
	 * @param qualifier
	 * @param context
	 * @return
	 */
	protected boolean canExecute(String qualifier, Context context) {
		return adaptToAccessController().canExecute(qualifier, context);
	}
	
	// --- Adapting to execution participants factories
	
	/**
	 * Adapts to a {@link SupplierFactory} of specific type by adapting to {@link SupplierFactory.Provider} first and obtaining a typed factory from it.
	 * If it doesn't work then adapts to SupplierFactory and then's it with a {@link FunctionFactory} which converts the result to requested type.
	 * @param <T>
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> SupplierFactory<T> adaptToSupplierFactory(EObject target, Class<T> type) {
		SupplierFactory.Provider provider = adaptTo(target, SupplierFactory.Provider.class);
		if (provider != null) {
			SupplierFactory<T> factory = provider.getFactory(type);
			if (factory != null) {
				return factory;
			}
		}
		SupplierFactory<Object> factory = adaptTo(target, SupplierFactory.class);
		if (factory == null) { 
			return null;
		}
		return factory.then(new FunctionFactory<Object,T>() {

			@Override
			public Function<Object, T> create(Context context) throws Exception {				
				return new Function<Object, T>() {

					@Override
					public double size() {
						return 1;
					}

					@Override
					public String name() {
						return "Converter to "+type;
					}

					@Override
					public T execute(Object result, ProgressMonitor progressMonitor) throws Exception {
						if (result == null || type.isInstance(result)) {
							return (T) result;
						}
						Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
						T ret = converter.convert(result, type);
						if (ret == null) {
							throw new IllegalArgumentException("Cannot convert " + result.getClass() + " " + result + " to " + type);
						}
						return ret;
					}
				};
			}
			
		});		
	}
	
	/**
	 * Adapts each element of the targets collection to {@link SupplierFactory} and returns as a list.
	 * Throws {@link NullPointerException} if any of the targets cannot be adapted.
	 * @param <T>
	 * @param targets
	 * @param type
	 * @return
	 */
	public static <T> List<SupplierFactory<T>> adaptToSupplierFactory(Collection<? extends EObject> targets, Class<T> type) {
		return targets.stream().map(e -> Objects.requireNonNull(adaptToSupplierFactory(e, type))).collect(Collectors.toList());
	}
	
	/**
	 * Adapts to a {@link FunctionFactory} of specific parameter and result types by adapting to {@link FunctionFactory.Provider} first and obtaining a typed factory from it.
	 * If it doesn't work then adapts to FunctionFactory and then's it with a {@link FunctionFactory} which converts the result to requested type. The argument is not converted.
	 * @param <T>
	 * @param <R>
	 * @param parameterType
	 * @param resultType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T,R> FunctionFactory<T,R> adaptToFunctionFactory(EObject target, Class<T> parameterType, Class<R> resultType) {
		FunctionFactory.Provider provider = adaptTo(target, FunctionFactory.Provider.class);
		if (provider != null) {
			FunctionFactory<T,R> factory = provider.getFactory(parameterType, resultType);
			if (factory != null) {
				return factory;
			}
		}
		FunctionFactory<Object,Object> factory = adaptTo(target, FunctionFactory.class);
		if (factory == null) { 
			return null;
		}
		return (FunctionFactory<T,R>) factory.then(new FunctionFactory<Object,R>() {

			@Override
			public Function<Object,R> create(Context context) throws Exception {				
				return new Function<Object,R>() {

					@Override
					public double size() {
						return 1;
					}

					@Override
					public String name() {
						return "Converter to "+resultType;
					}

					@Override
					public R execute(Object result, ProgressMonitor progressMonitor) throws Exception {
						if (result == null || resultType.isInstance(result)) {
							return (R) result;
						}
						Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
						R ret = converter.convert(result, resultType);
						if (ret == null) {
							throw new IllegalArgumentException("Cannot convert " + result.getClass() + " " + result + " to " + resultType);
						}
						return ret;
					}
				};
			}
			
		});		
	}
	
	/**
	 * Adapts each element of the targets collection to {@link FunctionFactory} and returns as a list.
	 * Throws {@link NullPointerException} if any of the targets cannot be adapted.
	 * @return
	 */
	public static <T,R> List<FunctionFactory<T,R>> adaptToFunctionFactory(Collection<? extends EObject> targets, Class<T> parameterType, Class<R> resultType) {
		return targets.stream().map(e -> Objects.requireNonNull(adaptToFunctionFactory(e, parameterType, resultType))).collect(Collectors.toList());
	}
	
	/**
	 * Adapts to a {@link ConsumerFactory} of specific type by adapting to {@link ConsumerFactory.Provider} first and obtaining a typed factory from it.
	 * If it doesn't work then adapts to ConsumerFactory.
	 * @param <T>
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> ConsumerFactory<T> adaptToConsumerFactory(EObject target, Class<T> type) {
		ConsumerFactory.Provider provider = adaptTo(target, ConsumerFactory.Provider.class);
		if (provider != null) {
			ConsumerFactory<T> factory = provider.getFactory(type);
			if (factory != null) {
				return factory;
			}
		}
		return (ConsumerFactory<T>) adaptTo(target, SupplierFactory.class);
	}
	
	/**
	 * Adapts each element of the targets collection to {@link ConsumerFactory} and returns as a list.
	 * Throws {@link NullPointerException} if any of the targets cannot be adapted.
	 * @param <T>
	 * @param targets
	 * @param type
	 * @return
	 */
	public static <T> List<ConsumerFactory<T>> adaptToConsumerFactory(Collection<? extends EObject> targets, Class<T> type) {
		return targets.stream().map(e -> Objects.requireNonNull(adaptToConsumerFactory(e, type))).collect(Collectors.toList());
	}

}
