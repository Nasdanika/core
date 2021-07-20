Named container of other resources, a folder/directory.

Configured with the following keys:

* ``contents`` - a list of contained resources.
* ``name`` - container name, interpolated.
* ``reconcile-action`` - defines an action to take if a container with given name already exists. Default action is ``OVERWRITE``:
    * ``KEEP`` - Keep the existing container, discard generated resources.
    * ``APPEND`` - Append resources to existing.
    * ``OVERWRITE`` - Delete the existing container and create a new one.
    * ``CANCEL`` -  Cancel generation if a container already exists.

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
    