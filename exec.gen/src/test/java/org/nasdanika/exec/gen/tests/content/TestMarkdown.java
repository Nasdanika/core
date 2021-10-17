package org.nasdanika.exec.gen.tests.content;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;

import org.junit.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Status;
import org.nasdanika.exec.gen.tests.TestBase;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestMarkdown extends TestBase {
	
	@Test
	public void testMarkdown() throws Exception {	
		InputStream in = loadInputStream(
				"markdown/markdown.yml",
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				},
				Context.EMPTY_CONTEXT,
				Context.EMPTY_CONTEXT);
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in).trim()).isEqualTo("<p>Hello, <code>World</code>!</p>");
	}
	
	@Test
	public void testStyled() throws Exception {	
		InputStream in = loadInputStream(
				"markdown/styled.yml",
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				},
				Context.EMPTY_CONTEXT,
				Context.EMPTY_CONTEXT);
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in).trim()).isEqualTo("<div class=\"markdown-body\"><p>Hello, <code>World</code>!</p>\n</div>");
	}
		
}
