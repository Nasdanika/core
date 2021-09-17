package org.nasdanika.exec.tests.content;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.Status;
import org.nasdanika.exec.content.Text;
import org.nasdanika.exec.tests.TestBase;
import org.nasdanika.ncore.Marker;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestText extends TestBase {
	
	@Test
	public void testFullDefinition() throws Exception {	
		load(
				"text/text.yml", 
				Context.singleton("token", "World"),
				obj -> {
					Text text = (Text) obj;
					assertThat(text.getDescription()).isEqualTo("Full text definition");
					assertThat(text.getContent()).isEqualTo("Hello World.");
					assertThat(text.isInterpolate()).isFalse();
					Marker marker = text.getMarker();
					assertThat(marker).isNotNull();
				},
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});		
	}
	
	@Test
	public void testDefaultFeature() throws Exception {	
		load(
				"text/text-default-feature.yml", 
				Context.singleton("token", "World"),
				obj -> {
					Text text = (Text) obj;
					assertThat(text.getDescription()).isNull();
					assertThat(text.getContent()).isEqualTo("Hello World.");
					assertThat(text.isInterpolate()).isTrue();
				}, 
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});		
	}
	
}
