package org.nasdanika.graph.processor.emf;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorFactory;
import org.nasdanika.graph.processor.ProcessorInfo;


/**
 * Base class for resource which load their contents by creating a {@link Stream} of {@link Element}'s from a resource {@link URI} and then uses {@link ProcessorFactory} to create {@link Supplier}'s of {@link EObject} with {@link ProcessorFactory}. 
 * @author Pavel
 *
 */
public abstract class GraphProcessorResource<P, T extends EObject> extends ResourceImpl {
	
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
	protected abstract Stream<? extends Element> loadElements(InputStream inputStream, Map<?, ?> options) throws IOException;
	
	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		List<? extends Element> elements = loadElements(inputStream, options).collect(Collectors.toList());
		Map<Element, ProcessorInfo<P>> registry = getProcessorFactory().createProcessors(elements.stream(), getProgressMonitor());
		List<T> roots = getSemanticElements(registry).filter(this::isRoot).collect(Collectors.toList()); // TODO .forEach(getContents()::add) - fails with concurrent modification exception
		getContents().addAll(roots);
		for (Element element: elements) {
			ProcessorInfo<P> info = registry.get(element);
			ProcessorConfig<P> config = info.getConfig();
			if (config instanceof NodeProcessorConfig) {
				eAdapters().add(new NodeProcessorConfigAdapter<>((NodeProcessorConfig<P, ?, ?>) config));
			} else if (config instanceof ConnectionProcessorConfig) {
				eAdapters().add(new ConnectionProcessorConfigAdapter<>((ConnectionProcessorConfig<P, ?, ?>) config));				
			} else {
				eAdapters().add(new ProcessorConfigAdapter<>(config));				
			}
		}
	}

	protected abstract ProgressMonitor getProgressMonitor();
	
	@SuppressWarnings("unchecked")
	protected Stream<T> getSemanticElements(P processor) {
		if (processor instanceof SemanticProcessor) {
			return ((SemanticProcessor<T>) processor).getSemanticElements().stream();
		}
		return Stream.empty();
	};

	/**
	 * Retrieves semantic elements {@link EObject}s from the registry.
	 * @param registry
	 * @return
	 */
	protected Stream<T> getSemanticElements(Map<Element, ProcessorInfo<P>> registry) {
		return registry.values().stream().map(pi -> pi.getProcessor()).filter(Objects::nonNull).flatMap(this::getSemanticElements);
	}
	
	/**
	 * @param semanticElement 
	 * @return true if the argument semantic element shall be added to the resource contents.
	 * This implementation returns true if the semantic element is not null and is not contained by another semantic element, i.e. its eContainer() returns null.
	 */
	protected boolean isRoot(EObject semanticElement) {
		return semanticElement != null && semanticElement.eContainer() == null;
	}
			
}