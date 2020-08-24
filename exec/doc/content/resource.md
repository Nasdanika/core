Loads resource from a URL specified as resource value. The value is interpolated and then is resolved relative to the base URL.

### Example

#### YAML specification

```yaml
resource: hello.txt
```

#### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object resource = loader.loadYaml(specURL, monitor);
assertEquals(Resource.class, resource.getClass());
		
Context context = Context.EMPTY_CONTEXT;		
InputStream result = callSupplier(context, monitor, resource);
assertEquals("Hello, ${name}!", Util.toString(context, result));
``` 