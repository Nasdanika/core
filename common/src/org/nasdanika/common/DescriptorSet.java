package org.nasdanika.common;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Grouping of descriptors.
 * @author Pavel
 *
 */
public interface DescriptorSet extends Descriptor, Composeable<DescriptorSet> {
	
	List<Descriptor> getDescriptors();
	
	@Override
	default Diagnostic diagnose(ProgressMonitor progressMonitor) {
		BasicDiagnostic ret = new BasicDiagnostic(Status.INFO, "Diagnostic of "+this, this);
		progressMonitor.setWorkRemaining(getDescriptors().size());
		for (Descriptor child: getDescriptors()) {
			ret.add(child.diagnose(progressMonitor.split("Diagnosing " + child, 1, child)));
		}
		return ret;
	}
	
	default DescriptorSet mapNames(Function<String,String> nameMapper) {		
		return map(descriptor -> {
			if (descriptor instanceof NamedDescriptor) {
				return ((NamedDescriptor) descriptor).mapName(nameMapper);
			}
			return descriptor;
		});
		
	}
	
	default DescriptorSet map(Function<Descriptor,Descriptor> mapper) {

		return new DescriptorSet() {
			
			@Override
			public String getLabel() {
				return DescriptorSet.this.getLabel();
			}
			
			@Override
			public String getIcon() {
				return DescriptorSet.this.getIcon();
			}
			
			@Override
			public String getDescription() {
				return DescriptorSet.this.getDescription();
			}
			
			@Override
			public List<Descriptor> getDescriptors() {
				return DescriptorSet.this.getDescriptors().stream().map(mapper).filter(Objects::nonNull).collect(Collectors.toList());
			}
		};
		
		
	}
	
	default DescriptorSet filter(Predicate<Descriptor> filter) {
		return map(d -> filter.test(d) ? d : null);
	}
	
	@Override
	default DescriptorSet compose(DescriptorSet other) {
		// TODO - Names, services, diagnose incompatible named descriptors types - property and composite at the same name. Or maybe throw
		// and exception right away - illegal argument
		// set value to both - also diagnose type compatibility, e.g. String and Map (or rely on conversion?).
		throw new UnsupportedOperationException("TODO - implement");
	}
	
}
