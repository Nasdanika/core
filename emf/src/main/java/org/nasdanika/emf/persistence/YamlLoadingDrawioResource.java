package org.nasdanika.emf.persistence;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.drawio.DrawioResource;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorFactory;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.persistence.ObjectLoader;

/**
 * Loads EObjects Drawio diagram resources using {@link YamlLoadingDrawioEObjectFactory}. 
 * @author Pavel
 *
 */
abstract class YamlLoadingDrawioResource extends DrawioResource<EObject> {

	public YamlLoadingDrawioResource(URI uri) {
		super(uri);
	}

	@Override
	protected ProcessorFactory<EObject, ?, ?> getProcessorFactory() {
		return new YamlLoadingDrawioEObjectFactory<EObject>() {

			@Override
			protected ObjectLoader getLoader() {
				return YamlLoadingDrawioResource.this.getLoader();
			}
			
			@Override
			protected Context getContext() {
				return YamlLoadingDrawioResource.this.getContext();
			}
			
		};
	}
	
	protected abstract ObjectLoader getLoader();	

	protected abstract Context getContext();	

	@Override
	protected Stream<EObject> getSemanticElements(Map<Element, ProcessorInfo<EObject>> registry) {
		return registry.values().stream().map(pi -> pi.getProcessor()).filter(Objects::nonNull);
	}

}