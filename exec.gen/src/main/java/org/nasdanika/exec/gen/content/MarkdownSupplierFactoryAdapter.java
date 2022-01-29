package org.nasdanika.exec.gen.content;

import org.eclipse.emf.common.util.URI;
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
			protected URI getResourceBase() {
				Marker marker = getTarget().getMarker();
				if (marker == null || Util.isBlank(marker.getLocation())) {
					return super.getResourceBase();
				}
				
				
				try {
					return URI.createURI(marker.getLocation());
				} catch (Exception e) {
					throw new ConfigurationException("Invalid location: " + marker.getLocation(), e, marker);
				}
			}
			
		};
		String html = /* context.computingContext().get(MarkdownHelper.class, helperComputer) */ markdownHelper.markdownToHtml(input);
		return getTarget().isStyle() ? "<div class=\"markdown-body\">" + html + "</div>" : html;
	}

}
