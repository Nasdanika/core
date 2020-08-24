package org.nasdanika.core.tests.exec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

import org.junit.Test;
import org.nasdanika.common.Adaptable;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.resources.BinaryEntity;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.BinaryResource;
import org.nasdanika.common.resources.EphemeralBinaryEntityContainer;
import org.nasdanika.common.resources.FileSystemContainer;
import org.nasdanika.exec.Configurator;
import org.nasdanika.exec.Iterator;
import org.nasdanika.exec.Loader;
import org.nasdanika.exec.Mapper;
import org.nasdanika.exec.Reference;
import org.nasdanika.exec.content.Base64;
import org.nasdanika.exec.content.Form;
import org.nasdanika.exec.content.FreeMarker;
import org.nasdanika.exec.content.HttpCall;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Json;
import org.nasdanika.exec.content.Mustache;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.exec.resources.Container;
import org.yaml.snakeyaml.Yaml;


public class TestExec {
	
	/**
	 * Tests retrieval of different entries from a context wrapping a map.
	 */
	@Test
	public void testIterator() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object iterator = loader.loadYaml(TestExec.class.getResource("iterator-spec.yml"), monitor);
		assertEquals(Iterator.class, iterator.getClass());
		
		Map<String, Object> yaml = new Yaml().load(TestExec.class.getResourceAsStream("iterator-config.yml"));
		Context context = Context.wrap(yaml::get);
		
		SupplierFactory<InputStream> supplierFactory = Loader.asSupplierFactory(iterator);
		Supplier<InputStream> supplier = supplierFactory.create(context);
		assertEquals(" * uno *  * dos *  * tres * ", Util.toString(context, supplier.execute(monitor)));
	}
	
	/**
	 * Tests injection of configuration.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testConfigurator() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object configurator = loader.loadYaml(TestExec.class.getResource("configurator-spec.yml"), monitor);
		assertEquals(Configurator.class, configurator.getClass());
		
		Map<String, Object> yaml = new Yaml().load(TestExec.class.getResourceAsStream("iterator-config.yml"));
		Context context = Context.wrap(yaml::get);
		
		SupplierFactory<InputStream> sf = ((Adaptable) configurator).adaptTo(SupplierFactory.class);
		Supplier<InputStream> s = sf.create(context);
		assertEquals(" * 123 -- v11 * ", Util.toString(context, s.execute(monitor)));
	}
		
	/**
	 * Tests injection of configuration.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testMapper() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object mapper = loader.loadYaml(TestExec.class.getResource("mapper-spec.yml"), monitor);
		assertEquals(Mapper.class, mapper.getClass());
		
		Map<String, Object> yaml = new Yaml().load(TestExec.class.getResourceAsStream("iterator-config.yml"));
		Context context = Context.wrap(yaml::get);
		
		SupplierFactory<InputStream> sf = ((Adaptable) mapper).adaptTo(SupplierFactory.class);
		Supplier<InputStream> s = sf.create(context);
		assertEquals(" * 123_v11 -- ${a/a1/a11} * ", Util.toString(context, s.execute(monitor)));
	}
		
	/**
	 * Tests injection of configuration.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testMapperResource() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object mapper = loader.loadYaml(TestExec.class.getResource("mapper-resource-spec.yml"), monitor);
		assertEquals(Mapper.class, mapper.getClass());
		
		Map<String, Object> yaml = new Yaml().load(TestExec.class.getResourceAsStream("iterator-config.yml"));
		Context context = Context.wrap(yaml::get);
		
		SupplierFactory<InputStream> sf = ((Adaptable) mapper).adaptTo(SupplierFactory.class);
		Supplier<InputStream> s = sf.create(context);
		assertEquals(" * 123_v11 -- Hello! * ", Util.toString(context, s.execute(monitor)));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testMapperReference() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object reference = loader.loadYaml(TestExec.class.getResource("mapper-reference-spec.yml"), monitor);
		assertEquals(Reference.class, reference.getClass());
		
		Map<String, Object> yaml = new Yaml().load(TestExec.class.getResourceAsStream("iterator-config.yml"));
		Context context = Context.wrap(yaml::get);
		
		SupplierFactory<InputStream> sf = ((Adaptable) reference).adaptTo(SupplierFactory.class);
		Supplier<InputStream> s = sf.create(context);
		assertEquals(" * 123_v11 -- ${a/a1/a11} * ", Util.toString(context, s.execute(monitor)));
	}
	
	@Test
	public void testResource() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object resource = loader.loadYaml(TestExec.class.getResource("resource-spec.yml"), monitor);
		assertEquals(Resource.class, resource.getClass());
		
		Context context = Context.EMPTY_CONTEXT;		
		InputStream result = callSupplier(context, monitor, resource);
		assertEquals("Hello, ${name}!", Util.toString(context, result));
	}

	/**
	 * Executes full supplier lifecycle - diagnose, execute, commit/rollback, close.
	 * @param context
	 * @param monitor
	 * @param component
	 * @return
	 * @throws Exception
	 */
	static InputStream callSupplier(Context context, ProgressMonitor monitor, Object component) throws Exception {
		try (Supplier<InputStream> supplier = Loader.asSupplierFactory(component).create(context); ProgressMonitor progressMonitor = monitor.setWorkRemaining(3).split("Calling component", 3)) {
			Diagnostic diagnostic = supplier.splitAndDiagnose(progressMonitor);
			if (diagnostic.getStatus() == Status.ERROR) {
				diagnostic.dump(System.err, 4);
				fail("Diagnostic failed: " + diagnostic.getMessage());
			}
			
			try {
				InputStream result = supplier.splitAndExecute(progressMonitor);
				supplier.splitAndCommit(progressMonitor);
				return result;
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof DiagnosticException) {
					((DiagnosticException) e).getDiagnostic().dump(System.err, 4);
				}
				if (supplier.splitAndRollback(progressMonitor)) {
					fail("Exception " + e + ", rollback successful");
				} else {
					fail("Exception " + e + ", rollback failed");						
				}
				throw new NasdanikaException("Never get here");
			}
		}
	}

	/**
	 * Executes full consumer lifecycle - diagnose, execute, commit/rollback, close.
	 * @param context
	 * @param monitor
	 * @param component
	 * @return
	 * @throws Exception
	 */
	static void callConsumer(Context context, ProgressMonitor monitor, Object component, BinaryEntityContainer container) throws Exception {
		try (Consumer<BinaryEntityContainer> consumer = Loader.asConsumerFactory(component).create(context); ProgressMonitor progressMonitor = monitor.setWorkRemaining(3).split("Calling component", 3)) {
			Diagnostic diagnostic = consumer.splitAndDiagnose(progressMonitor);
			if (diagnostic.getStatus() == Status.ERROR) {
				diagnostic.dump(System.err, 4);
				fail("Diagnostic failed: " + diagnostic.getMessage());
			}
			
			try {
				consumer.splitAndExecute(container, progressMonitor);
				consumer.splitAndCommit(progressMonitor);
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof DiagnosticException) {
					((DiagnosticException) e).getDiagnostic().dump(System.err, 4);
				}
				if (consumer.splitAndRollback(progressMonitor)) {
					fail("Exception " + e + ", rollback successful");
				} else {
					fail("Exception " + e + ", rollback failed");						
				}
			}
		}
	}
	
	@Test
	public void testInterpolator() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object interpolator = loader.loadYaml(TestExec.class.getResource("interpolator-spec.yml"), monitor);
		assertEquals(Interpolator.class, interpolator.getClass());
		
		Context context = Context.singleton("name", "World");		
		
		String result = Util.toString(context, callSupplier(context, monitor, interpolator));
		assertEquals("Hello, World!", result);
	}
	
	@Test
	public void testMustache() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object mustache = loader.loadYaml(TestExec.class.getResource("mustache-spec.yml"), monitor);
		assertEquals(Mustache.class, mustache.getClass());
		
		Context context = Context.singleton("name", "World");		
		
		Supplier<InputStream> supplier = Loader.asSupplierFactory(mustache).create(context);
		InputStream result = supplier.execute(monitor);
		assertEquals("Hello, World!", Util.toString(context, result));
	}
	
	@Test
	public void testMustacheResource() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object mustache = loader.loadYaml(TestExec.class.getResource("mustache-resource-spec.yml"), monitor);
		assertEquals(Mustache.class, mustache.getClass());
		
		Context context = Context.singleton("name", "World");		
		
		InputStream result = callSupplier(context, monitor, mustache);
		assertEquals("Hello World!", Util.toString(context, result));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testFreeMarker() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object freeMarker = loader.loadYaml(TestExec.class.getResource("free-marker-spec.yml"), monitor);
		assertEquals(FreeMarker.class, freeMarker.getClass());
		
		Context context = Context.singleton("name", "World");		
		
		Supplier<InputStream> s = ((SupplierFactory<InputStream>) freeMarker).create(context);
		assertEquals("Hello, World!", Util.toString(context, s.execute(monitor)));
	}
	
	@Test
	public void testHttpCall() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object httpCall = loader.loadYaml(TestExec.class.getResource("http-call-spec.yml"), monitor);
		assertEquals(HttpCall.class, httpCall.getClass());
		
		Context context = Context.singleton("nasdanika", "https://nasdanika.org");		
		
		InputStream response = callSupplier(context, monitor, httpCall);
		assertEquals("Hello World!", Util.toString(context, response));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testResources() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object container = loader.loadYaml(TestExec.class.getResource("resources-spec.yml"), monitor);
		assertEquals(Container.class, container.getClass());
		
		Context context = Context.EMPTY_CONTEXT;		
		
		Consumer<BinaryEntityContainer> consumer = ((ConsumerFactory<BinaryEntityContainer>) container).create(context);
		EphemeralBinaryEntityContainer root = new EphemeralBinaryEntityContainer();
		consumer.execute(root, monitor);
		
		BinaryResource testContainer = root.find("test-container", monitor);
		assertTrue(testContainer.exists(monitor));
		assertTrue(testContainer instanceof BinaryEntityContainer);
		
		BinaryResource testFile = root.find("test-container/test-file", monitor);
		assertTrue(testFile.exists(monitor));
		assertTrue(testFile instanceof BinaryEntity);		
		assertEquals("Hello, world!", Util.toString(context, ((BinaryEntity) testFile).getState(monitor)));
	}
	
	@Test
	public void testZipArchive() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object container = loader.loadYaml(TestExec.class.getResource("zip-archive-spec.yml"), monitor);
		assertEquals(Container.class, container.getClass());
				
		File outDir = new File("target" + File.separator + "test-output");
		outDir.mkdirs();
		Context context = Context.EMPTY_CONTEXT;		
		FileSystemContainer out = new FileSystemContainer(outDir);
		callConsumer(context, monitor, container, out);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testZipResourceCollection() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object container = loader.loadYaml(TestExec.class.getResource("zip-resource-collection-spec.yml"), monitor);
		assertEquals(Container.class, container.getClass());
		
		Context context = Context.EMPTY_CONTEXT;		
		
		Consumer<BinaryEntityContainer> consumer = ((ConsumerFactory<BinaryEntityContainer>) container).create(context);
		EphemeralBinaryEntityContainer root = new EphemeralBinaryEntityContainer();
		consumer.execute(root, monitor);
		
		BinaryResource testContainer = root.find("test-container/templates", monitor);
		assertTrue(testContainer.exists(monitor));
		assertTrue(testContainer instanceof BinaryEntityContainer);
		
		BinaryResource testFile = root.find("test-container/templates/hello-world.txt", monitor);
		assertTrue(testFile.exists(monitor));
		assertTrue(testFile instanceof BinaryEntity);		
		assertEquals("Hello ${name}!", Util.toString(context, ((BinaryEntity) testFile).getState(monitor)));
	}
		
	@Test
	public void testJava() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object container = loader.loadYaml(TestExec.class.getResource("java-spec.yml"), monitor);
		assertEquals(Container.class, container.getClass());
				
		File outDir = new File("target" + File.separator + "test-output");
		outDir.mkdirs();
		Context context = Context.EMPTY_CONTEXT;		
		FileSystemContainer out = new FileSystemContainer(outDir);
		callConsumer(context, monitor, container, out);		
	}
		
	@Test
	public void testBase64() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object base64 = loader.loadYaml(TestExec.class.getResource("base64-spec.yml"), monitor);
		assertEquals(Base64.class, base64.getClass());
		
		Context context = Context.EMPTY_CONTEXT;
		
		System.out.println(Util.toString(context, callSupplier(context, monitor, base64)));
	}
	
	@Test
	public void testForm() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object form = loader.loadYaml(TestExec.class.getResource("form-spec.yml"), monitor);
		assertEquals(Form.class, form.getClass());
		
		Context context = Context.EMPTY_CONTEXT;
		
		System.out.println(Util.toString(context, callSupplier(context, monitor, form)));
	}
	
	@Test
	public void testJson() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object json = loader.loadYaml(TestExec.class.getResource("json-spec.yml"), monitor);
		assertEquals(Json.class, json.getClass());
		
		Context context = Context.EMPTY_CONTEXT;
		
		InputStream result = callSupplier(context, monitor, json);
		System.out.println(Util.toString(context, result));
	}
	
	@Test
	public void testYaml() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object yaml = loader.loadYaml(TestExec.class.getResource("yaml-spec.yml"), monitor);
		assertEquals(org.nasdanika.exec.content.Yaml.class, yaml.getClass());
		
		Context context = Context.EMPTY_CONTEXT;
		
		InputStream result = callSupplier(context, monitor, yaml);
		System.out.println(Util.toString(context, result));
	}
	
	@Test
	public void testMapCast() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object json = loader.loadYaml(TestExec.class.getResource("map-cast-spec.yml"), monitor);
		assertEquals(Json.class, json.getClass());
		
		Context context = Context.EMPTY_CONTEXT;
		
		SupplierFactory<InputStream> supplierFactory = Loader.asSupplierFactory(json, null);
		Supplier<InputStream> supplier = supplierFactory.create(context);
		System.out.println(Util.toString(context, supplier.execute(monitor)));
	}
	
}
