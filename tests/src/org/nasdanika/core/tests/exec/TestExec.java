package org.nasdanika.core.tests.exec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import org.junit.Ignore;
import org.junit.Test;
import org.nasdanika.common.Adaptable;
import org.nasdanika.common.Command;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.FilterProgressMonitor;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.resources.BinaryEntity;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.BinaryResource;
import org.nasdanika.common.resources.EphemeralBinaryEntityContainer;
import org.nasdanika.common.resources.FileSystemContainer;
import org.nasdanika.exec.Block;
import org.nasdanika.exec.Configurator;
import org.nasdanika.exec.Eval;
import org.nasdanika.exec.Fail;
import org.nasdanika.exec.Group;
import org.nasdanika.exec.If;
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
import org.nasdanika.exec.content.Replace;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.exec.git.GitBinaryEntityContainerSupplierFactory;
import org.nasdanika.exec.resources.Container;
import org.nasdanika.exec.resources.Git;
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
		
		InputStream result = callSupplier(context, monitor, iterator);
		assertEquals(" * uno *  * dos *  * tres * ", Util.toString(context, result));
	}
	
	/**
	 * Iteration over map values.
	 */
	@Test
	public void testIteratorMap() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object iterator = loader.loadYaml(TestExec.class.getResource("iterator-map-spec.yml"), monitor);
		assertEquals(Iterator.class, iterator.getClass());
		
		Map<String, Object> yaml = new Yaml().load(TestExec.class.getResourceAsStream("iterator-map-config.yml"));
		Context context = Context.wrap(yaml::get);
		
		InputStream result = callSupplier(context, monitor, iterator);
		assertEquals(" * v301-v302-w21 *  * v311-v312-w21 * ", Util.toString(context, result));
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
	@Test
	public void testMapper() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object mapper = loader.loadYaml(TestExec.class.getResource("mapper-spec.yml"), monitor);
		assertEquals(Mapper.class, mapper.getClass());
		
		Map<String, Object> yaml = new Yaml().load(TestExec.class.getResourceAsStream("iterator-config.yml"));
		Context context = Context.wrap(yaml::get);
		
		InputStream result = callSupplier(context, monitor, mapper);
		assertEquals(" * 123_v11 -- ${a/a1/a11} * ", Util.toString(context, result));
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

	/**
	 * Executes full command lifecycle - diagnose, execute, commit/rollback, close.
	 * @param context
	 * @param monitor
	 * @param component
	 * @return
	 * @throws Exception
	 */
	static void callCommand(Context context, ProgressMonitor monitor, Object component) throws Exception {
		try (Command command = Loader.asCommandFactory(component).create(context); ProgressMonitor progressMonitor = monitor.setWorkRemaining(3).split("Calling component", 3)) {
			Diagnostic diagnostic = command.splitAndDiagnose(progressMonitor);
			if (diagnostic.getStatus() == Status.ERROR) {
				diagnostic.dump(System.err, 4);
				fail("Diagnostic failed: " + diagnostic.getMessage());
			}
			
			try {
				command.splitAndExecute(progressMonitor);
				command.splitAndCommit(progressMonitor);
			} catch (Exception e) {
				e.printStackTrace();
				if (e instanceof DiagnosticException) {
					((DiagnosticException) e).getDiagnostic().dump(System.err, 4);
				}
				if (command.splitAndRollback(progressMonitor)) {
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
	public void testReplace() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object replace = loader.loadYaml(TestExec.class.getResource("replace-spec.yml"), monitor);
		assertEquals(Replace.class, replace.getClass());
		
		Context context = Context.singleton("name", "Universe");		
		
		String result = Util.toString(context, callSupplier(context, monitor, replace));
		assertEquals("Hello, Universe!", result);
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
	
	@Test
	public void testHttpsTrustAllNoVerifyHostCall() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object httpCall = loader.loadYaml(TestExec.class.getResource("https-call-trust-all-no-verify-host-spec.yml"), monitor);
		assertEquals(HttpCall.class, httpCall.getClass());
		
		Context context = Context.singleton("nasdanika", "https://nasdanika.org");		
		
		InputStream response = callSupplier(context, monitor, httpCall);
		assertEquals("Hello World!", Util.toString(context, response));
	}
	
	@Test
	public void testResources() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object container = loader.loadYaml(TestExec.class.getResource("resources-spec.yml"), monitor);
		assertEquals(Container.class, container.getClass());
		
		Context context = Context.EMPTY_CONTEXT;		
		
		EphemeralBinaryEntityContainer root = new EphemeralBinaryEntityContainer();
		callConsumer(context, monitor, container, root);
		
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
	
	@Test
	public void testZipResourceCollection() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object container = loader.loadYaml(TestExec.class.getResource("zip-resource-collection-spec.yml"), monitor);
		assertEquals(Container.class, container.getClass());
		
		Context context = Context.EMPTY_CONTEXT;		
		
		EphemeralBinaryEntityContainer root = new EphemeralBinaryEntityContainer();
		callConsumer(context, monitor, container, root);
		
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
		Collection<String> annotations = new ArrayList<>();
		annotations.add("Override");
		annotations.add("${import/org.nasdanika.TestAnnotation}");
		Context context = Context.singleton("myFieldAnnotations", annotations);		
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
		
		SupplierFactory<InputStream> supplierFactory = Loader.asSupplierFactory(json);
		Supplier<InputStream> supplier = supplierFactory.create(context);
		System.out.println(Util.toString(context, supplier.execute(monitor)));
	}
	
	@Test
	@Ignore("Don't run as part of automated build")
	public void testGitSupplierFactory() throws Exception {
		
		// Effectively final results collector in enclosing scope
		// We have single result, so just using array. Use something like beans for collecting multiple results.
		String[] result = {null};
		
		ConsumerFactory<BinaryEntityContainer> onPushFactory = context -> new Consumer<BinaryEntityContainer>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "on push";
			}

			@Override
			public void execute(BinaryEntityContainer container, ProgressMonitor progressMonitor) throws Exception {
				// Reporting result by assigning it to the result's collector only element.
				result[0] = "*** On push: " + container;				
			}
			
		};
		
		GitBinaryEntityContainerSupplierFactory gitSupplierFactory = new GitBinaryEntityContainerSupplierFactory(
				"Test GIT supplier", 
				null, 
				"https://github.com/Nasdanika/git-supplier-test-2.git", 
				"feature/test-2",
				null,
				"${user}", 
				"${auth-token}", 
				false, 
				Collections.singleton("."), 
				"Test " + new Date(), 
				"Pavel Vlasov", 
				"Pavel.Vlasov@nasdanika.org", 
				"my-tag", 
				true,
				null,
				onPushFactory);
		
		ConsumerFactory<BinaryEntityContainer> cf = context -> new Consumer<BinaryEntityContainer>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Git consumer";
			}

			@Override
			public void execute(BinaryEntityContainer repo, ProgressMonitor progressMonitor) throws Exception {
				BinaryEntity readme = repo.get("README.md", progressMonitor);
				readme.setState(DefaultConverter.INSTANCE.toInputStream("Readme " + new Date()), progressMonitor);
			}
			
		};
		
		Context context = Context.wrap(System.getenv()::get).map(key -> "test-git-supplier-" + key);

		try (ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false)) {
			try (Command command = gitSupplierFactory.then(cf).create(context); ProgressMonitor progressMonitor = monitor.setWorkRemaining(3).split("Calling Git Supplier", 3)) {
				Diagnostic diagnostic = command.splitAndDiagnose(progressMonitor);
				if (diagnostic.getStatus() == Status.ERROR) {
					diagnostic.dump(System.err, 4);
					fail("Diagnostic failed: " + diagnostic.getMessage());
				}
				
				try {
					command.splitAndExecute(progressMonitor);
					command.splitAndCommit(progressMonitor);
				} catch (Exception e) {
					e.printStackTrace();
					if (e instanceof DiagnosticException) {
						((DiagnosticException) e).getDiagnostic().dump(System.err, 4);
					}
					if (command.splitAndRollback(progressMonitor)) {
						fail("Exception " + e + ", rollback successful");
					} else {
						fail("Exception " + e + ", rollback failed");						
					}
					throw new NasdanikaException("Never get here");
				}
			}
		}
		
		// We can access the result here.
		System.out.println(result[0]);
	}
	
	@Test
	@Ignore("Don't run as part of automated build")
	public void testGitComponent() throws Exception {
		ConsumerFactory<BinaryEntityContainer> onPushFactory = context -> new Consumer<BinaryEntityContainer>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "on push";
			}

			@Override
			public void execute(BinaryEntityContainer container, ProgressMonitor progressMonitor) throws Exception {
				System.out.println("*** On push: " + container);	
			}
			
		};		
		
		ObjectLoader loader = new Loader() {
			
			public Object create(ObjectLoader loader, String type, Object config, java.net.URL base, ProgressMonitor progressMonitor, org.nasdanika.common.persistence.Marker marker) throws Exception {
				
				if ("my-on-push-component".equals(type)) {
					return onPushFactory;
				}
				
				return super.create(loader, type, config, base, progressMonitor, marker);
			};
			
		};
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object git = loader.loadYaml(TestExec.class.getResource("git-spec.yml"), monitor);
		assertEquals(Git.class, git.getClass());

		Collection<String> annotations = new ArrayList<>();
		annotations.add("Override");
		annotations.add("${import/org.nasdanika.TestAnnotation}");		
		Context context = Context.wrap(System.getenv()::get).map(key -> "test-git-supplier-" + key)
				.compose(Context.singleton("myFieldAnnotations", annotations))
				.compose(Context.singleton("date", new Date()));
		callCommand(context, monitor, git);
	}
	
	/**
	 * Demonstrates how to return value from on-push handler defined in YAML to the client code.
	 * @throws Exception
	 */
	@Test
	@Ignore("Don't run as part of automated build")
	public void testGitOnPushResult() throws Exception {
		
		ObjectLoader loader = new Loader() {
			
			public Object create(ObjectLoader loader, String type, Object config, java.net.URL base, ProgressMonitor progressMonitor, org.nasdanika.common.persistence.Marker marker) throws Exception {
				
				if ("my-on-push-component".equals(type)) {
					return new OnPushConsumerFactory(loader, config, base, progressMonitor, marker);
				}
				
				return super.create(loader, type, config, base, progressMonitor, marker);
			};
			
		};
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object git = loader.loadYaml(TestExec.class.getResource("git-spec-on-push-result.yml"), monitor);
		assertEquals(Git.class, git.getClass());

		Collection<String> annotations = new ArrayList<>();
		annotations.add("Override");
		annotations.add("${import/org.nasdanika.TestAnnotation}");
		
		// A sample result consumer which prints it to console.
		java.util.function.Consumer<Object> onPushResultConsumer = System.out::println;
		
		Context context = Context.wrap(System.getenv()::get).map(key -> "test-git-supplier-" + key)
				.compose(Context.singleton("myFieldAnnotations", annotations))
				.compose(Context.singleton("date", new Date()))
				.compose(Context.singleton("on-push-result-consumer", onPushResultConsumer));
		callCommand(context, monitor, git);
	}
	
	// Block tests
		
	@Test
	public void testBlockSupplierSuccess() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object block = loader.loadYaml(TestExec.class.getResource("block-supplier-success-spec.yml"), monitor);
		assertEquals(Block.class, block.getClass());
		Context context = Context.EMPTY_CONTEXT;
		InputStream result = callSupplier(context, monitor, block);
		assertEquals("HelloWorld", Util.toString(context, result));
	}
	
	@Test(expected = AssertionError.class)
	public void testBlockSupplierFail() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object block = loader.loadYaml(TestExec.class.getResource("block-supplier-fail-spec.yml"), monitor);
		assertEquals(Block.class, block.getClass());
		Context context = Context.EMPTY_CONTEXT;
		InputStream result = callSupplier(context, monitor, block);
		assertEquals("HelloWorld", Util.toString(context, result));
	}
	
	@Test
	public void testBlockSupplierCatch() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object block = loader.loadYaml(TestExec.class.getResource("block-supplier-catch-spec.yml"), monitor);
		assertEquals(Block.class, block.getClass());
		Context context = Context.EMPTY_CONTEXT;
		InputStream result = callSupplier(context, monitor, block);
		assertEquals("Erroneous org.nasdanika.common.NasdanikaExceptionWorld", Util.toString(context, result));
	}
		
	@Test(expected = AssertionError.class)
	public void testFail() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object fail = loader.loadYaml(TestExec.class.getResource("fail-spec.yml"), monitor);
		assertEquals(Fail.class, fail.getClass());
		Context context = Context.singleton("message", "Life sucks");
		callCommand(context, monitor, fail);
	}	
	
	@Test
	public void testEvalString() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object eval = loader.loadYaml(TestExec.class.getResource("eval-string-spec.yml"), monitor);
		assertEquals(Eval.class, eval.getClass());
		Context context = Context.singleton("age", 3);
		InputStream result = callSupplier(context, monitor, eval);
		assertEquals("8", Util.toString(context, result));
	}
	
	@Test
	public void testEval() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object eval = loader.loadYaml(TestExec.class.getResource("eval-spec.yml"), monitor);
		assertEquals(Eval.class, eval.getClass());
		Context context = Context.singleton("age", 3);
		InputStream result = callSupplier(context, monitor, eval);
		assertEquals("true", Util.toString(context, result));
	}
	
	@Test
	public void testIf() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object _if = loader.loadYaml(TestExec.class.getResource("if-spec.yml"), monitor);
		assertEquals(If.class, _if.getClass());
		MutableContext context = Context.singleton("age", 3).fork();
		context.put("male", true);
		InputStream result = callSupplier(context, monitor, _if);
		assertEquals("Good Bye", Util.toString(context, result));
	}
	
	@Test
	public void testProgress() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object progress = loader.loadYaml(TestExec.class.getResource("progress-spec.yml"), monitor);
		Context context = Context.EMPTY_CONTEXT;
		InputStream result = callSupplier(context, monitor, progress);
		assertEquals("HelloWorld", Util.toString(context, result));
	}
	
	@Test
	public void testSwitchString() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object progress = loader.loadYaml(TestExec.class.getResource("switch-string-spec.yml"), monitor);

		Context context = Context.singleton("word", "one");
		assertEquals("uno", Util.toString(context, callSupplier(context, monitor, progress)));

		context = Context.singleton("word", "two");
		assertEquals("dos", Util.toString(context, callSupplier(context, monitor, progress)));

		context = Context.singleton("word", "three");
		assertEquals("tres", Util.toString(context, callSupplier(context, monitor, progress)));

		context = Context.singleton("word", "quatro");
		assertEquals("no comprendo", Util.toString(context, callSupplier(context, monitor, progress)));

		context = Context.singleton("word", "2");
		assertEquals("dos", Util.toString(context, callSupplier(context, monitor, progress)));

		context = Context.singleton("word", "3");
		assertEquals("tres", Util.toString(context, callSupplier(context, monitor, progress)));		
	}
	
	@Test
	public void testSwitchNumber() throws Exception {
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object progress = loader.loadYaml(TestExec.class.getResource("switch-number-spec.yml"), monitor);

		Context context = Context.singleton("number", 1);
		assertEquals("uno", Util.toString(context, callSupplier(context, monitor, progress)));

		context = Context.singleton("number", 2);
		assertEquals("dos", Util.toString(context, callSupplier(context, monitor, progress)));

		context = Context.singleton("number", 3);
		assertEquals("tres", Util.toString(context, callSupplier(context, monitor, progress)));

		context = Context.singleton("number", 4);
		assertEquals("no comprendo", Util.toString(context, callSupplier(context, monitor, progress)));		
	}
	
	// Results collection
	
	/**
	 * Demonstrates how to collect execution results using anonymous classes.
	 * @throws Exception
	 */
	@Test
	public void testResultCollectionAnonymousClass() throws Exception {		
		// Results collector in enclosing scope.
		String[] result = {null};
		
		ObjectLoader loader = new Loader() {

			@Override
			public Object create(ObjectLoader loader, String type, Object config, URL base,	ProgressMonitor progressMonitor, Marker marker) throws Exception {
				
				if ("custom-component".equals(type)) {
					return new SupplierFactory<InputStream>() {

						@Override
						public Supplier<InputStream> create(Context context) throws Exception {
							return new Supplier<InputStream>() {

								@Override
								public double size() {
									return 1;
								}

								@Override
								public String name() {
									return "Custom content supplier";
								}

								@Override
								public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
									// Setting result value and returning the same value.
									result[0] = new Date().toString(); 
									return Util.toStream(context, result[0]);
								}
								
							};
						}
						
					};
				}
				
				return super.create(loader, type, config, base, progressMonitor, marker);
			}
			
		};
		
		// Execution
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object container = loader.loadYaml(TestExec.class.getResource("results-collection.yml"), monitor);
				
		File outDir = new File("target" + File.separator + "test-output" + File.separator + "results-collection" + File.separator + "anonymous");
		outDir.mkdirs();
		Context context = Context.EMPTY_CONTEXT;		
		FileSystemContainer out = new FileSystemContainer(outDir);
		callConsumer(context, monitor, container, out);
		
		// Accessing result
		System.out.println(result[0]);
	}
	
	@Test
	public void testResultCollectionCustomSupplierAndLoader() throws Exception {		
		// Results consumer.
		java.util.function.Consumer<String> resultsConsumer = System.out::println;
		
		ObjectLoader loader = new CustomLoader(resultsConsumer, new Loader());
		
		// Execution
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object container = loader.loadYaml(TestExec.class.getResource("results-collection.yml"), monitor);
				
		File outDir = new File("target" + File.separator + "test-output" + File.separator + "results-collection" + File.separator + "custom-supplier-and-loader");
		outDir.mkdirs();
		Context context = Context.EMPTY_CONTEXT;		
		FileSystemContainer out = new FileSystemContainer(outDir);
		callConsumer(context, monitor, container, out);
	}

	@Test
	public void testResultCollectionService() throws Exception {		
		// Results collector.
		ResultsCollector resultCollector = new ResultsCollector() {
			
			@Override
			public void onMyResult(String result) {
				System.out.println("*** My result: " + result);
				
			}
		};
		
		ObjectLoader loader = new CustomLoaderTwo(new Loader());
		
		// Execution
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object container = loader.loadYaml(TestExec.class.getResource("results-collection.yml"), monitor);
				
		File outDir = new File("target" + File.separator + "test-output" + File.separator + "results-collection" + File.separator + "context-service");
		outDir.mkdirs();
		Context context = Context.singleton(ResultsCollector.class, resultCollector);		
		FileSystemContainer out = new FileSystemContainer(outDir);
		callConsumer(context, monitor, container, out);
	}

	@Test
	public void testResultCollectionProperty() throws Exception {				
		ObjectLoader loader = new CustomLoaderThree(new Loader());
		
		// Execution
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object container = loader.loadYaml(TestExec.class.getResource("results-collection.yml"), monitor);
				
		File outDir = new File("target" + File.separator + "test-output" + File.separator + "results-collection" + File.separator + "context-property");
		outDir.mkdirs();
		Context context = Context.singleton("results-collector", (java.util.function.Consumer<String>) System.out::println);		
		FileSystemContainer out = new FileSystemContainer(outDir);
		callConsumer(context, monitor, container, out);
	}
	
	@Test
	public void testResultCollectionProgressMonitor() throws Exception {				
		ObjectLoader loader = new CustomLoaderFour(new Loader());
		
		// Execution
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object container = loader.loadYaml(TestExec.class.getResource("results-collection.yml"), monitor);
				
		File outDir = new File("target" + File.separator + "test-output" + File.separator + "results-collection" + File.separator + "progress-monitor");
		outDir.mkdirs();
		Context context = Context.EMPTY_CONTEXT;		
		FileSystemContainer out = new FileSystemContainer(outDir);
		
		class FilterMonitor extends FilterProgressMonitor {
			
			public FilterMonitor(ProgressMonitor target) {
				super(target);
			}

			@Override
			public void worked(Status status, double work, String progressMessage, Object... data) {				
				if (Status.SUCCESS == status 
						&& data != null 
						&& data.length == 2 
						&& CustomSupplierFactoryFour.class.isInstance(data[0])) {
					
					System.out.println("Gotcha: " + data[1]);
				}
				super.worked(status, work, progressMessage, data);
			}
			
			@Override
			public ProgressMonitor split(String taskName, double size, Object... data) {
				return new FilterMonitor(super.split(taskName, size, data));
			}
			
		}
		
		// Filtering worked notification to get to what we need by analyzing arguments, e.g. data.
		callConsumer(context, new FilterMonitor(monitor), container, out);
	}
	
	@Test
	public void testGroup() throws Exception {				
		ObjectLoader loader = new Loader();
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object group = loader.loadYaml(TestExec.class.getResource("group-spec.yml"), monitor);
		assertEquals(Group.class, group.getClass());
		
//		Context context = Context.EMPTY_CONTEXT;
//		Context context = Context.singleton("name", new String[] {"Universe", "Galaxy", "Quadrant", "Parsec"});
		Context context = Context.singleton("name", "Universe");
		
		System.out.println(Util.toString(context, callSupplier(context, monitor, group)));
	}	
	
}
