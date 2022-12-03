package org.nasdanika.drawio;

import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.json.JSONObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.processor.ProcessorConfig;

/**
 * Uses {@link ResourceSet} to load objects from spec-uri.
 * Text specs get encoded into a data URL by creating a JSON object with spec, format, and base fields. The object is converted to string and base64 encoded.
 * application/json-eobject-spec is used as media type. 
 * @author Pavel
 *
 * @param <T>
 */
public abstract class ResourceSetDrawioEObjectFactory<T extends EObject> extends DrawioEObjectFactory<T> {
	
	/**
	 * @return {@link ResourceSet} to load objects. 
	 */
	protected abstract ResourceSet getResourceSet();
	
	/**
	 * Returns an object identified by specURI by calling {@link ResourceSet}.getEObject(). A single resource root object has <code>/</code> URI fragment by default.
	 * @param specURI
	 * @param config
	 * @param progressMonitor
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected T load(URI specURI, String specFormat, ProcessorConfig<T> config, Context context, ProgressMonitor progressMonitor) {
		ResourceSet resourceSet = getResourceSet();
		return (T) resourceSet.getEObject(specURI, true);
	}

	/**
	 * Encodes spec as a data URI and calls load(uri, ...)
	 */
	@Override
	protected T load(String spec, String specFormat, URI specBase, ProcessorConfig<T> config, Context context, ProgressMonitor progressMonitor) {
		JSONObject specObj = new JSONObject();
		specObj.put("spec", spec);
		if (!org.nasdanika.common.Util.isBlank(specFormat)) {
			specObj.put("format", specFormat);
		}
		if (specBase != null) {
			specObj.put("base", specBase.toString());
		}
		String specStr = specObj.toString();
		URI specURI = URI.createURI(ObjectLoaderResource. ";base64," + Base64.encodeBase64String(specStr.getBytes(StandardCharsets.UTF_8)));
		return load(specURI, specFormat, config, context, progressMonitor);
	}

}
