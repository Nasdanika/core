package org.nasdanika.core.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.Converter;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.JavaExpressionPropertyComputer;
import org.nasdanika.common.ListCompoundSupplier;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressEntry;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.PropertyComputer;
import org.nasdanika.common.ServiceComputer;
import org.nasdanika.common.SimpleMutableContext;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.resources.BinaryEntity;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.EphemeralBinaryEntityContainer;
import org.nasdanika.common.resources.EphemeralEntityContainer;
import org.nasdanika.common.resources.TypedEntityContainer;


public class TestCommon {
		
	@Test
	public void testContext() throws Exception {
		Function<String, Object> f = key -> "** " + key;		
		Map<String, Object> m = Collections.singletonMap("f", f);
		Context c = Context.wrap(m::get);
		Assert.assertEquals("** mom", c.get("f/mom"));
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
		Assert.assertEquals("33", converter.convert(33, String.class));
		Assert.assertEquals(Integer.valueOf(33), converter.convert("33", Integer.class));
		Assert.assertEquals("Hello", converter.convert("{ \"value\": \"Hello\" }", JSONObject.class).getString("value"));
	}
	
	@Test 
	public void testInterpolation() {
		MutableContext ctx = new SimpleMutableContext();
		ctx.put("name", "World");
		Assert.assertEquals("Hello, World!", ctx.interpolate("${greeting|Hello}, ${name}!"));		
		
		ctx.put("nameResource", getClass().getResource("name.txt"));
		ctx.register(Converter.class, DefaultConverter.INSTANCE);
		Assert.assertEquals("Hello, World!", ctx.interpolate("${greeting|Hello}, ${nameResource}!"));	
		
		// Escaping, peeling
		Assert.assertEquals("${name}", ctx.interpolate("${{name}}"));						
		Assert.assertEquals("Hello, World - ${name}!", ctx.interpolate("${greeting|Hello}, ${name} - ${{name}}!"));						
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
							public String execute(ProgressMonitor progressMonitor) throws Exception {
								progressMonitor.worked(1, "Produced W1 result");
								return "W1-result";
							}
			
							@Override
							public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
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
							public String execute(ProgressMonitor progressMonitor) throws Exception {
								progressMonitor.worked(1, "Produced W2 result");
								return "W2-result";
							}
			
							@Override
							public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
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
				
				System.out.println(pe.toJSON().toString(4));
			}
		}
	}
	
	@Test
	public void testContainerZipping() throws Exception {		
		ProgressMonitor pm = new PrintStreamProgressMonitor();
		
		BinaryEntityContainer ephemeralContainer = new EphemeralBinaryEntityContainer();
		BinaryEntity binaryEntity = ephemeralContainer.get("test/myfile.bin", pm.split("Getting myfile.bin", 1));
		assertNotNull(binaryEntity);
		binaryEntity.setState(new ByteArrayInputStream("Hello".getBytes()), pm.split("Setting state", 1));

		java.io.File testsDir = new java.io.File("target/tests/container-zipping");
		testsDir.mkdirs();
		try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new java.io.File(testsDir, "myarchive.zip")))) {
			ephemeralContainer.store(zipOutputStream, null, pm.split("Storing", 1));
		}
		
		BinaryEntityContainer bec = new EphemeralBinaryEntityContainer();
		try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(new java.io.File(testsDir, "myarchive.zip")))) {
			bec.load(zipInputStream, null, pm.split("Loading", 1));
		}
		BinaryEntity ebe = bec.get("test/myfile.bin", pm.split("Getting loaded", 1));
		assertTrue(ebe.exists(pm.split("Checking existence", 1, ebe)));
		assertEquals("Hello", DefaultConverter.INSTANCE.convert(ebe.getState(pm.split("Getting stte", 1, ebe)), String.class));
		
		BiFunction<String,InputStream,String> decoder = (path, state) -> DefaultConverter.INSTANCE.convert(state, String.class);
		String sd = bec.stateAdapter().adapt(decoder, null).get("test/myfile.bin", pm.split("Getting loaded", 1));
		assertEquals("Hello", sd);
		
		TypedEntityContainer<String> sec = new EphemeralEntityContainer<String>();
		try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(new java.io.File(testsDir, "myarchive.zip")))) {
			sec.load(zipInputStream, null, decoder, pm.split("Loading", 1));
		}
		
		String data = sec.stateAdapter().get("test/myfile.bin", pm.split("Getting loaded", 1));
		assertEquals("Hello", data);
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
	public void testJavaExpressionPropertyComputer() {		
		Context context = Context.singleton("name", "World").compose(Context.singleton("eval", new JavaExpressionPropertyComputer()));
		assertEquals("Hello World", context.computingContext().get("eval/\"Hello \"+context.get(\"name\")"));		
	}

	@Test
	public void testJavaExpressionPropertyComputerInSimpleMutableContext() {
		MutableContext context = new SimpleMutableContext();
		context.put("eval", new JavaExpressionPropertyComputer());
		context.put("name", "World");
		assertEquals("Hello World", context.computingContext().get("eval/\"Hello \"+context.get(\"name\")"));		
	}
	
	@Test
	public void testJavaExpressionPropertyComputerInterpolation() {
		MutableContext context = new SimpleMutableContext();
		context.put("eval", new JavaExpressionPropertyComputer());
		context.put("name", "World");
		assertEquals("Hello World!!!", context.computingContext().interpolate("${eval/\"Hello \"+context.get(\"name\")}!!!"));		
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
	
}
