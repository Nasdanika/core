package org.nasdanika.emf.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.json.JSONArray;
import org.nasdanika.common.Context;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.persistence.ObjectLoader;
import org.nasdanika.persistence.Storable;
import org.nasdanika.emf.EObjectAdaptable;

/**
 * Loads EMF classes from JSON arrays using {@link ObjectLoader}s. 
 * Saving is supported if the resource content implements or is adaptable to {@link Storable}. 
 * The idea of this resource is to load models from manually created JSON files or JSON files generated 
 * by some other means, e.g. from MongoDB or REST calls. 
 * @author Pavel
 *
 */
public class JsonResource extends ResourceImpl {
	
	private ObjectLoader loader;
	private ProgressMonitor progressMonitor;
	private Context context;

	public JsonResource(URI uri, ObjectLoader loader, Context context, ProgressMonitor progressMonitor) {
		super(uri);
		this.loader = loader;
		this.context = context;
		this.progressMonitor = progressMonitor;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		Object data = loader.loadJsonArray(inputStream, getURI(), progressMonitor);
		if (data instanceof Collection) {
			getContents().addAll((Collection<EObject>) data);
		} else {
			if (data instanceof SupplierFactory) {
				EObject eObject = Util.call(((SupplierFactory<EObject>) data).create(context), progressMonitor, null);
				getContents().add(eObject);
			} else if (data instanceof EObject) {
				getContents().add((EObject) data);
			} else {
				throw new IOException("Not an instance of EObject: " + data);
			}
		}
	}
	
	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		JSONArray data = new JSONArray();
		for (EObject e: getContents()) {
			Storable storable = EObjectAdaptable.adaptTo(e, Storable.class);
			if (storable == null) {
				throw new IOException("Cannot adapt " + e + " to " + Storable.class.getName());
			}
			data.put(storable.store(new URL(getURI().toString()), progressMonitor));
		}
		
		try (Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
			data.write(writer);
		}
	}

	/**
	 * If fragment is null returns the first content element.
	 */
	@Override
	public EObject getEObject(String uriFragment) {
		if (uriFragment == null) {
			return getContents().get(0);
		}
		return super.getEObject(uriFragment);
	}

}