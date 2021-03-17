package org.nasdanika.common.persistence;

import java.io.InputStream;

import org.nasdanika.common.Converter;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;

public class TypedSupplierFactoryAttribute<T> extends AbstractFeatureDelegate<SupplierFactoryFeature<?>> implements SupplierFactoryFeature<T> {

	private boolean interpolate;
	private Class<T> type;

	public TypedSupplierFactoryAttribute(Class<T> type, SupplierFactoryFeature<?> delegate, boolean interpolate) {
		super(delegate);
		this.type = type;
		this.interpolate = interpolate;
	}

	public TypedSupplierFactoryAttribute(Class<T> type, Feature<?> delegate, boolean interpolate) {
		this(type, interpolate ? new InputStreamSupplierFactoryAttribute(delegate) : new DelegatingSupplierFactoryFeature<>(delegate), interpolate);
	}

	@Override
	@SuppressWarnings("unchecked")
	public SupplierFactory<T> getValue() {
		if (!delegate.isLoaded()) {
			return SupplierFactory.from(null, "Null from not loaded feature " + getKey()); 
		}
		
		FunctionFactory<Object,T> converter = context -> {
			return new Function<Object, T>() {

				@Override
				public double size() {
					return 1;
				}

				@Override
				public String name() {
					return "Converting String to " + type;
				}

				@Override
				public T execute(Object obj, ProgressMonitor progressMonitor) throws Exception {
					if (obj == null) {
						return null;
					}
					
					if (type.isInstance(obj)) {
						return (T) obj;
					}
					
					Converter converter = context.get(Converter.class, DefaultConverter.INSTANCE);
					T ret = converter.convert(obj, type);
					if (ret == null) {
						throw new ConfigurationException("Cannot convert " + obj + " to " + type, TypedSupplierFactoryAttribute.this);
					}
					return ret;
				}
			};
			
		};		
		
		if (interpolate) {				
			SupplierFactory<String> ssf = ((SupplierFactory<InputStream>) delegate.getValue()).then(Util.TO_STRING);									
			return (interpolate ? ssf.then(Util.INTERPOLATE_TO_STRING) : ssf).then(converter);
		}
		
		return delegate.getValue().then(converter);		
	}

}
