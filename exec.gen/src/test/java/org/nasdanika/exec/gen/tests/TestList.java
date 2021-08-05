package org.nasdanika.exec.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Status;

/**
 * @author Pavel
 *
 */
public class TestList extends TestBase {
	
	@Test
	public void testFlat() throws Exception {	
		Object obj = load(
				"list/flat.yml",
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(obj).isNotNull().isInstanceOf(List.class);
		List<?> list = (List<?>) obj;
		assertThat(list).hasSize(2);
		
		Object first = list.get(0);
		assertThat(DefaultConverter.INSTANCE.convert(first, String.class)).isEqualTo("Hello");
		
		Object second = list.get(1);
		assertThat(DefaultConverter.INSTANCE.convert(second, String.class)).isEqualTo("World");		
	}
	
	@Test
	public void testNested() throws Exception {	
		Object obj = load(
				"list/nested.yml",
				diagnostic -> {
					assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(obj).isNotNull().isInstanceOf(List.class);
		List<?> list = (List<?>) obj;
		assertThat(list).hasSize(2);
		
		Object first = list.get(0);
		assertThat(DefaultConverter.INSTANCE.convert(first, String.class)).isEqualTo("Hello");
		
		Object second = list.get(1);		
		assertThat(second).isNotNull().isInstanceOf(List.class);
		List<?> nestedList = (List<?>) second;
		assertThat(nestedList).hasSize(1);		
		assertThat(DefaultConverter.INSTANCE.convert(nestedList.get(0), String.class)).isEqualTo("World");		
	}
	
}
