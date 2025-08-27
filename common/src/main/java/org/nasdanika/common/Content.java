package org.nasdanika.common;

import org.jsoup.Jsoup;

/**
 * A unit of text content with content type. For example, text/plain, text/markdown, text/html.
 */
public class Content {
	
	private String content;
	private String contentType;
	
	public Content() {
		
	}

	public Content(String content, String contentType) {
		this.content = content;
		this.contentType = contentType;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getContentType() {
		return contentType;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public boolean isMarkdown() {
		return "markdown".equalsIgnoreCase(getContentType()) || "text/markdown".equalsIgnoreCase(getContentType());
	}
	
	
	public boolean isHtml() {
		return "html".equalsIgnoreCase(getContentType()) || "text/html".equalsIgnoreCase(getContentType());				
	}
	
	public boolean isText() {
		return "text".equalsIgnoreCase(getContentType()) || "text/plain".equalsIgnoreCase(getContentType());		
	}
	
	public String toMarkdown() {
		if (isMarkdown()) {
			return getContent();
		}
		if (isHtml()) {
			return "<html>" + getContent() + "</html>";
		}
		if (isText()) {
			return System.lineSeparator()
					+ System.lineSeparator()
					+ "```"
					+ System.lineSeparator()
					+ getContent()
					+ System.lineSeparator()
					+ "```"
					+ System.lineSeparator()
					+ System.lineSeparator();
		}
		throw new UnsupportedOperationException("Cannot convert " + getContentType() + " to markdown");
	}
		
	public String toHtml() {
		if (isHtml()) {
			return getContent();
		}
		if (isText()) {
			return System.lineSeparator()
					+ "<PRE>"
					+ System.lineSeparator()
					+ getContent()
					+ System.lineSeparator()
					+ "</PRE>"
					+ System.lineSeparator();			
		}
		if (isMarkdown()) {
			return MarkdownHelper.INSTANCE.markdownToHtml(getContent());
		}		
		throw new UnsupportedOperationException("Cannot convert " + getContentType() + " to HTML");
	}
	
	public String toText() {
		if (isText()) {
			return getContent();
		}
		return Jsoup.parse(toHtml()).text();
	}
	
	public String toString(String format) {
		return switch (format.toLowerCase()) {
			case "markdown", "text/markdown" -> toMarkdown();
			case "html", "text/html" -> toHtml();
			case "text", "text/plain" -> toText();
			default -> throw new IllegalArgumentException("Unsupported format: " + format);
		};
	}

}
