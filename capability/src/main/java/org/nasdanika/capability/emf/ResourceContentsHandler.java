package org.nasdanika.capability.emf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.capability.CapabilityLoader;

/**
 * Interface for handling resource contents loading and saving.
 * Implementations of this interface can be used to customize the way resource contents are loaded and saved, allowing for filtering, transformation, or other processing as needed.
 * @param <T>
 */
public interface ResourceContentsHandler<T> {
	
	/**
	 * {@link CapabilityLoader} service requirement for resource contents handler.
	 */
	interface Requirement {
		
		Resource getResource();
		
		Class<?> getContentsType();
		
		String[] getQualifiers();
		
		int getQualifierIndex();
		
	}
	
	static Requirement createRequirement(Resource resource, Class<?> contentsType, String[] qualifiers, int qualifierIndex) {
		
		return new Requirement() {
			
			@Override
			public Resource getResource() {
				return resource;
			}
			
			@Override
			public Class<?> getContentsType() {
				return contentsType;
			}
			
			@Override
			public String[] getQualifiers() {
				return qualifiers;
			}
			
			@Override
			public int getQualifierIndex() {
				return qualifierIndex;
			}
			
			@Override
			public boolean equals(Object obj) {
				if (this == obj) {
					return true;
				}
				if (obj == null || getClass() != obj.getClass()) {
					return false;
				}
				return 
						Arrays.equals(qualifiers, ((Requirement) obj).getQualifiers())
						&& Objects.equals(resource.getURI().toString(), ((Requirement) obj).getResource().getURI().toString())
						&& Objects.equals(contentsType, ((Requirement) obj).getContentsType())
						&& qualifierIndex == ((Requirement) obj).getQualifierIndex();
			}
			
			@Override
			public int hashCode() {
				return Objects.hash(resource.getURI().toString(), contentsType, Arrays.hashCode(qualifiers), qualifierIndex);
			}
			
		};
						
	}
	
	/**
	 * Handler order for selecting more specific handler before less specific handler. 
	 * Longer order is considered smaller than shorter order. If orders are of the same length, compare order values element by element.
	 */
	interface Order extends Comparable<Order> {
		
		Order add(int order);
		
		int size();
		
		int get(int index);
		
		static Order of(int... order) {
			return new Order() {
				
				@Override
				public Order add(int order) {
					int[] newOrder = new int[size() + 1];
					for (int i = 0; i < size(); ++i) {
						newOrder[i + 1] = get(i);
					}
					newOrder[0] = order;
					return of(newOrder);
				}
				
				@Override
				public int size() {
					return order.length;
				}
				
				@Override
				public int get(int index) {
					return order[index];
				}
				
				@Override
				public int compareTo(Order o) {
					// Longer order is considered smaller than shorter order - more specific handler should be used before less specific handler. 
					// If orders are of the same length, compare order values element by element.
					int sizeCmp = Integer.compare(o.size(), size());
					if (sizeCmp == 0) {
						for (int i = 0; i < size(); ++i) {
							int cmp = Integer.compare(get(i), o.get(i));
							if (cmp != 0) {
								return cmp;
							}
						}
					}
					
					return sizeCmp;
				}
				
			};
		}
		
	}
	
	Order getOrder();	
	
	/**
	 * Loads resource contents from the specified input stream.
	 * @param inputStream
	 * @param options
	 * @return
	 * @throws IOException
	 */
	T load(InputStream inputStream, Map<?, ?> options) throws IOException;
	
	/**
	 * Saves resource contents to the specified output stream.
	 * Implementations of this method may throw UnsupportedOperationException if saving is not supported.
	 * This implementation throws UnsupportedOperationException. 
	 * It is provided as a default method for convenience for read-only handlers that only support loading resource contents - they can be implemented as functional interfaces.
	 * @param contents
	 * @param outputStream
	 * @param options
	 * @throws IOException
	 */
	default void save(T contents, OutputStream outputStream, Map<?, ?> options) throws IOException {
		throw new UnsupportedOperationException("Save operation is not supported");
	}
	
	default <V> ResourceContentsHandler<V> adapt(Function<T,V> loadAdapter, Function<V,T> saveAdapter) {
		
		return new ResourceContentsHandler<V>() {
			
			@Override
			public Order getOrder() {
				return ResourceContentsHandler.this.getOrder();
			}
			
			@Override
			public V load(InputStream inputStream, Map<?, ?> options) throws IOException {
				return loadAdapter.apply(ResourceContentsHandler.this.load(inputStream, options));
			}
			
			@Override
			public void save(V contents, OutputStream outputStream, Map<?, ?> options) throws IOException {
				ResourceContentsHandler.this.save(saveAdapter.apply(contents), outputStream, options);
			}
			
		};
		
	}	

}
