package org.nasdanika.exec.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Status;

/**
 * @author Pavel
 *
 */
public class TestMap extends TestBase {
	
	@Test
	public void testFlat() throws Exception {	
		Object obj = load(
				"map/flat.yml",
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(obj).isNotNull().isInstanceOf(Map.class);
		Map<?,?> map = (Map<?,?>) obj;
		assertThat(map).hasSize(2);
		
		Object greeting = map.get("greeting");
		assertThat(DefaultConverter.INSTANCE.convert(greeting, String.class)).isEqualTo("Hello");
		
		Object addressee = map.get("addressee");
		assertThat(DefaultConverter.INSTANCE.convert(addressee, String.class)).isEqualTo("World");		
	}
	
	@Test
	public void testNested() throws Exception {	
		Object obj = load(
				"map/nested.yml",
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(obj).isNotNull().isInstanceOf(Map.class);
		Map<?,?> map = (Map<?,?>) obj;
		assertThat(map).hasSize(2);
		
		Object greeting = map.get("greeting");
		assertThat(DefaultConverter.INSTANCE.convert(greeting, String.class)).isEqualTo("And here's to you");
		
		Object addressee = map.get("addressee");		
		assertThat(addressee).isNotNull().isInstanceOf(Map.class);
		Map<?,?> aMap = (Map<?,?>) addressee;
		assertThat(aMap).hasSize(2);
		
		Object salutation = aMap.get("salutation");
		assertThat(DefaultConverter.INSTANCE.convert(salutation, String.class)).isEqualTo("Mrs.");
		
		Object name = aMap.get("name");
		assertThat(DefaultConverter.INSTANCE.convert(name, String.class)).isEqualTo("Robinson");
	}
	
}
