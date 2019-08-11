package org.nasdanika.help.markdown.extensions;

import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.text.StringEscapeUtils;
import org.nasdanika.help.markdown.MarkdownPreProcessor;

import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

/**
 * Uses PlantUML (http://plantuml.com/) EPL version to convert textual definitions of diagrams to SVG.
 * 
 * @author Pavel Vlasov
 *
 */
public class PlantUmlMarkdownPreProcessor implements MarkdownPreProcessor {
	
	private static final String START_TAG = "@startuml";
	private static final String END_TAG = "@enduml";

	// We can't use a single pattern - it drives the matcher crazy if tags are misplaced - stack overflow
	private static final Pattern START_UML_PATTERN = Pattern.compile("\\R(\\s)*\\R"+START_TAG+"(\\s)*\\R");
	private static final Pattern END_UML_PATTERN = Pattern.compile("\\R"+END_TAG+"(\\s)*((\\R(\\s)*\\R)|$)");

	private static class RegionImpl implements Region {

		private final String content;
		private int start;
		private int end;

		public RegionImpl(String content, int start, int end) {
			this.content = content;
			this.start = start;
			this.end = end;
		}

		@Override
		public int getStart() {
			return start;
		}

		@Override
		public int getEnd() {
			return end;
		}

		@Override
		public String process(Chain chain) {
			// TODO - support of remote PlantUML server.			
//			Transcoder transcoder = TranscoderUtil.getDefaultTranscoder();
//	        String url = transcoder.encode(content);
//			Example - http://www.plantuml.com/plantuml/svg/SyfFKj2rKt3CoKnELR1Io4ZDoSa70000
//			Load URL from org.nasdanika.help.markdown.extensions.PlantUmlMarkdownPreProcessor.server property
	        // Send to the server, specify that we want SVG back.
	        
			SourceStringReader reader = new SourceStringReader(content);
			try (final ByteArrayOutputStream os = new ByteArrayOutputStream()) {
				// Write the first image to "os"
				reader.outputImage(os, new FileFormatOption(FileFormat.SVG));
				os.close();
				
				int startTagIdx = content.indexOf(START_TAG);
				String bareSpec = content.substring(startTagIdx+START_TAG.length(), content.lastIndexOf(END_TAG));
				String escapedBareSpec = StringEscapeUtils.escapeHtml4(bareSpec).trim();
	
				// The XML is stored into svg
				return 
					System.lineSeparator() + 
					System.lineSeparator() + 
					"<div>" + System.lineSeparator() + 
					"    <div style='display:none;white-space:pre-wrap' title='UML diagram definition for search'>" + System.lineSeparator() +
					"        " + escapedBareSpec + System.lineSeparator() + 
					"    </div>" +	System.lineSeparator() + 
					"    "+new String(os.toByteArray(), Charset.forName("UTF-8")) +	System.lineSeparator() + 
					"</div>" +	System.lineSeparator() + 
					System.lineSeparator();
			} catch (Exception e) {
				e.printStackTrace();
				return "(Error converting diagram definition to image: "+e+")";
			}
		}
		
	}
	
	@Override
	public List<Region> match(String content) {
		Matcher startMatcher = START_UML_PATTERN.matcher(content);
		List<Region> ret = new ArrayList<Region>();
		int lastEnd = -1;
		while (startMatcher.find()) {
			int start = startMatcher.start();
			if (start>lastEnd) {
				String endMatcherContent = content.substring(startMatcher.end());
				Matcher endMatcher = END_UML_PATTERN.matcher(endMatcherContent);
				if (endMatcher.find()) {
					lastEnd = startMatcher.end()+endMatcher.end();
					ret.add(new RegionImpl(content.substring(start, lastEnd), start, lastEnd));
				}
			}
		}
		return ret;
	}

}
