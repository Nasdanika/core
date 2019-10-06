package org.nasdanika.common;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface NamedDescriptorSet extends DescriptorSet, NamedDescriptor {

	
	@Override
	default NamedDescriptorSet map(Function<Descriptor,Descriptor> mapper) {
		return new NamedDescriptorSet() {
			
			@Override
			public String getName() {
				return NamedDescriptorSet.this.getName();
			}
			
			@Override
			public String getLabel() {
				return NamedDescriptorSet.this.getLabel();
			}
			
			@Override
			public String getIcon() {
				return NamedDescriptorSet.this.getIcon();
			}
			
			@Override
			public String getDescription() {
				return NamedDescriptorSet.this.getDescription();
			}
			
			@Override
			public List<Descriptor> getDescriptors() {
				return NamedDescriptorSet.this.getDescriptors().stream().map(mapper).filter(Objects::nonNull).collect(Collectors.toList());
			}
		};
		
	}

	@Override
	default NamedDescriptorSet compose(DescriptorSet other) {
		// TODO immplement
		throw new UnsupportedOperationException();
	}
	
	@Override
	default NamedDescriptor mapName(Function<String, String> mapper) {
		return new NamedDescriptorSet() {
			
			@Override
			public String getName() {
				return mapper.apply(NamedDescriptorSet.this.getName());
			}
			
			@Override
			public String getLabel() {
				return NamedDescriptorSet.this.getLabel();
			}
			
			@Override
			public String getIcon() {
				return NamedDescriptorSet.this.getIcon();
			}
			
			@Override
			public String getDescription() {
				return NamedDescriptorSet.this.getDescription();
			}
			
			@Override
			public List<Descriptor> getDescriptors() {
				return NamedDescriptorSet.this.getDescriptors();
			}
		};
	}
	
}
