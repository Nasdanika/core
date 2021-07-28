Configurator allows to inject additional properties to the context. 

Configured with a map with the following keys:

* ``properties`` - a map of properties to add to the the context.
* ``target`` - component to execute with the augmented context.

### Example 

```yaml
exec-configurator:
   target: 
     content-text: Hello, ${name} 
   properties: 
     name: 
       content-text: Extended ${name}
```

In this example target is a text which is interpolated with context properties. 
Property ``name`` uses property ``name`` passed to the configurator context and shadows it. 