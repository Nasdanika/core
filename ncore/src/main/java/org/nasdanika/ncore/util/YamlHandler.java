package org.nasdanika.ncore.util;

import java.util.Optional;
import java.util.function.BiPredicate;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Composable;

public interface YamlHandler extends Composable<YamlHandler> {
	
	/**
	 * Possibly loads {@link EObject} from YAML object - Map, List or Scalar. 
	 * @param resource
	 * @param obj
	 * @return Empty optional if this handler cannot load a given object.
	 */
	Optional<EObject> load(YamlResource resource, Object obj);
	
	/**
	 * Possibly saves {@link EObject} to YAML object - Map, List or Scalar. 
	 * @param resource
	 * @param obj
	 * @return Empty optional if this handler cannot load a given object.
	 */
	Optional<Object> save(YamlResource resource, EObject obj);
	
	default YamlHandler narrow(BiPredicate<YamlResource, Object> loadPredicate, BiPredicate<YamlResource, EObject> savePredicate) {
		return new YamlHandler() {
			
			@Override
			public Optional<Object> save(YamlResource resource, EObject obj) {
				if (savePredicate == null || savePredicate.test(resource, obj)) {
					return YamlHandler.this.save(resource, obj);
				}
				return Optional.empty();
			}
			
			@Override
			public Optional<EObject> load(YamlResource resource, Object obj) {
				if (loadPredicate == null || loadPredicate.test(resource, obj)) {
					return YamlHandler.this.load(resource, obj);
				}
				return Optional.empty();
			}
			
		};
	}
		
	@Override
	default YamlHandler compose(YamlHandler other) {
		return new YamlHandler() {
			
			@Override
			public Optional<Object> save(YamlResource resource, EObject obj) {
				Optional<Object> ret = YamlHandler.this.save(resource, obj);
				return ret.isPresent() ? ret : other.save(resource, obj);
			}
			
			@Override
			public Optional<EObject> load(YamlResource resource, Object obj) {
				Optional<EObject> ret = YamlHandler.this.load(resource, obj);
				return ret.isPresent() ? ret : other.load(resource, obj);
			}
			
		};
	}
	
	static YamlHandler of(YamlHandler...handlers) {
		return new YamlHandler() {
			
			@Override
			public Optional<Object> save(YamlResource resource, EObject obj) {
				for (YamlHandler handler: handlers) {
					Optional<Object> ret = handler.save(resource, obj);
					if (ret.isPresent()) {
						return ret;
					}
				}
				return Optional.empty();
			}
			
			@Override
			public Optional<EObject> load(YamlResource resource, Object obj) {
				for (YamlHandler handler: handlers) {
					Optional<EObject> ret = handler.load(resource, obj);
					if (ret.isPresent()) {
						return ret;
					}
				}
				return Optional.empty();
			}
			
		};		
	}

}
