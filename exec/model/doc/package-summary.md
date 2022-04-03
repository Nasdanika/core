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

To load and execute [resource components](resources/package-summary.html) which implement ${javadoc/org.nasdanika.common.ConsumerFactory}<${javadoc/org.nasdanika.common.resources.BinaryEntityContainer}> from a YAML resource use code like this:

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

#### Commands

TODO

### Solution instantiation

Solution instantiation is a form of transformation of one or more models into a "solution", e.g. a cloud application or a documentation site.
Code generation is an example of solution instantiation and may be a part of solution instantiation.

This section explains an approach which allows parallel evolution of the source models and the generated/instantiated solution with ability to re-instantiate a solution from the model preserving 
the changes made in the instantiated solution after it was instantiated.
For example, manual edits in source files.

Creating a solution instantiator requires effort and as such there is a break-even point between instantiating solutions manually and building an automated solution instantiator to instantiate them repeatedly.
In other words, solution instantiators shall be used to instantiate "sibling" solutions aiming to solve "commonly occuring problems", that is - instantiate [patterns](https://en.wikipedia.org/wiki/Software_design_pattern). 

#### Steps

##### Pattern

Define and publish a pattern. 
Pattern documentation shall specify "variation points" - what can be different between different instances. 
E.g. cache or database or a number of replicas. 

Patterns shall be defined by subject matter experts.
For example a cloud solution pattern shall be defined by people with a deep experience with cloud technologies, e.g. a cloud architecture group.
A pattern may be elicited from one or more one-off solutions with a significant overlap in functionality.

A published pattern has a value on its own without a solution instantiatior.
An instantiator may be created at a later point of time once the pattern has demonstrated its usefulness.

The pattern shall be treated as a product with releases and release numbers. 
It may be stored in a version control system.  

##### Reference implementation(s)

Build a reference implementation demonstrating that the pattern "holds water". 
The reference implementation doesn't have to externalize the variation points, but it shall document them.
There might be several reference implementations for a single pattern demonstrating different combinations of variation points.

A reference implementation shall also be built and maintained by subject matter experts, not necessarily the same people who created the pattern[^1], e.g. cloud developers. 

[^1]: Or necessarily not the same people to prove that the pattern can be instantiated based on the published documentation.

As well as a pattern a reference implementation shall be treated as a product with releases and release numbers which can include pattern release numbers.
There might be several releases of a reference implementation per pattern release.

Documentation for reference implementations may be "mounted" under the pattern documentation so it is easy to find.

##### Instantiation and Configuration models

Once a reference implementation is published it can be converted into two models.

###### Instantiation model

Instantiation model is created with Nasdanika Exec and reflects the constant part of the reference implementation which is parameterized by the configuration model.

Additional execution participants can be created as needed.
Such participants may leverage existing generation solutions, e.g. [Spring Initializr](https://start.spring.io/) - out of the box or customized to organization's needs.
 
Nasdanika Execution Model supports rollbacks, which can be important for instantiation of complex solutions where one of instantiation steps may fail - in this case the instantiator will clean up after itself.

###### Configuration model

The configuration model contains variation points. 
It may be quite involved with conditional logic and validations, e.g. lookups in internal registries.
It is also likely that different parts of the configuration model would have to be populated by different roles within the organization with different authorities.
In this case the model may leverage some kind of signing or authentication tokens/permits which can be validated at the instantiation time. 
Such permits may have an expiration date. 

Configuration models may be stored in different formats and edited in a variety of ways. 
One way to create a configuration model is to use Eclipse EMF and one way to load the model is from YAML using Nasdanika Core EMF.

If a configuration model is stored in YAML in Git one way to validate authority at the instantiation time is to check who's committed a particular configuration file or changed a particular line of code (using [blame](https://git-scm.com/docs/git-blame)). 
In this case, for example, if a developer modifies a configuration element which is supposed to be modified by an architect the instantiation will fail.

##### Instantiate

The instantiation process will:

* Load and validate the instantiation model.
* Load and validate the configuration model.
* Execute the instantiation flow as explained in the "Nasdanika Execution Model" mentioned above:
    * Diagnose
    * Execute
    * Commit or Rollback
    * Close
    
The instantiation process (or some of its parts) can be tested using the reference implementation(s) - given a specific input it shall produce the same resources as in the reference implementation.    

###### Example

* A web wizard is used to collect user input to create a new cloud application.
* The input collecting application creates a request YAML file and pushes it to a Git repository.
* An automated build job is triggered by the push. It inspects the commit message and starts the instantiation process passing the configuration YAML to it is as input. The process:
    * Creates Git repositories.
    * Generates code and pushes to the repositories.
    * Creates build jobs and triggers a build to the development environment.

##### Evolution

Once a solution is instantiated there are three parts which may evolve independently:

* The instantiation model - there might be a new release of the underlying pattern or reference implementation and the model may get updated.
* The configuration model, e.g. it might be decided to use a different caching solution.
* The instantiated solution - developers will modify the generated code to implement business functionality.

The solution instantiator merges the changes in the three "evolution streams" as explained below.

It re-executes the instantiation process.
For every resource (file, repository, database) the instantiation model would specify a [Reconcile Action](resources/ReconcileAction.html) - what to do if the resource already exists.
In case of ``Merge`` the instantiator would do the following:

* Check if the resource was modified since the last instantiation. For version-controlled files one way to do it is to use jGit and see who last committed the file. If the committer ID is the same ID which is used for instantiation that would mean that the file was not touched and can be overwritten.
* If the resource was modified use a merger applicable for the resource format. For Java resources there is JMerge which detects manual changes using ``@generated`` JavaDoc tags - it overwrites only classes and members with such tag present. Other file resources can be merged using a 3-way patch in a version-controlled environment, which is[^2]: 
    * Find the previous generated revision - go through the commit history, find a commit done by the generator ID. Another option is use a comment convention, e.g. look for something like ``Generated by <Generator Maven coordinates>``.
    * Do a diff between the previous and the newly generated code and create a patch. There are Java libraries to do diff/patch for [text files](https://github.com/google/diff-match-patch) and for formats such as [JSON](https://github.com/java-json-tools/json-patch/blob/master/src/main/java/com/github/fge/jsonpatch/diff/JsonDiff.java) and [XML](https://github.com/dnault/xml-patch).
    * If there is no difference do nothing - keep the current revision as it is.  
    * Otherwise apply the patch to the current revision.

[^2]: This is one option. Another option is to diff/patch between the previously generated and the current revision and then apply the patch to the newly generated content.

The re-instantiation process can be performed automatically on Git push, similarly to how the first instantiation happens.
In fact, it will be the same process with no pre-existing resources for the first instantiation.

One variation of a reconcile action for Git repositories may be "create a branch" - the instantiator would not commit changes to the same branch, but would create another branch. 
Then a build may be triggered off that branch and if it is successful and all tests pass then the branch maybe merged into the original branch or a pull request may be created.
If the build fails, then the development team would be notified to review and fix problems caused by a merge.

#### Value proposition

* Developers are inherently better at writing code than documentation - they wouldn't be developers but rather technical writers otherwise. With a well-established solution instantiation practice it might be easier and faster for developers to create solution instantiators than to create a detailed documentation for reference implementations.
* Instantiators are code and can be tested faster and cheaper than documentation.
* Solution instantiators shall produce the same result when given the same input. It is not always true for people.
* Extensibility - there might be "base" instantiators which define extension points to allow customization. It may be leveraged in a large organization where a central team produces such "base" instantiators and then business line/department teams customize them to their needs. The same approach can be applied to configuration models using template/prototype chains - a config model can define template/prototype or multiple templates/prototypes similar to how docker images define base image using ``FROM``.  
