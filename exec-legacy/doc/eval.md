Evaluates JavaScript script. Can be used to compute content values or in control flow components - [if](if.html) and [switch](switch.html).

Configuration value can be either string or map. If value is a string then it is interpolated and evaluated as JavaScript.

If value is a map it supports the following keys:

* ``script`` - required. Value can be a string or content component. In this case script is not interpolated - use bindings (see below) or ``context`` binding.
* ``bindings`` - a map. The map is interpolated and map entries are made available to the script as bindings (variables).

### Examples

#### String

##### YAML specification

```yaml
eval: 2 + 3 + ${age}
```

##### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object eval = loader.loadYaml(TestExec.class.getResource("eval-string-spec.yml"), monitor);
Context context = Context.singleton("age", 3);
InputStream result = callSupplier(context, monitor, eval);
assertEquals("8", Util.toString(context, result));
```

#### Map

##### YAML specification

```yaml
eval: 
   script:
      reference: script.js
   bindings:
      a: 2
      b: ${age}
```

##### script.js

```javascript
a < b
```

##### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object eval = loader.loadYaml(TestExec.class.getResource("eval-spec.yml"), monitor);
Context context = Context.singleton("age", 3);
InputStream result = callSupplier(context, monitor, eval);
assertEquals("true", Util.toString(context, result));
```