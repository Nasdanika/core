package org.nasdanika.emf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * Palettes are used for grouping of EObjects, e.g. child candidates for containment references.
 * Palettes allow to manage reference children in groups and also to have pre-configured children, e.g. objects with pre-populated structural features.
 * Palette extends {@link EReferencePredicate} so it can be used by flow control and reference model elements to filter candidates for a reference. 
 * 
 * @author Pavel
 *
 */
public interface Palette extends EReferencePredicate {
	
	interface Registry {
		
		Palette get(String id);
		
		Palette create(String id, String name, String description);		
		
		/**
		 * Returns a de-dupped list of elements from requested palettes.
		 * @param palettes
		 * @return
		 */
		default List<EObject> getElements(String... palettes) {
			Set<String> ids = new HashSet<>();			
			List<EObject> ret = new ArrayList<>();
			for (String pid: palettes) {
				Palette palette = get(pid);
				if (palette != null) {
					for (Contributor contributor: palette.getContributors()) {
						if (ids.add(contributor.getId())) {
							ret.add(contributor.get());
						}
					}
				}
			}
			return ret;
		}
		
		static Registry INSTANCE = new Registry() {
			
			private Map<String,Palette> palettes = new ConcurrentHashMap<>();
			
			@Override
			public Palette get(String id) {
				return create(id, null, null);
			}
			
			@Override
			public Palette create(String id, String name, String description) {
				return palettes.computeIfAbsent(id, pid -> new Palette() {
					
					private List<Contributor> contributors = new ArrayList<>();
					private List<EReferencePredicate> eReferencePredicates = new ArrayList<>();

					@Override
					synchronized public List<Contributor> getContributors() {
						return new ArrayList<Contributor>(contributors);
					}

					@Override
					synchronized public void add(Contributor contributor) {
						contributors.add(contributor);						
					}

					@Override
					public String getId() {
						return id;
					}

					@Override
					public String getName() {
						return name;
					}

					@Override
					public String getDescription() {
						return description;
					}

					@Override
					synchronized public boolean accept(EObject source, EReference eReference, EObject target, boolean direct) {
						for (EReferencePredicate p: eReferencePredicates) {
							if (p.accept(source, eReference, target, direct)) {
								return true;
							}
						}
						return false;
					}

					@Override
					synchronized public void add(EReferencePredicate predicate) {
						eReferencePredicates.add(predicate);
					}
					
				});
			}
		};
		
	}
	
	/**
	 * Palette id.
	 * @return
	 */
	String getId();
	
	interface Contributor extends Supplier<EObject> {
		
		/**
		 * Contributor ID used for de-duplication.
		 * @return
		 */
		String getId();
		
	}
	
	/**
	 * @return A copy of the internal list of contributors.
	 */
	List<Contributor> getContributors();
	
	void add(Contributor contributor);
			
	default void add(String id, Supplier<EObject> supplier) {
		add(new Contributor() {

			@Override
			public EObject get() {
				return supplier.get();
			}

			@Override
			public String getId() {
				return id;
			}
			
		});
	}
	
	/**
	 * @return Palette elements.
	 */
	default List<EObject> getElements() {
		Set<String> ids = new HashSet<>();			
		List<EObject> ret = new ArrayList<>();
		for (Contributor contributor: getContributors()) {
			if (ids.add(contributor.getId())) {
				ret.add(contributor.get());
			}
		}
		return ret;
	}
		
	/**
	 * Adds a contributor which instantiates given EClass and uses EClass name and EPackage namespace URI as ID.
	 * Also adds predicates checking that a candidate is instance of one of provided EClass'es. 
	 * @param eClass
	 */
	default void add(EClass... eClass) {
		for (EClass ec: eClass) {
			add(new Contributor() {
				
				@Override
				public EObject get() {
					return ec.getEPackage().getEFactoryInstance().create(ec);
				}
				
				@Override
				public String getId() {
					return ec.getName()+"@"+ec.getEPackage().getNsURI();
				}
				
			});
			add(new EReferencePredicate() {

				@Override
				public boolean accept(EObject source, EReference eReference, EObject target, boolean direct) {
					return ec.isInstance(target);
				}
				
			});
		}
	};

	/**
	 * Adds a contributor which creates a copy of a given EObject.
	 * @param eClass
	 * @param id
	 */
	default void add(String id, EObject eObject) {
		add(new Contributor() {

			@Override
			public EObject get() {
				return EcoreUtil.copy(eObject);
			}

			@Override
			public String getId() {
				return id;
			}
			
		});
	}
	
	/**
	 * Adds a predicate to the palette. The predicates are OR'd.
	 * @param predicate
	 */
	void add(EReferencePredicate predicate);
		
	String getName();
	
	String getDescription();
	
}
