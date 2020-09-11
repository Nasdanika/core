* Converts source input stream to String using context charset, which defaults to ``UTF-8``, 
* Iterates over the patterns map, matches key as Java Regex patterns in the contents and replaces them with the interpolated values.
* Converts back to input stream using context charset.

### Configuration

* ``source`` - Contents source(s).
* ``patterns`` - A map with keys treated as Java Regex patterns used for matching and replacing with interpolated values.

### Example

#### YAML specification

```yaml
replace:
   source:
      resource: hello-replace-template.txt
   patterns:
      "World": ${name}
```

#### hello-replace-template.txt resource

```
Hello, World!
```

#### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object replace = loader.loadYaml(TestExec.class.getResource("replace-spec.yml"), monitor);
assertEquals(Replace.class, replace.getClass());
		
Context context = Context.singleton("name", "Universe");		
		
String result = Util.toString(context, callSupplier(context, monitor, replace));
assertEquals("Hello, Universe!", result);
```
    