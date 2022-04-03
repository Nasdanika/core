Text content defined in YAML.

### Examples

#### Full definition

```yaml
content-text:
   description: Full text definition
   interpolate: false
   content: Hello ${token}.
```

#### Short definition

Just content (default feature):

##### Single line

```yaml
content-text: World
```

##### Multi-line

```yaml
content-text: |+2
  Line 1.
  Line 2.
```

