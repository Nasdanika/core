package org.nasdanika.exec.gen.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import org.junit.jupiter.api.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;

/**
 * Tests of descriptor view parts and wizards.
 * @author Pavel
 *
 */
public class TestCall extends TestBase {
	
	public static class CallTarget {
		
		private String name;

		public CallTarget() {
			this("Universe");
		}
		
		public CallTarget(String name) {
			this.name = name;
		}		
		
		public static String helloWorld() {
			return "Hello, World!";
		}
		
		public String greet(String greeting) {
			return greeting + ", " + name + "!";
		}
		
	}
	
	public static class CallSupplier implements Supplier<String> {
		
		private String name;

		public CallSupplier() {
			this("World");
		}
		
		public CallSupplier(String name) {
			this.name = name;
		}

		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return "Call supplier " + name;
		}

		@Override
		public String execute(ProgressMonitor progressMonitor) {
			return "Hello, " + name + "!";
		}
				
	}
	
	public static class CallSupplierFactory implements SupplierFactory<String> {

		@Override
		public Supplier<String> create(Context context) {
			return new CallSupplier(context.getString("name"));
		}
		
	}
	
	public static class ConfigurableCallTarget implements BiConsumer<String, Object> {
		
		private String name;
		private Map<String,String> properties = new HashMap<>();

		public ConfigurableCallTarget() {
			this("Universe");
		}
		
		public ConfigurableCallTarget(String name) {
			this.name = name;
		}		
		
		public String greet(String greeting) {
			return greeting + ", " + name + properties + "!";
		}

		@Override
		public void accept(String name, Object value) {
			properties.put(name, DefaultConverter.INSTANCE.convert(value, String.class));			
		}
		
	}	
	
	@Test
	public void testStaticMethod() throws Exception {	
		InputStream in = loadInputStream(
				"call/static-method.yml",
				diagnostic -> {
					Status status = diagnostic.getStatus();
					if (status != Status.SUCCESS) {
						diagnostic.dump(System.out, 0);
					}
					assertThat(status).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hello, World!");
	}
	
	@Test
	public void testInstanceMethod() throws Exception {	
		InputStream in = loadInputStream(
				"call/instance-method.yml",
				diagnostic -> {
					Status status = diagnostic.getStatus();
					if (status != Status.SUCCESS) {
						diagnostic.dump(System.out, 0);
					}
					assertThat(status).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hi, Universe!");
	}
	
	@Test
	public void testPropertyMethod() throws Exception {	
		InputStream in = loadInputStream(
				"call/property-method.yml",
				diagnostic -> {
					Status status = diagnostic.getStatus();
					if (status != Status.SUCCESS) {
						diagnostic.dump(System.out, 0);
					}
					assertThat(status).isEqualTo(Status.SUCCESS);
				},
				Context.EMPTY_CONTEXT,
				Context.singleton("call-target", new CallTarget("Quadrant")));
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hi, Quadrant!");
	}
	
	@Test
	public void testServiceMethod() throws Exception {	
		InputStream in = loadInputStream(
				"call/service-method.yml",
				diagnostic -> {
					Status status = diagnostic.getStatus();
					if (status != Status.SUCCESS) {
						diagnostic.dump(System.out, 0);
					}
					assertThat(status).isEqualTo(Status.SUCCESS);
				},
				Context.EMPTY_CONTEXT,
				Context.singleton(CallTarget.class, new CallTarget("Solar system")));
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hi, Solar system!");
	}
	
	@Test
	public void testInstanceMethodWithConstructor() throws Exception {	
		InputStream in = loadInputStream(
				"call/instance-with-constructor-arguments-method.yml",
				diagnostic -> {
					Status status = diagnostic.getStatus();
					if (status != Status.SUCCESS) {
						diagnostic.dump(System.out, 0);
					}
					assertThat(status).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hello, Galaxy!");
	}
	
	@Test
	public void testProperties() throws Exception {	
		InputStream in = loadInputStream(
				"call/instance-properties.yml",
				diagnostic -> {
					Status status = diagnostic.getStatus();
					if (status != Status.SUCCESS) {
						diagnostic.dump(System.out, 0);
					}
					assertThat(status).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hi, Universe{speed-of-light=Very high}!");
	}
	
	@Test
	public void testDefaultSupplier() throws Exception {	
		InputStream in = loadInputStream(
				"call/default-supplier.yml",
				diagnostic -> {
					Status status = diagnostic.getStatus();
					if (status != Status.SUCCESS) {
						diagnostic.dump(System.out, 0);
					}
					assertThat(status).isEqualTo(Status.SUCCESS);
				});
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hello, World!");
	}
		
	@Test
	public void testDefaultSupplierFactory() throws Exception {	
		InputStream in = loadInputStream(
				"call/default-supplier-factory.yml",
				diagnostic -> {
					Status status = diagnostic.getStatus();
					if (status != Status.SUCCESS) {
						diagnostic.dump(System.out, 0);
					}
					assertThat(status).isEqualTo(Status.SUCCESS);
				},
				Context.EMPTY_CONTEXT,
				Context.singleton("name", "Universe"));
		
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hello, Universe!");
	}
	
//	
//	@Test
//	public void testContentSuccessful() throws Exception {	
//		InputStream in = loadInputStream(
//				"block/block-content-successful.yml",
//				diagnostic -> {					
//					Status status = diagnostic.getStatus();
//					if (status != Status.SUCCESS) {
//						diagnostic.dump(System.out, 0);
//					}
//					assertThat(status).isEqualTo(Status.SUCCESS);
//				},
//				Context.EMPTY_CONTEXT,
//				Context.singleton("token", "World"));
//		
//		assertThat(in).isNotNull();
//		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hello, World!World");
//	}
		
}
