package org.nasdanika.persistence;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.nasdanika.common.ProgressMonitor;

/**
 * Resource loaded from data:application/json-eobject-spec... URI. Supports json and yaml formats. Defaults to YAML if format is not specified.
 * @author Pavel
 *
 */
public abstract class ObjectLoaderResource extends ResourceImpl {
	
	private static final String BASE_KEY = "base";
	private static final String FORMAT_KEY = "format";
	private static final String SPEC_KEY = "spec";
	private static final String BASE64 = ";base64,";
	private static final String OBJECT_SPEC_URI_PREFIX = "data:application/json-eobject-spec";
	
	public static URI encode(String spec, String format, URI base) {
		JSONObject specObj = new JSONObject();
		specObj.put(SPEC_KEY, spec);
		if (!org.nasdanika.common.Util.isBlank(format)) {
			specObj.put(FORMAT_KEY, format);
		}
		if (base != null) {
			specObj.put(BASE_KEY, base.toString());
		}
		String specStr = specObj.toString();
		return URI.createURI(OBJECT_SPEC_URI_PREFIX + BASE64 + URLEncoder.encode(Base64.encodeBase64String(specStr.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8));		
	}
	
	public ObjectLoaderResource(URI uri) {
		super(uri);
	}
	
	protected abstract ObjectLoader getObjectLoader();
	
	protected abstract ProgressMonitor getProgressMonitor();
	
	@Override
	public void load(Map<?, ?> options) throws IOException {
		String uriStr = getURI().toString();
		if (!uriStr.startsWith(OBJECT_SPEC_URI_PREFIX +";") && !uriStr.startsWith(OBJECT_SPEC_URI_PREFIX +",")) {
			throw new IllegalArgumentException("Unsupported URI: " + uriStr + ", URI should start with " + OBJECT_SPEC_URI_PREFIX + " followed by ;base64, or ,");
		}
		uriStr = uriStr.substring(OBJECT_SPEC_URI_PREFIX.length());
		if (uriStr.startsWith(BASE64)) {
			uriStr = new String(Base64.decodeBase64(URLDecoder.decode(uriStr.substring(BASE64.length()), StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
		} else {
			uriStr = URLDecoder.decode(uriStr.substring(1), StandardCharsets.UTF_8);
		}
		JSONObject specObj = new JSONObject(new JSONTokener(uriStr));
		boolean isYaml = !specObj.has(FORMAT_KEY) || "yaml".equalsIgnoreCase(specObj.getString(FORMAT_KEY));
		URI base = specObj.has(BASE_KEY) ? URI.createURI(specObj.getString(BASE_KEY)) : null;
		ObjectLoader objectLoader = getObjectLoader();
		Object obj = isYaml ? objectLoader.loadYaml(uriStr, base, getProgressMonitor()) : objectLoader.loadJsonObject(uriStr, base, getProgressMonitor());
		getContents().add((EObject) obj);
	}

}
