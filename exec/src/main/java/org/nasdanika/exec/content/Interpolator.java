package org.nasdanika.exec.content;

import java.net.URL;
import java.util.Base64;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.Converter;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertyComputer;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

public class Interpolator extends Filter {
	
	private static final String SOURCE_KEY = "source";
	private static final String PROCESS_INCLUDES_KEY = "process-includes";
	private static final String BASE_KEY = "base";

	private static Object getSource(Object config) {
		if (config instanceof Map && ((Map<?,?>) config).size() > 1) {
			Util.checkUnsupportedKeys((Map<?,?>) config, SOURCE_KEY, PROCESS_INCLUDES_KEY, BASE_KEY);			
			return (((Map<?,?>) config).get(SOURCE_KEY));
		}
		return config;
	}
	
	private static boolean isEmbedImages(Object config) {
		if (config instanceof Map && ((Map<?,?>) config).size() > 1) {
			return !Boolean.FALSE.equals((((Map<?,?>) config).get(PROCESS_INCLUDES_KEY)));
		}
		return true;
	}
	
	private static Object getImageBase(Object config) {
		if (config instanceof Map && ((Map<?,?>) config).size() > 1) {
			return ((Map<?,?>) config).get(BASE_KEY);
		}
		return null;
	}

	private boolean embedImages;
	private URL base;

	public Interpolator(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, getSource(config), base, progressMonitor, marker);
		Object imageBase = getImageBase(config);
		if (imageBase == null) {
			this.base = base;			
		} else if (imageBase instanceof String) {
			this.base = new URL(base, (String) imageBase);
		} else {
			throw new ConfigurationException("Image base shall be a string: " + imageBase, Util.getMarker((Map<?,?>) config, BASE_KEY));
		}
		embedImages = isEmbedImages(config);		
	}
	
	@SuppressWarnings("unchecked")
	private <T> T computeEmbeddedImage(Context context, String key, String path, Class<T> type) {
		if (type == null || type == String.class) {
			int idx = path.indexOf("/");
			if (idx == -1) {
				return null;
			}

			try {
				StringBuilder ret = new StringBuilder("<img src=\"data:image/" + path.substring(0, idx) + ";base64, ");
				URL imageURL = new URL(base, path.substring(idx + 1));
				Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
				byte[] imageBytes = converter.convert(imageURL.openStream(), byte[].class);
				ret
					.append(Base64.getEncoder().encodeToString(imageBytes))
					.append("\"/>");
				
				return (T) ret.toString();
			} catch (Exception e) {
				throw new ConfigurationException("Error encoding image '" + path + "' : " + e, e, getMarker());
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private <T> T computeInclude(Context context, String key, String path, Class<T> type) {
		if (type == null || type == String.class) {

			try {
				URL includeURL = new URL(base, path);
				Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
				String includeContent = converter.convert(includeURL.openStream(), String.class);
				return (T) context.interpolateToString(includeContent);
			} catch (Exception e) {
				throw new ConfigurationException("Error including '" + path + "': " + e, e, getMarker());
			}
		}
		return null;
	}	
	
	@SuppressWarnings("unchecked")
	private <T> T computeIncludeMarkdown(Context context, String key, String path, Class<T> type) {
		if (type == null || type == String.class) {

			try {
				URL includeURL = new URL(base, path);
				Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
				String markdown = converter.convert(includeURL.openStream(), String.class);
				String html = context.computingContext().get(MarkdownHelper.class, MarkdownHelper.INSTANCE).markdownToHtml(markdown);
				return (T) context.interpolateToString(html);
			} catch (Exception e) {
				throw new ConfigurationException("Error including markdown '" + path + "': " + e, e, getMarker());
			}
		}
		return null;
	}	
	
	@SuppressWarnings("unchecked")
	private <T> T computeIncludeStyledMarkdown(Context context, String key, String path, Class<T> type) {
		Object html = computeIncludeMarkdown(context, key, path, type);
		return (T) (html == null ? null : "<div class=\"markdown-body\">" + html + "</div>");
	}	

	@Override
	protected String filter(Context context, String input) {
		if (embedImages) {
			MutableContext mc = context.fork();
			mc.put("embedded-image", (PropertyComputer) this::computeEmbeddedImage);
			mc.put("include", (PropertyComputer) this::computeInclude);
			mc.put("include-markdown", (PropertyComputer) this::computeIncludeMarkdown);
			mc.put("include-styled-markdown", (PropertyComputer) this::computeIncludeStyledMarkdown);
			
			return mc.interpolateToString(input);
		}
		return context.interpolateToString(input);
	}

}
