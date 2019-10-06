package org.nasdanika.common;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface NamedCompositeDescriptor extends CompositeDescriptor, NamedDescriptor {

	
	@Override
	default NamedCompositeDescriptor map(Function<Descriptor,Descriptor> mapper) {
		return new NamedCompositeDescriptor() {
			
			@Override
			public String getName() {
				return NamedCompositeDescriptor.this.getName();
			}
			
			@Override
			public String getLabel() {
				return NamedCompositeDescriptor.this.getLabel();
			}
			
			@Override
			public String getIcon() {
				return NamedCompositeDescriptor.this.getIcon();
			}
			
			@Override
			public String getDescription() {
				return NamedCompositeDescriptor.this.getDescription();
			}
			
			@Override
			public List<Descriptor> getChildren() {
				return NamedCompositeDescriptor.this.getChildren().stream().map(mapper).filter(Objects::nonNull).collect(Collectors.toList());
			}
		};
		
	}

	@Override
	default NamedCompositeDescriptor compose(CompositeDescriptor other) {
		// TODO immplement
		throw new UnsupportedOperationException();
	}
	
	@Override
	default NamedDescriptor mapName(Function<String, String> mapper) {
		return new NamedCompositeDescriptor() {
			
			@Override
			public String getName() {
				return mapper.apply(NamedCompositeDescriptor.this.getName());
			}
			
			@Override
			public String getLabel() {
				return NamedCompositeDescriptor.this.getLabel();
			}
			
			@Override
			public String getIcon() {
				return NamedCompositeDescriptor.this.getIcon();
			}
			
			@Override
			public String getDescription() {
				return NamedCompositeDescriptor.this.getDescription();
			}
			
			@Override
			public List<Descriptor> getChildren() {
				return NamedCompositeDescriptor.this.getChildren();
			}
		};
	}
	
}
