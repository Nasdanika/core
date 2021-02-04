Context builders can be used customize ${javadoc/org.nasdanika.common.Context} of [Nasdanika Command Line Interface](${base-uri}reference/cli/index.html) (CLI) commands which extend ${javadoc/org.nasdanika.cli.ContextCommand}.
Context builders configurations are defined in YAML resources and URL's of these resources are provided to commands via ``-B``/``--context-builders`` option.

One usage scenario for context builders is to aid in writing documentation by simplifying referencing of external resources. 
For example the [Javadoc Context Builder](org.nasdanika.vinci.cli/javadoc-context-builder.html) makes it easy to reference Java API documentation so Java technical documentation can be written "in terms" of Java classes
and readers can easily navigate to the API documentation for additional information.
In a similar fashion the [Ecore Context Builder](org.nasdanika.vinci.cli/ecore-doc-context-builder.html) allows to write technical documentation in terms of model elements, thus providing a "[ubiquitous language](https://martinfowler.com/bliki/UbiquitousLanguage.html)" defined as EMF Ecore models.
Such a language can be extended by providing additional context builders. For example, a builder for linking to issues in an organization's issue tracker or services in a service registry.

### Using context builders

A list of available context builders can be found at the bottom of this page and each context builder has a documentation page explaining how to configure and use it.

In order to make one or more context builders available to a command a YAML configuration file shall be created and passed to the command via ``-B``/``--context-builders`` option.

The content of the YAML file is a nested map with the following elements, all of them optional:

* ``id`` - String, unique ID of a context builders - ``<bundle name>/<builder id>``, e.g. ``org.nasdanika.vinci.cli/javadoc-context-builder`` for the [Javadoc Context Builder](org.nasdanika.vinci.cli/javadoc-context-builder.html). If ``id`` is not specified then only ``mounts`` are processed.
* ``config`` - Context builder configuration. It is context builder specific and can be any structure, it is passed to the context as-is. Consult documentation of context builders for details. If ``id`` key is not specified then ``config`` is ignored.
* ``mounts`` - A map of mount points/prefixes (string) to a map with ``id``, ``config``, and ``mounts``. There can be multiple levels of nesting if needed, there is no limit.  

The below YAML defines only mounts at the root and no nested mounts. The first context builder takes a list as its configuration and the second takes a map.

#### Examples

##### YAML file

```yaml
mounts:
    javadoc/:
        id: org.nasdanika.vinci.cli/javadoc-context-builder
        config:
            # Third-party
            - https://docs.oracle.com/javase/8/docs/api/
            - https://download.eclipse.org/modeling/emf/emf/javadoc/2.9.0/
            - https://picocli.info/apidocs/

            # Core
            - ../products/core/bundles/org.nasdanika.cli/apidocs/

    ecore-doc/:
        id: org.nasdanika.vinci.cli/ecore-doc-context-builder
        config:
            ncore:
                base: reference/model-doc/
                ns-uri: urn:org.nasdanika.ncore
            vinci-html:
                base: reference/model-doc/
                ns-uri: urn:org.nasdanika.vinci.html
```

##### Command line

The below example demonstrates how to pass context builders to [vinci generate application](${base-uri}reference/cli/nsd/vinci/generate/application.html) command.

```console
nsd vinci generate application 
-p progress.txt 
-c release=develop 
-b https://www.nasdanika.org/builds/develop/doc/ 
-B file:C:/git/release/tool-suite/doc/models/context-builders.yml 
-o doc 
-f C:\Users\Pavel\git\release\tool-suite\doc\models\documentation.vinci
``` 

``-B file:C:/git/release/tool-suite/doc/models/context-builders.yml`` specifies location of the context builders configuration. 

``-B``/``--context-builders`` can be provided zero or more times. 

This is an example of using context builders option from Maven exec command:

```xml
<exec
	dir="${project.build.directory}\cli\eclipse"
	executable="${project.build.directory}\cli\eclipse\nsd.exe" 
	failonerror="true">

	<arg value="-data"/>
	<arg value="nsd-cli-workspace"/>
	<arg value="vinci"/>
	<arg value="generate"/>
	<arg value="documentation"/>
	<arg value="context-builders"/>
	<arg value="-o"/>
	<arg value="${project.basedir}\generated\context-builders.vinci"/>
</exec>
```

### Developing context builders

Development of new context builders includes the following steps:

* Implementation of ${javadoc/org.nasdanika.cli.ContextBuilder} interface.
* Documenting the new implementation in markdown.  
* Registration of a ``context-builder`` extension for ``org.nasdanika.cli.ext.cli`` extension point.
* Generation of context builders documentation model to include into the documentation system.

#### Implementation

The below code snippet shows an structure of a context builder.
You can also take a look at linked sources of context builder implementations for concrete implementation examples.  

```java
package ...;

import org.eclipse.emf.common.util.URI;

import org.nasdanika.cli.ContextBuilder;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Status;
import org.nasdanika.common.Util;

/**
 * ... API documentation here ...
 */
public class MyContextBuilder implements ContextBuilder {
	
	@Override
	public Context build(Object config, Context context, ProgressMonitor progressMonitor) {
		// Logic which needs to be executed once, e.g. loading and caching of information from remote systems.
		
		// Context URI may be available to context builders in some commands for resolving relative URL's.
		String location = ...  		
		URI contextURI = context.get(URI.class);
		if (contextURI != null) {
			location = URI.createURI(location).resolve(contextURI).toString();
		}
		
		return new Context() {
		
			// Helper methods here			

			@Override
			public Object get(String key) {
				// Logic property resolving key to value
				
				...
				
				return ... 
			}

			@Override
			public <T> T get(Class<T> type) {
				// Root (not mounted) context builders can also provide services
				return null;
			}
			
		};
	}

}
```   

#### Documentation

Create a folder to store documentation, e.g. ``doc``. Add the folder to the ``build.properties`` ``bin.includes``.
Then create a markdown file and document the new builder in markdown. 

[Documenation examples](https://github.com/Nasdanika/vinci/tree/master/cli/doc).

#### Registration

Available context builders are discovered using [Eclipse Extension Points and Extensions](https://www.vogella.com/tutorials/EclipseExtensionPoint/article.html) mechanism.

The below snippet shows registration of two context builders:

```xml
<plugin>
   <extension point="org.nasdanika.cli.ext.cli">
      <context-builder
            class="org.nasdanika.vinci.cli.JavadocContextBuilder"
            description="Resolves fully qualified class names to links to class Javadoc."
            documentation="doc/javadoc-doc-context-builder.md"
            id="javadoc-context-builder"></context-builder>
      <context-builder
            class="org.nasdanika.vinci.cli.EcoreDocContextBuilder"
            description="Resolves EPackage alias/EClass name to links to Ecore model documentation."
            documentation="doc/ecore-doc-context-builder.md"
            id="ecore-doc-context-builder"></context-builder>
   </extension>
</plugin>
```

Where:

* ``class`` - context builder implementation class.
* ``description`` - an optional description which is shown in the documentation list of contents.
* ``documentation`` - path to the documentation resource within the contributing plug-in.
* ``id`` - context builder id for addressing context builders in the context builders configuration YAML resource. It must be unique within the contributing plug-in. Do not use ``index`` as context builder id - while the context builder will work, its documentation will overwrite the documentation page of the declaring plug-in. 

#### Generate documentation model

Use [Nasdanika CLI](${base-uri}reference/cli/index.html) [vinci generate documentation context-builders](${base-uri}reference/cli/nsd/vinci/generate/documentation/context-builders.html) command to generate documentation for registered context builders. 
Then reference the generated documentation from the product documentation model. 
See the sources of [Nasdanika tool suite doc plug-in](https://github.com/Nasdanika/release/tree/master/tool-suite/doc) for a concrete example.
``pom-generate-doc.xml`` contains generation commands. The generated content is references from ``models/documentation.vinci`` model. 
