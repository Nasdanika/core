package org.nasdanika.exec.resources;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.common.resources.BinaryEntityContainer;

import io.github.azagniotov.matcher.AntPathMatcher;

public abstract class ResourceCollection implements ConsumerFactory<BinaryEntityContainer>, Marked {
	
	private static final String RECONCILE_ACTION_KEY = "reconcile-action";
	static final String CONTENTS_KEY = "contents";
	static final String MERGER_KEY = "merger";

	private static final String PATH_KEY = "path";
	private static final String PREFIX_KEY = "prefix";
	
	private static final String INCLUDES_KEY = "includes";
	private static final String EXCLUDES_KEY = "excludes";
		
	private static final String INTERPOLATION_INCLUDES_KEY = "interpolation-includes";
	private static final String INTERPOLATION_EXCLUDES_KEY = "interpolation-excludes";
	
	protected ReconcileAction reconcileAction = ReconcileAction.OVERWRITE;
	
	protected Collection<String> includes = new ArrayList<>();
	protected Collection<String> excludes = new ArrayList<>();
	
	protected Collection<String> interpolationIncludes = new ArrayList<>();
	protected Collection<String> interpolationExcludes = new ArrayList<>();
	
	protected String path;
	protected String prefix;
	private Marker marker;
	
	@Override
	public Marker getMarker() {
		return marker;
	}
	
	@SuppressWarnings("unchecked")
	public ResourceCollection(ObjectLoader loader, Object config, URL base,	ProgressMonitor progressMonitor, Marker marker) {
		if (config instanceof Map) {
			this.marker = marker;
			Map<String,Object> configMap = (Map<String,Object>) config;
			
			Util.checkUnsupportedKeys(configMap, 
					RECONCILE_ACTION_KEY, 
					CONTENTS_KEY,
					MERGER_KEY,
					PATH_KEY,
					PREFIX_KEY,
					INCLUDES_KEY,
					EXCLUDES_KEY,
					INTERPOLATION_EXCLUDES_KEY,
					INTERPOLATION_INCLUDES_KEY);
			
			if (configMap.containsKey(RECONCILE_ACTION_KEY)) {
				Object reconcileActionObj = configMap.get(RECONCILE_ACTION_KEY);
				if (reconcileActionObj instanceof String) {
					this.reconcileAction = ReconcileAction.valueOf((String) reconcileActionObj);
				} else {
					throw new ConfigurationException(RECONCILE_ACTION_KEY + " value must be a string", Util.getMarker(configMap, RECONCILE_ACTION_KEY));
				}
			}
			path = Util.getString(configMap, PATH_KEY, false, marker);
			prefix = Util.getString(configMap, PREFIX_KEY, false, marker);
			Util.loadMultiString(configMap, INCLUDES_KEY, includes::add);
			Util.loadMultiString(configMap, EXCLUDES_KEY, excludes::add);
			Util.loadMultiString(configMap, INTERPOLATION_INCLUDES_KEY, interpolationIncludes::add);
			Util.loadMultiString(configMap, INTERPOLATION_EXCLUDES_KEY, interpolationExcludes::add);			
		} else {
			throw new ConfigurationException(getClass().getName() + " configuration shall be a map, got " + config.getClass(), marker);
		}
	}
	
	public ResourceCollection(
			Marker marker, 
			ReconcileAction reconcileAction, 
			String path, 
			String prefix,
			Collection<String> includes, 
			Collection<String> excludes, 
			Collection<String> interpolationIncludes,
			Collection<String> interpolationExcludes) {

		this.marker = marker;
		this.reconcileAction = reconcileAction;
		this.path = path;
		this.prefix = prefix;
		this.includes = includes;
		this.excludes = excludes;
		this.interpolationIncludes = interpolationIncludes;
		this.interpolationExcludes = interpolationExcludes;
	}

	/**
	 * @param path
	 * @return true if this path shall be included into the resource collection.
	 */
	protected boolean include(String path) {
		AntPathMatcher.Builder builder = new AntPathMatcher.Builder();
		AntPathMatcher matcher = builder.build();		
		boolean included = includes.isEmpty();
		if (!included) {
			for (String pattern: includes) {
				if (!Util.isBlank(pattern) && matcher.isMatch(pattern, path)) {
					included = true;
					break;
				}
			}
		}
		for (String pattern: excludes) {
			if (!Util.isBlank(pattern) && matcher.isMatch(pattern, path)) {
				return false;
			}
		}
		return included;
	}
	
	/**
	 * @param path
	 * @return true if the resource shall be interpolated.
	 */
	protected boolean shouldInterpolate(String path) {
		AntPathMatcher.Builder builder = new AntPathMatcher.Builder();
		AntPathMatcher matcher = builder.build();
		boolean interpolate = false; 
		for (String pattern: interpolationIncludes) {
			if (!Util.isBlank(pattern) && matcher.isMatch(pattern, path)) {
				interpolate = true;
				break;
			}
		}
		for (String pattern: interpolationExcludes) {
			if (!Util.isBlank(pattern) && matcher.isMatch(pattern, path)) {
				return false;
			}
		}
		return interpolate;		
	}
	
	/**
	 * Interpolates input stream if path matches interpolation includes and does not match interpolation exculdes.
	 * @param path
	 * @param contents
	 * @return
	 */
	protected InputStream interpolate(Context context, String path, InputStream in) throws Exception {
		return shouldInterpolate(path) ? Util.filter(context, in, context::interpolateToString) : in;
	}
	

}
