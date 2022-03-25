Nasdanika Core Exec model classes can be used to create models for generation of resources such as files and their content.
Exec models can be used on their own or in other models.
For example, [markdown](content/Markdown.html) can be used as a documentation source for [flow](../../../flow/index.html) [package elements](../../../flow/PackageElement.html).

### Maven dependency

To use the Exec model and the generation adapters in the Maven environment search for the latest version on [Maven Central](https://mvnrepository.maven.org/artifact/org.nasdanika.core/exec-gen) and copy/paste a dependency declaration to the ``pom.xml``.
Example as of the time of writing:

```xml
<dependency>
  <groupId>org.nasdanika.core</groupId>
  <artifactId>exec-gen</artifactId>
  <version>2022.2.0</version>
</dependency>
```

### Java code

#### Suppliers

To load and execute [content components](content/package-summary.html) which implement ${javadoc/org.nasdanika.common.SupplierFactory}<InputStream> from a YAML resource use code like this:

```java
import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.util.Objects;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.junit.Test;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.DiagnosticException;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.gen.ExecGenYamlSupplier;
import org.nasdanika.exec.gen.tests.TestBase;

...

try (ProgressMonitor progressMonitor = new PrintStreamProgressMonitor()) {
	Context context = Context.singleton("token", "World"); // Context for token expansion
	URI resourceURI = URI.createURI(getClass().getResource("text/text.yml").toString()); // URI to load YAML resource from		
	try {
		// Diagnostic consumer to pass to Util.call
		Consumer<Diagnostic> diagnosticConsumer = diagnostic -> {
			assertThat(diagnostic.getStatus()).isEqualTo(Status.SUCCESS);
		};

		// Loading EObject with Util.call() which goes through the ExecutionParticipant lifecycle - diagnose(), execute(), commit()/rollback(), close()
		EObject eObject = Objects.requireNonNull(Util.call(new ExecGenYamlSupplier(resourceURI, context), progressMonitor, diagnosticConsumer), "Loaded null from " + resourceURI);

		// Adapting loaded EObject to SupplierFactory
		SupplierFactory<InputStream> supplierFactory = Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(eObject, InputStream.class), "Cannot adapt to SupplierFactory");
		
		// Calling the supplier factory to get InputStream
		InputStream in = Util.call(supplierFactory.create(context), progressMonitor, diagnosticConsumer);
		
		// Assertions
		assertThat(in).isNotNull();
		assertThat(DefaultConverter.INSTANCE.toString(in)).isEqualTo("Hello World.");
	} catch (DiagnosticException e) {
		// Dumping diagnostic in case of DiagnosticException which is thrown if diagnostic status is FAIL
		System.err.println("******************************");
		System.err.println("*      Diagnostic failed     *");
		System.err.println("******************************");
		e.getDiagnostic().dump(System.err, 4, Status.FAIL);
		throw e;
	}		
}		
```

#### Consumers

TODO

To load and execute [resource components](resource/package-summary.html) which implement ${javadoc/org.nasdanika.common.ConsumerFactory}<${javadoc/org.nasdanika.common.resources.BinaryEntityContainer}> from a YAML resource use code like this:

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

#### Commands?

### Solution instantiation

Using exec for solution instantiation (code generation). Three lines of evolution - exec model, configuration, generated solution.
Diagnostic, rollback/commit. Example - cloud application. Config commit hooks, jenkins - look up how the job was set up. 
Merging - Java (TODO), patching (3-way, also jGit to retrieve previous) , "touch detection" - jGit. 
Robo-coder - branch, build the branch, pull request or auto-merge if successful.
Exec change - instantiate-pom.xml or metadata/pom.xml, metadata/config.yml.