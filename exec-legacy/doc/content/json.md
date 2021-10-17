Converts contained map to a [JSON](https://en.wikipedia.org/wiki/JSON) input stream.
Can be used, for example, in [HTTP Call](http-call.html) body.

### Example

#### YAML specification

```yaml
json:
    name: icon.gif
    data:
        base64:
            resource: https://www.nasdanika.org/resources/images/ecore/EClass.gif
    custom-multi-value-attribute:
        - value-one
        - value-two
```

#### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object json = loader.loadYaml(specURL, monitor);
Context context = Context.EMPTY_CONTEXT;
InputStream result = callSupplier(context, monitor, json);
System.out.println(Util.toString(context, result));
```                

#### JSON result

```json
{
    "name": "icon.gif",
    "data": "R0lGO...truncated...ADs=",
    "custom-multi-value-attribute": [
        "value-one",
        "value-two"
    ]
}
```