package org.nasdanika.help.markdown.extensions;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nasdanika.help.markdown.MarkdownPreProcessor;

/**
 * Outputs fenced blocks AS-IS, i.e. without any pre-processing.
 * 
 * @author Pavel Vlasov
 *
 */
public class FencedBlockMarkdownPreProcessor implements MarkdownPreProcessor {

	// We can't use a single pattern - it drives the matcher crazy if tags are misplaced - stack overflow
	private static final Pattern FENCED_BLOCK_PATTERN = Pattern.compile("\\R(\\s)*\\R```(\\s)*\\R");

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
			return content;
		}
		
	}
	
	@Override
	public List<Region> match(String content) {
		Matcher startMatcher = FENCED_BLOCK_PATTERN.matcher(content);
		List<Region> ret = new ArrayList<Region>();
		int lastEnd = -1;
		while (startMatcher.find()) {
			int start = startMatcher.start();
			if (start>lastEnd) {
				String endMatcherContent = content.substring(startMatcher.end());
				Matcher endMatcher = FENCED_BLOCK_PATTERN.matcher(endMatcherContent);
				if (endMatcher.find()) {
					lastEnd = startMatcher.end()+endMatcher.end();
					ret.add(new RegionImpl(content.substring(start, lastEnd), start, lastEnd));
				}
			}
		}
		return ret;
	}

}
