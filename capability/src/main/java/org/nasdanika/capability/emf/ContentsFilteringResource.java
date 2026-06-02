package org.nasdanika.capability.emf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.common.Util;

/**
 * 
 */
public class ContentsFilteringResource extends ResourceImpl {

	private ResourceContentsFilter filter;

	public ContentsFilteringResource(URI uri, Collection<ResourceContentsFilter> filters) {
		super(uri);
		if (filters != null) {
			filter = filters
					.stream()
					.sorted((f1, f2) -> {
						int orderCmp = Integer.compare(f1.order(), f2.order());
						return orderCmp != 0 ? orderCmp : f1.getClass().getName().compareTo(f2.getClass().getName());
					})
					.reduce(ResourceContentsFilter::compose)
					.orElse(null);
		}
	}
	
	private static String[] reverse(String[] arr) {
	    int start = 0;
	    int end = arr.length - 1;
	    
	    while (start < end) {
	        // Swap elements using a temporary variable
	        String temp = arr[start];
	        arr[start] = arr[end];
	        arr[end] = temp;
	        
	        // Move pointers closer to the middle
	        start++;
	        end--;
	    }
	    return arr;
	}	
	
	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		List<EObject> contents = loadContents(inputStream, options);
		String lastSegment = getURI().lastSegment();
		if (!Util.isBlank(lastSegment) && filter != null) {			
			String[] qualifiers = reverse(lastSegment.split("\\."));
			for (int i = 0; i < qualifiers.length; ++i) {
				contents = filter.load(this, contents, qualifiers, i, options);
			}
		}		
		
		getContents().addAll(contents);
	}
	
	/**
	 * Loads content from the input stream.
	 * @param inputStream
	 * @param options
	 * @return
	 * @throws IOException
	 */
	protected List<EObject> loadContents(InputStream inputStream, Map<?, ?> options) throws IOException {
		throw new UnsupportedOperationException("Override to implement content loading");
	}	
	
	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		List<EObject> content = getContents();
		String lastSegment = getURI().lastSegment();
		if (!Util.isBlank(lastSegment) && filter != null) {
			String[] qualifiers = reverse(lastSegment.split("\\."));
			for (int i = qualifiers.length - 1; i >= 0 ; --i) {
				content = filter.save(this, content, qualifiers, i, options);
			}
		}		
		
		saveContents(content, outputStream, options);
	}
	
	protected void saveContents(List<EObject> contents, OutputStream outputStream, Map<?, ?> options) throws IOException {
		throw new UnsupportedOperationException("Override to implement content loading");
	}	

}
