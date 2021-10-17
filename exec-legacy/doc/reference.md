Reference value shall be a string containing a URL of resource to load. E.g. a component specification to load and include into the referencing specification.
The URL is resolved relative to the base URL. Use ``classpath`` protocol to load classloader resources. E.g. ``classpath://org/nasdanika/exec/some-spec.yml``.
References allow to assemble specifications from multiple specification resources or externalize scripts. 

```yaml
reference: mapper-spec.yml
```