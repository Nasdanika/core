package org.nasdanika.exec.content;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;

import freemarker.cache.URLTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.ObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.WrappingTemplateModel;

public class FreeMarker implements SupplierFactory<InputStream>, Marked {
	
	private static final String BASE_KEY = "base";
	private static final String TEMPLATE_KEY = "template";
	private static final String MODEL_KEY = "model";
	
	protected URL base;
	protected String name;
	protected String template;
	protected String model;	
	private Marker marker;
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	public FreeMarker(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof Map) {
			this.marker = marker;
			@SuppressWarnings("unchecked")
			Map<String,Object> configMap = (Map<String,Object>) config;
			Util.checkUnsupportedKeys(configMap, BASE_KEY, TEMPLATE_KEY, MODEL_KEY);
			if (configMap.containsKey(BASE_KEY)) {
				Object baseObj = configMap.get(BASE_KEY);
				if (baseObj instanceof String) {
					this.base = new URL(base, (String) baseObj);
				} else {
					throw new ConfigurationException(BASE_KEY + " value must be a string", Util.getMarker(configMap, BASE_KEY));
				}
			} else {
				this.base = base;
			}
			name = "FreeMarker" + (marker == null ? "" : " " + marker);
			
			if (!configMap.containsKey(TEMPLATE_KEY)) {
				throw new ConfigurationException(TEMPLATE_KEY + " is missing", marker);
			}
			
			Object templateObj = configMap.get(TEMPLATE_KEY);
			if (templateObj instanceof String) {
				this.template = (String) templateObj;
			} else {
				throw new ConfigurationException(TEMPLATE_KEY + " value must be a string", Util.getMarker(configMap, TEMPLATE_KEY));
			}
			
			if (configMap.containsKey(MODEL_KEY)) {
				Object modelObj = configMap.get(MODEL_KEY);
				if (modelObj instanceof String) {
					this.model = (String) modelObj;
				} else {
					throw new ConfigurationException(MODEL_KEY + " value must be a string", Util.getMarker(configMap, MODEL_KEY));
				}
			}			
		} else {
			throw new ConfigurationException("FreeMarker configuration shall be a map, got " + config.getClass(), marker);
		}
	}
	
	public FreeMarker(
			Marker marker,
			URL base,
			String template, 
			String model) {

		this.marker = marker;
		this.base = base;
		this.template = template;
		this.model = model;
		name = "FreeMarker" + (marker == null ? "" : " " + marker);
	}
	
	public FreeMarker(String name, URL base, String template, String model) {
		this.name = name;
		this.base = base;
		this.template = template;
		this.model = model;
	}

	@Override
	public Supplier<InputStream> create(Context iContext) throws Exception {
		return new Supplier<InputStream>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return name;
			}

			@Override
			public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				try (OutputStreamWriter writer = new OutputStreamWriter(baos, iContext.get(Charset.class, StandardCharsets.UTF_8))) {
					writer.write(evaluate(iContext));
				}
				baos.close();
				return new ByteArrayInputStream(baos.toByteArray());
			}
			
		};
	}
	
	protected String evaluate(Context context) throws Exception {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
		cfg.setLocalizedLookup(false);
		cfg.setTemplateLoader(new URLTemplateLoader() {
			
			@Override
			protected URL getURL(String name) {
				try {
					return new URL(base, name);
				} catch (MalformedURLException e) {
					throw new IllegalArgumentException("Malformed name: " + name, e);
				}
			}
		});						

		cfg.setDefaultEncoding(context.get(Charset.class, StandardCharsets.UTF_8).name());
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		cfg.setLogTemplateExceptions(false);
		
		class ContextWrapper extends WrappingTemplateModel implements TemplateHashModel {
			
			private Context context;

			ContextWrapper(Context context, ObjectWrapper ow) {
				super(ow);
				this.context = context;
			}

			@Override
			public TemplateModel get(String key) throws TemplateModelException {
				return wrap(context.get(key));
			}

			@Override
			public boolean isEmpty() throws TemplateModelException {
				return false;
			}
			
		}
		
		cfg.setObjectWrapper(new DefaultObjectWrapper(cfg.getIncompatibleImprovements()) {
			
			@Override
			protected TemplateModel handleUnknownType(Object obj) throws TemplateModelException {
				if (obj instanceof Context) {
					return new ContextWrapper((Context) obj, this);
				}
				return super.handleUnknownType(obj);
			}
			
		});					
		
		Template temp = cfg.getTemplate(context.interpolateToString(template));
		Writer out = new StringWriter();
		Object modelValue = context;
		String modelKey = model;
		if (!Util.isBlank(modelKey)) {
			modelValue = context.get(modelKey);
		}
		temp.process(modelValue, out);
		out.close();
		return out.toString();		
	}
	

}
