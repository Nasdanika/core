package org.nasdanika.common;

import java.io.IOException;

import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONObject;

public class DiagramGeneratorImpl implements DiagramGenerator {
	
	@Override
	public String generateDiagram(String spec, String dialect) throws IOException {
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
		
		throw new UnsupportedOperationException("Unsupported dialect: " + dialect);
	}

	protected String getDrawioViewer() {
		return "https://cdn.jsdelivr.net/gh/Nasdanika/drawio@dev/src/main/webapp/js/viewer-static.min.js";
	}
	
	protected String getMermaidInitialize() {
		return "mermaid.initialize({ startOnLoad: true, securityLevel: 'loose' });";
	}

	@Override
	public boolean isSupported(String dialect) {
		return DRAWIO_DIALECT.equals(dialect) || MERMAID_DIALECT.equals(dialect);
	}
}