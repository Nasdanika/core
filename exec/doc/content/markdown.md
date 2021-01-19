Generates HTML from [Markdown](https://en.wikipedia.org/wiki/Markdown). Use ``styled-markdown`` to wrap HTML into a DIV with ``markdown-body`` class.

### Example

#### YAML specification

```yaml
markdown: |+2 
  An example of inline markdown.
  
  ### List 
  
  * Bullet 1
  * Bullet 2 
```

The above specification uses in-line markdown. Markdown source can also be loaded from [resources](resource.html) or other sources, e.g. by making an [HTTP call](http-call.html), e.g.:

```yaml
markdown: 
    resource: https://nasdanika.org/hello.md
```

#### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object markdown = loader.loadYaml(specURL, monitor);
		
Context context = Context.EMPTY_CONTEXT;
		
InputStream streamResult = callSupplier(context, monitor, markdown);
String stringResult = Util.toString(context, result);
```                
