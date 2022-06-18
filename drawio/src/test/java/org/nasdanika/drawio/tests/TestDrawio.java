package org.nasdanika.drawio.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.junit.Test;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;

public class TestDrawio {

	@Test
	public void testCompressed() throws Exception {
		Document document = Document.load(getClass().getResource("compressed.drawio"));
		assertThat(document.getElement().getTagName()).isEqualTo("mxfile");
	}
	
	@Test
	public void testUncompressed() throws Exception {
		Document document = Document.load(getClass().getResource("uncompressed.drawio"));
		assertThat(document.getElement().getTagName()).isEqualTo("mxfile");
		assertThat(document.getPages().size()).isEqualTo(2);
		
		BiFunction<Element, Map<Element, String>, String> visitor = (element, childResults) -> {
			return element.getElement().getTagName() + "[" + element.getElement().getAttribute("id") + "] " + childResults;
		};
		String result = document.accept(visitor);
		System.out.println(result);
	}

}
