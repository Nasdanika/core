package org.nasdanika.exec.gen.content;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.content.Resource;

public class ResourceSupplierFactoryAdapter extends AdapterImpl implements SupplierFactory<InputStream> {

	private List<? extends Marker> markers;
	
	public ResourceSupplierFactoryAdapter(Resource resource) {
		setTarget(resource);
		Marked marked = EObjectAdaptable.adaptTo((EObject) getTarget(), Marked.class);
		markers = marked == null ? null : marked.getMarkers();
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}

	@Override
	public Supplier<InputStream> create(Context context) throws Exception {
		return new Supplier<InputStream>() {
			
			private URL theURL;
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				try {
					Resource resource = (Resource) getTarget();
					URI uri = URI.createURI(resource.isInterpolate() ? context.interpolateToString(resource.getLocation()) : resource.getLocation());
					org.eclipse.emf.ecore.resource.Resource eResource = resource.eResource();
					if (eResource != null) {
						URI base = eResource.getURI();
						if (base != null) {
							uri = uri.resolve(base);
						}
					}
					
					String iUrl = uri.toString(); 
					
					theURL = iUrl.startsWith(Util.CLASSPATH_URL_PREFIX) ? getClass().getClassLoader().getResource(iUrl.substring(Util.CLASSPATH_URL_PREFIX.length())) : new URL(iUrl);
					if (theURL == null) {
						return new BasicDiagnostic(Status.ERROR, "Classpath resource not found: " + iUrl, markers);						
					}
					return Supplier.super.diagnose(progressMonitor);
				} catch (MalformedURLException e) {					
					return new BasicDiagnostic(Status.ERROR, e.getMessage() + (markers == null ? "" : " at " + markers.stream().map(Object::toString).collect(Collectors.joining(", "))));
				}
			}

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Resource: " + markers == null ? "(undefined)" : markers.stream().map(Object::toString).collect(Collectors.joining(", "));
			}

			@Override
			public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
				return theURL.openStream();
			}
			
		};
	}

}
