package org.nasdanika.exec.gen.content;

import java.net.MalformedURLException;
import java.net.URL;

import org.nasdanika.common.Context;
import org.nasdanika.common.MarkdownHelper;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.ncore.Marker;

public class MarkdownSupplierFactoryAdapter extends FilterSupplierFactoryAdapter<Markdown> {

	protected MarkdownSupplierFactoryAdapter(Markdown markdown) {
		super(markdown);
	}

	@Override
	protected String filter(Context context, String input) {
		MarkdownHelper markdownHelper = new MarkdownHelper() {
			
			@Override
			protected URL getResourceBase() {
				Marker marker = getTarget().getMarker();
				if (marker == null || Util.isBlank(marker.getLocation())) {
					return super.getResourceBase();
				}
				
				try {
					return new URL(marker.getLocation());
				} catch (MalformedURLException e) {
					throw new ConfigurationException("Malformed location: " + marker.getLocation(), marker);
				}
			}
			
		};
		String html = /* context.computingContext().get(MarkdownHelper.class, helperComputer) */ markdownHelper.markdownToHtml(input);
		return getTarget().isStyle() ? "<div class=\"markdown-body\">" + html + "</div>" : html;
	}

}
