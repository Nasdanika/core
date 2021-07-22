package org.nasdanika.exec.legacy.content;

import java.io.InputStream;
import java.net.URL;

import org.nasdanika.common.Context;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * Markdown filter
 * @author Pavel
 *
 */
public class Markdown extends Filter {

	private boolean style;

	public Markdown(
			ObjectLoader loader, 
			Object config, 
			URL base, 
			ProgressMonitor progressMonitor, 
			Marker marker,
			boolean style) throws Exception {
		super(loader, config, base, progressMonitor, marker);
		this.style = style;
	}

	public Markdown(Marker marker, SupplierFactory<InputStream> source) {
		super(marker, source);
	}

	@Override
	protected String filter(Context context, String input) {
		String html = context.computingContext().get(MarkdownHelper.class, MarkdownHelper.INSTANCE).markdownToHtml(input);
		return style ? "<div class=\"markdown-body\">" + html + "</div>" : html;
	}

}
