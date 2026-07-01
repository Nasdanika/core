# Saving DSL resources

By default a Groovy DSL resource is **read-only**: it loads a `.groovy` script into an EMF model,
but saving it throws `UnsupportedOperationException`. This is the right default because the script -
not the in-memory model - is the source of truth, and there is no general way to serialize an
arbitrary model back into DSL syntax.

A script opts into saving by registering an **`onSave`** callback. Because the callback is defined in
the script itself, it can persist wherever the script's data actually lives: back into the `.groovy`
source (a *self-writing script*), or into an external system such as Jira or a SQL database.

## The `onSave` callback

```groovy
onSave { source, outputStream, options ->
    // ...
}
```

The callback receives three arguments:

| Argument       | Description                                                                                     |
|----------------|-------------------------------------------------------------------------------------------------|
| `source`       | The original `.groovy` script text this resource was loaded from.                               |
| `outputStream` | The target the content is written to (the resource URI - i.e. the `.groovy` file - by default). |
| `options`      | The save options `Map` passed to `Resource.save(...)`.                                           |

Registering a callback replaces any previously registered one - the last `onSave` wins. If no
callback is registered, `save` throws `UnsupportedOperationException`.

> **Truncation caveat.** EMF opens the output stream against the resource URI (the `.groovy` file)
> *before* the callback runs, which truncates the file immediately. A callback that writes nothing
> therefore leaves the source **empty**. Write `source` back to preserve it.

## Patterns

### 1. Verbatim write-back with side effects

The common case for scripts that source their data from an external system: do the real persistence
as a side effect, then write the source back unchanged so the `.groovy` file is preserved.

```groovy
ePackage {
    name 'familymodel'
    nsURI 'https://family.models.nasdanika.org'
    // ...
}

onSave { source, outputStream, options ->
    // Real persistence lives in the side effect - e.g. push to Jira or a database.
    jiraClient.updateIssues(dsl.roots)

    // Preserve the script file.
    outputStream.write(source.getBytes('UTF-8'))
}
```

### 2. Self-modifying script

The script rewrites its own source, e.g. appending DSL fragments for newly discovered or modified
objects so the next load picks them up.

```groovy
onSave { source, outputStream, options ->
    def fragment = discoverNewPeople().collect { person ->
        """
        eClass {
            name '${person.name}'
        }
        """.stripIndent()
    }.join('\n')

    outputStream.write(source.getBytes('UTF-8'))
    outputStream.write(fragment.getBytes('UTF-8'))
}
```

### 3. Read-only (default)

Register no callback. Saving the resource throws `UnsupportedOperationException`.

```groovy
ePackage {
    name 'savemodel'
    nsURI 'urn:test-save'
    // ... no onSave block
}
```

## Triggering a save

Saving goes through the standard EMF `Resource.save(...)` API. Note the two forms:

```java
Resource groovyResource = resourceSet.getResource(
        URI.createFileURI("family.ecore.groovy"), true);

// (a) Save to the resource URI (the .groovy file). EMF opens - and truncates - the file first,
//     so an onSave callback must write `source` back to avoid emptying it.
groovyResource.save(null);

// (b) Save to a caller-supplied stream. No truncation of the source file; useful for tests or
//     for capturing the written content.
ByteArrayOutputStream out = new ByteArrayOutputStream();
groovyResource.save(out, null);
String written = out.toString(StandardCharsets.UTF_8);
```

## How it works

- On **load**, the handler buffers the script source (the source handler consumes the stream to
  compile it and does not retain the text) and stores it, then evaluates the script - which may
  register an `onSave` callback via the `DslContext`.
- On **save**, the handler delegates to `DslContext.save(source, outputStream, options)`, which
  invokes the callback. If none was registered it throws `UnsupportedOperationException`, matching the
  default `ResourceContentsHandler.save` behaviour.
- A single handler instance serves both load and save for a resource's lifetime, so the captured
  source and callback are available at save time.
