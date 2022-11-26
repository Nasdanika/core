package org.nasdanika.emf.persistence;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.DrawioEObjectFactory;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.MarkerImpl;
import org.nasdanika.persistence.ObjectLoader;
import org.yaml.snakeyaml.error.MarkedYAMLException;

public abstract class YamlLoadingDrawioEObjectFactory<T extends EObject> extends DrawioEObjectFactory<T> {
	
	protected abstract ObjectLoader getLoader();
	
	protected Context getContext() {
		return Context.EMPTY_CONTEXT;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected T load(String spec, URI specBase, ProgressMonitor progressMonitor) {
		try {
			Object data = getLoader().loadYaml(spec, specBase, progressMonitor);
			if (data instanceof SupplierFactory) {
				return (T) Util.call(((SupplierFactory<EObject>) data).create(getContext()), progressMonitor, this::onDiagnostic);
			} 
			
			if (data instanceof EObject) {
				return (T) data;
			}
			
			throw new NasdanikaException("Not an instance of EObject or SupplierFactory: " + data);
		} catch (MarkedYAMLException e) {
			throw new ConfigurationException(e.getMessage(), e, new MarkerImpl(String.valueOf(specBase), e.getProblemMark()));
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new NasdanikaException(e);
		}
	}
	
	/**
	 * Called by the supplier factory.
	 * @param diagnostic
	 */
	protected void onDiagnostic(org.nasdanika.common.Diagnostic diagnostic) {
		if (diagnostic.getStatus() == Status.FAIL || diagnostic.getStatus() == Status.ERROR) {
			System.err.println("***********************");
			System.err.println("*      Diagnostic     *");
			System.err.println("***********************");
			diagnostic.dump(System.err, 4, Status.FAIL, Status.ERROR);
		}
		if (diagnostic.getStatus() != Status.SUCCESS) {
			throw new DiagnosticException(diagnostic);
		};
	}

}
