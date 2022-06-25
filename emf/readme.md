Nasdanika EMF module contains classes for working with Ecore models. 
These classes can be categorized as follows:

* General purpose - helper classes such as EMFUtil.
* Adapters and adapter factories - base classes for building (reflective) adapters and composite adapter factories in order to adapt Ecore models to required functionality. 
* Model execution - base classes for "wiring" Ecore models into the [Nasdanika Execution Model](../common/features/execution-model/index.html), i.e. making the models executable including [command line](../cli/index.html) execution.
* Persistence - classes for loading Ecore models from key-value data sources with special support of YAML. 

[TOC levels=6]

### Adapters

Adapters mechanism allows to adapt a model element to another type. 
Adapters are used by, say [HTML Flow](../../../html/modules/flow/index.html) to adapt [Core Flow](../flow/index.html) to ${javadoc/org.nasdanika.html.model.app.util.ActionProvider}s 
in order to generate an [action](../../../html/modules/models/modules/app/modules/model/Action.html) model and then generate a web site from the action model.

Adapters allow to separate concerns and to have multiple adapters and adapter factories for the same model.
For example, an action model can be transformed to a static web site or Eclipse help using different adapters.

Adapters are created by adapter factories which are registered with a resource set.

Below is a code snippet demonstrating the process of transforming a flow model to an action model by adapting the root model element to ``ActionProvider`` and then creating an ``Action`` instance:

```java
// Create a resource set and load a model
ResourceSet instanceModelsResourceSet = createResourceSet();
Resource instanceModelResource = instanceModelsResourceSet.getResource(URI.createURI("mymodel.xml"), true);

// Add an adapter factory
instanceModelsResourceSet.getAdapterFactories().add(new EngineeringActionProviderAdapterFactory(context)); {

// Resource set for a target (action) model		
ResourceSet actionModelsResourceSet = createResourceSet();
actionModelsResourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(org.eclipse.emf.ecore.resource.Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
org.eclipse.emf.ecore.resource.Resource actionModelResource = actionModelsResourceSet.createResource(URI.createURI("myactions.xml"));

// Getting the root object of the source model		
EObject instance = instanceModelResource.getContents().get(0);

// Adapting the root to ActionProvider and calling execute() to obtain an action
Action rootAction = EObjectAdaptable.adaptTo(instance, ActionProvider.class).execute(...);

// Adding the root action to a resource and then saving to a file
actionModelResource.getContents().add(rootAction);
actionModelResource.save(null);
```

#### Adapter factories

Below is a summary of Nasdanika EMF adapters:

* ${javadoc/org.nasdanika.emf.ComposeableAdapterFactory} - an interface for factories which can be composed into a ComposedAdapterFactory (see below).
* ${javadoc/org.nasdanika.emf.ComposedAdapterFactory} - a factory which delegates to its children. This factory is also composeable, i.e. it is possible to build a hierarchy of adapter factories.
* ${javadoc/org.nasdanika.emf.DelegatingAdapterFactory} - base class for adapter factories which create a delegate object and then create a [dynamic proxy](https://docs.oracle.com/javase/8/docs/technotes/guides/reflection/proxy.html) which implements ${javadoc/org.eclipse.emf.common.notify.Adapter} and all interfaces of the delegate. It allows to create adapters without having to implement ``Adapter`` or extend ${javadoc/org.eclipse.emf.common.notify.AdapterImpl}.
* ${javadoc/org.nasdanika.emf.BiFunctionAdapterFactory} - creates an adapter using a ${javadoc/java.util.function.BiFunction}, e.g. a constructor with two parameters. The first parameter is the notifier (target model element), the second is the factory.
* ${javadoc/org.nasdanika.emf.FunctionAdapterFactory} - creates an adapter using a ${javadoc/java.util.function.Function}, e.g. a single parameter constructor. The parameter is the notifier (target model element).
* ${javadoc/org.nasdanika.emf.InstanceAdapterFactory} - delegates to a single shared adapter instance for all notifiers (target model elements).
* ${javadoc/org.nasdanika.emf.SupplierAdapterFactory} - delegates to ${javadoc/java.util.function.Supplier}, which can be a no-argument constructor. In this case each notifier/element gets its own adapter instance. However, that instance is not aware of the notifier (target element). Such adapters can be used for collecting computation results. 


See [JavaDoc](apidocs/index.html) for more details.

### Model execution

Nasdanika EMF provides the following CLI command classes:

* ${javadoc/org.nasdanika.emf.ResourceSetCommand} - base class for commands which use a set of resources (models).
* ${javadoc/org.nasdanika.emf.ModelCommand} - loads a model from a URL or a file path, optionally validates and passes to a consumer for further processing.
* ${javadoc/org.nasdanika.emf.AdapterModelCommand} - base class for commands which execute models by adapting them to a specific adapter type and then creating a ${javadoc/org.nasdanika.common.CommandFactory} from that adapter.
* ${javadoc/org.nasdanika.emf.ConsumerModelCommand} - base class for commands which adapt models to ${javadoc/org.nasdanika.common.ConsumerFactory} and then combine input supplier with the consumer to execute the command.
* ${javadoc/org.nasdanika.emf.ExecuteModelCommand} - adapts the root model element to CommandFactory and executes it.
* ${javadoc/org.nasdanika.emf.FunctionModelCommand} - base class for commands which adapt models to ${javadoc/org.nasdanika.common.FunctionFactory} and then combine input supplier with the function and output consumer to execute the command.
* ${javadoc/org.nasdanika.emf.SupplierModelCommand} - base class for commands which adapt models to ${javadoc/org.nasdanika.common.SupplierFactory} and then combine the supplier with the output consumer to execute the command.

### Persistence

Nasdanika EMF persistence allows to load Ecore models from key-value sources such as:

* ``java.util.Function<String,Object>.apply(String key)``
* [JSON](https://en.wikipedia.org/wiki/JSON) 
* [YAML](https://en.wikipedia.org/wiki/YAML)

Loading from YAML and JSON can be used to implement ``<problem doman>-as-code``:

* DEefine models in YAML or JSON, 
* Edit in a text editor or in a web browser using version control system Web UI, 
* Store in a version control system,
* Keep track of model element definition source - file (URI), line, number, origin and commit (see [Marker](#marker) below),
* Diff and merge as code.

The persistence framework uses EAnnotation with ``urn:org.nasdanika`` source to customize loading.
This annotation is referred further as "Nasdanika annotation"

The persistence framework loads EObject structural features which are:

* Changeable 
* Not changeable with ``computed`` key of Nasdanika annotation set to ``true`` - in this case the feature value is loaded from the source, but not injected into the object - it is available via load tracking for custom handling - see below.
* Load key (see below) is not null. 

#### Load keys

##### Features

EObject features are loaded using load keys.
By default load keys are derived from feature names by converting camel case to kebab case.
E.g. ``firstName`` becomes ``first-name``.

Load key can be customized using the Nasdanika annotation:

* ``load-key`` detail key at the feature level. The value shall be a string.
* ``load-keys`` detail key at the class level. The value shall be a YAML map of feature names to load keys. This approach allows to override feature load key in subclasses.
* ``loadable`` detail key at the feature level - set it to ``false`` to suppress loading of features even if they are changeable. Please note that it is different from ``computed`` - ``computed`` forces loading of values of non-changeable features, and ``loadable`` suppresses loading of changeable features.
* ``feature-key`` detail key at the feature level. The value shall be a string. Feature key is used as a fallback value for a load key and also for computing containment path.
* ``feature-keys`` detail key at the class level. The value shall be a YAML map. This annotation can be defined on a subclass of the class defining the feature, which allows to override the default (kebab case) feature key.

The above process of resolving load keys is used by the default load key provider.
Load keys can be customized by passing a non-default load key provider to ${javadoc/org.nasdanika.emf.persistence.EObjectLoader} constructor.

##### Classes and packages

Default load keys for classes and packages are computed in the same way as for features by converting class or package name from came to kebab case.

Default behavior can be customized using the ``load-key`` Nasdanika annotation detail key with the load key as value.
It is also possible to suppress loading of a class or a package by setting ``loadable`` Nasdanika annotation detail key to ``false``.

As with features, it is possible to provide a custom key loader to ``EObjectLoader`` constructor to change the default
behavior if annotations are not enough. 
A custom key loader may fall-back/chain/delegate to the default key loader.

In the below example of loading of [Exec Text](../exec/modules/model/content/Text.html)

```yaml
content-text:
   description: Full text definition
   interpolate: false
   content: Hello ${token}.
```

* ``content`` - Package name converted to kebab case.
* ``text`` - Class name converted to kebab case.
* ``description``, ``interpolate``, ``content`` - object features.

#### Default feature

A feature can be defined as a default feature by setting ``default-feature`` Nasdanika annotation to ``true`` at the feature level or to the feature name at the class level.
Default features are used when object configuration value is not a map, e.g. a String.

For example, Exec ``Text`` defines ``content`` as its default feature.
It allows to load text from the following YAML specifications:

##### Full definition

```yaml
content-text:
   description: Full text definition
   interpolate: false
   content: Hello ${token}.
```

##### Short definition

Just content (default feature):

###### Single line

```yaml
content-text: World
```

###### Multi-line

```yaml
content-text: |+2
  Line 1.
  Line 2.
```

#### Loading references

By default configuration values of reference elements shall be single-entry maps with the type as a key and a value as configuration. 
The loading process instantiates an EObject by its key and then injects features from the value.

For example,

```yaml
  modules:
    - engineering-product:
        path: core
        name: Core
        owners: engineering://nasdanika/engineers/joe-doe
        experts: engineering://nasdanika/engineers/joe-doe
        action-prototype: "ncore.genmodel.xml#/"
        releases:
          "0.1":
            name: "0.1"
            increment: engineering://nasdanika/increments/2021/children/Q4            
          "1.0":
            name: "1.0"
```

* ``modules`` - is an interited reference of [Organization](../../../engineering/modules/model/Organization.html) (see All > References)
* ``engineering-product`` is the element type - ``engineering`` package, ``product`` type.
* ``path``, ``name``, ... are object features

In many cases reference values are either of the same type or their type can be derived from their configuration keys. 
In this case explicitly specifying the type is unnecessary and may lead to configuration errors.
For example, in the above code snippet ``releases`` reference can contain only instances of its reference type - [Release](../../../engineering/modules/model/Release.html).

To specify that the type of refernce elements shall be derived from the reference type or annotations instead of being supplied explicitly set ``homogenous`` Nasdanika annotation details key to ``true`` on the reference. 

In some situations the type of reference element can be derived from the type of its configuration - string, integer, map, list, boolean, date. In such cases add ``reference-type`` Nasdanika annotation key with a value containing a YAML map of configuration value types to element types.

For example:

```yaml
map: MapProperty
list: ListProperty
string: StringProperty
integer: IntegerProperty
```

Map values can be strings for types in the same EPackage or maps with the following keys:

* ``ns-uri`` - Namespace URI of the EPackage.
* ``name`` - EClass name.

In situations when it is necessary to override reference type in a subclass add ``reference-types`` Nasdanika annotation to the subclass. The value shall be a YAML map of reference names to element types.
Element types can be strings for the types in the same package or maps with ``ns-uri`` and ``name`` keys as explained above.

By default, if an element value of a homogenous reference is a string then it is treated as a relative URI of a resource from which the element shall be loaded. 
In this case that resource has to contain type definition.
For elements with a default feature this behavior can be modified by setting ``strict-containment`` Nasdanika annotation detail to ``true`` to treat the string value in configuration as the value of the default feature. In this case it is not possible to load reference elements from external resources.

##### Reference keys (EKeys)

References may specify attributes of their types as EKeys. 
References which specify EKeys can be loaded from maps. 
Currently only a single EKey is supported.
Support of multiple EKeys will be added when needed.

When a reference with EKeys is loaded from a map, map entry key is injected into the element object's EKey attributes. 
The map entry value is used to either set up element features or "value feature".

##### Value feature

When using EKeys you may want to use map entry value to set a specific feature of the reference element instead of using it to configure the element itself.
In this case set ``value-feature`` Nasdanika annotation on the reference to the name of the feature which shall be configured with the map entry value.

##### File sets

It is possible to load zero or more objects into a reference using ``fileset:`` URI schema followed by the file set specification. 

The specification can be a string, a YAML array, or a YAML map. 

In some cases if a patterns starts with ``*`` it is treated as a [YAML alias](https://yaml.org/spec/1.2.2/#alias-nodes) which would lead to a ScannerException being thrown. To solve it add ``./`` in front of ``*`` e.g. ``./*/engineering.yml``.

###### String

If it is a string, then it is treated as an [Ant path pattern](https://github.com/azagniotov/ant-style-path-matcher).

Example: ``fileset:issues/*.yml``

###### Array

If it is an array, then elements are treated as Ant path patterns.

Example: ``fileset:[issues/*.yml]``

###### Map

If it is a map, the following keys are supported:

* ``include`` - a string or an array of strings with Ant path patterns to include into the result. 
* ``exclude`` - a string or an array of strings with Ant path patterns to exclude from the matched include result.
* ``base`` - Base directory resolved relative to the context URI.

Examples;

Single line: ``fileset:{include:*.yml;exclude:done_*.yml;base:issues}``

Multi-line:

```
  issues:
    - |+2
      fileset:
        include: 
          - *.yml
          - *.yaml
        exclude: done_*.*
        base: issues
```

#### Mutually exclusive features

Features may have ``exclusive-with`` Nasdanika annotation details containing a space-separated list of feature keys which are exclusive with the annotated feature.
It is not necessary to declare exclusivity on both features, just on one of them.

If object configuration contains keys for mutually exclusive features, loading will fail.

#### Load tracker

During load a ${javadoc/org.nasdanika.common.persistence.LoadTracker} adapter is added to objects being loaded.
This adapter allows, for example, to compute derived values for features unless they were already loaded.
It also allows to access values of computed features - these values are stored in the adapter, but not injected into the target object because these features are not changeable.

An example of using a computed feature - the feature type is an object which is loaded from an external system, e.g. an issue tracker. 
Feature configuration value is a string, e.g. an issue ID. 
During load issue ID is stored in the load tracker adapter and then is used to contact the issue tracker system, retrieve issue details and create an issue object to be returned from the feature getter.

#### Marker

When objects are loaded from YAML information about source file line and number is injected into the objects as follows:

* An adapter of type ${javadoc/org.nasdanika.common.persistence.Marked} is added to the object.
* If the object extends [Marked](../ncore/Marked.html), then [Marker](../ncore/Marker.html) is added to the object.

It is possible to customize marker type by calling ``EObjectLoader.setMarkerFactory()``. 
For example, [GitMarker](../ncore/GitMarker.html) can be created with ${javadoc/org.nasdanika.emf.persistence.GitMarkerFactory}.

#### Examples

For examples see [Exec tests](https://github.com/Nasdanika/core/tree/master/exec.gen/src/test).
