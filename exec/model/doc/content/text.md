Loads resource from a URL specified as resource value. The value is interpolated if ``interpolate`` is set to ``true``, and then is resolved relative to the base URL.
Use ``classpath`` scheme to load classloader resources. E.g. ``classpath:org/nasdanika/exec/some-resource.md``

### Examples

#### String

```yaml
content-resource: hello.txt
```

#### Map

```yaml
content-resource:
   description: Full resource definition
   location: ${my-resources}/hello.txt 
   interpolate: true
```

#### Classpath resource

```yaml
content-resource: classpath:org/nasdanika/exec/gen/tests/content/hello.txt
```
