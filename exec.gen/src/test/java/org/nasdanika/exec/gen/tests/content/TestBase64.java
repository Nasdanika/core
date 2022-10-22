package org.nasdanika.exec.gen.tests.content;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Status;
import org.nasdanika.exec.gen.tests.TestBase;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestBase64 extends TestBase {
	
	private static final String ENCODED = "R0lGODlhEAAQAMQAAIKGgYKFgfj50fj51vf41vj41qCYbKGYbG1kP2piP3RoPHBlPXlqOoNvN35sOJN3Mo51NIlyNp18L5t7MJd4MP///wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAEAABUALAAAAAAQABAAAAVLYCWOZGmeqKSu6ykJcCxI5yTH00kRxDAUP8rpcTAYj48T5AaDnCJMQeTU4PmAhcbJAQh4v44TI8o4KaKK08L6+y1OiIR8nkCg7qIQADs=";
	
	@Test
	public void testBase64() throws Exception {	
		InputStream in = loadInputStream(
				"base-64/base-64.yml",
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				},
				Context.EMPTY_CONTEXT,
				Context.singleton("name", "World"));
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo(ENCODED);
	}
		
}
