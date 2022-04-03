package org.nasdanika.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONObject;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

public class DiagramGeneratorImpl implements DiagramGenerator {
	
	@Override
	public String generateDiagram(String spec, Dialect dialect) throws IOException {
		if (dialect == Dialect.DRAWIO) {
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
		
		if (dialect == Dialect.MERMAID) {
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
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		StringBuilder sb = new StringBuilder("@start")
				.append(dialect.name().toLowerCase())
				.append(System.lineSeparator())
				.append(spec)
				.append(System.lineSeparator())
				.append("@end")
				.append(dialect.name().toLowerCase())
				.append(System.lineSeparator());
		
		SourceStringReader reader = new SourceStringReader(sb.toString());
		
		FileFormatOption fileFormatOption = new FileFormatOption(FileFormat.PNG);
		reader.outputImage(baos, 0, fileFormatOption);		
		String diagramCMap = reader.getCMapData(0, fileFormatOption);
		baos.close();

		StringBuilder ret = new StringBuilder("<img src=\"data:image/png;base64, ");
		ret
			.append(Base64.getEncoder().encodeToString(baos.toByteArray()))
			.append("\"");
		
		if (Util.isBlank(diagramCMap)) {
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
	}

	protected String getDrawioViewer() {
		return "https://viewer.diagrams.net/js/viewer-static.min.js";
	}
	
	protected String getMermaidInitialize() {
		return "mermaid.initialize({ startOnLoad: true, securityLevel: 'loose' });";
	}
}