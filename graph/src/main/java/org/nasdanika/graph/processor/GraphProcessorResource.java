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
public abstract class GraphProcessorResource extends ResourceImpl {
	
	private ProcessorFactory<Supplier<EObject>, ?, ?> processorFactory;
	
	protected GraphProcessorResource(URI uri, ProcessorFactory<Supplier<EObject>, ?, ?> processorFactory) {
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
		Map<Element, ProcessorInfo<Supplier<EObject>>> registry = processorFactory.createProcessors(loadElements(inputStream, options));
		getRoots(registry).forEach(getContents()::add);
	}

	/**
	 * Loads root objects from the registry. This implementation loads objects not contained in other objects. 
	 * @param registry
	 * @return
	 */
	protected Stream<EObject> getRoots(Map<Element, ProcessorInfo<Supplier<EObject>>> registry) {
		return registry
				.values()
				.stream()
				.map(ProcessorInfo::getProcessor)
				.filter(Objects::nonNull)
				.map(Supplier::get)
				.filter(Objects::nonNull)
				.filter(eObj -> eObj.eContainer() == null);
	}
	
}