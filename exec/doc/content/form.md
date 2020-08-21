Encodes a map of values as URL encoded form.
Can be used to generate body for [HTTP Call](http-call.html).
Map values can be data entries or collections/lists. Nested maps are not supported.

Example:

```yaml
form:
    name: icon.gif
    data:
        base64:
            resource: https://www.nasdanika.org/resources/images/ecore/EClass.gif
    custom-multi-value-attribute:
        - value-one
        - value-two
```                
