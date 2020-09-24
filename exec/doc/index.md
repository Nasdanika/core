Nasdanika Core Exec module is a library of ${javadoc/org.nasdanika.common.ExecutionParticipant execution participants}.
It provides an [object loader](general/loader.html) for loading and composing the participants for execution.
See [Execution model](${base-uri}reference/knowledge-base/core/common/execution-model.html) for a general information regarding execution participants and Nasdanika execution model.

To use the loader in the OSGi environment add ``org.nasdanika.exec`` dependency to the manifest file.

To use in the Maven environment search for the latest version on [Maven Central](https://search.maven.org/search?q=g:org.nasdanika.core%20AND%20a:exec) and copy/paste a dependency declaration to the ``pom.xml``.

### Maven dependency

Example as of the time of writing:

```xml
<dependency>
  <groupId>org.nasdanika.core</groupId>
  <artifactId>exec</artifactId>
  <version>2020.9.3</version>
</dependency>
```

### Java code

#### Suppliers

To load and execute [content components](content/index.html) which implement ${javadoc/org.nasdanika.common.SupplierFactory}<${javadoc/java.io.InputStream}> from a YAML resource use code like this:

```java
import java.io.InputStream;
import java.util.Map;

import org.nasdanika.common.Context;
import org.nasdanika.common.ObjectLoader;
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
import org.nasdanika.common.ObjectLoader;
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
