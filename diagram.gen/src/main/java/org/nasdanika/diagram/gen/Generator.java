package org.nasdanika.diagram.gen;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.json.JSONObject;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.DiagramGenerator.Dialect;
import org.nasdanika.diagram.Diagram;

/**
 * Generates diagram HTML delegating to different generators based on diagram type.
 * @author Pavel
 *
 */
public class Generator {
	
	public static Generator INSTANCE = new Generator();

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
					uri = uri.resolve(rURI);
				}
			}
			URL url = new URL(uri.toString());
			return generateDrawioHtml(url);
		}
		
		if (type.equals("drawio")) {
			return createDrawioGenerator().generateDiagram(diagram);
		}
		
		throw new IllegalArgumentException("Unsupported diagram type: " + type);
	}
	
	/**
	 * Generates draw.io HTML from diagram resource.
	 * @param diagram
	 * @return
	 * @throws IOException
	 */
	public String generateDrawioHtml(URL diagram) throws Exception {
		return getDiagramGenerator().generateDrawioDiagram(DefaultConverter.INSTANCE.stringContent(diagram));
	}
	
	protected DiagramGenerator getDiagramGenerator() {
		return DiagramGenerator.INSTANCE;
	}
	
	protected PlantumlGenerator createPlantumlGenerator() {
		return new PlantumlGenerator(getDiagramGenerator());
	}
	
	protected DrawioGenerator createDrawioGenerator() {
		return new DrawioGenerator(getDiagramGenerator());
	}

}
