package org.nasdanika.common;

import java.util.List;
import java.util.function.Function;

public interface PropertyDescriptor<T> extends ValueDescriptor<T>, NamedDescriptor {
	
	@Override
	default PropertyDescriptor<T> mapName(Function<String, String> mapper) {
		return new PropertyDescriptor<T>() {

			@Override
			public void set(T value) {
				PropertyDescriptor.this.set(value);
			}

			@Override
			public T get() {
				return PropertyDescriptor.this.get();
			}

			@Override
			public T getType() {
				return PropertyDescriptor.this.getType();
			}

			@Override
			public List<T> getChoices() {
				return PropertyDescriptor.this.getChoices();
			}

			@Override
			public boolean isRequired() {
				return PropertyDescriptor.this.isRequired();
			}

			@Override
			public String getIcon() {
				return PropertyDescriptor.this.getIcon();
			}

			@Override
			public String getLabel() {
				return PropertyDescriptor.this.getLabel();
			}

			@Override
			public String getDescription() {
				return PropertyDescriptor.this.getDescription();
			}

			@Override
			public String getName() {
				return mapper.apply(PropertyDescriptor.this.getName());
			}
			
			@Override
			public Diagnostic diagnose(ProgressMonitor progressMonitor) {
				return PropertyDescriptor.this.diagnose(progressMonitor);
			}
		};
	}

}
