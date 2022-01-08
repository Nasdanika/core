package org.nasdanika.diagram.gen;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.Util;
import org.nasdanika.common.DiagramGenerator.Dialect;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.ncore.Marker;

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
			String diagramURI = type.substring(DRAWIO_SCHEMA.length());
			URI uri = URI.createURI(diagramURI);
			
			URI markerBase = null;
			Marker marker = diagram.getMarker();
			if (marker != null && !Util.isBlank(marker.getLocation())) {
				markerBase = URI.createURI(marker.getLocation());
			}

			URI resourceBase = null;
			Resource resource = diagram.eResource();
			if (resource != null) {
				resourceBase = resource.getURI();
			}
			
			if (markerBase != null && diagramURI.startsWith("./")) {
				uri = uri.resolve(markerBase);
			} else if (resourceBase != null) {
				uri = uri.resolve(resourceBase);
			}
			
			if (uri.isFile()) {
				String fileStr = uri.toFileString();
				File diagramFile = new File(fileStr);
				if (diagramFile.exists()) {
					// TODO - merging - read, merge models, write if result is not null.
				} else {
					File container = diagramFile.getParentFile();
					if (container.isDirectory() || container.mkdirs()) {
						Files.write(diagramFile.toPath(), DefaultConverter.INSTANCE.toByteArray(createDrawioGenerator().generateModel(diagram)));
					}
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
