Conditional execution.

Supported keys:

* ``condition`` - required. Boolean or string. If string then condition is interpolated and evaluated as JavaScript with ``context`` and ``progressMonitor`` bindings for the context and progress monitor respectively. If result is true, then the ``then`` block is executed. Otherwise the ``else`` block is executed if it is present. 
* ``then`` - required. Executed if condition is evaluated to true.
* ``else`` - optional. Executed if condition is evaluated to something other than true. 

### Example

#### YAML specification

```yaml
if: 
   condition: ${age} >= 21
   then: Hello
   else: Good Bye
```

#### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object _if = loader.loadYaml(TestExec.class.getResource("if-spec.yml"), monitor);
Context context = Context.singleton("age", 3);
InputStream result = callSupplier(context, monitor, _if);
assertEquals("Good Bye", Util.toString(context, result));
```