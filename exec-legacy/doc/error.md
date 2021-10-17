Reports supplied message to the progress monitor at error level. 
Message (error value) shall be a content component or a list of components.

### Example

Progress messages mixed with content components.

#### YAML specification
```yaml
- Hello
- info: Info message
- warning: Be careful
- error: Watch out 
- World
```

#### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object progress = loader.loadYaml(TestExec.class.getResource("progress-spec.yml"), monitor);
Context context = Context.EMPTY_CONTEXT;
InputStream result = callSupplier(context, monitor, progress);
assertEquals("HelloWorld", Util.toString(context, result));
```