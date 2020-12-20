Generates HTML from markdown. Use ``styled-markdown`` to wrap HTML into a DIV with ``markdown-body`` class.

### Example

#### YAML specification

```yaml
mustache: 'Hello, {{name}}!'
```

The above specification uses an in-line template. Templates can also be loaded from [resources](resource.html) or other sources, e.g. by making an [HTTP call](http-call.html), e.g.:

```yaml
mustache: 
    resource: https://nasdanika.org/hello-mustache-template.txt
```

#### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object mustache = loader.loadYaml(specURL, monitor);
		
Context context = Context.singleton("name", "World");		
		
InputStream result = callSupplier(context, monitor, mustache);
assertEquals("Hello, World!", Util.toString(context, result));
```                
