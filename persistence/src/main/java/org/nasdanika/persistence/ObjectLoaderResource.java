package org.nasdanika.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.nasdanika.common.Context;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.MarkedYAMLException;

/**
 * Resource loaded from data:application/json-eobject-spec... URI. Supports json and yaml formats. Defaults to YAML if format is not specified.
 * @author Pavel
 *
 */
public abstract class ObjectLoaderResource extends ResourceImpl {
	
	private static final String BASE_KEY = "base";
	private static final String QUALIFIER_KEY = "qualifier";
	private static final String FORMAT_KEY = "format";
	private static final String SPEC_KEY = "spec";
	private static final String BASE64 = ";base64,";
	private static final String OBJECT_SPEC_URI_PREFIX = "data:application/json-eobject-spec";
	
	/**
	 * 
	 * @param spec
	 * @param format
	 * @param base
	 * @param qualifier Used to differentiate two identical specs so they yield different URI's and are considered to be different resources. 
	 * Can be null. If null, the URI is a "value object" URI, and otherwise it is an "entity URI".
	 * @return
	 */
	public static URI encode(String spec, String format, URI base, Object qualifier) {
		JSONObject specObj = new JSONObject();
		specObj.put(SPEC_KEY, spec);
		if (!org.nasdanika.common.Util.isBlank(format)) {
			specObj.put(FORMAT_KEY, format);
		}
		if (base != null) {
			specObj.put(BASE_KEY, base.toString());
		}
		if (qualifier != null) {
			specObj.put(QUALIFIER_KEY, qualifier);			
		}
		String specStr = specObj.toString();
		return URI.createURI(OBJECT_SPEC_URI_PREFIX + BASE64 + URLEncoder.encode(Base64.encodeBase64String(specStr.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8));		
	}
	
	public ObjectLoaderResource(URI uri) {
		super(uri);
	}
	
	protected abstract ObjectLoader getObjectLoader();
	
	protected abstract ProgressMonitor getProgressMonitor();
	
	protected abstract Context getContext();
	
	@SuppressWarnings("unchecked")
	@Override
	public void load(Map<?, ?> options) throws IOException {
		String uriStr = getURI().toString();
		
		// Loading from data:... URI
		if (uriStr.startsWith(OBJECT_SPEC_URI_PREFIX +";") || uriStr.startsWith(OBJECT_SPEC_URI_PREFIX +",")) {
			uriStr = uriStr.substring(OBJECT_SPEC_URI_PREFIX.length());
			if (uriStr.startsWith(BASE64)) {
				uriStr = new String(Base64.decodeBase64(URLDecoder.decode(uriStr.substring(BASE64.length()), StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
			} else {
				uriStr = URLDecoder.decode(uriStr.substring(1), StandardCharsets.UTF_8);
			}
			JSONObject specObj = new JSONObject(new JSONTokener(uriStr));
			boolean isYaml;
			String spec = specObj.getString(SPEC_KEY);
			if (specObj.has(FORMAT_KEY)) {
				isYaml = "yaml".equalsIgnoreCase(specObj.getString(FORMAT_KEY));
			} else {
				isYaml = spec == null || !(spec.trim().startsWith("{") && spec.trim().endsWith("}")); // If starts with { and ends with } assuming a Json object definition.
			}
			URI base = specObj.has(BASE_KEY) ? URI.createURI(specObj.getString(BASE_KEY)) : null;
			ObjectLoader objectLoader = getObjectLoader();
			ProgressMonitor progressMonitor = getProgressMonitor();
			Object obj = isYaml ? objectLoader.loadYaml(spec, base, progressMonitor) : objectLoader.loadJsonObject(spec, base, progressMonitor);
			
			if (obj instanceof SupplierFactory) {
				obj = ((SupplierFactory<Object>) obj).create(getContext()).call(progressMonitor, this::onDiagnostic);
			} 
			
			if (obj instanceof EObject) {
				getContents().add((EObject) obj);
			} else {	
				throw new NasdanikaException("Not an instance of EObject or SupplierFactory: " + obj);
			}
		} else {
			super.load(options);
		}
	}
	
	/**
	 * Called by the supplier factory.
	 * @param diagnostic
	 */
	protected abstract void onDiagnostic(org.nasdanika.common.Diagnostic diagnostic);	
	
	@Override
	public EObject getEObject(String uriFragment) {
		return super.getEObject(uriFragment == null ? "/" : uriFragment);
	}
	
	protected abstract boolean isJsonObject();
	
	protected abstract boolean isJsonArray();
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		ProgressMonitor progressMonitor = getProgressMonitor();
		try {
			Object data;
			if (isJsonArray()) {
				 data = getObjectLoader().loadJsonArray(inputStream, getURI(), progressMonitor);				
			} else if (isJsonObject()) {
				data = getObjectLoader().loadJsonObject(inputStream, getURI(), progressMonitor);				
			} else {
				data = getObjectLoader().loadYaml(inputStream, getURI(), progressMonitor);				
			}
			
			if (data instanceof Collection) {
				getContents().addAll((Collection<EObject>) data);
			} else {
				if (data instanceof SupplierFactory) {
					EObject eObject = ((SupplierFactory<EObject>) data).create(getContext()).call(progressMonitor, this::onDiagnostic);
					getContents().add(eObject);
				} else if (data instanceof EObject) {
					getContents().add((EObject) data);
				} else {
					throw new IOException("Not an instance of EObject: " + data);
				}
			}
		} catch (MarkedYAMLException e) {
			throw new ConfigurationException(e.getMessage(), e, new MarkerImpl(getURI().toString(), e.getProblemMark()));
		} catch (RuntimeException | IOException e) {
			throw e;
		} catch (Exception e) {
			throw new NasdanikaException(e);
		}
	}
	
	protected abstract Storable toStorable(EObject eObj);
		
	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		List<Object> data = new ArrayList<>();
		for (EObject e: getContents()) {
			Storable storable = toStorable(e);
			if (storable == null) {
				throw new IOException("Cannot adapt " + e + " to " + Storable.class.getName());
			}
			data.add(storable.store(new URL(getURI().toString()), getProgressMonitor()));
		}
		
		DumperOptions dumperOptions = new DumperOptions();
		dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK);
		dumperOptions.setIndent(4);
		Yaml yaml = new Yaml(dumperOptions);
		yaml.dump(data.size() == 1 ? data.get(0) : data, new OutputStreamWriter(outputStream));		
	}
	
}
