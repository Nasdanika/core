Configurator allows to inject additional properties to the context. 

Configured with a map with the following keys:

* ``properties`` - a map of properties to add to the the context.
* ``target`` - component to execute with the augmented context.

### Example 

```yaml
configure:
   properties:
      p1: 123
   target: ' * ${p1} -- ${a/a1/a11} * '
```

In this example target is a string which is interpolated with context properties. Property ``p1`` is specified in the configurator, and property ``a/a1/a11`` is taken from the context passed to the configurator. 