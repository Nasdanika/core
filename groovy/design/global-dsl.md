I want to make several modifications in DSL oulined below - provide your assessment and generate a design document explaining how to implement the changes.

# Move the DSL operating on the resource set level

Move groovy-dsl\src\main\groovy\org\nasdanika\models\productmanagement\dsl\groovy\ReflectiveBuilder.groovy and groovy-dsl\src\main\groovy\org\nasdanika\models\productmanagement\dsl\groovy\DslContext.groovy
to another module where I have shared Groovy functionality.
This way one DSL will be applicable to all packages in a resource set and to multi-package models.

As a result, DslContext will receive a list of EPackages to use in sequence to resolve types and fall back to all packages registered with the resource set.
It may also receive a name resolver function - instead of populating byName in the constructor name resolution will be on-demand.
This would allow to externalize name resolution to Java client code.

# Type resolution

Add support of type resolution as explained in https://docs.nasdanika.org/core/mapping/index.html#type - or similar design.

* Simple name, e.g. `Capability` - look up in order in EPackages provided to DslContext and then 
* Qualified name with full or partial package path - `c4.Component`, `architecture.c4.Component` - the goal is to disambiguate.
* Hashed name - standard Ecore mechanism. E.g. `ecore://nasdanika.org/models/family#//Man`

Builders will provide context to the type resolver - this would allow to have a context EPackage. 
E.g. if Persona is being built then the product management EPackage will be used first to look for types, then its enclosing scope (e.g. PersonaDomain or ProductModel).

# Global objects

I want to add support of global objects with global URIs.
NcoreResourceSet will override getEObject() and will delegate to adapters to resolve URI to EObject and then fall-back to the default behavior.
Adapters would return Optional. If it is null - keep searching, if it is not null but empty - stop searching, return null.

One of adapters would be maintaining a map of URI -> EObject. Or it can be done by NcoreResourceSet.
The DSL shall have global('<URI>', eObject) function to register global objects.

# URI capability

For global objects to be valuable resources containing them will be pre-loaded using the capability framework.
There will be a ResourceURIRequirement record containing a predicate. The capability provider will return a URI or a collection of URI's if the predicate matches.
One flavor - NS URI to use in the predicate.

Example: 

The client code builds a product management model from all available modules.
It creates a requirement the product management package NS UI.
Capability providers return URIs of product management resources. E.g. `module://org.nasdanika.core.capabilities/capabilities.pm.groovy` 
The client code loads resources using the URIs returned by the capability providers.
The load process registers global objects with the resource set.

# Proxies

The resolving mechanism shall support proxies because global objects can't be resolved at load time.
Need your advice on how to implement it. I see the following options:

## Proxy attribute in object definiton

E.g.

```groovy
actor {
    proxy 'people:pavel'
    name 'Pavel'
}
```

This way the builder will recognize "proxy" attribute and set proxy URI. Then the rest will be taken care of by the EMF machinery. 
I think this is the cleanest way and it would work even if resolving fails - the proxy may have additional attriutes.

In this case a class may not have an attribute named `proxy`. 
To address this the proxy attribute name might be `^proxy` as in Xcore or `_proxy`.
Or, maybe, use a function:

* proxy `<URI>` if there is no proxy attribute
* proxy(`<URI>`) if there is

I'm not sure if this is supported by Groovy - please advise.




