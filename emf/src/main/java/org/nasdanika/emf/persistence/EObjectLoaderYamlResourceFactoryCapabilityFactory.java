package org.nasdanika.emf.persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.capability.emf.ResourceFactoryCapabilityFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.ncore.util.NcoreUtil;
import org.nasdanika.persistence.ObjectLoader;

/**
 * Loads objects using {@link EObjectLoader}
 */
public class EObjectLoaderYamlResourceFactoryCapabilityFactory extends ResourceFactoryCapabilityFactory {
		
	public class EObjectLoaderYamlResource extends ResourceImpl {
		
		private List<org.nasdanika.common.Diagnostic> diagnostic = Collections.synchronizedList(new ArrayList<>());
		
		public List<org.nasdanika.common.Diagnostic> getDiagnostic() {
			return diagnostic;
		}
		
		private ResourceSet rSet;

		public EObjectLoaderYamlResource(URI uri, ResourceSet resourceSet) {
			super(uri);
			this.rSet = resourceSet;
		}
		
		@SuppressWarnings("unchecked")
		protected void doLoad(java.io.InputStream inputStream, java.util.Map<?,?> options) throws java.io.IOException {
			EObjectLoader eObjectLoader = new EObjectLoader((ObjectLoader) null) {
				
				@Override
				public ResolutionResult resolveEClass(String type) {
					EClass eClass = (EClass) NcoreUtil.getType(type, rSet, msg -> new NasdanikaException(msg + " in " + getURI()));
					return new ResolutionResult(eClass, null);
				}
				
				@Override
				public ResourceSet getResourceSet() {
					return rSet;
				}
				
			};
			
			GitMarkerFactory markerFactory = new GitMarkerFactory();
			eObjectLoader.setMarkerFactory(markerFactory);			
			
			ProgressMonitor progressMonitor = createProgressMonitor(this);
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer = createDiagnosticConsumer(this, progressMonitor);
			BiConsumer<Object, BiConsumer<Object, ProgressMonitor>> resolver = (ref, sink) -> {
				if (ref instanceof URI) {
					EObject result = resourceSet.getEObject((URI) ref, true);
					sink.accept(result, progressMonitor);
				}
			};
			SupplierFactory<EObject> contentsSupplierFactory = (SupplierFactory<EObject>) eObjectLoader.loadYaml(inputStream, getURI(), resolver, progressMonitor);				
			EObject yamlContents = contentsSupplierFactory.create(createContext(this, progressMonitor)).call(progressMonitor, diagnosticConsumer);
			getContents().add(yamlContents);
		}
		
	}	

	@Override
	protected Factory getResourceFactory(ResourceSet resourceSet) {
		return new Factory() {

			@Override
			public Resource createResource(URI uri) {
				return new EObjectLoaderYamlResource(uri, resourceSet);
			}
			
		};
	}
	
	@Override
	protected String getExtension() {
		return "yml";
	}
	
	protected ProgressMonitor createProgressMonitor(EObjectLoaderYamlResource resource) {
		return new PrintStreamProgressMonitor();
	}
	
	protected Consumer<org.nasdanika.common.Diagnostic> createDiagnosticConsumer(EObjectLoaderYamlResource resource, ProgressMonitor progressMonitor) {
		return resource.diagnostic::add;
	}
	
	protected Context createContext(EObjectLoaderYamlResource resource, ProgressMonitor progressMonitor) {
		return Context.EMPTY_CONTEXT;
	}

}
