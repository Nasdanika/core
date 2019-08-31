package org.nasdanika.core.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.nasdanika.common.CompoundWork;
import org.nasdanika.common.Context;
import org.nasdanika.common.Converter;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.MutableContext;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressEntry;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SimpleMutableContext;
import org.nasdanika.common.Work;
import org.nasdanika.common.resources.Container.Copier;
import org.nasdanika.common.resources.EphemeralContainer;
import org.nasdanika.common.resources.EphemeralEntity;


public class TestCommon {
		
	@Test
	public void testContext() throws Exception {
		Function<String, Object> f = key -> "** " + key;		
		Map<String, Object> m = Collections.singletonMap("f", f);
		Context c = Context.wrap(m::get);
		Assert.assertEquals("** mom", c.get("f/mom"));
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
	public void testCompoundWork() throws Exception {
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		ProgressEntry pe = new ProgressEntry("Test progress entry", 0);		
		try (ProgressMonitor subMonitor = progressMonitor.compose(pe.split("Sub-entry", 1))) {
			CompoundWork<String, String> cw = new CompoundWork<String, String>("Test work", null) {
				
				@Override
				protected String combine(List<String> results, ProgressMonitor progressMonitor) throws Exception {
					StringBuilder ret = new StringBuilder();
					for (String result: results) {
						progressMonitor.worked(1, "Combining "+result, result);
						ret.append(result).append(" ");
					}
					return ret.toString();
				}
				
			};

			cw.add(new Work<String>() {

				@Override
				public long size() {
					return 1;
				}

				@Override
				public String getName() {
					return "W1";
				}

				@Override
				public String execute(ProgressMonitor progressMonitor) throws Exception {
					progressMonitor.worked(1, "Produced W1 result");
					return "W1-result";
				}

				@Override
				public boolean undo(ProgressMonitor progressMonitor) throws Exception {
					// TODO Auto-generated method stub
					return true;
				}
				
			});
			
			cw.add(new Work<String>() {

				@Override
				public long size() {
					return 1;
				}

				@Override
				public String getName() {
					return "W2";
				}

				@Override
				public String execute(ProgressMonitor progressMonitor) throws Exception {
					progressMonitor.worked(1, "Produced W2 result");
					return "W2-result";
				}

				@Override
				public boolean undo(ProgressMonitor progressMonitor) throws Exception {
					// TODO Auto-generated method stub
					return true;
				}
				
			});
			
			System.out.println(cw.execute(subMonitor));
			
		}
		
		System.out.println(pe.toJSON().toString(4));
	}
	
	@Test
	public void testContainerZipping() throws Exception {		
		ProgressMonitor pm = new PrintStreamProgressMonitor();
		
		EphemeralContainer<byte[]> ephemeralContainer = new EphemeralContainer<byte[]>();
		EphemeralEntity<byte[]> ephemeralEntity = ephemeralContainer.get("test/myfile.bin", pm.split("Getting myfile.bin", 1));
		assertNotNull(ephemeralEntity);
		ephemeralEntity.setState("Hello".getBytes(), pm.split("Setting state", 1));

		java.io.File testsDir = new java.io.File("target/tests/container-zipping");
		testsDir.mkdirs();
		try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(new java.io.File(testsDir, "myarchive.zip")))) {
			Copier<EphemeralEntity<byte[]>, InputStream> copier = (path, entity, existing, monitor) -> new ByteArrayInputStream(entity.getState(monitor));				
			ephemeralContainer.store(zipOutputStream, null, copier, pm.split("Storing", 1));
		}
		
		EphemeralContainer<String> sec = new EphemeralContainer<String>();
		try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(new java.io.File(testsDir, "myarchive.zip")))) {
			@SuppressWarnings("unchecked")
			Copier<InputStream, EphemeralEntity<String>> contentLoader = (path, source, existing, monitor) -> {
				String value = DefaultConverter.INSTANCE.convert(new FilterInputStream(source) { public void close() {} }, String.class);
				EphemeralEntity<String> existingEntity = (EphemeralEntity<String>) existing;
				assertNotNull(existingEntity);
				existingEntity.setState(value, monitor);		
				return null;
			};
			sec.load(zipInputStream, null, contentLoader, pm.split("Loading", 1));
		}
		EphemeralEntity<String> sf = sec.get("test/myfile.bin", pm.split("Getting loaded", 1));
		assertTrue(sf.exists(pm.split("Checking existens", 1, sf)));
		assertEquals("Hello", sf.getState(pm));
	}	
	
}
