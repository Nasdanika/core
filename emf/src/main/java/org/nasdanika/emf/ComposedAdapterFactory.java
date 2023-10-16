package org.nasdanika.emf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.nasdanika.ncore.util.NcoreUtil;

/**
 * Composed factory delegates to its child factories. It uses inheritance ordering to delegate -
 * delegates to factories registered for more specific classes first. If there are several factories
 * for the same class then they are compared using factory's compare method if it implements comparable or by inheritance - more specific first.
 * @author Pavel Vlasov
 *
 */
public class ComposedAdapterFactory implements ComposeableAdapterFactory {
	
	private ComposedAdapterFactory parentAdapterFactory;
	
	private Map<Object,Boolean> factoryForTypeCache = new ConcurrentHashMap<>();

	@Override
	public boolean isFactoryForType(Object type) {
		return factoryForTypeCache.computeIfAbsent(type, this::_isFactoryForType);
	}
	
	private boolean _isFactoryForType(Object type) {
		for (AdapterFactoryEntry c: children) {
			if (c.adapterFactory.isFactoryForType(type)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Object adapt(Object object, Object type) {
		for (AdapterFactory af: match(object, type)) {
			Object ret = af.adapt(object, type);
			if (ret != null) {
				return ret;
			}
		}
		return null;
	}

	@Override
	public Adapter adapt(Notifier target, Object type) {
		for (AdapterFactory af: match(target, type)) {
			Adapter ret = af.adapt(target, type);
			if (ret != null) {
				return ret;
			}
		}
		return null;
	}

	@Override
	public Adapter adaptNew(Notifier target, Object type) {
		for (AdapterFactory af: match(target, type)) {
			Adapter ret = af.adaptNew(target, type);
			if (ret != null) {
				return ret;
			}
		}
		return null;
	}

	@Override
	public void adaptAllNew(Notifier notifier) {
		for (AdapterFactory af: match(notifier, null)) {
			af.adaptAllNew(notifier);
		}
	}

	@Override
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	@Override
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;		
	}
	
	protected List<AdapterFactory> match(Object obj, Object type) {
		return children
			.stream()
			.filter(ae -> ae.match(obj) && (type == null || ae.adapterFactory.isFactoryForType(type)))
			.sorted((a,b) -> cmpDistance((EObject) obj, a.eClass, b.eClass))
			.map(ae -> ae.adapterFactory)
			.toList();
	}
	
	protected int cmpDistance(EObject obj, EClass a, EClass b) {
		if (obj == null) {
			return 0;
		}
		
		return NcoreUtil.cmpDistance(obj.eClass(), a, b);
	}
		
	private class AdapterFactoryEntry {
		
		EClass eClass;
		AdapterFactory adapterFactory;
		
		AdapterFactoryEntry(EClass eClass, AdapterFactory adapterFactory) {
			this.eClass = eClass == null ? EcorePackage.Literals.EOBJECT : eClass;
			this.adapterFactory = adapterFactory;
			if (adapterFactory instanceof ComposeableAdapterFactory) {
				((ComposeableAdapterFactory) adapterFactory).setParentAdapterFactory(ComposedAdapterFactory.this);
			}
		}
		
		boolean match(Object obj) {
			return obj == null || (obj instanceof EObject && (eClass == null || eClass.isInstance(obj)));
		}				
		
	}
	
	private List<AdapterFactoryEntry> children = new ArrayList<>();
	
	public void registerAdapterFactory(ComposeableAdapterFactory child) {
		for (EClass eClass: child.getEClasses()) {
			children.add(new AdapterFactoryEntry(eClass, child));			
		}
	}

	/**
	 * Registers a factory for specified {@link EClass}es in the package.
	 * If no EClasses are specified then the adapter factory is considered to be
	 * applicable to all classes.
	 * @param child
	 * @param eClasses
	 */
	public void registerAdapterFactory(AdapterFactory child, EClass... eClasses) {
		if (child instanceof ComposedAdapterFactory) {
			throw new IllegalArgumentException("Composeable adapter factories shall be registered with registerAdapterFactory(ComposeableAdapterFactory child) method to avoid ambiguity in eClasses");
		}
		if (eClasses.length == 0) {
			children.add(new AdapterFactoryEntry(null, child));
		} else for (EClass eClass: eClasses) {
			children.add(new AdapterFactoryEntry(eClass, child));			
		}
		factoryForTypeCache.clear();
	}

	@Override
	public Collection<EClass> getEClasses() {
		Set<EClass> ret = new HashSet<>();
		for (AdapterFactoryEntry child: children) {
			ret.add(child.eClass);
		}
		return ret;
	}
}
