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
public interface CompositeDescriptor extends Descriptor, Composeable<CompositeDescriptor> {
	
	List<Descriptor> getChildren();
	
	@Override
	default Diagnostic diagnose(ProgressMonitor progressMonitor) {
		BasicDiagnostic ret = new BasicDiagnostic(Status.INFO, "Diagnostic of "+this, this);
		progressMonitor.setWorkRemaining(getChildren().size());
		for (Descriptor child: getChildren()) {
			ret.add(child.diagnose(progressMonitor.split("Diagnosing " + child, 1, child)));
		}
		return ret;
	}
	
	default CompositeDescriptor mapNames(Function<String,String> nameMapper) {		
		return map(descriptor -> {
			if (descriptor instanceof NamedDescriptor) {
				return ((NamedDescriptor) descriptor).mapName(nameMapper);
			}
			return descriptor;
		});
		
	}
	
	default CompositeDescriptor map(Function<Descriptor,Descriptor> mapper) {

		return new CompositeDescriptor() {
			
			@Override
			public String getLabel() {
				return CompositeDescriptor.this.getLabel();
			}
			
			@Override
			public String getIcon() {
				return CompositeDescriptor.this.getIcon();
			}
			
			@Override
			public String getDescription() {
				return CompositeDescriptor.this.getDescription();
			}
			
			@Override
			public List<Descriptor> getChildren() {
				return CompositeDescriptor.this.getChildren().stream().map(mapper).filter(Objects::nonNull).collect(Collectors.toList());
			}
		};
		
		
	}
	
	default CompositeDescriptor filter(Predicate<Descriptor> filter) {
		return map(d -> filter.test(d) ? d : null);
	}
	
	@Override
	default CompositeDescriptor compose(CompositeDescriptor other) {
		// TODO - Names, services, diagnose incompatible named descriptors types - property and composite at the same name. Or maybe throw
		// and exception right away - illegal argument
		// set value to both - also diagnose type compatibility, e.g. String and Map (or rely on conversion?).
		throw new UnsupportedOperationException("TODO - implement");
	}
	
}
