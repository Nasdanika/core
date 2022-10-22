package org.nasdanika.common.tests;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.Function;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.Converter;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.DelimitedStringMap;
import org.nasdanika.common.DiagramGenerator;
import org.nasdanika.common.ListCompoundSupplier;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressEntry;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertyComputer;
import org.nasdanika.common.ServiceComputer;
import org.nasdanika.common.SimpleMutableContext;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.Util;
import org.yaml.snakeyaml.Yaml;


public class TestCommon {
		
	@Test
	public void testContext() throws Exception {
		Function<String, Object> f = key -> "** " + key;		
		Map<String, Object> m = Collections.singletonMap("f", f);
		Context c = Context.wrap(m::get);
		assertEquals("** mom", c.get("f/mom"));
	}
	
	@Test
	public void testServiceInstance() throws Exception {
		assertEquals(DefaultConverter.INSTANCE, Context.EMPTY_CONTEXT.get(DefaultConverter.class));
	}		
	
	@Test
	public void testSingleTokenInterpolation() throws Exception {
		Context ctx = Context.singleton("key", 33);
		assertEquals(33, ctx.interpolate("${key}"));
		
		Map<String,Object> map = new Yaml().load("k: ${key}");
		Map<String, Object> iMap = ctx.interpolate(map);
		assertEquals(33, iMap.get("k"));		
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testServiceComputer() throws Exception {
		MutableContext mc = new SimpleMutableContext();
		ServiceComputer<Callable> serviceComputer = new ServiceComputer<Callable>() {

			@Override
			public Callable compute(Context context, Class<Callable> type) {
				return () -> "I'm computed...";
			}
			
		};
		mc.register(Callable.class, serviceComputer);
		
		Object raw = mc.get(Callable.class);		
		assertTrue(raw instanceof ServiceComputer);
		
		Callable callable = mc.computingContext().get(Callable.class);		
		assertEquals("I'm computed..." , callable.call());
	}

	@Test
	public void testDefaultConverter() throws Exception {
		Converter converter = DefaultConverter.INSTANCE;
		assertEquals("33", converter.convert(33, String.class));
		assertEquals(Integer.valueOf(33), converter.convert("33", Integer.class));
		assertEquals("Hello", converter.convert("{ \"value\": \"Hello\" }", JSONObject.class).getString("value"));
	}
	
	@Test 
	public void testInterpolation() {
		MutableContext ctx = new SimpleMutableContext();
		ctx.put("name", "World");
		assertEquals("Hello, World!", ctx.interpolate("${greeting|Hello}, ${name}!"));		
		
		ctx.put("nameResource", getClass().getResource("name.txt"));
		ctx.register(Converter.class, DefaultConverter.INSTANCE);
		assertEquals("Hello, World!", ctx.interpolate("${greeting|Hello}, ${nameResource}!"));	
		
		// Escaping, peeling
		assertEquals("${name}", ctx.interpolate("${{name}}"));						
		assertEquals("Hello, World - ${name}!", ctx.interpolate("${greeting|Hello}, ${name} - ${{name}}!"));						
	}
	
	@Test 
	public void testYamlMapInterpolation() {
		Map<String, Object> yaml = new Yaml().load(TestCommon.class.getResourceAsStream("test-map-interpolation.yml"));
		Map<String, Object> interpolated = Context.singleton("name", "World").interpolate(yaml);	
		Context mapContext = Context.wrap(interpolated::get);
		assertEquals("World", mapContext.get("map/a"));
	}
	
	@Test 
	public void testProgressMonitor() {
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		ProgressEntry pe = new ProgressEntry("Test progress entry", 0);		
		try (ProgressMonitor subMonitor = progressMonitor.compose(pe.split("Sub-entry", 1))) {
			subMonitor.worked(1, "Worked some");
		}						
	}
		
	@Test 
	public void testCompoundSupplier() throws Exception {
		try (ProgressMonitor progressMonitor = new PrintStreamProgressMonitor()) {
			try (ProgressEntry pe = new ProgressEntry("Test progress entry", 0)) {		
				try (ProgressMonitor subMonitor = progressMonitor.compose(pe.split("Sub-entry", 1))) {
					try (ListCompoundSupplier<String> cw = new ListCompoundSupplier<>("Test work")) {
			
						cw.add(new Supplier<String>() {
			
							@Override
							public double size() {
								return 1;
							}
			
							@Override
							public String name() {
								return "W1";
							}
			
							@Override
							public String execute(ProgressMonitor progressMonitor) {
								progressMonitor.worked(1, "Produced W1 result");
								return "W1-result";
							}
			
							@Override
							public boolean rollback(ProgressMonitor progressMonitor) {
								// TODO Auto-generated method stub
								return true;
							}
							
						});
						
						cw.add(new Supplier<String>() {
			
							@Override
							public double size() {
								return 1;
							}
			
							@Override
							public String name() {
								return "W2";
							}
			
							@Override
							public String execute(ProgressMonitor progressMonitor) {
								progressMonitor.worked(1, "Produced W2 result");
								return "W2-result";
							}
			
							@Override
							public boolean rollback(ProgressMonitor progressMonitor) {
								// TODO Auto-generated method stub
								return true;
							}
							
						});
						
						System.out.println(cw.apply(subMonitor));
						
//						@Override
//						protected String combine(List<String> results, ProgressMonitor progressMonitor) throws Exception {
//							StringBuilder ret = new StringBuilder();
//							for (String result: results) {
//								progressMonitor.worked(1, "Combining "+result, result);
//								ret.append(result).append(" ");
//							}
//							return ret.toString();
//						}						
					}					
				}
				
				System.out.println(pe);
			}
		}
	}
	
	@Test
	public void testJSONObjectAsMap() {
		JSONObject jo = new JSONObject();
		jo.put("k1", "String");
		jo.put("k2",  33);
		
		JSONObject sjo = new JSONObject();
		sjo.put("sk1", "Text");
		jo.put("k3", sjo);
		
		JSONArray ja = new JSONArray();
		ja.put(1);
		ja.put("2");
		
		JSONObject ssjo = new JSONObject();
		ssjo.put("ssk1", 88);
		ja.put(ssjo);
				
		jo.put("k4", ja);
		
		Map<String, Object> jMap = jo.toMap();
		System.out.println(jMap);
		System.out.println(jMap.get("k3"));
		System.out.println(jMap.get("k4"));
	}
	
	@Test
	public void testMultiLevelPropertyComputer() {
		MutableContext context = new SimpleMutableContext();
		context.put("a/b/c", new PropertyComputer() {
			
			@SuppressWarnings("unchecked")
			@Override
			public <T> T compute(Context context, String key, String path, Class<T> type) {
				return (T) (key + " -> " + path);
			}
		});
		
		assertEquals("a/b/c -> d/e/f/g", context.computingContext().get("a/b/c/d/e/f/g"));		
	}
	
	/**
	 * Tests retrieval of different entries from a context wrapping a map.
	 */
	@Test
	public void testMapContext() {
		Map<String, Object> yaml = new Yaml().load(TestCommon.class.getResourceAsStream("test-map-context.yml"));
		System.out.println(yaml.get("empty-map"));
		Context mapContext = Context.wrap(yaml::get);		
		System.out.println("map: " + toString(mapContext.get("map")));
		System.out.println("map/a: " + toString(mapContext.get("map/a")));
		System.out.println("map/e: " + toString(mapContext.get("map/e")));
		System.out.println("map/e as sub-context: " + toString(mapContext.map(k -> "map/" + k).get("e")));
		System.out.println("list-of-maps: " + toString(mapContext.get("list-of-maps")));
		System.out.println("logical-yes: " + toString(mapContext.get("logical-yes")));
		System.out.println("logical-no: " + toString(mapContext.get("logical-no")));
	}
	
	private static String toString(Object obj) {
		if (obj == null) {
			return null;
		}
		
		return "[" + obj.getClass().toString() + "] " + obj;
			
	}

	@Test
	public void testHierarchicalMapping() {
		Map<String, Object> yaml = new Yaml().load(TestCommon.class.getResourceAsStream("test-hierarchical-mapping.yml"));
		Context mapContext = Context.wrap(yaml::get);		
		Context b1 = mapContext.map(Util.hierarchicalMapper("b/b1/"));
		Context b11 = b1.map(Util.hierarchicalMapper("b11/"));
		assertEquals("w111", b11.get("b111"));		
		assertEquals("w112", b1.get("b11/b112"));		
		assertEquals("w12", b11.get("../b12"));		
		assertEquals("w22", b11.get("../../b2/b22"));		
		assertEquals("v11", b11.get("../../../a/a1/a11"));		
	}
	
	@Test
	@Disabled
	public void testRemoteDiagramming() throws Exception {
		DiagramGenerator generator = DiagramGenerator.createClient(new URL("http://localhost:8090/spring-exec/api/v1/exec/diagram/"));
		System.out.println(generator.generateUmlDiagram("Alice -> Bob"));
	}
	
	@Test
	public void testJavadocResolver() throws Exception {
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();

		Function<String, String> java8Resolver = Util.createJavadocResolver(Collections.singleton(new URL("https://docs.oracle.com/javase/8/docs/api/")), progressMonitor);
		String java8MapLink = java8Resolver.apply("java.util.Map");
		assertEquals("<a href='https://docs.oracle.com/javase/8/docs/api/index.html?java/util/Map.html'>java.util.Map</a>", java8MapLink);

		Function<String, String> java11Resolver = Util.createJavadocResolver(Collections.singleton(new URL("https://docs.oracle.com/en/java/javase/11/docs/api/")), progressMonitor);
		String java11MapLink = java11Resolver.apply("java.util.Map");
		assertEquals("<a href='https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Map.html'>java.util.Map</a>", java11MapLink);	
		
		Function<String, String> nasdanikaResolver = Util.createNasdanikaJavadocResolver(new File("../.."), progressMonitor);
		String contextLink = nasdanikaResolver.apply("org.nasdanika.common.Context");
		assertEquals("<a href='https://docs.nasdanika.org/modules/core/apidocs/org.nasdanika.common/org/nasdanika/common/Context.html'>org.nasdanika.common.Context</a>", contextLink);		
	}
	
	@Test
	public void testJavadocPropertyComputer() throws Exception {
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Function<String, String> nasdanikaResolver = Util.createNasdanikaJavadocResolver(new File("../.."), progressMonitor);
		
		MutableContext context = Context.EMPTY_CONTEXT.fork();
		context.put("javadoc", Util.createJavadocPropertyComputer(nasdanikaResolver));
		String expected = "Hello <a href='https://docs.nasdanika.org/modules/core/apidocs/org.nasdanika.common/org/nasdanika/common/Context.html'>org.nasdanika.common.Context</a>!";
		assertEquals(expected, context.interpolateToString("Hello ${javadoc/org.nasdanika.common.Context}!"));
		
//		System.out.println(context.interpolateToString("${javadoc/org.nasdanika.common.SupplierFactory}<${javadoc/java.io.InputStream}>"));
	}
	
//	@Test
//	public void testURI() throws Exception {
//		URI uri = URI.createHierarchicalURI("z", "nasdanika", null, new String[] { "core", "", "pu", "" }, null, null);
//		System.out.println(uri + " " + Arrays.toString(uri.segments()) + " " + uri.isHierarchical() + " " + uri.hasAbsolutePath());
//		
//		URI subURI = URI.createURI("common/persistence");
//		System.out.println(subURI.resolve(uri));
//		System.out.println(subURI.scheme() + ", " + subURI.authority() + ", " + Arrays.toString(subURI.segments()));
//		
//		
//		URI sourceURI = URI.createURI("file:///C:/Users/Pavel/git/nasdanika.github.io/docs/modules/html/modules/models/modules/app/modules/model/index.html#content-left-navigation-panel");
//		URI targetURI = URI.createURI("file:///C:/Users/Pavel/git/nasdanika.github.io/docs/search.html");
//		URI deresolved = targetURI.deresolve(sourceURI, true, true, true);
//		System.out.println(deresolved);
//		URI pURI = deresolved.appendSegment("");
//		System.out.println(pURI + " -"+pURI.lastSegment()+"-");
//	}
	
	@Test
	public void testDelimitedStringMap() {
		String[] data = { "outlineConnect=0;fontColor=#232F3E;gradientColor=#945DF2;gradientDirection=north;fillColor=#5A30B5;strokeColor=#ffffff;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;shape=mxgraph.aws4.resourceIcon;resIcon=mxgraph.aws4.api_gateway;labelBackgroundColor=#ffffff;" };
		
		DelimitedStringMap map = new DelimitedStringMap(";", "=") {
			
			@Override
			protected void setState(String state) {
				data[0] = state;
			}
			
			@Override
			protected String getState() {
				return data[0];
			}
		};
		
		assertEquals(17, map.size());
		assertEquals("north", map.get("gradientDirection"));
		assertEquals("#ffffff", map.get("labelBackgroundColor"));

		assertEquals("#232F3E", map.put("fontColor", "#222222"));
		assertEquals("outlineConnect=0;fontColor=#222222;gradientColor=#945DF2;gradientDirection=north;fillColor=#5A30B5;strokeColor=#ffffff;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;shape=mxgraph.aws4.resourceIcon;resIcon=mxgraph.aws4.api_gateway;labelBackgroundColor=#ffffff", data[0]);

		assertEquals("#945DF2", map.remove("gradientColor"));
		assertEquals("outlineConnect=0;fontColor=#222222;gradientDirection=north;fillColor=#5A30B5;strokeColor=#ffffff;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;shape=mxgraph.aws4.resourceIcon;resIcon=mxgraph.aws4.api_gateway;labelBackgroundColor=#ffffff", data[0]);
		
		assertNull(map.put("answer-to-everything", "42"));
		assertEquals("outlineConnect=0;fontColor=#222222;gradientDirection=north;fillColor=#5A30B5;strokeColor=#ffffff;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;shape=mxgraph.aws4.resourceIcon;resIcon=mxgraph.aws4.api_gateway;labelBackgroundColor=#ffffff;answer-to-everything=42", data[0]);
		
		map.clear();
		assertEquals("", data[0]);		
	}
	
}
