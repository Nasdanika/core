Nasdanika Core Exec module is a library of ${javadoc/org.nasdanika.common.ExecutionParticipant execution participants}.
It provides an [object loader](general/loader.html) for loading and composing the participants for execution.

To use the loader in the OSGi environment add ``org.nasdanika.exec`` dependency to the manifest file.

To use in the Maven environment search for the latest version on [Maven Central](https://search.maven.org/search?q=g:org.nasdanika.core%20AND%20a:exec) and copy/paste a dependency declaration to the ``pom.xml``.

Example as of the time of writing:

```xml
<dependency>
  <groupId>org.nasdanika.core</groupId>
  <artifactId>exec</artifactId>
  <version>2020.8.2</version>
</dependency>
```

To load objects from a YAML resource use code like this:

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
		
// Adapting the loaded object to SupplierFactory		
SupplierFactory<InputStream> supplierFactory = Loader.asSupplierFactory(iterator, null);

// Creating supplier from the factory
Supplier<InputStream> supplier = supplierFactory.create(context);

// Executing the object - in this case we are getting InputStream as a result.
InputStream result = supplier.execute(monitor);

// Or, to get a string, use Util to convert InputStream to String 
String result = Util.toString(context, supplier.execute(monitor));
```

