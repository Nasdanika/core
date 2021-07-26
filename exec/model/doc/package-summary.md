Nasdanika Core Exec model classes can be used to create models for generation of resources such as files and their content.
To use in the Maven environment search for the latest version on [Maven Central](https://search.maven.org/artifact/org.nasdanika.core/exec) and copy/paste a dependency declaration to the ``pom.xml``.

### Maven dependency

Example as of the time of writing:

```xml
<dependency>
  <groupId>org.nasdanika.core</groupId>
  <artifactId>exec</artifactId>
  <version>2021.8.0</version>
</dependency>
```

----

TODO - Update code snippets and content below.

### Java code

#### Suppliers

To load and execute [content components](content/index.html) which implement ${javadoc/org.nasdanika.common.SupplierFactory}<${javadoc/java.io.InputStream}> from a YAML resource use code like this:

```java
import java.io.InputStream;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.exec.Iterator;
import org.nasdanika.exec.Loader;
import org.yaml.snakeyaml.Yaml;

...

// Create a loader. There is a constructor which takes a chain loader for processing unmatched types.
ObjectLoader loader = Loader();

// Progress monitor which outputs to the console. 
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);

// Loading YAML from a classloader resource.
Object iterator = loader.loadYaml(TestExec.class.getResource("iterator-spec.yml"), monitor);
		
// Loading context, also from a YAML classloader resource.		
Map<String, Object> yaml = new Yaml().load(TestExec.class.getResourceAsStream("iterator-config.yml"));
Context context = Context.wrap(yaml::get);
		
// Executing the object - in this case we are getting InputStream as a result.
InputStream result = callSupplier(context, monitor, iterator);

// Or, to get a string, use Util to convert InputStream to String 
String result = Util.toString(context, supplier.execute(monitor));
```

#### Consumers

To load and execute [resource components](resource/index.html) which implement ${javadoc/org.nasdanika.common.ConsumerFactory}<${javadoc/org.nasdanika.common.resources.BinaryEntityContainer}> from a YAML resource use code like this:

```java
import org.nasdanika.common.Context;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Util;
import org.nasdanika.exec.Loader;
import org.yaml.snakeyaml.Yaml;

...
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object container = loader.loadYaml(specURL, monitor);
		
File outDir = new File("target" + File.separator + "test-output");
outDir.mkdirs();
Context context = Context.EMPTY_CONTEXT;		
FileSystemContainer out = new FileSystemContainer(outDir);
callConsumer(context, monitor, container, out);		
```

#### Helper methods

Code snippets in components documentation and the code snippets above use two helper methods - ``callSupplier()`` and ``callConsumer()``. 
These methods cover full lifecycle of supplier and consumer execution participants (see [Execution model](${base-uri}reference/knowledge-base/core/common/execution-model.html)) - diagnose, execute, commit/rollback, and close. 
The methods use [JUnit](https://en.wikipedia.org/wiki/JUnit) ``fail()`` to report problems. Adjust as needed - throw an exception or return an exit or status code.

##### callSupplier()

```java
/**
 * Executes full supplier lifecycle - diagnose, execute, commit/rollback, close.
 * @param context
 * @param monitor
 * @param component
 * @return
 * @throws Exception
 */
static InputStream callSupplier(Context context, ProgressMonitor monitor, Object component) throws Exception {
	try (Supplier<InputStream> supplier = Loader.asSupplierFactory(component).create(context); ProgressMonitor progressMonitor = monitor.setWorkRemaining(3).split("Calling component", 3)) {
		Diagnostic diagnostic = supplier.splitAndDiagnose(progressMonitor);
		if (diagnostic.getStatus() == Status.ERROR) {
			diagnostic.dump(System.err, 4);
			fail("Diagnostic failed: " + diagnostic.getMessage());
		}
		
		try {
			InputStream result = supplier.splitAndExecute(progressMonitor);
			supplier.splitAndCommit(progressMonitor);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DiagnosticException) {
				((DiagnosticException) e).getDiagnostic().dump(System.err, 4);
			}
			if (supplier.splitAndRollback(progressMonitor)) {
				fail("Exception " + e + ", rollback successful");
			} else {
				fail("Exception " + e + ", rollback failed");						
			}
			throw new NasdanikaException("Never get here");
		}
	}
}
```

##### callConsumer()

```java
/**
 * Executes full consumer lifecycle - diagnose, execute, commit/rollback, close.
 * @param context
 * @param monitor
 * @param component
 * @return
 * @throws Exception
 */
static void callConsumer(Context context, ProgressMonitor monitor, Object component, BinaryEntityContainer container) throws Exception {
	try (Consumer<BinaryEntityContainer> consumer = Loader.asConsumerFactory(component).create(context); ProgressMonitor progressMonitor = monitor.setWorkRemaining(3).split("Calling component", 3)) {
		Diagnostic diagnostic = consumer.splitAndDiagnose(progressMonitor);
		if (diagnostic.getStatus() == Status.ERROR) {
			diagnostic.dump(System.err, 4);
			fail("Diagnostic failed: " + diagnostic.getMessage());
		}
		
		try {
			consumer.splitAndExecute(container, progressMonitor);
			consumer.splitAndCommit(progressMonitor);
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof DiagnosticException) {
				((DiagnosticException) e).getDiagnostic().dump(System.err, 4);
			}
			if (consumer.splitAndRollback(progressMonitor)) {
				fail("Exception " + e + ", rollback successful");
			} else {
				fail("Exception " + e + ", rollback failed");						
			}
		}
	}
}
```   

### Collecting execution results

In some situations it might be needed to return information from execution participants to the calling code.
For example, an ID of a generated resource or commit ID of pushed code.

This section outlines several ways of achieving this task:

* Anonymous execution participants - the calling code defines a custom loader which creates an anonymous sub-class of an execution participant. The subclass overrides one of methods to report execution result to some form of a result collector in the enclosing scope.
* Custom or customized execution participants - client code developer creates a custom execution participant or a sub-class of an existing execution participant class. The custom class takes some form of result collector in its constructor and stores it to an instance variable. Custom class uses the result collector to report execution result to the result collector. The sub-class overrides one of methods to report execution result to the result collector. Client code developer also creates a custom loader which takes the result collector as its constructor argument. The loader then passes the result collector to the customized execution participant constructor. This technique can be combined with the first one. For example, the loader may be anonymous and the custom component may be not, or vice versa. Also loaders can be sub-classed or chained.
* Context service - results collector is passed to execution participants as a context service. If the service is present in the context, execution participants report results to the service. 
* Context property - results collector is passed as a context property. A disadvantage of this approach over the previous one is that property name may change as a result of mapping or a property may become unavailable to downstream components unless explicitly mapped. 
* Progress monitor - results are reported as data to the progress monitor. The calling code inspects progress data to collect execution results.
* Exceptions - exception type and associated data is used to analyze the cause of failure. Can be used in conjunction with the progress monitor approach if the execution participant reporting failure also reports data associate with the failure to the progress monitor.

The following sections provide examples of implementing the first five of the above approaches.
All the examples use the same YAML specification:

```yaml
# Testing container and file
file:
   name: hello.txt
   contents:
      custom-component: {}
```      

#### Anonymous execution participants

```java
// Results collector in enclosing scope.
String[] result = {null};

ObjectLoader loader = new Loader() {

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base,	ProgressMonitor progressMonitor, Marker marker) throws Exception {
		
		if ("custom-component".equals(type)) {
			return new SupplierFactory<InputStream>() {

				@Override
				public Supplier<InputStream> create(Context context) throws Exception {
					return new Supplier<InputStream>() {

						@Override
						public double size() {
							return 1;
						}

						@Override
						public String name() {
							return "Custom content supplier";
						}

						@Override
						public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
							// Setting result value and returning the same value.
							result[0] = new Date().toString(); 
							return Util.toStream(context, result[0]);
						}
						
					};
				}
				
			};
		}
		
		return super.create(loader, type, config, base, progressMonitor, marker);
	}
	
};

// Execution
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object container = loader.loadYaml(TestExec.class.getResource("results-collection.yml"), monitor);
		
File outDir = new File("target" + File.separator + "test-output" + File.separator + "results-collection" + File.separator + "anonymous");
outDir.mkdirs();
Context context = Context.EMPTY_CONTEXT;		
FileSystemContainer out = new FileSystemContainer(outDir);
callConsumer(context, monitor, container, out);

// Accessing result
System.out.println(result[0]);
```

#### Custom execution participant

Custom content supplier and custom loader with chaining.

##### Custom supplier

```java
public class CustomSupplierFactory implements SupplierFactory<InputStream> {
	
	private Consumer<String> resultsCollector;

	public CustomSupplierFactory(java.util.function.Consumer<String> resultsCollector) {
		this.resultsCollector = resultsCollector;
	}

	@Override
	public Supplier<InputStream> create(Context context) throws Exception {
		return new Supplier<InputStream>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Custom content supplier";
			}

			@Override
			public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
				String result = new Date().toString();
				resultsCollector.accept(result);
				return Util.toStream(context, result);
			}
			
		};
	}
	
};
```

##### Custom loader

```java
public class CustomLoader extends ObjectLoader {
	
	private Consumer<String> resultsCollector;

	public CustomLoader(java.util.function.Consumer<String> resultsCollector, ObjectLoader chain) {
		this.resultsCollector = resultsCollector;
		this.chain = chain;
	}
		
	private org.nasdanika.common.ObjectLoader chain;

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		
		try (ProgressMonitor subMonitor = progressMonitor.setWorkRemaining(10).split("Creating " + type, 1, marker)) {
			switch (type) {
			case "custom-component":
				return new CustomSupplierFactory(resultsCollector);			
			default:
				if (chain == null) {
					throw new ConfigurationException("Unsupported type: " + type, marker);
				}
				
				return chain.create(loader, type, config, base, subMonitor, marker);
			}
		}
	}

}
```

##### Client code

```java
// Results consumer.
java.util.function.Consumer<String> resultsConsumer = System.out::println;

ObjectLoader loader = new CustomLoader(resultsConsumer, new Loader());

// Execution
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object container = loader.loadYaml(TestExec.class.getResource("results-collection.yml"), monitor);
		
File outDir = new File("target" + File.separator + "test-output" + File.separator + "results-collection" + File.separator + "anonymous");
outDir.mkdirs();
Context context = Context.EMPTY_CONTEXT;		
FileSystemContainer out = new FileSystemContainer(outDir);
callConsumer(context, monitor, container, out);
```

#### Context service

In the below example a service interface is defined for collecting results. 
An instance of the interface is passed as a context service down to executor.

##### Results collecting service

This example uses an interface, but it is also possible to define a results collecting class.

```java
public interface ResultsCollector {
	
	void onMyResult(String result);

}
```

##### Custom component

```java
public class CustomSupplierFactoryTwo implements SupplierFactory<InputStream> {
	
	@Override
	public Supplier<InputStream> create(Context context) throws Exception {
		return new Supplier<InputStream>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Custom content supplier";
			}

			@Override
			public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
				String result = new Date().toString();
				ResultsCollector resultsCollector = context.get(ResultsCollector.class);
				if (resultsCollector != null) {
					resultsCollector.onMyResult(result);
				}
				return Util.toStream(context, result);
			}
			
		};
	}
	
};
```

##### Custom loader

```java
public class CustomLoaderTwo extends ObjectLoader {

	public CustomLoaderTwo(ObjectLoader chain) {
		this.chain = chain;
	}
		
	private org.nasdanika.common.ObjectLoader chain;

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		
		try (ProgressMonitor subMonitor = progressMonitor.setWorkRemaining(10).split("Creating " + type, 1, marker)) {
			switch (type) {
			case "custom-component":
				return new CustomSupplierFactoryTwo();			
			default:
				if (chain == null) {
					throw new ConfigurationException("Unsupported type: " + type, marker);
				}
				
				return chain.create(loader, type, config, base, subMonitor, marker);
			}
		}
	}

}
```

##### Client code

```java
// Results collector.
ResultsCollector resultCollector = new ResultsCollector() {
	
	@Override
	public void onMyResult(String result) {
		System.out.println("*** My result: " + result);
		
	}
};

ObjectLoader loader = new CustomLoaderTwo(new Loader());

// Execution
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object container = loader.loadYaml(TestExec.class.getResource("results-collection.yml"), monitor);
		
File outDir = new File("target" + File.separator + "test-output" + File.separator + "results-collection" + File.separator + "context-service");
outDir.mkdirs();
Context context = Context.singleton(ResultsCollector.class, resultCollector);		
FileSystemContainer out = new FileSystemContainer(outDir);
callConsumer(context, monitor, container, out);
```

#### Context property

##### Custom component

```java
public class CustomSupplierFactoryThree implements SupplierFactory<InputStream> {
	
	@Override
	public Supplier<InputStream> create(Context context) throws Exception {
		return new Supplier<InputStream>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Custom content supplier";
			}

			@Override
			public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
				String result = new Date().toString();
				@SuppressWarnings("unchecked")
				java.util.function.Consumer<String> resultsCollector = context.get("results-collector", java.util.function.Consumer.class);
				if (resultsCollector != null) {
					resultsCollector.accept(result);
				}
				return Util.toStream(context, result);
			}
			
		};
	}
	
};
```

##### Custom loader

```java
public class CustomLoaderThree extends ObjectLoader {

	public CustomLoaderThree(ObjectLoader chain) {
		this.chain = chain;
	}
		
	private org.nasdanika.common.ObjectLoader chain;

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		
		try (ProgressMonitor subMonitor = progressMonitor.setWorkRemaining(10).split("Creating " + type, 1, marker)) {
			switch (type) {
			case "custom-component":
				return new CustomSupplierFactoryThree();			
			default:
				if (chain == null) {
					throw new ConfigurationException("Unsupported type: " + type, marker);
				}
				
				return chain.create(loader, type, config, base, subMonitor, marker);
			}
		}
	}

}
```

##### Client code

```java
ObjectLoader loader = new CustomLoaderThree(new Loader());

// Execution
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object container = loader.loadYaml(TestExec.class.getResource("results-collection.yml"), monitor);
		
File outDir = new File("target" + File.separator + "test-output" + File.separator + "results-collection" + File.separator + "context-service");
outDir.mkdirs();
Context context = Context.singleton("results-collector", (java.util.function.Consumer<String>) System.out::println);		
FileSystemContainer out = new FileSystemContainer(outDir);
callConsumer(context, monitor, container, out);
```

#### Progress monitor

##### Custom supplier

```java
public class CustomSupplierFactoryFour implements SupplierFactory<InputStream> {
	
	@Override
	public Supplier<InputStream> create(Context context) throws Exception {
		return new Supplier<InputStream>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Custom content supplier";
			}

			@Override
			public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
				String result = new Date().toString();
				progressMonitor.worked(1.0, "My execution result", CustomSupplierFactoryFour.this, result);
				return Util.toStream(context, result);
			}
			
		};
	}
	
};
```

##### Custom loader

```java
public class CustomLoaderFour extends ObjectLoader {

	public CustomLoaderFour(ObjectLoader chain) {
		this.chain = chain;
	}
		
	private org.nasdanika.common.ObjectLoader chain;

	@Override
	public Object create(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		
		try (ProgressMonitor subMonitor = progressMonitor.setWorkRemaining(10).split("Creating " + type, 1, marker)) {
			switch (type) {
			case "custom-component":
				return new CustomSupplierFactoryFour();			
			default:
				if (chain == null) {
					throw new ConfigurationException("Unsupported type: " + type, marker);
				}
				
				return chain.create(loader, type, config, base, subMonitor, marker);
			}
		}
	}

}
```

##### Client code

This code uses a subclass of FilterProgressMonitor to suppress reporting of the result to the print stream monitor. 
If suppression is not required monitor composition using ``compose()`` method can be used instead of filtering.

```java
ObjectLoader loader = new CustomLoaderFour(new Loader());

// Execution
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object container = loader.loadYaml(TestExec.class.getResource("results-collection.yml"), monitor);
		
File outDir = new File("target" + File.separator + "test-output" + File.separator + "results-collection" + File.separator + "progress-monitor");
outDir.mkdirs();
Context context = Context.EMPTY_CONTEXT;		
FileSystemContainer out = new FileSystemContainer(outDir);

class FilterMonitor extends FilterProgressMonitor {
	
	public FilterMonitor(ProgressMonitor target) {
		super(target);
	}

	@Override
	public void worked(Status status, double work, String progressMessage, Object... data) {				
		if (Status.SUCCESS == status 
				&& data != null 
				&& data.length == 2 
				&& CustomSupplierFactoryFour.class.isInstance(data[0])) {
			
			System.out.println("Gotcha: " + data[1]);
		} else {
			super.worked(status, work, progressMessage, data);
		}
	}
	
	@Override
	public ProgressMonitor split(String taskName, double size, Object... data) {
		return new FilterMonitor(super.split(taskName, size, data));
	}
	
}

// Filtering worked notification to get to what we need by analyzing arguments, e.g. data.
callConsumer(context, new FilterMonitor(monitor), container, out);
```
