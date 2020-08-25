package org.nasdanika.exec.java;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.exec.Loader;

public abstract class Member implements SupplierFactory<InputStream>, Marked {
	
	private static final String NAME_KEY = "name";
	private static final String MODIFIERS_KEY = "modifiers";
	private static final String ANNOTATIONS_KEY = "annotations";
	private static final String TYPE_PARAMETERS_KEY = "type-parameters";
	private static final String COMMENT_KEY = "comment";
	private static final String BODY_KEY = "body";
	
	protected String name;
	protected List<String> modifiers = new ArrayList<>();
	protected List<String> annotations = new ArrayList<>();
	protected List<String> typeParameters = new ArrayList<>();
	protected SupplierFactory<InputStream> comment = SupplierFactory.empty();
	protected SupplierFactory<InputStream> body = SupplierFactory.empty();
	private Marker marker;
	
	@Override
	public Marker getMarker() {
		return marker;
	}
	
	@SuppressWarnings("unchecked")
	protected Member(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		if (config instanceof Map) {
			this.marker = marker;
			Map<String,Object> configMap = (Map<String,Object>) config;
			name = Loader.getString(configMap, NAME_KEY, true, marker);
			Loader.loadMultiString(configMap, MODIFIERS_KEY, modifiers::add);
			Loader.loadMultiString(configMap, ANNOTATIONS_KEY, annotations::add);
			Loader.loadMultiString(configMap, TYPE_PARAMETERS_KEY, typeParameters::add);
			if (configMap.containsKey(COMMENT_KEY)) {
				comment = Loader.asSupplierFactory(loader.load(configMap.get(COMMENT_KEY), base, progressMonitor), Util.getMarker(configMap, COMMENT_KEY));
			}
			if (configMap.containsKey(BODY_KEY)) {
				body = Loader.asSupplierFactory(loader.load(configMap.get(BODY_KEY), base, progressMonitor), Util.getMarker(configMap, BODY_KEY));
			}
		} else {
			throw new ConfigurationException(getClass().getName() + " configuration shall be a map, got " + config.getClass(), marker);
		}
	}
	
	private FunctionFactory<BiSupplier<InputStream, InputStream>,InputStream> memberFactory = context -> new Function<BiSupplier<InputStream, InputStream>,InputStream>() {
		
		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return context.interpolateToString(Member.this.name);
		}

		@Override
		public InputStream execute(BiSupplier<InputStream, InputStream> commentAndBody, ProgressMonitor progressMonitor) throws Exception {
			String comment = Util.toString(context, commentAndBody.getFirst());				
			StringBuilder commentBuilder = new StringBuilder(System.lineSeparator())
					.append(System.lineSeparator())
					.append("/**")
					.append(System.lineSeparator());
			
			if (comment != null) {
				try (BufferedReader br = new BufferedReader(new StringReader(comment))) {
					String line;
					while ((line = br.readLine()) != null) {
						commentBuilder.append(" * ").append(line).append(System.lineSeparator());						
					}
				}
			}
			commentBuilder.append(" * @generated").append(System.lineSeparator());
			commentBuilder.append(" */").append(System.lineSeparator());
			
			return Util.toStream(
					context, 
					generate(
							context, 
							commentBuilder.toString(), 
							Util.toString(context, commentAndBody.getSecond()), 
							progressMonitor));
		}
		
	};
			
	@Override
	public Supplier<InputStream> create(Context iContext) throws Exception {		
		ImportManager importManager = iContext.get(ImportManager.class);				
		return comment.then(body.asFunctionFactory()).then(memberFactory).create(Context.singleton("import", importManager).compose(iContext));
	}
	
	protected abstract String generate(Context context, String comment, String body, ProgressMonitor progressMonitor) throws Exception;
	
	/**
	 * Interpolates the argument collection. 
	 * If any of collection elements are collections they are also interpolated and all elements are added to the returned collection. 
	 * @param context
	 * @param collection
	 * @return
	 */
	protected static Collection<String> flatten(Context context, Collection<?> collection) {
		Collection<String> ret = new ArrayList<>(); 
		for (Object e: context.interpolate(collection)) {
			if (e instanceof Collection) {
				ret.addAll(flatten(context, (Collection<?>) e));
			} else if (e == null) {
				ret.add(null);
			} else {
				ret.add(e.toString());
			}
		}
		return ret;
	}
	
	protected void appendModifiers(Context context, StringBuilder builder) {
		for (String modifier: flatten(context, modifiers)) {
			builder.append(modifier).append(" ");
		}
	}
	
	protected void appendAnnotations(Context context, StringBuilder builder) {
		for (String ann: flatten(context, annotations)) {
			builder.append("@").append(ann).append(System.lineSeparator());				
		}
	}
	
	protected void appendTypeParameters(Context context, StringBuilder builder) {
		if (!typeParameters.isEmpty()) {
			builder.append("<");
			boolean isFirst = true;
			for (String tp: flatten(context, typeParameters)) {
				if (!isFirst) {
					builder.append(",");
				}
				builder.append(tp);
				isFirst = false;
			}
			builder.append(">");
		}
	}	
	
}
