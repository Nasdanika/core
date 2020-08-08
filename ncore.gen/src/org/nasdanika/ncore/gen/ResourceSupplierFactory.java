package org.nasdanika.ncore.gen;

import java.net.URL;

import org.nasdanika.common.Context;
import org.nasdanika.common.Converter;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Supplier;
import org.nasdanika.emf.EmfUtil;
import org.nasdanika.ncore.Resource;

public class ResourceSupplierFactory extends ServiceSupplierFactory<Resource> {
	
	public ResourceSupplierFactory(Resource target) {
		super(target);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Supplier<Object> create(Context context) throws Exception {
		return Supplier.<Object>fromCallable(() -> {
			URL url = EmfUtil.resolveReference(target.eResource(), context.interpolateToString(target.getLocation()));
			if (org.nasdanika.common.Util.isBlank(target.getType())) {
				return url;
			}
			Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
			ClassLoader classLoader = context.get(ClassLoader.class);
			if (classLoader == null) {
				classLoader = getClass().getClassLoader();
			}
			Class type = classLoader.loadClass(target.getType());
			Object ret = converter.convert(url, type);
			if (ret instanceof String && target.isInterpolate()) {
				return context.interpolate((String) ret);
			}
			return ret;
		}, target.getTitle(), 1);
	}
	
}