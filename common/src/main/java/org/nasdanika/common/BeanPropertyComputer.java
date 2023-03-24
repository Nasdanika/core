package org.nasdanika.common;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * Wraps an object as context with properties retrieved using getters. 
 * Getters return values are processed as follows: list and array elements are addressed using indices, map elements using keys,
 * {@link File}s are converted to absolute paths, other objects are wrapped into BeanContext. 
 * @author Pavel
 */
public class BeanPropertyComputer implements PropertyComputer {
	
	private Map<String, Method> getters = new HashMap<>();
	private Object target;

	public BeanPropertyComputer(Object target) {
		this.target = target;
		if (target != null) {
			for (Method method: target.getClass().getMethods()) {
				if (method.getParameterCount() == 0 && method.getName().startsWith("get") && !Void.class.equals(method.getReturnType())) {
					String propName = StringUtils.uncapitalize(method.getName().substring(3));
					getters.put(propName, method);
				}
			}
		}
	}
	
	@Override
	public String toString() {
		return target.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T compute(Context context, String key, String path, Class<T> type) {
		if (Util.isBlank(path)) {
			return (T) target.toString();
		}
		String[] pa = path.split("/");
		Method getter = getters.get(pa[0]);
		if (getter == null) {
			return null;
		}
		try {
			Object result = getter.invoke(target);
			Object wrapped = wrap(result);
			if (wrapped == null || pa.length == 1) {
				return (T) wrapped;
			}
			if (result instanceof List) {
				int idx = Integer.parseInt(pa[1]);
				List<?> rList = (List<?>) result;
				if (idx >= 0 && idx < rList.size()) {
					wrapped = wrap(rList.get(idx));
					if (wrapped == null || pa.length == 2) {
						return (T) wrapped;						
					}
					if (wrapped instanceof PropertyComputer) {
						String subPath = String.join("/", Arrays.copyOfRange(pa, 2, pa.length));
						return ((PropertyComputer) wrapped).compute(context, pa[1], subPath, type);
					}
					return null;
				}
				return null;
			}
			if (result instanceof Map) {
				wrapped = wrap(((Map<?,?>) result).get(pa[1]));
				if (wrapped == null || pa.length == 2) {
					return (T) wrapped;						
				}
				if (wrapped instanceof PropertyComputer) {
					String subPath = String.join("/", Arrays.copyOfRange(pa, 2, pa.length));
					return ((PropertyComputer) wrapped).compute(context, pa[1], subPath, type);
				}
				return null;
			}
			if (wrapped == null || pa.length == 2) {
				return (T) wrapped;						
			}
			if (wrapped instanceof PropertyComputer) {
				String subPath = String.join("/", Arrays.copyOfRange(pa, 1, pa.length));
				return ((PropertyComputer) wrapped).compute(context, pa[1], subPath, type);
			}
			return null;
			
		} catch (Exception e) {
			return (T) e.toString();
		}
	}
	
	private static Class<?>[] NO_WRAP = { String.class, Number.class, Date.class, URL.class };
	
	protected Object wrap(Object object) {
		if (object == null) {
			return object;
		}
		for (Class<?> nwc: NO_WRAP) {
			if (nwc.isInstance(object)) {
				return object;
			}
		}
		return new BeanPropertyComputer(object);
	}

}
