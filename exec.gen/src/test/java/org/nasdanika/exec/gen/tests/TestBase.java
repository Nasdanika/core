package org.nasdanika.exec.gen.tests;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.resources.BinaryEntityContainer;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.gen.ExecGenYamlLoadingExecutionParticipant;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;

/**
 * Common methods for testing
 * @author Pavel
 *
 */
public class TestBase {
	
	public static void dumpToYaml(EObject eObject) {
		DumperOptions dumperOptions = new DumperOptions();
		dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK); dumperOptions.setIndent(4); 
		new Yaml(dumperOptions).dump(dump(eObject), new OutputStreamWriter(System.out));
	}
	
	/**
	 * Dumps {@link EObject} to {@link Map} for to further dump to YAML. 
	 * Outputs class, path, URI, and name.
	 * @param eObject
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,Object> dump(EObject eObject) {
		Map<String,Object> ret = new LinkedHashMap<>(); 
		ret.put("class", eObject.eClass().getName());
		for (EReference ref: eObject.eClass().getEAllReferences()) {
			if (ref. isContainment()) { 
				if (ref.isMany()) {
					Collection<Map<String,Object>> elements = new ArrayList<>(); 
					for (EObject el: ((Collection<EObject>) eObject.eGet(ref))) { 
						if (el != null) {
							elements.add(dump(el));
						}
					}
					if (!elements.isEmpty()) {
						ret.put(ref.getName(), elements);
					}
				} else {
					EObject val = (EObject) eObject.eGet(ref); 
					if (val != null) {
						ret.put(ref.getName(), dump(val));
					}
				}
			}
		}
		return ret;
	}
	
	// TODO - supplier, consumer, command tests - chaining with then

	protected EObject loadObject(
			String resource, 
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
			Context context,
			ProgressMonitor progressMonitor) throws Exception {

		class ObjectSupplier extends ExecGenYamlLoadingExecutionParticipant implements Supplier<EObject> {

			public ObjectSupplier(Context context) {
				super(context);
			}

			@Override
			protected Collection<URI> getResources() {
				Class<? extends TestBase> clazz = TestBase.this.getClass();
				URL resourceURL = clazz.getResource(resource);
				if (resourceURL == null) {
					throw new IllegalArgumentException("Classloader resource not found: " + resource + " by " + clazz); 
				}
				return Collections.singleton(URI.createURI(resourceURL.toString()));
			}

			@Override
			public EObject execute(ProgressMonitor progressMonitor) {
				assertEquals(1, roots.size());
				return roots.iterator().next();
			}
			
		};
		
		// Diagnosing loaded resources. 
		try {
			return Util.call(new ObjectSupplier(context), progressMonitor, diagnosticConsumer);
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}		
	}
	
	// Loading InputStream	
	protected InputStream loadInputStream(
			EObject eObject, 
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
			Context context,
			ProgressMonitor progressMonitor) throws Exception {
		
		// Diagnosing loaded resources. 
		try {
			SupplierFactory<InputStream> supplierFactory = Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(eObject, InputStream.class), "Cannot adapt to SupplierFactory");
			return Util.call(supplierFactory.create(context), progressMonitor, diagnosticConsumer);
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}		
	}
	
	protected InputStream loadInputStream(
			String resource, 
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
			Context modelContext,
			Context generationContext,
			ProgressMonitor progressMonitor) throws Exception {
		EObject eObject = Objects.requireNonNull(loadObject(resource, diagnosticConsumer, modelContext, progressMonitor), "Loaded null from " + resource);
		return loadInputStream(eObject, diagnosticConsumer, generationContext, progressMonitor);
	}
	
	protected InputStream loadInputStream(
			String resource, 
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
			Context modelContext,
			Context generationContext) throws Exception {
		try (ProgressMonitor progressMonitor = new PrintStreamProgressMonitor()) {
			return loadInputStream(resource, diagnosticConsumer, modelContext, generationContext, progressMonitor);
		}
	}
	
	/**
	 * Loads input stream with empty context and {@link PrintStreamProgressMonitor} outputting to System.out
	 * @param resource
	 * @param diagnosticConsumer
	 * @return
	 * @throws Exception
	 */
	protected InputStream loadInputStream(String resource, Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer) throws Exception {
		try (ProgressMonitor progressMonitor = new PrintStreamProgressMonitor()) {
			return loadInputStream(resource, diagnosticConsumer, Context.EMPTY_CONTEXT, Context.EMPTY_CONTEXT, progressMonitor);
		}
	}	
	
	// Generating container
	protected Diagnostic generate(
			EObject eObject, 
			BinaryEntityContainer container,
			Context context,
			ProgressMonitor progressMonitor) throws Exception {
		
		// Diagnosing loaded resources. 
		try {
			ConsumerFactory<BinaryEntityContainer> consumerFactory = Objects.requireNonNull(EObjectAdaptable.adaptToConsumerFactory(eObject, BinaryEntityContainer.class), "Cannot adapt to ConsumerFactory");
			return Util.call(consumerFactory.create(context), container, progressMonitor);
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}		
	}
	
	protected Diagnostic generate(
			String resource, 
			BinaryEntityContainer container,
			Context modelContext,
			Context generationContext,			
			ProgressMonitor progressMonitor,
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer) throws Exception {
		EObject eObject = Objects.requireNonNull(loadObject(resource, diagnosticConsumer, modelContext, progressMonitor), "Loaded null from " + resource);
		return generate(eObject, container, generationContext, progressMonitor);
	}
	
	protected Diagnostic generate(
			String resource, 
			BinaryEntityContainer container,
			Context modelContext,
			Context generationContext,
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer) throws Exception {
		try (ProgressMonitor progressMonitor = new PrintStreamProgressMonitor()) {
			return generate(resource, container, modelContext, generationContext, progressMonitor, diagnosticConsumer);
		}
	}
	
	/**
	 * Generates with empty context and {@link PrintStreamProgressMonitor} outputting to System.out
	 * @param resource
	 * @param container 
	 * @param diagnosticConsumer Consumer of model diagnostic. 
	 * @return Generation diagnostic
	 * @throws Exception
	 */
	protected Diagnostic generate(
			String resource,
			BinaryEntityContainer container,
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer) throws Exception {
		try (ProgressMonitor progressMonitor = new PrintStreamProgressMonitor()) {
			return generate(resource, container, Context.EMPTY_CONTEXT, Context.EMPTY_CONTEXT, progressMonitor, diagnosticConsumer);
		}
	}	

	// Loading Object	
	protected Object load(
			EObject eObject, 
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
			Context context,
			ProgressMonitor progressMonitor) throws Exception {
		
		// Diagnosing loaded resources. 
		try {
			SupplierFactory<Object> supplierFactory = Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(eObject, Object.class), "Cannot adapt to SupplierFactory");
			return Util.call(supplierFactory.create(context), progressMonitor, diagnosticConsumer);
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}		
	}
	
	protected Object load(
			String resource, 
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
			Context modelContext,
			Context generationContext,
			ProgressMonitor progressMonitor) throws Exception {
		EObject eObject = Objects.requireNonNull(loadObject(resource, diagnosticConsumer, modelContext, progressMonitor), "Loaded null from " + resource);
		return load(eObject, diagnosticConsumer, generationContext, progressMonitor);
	}
	
	protected Object load(
			String resource, 
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
			Context modelContext,
			Context generationContext) throws Exception {
		try (ProgressMonitor progressMonitor = new PrintStreamProgressMonitor()) {
			return load(resource, diagnosticConsumer, modelContext, generationContext, progressMonitor);
		}
	}
	
	/**
	 * Loads input stream with empty context and {@link PrintStreamProgressMonitor} outputting to System.out
	 * @param resource
	 * @param diagnosticConsumer
	 * @return
	 * @throws Exception
	 */
	protected Object load(String resource, Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer) throws Exception {
		try (ProgressMonitor progressMonitor = new PrintStreamProgressMonitor()) {
			return load(resource, diagnosticConsumer, Context.EMPTY_CONTEXT, Context.EMPTY_CONTEXT, progressMonitor);
		}
	}	
	
	// Consuming
	protected Diagnostic consume(
			EObject eObject, 
			Object arg,
			Context context,
			ProgressMonitor progressMonitor) throws Exception {
		
		// Diagnosing loaded resources. 
		try {
			ConsumerFactory<Object> consumerFactory = Objects.requireNonNull(EObjectAdaptable.adaptToConsumerFactory(eObject, Object.class), "Cannot adapt to ConsumerFactory");
			return Util.call(consumerFactory.create(context), arg, progressMonitor);
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}		
	}
	
	protected Diagnostic consume(
			String resource, 
			Object arg,
			Context modelContext,
			Context generationContext,			
			ProgressMonitor progressMonitor,
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer) throws Exception {
		EObject eObject = Objects.requireNonNull(loadObject(resource, diagnosticConsumer, modelContext, progressMonitor), "Loaded null from " + resource);
		return consume(eObject, arg, generationContext, progressMonitor);
	}
	
	protected Diagnostic consume(
			String resource, 
			Object arg,
			Context modelContext,
			Context generationContext,
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer) throws Exception {
		try (ProgressMonitor progressMonitor = new PrintStreamProgressMonitor()) {
			return consume(resource, arg, modelContext, generationContext, progressMonitor, diagnosticConsumer);
		}
	}
	
	/**
	 * Generates with empty context and {@link PrintStreamProgressMonitor} outputting to System.out
	 * @param resource
	 * @param container 
	 * @param diagnosticConsumer Consumer of model diagnostic. 
	 * @return Generation diagnostic
	 * @throws Exception
	 */
	protected Diagnostic consume(
			String resource,
			Object arg,
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer) throws Exception {
		try (ProgressMonitor progressMonitor = new PrintStreamProgressMonitor()) {
			return consume(resource, arg, Context.EMPTY_CONTEXT, Context.EMPTY_CONTEXT, progressMonitor, diagnosticConsumer);
		}
	}	
	

}
