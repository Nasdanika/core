package org.nasdanika.graph.processor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.graph.Element;


/**
 * Base class for resource which load their contents by creating a {@link Stream} of {@link Element}'s from a resource {@link URI} and then uses {@link ProcessorFactory} to create {@link Supplier}'s of {@link EObject} with {@link ProcessorFactory}. 
 * @author Pavel
 *
 */
public abstract class GraphProcessorResource<P> extends ResourceImpl {
	
	private ProcessorFactory<P, ?, ?> processorFactory;
	
	protected GraphProcessorResource(URI uri, ProcessorFactory<P, ?, ?> processorFactory) {
		super(uri);
		this.processorFactory = processorFactory;
	}

	/**
	 * Loads {@link Element}s from the input stream to pass to 
	 * @param inputStream
	 * @param options
	 * @return
	 * @throws IOException
	 */
	protected abstract Stream<Element> loadElements(InputStream inputStream, Map<?, ?> options) throws IOException;
	
	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		Map<Element, ProcessorInfo<P>> registry = processorFactory.createProcessors(loadElements(inputStream, options));
		getRoots(getSemanticElements(registry)).forEach(getContents()::add);
	}

	/**
	 * Retrieves semantic elements {@link EObject}s from the registry.
	 * @param registry
	 * @return
	 */
	protected abstract Stream<EObject> getSemanticElements(Map<Element, ProcessorInfo<P>> registry);

	/**
	 * Loads root objects from a stream of semantic elements. This implementation loads objects not contained in other objects. 
	 * @param registry
	 * @return
	 */
	protected Stream<? extends EObject> getRoots(Stream<EObject> semanticElements) {
		return semanticElements.filter(Objects::nonNull).filter(eObj -> eObj.eContainer() == null);
	}
	
}