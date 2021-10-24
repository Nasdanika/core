package org.nasdanika.emf;

import java.util.Collection;
import java.util.Collections;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EStructuralFeature;

public class DiagnosticProviderAdapter extends AdapterImpl implements DiagnosticProvider {
	
	private Function<Notifier, Collection<Diagnostic>> targetDiagnosticSupplier; 
	private BiFunction<Notifier, EStructuralFeature, Collection<Diagnostic>> featureDiagnosticSupplier;	

	public DiagnosticProviderAdapter(
			Notifier target, 
			Function<Notifier, Collection<Diagnostic>> targetDiagnosticSupplier, 
			BiFunction<Notifier, EStructuralFeature, Collection<Diagnostic>> featureDiagnosticSupplier) {
		setTarget(target);
		this.targetDiagnosticSupplier = targetDiagnosticSupplier;
		this.featureDiagnosticSupplier = featureDiagnosticSupplier;
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == DiagnosticProvider.class;
	}

	@Override
	public Collection<Diagnostic> getDiagnostic() {
		return targetDiagnosticSupplier == null ? Collections.emptyList() : targetDiagnosticSupplier.apply(getTarget());
	}

	@Override
	public Collection<Diagnostic> getFeatureDiagnostic(EStructuralFeature feature) {
		return featureDiagnosticSupplier == null ? Collections.emptyList() : featureDiagnosticSupplier.apply(getTarget(), feature);
	}

}
