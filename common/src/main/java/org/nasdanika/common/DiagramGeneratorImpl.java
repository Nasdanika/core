package org.nasdanika.common;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.UUID;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONObject;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

public class DiagramGeneratorImpl implements DiagramGenerator {
	
	private boolean isPlantUmlSvg;
	
	public DiagramGeneratorImpl() {
		this(false);
	}
	
	public DiagramGeneratorImpl(boolean isPlantUmlSvg) {
		this.isPlantUmlSvg = isPlantUmlSvg;
	}
	
	@Override
	public String generateDiagram(String spec, String dialect) {
		if (DRAWIO_DIALECT.equals(dialect)) {
			JSONObject data = new JSONObject();
			data.put("highlight", "#0000ff");
			data.put("nav", true);
			data.put("resize",true);
			data.put("toolbar", "zoom layers tags lightbox");
			data.put("edit","_blank");
			
			data.put("xml", spec);
			
			String diagramDiv = "<div class=\"mxgraph\" style=\"max-width:100%;border:1px solid transparent;\" data-mxgraph=\"" + StringEscapeUtils.escapeHtml4(data.toString()) + "\"></div>";
			String drawioViewer = getDrawioViewer();
			if (Util.isBlank(drawioViewer)) {
				return diagramDiv;
			}
			String script = "<script type=\"text/javascript\" src=\"" + drawioViewer + "\"></script>";
			return diagramDiv + System.lineSeparator() + script;		
		}
		
		if (MERMAID_DIALECT.equals(dialect)) {
			String mermaidDiv = "<div class=\"mermaid\">"  + System.lineSeparator() + spec + System.lineSeparator() + "</div>";
			String mermaidInitialize = getMermaidInitialize();
			if (Util.isBlank(mermaidInitialize)) {
				return mermaidDiv;
			}
			return mermaidDiv
					+ System.lineSeparator()
					+ "<script>"
					+ System.lineSeparator()
					+ mermaidInitialize
					+ System.lineSeparator()
					+ "</script>";					
		}
		
		if ( DiagramGenerator.UML_DIALECT.equals(dialect)
				|| DiagramGenerator.GANTT_DIALECT.equals(dialect)
				|| DiagramGenerator.MINDMAP_DIALECT.equals(dialect)
				|| DiagramGenerator.SALT_DIALECT.equals(dialect)
				|| DiagramGenerator.WBS_DIALECT.equals(dialect)) {
			
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				StringBuilder sb = new StringBuilder("@start")
						.append(dialect)
						.append(System.lineSeparator())
						.append(spec)
						.append(System.lineSeparator())
						.append("@end")
						.append(dialect)
						.append(System.lineSeparator());
				
				SourceStringReader reader = new SourceStringReader(sb.toString());
				
				FileFormatOption fileFormatOption = new FileFormatOption(isPlantUmlSvg ? FileFormat.SVG : FileFormat.PNG);
				reader.outputImage(baos, 0, fileFormatOption);		
				baos.close();
				
				if (isPlantUmlSvg) {
					return new String(baos.toByteArray());				
				}
				
				String diagramCMap = reader.getCMapData(0, fileFormatOption);

				StringBuilder ret = new StringBuilder("<img src=\"data:image/png;base64, ");
				ret
					.append(Base64.getEncoder().encodeToString(baos.toByteArray()))
					.append("\"");
				
				if (org.nasdanika.common.Util.isBlank(diagramCMap)) {
					ret.append("/>");
					return ret.toString();			
				}
				
				String openingTag = "<map id=\"plantuml_map\" name=\"plantuml_map\">";
				if (diagramCMap.startsWith(openingTag)) {
					String mapId = "plantuml_map_" + UUID.randomUUID().toString();
					ret			
					.append(" usemap=\"#")
					.append(mapId)
					.append("\"/>")
					.append(System.lineSeparator())
					.append("<map id=\"")
					.append(mapId)
					.append("\" name=\"")
					.append(mapId)
					.append("\">")
					.append(diagramCMap.substring(openingTag.length()));
					
				} else {				
					ret			
						.append(" usemap=\"#plant_uml_map\"/>")
						.append(System.lineSeparator())
						.append(diagramCMap);
					return ret.toString();
				}
				return ret.toString();			
			} catch (Exception e) {
				return "<div class=\"nsd-error\">Error during diagram rendering: " + e + "</div>";
			}
		}
		
		throw new UnsupportedOperationException("Unsupported dialect: " + dialect);
	}

	protected String getDrawioViewer() {		
		return "https://cdn.jsdelivr.net/gh/jgraph/drawio@v22.1.11/src/main/webapp/js/viewer.min.js"; // Last known working viewer, update during release.
		/*
		 * An alternative in a page (template) script:
		 * window.DRAWIO_BASE_URL = "...";
		 * GraphViewer.prototype.editBlankUrl = window.DRAWIO_BASE_URL;
		 */
	}
	
	protected String getMermaidInitialize() {
		return "mermaid.initialize({ startOnLoad: true, securityLevel: 'loose' });";
	}

	@Override
	public boolean isSupported(String dialect) {
		return DRAWIO_DIALECT.equals(dialect) 
				|| MERMAID_DIALECT.equals(dialect)
				|| DiagramGenerator.UML_DIALECT.equals(dialect)
				|| DiagramGenerator.GANTT_DIALECT.equals(dialect)
				|| DiagramGenerator.MINDMAP_DIALECT.equals(dialect)
				|| DiagramGenerator.SALT_DIALECT.equals(dialect)
				|| DiagramGenerator.WBS_DIALECT.equals(dialect);
	}
}