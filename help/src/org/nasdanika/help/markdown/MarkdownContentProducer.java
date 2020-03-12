package org.nasdanika.help.markdown;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.help.IHelpContentProducer;
import org.nasdanika.common.MarkdownHelper;
import org.osgi.framework.Bundle;

/**
 * Serves file.md.html references by converting file.md to HTML if file.md is present.
 * @author Pavel
 *
 */
public class MarkdownContentProducer implements IHelpContentProducer {

	private MarkdownHelper markdownHelper = MarkdownHelper.INSTANCE;
	
	@Override
	public InputStream getInputStream(String pluginID, String href, Locale locale) {
		int idx = href.indexOf("?");
		if (idx != -1) {
			href = href.substring(0, idx);			
		}
		if (href.endsWith(".md.html")) {
			Bundle bundle = Platform.getBundle(pluginID);
			URL mdEntry = bundle.getEntry(href.substring(0, href.length()-".html".length()));
			if (mdEntry != null) {
				try (Reader r = new BufferedReader(new InputStreamReader(mdEntry.openStream()))) {
					StringWriter sw = new StringWriter();
					int ch;
					while ((ch = r.read()) != -1) {
						sw.write(ch);
					}
					sw.close();					
					String html = markdownHelper.markdownToHtml(preProcessMarkdown(sw.toString(), pluginID, href, locale));
					return new ByteArrayInputStream(html.getBytes());
				} catch (Exception e) {
					return new ByteArrayInputStream(("Exception: "+e).getBytes());
				}
			}
		}
		return null;
	}
	
	protected String preProcessMarkdown(String content, String pluginID, String href, Locale locale) {
		List<MarkdownPreProcessor> preProcessors = new ArrayList<>();
		for (IConfigurationElement ce: Platform.getExtensionRegistry().getConfigurationElementsFor("org.nasdanika.help.extensions")) {
			// TODO renderers cache to improve performance?
			if ("markdown-pre-processor".equals(ce.getName())) {
				try {
					MarkdownPreProcessor preProcessor = ((MarkdownPreProcessor) ce.createExecutableExtension("class"));
					preProcessors.add(preProcessor);
				} catch (CoreException e) {
					System.err.println("Exception while creating markdown pre-processor");
					e.printStackTrace();
				}
			}
		}	
		
		if (preProcessors.isEmpty() || content == null || content.length()==0) {
			return "";			
		}
		
		MarkdownPreProcessor.Region.Chain chain = new MarkdownPreProcessor.Region.Chain() {
			
			@Override
			public String process(String content) {
				return preProcessMarkdown(content, pluginID, href, locale);
			}
			
		};
		
		List<MarkdownPreProcessor.Region> matchedRegions = new ArrayList<>();
		for (org.nasdanika.help.markdown.MarkdownPreProcessor pp: preProcessors) {
			matchedRegions.addAll(pp.match(content));
		}
		Collections.sort(matchedRegions, new Comparator<MarkdownPreProcessor.Region>() {

			@Override
			public int compare(MarkdownPreProcessor.Region r1, MarkdownPreProcessor.Region r2) {
				if (r1.getStart() == r2.getStart()) {
					if (r1.getEnd() == r2.getEnd()) {
						return r1.hashCode() - r2.hashCode();
					}
					return r2.getEnd() - r1.getEnd(); // Larger regions get precedence
				}
				
				return r1.getStart() - r2.getStart(); // Earlier regions get precedence
			}
		});
		
		StringBuilder out = new StringBuilder();
		int start = 0;
		for (MarkdownPreProcessor.Region region: matchedRegions) {
			int regionStart = region.getStart();
			if (regionStart>=start) {
				if (regionStart>start) {
					out.append(content.substring(start, regionStart));
					start = regionStart;
				}
				String result = region.process(chain);
				if (result != null) {
					out.append(result);
					start = region.getEnd();
				}
			}
		}
		if (start<content.length()) {
			out.append(content.substring(start));
		}
		return out.toString();		
	}

}
