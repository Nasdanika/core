package org.nasdanika.resources.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.function.BiFunction;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.junit.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.resources.BinaryEntity;
import org.nasdanika.resources.BinaryEntityContainer;
import org.nasdanika.resources.EphemeralBinaryEntityContainer;
import org.nasdanika.resources.EphemeralEntityContainer;
import org.nasdanika.resources.TypedEntityContainer;


public class TestResources {
		
	@Test
	public void testContainerZipping() throws Exception {		
		try (ProgressMonitor pm = new PrintStreamProgressMonitor()) {		
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
	}	
		
	@Test 
	public void testContainerDeletion() throws Exception {
		EphemeralBinaryEntityContainer container = new EphemeralBinaryEntityContainer();
		ProgressMonitor monitor = new PrintStreamProgressMonitor();
		BinaryEntity file = container.get("a/b/c/d.txt", monitor);
		file.setState(Util.toStream(Context.EMPTY_CONTEXT, "Hello"), monitor);
		container.getContainer("a/b", monitor).delete(monitor);
	}
	
}
