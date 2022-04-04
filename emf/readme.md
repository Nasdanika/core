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




### Persistence

General

Attributes

References - homogenous, selectors.

annotations

Load tracker

code snippets/examples




