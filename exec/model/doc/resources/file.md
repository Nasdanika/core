File is a named container of binary contents (bytes) which can be set and retrieved as an input stream.

### Example

```yaml
resources-container:
  name: my-container
  contents:
    - resources-file:
        name: my-file.txt
        contents:
          content-text: Hello, World!
```

    