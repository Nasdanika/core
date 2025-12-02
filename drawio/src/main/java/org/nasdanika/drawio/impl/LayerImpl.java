package org.nasdanika.drawio.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.Document.Context;
import org.nasdanika.drawio.Layer;
import org.nasdanika.drawio.LayerElement;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Rectangle;
import org.nasdanika.drawio.Tag;
import org.nasdanika.drawio.model.ModelFactory;
import org.nasdanika.persistence.Marker;
import org.w3c.dom.Element;

class LayerImpl<L extends Layer<L>> extends ModelElementImpl<L> implements Layer<L> {
	
	LayerImpl(
			Element element, 
			ModelImpl model, 
			int position,
			Context context) {
		super(element, model, position, context);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<LayerElement<?>> getChildren() {
		return (List) super.getChildren();
	}

	@Override
	public Node createNode() {
		Element nodeElement = element.getOwnerDocument().createElement("mxCell");
		nodeElement.setAttribute(ATTRIBUTE_ID, UUID.randomUUID().toString());
		nodeElement.setAttribute(ATTRIBUTE_PARENT, element.getAttribute(ATTRIBUTE_ID));
		nodeElement.setAttribute(ModelImpl.ATTRIBUTE_VERTEX, "1");
		element.getParentNode().appendChild(nodeElement);
		List<LayerElement<?>> elements = getElements();
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
		List<LayerElement<?>> elements = getElements();
		return (Connection) elements.get(elements.size() - 1);
	}
	
	org.nasdanika.drawio.model.Layer toModelLayer(
			ModelFactory factory, 
			Function<org.nasdanika.persistence.Marker, org.nasdanika.ncore.Marker> markerFactory,
			Function<org.nasdanika.drawio.Element<?>, CompletableFuture<EObject>> modelElementProvider,
			Function<Tag, org.nasdanika.drawio.model.Tag> tagProvider) {
		return toModelLayer(factory, factory.createLayer(), markerFactory, modelElementProvider, tagProvider);
	}
	
	protected <T extends org.nasdanika.drawio.model.Layer> T toModelLayer(
			ModelFactory factory,			
			T mElement,
			Function<Marker, org.nasdanika.ncore.Marker> markerFactory,
			Function<org.nasdanika.drawio.Element<?>, CompletableFuture<EObject>> modelElementProvider,
			Function<Tag, org.nasdanika.drawio.model.Tag> tagProvider) {
		
		toModelElement(mElement, markerFactory, modelElementProvider, tagProvider);

		EList<org.nasdanika.drawio.model.LayerElement> layerElements = ((org.nasdanika.drawio.model.Layer) mElement).getElements();
		for (LayerElement<?> layerElement: getElements()) {
			if (layerElement instanceof NodeImpl) {
				layerElements.add(((NodeImpl) layerElement).toModelNode(factory, markerFactory, modelElementProvider, tagProvider));
			} else {
				layerElements.add(((ConnectionImpl) layerElement).toModelConnection(factory, markerFactory, modelElementProvider, tagProvider));				
			}
		}		
		
		return mElement;
	}
	
//	<mxGraphModel dx="1408" dy="823" grid="1" gridSize="10" guides="1" tooltips="1" connect="1" arrows="1" fold="1" page="1" pageScale="1" pageWidth="850" pageHeight="1100" math="0" shadow="0">
//	  <root>
//	    <mxCell id="0" />
//	    <mxCell id="1" parent="0" />
//	    <mxCell id="pSct6YIava2kl6eoWyhF-1" value="Vertical Container" style="swimlane;whiteSpace=wrap;html=1;collapsible=0;" vertex="1" parent="1">
//	      <mxGeometry x="330" y="320" width="200" height="200" as="geometry" />
//	    </mxCell>
//	  </root>
//	</mxGraphModel>
	
	protected Node createNode(
			Layer<?> layer,
			org.nasdanika.graph.Node graphNode, 
			BiConsumer<org.nasdanika.graph.Node, Node> nodeConsumer, 
			BiConsumer<org.nasdanika.graph.Element, ModelElement<?>> configurator) {
		
		Node ret = layer.createNode();
		Rectangle geometry = ret.getGeometry();
		geometry.setWidth(150);
		geometry.setHeight(50);
		Collection<? extends org.nasdanika.graph.Element> children = graphNode.getChildren();
		boolean hasChildNodes = false;
		for (org.nasdanika.graph.Element child: children) {
			if (child instanceof org.nasdanika.graph.Node) {
				createNode(ret, (org.nasdanika.graph.Node) child, nodeConsumer, configurator);
				hasChildNodes = true;
			}
		}				
		if (hasChildNodes) {
			ret.setProperty(ATTRIBUTE_STYLE, "swimlane;whiteSpace=wrap;html=1;collapsible=0;container=1;");
			geometry.setWidth(400);
			geometry.setHeight(300);
		} else {
			geometry.setWidth(150);
			geometry.setHeight(50);
		}
		
		populate(graphNode, ret);
		if (configurator != null) {
			configurator.accept(graphNode, ret);
		}		
		if (nodeConsumer != null) {
			nodeConsumer.accept(graphNode, ret);
		}
		return ret;
	}
	
	protected void populate(org.nasdanika.graph.Element element, ModelElement<?> modelElement) {
		if (element instanceof Supplier) {
			Object value = ((Supplier<?>) element).get();
			if (value instanceof Map) {
				for (Entry<?, ?> ve: ((Map<?,?>) value).entrySet()) {
					Object entryKey = ve.getKey();
					Object entryValue = ve.getValue();
					if (entryKey instanceof String) {
						if (entryValue instanceof String) {
							modelElement.setProperty((String) entryKey, (String) entryValue);
						} else if (entryValue instanceof Boolean) {
							modelElement.setProperty((String) entryKey, (Boolean) entryValue ? "1" : "0");							
						}
					}
				}
				modelElement.setProperty(ATTRIBUTE_ID, ATTRIBUTE_VALUE);
			} else if (value instanceof String) {
				modelElement.setLabel((String) value);
			}
		}
	}

	@Override
	public void populate(org.nasdanika.graph.Element element, BiConsumer<org.nasdanika.graph.Element, ModelElement<?>> configurator) {
		Map<org.nasdanika.graph.Node, Node> nodeMap = new HashMap<>();
		if (element instanceof org.nasdanika.graph.Node) {
			createNode(this, (org.nasdanika.graph.Node) element, nodeMap::put, configurator);
		} else if (!(element instanceof org.nasdanika.graph.Connection)) {
			for (org.nasdanika.graph.Element child: element.getChildren()) {
				if (child instanceof org.nasdanika.graph.Node) {
					createNode(this, (org.nasdanika.graph.Node) child, nodeMap::put, configurator);
				}
			}
		}
		
		Function<org.nasdanika.graph.Node, Node> nodeProvider = n -> nodeMap.computeIfAbsent(n, nn -> createNode(this, nn, null, configurator));
		element.traverse(el -> {
			if (el instanceof org.nasdanika.graph.Connection) {
				org.nasdanika.graph.Connection graphConnection = (org.nasdanika.graph.Connection) el;
				org.nasdanika.graph.Node graphSource = graphConnection.getSource();
				Node source = graphSource == null ? null : nodeProvider.apply(graphSource);
				org.nasdanika.graph.Node graphTarget = graphConnection.getTarget();
				Node target = graphTarget == null ? null : nodeProvider.apply(graphTarget);
				Connection connection = createConnection(source, target);
				populate(graphConnection, connection);
				if (configurator != null) {
					configurator.accept(graphConnection, connection);
				}
			}
		});
	}

}
