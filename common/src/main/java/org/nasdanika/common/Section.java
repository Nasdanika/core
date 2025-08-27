package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jsoup.Jsoup;
import org.nasdanika.ai.SectionReference;

/**
 * A section of structured narration
 */
public class Section extends SectionReference {
	
	private List<Section> children = Collections.synchronizedList(new ArrayList<>());
	private List<Content> contents = Collections.synchronizedList(new ArrayList<>());
	
	public Section() {
		
	}
	
	public Section(String title, String id) {
		super(title, id);
	}
	
	public List<Section> getChildren() {
		return children;
	}
	
	public List<Content> getContents() {
		return contents;
	}
	
	public String toMarkdown(int headerLevel) {
		// TODO - headers, {id}
	}
		
	public String toHtml(int headerLevel) {
		// TODO - headers, name tags
	}
	
	public String toText() {
		// TODO - headers on a new line separated with new lines, maybe underlined, id in parenthesis
	}
	
	public String toString(String format) {
		return switch (format.toLowerCase()) {
			case "markdown", "text/markdown" -> toMarkdown(1);
			case "html", "text/html" -> toHtml(1);
			case "text", "text/plain" -> toText();
			default -> throw new IllegalArgumentException("Unsupported format: " + format);
		};
	}
	

}
