package org.nasdanika.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.springframework.util.AntPathMatcher;

/**
 * {@link ResourceSet} matches factories by extensions, which might not be sufficient in some cases. 
 * E.g. <code>pom.xml</code> is an XML file, but it shall be loaded with a Maven resource factory.
 * This class dispatches to registered resource factories matching predicates in the order of registration. 
 */
public class DispatchingResourceFactory implements Factory {
	
	private List<Map.Entry<Predicate<URI>, Resource.Factory>> factories = new ArrayList<>();

	@Override
	public Resource createResource(URI uri) {
		for (Entry<Predicate<URI>, Factory> fe: factories) {
			if (fe.getKey().test(uri)) {
				return fe.getValue().createResource(uri);
			}
		}
		throw new IllegalArgumentException("There is no factory matching " + uri); 
	}
	
	public void register(Predicate<URI> predicate, Resource.Factory factory) {
		factories.add(Map.entry(predicate, factory));
	}
	
	/**
	 * Register exact match
	 * @param uri
	 * @param factory
	 */
	public void register(URI uri, Resource.Factory factory) {
		register(u -> Objects.equals(u, uri), factory);
	}
	
	/**
	 * Regex pattern matching the URI.
	 * @param regex
	 * @param factory
	 */
	public void register(String regex, Resource.Factory factory) {
		register(u -> Pattern.matches(regex, u.toString()), factory);
	}
	
	/**
	 * Regex pattern matching the URI deresolved against the base.
	 * @param regex
	 * @param factory
	 */
	public void register(String regex, URI base, Resource.Factory factory) {
		register(u -> Pattern.matches(regex, u.deresolve(base, true, true, true).toString()), factory);
	}
	
	/**
	 * Regex pattern matching the URI deresolved against the base.
	 * @param regex
	 * @param factory
	 */
	public void registerAntPattern(String pattern, URI base, Resource.Factory factory) {
		AntPathMatcher matcher = new AntPathMatcher();
		register(u ->  matcher.match(pattern, u.deresolve(base, true, true, true).toString()), factory);
	}
	
	public boolean canCreate(URI uri) {
		for (Entry<Predicate<URI>, Factory> p: factories) {
			if (p.getKey().test(uri)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Gets a factory for URI and returns result of a call to canCreate() if the factory is instance of {@link DispatchingResourceFactory} or true if the factory is not null otherwise.
	 * @param resourceSet
	 * @param uri
	 * @return
	 */
	public static boolean canLoad(Factory.Registry registry, URI uri) {
		Factory factory = registry.getFactory(uri);
		if (factory instanceof DispatchingResourceFactory) {
			return ((DispatchingResourceFactory) factory).canCreate(uri);
		}
		return factory != null;
	}
	
}
