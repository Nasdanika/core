package org.nasdanika.exec.gen.content;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagramGenerator;
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
				Resource targetResource = getTarget().eResource();
				URI resURI = targetResource == null ? null : targetResource.getURI();
				List<? extends Marker> markers = getTarget().getMarkers();
				if (markers != null) {
					for(Marker marker: markers) {
						if (marker == null || Util.isBlank(marker.getLocation())) {
							return resURI;
						}
				
						try {
							URI markerLocation = URI.createURI(marker.getLocation());
							return resURI != null && resURI.isHierarchical() ? markerLocation.resolve(resURI) : markerLocation;
						} catch (Exception e) {
							throw new ConfigurationException("Invalid location: " + marker.getLocation(), e, marker);
						}
					}
				}
				return resURI;
			}
			
			@Override
			protected DiagramGenerator getDiagramGenerator() {
				return context == null ? super.getDiagramGenerator() : context.get(DiagramGenerator.class, super.getDiagramGenerator()); 
			}
			
		};
		String html = /* context.computingContext().get(MarkdownHelper.class, helperComputer) */ markdownHelper.markdownToHtml(input);
		return getTarget().isStyle() ? "<div class=\"markdown-body\">" + html + "</div>" : html;
	}

}
