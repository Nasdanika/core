# Groovy & Kotlin DSLs as Authoring Surfaces — Design

**Status:** Draft for review
**Date:** 2026-06-15
**Scope:** Add Groovy and Kotlin (and optionally other JSR-223 languages) as authoring surfaces for Ecore models in general and the [Product Management model](https://product-management.models.nasdanika.org/) in particular, wired through the existing filename pipeline and `ResourceContentsHandler` machinery.

---

## 1. Goals

1. Let a `.pm.groovy` (and later `.pm.kts`) file be loaded as a Product Management model via `ResourceSet.getResource(uri)`, exactly like `.pm.md` and `.pm.drawio` are today.
2. Reuse the existing right-to-left filename pipeline rather than inventing a new loading path.
3. Keep the scripting integration on JSR-223 (`javax.script`) so the same plumbing serves every supported language and the dependency surface stays small.
4. Adopt the `ResourceContentsHandler<T>` contract (single-typed, stream-based, dispatched by the `CapabilityLoader` against a `Requirement`), with `adapt(...)` for clients that want a non-`EObject` value. *(Refined, see §11: modeling scripts as a `Script` EClass keeps the scripting path in `EObject`-land, so a non-`EObject` handler value is the escape hatch, not the default.)*
5. Provide validation hooks expressible *in the DSL* (Groovy, and optionally SpEL) via annotations, then a path to persist as `.ecore` and generate Java.

Non-goals for this round: a full Xtext-style editor, an LSP, or replacing Markdown/draw.io surfaces. This is additive.

---

## 2. Assessment

**Verdict: sound, and a natural extension of what you already have.** The filename pipeline is exactly the right seam to hang scripting off of, and JSR-223 is the right level of abstraction for "less powerful but simpler." The design below is mostly a matter of being disciplined about three boundaries: (a) the engine-handoff between pipeline stages, (b) the typed `T → V` / `V → T` contract on filters, and (c) where evaluation happens versus where validation happens.

What's genuinely good about the approach:

- **It plays to a real strength.** A Groovy/Kotlin builder gives you nesting, variables, loops, string interpolation, and cross-file references *for free* from the host language. You are not re-implementing a parser, a symbol table, or an expression sub-language — which is the tax Xcore/Emfatic/Xtext make you pay.
- **The "factory as a variable" trick is small.** The `.pm` stage only has to bind a factory (and a few helpers) into the engine's bindings and `eval`. That is a dozen lines, not a subsystem.
- **It composes with everything you've already built.** `.pm.groovy` slots into the same right-to-left grammar as `.pm.md`; the CLI command pipeline downstream doesn't know or care which surface produced the model.

The risks to manage, in priority order:

1. **Kotlin's dependency footprint and cold-start.** `kotlin-scripting-jsr223` drags in `kotlin-compiler-embeddable` (tens of MB) and first-evaluation latency is seconds, not milliseconds, because each script is *compiled*. Groovy is dramatically lighter and faster to warm up. Recommendation: ship Groovy as the first-class scripting surface; make Kotlin an opt-in module (see §7).
2. **Security / arbitrary code execution.** A `.groovy` model file is executable code. Loading one runs it. This is fine for author-trusted, CLI-first workflows (your primary persona), but it is a real consideration the moment a model file arrives from an untrusted source. Document the trust boundary explicitly; consider a Groovy `SecureASTCustomizer`/sandbox profile for the "load a model someone sent me" case. Markdown/draw.io don't have this property; Groovy/Kotlin do.
3. **Handler resolution ambiguity.** Multiple `ResourceContentsHandler`s can satisfy the same `Requirement`, so the resolution order is load-bearing. Lean on `Order` (longer = more specific = first) and make "first match wins after sort" an explicit, tested contract (see §4).
4. **Determinism / reproducibility.** Because the file is a program, two loads can differ (clock, environment, RNG, network in a filter). For models you want this to be boring and deterministic. Worth a stated convention: model scripts are pure builders; side-effecting enrichment belongs in a downstream handler, not in the authoring script.

Net: proceed with Groovy now, Kotlin as a module, and treat validation + `.ecore` generation as a fast-follow rather than a blocker.

---

## 3. Prior art

You are not breaking new ground here, which is reassuring — the pattern is well trodden, just not packaged the way you want.

**Groovy builders for EMF.** The canonical reference is Jörn Dinkla's *Using EMF with Groovy*, which describes an `EMFBuilder` taking an `EFactory` and using nested closures so braces express parent/child containment and nested names map to the parent's `EReference`s. References to created objects are stored in Groovy variables and reused later (e.g. a `Writer` referenced from a `Book`) — i.e. cross-referencing via host-language variables, which is exactly the mechanism you want. This is essentially `groovy.util.BuilderSupport`/`FactoryBuilderSupport` pointed at an Ecore factory. ([jdinkla.github.io](https://jdinkla.github.io/software-development/2007/10/22/using-emf-with-groovy.html))

**Kotlin type-safe builders.** Kotlin's official "type-safe builders" pattern (lambda-with-receiver functions that create a child, configure it via the lambda, attach it to the parent, and `@DslMarker` to control receiver scope) is the standard way to build hierarchical structures checked at compile time. Gradle's whole Kotlin DSL is the existence proof at scale. There is no single dominant "Kotlin-EMF" library the way there is for Groovy, but the builder mechanics map cleanly onto an Ecore factory. ([kotlinlang.org](https://kotlinlang.org/docs/type-safe-builders.html), [docs.gradle.org](https://docs.gradle.org/current/userguide/kotlin_dsl.html))

**Textual Ecore DSLs (the incumbents you're implicitly competing with).**
- **Emfatic** — a textual syntax for Ecore meta-models, Eclipse-hosted, Java-ish surface. ([eclipse-emfatic/emfatic](https://github.com/eclipse-emfatic/emfatic))
- **Xcore** — textual Ecore *plus* Xbase expressions, so you can write `EOperation` bodies inline; powerful but adds Xtext/Xbase to the toolchain. ([Xcore wiki](https://wiki.eclipse.org/Xcore))
- **OCLinEcore / Xtext grammars** — other generative routes, all of which require a build step to generate code before a model file can be parsed. ([itemis blog](https://blogs.itemis.com/en/using-xtext-with-xcore-and-gradle))

The relevant contrast: Emfatic/Xcore/Xtext are *generative* — they own a grammar and a code generator. Your Groovy/Kotlin approach is *interpretive/host-language-embedded* — no grammar to own, no generation step to author the model, and you inherit the host IDE tooling. That is the crux of the "Xcore vs Kotlin is moot" claim: a Kotlin DSL can express an Ecore metamodel without Xcore's grammar+generator, because Kotlin's own compiler and IDE support do that work. ([polyglot-emf proposal](https://github.com/dslmeinte/polyglot-emf))

**IDE support for Groovy DSLs.** `@DelegatesTo` is the documented mechanism for telling both the static type checker and the IDE (IntelliJ, and Groovy tooling generally) what the delegate type of a DSL closure is, which is what unlocks code completion inside `{ ... }` blocks. ([Groovy `DelegatesTo` docs](https://docs.groovy-lang.org/latest/html/api/groovy/lang/DelegatesTo.html), [mrhaki](https://blog.mrhaki.com/2013/05/groovy-goodness-delegatesto-for-type.html)) See §6.3 for the VS Code caveat.

---

## 4. The filename pipeline and the engine handoff

Today the grammar is read right-to-left: rightmost qualifier is the source format, each qualifier to its left is a transformation stage. `personas.pm.groovy` therefore parses as:

```
personas . pm . groovy
                 └─ position 0: source format = Groovy script
            └──── position 1: transform = Product Management model
```

Under the `ResourceContentsHandler<T>` interface a stage is no longer a `T -> V` filter that receives the previous stage's output as an argument. Each handler reads from the raw resource `InputStream` and returns its contents `T`; composition and dispatch move into the `CapabilityLoader`. A handler is resolved against a `Requirement` that carries the `Resource`, the desired `contentsType`, the full `qualifiers` array, and a `qualifierIndex` into it. Specificity is expressed by `Order` (a longer order is more specific and sorts first), which replaces the old single-int tie-break with a richer, multi-level comparison.

**How stages compose without a "previous output" parameter.** Loading begins at the leftmost (outermost) transform qualifier and works rightward toward the source. A handler that needs upstream contents builds a new `Requirement` with the same resource and qualifiers but `qualifierIndex` moved one step toward the rightmost source qualifier, asks the `CapabilityLoader` to resolve the handler for it, and calls that handler's `load(inputStream, options)`. The recursion bottoms out at the source-format handler (`.groovy`), the only one that actually reads the stream. So the `InputStream` is threaded straight through to the source handler; every transform above it receives a typed `T` (a compiled `Script`, then a model) rather than bytes.

**Stage `.groovy` (source).** Resolve nothing upstream. Read the stream and *compile* it into a `Script` EObject (§11); do *not* evaluate. This handler is a `ResourceContentsHandler<Script>`. Engine acquisition (JSR-223 `ScriptEngineManager`) and compilation are encapsulated inside the `Script`/`GroovyScript` EClass, not exposed as a loose `ScriptEngine` value passed between stages. The language-agnostic context (`resource`, `resourceSet`, `baseURI`) is available to the handler through its `Requirement` rather than pre-bound onto an engine.

**Stage `.pm` (transform).** Resolve the upstream source handler, obtain the compiled `Script`, then bind the model-specific helpers *per call* (not onto a shared engine):

- `factory`, the `ProductManagementFactory` (or a thin builder wrapper around it; see §6)
- `resource`, `resourceSet`, `baseURI`, so cross-file references resolve through EMF
- the DSL entry points (`product { ... }`, `concern { ... }`, etc.)

Then `eval` the compiled script against those bindings. The return value (last expression, or an explicit `return`) is the raw result: a single `EObject`, a `Collection`, or something else. This handler is a `ResourceContentsHandler<List<EObject>>` (or an `EObject`-specialized variant obtained via `adaptToResourceEObjectContentsHandler`).

**Terminal normalize + validate.** Still a single, language-independent concern:

1. Run EMF validation (`Diagnostician.INSTANCE.validate(...)`) over the result and attach diagnostics to `resource.getErrors()`, so consumers see them through the standard EMF channel.
2. Normalize the result into `Resource` contents: wrap a single `EObject` with `List.of(obj)`; pass a `Collection<EObject>` through; throw a typed exception otherwise.

Under the handler model this is just the tail of the `.pm` handler (or a thin wrapper handler), not a distinct filter type. It is identical for Groovy, Kotlin, and any future engine, and it is where the "scripts can return anything" looseness is converted back into the strict `EList<EObject>` a `Resource` requires.

```
┌─────────────┐  compiled Script  ┌──────────┐   raw result   ┌────────────────┐
│  .groovy    │ ────────────────▶ │   .pm    │ ─────────────▶ │ terminal stage │ ─▶ Resource.contents
│ read +      │   (a Script       │ bind +   │                │ validate +     │    (EList<EObject>)
│ compile     │    EObject)       │ evaluate │                │ normalize      │
└─────────────┘                   └──────────┘                └────────────────┘
   resolved by the CapabilityLoader against a Requirement (resource, contentsType, qualifiers, qualifierIndex);
   the .pm handler resolves the .groovy handler by moving qualifierIndex toward the source qualifier.
```

### The handler contract, and what crosses between handlers

`ResourceContentsHandler<T>` is single-typed: `T load(InputStream, options)` plus a default `save(T, OutputStream, options)` that throws `UnsupportedOperationException` (so a read-only handler is a one-method functional interface). Two adapters bridge types without reintroducing a two-parameter filter:

- `adapt(Function<T,V> load, Function<V,T> save)` produces a `ResourceContentsHandler<V>` for clients that legitimately want a non-`EObject` value (a domain POJO, a byte buffer, a specific Java type).
- `adaptToResourceEObjectContentsHandler(...)` specializes to `ResourceEObjectContentsHandler<V extends EObject>` for the common case.

This is what the old "generalize the filter to `<T, V>`" goal becomes: a single-typed handler plus `adapt` reaches the same cases, with `EObject -> EObject` as the well-lit default and a non-`EObject` value as the deliberate escape hatch.

Dispatch is the `CapabilityLoader`'s job. It collects the handlers whose capability factory accepts the `Requirement`, sorts by `Order` (longer order = more specific = first), and the first match wins. `Requirement` equality is by resource URI, contents type, qualifiers, and qualifier index, so resolution is deterministic and cacheable. This replaces the earlier `canHandleLoad`/`canHandleSave` instance-predicate dispatch: the predicate moves into the capability factory's acceptance test, and the `Order` carries specificity instead of a single int.

**What object should `.groovy` hand `.pm`?** This is the open question, and the choice matters. Three candidates:

- *A live `ScriptEngine`* (the original "prepared engine" framing). Stateful: bindings live on the engine, so two evaluations share mutable state and concurrent activity logic (§11) races. It is also not an `EObject`, so the handler value leaves `EObject`-land and is neither serializable nor inspectable.
- *A bare `CompiledScript`* (JSR-223 `Compilable`). Stateless and reusable, which is the right lifecycle (compile once, `eval(bindings)` many with a fresh context per call). But it is still a raw `javax.script` object outside the model, and SpEL / GraalJS would each need a different, non-uniform carrier.
- *A `Script` EObject* (§11) wrapping the compiled form. Same compile-once / eval-many lifecycle as `CompiledScript`, but it is an `EObject`: the handler stays `ResourceContentsHandler<Script>`, nothing forces a non-`EObject` value, and it is serializable, diffable, and documentable. One abstraction covers Groovy, GraalJS, and SpEL uniformly.

Recommendation for now: hand over a bare **`CompiledScript`**. It has the right lifecycle (compile once at load, `eval(bindings)` per call with a fresh `Bindings`), it is the smallest thing that works, and it keeps the source handler to a few lines with no new metamodel. The cost (a raw `javax.script` carrier that is not an `EObject`, and a per-engine carrier when SpEL/GraalJS arrive) is acceptable while the surface is Groovy-first and load-only.

The `Script` EObject stays on the table as a later upgrade. When the §11 unification starts to pay off (activity logic, NSML rule bodies, constraints, serializable behavior), wrap the same `CompiledScript` in a `GroovyScript` and the change is small: the source handler instantiates a `GroovyScript` instead of returning the compiled form directly, and the `.pm` handler calls `Script.eval` instead of `CompiledScript.eval`. The skeletons below take the `CompiledScript` path; the `Script` variant is kept after them as the deferred option.

#### Skeleton 1: generic Groovy source handler (`.groovy`)

```java
package org.nasdanika.capability.scripting.groovy;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.nasdanika.capability.emf.ResourceContentsHandler;

/**
 * Source-format handler for the rightmost `.groovy` qualifier.
 * Reads Groovy source and COMPILES it to a JSR-223 CompiledScript. It does not evaluate:
 * evaluation is a downstream (.pm) or runtime concern. Bindings are supplied per eval, so the
 * compiled form is reusable and carries no per-call state.
 *
 * Because the class implements ResourceContentsHandler, the nested types Order and
 * Requirement are in scope unqualified.
 */
public class GroovyScriptResourceContentsHandler implements ResourceContentsHandler<CompiledScript> {

    private final Requirement requirement;

    /** Constructed by the capability factory the CapabilityLoader resolves for this Requirement. */
    public GroovyScriptResourceContentsHandler(Requirement requirement) {
        this.requirement = requirement;
    }

    @Override
    public Order getOrder() {
        // Keyed off a single source qualifier ("groovy"); a bare source handler is the least specific.
        // A handler matching a longer qualifier run returns a longer Order and wins the tie-break.
        return Order.of(0);
    }

    @Override
    public CompiledScript load(InputStream inputStream, Map<?, ?> options) throws IOException {
        String source = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        ScriptEngine engine = new ScriptEngineManager().getEngineByExtension("groovy");
        if (!(engine instanceof Compilable)) {
            throw new IOException("Groovy JSR-223 engine is not Compilable on the classpath");
        }
        try {
            // compiled, NOT evaluated; syntax errors surface here, at load time
            return ((Compilable) engine).compile(source);
        } catch (ScriptException e) {
            throw new IOException("Failed to compile " + requirement.getResource().getURI(), e);
        }
    }

    // save(...) inherits the default UnsupportedOperationException:
    // generating .groovy source from a model is out of scope for now (load-only surface, §10).
}
```

#### Skeleton 2: Product Management transform handler (`.pm`)

```java
package org.nasdanika.models.pm.scripting;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.script.Bindings;
import javax.script.CompiledScript;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.emf.ResourceContentsHandler;

/**
 * Transform handler for the `.pm` qualifier sitting to the LEFT of a source qualifier
 * (e.g. `personas.pm.groovy`). It resolves the upstream source handler to obtain a
 * CompiledScript, binds the PM-model helpers per call, evaluates, then normalizes to EList<EObject>.
 */
public class ProductManagementScriptResourceContentsHandler implements ResourceContentsHandler<List<EObject>> {

    private final Requirement requirement;
    private final CapabilityLoader capabilityLoader;

    public ProductManagementScriptResourceContentsHandler(Requirement requirement, CapabilityLoader capabilityLoader) {
        this.requirement = requirement;
        this.capabilityLoader = capabilityLoader;
    }

    @Override
    public Order getOrder() {
        // Matches the ["...","pm","groovy"] run, so it is longer (more specific) than a bare source handler.
        return Order.of(0).add(0);
    }

    @Override
    public List<EObject> load(InputStream inputStream, Map<?, ?> options) throws IOException {
        Resource resource = requirement.getResource();

        // 1. Build the UPSTREAM (source) Requirement: same resource & qualifiers,
        //    qualifierIndex moved one step toward the rightmost source qualifier.
        //    (Doc convention: index 0 = rightmost = source, so "toward source" decrements.)
        Requirement upstream = ResourceContentsHandler.createRequirement(
                resource,
                CompiledScript.class,                    // we want a compiled script, not raw bytes
                requirement.getQualifiers(),
                requirement.getQualifierIndex() - 1);

        CompiledScript compiled = resolveUpstream(upstream).load(inputStream, options); // compiled, not yet evaluated

        // 2. Build the PM DSL for this load and install its top-level entry points (product, ref) plus
        //    context (factory/resource/resourceSet/baseURI) into a fresh Bindings. groovy.lang.Script
        //    .invokeMethod resolves a bare `product { }` against the same-named Closure here; nested
        //    names resolve via each closure's delegate. No shared state, so concurrent eval is safe.
        //    See §5.4 for the builder mechanism.
        ProductManagementGroovyDsl dsl =
                new ProductManagementGroovyDsl(ProductManagementFactory.eINSTANCE, resource);
        Bindings bindings = new SimpleBindings();
        dsl.installInto(bindings);

        // 3. Evaluate the compiled script against the bindings -> raw model result (the script's value).
        Object result;
        try {
            result = compiled.eval(bindings);
        } catch (ScriptException e) {
            throw new IOException("Failed to evaluate " + resource.getURI(), e);
        }

        // 4. Resolve id-based references, then normalize + validate (terminal concern) -> EList<EObject>.
        dsl.resolveDeferred();
        return normalize(result, resource);
    }

    /**
     * Resolve the handler for an upstream qualifier through the CapabilityLoader.
     * The exact call depends on your CapabilityLoader API (it wraps the Requirement in the
     * project's capability-requirement type); the shape is "ask the loader for the handler
     * that satisfies this Requirement and return the best (highest-Order) match".
     */
    @SuppressWarnings("unchecked")
    private ResourceContentsHandler<CompiledScript> resolveUpstream(Requirement upstream) {
        // return (ResourceContentsHandler<CompiledScript>) capabilityLoader.loadOne(wrap(upstream), null);
        throw new UnsupportedOperationException("wire to CapabilityLoader");
    }

    /** Wrap a single EObject, pass a Collection<EObject> through, run Diagnostician, attach to resource.getErrors(). */
    private List<EObject> normalize(Object result, Resource resource) {
        // ... see §4 terminal normalize + validate ...
        throw new UnsupportedOperationException("sketch");
    }
}
```

The `resolveUpstream` body depends on your `CapabilityLoader` API; the shape is what matters. An outer handler resolves the next-inner handler by moving the qualifier index toward the source and asks it for typed contents (`CompiledScript`), rather than receiving them as a parameter. The same pattern adds Kotlin or GraalJS for free: only the source handler changes (the engine name, and the compiled form it returns), while the `.pm` handler is engine-agnostic because it only ever sees a `CompiledScript`.

#### Later: the `Script` / `GroovyScript` EObject variant

When the §11 unification earns its keep, swap the carrier from a raw `CompiledScript` to a `Script` EObject that *wraps* the same compiled form. Only two things change. The source handler instantiates a `GroovyScript` instead of returning the compiled script directly:

```java
public class GroovyScriptResourceContentsHandler implements ResourceContentsHandler<Script> {

    // ... requirement, getOrder() as above ...

    @Override
    public Script load(InputStream inputStream, Map<?, ?> options) throws IOException {
        String source = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        GroovyScript script = ScriptingFactory.eINSTANCE.createGroovyScript();
        script.setLanguage("groovy");
        script.setSource(source);
        script.setSourceURI(requirement.getResource().getURI().toString());
        script.compile(); // idempotent; caches the CompiledScript on the EObject; throws on syntax error

        return script;    // an EObject; compiled, NOT evaluated
    }
}
```

And the `.pm` handler changes the upstream `contentsType` to `Script.class`, holds a `Script` instead of a `CompiledScript`, and calls `script.eval(bindings)` (which delegates to the cached `CompiledScript`) in place of `compiled.eval(bindings)`. Everything else (per-call bindings, normalize + validate, the resolution-by-index pattern) is unchanged. The payoff is that the `Script` is now an `EObject`: serializable, diffable, reachable by the same tooling, and reusable for activity logic, NSML rule bodies, and constraints (§11).

---

## 5. Groovy as a DSL for Ecore and the Product Management model

### 5.1 Nested objects (JSON/YAML-like)

Groovy closures + a builder give you the nested, declarative feel of YAML without YAML. Two implementation strategies:

**(a) `FactoryBuilderSupport` / `BuilderSupport`** — register node factories that map names to `EFactory.create(...)` calls and attach children to parents via the appropriate `EReference`. This is the Dinkla approach and the most "generic Ecore" of the two: one builder works for any `EPackage`.

**(b) Hand-written builder methods with `@DelegatesTo`** — write explicit DSL methods per model. More code, but far better IDE support and static checking (see §6.3). For the PM model specifically, this is worth it because the model is stable and high-value.

Authoring surface you're aiming for:

```groovy
// personas.pm.groovy
product {
    name 'Business Banking Platform'

    concern {
        id 'kyc-latency'
        name 'KYC onboarding latency'
        description 'Time from application to first usable account'
    }

    capability {
        id 'automated-kyc'
        name 'Automated KYC'
        // cross-reference a concern defined above, by id
        addresses 'kyc-latency'
    }
}
```

The `{ ... }` blocks are closures whose delegate is a per-element builder. `name`, `id`, `description` are setter-style methods on the delegate; `concern`/`capability` are child-factory methods on the parent delegate.

### 5.2 Cross-referencing — within and across files

Three tiers, increasing in power:

1. **Within a file, by variable.** Pure host language: `def kyc = concern { ... }` then `capability { addresses kyc }`. Zero framework support needed. This is the Dinkla pattern.

2. **Within a file, by id (deferred resolution).** `addresses 'kyc-latency'` stores an *unresolved key*; the terminal stage (or a post-eval resolver bound into the engine) walks the model, indexes elements by `id`, and resolves keys to `EObject` references. This decouples definition order from reference order and reads more like YAML `$ref`. Recommended default for the PM model.

3. **Across files.** A `concern` in `concerns.pm.groovy` referenced by a `capability` in `capabilities.pm.groovy`. Two viable mechanisms:
   - **`resourceSet`-mediated `EMF` proxies.** Bind a helper `ref(uri)` / `ref('concerns.pm.groovy#kyc-latency')` that resolves through the same `resourceSet`, relative to `sourceURI`. Because the engine already has `resourceSet` and `baseURI`, this is the natural fit and reuses EMF's existing cross-document reference/proxy resolution. This is the one to build.
   - **An include/import helper.** `def concerns = load('concerns.pm.groovy')` returning the loaded model so the script can reference its elements directly. Convenient but eager; prefer the proxy approach for large graphs.

The cross-file story is the strongest argument for binding `resourceSet` + `baseURI` into the engine at the `.groovy` stage rather than later — it makes references *transparent* through the standard EMF resolution machinery, exactly like the draw.io → Markdown prototype references you already support.

### 5.3 IDE friendliness (VS Code)

This is the honest weak spot and worth being candid about in the design.

- **`@DelegatesTo` is the right mechanism** and it works well — *in IntelliJ IDEA and Eclipse with the Groovy tooling*, which honor `@DelegatesTo` (and `@DelegatesTo.Target`) for in-closure completion and type checking. If you annotate your builder methods, those IDEs light up completion inside the `{ }` blocks. ([Groovy docs](https://docs.groovy-lang.org/latest/html/api/groovy/lang/DelegatesTo.html))
- **VS Code is weaker for Groovy.** The common VS Code Groovy extensions provide syntax highlighting reliably, but semantic completion driven by `@DelegatesTo` is not on par with IntelliJ. There is no first-class Groovy language server with the maturity of the Java/Kotlin ones. Set expectations accordingly.

Practical layering for VS Code, best ROI first:

1. **Syntax highlighting** — free, via any Groovy grammar extension. Always available.
2. **Strong typing in the builder API** — give every DSL method real return types and `@DelegatesTo` annotations. Even where completion is weak, this makes `@CompileStatic`/`@TypeChecked` catch errors and makes the *Java* tooling understand the API.
3. **A generated stub / `.groovy` API surface** the editor can index — publish the builder classes as a normal dependency so go-to-definition works.
4. **GDSL/DSLD descriptors** (IntelliJ GDSL, Eclipse DSLD) for the IDEs that consume them — these describe dynamic DSL contributions for completion. They do *not* help VS Code, but they're cheap insurance for your IntelliJ/Eclipse users.
5. **If VS Code completion truly matters,** the honest answer is that **Kotlin gives you a better VS Code/editor story than Groovy** because `.kts` is statically typed and tooling-friendly — which is an argument for Kotlin-as-authoring-surface even though it's heavier as an engine. Flag this trade-off rather than papering over it.

Recommendation: lead with Groovy for the runtime/engine simplicity and the builder ergonomics, annotate aggressively with `@DelegatesTo` + `@CompileStatic`, set the expectation that the premium editor experience is IntelliJ/Eclipse, and keep Kotlin in your pocket as the "statically-typed authoring surface" answer.

### 5.4 How the script "sees" the builders (entry points and delegates)

A `.pm.groovy` script calls `product { ... }` at the top level and `concern { ... }`, `name '...'`, `addresses kyc` inside the braces. None of those names are declared in the script. Two distinct mechanisms make them resolve, and they map onto the two scopes:

**Top-level names (`product`, `ref`): bound closures.** `groovy.lang.Script.invokeMethod` has a built-in fallback: when the script calls a bare method that the script class does not define, it looks for a same-named `Closure` in the script's binding and calls it. So the `.pm` handler does not need a custom base class or a parser hook; it just puts `product` and `ref` into the `Bindings` as closures before `eval`. `product { ... }` then resolves to the bound `product` closure, invoked with the trailing closure as its argument.

This is exactly why bound closures beat a custom *script base class* here. A base class declaring `product(Closure)` as a real method is more IDE-friendly, but it is PM-specific, so the *compile* step would have to know about the PM model. That breaks the §4 layering: the generic `.groovy` source handler must stay model-agnostic (it only compiles), and all PM knowledge belongs in the `.pm` transform handler. Injecting the entry points as bindings at eval time keeps that separation clean and works with plain JSR-223.

**Nested names (`concern`, `name`, `addresses`): the closure delegate.** Inside `product { ... }`, the names resolve against the closure's *delegate*, which the `product` closure sets to a per-element builder with `resolveStrategy = DELEGATE_FIRST`. The builder exposes `concern`, `name`, etc. as methods; each child method repeats the trick for its own closure. `@DelegatesTo(ProductBuilder)` on the entry point is what gives IntelliJ/Eclipse completion inside the braces (§5.3).

The PM DSL is therefore a small Groovy support type the Java `.pm` handler instantiates per load and installs into the bindings:

```groovy
// ProductManagementGroovyDsl.groovy — instantiated and installed per load by the .pm handler
class ProductManagementGroovyDsl {

    final ProductManagementFactory factory
    final Resource resource
    final ResourceSet resourceSet
    final URI baseURI
    private final Map<String, EObject> byId = [:]          // index for id-based references
    private final List<Closure> deferred = []              // id references resolved after eval

    ProductManagementGroovyDsl(ProductManagementFactory factory, Resource resource) {
        this.factory = factory
        this.resource = resource
        this.resourceSet = resource.resourceSet
        this.baseURI = resource.URI
    }

    /** Put the top-level entry points (and context) where Script.invokeMethod will find them. */
    void installInto(Bindings bindings) {
        bindings.product = this.&product   // a method reference is a Closure, callable as `product { }`
        bindings.ref     = this.&ref
        bindings.factory = factory; bindings.resource = resource
        bindings.resourceSet = resourceSet; bindings.baseURI = baseURI
    }

    /** Top-level: product { ... }. Returns the built element; the terminal stage puts it in the resource.
        (A multi-root file ends with a list literal, e.g. `[product { }, product { }]`, which normalize passes through.) */
    Product product(@DelegatesTo(ProductBuilder) Closure cl) {
        Product product = factory.createProduct()
        run(cl, new ProductBuilder(this, product))
        product
    }

    /** Tier 3 cross-file: ref('concerns.pm.groovy#kyc-latency') -> proxy resolved through resourceSet. */
    EObject ref(String fragment) {
        resourceSet.getEObject(URI.createURI(fragment).resolve(baseURI), true)
    }

    void index(String id, EObject e) { byId[id] = e }
    void defer(Closure c)            { deferred << c }
    void resolveDeferred()           { deferred*.call() }   // called by the terminal stage, post-eval

    static void run(Closure cl, Object delegate) {
        cl.delegate = delegate
        cl.resolveStrategy = Closure.DELEGATE_FIRST
        cl.call()
    }
}
```

The per-element builders carry the nested vocabulary, and the three reference tiers (§5.2) fall out of Groovy's runtime overload selection on `addresses`:

```groovy
class CapabilityBuilder {
    private final ProductManagementGroovyDsl dsl
    private final Capability capability
    CapabilityBuilder(ProductManagementGroovyDsl dsl, Capability capability) { this.dsl = dsl; this.capability = capability }

    void id(String s)   { capability.id = s }
    void name(String s) { capability.name = s }

    // Tier 1, by variable: `addresses kyc` where kyc is the EObject returned by `concern { }`.
    void addresses(Concern concern) { capability.addresses.add(concern) }

    // Tier 2, by id: `addresses 'kyc-latency'` — deferred until every id has been indexed.
    void addresses(String id) { dsl.defer { capability.addresses.add((Concern) dsl.byId[id]) } }

    // Tier 3, cross-file: `addresses ref('concerns.pm.groovy#kyc-latency')` — ref(...) returns an
    // EObject, so it binds to the Concern/EObject overload above; no extra method needed.
}
```

And the `ProductBuilder.concern` method is what makes `def kyc = concern { ... }` return the built element so it can be referenced by variable:

```groovy
Concern concern(@DelegatesTo(ConcernBuilder) Closure cl) {
    Concern c = dsl.factory.createConcern()
    ProductManagementGroovyDsl.run(cl, new ConcernBuilder(c))
    product.concerns.add(c)
    if (c.id) dsl.index(c.id, c)   // make it reachable by id (tier 2) as well
    c                               // returned -> `def kyc = concern { }` captures the EObject
}
```

So the `.pm` handler's job is small: build a `ProductManagementGroovyDsl` for this load, `installInto` the bindings, `eval` the compiled script, then call `resolveDeferred()` as part of the terminal normalize+validate stage. The generic `.groovy` source handler never sees any of this.

### 5.5 Building and shipping the DSL (Maven and Eclipse)

There are two kinds of Groovy in play, and only one of them is compiled by your build. Keeping them separate is the whole trick:

- **DSL library code** (`ProductManagementGroovyDsl`, `ProductBuilder`, `CapabilityBuilder`, the entry points). This is ordinary library code that happens to be written in Groovy. It must be **compiled to JVM bytecode at build time** and placed on the classpath so the Java `ResourceContentsHandler` can `new ProductManagementGroovyDsl(...)` and reference `ProductBuilder` like any Java type. This is what you meant by "compiled to Java classes to be visible by the handler", and it is correct: once compiled, a Groovy class is just a `.class` file the JVM and the handler cannot tell apart from a Java one.
- **User model scripts** (`personas.pm.groovy`). These are **not** compiled by your build. They are loaded as resources and compiled at *runtime* by the `.groovy` source handler (§4) via `Compilable.compile()`. Do not let Maven or Eclipse try to compile them; mark the folder that holds them as resources, not a source folder. (A practical gotcha: if `.pm.groovy` model files sit under a compiled source root, the build will choke on the unbound `product`/`concern` names, because the builders are not on the script's compile classpath.)

#### Module layout (the simplest setup)

Put the DSL library in its own **Groovy-only** module, e.g. `org.nasdanika.models.pm.dsl.groovy`, with sources under `src/main/groovy`. Because the module contains no Java, there is no Java-to-Groovy joint compilation to arrange: GMavenPlus simply compiles `.groovy` to `.class`. The Java handler module then depends on this jar like any other, and the builder classes are visible with zero extra ceremony. This sidesteps the stub-generation complexity that mixed Java+Groovy modules otherwise pull in.

#### Maven, option A: GMavenPlus (recommended for the Groovy-only module)

```xml
<properties>
  <groovy.version>4.0.32</groovy.version>        <!-- JDK 8+; use 5.0.x on JDK 11+ -->
  <gmavenplus.version>4.3.1</gmavenplus.version>  <!-- 5.0.0 if you are on Maven 4 -->
</properties>

<dependencies>
  <dependency>
    <groupId>org.apache.groovy</groupId>          <!-- Groovy 4+ groupId; was org.codehaus.groovy in 3.x -->
    <artifactId>groovy</artifactId>
    <version>${groovy.version}</version>
  </dependency>
  <dependency>                                     <!-- the generated EMF model: Product, Concern, Capability... -->
    <groupId>org.nasdanika.models</groupId>
    <artifactId>product-management</artifactId>
    <version>...</version>
  </dependency>
</dependencies>

<build>
  <plugins>
    <plugin>
      <groupId>org.codehaus.gmavenplus</groupId>
      <artifactId>gmavenplus-plugin</artifactId>
      <version>${gmavenplus.version}</version>
      <executions>
        <execution>
          <goals>
            <goal>addSources</goal>      <!-- register src/main/groovy as a source root -->
            <goal>addTestSources</goal>
            <goal>compile</goal>         <!-- compile .groovy -> target/classes/*.class -->
            <goal>compileTests</goal>
          </goals>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

The output is identical to a Java compile: real `.class` files in `target/classes`, packaged into the jar, resolvable by the handler module.

#### Maven, option B: groovy-eclipse-compiler (only if Java and Groovy share one module)

If you would rather keep the Java handler and the Groovy builders in a *single* module, you need joint compilation because Java references Groovy. Prefer the `groovy-eclipse-compiler` adapter for `maven-compiler-plugin`: it does **stubless** joint compilation (the same JDT-based compiler the Eclipse Groovy tooling uses), avoiding the stub-generation step GMavenPlus requires.

```xml
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-compiler-plugin</artifactId>
  <version>3.13.0</version>
  <configuration>
    <compilerId>groovy-eclipse-compiler</compilerId>
  </configuration>
  <dependencies>
    <dependency>
      <groupId>org.codehaus.groovy</groupId>
      <artifactId>groovy-eclipse-compiler</artifactId>
      <version>3.9.0</version>
    </dependency>
    <dependency>
      <groupId>org.codehaus.groovy</groupId>
      <artifactId>groovy-eclipse-batch</artifactId>
      <version>4.0.32-01</version>   <!-- batch artifact tracks the Groovy version: <groovy>-NN -->
    </dependency>
  </dependencies>
</plugin>
```

Co-locate `.groovy` under `src/main/java`, or add `src/main/groovy` as an extra source root via `build-helper-maven-plugin`. You may also need the Groovy plugin repository `https://groovy.jfrog.io/artifactory/plugins-release` to resolve the newest batch/compiler artifacts.

#### Runtime dependency for the source handler

Separate from compiling the builders, the `.groovy` source handler needs a JSR-223 engine at runtime so `new ScriptEngineManager().getEngineByExtension("groovy")` resolves and returns a `Compilable` engine. Add `org.apache.groovy:groovy-jsr223` (plus `org.apache.groovy:groovy`) to the runtime classpath of whatever module hosts the handler.

#### JPMS note

Nasdanika is module-based, and Groovy is the rough edge here. Two points:

- For the **DSL library module**, a `module-info.java` needs `requires` for the Groovy module, the EMF Ecore module, and the generated PM model module, and `exports` the builder package the handler reaches. Groovy 4 jars carry an `Automatic-Module-Name` in their manifest; confirm the exact token with `jar --describe-module --file groovy-4.0.32.jar` rather than guessing it, since automatic module names have changed across Groovy releases.
- For the **runtime engine**, do not expect Groovy to behave as a clean named module: it defines compiled-script classes dynamically, and those land outside your module graph. The pragmatic posture is to treat the Groovy runtime as an automatic module (or even keep it on the classpath) and accept that user scripts compile into a dynamic/unnamed module. This is consistent with §2's trust-boundary framing: user scripts are runtime artifacts, not part of your compiled module surface.

#### Eclipse

1. Install **Groovy Development Tools** (Groovy-Eclipse) matching your Eclipse release: Help -> Install New Software, and point at the update site for your version (for example `https://groovy.jfrog.io/artifactory/plugins-release/e4.39` for Eclipse 2026-03, or the corresponding `plugins-snapshot/e4.40` site for 2026-06). The Eclipse Marketplace entry "Groovy Development Tools" does the same. The install directory must be writable by the current user.
2. Give the DSL module the **Groovy nature** (right-click the project -> Configure -> Convert to Groovy Project). This puts the JDT-based Groovy compiler on the build path and turns `src/main/groovy` into a recognized source folder, so the builder classes compile in-IDE exactly as Maven compiles them.
3. For the **m2e** (Maven-in-Eclipse) story, the `groovy-eclipse-compiler` setup integrates the most smoothly because m2e drives the same JDT Groovy compiler the IDE already uses. With GMavenPlus you will likely need the matching m2e connector / lifecycle mapping so the IDE runs the `compile` goal. Either way the goal is that an in-IDE build and a command-line `mvn` build produce the same `.class` files.
4. With GDT installed, the `@DelegatesTo(ProductBuilder)` annotations on the entry points (§5.3, §5.4) drive in-closure completion, so authoring `product { ... }` gives real content assist inside Eclipse.

Net: a dedicated Groovy-only module compiled by GMavenPlus is the path of least resistance. The builders become ordinary classpath classes the Java handler links against, the user `.pm.groovy` files stay out of the compile path and are compiled at load time, and Eclipse sees the same picture through GDT.

---

### 5.6 A fully reflective builder (eliminating per-class drift)

The §5.4 builders are hand-written: one class per `EClass`, one method per feature. That works and gives the best IDE story, but it only ever covers the part of the metamodel someone has gotten around to writing, and every model change invites drift between the Ecore and the DSL. An alternative — or a complement — is to drive the entire authoring surface off the metamodel at runtime, so a single builder covers the whole `EPackage` and tracks any future xcore change for free.

This is viable here precisely because of the genmodel settings: `featureDelegation="Dynamic"` and `operationReflection="true"` mean `eGet`/`eSet` and operations work uniformly through reflection, and `ProductmanagementPackage.eINSTANCE` exposes every `EClass`, `EStructuralFeature`, and `EEnum`. The reference implementation is dropped into the module as `ReflectiveBuilder.groovy` + `DslContext.groovy`, living alongside the hand-written builders rather than replacing them.

#### The win: multiple inheritance for free

`element.eClass().getEAllStructuralFeatures()` flattens the whole inheritance closure. `ProductModel extends NamedPeriod, PersonaDomain, CapabilityDomain, CapabilityProviderDomain, ActorDomain` therefore exposes `capabilities`, `personas`, `capabilityProviders`, and `actors` with zero per-feature code; `Capability` mixing in `EvidenceDomain`, `PersonaDomain`, `ConcernDomain` likewise. The hand-written builders would need a method per mixed-in feature on each combining class.

#### Two-level dispatch

A single `ReflectiveBuilder` wraps an `EObject` and resolves every in-closure name through `methodMissing`:

1. **Feature dispatch** — the name is a structural feature of the current `EClass`. `EAttribute` → coerce + `eSet`/add; containment `EReference` with a closure → build inline; any other reference → the cross-reference resolver below. Derived/read-only features (`resolvedPersonas`, `allAddresses`, `dependents`, `allBlockedBy`, …) are rejected via `f.changeable && !f.derived`.
2. **Type dispatch** — the name is a concrete `EClass` (`goal { }`); a child is created and auto-routed to the *unique* containment feature that accepts it. Where more than one fits — inside `Capability`, both `concerns` (from `ConcernDomain`) and `addresses` are `AbstractConcern` containments — it refuses and asks for the feature form, `concerns('Concern') { }` vs `addresses('Goal') { }`. Abstract-typed containments require a concrete-type token for the same reason.

Enums coerce through `EEnum` (`lifecycle 'Available'` or `'AVAILABLE'`, `DependencyKind` likewise) with no per-enum code; simple datatypes go through `EcoreUtil.createFromString`; `wraps` types (`Instant`, `Duration`) pass through when already the right Java type.

#### The centralised cross-reference resolver (the real payoff)

This is the part most prone to drift in the hand-written approach, where each reference feature needs its own `(String)` overload (`addresses`, `addressedBy`, and eventually `target`, `supports`, `blockedBy`, `dependencies`, `violates`…). The reflective builder funnels **all** of them through one `resolveReference` / `attachReference` pair keyed on the `EReference`, so there is nothing to keep in sync:

- **Lookup is unified.** Tier 2 (local `id`) and tier 3 (cross-file URI) collapse into one `DslContext.resolve(String)` — local index first, then `resourceSet.getEObject(uri)`. A bare `'kyc-latency'` and a `'concerns.pm.groovy#kyc-latency'` take the same path. Resolution defers until after evaluation, exactly as today, so order is irrelevant and the existing `resolveDeferred()` two-phase flow is unchanged. Type validity is checked with the metamodel's own constraint, `r.EType.isInstance(target)`, giving one consistent error everywhere.
- **Wrapper-vs-direct is the one rule that must be centralised.** This metamodel models "reference" two ways: as a direct `refers` feature (`ConcernReference.target`, `CapabilityReference.target`, …), and as *containment of a `*Reference` wrapper*. Because EMF containment is exclusive, adding a resolved element straight into a containment feature (`Concern.addressedBy`, `Capability.addresses`, `Goal.blockedBy`) would **relocate** it out of its real container. The resolver instead detects the wrapper from Ecore — a concrete subtype of the feature's type carrying a single-valued, non-containment `target` whose type accepts the resolved element — creates it, and sets its target. The model's consistent `*Reference` + `target` convention makes this fully discoverable (`AbstractConcern→ConcernReference`, `AbstractCapability→CapabilityReference`, preferring the most general where `CapabilityDependency` also qualifies). Where no wrapper exists and the target is already contained, the resolver refuses rather than silently steal it.

One consequence worth flagging during migration: the current hand-written `addressedBy(String)` / `addresses(String)` add the resolved element directly to a containment feature, which *would* relocate an element defined elsewhere. The reflective resolver's wrapper policy is the corrected behaviour; confirm against existing `.pm.groovy` data whether by-id `addressedBy`/`addresses` was ever meant to inline-contain rather than reference.

#### Runtime-reflective vs. codegen, and IDE support

Pure runtime reflection costs the static `@DelegatesTo` checking and IDE completion the §5.4 builders enjoy (§5.3). Two non-exclusive recoveries: generate typed builder stubs at build time by walking `ProductmanagementPackage.eINSTANCE` (Xtend fits the existing xcore/EMF toolchain; the stubs can subclass the reflective core and add only typed signatures), or generate an IntelliJ GDSL / Eclipse DSLD descriptor from the `EPackage` so editors complete while the runtime stays reflective. A clean hybrid keeps the reflective engine as the default and a `Map<EClass, Class<? extends ReflectiveBuilder>>` registry of hand-written specialisations only where coercion is non-trivial.

#### Migration

`DslContext` is `ProductManagementGroovyDsl` generalised: it keeps `factory`/`resource`/`resourceSet`/`baseURI`, the `byId` index, `defer`/`resolveDeferred`, `ref`, and `run`, and adds a name→`EClass` registry and the wrapper detector, all built from the `EPackage` in the constructor. `installInto` exposes every concrete class as a root entry point (`product { }`, `persona { }`, …) generically. The `.pm` handler's job is unchanged in shape: build a `DslContext`, `installInto` the bindings, `eval`, then `resolveDeferred()` in the terminal normalise stage.

---

## 6. Kotlin as a scripting engine

**Short answer: yes, it works via JSR-223, and yes, it comes with a lot of dependencies.**

- The engine is `kotlin-scripting-jsr223`; you obtain it with `ScriptEngineManager().getEngineByExtension("kts")` (or `getEngineByName("kotlin")`). ([Kotlin JSR-223 example](https://github.com/Kotlin/kotlin-script-examples/blob/master/jvm/jsr223/jsr223.md))
- It transitively depends on `kotlin-scripting-compiler-embeddable` / `kotlin-compiler-embeddable`, plus `kotlin-scripting-jvm`, `kotlin-script-runtime`, and `kotlin-stdlib`. The compiler-embeddable artifact alone is tens of MB. Users have repeatedly hit `ClassNotFoundException`/missing-dependency issues when the full set isn't on the classpath. ([Kotlin discussions](https://discuss.kotlinlang.org/t/jsr223-usage-dependencies/3948), [KT-30986](https://youtrack.jetbrains.com/issue/KT-30986/))
- **Runtime cost:** each script is *compiled* before it runs. Cold start is seconds; the first evaluation in a JVM pays compiler-init cost. Groovy, by contrast, is comparatively light and warm-starts fast.

Design consequence: **put Kotlin in its own optional module** (`nasdanika-resource-kotlin` or similar) so the base distribution and the CLI stay slim. A user who wants `.pm.kts` opts in and accepts the footprint. The JSR-223 abstraction means the `.kts` source stage is the same shape as the `.groovy` stage — only the engine name and the dependency module differ. Bindings (`factory`, `resource`, `resourceSet`) cross via `Bindings`/`ScriptContext` identically.

One sharp edge: Kotlin JSR-223 binding semantics and receiver/scoping differ from Groovy's; the type-safe-builder DSL for Kotlin uses lambda-with-receiver + `@DslMarker` rather than `@DelegatesTo`. So the *DSL surface* is written twice (once per language), even though the *pipeline plumbing* is shared. Budget for that.

---

## 7. Other scripting engines worth supporting

Through the same JSR-223 seam, near-zero marginal plumbing:

- **GraalJS (JavaScript)** — strong candidate. JS object literals are essentially JSON-with-behavior, which is a comfortable model-authoring surface; GraalVM polyglot is well maintained and sandboxable. Heavier dependency than Groovy but lighter conceptually than Kotlin for "JSON-ish" authors.
- **Nashorn** — deprecated/removed from the JDK; available as a standalone artifact but not worth investing in for new work.
- **Jython / JRuby** — possible, low demand for your personas; skip unless a concrete user asks.
- **BeanShell / `java.util.spi` Java-as-script (JShell-based)** — interesting for the "I'm thinking in Java" thesis: authoring a model in near-plain Java. JShell isn't JSR-223, so it's a separate integration; park it as a thought experiment that reinforces the article's theme rather than a near-term deliverable.

Recommendation: **Groovy (default) + Kotlin (opt-in module) now; GraalJS as the most likely third.** Keep the engine registry open so adding one is "register a source-format handler for the extension," not a code change to the pipeline.

---

## 8. Validation in the DSL (Groovy, and SpEL)

You want validation rules authored *near the model*, in the DSL, executed at load and surfaced as EMF diagnostics.

**Mechanism: annotations carrying expression source.** Annotate Ecore features/classes (via `EAnnotation`s, the standard Ecore extension point) with constraint expressions, then evaluate them in a chosen language at validation time. This is exactly how OCL-in-Ecore works today (`EAnnotation` source `http://www.eclipse.org/emf/2002/Ecore` with `constraints`/`invariant` details) — you'd add Groovy and SpEL as alternative expression languages alongside (or instead of) OCL.

```groovy
capability {
    id 'automated-kyc'
    name 'Automated KYC'
    // inline constraint, evaluated in Groovy against the element as `self`
    constraint 'name', 'self.name != null && self.name.length() <= 80'
    constraint 'addressesSomething', { self -> !self.addresses.isEmpty() }
}
```

Two evaluation backends:

- **Groovy-in-Groovy.** Compile the constraint string/closure with a `GroovyShell`/`@CompileStatic` binding where `self` is the `EObject` (and maybe `resourceSet`, `monitor`). Cheapest to add since Groovy is already on the classpath for the authoring surface. Closures (`{ self -> ... }`) are even nicer than strings because the IDE can check them.
- **SpEL (Spring Expression Language).** Very doable and *lightweight* — `spring-expression` is a small, dependency-light jar (it needs `spring-core`, not the whole framework). Evaluate with a `StandardEvaluationContext` whose root object is the `EObject`; expose features as properties. SpEL is a good fit for "simple boolean/derivation expressions authored by less Groovy-fluent users" and it's genuinely *not too hard* — this is a clean, contained addition. Register it behind a small `ConstraintLanguage` SPI so Groovy/SpEL/OCL are interchangeable per-annotation.

Wiring into EMF: implement an `EValidator` (or contribute via `EValidator.Registry`) that, on `validate`, reads the constraint annotations off the element's `EClass`, evaluates each in its declared language, and reports failures as `Diagnostic`s. The terminal stage (§4) already calls `Diagnostician` and attaches results to the resource, so validation flows out through the standard channel with no new surface for consumers.

**Persisting + Java generation.** The authored model can be serialized to `.ecore`/`.genmodel` (`resource.save(...)` with the XMI factory) and Java generated two ways:

- **Eclipse** — the genmodel "Generate Model Code" path, for interactive work.
- **CLI / headless** — EMF's `org.eclipse.emf.codegen.ecore.Generator` invoked headlessly, or via Maven (`emf-maven-plugin` / `org.eclipse.emf.codegen` Ant tasks), which fits your CLI-first, reproducible-build posture. This is the route to wire into `nsd`. Constraints authored as annotations survive into the generated `validate...` methods if you map them to OCL/EMF-validation annotations, or live as runtime `EValidator` contributions if you keep them in Groovy/SpEL.

Recommendation: ship **Groovy closures as the first constraint language** (already on classpath, IDE-checkable), add **SpEL behind the same SPI** as the lightweight option, and keep OCL interop in mind but don't gate on it.

---

## 9. Recommended sequencing

*Revised: lead with the `CompiledScript` carrier (smallest thing that works); hold the `Script` EClass as a later upgrade once the §11 unification earns its keep.*

1. Adopt `ResourceContentsHandler<T>` (single-typed, stream-based) with `CapabilityLoader` + `Requirement` dispatch and `Order`-based specificity; use `adapt(...)` / `adaptToResourceEObjectContentsHandler(...)` for non-`EObject` or `EObject`-specialized handler values. *(Replaces the earlier `ResourceContentsFilter<T,V>` plan; the single-typed handler plus `adapt` covers the same cases.)*
2. Build the terminal normalize+validate stage (language-independent).
3. Implement the `.groovy` source stage (read + compile to `CompiledScript`, do not evaluate) and the `.pm` transform stage (resolve upstream, bind factory + helpers per call, `eval`). *(See §4 skeletons.)*
4. Ship the PM-model Groovy builder with `@DelegatesTo` + `@CompileStatic`, id-based and cross-file (`resourceSet`/`baseURI`) referencing.
5. Add Groovy-closure constraints + `EValidator`; surface via the terminal stage.
6. Add SpEL behind a `ConstraintLanguage` SPI.
7. `.ecore`/`.genmodel` save + headless Java generation wired into the CLI.
8. Kotlin (`.kts`) as an opt-in module; rewrite the DSL surface with lambda-with-receiver + `@DslMarker`.
9. (Optional) GraalJS source stage.
10. *(Later, optional)* Introduce the `Script` EClass (abstract) + `GroovyScript` wrapping the `CompiledScript`, and switch the carrier to it when activity logic, NSML rule bodies, serializable behavior, or constraint reuse make an `EObject` carrier worthwhile. *(See §11; §4 "Later" skeleton shows the swap.)*

---

## 10. Open questions

- **Trust model for executable model files.** Sandbox profile (`SecureASTCustomizer`) for untrusted Groovy, or document "only load model scripts you trust"? Affects whether `.pm.groovy` is safe to accept from email/PRs.
- **Determinism convention.** State explicitly that authoring scripts must be pure builders; side-effecting enrichment belongs in downstream handlers.
- **Save round-trip fidelity.** Implementing `ResourceContentsHandler.save(...)` for a scripting surface means *generating Groovy/Kotlin source from a model*. Is save-to-script in scope, or is the script surface load-only (save goes to `.ecore`/`.xmi`)? Recommend load-only initially (the default `save` throws `UnsupportedOperationException`); document it.
- **One builder vs per-model builders.** Generic `FactoryBuilderSupport` (works for any `EPackage`, weaker IDE support) vs hand-written PM-model builder (best IDE support, per-model cost). Recommend hand-written for PM, generic fallback for arbitrary Ecore.

---

## 11. Addendum — Script as a first-class EClass (executable resources)

**Proposal.** Put the scripting engine *behind an `EClass`*. A `.groovy`, `.js`, or `.spel` resource loads to an **`EObject` that is an instance of a `Script` class** holding the source, bound to its engine, exposing "set variables / evaluate", rather than to a raw `ScriptEngine` or a bare `CompiledScript` (the §4 carrier) or an already-evaluated value. Loading **compiles/parses; it does not necessarily evaluate**. A `.spel` factory does this through Spring's `ExpressionParser` directly, without the `javax.script` API.

**Assessment: the better primitive, deferred.** It is strictly more general than the bare `CompiledScript` carrier chosen for now in §4, and it unlocks a unification the carrier alone cannot reach: the `CompiledScript` path becomes a *special case* (load-time evaluation, carrier not retained) of this more general model. The decision (§4) is to ship the `CompiledScript` carrier first and upgrade to this `Script` EClass when the unification below starts to pay (activity logic, NSML rule bodies, serializable behavior, constraint reuse). What follows is the design to grow into, not a blocker for the first cut.

### Why it's the right primitive

- **The pipeline stays in `EObject`-land.** A `Script` instance is an `EObject`, so the inter-stage handoff carries `EObject`s like every other stage. This is what keeps a non-`EObject` handler value optional rather than load-bearing (see §4, the handler contract).
- **Load-time and run-time evaluation become the same mechanism, at different lifecycle points.** For a DSL surface (`.pm.groovy`) the `.pm` stage binds `factory`/`resourceSet`, calls `eval`, and returns the model — evaluation at *load*. For activity logic, the `Script` is **retained in the model** and evaluated at *execution* (per token / per input) — evaluation at *run*. One class, two call sites.
- **It composes into other models.** An OpGraph / Draw.io executable-diagram activity can **contain** a `Script`; an NSML rule body can **be** a `Script`; an Ecore constraint (§8) can **reference** a `Script`. Because it is an `EObject`, it is serializable, diffable, documentable, and reachable by the same tooling as the rest of the model.
- **SpEL fits cleanly without JSR-223.** `SpelScript` parses once (`ExpressionParser`) and evaluates via `Expression.getValue(context)`; variables are SpEL `#vars` on a per-call `EvaluationContext`, root object is the element. `spring-expression` + `spring-core` only — a small, contained dependency.

### Shape

```
abstract Script                       // EClass
    source   : EString
    language : EString  (or ScriptLanguage enum / engine reference)
    // transient: compiled form cached after first compile()
    op compile()                      // idempotent; caches CompiledScript / Source / Expression
    op eval(context) : EJavaObject    // bindings passed per call

GroovyScript    : Script              // JSR-223, Compilable
GraalJsScript   : Script              // GraalJS polyglot (the ".js" surface)
SpelScript      : Script              // Spring ExpressionParser — no javax.script
Jsr223Script    : Script              // generic base, any engine by name/extension
```

Resource factories: `.groovy → GroovyScript`, `.js → GraalJsScript`, `.spel → SpelScript`; `resource.contents == [script]`.

### What it means for the handler contract

Your read is correct: with `Script` as an `EObject`, no handler needs a non-`EObject` value, so `adapt(...)` (the §4 escape hatch) is **not required by scripting**. Keep it available anyway, justified independently: typed clients that legitimately want a non-`EObject` result (a domain POJO, a byte buffer, a specific Java type) where an `EObject` wrapper is artificial or impractical. Net: scripting now **demonstrates** the `EObject -> EObject` default (`ResourceContentsHandler<Script>`, then `ResourceContentsHandler<List<EObject>>`) instead of being the exception that forces a non-`EObject` carrier; `adapt` remains the deliberate escape hatch for the cases you flagged.

### The unification (the real payoff)

One `Script` abstraction serves **four** consumers — model/schema DSL, workflow **activity logic** (Draw.io executable diagrams / OpGraph), **NSML** rule bodies, and validation **constraints** (§8). A developer fluent in a single language (Groovy, or JS via GraalJS) authors all four in that language. This is a direct hit on DSL proliferation: one expression substrate instead of a bespoke sub-language per surface, with "right-sized" power per use (a less-powerful-but-simpler authoring surface where that is appropriate). It reinforces the "think in one language" thesis rather than fragmenting authors across creole dialects.

### Refinements to bake in

1. **Per-eval bindings, not shared mutable state.** A `setVariable` that mutates a shared `Script` is a race the moment activity logic runs concurrently. Prefer `eval(bindings)` with a fresh `ScriptContext` / `EvaluationContext` per call. If a `setVariable` convenience is kept, scope it to single-threaded author-time DSL use and document that bound