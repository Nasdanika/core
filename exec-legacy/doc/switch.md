Executes one of cases or the default case based on result of expression evaluation

Supported keys:

* ``expression`` - required string. Interpolated and evaluated as JavaScript with ``context`` and ``progressMonitor`` bindings for the context and progress monitor respectively. Evaluation result is used to get one of cases from the case map and execute it. If there is no matching case then ``default`` block is executed if it is present. 
* ``case`` - An optional map. 
* ``default`` - optional. Executed if there is no matching case. 

### Examples

#### String cases

##### YAML specification

Note that the expression is enclosed in quotes so it is evaluated as string.

```yaml
switch:
   expression: "\"${word}\""
   case:
      one: uno
      two: dos
      three: tres
      "2": dos
      "3": tres
   default: no comprendo      
```

##### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object progress = loader.loadYaml(TestExec.class.getResource("switch-string-spec.yml"), monitor);

Context context = Context.singleton("word", "one");
assertEquals("uno", Util.toString(context, callSupplier(context, monitor, progress)));

context = Context.singleton("word", "two");
assertEquals("dos", Util.toString(context, callSupplier(context, monitor, progress)));

context = Context.singleton("word", "three");
assertEquals("tres", Util.toString(context, callSupplier(context, monitor, progress)));

context = Context.singleton("word", "quatro");
assertEquals("no comprendo", Util.toString(context, callSupplier(context, monitor, progress)));

context = Context.singleton("word", "2");
assertEquals("dos", Util.toString(context, callSupplier(context, monitor, progress)));

context = Context.singleton("word", "3");
assertEquals("tres", Util.toString(context, callSupplier(context, monitor, progress)));		
```

#### Integer cases

##### YAML specification


```yaml
switch:
   expression: ${number}
   case:
      1: uno
      2: dos
      3: tres      
   default: no comprendo      
```

##### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object progress = loader.loadYaml(TestExec.class.getResource("switch-number-spec.yml"), monitor);

Context context = Context.singleton("number", 1);
assertEquals("uno", Util.toString(context, callSupplier(context, monitor, progress)));

context = Context.singleton("number", 2);
assertEquals("dos", Util.toString(context, callSupplier(context, monitor, progress)));

context = Context.singleton("number", 3);
assertEquals("tres", Util.toString(context, callSupplier(context, monitor, progress)));

context = Context.singleton("number", 4);
assertEquals("no comprendo", Util.toString(context, callSupplier(context, monitor, progress)));		
```