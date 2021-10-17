Converts contained map to a [YAML](https://en.wikipedia.org/wiki/YAML) input stream.

### Example

#### YAML specification

```yaml
yaml:
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
Object yaml = loader.loadYaml(specURL, monitor);
		
Context context = Context.EMPTY_CONTEXT;
InputStream result = callSupplier(context, monitor, yaml);
System.out.println(Util.toString(context, result));
```

#### YAML result

```yaml
name: icon.gif
data: R0l...truncated...ADs=
custom-multi-value-attribute:
- value-one
- value-two
```