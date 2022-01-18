Element documentation. Documentation elements are adapted to ``SupplierFactory<InputStream>`` during generation. 
[Exec content](https://docs.nasdanika.org/modules/core/modules/exec/modules/model/content/package-summary.html) classes such as [Markdown](https://docs.nasdanika.org/modules/core/modules/exec/modules/model/content/Markdown.html) and [Interpolator](https://docs.nasdanika.org/modules/core/modules/exec/modules/model/content/Interpolator.html) can be used as documentation elements. 

### Examples

#### Plain text

##### Single line

```yaml
documentation:
  content-text: Single line documentation
```

##### Multi-line

```yaml
documentation:
  content-text: |+2
    Multi-line documentation, line 1
    Line 2.
```

##### Loaded from resource

```yaml
documentation:
  content-resource: doc.txt
```

#### Markdown

##### Single line

```yaml
documentation:
  content-markdown:
    style: true
    source:
      content-text: Single ``line`` documentation
```

##### Multi-line

```yaml
documentation:
  content-markdown:
    style: true
    source:
      content-text: |+2
        Line 1.        
        **Line 2**.
```

##### Loaded from resource

```yaml
documentation:
  content-markdown:
    style: true
    source:
      content-resource: doc.md
```
