package org.nasdanika.exec.gen.content;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

import org.nasdanika.common.Context;
import org.nasdanika.common.Converter;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.PropertyComputer;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.content.Interpolator;

public class InterpolatorSupplierFactoryAdapter extends FilterSupplierFactoryAdapter<Interpolator> {

	protected InterpolatorSupplierFactoryAdapter(Interpolator markdown) {
		super(markdown);
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
				URL imageURL = new URL(getBase(), path.substring(idx + 1));
				Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
				byte[] imageBytes = converter.convert(imageURL.openStream(), byte[].class);
				ret
					.append(Base64.getEncoder().encodeToString(imageBytes))
					.append("\"/>");
				
				return (T) ret.toString();
			} catch (Exception e) {
				throw new ConfigurationException("Error encoding image '" + path + "' : " + e, e, EObjectAdaptable.adaptTo(getTarget(), Marked.class));
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private <T> T computeInclude(Context context, String key, String path, Class<T> type) {
		if (type == null || type == String.class) {

			try {
				URL includeURL = new URL(getBase(), path);
				Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
				String includeContent = converter.convert(includeURL.openStream(), String.class);
				return (T) context.interpolateToString(includeContent);
			} catch (Exception e) {
				throw new ConfigurationException("Error including '" + path + "': " + e, e, EObjectAdaptable.adaptTo(getTarget(), Marked.class));
			}
		}
		return null;
	}

	protected URL getBase() throws MalformedURLException {
		URL base = Util.isBlank(getTarget().getBase()) ? null : new URL(getTarget().getBase());
		return base;
	}	
	
	@SuppressWarnings("unchecked")
	private <T> T computeIncludeMarkdown(Context context, String key, String path, Class<T> type) {
		if (type == null || type == String.class) {

			try {
				URL includeURL = new URL(getBase(), path);
				Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
				String markdown = converter.convert(includeURL.openStream(), String.class);
				String html = context.computingContext().get(MarkdownHelper.class, MarkdownHelper.INSTANCE).markdownToHtml(markdown);
				return (T) context.interpolateToString(html);
			} catch (Exception e) {
				throw new ConfigurationException("Error including markdown '" + path + "': " + e, e, EObjectAdaptable.adaptTo(getTarget(), Marked.class));
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
		if (getTarget().isProcessIncludes()) {
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
