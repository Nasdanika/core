Makes an HTTP call and returns response as InputStream.

Configuration keys:
	
* ``method`` - [HTTP method](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol#Request_methods) as defined in ${javadoc/java.net.HttpURLConnection#setRequestMethod(java.lang.String)}, defaults to ``GET``.
* ``url`` - Request URL, interpolated.
* ``headers`` - An optional map of request headers.
* ``body`` - An optional request body.
* ``success-code`` - Success code, defaults to ``200``.
* ``connect-timeout`` - Connect timeout in seconds. Defaults to ``60``.
* ``read-timeout`` - Read timeout in seconds. Defaults to ``60``.


### Example

#### YAML specification

```yaml
http:
   url: ${nasdanika}/hello-world.txt
```

#### Java code
 
```java
ObjectLoader loader = new org.nasdanika.exec.Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object httpCall = loader.loadYaml(specURL, monitor);

// For token replacement in the URL		
Context context = Context.singleton("nasdanika", "https://nasdanika.org");		

// Can also cast in the case of HTTP Call, but Loader.asSupplierFactory() is more flexible.		
Supplier<InputStream> supplier = Loader.asSupplierFactory(httpCall, null).create(context);
InputStream response = supplier.execute(monitor);
``` 