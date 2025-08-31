package org.nasdanika.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
		StringBuilder sb = new StringBuilder();
		if (headerLevel > 6) {
			if (Util.isBlank(getTitle())) {
				if (!Util.isBlank(getId())) {
					sb
						.append("<a name='")
						.append(getId())
						.append("'/>");					
				}
			} else {
				sb
					.append("**")
					.append(getTitle())
					.append("**");
				
				if (!Util.isBlank(getId())) {
					sb
						.append(" <a name='")
						.append(getId())
						.append("'/>");										
				}
				sb.append(System.lineSeparator());								
			}
		} else {
			if (Util.isBlank(getTitle())) {
				if (!Util.isBlank(getId())) {
					sb
						.append("<a name='")
						.append(getId())
						.append("'/>");					
				}				
			} else {
				for (int i = 0; i < headerLevel; ++i) {
					sb.append("#");
				}
				
				sb.append(" ").append(getTitle());
				
				if (!Util.isBlank(getId())) {
					sb.append(" {" + getId() + "}");
				}
				
				sb
					.append(System.lineSeparator())
					.append(System.lineSeparator());					
			}
		}
		
		for (Content content: getContents()) {
			sb
				.append(content.toMarkdown())
				.append(System.lineSeparator())
				.append(System.lineSeparator());			
		}
		
		for (Section child: getChildren()) {
			sb
				.append(System.lineSeparator())
				.append(System.lineSeparator())								
				.append(child.toMarkdown(headerLevel + 1));
		}
		
		return sb.toString();
	}
		
	public String toHtml(int headerLevel) {
		StringBuilder sb = new StringBuilder();
		if (headerLevel > 6) {
			if (Util.isBlank(getTitle())) {
				if (!Util.isBlank(getId())) {
					sb
						.append("<a name='")
						.append(getId())
						.append("'/>");					
				}
			} else {
				sb
					.append(System.lineSeparator())
					.append("<b>")
					.append(getTitle())
					.append("</b>");
				
				if (!Util.isBlank(getId())) {
					sb
						.append(" <a name='")
						.append(getId())
						.append("'/>");										
				}
				sb.append(System.lineSeparator());								
			}
		} else {
			if (Util.isBlank(getTitle())) {
				if (!Util.isBlank(getId())) {
					sb
						.append("<a name='")
						.append(getId())
						.append("'/>");					
				}				
			} else {
				sb
					.append(System.lineSeparator())
					.append("<h")
					.append(headerLevel)
					.append(">")
					.append(getTitle())
					.append("</h")
					.append(headerLevel)
					.append(">");
				
				
				if (!Util.isBlank(getId())) {
					sb
						.append(" <a name='")
						.append(getId())
						.append("'/>");										
				}
				
				sb.append(System.lineSeparator());					
			}
		}
		
		for (Content content: getContents()) {
			sb
				.append(content.toHtml())
				.append(System.lineSeparator())
				.append(System.lineSeparator());			
		}
		
		for (Section child: getChildren()) {
			sb.append(child.toHtml(headerLevel + 1));
		}
		
		return sb.toString();
	}
	
	public String toText(int headerLevel) {
		StringBuilder sb = new StringBuilder();
		
		if (!Util.isBlank(getTitle())) {
			sb.append(getTitle());
			
			if (!Util.isBlank(getId())) {
				sb
					.append(" (")
					.append(getId())
					.append(")");								
			}
			int titleLength = sb.length();
			sb.append(System.lineSeparator());
			
			char underscore = switch (headerLevel) {
				case 1 -> '=';
				case 2 -> '-';
				default -> '.';
			};
			
			for (int i = 0; i < titleLength; ++i) {
				sb.append(underscore);
			}
			sb
				.append(System.lineSeparator())
				.append(System.lineSeparator());			
		}
		
		for (Content content: getContents()) {
			sb
				.append(content.toText())
				.append(System.lineSeparator())
				.append(System.lineSeparator());			
		}
		
		for (Section child: getChildren()) {
			sb
				.append(System.lineSeparator())
				.append(System.lineSeparator())
				.append(child.toText(headerLevel + 1));
		}
		
		return sb.toString();
	}
	
	public String toString(String format) {
		return switch (format.toLowerCase()) {
			case Content.MARKDOWN, Content.TEXT_MARKDOWN -> toMarkdown(1);
			case Content.HTML, Content.TEXT_HTML -> toHtml(1);
			case Content.TEXT, Content.TEXT_PLAIN -> toText(1);
			default -> throw new IllegalArgumentException("Unsupported format: " + format);
		};
	}

}
