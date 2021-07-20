File is a named container of binary contents (bytes) which can be set and retrieved as an input stream.

Configured with the following keys:

* ``contents`` - a content component, a string, or a list of content components.
* ``merger`` - ${javadoc/org.nasdanika.common.resources.Merger} implementation if ``reconcile-action`` is ``MERGE``. Optional there is a native merger.
* ``name`` - file name, interpolated.
* ``reconcile-action`` - defines an action to take if a file with given name already exists. Default action is ``OVERWRITE``:
    * ``KEEP`` - Keep the existing file, discard generated contents.
    * ``APPEND`` - Append generated contents to the existing contents.
    * ``MERGE`` - Merge old and new contents. 
    * ``OVERWRITE`` - Replace the existing contents with the generated contents.
    * ``CANCEL`` -  Cancel generation if a file already exists.

### Example

#### YAML specification

```yaml
container:
   name: test-container
   contents:
      - file:
         name: test-file
         contents: Hello, world!
```

#### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object container = loader.loadYaml(specURL, monitor);

Context context = Context.EMPTY_CONTEXT;		

EphemeralBinaryEntityContainer root = new EphemeralBinaryEntityContainer();
callConsumer(context, monitor, container, root);

BinaryResource testContainer = root.find("test-container", monitor);
assertTrue(testContainer.exists(monitor));
assertTrue(testContainer instanceof BinaryEntityContainer);

BinaryResource testFile = root.find("test-container/test-file", monitor);
assertTrue(testFile.exists(monitor));
assertTrue(testFile instanceof BinaryEntity);		
assertEquals("Hello, world!", Util.toString(context, ((BinaryEntity) testFile).getState(monitor)));
```
    