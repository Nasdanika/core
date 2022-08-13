package org.nasdanika.diagram.gen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.Util;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.drawio.Document;
import org.nasdanika.ncore.Marker;

/**
 * Generates diagram HTML delegating to different generators based on diagram type.
 * @author Pavel
 *
 */
public class Generator {
	
	public static Generator INSTANCE = new Generator();

	public static final String PLANTUML_SCHEMA = "plantuml:";
	public static final String DRAWIO_SCHEMA = "drawio:";
//	public static final String MERMAID_SCHEMA = "mermaid:";

	public String generate(Diagram diagram) {
		String type = diagram.getType();
		try {
			if (type.startsWith(PLANTUML_SCHEMA)) {
				String dialect = type.substring(PLANTUML_SCHEMA.length()).toLowerCase();
				return createPlantumlGenerator().generateDiagram(diagram, dialect);
			}
			
	//		if (type.startsWith(MERMAID_SCHEMA)) {
	//			String dialect = type.substring(MERMAID_SCHEMA.length()).toLowerCase();
	//			return createMermaidGenerator().generateDiagram(diagram, dialect);
	//		}
			
			if (type.startsWith(DRAWIO_SCHEMA)) {
				String diagramURI = type.substring(DRAWIO_SCHEMA.length());
				URI uri = URI.createURI(diagramURI);
				
				URI markerBase = null;
				for (Marker marker: diagram.getMarkers()) { 
					if (marker != null && !Util.isBlank(marker.getLocation())) { // Finds a first marker with location
						markerBase = URI.createURI(marker.getLocation());
						break;
					}				
				}
	
				URI resourceBase = null;
				Resource resource = diagram.eResource();
				if (resource != null) {
					resourceBase = resource.getURI();
				}
				
				if (markerBase != null && markerBase.hasAbsolutePath() && diagramURI.startsWith("./")) {
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
							Document document = createDrawioGenerator().generateModel(diagram);
							Files.write(diagramFile.toPath(), DefaultConverter.INSTANCE.toByteArray(document.save(true)));
						}
					}
				}						
				
				return generateDrawioHtml(uri);
			}
			
			if (type.equals("drawio")) {
				return createDrawioGenerator().generateDiagram(diagram);
			}
		} catch (NasdanikaException e) {
			throw e;
		} catch (Exception e) {
			throw new NasdanikaException(e);
		}
		
		throw new IllegalArgumentException("Unsupported diagram type: " + type);
	}
	
	/**
	 * Generates draw.io HTML from diagram resource.
	 * @param diagram
	 * @return
	 * @throws IOException
	 */
	public String generateDrawioHtml(URI diagram) {
		try {
			return getDiagramGenerator().generateDrawioDiagram(DefaultConverter.INSTANCE.stringContent(diagram));
		} catch (IOException e) {
			throw new NasdanikaException(e);
		}
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
