An example of artifact YAML definitions, lines 5+:

```yaml
artifacts:
  reference-materials:
    name: Reference Materials
    description: Reference materials external to enterprise
    children:
      togaf-library:
        name: TOGAF Library
        documentation:
          content-markdown:
            source:
              content-text: "[TOGAF Library](https://publications.opengroup.org/togaf-library)"
      architecture-frameworks:
        name: Architecture frameworks
        documentation:
          content-markdown:
            source:
              content-text: Other architecture frameworks.
```