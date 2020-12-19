package org.nasdanika.common;

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
	public static final String GITHUB_MARKDOWN_CSS_CDN = "https://cdnjs.cloudflare.com/ajax/libs/github-markdown-css/3.0.1/github-markdown.css";
	
	public static final String HIGHLIGHT_JS_CSS_CDN = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.18.1/styles/default.min.css";
	public static final String HIGHLIGHT_JS_SCRIPT_CDN = "https://cdnjs.cloudflare.com/ajax/libs/highlight.js/9.18.1/highlight.min.js";
	public static final String HIGHLIGHT_JS_INIT_SCRIPT = "hljs.initHighlightingOnLoad();";
		
	public static final int MIN_FIRST_SENTENCE_LENGTH = 20;
	public static final int MAX_FIRST_SENTENCE_LENGTH = 250;	
	public static final String[] ABBREVIATIONS = { "e.g.", "i.e.", "etc." };
		
	protected String[] getAbbreviations() {
		return ABBREVIATIONS;
	}	
		
	protected String preProcessMarkdown(String markdown) {
		return markdown;
	}
		
	protected DataHolder getFlexmarkOptions() {
	    return PegdownOptionsAdapter.flexmarkOptions(Extensions.ALL ^ Extensions.HARDWRAPS ^ Extensions.SUPPRESS_HTML_BLOCKS ^ Extensions.SUPPRESS_ALL_HTML);
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
		Document document = createMarkdownParser().parse(preProcessMarkdown(markdown));
		return createMarkdownHtmlRenderer().render(document);
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
