package org.nasdanika.html.tests;

import org.junit.Test;
import org.nasdanika.html.Event;
import org.nasdanika.html.HTMLFactory;
import org.nasdanika.html.Tag;
import org.nasdanika.html.impl.HTMLElementImpl;


public class TestHtml extends HTMLTestBase {
	
	@Test
	public void testStringify() {
		System.out.println(HTMLElementImpl.stringify(getClass().getResource("test-resource.txt")));
	}
	
	@Test
	public void testInterpolate() {
		System.out.println(HTMLFactory.INSTANCE.interpolate(getClass().getResource("test-resource.txt"), "addressee", "world"));		
	}
	
	@Test public void testDiv() {
		HTMLFactory htmlFactory = HTMLFactory.INSTANCE;
		Tag div = htmlFactory.div("Hello")
				.style().border("solid 1px")
				.on(Event.click, getClass().getResource("ClickHandler.js"));
		System.out.println(div);
	}
			
}
