package org.nasdanika.help.markdown;

import java.util.List;

/**
 * Pre-processes markdown pages before they are converted to HTML.
 * @author Pavel
 *
 */
public interface MarkdownPreProcessor {
	
	/**
	 * Matched region.
	 * @author Pavel Vlasov
	 *
	 */
	interface Region {
		
		/**
		 * @return match region start, inclusive.
		 */
		int getStart();
		
		/**
		 * @return match region end, exclusive.
		 */
		int getEnd();
		
		
		/**
		 * Can be used by the pre-processor to pass pre-processing to other pre-processors.
		 * @author Pavel
		 *
		 */
		interface Chain {
			
			/**
			 * Passes content through plugin expansion.
			 * @param content
			 * @return Markdown without plugin tags.
			 */
			String process(String content);
			
		}
		
		/**
		 * Processes matched content
		 * @param content
		 * @param baseURL
		 * @param urlPrefix
		 * @param chain
		 * @param docRoute
		 * @return Processed content or null if content could not be processed and shall be left AS-IS.
		 */
		String process(Chain chain);	
		
	}

	/**
	 * Matches regions in the content which may be processed by this pre-processor.
	 * @param content
	 * @return
	 */
	List<Region> match(String content);

}
