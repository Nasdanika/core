# Global DSL — Design & Assessment

Companion to [global-dsl.md](global-dsl.md) (requirements) and
[groovy-kotlin-dsl-design.md](groovy-kotlin-dsl-design.md) (the reflective-builder design this
extends). It assesses the five changes requested in `global-dsl.md` and lays out how to implement
them.

## 0. Where things stand today

The reflective DSL is two Groovy classes in the product-management repo:

- [DslContext.groovy](../groovy-dsl/src/main/groovy/org/nasdanika/models/productmanagement/dsl/groovy/DslContext.groovy)
  — bound to **one** `EPackage`, eagerly indexes every concrete `EClass` by decapitalised name in
  `byName`, owns the id index, deferred resolution, the resource-set-based `resolve`, and the
  reference-wrapper policy.
- [ReflectiveBuilder.groovy](../groovy-dsl/src/main/groovy/org/nasdanika/models/productmanagement/dsl/groovy/ReflectiveBuilder.groovy)
  — wraps one `EObject`, drives everything off `eClass().getEAllStructuralFeatures()`.

It is invoked from
[GroovyToProductManagementResourceContentsHandler](../handlers/src/main/java/org/nasdanika/models/productmanagement/handlers/groovy/GroovyToProductManagementResourceContentsHandler.java):
`new DslContext(ProductmanagementPackage.eINSTANCE, resource)`, `installInto(bindings)`, `eval`,
`resolveDeferred`, `normalize`.

The single-package assumption shows up in five places, and **every one of them must be touched** by
this work: the `DslContext(EPackage, Resource)` constructor, the eager `byName` map, `create()`
(uses `ePackage.EFactoryInstance`), `toEClass()`, `referenceWrapperFor()` (scans
`ePackage.EClassifiers`), and `installInto()`.

Cross-repo dependencies (`NcoreResourceSet`, the capability framework, the "shared Groovy" module)
live **outside this repo**; this document specifies them at the API boundary and flags the work that
lands elsewhere.

---

## 1. Move the DSL to the shared Groovy module

### Assessment

Sound and overdue — nothing in either class is product-management-specific except the constructor
argument. The move is mostly mechanical (package rename + JPMS/Maven wiring); the *design* work is
the generalisation from one `EPackage` to many, which the type-resolution change (§2) needs anyway.
Do them together.

### Target

A shared module — call it `org.nasdanika.models.commons.dsl.groovy` (or wherever the shared Groovy
functionality already lives). Same gmavenplus + `--patch-module` joint-compilation setup the current
`groovy-dsl` module uses (see [groovy-dsl/pom.xml](../groovy-dsl/pom.xml)); only `model` →
`org.nasdanika.ncore` (or equivalent core) changes in the dependency list, since the shared module
must not depend on the product-management model.

`groovy-dsl` keeps `module-info`/Maven coordinates but its two source files move out; it now just
`requires transitive` the shared module and the PM model. The PM handler changes only in how it
constructs the context (§1.2).

### 1.1 `DslContext` constructor and fields

Replace the single `EPackage ePackage` field with an ordered list, and make name→type resolution
lazy and pluggable:

```groovy
class DslContext {
    final List<EPackage> ePackages          // search order for simple names
    final Resource resource
    final ResourceSet resourceSet
    final URI baseURI
    /** Optional: externalised name resolution. (simpleName, contextEClass) -> EClassifier or null. */
    private final BiFunction<String, EClass, EClassifier> nameResolver

    DslContext(List<EPackage> ePackages, Resource resource,
               BiFunction<String, EClass, EClassifier> nameResolver = null) { ... }
}
```

- **No more eager `byName`.** It assumes one package and pre-decides the decapitalised-name map.
  Replace with on-demand resolution (§2) backed by a `Map<String, EClass>` *cache*, not a
  pre-population. The cache key must be scoped by context (see §2) or kept conservative.
- **`create(EClass)`** must use the type's own package: `type.EPackage.EFactoryInstance.create(type)`
  — not a single `ePackage`. This is the one-line fix that makes multi-package models actually
  instantiate.
- **`referenceWrapperFor` / `targetFeature`** currently scan `ePackage.EClassifiers`. Scan the
  **feature type's** package (`featureType.EPackage.EClassifiers`) instead — the wrapper is always
  defined alongside the reference type in the same metamodel. This is both correct and faster than
  scanning all packages.
- **`installInto`** must enumerate concrete classes across all `ePackages`. Name collisions across
  packages are now possible (e.g. two packages with `Component`); see §2.4 for the policy.
- **`resolveDeferred`, `index`, `byId`, `roots`, `defer`** are package-agnostic already — they move
  unchanged.

### 1.2 The `nameResolver` hook

The `BiFunction<String, EClass, EClassifier>` lets Java client code own name resolution (e.g. a
registry the host app maintains, or a project-wide symbol table). `DslContext`'s own resolver (§2)
becomes the **fallback** when the function returns `null`. Keeping it a plain `BiFunction` (not a
Nasdanika interface) keeps the shared module's API surface minimal and testable.

The PM handler keeps working with a one-line change:

```java
new DslContext(List.of(ProductmanagementPackage.eINSTANCE), resource);  // nameResolver = null
```

---

## 2. Type resolution

### Assessment

This is the substantive change and the right model — it mirrors the Nasdanika core mapping
type-resolution contract (simple / qualified / hashed). The only real design decisions are (a) how
"context" is threaded from the builder, and (b) the collision policy for simple names across
packages. Both are answered below.

### 2.1 The three forms

A single entry point on `DslContext`:

```groovy
EClassifier resolveType(String token, EClass context)
```

1. **Hashed / URI form** — `token` contains `#` (or a scheme like `ecore:`):
   `ecore://nasdanika.org/models/family#//Man`. Resolve via
   `resourceSet.getEObject(URI.createURI(token), true)`, falling back to
   `EPackage.Registry.INSTANCE`. Detect this case first (cheapest test, unambiguous).

2. **Qualified / partial-qualified form** — `token` contains `.`: `c4.Component`,
   `architecture.c4.Component`. Split on `.`; the last segment is the classifier name, the leading
   segments are a package path. A package *matches* when its qualified name (built by walking
   `eSuperPackage`, or its `nsPrefix` dotted path) **ends with** the given leading segments. Search
   packages in context order (§2.2) and collect matches; **exactly one** match resolves, zero throws
   "unknown type", more than one throws "ambiguous — qualify further".

3. **Simple form** — `Capability`. Search, in context order, each package's `EClassifier`s by name
   (case-insensitive on first char to absorb the decapitalised DSL spelling). First hit wins
   *within* the ordered search; an exact-name clash inside the same package never happens (Ecore
   forbids it), and across packages the **order** is the disambiguator (§2.4).

The `nameResolver` (§1.2), if present, is consulted before all three.

### 2.2 Context from the builder (the key mechanism)

`ReflectiveBuilder` already knows its `eClass`, and during an inline build it creates child builders
(`new ReflectiveBuilder(ctx, child)`). Give the builder a parent link so it can produce a **context
chain**:

```groovy
ReflectiveBuilder(DslContext ctx, EObject element, ReflectiveBuilder parent = null)
```

The search order for a type reference inside a builder becomes:

1. the **context EClass's own package** (`element.eClass().EPackage`),
2. each enclosing builder's package, walking `parent` up to the root,
3. the `DslContext.ePackages` list (the global default order),
4. all packages registered with the `resourceSet` (`resourceSet.packageRegistry`) / `EPackage.Registry.INSTANCE`.

This gives exactly the requested behaviour: building a `Persona`, the product-management package is
tried first, then the enclosing `PersonaDomain` / `ProductModel` scope, then the configured defaults,
then everything registered. Concretely, `resolveConcreteType` and `createChildAutoRouted` /
`methodMissing` pass `this` (or `this.eClass`) into `ctx.resolveType(token, contextEClass)` instead
of calling the old `ctx.classByName` / `ctx.toEClass`.

De-duplicate the assembled package list (a package can appear in several tiers) preserving order.

### 2.3 Caching

Cache on `(token, contextEClass)` since the same token can resolve differently per context. A plain
`Map<String, EClass>` keyed by `contextEClass.name + '|' + token` is enough; clear nothing
(metamodels are immutable at author time). The current `NONE`-sentinel trick (cache misses too) is
worth keeping.

### 2.4 Simple-name collision policy across packages

With one package this never arose. Now it can. Policy:

- **Simple names**: resolution is **order-sensitive and silent** — first package in the context
  chain that has the name wins. This is what makes `Capability` "just work" without qualification.
- **Root entry points** (`installInto`): a *binding* name can only point at one class. If two
  packages contribute the same decapitalised entry-point name, register the **first** (by `ePackages`
  order) and require the qualified form (`pkg.Type { }` via the feature/type token) for the other.
  Log a debug note rather than failing the whole script.

---

## 3. Global objects (resource-set resolution)

### Assessment

The three-state `Optional` protocol is the right call — it cleanly distinguishes "I don't handle
this URI" from "I authoritatively say it doesn't exist." This lands in `NcoreResourceSet` (core
repo), not here; specified at the API boundary below.

### 3.1 `NcoreResourceSet.getEObject` override

```java
@Override
public EObject getEObject(URI uri, boolean loadOnDemand) {
    for (URIResolver resolver : getURIResolvers()) {     // adapters, in order
        Optional<EObject> r = resolver.resolve(uri, loadOnDemand);
        if (r == null) continue;        // not handled — keep searching
        return r.orElse(null);          // handled: present -> object, empty -> null (stop)
    }
    return super.getEObject(uri, loadOnDemand);           // default EMF behaviour
}
```

Where a resolver is:

```java
@FunctionalInterface
interface URIResolver {
    /** null = not mine; Optional.empty() = mine, definitively absent; Optional.of = found. */
    Optional<EObject> resolve(URI uri, boolean loadOnDemand);
}
```

Resolvers can be registered explicitly on the resource set or discovered via adapters on the
resource set's `eAdapters()` that implement `URIResolver`. Prefer an explicit ordered list on
`NcoreResourceSet` (predictable order; adapters are unordered) with an adapter bridge for
extensibility.

### 3.2 The global-object resolver

One built-in resolver maintains `Map<URI, EObject> globals`:

```java
Optional<EObject> resolve(URI uri, boolean loadOnDemand) {
    EObject o = globals.get(uri);
    return o == null ? null : Optional.of(o);   // miss -> not mine (fall through), not "absent"
}
```

Note it returns `null` (not `Optional.empty()`) on a miss, so a URI it doesn't know falls through to
real loading. `Optional.empty()` is reserved for a resolver that *owns* a URI space and can assert
absence.

Keep the map **on `NcoreResourceSet`** (with a registration method) rather than in a free-floating
adapter — registration is then a plain method call and the map's lifecycle matches the resource set.

### 3.3 DSL `global(uri, eObject)`

A root binding installed by `installInto`:

```groovy
bindings.global = { String uri, EObject o ->
    ((NcoreResourceSet) resourceSet).registerGlobal(URI.createURI(uri), o); o
}
```

Guard the cast (the shared module shouldn't hard-require core's `NcoreResourceSet` type at compile
time if that creates a cycle — resolve via a small interface, e.g. `GlobalObjectRegistry`, that
`NcoreResourceSet` implements, looked up through `resourceSet` or an adapter).

---

## 4. URI capability

### Assessment

Straightforward application of the existing `ServiceCapabilityFactory` pattern already used by the
handler factories in this repo. The one decision is the requirement shape; the record-with-predicate
is fine, with a convenience flavour for the common NS-URI case.

### 4.1 Requirement

```java
public record ResourceURIRequirement(Predicate<URI> predicate) {
    /** NS-URI flavour: match providers that publish resources for a given package namespace. */
    public static ResourceURIRequirement forNsURI(String nsURI) {
        return new ResourceURIRequirement(uri -> /* provider-side match on nsURI */ true);
    }
}
```

The predicate is evaluated **provider-side**: each capability provider knows the URIs it can offer
and the namespace they serve, and returns those whose namespace the predicate accepts. (If the
predicate were over the candidate URI itself, the provider would have to enumerate first; passing the
NS-URI flavour lets the provider answer without enumerating.) Keep both readings open by documenting
that `forNsURI` carries the namespace and providers match on it.

### 4.2 Provider and capability type

Capability service type is `URI` (or `Iterable<URI>` for the multi flavour). A
`ServiceCapabilityFactory<ResourceURIRequirement, URI>` per contributing module returns
`module://<bundle>/<resource>` URIs (e.g.
`module://org.nasdanika.core.capabilities/capabilities.pm.groovy`) when the requirement's namespace
matches what the module provides.

### 4.3 Client flow

```java
ResourceURIRequirement req = ResourceURIRequirement.forNsURI(ProductmanagementPackage.eNS_URI);
Iterable<CapabilityProvider<URI>> providers = capabilityLoader.load(
        ServiceCapabilityFactory.createRequirement(URI.class, null, req), monitor);
for (URI uri : flatten(providers)) {
    Resource r = resourceSet.getResource(uri, true);   // load
    // load registers globals: see §4.4
}
```

### 4.4 Where global registration happens

Two viable hooks; recommend the **resource-contents-handler** path since the load already runs
through it:

- The `.pm`/`.groovy` (or a dedicated global) contents handler calls `global(...)` /
  `registerGlobal(...)` for objects that declare a global URI during `load`. The DSL's `global(...)`
  function (§3.3) is exactly this when authoring in Groovy.
- Alternatively a post-load pass walks new resources for objects carrying a global-URI annotation and
  registers them. More magic, less explicit — prefer the handler path.

---

## 5. Proxies

### Assessment & the Groovy answer you asked for

**The `proxy '<uri>'` vs `proxy('<uri>')` distinction does not exist in Groovy.** Command-chain
syntax (`proxy 'x'`) is pure sugar for the method call (`proxy('x')`) — both compile to the identical
`invokeMethod("proxy", ["x"])`. There is no way for the receiver to tell whether the author wrote
parentheses. So that particular disambiguation idea can't be implemented, and you don't need it.

Also: **`^proxy` is not a legal Groovy identifier** (`^` is the XOR operator), so the Xcore-style
spelling won't compile as a method/property name. Legal "reserved" spellings are `_proxy` or
`$proxy`.

The cleaner mechanism, given how dispatch already works:

**Define `proxy` as a real method on `ReflectiveBuilder`.** Real methods take priority over
`methodMissing`, so `proxy 'people:pavel'` is captured directly and never reaches feature/type
dispatch:

```groovy
EObject proxy(String uri) {
    ((InternalEObject) element).eSetProxyURI(URI.createURI(uri))
    element
}
```

The object was already instantiated via the factory, so it has a concrete `eClass` and can still hold
the additional attributes the author sets (`name 'Pavel'`) — exactly the "works even if resolving
fails" property you want. EMF then resolves the proxy lazily on first navigation through
`resourceSet.getEObject`, which now consults the global resolvers (§3).

**Collision handling.** A real `proxy` method shadows any `EStructuralFeature` literally named
`proxy`. That clash is rare; when it happens the feature is still reachable via the assignment form
`proxy = value` (which routes through `propertyMissing`/`setProperty`, not the method) or
`element.eSet(...)`. Document this. If you'd rather never shadow, use the reserved-name route instead:
have `methodMissing` (and `propertyMissing`) intercept a leading-sigil token `_proxy` / `$proxy`
*before* feature lookup, and set the proxy URI there. Recommendation: **real `proxy` method**, with
`_proxy` documented as the escape hatch for the (rare) model that needs a feature named `proxy`.

### 5.1 Resolution caveat to bake in

A standard EMF proxy is replaced by its resolved target on first access (`eIsProxy()` →
`eResolveProxy`), which means attributes set on the *proxy stand-in* are not merged into the resolved
target — they exist only while unresolved. That matches the stated intent ("usable even if resolving
fails"), but document it so authors don't expect proxy-side attributes to survive a successful
resolve. If merge-on-resolve is ever wanted, that's a custom resolver concern, not the builder's.

---

## 6. Sequencing

1. **§1 move + multi-package generalisation** (`List<EPackage>`, `create()` via `type.EPackage`,
   `referenceWrapperFor` via `featureType.EPackage`, builder `parent` link). Ship with PM handler
   passing `List.of(...)`; existing tests must stay green. This is the foundation everything else
   sits on.
2. **§2 type resolution** (`resolveType`, context chain, qualified/hashed forms, collision policy).
   Pure addition over (1).
3. **§5 proxies** — small, independent, testable in the shared module alone (`proxy` method +
   `eSetProxyURI`).
4. **§3 global objects** — lands in `NcoreResourceSet` (core repo); DSL `global(...)` binding here.
5. **§4 URI capability** — requirement record + providers + client flow; depends on §3 for the
   registration step to be meaningful.

§§1–2 and §5 are in-repo (shared module); §§3–4 cross into core/capabilities. Tackle 1→2→5 here,
then 3→4 across repos.

## 7. Open questions

- **Shared-module name/coordinates** — confirm the exact module the two classes move into and whether
  it may depend on `org.nasdanika.ncore` (needed for the `GlobalObjectRegistry` interface) without a
  cycle.
- **`GlobalObjectRegistry` interface vs. direct `NcoreResourceSet` cast** in §3.3 — depends on the
  dependency direction between the shared Groovy module and core.
- **Qualified-name matching** — match on `eSuperPackage` name chain, or on `nsPrefix` dotted path, or
  accept both? `nsPrefix` is the more stable identifier; the requirement's examples (`c4.Component`,
  `architecture.c4.Component`) read like nsPrefix segments.
- **`ResourceURIRequirement` predicate semantics** — confirm provider-side NS-URI matching (§4.1) is
  the intended reading.
