package org.nasdanika.emf.persistence;

import java.net.URL;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.persistence.Attribute;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * If config is a list loads each element creating elements using element factory and then loading them, otherwise creates a singleton in the same way as explained before.
 * @author Pavel
 *
 * @param <T>
 */
public class Reference<T> extends Attribute<T> {

	private EObjectLoader resolver;
	private EClass referenceType;
	private Function<ENamedElement, String> keyProvider;
	private boolean isStrictContainment;
	private ResourceSet resourceSet;
	private boolean referenceSupplierFactory;
	private boolean resolveProxies;

	/**
	 * 
	 * @param key
	 * @param isDefault
	 * @param required
	 * @param defaultValue
	 * @param description
	 * @param resolver
	 * @param referenceType Reference type if the reference is homogenous, i.e. its type is known beforehand.
	 * @param exclusiveWith
	 */
	public Reference(Object key, 
			boolean isDefault, 
			boolean required, 
			T defaultValue, 
			String description, 
			ResourceSet resourceSet,
			EObjectLoader resolver,
			EClass referenceType,
			boolean isStrictContainment,
			boolean referenceSupplierFactory,
			boolean resolveProxies,
			java.util.function.Function<ENamedElement,String> keyProvider,
			Object... exclusiveWith) {
		super(key, isDefault, required, defaultValue, description, exclusiveWith);
		this.resourceSet = resourceSet;
		this.resolver = resolver;
		this.referenceType = referenceType;
		this.isStrictContainment = isStrictContainment;
		this.referenceSupplierFactory = referenceSupplierFactory;
		this.resolveProxies = resolveProxies;
		this.keyProvider = keyProvider;		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T create(ObjectLoader loader, Object element, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		try {
			// Strings are references if not strict containment.
			if (element instanceof String && !isStrictContainment) {
				if (referenceSupplierFactory) {
					return (T) new SupplierFactory<EObject>() {
	
						@Override
						public Supplier<EObject> create(Context context) throws Exception {
							String ref = context.interpolateToString((String) element);
							return new Supplier<EObject>() {
	
								@Override
								public double size() {
									return 1;
								}
	
								@Override
								public String name() {
									return "Loading " + ref;
								}
	
								@Override
								public EObject execute(ProgressMonitor progressMonitor) throws Exception {
									URI refURI = URI.createURI(ref);
									ConfigurationException.pushThreadMarker(marker);
									try {
										return resourceSet.getEObject(base == null ? refURI : refURI.resolve(URI.createURI(base.toString())), true);
									} finally {
										ConfigurationException.popThreadMarker();
									}
								}
								
							};
						}
						
					};
				}
				String ref = (String) element;
				URL refURL = new URL(base, ref);
				return (T) resourceSet.getEObject(URI.createURI(refURL.toString()), true);
			}
			Object ret = referenceType == null ? loader.load(element, base, progressMonitor) : resolver.create(loader, referenceType, element, base, progressMonitor, marker, keyProvider);
			if (resolveProxies && ret instanceof EObject && ((EObject) ret).eIsProxy()) {
				return (T) resolver.resolve((EObject) ret);
			}
			return (T) ret;
		} catch (ConfigurationException e) {
			throw e;
		} catch (Exception e) {
			if (marker == null) {
				throw e;
			}
			throw new ConfigurationException("Error loading reference: " + e, e, marker);
		}
	}

}
