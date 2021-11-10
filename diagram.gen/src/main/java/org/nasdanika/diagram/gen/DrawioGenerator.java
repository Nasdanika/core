package org.nasdanika.diagram.gen;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.DiagramGenerator.Dialect;
import org.nasdanika.common.Util;
import org.nasdanika.diagram.Diagram;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.mxgraph.io.mxCodec;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.util.mxDomUtils;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.view.mxGraph;

/**
 * Generates Drawio diagrams.
 * @author Pavel
 *
 */
public class DrawioGenerator {

	private DiagramGenerator diagramGenerator;

	public DrawioGenerator(DiagramGenerator diagramGenerator) {
		this.diagramGenerator = diagramGenerator;
	}
	
	/**
	 * Generates diagram HTML using provided diagram generator.
	 * @param diagram
	 * @param diagramGenerator
	 * @return
	 * @throws Exception 
	 */
	public String generateDiagram(Diagram diagram) throws Exception {
		return diagramGenerator.generateDrawioDiagram(generateModel(diagram));
	}

	/**
	 * Generates diagram model in .drawio format which can be further converted to HTML or stored to a file for manual editing.
	 * @param diagram
	 * @return
	 */
	public String generateModel(Diagram diagram) throws Exception {
		mxCodec codec = new mxCodec();
		Node encodedNode = codec.encode(generateGraph(diagram).getModel());
		return encodeDiagram(encodedNode, diagram.getUuid(), diagram.getName());
	}
	
	/**
	 * Encodes diagram node to .drawio format.
	 * @param diagramNode
	 * @return
	 * @throws Exception
	 */
	protected String encodeDiagram(Node diagramNode, String id, String name) throws Exception {
		String templateSource = DefaultConverter.INSTANCE.stringContent(getClass().getResource("template.drawio"));
		Document xmlDocument = mxXmlUtils.parseXml(templateSource);
        Element diagram = (Element) xmlDocument.getElementsByTagName("diagram").item(0);
        if (!Util.isBlank(id)) {
        	diagram.setAttribute("id", id);
        }
        if (!Util.isBlank(name)) {
        	diagram.setAttribute("name", name);
        }
        diagram.appendChild(xmlDocument.importNode(diagramNode, true));
        return mxXmlUtils.getXml(xmlDocument);
	}
	
	protected mxGraph generateGraph(Diagram diagram) throws Exception {
		mxGraph graph = new mxGraph(/* graphModel */);
		
		mxIGraphModel graphModel = graph.getModel();
		graphModel.beginUpdate();
		Object parent = graph.getDefaultParent();
		try	{
			Document doc = mxDomUtils.createDocument();
			Element v1uo = doc.createElement("UserObject");
			v1uo.setAttribute("label", "Hren\nsobachya");
			v1uo.setAttribute("link", "https://nasdanika.org");
			v1uo.setAttribute("uri", "nasdanika://hmmm");
			mxCell v1 = (mxCell) graph.insertVertex(parent, null, v1uo, 40, 40, 160, 60);
			v1.setStyle("verticalAlign=top;fillColor=#fefece");
			Object v11 = graph.insertVertex(v1, null, "Purum", 20, 20, 80, 30);
			Object v2 = graph.insertVertex(parent, null, "World!", 280, 250, 160, 60);
			mxCell v21 = (mxCell) graph.insertVertex(parent, null, "Govno", 20, 20, 80, 30);
			v21.setStyle("fillColor=#fefece");
			graph.insertEdge(parent, null, "Edge", v1, v2);
			graph.insertEdge(parent, null, "Cross Edge", v11, v21);
			
			mxIGraphLayout layout = new mxCircleLayout(graph);
			layout.execute(parent);
		} finally {
			graphModel.endUpdate();
		}		
		return graph;
	}
	
	// TODO - merging of existing drawio model
	// a) Generate a model from the diagram and then copy geometry (layout) from the existing diagram.
	// b) Modify the existing diagram - drop removed elements, add new, update existing - label, link.
	
	/**
	 * Decodes compressed text content of the diagram element.
	 * @param diagramSource
	 * @return
	 * @throws Exception
	 */
	protected String decodeDiagram(String diagramSource) throws Exception {
		Document xmlDocument = mxXmlUtils.parseXml(diagramSource);
        Node diagram = xmlDocument.getElementsByTagName("diagram").item(0);
        String textContent = diagram.getTextContent();

        byte[] compressed = Base64.getDecoder().decode(textContent);
        byte[] decompressed = inflate(compressed);
        String decompressedStr = new String(decompressed, getCharset());
        return URLDecoder.decode(decompressedStr, getCharset());
	}
		
    private byte[] inflate(byte[] content) throws Exception {
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	try (ByteArrayInputStream source = new ByteArrayInputStream(content); OutputStream target = new InflaterOutputStream(baos, new Inflater(true))) {
            byte[] buf = new byte[8192];
            int length;
            while ((length = source.read(buf)) > 0) {
                target.write(buf, 0, length);
            }
    	}
    	
    	return baos.toByteArray();
    }
    
    /**
     * {@link Charset} for encoding/decoding.
     * @return This implementation returns UTF-8.
     */
    protected Charset getCharset() {
    	return StandardCharsets.UTF_8;
    }

}
