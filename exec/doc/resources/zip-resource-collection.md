Zip resource collection allows to load resources into a container from a zip input stream.
Resources can be selectively interpolated. 

Configured with the following keys:

* ``contents`` - a content component, or a list of content components. InputStream produced by each component is treated as a ${javadoc/java.util.zip.ZipInputStream} from which entries are loaded. 
* ``merger`` - ${javadoc/org.nasdanika.common.resources.Merger} implementation if ``reconcile-action`` is ``MERGE``. Optional there is a native merger.
* ``name`` - container name, interpolated.
* ``reconcile-action`` - defines an action to take if a file with given name already exists. Default action is ``OVERWRITE``:
    * ``KEEP`` - Keep the existing file.
    * ``APPEND`` - Append file contents from the collection to the existing contents.
    * ``OVERWRITE`` - Replace/overwrite file contents.
    * ``CANCEL`` -  Cancel generation if a file already exists.
* ``path`` - Path in the underlying container to use as a source of collection elements. Path prefix is removed from element name. E.g. if path is ``images`` and there is a resource named ``images/logo.png`` then there will be a collection element ``logo.png``.
* ``prefix`` - Prefix to add to resource paths. E.g. if the prefix is ``gen_`` then ``logo.png`` will be added to the resource container as ``gen_logo.png``.
* ``includes`` - A list of [Ant path patterns](https://ant.apache.org/manual/dirtasks.html) specifying resources to include into the collection. String value or a list. If not present all resources are included.
* ``excludes`` - A list of [Ant path patterns](https://ant.apache.org/manual/dirtasks.html) specifying resources to exclude from the collection. String value or a list.
* ``interpolation-includes`` - A list of [Ant path patterns](https://ant.apache.org/manual/dirtasks.html) specifying included resources to interpolate. String value or a list. If not present none of included resources are interpolated.
* ``interpolation-excludes`` - A list of [Ant path patterns](https://ant.apache.org/manual/dirtasks.html) specifying included resources to exclude from interpolation. String value or a list.

### Example

#### YAML specification

```yaml
container:
   name: test-container
   contents:
      - zip-resource-collection:
         contents: 
            http: https://nasdanika.org/zip-resource-collection-test.zip
```

#### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object container = loader.loadYaml(specURL, monitor);

Context context = Context.EMPTY_CONTEXT;		

EphemeralBinaryEntityContainer root = new EphemeralBinaryEntityContainer();
callConsumer(context, monitor, container, root);

BinaryResource testContainer = root.find("test-container/templates", monitor);
assertTrue(testContainer.exists(monitor));
assertTrue(testContainer instanceof BinaryEntityContainer);

BinaryResource testFile = root.find("test-container/templates/hello-world.txt", monitor);
assertTrue(testFile.exists(monitor));
assertTrue(testFile instanceof BinaryEntity);		
assertEquals("Hello ${name}!", Util.toString(context, ((BinaryEntity) testFile).getState(monitor)));
```
    