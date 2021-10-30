package org.nasdanika.diagram.gen;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.json.JSONObject;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.DiagramGenerator.Dialect;
import org.nasdanika.diagram.Diagram;

public class Generator {

	private static final String PLANTUML_SCHEMA = "plantuml:";
	private static final String DRAWIO_SCHEMA = "drawio:";

	public String generate(Diagram diagram) throws Exception {
		String type = diagram.getType();
		if (type == null) {
			type = "plantuml:uml";
		}
		if (type.startsWith(PLANTUML_SCHEMA)) {
			Dialect dialect = Dialect.valueOf(type.substring(PLANTUML_SCHEMA.length()).toUpperCase());
			return createPlantumlGenerator().generateDiagram(diagram, dialect);
		}
		
		if (type.startsWith(DRAWIO_SCHEMA)) {
			URI uri = URI.createURI(type.substring(DRAWIO_SCHEMA.length()));
			Resource dResource = diagram.eResource();
			if (dResource != null) {
				URI rURI = dResource.getURI();
				if (rURI != null) {
					uri.resolve(rURI);
				}
			}
			URL url = new URL(uri.toString());
			return generateDrawioHtml(url);
		}
		
		throw new IllegalArgumentException("Unsupported diagram type: " + type);
	}
	
	/**
	 * Generates draw.io HTML from diagram resource.
	 * @param diagram
	 * @return
	 * @throws IOException
	 */
	public String generateDrawioHtml(URL diagram) throws IOException {
		JSONObject data = new JSONObject();
		data.put("highlight", "#0000ff");
		data.put("nav", true);
		data.put("resize",true);
		data.put("toolbar", "zoom layers tags lightbox");
		data.put("edit","_blank");
		
		String mxFile = DefaultConverter.INSTANCE.stringContent(diagram);
		data.put("xml", mxFile);
		
		String diagramDiv = "<div class=\"mxgraph\" style=\"max-width:100%;border:1px solid transparent;\" data-mxgraph=\"" + StringEscapeUtils.escapeHtml4(data.toString()) + "\"></div>";
		String script = "<script type=\"text/javascript\" src=\"https://viewer.diagrams.net/js/viewer-static.min.js\"></script>";
		return diagramDiv + System.lineSeparator() + script;		
	}
	
	protected PlantumlGenerator createPlantumlGenerator() {
		return new PlantumlGenerator();
	}

}
