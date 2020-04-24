package org.nasdanika.emf.presentation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
//import org.eclipse.jdt.core.IJavaProject;
//import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.nasdanika.common.Util;

/**
 * Base class for generators from model elements.
 * @author Pavel Vlasov
 *
 */
public abstract class NasdanikaGenerateAction<T extends EObject> extends Action {
	
	protected T modelElement;					
	protected AdapterFactory adapterFactory;	
	protected ILabelProvider labelProvider;
	
	protected NasdanikaGenerateAction(String name, T modelElement, AdapterFactory adapterFactory) {
		super(name);
		this.modelElement = modelElement;
		this.adapterFactory = adapterFactory;
		if (adapterFactory != null) {
			labelProvider = new AdapterFactoryLabelProvider(adapterFactory);
		}
	}
	
	@Override
	public void run() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		Shell shell = workbench.getModalDialogShellProvider().getShell();
				
		Diagnostician diagnostician = new Diagnostician() {
			
			@Override
			public String getObjectLabel(EObject eObject) {
				String ret = getLabel(eObject);
				return Util.isBlank(ret) ? super.getObjectLabel(eObject) : ret;
			}
			
		}; 
		
		Diagnostic diagnostic = diagnostician.validate(modelElement);
		
		IStatus validationStatus = BasicDiagnostic.toIStatus(diagnostic);
		if (validationStatus.getSeverity() == IStatus.ERROR) {
			ErrorDialog dialog = new ErrorDialog(
					shell, 
					"Model diagnostic failed", 
					"Model contains errors, see details for more information", 
					validationStatus, 
					IStatus.OK	| IStatus.INFO | IStatus.WARNING | IStatus.ERROR) {
				
				
				@Override
				public void create() {
					super.create();
					showDetailsArea();
				}

			};			
			
			dialog.open();
			
			getLog().log(validationStatus);
			return;
		}
		
		try {							
//			URI resourceURI = modelElement.eResource().getURI();
//			URL baseURL = null;			
//			try {
//				baseURL = new URL(resourceURI.toString());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			
//			Map<String, Object> properties = new HashMap<>();
//			properties.put(Configuration.BASE_URL_PROPERTY, baseURL);
							
//			ClassLoader[] classLoader = { getClass().getClassLoader() }; 
//			
//			IFile modelFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(resourceURI.toPlatformString(true)));
//			if (modelFile.exists()) {
//				IProject project = modelFile.getProject();
//				IProjectNature javaNature = project.getNature(JavaCore.NATURE_ID);
//				if (javaNature instanceof IJavaProject) {
//					classLoader[0] = new JavaProjectClassLoader(classLoader[0], (IJavaProject) javaNature);
//				}					
//			}
			
			WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
				
				@Override
				protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {	
					try {
						NasdanikaGenerateAction.this.execute(monitor);
					} catch (CoreException | InvocationTargetException | InterruptedException | RuntimeException e) {
						throw e;
					} catch (Exception e) {
						throw new InvocationTargetException(e);
					} finally {
						monitor.done();
					}					
				}
				
			};

			new ProgressMonitorDialog(shell).run(true, true, operation);
		} catch (Exception exception) {
			while (exception.getCause() instanceof Exception && exception.getSuppressed().length == 0) {
				exception = (Exception) exception.getCause();
			}
            IStatus status = createStatus(exception.toString(), exception);
            ErrorDialog.openError(shell, "Generation error", exception.toString(), status);
			getLog().log(status);
			exception.printStackTrace();
		}
	}
	
	protected String getLabel(EObject eObject) {
		if (eObject != null && labelProvider != null) {
			String label = labelProvider.getText(eObject);
			if (!Util.isBlank(label)) {
				return label;
			}
		}
		return null;
	};

	private static IStatus createStatus(String msg, Throwable t) {
		if (t instanceof DiagnosticException) {
			return BasicDiagnostic.toIStatus((DiagnosticException) t);
		}
		
		if (t instanceof org.nasdanika.common.DiagnosticException) {
			return createMultiStatus(((org.nasdanika.common.DiagnosticException) t).getDiagnostic());
		}
		
		List<IStatus> childStatuses = new ArrayList<>();

		for (StackTraceElement stackTrace : t.getStackTrace()) {
			childStatuses.add(new Status(IStatus.ERROR, "org.nasdanika.codegen.editor", stackTrace.toString()));
		}

		if (t.getCause() != null) {
			childStatuses.add(createStatus("Caused by: " + t.getCause(), t.getCause()));
		}

		for (Throwable s : t.getSuppressed()) {
			childStatuses.add(createStatus("Supressed: " + s, s.getCause()));
		}

		MultiStatus ms = new MultiStatus("org.nasdanika.codegen.editor", IStatus.ERROR,	childStatuses.toArray(new Status[childStatuses.size()]), msg, t);

		return ms;
	}	
	
	public static MultiStatus createMultiStatus(org.nasdanika.common.Diagnostic diagnostic) {
		List<Status> childStatuses = new ArrayList<>();

		for (org.nasdanika.common.Diagnostic child : diagnostic.getChildren()) {
			childStatuses.add(createMultiStatus(child));
		}
		
		int status;
		switch (diagnostic.getStatus()) {
		case CANCEL:
			status = IStatus.CANCEL;
			break;
		case ERROR:
			status = IStatus.ERROR;
			break;
		case INFO:
			status = IStatus.INFO;
			break;
		case SUCCESS:
			status = IStatus.OK;
			break;
		case WARNING:
			status = IStatus.WARNING;
			break;
		default:
			throw new IllegalArgumentException("Unsupported diagnostic status: " + diagnostic.getStatus());
		}

		return new MultiStatus("org.nasdanika.vinci.editor", status, childStatuses.toArray(new Status[childStatuses.size()]), diagnostic.getMessage(), null);
	}	
		
	protected abstract void execute(IProgressMonitor monitor) throws Exception;
	
	protected ILog getLog() {
		return NasdanikaEditorPlugin.getPlugin().getLog();
	}

}
