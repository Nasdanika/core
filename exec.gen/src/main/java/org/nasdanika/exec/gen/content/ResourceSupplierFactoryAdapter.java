package org.nasdanika.exec.gen.content;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.emf.persistence.LoadingExecutionParticipant;
import org.nasdanika.exec.content.Resource;

public class ResourceSupplierFactoryAdapter extends AdapterImpl implements SupplierFactory<InputStream> {

	private static final String CLASSPATH_URI_PREFIX = LoadingExecutionParticipant.CLASSPATH_SCHEME + ":";
	
	private Marker marker;
	
	public ResourceSupplierFactoryAdapter(Resource resource) {
		setTarget(resource);
		Marked marked = EObjectAdaptable.adaptTo((EObject) getTarget(), Marked.class);
		marker = marked == null ? null : marked.getMarker();
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
					String iUrl = resource.isInterpolate() ? context.interpolateToString(resource.getLocation()) : resource.getLocation(); 
					theURL = iUrl.startsWith(CLASSPATH_URI_PREFIX) ? getClass().getClassLoader().getResource(iUrl.substring(CLASSPATH_URI_PREFIX.length())) : new URL(iUrl);
					if (theURL == null) {
						return new BasicDiagnostic(Status.ERROR, "Classpath resource not found: " + iUrl, marker);						
					}
					return Supplier.super.diagnose(progressMonitor);
				} catch (MalformedURLException e) {					
					return new BasicDiagnostic(Status.ERROR, e.getMessage() + (marker == null ? "" : " at " + marker));
				}
			}

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Resource: " + marker;
			}

			@Override
			public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
				return theURL.openStream();
			}
			
		};
	}

}
