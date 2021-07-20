Makes an HTTP call and returns response as InputStream.

Configuration keys:
	
* ``method`` - [HTTP method](https://en.wikipedia.org/wiki/Hypertext_Transfer_Protocol#Request_methods) as defined in ${javadoc/java.net.HttpURLConnection#setRequestMethod(java.lang.String)}, defaults to ``GET``.
* ``url`` - Request URL, interpolated.
* ``headers`` - An optional map of request headers.
* ``body`` - An optional request body.
* ``success-code`` - Success code, defaults to ``200``.
* ``connect-timeout`` - Connect timeout in seconds. Defaults to ``60``.
* ``read-timeout`` - Read timeout in seconds. Defaults to ``60``.
* ``trust-all-certificates`` - if ``true`` all SSL certificates are trusted. Default is ``false``. Can be used with self-signed certificates.
* ``verify-host`` - if ``false`` host name is not verified when establishing an SSL connection. Default is ``true``. 

### Example

#### YAML specification

```yaml
http:
   url: ${nasdanika}/hello-world.txt
```

or, because ``url`` is the only defined attribute:

```yaml
http: ${nasdanika}/hello-world.txt
```

HTTP Call with just URL as shown in the snippets above has the same behavior as [resource](resource.html) with a URL with ``http(s)`` protocol. ``resource`` supports additional protocols.

#### Java code
 
```java
ObjectLoader loader = new org.nasdanika.exec.Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object httpCall = loader.loadYaml(specURL, monitor);

// For token replacement in the URL		
Context context = Context.singleton("nasdanika", "https://nasdanika.org");		

InputStream response = callSupplier(context, monitor, httpCall);
``` 