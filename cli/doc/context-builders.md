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

### Developing context builders

index - reserved name.

In an enterprise      


examples of command line
 's ``ExecutionParticipant``. Implements org.nasdanika.cli.ContextBuilder
For example, ``JavadocContextBuilder`` loads package lists from Javadoc URL's provided in configurations and replaces fully qualified names of classes with links to Javadoc documentation. 
E.g. ``${{{javadoc/org.nasdankia.common.Context}}}`` with ${javadoc/org.nasdanika.common.Context}.
 
Context builders are Java classes which implement ``FunctionFactory<Context,Context>`` and are registered with xxx extension point.

Spec - members after fragments

Tests:

* Package: ${javadoc/org.nasdanika.common}
* Class: 
    * ${javadoc/org.nasdanika.common.Context}, 
    * ${javadoc/org.nasdanika.vinci.design.Services}, 
    * ${javadoc/java.util.Map}
* Field: ${javadoc/org.nasdanika.common.Context#BASE_URI_PROPERTY}
* Methods: 
    * ${javadoc/org.nasdanika.common.Context#fork()}
    * ${javadoc/org.nasdanika.common.Context#get(java.lang.Class)}
    
Ecoredoc tests:

* Package: ${ecore-doc/ncore}
* Class: 
    * ${ecore-doc/app/ActionBase}, 
    * ${ecore-doc/app/ActionBase Action Base}, 
* Attribute: 
    * ${ecore-doc/app/ActionBase.activator}
    * ${ecore-doc/app/ActionBase.activator Action Base activator}
* Reference: 
    * ${ecore-doc/app/ActionBase:content}
    * ${ecore-doc/app/ActionBase:content Action Base content}
* Operation: 
    * ${ecore-doc/app/BootstrapContainerApplicationBuilder#EOperation-createApplicationBuilderSupplier-978b17ea4dfe41ec4562d0ce7f4eaa16b83bc0a4e3250ba83665d93d4b799507}
    * ${ecore-doc/app/BootstrapContainerApplicationBuilder#EOperation-createApplicationBuilderSupplier-978b17ea4dfe41ec4562d0ce7f4eaa16b83bc0a4e3250ba83665d93d4b799507 createApplicationBuilderSupplier()}

TODO:

* Registration example
* Implementation example - configuration context to factory and command context to function.
* Command line example, config example
* Generate vinci model from registered context builders - doc resource attribute. Mount under Context builders action - generate the action and link from the doc model. ``vinci generate documentation context-builders``. 
* Tech doc - org context/dictionary/language - link - Javadoc, model doc, service catalogue, mainframe structures and - mega-wiki.  
Modify other documentation commands to move under documentation - ecore-doc and help to cli

Builders may have parameters in this case they have to implement BiConsumer. Builder parameters are passed to the builder by calling its <code>java.util.function.BiConsumer.accept(String,String)</code> method.
 
Addressing plugin symbolic name/builder id e.g. ``org.nasdanika.vinci.cli/javadoc-context-builder``

context-builder - individual c/b? or context-builders - mapping, config

Config:

- Map - id, config, mounts
- List of maps

# Headings

3+

# Next steps

* Implement context builder loading in the command - javadoc context builder in vinci.cli bundle.
* Test - javadoc context builder for documentation.

```yaml
mounts:
    javadoc:
        id: org.nasdanika.vinci.cli/javadoc-context-builder
        config:
            # Core
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.cli/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.common/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.eclipse/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.emf/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.emf.edit/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.emf.presentation/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.help/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.ncore/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.ncore.edit/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.sirius.tree/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.sirius.tree.edit/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.texttospeech/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.ui/apidocs/

            # HTML
            - https://nasdanika.org/builds/${release}/products/html/bundles/org.nasdanika.html/apidocs/
```