Calls a Java method and returns the result. Currently can be used as content. Support of consumer and command will be implemented in the future releases.

Can be configured with a String or a Map. 

In the first case the string is a fully qualified class name. The class is loaded and then is instantiated using the default constructor.

See the "Load specification" for the second case. 

Constructor and method are selected by matching the number of parameters. The first matched method or constructor is selected for invocation and then an attempt is made to convert
arguments to constructor/method types. 

### Examples

#### Default property

Class implements Supplier.

```yaml
exec-call: org.nasdanika.exec.gen.tests.TestCall$CallSupplier
```

#### Static method

```yaml
exec-call:
  class: org.nasdanika.exec.gen.tests.TestCall$CallTarget
  method: helloWorld
```

#### Constructor and method arguments

``` yaml
exec-call:
  class: org.nasdanika.exec.gen.tests.TestCall$CallTarget
  method: greet
  init:
    content-text: Galaxy
  arguments:
    content-text: Hello
```
 