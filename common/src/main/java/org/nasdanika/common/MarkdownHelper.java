package org.nasdanika.common;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.emf.common.util.URI;
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
	
	private static final String FENSED_BLOCK_START_PREFIX_REGEX = "(^|((\\R|^)(\\s)*\\R))"; //String start or an empty line before   "\\R(\\s)*\\R"; 
	private static final String FENCED_BLOCK_START_SUFFIX_REGEX = "(\\s)*\\R"; // Zero or more whitespace followed by a new line.

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
		
//	private static final String 	
	
	private static final String START_UML_BLOCK = "```uml";
	private static final String START_UML_RESOURCE_BLOCK = "```uml-resource";
	private static final Pattern START_UML_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_UML_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);
	private static final Pattern START_UML_RESOURCE_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_UML_RESOURCE_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);

	private static final String START_WIREFRAME_BLOCK = "```wireframe";
	private static final String START_WIREFRAME_RESOURCE_BLOCK = "```wireframe-resource";
	private static final Pattern START_WIREFRAME_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_WIREFRAME_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);
	private static final Pattern START_WIREFRAME_RESOURCE_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_WIREFRAME_RESOURCE_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);

	private static final String START_GANTT_BLOCK = "```gantt";
	private static final String START_GANTT_RESOURCE_BLOCK = "```gantt-resource";
	private static final Pattern START_GANTT_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_GANTT_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);
	private static final Pattern START_GANTT_RESOURCE_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_GANTT_RESOURCE_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);

	private static final String START_MINDMAP_BLOCK = "```mindmap";
	private static final String START_MINDMAP_RESOURCE_BLOCK = "```mindmap-resource";
	private static final Pattern START_MINDMAP_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_MINDMAP_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);
	private static final Pattern START_MINDMAP_RESOURCE_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_MINDMAP_RESOURCE_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);

	private static final String START_WBS_BLOCK = "```wbs";
	private static final String START_WBS_RESOURCE_BLOCK = "```wbs-resource";
	private static final Pattern START_WBS_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_WBS_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);
	private static final Pattern START_WBS_RESOURCE_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_WBS_RESOURCE_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);
	
	private static final String START_DRAWIO_BLOCK = "```drawio";
	private static final String START_DRAWIO_RESOURCE_BLOCK = "```drawio-resource";
	private static final Pattern START_DRAWIO_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_DRAWIO_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);
	private static final Pattern START_DRAWIO_RESOURCE_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_DRAWIO_RESOURCE_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);
	
	private static final String START_MERMAID_BLOCK = "```mermaid";
	private static final String START_MERMAID_RESOURCE_BLOCK = "```mermaid-resource";
	private static final Pattern START_MERMAID_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_MERMAID_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);
	private static final Pattern START_MERMAID_RESOURCE_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_MERMAID_RESOURCE_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);
	
	private static final String START_PNG_BLOCK = "```png";
	private static final String START_PNG_RESOURCE_BLOCK = "```png-resource";
	private static final Pattern START_PNG_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_PNG_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);
	private static final Pattern START_PNG_RESOURCE_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_PNG_RESOURCE_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);
	
	private static final String START_JPEG_BLOCK = "```jpeg";
	private static final String START_JPEG_RESOURCE_BLOCK = "```jpeg-resource";
	private static final Pattern START_JPEG_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_JPEG_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);
	private static final Pattern START_JPEG_RESOURCE_PATTERN = Pattern.compile(FENSED_BLOCK_START_PREFIX_REGEX+START_JPEG_RESOURCE_BLOCK+FENCED_BLOCK_START_SUFFIX_REGEX);

	private static final String FENCED_BLOCK = "```";
	private static final Pattern FENCED_BLOCK_PATTERN = Pattern.compile("\\R"+FENCED_BLOCK+"(\\s)*((\\R(\\s)*\\R)|$)");	

	protected String[] getAbbreviations() {
		return ABBREVIATIONS;
	}	
	
	protected String nextToken() {
		return "fenced_block_token_"+UUID.randomUUID().toString().replace("-", "_");
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
			String ret = processFencedBlocks(markdown, DiagramGenerator.UML_DIALECT, START_UML_PATTERN, replacements, false);
			ret = processFencedBlocks(ret, DiagramGenerator.SALT_DIALECT, START_WIREFRAME_PATTERN, replacements, false);
			ret = processFencedBlocks(ret, DiagramGenerator.GANTT_DIALECT, START_GANTT_PATTERN, replacements, false);
			ret = processFencedBlocks(ret, DiagramGenerator.MINDMAP_DIALECT, START_MINDMAP_PATTERN, replacements, false);
			ret = processFencedBlocks(ret, DiagramGenerator.WBS_DIALECT, START_WBS_PATTERN, replacements, false);
			ret = processFencedBlocks(ret, DiagramGenerator.DRAWIO_DIALECT, START_DRAWIO_PATTERN, replacements, false);
			ret = processFencedBlocks(ret, DiagramGenerator.MERMAID_DIALECT, START_MERMAID_PATTERN, replacements, false);
			ret = processFencedBlocks(ret, null, START_PNG_PATTERN, replacements, false);
			ret = processFencedBlocks(ret, null, START_JPEG_PATTERN, replacements, false);
					
			ret = processFencedBlocks(ret, DiagramGenerator.UML_DIALECT, START_UML_RESOURCE_PATTERN, replacements, true);
			ret = processFencedBlocks(ret, DiagramGenerator.SALT_DIALECT, START_WIREFRAME_RESOURCE_PATTERN, replacements, true);
			ret = processFencedBlocks(ret, DiagramGenerator.GANTT_DIALECT, START_GANTT_RESOURCE_PATTERN, replacements, true);
			ret = processFencedBlocks(ret, DiagramGenerator.MINDMAP_DIALECT, START_MINDMAP_RESOURCE_PATTERN, replacements, true);
			ret = processFencedBlocks(ret, DiagramGenerator.WBS_DIALECT, START_WBS_RESOURCE_PATTERN, replacements, true);
			ret = processFencedBlocks(ret, DiagramGenerator.DRAWIO_DIALECT, START_DRAWIO_RESOURCE_PATTERN, replacements, true);
			ret = processFencedBlocks(ret, DiagramGenerator.MERMAID_DIALECT, START_MERMAID_RESOURCE_PATTERN, replacements, true);
			ret = processFencedBlocks(ret, null, START_PNG_RESOURCE_PATTERN, replacements, true);
			ret = processFencedBlocks(ret, null, START_JPEG_RESOURCE_PATTERN, replacements, true);
					
			return ret;		
		}
				
		return markdown;
	}

	/**
	 * 
	 * @param input
	 * @param dialect
	 * @param startPattern
	 * @param replacements
	 * @param resource Indicates that spec is a URL of a resource resolved relative to the URL returned by getResourceBase(). The URL supports <code>classpath</code> schema for classloader resources.
	 * @return
	 */
	private String processFencedBlocks(String input, String dialect, Pattern startPattern, Map<String,String> replacements, boolean resource) {
		StringBuilder output = new StringBuilder();
		Matcher startMatcher = startPattern.matcher(input);
		int i = 0;
		while (startMatcher.find()) {
		    int startMatcherStart = startMatcher.start();
		    if (startMatcherStart >= i) { // Need to reproduce a situation when i is greater than startMatcherStart
				int startMatcherEnd = startMatcher.end();
				String endMatcherContent = input.substring(startMatcherEnd);
				Matcher endMatcher = FENCED_BLOCK_PATTERN.matcher(endMatcherContent);			
				if (endMatcher.find()) {
					if (startMatcherStart > i) {
						output.append(input.substring(i, startMatcherStart));
					}
					String bareSpec = processSpec(dialect, endMatcherContent.substring(0, endMatcher.start())) ;
					String match = endMatcherContent.substring(endMatcher.start(), endMatcher.end());
				    i = startMatcherEnd + endMatcher.start() + match.indexOf("```") + 3; // Just the closing back-ticks, no space or new line characters.
					
					if (resource) {					
						try {		
							URI resourceURI = URI.createURI(bareSpec);
							URI resourceBase = getResourceBase();
							if (resourceBase != null && !resourceBase.isRelative() && resourceBase.isHierarchical()) {
								resourceURI = resourceURI.resolve(resourceBase);
							}
							bareSpec = loadResource(resourceURI, dialect == null);
						} catch (Exception e) {					
							output
								.append(System.lineSeparator())
								.append("<div class=\"nsd-error\">").append(System.lineSeparator())
								.append("Error loading resource " + bareSpec + ": " + e) 
								.append(System.lineSeparator()).append("</div>")
								.append(System.lineSeparator());
							bareSpec = null;
						}
					}
					
					if (bareSpec != null) {
						String escapedBareSpec = escapeDiagramSpec(dialect, bareSpec);
						
						StringBuilder replacementBuilder = new StringBuilder();
						if (!Util.isBlank(escapedBareSpec)) {
							replacementBuilder
								.append(System.lineSeparator())
								.append("<div style='display:none;white-space:pre-wrap' title='Diagram escaped spec for search'>").append(System.lineSeparator())
								.append(escapedBareSpec).append(System.lineSeparator()) 
								.append("</div> ").append(System.lineSeparator());
						}
						
						try {
							String token = nextToken();
							
							if (dialect == null) {
								replacementBuilder.append("<img src=\"data:image/");
								if (startPattern == START_PNG_PATTERN || startPattern == START_PNG_RESOURCE_PATTERN) {
									replacementBuilder.append("png");
								} else if (startPattern == START_JPEG_PATTERN || startPattern == START_JPEG_RESOURCE_PATTERN) {
									replacementBuilder.append("jpeg");
								}
								replacementBuilder
									.append(";base64, ")
									.append(bareSpec)
									.append("\"/>")
									.append(System.lineSeparator());
							} else {
								replacementBuilder
									.append(getDiagramGenerator().generateDiagram(bareSpec, dialect))
									.append(System.lineSeparator());
							}
							
							replacements.put(token, replacementBuilder.toString());
							output
								.append(System.lineSeparator())
								.append(System.lineSeparator())
								.append(token)
								.append(System.lineSeparator())
								.append(System.lineSeparator());
						} catch (Exception e) {
							output.append("<div class=\"nsd-error\">Error during diagram rendering: " + e + "</div>");
						}
					}				
				}
		    }
		}
		output.append(input.substring(i, input.length()));
		return output.toString();
	}
	
	/**
	 * Override to process bare spec.
	 * @param dialect
	 * @param bareSpec
	 * @return
	 */
	protected String processSpec(String dialect, String bareSpec) {
		return bareSpec;
	}

	protected String escapeDiagramSpec(String dialect, String bareSpec) {
		if (dialect == null) {
			return null; // For PNG and JPEG
		}
		if (DiagramGenerator.DRAWIO_DIALECT.equals(dialect)) {
			// TODO - Extract text from XML, return null for now.
			// Not needed with Nasdanika search - it extracts text from Drawio diagrams.
			return null;
		}
		if (DiagramGenerator.MERMAID_DIALECT.equals(dialect)) {
			// No need - spec is available as plain text. 
			return null;
		}
		return StringEscapeUtils.escapeHtml4(bareSpec).trim();
	}
	
	protected ClassLoader getResourceClassLoader() {
		return Thread.currentThread().getContextClassLoader();

	}
	
	/**
	 * Loads resource and optionally encodes it to Base64.
	 * @param resource
	 * @param encode
	 * @return
	 */
	protected String loadResource(URI resource, boolean encode) throws Exception {
		if (encode) {
			byte[] bytes = DefaultConverter.INSTANCE.toByteArray(resource);
			return Base64.getEncoder().encodeToString(bytes);
		}
		return DefaultConverter.INSTANCE.stringContent(resource);
	}

	/**
	 * Override to return base URL for resolving resource references in UML resource blocks.
	 * @return
	 */
	protected URI getResourceBase() {
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
	    		| (Extensions.ALL ^ Extensions.HARDWRAPS ^ Extensions.SUPPRESS_HTML_BLOCKS ^ Extensions.SUPPRESS_INLINE_HTML ^ Extensions.SUPPRESS_ALL_HTML));
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
