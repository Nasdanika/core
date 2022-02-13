An example of a service YAML definition:

```yaml
service:
  flow-service:
    name: Install stuff
    target: nasdanika://flow/test/resources/ide/services/install 
    documentation:
      content-interpolator:
        #process-includes: false
        source:
          content-markdown:
            style: true
            source:
              content-resource: install.md      
```