Converts input stream to String using context charset, which defaults to ``UTF-8``, interpolates, and converts back to input stream using context charset.

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
    