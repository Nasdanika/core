Converts input stream to String using context charset, which defaults to ``UTF-8``, interpolates, and converts back to input stream using context charset.

Interpolator can take a single value or a singleton map as configuration or a multi-value map.
In the first case the value will be treated as interpolator source content.

In the second case the multi-value map supports the following configuration keys:

* ``source`` - interpolator source content, the same as in the case of the single-value configuration or singleton map.
* ``base`` - optional base URL for resolving include and image URL's. The base itself is resolved relative to the specification resource URL.
* ``process-includes`` - if ``true`` (default) processes:
    * ``${{embedded-image/<image type>/<image url>}}`` - reads image from the URL resolved relative to the base URL. Encodes as an embedded image. Example: ``${{embedded-image/png/logo.png}}``.
    * ``${{include/<resource url>}}`` - reads, interpolates, and includes resource at the resource URL resolved relative to the base URL. Example: ``${{include/report.html}}``.
    * ``${{include-markdown/<resource url>}}`` - reads a resource at the resource URL resolved relative to the base URL. Renders Markdown to HTML. Interpolates and includes. Example: ``${{include-markdown/report.md}}``.
    * ``${{include-styled-markdown/<resource url>}}`` - reads a resource at the resource URL resolved relative to the base URL. Renders Markdown to HTML. Interpolates, wraps into a DIV with ``markdown-body`` class, and includes. Example: ``${{include-styled-markdown/report.md}}``.

### Example

#### YAML specification

```yaml
interpolator:
    resource: hello.txt
```

#### hello.txt resource

```
Hello, ${name}!
```

#### Java code

```java
ObjectLoader loader = new org.nasdanika.exec.Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object interpolator = loader.loadYaml(specURL, monitor);
		
Context context = Context.singleton("name", "World");		
		
String result = Util.toString(context, callSupplier(context, monitor, interpolator));
assertEquals("Hello, World!", result);
```
    