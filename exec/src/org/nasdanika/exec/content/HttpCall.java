package org.nasdanika.exec.content;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.exec.Loader;

/**
 * Config is either string (URL) or map.
 * @author Pavel
 *
 */
public class HttpCall implements SupplierFactory<InputStream>, Marked {
	
	private static final String METHOD_KEY = "method";
	private static final String URL_KEY = "url";
	private static final String HEADERS_KEY = "headers";
	private static final String BODY_KEY = "body";
	private static final String SUCCESS_CODE_KEY = "success-code";
	private static final String CONNECT_TIMEOUT_KEY = "connect-timeout";
	private static final String READ_TIMEOUT_KEY = "read-timeout";

	protected String method = "GET";
	protected SupplierFactory<InputStream> body;
	protected Map<String, SupplierFactory<InputStream>> headers = new LinkedHashMap<>();
	protected URL base;
	protected String url;
	private Marker marker;
	protected String name;
	protected int successCode = 200;
	protected int connectTimeout = 60;
	protected int readTimeout = 60;
	
	@Override
	public Marker getMarker() {
		return marker;
	}
	
	@SuppressWarnings("unchecked")
	public HttpCall(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		this.base = base;
		this.marker = marker;
		if (config instanceof String) {
			this.url = (String) config;
		} else if (config instanceof Map) {
			Map<String,Object> configMap = (Map<String,Object>) config;
			Loader.checkUnsupportedKeys(configMap, METHOD_KEY, URL_KEY, HEADERS_KEY, BODY_KEY, SUCCESS_CODE_KEY, CONNECT_TIMEOUT_KEY, READ_TIMEOUT_KEY);			
			if (configMap.containsKey(METHOD_KEY)) {
				Object methodObj = configMap.get(METHOD_KEY);
				if (methodObj instanceof String) {
					this.method = (String) methodObj;
				} else {
					throw new ConfigurationException(METHOD_KEY + " value must be a string", Util.getMarker(configMap, METHOD_KEY));
				}
			}
			
			name = "HTTP Call" + (marker == null ? "" : " " + marker);
			
			if (!configMap.containsKey(URL_KEY)) {
				throw new ConfigurationException(URL_KEY + " is missing", marker);
			}
			
			Object urlObj = configMap.get(URL_KEY);
			if (urlObj instanceof String) {
				this.url = (String) urlObj;
			} else {
				throw new ConfigurationException(URL_KEY + " value must be a string", Util.getMarker(configMap, URL_KEY));
			}
			
			if (configMap.containsKey(HEADERS_KEY)) {
				Object headersObj = configMap.get(HEADERS_KEY);
				if (headersObj instanceof Map) {
					for (Entry<String, Object> he: ((Map<String,Object>) headersObj).entrySet()) {
						headers.put(he.getKey(), Loader.asSupplierFactory(loader.load(he.getValue(), base, progressMonitor)));
					}
				} else {
					throw new ConfigurationException(HEADERS_KEY + " value must be a map", Util.getMarker(configMap, HEADERS_KEY));
				}
			}			
			
			if (configMap.containsKey(BODY_KEY)) {
				body = Loader.asSupplierFactory(loader.load(configMap.get(BODY_KEY), base, progressMonitor));
			}
			
			if (configMap.containsKey(SUCCESS_CODE_KEY)) {
				Object successCodeObj = configMap.get(SUCCESS_CODE_KEY);
				if (successCodeObj instanceof Number) {
					this.successCode = ((Number) successCodeObj).intValue();
				} else {
					throw new ConfigurationException(SUCCESS_CODE_KEY + " value must be a number", Util.getMarker(configMap, SUCCESS_CODE_KEY));
				}
			}
			
			if (configMap.containsKey(CONNECT_TIMEOUT_KEY)) {
				Object connectTimeoutObj = configMap.get(CONNECT_TIMEOUT_KEY);
				if (connectTimeoutObj instanceof Number) {
					this.connectTimeout = ((Number) connectTimeoutObj).intValue();
				} else {
					throw new ConfigurationException(CONNECT_TIMEOUT_KEY + " value must be a number", Util.getMarker(configMap, CONNECT_TIMEOUT_KEY));
				}
			}
			
			if (configMap.containsKey(READ_TIMEOUT_KEY)) {
				Object readTimeoutObj = configMap.get(READ_TIMEOUT_KEY);
				if (readTimeoutObj instanceof Number) {
					this.readTimeout = ((Number) readTimeoutObj).intValue();
				} else {
					throw new ConfigurationException(READ_TIMEOUT_KEY + " value must be a number", Util.getMarker(configMap, READ_TIMEOUT_KEY));
				}
			}			
		} else {
			throw new ConfigurationException("HTTP call configuration shall be a string or a map, got " + config.getClass(), marker);
		}
	}
	
	public HttpCall(
			Marker marker,
			URL base, 
			String url,
			String method, 
			Map<String, SupplierFactory<InputStream>> headers,
			SupplierFactory<InputStream> body,
			int successCode,
			int connectTimeout,
			int readTimeout) {
		
		this.marker = marker;
		this.base = base;
		this.url = url;
		this.method = method;
		this.headers.putAll(headers);
		this.body = body;
		this.successCode = successCode;
		this.connectTimeout = connectTimeout;
		this.readTimeout = readTimeout;
		name = "HTTP Call" + (marker == null ? "" : " " + marker);
	}
	
	private FunctionFactory<BiSupplier<Map<String, InputStream>, InputStream>,InputStream> httpCallFactory = context -> new Function<BiSupplier<Map<String, InputStream>, InputStream>, InputStream>() {

		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return name;
		}

		@Override
		public InputStream execute(BiSupplier<Map<String, InputStream>, InputStream> headersAndBody, ProgressMonitor progressMonitor) throws Exception {
			return call(context, headersAndBody.getFirst(), headersAndBody.getSecond(), progressMonitor);
		}
		
	};
	
	@Override
	public Supplier<InputStream> create(Context iContext) throws Exception {
		MapCompoundSupplierFactory<String,InputStream> headersSupplierFactory = new MapCompoundSupplierFactory<>("Headers", headers);
		SupplierFactory<InputStream> bf = body;
		if (bf == null) {
			bf = context -> new Supplier<InputStream>() {

				@Override
				public double size() {
					return 1;
				}

				@Override
				public String name() {
					return "Empty body";
				}

				@Override
				public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
					return null;
				}
				
			};
		}
		return headersSupplierFactory.then(bf.asFunctionFactory()).then(httpCallFactory).create(iContext);
	}
	
	protected InputStream call(Context context, Map<String, InputStream> headers, InputStream body, ProgressMonitor progressMonitor) throws Exception {		
		URL theURL = new URL(base, context.interpolateToString(url));
		try (ProgressMonitor subMonitor = progressMonitor.split("HTTP " + method + " to " + theURL, 2, marker)) {
			URLConnection connection = theURL.openConnection();
			if (!(connection instanceof HttpURLConnection)) {
				throw new IllegalArgumentException("Not an HTTP(s) url: "+url);
			}
			
			HttpURLConnection httpConnection = (HttpURLConnection) connection;
			httpConnection.setRequestMethod(method);
			httpConnection.setDoOutput(body != null);
			for (Entry<String, InputStream> hwe: headers.entrySet()) {
				httpConnection.setRequestProperty(hwe.getKey(), Util.toString(context, hwe.getValue()));
			}
			httpConnection.setConnectTimeout(connectTimeout * 1000); 
			httpConnection.setReadTimeout(readTimeout * 1000);
			
			subMonitor.worked(1, "Open connection");
					
			if (body != null) {
				try (OutputStream bout = new BufferedOutputStream(connection.getOutputStream())) {
					try (InputStream bin = new BufferedInputStream(body)) {
						int b;
						while ((b = bin.read()) != -1) {
							bout.write(b);
						}
					}
				}
				subMonitor.worked(1, "Wrote request body");
			}
			
			int responseCode = httpConnection.getResponseCode();
			subMonitor.worked(1, "Received response: " + responseCode);
			if (responseCode == successCode) { 
				return httpConnection.getInputStream();
			}
			
			String location = marker == null ? "" : " at " + marker;
			throw new NasdanikaException("HTTP Call to "+theURL+" has failed with response: "+responseCode+" "+httpConnection.getResponseMessage() + location);
		}
	}	

}
