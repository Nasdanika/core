Calls a Java method and returns the result. Can be used as content, consumer, or command.

Can be configured with a String or a Map. 

In the first case the string is a fully qualified class name. The class is loaded using the classloader of the top-level loader and then is instantiated using the default constructor.

In the second case the following configuration keys are supported:

* ``class`` - fully qualified class name. The class is loaded using the classloader of the top-level loader as in the case of String configuration. Mutually exclusive with ``service`` and ``property``. One of ``class``, ``property``, or ``service`` is required.
* ``property`` - property name. Mutually exclusive with ``class`` and ``service``.
* ``service`` - fully qualified service class name. Mutually exclusive with ``class`` and ``property``. 
* ``init`` - an optional array of constructor arguments for the ``class``. Not applicable for ``property`` and ``service``. Arguments get converted to constructor parameter types if conversion is available. If conversion is not available, an exception is thrown.
* ``properties`` - a map injected into the instance in the ``class`` case if the instance implements ${javadoc/java.util.function.BiConsumer}.
* ``method`` - an optional method to call. In the ``class`` case the method can be static. If the method is static the class is not instantiated and if ``init`` or ``properties`` are present it results in an exception.
* ``arguments`` - an optional array of method arguments. Arguments get converted to method parameter types if conversion is available. If conversion is not available, an exception is thrown.

Constructors and methods are selected by matching the number of parameters. The first matched method or constructor is selected for invocation and then an attempt is made to convert . 

### Example

#### YAML specification

##### String configuration

```yaml
call: java.util.LinkedHashMap
```

##### Map configuration

```yaml
call:
   class: org.nasdanika...
```
#### Java code
 
```java
ObjectLoader loader = new org.nasdanika.exec.Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
... TODO ...
``` 