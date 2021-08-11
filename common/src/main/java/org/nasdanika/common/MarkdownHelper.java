package org.nasdanika.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.profile.pegdown.Extensions;
import com.vladsch.flexmark.profile.pegdown.PegdownOptionsAdapter;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.data.DataHolder;

/**
 * Helper class to generate HTML from markdown. Use INSTANCE for default behavior and subclass to customize.
 * @author Pavel
 *
 */
public class MarkdownHelper {
	
	/**
	 * Shared instance with default configuration.
	 */
	public static final MarkdownHelper INSTANCE = new MarkdownHelper();
	
	/**
	 * Subclass to customize by overriding protected methods.
	 */
	protected MarkdownHelper() {
		
	}

	/**
	 * CDN URL of the Github Markdown CSS (https://github.com/sindresorhus/github-markdown-css)
	 */
	public static final String GITHUB_MARKDOWN_CSS_CDN = "https://cdnjs.cloudflare.com/ajax/libs/github-markdown-css/4.0.0/github-markdown.css";
	
	public static final String HIGHLIGHT_JS_CSS_CDN = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.5.0/styles/default.min.css";
	public static final String HIGHLIGHT_JS_SCRIPT_CDN = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/10.5.0/highlight.min.js";
	public static final String HIGHLIGHT_JS_INIT_SCRIPT = "hljs.initHighlightingOnLoad();";
		
	public static final int MIN_FIRST_SENTENCE_LENGTH = 20;
	public static final int MAX_FIRST_SENTENCE_LENGTH = 250;	
	public static final String[] ABBREVIATIONS = { "e.g.", "i.e.", "etc." };
		
	private static final String START_UML_BLOCK = "```uml";
	private static final String START_UML_RESOURCE_BLOCK = "```uml-resource";
	private static final Pattern START_UML_PATTERN = Pattern.compile("\\R(\\s)*\\R"+START_UML_BLOCK+"(\\s)*\\R");
	private static final Pattern START_UML_RESOURCE_PATTERN = Pattern.compile("\\R(\\s)*\\R"+START_UML_BLOCK+"(\\s)*\\R");

	private static final String START_WIREFRAME_BLOCK = "```wireframe";
	private static final String START_WIREFRAME_RESOURCE_BLOCK = "```wireframe-resource";
	private static final Pattern START_WIREFRAME_PATTERN = Pattern.compile("\\R(\\s)*\\R"+START_WIREFRAME_BLOCK+"(\\s)*\\R");
	private static final Pattern START_WIREFRAME_RESOURCE_PATTERN = Pattern.compile("\\R(\\s)*\\R"+START_WIREFRAME_BLOCK+"(\\s)*\\R");

	private static final String START_GANTT_BLOCK = "```gantt";
	private static final String START_GANTT_RESOURCE_BLOCK = "```gantt-resource";
	private static final Pattern START_GANTT_PATTERN = Pattern.compile("\\R(\\s)*\\R"+START_GANTT_BLOCK+"(\\s)*\\R");
	private static final Pattern START_GANTT_RESOURCE_PATTERN = Pattern.compile("\\R(\\s)*\\R"+START_GANTT_BLOCK+"(\\s)*\\R");

	private static final String START_MINDMAP_BLOCK = "```mindmap";
	private static final String START_MINDMAP_RESOURCE_BLOCK = "```mindmap-resource";
	private static final Pattern START_MINDMAP_PATTERN = Pattern.compile("\\R(\\s)*\\R"+START_MINDMAP_BLOCK+"(\\s)*\\R");
	private static final Pattern START_MINDMAP_RESOURCE_PATTERN = Pattern.compile("\\R(\\s)*\\R"+START_MINDMAP_BLOCK+"(\\s)*\\R");

	private static final String START_WBS_BLOCK = "```wbs";
	private static final String START_WBS_RESOURCE_BLOCK = "```wbs-resource";
	private static final Pattern START_WBS_PATTERN = Pattern.compile("\\R(\\s)*\\R"+START_WBS_BLOCK+"(\\s)*\\R");
	private static final Pattern START_WBS_RESOURCE_PATTERN = Pattern.compile("\\R(\\s)*\\R"+START_WBS_BLOCK+"(\\s)*\\R");

	private static final String FENCED_BLOCK = "```";
	private static final Pattern FENCED_BLOCK_PATTERN = Pattern.compile("\\R"+FENCED_BLOCK+"(\\s)*((\\R(\\s)*\\R)|$)");	

	protected String[] getAbbreviations() {
		return ABBREVIATIONS;
	}	
	
	protected String nextToken() {
		return "diagram_token_"+UUID.randomUUID().toString().replace("-", "_");
	}
	
	protected DiagramGenerator getDiagramGenerator() {
		return DiagramGenerator.INSTANCE;
	}
		
	/**
	 * @param markdown Markdown to pre-process
	 * @param replacements Accumulator of post-processing replacements token -&gt; HTML to replace the token with.
	 * @return
	 */
	protected String preProcessMarkdown(String markdown, Map<String,String> replacements) {
		if (isProcessFendedBlocks()) {
			String ret = processFencedBlocks(markdown, DiagramGenerator.Dialect.UML, START_UML_PATTERN, replacements);
			ret = processFencedBlocks(ret, DiagramGenerator.Dialect.SALT, START_WIREFRAME_PATTERN, replacements);
			ret = processFencedBlocks(ret, DiagramGenerator.Dialect.GANTT, START_GANTT_PATTERN, replacements);
			ret = processFencedBlocks(ret, DiagramGenerator.Dialect.MINDMAP, START_MINDMAP_PATTERN, replacements);
			ret = processFencedBlocks(ret, DiagramGenerator.Dialect.WBS, START_WBS_PATTERN, replacements);
					
			// TODO - resource blocks
					
			return ret;		
		}
		
		
		return markdown;
	}
	
//	private String processUmlResourceBlocks(String markdown) {
//		// TODO
//		return markdown;
//	}

	private String processFencedBlocks(String input, DiagramGenerator.Dialect dialect, Pattern startPattern, Map<String,String> replacements) {
		StringBuilder output = new StringBuilder();
		Matcher startMatcher = startPattern.matcher(input);
		int i = 0;
		while (startMatcher.find()) {
			String endMatcherContent = input.substring(startMatcher.end());
			Matcher endMatcher = FENCED_BLOCK_PATTERN.matcher(endMatcherContent);
			if (endMatcher.find()) {
			    output.append(input.substring(i, startMatcher.start()));

				String bareSpec = input.substring(startMatcher.end(), startMatcher.end() + endMatcher.start());

				String escapedBareSpec = StringEscapeUtils.escapeHtml4(bareSpec).trim();
				
				StringBuilder replacementBuilder = new StringBuilder();
				replacementBuilder
					.append(System.lineSeparator())
					.append("<div> ").append(System.lineSeparator())
					.append("    <div style='display:none;white-space:pre-wrap' title='Diagram definition for search'>").append(System.lineSeparator())
					.append(escapedBareSpec).append(System.lineSeparator()) 
					.append("    </div>").append(System.lineSeparator())
					.append("</div> ").append(System.lineSeparator());
				
				try {
					String token = nextToken();
					replacementBuilder
						.append(getDiagramGenerator().generateDiagram(bareSpec, dialect))
						.append(System.lineSeparator());
					
					replacements.put(token, replacementBuilder.toString());
					output
						.append(System.lineSeparator())
						.append(System.lineSeparator())
						.append(token)
						.append(System.lineSeparator())
						.append(System.lineSeparator());
				} catch (Exception e) {
					output.append("Error during diagram rendering: " + e);
				}
				
				i = startMatcher.end()+endMatcher.end();
			}
		}
		output.append(input.substring(i, input.length()));
		return output.toString();
	}

	/**
	 * Override to return base URL for resolving resource references in UML resource blocks.
	 * @return
	 */
	protected String getResourceBase() {
		return null;
	}
	
	protected boolean isProcessFendedBlocks() {
		return true;
	}
		
	protected DataHolder getFlexmarkOptions() {
	    return PegdownOptionsAdapter.flexmarkOptions(
	    		Extensions.FOOTNOTES 
	    		| Extensions.SUBSCRIPT 
	    		| Extensions.STRIKETHROUGH 
	    		| Extensions.SUPERSCRIPT 
	    		| Extensions.TOC 
	    		| (Extensions.ALL ^ Extensions.HARDWRAPS ^ Extensions.SUPPRESS_HTML_BLOCKS ^ Extensions.SUPPRESS_ALL_HTML));
	}	
	
	protected Parser createMarkdownParser() {
		return Parser.builder(getFlexmarkOptions()).build();
	}
		
	protected HtmlRenderer createMarkdownHtmlRenderer() {
		return HtmlRenderer.builder(getFlexmarkOptions()).build();
	}
			
	public String markdownToHtml(String markdown) {
		if (Util.isBlank(markdown)) {
			return "";
		}
		Map<String,String> replacements = new HashMap<>();
		Document document = createMarkdownParser().parse(preProcessMarkdown(markdown, replacements));
		String html = createMarkdownHtmlRenderer().render(document);
		for (Entry<String, String> te: replacements.entrySet()) {
			html = html.replace(te.getKey(), te.getValue());
		}
		return html;
	}
	
	/**
	 * Converts markdown to HTML, converts HTML to plain text and then extracts the first sentence.
	 * @param markdown
	 * @return
	 */
	public String firstPlainTextSentence(String markdown) {
		return firstSentence(Jsoup.parse(markdownToHtml(markdown)).text());		
	}
	
	/**
	 * Extracts first sentence from text
	 * @param context
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public String firstSentence(String text) {
		return Util.firstSentence(text, getMinFirstSentenceLength(), getMaxFirstSentenceLength(), getAbbreviations());
	}
	
	protected int getMinFirstSentenceLength() {
		return MIN_FIRST_SENTENCE_LENGTH;
	}
	
	protected int getMaxFirstSentenceLength() {
		return MAX_FIRST_SENTENCE_LENGTH;
	}

}