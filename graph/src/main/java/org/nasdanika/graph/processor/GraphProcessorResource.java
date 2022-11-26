package org.nasdanika.graph.processor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;


/**
 * Base class for resource which load their contents by creating a {@link Stream} of {@link Element}'s from a resource {@link URI} and then uses {@link ProcessorFactory} to create {@link Supplier}'s of {@link EObject} with {@link ProcessorFactory}. 
 * @author Pavel
 *
 */
public abstract class GraphProcessorResource<P> extends ResourceImpl {
	
	protected abstract ProcessorFactory<P, ?, ?> getProcessorFactory();
	
	protected GraphProcessorResource(URI uri) {
		super(uri);
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
		Map<Element, ProcessorInfo<P>> registry = getProcessorFactory().createProcessors(loadElements(inputStream, options), getProgressMonitor());
		getSemanticElements(registry).filter(this::isRoot).forEach(getContents()::add);
	}

	protected ProgressMonitor getProgressMonitor() {
		return new NullProgressMonitor();
	}

	/**
	 * Retrieves semantic elements {@link EObject}s from the registry.
	 * @param registry
	 * @return
	 */
	protected abstract Stream<? extends EObject> getSemanticElements(Map<Element, ProcessorInfo<P>> registry);
	
	/**
	 * @param semanticElement 
	 * @return true if the argument semantic element shall be added to the resource contents.
	 * This implementation returns true if the semantic element is not null and is not contained by another semantic element, i.e. its eContainer() returns null.
	 */
	protected boolean isRoot(EObject semanticElement) {
		return semanticElement != null && semanticElement.eContainer() == null;
	}
			
}