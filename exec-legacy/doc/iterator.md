Iterator is configured with a map of context property names to target components. It allows to execute target components zero or more times depending on the context property type:

* If iterator is blank then generator is executed once using the current generation context.
* If iterator is not blank its value is used to get a property from the current generation context. Depending on the property value the generator is executed zero or more times:
    * null - in this case iterator value is used as a prefix to create a sub-context to be used by the generator. E.g. if iterator value is ``my-component/`` then ``my-property`` property of the sub-context maps to ``my-component/my-property`` property of the parent context.
    * boolean ``false`` - generator is not executed.
    * boolean ``true`` - generator is executed once with the current context.
    * single value (scalar) - generator is executed once with the current context and value available via ``data`` context property.
    * list - generator is executed once for each list element with element value being processed as explained here.
    * map - the map values are interpolated recursively by the current context. Then the map is wrapped into a context which is used to execute the generator. The resulting context is composed (chained) with the current context. I.e. the map properties create a new scope and shadow the properties with the same name of the current context. Other properties are still  accessible.

### Examples

#### List of values

This example shows iteration over a list of strings.

##### YAML Specification

```yaml
for-each:
   a/a2: ' * ${data} * '
```

##### YAML context

```yaml
a:
   a1:
      a11: v11
      a12: v12
   a2:
      - uno
      - dos
      - tres
b:
   b1:
      b11: 
         b111: w111
         b112: w112
      b12: w12
   b2:       
      b21: w21
      b22: w22
```

##### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object iterator = loader.loadYaml(specURL, monitor);
assertEquals(Iterator.class, iterator.getClass());

Map<String, Object> yaml = new Yaml().load(contextURL);
Context context = Context.wrap(yaml::get);

InputStream result = callSupplier(context, monitor, iterator);
assertEquals(" * uno *  * dos *  * tres * ", Util.toString(context, result));
```   

#### List of maps

This example shows iteration over a list of maps and access to both map properties and inherited "global" properties.
In the below code ``k`` and ``l`` are properties from the iterated maps with values ``v301`` and ``v302`` in the first map, and ``v311`` and ``v312`` in the second.
``b/b2/b21`` is an inherited "global" property with the value ``w21``.

Therefore, the first iteration result is `` * v301-v302-w21 * `` and the second in `` * v311-v312-w21 ``.

##### YAML Specification

```yaml
for-each:
   a/a3: ' * ${k}-${l}-${b/b2/b21} * '
```

##### YAML context

```yaml
a:
   a1:
      a11: v11
      a12: v12
   a2:
      - uno
      - dos
      - tres
   a3:
      - 
         k: v301
         l: v302
      - 
         k: v311
         l: v312
b:
   b1:
      b11: 
         b111: w111
         b112: w112
      b12: w12
   b2:       
      b21: w21
      b22: w22
```

##### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object iterator = loader.loadYaml(TestExec.class.getResource("iterator-map-spec.yml"), monitor);
assertEquals(Iterator.class, iterator.getClass());
		
Map<String, Object> yaml = new Yaml().load(TestExec.class.getResourceAsStream("iterator-map-config.yml"));
Context context = Context.wrap(yaml::get);
		
InputStream result = callSupplier(context, monitor, iterator);
assertEquals(" * v301-v302-w21 *  * v311-v312-w21 * ", Util.toString(context, result));
```   
