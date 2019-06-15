package org.nasdanika.core.tests;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.nasdanika.common.Converter;
import org.nasdanika.common.DefaultConverter;


public class TestCommon {
		
	@Test
	public void testContext() throws Exception {
		// TODO
	}

	@Test
	public void testDefaultConverter() throws Exception {
		Converter converter = DefaultConverter.INSTANCE;
		Assert.assertEquals("33", converter.convert(33, String.class));
		Assert.assertEquals(Integer.valueOf(33), converter.convert("33", Integer.class));
		Assert.assertEquals("Hello", converter.convert("{ \"value\": \"Hello\" }", JSONObject.class).getString("value"));
	}
	
}
