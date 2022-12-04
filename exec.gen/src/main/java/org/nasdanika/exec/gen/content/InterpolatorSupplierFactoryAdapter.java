package org.nasdanika.exec.gen.content;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Base64;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.common.Context;
import org.nasdanika.common.Converter;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.PropertyComputer;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.ncore.Marker;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.Marked;

public class InterpolatorSupplierFactoryAdapter extends FilterSupplierFactoryAdapter<Interpolator> {

	protected InterpolatorSupplierFactoryAdapter(Interpolator interpolator) {
		super(interpolator);
	}

	@SuppressWarnings("unchecked")
	private <T> T computeEmbeddedImage(Context context, String key, String path, Class<T> type) {
		if (type == null || type.isAssignableFrom(String.class)) {
			int idx = path.indexOf("/");
			if (idx == -1) {
				return null;
			}

			try {
				StringBuilder ret = new StringBuilder("<img src=\"data:image/" + path.substring(0, idx) + ";base64, ");
				Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
				byte[] imageBytes = converter.convert(converter.convert(resolve(path.substring(idx + 1)), InputStream.class), byte[].class);
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
		if (type == null || type.isAssignableFrom(String.class)) {
			try {
				Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
				String includeContent = converter.convert(converter.convert(resolve(path), InputStream.class), String.class);
				return (T) context.interpolateToString(includeContent);
			} catch (Exception e) {
				throw new ConfigurationException("Error including '" + path + "': " + e, e, EObjectAdaptable.adaptTo(getTarget(), Marked.class));
			}
		}
		return null;
	}
	
	protected URI resolve(String path) throws MalformedURLException {
		URI uri = URI.createURI(path);
		
		URI markerBase = null;
		List<? extends Marker> markers = getTarget().getMarkers();
		if (markers != null) {
			for (Marker marker: markers) {
				if (!Util.isBlank(marker.getLocation())) {
					markerBase = URI.createURI(marker.getLocation());
					break;
				}
			}
		}

		URI resourceBase = null;
		Resource resource = getTarget().eResource();
		if (resource != null) {
			resourceBase = resource.getURI();
		}
		
		if (markerBase != null && markerBase.hasAbsolutePath() && path.startsWith("./")) {
			uri = uri.resolve(markerBase);
		} else if (resourceBase != null) {
			uri = uri.resolve(resourceBase);
		}
		
		return uri;					
	}
	
	@SuppressWarnings("unchecked")
	private <T> T computeIncludeMarkdown(Context context, String key, String path, Class<T> type) {
		if (type == null || type.isAssignableFrom(String.class)) {
			try {
				Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
				String markdown = converter.convert(converter.convert(resolve(path), InputStream.class), String.class);
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
