Mapper allows to replace execution context with a context constructed from an interpolated map. 
The map is specified in the mapper configuration. 
Mappers can be used in calling reusable/shared components.

Configuration keys:

* ``map`` - Map or string. Map is interpolated an wrapped into a context. String is treated as a URL of a YAML resource containing mapping definition. The URL is resolved relative to the base URL.
* ``target`` - Component or a list of components to be executed with the mapped context.

### Example

In this example the map defines property ``p1`` computed from the context property ``a/a1/a11``. 
The target is a string. In this string property ``p1`` is interpolated, but property ``a/a1/a11`` is not, because the mapped context replaces the mapper execution context.

#### YAML Specification

##### In-line map

```yaml
map:
   map:
      p1: '123_${a/a1/a11}'
   target: ' * ${p1} -- ${a/a1/a11} * '
```

##### Referenced map

```yaml
map:
   map: map.yml
   target: ' * ${p1} -- ${p2} * '
```

#### YAML context

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

#### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object mapper = loader.loadYaml(TestExec.class.getResource("mapper-spec.yml"), monitor);
assertEquals(Mapper.class, mapper.getClass());

Map<String, Object> yaml = new Yaml().load(TestExec.class.getResourceAsStream("iterator-config.yml"));
Context context = Context.wrap(yaml::get);

InputStream result = callSupplier(context, monitor, mapper);
assertEquals(" * 123_v11 -- ${a/a1/a11} * ", Util.toString(context, result));
```   