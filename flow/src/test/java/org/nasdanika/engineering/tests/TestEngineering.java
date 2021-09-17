package org.nasdanika.engineering.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.nasdanika.html.app.impl.Util.writeAction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.apache.commons.codec.binary.Hex;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Test;
import org.nasdanika.common.Command;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;
import org.nasdanika.common.resources.Container;
import org.nasdanika.common.resources.FileSystemContainer;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.engineering.Engineer;
import org.nasdanika.engineering.Event;
import org.nasdanika.engineering.Issue;
import org.nasdanika.engineering.Module;
import org.nasdanika.engineering.NamedElement;
import org.nasdanika.engineering.Organization;
import org.nasdanika.engineering.Temporal;
import org.nasdanika.engineering.util.EngineeringYamlLoadingExecutionParticipant;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.factories.BootstrapContainerApplicationSupplierFactory;
import org.nasdanika.html.app.factories.ComposedLoader;
import org.nasdanika.html.app.viewparts.AdaptiveNavigationPanelViewPart.Style;
import org.nasdanika.html.ecore.EcoreViewActionStorableAdapterFactory;
import org.nasdanika.html.ecore.GenModelResourceSet;
import org.nasdanika.html.emf.ViewActionStorable;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestEngineering extends TestBase {
	
	private DiagramGenerator createDiagramGenerator(ProgressMonitor progressMonitor) {
		FileSystemContainer output = new FileSystemContainer(new File("target\\diagram-cache"));
		
		BiFunction<String,InputStream,String> decoder = (path, state) -> DefaultConverter.INSTANCE.convert(state, String.class);
		BiFunction<String,String,InputStream> encoder = (path, state) -> DefaultConverter.INSTANCE.convert(state, InputStream.class);
		return DiagramGenerator.INSTANCE.cachingDiagramGenerator(output.stateAdapter().adapt(decoder, encoder), progressMonitor);
	}
		
	@Test	
	public void testEcoreDoc() throws Exception {
		GenModelResourceSet resourceSet = new GenModelResourceSet();
		
		Map<String,String> pathMap = new ConcurrentHashMap<>();
		
		Function<EPackage,String> getEPackagePath = ePackage -> {
			for (int i = 0; i < Integer.MAX_VALUE; ++i) {
				String path = i == 0 ? ePackage.getName() : ePackage.getName() + "_" + i;
				if (pathMap.containsKey(path)) {
					if (ePackage.getNsURI().equals(pathMap.get(path))) {
						return path;
					}
				} else {
					pathMap.put(path, ePackage.getNsURI());
					return path;
				}
			}
			
			// Encoding NS URI as HEX. Shall never reach this point.
			return Hex.encodeHexString(ePackage.getNsURI().getBytes(StandardCharsets.UTF_8));
		};
		
		MutableContext context = Context.EMPTY_CONTEXT.fork();
		
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		DiagramGenerator diagramGenerator = createDiagramGenerator(progressMonitor);
		context.register(DiagramGenerator.class, diagramGenerator);//DiagramGenerator.createClient(new URL("http://localhost:8090/spring-exec/api/v1/exec/diagram/")));
		
		resourceSet.getAdapterFactories().add(new EcoreViewActionStorableAdapterFactory(context, getEPackagePath));
		
		Map<String,String> bundleMap = new LinkedHashMap<>();
		bundleMap.put("model", "org.nasdanika.engineering");

		File modelDir = new File("target/models").getAbsoluteFile();
		modelDir.mkdirs();
		
		File modelDocDir = new File("target/model-doc").getAbsoluteFile();
		delete(modelDocDir);
		modelDocDir.mkdirs();
		
		Map<URI,File> modelToDocMap = new LinkedHashMap<>();
		
		for (Entry<String, String> be: bundleMap.entrySet()) {					
			File sourceDir = new File("..\\" + be.getKey());
			File targetDir = new File(modelDir, be.getValue());
			copy(new File(sourceDir, "model"), new File(targetDir, "model"), true, (source, target) -> {
				if (target.getName().endsWith(".genmodel")) {
					modelToDocMap.put(URI.createFileURI(target.getAbsolutePath()), new File(modelDocDir, target.getName() + ".yml"));
				}
			});			
			copy(new File(sourceDir, "doc"), new File(targetDir, "doc"), true, null);
		}		
		
		// Loading resources to the resource set.
		for (URI uri: modelToDocMap.keySet()) {
			resourceSet.getResource(uri, true);
		}		
		
		EcoreUtil.resolveAll(resourceSet);
		
		// Generating
		for (URI uri: modelToDocMap.keySet()) {
			Resource resource = resourceSet.getResource(uri, false);
			File output = modelToDocMap.get(resource.getURI());
			List<Object> data = new ArrayList<>();
			for (EObject contents: resource.getContents()) {
				if (contents instanceof GenModel) {
					for (GenPackage genPackage: ((GenModel) contents).getGenPackages()) {
						EPackage ecorePackage = genPackage.getEcorePackage();
						data.add(EObjectAdaptable.adaptTo(ecorePackage, ViewActionStorable.class).store(output.toURI().toURL(), progressMonitor));
					}
				}
			}
			
			DumperOptions dumperOptions = new DumperOptions();
			dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK);
			dumperOptions.setIndent(4);
			Yaml yaml = new Yaml(dumperOptions);
			yaml.dump(data.size() == 1 ? data.get(0) : data, new FileWriter(output));							
		}		
		
		// --- Generating documentation site ---
		String base = "tmp://base/engineering/";
		context.put(Context.BASE_URI_PROPERTY, base);
		
		ComposedLoader loader = new ComposedLoader() {
			@Override
			public Charset getCharset() {
				return StandardCharsets.UTF_8;
			}
		};
		Object actionFactory = loader.loadYaml(new File("doc-site.yml"), progressMonitor);
		Action action = Util.call(Util.<Action>asSupplierFactory(actionFactory).create(context), progressMonitor, null);
		
		FileSystemContainer output = new FileSystemContainer(new File("..\\docs")); 
		BiFunction<String, InputStream, String> decoder = Util.INPUT_STREAM_TO_STRING_DECODER;
		BiFunction<String, Object, InputStream> encoder = Util.OBJECT_TO_INPUT_STREAM_ENCODER;
		Container<String> container = output.stateAdapter().adapt(decoder, encoder);

		String resourceName = "cerulean/dark-fluid.yml";
		BootstrapContainerApplicationSupplierFactory applicationSupplierFactory = (BootstrapContainerApplicationSupplierFactory) loader.loadYaml(getClass().getResource(resourceName), progressMonitor);
		
		writeAction(
				action, 
				action.getNavigationChildren().get(0), 
				action, 
				base, 
				container, 
				context, 
				Style.CARDS, 
				applicationSupplierFactory, 
				progressMonitor);
		
		
		System.out.println(diagramGenerator);
	}
	
	public static void copy(File source, File target, boolean cleanTarget, BiConsumer<File,File> listener) throws IOException {
		if (cleanTarget && target.isDirectory()) {
			delete(target.listFiles());
		}
		if (source.isDirectory()) {
			target.mkdirs();
			for (File sc: source.listFiles()) {
				copy(sc, new File(target, sc.getName()), false, listener);
			}
		} else if (source.isFile()) {
			Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);			
			if (listener != null) {
				listener.accept(source, target);
			}
		}
	}

	public static void delete(File... files) {
		for (File file: files) {
			if (file.exists()) {
				if (file.isDirectory()) {
					delete(file.listFiles());
				}
				file.delete();
			}
		}
	}
	
	@Test
	public void testTemporal() throws Exception {	
		// Outputs to console, send to file if desired.
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		class TestCommand extends EngineeringYamlLoadingExecutionParticipant implements Command {

			public TestCommand(Context context) {
				super(context);
			}

			@Override
			protected Collection<URI> getResources() {
				return Collections.singleton(URI.createURI(getClass().getResource("temporal.yml").toString()));
			}

			@Override
			public void execute(ProgressMonitor progressMonitor) throws Exception {
				Organization org = (Organization) roots.iterator().next();
				
				Iterator<NamedElement> rit = org.getResources().iterator();
				
//		         name: Floating base
//		         path: floating-base         
//		         lower-bounds: 2021/7/6
//		         upper-bounds: 2021/7/9
				
				Event floatingBase = (Event) rit.next();
				assertEquals("floating-base", floatingBase.getPath());
				assertEquals(1, floatingBase.getLowerBounds().size());
				assertEquals(1, floatingBase.getUpperBounds().size());
				assertNull(floatingBase.getBase());
				assertNull(floatingBase.getInstant());				

//		         name: Instant base
//		         path: instant-base         
//		         instant: 2021/7/7
//		         upper-bounds: 2021/7/8				
				Event instantBase = (Event) rit.next();
				assertEquals("instant-base", instantBase.getPath());
				assertEquals(1, instantBase.getUpperBounds().size());
				assertNull(instantBase.getBase());
				assertNotNull(instantBase.getInstant());
				
//		         name: Relative to floating
//		         path: relative-to-floating
//		         base: temporal-tests:resources/floating-base
//		         offset: P10D        
//		         lower-bounds: 2021/7/15
//		         upper-bounds: 2021/7/18
				Event relativeToFloating = (Event) rit.next();
				assertEquals("relative-to-floating", relativeToFloating.getPath());
				assertEquals(1, relativeToFloating.getLowerBounds().size());
				assertEquals(1, relativeToFloating.getUpperBounds().size());
				assertEquals(Duration.parse("P10D"),relativeToFloating.minus(floatingBase));
				assertNull(relativeToFloating.minus(instantBase));
				assertEquals(floatingBase,relativeToFloating.getBase());

				Temporal normalizedRelativeToFloating = relativeToFloating.normalize();
				assertEquals(2, normalizedRelativeToFloating.getLowerBounds().size());
				assertEquals(2, normalizedRelativeToFloating.getUpperBounds().size());
				assertEquals(floatingBase,normalizedRelativeToFloating.getBase());
				assertEquals(Duration.parse("P10D"),normalizedRelativeToFloating.minus(floatingBase));
				assertNull(normalizedRelativeToFloating.minus(instantBase));
				
//		         name: Relative to instant
//		         path: relative-to-instant
//		         base: temporal-tests:resources/instant-base
//		         offset: P20D        
//		         lower-bounds: 2021/7/25
//		         upper-bounds: 2021/7/28
				Event relativeToInstant = (Event) rit.next();
				assertEquals("relative-to-instant", relativeToInstant.getPath());
				assertEquals(1, relativeToInstant.getLowerBounds().size());
				assertEquals(1, relativeToInstant.getUpperBounds().size());
				assertEquals(Duration.parse("P20D"),relativeToInstant.minus(instantBase));
				assertNull(relativeToInstant.minus(floatingBase));
				
				Temporal normalizedRelativeToInstant = relativeToInstant.normalize();
				assertEquals(1, normalizedRelativeToInstant.getLowerBounds().size());
				assertEquals(2, normalizedRelativeToInstant.getUpperBounds().size());
				assertNull(normalizedRelativeToInstant.getBase());
				
//		         name: Indirect relative to instant
//		         path: indirect-relative-to-instant
//		         base: temporal-tests:resources/relative-to-instant
//		         offset: P50D        
				Event indirectRelativeToInstant = (Event) rit.next();
				assertEquals("indirect-relative-to-instant", indirectRelativeToInstant.getPath());
				assertEquals(0, indirectRelativeToInstant.getLowerBounds().size());
				assertEquals(0, indirectRelativeToInstant.getUpperBounds().size());
				assertEquals(Duration.parse("P70D"),indirectRelativeToInstant.minus(instantBase));
				assertNull(indirectRelativeToInstant.minus(floatingBase));
				assertEquals(relativeToInstant, indirectRelativeToInstant.getBase());
				
				Temporal normalizedIndirectRelativeToInstant = indirectRelativeToInstant.normalize();
				assertEquals(1, normalizedIndirectRelativeToInstant.getLowerBounds().size());
				assertEquals(2, normalizedIndirectRelativeToInstant.getUpperBounds().size());
				assertNull(normalizedIndirectRelativeToInstant.getBase());
				assertEquals(Duration.parse("P70D"),normalizedIndirectRelativeToInstant.minus(instantBase));
				assertNull(normalizedIndirectRelativeToInstant.minus(floatingBase));
				assertNull(normalizedIndirectRelativeToInstant.getBase());
				
//		         name: Indirect relative to floating
//		         path: indirect-relative-to-floating
//		         base: temporal-tests:resources/relative-to-floating
//		         offset: P100D        
				Event indirectRelativeToFloating = (Event) rit.next();
				assertEquals("indirect-relative-to-floating", indirectRelativeToFloating.getPath());
				assertEquals(0, indirectRelativeToFloating.getLowerBounds().size());
				assertEquals(0, indirectRelativeToFloating.getUpperBounds().size());
				assertEquals(Duration.parse("P110D"),indirectRelativeToFloating.minus(floatingBase));
				assertNull(indirectRelativeToFloating.minus(instantBase));

				Temporal normalizedIndirectRelativeToFloating = indirectRelativeToFloating.normalize();
				assertEquals(2, normalizedIndirectRelativeToFloating.getLowerBounds().size());
				assertEquals(2, normalizedIndirectRelativeToFloating.getUpperBounds().size());
				assertEquals(Duration.parse("P110D"),normalizedIndirectRelativeToFloating.minus(floatingBase));
				assertNull(normalizedIndirectRelativeToFloating.minus(instantBase));
				assertEquals(floatingBase,normalizedIndirectRelativeToFloating.getBase());
			}
			
		};
		
		// Diagnosing loaded resources. 
		try {
			org.nasdanika.common.Diagnostic diagnostic = Util.call(new TestCommand(Context.EMPTY_CONTEXT), progressMonitor);
			if (diagnostic.getStatus() == Status.WARNING || diagnostic.getStatus() == Status.ERROR) {
				System.err.println("***********************");
				System.err.println("*      Diagnostic     *");
				System.err.println("***********************");
				diagnostic.dump(System.err, 4, Status.ERROR, Status.WARNING);
			}
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}
		
	}

	@Test
	public void testProxyResolution() throws Exception {	
		// Outputs to console, send to file if desired.
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		class TestCommand extends EngineeringYamlLoadingExecutionParticipant implements Command {

			public TestCommand(Context context) {
				super(context);
			}

			@Override
			protected Collection<URI> getResources() {
				return Collections.singleton(URI.createURI(getClass().getResource("proxy-resolution.yml").toString()));
			}

			@Override
			public void execute(ProgressMonitor progressMonitor) throws Exception {
				Organization org = (Organization) roots.iterator().next();
				//dumpToYaml(org);
				Issue issue = org.getIssues().iterator().next();
				Engineer engineer = org.getEngineers().iterator().next();
				assertThat(engineer).isEqualTo(issue.getAssignee());
				assertThat(engineer.getAssignments()).hasSize(1).contains(issue);
				
				Module product = org.getModules().iterator().next();
				assertEquals(1, product.getOwners().size());
				assertEquals(engineer, product.getOwners().iterator().next());
				assertEquals(1, engineer.getOwns().size());
				assertEquals(product, engineer.getOwns().iterator().next());
			}
			
		};
		
		// Diagnosing loaded resources. 
		try {
			org.nasdanika.common.Diagnostic diagnostic = Util.call(new TestCommand(Context.EMPTY_CONTEXT), progressMonitor);
			if (diagnostic.getStatus() == Status.WARNING || diagnostic.getStatus() == Status.ERROR) {
				System.err.println("***********************");
				System.err.println("*      Diagnostic     *");
				System.err.println("***********************");
				diagnostic.dump(System.err, 4, Status.ERROR, Status.WARNING);
			}
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}
		
	}
	
	@Test
	public void testImageEmbeddingAndInclusion() throws Exception {	
		// Outputs to console, send to file if desired.
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		
		class TestCommand extends EngineeringYamlLoadingExecutionParticipant implements Command {

			public TestCommand(Context context) {
				super(context);
			}

			@Override
			protected Collection<URI> getResources() {
				return Collections.singleton(URI.createURI(getClass().getResource("image-embedding-and-inclusion.yml").toString()));
			}

			@Override
			public void execute(ProgressMonitor progressMonitor) throws Exception {
				Organization org = (Organization) roots.iterator().next();
				Files.write(Path.of("dump.html"), org.getMarkdownDescription().getBytes());
			}
			
		};
		
		// Diagnosing loaded resources. 
		try {
			org.nasdanika.common.Diagnostic diagnostic = Util.call(new TestCommand(Context.EMPTY_CONTEXT), progressMonitor);
			if (diagnostic.getStatus() == Status.WARNING || diagnostic.getStatus() == Status.ERROR) {
				System.err.println("***********************");
				System.err.println("*      Diagnostic     *");
				System.err.println("***********************");
				diagnostic.dump(System.err, 4, Status.ERROR, Status.WARNING);
			}
		} catch (DiagnosticException e) {
			System.err.println("******************************");
			System.err.println("*      Diagnostic failed     *");
			System.err.println("******************************");
			e.getDiagnostic().dump(System.err, 4, Status.FAIL);
			throw e;
		}
		
	}
	
}
