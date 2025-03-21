package org.nasdanika.drawio.impl;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.LayerElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Tag;
import org.nasdanika.drawio.model.ModelFactory;
import org.nasdanika.persistence.Marker;
import org.w3c.dom.Element;

class LayerImpl extends ModelElementImpl implements Layer {
	
	LayerImpl(Element element, ModelImpl model) {
		super(element, model);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<LayerElement> getChildren() {
		return (List) super.getChildren();
	}

	@Override
	public Node createNode() {
		Element nodeElement = element.getOwnerDocument().createElement("mxCell");
		nodeElement.setAttribute(ATTRIBUTE_ID, UUID.randomUUID().toString());
		nodeElement.setAttribute(ATTRIBUTE_PARENT, element.getAttribute(ATTRIBUTE_ID));
		nodeElement.setAttribute(ModelImpl.ATTRIBUTE_VERTEX, "1");
		element.getParentNode().appendChild(nodeElement);
		List<LayerElement> elements = getElements();
		return (Node) elements.get(elements.size() - 1);
	}

	@Override
	public Connection createConnection(Node source, Node target) {
		Element connectionElement = element.getOwnerDocument().createElement("mxCell");
		connectionElement.setAttribute(ATTRIBUTE_ID, UUID.randomUUID().toString());
		connectionElement.setAttribute(ATTRIBUTE_PARENT, element.getAttribute(ATTRIBUTE_ID));
		connectionElement.setAttribute(ModelImpl.ATTRIBUTE_EDGE, "1");
		if (source != null) {
			connectionElement.setAttribute(ATTRIBUTE_SOURCE, source.getElement().getAttribute(ATTRIBUTE_ID));
		}
		if (target != null) {
			connectionElement.setAttribute(ATTRIBUTE_TARGET, target.getElement().getAttribute(ATTRIBUTE_ID));
		}
		
		Element geometryElement = element.getOwnerDocument().createElement("mxGeometry");
		connectionElement.appendChild(geometryElement);
		geometryElement.setAttribute("relative", "1");
		geometryElement.setAttribute("as", "geometry");
		
		element.getParentNode().appendChild(connectionElement);
		List<LayerElement> elements = getElements();
		return (Connection) elements.get(elements.size() - 1);
	}
	
	org.nasdanika.drawio.model.Layer toModelLayer(
			ModelFactory factory, 
			Function<org.nasdanika.persistence.Marker, org.nasdanika.ncore.Marker> markerFactory,
			Function<org.nasdanika.drawio.Element, CompletableFuture<EObject>> modelElementProvider,
			Function<Tag, org.nasdanika.drawio.model.Tag> tagProvider) {
		return toModelLayer(factory, factory.createLayer(), markerFactory, modelElementProvider, tagProvider);
	}
	
	protected <T extends org.nasdanika.drawio.model.Layer> T toModelLayer(
			ModelFactory factory,			
			T mElement,
			Function<Marker, org.nasdanika.ncore.Marker> markerFactory,
			Function<org.nasdanika.drawio.Element, CompletableFuture<EObject>> modelElementProvider,
			Function<Tag, org.nasdanika.drawio.model.Tag> tagProvider) {
		
		toModelElement(mElement, markerFactory, modelElementProvider, tagProvider);

		EList<org.nasdanika.drawio.model.LayerElement> layerElements = ((org.nasdanika.drawio.model.Layer) mElement).getElements();
		for (LayerElement layerElement: getElements()) {
			if (layerElement instanceof NodeImpl) {
				layerElements.add(((NodeImpl) layerElement).toModelNode(factory, markerFactory, modelElementProvider, tagProvider));
			} else {
				layerElements.add(((ConnectionImpl) layerElement).toModelConnection(factory, markerFactory, modelElementProvider, tagProvider));				
			}
		}		
		
		return mElement;
	}

}
