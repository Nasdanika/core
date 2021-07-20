try-catch-finally block.

Supported keys:

* ``try`` - required. A component or a list of components to execute.
* ``catch`` - optional. A component or a list of components to execute if the try component(s) execution fails. If not present and try fails the entire block fails, but finally still gets executed. Information about the exception which caused catch execution is available to the catch components via the following context properties:
    * ``error`` - exception instance.
    * ``error/type`` - fully qualified exception class name.
    * ``error/message`` - exception message.
* ``finally`` - optional. A component or a list of components to always execute after try and catch.

Components in all blocks shall be of the same type, or adaptable to the same type - resource, content, or command.
In the case of content components output produced by the ``try`` if it succeeds or ``catch`` if it is present and the ``try`` fails is concatenated with the content produced by the ``finally`` if it is present.

If ``try`` fails it gets rolled back.

### Example

A block of content components.

```yaml
block:
   try: 
      http: https://nasdanika.org/no-such-path
   catch: Erroneous ${error}      
   finally: World
```