package org.nasdanika.emf;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Work;

/**
 * This class loads a model, validates it and then returns work for executing generation. 
 * @author Pavel
 *
 */
public class ValidatingModelWorkFactory<T> extends ModelWorkFactory<T> {

	public ValidatingModelWorkFactory(ResourceSet resourceSet, URI modelUri) throws Exception {
		super(resourceSet, modelUri);
	}

	public ValidatingModelWorkFactory(String platformPluginUri, EPackage... ePackages) throws Exception {
		super(platformPluginUri, ePackages);
	}

	public ValidatingModelWorkFactory(URI modelUri, EPackage... ePackages) throws Exception {
		super(modelUri, ePackages);
	}
	
	public ValidatingModelWorkFactory(ResourceSet resourceSet, String platformPluginUri) throws Exception {
		super(resourceSet, platformPluginUri);
	}
	
	@Override
	public Work<T> create(Context context) throws Exception {
		Diagnostician diagnostician = new Diagnostician() {
			
			public Map<Object,Object> createDefaultContext() {
				Map<Object, Object> ctx = super.createDefaultContext();
				ctx.put(Context.class, context);
				return ctx;
			};
			
		};				
		Diagnostic validationResult = diagnostician.validate((EObject) workFactory);
		if (validationResult.getSeverity() == Diagnostic.ERROR) {
			throw new DiagnosticException(validationResult);
		}
		return super.create(context);
	}
	
//	static void diagnosticToProgress(ProgressMonitor progressMonitor, long worked, Diagnostic diagnostic) {
//		Status status;
//		switch (diagnostic.getSeverity()) {
//		case Diagnostic.CANCEL:
//			status = Status.CANCEL;
//			break;
//		case Diagnostic.ERROR:
//			status = Status.ERROR;
//			break;
//		case Diagnostic.WARNING:
//			status = Status.WARNING;
//			break;
//		case Diagnostic.INFO:
//			status = Status.INFO;
//			break;
//		case Diagnostic.OK:
//		default:
//			status = Status.SUCCESS;
//			break;
//		}
//		progressMonitor.worked(status, worked, diagnostic.getMessage());
//		List<Diagnostic> children = diagnostic.getChildren();
//		if (children != null) {
//			for (Diagnostic child: children) {
//				try (ProgressMonitor childMonitor = progressMonitor.split(child.getSource(), 0, child.getData(), child.getException())) {
//					diagnosticToProgress(childMonitor, worked, child);
//				}
//			}
//		}
//	}	

}
