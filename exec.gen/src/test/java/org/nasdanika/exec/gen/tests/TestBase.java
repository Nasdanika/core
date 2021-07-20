package org.nasdanika.exec.gen.tests;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.io.OutputStreamWriter;
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
import org.nasdanika.common.Context;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
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
				return Collections.singleton(URI.createURI(TestBase.this.getClass().getResource(resource).toString()));
			}

			@Override
			public EObject execute(ProgressMonitor progressMonitor) throws Exception {
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
	
	protected InputStream loadInputStream(
			EObject eObject, 
			Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer,
			Context context,
			ProgressMonitor progressMonitor) throws Exception {
		
		// Diagnosing loaded resources. 
		try {
			@SuppressWarnings("unchecked")
			SupplierFactory<InputStream> supplierFactory = Objects.requireNonNull(EObjectAdaptable.adaptTo(eObject, SupplierFactory.class), "Cannot adapt to SupplierFactory");
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
			Context context,
			ProgressMonitor progressMonitor) throws Exception {
		EObject eObject = Objects.requireNonNull(loadObject(resource, diagnosticConsumer, context, progressMonitor), "Loaded null from " + resource);
		return loadInputStream(eObject, diagnosticConsumer, context, progressMonitor);
	}
	
	
	protected InputStream loadInputStream(String resource, Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer) throws Exception {
		try (ProgressMonitor progressMonitor = new PrintStreamProgressMonitor()) {
			return loadInputStream(resource, diagnosticConsumer, Context.EMPTY_CONTEXT, progressMonitor);
		}
	}
	
	
//	protected InputStream loadInputStream(String resource, Consumer<EObject> consumer, Consumer<org.nasdanika.common.Diagnostic> diagnosticConsumer) throws Exception {	


}
