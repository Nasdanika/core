An example of a participant YAML definitions:

```yaml
flow-package:
  uri: nasdanika://agile/core
  description: Generic agile software development.
  modifiers: abstract 
  participants:
    product-owner:
      name: Product Owner
      documentation: 
        content-markdown:
          source:
            content-text: Creates and ``grooms`` stories.
    developer:
      name: Developer
      description: Implements stories and tests in their local environment
    tester:
      name: Tester
      description: Tests in runtime environment
```