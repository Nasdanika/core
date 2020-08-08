package org.nasdanika.ncore.gen;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.Converter;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Function;
import org.nasdanika.common.MapCompoundSupplier;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.ncore.AbstractEntry;
import org.nasdanika.ncore.HttpCall;

public class HttpCallSupplierFactory implements SupplierFactory<InputStream> {
	
	private HttpCall target;

	public HttpCallSupplierFactory(HttpCall target) {
		this.target = target;
	}
		
	/**
	 * Override to provide request body.
	 * @return
	 */
	protected Supplier<InputStream> createBodySupplier(Context context) throws Exception {
		EList<EObject> body = target.getBody();
		if (body.isEmpty()) {
			return null;
		}
		throw new UnsupportedOperationException();
	}
	
	protected Supplier<Map<String,Object>> createHeadersSupplier(Context context) throws Exception {
		EList<AbstractEntry> headers = target.getHeaders();
		if (headers.isEmpty()) {
			return null;
		}
		
		MapCompoundSupplier<String, Object> headersWork = new MapCompoundSupplier<>("Headers");
		for (AbstractEntry e: headers) {
			headersWork.put(e.getName(), EObjectAdaptable.adaptToSupplierFactory(e, Object.class).create(context));
		}

		return headersWork;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Supplier<InputStream> create(Context context) throws Exception {
		MapCompoundSupplier<String,Object> ret = new MapCompoundSupplier<>(target.getTitle());
		
		// Ugly, but what to do?
		ret.put("Headers", (Supplier) createHeadersSupplier(context));		
		
		Supplier<InputStream> bodyWork = createBodySupplier(context);
		if (bodyWork != null) {
			ret.put("Body", (Supplier) bodyWork);
		}
				
		return ret.then(new Function<Map<String,Object>,InputStream>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return target.getTitle();
			}

			@Override
			public InputStream execute(Map<String, Object> arg, ProgressMonitor progressMonitor) throws Exception {
				URL url = new URL(context.interpolate(target.getUrl())); // Resole relative to model?
				URLConnection connection = url.openConnection();
				if (!(connection instanceof HttpURLConnection)) {
					throw new IllegalArgumentException("Not an HTTP(s) URL: "+url);
				}
				
				HttpURLConnection httpConnection = (HttpURLConnection) connection;
				httpConnection.setRequestMethod(target.getMethod().getLiteral());
				Object body = arg.get("Body");
				httpConnection.setDoOutput(body != null);
				Converter converter = context.get(Converter.class);
				if (converter == null) {
					converter = DefaultConverter.INSTANCE;
				}
				for (Map.Entry<String, Object> header: ((Map<String,Object>) arg.get("Headers")).entrySet()) {						
					httpConnection.setRequestProperty(header.getKey(), converter.convert(header.getValue(), String.class));
				}
				httpConnection.setConnectTimeout(target.getConnectTimeout() * 1000); 
				httpConnection.setReadTimeout(target.getReadTimeout() * 1000); 
				
				if (body != null) {
					try (OutputStream bout = new BufferedOutputStream(connection.getOutputStream()); InputStream bin = new BufferedInputStream(converter.convert(body, InputStream.class))) {
						int b;
						while ((b = bin.read()) != -1) {
							bout.write(b);
						}
					}
				}
				
				int responseCode = httpConnection.getResponseCode();
				if (responseCode == target.getSuccessCode()) { 
					return httpConnection.getInputStream();  
				}
				
				throw new NasdanikaException("HTTP Call to "+url+" has failed with response: "+responseCode+" "+httpConnection.getResponseMessage()); // TODO - body to message if message is empty, e.g. JSON details. Also TODO - to common.
			}
			
		});
	}
	
}