package org.nasdanika.drawio.impl;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.Root;
import org.nasdanika.drawio.Tag;
import org.nasdanika.drawio.model.ModelFactory;
import org.w3c.dom.Element;

class RootImpl extends ModelElementImpl implements Root {

	RootImpl(Element element, ModelImpl model) {
		super(element, model);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<Layer> getChildren() {
		return (List) super.getChildren();
	}

	@Override
	public List<Layer> getLayers() {
		return (List<Layer>) getChildren();
	}

	@Override
	public Layer createLayer() {
		Element layerElement = element.getOwnerDocument().createElement("mxCell");
		layerElement.setAttribute(ATTRIBUTE_ID, UUID.randomUUID().toString());
		layerElement.setAttribute(ATTRIBUTE_PARENT, element.getAttribute(ATTRIBUTE_ID));
		element.getParentNode().appendChild(layerElement);
		List<Layer> layers = getLayers();
		return layers.get(layers.size() - 1);
	}

	@Override
	public URI getURI() {
		URI modelURI = getModel().getURI();
		return modelURI == null ? URI.createURI(getId()) : modelURI.appendFragment(modelURI.fragment() + "/" + URLEncoder.encode(getId(), StandardCharsets.UTF_8));
	}
	
	org.nasdanika.drawio.model.Root toModelRoot(
			ModelFactory factory, 
			Function<org.nasdanika.persistence.Marker, org.nasdanika.ncore.Marker> markerFactory,
			Function<org.nasdanika.drawio.Element, CompletableFuture<EObject>> modelElementProvider,
			Function<Tag, org.nasdanika.drawio.model.Tag> tagProvider) {
		org.nasdanika.drawio.model.Root mRoot = toModelElement(factory.createRoot(), markerFactory, modelElementProvider, tagProvider);
		modelElementProvider.apply(this).complete(mRoot);
		for (Layer layer: getLayers()) {
			mRoot.getLayers().add(((LayerImpl) layer).toModelLayer(factory, markerFactory, modelElementProvider, tagProvider));
		}
		return mRoot;
	}	

}
