package org.nasdanika.core.tests;

import org.junit.Assert;
import org.junit.Test;
import org.nasdanika.common.Converter;
import org.nasdanika.common.ReflectiveConverter;


public class TestCommon {
		
	@Test
	public void testContext() throws Exception {
		
	}

	@Test
	public void testReflectiveConverter() throws Exception {
		Converter converter = ReflectiveConverter.INSTANCE;
		Assert.assertEquals("33", converter.convert(33, String.class));
	}
	
}
