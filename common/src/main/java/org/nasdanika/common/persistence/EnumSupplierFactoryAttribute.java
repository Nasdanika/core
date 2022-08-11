package org.nasdanika.common.persistence;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;

public class EnumSupplierFactoryAttribute<T extends Enum<T>> extends AbstractFeatureDelegate<SupplierFactoryFeature<String>> implements SupplierFactoryFeature<T> {

	private Class<T> type;
	private T defaultValue;

	public EnumSupplierFactoryAttribute(SupplierFactoryFeature<String> delegate, Class<T> type, T defaultValue) {
		super(delegate);
		this.type = type;
		this.defaultValue = defaultValue;
	}

	@Override
	public SupplierFactory<T> getValue() {
		return delegate.getValue().then(this::createFunction);
	}
	
	private Function<String,T> createFunction(Context context) {
		return new Function<String, T>() {

			@Override
			public double size() {
				return 0;
			}

			@Override
			public String name() {
				return "String to " + type.getName();
			}

			@Override
			public T execute(String name, ProgressMonitor progressMonitor) {
				if (isLoaded()) {
					try {
						return Enum.valueOf(type, name);
					} catch (IllegalArgumentException e) {
						throw new ConfigurationException(e.getMessage(), e, getMarkers());
					}
				}
				return defaultValue;
			}
			
		};		
	}

}
