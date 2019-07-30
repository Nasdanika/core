package org.nasdanika.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.profiles.pegdown.Extensions;
import com.vladsch.flexmark.profiles.pegdown.PegdownOptionsAdapter;
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.options.DataHolder;

public class MarkdownHelper {
		
	public static final Pattern SENTENCE_PATTERN = Pattern.compile(".+?[\\.?!]+\\s+");		
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
		Document document = createMarkdownParser().parse(preProcessMarkdown(markdown));
		return createMarkdownHtmlRenderer().render(document);
	}		
	
	/**
	 * Extracts first sentence from text
	 * @param context
	 * @param text
	 * @return
	 * @throws Exception
	 */
	public String firstSentence(String text) {
		if (text == null || text.length() < getMinFirstSentenceLength()) {
			return text;
		}
		Matcher matcher = SENTENCE_PATTERN.matcher(text);		
		Z: while (matcher.find()) {
			String group = matcher.group();
			String[] abbreviations = getAbbreviations();
			for (String abbr: abbreviations) {
				if (group.trim().endsWith(abbr)) {
					continue Z;
				}
			}
			if (matcher.end() > getMinFirstSentenceLength() && matcher.end() < getMaxFirstSentenceLength()) {
				return text.substring(0, matcher.end());
			}
		}
		
		return text.length() < getMaxFirstSentenceLength() ? text : text.substring(0, getMaxFirstSentenceLength())+"...";
	}
	
	protected int getMinFirstSentenceLength() {
		return MIN_FIRST_SENTENCE_LENGTH;
	}
	
	protected int getMaxFirstSentenceLength() {
		return MAX_FIRST_SENTENCE_LENGTH;
	}
	

}
