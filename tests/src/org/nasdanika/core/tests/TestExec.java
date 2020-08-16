package org.nasdanika.core.tests;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.util.Map;

import org.junit.Test;
import org.nasdanika.common.Adaptable;
import org.nasdanika.common.Context;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.exec.Iterator;
import org.yaml.snakeyaml.Yaml;


public class TestExec {
	
	/**
	 * Tests retrieval of different entries from a context wrapping a map.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testIterator() throws Exception {
		ObjectLoader loader = new ObjectLoader(new org.nasdanika.exec.Factory());
		ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
		Object iterator = loader.loadYaml(TestExec.class.getResource("exec-iterator-spec.yml"), monitor);
		assertEquals(Iterator.class, iterator.getClass());
		
		Map<String, Object> yaml = new Yaml().load(TestExec.class.getResourceAsStream("exec-iterator-config.yml"));
		Context context = Context.wrap(yaml::get);
		
		SupplierFactory<InputStream> sf = ((Adaptable) iterator).adaptTo(SupplierFactory.class);
		Supplier<InputStream> s = sf.create(context);
		assertEquals(" * uno *  * dos *  * tres * ", Util.toString(context, s.execute(monitor)));
	}
	
}
